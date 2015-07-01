/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.layout;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;

/**
 * Graph format transformation service for the layout server.
 *
 * @author msp
 */
public final class ServerGraphFormatsService extends GraphFormatsService {

    /**
     * Create the server transformation service.
     */
    public ServerGraphFormatsService() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
    @Override
    protected void reportError(final CoreException exception) {
        reportError(null, null, null, exception);
    }

}
