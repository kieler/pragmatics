/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.diagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.core.kivi.UndoEffect;
import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;

/**
 * Performs automatic layout on a diagram editor for a given selection. The layout
 * can be customized using {@link #setOption(EObject, IProperty, Object)}, which
 * sets specific layout options only for this instance of the layout effect.
 * 
 * @author mmu
 * @author msp
 */
public class LayoutEffect extends AbstractEffect {

    /** the diagram editor containing the diagram to layout. */
    private IWorkbenchPart diagramEditor;
    /** The bridge to a concrete graphical framework corresponding to this editor. */
    private IGraphicalFrameworkBridge bridge;
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
    /** the layout mapping that has been used for layout. */
    private LayoutMapping<?> layoutMapping;
    /** additional layout options configuration. */
    private VolatileLayoutConfig layoutConfig;

    /**
     * Create a new layout effect for the given diagram editor and EObject. If {@code null}
     * is given as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param object
     *            the top-level domain model object to layout, or {@code null}
     * @throws de.cau.cs.kieler.core.ui.UnsupportedPartException
     *            if layout is not supported for the given workbench part
     */
    public LayoutEffect(final IWorkbenchPart workbenchPart, final EObject object) {
        this.diagramEditor = workbenchPart;
        this.bridge = GraphicalFrameworkService.getInstance().getBridge(workbenchPart);
        this.diagramPart = bridge.getEditPart(object);
    }

    /**
     * Create a new layout effect for the given diagram editor and EObject. If {@code null}
     * is given as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param object
     *            the top-level domain model object to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @throws de.cau.cs.kieler.core.ui.UnsupportedPartException
     *            if layout is not supported for the given workbench part
     */
    public LayoutEffect(final IWorkbenchPart workbenchPart, final EObject object,
            final boolean zoomToFit) {
        this(workbenchPart, object);
        this.doZoom = zoomToFit;
    }

    /**
     * Create a new layout effect for the given diagram editor and EObject. If {@code null}
     * is given as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param object
     *            the top-level domain model object to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     * @throws de.cau.cs.kieler.core.ui.UnsupportedPartException
     *            if layout is not supported for the given workbench part
     */
    public LayoutEffect(final IWorkbenchPart workbenchPart, final EObject object,
            final boolean zoomToFit, final boolean progressBar) {
        this(workbenchPart, object);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
    }

    /**
     * Create a new layout effect for the given diagram editor and EObject. If {@code null}
     * is given as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param object
     *            the top-level domain model object to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     * @param ancestors
     *            whether to include the ancestors in the layout process
     * @throws de.cau.cs.kieler.core.ui.UnsupportedPartException
     *            if layout is not supported for the given workbench part
     */
    public LayoutEffect(final IWorkbenchPart workbenchPart, final EObject object,
            final boolean zoomToFit, final boolean progressBar,
            final boolean ancestors) {
        this(workbenchPart, object);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
        this.layoutAncestors = ancestors;
    }

    /**
     * Create a new layout effect for the given diagram editor and EObject. If {@code null}
     * is given as top-level object, layout is performed for the whole diagram.
     * 
     * @param workbenchPart
     *            the workbench part containing the diagram to layout
     * @param object
     *            the top-level domain model object to layout, or {@code null}
     * @param zoomToFit
     *            whether zoom to fit shall be performed
     * @param progressBar
     *            whether a progress bar shall be displayed
     * @param ancestors
     *            whether to include the ancestors in the layout process
     * @param animation
     *            whether the layout shall be animated
     * @throws de.cau.cs.kieler.core.ui.UnsupportedPartException
     *            if layout is not supported for the given workbench part
     */
    public LayoutEffect(final IWorkbenchPart workbenchPart, final EObject object,
            final boolean zoomToFit, final boolean progressBar, final boolean ancestors,
            final boolean animation) {
        this(workbenchPart, object);
        this.doZoom = zoomToFit;
        this.progressBar = progressBar;
        this.layoutAncestors = ancestors;
        this.doAnimate = animation;
    }

