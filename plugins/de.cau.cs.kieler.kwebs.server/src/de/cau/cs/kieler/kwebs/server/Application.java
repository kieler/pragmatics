/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Kiel University
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.DisplayLogging;
import de.cau.cs.kieler.kwebs.server.logging.ILoggerListener;
import de.cau.cs.kieler.kwebs.server.logging.JavaLoggingAdapter;
import de.cau.cs.kieler.kwebs.server.logging.Logger;
import de.cau.cs.kieler.kwebs.server.logging.RoundTripFileLogging;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Mode;
import de.cau.cs.kieler.kwebs.server.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.management.ManagementService;
import de.cau.cs.kieler.kwebs.server.publishing.ServicePublisher;
import de.cau.cs.kieler.kwebs.server.util.Arguments;
import de.cau.cs.kieler.kwebs.server.util.Resources;

/**
 * The main server application.
 *
 * @kieler.design 2011-08-25 reviewed by ckru, msp, mri
 * @author swe
 */
public class Application implements IApplication {

    /** The id of this plug-in. */
    public static final String PLUGIN_ID
        = "de.cau.cs.kieler.kwebs.server";

    /** The file logger used. */
    private ILoggerListener fileLogging;

    /** The display logger used in debug mode. */
    private ILoggerListener displayLogging;

    /** Object to synchronize and wait on for termination request. */
    private final Object termSync
        = new Object();

    /** Whether the server shall terminate. */
    private boolean termRequested
        = false;

    /** Default path of the log file. */
    private static final String DEFAULT_LOGPATH
        = "./server/kwebs/logs/kwebs.log";

    /** Default size of log file in megabytes. */
    private static final int DEFAULT_LOGSIZE
        = 1;

    /** Default path to configuration file. */
    private static final String DEFAULT_CONFIG
        = "server/kwebs/config/kwebs.properties";

    /** Default path to user configuration file. */
    private static final String DEFAULT_USERCONFIG
        = "kwebs.user";

    /** Index of command line arguments in result of {@code context.getArguments()}. */
    private static final String ARGUMENTS_INDEX
        = "application.args";

    /** Parameter map index of the user configuration path command line option. */
    private static final String USERCONFIG_INDEX
        = "userconfig";

    /** Identifier for override command line options. */
    private static final String OVERRIDE_IDENTIFIER
        = "#";

    /** Prefix for properties. */
    private static final String PROPERTY_PREFIX
        = "de.cau.cs.kieler.kwebs.";

    /** */
    private static Injector injector;

    /** The instance of this application. */
    private static Application instance;

    /**
     * Default constructor for setting the instance field.
     */
    public Application() {
        instance = this;
    }

