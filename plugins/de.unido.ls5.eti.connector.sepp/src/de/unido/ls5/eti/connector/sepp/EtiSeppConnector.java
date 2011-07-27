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
package de.unido.ls5.eti.connector.sepp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorCode;

import de.unido.ls5.eti.sps.apis.ErrorCodeConstants;
import de.unido.ls5.eti.sps.apis.EtiConfig;
import de.unido.ls5.eti.sps.apis.EtiConnector;
import de.unido.ls5.eti.sps.apis.EtiExecutor;
import de.unido.ls5.eti.sps.apis.EtiServerException;


/** 
 * The server-side connector for the Streaming Eti Performance Protocol.
 * 
 * For protocol "specifications" see sepp-connector/doc/protocol.txt
 * 
 * @author Stefan Naujokat
 * @author Benjamin Hanzelmann
 *
 */
public class EtiSeppConnector extends Thread implements EtiConnector{
	
	/**
	 * The property configuring the hostname to use in the URI.
	 */
	public static final String HOSTNAME_PROPERTY = "jeti.server.hostname";

	/**
	 * The property configuring the port to listen on.
	 */
	public static final String PORT_PROPERTY = "jeti.connector.sepp.port";

	private static Map<String, EtiServerException> uidMapping = new HashMap<String, EtiServerException>();
	
	private static char SEP = '\0';
	private static final int OKAY = 0x0;
	
	@SuppressWarnings("unused") // for later use
	private static final int ASYNC = 0x1;
	
	private static final int NEWSESSION = 0x2;
	private static final int DATA = 0x3;
	
	private Logger logger = Logger.getLogger(EtiSeppConnector.class);

	private ServerSocket listener;

	private String hostname = "localhost";
	private int port = 12345;
	
	/**
	 * Waits endlessly for TCP-connections and gives control to {@link Sepp}.
	 */
	public void run() {
		try {
			while ( true ) {
				new Thread(new Sepp( listener.accept() )).start();
			}
		} catch (IOException e) {
			// warn and exit
			logger.debug(e.getMessage(), e);
			logger.error("IO error: " + e.getMessage());
		}
	}

	/**
	 * The connection-handler, server-side SEPP implementation.
	 * 
	 * @author <a href="benjamin.hanzelmann@cs.uni-dortmund.de">Benjamin Hanzelmann</a>
	 */
	public class Sepp implements Runnable {
		
		private Socket socket = null;
		private InputStream inputStream = null;
		private OutputStream outputStream = null;
		
		/**
		 * Saves the socket for later use. Nothing unusual.
		 * @param socket
		 */
		public Sepp(Socket socket) {
			this.socket = socket;
		}

		/**
		 * Listens for methods to react to.
		 * 
		 * @see EtiConnector
		 */
		public void run() {
			
			try {
				inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();
				
				logger.debug("Receiving Sepp Connection from '" + socket.getRemoteSocketAddress() + "'");
				String method = nextValue();
				logger.debug("calling method " + method);
				
				if ( method.equals("login") ) {
					login();
				} else {
					if (method.equals("store")) {
						store();
					}
					else if (method.equals("exec")) {
						exec();
					}
					else if (method.equals("retrieve")) {
						retrieve();
					}
					else if (method.equals("endsession")) {
						endsession();
					}
					else if ( method.equals("forward")) {
						forward();
					}
					else if ( method.equals("verify")) {
						verify();
					}
					else {
						throw new EtiServerException("unknown method", 0xfc);
					}
				}
			} catch (EtiServerException ese) {
				try {
					outputStream.write(ese.getErrorId());
					outputStream.write((ese.getMessage() + "\n").getBytes("us-ascii"));
					outputStream.flush();
				} catch (IOException e) {
					logger.debug(e.getMessage(), e);
				}
			} catch (Exception re) {
				try {
					outputStream.write(0xff);
					outputStream.write((re.getMessage() + "\n").getBytes("us-ascii"));
					outputStream.flush();
				} catch (IOException e) {
					logger.debug(e.getMessage(), e);
				}
			} finally {
				close();
			}

		}

