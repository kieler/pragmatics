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
