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

import org.apache.log4j.Logger;

import de.unido.ls5.eti.sps.apis.EtiConfig;

/**
 *  This class is used to represent a user's session with the toolserver.
 *  It has a unique identifier and an own directory to store files to.
 *  Furthermore it can be "touched" to remember the timestamp of the
 *  most recent use.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class Session {
	
	private static Logger logger = Logger.getLogger(Session.class);

	private long timestamp = 0;

	private String sessionId;
	private File sessionFolder;
	
	/**
	 * Creates a new Session. It is initially touched to set creation time
	 * as most recent "use".
	 * 
	 * @param sessionId The unique identifier for this session
	 * 
	 * @param sessionFolder The folder of this session
	 * 
	 * @throws IllegalArgumentException if sessionFolder is no directory but a
	 * regular file, if the directory does not exist or if sessionFolder==null.
	 * 
	 * @throws NullPointerException if sessionFolder==null or sessionId==null
	 */
	public Session(String sessionId, File sessionFolder) {
		
		if (sessionId == null || sessionFolder == null)
			throw new NullPointerException("sessionId: " + sessionId + ", sessionFolder: " + sessionFolder);
		
		if (!sessionFolder.exists())
			throw new IllegalArgumentException("Session folder " + sessionFolder + ") does not exist, can't create session.");
		
		if (!sessionFolder.isDirectory())
			throw new IllegalArgumentException("Session folder " + sessionFolder + ") is no directory, can't create session.");
		
		timestamp = new Date().getTime();
		this.sessionId = sessionId;
		this.sessionFolder = sessionFolder;
	}
	
	
	/**
	 * Sets the timestamp of this Session to current time
	 * to prevent session timeout.
	 * 
	 * @see Date#getTime()
	 */
	public void touch() {
		timestamp = new Date().getTime();
	}


	/**
	 * Retrieve the path to the session's folder
	 * 
	 * @return the path to the session's folder
	 */
	public File getSessionFolder() {
		return sessionFolder;
	}


	/**
	 * Retrieve the session's unique identifier.
	 * 
	 * @return the sessions unique identifier
	 */
	public String getSessionId() {
		return sessionId;
	}
	
	/**
	 * Retrieves the timestamp when the Session was used most recently.
	 * 
	 * @return the timestamp of the most recent {@link #touch()}
	 */
	public long getLastTouched() {
	    return timestamp;
	}
	
	/**
	 * causes the session to recursively delete its session folder. 
	 * 
	 * @return true if the deletion fully succeeded, false if debug mode
	 * is enabled (and therefore nothing is deleted) or if the folder could not
	 * be deleted because it was not empty (meaning the recursive deletion somehow
	 * must have failed)
	 */
	public boolean delete() {
		if (EtiConfig.debugMode()) {
			logger.debug("DebugMode is enabled. Session Folder won't be deleted");
			return false;
		}
		
		// first recursively delete all files in the folder...
		File[] files = sessionFolder.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				delete(files[i]);
			}
		}		
		// ...then delete the folder itself and return success state
		return sessionFolder.delete();
	}
	
	/**
	 * Recursive deletion of a file.
	 * 
	 * @param f the file to delete. if it is a directory, the included files
	 * will be deleted first.
	 */
	private void delete(File f) {
		File[] files = f.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				delete(files[i]);
			}
		}
		f.delete();
	}
	
}
