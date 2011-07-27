/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.diagram;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager;
import de.cau.cs.kieler.kiml.ui.util.DebugCanvas;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.kwebs.client.layout.RemoteGraphLayoutEngine;
import de.cau.cs.kieler.kwebs.client.preferences.Preferences;

/**
 * The entry class for automatic layout of graphical diagrams.
 * 
 * @author msp
 */
public class DiagramLayoutEngine {
    
    /** the singleton instance that can be used whenever layout needs to be performed. */
    public static final DiagramLayoutEngine INSTANCE = new DiagramLayoutEngine();
    
    /**
     * Perform layout on the given workbench part.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param animate
     *            if true, animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected diagram part, but also for
     *            its ancestors
     * @param zoom
     *            if true, automatic zoom-to-fit is activated
     * @return the layout mapping used in this session
     */
    public LayoutMapping<?> layout(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors,
            final boolean zoom) {
        return layout(workbenchPart, diagramPart, animate, progressBar, layoutAncestors, zoom, null);
    }
    
    /**
     * Perform layout on the given workbench part.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param animate
     *            if true, animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected diagram part, but also for
     *            its ancestors
     * @param zoom
     *            if true, automatic zoom-to-fit is activated
     * @param extraLayoutConfig
     *            an additional layout configuration to use, or {@code null}
     * @return the layout mapping used in this session
     */
    public LayoutMapping<?> layout(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors,
            final boolean zoom, final ILayoutConfig extraLayoutConfig) {
        DiagramLayoutManager<?> layoutManager = EclipseLayoutInfoService.getInstance().getManager(
                workbenchPart, diagramPart);
        if (layoutManager != null) {
            return layout(layoutManager, workbenchPart, diagramPart, animate, progressBar,
                    layoutAncestors, zoom, extraLayoutConfig);
        } else {
            throw new UnsupportedOperationException(Messages.getString("kiml.ui.15")
                    + workbenchPart.getTitle() + ".");
        }
    }

    /** maximal number of recursion levels for which progress is displayed. */
    private static final int MAX_PROGRESS_LEVELS = 2;

    /**
     * Perform layout on the given workbench part using the given layout manager.
     * 
     * @param <T> the type of diagram part that is handled by the given diagram layout manager
     * @param layoutManager
     *            a diagram layout manager
     * @param workbenchPart
     *            the workbench part for which layout is performed
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param animate
     *            if true, animation is activated
     * @param progressBar
     *            if true, a progress bar is displayed
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected diagram part, but also for
     *            its ancestors
     * @param zoom
     *            if true, automatic zoom-to-fit is activated
     * @param extraLayoutConfig
     *            an additional layout configuration to use, or {@code null}
     * @return the layout mapping used in this session
     */
    public <T> LayoutMapping<T> layout(final DiagramLayoutManager<T> layoutManager,
            final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors,
            final boolean zoom, final ILayoutConfig extraLayoutConfig) {
        final Maybe<LayoutMapping<T>> layoutMapping = Maybe.create();
        
        final MonitoredOperation monitoredOperation = new MonitoredOperation() {
            // first phase: build the layout graph
            @Override
            protected void preUIexec() {
                layoutMapping.set(layoutManager.buildLayoutGraph(workbenchPart,
                        layoutAncestors ? null : diagramPart));
            }

            // second phase: execute layout algorithms
            @Override
            protected IStatus execute(final IProgressMonitor monitor) {
                IKielerProgressMonitor kielerMonitor;
                if (monitor == null) {
                    kielerMonitor = new BasicProgressMonitor(0);
                } else {
                    kielerMonitor = new KielerProgressMonitor(monitor, MAX_PROGRESS_LEVELS);
                }
                return layout(layoutMapping.get(), diagramPart, kielerMonitor,
                        extraLayoutConfig, layoutAncestors);
            }

            // third phase: apply layout with animation
            @Override
            protected void postUIexec() {
                int animationTime = calcAnimationTime(layoutMapping.get(), animate);
                layoutManager.applyLayout(layoutMapping.get(), zoom, animationTime);
            }
        };

        if (progressBar) {
            // perform layout with a progress bar
            monitoredOperation.runMonitored();
        } else {
            // perform layout without a progress bar
            monitoredOperation.runUnmonitored();
        }
        
        return layoutMapping.get();
    }

    /** minimal animation time for diagram layout. */
    private static final int MIN_ANIMATION_TIME = 400;
    /** maximal animation time for diagram layout. */
    private static final int MAX_ANIMATION_TIME = 4000;
    /** factor for animation time calculation. */
    private static final double ANIM_FACT = 100.0;

