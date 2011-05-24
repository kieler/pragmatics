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
package de.cau.cs.kieler.klighd;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * Singleton for accessing the light diagram services.
 * 
 * @author mri
 */
public final class LightDiagramServices {

    /** identifier of the extension point for viewer providers. */
    public static final String EXTP_ID_VIEWER_PROVIDERS = "de.cau.cs.kieler.klighd.viewerProviders";
    /** name of the 'viewer' element. */
    public static final String ELEMENT_VIEWER = "viewer";
    /** name of the 'id' attribute in the extension points. */
    public static final String ATTRIBUTE_ID = "id";
    /** name of the 'class' attribute in the extension points. */
    public static final String ATTRIBUTE_CLASS = "class";

    /** the singleton instance. */
    private static LightDiagramServices instance;
    /** a mapping between viewer provider id's and the instances. */
    private Map<String, IViewerProvider> idViewerProviderMapping =
            new LinkedHashMap<String, IViewerProvider>();

    /**
     * A private constructor to prevent instantiation.
     */
    private LightDiagramServices() {
        // do nothing
    }

    /**
     * Creates the singleton and initializes it with the data from the extension point.
     */
    static {
        instance = new LightDiagramServices();
        // load the data from the extension point
        instance.loadViewerProviderExtension();
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton
     */
    public static LightDiagramServices getInstance() {
        return instance;
    }

    /**
     * Reports an error that occurred while reading extensions.
     * 
     * @param extensionPoint
     *            the identifier of the extension point
     * @param element
     *            the configuration element
     * @param attribute
     *            the attribute that contains an invalid entry
     * @param exception
     *            an optional exception that was caused by the invalid entry
     */
    private static void reportError(final String extensionPoint,
            final IConfigurationElement element, final String attribute, final Exception exception) {
        String message =
                "Extension point " + extensionPoint + ": Invalid entry in attribute '" + attribute
                        + "' of element " + element.getName() + ", contributed by "
                        + element.getContributor().getName();
        IStatus status = new Status(IStatus.WARNING, KLighDPlugin.PLUGIN_ID, 0, message, exception);
        StatusManager.getManager().handle(status);
    }

    /**
     * Loads and registers all viewer provider from the extension point.
     */
    private void loadViewerProviderExtension() {
        IConfigurationElement[] extensions =
                Platform.getExtensionRegistry().getConfigurationElementsFor(
                        EXTP_ID_VIEWER_PROVIDERS);
        for (IConfigurationElement element : extensions) {
            if (ELEMENT_VIEWER.equals(element.getName())) {
                try {
                    // initialize viewer provider from the extension point
                    IViewerProvider viewerProvider =
                            (IViewerProvider) element.createExecutableExtension(ATTRIBUTE_CLASS);
                    if (viewerProvider != null) {
                        String id = element.getAttribute(ATTRIBUTE_ID);
                        if (id == null || id.length() == 0) {
                            reportError(EXTP_ID_VIEWER_PROVIDERS, element, ATTRIBUTE_ID, null);
                        } else {
                            idViewerProviderMapping.put(id, viewerProvider);
                        }
                    }
                } catch (CoreException exception) {
                    StatusManager.getManager().handle(exception, KLighDPlugin.PLUGIN_ID);
                }
            }
        }
    }

    /**
     * Returns a viewer provider which is supporting the given model.
     * 
     * @param model
     *            the model
     * @return the viewer provider or null if no viewer provider could be found
     */
    public IViewerProvider getViewerProviderForModel(final Object model) {
        for (IViewerProvider viewerProvider : idViewerProviderMapping.values()) {
            if (viewerProvider.isModelSupported(model)) {
                return viewerProvider;
            }
        }
        return null;
    }

}
