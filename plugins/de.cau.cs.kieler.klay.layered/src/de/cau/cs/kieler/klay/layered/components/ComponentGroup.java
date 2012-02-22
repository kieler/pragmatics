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
package de.cau.cs.kieler.klay.layered.components;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * Represents a group of connected components grouped for layout purposes.
 * 
 * <p>A component group is conceptually divided into nine sectors as such: (the nine sectors are
 * enumerated in the {@link ComponentGroupSector} enumeration)</p>
 * <pre>
 *   +----+----+----+
 *   | nw | n  | ne |
 *   +----+----+----+
 *   | w  | c  | e  |
 *   +----+----+----+
 *   | sw | s  | se |
 *   +----+----+----+
 * </pre>
 * <p>The port sides of external ports a component connects to determines which sector(s) it will
 * occupy. This is best illustrated by some examples:</p>
 * <ul>
 *   <li>Let {@code c} be a component connected to a northern port. Then {@code c} would be placed in
 *       the {@code n} sector.</li>
 *   <li>Let {@code c} be a comopnent connected to a southern port and to an eastern port. Then
 *       {@code c} would be placed in the {@code se} sector.</li>
 *   <li>Let {@code c} be a component connected to no port at all. Then {@code c} would be placed in the
 *       {@code c} sector.</li>
 *   <li>Let {@code c} be a component connected to a western and to an eastern port. Then {@code c}
 *       would be placed in the {@code w}, {@code c}, and {@code e} sectors. If {@code c} would also
 *       connected to a southern port, it would also occupy the {@code sw}, {@code sc}, and {@code se}
 *       sectors.</li>
 * </ul>
 * <p>With this placement comes a bunch of constraints. For example, for a component to occupy the
 * top three sectors, none of them must be occupied by another component yet. If the addition of a
 * component to this group would cause a constraint to be violated, it cannot be added.</p>
 * 
 * @author cds
 */
class ComponentGroup {
    
    ///////////////////////////////////////////////////////////////////////////////
    // Constants
    
    /**
     * A map of constraints used to decide whether a component can be placed in this group.
     * 
     * <p>For a new component that is to be placed in this group, the set of external port sides
     * it connects to implies which sets of port sides of other components it is compatible to.
     * For instance, a component connecting to a northern and an eastern port requires that no
     * other component connects to this particular combination of ports. This map maps sets of
     * port sides to a list of port side sets that must not already exist in this group for a
     * component to be added.</p>
     */
    private static final Multimap<Set<PortSide>, Set<PortSide>> CONSTRAINTS = HashMultimap.create();
    