		private void forward() throws IOException, EtiServerException, URISyntaxException {
			String sessionId = nextValue();
			isSessionValid(sessionId);
			
			String serverURI = nextValue();
			String remoteSessionId = nextValue();
			Set<String> fileList = getFilenames();
			
			EtiConfig.getExecutor().forward(sessionId, remoteSessionId, new URI(serverURI), fileList);
			outputStream.write(OKAY);
		}
		
		/**
		 * Extracts the list of parameters from the TCP-stream.
		 * 
		 * Reads the number of parameters as a 2-byte-big-endian number, 
		 * then reads a 4-byte-big-endian number which is the length of the 
		 * parameter name, then a 4-byte-big-endian number which is the length of the 
		 * parameter value, then the name and value according to the length. 
		 * 
		 * @return
		 * 	the list of parameters sent over the TCP-stream.
		 * @throws IOException
		 * 	if reading from the stream fails.
		 */
		private Map<String,String> getParameters() throws IOException {
			Map<String, String> map = new HashMap<String, String>();
			int num = 0;
			
			num = num|(inputStream.read()<<8);
			num = num|(inputStream.read());
			
			for (int i = 0; i < num; i++) {
				int len = readInteger();
				int value = readInteger();
				String paramName = readBytes(len);
				String paramValue = readBytes(value);
				map.put(paramName, paramValue);
			}
			
			return map;
		}

		/**
		 * Extracts the list of filenames from the TCP-stream.
		 * 
		 * Reads the number of filenames as a 2-byte-big-endian number, 
		 * then reads a 4-byte-big-endian number which is the length of the filename,
		 * then the name itself. Then the length, the name, length, name, ...
		 * 
		 * @return
		 * 	the list of filenames sent over the TCP-stream.
		 * @throws IOException
		 * 	if reading from the stream fails.
		 */
		private Set<String> getFilenames() throws IOException {
			int num = 0;
			num = num|(inputStream.read()<<8);
			num = num|(inputStream.read());
			
			Set<String> files = new HashSet<String>();
			
			for (int i = 0; i < num; i++) {
				int len = readInteger();
				files.add(readBytes(len));
			}
			return files;
		}
		
		/**
		 * Read given number of bytes or until EOF and return them as utf8-String.
		 *  
		 * @param length
		 * The number of bytes to read
		 * @return
		 * The us-ascii encoded bytes as a String.
		 * @throws IOException
		 * if the encoding of the bytes fails or a read from the socket fails.
		 */
		private String readBytes(int length) throws IOException {
			int readBytes = 0, totalBytes = 0;
			byte[] buffer = new byte[length];
			do {
				readBytes = inputStream.read(buffer, readBytes, length - readBytes);
				if ( readBytes == -1 ) break;
			} while ( (totalBytes += readBytes) < length);
			
			return new String(buffer, "utf-8");
		}
		
		/**
		 * Converts 4 bytes into an integer (big-endian, hi-lo). 
		 */
		private int readInteger() throws IOException {
			int ret = 0;
			for (int i = 3; i >= 0; i--) {
				int read = inputStream.read();
				int shift = (i)*8;
				ret |= read<<(shift);
			}
			return ret;
		}

		private void endsession() throws IOException, EtiServerException {
			String sessionId = nextValue();
			isSessionValid(sessionId);
			EtiConfig.getExecutor().endsession(sessionId);
			outputStream.write(OKAY);
		}

		private void retrieve() throws IOException, EtiServerException {
			String sessionId = nextValue();
			isSessionValid(sessionId);
			
			Set<String> files = getFilenames();
			InputStream in = EtiConfig.getExecutor().retrieve(files, sessionId);
			outputStream.write(DATA);
			
			byte[] buffer = new byte[32000];
			int read;
			while ( (read = in.read(buffer)) != -1) {
    			outputStream.write(buffer, 0, read);
			}
		}

		private void exec() throws IOException, EtiServerException {
			String sessionId = nextValue();
			isSessionValid(sessionId);
			
			String toolname = nextValue();
			Map<String, String> parameters = getParameters();
			EtiConfig.getExecutor().exec(toolname, parameters, sessionId);
			outputStream.write(OKAY);
		}

