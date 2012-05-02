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
package de.cau.cs.kieler.kiml.kivi;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.core.kivi.UndoEffect;
import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;

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
    /** additional layout options configurations. */
    private LinkedList<ILayoutConfig> layoutConfigs = new LinkedList<ILayoutConfig>();
    /** whether the effect should be merged with other layout effects. */
    private boolean doMerge = true;

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
     * Constrain this layout effect not to be merged with any other effect.
     */
    public void dontMerge() {
        doMerge = false;
    }

    /**
     * Set a layout option value for this layout effect using the last created layout configuration.
     * The value is only applied for this layout run and is thrown away afterwards. If no layout
     * configuration is present yet, a new one is created for the given option.
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
        if (layoutConfigs.isEmpty()) {
            addLayoutConfig();
        }
        VolatileLayoutConfig config = (VolatileLayoutConfig) layoutConfigs.getLast();
        if (object instanceof EObject) {
            config.setValue(option, object, LayoutContext.DOMAIN_MODEL, value);
        } else {
            config.setValue(option, object, LayoutContext.DIAGRAM_PART, value);
        }
    }
    
    /**
     * Add a new layout configuration to this layout effect. This is done implicitly when
     * {@link #setOption(Object, IProperty, Object)} is used on a layout effect that contains no
     * layout configuration yet. If a layout configuration is added to a layout effect that
     * already contains one, the consequence is that automatic layout is performed once more using
     * the new configuration. This mechanism can be used to perform arbitrarily many different
     * layouts in a row, which is useful if one layout relies on the results of the previous one.
     */
    public void addLayoutConfig() {
        layoutConfigs.addLast(new VolatileLayoutConfig());
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        DiagramLayoutEngine layoutEngine = DiagramLayoutEngine.INSTANCE;
        layoutMapping = layoutEngine.layout(diagramEditor, diagramPart, doAnimate, progressBar,
                layoutAncestors, doZoom, layoutConfigs);
    }

    /**
     * Undo a layout effect.
     */
    @Override
    public void undo() {
        if (layoutMapping != null) {
            undo(layoutMapping);
        }
    }
    
    /**
     * Undo the layout effect of the given layout mapping.
     * 
     * @param mapping a layout mapping
     */
    private static <T> void undo(final LayoutMapping<T> mapping) {
        @SuppressWarnings("unchecked")
        final IDiagramLayoutManager<T> layoutManager = (IDiagramLayoutManager<T>) mapping.getProperty(
                DiagramLayoutEngine.DIAGRAM_LM);
        if (layoutManager != null) {
            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    layoutManager.undoLayout(mapping);
                }
            }, true);
        }
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
        return doMerge;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IEffect merge(final IEffect otherEffect) {
        if (doMerge && otherEffect instanceof LayoutEffect) {
            LayoutEffect other = (LayoutEffect) otherEffect;
            if (other.doMerge && diagramEditor == other.diagramEditor) {
                if (this.diagramPart instanceof EditPart && other.diagramPart instanceof EditPart) {
                    this.diagramPart = commonAncestor((EditPart) this.diagramPart,
                            (EditPart) other.diagramPart);
                }
                this.doZoom |= other.doZoom;
                this.doAnimate |= other.doAnimate;
                this.progressBar |= other.progressBar;
                ListIterator<ILayoutConfig> configIter = other.layoutConfigs.listIterator(
                        other.layoutConfigs.size());
                while (configIter.hasPrevious()) {
                    this.layoutConfigs.addFirst(configIter.previous());
                }
                return this;
            }
        } else if (otherEffect instanceof UndoEffect) {
            if (((UndoEffect) otherEffect).getEffect() instanceof LayoutEffect) {
                LayoutEffect other = (LayoutEffect) ((UndoEffect) otherEffect).getEffect();
                if (this.diagramEditor == other.diagramEditor
                        && this.diagramPart == other.diagramPart) {
                    return this;
                }
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
        }
        EditPart aParent = a;
        EditPart bParent = b;
        Set<EditPart> aAncestors = new HashSet<EditPart>();
        Set<EditPart> bAncestors = new HashSet<EditPart>();
        EditPart result = null;
        do {
            if (aParent != null) {
                aAncestors.add(aParent);
                if (bAncestors.contains(aParent)) {
                    result = aParent;
                }
                aParent = aParent.getParent();
            }
            if (bParent != null) {
                bAncestors.add(bParent);
                if (aAncestors.contains(bParent)) {
                    result = bParent;
                }
                bParent = bParent.getParent();
            }
        } while (result == null && !(aParent == null && bParent == null));
        if (result instanceof RootEditPart) {
            return ((RootEditPart) result).getContents();
        }
        return result;
    }

}
