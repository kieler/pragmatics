/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.service;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.service.AnalysisService;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * Analysis service class for use in Eclipse.
 *
 * @author msp
 * @kieler.rating 2012-07-10 proposed yellow msp
 */
public final class EclipseAnalysisService extends AnalysisService {
    
    /**
     * Hidden constructor to avoid instantiation from outside this class.
     */
    private EclipseAnalysisService() {
        super();
    }
    
    /**
     * Create the analysis service and load extension points.
     */
    public static synchronized void create() {
        // creating an instance stores this instance as the singleton instance
        EclipseAnalysisService instance = new EclipseAnalysisService();
        instance.loadAnalysisProviderExtension();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute, final Throwable exception) {
        String message;
        if (element != null && attribute != null) {
            message = "Extension point " + extensionPoint + ": Invalid entry in attribute '"
                    + attribute + "' of element " + element.getName() + ", contributed by "
                    + element.getContributor().getName();
        } else {
            message = "Extension point " + extensionPoint
                    + ": An error occured while loading extensions.";
        }
        IStatus status = new Status(IStatus.WARNING, KimlUiPlugin.PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final CoreException exception) {
        StatusManager.getManager().handle(exception, KimlUiPlugin.PLUGIN_ID);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportWarning(final String message) {
        IStatus status = new Status(IStatus.WARNING, KimlUiPlugin.PLUGIN_ID, 0, message, null);
        StatusManager.getManager().handle(status);
    }
    
}
