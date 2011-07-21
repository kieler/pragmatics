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

import java.io.StringReader;

import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.service.XMLLayoutDataService;
import de.cau.cs.kieler.kwebs.client.IWebServiceClient;
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;

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
public final class RemoteLayoutDataService extends XMLLayoutDataService {

    /**
     * This class needs to be instantiated through the {@link #create}
     * method.
     */
    private RemoteLayoutDataService() {
    }

    /**
     * Creates the singleton instance of this class. The layout capabilities
     * need to be retrieved afterwards through any client which implements the
     * {@link IKimlWebServiceClient} interface
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
        if (lds != null) {
            LayoutDataService.removeService(lds);
        }
        create();
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
    public synchronized void initializeWithClient(final IWebServiceClient client) {
        if (!client.isAvailable()) {
            throw new ServiceUnavailableException(
                "The service on address "
                + client.getProvider().getAddress()
                + " is currently not reachable"
            );
        }
        try {
            String capabilities = client.getCapabilities();
            Logger.log(Severity.INFO, "Received server meta data", capabilities);
            super.initializeFromReader(new StringReader(capabilities));
        } catch (Exception e) {
            Logger.log(Severity.FAILURE,
                "Server meta data could not be retrieved or correctly processed",
            e);
            throw new IllegalStateException(
                "Server meta data could not be retrieved or correctly processed",
            e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final String message) {
        Logger.log(Severity.FAILURE, "Error while parsing meta data", message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportError(final String message, final Exception exception) {
        Logger.log(Severity.FAILURE, "Exception while parsing meta data", message, exception);
    }

}
