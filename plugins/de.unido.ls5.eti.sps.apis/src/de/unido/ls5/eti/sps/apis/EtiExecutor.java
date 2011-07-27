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
 * Created on 04.10.2005
 *
 */
package de.unido.ls5.eti.sps.apis;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;


/**
 * API of the toolserver executor. The executor is the core contact point for
 * all connectors, which only need to forward their requests to it. The
 * currently active instance can be retrieved by {@link EtiConfig#getExecutor()}.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public interface EtiExecutor {
	
	
    /** 
     * Wrapper for SOAP, which sends the ZIP file in base64 encoding.
     * 
     * @param b64file the ZIP file that contains the files to store in base64
     * encoding.
     * 
     * @param sessionId the id of the active session or "0" if no session is
     * open yet.
     * 
     * @return the session ID as indicator that everything went well. if sessionId
     * was "0" before, the newly created sessionId will be returned.
     * 
     * @see #store(byte[], String)
     * 
     * @throws EtiServerException if something goes wrong
     */
	@Deprecated
    public String storeBase64(String b64file, String sessionId) throws EtiServerException;
    
    
	/**
	 * Store the content of a ZIP archive to a user's session folder. If no
	 * Session is known so far, "0" can be used to create a new one.
     * 
     * @param zipfile
     *    The ZIP archive as byte array that contains the files that shall
     *    be stored to the session folder.
     *    
     * @param sessionId id of the session. may be "0" to indicate that a new
     * session shall be created.
     * 	
     * @return the session ID as indicator that everything went well. if sessionId
     * was "0" before, the newly created sessionId will be returned.
     * 
     * @throws EtiServerException may be thrown if something goes wrong.
     */
	@Deprecated
    public String store(byte[] zipfile, String sessionId)
    throws EtiServerException; 
  
    /**
     * Wrapper for SOAP to get the retrieved files in base64 encoding.
     * 
     * @param filelist list of files to retrieve
     * @param sessionId the id of the session to retrieve the files from
     * @return ZIP archive containing the requested files, encoded in base64.
     * @throws EtiServerException may be thrown if something goes wrong.
     */
	@Deprecated
    public String retrieveBase64(String filelist, String sessionId) throws EtiServerException;
    
    /**
     * Fordert den Server auf, die in filelist übergebenen Dateinamen (relativ
     * zum Session Pfad) in eine ZIP Datei zu packen und an den Client zurück
     * zu schicken. 
     * 
     * @param filelist
     *    Die liste an Dateien, relativ zum temporären Session-Pfad. Einzelne
     *    Dateien werden mit : getrennt, z.B.: "output/bild1.jpg:output/bild2.jpg"
     *    Wenn Dateien nicht vorhanden sind, wird eine Exception geworfen.
     * @param sessionId
     *    Die Session, aus der die Dateien ausgelesen werden sollen. Wenn die
     *    Session ungültig ist, wirft der Server eine Exception.
     * @return
     *    Gibt die ZIP Datei als Base64 kodiert zurück.
     *    
     * @throws EtiServerException may be thrown if something goes wrong.
     */
	@Deprecated
    public byte[] retrieve(String filelist, String sessionId)
    throws EtiServerException;
    
    
    public InputStream retrieve(Set<String> filenames, String sessionId) throws EtiServerException;
    public String store(ZipInputStream zipfile, String sessionId) throws EtiServerException;
    public String forward(String sessionId, String remoteSessionId, URI forwardServer, Set<String> filenames) throws EtiServerException;
    public String exec(String toolname, Map<String, String> parameters, String sessionId) throws EtiServerException;
    
    
    /**
     * Forwards a set of files from the user's session directory to another 
     * toolserver. This prevents the files from being transfered to the user
     * and then back to the toolserver.
     * 
     * @param sessionId the id of the user's session on this toolserver
     * 
     * @param remoteSessionId the id of the user's session on the toolserver where
     * the files shall be forwarded to.
     * 
     * @param serverURI the URI of the toolserver where the files shall be forwarded
     * 
     * @param fileList colon-seperated list of files that shall be forwarded
     * @return the sessionId to indicate that everything went well
     * 
     * @throws EtiServerException if something goes wrong.
     */
    @Deprecated
     public String forward(String sessionId, String remoteSessionId, String serverURI, String fileList)
     throws EtiServerException;
    
  
  /**
   * Fordert den Server auf, die Session zu schließen und alle noch gespeicherten
   * Dateien zu löschen. ("Nice" Funktion für den Client)
   * 
   * @param session
   *    Die Session, die gelöscht werden soll. Wenn die Session nicht existiert,
   *    wird eine Exception geworfen.
   * @return
   *    gibt 0 zurück, wenn alles geklappt hat.
   *    
   * @throws EtiServerException if the session did not exist or something else went
   * wrong.
   */
  public String endsession(String session)
  throws EtiServerException;
  	
  /**
   * Führt das in tool gegebene tool mit den parametern aus parameterlist aus
   * 
   * @param tool 
   *    der identifier des tools, das ausgeführt werden soll
   * @param parameterlist
   *    parameterliste, abhängig vom tool
   * @param sessionId
   *    die session, unter der das tool ausgeführt werden soll. wenn die Session
   *    nicht existiert, wird eine Exception geworfen.
   * @return
   *    gibt die session id zurück, wenn alles geklappt hat.
   *    
   * @throws EtiServerException if something goes wrong
   */
  @Deprecated
  public String exec(String tool, String parameterlist, String sessionId) 
  throws EtiServerException;
  
  /**
   * Checks wether the given session is (still) valid.
   * validating a session "touches" it, meaning that the most recent use is set to "now".
   * 
   * @param session
   * 		the session id to validate. Use "0" to create a new session.
   * @return
   * 		the session id to use. This is the old session id, if it is still valid, otherwise it is a new session id.
   * @throws EtiServerException
   * 		if the server has configuration errors.
   */
  public boolean validateSession(String session) throws EtiServerException;
  
  
  /**
   * Creates a new session for the given user.
   * Note: currently all usernames/passwords are accepted, but maybe in future.... 
   * 
   * @param username
   * 	the account name of the user who creates the session
   * @param password
   * 	the password of the user who creates the session
   * @return
   * 	the ID of the newly created session
   * @throws EtiServerException
   * 	
   */
  public String login(String username, String password) throws EtiServerException;
  
}