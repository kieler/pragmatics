/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kivi.effects;

import org.eclipse.elk.graph.ElkGraphElement;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.klighd.ui.DiagramViewManager;
import de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart;

/**
 * A view management effect for revealing a diagram element in a view.
 * 
 * @author mri
 */
public class KlighdRevealEffect extends AbstractEffect {

    /** the view identifier. */
    private String viewId;
    /** the element. */
    private Object element;
    /** whether the element is a diagram element or a model element. */
    private boolean isDiagramElement;
    /** the duration. */
    private int duration;

    /**
     * Constructs a reveal effect.
     * 
     * @param viewId
     *            the view identifier
     * @param element
     *            the element
     * @param isDiagramElement
     *            true if the element is a diagram element; false if the element is a model element
     * @param duration
     *            the duration
     */
    public KlighdRevealEffect(final String viewId, final Object element,
            final boolean isDiagramElement, final int duration) {
        this.viewId = viewId;
        this.element = element;
        this.isDiagramElement = isDiagramElement;
        this.duration = duration;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        final DiagramViewPart view = DiagramViewManager.getView(viewId);
        if (view != null) {
            // get the diagram element
            ElkGraphElement theElement;
            if (isDiagramElement) { // implies element instanceof EObject
                theElement = (ElkGraphElement) element;
            } else {
                theElement = view.getViewer().getViewContext().getTargetElement(
                        element, ElkGraphElement.class);
            }
            // reveal the element
            if (theElement != null) {
                view.getViewer().reveal(theElement, duration);
            }
        }
    }

}
