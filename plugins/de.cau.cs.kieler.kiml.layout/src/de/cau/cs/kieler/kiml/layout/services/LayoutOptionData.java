/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.kiml.layout.services;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;

/**
 * Data type used to store information for a layout option.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayoutOptionData {

	/** literal value constant for booleans */
	public static final String BOOLEAN_LITERAL = "boolean";
	/** literal value constant for integer numbers */
	public static final String INT_LITERAL     = "int";
	/** literal value constant for strings */
	public static final String STRING_LITERAL  = "string";
	/** literal value constant for floating point numbers */
	public static final String FLOAT_LITERAL   = "float";
	/** literal value constant for enumerations */
	public static final String ENUM_LITERAL = "enum";
	
	/** Data type enumeration */
	public enum Type {
		UNDEFINED, BOOLEAN, INT, STRING, FLOAT, ENUM;
	}
	
	/** identifier of the layout option */
    public String id;
    /** type of the layout option */
    public Type type = Type.UNDEFINED;
    /** user friendly name of the layout option */
    public String name;
    /** a description to be displayed in the UI */
    public String description;
    
    /** cached value of the enumeration class, used for ENUM typed options */
    @SuppressWarnings("unchecked")
    private Class<? extends Enum> enumClass = null;
    
    /**
     * Sets the type field depending on the given literal.
     * 
     * @param typeLiteral a string value that is expected to be equal to one of the
     *     predefined literal value constants
     * @throws IllegalArgumentException if the type literal is invalid
     */
    public void setType(String typeLiteral) {
    	if (BOOLEAN_LITERAL.equals(typeLiteral))
    		type = Type.BOOLEAN;
    	else if (INT_LITERAL.equals(typeLiteral))
    		type = Type.INT;
    	else if (STRING_LITERAL.equals(typeLiteral))
    		type = Type.STRING;
    	else if (FLOAT_LITERAL.equals(typeLiteral))
    		type = Type.FLOAT;
    	else if (ENUM_LITERAL.equals(typeLiteral))
    	    type = Type.ENUM;
    	else throw new IllegalArgumentException("The given type literal is invalid.");
    }
    
    /**
     * Parses a string value for this layout option.
     * 
     * @param valueString a serialized value
     * @return an instance of the corresponding correctly typed value, or {@code null}
     *     if the given value string is invalid
     * @throws IllegalStateException if the type that is set for this layout option
     *     is invalid
     */
    @SuppressWarnings("unchecked")
    public Object parseValue(String valueString) {
        if (valueString == null)
            return null;
        
        switch (type) {
        case BOOLEAN:
            return Boolean.valueOf(valueString);
        case INT:
            try {
                return Integer.valueOf(valueString);
            }
            catch (NumberFormatException exception) {
                return null;
            }
        case STRING:
            return valueString;
        case FLOAT:
            try {
                return Float.valueOf(valueString);
            }
            catch (NumberFormatException exception) {
                return null;
            }
        case ENUM:
            try {
                if (enumClass == null)
                    enumClass = LayoutOptions.getEnumClass(id);
                if (enumClass == null)
                    throw new IllegalStateException("Unknown enumeration type set for this layout option.");
                return Enum.valueOf(enumClass, valueString);
            }
            catch (IllegalArgumentException exception) {
                return null;
            }
        default:
            throw new IllegalStateException("Invalid type set for this layout option.");
        }
    }
    
    /**
     * Sets the given value of this layout option in the given layout data.
     * 
     * @param layoutData layout data for which the option shall be set
     * @param value the new value of this option
     * @throws IllegalStateException if the type that is set for this layout option
     *     is invalid
     */
    public void setValue(KLayoutData layoutData, Object value) {
        KOption option = layoutData.getOption(id);
        if (option == null) {
            switch (type) {
            case BOOLEAN:
                option = KLayoutDataFactory.eINSTANCE.createKBooleanOption();
                break;
            case ENUM:
            case INT:
                option = KLayoutDataFactory.eINSTANCE.createKIntOption();
                break;
            case STRING:
                option = KLayoutDataFactory.eINSTANCE.createKStringOption();
                break;
            case FLOAT:
                option = KLayoutDataFactory.eINSTANCE.createKFloatOption();
                break;
            default:
                throw new IllegalStateException("Invalid type set for this layout option.");
            }
            option.setKey(id);
            layoutData.getOptions().add(option);
        }
        switch (type) {
        case BOOLEAN:
            KBooleanOption booleanOption = (KBooleanOption)option;
            booleanOption.setValue(((Boolean)value).booleanValue());
            break;
        case ENUM:
            KIntOption intOption = (KIntOption)option;
            intOption.setValue(((Enum<?>)value).ordinal());
            break;
        case INT:
            intOption = (KIntOption)option;
            intOption.setValue(((Integer)value).intValue());
            break;
        case STRING:
            KStringOption stringOption = (KStringOption)option;
            stringOption.setValue((String)value);
            break;
        case FLOAT:
            KFloatOption floatOption = (KFloatOption)option;
            floatOption.setValue(((Float)value).floatValue());
            break;
        default:
            throw new IllegalStateException("Invalid type set for this layout option.");
        }
    }
    
}
