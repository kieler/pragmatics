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
import de.cau.cs.kieler.core.math.KielerMath;
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
            KVector groupSize = placeComponents(group, offset, spacing);
            
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
     * We first go through all the possible combinations of external port side connections. For each
     * combination, we compute a placement for the components with the given combination, remembering
     * the amount of space the placement takes up.
     * 
     * We then go through all the combinations again, this time moving the components so as not to
     * overlap other components.
     */
    
    
    /**
     * Computes a placement for the components in the given component group.
     * 
     * @param group the group whose components are to be placed.
     * @param offset the offset to apply to the group's components along the way.
     * @param spacing the amount of space to leave between two components.
     * @return the group's size.
     */
    private KVector placeComponents(final ComponentGroup group, final KVector offset,
            final double spacing) {
        
        // Determine the spacing between two components
        // Place the different sector components and remember the amount of space their placement uses.
        // In this phase, we pretend that no other components are in the component group.
        KVector sizeC = placeComponentsInRows(
                group.getComponents(ComponentGroup.CONN_C), spacing);
        KVector sizeN = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_N), spacing);
        KVector sizeS = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_S), spacing);
        KVector sizeW = placeComponentsVertically(
                group.getComponents(ComponentGroup.CONN_W), spacing);
        KVector sizeE = placeComponentsVertically(
                group.getComponents(ComponentGroup.CONN_E), spacing);
        KVector sizeNW = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_NW), spacing);
        KVector sizeNE = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_NE), spacing);
        KVector sizeSW = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_SW), spacing);
        KVector sizeSE = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_SE), spacing);
        KVector sizeWE = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_WE), spacing);
        KVector sizeNS = placeComponentsVertically(
                group.getComponents(ComponentGroup.CONN_NS), spacing);
        KVector sizeNWE = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_NWE), spacing);
        KVector sizeSWE = placeComponentsHorizontally(
                group.getComponents(ComponentGroup.CONN_SWE), spacing);
        KVector sizeWNS = placeComponentsVertically(
                group.getComponents(ComponentGroup.CONN_WNS), spacing);
        KVector sizeENS = placeComponentsVertically(
                group.getComponents(ComponentGroup.CONN_ENS), spacing);
        
        // Find the maximum height of the three rows and the maximum width of the three columns the
        // component group is divided into (we're adding a fourth row for WE components and a fourth
        // column for NS components to make the placement easier later)
        double colLeftWidth = KielerMath.maxd(sizeNW.x, sizeW.x, sizeSW.x, sizeWNS.x);
        double colMidWidth = KielerMath.maxd(sizeN.x, sizeC.x, sizeS.x);
        double colNsWidth = sizeNS.x;
        double colRightWidth = KielerMath.maxd(sizeNE.x, sizeE.x, sizeSE.x, sizeENS.x);
        double rowTopHeight = KielerMath.maxd(sizeNW.y, sizeN.y, sizeNE.y, sizeNWE.y);
        double rowMidHeight = KielerMath.maxd(sizeW.y, sizeC.y, sizeE.y);
        double rowWeHeight = sizeWE.y;
        double rowBottomHeight = KielerMath.maxd(sizeSW.y, sizeS.y, sizeSE.y, sizeSWE.y);
        
        // With the individual placements computed, we now move the components to their final place,
        // taking the size of other component placements into account (the NW, NWE, and WNS components
        // stay at coordinates (0,0) and thus don't need to be moved around)
        offsetGraphs(group.getComponents(ComponentGroup.CONN_C),
                colLeftWidth + colNsWidth,
                rowTopHeight + rowWeHeight);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_N),
                colLeftWidth + colNsWidth,
                0.0);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_S),
                colLeftWidth + colNsWidth,
                rowTopHeight + rowWeHeight + rowMidHeight);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_W),
                0.0,
                rowTopHeight + rowWeHeight);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_E),
                colLeftWidth + colNsWidth + colMidWidth,
                rowTopHeight + rowWeHeight);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_NE),
                colLeftWidth + colNsWidth + colMidWidth,
                0.0);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_SW),
                0.0,
                rowTopHeight + rowWeHeight + rowMidHeight);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_SE),
                colLeftWidth + colNsWidth + colMidWidth,
                rowTopHeight + rowWeHeight + rowMidHeight);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_WE),
                0.0,
                rowTopHeight);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_NS),
                colLeftWidth,
                0.0);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_SWE),
                0.0,
                rowTopHeight + rowWeHeight + rowMidHeight);
        offsetGraphs(group.getComponents(ComponentGroup.CONN_ENS),
                colLeftWidth + colNsWidth + colMidWidth,
                0.0);
        
        // Return the size of this component group
        return new KVector(colLeftWidth + colMidWidth + colNsWidth + colRightWidth,
                rowTopHeight + rowMidHeight + rowWeHeight + rowBottomHeight);
    }
    
    /**
     * Places the given collection of components along a horizontal line.
     * 
     * @param components the components to place.
     * @param spacing the amount of space to leave between two components.
     * @return the space used by the component placement, including spacing to the right and to the
     *         bottom of the components.
     */
    private KVector placeComponentsHorizontally(final Collection<LayeredGraph> components,
            final double spacing) {
        
        KVector size = new KVector();
        
        // Iterate over the components and place them
        for (LayeredGraph component : components) {
            offsetGraph(component, size.x, 0.0);
            
            size.x += spacing + component.getSize().x;
            size.y = Math.max(size.y, component.getSize().y);
        }
        
        // Add vertical spacing, if necessary
        if (size.y > 0.0) {
            size.y += spacing;
        }
        
        return size;
    }
    
    /**
     * Places the given collection of components along a vertical line.
     * 
     * @param components the components to place.
     * @param spacing the amount of space to leave between two components.
     * @return the space used by the component placement.
     */
    private KVector placeComponentsVertically(final Collection<LayeredGraph> components,
            final double spacing) {
        
        KVector size = new KVector();
        
        // Iterate over the components and place them
        for (LayeredGraph component : components) {
            offsetGraph(component, 0.0, size.y);
            
            size.y += spacing + component.getSize().y;
            size.x = Math.max(size.x, component.getSize().x);
        }
        
        // Add horizontal spacing, if necessary
        if (size.x > 0.0) {
            size.x += spacing;
        }
        
        return size;
    }
    
    /**
     * Place the given collection of components in multiple rows.
     * 
     * @param components the components to place.
     * @param spacing the amount of space to leave between two components.
     * @return the space used by the component placement.
     */
    private KVector placeComponentsInRows(final Collection<LayeredGraph> components,
            final double spacing) {
        
        // TODO: Change implementation to actually produce more than one row.
        
        KVector size = new KVector();
        
        // Iterate over the components and place them
        for (LayeredGraph component : components) {
            offsetGraph(component, size.x, 0.0);
            
            size.x += spacing + component.getSize().x;
            size.y = Math.max(size.y, component.getSize().y);
        }
        
        // Add vertical spacing, if necessary
        if (size.y > 0.0) {
            size.y += spacing;
        }
        
        return size;
    }

}
