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

import java.util.Properties;
import java.util.Timer;

import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.Application;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.util.Io;
import de.unido.ls5.eti.connector.sepp.EtiSeppConnector;
import de.unido.ls5.eti.sps.apis.EtiConfig;
import de.unido.ls5.eti.sps.apis.EtiConnector;
import de.unido.ls5.eti.toolserver.EtiExecutorImpl;
import de.unido.ls5.eti.toolserver.SessionTimeouter;

/**
 * Manager for wrapping the jETI tool server.
 * Concurrent safety has to be provided by using instance.
 *
 * @author swe
 *
 */
final class JetiManager extends AbstractServerManager {

    /** The published eti connector. */
    private EtiConnector etiConnector;

    /**
     * {@inheritDoc}
     */
    public void publish(final Object theobject) {
        createServer();
        try {                    
            etiConnector.start(EtiConfig.getProperties());
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "jETI server could not be published", e);
            throw new NotPublishedException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void unpublish() {
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
    public boolean isPublished() {
        return (etiConnector != null); //FIXME unscharf
    }

    /** */
    private static final String JETICONFIG_SCI
        = "jeti.sessions.checkinterval";

    /**
     * Creates and configures an embedded jETI tool server.
     */
    private void createServer() {
        if (etiConnector != null) {
            throw new AlreadyPublishedException();
        }
        try {
            java.util.Properties log4jProps = createLog4jProperties();
            if (log4jProps == null) {
                throw new ServerNotCreatedException();
            }
            org.apache.log4j.PropertyConfigurator.configure(log4jProps);
            org.apache.log4j.Logger.getLogger(ServicePublisher.class);        
            java.util.Properties jetiProps = createJetiProperties();
            EtiConfig.setProperties(jetiProps);
            EtiConfig.setExecutor(new EtiExecutorImpl());
            Long checkInterval
                = new Long(jetiProps.getProperty(JETICONFIG_SCI));
            Timer sessionTimeout
                = new Timer();
            sessionTimeout.schedule(
                new SessionTimeouter(),
                checkInterval.longValue(),
                checkInterval.longValue()
            );
            etiConnector = new EtiSeppConnector();
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "jETI server could not be created", e);
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ServerNotCreatedException(e);
        }
    }

    /** */
    private static final String PREFIX
        = "de.cau.cs.kieler.kwebs.";

    /**
     * Creates a {@code java.util.Properties} object containing relevant data
     * for publishing the embedded jETI tool server.
     * 
     * @return the properties
     */
    private java.util.Properties createJetiProperties() {
        java.util.Properties props = new java.util.Properties();            
        props.put(
            Configuration.JETI_PROVIDER_ID.substring(PREFIX.length()),
            Configuration.getConfigProperty(Configuration.JETI_PROVIDER_ID)
        );
        props.put(
            Configuration.JETI_TOOLXML.substring(PREFIX.length()),
            Configuration.getConfigProperty(Configuration.JETI_TOOLXML)
        );
        props.put(
            Configuration.JETI_SESSIONSFOLDER.substring(PREFIX.length()),
            Configuration.getConfigProperty(Configuration.JETI_SESSIONSFOLDER)
        );
        props.put(
            Configuration.JETI_SESSIONSTIMEOUT.substring(PREFIX.length()),
            Configuration.getConfigProperty(Configuration.JETI_SESSIONSTIMEOUT)
        );
        props.put(
            Configuration.JETI_SESSIONSCHECKINTERVAL.substring(PREFIX.length()),
            Configuration.getConfigProperty(Configuration.JETI_SESSIONSCHECKINTERVAL)
        );
        props.put(
            Configuration.JETI_SERVERHOSTNAME.substring(PREFIX.length()),
            Configuration.getConfigProperty(Configuration.JETI_SERVERHOSTNAME)
        );
        props.put(
            Configuration.JETI_CONNECTORSEPPPORT.substring(PREFIX.length()),
            Configuration.getConfigProperty(Configuration.JETI_CONNECTORSEPPPORT)
        );
        props.put(
            Configuration.JETI_DEBUG.substring(PREFIX.length()),
            Configuration.getConfigProperty(Configuration.JETI_DEBUG)
        );
        return props;
    }

    /**
     * Creates a {@code java.util.Properties} object containing relevant data
     * for configuring log4j logger used by jETI tool server.
     * 
     * @return the properties
     */
    private Properties createLog4jProperties() {
        Properties props = new Properties();
        try {
            props.load(Io.getResourceStream(
                Application.PLUGIN_ID,
                Configuration.getConfigProperty(Configuration.JETI_LOG4JCONFIG))
            );
            props.put(
                "log4j.appender.A2.File", 
                Configuration.getConfigProperty(Configuration.JETI_LOGPATH)
            );
        } catch (Exception e) {
            Logger.log(Severity.FAILURE, "jETI log4j configuration could not be created", e);
            props = null;
        }
        return props;
    }
    
}
