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
import java.util.Iterator;
import java.util.StringTokenizer;

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
        /**
         * Edges connecting a node with itself.
         */
        SELF_LOOPS,
        /**
         * Multiple edges with the same source and target node.
         */
        MULTI_EDGES,
        /**
         * Labels that are associated with edges.
         */
        EDGE_LABELS,
        /**
         * Edges are connected to nodes over ports.
         */
        PORTS,
        /**
         * A full compound graph hierarchy, including edges that connect nodes from different
         * hierarchy levels, and potentially also edges incident to compound nodes.
         * @see LayoutOptions#LAYOUT_HIERARCHY
         */
        COMPOUND,
        /**
         * Edges connect nodes from different clusters, but not the cluster parent nodes.
         */
        CLUSTERS;
    }
    
    /** the set of graph features. */
    private EnumSet<GraphFeature> featureSet = EnumSet.noneOf(GraphFeature.class);

    /**
     * {@inheritDoc}
     */
    public void parse(final String string) {
        featureSet.clear();
        StringTokenizer tokenizer = new StringTokenizer(string, ", \t");
        while (tokenizer.hasMoreTokens()) {
            // this may throw an IllegalArgumentException
            featureSet.add(GraphFeature.valueOf(tokenizer.nextToken().toUpperCase()));
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Iterator<GraphFeature> featureIter = featureSet.iterator();
        while (featureIter.hasNext()) {
            builder.append(featureIter.next().toString());
            if (featureIter.hasNext()) {
                builder.append(',');
            }
        }
        return builder.toString();
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
