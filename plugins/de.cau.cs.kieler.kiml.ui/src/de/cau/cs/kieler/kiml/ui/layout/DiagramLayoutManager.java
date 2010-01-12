/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Animation;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.layout.RecursiveLayouterEngine;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * Abstract superclass for managers of diagram layout. Contains static methods
 * to layout a specific diagram, and manages selection of an appropriate
 * subclass to translate the diagram into the internal {@code KGraph} structure.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public abstract class DiagramLayoutManager {

    /** maximal number of recursion levels for which progress is displayed. */
    public static final int MAX_PROGRESS_LEVELS = 3;
    
    /** list of registered diagram layout managers. */
    private static final List<DiagramLayoutManager> MANAGERS = new LinkedList<DiagramLayoutManager>();

    /** the layouter engine used to layout diagrams. */
    private RecursiveLayouterEngine layouterEngine = new RecursiveLayouterEngine();

    /**
     * Registers the given diagram layout manager.
     * 
     * @param manager an instance of a diagram layout manager
     */
    public static final synchronized void registerManager(final DiagramLayoutManager manager) {
        MANAGERS.add(manager);
    }

    /**
     * Performs layout on the given editor by choosing an appropriate layout
     * manager instance. Animation and a progress bar can be optionally turned on.
     * 
     * @param editorPart the editor for which layout is performed, or {@code
     *         null} if the diagram is not part of an editor
     * @param editPart the parent edit part for which layout is performed, or
     *         {@code null} if the whole diagram shall be layouted
     * @param animate if true, Draw2D animation is activated
     * @param progressBar if true, a progress bar is displayed
     */
    public static final void layout(final IEditorPart editorPart, final EditPart editPart,
            final boolean animate, final boolean progressBar) {
        layout(editorPart, editPart, animate, progressBar, false);
    }

    /**
     * Performs layout on the given editor by choosing an appropriate layout
     * manager instance. Animation, a progress bar, and layout of ancestors can
     * be optionally turned on.
     * 
     * @param editorPart the editor for which layout is performed, or {@code
     *         null} if the diagram is not part of an editor
     * @param editPart the parent edit part for which layout is performed, or
     *         {@code null} if the whole diagram shall be layouted
     * @param animate if true, Draw2D animation is activated
     * @param progressBar if true, a progress bar is displayed
     * @param layoutAncestors if true, layout is not only performed for the selected
     *         edit part, but also for its ancestors
     */
    public static final void layout(final IEditorPart editorPart, final EditPart editPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors) {
        for (DiagramLayoutManager manager : MANAGERS) {
            if (manager.supports(editorPart) || manager.supports(editPart)) {
                manager.doLayout(editorPart, editPart, animate, progressBar, layoutAncestors, false);
                return;
            }
        }
        throw new UnsupportedOperationException(Messages.getString("kiml.ui.15")
                + editorPart.getTitle() + ".");
    }
    
    /**
     * Performs layout on the given editor by choosing an appropriate layout
     * manager instance and caches the layout result. Animation and a progress bar
     * can be optionally turned on.
     * 
     * @param editorPart the editor for which layout is performed, or {@code
     *         null} if the diagram is not part of an editor
     * @param editPart the parent edit part for which layout is performed, or
     *         {@code null} if the whole diagram shall be layouted
     * @param animate if true, Draw2D animation is activated
     * @param progressBar if true, a progress bar is displayed
     * @return the cached layout result
     */
    public static final CachedLayout cacheLayout(final IEditorPart editorPart, final EditPart editPart,
            final boolean animate, final boolean progressBar) {
        for (DiagramLayoutManager manager : MANAGERS) {
            if (manager.supports(editorPart) || manager.supports(editPart)) {
                manager.doLayout(editorPart, editPart, animate, progressBar, false, true);
                return manager.getCachedLayout();
            }
        }
        throw new UnsupportedOperationException(Messages.getString("kiml.ui.15")
                + editorPart.getTitle() + ".");
    }
    
    /**
     * Performs layout on the given editor or edit part using this layout
     * manager. A progress bar indicating progress of the layout algorithm is
     * optionally shown to the user.
     * 
     * @param editorPart the editor for which layout is performed, or {@code
     *            null} if the diagram is not part of an editor
     * @param editPart the parent edit part for which layout is performed, or
     *            {@code null} if the whole diagram shall be layouted
     * @param animate if true, Draw2D animation is activated
     * @param progressBar if true, a progress bar is displayed
     * @param layoutAncestors if true, layout is not only performed for the selected
     *         edit part, but also for its ancestors
     * @param cacheLayout if true, the layout result is cached for the underlying model
     */
    public final void doLayout(final IEditorPart editorPart, final EditPart editPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors,
            final boolean cacheLayout) {
        final MonitoredOperation monitoredOperation = new MonitoredOperation() {
            protected IStatus execute(final IProgressMonitor monitor) {
                return doLayout(editorPart, editPart,
                        new KielerProgressMonitor(monitor, MAX_PROGRESS_LEVELS),
                        layoutAncestors, cacheLayout);
            }
        };
        
        final IStatus status;
        if (progressBar) {
            monitoredOperation.runMonitored();
            status = monitoredOperation.getStatus();
        } else {
            status = doLayout(editorPart, editPart, new BasicProgressMonitor(0),
                    layoutAncestors, cacheLayout);
        }
        MonitoredOperation.runInUI(new Runnable() {
            public void run() {
                if (animate) {
                    // apply the layout with animation
                    Animation.markBegin();
                    applyLayout();
                    int nodeCount = status == null ? 0 : status.getCode();
                    Animation.run(calcAnimationTime(nodeCount));
                } else {
                    // apply the layout without animation
                    applyLayout();
                }
            }
        }, false);
    }
    
    /** amount of work for a small task. */
    private static final int SMALL_TASK = 10;
    /** amount of work for the main task. */
    private static final int MAIN_TASK = 80;
    
    /**
     * Performs layout on the given editor or edit part using this layout
     * manager and a specific progress monitor.
     * 
     * @param editorPart the editor for which layout is performed, or {@code
     *            null} if the diagram is not part of an editor
     * @param editPart the parent edit part for which layout is performed, or
     *            {@code null} if the whole diagram shall be layouted
     * @param progressMonitor a progress monitor to which progress of the layout
     *            algorithm is reported
     * @param layoutAncestors if true, layout is not only performed for the selected
     *         edit part, but also for its ancestors
     * @param cacheLayout if true, the layout result is cached for the underlying model
     * @return a status indicating success or failure; if successful, the status
     *         contains the number of layouted nodes as code value
     */
    public final synchronized IStatus doLayout(final IEditorPart editorPart,
            final EditPart editPart, final IKielerProgressMonitor progressMonitor,
            final boolean layoutAncestors, final boolean cacheLayout) {
        try {
            progressMonitor.begin("Diagram layout", SMALL_TASK + MAIN_TASK + SMALL_TASK);
            LayoutServices layoutServices = LayoutServices.getInstance();

            // transform the diagram into a KGraph instance
            KNode layoutGraph = buildLayoutGraph(editorPart, editPart,
                    progressMonitor.subTask(SMALL_TASK), layoutAncestors);

            // notify layout listeners about the layout request
            layoutServices.layoutRequested(layoutGraph);

            // perform layout on the layout graph
            layouterEngine.layout(layoutGraph, progressMonitor.subTask(MAIN_TASK), layoutAncestors);
            if (progressMonitor.isCanceled()) {
                return new Status(IStatus.CANCEL, KimlUiPlugin.PLUGIN_ID, 0, null, null);
            }

            // transfer layout to the diagram
            transferLayout(progressMonitor.subTask(SMALL_TASK), cacheLayout);

            // notify layout listeners about the performed layout
            progressMonitor.done();
            layoutServices.layoutPerformed(layoutGraph, progressMonitor);

            // return a positive status including graph size
            int graphSize = countNodes(layoutGraph);
            return new Status(IStatus.OK, KimlUiPlugin.PLUGIN_ID, graphSize, null, null);

        } catch (Throwable exception) {
            progressMonitor.done();
            String message = Messages.getString("kiml.ui.1");
            if (layouterEngine != null && layouterEngine.getLastLayoutProvider() != null) {
                message += " (" + layouterEngine.getLastLayoutProvider().getClass().getSimpleName()
                        + ")";
            }
            return new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID, message, exception);
        }
    }

    /**
     * Counts the total number of children in the given node, including deep
     * hierarchies.
     * 
     * @param node parent node
     * @return number of children and grandchildren in the given parent
     */
    private int countNodes(final KNode node) {
        int count = 0;
        for (KNode child : node.getChildren()) {
            count += countNodes(child) + 1;
        }
        return count;
    }

    /** minimal animation time for diagram layout. */
    private static final int MIN_ANIMATION_TIME = 500;
    /** maximal animation time for diagram layout. */
    private static final int MAX_ANIMATION_TIME = 4000;
    /** factor for animation time calculation. */
    private static final double ANIM_FACT = 100.0;
    
    /**
     * Calculates animation time for the given graph size.
     * 
     * @param graphSize total number of nodes in the graph
     * @return number of milliseconds to animate
     */
    public static int calcAnimationTime(final int graphSize) {
        int time = MIN_ANIMATION_TIME + (int) (ANIM_FACT * Math.sqrt(graphSize));
        return time <= MAX_ANIMATION_TIME ? time : MAX_ANIMATION_TIME;
    }

    /**
     * Determines whether this layout manager is able to perform layout for the
     * given editor.
     * 
     * @param editorPart an editor part
     * @return true if this layout manager supports the editor part
     */
    protected abstract boolean supports(IEditorPart editorPart);

    /**
     * Determines whether this layout manager is able to perform layout for the
     * given edit part.
     * 
     * @param editPart an edit part
     * @return true if this layout manager supports the edit part
     */
    protected abstract boolean supports(EditPart editPart);

    /**
     * Builds a KGraph instance for the given editor or edit part. The resulting
     * layout graph should reflect the structure of edit parts in the original
     * diagram.
     * 
     * @param editorPart the editor for which layout is performed, or {@code
     *            null} if the diagram is not part of an editor
     * @param editPart the parent edit part for which layout is performed, or
     *            {@code null} if the whole diagram shall be layouted
     * @param progressMonitor a monitor to keep track of progress
     * @param layoutAncestors if true, layout is not only performed for the selected
     *         edit part, but also for its ancestors
     * @return a layout graph instance
     */
    protected abstract KNode buildLayoutGraph(IEditorPart editorPart, EditPart editPart,
            IKielerProgressMonitor progressMonitor, boolean layoutAncestors);

    /**
     * Transfers all layout data from the last created KGraph instance to the
     * original diagram. The diagram is not modified yet, but all required preparations
     * are performed.
     * 
     * @param progressMonitor a monitor to keep track of progress
     * @param cacheLayout if true, the layout result is cached for the underlying model
     */
    protected abstract void transferLayout(IKielerProgressMonitor progressMonitor, boolean cacheLayout);
    
    /**
     * Applies the transferred layout to the original diagram. This final step is where
     * the actual change to the diagram is done.
     */
    protected abstract void applyLayout();
    
    /**
     * Returns the cached layout for the last layout run.
     * 
     * @return the last cached layout
     */
    protected abstract CachedLayout getCachedLayout();

}
