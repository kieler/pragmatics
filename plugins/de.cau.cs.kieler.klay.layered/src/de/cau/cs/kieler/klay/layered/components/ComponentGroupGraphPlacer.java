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
    
    /* Component placement works as follows.
     * 
     * Each component group is divided into nine sectors in three rows and three columns. We therefore
     * have two separating horizontal and vertical lines each whose position we will remember while
     * placing the components.
     * 
     * To place the components, we work our way through all the different possible combinations of
     * external port sides the components may be connected to. We don't need to pay attention to which
     * combinations are possible -- the process of component group creation already took care of that
     * for us. We start in the top left corner and roughly work our way through the three rows of the
     * component group, in a certain order. For each collection of components, we call an appropriate
     * placement method which places the components and returns the amount of space they took up. That
     * information is then used to keep track of the position of the separating lines, which in turn
     * give us an idea of where to start placing the next components.
     * 
     * Once we're done placing the different components, we use the position of the lowermost horizontal
     * and the rightmost vertical separating line to calculate how much space this component group takes
     * up.
     */
    
    
    /**
     * Computes a placement for the components in the given component group.
     * 
     * @param group the group whose components are to be placed.
     * @param offset the offset to apply to the group's components along the way.
     * @return the group's size.
     */
    private KVector placeComponents(final ComponentGroup group, final KVector offset) {
        // Keep track of the positions of the horizontal and vertical separating lines
        double upperHorizontalLine = 0.0;
        double lowerHorizontalLine = 0.0;
        double leftVerticalLine = 0.0;
        double rightVerticalLine = 0.0;
        
        // Components to be placed and the size of their placement
        Collection<LayeredGraph> components;
        KVector placementSize;
        
        
        // NW component
        components = group.getComponents(ComponentGroup.CONN_NW);
        placementSize = placeComponentsHorizontally(components, new KVector(0.0, 0.0));
        
        leftVerticalLine = placementSize.x;
        rightVerticalLine = placementSize.x;
        upperHorizontalLine = placementSize.y;
        lowerHorizontalLine = placementSize.y;
        
        
        // NWE component
        components = group.getComponents(ComponentGroup.CONN_NWE);
        placementSize = placeComponentsHorizontally(components, new KVector(0.0, 0.0));
        
        upperHorizontalLine = placementSize.y;
        lowerHorizontalLine = placementSize.y;
        
        
        // WNS component
        components = group.getComponents(ComponentGroup.CONN_WNS);
        placementSize = placeComponentsHorizontally(components, new KVector(0.0, 0.0));

        leftVerticalLine = placementSize.x;
        rightVerticalLine = placementSize.x;
        
        
        // N component
        components = group.getComponents(ComponentGroup.CONN_N);
        placementSize = placeComponentsHorizontally(components, new KVector(leftVerticalLine, 0.0));
        
        upperHorizontalLine = Math.max(upperHorizontalLine, placementSize.y);
        lowerHorizontalLine = Math.max(lowerHorizontalLine, upperHorizontalLine);
        rightVerticalLine = Math.max(rightVerticalLine, leftVerticalLine + placementSize.x);
        
        
        // W components
        components = group.getComponents(ComponentGroup.CONN_W);
        placementSize = placeComponentsVertically(components, new KVector(0.0, upperHorizontalLine));
        
        lowerHorizontalLine = Math.max(lowerHorizontalLine, upperHorizontalLine + placementSize.y);
        leftVerticalLine = Math.max(leftVerticalLine, placementSize.x);
        rightVerticalLine = Math.max(rightVerticalLine, leftVerticalLine);
        
        
        // C components
        components = group.getComponents(ComponentGroup.CONN_C);
        placementSize = placeComponentsVertically(components, new KVector(
                leftVerticalLine, upperHorizontalLine));
        
        lowerHorizontalLine = Math.max(lowerHorizontalLine, upperHorizontalLine + placementSize.y);
        rightVerticalLine = Math.max(rightVerticalLine, leftVerticalLine + placementSize.x);
        
        // NS components
        
        // WE components
        
        
        
        
        // Return the size of this component group
        return new KVector(rightVerticalLine, lowerHorizontalLine);
    }
    
    /**
     * Places the given collection of components along a horizontal line starting at the given top left
     * coordinate.
     * 
     * @param components the components to place.
     * @param topLeft the top left corner of the placement area.
     * @return the space used by the component placement.
     */
    private KVector placeComponentsHorizontally(final Collection<LayeredGraph> components,
            final KVector topLeft) {
        
        return null;
    }
    
    /**
     * Places the given collection of components along a vertical line starting at the given top left
     * coordinate.
     * 
     * @param components the components to place.
     * @param topLeft the top left corner of the placement area.
     * @return the space used by the component placement.
     */
    private KVector placeComponentsVertically(final Collection<LayeredGraph> components,
            final KVector topLeft) {
        
        return null;
    }

}
