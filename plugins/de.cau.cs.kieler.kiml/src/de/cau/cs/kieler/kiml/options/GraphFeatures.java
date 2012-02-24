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
package de.cau.cs.kieler.kiml.options;

import java.util.EnumSet;

import de.cau.cs.kieler.core.util.IDataObject;

/**
 * Graph features used for automatic recognition of the suitability of layout algorithms.
 *
 * @author msp
 */
public class GraphFeatures implements IDataObject {

    /** the serial version UID. */
    private static final long serialVersionUID = -1415920110365097914L;
    
    /**
     * Definition of specific graph features.
     */
    public enum GraphFeature {
        /** edges connecting a node with itself. */
        SELF_LOOPS,
        /** multiple edges with the same source and target node. */
        MULTI_EDGES,
        /** labels that are associated with edges. */
        EDGE_LABELS,
        /** edges are connected to nodes over ports. */
        PORTS,
        /** edges that connect nodes from different hierarchy levels. */
        HIERARCHY_EDGES;
    }
    
    /** the set of graph features. */
    private EnumSet<GraphFeature> featureSet = EnumSet.noneOf(GraphFeature.class);

    /**
     * {@inheritDoc}
     */
    public void parse(final String string) {
        // TODO Auto-generated method stub
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Returns the set of graph features.
     * 
     * @return the graph feature set
     */
    public EnumSet<GraphFeature> getFeatureSet() {
        return featureSet;
    }

}
