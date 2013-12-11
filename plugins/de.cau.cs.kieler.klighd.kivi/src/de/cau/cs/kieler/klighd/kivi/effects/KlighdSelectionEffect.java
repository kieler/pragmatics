/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
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
package de.cau.cs.kieler.klighd.kivi.effects;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.klighd.util.Iterables2;
import de.cau.cs.kieler.klighd.views.DiagramViewManager;
import de.cau.cs.kieler.klighd.views.IDiagramWorkbenchPart;

/**
 * A view management effect to select a number of diagram elements in a view.
 * 
 * @author mri
 * @author chsch
 */
public class KlighdSelectionEffect extends AbstractEffect {

    /** the view identifier. */
    private String viewId;
    /** the elements. */
    private Iterable<? extends Object> elements;
    /** whether the elements are diagram elements or a model elements. */
    private boolean areDiagramElements;

    /**
     * Constructs a selection effect.
     * 
     * @param viewId
     *            the view identifier
     * @param element
     *            the element
     * @param isDiagramElement
     *            true if the element is a diagram element; false if the element is a model element
     */
    public KlighdSelectionEffect(final String viewId, final Object element,
            final boolean isDiagramElement) {
        this.viewId = viewId;
        this.elements = Iterables2.singletonIterable(element);
        this.areDiagramElements = isDiagramElement;
    }

    /**
     * Constructs a selection effect.
     * 
     * @param viewId
     *            the view identifier
     * @param elements
     *            the elements
     * @param areDiagramElements
     *            true if the elements are diagram elements; false if the elements are model
     *            elements
     */
    public KlighdSelectionEffect(final String viewId, final Iterable<Object> elements,
            final boolean areDiagramElements) {
        this.viewId = viewId;
        this.elements = elements;
        this.areDiagramElements = areDiagramElements;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        IDiagramWorkbenchPart part = DiagramViewManager.getInstance().getDiagramWorkbenchPart(viewId);
        if (part != null) {
            // get the diagram elements
            if (areDiagramElements) {
                part.getContextViewer().resetSelectionToDiagramElements(
                        Iterables.filter(this.elements, EObject.class));
            } else {
                part.getContextViewer().resetSelectionToSemanticElements(this.elements);
            }
        }
    }
}