		private void store() throws IOException, EtiServerException {
			logger.debug("Executing: store");
			
			// read session id
			String sessionId = nextValue();
			isSessionValid(sessionId);
			String uid = nextValue();
			outputStream.write(OKAY);
			
			try {
				EtiConfig.getExecutor().store(new ZipInputStream(inputStream),
						sessionId);
			} catch (EtiServerException e) {
				uidMapping.put(uid, e);
			}
		}
		
		private void verify() throws IOException, EtiServerException {
			logger.debug("Executing: verify");
			
			String sessionId = nextValue();
			String uid = nextValue();
			isSessionValid(sessionId);
			
			EtiServerException e = uidMapping.get(uid);
			if ( e != null ) 
				throw e; 
			else 
				outputStream.write(OKAY);
		}

		
		private void login() throws IOException, EtiServerException {
			String user = nextValue();
			String pass = nextValue();
			String sid = EtiConfig.getExecutor().login(user, pass);
			outputStream.write(NEWSESSION);
			outputStream.write(sid.getBytes("us-ascii"));
		}

		private void close() {
			try {
				outputStream.flush();
				outputStream.close();
				socket.close();
			} catch (IOException e) {
				logger.debug(e.getMessage(), e);
			}
			
		}

		/**
		 * Get next SEP-separated value. 
		 * 
		 * @return
		 * The next value in the stream.
		 * @throws IOException
		 * if {@link InputStream#read()} throws an IOException
		 * @throws EtiServerException 
		 */
		private String nextValue() throws IOException, EtiServerException {
			return nextValue(SEP);
		}
		
		/**
		 * Get the next value separated by the given separator.
		 * @param SEP
		 * the separator to use
		 * @return
		 * the String read from the stream to the next separator
		 * @throws IOException
		 * if {@link InputStream#read()} throws an IOException
		 * @throws EtiServerException 
		 */
		private String nextValue(char SEP) throws IOException, EtiServerException {
			int total = 0;
			byte[] buffer = new byte[32000]; 
			while ( inputStream.read(buffer, total, 1) != -1) {
				if ( buffer[total] == 0x0 ) {
					return new String(buffer, 0, total, "us-ascii");
				}
				total++;
			}
			throw new EtiServerException("No separator found in stream", 0xfd);
		}
		
		/**
		 * Checks if the given session id is valid.
		 * 
		 * @param sessionId
		 * 	the session id to check.
		 * @throws IOException
		 * 	if writing to the TCP-stream fails.
		 * @throws EtiServerException
		 *  if the session is not valid or {@link EtiExecutor#validateSession(String)} fails.
		 */
		private synchronized void isSessionValid(String sessionId) throws IOException, EtiServerException {
			if ( ! EtiConfig.getExecutor().validateSession(sessionId) ) {
				throw new EtiServerException("session is not valid, login required", ErrorCodeConstants.ILLEGAL_SESSION);
		} else {
				outputStream.write(OKAY);
			}
		}

	}

	/**
	 * Configures the connector and starts accepting connections.
	 */
	public String start(Properties props) {
		if ( ! props.containsKey(PORT_PROPERTY) )
			logger.warn(PORT_PROPERTY + " not set. Falling back to default (12345)");
		if ( ! props.containsKey(PORT_PROPERTY) )
			logger.warn(HOSTNAME_PROPERTY + " not set. Falling back to default (localhost)");
		port = Integer.valueOf(props.getProperty(PORT_PROPERTY, "12345")).intValue();
		hostname  = props.getProperty(HOSTNAME_PROPERTY, "localhost");
		
		String uri = "eti://" + hostname + ":" + port;
		logger.info("URI for SEPP Connector: " + uri);
		if (hostname.equals("localhost")) {
			logger.warn("You have specified 'localhost' as your server's hostname. Note that " + 
					"this will cause problems when setting up a 'live' server. See '" + HOSTNAME_PROPERTY + "' in your " +
        			"jeti.properties file.");
		}
		
		try {
			listener = new ServerSocket(port);
		} catch (IOException e) {
			logger.debug(e.getMessage(), e);
			logger.error("IO error: " + e.getMessage());
			return null;
		}
		this.start();
		return uri;
	}

}
