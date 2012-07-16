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
package de.cau.cs.kieler.kex.ui.wizards.exporting;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

/**
 * Bundle of Messages for the import of kex.ui plug-in.
 * 
 * @author pkl
 * 
 */
public final class Messages extends NLS {
    /** the bundle name. **/
    private static final String BUNDLE_NAME 
        = "de.cau.cs.kieler.kex.ui.wizards.exporting.messages"; //$NON-NLS-1$

    /** the resource bundle instance. */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Hidden constructor.
     */
    private Messages() {
    }

    /**
     * Returns the String associated with the given key.
     * 
     * @param key
     *            key to look up in the {@code messages.properties} file
     * @return the associated string
     */
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    /**
     * Returns the String associated with the given key.
     * 
     * @param key
     *            key to look up in the {@code messages.properties} file
     * @param params
     *            parameters for the text
     * @return the associated string
     */
    public static String getString(final String key, final Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

}
