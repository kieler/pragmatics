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

import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;

/**
 * Represents a group of connected components grouped for layout purposes.
 * 
 * <p>A component group is divided into nine sectors as such: (the nine sectors are enumerated in the
 * {@link ComponentGroupSector} enumeration)</p>
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
 * <p>This placement strategy implies a few restrictions:</p>
 * <ul>
 *   <li>The corner sectors may only accommodate at most one component each.</li>
 *   <li>If a component occupies multiple sectors, the restrictions of the most severely restricted
 *       sector applies to all the sectors. That is, if a component occupies multiple sectors of which
 *       one is a corner sector, the other sectors may also only contain one component each, even if
 *       they would usually be able to contain more.</li>
 * </ul>
 * 
 * @author cds
 */
class ComponentGroup {
    
    ///////////////////////////////////////////////////////////////////////////////
    // Variables
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Constructors
    
    /**
     * Constructs a new, empty component group.
     */
    public ComponentGroup() {
        
    }
    
    /**
     * Constructs a new component group with the given initial component. This is equivalent to
     * constructing an empty component group and then calling {@link #place(LayeredGraph)}.
     * 
     * @param component the component to be placed in the group.
     */
    public ComponentGroup(final LayeredGraph component) {
        place(component);
    }

    
    ///////////////////////////////////////////////////////////////////////////////
    // Component Placement
    
    /**
     * Tries to place the given component in the group. Before placing the component, a call to
     * {@link #canPlace(LayeredGraph)} determines if the component can actually be placed in this
     * group.
     * 
     * @param component the component to be placed in this group.
     * @return {@code true} if the component was successfully placed, {@code false} otherwise.
     */
    public boolean place(final LayeredGraph component) {
        if (canPlace(component)) {
            // TODO: Implement.
            
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks whether this group has enough space left to place a given component.
     * 
     * @param component the component to be placed in the group.
     * @return {@code true} if the group has enough space left to place the component, {@code false}
     *         otherwise.
     */
    private boolean canPlace(final LayeredGraph component) {
        // TODO: Implement.
        return true;
    }
}
