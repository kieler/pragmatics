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
package de.unido.ls5.eti.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * This class manages the jETI file context. It keeps track where the most recently modified 
 * version of a filename can be found. and stores/retrieves/forwards them the the required
 * location if necessary.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 * 
 */
public class TransferHandler {
	
	
	/**
	 * log4j logger
	 */
	private static Logger logger = Logger.getLogger(TransferHandler.class);
	
	
	/**
	 * global switch for debug mode on/off for all transfer handler instances
	 */
	private static boolean debug = false;
	
	/**
	 * This URI is used to flag files whose newest version can be found in the
	 * local jETI context
	 */
	private static final String LOCAL_URI = "virtual://local";
	
	/**
	 *  @see  #LOCAL_URI
	 */
	private URI localURI;
	
	/**
	 * Mapping from filename in jETI context to the location where the 
	 * most recently modified version of this file can be found
	 */
	private Map<String, URI> cache;
	
	/**
	 * The environment that was used to instanciate this transfer handler
	 * it is used to store: the th itself, the connections map, 
	 */
	private Map<String, Object> executionEnvironment;
	
	/**
	 * local files in the jETI context are managed here. maps from filename
	 * in jETI context to the actual file
	 */
	private Map<String, VirtualFile> localFiles;


	/** 
	 * context key name where the {@link de.unido.ls5.eti.client.EtiConnection} 
	 * Map is stored. Only used in debug mode 
	 */
	public static final String CONTEXT_ETI_CONNECTIONS = "jETI-Connections";


	/** 
	 * Context key name where the {@link TransferHandler} is stored. 
	 */
	public static final String CONTEXT_TRANSFER_HANDLER = "jETI-TransferHandler";
	
	/**
	 * Retrieves the TransferHandler from given ExecutionEnvironment.
	 * If env does not yet contain a TransferHandler, a new one is
	 * instanciated, put into env and returned.
	 * 
	 * @param env the given execution environment
	 * 
	 * @return the transfher handler from env or a newly instanciated one
	 * 
	 * @throws ClassCastException if the environment contains an Object with 
	 * key {@link TransferHandler#CONTEXT_TRANSFER_HANDLER} that cannot be
	 * casted to TransfherHandler
	 */
	public synchronized static TransferHandler get(Map<String, Object> env) {
    	TransferHandler th = ((TransferHandler) env.get(CONTEXT_TRANSFER_HANDLER));
    	//TODO: check instanceof TransferHandler
    	if (th == null) {
    		th = new TransferHandler(env, TransferHandler.debug);
    		env.put(CONTEXT_TRANSFER_HANDLER, th);
    	}
    	return th;
    }
	
	
	/**
	 * Retrieves the EtiConnections Map from the given environment. If the the environment
	 * does not yet contain this map, a new one will be instanciated and put into it.
	 * 
	 * @return the retrieved map from the environment or an empty one that has been newly instanciated
	 */
	@SuppressWarnings("unchecked")
	private synchronized Map<URI, EtiConnection> getEtiConnections() {
		Map<URI, EtiConnection> etiConnections = ((Map<URI, EtiConnection>) this.executionEnvironment.get(CONTEXT_ETI_CONNECTIONS));
		if (etiConnections == null) {
			etiConnections = new HashMap<URI, EtiConnection>();
			logger.debug("Instanciating new Map<URI, EtiConnection> and putting it to ExecutionContext");
			// TODO: check whether connections map must really be stored to context
			this.executionEnvironment.put(CONTEXT_ETI_CONNECTIONS, etiConnections);
		}
		return etiConnections;
	}

	/**
	 * 
	 * Looks up the internal {@link LightweightExecutionEnvironment} wether there already exists a connection to the jETI toolserver 
	 * identified by uri. If a connection exists, it is returned. If not, a new one is instanciated or null is returned 
	 * (depends on createNew)
	 * 
	 * ({@link EtiConnectionFactory#createConnection(URI)} is used to create new connection)
	 * 
	 * @param uri URI of the jETI toolserver
	 * 
	 * @param createNew defines wether a new EtiConnection should be instanciated if there's
	 *  none found
	 *  
	 * @return connection to server or null, if createNew == false and no connection was found
	 * 
	 * @throws EtiLocalException if the URI is not valid for jETI connections
	 * @throws EtiRemoteException if login to server failed
	 */
	public synchronized EtiConnection getEtiConnection(URI uri, boolean createNew) throws EtiLocalException, EtiRemoteException {
		Map<URI, EtiConnection> etiConnections = getEtiConnections();
		
		if(createNew && !etiConnections.containsKey(uri)) {
			logger.debug("Creating new EtiConnection for " + uri);
			EtiConnection connection = EtiConnectionFactory.createConnection(uri);
			// FIXME: Think about user/password system...
			//connection.login(ETIPlugin.getToolserverUser(), ETIPlugin.getToolserverPassword());
			connection.login("anonymous", "secret");
			etiConnections.put(uri, connection);
			
		}
		
		return etiConnections.get(uri);
	}
	
