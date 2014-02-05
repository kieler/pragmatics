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
package de.cau.klay.gwt.client.layout;

import static de.cau.cs.kieler.kiml.options.LayoutOptions.ALGORITHM;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.ALIGNMENT;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.DIRECTION;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.EDGE_ROUTING;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.EXPAND_NODES;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.INTERACTIVE;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.LABEL_SPACING;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.LAYOUT_HIERARCHY;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.NODE_LABEL_PLACEMENT;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.NO_LAYOUT;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.PORT_CONSTRAINTS;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.PORT_INDEX;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.PORT_LABEL_PLACEMENT;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.PORT_SIDE;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.POSITION;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.RANDOM_SEED;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.SEPARATE_CC;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.SIZE_CONSTRAINT;
import static de.cau.cs.kieler.kiml.options.LayoutOptions.SIZE_OPTIONS;
import static de.cau.cs.kieler.klay.layered.properties.Properties.ASPECT_RATIO;
import static de.cau.cs.kieler.klay.layered.properties.Properties.BORDER_SPACING;
import static de.cau.cs.kieler.klay.layered.properties.Properties.CROSS_MIN;
import static de.cau.cs.kieler.klay.layered.properties.Properties.CYCLE_BREAKING;
import static de.cau.cs.kieler.klay.layered.properties.Properties.DEBUG_MODE;
import static de.cau.cs.kieler.klay.layered.properties.Properties.DISTRIBUTE_NODES;
import static de.cau.cs.kieler.klay.layered.properties.Properties.EDGE_LABEL_SIDE;
import static de.cau.cs.kieler.klay.layered.properties.Properties.EDGE_SPACING_FACTOR;
import static de.cau.cs.kieler.klay.layered.properties.Properties.FEEDBACK_EDGES;
import static de.cau.cs.kieler.klay.layered.properties.Properties.FIXED_ALIGNMENT;
import static de.cau.cs.kieler.klay.layered.properties.Properties.LAYER_CONSTRAINT;
import static de.cau.cs.kieler.klay.layered.properties.Properties.MERGE_PORTS;
import static de.cau.cs.kieler.klay.layered.properties.Properties.NODE_LAYERING;
import static de.cau.cs.kieler.klay.layered.properties.Properties.NODE_PLACER;
import static de.cau.cs.kieler.klay.layered.properties.Properties.OBJ_SPACING;
import static de.cau.cs.kieler.klay.layered.properties.Properties.OBJ_SPACING_IN_LAYER_FACTOR;
import static de.cau.cs.kieler.klay.layered.properties.Properties.OFFSET;
import static de.cau.cs.kieler.klay.layered.properties.Properties.PRIORITY;
import static de.cau.cs.kieler.klay.layered.properties.Properties.THOROUGHNESS;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gwt.json.client.JSONValue;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.UnsupportedGraphException;
import de.cau.cs.kieler.kiml.options.Alignment;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.NodeLabelPlacement;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortLabelPlacement;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.options.SizeConstraint;
import de.cau.cs.kieler.kiml.options.SizeOptions;
import de.cau.cs.kieler.klay.layered.graph.LGraphElement;
import de.cau.cs.kieler.klay.layered.graph.LLabel.LabelSide;
import de.cau.cs.kieler.klay.layered.p1cycles.CycleBreakingStrategy;
import de.cau.cs.kieler.klay.layered.p2layers.LayeringStrategy;
import de.cau.cs.kieler.klay.layered.p3order.CrossingMinimizationStrategy;
import de.cau.cs.kieler.klay.layered.p4nodes.NodePlacementStrategy;
import de.cau.cs.kieler.klay.layered.properties.FixedAlignment;
import de.cau.cs.kieler.klay.layered.properties.LayerConstraint;

/**
 * GWT does not support any of Java's reflection mechanisms. Hence we have to 
 * most of the job to map layout options to their respective types by hand.
 * 
 * @author uru
 */
public class LayoutOptionResolver {

    private static final Pair<Set<String>, Map<String, IProperty<?>>> STRING_TYPES = createTypesSet(
            ALGORITHM
            // klay
            );
    
    private static final Pair<Set<String>, Map<String, IProperty<?>>> INT_TYPES = createTypesSet(
            PORT_INDEX,
            RANDOM_SEED,
            // klay
            PRIORITY,
            THOROUGHNESS
            );
    
    private static final Pair<Set<String>, Map<String, IProperty<?>>> BOOLEAN_TYPES = createTypesSet(
            NO_LAYOUT,
            EXPAND_NODES,
            INTERACTIVE,
            LAYOUT_HIERARCHY,
            SEPARATE_CC,
            // klay
            DEBUG_MODE,
            DISTRIBUTE_NODES,
            MERGE_PORTS,
            FEEDBACK_EDGES
            );
    
