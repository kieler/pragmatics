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

package de.cau.cs.kieler.kwebs;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Utility class for transferring layout options to the layout server.
 *
 * @kieler.rating 2011-08-02 yellow
 *     reviewed by ckru, mri, msp
 *     
 * @author swe
 */
public class GraphLayoutOption {

    /** Identifier of the layout option. */
    private String id;

    /** Value of the layout option. */
    private String value;

    /**
     * Public default constructor needed for binding.
     */
    public GraphLayoutOption() {
    }

    /**
     * This constructor initiates from a {@link IProperty} and
     * its associated value. The identifier of this {@link GraphLayoutOption}
     * is derived from the property by {@link getId} and the value by calling
     * {@code thevalue.toString()}.
     *
     * @param theproperty
     *            the property
     * @param thevalue
     *            the value
     *//*
    public GraphLayoutOption(final IProperty<?> theproperty, final Object thevalue) {
        this(theproperty.getId(), thevalue.toString());
    }*/
    
    /**
     * Constructs a new instance with the given id and value.
     *
     * @param theid
     *            the id
     * @param thevalue
     *            the value
     */
    public GraphLayoutOption(final String theid, final String thevalue) {
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
     * Parses a serialized option.
     * 
     * @param serialOption
     *            the serialized option
     * @return the option or {@code null} if a parse error occurred
     */
    public static GraphLayoutOption fromString(final String serialOption) {
        GraphLayoutOption option = null;
        if (serialOption != null) {
            try {
                int index = serialOption.indexOf("=");
                if (index > 0 && index < serialOption.length() - 1) {
                    String id = serialOption.substring(0, index);
                    String value = serialOption.substring(index + 1);
                    option = new GraphLayoutOption(id, value);
                }
            } catch (Exception e) {
                // Exception while parsing only occurs on invalid serial data
            }
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
     * Returns a string representation of a list of options.
     * 
     * @param options 
     *            a list of options
     * @return string representation of the list 
     */    
    public static String listToString(final List<GraphLayoutOption> options) {
        String result = "";
        if (options != null) {
            result = arrayToString(options.toArray(new GraphLayoutOption[0]));
        }
        return result;
    }
    
    /**
     * Returns a string representation of an array of options.
     * 
     * @param options 
     *            an array of options
     * @return string representation of the array
     */
    public static String arrayToString(final GraphLayoutOption[] options) {
       String result = "";
       if (options != null && options.length > 0) {
           for (GraphLayoutOption option : options) {
               if (result.length() > 0) {
                   result += DELIMITER;
               }
               result += escape(option.toString());
           }
       }
       return result;
    }

    /**
     * Returns a List of options which is created from its string representation.
     * 
     * @param options 
     *            string containing serialized array of options
     * @return a list of options
     */
    public static List<GraphLayoutOption> stringToList(final String options) {
        LinkedList<GraphLayoutOption> result = new LinkedList<GraphLayoutOption>();
        GraphLayoutOption[] array = stringToArray(options);
        for (GraphLayoutOption option : array) {
            result.add(option);
        }
        return result;
    }
    
    /**
     * Returns an array of options which is created from its string representation.
     * 
     * @param options 
     *            string containing serialized array of options
     * @return an array of options
     */
    public static GraphLayoutOption[] stringToArray(final String options) {
        GraphLayoutOption[] result = new GraphLayoutOption[0];
        if (options != null) {
            Vector<GraphLayoutOption> vectoredOptions = new Vector<GraphLayoutOption>();
            StringTokenizer tokenizer = new StringTokenizer(options, DELIMITER);
            String token;
            GraphLayoutOption option;
            while (tokenizer.hasMoreTokens()) {
                token = unescape(tokenizer.nextToken());
                option = fromString(token);
                if (option != null) {
                    vectoredOptions.add(option);
                }
            }
            result = vectoredOptions.toArray(new GraphLayoutOption[0]);
        }
        return result;
    }

    /** The delimiter used when serializing or deserializing a list of {@code GraphLayouterOption}. */
    private static final String DELIMITER
        = "|";

    /** The escaped delimiter if it occurs in the value of a {@code GraphLayouterOption}. */
    private static final String ESCAPED
        = "\\x7c";
    
    /**
     * Escapes occurrences of the delimiter in a serialized option.
     *
     * @param serializedOption the unescaped serialized option
     * @return escaped serialized option
     */
    private static String escape(final String serializedOption) {
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
    private static String unescape(final String serializedOption) {
        String result = "";
        if (serializedOption != null) {
            result = serializedOption.replace(ESCAPED, DELIMITER);
        }
        return result;
    }
    
}
