/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * String externalization class for the KIML UI plugin.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public final class Messages {

    /** the bundle name. */
    private static final String BUNDLE_NAME = "de.cau.cs.kieler.kiml.ui.messages"; //$NON-NLS-1$
    /** the resource bundle instance. */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Hidden constructor.
     */
    private Messages() {
    }
    
    /**
     * Returns the string associated with the given key.
     * 
     * @param key key to look up in the {@code messages.properties} file
     * @return the associated string
     */
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