    /**
     * Set a layout option value for this layout effect. The value is only applied for this layout
     * run and is thrown away afterwards.
     * 
     * @param object
     *            the domain model element or edit part for which the option shall be set
     * @param option
     *            the layout option to set (see {@link de.cau.cs.kieler.kiml.options.LayoutOptions
     *            LayoutOptions})
     * @param value
     *            the value for the layout option
     */
    public void setOption(final Object object, final IProperty<?> option, final Object value) {
        if (layoutConfig == null) {
            layoutConfig = new VolatileLayoutConfig();
        }
        if (object instanceof EObject) {
            layoutConfig.setValue(option, object, LayoutContext.DOMAIN_MODEL, value);
        } else {
            layoutConfig.setValue(option, object, LayoutContext.DIAGRAM_PART, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        DiagramLayoutEngine layoutEngine = DiagramLayoutEngine.INSTANCE;
        layoutMapping = layoutEngine.layout(diagramEditor, diagramPart, doAnimate, progressBar,
                layoutAncestors, doZoom, layoutConfig);
    }

    /**
     * Undo a layout effect. For now, only a new layout is executed. This leads to correct
     * behavior if a list of effects is undone sequentially and the last one was a layout effect.
     * However, this is not really undoing that layout.
     * 
     * TODO: implement real undoing of layout, best with animation. Using the standard command
     * undo functionality will not do any animation.
     */
    @Override
    public void undo() {
        this.execute();
    }
    
    /**
     * Returns the layout mapping that was used for this layout effect, or {@code null} if
     * the effect has not been executed yet.
     * 
     * @return the diagram layout manager
     */
    public LayoutMapping<?> getMapping() {
        return layoutMapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMergeable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEffect merge(final IEffect otherEffect) {
        if (otherEffect instanceof LayoutEffect) {
            LayoutEffect other = (LayoutEffect) otherEffect;
            if (diagramEditor == other.diagramEditor) {
                if (this.diagramPart instanceof EditPart && other.diagramPart instanceof EditPart) {
                    this.diagramPart = commonAncestor((EditPart) this.diagramPart,
                            (EditPart) other.diagramPart);
                }
                this.doZoom |= other.doZoom;
                this.doAnimate |= other.doAnimate;
                this.progressBar |= other.progressBar;
                if (this.layoutConfig != null && other.layoutConfig != null) {
                    this.layoutConfig.copyValues(other.layoutConfig);
                } else if (other.layoutConfig != null) {
                    this.layoutConfig = other.layoutConfig;
                }
                return this;
            }
        } else if (otherEffect instanceof UndoEffect) {
            if (((UndoEffect) otherEffect).getEffect() instanceof LayoutEffect) {
                return this;
            }
        }
        return null;
    }

    /**
     * Find the first common ancestor of two edit parts.
     * 
     * @param a
     *            first edit part
     * @param b
     *            second edit part
     * @return the first common ancestor
     */
    private EditPart commonAncestor(final EditPart a, final EditPart b) {
        if (a == b) {
            return a;
        } else if (a == null || b == null) {
            return null;
        }
        EditPart aParent = a;
        EditPart bParent = b;
        List<EditPart> aAncestors = new ArrayList<EditPart>();
        List<EditPart> bAncestors = new ArrayList<EditPart>();
        aAncestors.add(a);
        bAncestors.add(b);
        do {
            if (aParent != null) {
                aParent = aParent.getParent();
            }
            if (bParent != null) {
                bParent = bParent.getParent();
            }
            if (aParent != null) {
                aAncestors.add(aParent);
            }
            if (bParent != null) {
                bAncestors.add(bParent);
            }
            if (aAncestors.contains(bParent)) {
                return bParent;
            }
            if (bAncestors.contains(aParent)) {
                return aParent;
            }
        } while (!(aParent == null && bParent == null));
        return null;
    }

}