    /**
     * Calculates animation time for the given graph size.
     * 
     * @param mapping a mapping of the layout graph
     * @param animate whether animation should be performed
     * @return number of milliseconds to animate, or 0 if no animation is desired
     */
    public static int calcAnimationTime(final LayoutMapping<?> mapping, final boolean animate) {
        if (animate) {
            int graphSize = countNodes(mapping.getLayoutGraph());
            int time = MIN_ANIMATION_TIME + (int) (ANIM_FACT * Math.sqrt(graphSize));
            return time <= MAX_ANIMATION_TIME ? time : MAX_ANIMATION_TIME;
        }
        return 0;
    }

    /**
     * Counts the total number of children in the given node, including deep hierarchies.
     * 
     * @param node
     *            parent node
     * @return number of children and grandchildren in the given parent
     */
    private static int countNodes(final KNode node) {
        int count = 0;
        for (KNode child : node.getChildren()) {
            count += countNodes(child) + 1;
        }
        return count;
    }

    /** the debug canvas to use. */
    private DebugCanvas debugCanvas = new DebugCanvas();
    /** the graph layout engine used to layout diagrams. */
    private IGraphLayoutEngine graphLayoutEngine = new RecursiveGraphLayoutEngine(debugCanvas);
    /** the graph layout engine used to layout diagrams remotely. */
    private IGraphLayoutEngine remoteGraphLayoutEngine = new RemoteGraphLayoutEngine();
    /** the layout options manager for configuration of layout graphs. */
    private LayoutOptionManager layoutOptionManager = new LayoutOptionManager();
    
    /**
     * Returns the currently used layout option manager.
     * 
     * @return the layout option manager
     */
    public LayoutOptionManager getOptionManager() {
        return layoutOptionManager;
    }
    
    /** property for layout context: the progress monitor that was used for layout. */
    public static final IProperty<IKielerProgressMonitor> PROGRESS_MONITOR
            = new Property<IKielerProgressMonitor>("layout.progressMonitor");
    
    /**
     * Perform layout on the given layout graph mapping.
     * 
     * @param mapping
     *            a mapping for the layout graph
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported
     * @param extraLayoutConfig
     *            an additional layout configuration to use, or {@code null}
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected diagram part, but also for
     *            its ancestors
     * @return a status indicating success or failure
     */
    public IStatus layout(final LayoutMapping<?> mapping, final Object diagramPart,
            final IKielerProgressMonitor progressMonitor, final ILayoutConfig extraLayoutConfig,
            final boolean layoutAncestors) {
        if (layoutAncestors) {
            // remove all parallel areas from the layout graph
            KGraphElement graphElem = mapping.getGraphMap().inverse().get(diagramPart);
            if (graphElem instanceof KNode) {
                KNode node = (KNode) graphElem;
                while (node.getParent() != null) {
                    KNode parent = node.getParent();
                    for (KNode child : parent.getChildren()) {
                        if (child != node) {
                            KimlUtil.excludeContent(child);
                        }
                    }
                    node = parent;
                }
            }
        }
        return layout(mapping, progressMonitor, extraLayoutConfig);
    }
    
    /** The preference store for the KIELER related remote layout options. */
    private IPreferenceStore preferenceStore = Preferences.getPreferenceStore();
    
    /**
     * Perform layout on the given layout graph mapping.
     * 
     * @param mapping
     *            a mapping for the layout graph
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported
     * @param extraLayoutConfig
     *            an additional layout configuration to use, or {@code null}
     * @return a status indicating success or failure
     */
    public IStatus layout(final LayoutMapping<?> mapping,
            final IKielerProgressMonitor progressMonitor, final ILayoutConfig extraLayoutConfig) {
        // configure the layout graph using a layout option manager
        if (extraLayoutConfig != null) {
            mapping.getLayoutConfigs().add(extraLayoutConfig);
        }
        layoutOptionManager.configure(mapping);
        
        mapping.setProperty(PROGRESS_MONITOR, progressMonitor);
        debugCanvas.setMapping(mapping);

        // the layout engine to be used
        IGraphLayoutEngine layouterEngine = graphLayoutEngine;
        try {
            // use local or remote layout, default is local
            boolean remoteLayout = false;            
            if (preferenceStore != null) {
                remoteLayout = preferenceStore.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
            }            
            if (remoteLayout) {
                layouterEngine = remoteGraphLayoutEngine;
            } else {
                layouterEngine = graphLayoutEngine;
            }            
            // perform layout on the layout graph
            layouterEngine.layout(mapping.getLayoutGraph(), progressMonitor);            
            // check for cancellation
            if (progressMonitor.isCanceled()) {
                return new Status(IStatus.CANCEL, KimlUiPlugin.PLUGIN_ID,  null);
            }
            // return a positive status
            return new Status(IStatus.OK, KimlUiPlugin.PLUGIN_ID, null);
        } catch (Throwable exception) {
            String message = Messages.getString("kiml.ui.1");
            if (layouterEngine.getLastLayoutProvider() != null) {
                message += " ("
                        + layouterEngine.getLastLayoutProvider().getClass().getSimpleName() + ")";
            }
            return new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID, message, exception);
        }
    }

}
