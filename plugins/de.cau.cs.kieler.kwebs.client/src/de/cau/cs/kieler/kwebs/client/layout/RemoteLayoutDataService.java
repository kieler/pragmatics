/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.layout;

import java.net.URL;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.service.ServiceDataLayoutDataService;
import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.activator.Activator;

/**
 * This class is designed for retrieving the layout capabilities of a
 * remote layout provider and make it available in the KIELER framework
 * so the user can annotate his model only with the server side available
 * options. At this moment the layout capabilities include available layout
 * algorithms and layout options only.
 *
 * @kieler.rating 2011-05-17 red
 * @author swe
 */
public final class RemoteLayoutDataService extends ServiceDataLayoutDataService {

    /**
     * This class needs to be instantiated through the {@link #create}
     * method.
     */
    private RemoteLayoutDataService() {
    }

    /**
     * Creates the singleton instance of this class. The layout capabilities
     * need to be retrieved afterwards through any client which implements the
     * {@link ILayoutServiceClient} interface
     * by calling {@link initializeWithClient} with the client as parameter.
     */
    public static synchronized void create() {
        RemoteLayoutDataService lds =
            LayoutDataService.getInstanceOf(
                LayoutDataService.REMOTEDATASERVICE
        );
        if (lds == null) {
            lds = new RemoteLayoutDataService();
            LayoutDataService.addService(lds);
        }
    }

    /**
     * 
     */
    public static synchronized void resetInstance() {
        RemoteLayoutDataService lds =
            LayoutDataService.getInstanceOf(
                LayoutDataService.REMOTEDATASERVICE
        );
        // Remember current mode
        String currentMode = LayoutDataService.getMode();
        if (lds != null) {          
            // Set the mode temporarily to local mode since the
            // RemoteLayoutDataService instance can only be removed
            // if it is not the currently active instance.
            LayoutDataService.setMode(ECLIPSEDATASERVICE);            
            LayoutDataService.removeService(lds);
        }
        // Create new RemoteLayoutDataService instance and register it
        create();
        // Switch back to original mode
        LayoutDataService.setMode(currentMode);
    }

    /**
     * Returns the singleton instance of this class.
     *
     * @return the singleton instance.
     */
    public static synchronized RemoteLayoutDataService getInstance() {
        return LayoutDataService.getInstanceOf(
            LayoutDataService.REMOTEDATASERVICE
        );
    }

    /**
     * Initializes the layout service meta data with the given web service client.
     *
     * @param client 
     *            the client.
     */
    public synchronized void initializeWithClient(final ILayoutServiceClient client) {
        if (!client.isAvailable()) {
            throw new ServiceUnavailableException(
                "The service on address "
                + client.getServerConfig().getAddress()
                + " is currently not reachable"
            );
        }
        try {
            String capabilities = client.getServiceData();
            super.initializeFromString(capabilities);
        } catch (Exception e) {
            StatusManager.getManager().handle(
                new Status(
                    IStatus.ERROR, 
                    Activator.PLUGIN_ID, 
                    "Server meta data could not be retrieved or correctly processed", 
                    null
                )
            );
            throw new IllegalStateException(
                "Server meta data could not be retrieved or correctly processed",
            e);
        }
    }

    /** name of the 'previewImage' attribute layout algorithms meta data. */
    public static final String ATTRIBUTE_PREVIEWIMAGE 
        = "previewImage";

    /**
     * {@inheritDoc}
     */
    @Override
    protected LayoutAlgorithmData createLayoutAlgorithmData(final IConfigurationElement element) {
        RemoteLayoutAlgorithmData algoData = new RemoteLayoutAlgorithmData();
        String previewImage = element.getAttribute(ATTRIBUTE_PREVIEWIMAGE);
        if (previewImage != null) {
            try {
                algoData.setPreviewImage(ImageDescriptor.createFromURL(new URL(previewImage)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return algoData;
    }

    /**
     * {@inheritDoc}
     */
    protected void reportError(final String message) {
        reportError(message, null);
    }

    /**
     * {@inheritDoc}
     */
    protected void reportError(final String message, final Throwable throwable) {
        StatusManager.getManager().handle(
            new Status(
                IStatus.ERROR, 
                Activator.PLUGIN_ID, 
                message,
                throwable
            )
        );
    }

}