    /**
     * Return this instance.
     *
     * @return this instance
     */
    public static final Application getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    public final Object start(final IApplicationContext context) {

        System.out.println(
            ApplicationHelper.toDisplayable(ApplicationHelper.TITLE_TEXT).
                replace("$VERSION$", getVersion())
        );

        // Register display logging
        displayLogging = new DisplayLogging();
        Logger.addLoggerListener(displayLogging);

        String logPath = DEFAULT_LOGPATH;
        int logSize = DEFAULT_LOGSIZE;

        boolean debugMode = false;

        // Parse command line arguments
        Map<String, String> arguments = null;
        if (context != null) {
            @SuppressWarnings("rawtypes")
            Map argumentsMap = context.getArguments();
            if (argumentsMap != null && argumentsMap.containsKey(ARGUMENTS_INDEX)) {
                arguments = Arguments.parseArgs((String[]) argumentsMap.get(ARGUMENTS_INDEX));
            }
        }

        // Read default config
        Logger.log(Severity.ALWAYS, "Loading default configuration.");
        try {
            Configuration.INSTANCE.loadFromStream(
                Resources.getResourceStream(PLUGIN_ID, DEFAULT_CONFIG)
            );
        } catch (Exception e) {
            Logger.log(Severity.FAILURE, "Could not load default configuration.");
            return IApplication.EXIT_OK;
        }

        // Load user configuration. The properties in the user configuration override
        // the default configuration properties defined in this plugin. Normally, the user
        // configuration lies in the servers program folder but the user can define a different
        // user config file with the '-userconfig=<path>' switch.
        String userConfig = DEFAULT_USERCONFIG;
        if (arguments != null) {
            if (arguments.containsKey(USERCONFIG_INDEX)) {
                userConfig = arguments.get(USERCONFIG_INDEX);
            }
        }
        Logger.log(Severity.ALWAYS,
            "Loading user configuration: " + new File(userConfig).getAbsolutePath()
        );
        try {
            Configuration.INSTANCE.loadFromStream(new FileInputStream(userConfig));
        } catch (Exception e) {
            Logger.log(Severity.WARNING, "Could not load user configuration.", e);
        }

        // Command line overrides for the service publisher properties.
        // If the given property does not start with the common property prefix is
        // is being extended with it. This is for user friendliness.
        if (arguments != null) {
            String property = null;
            for (String key : arguments.keySet()) {
                if (key.startsWith(OVERRIDE_IDENTIFIER)) {
                    property = key.substring(1);
                    if (!property.startsWith(PROPERTY_PREFIX)) {
                        property = PROPERTY_PREFIX + property;
                    }
                    Configuration.INSTANCE.setConfigProperty(property, arguments.get(key));
                }
            }
        }

        // Update local vars from properties
        if (Configuration.INSTANCE.hasConfigProperty(Configuration.KWEBS_LOGPATH)) {
            logPath = Configuration.INSTANCE.getConfigProperty(Configuration.KWEBS_LOGPATH);
        }
        if (Configuration.INSTANCE.hasConfigProperty(Configuration.KWEBS_LOGSIZE)) {
            try {
                logSize = Integer.parseInt(
                    Configuration.INSTANCE.getConfigProperty(Configuration.KWEBS_LOGSIZE)
                );
            } catch (Exception e) {
                Logger.log(Severity.WARNING,
                    "Invalid log size: "
                    + Configuration.INSTANCE.getConfigProperty(Configuration.KWEBS_LOGSIZE)
                    + ", using default log size of " + DEFAULT_LOGSIZE + " mb"
                );
            }
        }

        // Register file logging
        fileLogging = new RoundTripFileLogging(logPath, logSize);
        Logger.addLoggerListener(fileLogging);

        // Optionally set the application in debug mode. In debug mode
        // the logger output is more verbose.
        if (Configuration.INSTANCE.hasConfigProperty(Configuration.KWEBS_LOGDEBUGMODE)) {
            try {
                debugMode = Boolean.parseBoolean(
                    Configuration.INSTANCE.getConfigProperty(Configuration.KWEBS_LOGDEBUGMODE)
                );
                if (debugMode) {
                    Logger.setRunMode(Mode.DEBUG);
                }
            } catch (Exception e) {
                Logger.log(Severity.WARNING,
                    "Invalid debug mode: "
                    + Configuration.INSTANCE.getConfigProperty(Configuration.KWEBS_LOGDEBUGMODE)
                    + ", using non debug mode"
                );
            }
        }

        injector = Guice.createInjector();

        // Create server configuration folder structure if it not already
        // exists.
        createConfigurationFolder();

        // Start the management service
        if (Configuration.INSTANCE.hasConfigProperty(Configuration.MANAGEMENT_PORT)) {
            try {
                ManagementService.INSTANCE.setPort(
                    Integer.parseInt(
                        Configuration.INSTANCE.getConfigProperty(Configuration.MANAGEMENT_PORT)
                    )
                );
                ManagementService.INSTANCE.setKeystoreLocation(
                    Configuration.INSTANCE.getConfigProperty(Configuration.MANAGEMENT_KEYSTORE_LOCATION)
                );
                ManagementService.INSTANCE.setKeystorePassword(
                    Configuration.INSTANCE.getConfigProperty(Configuration.MANAGEMENT_KEYSTORE_PASSWORD)
                );
            } catch (Exception e) {
                Logger.log(Severity.WARNING,
                    "Invalid management port: "
                    + Configuration.INSTANCE.getConfigProperty(Configuration.MANAGEMENT_PORT)
                    + ", using default port " + ManagementService.DEFAULT_MANAGEMENTPORT
                );
            }
        }
        Logger.log(
            Severity.ALWAYS,
            "Starting management service on port " + ManagementService.INSTANCE.getPort()
        );
        try {
            ManagementService.INSTANCE.startManagement();
            Logger.log(Severity.ALWAYS, "Management service started");
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Management service could not be started", e);
        }

        // Set necessary plug-in preferences of the used layout plug-ins
        try {
            setPluginPreferencesFromConfiguration();
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Error while initializing layouter preferences", e);
            return IApplication.EXIT_OK;
        }

        // Initialize server and publish service
        try {
            Logger.log(Severity.DEBUG, "Registering logging adapter for jax-ws");
            JavaLoggingAdapter.register();
            ServicePublisher.INSTANCE.publish();
            synchronized (termSync) {
                while (!termRequested) {
                    try {
                        termSync.wait();
                    } catch (InterruptedException e) {
                        // Nothing to do, simply wait on synchronization
                        // object again.
                    }
                }
            }
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Error while initializing layout server", e);
        }

        // Unpublish service on exit if necessary
        if (ServicePublisher.INSTANCE.isPublished()) {
            ServicePublisher.INSTANCE.unpublish();
        }

        // Unpublish management service
        ManagementService.INSTANCE.stopManagement();

        // Unregister file logging
        Logger.removeLoggerListener(fileLogging);

        try {
            ((RoundTripFileLogging) fileLogging).close();
        } catch (Exception e) {
            Logger.log(Severity.FAILURE, "Log file could not be closed.");
        }

        // Unregister display logging
        Logger.removeLoggerListener(displayLogging);

        //FIXME: Unregister logging adapters

        return IApplication.EXIT_OK;

    }

