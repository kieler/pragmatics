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
package de.cau.cs.kieler.klay.layered.properties;

/**
 * Definition of port distribution strategies.
 *
 * @author msp
 */
public enum PortDistribution {
    
    /**
     * Port ranks are assigned a value in the range [0,1] beginning from the index of each node.
     */
    NODE_RELATIVE,
    /**
     * Port ranks are increased by counting the total number of ports in the layer.
     */
    LAYER_TOTAL;

}
