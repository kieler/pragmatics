/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola.properties;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * @author uru
 * 
 */
public final class ColaProperties {

    /**
     * Whether to vertically align nodes wrt to their left bound.
     */
    public static final IProperty<HorizontalAlignment> ALIGN_NODES =
            new Property<HorizontalAlignment>("de.cau.cs.kieler.klay.cola.alignNodesHorizontal",
                    HorizontalAlignment.LEFT);

    /**
     * Whether to consider previous node positions.
     */
    public static final IProperty<Boolean> CONSIDER_PREVIOUS_POSITION = new Property<Boolean>(
            "de.cau.cs.kieler.klay.cola.considerPreviousPositions", false);

    /**
     * Whether to emphasize direction of the diagram, i.e. whether separation constraints should be
     * created.
     */
    public static final IProperty<Boolean> EMPHASIZE_DIRECTION = new Property<Boolean>(
            "de.cau.cs.kieler.klay.cola.emphasizeDirection", true);

    /**
     * How to handle cycles in the graph.
     */
    public static final IProperty<CycleTreatment> CYCLE_TREATMENT = new Property<CycleTreatment>(
            "de.cau.cs.kieler.klay.cola.cycleTreatment", CycleTreatment.NONE);

    /**
     * Specifies how ideal edge lengths are determined.
     */ 
    public static final IProperty<EdgeLengthStrategy> EDGE_LENGTH_STRATEGY =
            new Property<EdgeLengthStrategy>("de.cau.cs.kieler.klay.cola.edgeLengthStrategy",
                    EdgeLengthStrategy.UNIFORM);

    /**
     * A scalar modifier of ideal edge lengths.
     * */
    public static final IProperty<Float> IDEAL_EDGE_LENGTHS = new Property<Float>(
            "de.cau.cs.kieler.klay.cola.idealEdgeLengths", 100f, 1f);

    /**
     * The maximum length of a centre S-bend connector segments to attempt to improve (default
     * 120.0).
     */
    public static final IProperty<Float> MOVE_LIMIT = new Property<Float>(
            "de.cau.cs.kieler.klay.cola.moveLimit", 120f, 10f);

    /**
     * Should ports be turned into dummy nodes?
     */
    public static final IProperty<Boolean> PORT_DUMMIES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.cola.dummyPortNodes", true);

    /**
     * Distance of dummy port nodes to their parent node. This allows the ports to breathe a bit and
     * move along the outside of the node.
     */
    public static final IProperty<Float> PORT_DUMMY_BREATHE = new Property<Float>(
            "de.cau.cs.kieler.klay.cola.portDummyBreathe", 2f);

    /**
     * Whether to consider previous node positions.
     */
    public static final IProperty<Boolean> REPOSITION_HIERARCHICAL_PORTS = new Property<Boolean>(
            "de.cau.cs.kieler.klay.cola.repositionHierarchicalPorts", false);

    public static final IProperty<Boolean> ACA_ALIGN_CROSS_HIERARCHY_EDGES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.cola.aca.alignCrossHierarchyEdges", false);
    
    public static final IProperty<Boolean> ACA_FAVOR_LONG_EDGES = new Property<Boolean>(
            "de.cau.cs.kieler.klay.cola.aca.favorLongEdges", false);
    
    public static final IProperty<Boolean> ACA_POST_COMPACTION = new Property<Boolean>(
            "de.cau.cs.kieler.klay.cola.aca.postCompaction", false);
    
    /**
     * Utility class.
     */
    private ColaProperties() {
    }
}
