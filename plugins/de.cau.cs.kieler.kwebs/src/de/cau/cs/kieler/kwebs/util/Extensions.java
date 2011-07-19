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

package de.cau.cs.kieler.kwebs.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * Utility class for extension registry access.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class Extensions {

    /**
     * Private constructor, no need to instantiate utility class.
     */
    private Extensions() {
    }

    /**
     * Returns the value of the attribute of the first occurrence of the element in the
     * extension registry from the first extension with the given id.
     * 
     * @param id
     *            extension point id
     * @param element
     *            element name
     * @param attribute
     *            attribute name
     * @return the value or {@code null}
     */
    public static String get(final String id, final String element, final String attribute) {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        for (IConfigurationElement e : registry.getConfigurationElementsFor(id)) {
            if (e.getName().equals(element)) {
                return e.getAttribute(attribute);
            }
        }
        return null;
    }

}