	/**
	 * Retrieve an ETI Connection for a given List of uris following the order of the list.
	 * It first tries to find an already existing connection according to the list. if none is found,
	 * it iterates the list again trying to create new a connection object. This method will always
	 * return a valid connection or throw an Exception if it fails.
	 * @param uris
	 * @return the EtiConnection
	 * @throws EtiLocalException
	 */
	public synchronized EtiConnection getEtiConnection(List<URI> uris) throws EtiLocalException {
		EtiConnection myEti = null;
		for (URI u : uris) {
			try {
				myEti = getEtiConnection(u, false);
			} catch (EtiRemoteException e) {
				// Do nothing. RemoteExcpetion/LocalException cannot occur with createNew==false
			}
			if (myEti != null)
				break;
		}
		if (myEti == null) {
			for (URI u : uris) {
				try {
					myEti = getEtiConnection(u, true);
				}
				catch (EtiLocalException e) {
					logger.debug("Could not intanciate jETI Connection for URI '" + u + "'. Skipping.", e);
				}
				catch (EtiRemoteException e) {
					logger.debug("Could not connect to server with URI '" + u + "'. Skipping.", e);
				}
				if (myEti != null)
					break;
			}
		}
		if (myEti == null) {
			throw new EtiLocalException("Could not connect to server for any of the given URIs.");
		}
		return myEti;

	}
	
	/**
	 * Instanciates a new transfer handler. 
	 * @param env the environment that the transher handler shall use
	 * for storing internal variables
	 */
	private TransferHandler(Map<String, Object> env, boolean debug) {
		executionEnvironment = env;
		cache = new HashMap<String, URI>();
		localFiles = new HashMap<String, VirtualFile>();
		if (debug) {
			env.put("jETI-filecache", cache);
			env.put("jETI-localFiles", localFiles);
		}
		// FIXME: Proxy Settings... this should probably be handled within the EtiConnection
		// instead of the TransferHandler
		/* 
		if (ETIPlugin.isProxyEnabled()) {
			logger.debug("Starting TransferHandler with Proxy=on");
	        System.setProperty( "proxySet", "true" );
	        System.setProperty( "http.proxyHost", ETIPlugin.getProxyHost());
	        System.setProperty( "http.proxyPort", ETIPlugin.getProxyPort());
	        System.setProperty( "http.proxyUser", ETIPlugin.getProxyUser());
	        System.setProperty( "http.proxyPassword", ETIPlugin.getProxyPassword()); 
		}
		else {
			logger.debug("Starting TransferHandler with Proxy=off");
	        System.clearProperty( "proxySet");
	        System.clearProperty( "http.proxyHost");
	        System.clearProperty( "http.proxyPort");
	        System.clearProperty( "http.proxyUser");
	        System.clearProperty( "http.proxyPassword");
		}
		*/
		
		try {
			localURI = new URI(LOCAL_URI);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException("Should not happen... fix broken code!");
		}
	}
	
		
	/** put virtual file into executionEnvironment and
	 *  set cache entry for name to local URI
	 * @param vFile 
         * @param name 
        */
	public synchronized void put(VirtualFile vFile, String name) {
		//executionEnvironment.put(name, vFile);
		localFiles.put(name, vFile);
		cache.put(name, localURI);
		logger.debug("cache: Setting current location of '" + name + "' to " + localURI);
	}
	
	/**
	 * Retrieves the VirtualFile Object from the Context. If the File
	 * is still located at the Server, it will be retrieved first.
	 * 
	 * @param name
	 * @return The requested VirtualFile or null, if there is no known
	 *   location for that file
	 * @throws EtiLocalException 
	 * @throws EtiRemoteException 
	 */
	public synchronized VirtualFile get(String name) throws EtiLocalException, EtiRemoteException {
		if (cache.containsKey(name)) {
			
			URI currentLocation = cache.get(name);
			
			if (!currentLocation.equals(localURI)) {
				
				logger.debug("Eti File '" + name + "' is still at '" + currentLocation + "', but required locally... retrieving...");
				
				EtiConnection myEti = getEtiConnection(currentLocation, false);
				
				if (myEti == null)
					throw new EtiLocalException("File retrieval failed: No open connection to the jETI Toolserver,that should hold the file, found");
				
				List<String> toBeRetrieved = new ArrayList<String>();
				toBeRetrieved.add(name);
				
				List<VirtualFile> retrievedFiles = myEti.retrieve(toBeRetrieved);
				
				if (retrievedFiles.size() > 1) {
					logger.warn("Requested only one file from jETItoolserver, but more came back...");
				}
				
				for (VirtualFile vFile : retrievedFiles) {
					//executionEnvironment.put(name, vFile);
					localFiles.put(name, vFile);
				}
				cache.put(name, localURI);
				logger.debug("cache: Setting current location of '" + name + "' to " + localURI);
			}
			
			return localFiles.get(name);
			
		}
		else {
			throw new EtiLocalException("Location of " + name + " is not known, aborting...");
		}
	}
	