    private static final Pair<Set<String>, Map<String, IProperty<?>>> FLOAT_TYPES = createTypesSet(
            LABEL_SPACING,
            // klay
            BORDER_SPACING,
            OFFSET,
            ASPECT_RATIO,
            OBJ_SPACING,
            OBJ_SPACING_IN_LAYER_FACTOR,
            EDGE_SPACING_FACTOR
            );

    private static final Pair<Set<String>, Map<String, IProperty<?>>> ENUM_TYPES = createTypesSet(
            PORT_SIDE,
            ALIGNMENT,
            DIRECTION,
            EDGE_ROUTING,
            PORT_CONSTRAINTS,
            PORT_LABEL_PLACEMENT,
            // klay
            CYCLE_BREAKING,
            NODE_LAYERING,
            CROSS_MIN,
            NODE_PLACER,
            FIXED_ALIGNMENT,
            EDGE_LABEL_SIDE,
            LAYER_CONSTRAINT
            );
    
    private static final Pair<Set<String>, Map<String, IProperty<?>>> ENUMSET_TYPES = createTypesSet(
            NODE_LABEL_PLACEMENT,
            SIZE_CONSTRAINT,
            SIZE_OPTIONS
            // klay
            );
    
    private static final Pair<Set<String>, Map<String, IProperty<?>>> OTHER_TYPES = createTypesSet(
            POSITION
            // klay
            );

    /**
     * Convenience method to create the id->type mappings of the layout options. 
     */
    private static Pair<Set<String>, Map<String, IProperty<?>>> createTypesSet(final IProperty<?>... props) {
        Set<String> set = Sets.newHashSet();
        Map<String, IProperty<?>> map = Maps.newHashMap();
        for (IProperty<?> p : props) {
            set.add(p.getId());
            map.put(p.getId(), p);
        }
        
        Set<String> iset = ImmutableSet.copyOf(set);
        Map<String, IProperty<?>> imap = ImmutableMap.copyOf(map);
        return Pair.of(iset, imap);
    }

