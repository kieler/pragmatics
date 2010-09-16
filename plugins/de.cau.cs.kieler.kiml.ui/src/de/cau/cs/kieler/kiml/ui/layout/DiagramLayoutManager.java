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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Animation;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.RecursiveLayouterEngine;
import de.cau.cs.kieler.kiml.ui.IEditorChangeListener;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;

/**
 * Abstract superclass for managers of diagram layout.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public abstract class DiagramLayoutManager {

    /** maximal number of recursion levels for which progress is displayed. */
    public static final int MAX_PROGRESS_LEVELS = 2;

    /** the debug canvas to use. */
    private static final DebugCanvas DEBUG_CANVAS = new DebugCanvas();
    /** the layouter engine used to layout diagrams. */
    private static final RecursiveLayouterEngine LAYOUTER_ENGINE = new RecursiveLayouterEngine(
            DEBUG_CANVAS);
    /** the configured priority of the layout manager. */
    private int priority;


    /**
     * Return the manager priority.
     * 
     * @return the priority
     */
    public final int getPriority() {
        return priority;
    }

    /**
     * Set the manager priority.
     * 
     * @param thepriority the priority to set
     */
    public final void setPriority(final int thepriority) {
        this.priority = thepriority;
    }

    /**
     * Performs layout on the given editor or edit part using this layout
     * manager. A progress bar indicating progress of the layout algorithm is
     * optionally shown to the user.
     * 
     * @param editorPart
     *            the editor for which layout is performed, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the parent edit part for which layout is performed, or {@code
     *            null} if the whole diagram shall be layouted
     * @param animate
     *            if true, Draw2D animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected edit
     *            part, but also for its ancestors
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     */
    public final void layout(final IEditorPart editorPart,
            final EditPart editPart, final boolean animate,
            final boolean progressBar, final boolean layoutAncestors,
            final boolean cacheLayout) {
        // perform layout with a progress bar
        if (progressBar) {
            final MonitoredOperation monitoredOperation = new MonitoredOperation() {
                // first phase: build the layout graph
                @Override
                protected void preUIexec() {
                    buildLayoutGraph(editorPart, editPart, layoutAncestors);
                }

                // second phase: execute layout algorithms
                @Override
                protected IStatus execute(final IProgressMonitor monitor) {
                    return layout(new KielerProgressMonitor(monitor,
                            MAX_PROGRESS_LEVELS), layoutAncestors);
                }

                // third phase: apply layout with animation
                @Override
                protected void postUIexec(final IStatus status) {
                    if (status.getSeverity() == IStatus.OK) {
                        int nodeCount = status == null ? 0 : status.getCode();
                        applyAnimatedLayout(animate, cacheLayout, nodeCount);
                    }
                }
            };
            monitoredOperation.runMonitored();

            // perform layout without a progress bar
        } else {
            MonitoredOperation.runInUI(new Runnable() {
                // first phase: build the layout graph
                public void run() {
                    buildLayoutGraph(editorPart, editPart, layoutAncestors);
                }
            }, true);
            // second phase: execute layout algorithms
            final IStatus status = layout(new BasicProgressMonitor(0), layoutAncestors);
            MonitoredOperation.runInUI(new Runnable() {
                // third phase: apply layout with animation
                public void run() {
                    int nodeCount = status == null ? 0 : status.getCode();
                    applyAnimatedLayout(animate, cacheLayout, nodeCount);
                }
            }, false);
        }
    }

    /**
     * Apply layout with or without animation.
     * 
     * @param animate
     *            if true, activate Draw2D animation
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     * @param nodeCount
     *            the number of nodes in the layouted diagram
     */
    public void applyAnimatedLayout(final boolean animate, final boolean cacheLayout,
            final int nodeCount) {
        // transfer layout to the diagram
        transferLayout(cacheLayout);
        if (animate) {
            // apply the layout with animation
            Animation.markBegin();
            applyLayout();
            Animation.run(calcAnimationTime(nodeCount));
        } else {
            // apply the layout without animation
            applyLayout();
        }
    }

    /**
     * Performs layout on the given editor or edit part using this layout
     * manager and a specific progress monitor. The layout graph must be built
     * before this method is called, and the layout must be applied after this
     * method returns.
     * 
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm
     *            is reported
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected edit
     *            part, but also for its ancestors
     * @return a status indicating success or failure; if successful, the status
     *         contains the number of layouted nodes as code value
     */
    public IStatus layout(final IKielerProgressMonitor progressMonitor,
            final boolean layoutAncestors) {
        try {
            LayoutServices layoutServices = LayoutServices.getInstance();

            // get the layout graph instance
            KNode layoutGraph = getLayoutGraph();

            // notify layout listeners about the layout request
            layoutServices.layoutRequested(layoutGraph);

            // perform layout on the layout graph
            DEBUG_CANVAS.setManager(this);
            LAYOUTER_ENGINE.layout(layoutGraph, progressMonitor, layoutAncestors);
            if (progressMonitor.isCanceled()) {
                return new Status(IStatus.CANCEL, KimlUiPlugin.PLUGIN_ID, 0,
                        null, null);
            }

            // notify layout listeners about the performed layout
            layoutServices.layoutPerformed(layoutGraph, progressMonitor);

            // return a positive status including graph size
            int graphSize = countNodes(layoutGraph);
            return new Status(IStatus.OK, KimlUiPlugin.PLUGIN_ID, graphSize,
                    null, null);

        } catch (Throwable exception) {
            String message = Messages.getString("kiml.ui.1");
            if (LAYOUTER_ENGINE.getLastLayoutProvider() != null) {
                message += " (" + LAYOUTER_ENGINE.getLastLayoutProvider().getClass()
                                .getSimpleName() + ")";
            }
            return new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID, message,
                    exception);
        }
    }

    /**
     * Counts the total number of children in the given node, including deep
     * hierarchies.
     * 
     * @param node
     *            parent node
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
     * @param graphSize
     *            total number of nodes in the graph
     * @return number of milliseconds to animate
     */
    public static int calcAnimationTime(final int graphSize) {
        int time = MIN_ANIMATION_TIME
                + (int) (ANIM_FACT * Math.sqrt(graphSize));
        return time <= MAX_ANIMATION_TIME ? time : MAX_ANIMATION_TIME;
    }
    
    /**
     * Returns the edit part associated with the given layout node. This is only valid after
     * {@link #buildLayoutGraph(IEditorPart, EditPart, boolean)} was called.
     * 
     * @param knode a node from the layout graph
     * @return the corresponding edit part, or {@code null}
     */
    public EditPart getEditPart(final KNode knode) {
        return null;
    }
    
    /**
     * Returns the layout node associated with the given edit part. This is only valid after
     * {@link #buildLayoutGraph(IEditorPart, EditPart, boolean)} was called.
     * 
     * @param editPart an edit part of the currently layouted diagram
     * @return the corresponding layout node, or {@code null}
     */
    public KNode getLayoutNode(final EditPart editPart) {
        return null;
    }
    
    /**
     * Returns the currently processed top level edit part. This is only valid after
     * {@link #buildLayoutGraph(IEditorPart, EditPart, boolean)} was called.
     * 
     * @return the currently processed edit part
     */
    public abstract EditPart getCurrentEditPart();

    /**
     * Determines whether this layout manager is able to perform layout for the
     * given editor.
     * 
     * @param editorPart
     *            an editor part
     * @return true if this layout manager supports the editor part
     */
    protected abstract boolean supports(IEditorPart editorPart);

    /**
     * Determines whether this layout manager is able to perform layout for the
     * given edit part.
     * 
     * @param editPart
     *            an edit part
     * @return true if this layout manager supports the edit part
     */
    protected abstract boolean supports(EditPart editPart);

    /**
     * Builds a KGraph instance for the given editor or edit part. The resulting
     * layout graph should reflect the structure of edit parts in the original
     * diagram.
     * 
     * @param editorPart
     *            the editor for which layout is performed, or {@code null} if
     *            the diagram is not part of an editor
     * @param editPart
     *            the parent edit part for which layout is performed, or {@code
     *            null} if the whole diagram shall be layouted
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected edit
     *            part, but also for its ancestors
     * @return a layout graph instance
     */
    public abstract KNode buildLayoutGraph(IEditorPart editorPart,
            EditPart editPart, boolean layoutAncestors);
    
    /**
     * Returns a layout inspector for the given edit part.
     * 
     * @param editPart an edit part
     * @return a layout inspector for the edit part
     */
    public abstract ILayoutInspector getInspector(EditPart editPart);

    /**
     * Transfers all layout data from the last created KGraph instance to the
     * original diagram. The diagram is not modified yet, but all required
     * preparations are performed.
     * 
     * @param cacheLayout
     *            if true, the layout result is cached for the underlying model
     */
    protected abstract void transferLayout(boolean cacheLayout);

    /**
     * Applies the transferred layout to the original diagram. This final step
     * is where the actual change to the diagram is done.
     */
    protected abstract void applyLayout();

    /**
     * Returns the last built layout graph.
     * 
     * @return the last built layout graph
     */
    public abstract KNode getLayoutGraph();

    /**
     * Returns the cached layout for the last layout run.
     * 
     * @return the last cached layout
     */
    protected abstract ICachedLayout getCachedLayout();
    
    /**
     * Register a listener for change of the active editor or active selection.
     * The default implementation does nothing.
     * 
     * @param editorPart editor to register to
     * @param listener listener to register
     */
    public abstract void addChangeListener(IEditorPart editorPart,
            IEditorChangeListener listener);

    /**
     * Remove a change listener from all editors for which it has registered.
     * The default implementation does nothing.
     * 
     * @param listener listener to remove
     */
    public abstract void removeChangeListener(IEditorChangeListener listener);
    
    /**
     * Returns the current selection for the given editor part.
     * 
     * @param editorPart an editor part
     * @return the current selection, or {@code null} if the selection cannot
     *     be determined
     */
    public abstract ISelection getSelection(IEditorPart editorPart);
    
}
