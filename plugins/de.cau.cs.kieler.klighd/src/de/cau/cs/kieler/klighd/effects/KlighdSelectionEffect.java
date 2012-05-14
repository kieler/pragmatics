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
package de.cau.cs.kieler.klighd.effects;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.klighd.views.DiagramViewPart;
import de.cau.cs.kieler.klighd.views.DiagramViewManager;

/**
 * A view management effect to select a number of diagram elements in a view.
 * 
 * @author mri
 */
public class KlighdSelectionEffect extends AbstractEffect {

    /** the view identifier. */
    private String viewId;
    /** the elements. */
    private Object[] elements;
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
        this.elements = new Object[] { element };
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
    public KlighdSelectionEffect(final String viewId, final Object[] elements,
            final boolean areDiagramElements) {
        this.viewId = viewId;
        this.elements = elements;
        this.areDiagramElements = areDiagramElements;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        DiagramViewPart view = DiagramViewManager.getInstance().getView(viewId);
        if (view != null) {
            // get the diagram elements
            Object[] theElements;
            if (areDiagramElements) {
                theElements = elements;
            } else {
                List<Object> temp = new LinkedList<Object>();
                for (Object modelElement : elements) {
                    Object diagramElement =
                            view.getContextViewer().getCurrentViewContext()
                                    .getTargetElement(modelElement);
                    if (diagramElement != null) {
                        temp.add(diagramElement);
                    }
                }
                theElements = temp.toArray();
            }
            // select the elements
            if (theElements.length > 0) {
                view.getContextViewer().setSelection(theElements);
            }
        }
    }

}
