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
package de.unido.ls5.eti.toolserver;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import de.unido.ls5.eti.client.EtiConnection;
import de.unido.ls5.eti.client.EtiConnectionFactory;
import de.unido.ls5.eti.client.EtiLocalException;
import de.unido.ls5.eti.client.EtiRemoteException;
import de.unido.ls5.eti.client.FileVirtualFile;
import de.unido.ls5.eti.client.VirtualFile;
import de.unido.ls5.eti.common.Parameter;
import de.unido.ls5.eti.common.ParameterArray;
import de.unido.ls5.eti.common.ParameterElement;
import de.unido.ls5.eti.common.ParameterUnion;
import de.unido.ls5.eti.common.Tool;
import de.unido.ls5.eti.common.ToolXmlHelper;
import de.unido.ls5.eti.sps.apis.ErrorCodeConstants;
import de.unido.ls5.eti.sps.apis.EtiConfig;
import de.unido.ls5.eti.sps.apis.EtiExecutor;
import de.unido.ls5.eti.sps.apis.EtiServerException;

import static de.unido.ls5.eti.sps.apis.ErrorCodeConstants.*;

/**
 * 
 * Default implementation of {@link EtiExecutor}. Look also there for
 * API documentation.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class EtiExecutorImpl implements EtiExecutor {
	
	private Logger logger = Logger.getLogger(EtiExecutorImpl.class);
	
    /** 
     * Wrapper für SOAP, welches das ZIP File als Base64 kodiert sendet.
     * 
     * @param b64file
     * @param sessionId
     * @return
     * @throws EtiServerException if something goes wrong
     */
    public String storeBase64(String b64file, String sessionId) throws EtiServerException {
        return store(Base64.decodeBase64(b64file.getBytes()), sessionId);
    }
    
    
	/**
     * Sendet eine mit ZIP gepackte Datei an den Server. Die in dem ZIP enthaltenen
     * Dateien werden dann im Session Verzeichnis (/tmp/eti/[session_id]/)
     * gespeichert.
     * 
     * @param zipfile
     *    Die ZIP Datei, die an den Server gesendet wird
     * @param sessionId
     *    Wenn man schon eine Session am Server hat, dann kann hier durch angabe
     *    der Session ID Dateien in die gleiche Session speichern. Wenn die 
     *    Session ungültig ist (Time Out oder "" z.B) erstellt der Server eine 
     *    neue Session.
     * @return
     *    liefert immer die Session ID zurück. Bei zuvor gesendeter Session wird
     *    diese, falls noch gültig, auch wieder zurückgeschickt.
     *    
     *  @throws EtiServerException if something goes wrong
     */
       
    public String store(byte[] zipfile, String sessionId)
    throws EtiServerException {
    	try {
            ByteArrayInputStream bis = new ByteArrayInputStream(zipfile);
            ZipInputStream zis = new ZipInputStream(bis);
            String returnSid = store(zis, sessionId);
            bis.close();
            return returnSid;
    	}
    	catch(EtiServerException e) {
    		throw e;
    	}
        catch (Exception e) {
            throw new EtiServerException(e.getMessage(), OTHER_ERROR);
        }
    }
    
    public String store(ZipInputStream zipfile, String sessionId) throws EtiServerException {
		Session s = getSession(sessionId);    	
        ZipEntry ze = null;
        final int BUFFER = 2048;
        BufferedOutputStream dest = null;
        logger.info("Storing file(s) to folder " + s.getSessionFolder());  
        try  {

            while ((ze = zipfile.getNextEntry()) != null) {
                int count;
                byte data[] = new byte[BUFFER];
                
                // Erstellt ggf. im ZipEntry vorhandene relative Verzeichnisse
                String entryname = ze.getName();
                int index = entryname.lastIndexOf("/");
                if (index >= 0) {
                	File tmpDirIdentifier = new File(s.getSessionFolder(), entryname.substring(0,index));
                	tmpDirIdentifier.mkdirs();                	
                }                
                FileOutputStream fos = new FileOutputStream(new File(s.getSessionFolder(), ze.getName()));
                logger.info("store: '" + s.getSessionFolder() + "/" + ze.getName() + "'");
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zipfile.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
                fos.close();
            }
            zipfile.close();
        }
        catch (Exception e) {
            throw new EtiServerException(e.getMessage(), OTHER_ERROR);
        }
        
        logger.info("Returning Session String: " + s.getSessionId());
        return s.getSessionId();
        
    	
    }
  
    public String retrieveBase64(String filelist, String sessionId) throws EtiServerException {
        return new String(Base64.encodeBase64(retrieve(filelist, sessionId)));
    }
    
    
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
     */
    @Deprecated
    public byte[] retrieve(String filelist, String sessionId)
    throws EtiServerException {
    	Session s = getSession(sessionId);
        try {
            int read = 0;
            ByteArrayOutputStream bos = null;
            byte [] data = new byte[1024];
            
            bos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(bos);
            zos.setLevel(9);
            
            for (VirtualFile file : namesToFiles(parseFileSet(filelist), s)) {
                ZipEntry entry = new ZipEntry(file.getFilename());
                InputStream is = file.getInputStream();
                logger.info("retrieve:  '" + s.getSessionFolder() + "/" + file.getFilename() + "'");
                zos.putNextEntry(entry);
                while ((read = is.read(data, 0, 1024)) != -1)
                    zos.write(data,0,read);
                zos.closeEntry();
                is.close();            
			}
            zos.close();
            bos.close();
            return bos.toByteArray();
            
        }
        catch (Exception e) {
            throw new EtiServerException(e.getMessage(), OTHER_ERROR);
        }
    }
    
	public InputStream retrieve(Set<String> filenames, String sessionId) throws EtiServerException {
    	Session s = getSession(sessionId);
        try {
            int read = 0;
            ByteArrayOutputStream bos = null;
            byte [] data = new byte[1024];
            bos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(bos);
            zos.setLevel(9);
            for (VirtualFile file : namesToFiles(filenames, s)) {
                ZipEntry entry = new ZipEntry(file.getFilename());
                InputStream is = file.getInputStream();
                logger.info("retrieve:  '" + s.getSessionFolder() + "/" + file.getFilename() + "'");
                zos.putNextEntry(entry);
                while ((read = is.read(data, 0, 1024)) != -1)
                    zos.write(data,0,read);
                zos.closeEntry();
                is.close();            
			}
            zos.close();
            bos.close();
            return new ByteArrayInputStream(bos.toByteArray());
        }
        catch (Exception e) {
            throw new EtiServerException(e.getMessage(),e,OTHER_ERROR);
        }
	}
    
	public String forward(String sessionId, String remoteSessionId, URI forwardServer, Set<String> filenames) throws EtiServerException {
	   Session s = getSession(sessionId);
	   assert s != null;
	   try {
		   EtiConnection myEti = EtiConnectionFactory.createConnection(forwardServer, remoteSessionId);
		   logger.info("forwarding " + filenames + " to " + myEti.getServerURI());
		   logger.debug("Session ID before storing: " + myEti.getSession());
		   myEti.store(namesToFiles(filenames, s));
		   logger.debug("Session ID after storing: " + myEti.getSession());
		   logger.info ("done forwarding");
		   //FIXME: check if remoteSessionId has changed. if so, throw exception
	   }
	   catch (EtiRemoteException e) {
		   throw new EtiServerException("Forwarding failed. Error " + e.getErrorId() + ": '" + e.getMessage() + "'", e, FORWARD_FAILED);
	   }
	   catch (EtiLocalException e) {
		   throw new EtiServerException("Forwarding failed:  '" + e.getMessage() + "'", e, FORWARD_FAILED);
	   }
	   catch (Throwable e) {
		   throw new EtiServerException(e.getMessage(), e, OTHER_ERROR);
	   }
	   return s.getSessionId();
	}
	
	@Deprecated
	public String forward(String sessionId, String remoteSessionId, String serverURI, String files) throws EtiServerException {
		Set<String> filelist = parseFileSet(files);
		URI forwardServer;
		try {
			forwardServer = new URI(serverURI);
		} catch (URISyntaxException e) {
			throw new EtiServerException(e.getMessage(), e, OTHER_ERROR);
		}
		logger.debug("parsed filelist, " + filelist.size() + " elements found: " + filelist);
		return forward(sessionId, remoteSessionId, forwardServer, filelist);
   }
   
   /**
    * parses a set of names that is seperated by colons ("filename1:filename2:....:filenameN");
    * @param fileset
    * @return
    */
   private Set<String> parseFileSet(String fileset) {
	   Set<String> returnSet;
	   if ("".equals(fileset))
		   returnSet = new HashSet<String>(0);
	   else
		   returnSet = new HashSet(Arrays.asList(fileset.split(":")));
	   return returnSet;
   }
  
  /**
   * Fordert den Server auf, die Session zu schließen und alle noch gespeicherten
   * Dateien zu löschen. ("Nice" Funktion für den Client)
   * 
   * @param session
   *    Die Session, die gelöscht werden soll. Wenn die Session nicht existiert,
   *    wird eine Exception geworfen.
   * @return
   *    gibt die alte sessionId wieder zurück, wenn alles geklappt hat.
   */
   public String endsession(String session)
   throws EtiServerException { 
	   SessionManager sm = SessionManager.getInstance();
	   sm.endSession(session);
	   return session;	
   }
  
	public String exec(String tool, Map<String, String> parameters, String sessionId) throws EtiServerException {
		
		Session s = getSession(sessionId);

		Map<String, Tool> tools = null;
		try {
			tools = ToolXmlHelper.parseToolXML(EtiConfig.getToolDescriptor());
		}
		catch (Exception e) {
			logger.fatal("Tool file configuration error", e);
			throw new EtiServerException("jETI Server configuration error (" + e.getMessage() + ")", e, SERVER_CONFIG_ERROR);      	
		}

		try {

			/* Testet, ob das angeforderte Tool nicht auf dem Server installiert ist */
			if (!tools.containsKey(tool)) {
				throw new EtiServerException("Tool " + tool + " does not exist on this jETI Server", TOOL_DOES_NOT_EXIST);
			}
			else {    

				Tool currentTool = tools.get(tool);

				/* Falls das Tool auf "active=false" steht, wird es behandelt, als w�re es nicht installiert */
				if (!currentTool.isActive()) {
					throw new EtiServerException("Tool " + tool + " does not exist on this jETI Server", TOOL_DOES_NOT_EXIST);
				}

				List<Object> argumentsVector = new Vector<Object>();

				Iterator<ParameterElement> i = currentTool.getElements().iterator();

				while (i.hasNext()) {

					Object parameterElement = i.next();
					Object createdElement;
					List<Object> createdVector;

					if (parameterElement instanceof Parameter) {
						createdElement = handleParameter((Parameter)parameterElement, parameters, currentTool, s);
						if (createdElement != null)
							argumentsVector.add(createdElement);
					}

					else if (parameterElement instanceof ParameterArray) {
						createdElement = handleArray((ParameterArray)parameterElement, parameters, currentTool, s);
						argumentsVector.add(createdElement);
					}

					else if (parameterElement instanceof ParameterUnion) {
						createdVector = handleUnion((ParameterUnion)parameterElement, parameters, currentTool, s);
						if (!createdVector.isEmpty())
							argumentsVector.addAll(createdVector);
					}

				}

				Object[] arguments = new Object[argumentsVector.size()];

				@SuppressWarnings("unchecked") Class<? extends Object>[] argumentsClasses = new Class[argumentsVector.size()];

				for (int j = 0; j < argumentsVector.size(); j++) {
					Array.set(arguments, j, argumentsVector.get(j));
					Array.set(argumentsClasses, j, argumentsVector.get(j).getClass());
				}	

				Class<? extends Object> c = Class.forName(currentTool.getClassIdentifier());

				Method m = c.getMethod(currentTool.getMethodIdentifier(), argumentsClasses);

				m.invoke(c.newInstance(), arguments);

				return s.getSessionId();

			}
		}
		catch (InvocationTargetException ite) {      	
			throw new EtiServerException("Execution failed: " + ite.getCause().getMessage(), ite.getCause(), EXECUTION_FAILED);
		}
		catch (EtiServerException e) {
			throw e;
		}
		catch (Exception e) {
			throw new EtiServerException(e.getMessage(), e, OTHER_ERROR);

		}  
	}
	
  /**
   * F�hrt das in tool gegebene tool mit den parametern aus parameterlist aus
   * 
   * @param tool 
   *    der identifier des tools, das ausgef�hrt werden soll
   * @param parameterlist
   *    parameterliste, abh�ngig vom tool
   * @param sessionId
   *    die session, unter der das tool ausgef�hrt werden soll. wenn die Session
   *    nicht existiert, wird eine Exception geworfen.
   * @return
   *    gibt die session id zur�ck, wenn alles geklappt hat.
   * @throws EtiServerException 
   * 	if parameterlist ins encoded with wrong syntax
   */
	@Deprecated
	public String exec(String tool, String parameterlist, String sessionId) 
	throws EtiServerException {
		Map<String, String> sentParams = parseParameters(parameterlist);
		logger.debug("Parsed retrieved parameters: " + parameterlist);
		return exec(tool, sentParams, sessionId);
  }
  
    
  
  private Session getSession(String sessionId) throws EtiServerException {
	   SessionManager sm = SessionManager.getInstance();
	   Session s = sm.getSession(sessionId);
	   s.touch();
	   return s;
   }
  
  private Set<VirtualFile> namesToFiles(Set<String> filelist, Session s) throws EtiServerException {
	  Set<VirtualFile> returnList = new HashSet<VirtualFile>();
		  for (String file : filelist) {
			  try {
				  VirtualFile rep = new FileVirtualFile(new File(s.getSessionFolder(), file), file);
				  returnList.add(rep);
			  }
			  catch (FileNotFoundException e ) {
				  throw new EtiServerException("File '" + file + "' could not be retrieved/forwarded. I found such file found in session folder.", FILE_NOT_FOUND); 
			  }
				  
		  }
	  return returnList;
  }


