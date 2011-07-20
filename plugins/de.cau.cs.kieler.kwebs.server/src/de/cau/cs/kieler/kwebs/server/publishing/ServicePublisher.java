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

import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.service.JaxWsService;
import de.cau.cs.kieler.kwebs.service.IGraphLayouterService;

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

    /** Manager for publishing via http. */
    private IServerManager httpManager
        = new HttpServerManager();

    /** Manager for publishing via https. */
    private IServerManager httpsManager
        = new HttpsServerManager();

    /** Manager for publishing via jETI. */
    private IServerManager jetiManager
        = new JetiManager();

    /** Instance of the layouter web service to be published. */
    private IGraphLayouterService service
        = new JaxWsService();

    /**
     * Private constructor.
     */
    private ServicePublisher() {
    }

    /**
     * Publishes the web service.
     *
     */
    public static synchronized void publish() {
        if (isPublished()) {
            throw new AlreadyPublishedException();
        }
        try {
            if (Configuration.getConfigProperty(Configuration.PUBLISH_HTTP).equalsIgnoreCase("true")) {
                INSTANCE.httpManager.publish(INSTANCE.service);
            }
            if (Configuration.getConfigProperty(Configuration.PUBLISH_HTTPS).equalsIgnoreCase("true")) {
                INSTANCE.httpsManager.publish(INSTANCE.service);
            }
            if (Configuration.getConfigProperty(Configuration.PUBLISH_JETI).equalsIgnoreCase("true")) {
                INSTANCE.jetiManager.publish(null);
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
    public static synchronized void unpublish() {
        INSTANCE.httpManager.unpublish();
        INSTANCE.httpsManager.unpublish();
        INSTANCE.jetiManager.unpublish();
    }

    /**
     * Gives information about the publishing state.
     *
     * @return whether service is published or not
     */
    public static synchronized boolean isPublished() {
        return (INSTANCE.httpManager.isPublished()
                || INSTANCE.httpsManager.isPublished()
                || INSTANCE.jetiManager.isPublished());
    }

}
