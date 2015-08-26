/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.nonosgi.service;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;

/**
 * Implementation of {@link AbstractExtensionLayoutMetaDataService} that basically only contributes
 * a {@link #reportError(String, IConfigurationElement, String, Throwable)} method. As opposed to
 * other implementation it does neither require eclipse.ui oder logging specific functionality. It
 * just writes errors to stderr.
 * 
 * @author uru
 */
public class NonOsgiExtensionLayoutMetaDataService extends AbstractExtensionLayoutMetaDataService {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final String extensionPoint, final IConfigurationElement element,
            final String attribute, final Throwable exception) {
        System.err.println(
            "Error while parsing extension point:" 
            + "\nExtension point: " + (extensionPoint != null ? extensionPoint : "<unknown>") 
            + "\nElement: " + (element != null ? element.toString() : "<unknown>")
            + "\nAttribute: " + (attribute != null ? attribute : "<unknown>")
            + "\nException: " + (exception != null ? exception.getMessage() : "<unknown>")
            + "\n\n"
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final CoreException exception) {
        reportError(null, null, null, exception);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LayoutAlgorithmData createLayoutAlgorithmData(IConfigurationElement element) {
        return new LayoutAlgorithmData();
    }

}
