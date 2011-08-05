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

package de.cau.cs.kieler.kwebs.server.publishing;

import de.cau.cs.kieler.kwebs.jaxws.LayoutServicePort;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.service.JaxWsService;

//FIXME If service is published via HTTP AND HTTPS, both
//      servers are created with the full pool size. They should share.

/**
 * The {@code ServicePublisher} is the central point for managing the
 * publishing of the layout service. It is implemented as singleton and all
 * relevant methods are static.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public final class ServicePublisher {

    /** The singleton instance of the service publisher. */
    private static final ServicePublisher INSTANCE
        = new ServicePublisher();

    /** Manager for publishing via HTTP. */
    private IServerManager httpManager
        = new HttpServerManager();

    /** Manager for publishing via HTTPS. */
    private IServerManager httpsManager
        = new HttpsServerManager();

    /** Manager for publishing via jETI. */
    private IServerManager jetiManager
        = new JetiManager();

    /** Manager for publishing diverse support handlers. */
    private IServerManager supportManager
        = new SupportingServerManager();

    /** Instance of the layout web service to be published. */
    private LayoutServicePort service
        = new JaxWsService();

    /**
     * Private constructor.
     */
    private ServicePublisher() {
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    public static ServicePublisher getInstance() {
        return INSTANCE;
    }
    
    /**
     * Publishes the web service.
     *
     */
    public synchronized void publish() {
        if (isPublished()) {
            throw new AlreadyPublishedException();
        }
        try {
            if (Configuration.getInstance().getConfigProperty(Configuration.PUBLISH_HTTP).
                    equalsIgnoreCase("true")
               ) {
                Logger.log("Publishing layout service via HTTP");
                httpManager.publish(service);
            }
            if (Configuration.getInstance().getConfigProperty(Configuration.PUBLISH_HTTPS).
                    equalsIgnoreCase("true")
               ) {
                Logger.log("Publishing layout service via HTTPS");
                httpsManager.publish(service);
            }
            if (Configuration.getInstance().getConfigProperty(Configuration.PUBLISH_JETI).
                    equalsIgnoreCase("true")
               ) {
                Logger.log("Publishing layout service via jETI");
                jetiManager.publish(null);
            }
            if (Configuration.getInstance().getConfigProperty(Configuration.PUBLISH_SUPPORTSERVER).
                    equalsIgnoreCase("true")
               ) {
                Logger.log("Publishing support server");
                supportManager.publish(null);
            }
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Web service could not be published", e);
            unpublish();
            throw new NotPublishedException();
        }
    }

    /**
     * Unpublishes the web service.
     *
     */
    public synchronized void unpublish() {
        httpManager.unpublish();
        httpsManager.unpublish();
        jetiManager.unpublish();
        supportManager.unpublish();
    }

    /**
     * Gives information about the publishing state.
     *
     * @return whether service is published or not
     */
    public synchronized boolean isPublished() {
        return (httpManager.isPublished() || httpsManager.isPublished() 
                || jetiManager.isPublished() || supportManager.isPublished());
    }

}
