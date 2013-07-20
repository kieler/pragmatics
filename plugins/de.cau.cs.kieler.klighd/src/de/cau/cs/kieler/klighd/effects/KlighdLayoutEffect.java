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
package de.cau.cs.kieler.klighd.effects;

import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.klighd.views.DiagramViewManager;

/**
 * A layout effect for KlighD views. It is basically a stripped down version of the original layout
 * effect and works on other workbench parts then the KLighD view as well.
 * 
 * @author mri
 */
public class KlighdLayoutEffect extends AbstractEffect {

    /** the workbench part containing the diagram to layout. */
    private IWorkbenchPart workbenchPart;
    /** the selected diagram part. */
    private Object diagramPart;
    /** whether to zoom before or after layout. */
    private boolean doZoom = true;
    /** whether to animate the layout. */
    private boolean doAnimate = true;
    /** whether to show a progress bar. */
    private boolean progressBar = false;
    /** whether to include the ancestors in the layout process. */
    private boolean layoutAncestors = false;

    /**
     * Create a new layout effect for the diagram of the visualization with the given identifier and
     * diagram part. If {@code null} is given as top-level object, layout is performed for the whole
     * diagram.
     * 
     * @param viewId
     *            the view identifier for the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     */
    public KlighdLayoutEffect(final String viewId, final Object diagramPart) {
        this.workbenchPart = DiagramViewManager.getInstance().getView(viewId);
        this.diagramPart = diagramPart;
    }

    /**
     * Create a new layout effect for the given diagram of the visualization with the given
     * identifier and diagram part. If {@code null} is given as top-level object, layout is
     * performed for the whole diagram.
     * 
     * @param viewId
     *            the view identifier for the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     */
    public KlighdLayoutEffect(final String viewId, final Object diagramPart, final boolean zoomToFit) {
        this(DiagramViewManager.getInstance().getView(viewId), diagramPart);
        this.doZoom = zoomToFit;
    }

    /**
     * Create a new layout effect for the given diagram of the visualization with the given
     * identifier and diagram part. If {@code null} is given as top-level object, layout is
     * performed for the whole diagram.
     * 
     * @param viewId
     *            the view identifier for the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     */
    public KlighdLayoutEffect(final String viewId, final Object diagramPart,
            final boolean zoomToFit, final boolean progressBar) {
        this(DiagramViewManager.getInstance().getView(viewId), diagramPart);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
    }

    /**
     * Create a new layout effect for the given diagram of the visualization with the given
     * identifier and diagram part. If {@code null} is given as top-level object, layout is
     * performed for the whole diagram.
     * 
     * @param viewId
     *            the view identifier for the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     * @param ancestors
     *            whether to include the ancestors in the layout process
     */
    public KlighdLayoutEffect(final String viewId, final Object diagramPart,
            final boolean zoomToFit, final boolean progressBar, final boolean ancestors) {
        this(viewId, diagramPart);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
        this.layoutAncestors = ancestors;
    }

    /**
     * Create a new layout effect for the given diagram of the visualization with the given
     * identifier and diagram part. If {@code null} is given as top-level object, layout is
     * performed for the whole diagram.
     * 
     * @param viewId
     *            the view identifier for the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     * @param ancestors
     *            whether to include the ancestors in the layout process
     * @param animation
     *            whether the layout shall be animated
     */
    public KlighdLayoutEffect(final String viewId, final Object diagramPart,
            final boolean zoomToFit, final boolean progressBar, final boolean ancestors,
            final boolean animation) {
        this(viewId, diagramPart);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
        this.layoutAncestors = ancestors;
        this.doAnimate = animation;
    }

    /**
     * Create a new layout effect for the given diagram and diagram part. If {@code null} is given
     * as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     */
    public KlighdLayoutEffect(final IWorkbenchPart workbenchPart, final Object diagramPart) {
        this.workbenchPart = workbenchPart;
        this.diagramPart = diagramPart;
    }

    /**
     * Create a new layout effect for the given diagram and diagram part. If {@code null} is given
     * as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     */
    public KlighdLayoutEffect(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean zoomToFit) {
        this(workbenchPart, diagramPart);
        this.doZoom = zoomToFit;
    }

    /**
     * Create a new layout effect for the given diagram and diagram part. If {@code null} is given
     * as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     */
    public KlighdLayoutEffect(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean zoomToFit, final boolean progressBar) {
        this(workbenchPart, diagramPart);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
    }

    /**
     * Create a new layout effect for the given diagram and diagram part. If {@code null} is given
     * as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     * @param ancestors
     *            whether to include the ancestors in the layout process
     */
    public KlighdLayoutEffect(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean zoomToFit, final boolean progressBar, final boolean ancestors) {
        this(workbenchPart, diagramPart);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
        this.layoutAncestors = ancestors;
    }

    /**
     * Create a new layout effect for the given diagram and diagram part. If {@code null} is given
     * as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param diagramPart
     *            the top-level diagram part to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     * @param ancestors
     *            whether to include the ancestors in the layout process
     * @param animation
     *            whether the layout shall be animated
     */
    public KlighdLayoutEffect(final IWorkbenchPart workbenchPart, final Object diagramPart,
            final boolean zoomToFit, final boolean progressBar, final boolean ancestors,
            final boolean animation) {
        this(workbenchPart, diagramPart);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
        this.layoutAncestors = ancestors;
        this.doAnimate = animation;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        if (workbenchPart == null && diagramPart == null) {
            return;
        }
        try {
            DiagramLayoutEngine layoutEngine = DiagramLayoutEngine.INSTANCE;
            layoutEngine.layout(workbenchPart, diagramPart, doAnimate, progressBar,
                    layoutAncestors, doZoom, null);
        } catch (UnsupportedOperationException e) {
            // ignore
        }
    }

}
