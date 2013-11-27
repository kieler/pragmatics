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
package de.cau.cs.kieler.kiml.service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.LayoutContext;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.service.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * The entry class for automatic layout of graphical diagrams.
 * Use this class to perform automatic layout on the content of a workbench part that contains
 * a graph-based diagram. The mapping between the diagram and the layout graph structure is managed
 * by a {@link IDiagramLayoutManager} implementation, which has to be registered using the
 * {@code layoutManagers} extension point.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating yellow 2012-07-05 review KI-18 by cmot, sgu
 */
public class DiagramLayoutEngine {
    
    /**
     * The singleton instance that can be used whenever layout needs to be performed.
     */
    public static final DiagramLayoutEngine INSTANCE = new DiagramLayoutEngine();
    
    /**
     * Property for the diagram layout manager used for automatic layout. This property is
     * attached to layout mappings created by the {@code layout} methods.
     */
    public static final IProperty<IDiagramLayoutManager<?>> DIAGRAM_LM
            = new Property<IDiagramLayoutManager<?>>("layoutEngine.diagramLayoutManager");
    /** preference identifier for debug graph output. */
    public static final String PREF_DEBUG_OUTPUT = "kiml.debug.graph";
    
    
    /** the layout options manager for configuration of layout graphs. */
    private final LayoutOptionManager layoutOptionManager = new LayoutOptionManager();
    
    /** the executor service used to perform layout operations. */
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    /** map of currently running layout operations. */
    private final Multimap<Pair<IWorkbenchPart, Object>, MonitoredOperation> runningOperations
            = HashMultimap.create();
    
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
     * Perform layout on the given workbench part. If zero or one layout configurator is passed,
     * the layout engine is executed exactly once. If multiple layout configurators are passed,
     * the layout engine is executed accordingly often, but the resulting layout is applied only
     * once. This is useful for composition of multiple algorithms that process only parts of
     * the graph.
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
     * @param extraLayoutConfigs
     *            list of additional layout configurators to use, or {@code null}
     * @return the layout mapping used in this session
     */
    public LayoutMapping<?> layout(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors,
            final boolean zoom, final List<ILayoutConfig> extraLayoutConfigs) {
        IDiagramLayoutManager<?> layoutManager = LayoutManagersService.getInstance().getManager(
                workbenchPart, diagramPart);
        if (layoutManager != null) {
            LayoutMapping<?> mapping = layout(layoutManager, workbenchPart, diagramPart, animate,
                    progressBar, layoutAncestors, zoom, extraLayoutConfigs);
            if (mapping != null) {
                mapping.setProperty(DIAGRAM_LM, layoutManager);
            }
            return mapping;
        } else {
            IStatus status = new Status(IStatus.ERROR, KimlServicePlugin.PLUGIN_ID,
                    workbenchPart == null
                    ? "No layout manager is available for the selected part."
                    : "No layout manager is available for " + workbenchPart.getTitle() + ".");
            StatusManager.getManager().handle(status, StatusManager.SHOW);
            return null;
        }
    }

