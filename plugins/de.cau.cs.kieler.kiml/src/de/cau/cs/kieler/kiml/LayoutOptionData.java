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
package de.cau.cs.kieler.kiml;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.IDataObject;

/**
 * Data type used to store information for a layout option.
 * 
 * @param <T> data type for the option data
 * @kieler.rating 2011-02-01 yellow
 *     reviewed by cmot, soh
 * @author msp
 */
public class LayoutOptionData<T> implements ILayoutData, IProperty<T>, Comparable<IProperty<?>> {

    /** literal value constant for booleans. */
    public static final String BOOLEAN_LITERAL = "boolean";
    /** literal value constant for integer numbers. */
    public static final String INT_LITERAL = "int";
    /** literal value constant for strings. */
    public static final String STRING_LITERAL = "string";
    /** literal value constant for floating point numbers. */
    public static final String FLOAT_LITERAL = "float";
    /** literal value constant for enumerations. */
    public static final String ENUM_LITERAL = "enum";
    /** literal value constant for data objects. */
    public static final String OBJECT_LITERAL = "object";
    /** literal value constant for enumeration coming from remote layout. */
    public static final String REMOTEENUM_LITERAL = "remoteenum";
    /** default name for layout options for which no name is given. */
    public static final String DEFAULT_OPTION_NAME = "<Unnamed Option>";

    /** data type enumeration. */
    public static enum Type {
        /** undefined type. */
        UNDEFINED,
        /** boolean type. */
        BOOLEAN,
        /** integer type. */
        INT,
        /** string type. */
        STRING,
        /** float type. */
        FLOAT,
        /** enumeration type. */
        ENUM,
        /** {@link IDataObject} type. */
        OBJECT,
        /** remote enumeration type. */
        REMOTE_ENUM;         
        
        /**
         * Returns a user-friendly literal for the enumeration value.
         * 
         * @return a literal
         */
        public String literal() {
            switch (this) {
            case BOOLEAN:
                return BOOLEAN_LITERAL;
            case INT:
                return INT_LITERAL;
            case STRING:
                return STRING_LITERAL;
            case FLOAT:
                return FLOAT_LITERAL;
            case ENUM:
                return ENUM_LITERAL;
            case OBJECT:
                return OBJECT_LITERAL;
            case REMOTE_ENUM:
                return REMOTEENUM_LITERAL;
            default:
                return toString();
            }
        }
    }

    /** literal value constant for diagram target. */
    public static final String PARENTS_LITERAL = "parents";
    /** literal value constant for nodes target. */
    public static final String NODES_LITERAL = "nodes";
    /** literal value constant for edges target. */
    public static final String EDGES_LITERAL = "edges";
    /** literal value constant for ports target. */
    public static final String PORTS_LITERAL = "ports";
    /** literal value constant for labels target. */
    public static final String LABELS_LITERAL = "labels";

    /** option target enumeration. */
    public static enum Target {
        /** parents target (hierarchical nodes). */
        PARENTS,
        /** nodes target. */
        NODES,
        /** edges target. */
        EDGES,
        /** ports target. */
        PORTS,
        /** labels target. */
        LABELS;
    }
    
    /** probability distribution types for option values. */
    public static enum Distribution {
        /** uniform distribution. */
        UNIFORM,
        /** Gaussian distribution. */
        GAUSSIAN;
    }

    /** identifier of the layout option. */
    private String id = "";
    /** the default value of this option. */
    private T defaultValue;
    /** type of the layout option. */
    private Type type = Type.UNDEFINED;
    /** user friendly name of the layout option. */
    private String name = "";
    /** a description to be displayed in the UI. */
    private String description = "";
    /** configured targets. */
    private Set<Target> targets = Collections.emptySet();
    /** the class that represents this option type. */
    private Class<?> clazz;
    /** cached value of the available choices. */
    private String[] choices;
    /** whether the option should be shown in advanced mode only. */
    private boolean advanced;
    /** the lower bound for option values. */
    private T lowerBound;
    /** the upper bound for option values. */
    private T upperBound;
    /** the probability distibution for option values. */
    private Distribution distribution;
    /** the variance for option values. */
    private float variance;
    
    /**
     * Checks whether the enumeration class is set correctly. This method must
     * not be called for options other than of type 'enum'.
     */
    private void checkEnumClass() {
        if (clazz == null || !clazz.isEnum()) {
            throw new IllegalStateException("Enumeration class expected for layout option " + id);
        }
    }

