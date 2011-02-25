/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.intermediate;

import de.cau.cs.kieler.klay.layered.ILayoutProcessor;

/**
 * Definition of available intermediate layout processors for the layered layouter.
 * This enumeration also serves as a factory for intermediate layout processors.
 * 
 * @author cds
 */
public enum IntermediateLayoutProcessor {
    
    /** Takes a properly layered graph and removes the dummy nodes due to proper layering. */
    EDGE_JOINER,
    /** Takes a layered graph and turns it into a properly layered graph. */
    EDGE_SPLITTER,
    /** Takes a layered graph and inserts dummy nodes for edges connected to ports on odd sides. */
    ODD_PORT_SIDE_PROCESSOR,
    /** Sets the positions of ports. */
    PORT_ARRANGER,
    /** Takes the reversed edges of a graph and restores their original direction. */
    REVERSED_EDGE_RESTORER;
    
    
    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i ordinal value
     * @return the related enumeration value
     */
    public static IntermediateLayoutProcessor valueOf(final int i) {
        return values()[i];
    }
    
    
    /**
     * Creates an instance of the layout processor described by this instance.
     * 
     * @return the layout processor.
     */
    public ILayoutProcessor create() {
        switch (this) {
        case EDGE_JOINER:
            return new EdgeJoiner();
            
        case EDGE_SPLITTER:
            return new EdgeSplitter();
        
        case ODD_PORT_SIDE_PROCESSOR:
            return new OddPortSideProcessor();
        
        case PORT_ARRANGER:
            return new PortArranger();
        
        case REVERSED_EDGE_RESTORER:
            return new ReversedEdgeRestorer();
        
        default:
            return null;
        }
    }
}