/** parses the String of parameters sent by the eti client
   *  and creates a HashMap with parameter name as key and
   *  parameter value as value.
   * 
   * @param parameterlist
   * 	to be parsed parameter String, 
   * 	syntax is {@code param1<value1>param2<value2>...}
   * @return
   * @throws EtiServerException 
   */
  private Map<String, String> parseParameters(String parameterlist) throws EtiServerException {
  
  Map<String, String> parameters = new HashMap<String, String>();
  
  if ("".equals(parameterlist))
	  return parameters;
  
  String[] parameterarray = parameterlist.split(">"); 
  
  for (int i = 0; i < parameterarray.length; i++) {
      try {
    	String key = parameterarray[i].split("<")[0];
    	String value = parameterarray[i].split("<")[1];
    	// FIXME hanzelm: rather handle escaping/unescaping in protocol implementations? (connector/connection)
    	// (EtiConnectionImpl is doing the escaping ATM)
    	key = StringEscapeUtils.unescapeHtml(key);
    	value = StringEscapeUtils.unescapeHtml(value);
		parameters.put(key, value);
      } catch (ArrayIndexOutOfBoundsException aie) {
          logger.debug("parameter syntax error, got: " + parameterlist, aie);
          throw new EtiServerException("Wrong syntax in parameterlist (must be: 'param1<value1>param2<value2>...'). Check if server/client are the same versions.", OTHER_ERROR);
      }            
      
  }        
  return parameters;
}
  
 
  	/** Verarbeitet eine ParameterUnion und liefert die passenden Objekte zur�ck.
  	 *  Sollte ein Parameter aus der Union required sein, aber nicht in
  	 *  den sentParams vorhanden sein, wird die gesamte Union nicht verwendet.
  	 *  Der zur�ckgegebene Vector ist dann leer.
	 * @param union
	 *  die zu verarbeitende ParameterUnion
	 * @param sentParams
	 *  HashMap aus den vom Client geschickten Parameter Werten
	 * @param tool
	 *  Das Tool welches ausgef�hrt werden soll
	 * @param session
	 *  Die Session ID (ben�tigt f�r FileReferences)
	 * @return
	 *  Vector aus Objekten, die die Union darstellen. Ist leer, wenn die Union
	 *  aufgrund von nicht gesetzten required-Parametern nicht verwendet wird.
	 */
	private List<Object> handleUnion(ParameterUnion union, Map<String, String> sentParams, Tool tool, Session session)
	throws Exception {
		
		List<Object> returnVector = new Vector<Object>();
		
		try {
			Iterator<ParameterElement> i = union.getUnionElements().iterator();
			while (i.hasNext()) {
				Parameter parameter = (Parameter)i.next();
				Object createdElement = handleParameter(parameter, sentParams, tool, session);
				if (createdElement != null) {
					returnVector.add(createdElement);
				}
			}		
		}
		catch (EtiServerException e) {
			if (e.getErrorId() == REQUIRED_PARAM_NOT_SET) {
				returnVector.clear();			
				return returnVector;
			}
			else {
				throw e;
			}
		}
		catch (Exception e) {
			throw e;
		}
		return returnVector;
	}

	/** 
	 * Erzeugt ein Objekt passend zu einem Parameter unter Ber�cksichtigung von
	 * gesendeten Werten, Default-Werten, usw. 
	 * 
	 * @param parameter
	 *  Der Parameter der verarbeitet werden soll
	 * @param sentParams
	 *  HashMap aus den vom Client geschickten Parameter Werten
	 * @param tool
	 *  Das Tool welches ausgef�hrt werden soll
	 * @param session
	 *  Die Session ID (ben�tigt f�r FileReferences)
	 * @throws RequiredParameterNotSetException
	 *  wenn ein Parameter required ist, aber kein entsprechender Wert in sentParams vorhanden ist
	 * @return
	 *  Ein Obect von dem Typ, der in Parameter.classIdentifier angegeben ist.
	 * 
	 */
	private Object handleParameter(Parameter parameter, Map<String, String> sentParams, Tool tool, Session session) 
	throws EtiServerException {
		
		Object returnObject;
		
		try {
		
			@SuppressWarnings("unchecked") Class[] stringConstructorSignature = { String.class };
			Object[] stringConstructor = { "" };
			
			Class<? extends Object> c = Class.forName(parameter.getClassIdentifier());
			Constructor<? extends Object> con = c.getConstructor(stringConstructorSignature);
			
			/*
			 * Fallunterscheidung um welche Art von Parameter es sich handelt
			 */
			
			// Fall1: Parameter ist required, aber wurde nicht gesendet. 
			if (parameter.isRequired() && (!sentParams.containsKey(parameter.getName()))) {
				throw new EtiServerException("Parameter '" + parameter.getName() + "' for tool '" + tool.getName() + "' is required, but not set", REQUIRED_PARAM_NOT_SET);
			}
			
			
			// Fall2: Parameter ist statisch (Wert schon in der tools.xml definiert)
			else if (!parameter.getValue().equals("")) {
				stringConstructor[0] = parameter.getValue();			
				returnObject = con.newInstance(stringConstructor);
			}
			
			// Fall3: Parameter wurde nicht geschickt, aber es existiert ein default-wert
			else if ((!sentParams.containsKey(parameter.getName())) && (!parameter.getDefaultValue().equals(""))) {
				stringConstructor[0] = parameter.getDefaultValue();			
				returnObject = con.newInstance(stringConstructor);
			}
						
			// Fall4: Parameter wurde wurde geschickt, und keiner der obigen F�lle trifft zu
			else if (sentParams.containsKey(parameter.getName())) {
				stringConstructor[0] = sentParams.get(parameter.getName());			
				try {
					returnObject = con.newInstance(stringConstructor);
				} catch (InvocationTargetException e) {
					throw new EtiServerException("Parameter could not be instanciated. Probably wrong serialization?", e, PARAM_INSTANCIATION_FAILED);
				}
			}
			
			// Fall 5: In allen anderen F�llen wird null zur�ckgegeben
			else {
				returnObject = null;
			}
			
			// Wenn der parameter vom Typ FileReference ist, wird der Pfad angepasst
			if ((returnObject != null) && (returnObject instanceof FileReference)) {
				((FileReference)returnObject).addPathPrefix(session.getSessionFolder().getAbsolutePath() + "/");
			}
			
			// wenn der parameter validierbar ist, wird die funktion mit dem
			// vergleichsstring aus der tools.xml aufgerufen
			if ((returnObject != null) && (returnObject instanceof ValidatableParameter)) {
				((ValidatableParameter)returnObject).validateClassArgument(
						parameter.getClassArgument()
				);
			}
			
		} catch (EtiServerException e) {
			throw e;
		} catch (Exception e) {
			logger.warn("An exception occured during execution of tool '" + tool.getName() + "'. This is probably due to a tool configuration error.", e);
			logger.debug(e);
			throw new EtiServerException("Execution failed due to error in server configuration", SERVER_CONFIG_ERROR);
		}
		
		return returnObject;
	}

	/**
	 * @param array
	 * @param tool
	 * @param sentParams
	 * @param session
	 * @return
	 */
	private Object handleArray(ParameterArray array, Map<String, String> sentParams, Tool tool, Session session) throws Exception{
		Object returnObject;
		try {
			
			Iterator<ParameterElement> i = array.getElements().iterator();
			
			List<Object> argumentsVector = new Vector<Object>();
			
			while (i.hasNext()) {
				
				Object parameterElement = i.next();
				
				if (parameterElement instanceof Parameter) {
					Object createdElement = handleParameter((Parameter)parameterElement, sentParams, tool, session);
					if (createdElement != null) {
						argumentsVector.add(createdElement);
					}
				}
				
				else if (parameterElement instanceof ParameterUnion) {
					List<Object> createdVector = handleUnion((ParameterUnion)parameterElement, sentParams, tool, session);
					if (!createdVector.isEmpty())
						argumentsVector.addAll(createdVector);
				}
				
			}
			
			returnObject = Array.newInstance(Class.forName(array.getClassIdentifier()), argumentsVector.size());
			for (int j = 0; j < argumentsVector.size(); j++) {				
				Array.set(returnObject, j, argumentsVector.get(j));
			}		
			
			
		
		}
		catch (Exception e) {
			throw e;
		}
		return returnObject;
	}
	
	public boolean validateSession(String id) throws EtiServerException {
		try {
			getSession(id);
			return true;
		}
		catch (EtiServerException e) {
			if (e.getErrorId() == ErrorCodeConstants.ILLEGAL_SESSION)
				return false;
			else 
				throw e;
		}
	}	



	public String login(String username, String password) throws EtiServerException {
		Session s = getSession("0");
		return s.getSessionId();
	}
}
