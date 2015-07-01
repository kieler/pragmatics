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
package de.cau.cs.kieler.kiml.ui.preferences;

import de.cau.cs.kieler.kiml.ui.Messages;

/**
 * Enumeration of element types that can receive default options.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public enum ElementType {
    
    /** highest priority: edit parts of specific diagram editors. */
    EDIT_PART,
    
    /** medium priority: domain model elements. */
    MODEL_ELEM,
    
    /** lowest priority: diagram type definition (contributed via extension point). */
    DIAG_TYPE;
    
    /**
     * Returns a description for the element type.
     * 
     * @return a user-friendly description
     */
    public String getDescription() {
        switch (this) {
        case EDIT_PART:
            return Messages.getString("kiml.ui.54");
        case MODEL_ELEM:
            return Messages.getString("kiml.ui.55");
        case DIAG_TYPE:
            return Messages.getString("kiml.ui.56");
        }
        return null;
    }

}
