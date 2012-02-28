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

import java.util.List;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.klay.layered.graph.LayeredGraph;
import de.cau.cs.kieler.klay.layered.properties.Properties;

/**
 * A graph placer that tries to place the components of a graph with taking connections to external
 * ports into account. This graph placer should only be used if the constraints applying to the
 * external ports are either {@code FREE} or {@code FIXED_SIDES}.
 * 
 * <p>This placer first greedily builds a list of {@link ComponentGroup} instances. It is greedy in
 * that it places a component in the first group it finds that is able to hold it. Afterwards, the
 * components in each group are placed. The different groups are then placed along a diagonal from
 * the top-left to the bottom-right corner.</p>
 * 
 * @author cds
 */
class ComponentGroupGraphPlacer extends GraphPlacer {
    
    ///////////////////////////////////////////////////////////////////////////////
    // Variables
    
    /**
     * List of component groups holding the different components.
     */
    private List<ComponentGroup> componentGroups = Lists.newLinkedList();
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // AbstractGraphPlacer

    /**
     * {@inheritDoc}
     */
    public LayeredGraph combine(final List<LayeredGraph> components) {
        // Create a new layered graph
        LayeredGraph result = new LayeredGraph();
        
        // Check if there are any components to be placed
        if (components.isEmpty()) {
            return result;
        }
        
        // Set the graph properties
        result.copyProperties(components.get(0));
        result.getInsets().copy(components.get(0).getInsets());
        
        // Construct component groups
        for (LayeredGraph component : components) {
            addComponent(component);
        }
        
        // Place components in each group
        KVector offset = new KVector();
        float spacing = 2 * components.get(0).getProperty(Properties.OBJ_SPACING);
        
        for (ComponentGroup group : componentGroups) {
            KVector groupSize = placeComponents(group, offset);
            
            // Compute the new offset
            offset.x += spacing + groupSize.x;
            offset.y += spacing + groupSize.y;
        }
        
        // TODO: Place groups, if no offset was used in the previous step.
        
        return result;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Component Group Building
    
    /**
     * Adds the given component to the first component group that has place for it.
     * 
     * @param component the component to be placed.
     */
    private void addComponent(final LayeredGraph component) {
        // Check if one of the existing component groups has some place left
        for (ComponentGroup group : componentGroups) {
            if (group.add(component)) {
                return;
            }
        }
        
        // Create a new component group for the component
        componentGroups.add(new ComponentGroup(component));
    }
    
    
    ///////////////////////////////////////////////////////////////////////////////
    // Component Placement
    
    /**
     * Computes a placement for the components in the given component group.
     * 
     * @param group the group whose components are to be placed.
     * @param offset the offset to apply to the group's components along the way.
     * @return the group's size.
     */
    private KVector placeComponents(final ComponentGroup group, final KVector offset) {
        // TODO: Implement.
        return null;
    }

}