    /**
     * {@inheritDoc}
     */
    public final void stop() {
        shutdownServer();
    }

    /**
     * Shuts down the server.
     */
    public final synchronized void shutdownServer() {
        Logger.log("Shutting down the server.");
        // Notify the termination sync loop
        termRequested = true;
        synchronized (termSync) {
            termSync.notify();
        }
    }

    /** The plug-in id of the graphviz layouter. */
    private static final String GRAPHVIZ_PLUGINID
        = "de.cau.cs.kieler.kiml.graphviz.layouter";

    /** The preference id of the graphviz executable preference. */
    private static final String GRAPHVIZ_EXECPREF
        = "graphviz.executable";

    /** The preference id of the graphviz timeout. */
    private static final String GRAPHVIZ_TIMEOUTPREF
        = "graphviz.timeout";

    /** The plug-in id of the ogdf layouter. */
    private static final String OGDF_PLUGINID
        = "net.ogdf.bin";

    /** The preference id of the graphviz timeout. */
    private static final String OGDF_TIMEOUTPREF
        = "ogdf.timeout";

    /**
     * Sets the necessary preferences from different layouter plugins in order
     * for them to function correctly.
     */
    private void setPluginPreferencesFromConfiguration() {
        String value = null;
        File file = null;
        if (!Configuration.INSTANCE.hasConfigProperty(Configuration.GRAPHVIZ_PATH)) {
            throw new IllegalStateException(
                "Properties do not contain property for graphviz executable"
            );
        }
        if (!Configuration.INSTANCE.hasConfigProperty(Configuration.GRAPHVIZ_TIMEOUT)) {
            throw new IllegalStateException(
                "Properties do not contain property for graphviz timeout"
            );
        }
        if (!Configuration.INSTANCE.hasConfigProperty(Configuration.OGDF_TIMEOUT)) {
            throw new IllegalStateException(
                "Properties do not contain property for ogdf timeout"
            );
        }
        value = Configuration.INSTANCE.getConfigProperty(Configuration.GRAPHVIZ_PATH);
        if (value != null) {
            file = new File(value.replaceAll("\\\\", "/"));
            if (!file.exists() || !file.canExecute()) {
                Logger.log(Severity.WARNING,
                    "The specified graphviz executable does not exist or is not executable."
                    + " Graphviz based layout will not work."
                    + " Please check your config file (normally kwebs.user in server root path)."
                );
            } else {
                Logger.log(Severity.ALWAYS, "Setting graphviz executable: " + value);
                setPluginPreference(GRAPHVIZ_PLUGINID, GRAPHVIZ_EXECPREF, value);
            }
        } else {
            Logger.log(Severity.WARNING,
                "The graphviz executable is not configured."
                + " Graphviz based layout will not work."
                + " Please check your config file (normally kwebs.user in server root path)."
            );
        }
        value = Configuration.INSTANCE.getConfigProperty(Configuration.GRAPHVIZ_TIMEOUT);
        Logger.log(Severity.ALWAYS, "Setting graphviz timeout: " + value);
        setPluginPreference(GRAPHVIZ_PLUGINID, GRAPHVIZ_TIMEOUTPREF, value);
        value = Configuration.INSTANCE.getConfigProperty(Configuration.OGDF_TIMEOUT);
        Logger.log(Severity.ALWAYS, "Setting ogdf timeout: " + value);
        setPluginPreference(OGDF_PLUGINID, OGDF_TIMEOUTPREF, value);
    }

    /**
     * Sets a preference in the preference store of the given plugin.
     *
     * @param pluginid
     *            the identifier of the plugin
     * @param preferenceid
     *            the identifier of the preference to be set
     * @param value
     *            the value to be set for the preference
     */
    private void setPluginPreference(final String pluginid, final String preferenceid,
        final String value) {
        IEclipsePreferences store = InstanceScope.INSTANCE.getNode(pluginid);
        Logger.log(Severity.DEBUG,
            pluginid + ", " + preferenceid + " : " + store.get(preferenceid, "")
        );
        store.put(preferenceid, value);
        try {
            store.flush();
        } catch (Exception e) {
            Logger.log(Severity.FAILURE, "Could not save preference store: " + pluginid);
        }
    }

    /**
     * Builds the configuration folder structure needed for operation.
     */
    private void createConfigurationFolder() {
        for (String file : ApplicationHelper.CONFIGURATION_FILES) {
            if (!new File(file).exists()) {
                try {
                    Resources.writeFile(file, Resources.getResourceStream(PLUGIN_ID, file));
                } catch (Exception e) {
                    Logger.log(Severity.FAILURE, "Could not create config file " + file, e);
                }
            }
        }
    }

    /**
     * Read the version of this plug-in.
     *
     * @return the version of this plug-in
     */
    public static String getVersion() {
        return Resources.getPluginVersion(PLUGIN_ID);
    }

    /**
     * Returns the injector to be used server wide.
     *
     * @return the injector to be used server wide
     */
    public static Injector getInjector() {
        return injector;
    }

}
