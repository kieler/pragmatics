/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.hierarchical.modules;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IAlgorithm;
import de.cau.cs.kieler.klodd.hierarchical.structures.Layer;
import de.cau.cs.kieler.klodd.hierarchical.structures.RoutingSlot;

/**
 * Interface for algorithms that calculate routing slots for connections going
 * from a layer to the subsequent one.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public interface ILayerwiseEdgePlacer extends IAlgorithm {

    /**
     * Determines a placement for each outgoing edge of the given layer.
     * 
     * @param layer layer to process
     * @param minDist minimal distance between elements
     * @return number of assigned slot ranks
     */
    int placeEdges(Layer layer, float minDist);

    /**
     * Returns the map of layer elements to their corresponding routing slots
     * that was constructed during the last run of <code>placeEdges</code>.
     * 
     * @return the slot map
     */
    Map<Object, RoutingSlot> getSlotMap();

}
