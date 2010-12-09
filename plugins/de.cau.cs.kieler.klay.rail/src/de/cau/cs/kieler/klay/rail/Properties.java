/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.rail;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.klay.rail.options.NodeType;

/**
 * Container for property definitions.
 * 
 * @author jjc
 * 
 */
public final class Properties {
    
    /** the original object from which a graph element was created. */
    public static final IProperty<Object> ORIGIN = new Property<Object>("origin");
    /** priority of elements. */
    public static final Property<Integer> PRIORITY = new Property<Integer>(LayoutOptions.PRIORITY, 0);
    /** flag for reversed edges. */
    public static final IProperty<Boolean> REVERSED = new Property<Boolean>("reversed", false);
    
    /** port constraints. */
    public static final Property<PortConstraints> PORT_CONS = new Property<PortConstraints>(
            LayoutOptions.PORT_CONSTRAINTS, PortConstraints.FREE);

    /** default value for object spacing. */
    public static final float DEF_SPACING = 20.0f;
    /** minimal spacing between objects. */
    public static final Property<Float> OBJ_SPACING = new Property<Float>(
            LayoutOptions.OBJ_SPACING, DEF_SPACING);
    
    /** default value for border spacing. */
    public static final float DEF_BORDER = 10.0f;
    /** minimal border spacing. */
    public static final Property<Float> BOR_SPACING = new Property<Float>(
            LayoutOptions.BORDER_SPACING, DEF_BORDER);

    /** option identifier for the node type. */
    public static final String NODE_TYPE_ID = "de.cau.cs.kieler.klay.rail.nodeType";
    /** determines of which railway element kind the node is. */
    public static final IProperty<NodeType> NODE_TYPE = new Property<NodeType>(NODE_TYPE_ID,
            NodeType.BREACH_OR_CLOSE);
    
    /** option identifier for the entry point option. */
    public static final String ENTRY_POINT_ID = "de.cau.cs.kieler.klay.rail.entryPoint";
    /** determines the entry point of a connected component. can only have one. */
    public static final IProperty<Boolean> ENTRY_POINT = new Property<Boolean>(ENTRY_POINT_ID, false);

    /** Hidden default constructor. */
    private Properties() {
    }

}