    /**
     * Checks whether the remote enumeration options have been correctly initialized.
     */
    private void checkRemoteEnumoptions() {
        if (choices == null || choices.length == 0) {
            throw new IllegalStateException(
                "Remote enumeration values have not been initialized correctly"
                + " for layout option with id " + id
            );
        }
    }

    /**
     * Checks whether the {@link IDataType} class is set correctly and creates an instance.
     * This method must not be called for options other than of type 'object'.
     * 
     * @return an instance of the data object
     */
    private IDataObject createDataInstance() {
        if (!IDataObject.class.isAssignableFrom(clazz)) {
            throw new IllegalStateException("IDataType class expected for layout option " + id);
        }
        try {
            return (IDataObject) clazz.newInstance();
        } catch (InstantiationException exception) {
            throw new IllegalStateException("The data object for layout option " + id
                    + " cannot be instantiated.", exception);
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException("The data object for layout option " + id
                    + " cannot be accessed.", exception);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        if (obj instanceof LayoutOptionData<?>) {
            return this.id.equals(((LayoutOptionData<?>) obj).id);
        } else if (obj instanceof IProperty<?>) {
            return this.id.equals(((IProperty<?>) obj).getId());
        } else {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(final IProperty<?> other) {
        Object otherId = other.getId();
        if (otherId instanceof String) {
            return id.compareTo((String) otherId);
        } else {
            throw new UnsupportedOperationException("Not comparable with given property.");
        }
    }

     /**
      * {@inheritDoc}
      */
     @Override
    public String toString() {
        if (name != null && name.length() > 0) {
            return name;
        } else {
            return id;
        }
    }

    /**
     * Sets the type field depending on the given literal.
     * 
     * @param typeLiteral a string value that is expected to be equal to one of
     *            the predefined literal value constants
     */
    public void setType(final String typeLiteral) {
        if (BOOLEAN_LITERAL.equalsIgnoreCase(typeLiteral)) {
            type = Type.BOOLEAN;
        } else if (INT_LITERAL.equalsIgnoreCase(typeLiteral)) {
            type = Type.INT;
        } else if (STRING_LITERAL.equalsIgnoreCase(typeLiteral)) {
            type = Type.STRING;
        } else if (FLOAT_LITERAL.equalsIgnoreCase(typeLiteral)) {
            type = Type.FLOAT;
        } else if (ENUM_LITERAL.equalsIgnoreCase(typeLiteral)) {
            type = Type.ENUM;
        } else if (OBJECT_LITERAL.equalsIgnoreCase(typeLiteral)) {
            type = Type.OBJECT;
        } else if (REMOTEENUM_LITERAL.equalsIgnoreCase(typeLiteral)) {
            type = Type.REMOTE_ENUM;
        } else {
            throw new IllegalArgumentException("The given type literal is invalid.");
        }
    }

    /**
     * Parses a string value for this layout option.
     * 
     * @param valueString a serialized value
     * @return an instance of the corresponding correctly typed value, or
     *         {@code null} if the given value string is invalid
     */
    @SuppressWarnings("unchecked")
    public T parseValue(final String valueString) {
        if (valueString == null || valueString.length() == 0 || valueString.equals("null")) {
            return null;
        }
        switch (type) {
        case BOOLEAN:
            return (T) Boolean.valueOf(valueString);
        case INT:
            try {
                return (T) Integer.valueOf(valueString);
            } catch (NumberFormatException exception) {
                return null;
            }
        case STRING:
            return (T) valueString;
        case FLOAT:
            try {
                return (T) Float.valueOf(valueString);
            } catch (NumberFormatException exception) {
                return null;
            }
        case ENUM:
            try {
                checkEnumClass();
                @SuppressWarnings("rawtypes")
                Enum<?> value = Enum.valueOf((Class<? extends Enum>) clazz, valueString);
                return (T) value;
            } catch (IllegalArgumentException exception) {
                // the value could not be parsed as enumeration constant, try as integer
                try {
                    int index = Integer.parseInt(valueString);
                    Object[] constants = clazz.getEnumConstants();
                    if (index >= 0 && index < constants.length) {
                        return (T) constants[index];
                    }
                } catch (NumberFormatException e) {
                    // ignore exception and return null
                }
                return null;
            }
        case OBJECT:
            try {
                IDataObject value = createDataInstance();
                value.parse(valueString);
                return (T) value;
            } catch (IllegalArgumentException exception) {
                return null;
            }
        case REMOTE_ENUM:
            checkRemoteEnumoptions();
            for (int i = 0; i < choices.length; i++) {
                if (choices[i].equals(valueString)) {
                    return (T) valueString;
                }
            }
            return null;
        default:
            throw new IllegalStateException("Invalid type set for this layout option.");
        }
    }

    /**
     * Parses the possible values for a remote enumeration from a space separated string.
     * 
     * @param valueString
     *            the space separated string containing the possible values
     */
    public void parseRemoteEnumValues(final String valueString) {
        Vector<String> tmp = new Vector<String>();
        StringTokenizer tokenizer = new StringTokenizer(valueString, " ");
        while (tokenizer.hasMoreTokens()) {
            tmp.add(tokenizer.nextToken());
        }      
        choices = tmp.toArray(new String[0]);
    }
    
    /**
     * Creates a default-default value for this layout option. In contrast to {@link #getDefault()},
     * this never returns {@code null} for options with type other than 'object'.
     * 
     * @return a default-default value, depending on the option type
     */
    @SuppressWarnings("unchecked")
    public T getDefaultDefault() {
        switch (type) {
        case STRING:
            return (T) "";
        case BOOLEAN:
            return (T) Boolean.FALSE;
        case INT:
            return (T) Integer.valueOf(0);
        case FLOAT:
            return (T) Float.valueOf(0.0f);
        case ENUM:
            checkEnumClass();
            @SuppressWarnings({ "rawtypes" })
            Enum<?>[] enums = ((Class<Enum>) clazz).getEnumConstants();
            return (T) enums[0];
        case OBJECT:
            return null;
        case REMOTE_ENUM:
             checkRemoteEnumoptions();
             return (T) choices[0];
        default:
            throw new IllegalStateException("Invalid type set for this layout option.");
        }
    }
    
    /** choices for boolean type options. */
    public static final String[] BOOLEAN_CHOICES = { "false", "true" };

    /**
     * Creates an array of choices that can be selected by the user to set a
     * value for this option. This makes only sense for enumeration type
     * or boolean type options.
     * 
     * @return an array of values to be displayed for the user
     */
    public String[] getChoices() {
        if (choices == null) {
            switch (type) {
            case ENUM:
                checkEnumClass();
                @SuppressWarnings({ "unchecked", "rawtypes" })
                Enum<?>[] enums = ((Class<Enum>) clazz).getEnumConstants();
                choices = new String[enums.length];
                for (int i = 0; i < enums.length; i++) {
                    choices[i] = enums[i].toString();
                }
                break;
            case BOOLEAN:
                choices = BOOLEAN_CHOICES;
                break;
            case REMOTE_ENUM:
                checkRemoteEnumoptions();
                return choices;
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
    public Enum<?> getEnumValue(final int intValue) {
        switch (type) {
        case ENUM:
            checkEnumClass();
            @SuppressWarnings({ "unchecked", "rawtypes" })
            Enum<?>[] enums = ((Class<Enum>) clazz).getEnumConstants();
            return enums[intValue]; 
        default:
            return null;
        }
    }

    /**
     * Sets the targets property of this layout option data.
     * 
     * @param targetsString comma separated list of targets
     */
    public void setTargets(final String targetsString) {
        targets = EnumSet.noneOf(Target.class);
        if (targetsString != null) {
            StringTokenizer tokenizer = new StringTokenizer(targetsString, ", \t");
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (token.equalsIgnoreCase(PARENTS_LITERAL)) {
                    targets.add(Target.PARENTS);
                } else if (token.equalsIgnoreCase(NODES_LITERAL)) {
                    targets.add(Target.NODES);
                } else if (token.equalsIgnoreCase(EDGES_LITERAL)) {
                    targets.add(Target.EDGES);
                } else if (token.equalsIgnoreCase(PORTS_LITERAL)) {
                    targets.add(Target.PORTS);
                } else if (token.equalsIgnoreCase(LABELS_LITERAL)) {
                    targets.add(Target.LABELS);
                }
            }
        }
    }

    /**
     * Checks whether the given target is active for this layout option.
     * 
     * @param target a layout option target
     * @return true if the target is active
     */
    public boolean hasTarget(final Target target) {
        return targets.contains(target);
    }

    /**
     * Returns a user friendly description of the active targets of this layout
     * option.
     * 
     * @return a description of the active targets, or {@code null} if there are
     *         no active targets
     */
    public String getTargetsDescription() {
        StringBuilder descriptionBuf = new StringBuilder();
        int count = targets.size(), index = 0;
        for (Target target : targets) {
            switch (target) {
            case PARENTS:
                descriptionBuf.append("Parents");
                break;
            case NODES:
                descriptionBuf.append("Nodes");
                break;
            case EDGES:
                descriptionBuf.append("Edges");
                break;
            case PORTS:
                descriptionBuf.append("Ports");
                break;
            case LABELS:
                descriptionBuf.append("Labels");
                break;
            }
            index++;
            if (count - index >= 2) {
                descriptionBuf.append(", ");
            } else if (count - index == 1) {
                descriptionBuf.append(" and ");
            }
        }
        return descriptionBuf.toString();
    }

    /**
     * Sets the identifier.
     *
     * @param theid the identifier to set
     */
    public void setId(final String theid) {
        assert theid != null;
        this.id = theid;
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the data type. If the option class can be derived from the class, it is also set.
     *
     * @param thetype the data type to set
     */
    public void setType(final Type thetype) {
        this.type = thetype;
        switch (thetype) {
        case STRING:
            clazz = String.class;
            break;
        case BOOLEAN:
            clazz = Boolean.class;
            break;
        case INT:
            clazz = Integer.class;
            break;
        case FLOAT:
            clazz = Float.class;
            break;
        }
    }

    /**
     * Returns the type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the name.
     *
     * @param thename the name to set
     */
    public void setName(final String thename) {
        if (thename == null || thename.length() == 0) {
            this.name = DEFAULT_OPTION_NAME;
        } else {
            this.name = thename;
        }
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the description.
     *
     * @param thedescription the description to set
     */
    public void setDescription(final String thedescription) {
        if (thedescription == null) {
            this.description = "";
        } else {
            this.description = thedescription;
        }
    }

    /**
     * Returns the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    public T getDefault() {
        return defaultValue;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Comparable<T> getLowerBound() {
        if (lowerBound instanceof Comparable<?>) {
            return (Comparable<T>) lowerBound;
        }
        return (Comparable<T>) Property.NEGATIVE_INFINITY;
    }

    /**
     * Sets the lower bound for layout option values.
     * 
     * @param lowerBound the lowerBound to set
     */
    public void setLowerBound(final T lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Comparable<T> getUpperBound() {
        if (upperBound instanceof Comparable<?>) {
            return (Comparable<T>) upperBound;
        }
        return (Comparable<T>) Property.POSITIVE_INFINITY;
    }

    /**
     * Sets the upper bound for layout option values.
     * 
     * @param upperBound the upperBound to set
     */
    public void setUpperBound(final T upperBound) {
        this.upperBound = upperBound;
    }
    
    /**
     * Sets the default value.
     * 
     * @param thedefaultValue the default value
     */
    public void setDefault(final T thedefaultValue) {
        this.defaultValue = thedefaultValue;
    }

    /**
     * Returns the option type class.
     * 
     * @return the type class
     */
    public Class<?> getOptionClass() {
        return clazz;
    }

    /**
     * Sets the option type class.
     * 
     * @param theclazz the class to set
     */
    public void setOptionClass(final Class<?> theclazz) {
        this.clazz = theclazz;
    }

    /**
     * Whether the option should be shown in advanced mode only.
     * 
     * @return true if the option is advanced
     */
    public boolean isAdvanced() {
        return advanced;
    }

    /**
     * Sets the advanced property of the layout option.
     * 
     * @param theadvanced true if the option is advanced
     */
    public void setAdvanced(final boolean theadvanced) {
        this.advanced = theadvanced;
    }

    /**
     * Returns the probability distribution for layout option values.
     * 
     * @return the distribution
     */
    public Distribution getDistribution() {
        return distribution;
    }

    /**
     * Sets the probability distribution for layout option values.
     * 
     * @param distribution the distribution to set
     */
    public void setDistribution(final Distribution distribution) {
        this.distribution = distribution;
    }

    /**
     * Returns the variance for layout option values.
     * 
     * @return the variance
     */
    public float getVariance() {
        return variance;
    }

    /**
     * Sets the variance for layout option values.
     * 
     * @param variance the variance to set
     */
    public void setVariance(final float variance) {
        this.variance = variance;
    }

}
