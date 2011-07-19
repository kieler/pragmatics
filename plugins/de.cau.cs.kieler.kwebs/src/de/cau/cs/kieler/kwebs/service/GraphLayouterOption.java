/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.service;

import java.util.StringTokenizer;
import java.util.Vector;

import de.cau.cs.kieler.core.properties.IProperty;

/**
 * Utility class for transfering layout options to the layout server.
 *
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
public class GraphLayouterOption {

    /** Identifier of the layout option. */
    private String id;

    /** Value of the layout option. */
    private String value;

    /**
     * Public default constructor needed for binding.
     */
    public GraphLayouterOption() {
    }

    /**
     * This constructor initiates from a {@link IProperty} and
     * its associated value. The identifier of this {@link GraphLayouterOption}
     * is derived from the property by {@link getId} and the value by calling
     * {@code thevalue.toString()}.
     *
     * @param theproperty
     *            the property
     * @param thevalue
     *            the value
     */
    public GraphLayouterOption(final IProperty<?> theproperty, final Object thevalue) {
        this(theproperty.getId(), thevalue.toString());
    }
    
    /**
     * .
     *
     * @param theid
     *            the id
     * @param thevalue
     *            the value
     */
    public GraphLayouterOption(final String theid, final String thevalue) {
        id = theid;
        value = thevalue;
    }

    /**
     * Returns the id of this option.
     *
     * @return String
     *             the id
     */
    public final String getId() {
        return id;
    }

    /**
     * Sets the id of this option.
     *
     * @param theid the new id
     */
    public final void setId(final String theid) {
        id = theid;
    }

    /**
     * Returns the value of this option.
     *
     * @return String
     *             the value
     */
    public final String getValue() {
        return value;
    }

    /**
     * Sets the value of this option.
     * @param thevalue
     *            the new value
     */
    public final void setValue(final String thevalue) {
        value = thevalue;
    }

    /**
     * 
     * @param serialOption
     *            the serialized option
     * @return the option
     */
    public static GraphLayouterOption fromString(final String serialOption) {
        GraphLayouterOption option = null;
        if (serialOption != null && serialOption.contains("=")) {
            String id = serialOption.substring(
                0, serialOption.indexOf("=")
            );
            String value = serialOption.substring(
                serialOption.indexOf("=")
            );
            option = new GraphLayouterOption(id, value);
        }
        return option;
    }
    
    /**
     * Returns a string representation of this option.
     * 
     * @return a string representation of this option
     */
    @Override
    public String toString() {
        return id + "=" + value;
    }
    
    /**
     * Returns a string representation of an array of options.
     * 
     * @param options an array of options
     * @return string representation of the array
     */
    public static String arrayToString(final GraphLayouterOption[] options) {
       String result = "";
       if (options != null && options.length > 0) {
           for (GraphLayouterOption option : options) {
               if (result.length() > 0) {
                   result += DELIMITER;
               }
               result += escape(option.toString());
           }
       }
       return result;
    }
    
    /**
     * Returns an array of options which is created from its string representation.
     * 
     * @param options string containing serialized array of options
     * @return an array of options
     */
    public static GraphLayouterOption[] stringToArray(final String options) {
        GraphLayouterOption[] result = new GraphLayouterOption[0];
        if (options != null) {
            Vector<GraphLayouterOption> vectoredOptions = new Vector<GraphLayouterOption>();
            StringTokenizer tokenizer = new StringTokenizer(options, DELIMITER);
            String token;
            GraphLayouterOption option;
            while (tokenizer.hasMoreTokens()) {
                token = unescape(tokenizer.nextToken());
                option = fromString(token);
                if (option != null) {
                    vectoredOptions.add(option);
                }
            }
            result = vectoredOptions.toArray(result);
        }
        return result;
    }

    /** The delimiter used when de-/serializing a list of {@code GraphLayouteroption}. */
    private static final String DELIMITER
        = "|";

    /** The escaped delimiter if it occurrs in the value of a {@code GraphLayouteroption}. */
    private static final String ESCAPED
        = "\\x7c";
    
    /**
     * Escapes occurrences of the delimiter in a serialized option.
     *
     * @param serializedOption the unescaped serialized option
     * @return escaped serialized option
     */
    public static String escape(final String serializedOption) {
        String result = "";
        if (serializedOption != null) {
            result = serializedOption.replace(DELIMITER, ESCAPED);
        }
        return result;
    }

    /**
     * Unescapes occurrences of the delimiter in a serialized option.
     *
     * @param serializedOption the escaped serialized option
     * @return unescaped serialized option
     */
    public static String unescape(final String serializedOption) {
        String result = "";
        if (serializedOption != null) {
            result = serializedOption.replace(ESCAPED, DELIMITER);
        }
        return result;
    }
    
}
