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
package de.cau.cs.kieler.klighd.graphiti.piccolo;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.klighd.piccolo.PiccoloViewer;
import edu.umd.cs.piccolo.PNode;

/**
 * A viewer for Graphiti Pictogram models. It uses Piccolo to draw the diagram representing the
 * model.
 * 
 * @author mri
 */
public class PictogramViewer extends PiccoloViewer {

    /** the current input. */
    private Diagram currentInput = null;

    /**
     * Constructs a PictogramViewer with default style.
     * 
     * @param parent
     *            the parent composite
     */
    public PictogramViewer(final Composite parent) {
        super(parent);
    }

    /**
     * Constructs a PictogramViewer with given style.
     * 
     * @param parent
     *            the parent composite
     * @param style
     *            the style
     */
    public PictogramViewer(final Composite parent, final int style) {
        super(parent, style);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getInput() {
        return currentInput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInput(final Object input) {
        if (input instanceof Diagram) {
            currentInput = (Diagram) input;
            Pictogram2PNodeTransformation transformation = new Pictogram2PNodeTransformation();
            PNode rootNode = transformation.transform(currentInput);
            super.setInput(rootNode);
        }
    }

}
