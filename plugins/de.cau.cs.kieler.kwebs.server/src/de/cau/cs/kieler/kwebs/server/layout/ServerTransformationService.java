/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.layout;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import de.cau.cs.kieler.kiml.service.TransformationService;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;

/**
 * Graph format transformation service for the layout server.
 *
 * @author msp
 */
public final class ServerTransformationService extends TransformationService {

    /**
     * Hidden constructor to avoid instantiation from outside this class.
     */
    private ServerTransformationService() {
        super();
    }
    
    /**
     * Initialize the singleton instance from the extension points.
     */
    public static void create() {
        ServerTransformationService instance = new ServerTransformationService();
        instance.loadGraphTransExtensions();
    }

    /**
     * {@inheritDoc}
     */
    protected void reportError(final String extensionPoint,
        final IConfigurationElement element, final String attribute,
        final Throwable exception) {
        Logger.log(
            Severity.FAILURE, 
            "Error while parsing extension point", 
            "\n\nExtension point: " + (extensionPoint != null ? extensionPoint : "<unknown>") 
            + "\n\nElement: " + (element != null ? element.toString() : "<unknown>")
            + "\n\nAttribute: " + (attribute != null ? attribute : "<unknown>")
            + "\n\nException: " + (exception != null ? exception.getMessage() : "<unknown>")
        );
    }

    /**
     * {@inheritDoc}
     */
    protected void reportError(final CoreException exception) {
        reportError(null, null, null, exception);
    }

}
