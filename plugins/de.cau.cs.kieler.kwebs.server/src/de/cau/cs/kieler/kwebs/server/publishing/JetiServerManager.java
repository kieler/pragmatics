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

import java.io.IOException;
import java.util.Properties;
import java.util.Timer;

import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.util.Resources;
import de.unido.ls5.eti.connector.sepp.EtiSeppConnector;
import de.unido.ls5.eti.sps.apis.EtiConfig;
import de.unido.ls5.eti.sps.apis.EtiConnector;
import de.unido.ls5.eti.toolserver.EtiExecutorImpl;
import de.unido.ls5.eti.toolserver.SessionTimeouter;

/**
 * Manager for wrapping the jETI tool server.
 * Concurrent safety has to be provided by using instance.
 * 
 * @kieler.design 2011-08-25 reviewed by ckru, msp, mri
 * @author swe
 */
final class JetiServerManager extends AbstractServerManager {

    /** The published jETI connector. */
    private EtiConnector etiConnector;

    /**
     * {@inheritDoc}
     */
    public synchronized void publish(final Object theobject) {
        try {                    
            createServer();
            etiConnector.start(EtiConfig.getProperties());
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "jETI server could not be published", e);
            throw new NotPublishedException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void unpublish() {
        if (etiConnector != null) {
            try {
                etiConnector.stop();
                etiConnector = null;
            } catch (Exception e) {
                Logger.log(Severity.CRITICAL, "jETI server could not be unpublished", e);
                throw new NotUnpublishedException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized boolean isPublished() {
        return (etiConnector != null);
    }

    /** */
    private static final String JETICONFIG_SCI
        = "jeti.sessions.checkinterval";

    /**
     * Creates and configures an embedded jETI tool server.
     */
    private synchronized void createServer() {
        if (etiConnector != null) {
            throw new AlreadyPublishedException();
        }
        java.util.Properties log4jProps = null;
        try {
            log4jProps = createLog4jProperties();
        } catch (IOException e) {
            throw new ServerNotCreatedException(e);
        }
        org.apache.log4j.PropertyConfigurator.configure(log4jProps);
        org.apache.log4j.Logger.getLogger(ServicePublisher.class);        
        java.util.Properties jetiProps = createJetiProperties();
        EtiConfig.setProperties(jetiProps);
        EtiConfig.setExecutor(new EtiExecutorImpl());
        long checkInterval
            = new Long(jetiProps.getProperty(JETICONFIG_SCI)).longValue();
        Timer sessionTimeout
            = new Timer();
        sessionTimeout.schedule(
            new SessionTimeouter(), checkInterval, checkInterval
        );
        etiConnector = new EtiSeppConnector();
    }

    /** 
     *  Prefix to be cut off from the jETI property identifiers when they are put
     *  in the jETI configuration.
     */
    private static final String PREFIX
        = "de.cau.cs.kieler.kwebs.";

    /** 
     *  Statically defined array of the property identifiers
     *  relevant for initializing the jETI configuration.
     */
    private static final String[] JETI_PROPERTIES
        = new String[] {
            Configuration.JETI_PROVIDER_ID,
            Configuration.JETI_TOOLXML,
            Configuration.JETI_SESSIONSFOLDER,
            Configuration.JETI_SESSIONSTIMEOUT,
            Configuration.JETI_SESSIONSCHECKINTERVAL,
            Configuration.JETI_SERVERHOSTNAME,
            Configuration.JETI_CONNECTORSEPPPORT,
            Configuration.JETI_DEBUG
        };
    
    /**
     * Creates a {@code java.util.Properties} object containing relevant data
     * for publishing the embedded jETI tool server.
     * 
     * @return the properties
     */
    private java.util.Properties createJetiProperties() {
        java.util.Properties props = new java.util.Properties();  
        for (String prop : JETI_PROPERTIES) {
            props.put(prop.substring(PREFIX.length()), config.getConfigProperty(prop));  
        }
        return props;
    }

    /**
     * Creates a {@code java.util.Properties} object containing relevant data
     * for configuring log4j logger used by jETI tool server.
     * 
     * @return the properties
     * @throws IOException
     *             If the via the KWebS configuration defined configuration file for jog4j
     *             does not exist or is not readable
     */
    private Properties createLog4jProperties() throws IOException {
        Properties props = new Properties();
        props.load(Resources.getResourceStream(
            Application.PLUGIN_ID,
            config.getConfigProperty(Configuration.JETI_LOG4JCONFIG))
        );
        props.put(
            "log4j.appender.A2.File", 
            config.getConfigProperty(Configuration.JETI_LOGPATH)
        );
        return props;
    }
    
}
