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
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorPart;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.core.kivi.IEffect;
import de.cau.cs.kieler.core.kivi.UndoEffect;
import de.cau.cs.kieler.core.model.util.ModelingUtil;

/**
 * Performs automatic layout on a diagram editor for a given selection.
 * 
 * @author mmu
 * @author msp
 */
public class LayoutEffect extends AbstractEffect {

    /** the diagram editor containing the diagram to layout. */
    private IEditorPart diagramEditor;
    /** the selected edit part. */
    private EditPart editPart;
    /** whether to zoom before or after layout. */
    private boolean doZoom = true;
    /** whether to animate the layout. */
    private boolean doAnimate = true;
    /** whether to use a progress monitor. */
    private boolean useProgMonitor = false;
    /** whether to include the ancestors in the layout process. */
    private boolean layoutAncestors = false;
    /** the diagram layout manager that has been used for layout. */
    private DiagramLayoutManager manager;

    /**
     * Create a new layout effect for the given diagram editor and EObject.
     * 
     * @param editor the diagram editor containing the diagram to layout
     * @param object the domain model object to layout
     */
    public LayoutEffect(final IEditorPart editor, final EObject object) {
        this.diagramEditor = editor;
        this.editPart = ModelingUtil.getEditPart(editor, object);
    }

    /**
     * Create a new layout effect for the given diagram editor and EObject.
     * 
     * @param editor the diagram editor containing the diagram to layout
     * @param object the domain model object to layout
     * @param zoomToFit whether zoom to fit shall be performed
     */
    public LayoutEffect(final IEditorPart editor, final EObject object, final boolean zoomToFit) {
        this(editor, object);
        this.doZoom = zoomToFit;
    }

    /**
     * Create a new layout effect for the given diagram editor and EObject.
     * 
     * @param editor the diagram editor containing the diagram to layout
     * @param object the domain model object to layout
     * @param zoomToFit whether zoom to fit shall be performed
     * @param progressBar whether a progress bar shall be displayed
     */
    public LayoutEffect(final IEditorPart editor, final EObject object, final boolean zoomToFit,
            final boolean progressBar) {
        this(editor, object);
        this.doZoom = zoomToFit;
        this.useProgMonitor = progressBar;
    }
    
    /**
     * Create a new layout effect for the given diagram editor and EObject.
     * 
     * @param editor the diagram editor containing the diagram to layout
     * @param object the domain model object to layout
     * @param zoomToFit whether zoom to fit shall be performed
     * @param progressBar whether a progress bar shall be displayed
     * @param ancestors whether to include the ancestors in the layout process
     */
    public LayoutEffect(final IEditorPart editor, final EObject object, final boolean zoomToFit,
            final boolean progressBar, final boolean ancestors) {
        this(editor, object);
        this.doZoom = zoomToFit;
        this.useProgMonitor = progressBar;
        this.layoutAncestors = ancestors;
    }
    
    /**
     * Create a new layout effect for the given diagram editor and EObject.
     * 
     * @param editor the diagram editor containing the diagram to layout
     * @param object the domain model object to layout
     * @param zoomToFit whether zoom to fit shall be performed
     * @param progressBar whether a progress bar shall be displayed
     * @param ancestors whether to include the ancestors in the layout process
     * @param animation whether the layout shall be animated
     */
    public LayoutEffect(final IEditorPart editor, final EObject object, final boolean zoomToFit,
            final boolean progressBar, final boolean ancestors, final boolean animation) {
        this(editor, object);
        this.doZoom = zoomToFit;
        this.useProgMonitor = progressBar;
        this.layoutAncestors = ancestors;
        this.doAnimate = animation;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        manager = EclipseLayoutServices.getInstance().getManager(diagramEditor, editPart);
        if (manager != null) {
            manager.layout(diagramEditor, editPart, doAnimate, useProgMonitor, layoutAncestors,
                    false, doZoom);
        }
    }
    
    /**
     * Returns the diagram layout manager that was used for this layout effect, or
     * {@code null} if the effect has not been executed yet.
     * 
     * @return the diagram layout manager
     */
    public DiagramLayoutManager getManager() {
        return manager;
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
                this.editPart = commonAncestor(this.editPart, other.editPart);
                this.doZoom |= other.doZoom;
                this.doAnimate |= other.doAnimate;
                this.useProgMonitor |= other.useProgMonitor;
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
     * @param a first edit part
     * @param b second edit part
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