    static {
        // Create the 15 possible port side combination sets (except for the case without port
        // connections, which doesn't have any constraints)
        Set<PortSide> w = EnumSet.of(PortSide.WEST);
        Set<PortSide> e = EnumSet.of(PortSide.EAST);
        Set<PortSide> n = EnumSet.of(PortSide.NORTH);
        Set<PortSide> s = EnumSet.of(PortSide.SOUTH);

        Set<PortSide> ns = EnumSet.of(PortSide.NORTH, PortSide.SOUTH);
        Set<PortSide> we = EnumSet.of(PortSide.EAST, PortSide.WEST);
        Set<PortSide> nw = EnumSet.of(PortSide.NORTH, PortSide.WEST);
        Set<PortSide> ne = EnumSet.of(PortSide.NORTH, PortSide.EAST);
        Set<PortSide> sw = EnumSet.of(PortSide.SOUTH, PortSide.WEST);
        Set<PortSide> se = EnumSet.of(PortSide.SOUTH, PortSide.EAST);
        
        Set<PortSide> nwe = EnumSet.of(PortSide.NORTH, PortSide.WEST, PortSide.EAST);
        Set<PortSide> swe = EnumSet.of(PortSide.SOUTH, PortSide.WEST, PortSide.EAST);
        Set<PortSide> wns = EnumSet.of(PortSide.WEST, PortSide.NORTH, PortSide.SOUTH);
        Set<PortSide> ens = EnumSet.of(PortSide.EAST, PortSide.NORTH, PortSide.SOUTH);
        
        // Setup constraints
        CONSTRAINTS.put(w, wns);
        CONSTRAINTS.put(e, ens);
        CONSTRAINTS.put(n, nwe);
        CONSTRAINTS.put(s, swe);
        CONSTRAINTS.put(ns, we);
        CONSTRAINTS.put(ns, nwe);
        CONSTRAINTS.put(ns, swe);
        CONSTRAINTS.put(we, ns);
        CONSTRAINTS.put(we, wns);
        CONSTRAINTS.put(we, ens);
        CONSTRAINTS.put(nw, nw);
        CONSTRAINTS.put(nw, nwe);
        CONSTRAINTS.put(nw, wns);
        CONSTRAINTS.put(ne, ne);
        CONSTRAINTS.put(ne, nwe);
        CONSTRAINTS.put(ne, ens);
        CONSTRAINTS.put(sw, sw);
        CONSTRAINTS.put(sw, swe);
        CONSTRAINTS.put(sw, wns);
        CONSTRAINTS.put(se, se);
        CONSTRAINTS.put(se, swe);
        CONSTRAINTS.put(se, ens);
        CONSTRAINTS.put(nwe, n);
        CONSTRAINTS.put(nwe, ns);
        CONSTRAINTS.put(nwe, nw);
        CONSTRAINTS.put(nwe, ne);
        CONSTRAINTS.put(nwe, nwe);
        CONSTRAINTS.put(nwe, wns);
        CONSTRAINTS.put(nwe, ens);
        CONSTRAINTS.put(swe, s);
        CONSTRAINTS.put(swe, ns);
        CONSTRAINTS.put(swe, sw);
        CONSTRAINTS.put(swe, se);
        CONSTRAINTS.put(swe, swe);
        CONSTRAINTS.put(swe, wns);
        CONSTRAINTS.put(swe, ens);
        CONSTRAINTS.put(wns, w);
        CONSTRAINTS.put(wns, we);
        CONSTRAINTS.put(wns, nw);
        CONSTRAINTS.put(wns, sw);
        CONSTRAINTS.put(wns, nwe);
        CONSTRAINTS.put(wns, swe);
        CONSTRAINTS.put(wns, wns);
        CONSTRAINTS.put(ens, e);
        CONSTRAINTS.put(ens, we);
        CONSTRAINTS.put(ens, ne);
        CONSTRAINTS.put(ens, se);
        CONSTRAINTS.put(ens, nwe);
        CONSTRAINTS.put(ens, swe);
        CONSTRAINTS.put(ens, ens);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Variables
    
    /**
     * The list of connected components in this group.
     */
    private List<LayeredGraph> components = Lists.newLinkedList();
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Constructors
    
    /**
     * Constructs a new, empty component group.
     */
    public ComponentGroup() {
        
    }
    
    /**
     * Constructs a new component group with the given initial component. This is equivalent to
     * constructing an empty component group and then calling {@link #add(LayeredGraph)}.
     * 
     * @param component the component to be added to the group.
     */
    public ComponentGroup(final LayeredGraph component) {
        add(component);
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // Component Placement
    
    /**
     * Tries to add the given component to the group. Before adding the component, a call to
     * {@link #canAdd(LayeredGraph)} determines if the component can actually be added to this
     * group.
     * 
     * @param component the component to be added to this group.
     * @return {@code true} if the component was successfully added, {@code false} otherwise.
     */
    public boolean add(final LayeredGraph component) {
        if (canAdd(component)) {
            components.add(component);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks whether this group has enough space left to add a given component.
     * 
     * @param component the component to be added to the group.
     * @return {@code true} if the group has enough space left to add the component, {@code false}
     *         otherwise.
     */
    private boolean canAdd(final LayeredGraph component) {
        // Check if we have a component with incompatible external port sides
        Set<PortSide> candidateSides = component.getProperty(Properties.EXT_PORT_CONNECTIONS);
        Collection<Set<PortSide>> portSideConstraints = CONSTRAINTS.get(candidateSides);
        
        for (LayeredGraph existingComponent : components) {
            Set<PortSide> existingSides = existingComponent.getProperty(Properties.EXT_PORT_CONNECTIONS);
            
            if (portSideConstraints.contains(existingSides)) {
                // A component has one of the incompatible port side sets
                return false;
            }
        }
        
        // We haven't found any conflicting components
        return true;
    }
}
