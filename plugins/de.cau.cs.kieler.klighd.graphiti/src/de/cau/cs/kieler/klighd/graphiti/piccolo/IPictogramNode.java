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

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;

/**
 * The interface for Piccolo nodes representing Pictogram elements.
 * 
 * @author mri
 */
public interface IPictogramNode {

    /**
     * Returns the Pictogram element which is represented by this Piccolo node.
     * 
     * @return the Pictogram element
     */
    // chsch: return type changed in order to allow e.g. text fields to be related to
    //  an element in the model
    GraphicsAlgorithmContainer getPictogramElement();
    //PictogramElement getPictogramElement();

}