	public synchronized void setCurrentLocation(List<String> names, URI uri) {
		setCurrentLocation(names.toArray(new String[0]), uri);
	}
	
	/**
	 * This method can be used to tell the transfher handler, that the most recently modified
	 * version of given filenames can now be found at uri. This should only be used for newly created
	 * files in remote contexts (e.g. OutputParameters of tools).
	 * 
	 * @param names
	 * 
	 * @param uri
	 */
	public synchronized void setCurrentLocation(String[] names, URI uri) {
		for (String name : names) {
			cache.put(name, uri);
			logger.debug("cache: Setting current location of '" + name + "' to " + uri);
		}
	}
	
	public synchronized void storeToLocation(List<String> names, URI targetServer) throws EtiLocalException, EtiRemoteException {
		storeToLocation(names.toArray(new String[0]), targetServer);
	}
	/**
	 * Makes sure that the newest version of all files provided by 
	 * parameter 'names' is located at the given URI afterwards.
	 * If the newest file is already located at that server, no transfer
	 * will be made.
	 * 
	 * @param names array of filenames from the jETI context
	 * 
     * @param targetServer the URI of the server where the newest versions
     * of {@code names} are needed.
     * 
     * @throws EtiLocalException if names contains an unknown filename
     * 
	 * @throws EtiRemoteException if store or forward somehow fails
	 *
	 */
	public synchronized void storeToLocation(String[] names, URI targetServer) throws EtiLocalException, EtiRemoteException {
		
		// List of files that are currently in local environment and thus
		// need to be stored directly to the server
		List<VirtualFile> toBeStored = new ArrayList<VirtualFile>();
		
		// Files that are currently located on different jETI Toolservers
		Map<URI, List<String>> toBeForwarded = new HashMap<URI, List<String>>();
		
		for (String name : names) {
			
			URI cachedLocation = cache.get(name);
			
			if (cachedLocation == null) {
				throw new EtiLocalException("Location of " + name + " is not known, aborting...");
			}
			else if (cachedLocation.equals(targetServer)) {
				// file is already there... fine... nothing to do
				logger.debug("File " + name + " is already at " + cachedLocation + ", nothing to do");
			}
			else if (cachedLocation.equals(localURI)) {
				toBeStored.add(localFiles.get(name));
				logger.debug("File " + name + "is currently in local context, preparing to store at " + targetServer);
				//cache.put(name, targetServer);
			}
			else {
				if (! toBeForwarded.containsKey(cachedLocation)) {
					toBeForwarded.put(cachedLocation, new ArrayList<String>());
					logger.debug("New source location for forwarding found: " + cachedLocation);
				}
				toBeForwarded.get(cachedLocation).add(name);
				//cache.put(name, targetServer);
			}
		}
		
		// Get Connection to target server, instanciate new one if none present
		EtiConnection connectionToTarget = getEtiConnection(targetServer, true);
		assert connectionToTarget != null;
		
		// Storing collected files from Local Context to target jETI Toolserver
		if (toBeStored.size() != 0) { 
			logger.debug("Storing " + toBeStored.size() + " Files to " + targetServer);
			logger.debug("Session ID before storing: " + connectionToTarget.getSession());
			connectionToTarget.store(toBeStored);
			logger.debug("Session ID after storing: " + connectionToTarget.getSession());
		}
		
		// Forwarding collected files from other Servers to target jETI Toolserver
		for (URI sourceServer : toBeForwarded.keySet()) {
			EtiConnection connectionToSource = getEtiConnection(sourceServer, false);
			if (connectionToSource == null) {
				/* we do not want to
				 * instanciate a new connection if none is present. Forwarding
				 * requires an alredy open Session, but there always should be one
				 * because the cached file must have gotten there somehow...
				 */
				throw new EtiLocalException("Forwarding failed: No open connection to originating jETI Toolserver found");
			}
			logger.debug("Forwarding " + toBeForwarded.get(sourceServer) + " from " + connectionToSource.getServerURI() + " to " + connectionToTarget.getServerURI());
			connectionToSource.forward(toBeForwarded.get(sourceServer), connectionToTarget);
		}
		
	}
	
	public void closeAllSessions() {
		Collection<EtiConnection> etiConnections = getEtiConnections().values();
		for (EtiConnection etiConnection : etiConnections) {
			try {
				logger.debug("Ending EtiSession: " + etiConnection.getServerURI().toString());
				etiConnection.endSession();
			} catch (Exception e) {
				// At least I tried... but okay, let the server take care with its session timeouter...
				logger.debug("Ending EtiSession failed", e);
			}
		}
	}
	
	
	/**
	 * Generates a String representation of this transfher handler that contains
	 * debug information such as number of known files and number of files in local context.
	 * 
	 * @return the string representation of this transfer handler
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TH (")
			.append(cache.size())
			.append(" known files, ")
			.append(localFiles.size())
			.append(" in local context)");
		return sb.toString();
	}


	public static boolean isDebug() {
		return debug;
	}


	public static void setDebug(boolean debug) {
		TransferHandler.debug = debug;
	}
	
	

}
