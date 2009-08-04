/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.layout.options;

/**
 * Definition of node shapes.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public enum Shape {
	
	/** undefined shape */
	UNDEFINED,
	/** rectangle shape */
	RECTANGLE,
	/** ellipse shape */
	ELLIPSE,
	/** triangle shape */
	TRIANGLE;
	
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static Shape valueOf(int i) {
        return values()[i];
    }

}
