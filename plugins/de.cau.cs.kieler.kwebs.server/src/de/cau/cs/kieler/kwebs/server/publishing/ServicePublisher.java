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

    /** Manager for publishing via jETI. */
    private IServerManager jetiManager
        = new JetiServerManager();

    /** Manager for publishing diverse support handlers. */
    private IServerManager supportManager
        = new SupportingServerManager();

    /** Instance of the layout web service to be published. */
    private LayoutServicePort jaxwsService
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
            URI address = null;  
            // Determine the maximum amount of concurrently executed requests
            int poolSize = AbstractServerManager.DEFAULT_POOLSIZE;
            try {
                poolSize = Integer.parseInt(config.getConfigProperty(Configuration.SERVER_POOLSIZE));
            } catch (NumberFormatException e) {
                Logger.log(
                    Severity.WARNING, 
                    "Illegal pool size configured: " 
                    + config.getConfigProperty(Configuration.SERVER_POOLSIZE)
                    + ", using default value of " 
                    + AbstractServerManager.DEFAULT_POOLSIZE
                );
            }   
            // Check which variants are to be published
            boolean publishHTTP = true;            
            if (config.hasConfigProperty(Configuration.JAXWS_PUBLISH_HTTP)) {
                publishHTTP = config.getConfigProperty(Configuration.JAXWS_PUBLISH_HTTP).
                    equalsIgnoreCase("true"); 
            }
            boolean publishHTTPS = false;
            if (config.hasConfigProperty(Configuration.JAXWS_PUBLISH_HTTPS)) {
                publishHTTPS = config.getConfigProperty(Configuration.JAXWS_PUBLISH_HTTPS).
                    equalsIgnoreCase("true"); 
            }        
            boolean publishJETI = false;
            if (config.hasConfigProperty(Configuration.PUBLISH_JETI)) {
                publishJETI = config.getConfigProperty(Configuration.PUBLISH_JETI).
                    equalsIgnoreCase("true"); 
            }        
            boolean publishSUPP = true;
            if (config.hasConfigProperty(Configuration.PUBLISH_SUPPORTSERVER)) {
                publishSUPP = config.getConfigProperty(Configuration.PUBLISH_SUPPORTSERVER).
                    equalsIgnoreCase("true"); 
            }        
            // When publishing the JAXWS as HTTP and HTTPS variant they have to share the maximum
            // amount of concurrently executed requests
            if (publishHTTP && publishHTTPS) {
                if (poolSize > 1) {
                    poolSize /= 2;
                }
            }   
            // Publish the variants
            if (publishHTTP) {
                address = new URI(config.getConfigProperty(Configuration.JAXWS_HTTP_ADDRESS));
                Logger.log(
                    "Publishing jaxws layout service via HTTP on "
                    + address.toString()
                );                
                jaxwsHttpManager.setPoolSize(poolSize);
                jaxwsHttpManager.setAddress(address);
                jaxwsHttpManager.publish(jaxwsService);
            }
            if (publishHTTPS) {
                address = new URI(config.getConfigProperty(Configuration.JAXWS_HTTPS_ADDRESS));
                Logger.log(
                    "Publishing jaxws layout service via HTTPS on "
                    + address.toString()
                );
                jaxwsHttpsManager.setPoolSize(poolSize);
                jaxwsHttpsManager.setAddress(address);
                jaxwsHttpsManager.publish(jaxwsService);
            }
            if (publishJETI) {
                Logger.log("Publishing layout service via jETI");
                jetiManager.publish(null);
            }
            // Publish the support server
            if (publishSUPP) {
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
                || jetiManager.isPublished() || supportManager.isPublished());
    }

}
