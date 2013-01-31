/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus.sequence;

/**
 * The different message label alignment strategies.
 *  
 * @author grh
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 */
public enum LabelAlignment {
    /** Aligns the label central between the source lifeline and its next neighbor. */
    FIRST_CENTER,
    /** Aligns the label directly at the source lifeline. */
    SOURCE,
    /** Aligns the label in the center of its message. */
    CENTER;
}
