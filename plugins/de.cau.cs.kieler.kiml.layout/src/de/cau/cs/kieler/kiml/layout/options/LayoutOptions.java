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
package de.cau.cs.kieler.kiml.layout.options;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;

/**
 * Definition of layout options and utility methods to get and set these
 * options.
 * 
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public final class LayoutOptions {

    /**
     * Hide constructor to avoid instantiation.
     */
    private LayoutOptions() {
    }
    
    /** layout option key: layout hint. */
    public static final String LAYOUT_HINT = "de.cau.cs.kieler.layout.options.layoutHint";
    /** layout option key: diagram type. */
    public static final String DIAGRAM_TYPE = "de.cau.cs.kieler.layout.options.diagramType";
    /** layout option key: direction of layout. */
    public static final String LAYOUT_DIRECTION = "de.cau.cs.kieler.layout.options.layoutDirection";
    /** layout option key: distance of node contents to the boundary. */
    public static final String INSETS = "de.cau.cs.kieler.layout.options.insets";
    /** layout option key: side of a port on its node's boundary. */
    public static final String PORT_SIDE = "de.cau.cs.kieler.layout.options.portSide";
    /** layout option key: constraints for port positions. */
    public static final String PORT_CONSTRAINTS = "de.cau.cs.kieler.layout.options.portConstraints";
    /** layout option key: rank of a port. */
    public static final String PORT_RANK = "de.cau.cs.kieler.layout.options.portRank";
    /** layout option key: size constraint for nodes. */
    public static final String FIXED_SIZE = "de.cau.cs.kieler.layout.options.fixedSize";
    /** layout option key: placement positions for edge labels. */
    public static final String EDGE_LABEL_PLACEMENT =
        "de.cau.cs.kieler.layout.options.edgeLabelPlacement";
    /** layout option key: edge routing style. */
    public static final String EDGE_ROUTING = "de.cau.cs.kieler.layout.options.edgeRouting";
    /** layout option key: minimal distance between elements. */
    public static final String MIN_SPACING = "de.cau.cs.kieler.layout.options.minSpacing";    
    /** layout option key: distance to border of the drawing. */
    public static final String BORDER_SPACING = "de.cau.cs.kieler.layout.options.borderSpacing";
    /** layout option key: priority of elements. */
    public static final String PRIORITY = "de.cau.cs.kieler.layout.options.priority";
    /** layout option key: font name. */
    public static final String FONT_NAME = "de.cau.cs.kieler.layout.options.fontName";
    /** layout option key: font size. */
    public static final String FONT_SIZE = "de.cau.cs.kieler.layout.options.fontSize";
    /** layout option key: shape of a node. */
    public static final String SHAPE = "de.cau.cs.kieler.layout.options.shape";
    /** layout option key: expand nodes to fill their parent. */
    public static final String EXPAND_NODES = "de.cau.cs.kieler.layout.options.expandNodes";
    /** layout option key: minimal width. */
    public static final String MIN_WIDTH = "de.cau.cs.kieler.layout.options.minWidth";
    /** layout option key: minimal height. */
    public static final String MIN_HEIGHT = "de.cau.cs.kieler.layout.options.minHeight";    
    /** layout option key: randomization seed. */
    public static final String RANDOM_SEED = "de.cau.cs.kieler.layout.options.randomSeed";    
    /** layout option key: optimize layout for user interaction. */
    public static final String INTERACTIVE = "de.cau.cs.kieler.layout.options.interactive";    
    /** layout option key: spacing of edge labels to edges. */
    public static final String LABEL_SPACING = "de.cau.cs.kieler.layout.options.labelSpacing";

    /** map of option identifiers to enumeration classes. */
    private static Map<String, Class<? extends Enum<?>>> id2enumMap =
            new HashMap<String, Class<? extends Enum<?>>>();
    /** map of enumeration classes to option identifiers. */
    private static Map<Class<? extends Enum<?>>, String> enum2idMap =
            new HashMap<Class<? extends Enum<?>>, String>();
    
    /**
     * Register the given enumeration class with an option identifier.
     * 
     * @param id identifier of the layout option
     * @param clazz enumeration class for the layout option
     */
    public static synchronized void registerEnum(final String id,
            final Class<? extends Enum<?>> clazz) {
        if (id != null && clazz != null) {
            id2enumMap.put(id, clazz);
            enum2idMap.put(clazz, id);
        }
    }
    
    /**
     * Resolves the class of an enumeration given by an identifier.
     * 
     * @param optionId identifier of a layout option that is represented by an
     *         enumeration
     * @return the corresponding enumeration class, or {@code null} if there
     *         is no registered enumeration class for that identifier
     */
    public static Class<? extends Enum<?>> getEnumClass(final String optionId) {
        return id2enumMap.get(optionId);
    }

    /**
     * Returns a string valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @return the valued option for the given layout data, or {@code null} if
     *         there is no such option
     */
    public static String getString(final KLayoutData layoutData, final String optionId) {
        KStringOption stringOption = (KStringOption) layoutData.getOption(optionId);
        if (stringOption == null) {
            return null;
        } else {
            return stringOption.getValue();
        }
    }

    /**
     * Sets a string valued option for the given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @param value the option value to set
     */
    public static void setString(final KLayoutData layoutData, final String optionId,
            final String value) {
        KStringOption stringOption = (KStringOption) layoutData.getOption(optionId);
        if (stringOption == null) {
            stringOption = KLayoutDataFactory.eINSTANCE.createKStringOption();
            stringOption.setKey(optionId);
            layoutData.getOptions().add(stringOption);
        }
        stringOption.setValue(value);
    }

    /**
     * Returns an integer valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @return the integer valued option for the given layout data, or {@code MIN_VALUE} if
     *         there is no such option
     */
    public static int getInt(final KLayoutData layoutData, final String optionId) {
        KIntOption intOption = (KIntOption) layoutData.getOption(optionId);
        if (intOption == null) {
            return Integer.MIN_VALUE;
        } else {
            return intOption.getValue();
        }
    }

    /**
     * Sets an integer valued option for the given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @param value the option value to set
     */
    public static void setInt(final KLayoutData layoutData, final String optionId,
            final int value) {
        KIntOption intOption = (KIntOption) layoutData.getOption(optionId);
        if (intOption == null) {
            intOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            intOption.setKey(optionId);
            layoutData.getOptions().add(intOption);
        }
        intOption.setValue(value);
    }
    
    /**
     * Returns a float valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @return the valued option for the given layout data, or {@code NaN} if
     *         there is no such option
     */
    public static float getFloat(final KLayoutData layoutData, final String optionId) {
        KFloatOption floatOption = (KFloatOption) layoutData.getOption(optionId);
        if (floatOption == null) {
            return Float.NaN;
        } else {
            return floatOption.getValue();
        }
    }

    /**
     * Sets a float valued option for the given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @param value minimal spacing to set
     */
    public static void setFloat(final KLayoutData layoutData, final String optionId,
            final float value) {
        KFloatOption floatOption = (KFloatOption) layoutData.getOption(optionId);
        if (floatOption == null) {
            floatOption = KLayoutDataFactory.eINSTANCE.createKFloatOption();
            floatOption.setKey(optionId);
            layoutData.getOptions().add(floatOption);
        }
        floatOption.setValue(value);
    }

    /**
     * Returns a boolean valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @return the boolean valued option for the given layout data, or {@code false}
     *         if there is no such option
     */
    public static boolean getBoolean(final KLayoutData layoutData, final String optionId) {
        KBooleanOption boolOption = (KBooleanOption) layoutData.getOption(optionId);
        if (boolOption == null) {
            return false;
        } else {
            return boolOption.isValue();
        }
    }

    /**
     * Sets a boolean valued option for a given layout data instance.
     * 
     * @param layoutData layout data for a graph element
     * @param optionId identifier of a layout option
     * @param value the option value to set
     */
    public static void setBoolean(final KLayoutData layoutData, final String optionId,
            final boolean value) {
        KBooleanOption boolOption = (KBooleanOption) layoutData.getOption(optionId);
        if (boolOption == null) {
            boolOption = KLayoutDataFactory.eINSTANCE.createKBooleanOption();
            boolOption.setKey(optionId);
            layoutData.getOptions().add(boolOption);
        }
        boolOption.setValue(value);
    }

    /**
     * Returns an enumeration valued option for a given layout data instance.
     * 
     * @param <T> type of enumeration class
     * @param layoutData layout data for a graph element
     * @param enumClass the enumeration class
     * @return the enumeration valued option for the given layout data, or the first
     *         enumeration value if there is no such option
     */
    public static <T extends Enum<?>> T getEnum(final KLayoutData layoutData,
            final Class<T> enumClass) {
        String optionId = enum2idMap.get(enumClass);
        KIntOption enumOption = (KIntOption) layoutData.getOption(optionId);
        if (enumOption == null) {
            return enumClass.getEnumConstants()[0];
        } else {
            return enumClass.getEnumConstants()[enumOption.getValue()];
        }
    }

    /**
     * Sets an enumeration valued option for the given layout data instance.
     * 
     * @param <T> type of enumeration class
     * @param layoutData layout data for a graph element
     * @param value the option value to set
     */
    public static <T extends Enum<?>> void setEnum(final KLayoutData layoutData, final T value) {
        String optionId = enum2idMap.get(value.getClass());
        KIntOption enumOption = (KIntOption) layoutData.getOption(optionId);
        if (enumOption == null) {
            enumOption = KLayoutDataFactory.eINSTANCE.createKIntOption();
            enumOption.setKey(optionId);
            layoutData.getOptions().add(enumOption);
        }
        enumOption.setValue(value.ordinal());
    }

    /**
     * Returns the insets for a given layout data instance. If no insets option
     * is set, default values are created.
     * 
     * @param <T> type of object; only insets are supported
     * @param layoutData layout data for a graph element
     * @param clazz the object class; must equal {@code KInsets.class}
     * @return the insets for the given layout data
     */
    public static <T extends EObject> T getObject(final KLayoutData layoutData,
            final Class<T> clazz) {
        if (clazz == KInsets.class) {
            KObjectOption objectOption = (KObjectOption) layoutData.getOption(INSETS);
            if (objectOption == null || objectOption.getValue() == null) {
                return clazz.cast(KLayoutDataFactory.eINSTANCE.createKInsets());
            } else {
                return clazz.cast(objectOption.getValue());
            }
        } else {
            return null;
        }
    }
    
}
