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
/*
 * Created on 12.05.2005
 *
 */
package de.unido.ls5.eti.toolserver;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import de.unido.ls5.eti.sps.apis.ErrorCodeConstants;
import de.unido.ls5.eti.sps.apis.EtiConfig;
import de.unido.ls5.eti.sps.apis.EtiServerException;

/**
 * This class manages the sessions at the jETI toolserver. it provides
 * creation, deletion and timeout of the sessions. Each session is identified
 * by unique String identifier. A new session can be opened by passing "0" to
 * {@link #getSession(String)}.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class SessionManager {

	private Map <String, Session> sessions = null;
	private String sessionFolder;
	
	private long timeout = 30*60*1000;
	
	
	private static SessionManager instance = null;
    
	
	/**
	 * Get singleton instance of SessionManager
	 * 
	 * @return the singleton instance.
	 * @throws EtiServerException if SessionManager could not be 
	 * instantiated because of wrongly configured sessionfolderpath in
	 * jeti.properties (error code {@link ErrorCodeConstants#SERVER_CONFIG_ERROR})
	 * 
	 */
	public synchronized static SessionManager getInstance()
	throws EtiServerException {
		if (instance == null) {
			instance = new SessionManager();	
		}
		return instance;
	}
		
	private SessionManager() throws EtiServerException {
		
		Logger logger = Logger.getLogger(SessionManager.class);
		
		sessions = new HashMap<String, Session>();
		
    	try {
    		// TODO: move getSessionFolder and getSessionTimeout to EtiConfig
    		String sessionFolderProperty = EtiConfig.getProperties().getProperty("jeti.sessions.folder");
			if (sessionFolderProperty != null)
    			sessionFolder = sessionFolderProperty;
    		else {
    			logger.warn("Session folder property not set. Fallback to 'var/sessions/'");
    			sessionFolder = "var/sessions/";
    		}
			logger.info("Using directory for sessions: " + sessionFolder);
			
			String timeoutProperty = EtiConfig.getProperties().getProperty("jeti.sessions.timeout");
			if (timeoutProperty != null)
				timeout = new Long(timeoutProperty).longValue();
			else {
				logger.warn("Session timeout not specified in properties. Fallback to 5 min.");
				timeout = 300000;
			}
			logger.info("Timeout for sessions: " + timeout + " milliseconds");
    	}
    	catch (Exception e) {
    		logger.fatal("Unrecoverable Error in config. Could not instanciate SessionManager.", e);
    		throw new EtiServerException("Internal Server Error", ErrorCodeConstants.SERVER_CONFIG_ERROR);
    	}
		
		
	}
	
	/**
	 * Gets the session object with the specified id. If id="0", a new
	 * session will be created.
	 * 
	 * @param id the id of the session to look up
	 * @return the looked up session
	 * @throws EtiServerException
	 * 	if specified session String is not known (or timed out)
	 * 	(error code: {@link ErrorCodeConstants#ILLEGAL_SESSION}
	 */
	public Session getSession(String id)
	throws EtiServerException {
		
		synchronized (sessions) {
			Logger logger = Logger.getLogger(SessionManager.class);

			Session session = null;
			logger.info ("Looking up session " + id);
			if (sessions.containsKey(id)) {
				session = sessions.get(id);
				logger.info ("Found session " + id);

			} else if (id.equals("0")) {
				logger.info ("Creating new session");
				String timestamp = String.valueOf(new Date().getTime());
				boolean sessionCreated = false;
				Random random = new Random();
				String sid = "";
				// Verhindert zufaellig gleiche Session IDs
				while (!sessionCreated) {
					int randomInt = random.nextInt(); 
					if (randomInt < 0) randomInt = - randomInt;        		
					sid = String.valueOf(randomInt) + "-" + timestamp;
					sessionCreated = (!sessions.containsKey(sid));
				} 

				File newSessionFolder = new File(sessionFolder, sid);
				newSessionFolder.mkdirs();
				session = new Session(sid, newSessionFolder);
				logger.info ("Created session " + sid);
				sessions.put(sid, session);


			} else throw new EtiServerException("Session with ID " + id + " does not exist or is timed out", ErrorCodeConstants.ILLEGAL_SESSION);

			return session;
		}
		
	}
	
	/**
	 * ends the session with the specified ID and deletes the session's folder.
	 * 
	 * @param sessionId the id of the session to end
	 * 
	 * @throws EtiServerException if the session that shall be deleted
	 * is not found (error code {@link ErrorCodeConstants#ILLEGAL_SESSION}
	 * 
	 * @throws EtiServerException if the session folder of the session that
	 * shall be deleted does not have the conigured session path as prefix.
	 * usually this should not happen, but deletion is aborted to make sure
	 * nothing unwanted is deleted, because the session's folder is recursively
	 * removed (error code {@link ErrorCodeConstants#SERVER_CONFIG_ERROR}.
	 */
	public void endSession(String sessionId)
	throws EtiServerException {
		
		synchronized (sessions) {
			Logger logger = Logger.getLogger(SessionManager.class);

			if (sessions.containsKey(sessionId)) {
				Session s = sessions.get(sessionId);
				/* check if session folder really begins with eti session folder
				 * so that not accidentally anything else can be deleted
				 */
				if (! s.getSessionFolder().getAbsolutePath().startsWith(new File(sessionFolder).getAbsolutePath())) {
					throw new EtiServerException("Session folder of to-be-deleted session does not seem to be in configured session folder. aborting deletion (just in case).", ErrorCodeConstants.SERVER_CONFIG_ERROR);
				}
				if (s.delete()) {
					logger.info("successfully deleted everything in " + s.getSessionFolder());
				}
				sessions.remove(sessionId);
				logger.info("deleted session with id " + sessionId);

			}
			else throw new EtiServerException("Session with ID " + sessionId + " does not exist or is timed out", ErrorCodeConstants.ILLEGAL_SESSION);
		}
		
	}

    /**
     *  Deletes all sessions that have not been touched for too long. 
     *  How long this is is determined by the "jeti.sessions.timeout" property
     *  in jeti.properties
     */
	public void endTimedOutSessions() {
		synchronized (sessions) {
			Logger logger = Logger.getLogger(SessionManager.class);
			long now = new Date().getTime();

			Iterator<String> i = sessions.keySet().iterator();
			while (i.hasNext()) {

				Object key = i.next();

				logger.debug("Checking Session with id=" + key.toString());

				Session s = sessions.get(key);

				if (now > (s.getLastTouched() + timeout)) {
					logger.info("Deleting session with id=" + key.toString() + " due to timeout...");
					if (! s.getSessionFolder().getAbsolutePath().startsWith(new File(sessionFolder).getAbsolutePath())) {
						logger.fatal("Server Error. Session's actual session folder does not seem to have configured " + 
								"session folder as prefix. configuredFolder=" + new File(sessionFolder).getAbsolutePath() + 
								", sessionFolder=" + s.getSessionFolder().getAbsolutePath());
					}
					else {
						if ((!EtiConfig.debugMode()) && s.delete()) {
							i.remove();
						}
						else if (EtiConfig.debugMode()) {
							i.remove();
						}
						else {
							logger.error("could not delete directory of session with id=" + key);
						}
						logger.info("... done");
					}
				}    
			}
		}
    }
	

}