    /**
     * Perform layout on the given workbench part using the given layout manager. If zero or one
     * layout configurator is passed, the layout engine is executed exactly once. If multiple
     * layout configurators are passed, the layout engine is executed accordingly often,
     * but the resulting layout is applied only once. This is useful for composition of multiple
     * algorithms that process only parts of the graph.
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
     * @param extraLayoutConfigs
     *            list of additional layout configurators to use, or {@code null}
     * @return the layout mapping used in this session
     */
    protected <T> LayoutMapping<T> layout(final IDiagramLayoutManager<T> layoutManager,
            final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors,
            final boolean zoom, final List<ILayoutConfig> extraLayoutConfigs) {
        final Maybe<LayoutMapping<T>> layoutMapping = Maybe.create();
        final Pair<IWorkbenchPart, Object> target = Pair.of(workbenchPart, diagramPart);
        
        final MonitoredOperation monitoredOperation = new MonitoredOperation(executorService) {
            // first phase: build the layout graph
            @Override
            protected void preUIexec() {
                layoutMapping.set(layoutManager.buildLayoutGraph(workbenchPart,
                        layoutAncestors ? null : diagramPart));
            }

            // second phase: execute layout algorithms
            @Override
            protected IStatus execute(final IKielerProgressMonitor monitor) {
                LayoutMapping<T> mapping = layoutMapping.get();
                IStatus status;
                if (mapping != null && mapping.getLayoutGraph() != null) {
                    // the manager's adapter list is expected to return its diagram part type
                    Class<?>[] adapterList = layoutManager.getAdapterList();
                    assert adapterList.length > 0;
                    
                    // translate the diagram part into one that is understood by the layout manager
                    Object transDiagPart;
                    if (adapterList != null && adapterList.length > 0) {
                        transDiagPart = layoutManager.getAdapter(diagramPart, adapterList[0]);
                    } else {
                        transDiagPart = diagramPart;
                    }
                    
                    // perform the actual layout
                    status = layout(mapping, transDiagPart, monitor, extraLayoutConfigs,
                            layoutAncestors);
                    
                    // stop earlier layout operations that are still running
                    if (!monitor.isCanceled()) {
                        stopEarlierOperations(target, getTimestamp());
                    }
                } else {
                    status = new Status(Status.WARNING, KimlServicePlugin.PLUGIN_ID,
                            "Unable to build the layout graph from the given workbench part.");
                }
                
                monitor.done();
                return status;
            }

            // third phase: apply layout with animation
            @Override
            protected void postUIexec() {
                if (layoutMapping.get() != null) {
                    // check for visibility of the given workbench part
                    boolean withAnimation = animate && (workbenchPart == null
                            || workbenchPart.getSite().getPage().isPartVisible(workbenchPart));
                    int animationTime = calcAnimationTime(layoutMapping.get(), withAnimation);
                    layoutManager.applyLayout(layoutMapping.get(), zoom, animationTime);
                }
            }
        };
        
        synchronized (runningOperations) {
            runningOperations.put(target, monitoredOperation);
        }

        try {
            if (progressBar) {
                // perform layout with a progress bar
                monitoredOperation.runMonitored();
            } else {
                // perform layout without a progress bar
                monitoredOperation.runUnmonitored();
            }
        } finally {
            synchronized (runningOperations) {
                runningOperations.remove(target, monitoredOperation);
            }
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
    private static int calcAnimationTime(final LayoutMapping<?> mapping, final boolean animate) {
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
    
    /**
     * Stop all running operations whose timestamp is earlier than the given one.
     * 
     * @param target the layout target
     * @param time operations with a timestamp that is less than this are stopped
     */
    private void stopEarlierOperations(final Pair<IWorkbenchPart, Object> target, final long time) {
        synchronized (runningOperations) {
            for (MonitoredOperation operation : runningOperations.get(target)) {
                if (operation.getTimestamp() < time) {
                    operation.cancel();
                }
            }
        }
    }
    
    /**
     * Perform layout with a given progress monitor, possibly without a workbench part.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported,
     *            or {@code null} if no progress reporting is required
     * @return a status indicating success or failure
     */
    public IStatus layout(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final IKielerProgressMonitor progressMonitor) {
        IDiagramLayoutManager<?> layoutManager = LayoutManagersService.getInstance().getManager(
                workbenchPart, diagramPart);
        if (layoutManager != null) {
            return layout(layoutManager, workbenchPart, diagramPart, progressMonitor);
        } else {
            return new Status(IStatus.ERROR, KimlServicePlugin.PLUGIN_ID,
                    "No layout manager is available for the selected part.");
        }
    }
    
    /**
     * Perform layout with a given progress monitor using the given layout manager, possibly
     * without a workbench part.
     * 
     * @param <T> the type of diagram part that is handled by the given diagram layout manager
     * @param layoutManager
     *            a diagram layout manager
     * @param workbenchPart
     *            the workbench part for which layout is performed
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported,
     *            or {@code null} if no progress reporting is required
     * @return a status indicating success or failure
     */
    protected <T> IStatus layout(final IDiagramLayoutManager<T> layoutManager,
            final IWorkbenchPart workbenchPart, final Object diagramPart,
            final IKielerProgressMonitor progressMonitor) {
        IKielerProgressMonitor monitor;
        if (progressMonitor == null) {
            monitor = new BasicProgressMonitor(0);
        } else {
            monitor = progressMonitor;
        }
        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        monitor.begin("Layout Diagram", 3);
        
        // build the layout graph
        IKielerProgressMonitor submon1 = monitor.subTask(1);
        submon1.begin("Build layout graph", 1);
        LayoutMapping<T> mapping = layoutManager.buildLayoutGraph(workbenchPart, diagramPart);
        
        IStatus status;
        if (mapping != null && mapping.getLayoutGraph() != null) {
            // the manager's adapter list is expected to return its internally used diagram part type
            Class<?>[] adapterList = layoutManager.getAdapterList();
            assert adapterList.length > 0;
                    
            // translate the diagram part into one that is understood by the layout manager
            Object transDiagPart = layoutManager.getAdapter(diagramPart, adapterList[0]);
            submon1.done();
            
            // perform the actual layout
            status = layout(mapping, transDiagPart, monitor.subTask(1), null, false);
            
            // apply the layout to the diagram
            IKielerProgressMonitor submon3 = monitor.subTask(1);
            submon3.begin("Apply layout to the diagram", 1);
            layoutManager.applyLayout(mapping, false, 0);
            submon3.done();
        } else {
            status = new Status(Status.WARNING, KimlServicePlugin.PLUGIN_ID,
                    "Unable to build the layout graph from the given workbench part.");
        }
        
        monitor.done();
        return status;
    }
    
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
     * Perform layout on the given layout graph mapping. If zero or one layout configurator is
     * passed, the layout engine is executed exactly once. If multiple layout configurators are
     * passed, the layout engine is executed accordingly often, but the resulting layout is applied
     * only once. This is useful for composition of multiple algorithms that process only parts of
     * the graph. Layout listeners are notified after the layout has been computed.
     * 
     * @param mapping
     *            a mapping for the layout graph
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported
     * @param extraLayoutConfigs
     *            list of additional layout configurators to use
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected diagram part, but also for
     *            its ancestors
     * @return a status indicating success or failure
     */
    protected IStatus layout(final LayoutMapping<?> mapping, final Object diagramPart,
            final IKielerProgressMonitor progressMonitor, final List<ILayoutConfig> extraLayoutConfigs,
            final boolean layoutAncestors) {
        if (layoutAncestors) {
            // mark all parallel areas for exclusion from layout
            KGraphElement graphElem = mapping.getGraphMap().inverse().get(diagramPart);
            if (graphElem instanceof KNode && ((KNode) graphElem).getParent() != null) {
                KNode node = (KNode) graphElem;
                VolatileLayoutConfig vlc = new VolatileLayoutConfig();
                do {
                    KNode parent = node.getParent();
                    for (KNode child : parent.getChildren()) {
                        if (child != node) {
                            // do not layout the content of the child node
                            vlc.setValue(LayoutOptions.NO_LAYOUT, child,
                                    LayoutContext.GRAPH_ELEM, true);
                            // do not change the size of the child node
                            vlc.setValue(LayoutOptions.SIZE_CONSTRAINT, child,
                                    LayoutContext.GRAPH_ELEM, SizeConstraint.fixed());
                            // do not move the ports of the child node
                            vlc.setValue(LayoutOptions.PORT_CONSTRAINTS, child,
                                    LayoutContext.GRAPH_ELEM, PortConstraints.FIXED_POS);
                        }
                    }
                    node = parent;
                } while (node.getParent() != null);
                mapping.getLayoutConfigs().add(vlc);
            }
        }
        
        mapping.setProperty(PROGRESS_MONITOR, progressMonitor);
        IStatus status = null;
        if (extraLayoutConfigs == null || extraLayoutConfigs.isEmpty()) {
            // perform layout without any extra configuration
            status = layout(mapping, progressMonitor);
        } else if (extraLayoutConfigs.size() == 1) {
            // perform layout once with an extra configuration
            mapping.getLayoutConfigs().add(extraLayoutConfigs.get(0));
            status = layout(mapping, progressMonitor);
        } else {
            // perform layout multiple times with different configurations
            progressMonitor.begin("Diagram layout engine",
                    TOTAL_WORK * extraLayoutConfigs.size());
            for (ILayoutConfig config : extraLayoutConfigs) {
                mapping.getLayoutConfigs().add(config);
                status = layout(mapping, progressMonitor);
                if (!status.isOK()) {
                    break;
                }
                mapping.getLayoutConfigs().remove(config);
            }
            progressMonitor.done();
        }

        // notify listeners of the executed layout
        for (IListener listener : layoutListeners) {
            listener.layoutDone(mapping.getLayoutGraph(), progressMonitor);
        }
        return status;
    }
    
    private static final float CONFIGURE_WORK = 1;
    private static final float LAYOUT_WORK = 4;
    private static final float TOTAL_WORK = CONFIGURE_WORK + LAYOUT_WORK;
    
    /**
     * Perform layout on the given layout graph mapping. Layout listeners are <em>not</em> notified
     * in this method.
     * 
     * @param mapping
     *            a mapping for the layout graph
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported; if the
     *            given monitor is not yet started, it is started in this method
     * @return a status indicating success or failure
     */
    public IStatus layout(final LayoutMapping<?> mapping,
            final IKielerProgressMonitor progressMonitor) {
        boolean newTask = progressMonitor.begin("Diagram layout engine", TOTAL_WORK);
        if (mapping.getProperty(PROGRESS_MONITOR) == null) {
            mapping.setProperty(PROGRESS_MONITOR, progressMonitor);
        }
        
        // Fetch the active graph layout engine to be used
        IGraphLayoutEngine layoutEngine = LayoutManagersService.getInstance().getLayoutEngine();
        try {
            // configure the layout graph using a layout option manager
            layoutOptionManager.configure(mapping, progressMonitor.subTask(CONFIGURE_WORK));
            
            // export the layout graph for debugging
            if (Platform.getPreferencesService().getBoolean("de.cau.cs.kieler.kiml.ui",
                    PREF_DEBUG_OUTPUT, false, null)) {
                exportLayoutGraph(mapping.getLayoutGraph());
            }

            // perform layout on the layout graph
            layoutEngine.layout(mapping.getLayoutGraph(), progressMonitor.subTask(LAYOUT_WORK));
            
            if (newTask) {
                progressMonitor.done();
            }
            if (progressMonitor.isCanceled()) {
                return Status.CANCEL_STATUS;
            }
            
            // return a positive status
            return Status.OK_STATUS;
            
        } catch (Throwable exception) {
            return new Status(IStatus.ERROR, KimlServicePlugin.PLUGIN_ID,
                    "Failed to perform diagram layout.", exception);
        }
    }
    
    /**
     * Listener interface for graph layout. Implementations must not modify the graph in any way
     * and should execute as quickly as possible.
     */
    public interface IListener {
        /**
         * Called when layout has been done on a graph.
         * 
         * @param layoutGraph the parent node of the graph
         * @param monitor the progress monitor with information on the executed layout
         */
        void layoutDone(KNode layoutGraph, IKielerProgressMonitor monitor);
    }
    
    /** list of registered layout listeners. */
    private List<IListener> layoutListeners = new LinkedList<IListener>();
    
    /**
     * Add the given object to the list of layout listeners.
     * 
     * @param listener a listener
     */
    public void addListener(final IListener listener) {
        layoutListeners.add(listener);
    }
    
    /**
     * Export the given layout graph in KGraph format.
     * 
     * @param graph the parent node of the layout graph
     */
    private void exportLayoutGraph(final KNode graph) {
        String path = System.getProperty("user.home");
        if (path != null) {
            if (path.endsWith(File.separator)) {
                path += "tmp" + File.separator + "layout" + File.separator
                        + Integer.toHexString(graph.hashCode()) + ".kgraph";
            } else {
                path += File.separator + "tmp" + File.separator + "layout" + File.separator
                        + Integer.toHexString(graph.hashCode()) + ".kgraph";
            }
            
            // serialize all properties of the graph
            KimlUtil.persistDataElements(graph);
            
            // save the KGraph to a file
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource = resourceSet.createResource(URI.createFileURI(path));
            resource.getContents().add(graph);
            try {
                resource.save(Collections.emptyMap());
            } catch (IOException e) {
                // ignore the exception and abort the layout graph exporting
            }
        }
    }

}
