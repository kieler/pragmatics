/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus.sequence.graph;

import de.cau.cs.kieler.core.properties.MapPropertyHolder;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;

public class SGraphElement extends MapPropertyHolder {
    private static final long serialVersionUID = -7980530866752118344L;
    // CHECKSTYLEOFF VisibilityModifier
    /** Identifier value, may be arbitrarily used by algorithms. */
    public int id;
    // CHECKSTYLEON VisibilityModifier
    
    /**
     * {@inheritDoc}
     */
    public int compareTo(final LGraphElement other) {
        return this.id - other.id;
    }
}