    /**
     * Tries to parse the passed {@code value} according to the type of the layout option registered
     * for the {@code id}. If this is successful, the property is set for the passed {@code element}.
     * 
     * @param element
     *            the graph's element for which the property should be set
     * @param id
     *            the id of the layout option
     * @param value
     *            the value to parse
     * @throws UnsupportedGraphException
     *             in case anything went wrong. This might be due to the id not being registered or
     *             the value being of an invalid format.
     */
    @SuppressWarnings("unchecked")
    public static void setOption(final LGraphElement element, final String id, final JSONValue value) {

        if (STRING_TYPES.getFirst().contains(id)) {
            /*----------------------------------------------------------------
             *          STRING TYPE
             */
            if (value.isString() == null) {
                throw new UnsupportedGraphException("Invalid boolean format for property '" + id
                        + "' (" + value + ").");
            }
            IProperty<String> p = (IProperty<String>) STRING_TYPES.getSecond().get(id);
            String s = value.isString().stringValue();
            element.setProperty(p, s);
            return;

        } else if (INT_TYPES.getFirst().contains(id)) {
            /*----------------------------------------------------------------
             *          INT TYPE
             */
            try {
                if (value.isNumber() == null) {
                    throw new NumberFormatException();
                }
                IProperty<Integer> p = (IProperty<Integer>) INT_TYPES.getSecond().get(id);
                // check that it's a proper integer
                Integer val = Integer.valueOf(String.valueOf(value.isNumber().doubleValue()));
                element.setProperty(p, val);
                return;
                
            } catch (NumberFormatException nfe) {
                throw new UnsupportedGraphException("Invalid integer format for property '" + id
                        + "' (" + value + ").");
            }

        } else if (BOOLEAN_TYPES.getFirst().contains(id)) {
            /*----------------------------------------------------------------
             *          BOOLEAN TYPE
             */
            if (value.isBoolean() == null) {
                throw new UnsupportedGraphException("Invalid boolean format for property '" + id
                        + "' (" + value + ").");
            }
            IProperty<Boolean> p = (IProperty<Boolean>) BOOLEAN_TYPES.getSecond().get(id);
            Boolean val = new Boolean(value.isBoolean().booleanValue());
            element.setProperty(p, val);
            return;

        } else if (FLOAT_TYPES.getFirst().contains(id)) {
            /*----------------------------------------------------------------
             *          FLOAT TYPE
             */
            if (value.isNumber() == null) {
                throw new UnsupportedGraphException("Invalid float format for property '" + id
                        + "' (" + value + ").");
            }
            IProperty<Float> p = (IProperty<Float>) FLOAT_TYPES.getSecond().get(id);
            Float val = new Float(value.isNumber().doubleValue());
            element.setProperty(p, val);
            return;

        } else if (ENUM_TYPES.getFirst().contains(id)) {
            /*----------------------------------------------------------------
             *          ENUM TYPE
             */
            if (value.isString() == null) {
                throw new UnsupportedGraphException("Invalid enum format for property '" + id
                        + "' (" + value + ").");
            }
            
            String enumValue = value.isString().stringValue();
            Enum<?> enumeration = null;
            
            try {
                if (PORT_SIDE.getId().equals(id)) {
                    enumeration = PortSide.valueOf(enumValue);
                } else if (ALIGNMENT.getId().equals(id)) {
                    enumeration = Alignment.valueOf(enumValue);
                } else if (DIRECTION.getId().equals(id)) {
                    enumeration = Direction.valueOf(enumValue);
                } else if (EDGE_ROUTING.getId().equals(id)) {
                    enumeration = EdgeRouting.valueOf(enumValue);
                } else if (PORT_CONSTRAINTS.getId().equals(id)) {
                    enumeration = PortConstraints.valueOf(enumValue);
                } else if (PORT_LABEL_PLACEMENT.getId().equals(id)) {
                    enumeration = PortLabelPlacement.valueOf(enumValue);
                }
                // KLAY
                else if (CYCLE_BREAKING.getId().equals(id)) {
                    enumeration = CycleBreakingStrategy.valueOf(enumValue);
                } else if (NODE_LAYERING.getId().equals(id)) {
                    enumeration = LayeringStrategy.valueOf(enumValue);
                } else if (CROSS_MIN.getId().equals(id)) {
                    enumeration = CrossingMinimizationStrategy.valueOf(enumValue);
                } else if (NODE_PLACER.getId().equals(id)) {
                    enumeration = NodePlacementStrategy.valueOf(enumValue);
                } else if (FIXED_ALIGNMENT.getId().equals(id)) {
                    enumeration = FixedAlignment.valueOf(enumValue);
                } else if (EDGE_LABEL_SIDE.getId().equals(id)) {
                    enumeration = LabelSide.valueOf(enumValue);
                } else if (LAYER_CONSTRAINT.getId().equals(id)) {
                    enumeration = LayerConstraint.valueOf(enumValue);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new UnsupportedGraphException("Invalid enum format for property '" + id
                        + "' (" + value + ").");
            }
            
            // set the property
            IProperty<Enum<?>> p = (IProperty<Enum<?>>) ENUM_TYPES.getSecond().get(id);
            element.setProperty(p, enumeration);
            return;
            
        } else if (ENUMSET_TYPES.getFirst().contains(id)) {
            /*----------------------------------------------------------------
             *          ENUMSET TYPE
             */
            if (value.isString() == null) {
                throw new UnsupportedGraphException("Invalid enum format for property '" + id
                        + "' (" + value + ").");
            }
            
            String enumSetValue = value.isString().stringValue();
            
            // we cannot use generics here, otherwise we would have to 
            // parameterize the set for each enum individually
            @SuppressWarnings("rawtypes")
            EnumSet set = null;
            
            // break the value string into its different components and iterate over them;
            // the string will be of the form "[a, b, c]"
            String[] components = enumSetValue.split("[\\[\\]\\s,]+");
            for (String component : components) {
                // Check for empty strings
                if (component.trim().length() == 0) {
                    continue;
                }
                
                if (NODE_LABEL_PLACEMENT.getId().equals(id)) {
                    if(set == null) {
                        set = EnumSet.noneOf(NodeLabelPlacement.class);
                    }
                    set.add(NodeLabelPlacement.valueOf(component));
                    
                } else if (SIZE_CONSTRAINT.getId().equals(id)) {
                    if(set == null) {
                        set = EnumSet.noneOf(SizeConstraint.class);
                    }
                    set.add(NodeLabelPlacement.valueOf(component));
                    
                } else if (SIZE_OPTIONS.getId().equals(id)) {
                    if(set == null) {
                        set = EnumSet.noneOf(SizeOptions.class);
                    }
                    set.add(NodeLabelPlacement.valueOf(component));
                    
                }
            }
            
            // finished collecting, add the property
            // again we cannot use generics on EnumSet
            @SuppressWarnings("rawtypes")
            IProperty<EnumSet> p = (IProperty<EnumSet>) ENUMSET_TYPES.getSecond().get(id);
            element.setProperty(p, set);
            
            return;
            
        } else if (OTHER_TYPES.getFirst().contains(id)) {
            /*----------------------------------------------------------------
             *          OTHER TYPE
             */
            if (value.isString() == null) {
                throw new UnsupportedGraphException("Invalid _other_ format for property '" + id
                        + "' (" + value + ").");
            }

            if (POSITION.getId().equals(id)) {

                try {
                    KVector v = new KVector();
                    v.parse(value.isString().stringValue());
                    IProperty<KVector> p = (IProperty<KVector>) OTHER_TYPES.getSecond().get(id);
                    element.setProperty(p, v);
                    return;

                } catch (IllegalArgumentException exception) {
                    throw new UnsupportedGraphException("Invalid KVector format for property '" + id
                            + "' (" + value + ").");
                }
            }

        }
        
        throw new UnsupportedGraphException("Unsupported layout option '" + id + "' (" + value + ").");
    }

}
