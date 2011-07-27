/**
 * Java Electronic Tool Integration - jETI
 * Copyright (C) 2004-2011 Chair for Programming Systems, TU Dortmund
 *
 * This file is part of jETI.
 *
 * jETI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * jETI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with jETI. If not, see <http://www.gnu.org/licenses/>.
 */
package de.unido.ls5.eti.sps.apis;

import java.io.File;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This class provides access to the configuration of the toolserver, so that
 * supporting projects may implement addons to the sps without having a
 * dependency to the sps core classes.
 * 
 * When the SPS is started, it will initialize the static fields of this
 * class.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class EtiConfig {
	
	private static EtiExecutor etiExecutor;
	private static Properties properties;
	
	private static Logger logger = Logger.getLogger(EtiConfig.class);
	
	private static SortedSet<String> uris;
	
	/*
	 * fields that are infered from the properties
	 */
	private static File iconDirectory;
	private static File toolDescriptor;
	private static Boolean debugMode;
	private static Boolean readOnlyMode;
	private static String toolProviderId;
	private static String webUsersFile;

	/**
	 * Retrieve the currently active executor.
	 * @return the currently active executor
	 */
	public static EtiExecutor getExecutor() {
		return etiExecutor;
	}
	
	/**
	 * Retrieve the startup properties (usually ./conf/jeti.properties) of the 
	 * toolserver. Note that this should only be used if no dedicated wrapper
	 * method (such as {@link #getToolDescriptor()} or {@link #getToolProviderId()})
	 * exists.
	 * 
	 * @return the server's startup properties
	 */
	public static Properties getProperties() {
		return properties;
	}
	
	/**
	 * Sets the currently active instance of an executor.
	 * @param executor the currently active executor
	 */
	public static void setExecutor(EtiExecutor executor) {
		EtiConfig.etiExecutor = executor;
	}
	
	/**
	 * Sets the startup properties. Note that this will cause the cache of
	 * {@link #getToolDescriptor()}, {@link #debugMode()}, {@link #getToolProviderId()} 
	 * and {@link #getIconDirectory()} to be reset.
	 * 
	 * Furthermore, the Set of URIs that is obtained by {@link #getServerUris()} will
	 * be reinstanciated with a new Comparator using the provided properties.
	 * 
	 * @param properties the properties to set
	 * 
	 */
	public static void setProperties(Properties properties) {
		
		logger.info("Setting new Properties");
		
		EtiConfig.properties = properties;
		/*
		 * infered fields need to be reset
		 */
		iconDirectory = null;
		toolDescriptor = null;
		debugMode = null;
		toolProviderId = null;
		webUsersFile = null;
		
		// preserve old URIs, but with possibly new order
		if (uris != null) {
			SortedSet<String> olduris = uris;
			uris = new TreeSet<String>(new UriSchemeComparator(properties));
			uris.addAll(olduris);
		}
	}
	
	/**
	 * Retrieve all URIs the server is listening to. These URIs must have
	 * been provided using {@link #addServerUri(String)}.
	 * 
	 * @return sorted set of uris or null, if no uri has been added so far.
	 */
	public static SortedSet<String> getServerUris() {
		return uris;
	}
	
	/**
	 * Adds a URI to the set of URIs the server is listening to.
	 * If this method is called for the first time, a new {@link TreeSet} is
	 * instanciated with {@link UriSchemeComparator}. You must have set valid
	 * properties with {@link #setProperties(Properties)} before.
	 * 
	 * @param uri the URI to add
	 * 
	 * @throws NullPointerException if properties have not yet been set using {@link #setProperties(Properties)}.
	 */
	public static void addServerUri(String uri) {
		if (uris == null) {
			uris = new TreeSet<String>(new UriSchemeComparator(properties));
		}
		uris.add(uri);
	}
	
	/**
	 * Retrieves the directory where the server stores icon files (SVG, PNG) 
	 * for the configured tools. As a naming convention, an icon file should
	 * be ${iconDirectory}/${toolName}.svg or ${iconDirectory}/${toolName}.svg.
	 * The directory location is taken from property "jeti.icondir".
	 * 
	 * @return the icon directory or null, if the property is not set or points
	 * to a non-directory file.
	 * 
	 * @throws NullPointerException if properties have not yet been set using {@link #setProperties(Properties)}.
	 */
	public static File getIconDirectory() {
		if (iconDirectory == null) {
			String s_icondir = properties.getProperty("jeti.icondir");
			if (s_icondir == null) {
				logger.debug("jeti.icondir not set in jeti.properties file");
			}
			File f_icondir = new File(s_icondir);
			if (!f_icondir.exists()) {
				logger.error("jeti.icondir in jeti.properties points to a non existing file ("+ s_icondir + ")");
			} else if (!f_icondir.isDirectory()) {
				logger.error("jeti.icondir in jeti.properties points to a file that is not a directory ("+ s_icondir + ")");
			} else {
				EtiConfig.iconDirectory = f_icondir;
				logger.info("Icon Directory: " + f_icondir.getAbsolutePath());
			}
		}
		return iconDirectory;
	}
	
	
	/**
	 * Set the logging level of the Root Logger to level
	 * @param level
	 */
	public static void setRootLoglevel(Level level) {
		Logger.getRootLogger().setLevel(level);
		Logger.getRootLogger().log(level, "Setting rootLogger to log level " + level);
	}
	
	/**
	 * retrieve the log level of the root logger
	 * @return
	 */
	public static Level getRootLoglevel() {
		return Logger.getRootLogger().getLevel();
	}
	
	/**
	 * retrieve the log level of the eti logger
	 * @return
	 */
	public static Level getEtiLoglevel() {
		Logger etiLogger = Logger.getLogger("de.unido.ls5.eti");
		return etiLogger.getLevel();
	}
	
	/**
	 * Set the logging level of the Logger 'de.unido.ls5.eti' to level
	 * @param level
	 */
	public static void setEtiLoglevel(Level level) {
		Logger etiLogger = Logger.getLogger("de.unido.ls5.eti");
		etiLogger.setLevel(level);
		etiLogger.log(level, "Setting logger of Eti classes to log level " + level);
	}
	
	/**
	 * retrieve the log level of the runtime unix logger
	 * @return
	 */
	public static Level getRuntimeUnixLoglevel() {
		Logger etiLogger = Logger.getLogger("de.unido.ls5.eti.toolserver.RuntimeUnix");
		return etiLogger.getLevel();
	}
	
	/**
	 * Set the logging level of the Logger 'de.unido.ls5.eti.toolserver.RuntimeUnix' 
	 * to level
	 * 
	 * @param level
	 */
	public static void setRuntimeUnixLoglevel(Level level) {
		Logger runtimeUnixLogger = Logger.getLogger("de.unido.ls5.eti.toolserver.RuntimeUnix");
		runtimeUnixLogger.setLevel(level);
		runtimeUnixLogger.log(level, "Setting logger of RuntimeUnix to log level " + level);
	}
	
	/**
	 * Retrieve the location of the Tool Descriptor File (tools.xml). The location
	 * is taken from "jeti.toolxml" property.
	 * 
	 * @return the tool.xml file or null if it was not properly configured (note
	 * that this is really a bad configuration problem)
	 * 
	 * @throws NullPointerException if properties have not yet been set using {@link #setProperties(Properties)}.
	 */
	public static File getToolDescriptor() {
		if (toolDescriptor == null) {
			String toolDescriptorFilename = properties.getProperty("jeti.toolxml");
			if (toolDescriptorFilename == null) {
				logger.fatal("jeti.toolxml not set in jeti.properties file");
			}
			File toolDescriptorFile = new File(toolDescriptorFilename);
			if (!toolDescriptorFile.exists()) {
				EtiConfig.toolDescriptor = toolDescriptorFile;
				logger.warn("jeti.toolxml in jeti.properties points to a non existing file ("+ toolDescriptorFilename+ "). This is not necessarily bad, but you better be warned.");
			} else {
				EtiConfig.toolDescriptor = toolDescriptorFile;
				logger.info("Tool Descriptor: " + toolDescriptorFile.getAbsolutePath());
			}
		}
		return toolDescriptor;
	}
	
	/**
	 * Retrieve the location of the authentication file for web access. It
	 * is taken from "jeti.configurator.users" property.
	 * 
	 * @return the users file or null if it was not properly configured
	 * 
	 * @throws NullPointerException if properties have not yet been set using {@link #setProperties(Properties)}.
	 */
	public static String getWebUsersFile() {
		if (webUsersFile == null) {
			String property = properties.getProperty("jeti.configurator.users");
			if (property == null) {
				logger.warn("the Web Users file (typically ./config/users.properties) not set in jeti.properties file");
				return null;
			}
			webUsersFile = property;
		}
		return webUsersFile;
	}
	
	
	
	/**
	 * Check wether the server is currently running in debug mode. This means
	 * that the session folders will not be deleted with the sessions. The
	 * information is taken from the "jeti.debug" property. If the property
	 * is not specified, debug mode is assumed to be off.
	 * 
	 * @return true if server runs in debug mode
	 * 
	 * @throws NullPointerException if properties have not yet been set using {@link #setProperties(Properties)}.
	 */
	public static boolean debugMode() {
		if (debugMode == null) {
			String property = properties.getProperty("jeti.debug");
			if (property == null) {
				logger.info("The property 'jeti.debug' is not set in properties file. Falling back to value 'false'");
				debugMode = Boolean.FALSE;
			}
			else if ("true".equals(property)) {
				logger.info("Debug Mode is ENABLED due to jeti.debug in properties file");
				debugMode = Boolean.TRUE;
			} 
			else if ( "false".equals(property)) {
				logger.info("Debug Mode is DISABLED due to jeti.debug in properties file");
				debugMode = Boolean.FALSE;
			}
			else {
				logger.info("The property 'jeti.debug' is not properly set in jeti.properties. it must either have value 'true' or 'false'. Falling back to value 'false'");
				debugMode = Boolean.FALSE;
			}
		}
		return debugMode;
	}
	
	
	/**
	 * retrieves the tool provider id from the properties that must have
	 * been set with {@link #setProperties(Properties)} before. The used
	 * key is "jeti.provider.id"
	 * 
	 * @return the tool provider id
	 * 
	 * @throws NullPointerException if properties have not yet been set using {@link #setProperties(Properties)}.
	 */
	public static String getToolProviderId() {
		if (toolProviderId == null) {
			toolProviderId = properties.getProperty("jeti.provider.id");
			if (toolProviderId == null) {
				logger.warn("The property 'jeti.provider.id' is not set in jeti.properties. Falling back to value 'default'");
				toolProviderId = "default";
			}
		}
		return toolProviderId;
	}
	
	/**
	 * Check wether the server is running in "read only" mode, which means that no user can make any changes to the
	 * tools.xml. This is meant to be a quickly implemented security feature until true user priviliges are implemented.
	 * 
	 * @return true if the server is running in read only mode
	 */
	public static boolean isReadOnlyMode() {
		if (readOnlyMode == null) {
			String property = properties.getProperty("jeti.readOnly");
			if (property == null) {
				logger.info("The property 'jeti.readOnly' is not set in properties file. Falling back to default value 'false'");
				readOnlyMode = Boolean.FALSE;
			}
			else if ("true".equals(property)) {
				logger.warn("ReadOnly mode is ENABLED due to jeti.readOnly in properties file. You won't be able to save changes to the tool descriptor file.");
				readOnlyMode = Boolean.TRUE;
			} 
			else if ( "false".equals(property)) {
				logger.info("ReadOnly mode is DISABLED due to jeti.readOnly in properties file");
				readOnlyMode = Boolean.FALSE;
			}
			else {
				logger.warn("The property 'jeti.readOnly' is not properly set in jeti.properties. it must either have value 'true' or 'false'. Falling back to default value 'false'");
				readOnlyMode = Boolean.FALSE;
			}
		}
		return readOnlyMode;
		
	}
	
	
	
}
