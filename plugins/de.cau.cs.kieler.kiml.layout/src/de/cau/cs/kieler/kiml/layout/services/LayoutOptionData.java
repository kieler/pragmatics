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

import java.util.StringTokenizer;

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
	
	/** data type enumeration */
	public enum Type {
		UNDEFINED, BOOLEAN, INT, STRING, FLOAT, ENUM;
	}
	
	/** literal value constant for diagram target */
	public static final String PARENTS_LITERAL = "parents";
	/** bit mask for diagram target */
	private static final int PARENTS_MASK = 0x01;
	/** literal value constant for nodes target */
	public static final String NODES_LITERAL = "nodes";
	/** bit mask for nodes target */
	private static final int NODES_MASK = 0x02;
	/** literal value constant for edges target */
	public static final String EDGES_LITERAL = "edges";
	/** bit mask for edges target */
	private static final int EDGES_MASK = 0x04;
	/** literal value constant for ports target */
	public static final String PORTS_LITERAL = "ports";
	/** bit mask for ports target */
	private static final int PORTS_MASK = 0x08;
	/** literal value constant for labels target */
	public static final String LABELS_LITERAL = "labels";
	/** bit mask for labels target */
	private static final int LABELS_MASK = 0x10;
	
	/** option target enumeration */
	public enum Target {
	    PARENTS, NODES, EDGES, PORTS, LABELS;
	}
	
	/** identifier of the layout option */
    public String id;
    /** type of the layout option */
    public Type type = Type.UNDEFINED;
    /** user friendly name of the layout option */
    public String name;
    /** a description to be displayed in the UI */
    public String description;
    
    /** configured targets (accessed through bit masks) */
    private int targets;
    /** cached value of the enumeration class, used for ENUM typed options */
    @SuppressWarnings("unchecked")
    private Class<? extends Enum> enumClass = null;
    /** cached value of the available choices */
    private String[] choices = null;
    
    /**
     * Checks whether the enumeration class is set correctly. This method must not be called
     * for options other than enumerations.
     */
    private void checkEnumClass() {
        if (enumClass == null)
            enumClass = LayoutOptions.getEnumClass(id);
        if (enumClass == null)
            throw new IllegalStateException("Unknown enumeration type set for this layout option.");
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        return obj instanceof LayoutOptionData && id.equals((LayoutOptionData)obj);
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
       return id.hashCode();
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (name != null && name.length() > 0)
            return name;
        else
            return id;
    }
    
    /**
     * Sets the type field depending on the given literal.
     * 
     * @param typeLiteral a string value that is expected to be equal to one of the
     *     predefined literal value constants
     * @throws IllegalArgumentException if the type literal is invalid
     */
    public void setType(String typeLiteral) {
    	if (BOOLEAN_LITERAL.equalsIgnoreCase(typeLiteral))
    		type = Type.BOOLEAN;
    	else if (INT_LITERAL.equalsIgnoreCase(typeLiteral))
    		type = Type.INT;
    	else if (STRING_LITERAL.equalsIgnoreCase(typeLiteral))
    		type = Type.STRING;
    	else if (FLOAT_LITERAL.equalsIgnoreCase(typeLiteral))
    		type = Type.FLOAT;
    	else if (ENUM_LITERAL.equalsIgnoreCase(typeLiteral))
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
                checkEnumClass();
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
     * Creates an array of choices that can be selected by the user to set a value for this option.
     * This makes only sense for enumeration type options.
     * 
     * @return an array of values to be displayed for the user
     */
    public String[] getChoices() {
        if (choices == null) {
            switch (type) {
            case ENUM:
                checkEnumClass();
                Enum<?>[] enums = enumClass.getEnumConstants();
                choices = new String[enums.length];
                for (int i = 0; i < enums.length; i++) {
                    choices[i] = enums[i].toString();
                }
                break;
            default:
                choices = new String[0];
            }
        }
        return choices;
    }
    
    /**
     * Returns the enumeration value for a given index.
     * 
     * @param intValue zero-based index of the enumeration value
     * @return the corresponding enumeration value
     */
    public Enum<?> getEnumValue(int intValue) {
        switch (type) {
        case ENUM:
            checkEnumClass();
            Enum<?>[] enums = enumClass.getEnumConstants();
            return enums[intValue];
        default:
            return null;
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

    /**
     * Sets the targets property of this layout option data.
     * 
     * @param targetsString comma separated list of targets
     */
    public void setTargets(String targetsString) {
        targets = 0;
        if (targetsString != null) {
            StringTokenizer tokenizer = new StringTokenizer(targetsString, ", \t");
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (token.equalsIgnoreCase(PARENTS_LITERAL))
                    targets |= PARENTS_MASK;
                else if (token.equalsIgnoreCase(NODES_LITERAL))
                    targets |= NODES_MASK;
                else if (token.equalsIgnoreCase(EDGES_LITERAL))
                    targets |= EDGES_MASK;
                else if (token.equalsIgnoreCase(PORTS_LITERAL))
                    targets |= PORTS_MASK;
                else if (token.equalsIgnoreCase(LABELS_LITERAL))
                    targets |= LABELS_MASK;
            }
        }
    }
    
    /**
     * Checks whether the given target is active for this layout option.
     * 
     * @param target a layout option target
     * @return true if the target is active
     */
    public boolean hasTarget(Target target) {
        switch (target) {
        case PARENTS:
            return (targets & PARENTS_MASK) != 0;
        case NODES:
            return (targets & NODES_MASK) != 0;
        case EDGES:
            return (targets & EDGES_MASK) != 0;
        case PORTS:
            return (targets & PORTS_MASK) != 0;
        case LABELS:
            return (targets & LABELS_MASK) != 0;
        default:
            return false;
        }
    }
    
    /**
     * Returns a user friendly description of the active targets of this layout option.
     * 
     * @return a description of the active targets, or {@code null} if there are no active targets
     */
    public String getTargetsDescription() {
        int bitCount = Integer.bitCount(targets);
        if (bitCount == 0)
            return null;
        StringBuffer description = new StringBuffer();
        int highest = Integer.SIZE - Integer.numberOfLeadingZeros(targets);
        int bitsRead = 0;
        for (int i = 0; i < highest; i++) {
            int mask = 1 << i;
            if ((targets & mask) != 0) {
                switch (mask) {
                case PARENTS_MASK:
                    description.append("Parents");
                    break;
                case NODES_MASK:
                    description.append("Nodes");
                    break;
                case EDGES_MASK:
                    description.append("Edges");
                    break;
                case PORTS_MASK:
                    description.append("Ports");
                    break;
                case LABELS_MASK:
                    description.append("Labels");
                    break;
                }
                bitsRead++;
                if (bitCount - bitsRead >= 2)
                    description.append(", ");
                else if (bitCount - bitsRead == 1)
                    description.append(" and ");
            }
        }
        return description.toString();
    }
    
}
