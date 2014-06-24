/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.properties;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;

/**
 * @author uru
 */
public final class InternalColaProperties {

    /*--------------------------------------------------------------------------------------------
     *                          Internal Use only
     */

    /**
     * Whether libavoid treated a certain edge, ie bendpoints are set and should be applied back to
     * the source kgraph.
     */
    public static final IProperty<Boolean> LIBAVOID_WORKED = new Property<Boolean>(
            "codaflow.libavoid.worked", false);

    /**
     * The original object from which a graph element was created.
     */
    public static final IProperty<List<KEdge>> EDGE_CHAIN = new Property<List<KEdge>>(
            "cola.edgeChain", new LinkedList<KEdge>());

    /**
     * Sub routes of a hierarchy-crossing {@link de.cau.cs.kieler.klay.cola.graph.CEdge CEdge}.
     */
    public static final IProperty<List<KVectorChain>> EDGE_SUB_ROUTES =
            new Property<List<KVectorChain>>("libavoid.subRoutes", new LinkedList<KVectorChain>());

    /**
     * Positions of ports that represent hierarchy transitions on hierarchy-crossing edges.
     */
    public static final IProperty<List<Pair<KPort, KVector>>> EDGE_CHECKPOINTS =
            new Property<List<Pair<KPort, KVector>>>("cola.checkpoints",
                    new LinkedList<Pair<KPort, KVector>>());

    /**
     * Whether aca aligned an edge. 
     */
    public static final IProperty<Boolean> ACA_EDGE_ALIGNED = new Property<Boolean>(
            "aca.edgeAligned", false);

    /**
     * Utility class.
     */
    private InternalColaProperties() {
    }

}
