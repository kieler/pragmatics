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

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;
import de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * The entry class for automatic layout of graphical diagrams.
 * 
 * @author msp
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
     * Perform layout on the given workbench part. If zero or one layout configuration is passed,
     * the layout engine is executed exactly once. If multiple layout configurations are passed,
     * the layout engine is executed accordingly often.
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
     *            list of additional layout configurations to use
     * @return the layout mapping used in this session
     */
    public LayoutMapping<?> layout(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors,
            final boolean zoom, final List<ILayoutConfig> extraLayoutConfigs) {
        IDiagramLayoutManager<?> layoutManager = EclipseLayoutInfoService.getInstance().getManager(
                workbenchPart, diagramPart);
        if (layoutManager != null) {
            LayoutMapping<?> mapping = layout(layoutManager, workbenchPart, diagramPart, animate,
                    progressBar, layoutAncestors, zoom, extraLayoutConfigs);
            mapping.setProperty(DIAGRAM_LM, layoutManager);
            return mapping;
        } else {
            throw new UnsupportedOperationException(workbenchPart == null
                    ? Messages.getString("kiml.ui.17")
                    : Messages.getString("kiml.ui.15") + workbenchPart.getTitle() + ".");
        }
    }

    /** maximal number of recursion levels for which progress is displayed. */
    private static final int MAX_PROGRESS_LEVELS = 3;

    /**
     * Perform layout on the given workbench part using the given layout manager. If zero or one
     * layout configuration is passed, the layout engine is executed exactly once. If multiple
     * layout configurations are passed, the layout engine is executed accordingly often.
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
     *            list of additional layout configurations to use
     * @return the layout mapping used in this session
     */
    protected <T> LayoutMapping<T> layout(final IDiagramLayoutManager<T> layoutManager,
            final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean animate, final boolean progressBar, final boolean layoutAncestors,
            final boolean zoom, final List<ILayoutConfig> extraLayoutConfigs) {
        final Maybe<LayoutMapping<T>> layoutMapping = Maybe.create();
        
        final MonitoredOperation monitoredOperation = new MonitoredOperation() {
            // first phase: build the layout graph
            @Override
            protected void preUIexec() {
                if (workbenchPart != null
                        && workbenchPart.getSite().getPage().isPartVisible(workbenchPart)) {
                    layoutMapping.set(layoutManager.buildLayoutGraph(workbenchPart,
                            layoutAncestors ? null : diagramPart));
                }
            }

            // second phase: execute layout algorithms
            @Override
            protected IStatus execute(final IProgressMonitor monitor) {
                IStatus status;
                if (layoutMapping.get() == null) {
                    status = new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID,
                            Messages.getString("kiml.ui.62")); 
                } else {
                    IKielerProgressMonitor kielerMonitor;
                    if (monitor == null) {
                        kielerMonitor = new BasicProgressMonitor(0);
                    } else {
                        kielerMonitor = new KielerProgressMonitor(monitor, MAX_PROGRESS_LEVELS);
                    }
                    status = layout(layoutMapping.get(), diagramPart, kielerMonitor,
                            extraLayoutConfigs, layoutAncestors);
                    kielerMonitor.done();
                }
                return status;
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
     * Perform layout with a given progress monitor, possibly without a workbench part.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported
     * @return a status indicating success or failure
     */
    public IStatus layout(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final IKielerProgressMonitor progressMonitor) {
        IDiagramLayoutManager<?> layoutManager = EclipseLayoutInfoService.getInstance().getManager(
                workbenchPart, diagramPart);
        if (layoutManager != null) {
            return layout(layoutManager, workbenchPart, diagramPart, progressMonitor);
        } else {
            throw new UnsupportedOperationException(Messages.getString("kiml.ui.17"));
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
     *            a progress monitor to which progress of the layout algorithm is reported
     * @return a status indicating success or failure
     */
    protected <T> IStatus layout(final IDiagramLayoutManager<T> layoutManager,
            final IWorkbenchPart workbenchPart, final Object diagramPart,
            final IKielerProgressMonitor progressMonitor) {
        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        progressMonitor.begin("Layout Diagram", 3);
        
        // build the layout graph
        IKielerProgressMonitor submon1 = progressMonitor.subTask(1);
        submon1.begin("Build layout graph", 1);
        LayoutMapping<T> mapping = layoutManager.buildLayoutGraph(workbenchPart, diagramPart);
        submon1.done();
        
        // perform layout
        IStatus status = layout(mapping, diagramPart, progressMonitor.subTask(1), null, false);
        
        // apply the layout to the diagram
        IKielerProgressMonitor submon3 = progressMonitor.subTask(1);
        submon3.begin("Apply layout to the diagram", 1);
        layoutManager.applyLayout(mapping, false, 0);
        submon3.done();
        
        return status;
    }

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
     * Perform layout on the given layout graph mapping. If zero or one layout configuration is
     * passed, the layout engine is executed exactly once. If multiple layout configurations are
     * passed, the layout engine is executed accordingly often.
     * 
     * @param mapping
     *            a mapping for the layout graph
     * @param diagramPart
     *            the parent diagram part for which layout is performed, or {@code null} if the whole
     *            diagram shall be layouted
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported
     * @param extraLayoutConfigs
     *            list of additional layout configurations to use
     * @param layoutAncestors
     *            if true, layout is not only performed for the selected diagram part, but also for
     *            its ancestors
     * @return a status indicating success or failure
     */
    protected IStatus layout(final LayoutMapping<?> mapping, final Object diagramPart,
            final IKielerProgressMonitor progressMonitor, final List<ILayoutConfig> extraLayoutConfigs,
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
        
        mapping.setProperty(PROGRESS_MONITOR, progressMonitor);
        if (extraLayoutConfigs == null || extraLayoutConfigs.isEmpty()) {
            // perform layout without any extra configuration
            return layout(mapping, progressMonitor);
        } else if (extraLayoutConfigs.size() == 1) {
            // perform layout once with an extra configuration
            mapping.getLayoutConfigs().add(extraLayoutConfigs.get(0));
            return layout(mapping, progressMonitor);
        } else {
            // perform layout multiple times with different configurations
            progressMonitor.begin("Multiple layout", extraLayoutConfigs.size());
            IStatus status = null;
            for (ILayoutConfig config : extraLayoutConfigs) {
                mapping.getLayoutConfigs().add(config);
                status = layout(mapping, progressMonitor.subTask(1));
                if (!status.isOK()) {
                    return status;
                }
                mapping.getLayoutConfigs().remove(config);
            }
            progressMonitor.done();
            return status;
        }
    }
    
    /**
     * Perform layout on the given layout graph mapping.
     * 
     * @param mapping
     *            a mapping for the layout graph
     * @param progressMonitor
     *            a progress monitor to which progress of the layout algorithm is reported
     * @return a status indicating success or failure
     */
    public IStatus layout(final LayoutMapping<?> mapping,
            final IKielerProgressMonitor progressMonitor) {
        // configure the layout graph using a layout option manager
        if (mapping.getProperty(PROGRESS_MONITOR) == null) {
            mapping.setProperty(PROGRESS_MONITOR, progressMonitor);
        }
        layoutOptionManager.configure(mapping);
        
        // Fetch the active graph layout engine to be used
        IGraphLayoutEngine layoutEngine = EclipseLayoutInfoService.getInstance().getLayoutEngine();
        try {
            
            // perform layout on the layout graph
            layoutEngine.layout(mapping.getLayoutGraph(), progressMonitor);
            
            // check for cancellation
            if (progressMonitor.isCanceled()) {
                return new Status(IStatus.CANCEL, KimlUiPlugin.PLUGIN_ID,  null);
            }
            
            // return a positive status
            return new Status(IStatus.OK, KimlUiPlugin.PLUGIN_ID, null);
            
        } catch (Throwable exception) {
            return new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID,
                    Messages.getString("kiml.ui.1"), exception);
        }
    }

}
