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

package de.cau.cs.kieler.kwebs.server;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osgi.framework.Version;

import de.cau.cs.kieler.kwebs.logging.ILoggerListener;
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Mode;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.server.configuration.Configuration;
import de.cau.cs.kieler.kwebs.server.logging.DisplayLogging;
import de.cau.cs.kieler.kwebs.server.logging.JaxWsAdapter;
import de.cau.cs.kieler.kwebs.server.logging.RoundTripFileLogging;
import de.cau.cs.kieler.kwebs.server.management.ManagementService;
import de.cau.cs.kieler.kwebs.server.publishing.ServicePublisher;
import de.cau.cs.kieler.kwebs.util.Files;
import de.cau.cs.kieler.kwebs.util.Io;

/**
 * The main eclipse application.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class Application implements IApplication {

    /** */
    public static final String PLUGIN_ID
        = "de.cau.cs.kieler.kwebs.server";

    /** */
    private ILoggerListener fileLogging;

    /** */
    private ILoggerListener displayLogging;

    /** */
    private boolean stopped
        = false;

    /** Timeout for main application loop. */
    private static final long LOOP_TIMEOUT
        = 1000;

    /** Default path of the log file. */
    private static final String DEFAULT_LOGPATH
        = "./server/kwebs/logs/kwebs.log";

    /** Default size of log file in mb. */
    private static final int DEFAULT_LOGSIZE
        = 10;

    /** Default path to config file. */
    private static final String DEFAULT_CONFIG
        = "server/kwebs/config/kwebs.properties";

    /** Default path to user config file. */
    private static final String DEFAULT_USERCONFIG
        = "kwebs.user";

    /** Index of command line arguments in result of {@code context.getArguments()}. */
    private static final String ARGUMENTS_INDEX
        = "application.args";

    /** Parameter map index of the user config path command line option. */
    private static final String USERCONFIG_INDEX
        = "userconfig";

    /** Identifier for override command line options. */
    private static final String OVERRIDE_IDENTIFIER
        = "#";
    
    /** Prefix for properties. */
    private static final String PROPERTY_PREFIX
        = "de.cau.cs.kieler.kwebs.";
    
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
        
        int managementPort = ManagementService.DEFAULT_MANAGEMENTPORT;
        
        boolean debugMode = false;
        
        // Parse command line arguments
        @SuppressWarnings("rawtypes")
        Map argumentsMap = context.getArguments();
        Map<String, String> arguments = null;
        if (argumentsMap != null && argumentsMap.containsKey(ARGUMENTS_INDEX)) {
            arguments = parseArgs((String[]) argumentsMap.get(ARGUMENTS_INDEX));
        }
        
        // Read default config
        Logger.log(Severity.ALWAYS, "Loading default configuration.");
        try {
            Configuration.loadFromStream(Io.getResourceStream(PLUGIN_ID, DEFAULT_CONFIG));
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
            Configuration.loadFromStream(new FileInputStream(userConfig));
        } catch (Exception e) {
            Logger.log(Severity.WARNING, "Could not load user configuration.");
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
                    Configuration.setConfigProperty(property, arguments.get(key));
                }
            }
        }
        
        // Update local vars from properties
        if (Configuration.hasConfigProperty(Configuration.KWEBS_LOGPATH)) {
            logPath = Configuration.getConfigProperty(Configuration.KWEBS_LOGPATH);
        }
        if (Configuration.hasConfigProperty(Configuration.KWEBS_LOGSIZE)) {
            try {
                logSize = Integer.parseInt(Configuration.getConfigProperty(Configuration.KWEBS_LOGSIZE));
            } catch (Exception e) {
                Logger.log(Severity.WARNING,
                    "Invalid log size: " 
                    + Configuration.getConfigProperty(Configuration.KWEBS_LOGSIZE)
                    + ", using default log size of " + DEFAULT_LOGSIZE + " mb"
                );
            }
        }
        
        // Register file logging
        fileLogging = new RoundTripFileLogging(logPath, logSize);
        Logger.addLoggerListener(fileLogging);

        // Optionally set the application in debug mode. In debug mode
        // the logger output is more verbose.
        if (Configuration.hasConfigProperty(Configuration.KWEBS_LOGDEBUGMODE)) {
            try {
                debugMode = Boolean.parseBoolean(
                    Configuration.getConfigProperty(Configuration.KWEBS_LOGDEBUGMODE)
                );
                if (debugMode) {
                    Logger.setRunMode(Mode.DEBUG);
                }
            } catch (Exception e) {
                Logger.log(Severity.WARNING,
                    "Invalid debug mode: " 
                    + Configuration.getConfigProperty(Configuration.KWEBS_LOGDEBUGMODE)
                    + ", using non debug mode"
                );
            }
        }
        
        // Create server configuration folder structure if it not already
        // exists.
        createConfigurationFolder();

        // Start the management servive
        if (Configuration.hasConfigProperty(Configuration.MANAGEMENT_PORT)) {
            try {
                managementPort = Integer.parseInt(
                    Configuration.getConfigProperty(Configuration.MANAGEMENT_PORT)
                );
            } catch (Exception e) {
                Logger.log(Severity.WARNING,
                    "Invalid management port: " 
                    + Configuration.getConfigProperty(Configuration.MANAGEMENT_PORT)
                    + ", using default port " + ManagementService.DEFAULT_MANAGEMENTPORT
                );
            }
        }
        Logger.log(Severity.ALWAYS, "Starting management service on port " + managementPort);
        try {
            ManagementService.startManagement(managementPort);
            Logger.log(Severity.ALWAYS, "Management service started");            
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Management service could not be started", e);
        }
        
        // Set plugin preferences of the necessary layouter plugins
        try {
            setPluginPreferencesFromConfiguration();
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Error while initializing layouter preferences", e);
            return IApplication.EXIT_OK;
        }
        
        // Initialize server and publish service
        try {
            ServicePublisher.publish();   
            JaxWsAdapter.registerToLoggers();
            while (!stopped) {
                Thread.sleep(LOOP_TIMEOUT);
            }
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Error while initializing layout server", e);
        }

        // Unpublish service on exit if necessary
        if (ServicePublisher.isPublished()) {
            ServicePublisher.unpublish();
        }

        // Unpublish management service
        ManagementService.stopManagement();
        
        // Unregister file logging
        Logger.removeLoggerListener(fileLogging);
        
        try {
            ((RoundTripFileLogging) fileLogging).close();
        } catch (Exception e) {
            Logger.log(Severity.FAILURE, "Log file could not be closed.");
        }

        // Unregister display logging
        Logger.removeLoggerListener(displayLogging);

        return IApplication.EXIT_OK;

    }

    /**
     * {@inheritDoc}
     */
    public final void stop() {
        stopped = true;
    }

    /**
     * Shuts down the server.
     */
    public final void shutdownServer() {
        stopped = true;
    }

    /**
     * Parses the command line arguments and stores the key/value
     * pairs in a {@code HashMap}.
     *
     * @param args
     *            Array of type {@code String} containing
     *            the command line arguments
     * @return a {@code HashMap<String, String>} containing key/value pairs generated
     *         from the command line arguments
     */
    private static HashMap<String, String> parseArgs(final String[] args) {
        HashMap<String, String> result = new HashMap<String, String>();
        int index = 0;
        String key = null;
        String value = null;
        for (String arg : args) {
            if (arg.charAt(0) == '/' || arg.charAt(0) == '-') {
                arg = arg.substring(1);
            }
            key = arg.toLowerCase();
            value = null;
            index = arg.indexOf("=");
            if (index > -1) {
                key = arg.substring(0, index).toLowerCase();
                value = arg.substring(index + 1);
                if (value.startsWith("\"")
                    && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1).trim();
                }
                if (value.startsWith("'")
                    && value.endsWith("'")) {
                    value = value.substring(1, value.length() - 1).trim();
                }
            }
            result.put(key, value);
        }
        return result;
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
     * Sets the neccesary preferences from different layouter plugins in order
     * for them to function correctly.
     */
    private void setPluginPreferencesFromConfiguration() {
        String value = null;
        File file = null;
        if (!Configuration.hasConfigProperty(Configuration.GRAPHVIZ_PATH)) {
            throw new IllegalStateException(
                "Properties do not contain property for graphviz executable"
            );
        }
        if (!Configuration.hasConfigProperty(Configuration.GRAPHVIZ_TIMEOUT)) {
            throw new IllegalStateException(
                "Properties do not contain property for graphviz timeout"
            );
        }
        if (!Configuration.hasConfigProperty(Configuration.OGDF_TIMEOUT)) {
            throw new IllegalStateException(
                "Properties do not contain property for ogdf timeout"
            );
        }
        value = Configuration.getConfigProperty(Configuration.GRAPHVIZ_PATH);
        file = new File(value);
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
        value = Configuration.getConfigProperty(Configuration.GRAPHVIZ_TIMEOUT);
        Logger.log(Severity.ALWAYS, "Setting graphviz timeout: " + value);
        setPluginPreference(GRAPHVIZ_PLUGINID, GRAPHVIZ_TIMEOUTPREF, value);        
        value = Configuration.getConfigProperty(Configuration.OGDF_TIMEOUT);
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
        /*ScopedPreferenceStore store = new ScopedPreferenceStore(InstanceScope.INSTANCE, pluginid);
        System.out.println(pluginid + ", " + preferenceid + " : " + store.getString(preferenceid));
        store.setValue(preferenceid, value);
        try {
            store.save();
        } catch (Exception e) {
            Logger.log(Severity.FAILURE, "Could not save preference store: " + pluginid);
        }*/
        IEclipsePreferences store = InstanceScope.INSTANCE.getNode(pluginid);
        //System.out.println(pluginid + ", " + preferenceid + " : " + store.get(preferenceid, ""));
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
        for (String property : ApplicationHelper.CONFIGURATION_FILES) {
            String file = Configuration.getConfigProperty(property);
            if (!new File(file).exists()) {
                try {
                    Files.writeFile(file, Io.getResourceStream(PLUGIN_ID, file));
                } catch (Exception e) {
                    Logger.log(Severity.FAILURE, "Could not create config file " + file);
                }
            }
        }        
    }

    /**
     * Read the version of this plugin.
     * 
     * @return the version of this plugin
     */
    private String getVersion() {
        Version tmp = Platform.getBundle(PLUGIN_ID).getVersion();
        return tmp.getMajor() + "." + tmp.getMinor() + "." + tmp.getMicro();
    }

}
