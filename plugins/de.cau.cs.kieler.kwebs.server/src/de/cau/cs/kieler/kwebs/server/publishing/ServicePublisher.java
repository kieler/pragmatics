/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.publishing;

import java.net.URI;

import de.cau.cs.kieler.kwebs.jaxws.LayoutServicePort;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.service.JaxWsService;
//import de.cau.cs.kieler.kwebs.server.service.RestService;

//FIXME If service is published via HTTP AND HTTPS, both
//      servers are created with the full pool size. They should share.

/**
 * The {@code ServicePublisher} is the central point for managing the
 * publishing of the layout service.
 * 
 * @kieler.rating  2011-08-25 proposed yellow
 *      reviewed by ckru, msp, mri
 *      
 * @author  swe
 */
public final class ServicePublisher {

    /** The singleton instance of the service publisher. */
    private static final ServicePublisher INSTANCE
        = new ServicePublisher();

    /** The server wide configuration instance. */
    private Configuration config
        = Configuration.getInstance();

    /** Manager for publishing JAXWS service via HTTP. */
    private IServerManager jaxwsHttpManager
        = new HttpServerManager();

    /** Manager for publishing JAXWS service via HTTPS. */
    private IServerManager jaxwsHttpsManager
        = new HttpsServerManager();

    /** Manager for publishing REST service via HTTP. */
    //private IServerManager restHttpManager
    //    = new HttpServerManager();

    /** Manager for publishing REST service via HTTPS. */
    //private IServerManager restHttpsManager
    //    = new HttpsServerManager();

    /** Manager for publishing via jETI. */
    private IServerManager jetiManager
        = new JetiManager();

    /** Manager for publishing diverse support handlers. */
    private IServerManager supportManager
        = new SupportingServerManager();

    /** Instance of the layout web service to be published. */
    private LayoutServicePort jaxwsService
        = new JaxWsService();

    /** Instance of the RESTful web service implementation. */
    //private RestService restService 
    //    = new RestService();
    
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
            URI address = null;            
            if (Configuration.getInstance().getConfigProperty(Configuration.JAXWS_PUBLISH_HTTP).
                equalsIgnoreCase("true")
            ) {
                address = new URI(config.getConfigProperty(Configuration.JAXWS_HTTP_ADDRESS));
                Logger.log(
                    "Publishing jaxws layout service via HTTP on "
                    + address.toString()
                );                
                jaxwsHttpManager.setAddress(address);
                jaxwsHttpManager.publish(jaxwsService);
            }
            if (Configuration.getInstance().getConfigProperty(Configuration.JAXWS_PUBLISH_HTTPS).
                equalsIgnoreCase("true")
            ) {
                address = new URI(config.getConfigProperty(Configuration.JAXWS_HTTPS_ADDRESS));
                Logger.log(
                    "Publishing jaxws layout service via HTTPS on "
                    + address.toString()
                );                
                jaxwsHttpsManager.setAddress(address);
                jaxwsHttpsManager.publish(jaxwsService);
            }/*        
            if (Configuration.getInstance().getConfigProperty(Configuration.REST_PUBLISH_HTTP).
                equalsIgnoreCase("true")
            ) {
                address = new URI(config.getConfigProperty(Configuration.REST_HTTP_ADDRESS));
                Logger.log(
                    "Publishing rest layout service via HTTP on "
                    + address.toString()
                );                
                restHttpManager.setAddress(address);
                restHttpManager.publish(restService);
            }
            if (Configuration.getInstance().getConfigProperty(Configuration.REST_PUBLISH_HTTPS).
                equalsIgnoreCase("true")
            ) {
                address = new URI(config.getConfigProperty(Configuration.REST_HTTPS_ADDRESS));
                Logger.log(
                    "Publishing rest layout service via HTTPS on "
                    + address.toString()
                );                
                restHttpsManager.setAddress(address);
                restHttpsManager.publish(restService);
            }*/
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
        jaxwsHttpManager.unpublish();
        jaxwsHttpsManager.unpublish();
        //restHttpManager.unpublish();
        //restHttpsManager.unpublish();
        jetiManager.unpublish();
        supportManager.unpublish();
    }

    /**
     * Gives information about the publishing state.
     *
     * @return whether service is published or not
     */
    public synchronized boolean isPublished() {
        return (jaxwsHttpManager.isPublished() || jaxwsHttpsManager.isPublished()
                //|| restHttpManager.isPublished() || restHttpsManager.isPublished()
                || jetiManager.isPublished() || supportManager.isPublished());
    }

}
