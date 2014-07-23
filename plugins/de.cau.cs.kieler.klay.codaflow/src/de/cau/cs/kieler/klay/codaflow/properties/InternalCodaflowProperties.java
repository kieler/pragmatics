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

import java.util.ArrayList;
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
public final class InternalCodaflowProperties {

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
     * {@link de.cau.cs.kieler.adaptagrams.cgraph.CEdge CEdge}s that represent multiple
     * {@link KEdge}s should hold this property. For directed edges the order is relevant.
     * 
     * Edge routing algorithms may determine separate routes for the elements of the 
     * edge chain. See {@link #EDGE_SUB_ROUTES}.
     */
    public static final IProperty<List<KEdge>> EDGE_CHAIN = new Property<List<KEdge>>(
            "codaflow.hierarchical.edgeChain", new ArrayList<KEdge>());

    /**
     * Sub routes for edge chains as specified by {@link #EDGE_CHAIN}.
     */
    public static final IProperty<List<KVectorChain>> EDGE_SUB_ROUTES =
            new Property<List<KVectorChain>>("libavoid.subRoutes", new ArrayList<KVectorChain>());

    /**
     * Positions of ports that represent hierarchy transitions on hierarchy-crossing edges.
     */
    public static final IProperty<List<Pair<KPort, KVector>>> EDGE_CHECKPOINTS =
            new Property<List<Pair<KPort, KVector>>>("cola.checkpoints",
                    new LinkedList<Pair<KPort, KVector>>());

    /**
     * Whether an edge is a hyperedge.
     */
    public static final IProperty<Boolean> HIERARCHICAL_HYPEREDGE = new Property<Boolean>(
            "codaflow.hierarchicalHyperedge", false);
    
    /**
     * Whether aca aligned an edge.
     */
    public static final IProperty<Boolean> ACA_EDGE_ALIGNED = new Property<Boolean>(
            "aca.edgeAligned", false);

    /**
     * Whether a node is part of a tree.
     */
    public static final IProperty<Boolean> PART_OF_TREE = new Property<Boolean>(
            "codaflow.partOfTree", false);

    /**
     * Utility class.
     */
    private InternalCodaflowProperties() {
    }

}
