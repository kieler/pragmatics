/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ogdf.options;

/**
 * Definition of the "quality vs. speed" option for the FMMM layout algorithm.
 * 
 * @author mri
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum QualityVsSpeed {
    
    /** best quality, worst speed. */
    GORGEOUSANDEFFICIENT,
    /** medium quality, medium speed. */
    BEAUTIFULANDFAST,
    /** worst quality, best speed. */
    NICEANDINCREDIBLESPEED;
    
}
