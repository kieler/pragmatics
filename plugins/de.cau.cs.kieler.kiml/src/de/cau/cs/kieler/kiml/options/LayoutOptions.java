/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.options;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;

/**
 * Definition of layout options and utility methods to get and set these
 * options.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class LayoutOptions {
    
    ///////   PROGRAMMATIC LAYOUT OPTIONS   ///////

    /** layout option key: diagram type (string). */
    public static final String DIAGRAM_TYPE_ID = "de.cau.cs.kieler.layout.options.diagramType";
    /** diagram type property. */
    public static final IProperty<String> DIAGRAM_TYPE = new Property<String>(DIAGRAM_TYPE_ID);
    /** layout option key: placement positions for edge labels (enum). */
    public static final String EDGE_LABEL_PLACEMENT_ID =
        "de.cau.cs.kieler.layout.options.edgeLabelPlacement";
    /** edge label placement property. */
    public static final IProperty<EdgeLabelPlacement> EDGE_LABEL_PLACEMENT
            = new Property<EdgeLabelPlacement>(EDGE_LABEL_PLACEMENT_ID, EdgeLabelPlacement.UNDEFINED);
    /** layout option key: edge type (enum). */
    public static final String EDGE_TYPE_ID = "de.cau.cs.kieler.layout.options.edgeType";
    /** edge type property. */
    public static final IProperty<EdgeType> EDGE_TYPE = new Property<EdgeType>(
            EDGE_TYPE_ID, EdgeType.NONE);
    /** layout option key: font name (string). */
    public static final String FONT_NAME_ID = "de.cau.cs.kieler.layout.options.fontName";
    /** font name property. */
    public static final IProperty<String> FONT_NAME = new Property<String>(FONT_NAME_ID);
    /** layout option key: font size (integer). */
    public static final String FONT_SIZE_ID = "de.cau.cs.kieler.layout.options.fontSize";
    /** font size property. */
    public static final IProperty<Integer> FONT_SIZE = new Property<Integer>(FONT_SIZE_ID, 0);
    /** layout option key: hypernode (boolean). */
    public static final String HYPERNODE_ID = "de.cau.cs.kieler.layout.options.hypernode";
    /** hypernode property. */
    public static final IProperty<Boolean> HYPERNODE = new Property<Boolean>(HYPERNODE_ID, false);
    /** layout option key: distance of node contents to the boundary (float). */
    public static final String INSETS_ID = "de.cau.cs.kieler.layout.options.insets";
    /** insets property. */
    public static final IProperty<KInsets> INSETS = new Property<KInsets>(INSETS_ID);
    /** layout option key: minimal height (float). */
    public static final String MIN_HEIGHT_ID = "de.cau.cs.kieler.layout.options.minHeight";
    /** minimal height property. */
    public static final IProperty<Float> MIN_HEIGHT = new Property<Float>(MIN_HEIGHT_ID, 0f);
    /** layout option key: minimal width (float). */
    public static final String MIN_WIDTH_ID = "de.cau.cs.kieler.layout.options.minWidth";
    /** minimal width property. */
    public static final IProperty<Float> MIN_WIDTH = new Property<Float>(MIN_WIDTH_ID, 0f);
    /** layout option key: no layout (boolean). */
    public static final String NO_LAYOUT_ID = "de.cau.cs.kieler.layout.options.noLayout";
    /** 'no layout' property. */
    public static final IProperty<Boolean> NO_LAYOUT = new Property<Boolean>(NO_LAYOUT_ID, false);
    /** layout option key: side of a port on its node's boundary (enum). */
    public static final String PORT_SIDE_ID = "de.cau.cs.kieler.layout.options.portSide";
    /** port side property. */
    public static final IProperty<PortSide> PORT_SIDE = new Property<PortSide>(PORT_SIDE_ID,
            PortSide.UNDEFINED);
    /** layout option key: rank of a port (integer). */
    public static final String PORT_RANK_ID = "de.cau.cs.kieler.layout.options.portRank";
    /** port rank property. */
    public static final IProperty<Integer> PORT_RANK = new Property<Integer>(PORT_RANK_ID, -1);
    /** layout option key: shape of a node (enum). */
    public static final String SHAPE_ID = "de.cau.cs.kieler.layout.options.shape";
    /** node shape property. */
    public static final IProperty<Shape> SHAPE = new Property<Shape>(SHAPE_ID, Shape.UNDEFINED);

    
    ///////  USER INTERFACE LAYOUT OPTIONS  ///////
    
    /** layout option key: aimed aspect ratio (float). */
    public static final String ASPECT_RATIO_ID = "de.cau.cs.kieler.layout.options.aspectRatio";
    /** layout option key: distance to border of the drawing (float). */
    public static final String BORDER_SPACING_ID = "de.cau.cs.kieler.layout.options.borderSpacing";
    /** layout option key: edge routing style (enum). */
    public static final String EDGE_ROUTING_ID = "de.cau.cs.kieler.layout.options.edgeRouting";
    /** layout option key: expand nodes to fill their parent (boolean). */
    public static final String EXPAND_NODES_ID = "de.cau.cs.kieler.layout.options.expandNodes";
    /** layout option key: size constraint for nodes (boolean). */
    public static final String FIXED_SIZE_ID = "de.cau.cs.kieler.layout.options.fixedSize";
    /** layout option key: optimize layout for user interaction (boolean). */
    public static final String INTERACTIVE_ID = "de.cau.cs.kieler.layout.options.interactive";    
    /** layout option key: spacing of edge labels to edges (float). */
    public static final String LABEL_SPACING_ID = "de.cau.cs.kieler.layout.options.labelSpacing";
    /** layout option key: layout descendants (boolean). */
    public static final String LAYOUT_DESCENDANTS_ID
            = "de.cau.cs.kieler.layout.options.layoutDescendants";
    /** layout option key: direction of layout (enum). */
    public static final String LAYOUT_DIRECTION_ID = "de.cau.cs.kieler.layout.options.layoutDirection";
    /** layout option key: layout hint (string). */
    public static final String LAYOUT_HINT_ID = "de.cau.cs.kieler.layout.options.layoutHint";
    /** layout option key: minimal distance between elements (float). */
    public static final String MIN_SPACING_ID = "de.cau.cs.kieler.layout.options.minSpacing";    
    /** layout option key: constraints for port positions (enum). */
    public static final String PORT_CONSTRAINTS_ID = "de.cau.cs.kieler.layout.options.portConstraints";
    /** layout option key: priority of elements (integer). */
    public static final String PRIORITY_ID = "de.cau.cs.kieler.layout.options.priority";
    /** layout option key: randomization seed (integer). */
    public static final String RANDOM_SEED_ID = "de.cau.cs.kieler.layout.options.randomSeed";    
    


    /**
     * Hide constructor to avoid instantiation.
     */
    private LayoutOptions() {
    }

    /**
     * Returns a string valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @return the valued option for the given layout data, or {@code null} if
     *         there is no such option
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#getProperty(IProperty)} instead
     */
    public static String getString(final KGraphData layoutData, final String optionId) {
        return (String) layoutData.getProperty(LayoutServices.getInstance()
                .getLayoutOptionData(optionId));
    }

    /**
     * Sets a string valued option for the given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @param value the option value to set
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#setProperty(IProperty, Object)}
     *      instead
     */
    public static void setString(final KGraphData layoutData, final String optionId,
            final String value) {
        layoutData.setProperty(LayoutServices.getInstance().getLayoutOptionData(optionId), value);
    }

    /**
     * Returns an integer valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @return the integer valued option for the given layout data, or {@code MIN_VALUE} if
     *         there is no such option
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#getProperty(IProperty)} instead
     */
    public static int getInt(final KGraphData layoutData, final String optionId) {
        Object value = layoutData.getProperty(LayoutServices.getInstance()
                .getLayoutOptionData(optionId));
        if (value instanceof Integer) {
            return (Integer) value;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Sets an integer valued option for the given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @param value the option value to set
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#setProperty(IProperty, Object)}
     *      instead
     */
    public static void setInt(final KGraphData layoutData, final String optionId,
            final int value) {
        layoutData.setProperty(LayoutServices.getInstance().getLayoutOptionData(optionId), value);
    }
    
    /**
     * Returns a float valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @return the valued option for the given layout data, or {@code NaN} if
     *         there is no such option
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#getProperty(IProperty)} instead
     */
    public static float getFloat(final KGraphData layoutData, final String optionId) {
        Object value = layoutData.getProperty(LayoutServices.getInstance()
                .getLayoutOptionData(optionId));
        if (value instanceof Float) {
            return (Float) value;
        } else {
            return Float.NaN;
        }
    }

    /**
     * Sets a float valued option for the given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @param value minimal spacing to set
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#setProperty(IProperty, Object)}
     *      instead
     */
    public static void setFloat(final KGraphData layoutData, final String optionId,
            final float value) {
        layoutData.setProperty(LayoutServices.getInstance().getLayoutOptionData(optionId), value);
    }

    /**
     * Returns a boolean valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @return the boolean valued option for the given layout data, or {@code false}
     *         if there is no such option
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#getProperty(IProperty)} instead
     */
    public static boolean getBoolean(final KGraphData layoutData, final String optionId) {
        Object value = layoutData.getProperty(LayoutServices.getInstance()
                .getLayoutOptionData(optionId));
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            return false;
        }
    }

    /**
     * Sets a boolean valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @param value the option value to set
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#setProperty(IProperty, Object)}
     *      instead
     */
    public static void setBoolean(final KGraphData layoutData, final String optionId,
            final boolean value) {
        layoutData.setProperty(LayoutServices.getInstance().getLayoutOptionData(optionId), value);
    }

    /**
     * Returns an enumeration valued option for a given layout data instance.
     * 
     * @param <T> type of enumeration class
     * @param layoutData layout data for a graph element
     * @param enumClass the enumeration class
     * @return the enumeration valued option for the given layout data, or the first
     *         enumeration value if there is no such option
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#getProperty(IProperty)} instead
     */
    public static <T extends Enum<?>> T getEnum(final KGraphData layoutData,
            final Class<T> enumClass) {
        return (T) layoutData.getProperty(LayoutServices.getInstance()
                .getLayoutOptionData(enumClass));
    }

    /**
     * Sets an enumeration valued option for the given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param value the option value to set
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#setProperty(IProperty, Object)}
     *      instead
     */
    public static void setEnum(final KGraphData layoutData, final Enum<?> value) {
        layoutData.setProperty(LayoutServices.getInstance().getLayoutOptionData(
                value.getClass()), value);
    }

    /**
     * Returns the insets for a given layout data instance. If no insets option
     * is set, default values are created.
     * 
     * @param <T> type of object; only insets are supported
     * @param layoutData layout data for a graph element
     * @param clazz the object class; must equal {@code KInsets.class}
     * @return the insets for the given layout data
     * @deprecated use {@link de.cau.cs.kieler.core.kgraph.KGraphData#getProperty(IProperty)} instead
     */
    public static <T extends EObject> T getObject(final KGraphData layoutData,
            final Class<T> clazz) {
        if (clazz == KInsets.class) {
            KInsets insets = (KInsets) layoutData.getProperty(INSETS);
            if (insets == null) {
                insets = KLayoutDataFactory.eINSTANCE.createKInsets();
                layoutData.setProperty(INSETS, insets);
            }
            return clazz.cast(insets);
        } else {
            return null;
        }
    }
    
}
