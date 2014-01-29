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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.config.CompoundLayoutConfig;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine.IListener;
import de.cau.cs.kieler.kiml.service.LayoutMapping;
import de.cau.cs.kieler.kiml.service.LayoutOptionManager;

/**
 * The entry class for automatic layout of graphical diagrams.
 * Use this class to perform automatic layout on the content of a workbench part that contains
 * a graph-based diagram. The mapping between the diagram and the layout graph structure is managed
 * by a {@link IDiagramLayoutManager} implementation, which has to be registered using the
 * {@code layoutManagers} extension point.
 * 
 * @author msp
 * @deprecated Use {@link de.cau.cs.kieler.kiml.service.DiagramLayoutEngine} instead.
 */
public class DiagramLayoutEngine {
    
    /**
     * The singleton instance that can be used whenever layout needs to be performed.
     */
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
        return de.cau.cs.kieler.kiml.service.DiagramLayoutEngine.INSTANCE.layout(
                workbenchPart, diagramPart, animate, progressBar, layoutAncestors, zoom);
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
        VolatileLayoutConfig parameters = new VolatileLayoutConfig()
                .setValue(LayoutOptions.ANIMATE, animate)
                .setValue(LayoutOptions.PROGRESS_BAR, progressBar)
                .setValue(LayoutOptions.LAYOUT_ANCESTORS, layoutAncestors)
                .setValue(LayoutOptions.ZOOM_TO_FIT, zoom);
        ILayoutConfig[] confs;
        if (extraLayoutConfigs == null || extraLayoutConfigs.isEmpty()) {
            confs = new ILayoutConfig[] { parameters };
        } else if (extraLayoutConfigs.get(0) instanceof CompoundLayoutConfig) {
            ((CompoundLayoutConfig) extraLayoutConfigs.get(0)).add(parameters);
            confs = extraLayoutConfigs.toArray(new ILayoutConfig[extraLayoutConfigs.size()]);
        } else {
            confs = extraLayoutConfigs.toArray(new ILayoutConfig[extraLayoutConfigs.size()]);
            confs[0] = CompoundLayoutConfig.of(parameters, confs[0]);
        }
        return de.cau.cs.kieler.kiml.service.DiagramLayoutEngine.INSTANCE.layout(
                workbenchPart, diagramPart, confs);
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
        return de.cau.cs.kieler.kiml.service.DiagramLayoutEngine.INSTANCE.layout(
                workbenchPart, diagramPart, progressMonitor);
    }
    
    /**
     * Returns the currently used layout option manager.
     * 
     * @return the layout option manager
     */
    public LayoutOptionManager getOptionManager() {
        return de.cau.cs.kieler.kiml.service.DiagramLayoutEngine.INSTANCE.getOptionManager();
    }
    
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
        return de.cau.cs.kieler.kiml.service.DiagramLayoutEngine.INSTANCE.layout(
                mapping, progressMonitor);
    }
    
    /**
     * Add the given object to the list of layout listeners.
     * 
     * @param listener a listener
     */
    public void addListener(final IListener listener) {
        de.cau.cs.kieler.kiml.service.DiagramLayoutEngine.INSTANCE.addListener(listener);
    }

}
