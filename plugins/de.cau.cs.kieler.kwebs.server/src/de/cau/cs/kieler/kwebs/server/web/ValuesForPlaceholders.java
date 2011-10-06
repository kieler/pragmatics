/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.web;

import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.kwebs.server.configuration.Configuration;

/**
 * This class provides the translation of place holders used in static web content to runtime
 * dependent values.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class ValuesForPlaceholders {

    /**
     * Replaces all occurrences of known place holders in the given content.
     * 
     * @param content
     *            the content to replace place holders in
     *       
     * @return the content with all known place holders replaced by its runtime dynamic values
     */
    public static String replacePlaceholders(final String content) {
        StringBuffer replaced = new StringBuffer();
        int l = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '^') {
                replaced.append(content.substring(l, i));
                for (int j = i + 1; j < content.length(); j++) {
                    if (content.charAt(j) == '^') {
                        String placeholder = content.substring(i + 1, j);
                        String value = ValuesForPlaceholders.translate(placeholder);
                        if (value != null) {
                            replaced.append(value);
                        }
                        i = j + 1;
                        l = j + 1;
                    }
                }
            }
        }
        if (l < content.length()) {
            replaced.append(content.substring(l, content.length() - 1));
        }
        return replaced.toString();
    }

    /**
     * Translates a place holder into its runtime dependent value.
     * 
     * @param placeholder
     *            the place holder
     *            
     * @return the runtime value of the placeholder
     */
    public static String translate(final String placeholder) {
        if (placeholder == null) {
            return null;
        }
        return getValuesForPlaceholders().get(placeholder);
    }

    /** The mapping of place holders to runtime dependent values. */
    private static Map<String, String> valuesForPlaceholders;

    /**
     * Builds the mapping of place holders to runtime dependent values.
     * 
     * @return the mapping
     */
    private static Map<String, String> getValuesForPlaceholders() {
        if (valuesForPlaceholders == null) {
            valuesForPlaceholders = new HashMap<String, String>();
            Configuration config = Configuration.getInstance();
            valuesForPlaceholders.put(
                "JaxWsHttpServiceAddress", 
                config.getConfigProperty(Configuration.JAXWS_HTTP_ADDRESS)
            );
            valuesForPlaceholders.put(
                "JaxWsHttpsServiceAddress", 
                config.getConfigProperty(Configuration.JAXWS_HTTPS_ADDRESS)
            );
            valuesForPlaceholders.put(
                "JetiServiceAddress", 
                "eti://" + config.getConfigProperty(Configuration.JETI_SERVERHOSTNAME)
                + ":" + config.getConfigProperty(Configuration.JETI_CONNECTORSEPPPORT)
            );
            valuesForPlaceholders.put(
                "HttpServiceDescription", 
                config.getConfigProperty(Configuration.JAXWS_HTTP_ADDRESS) + "?wsdl"
            );
            valuesForPlaceholders.put(
                "HttpsServiceDescription", 
                config.getConfigProperty(Configuration.JAXWS_HTTPS_ADDRESS) + "?wsdl"
            );            
        }
        return valuesForPlaceholders;
    }

    /**
     * Private constructor for utility class.
     */
    private ValuesForPlaceholders() {        
    }
    
}
