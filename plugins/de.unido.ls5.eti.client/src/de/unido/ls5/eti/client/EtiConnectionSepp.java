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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * A jETI-connection for SEPP, the Streaming Eti Performance Protocol.
 * SEPP is a very simple byte-monging TCP-based protocol.
 * 
 * @author <a href="benjamin.hanzelmann@cs.uni-dortmund.de">Benjamin Hanzelmann</a>
 */
public class EtiConnectionSepp implements EtiConnection {
	
//	public static void main(String[] args) throws URISyntaxException, EtiLocalException, EtiRemoteException {
//		EtiConnectionSepp c = new EtiConnectionSepp(new URI("sepp://localhost:9867"));
//		c.login("bla", "blubb");
//		System.out.println(c.sessionId);
//		
//		
//		
//		File f = new File("/tmp/jeti.test");
//		FileVirtualFile vf = new FileVirtualFile(f, "test.jpg");
//		c.store(new HashSet(Arrays.asList(vf)));
//		Map<String,String> p = new HashMap<String, String>();
//		p.put("ROTATEANGLE", "45");
//		p.put("INFILE", "test.jpg");
//		p.put("OUTFILE", "ret.jpg");
//		c.exec("rotate", p);
//		Set<VirtualFile> ret = c.retrieve(new HashSet(Arrays.asList("ret.jpg")));
//		VirtualFile i = new ArrayList<VirtualFile>(ret).get(0);
//		String name = i.getFilename();
//		try {
//			FileOutputStream fos = new FileOutputStream(new File(new File("/tmp"), name));
//			InputStream in = i.getInputStream();
//			int read;
//			byte[] buffer = new byte[32000];
//			while ( (read = in.read(buffer)) != -1) {
//				fos.write(buffer, 0, read);
//			}
//			fos.flush();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//		c.endSession();
//		
//	}
	
	private static final int SEP = 0x0;
	
	private static final int OKAY = 0x0;
	
	@SuppressWarnings("unused") // for later use
	private static final int ASYNC = 0x1;
	
	private static final int NEWSESSION = 0x2;
	private static final int DATA = 0x3;
		
	private static final int SID_LENGTH = 40;
		
	private static Logger log = Logger.getLogger(EtiConnectionSepp.class);
	
	private URI uri;
	private String sessionId;

	private int counter = 0;

	/**
	 * A short lived connection, valid only for one command.
	 * 
	 * @author <a href="benjamin.hanzelmann@cs.uni-dortmund.de">Benjamin Hanzelmann</a>
	 */
	private class Connection {
		
		private Socket socket;
		private InputStream in;
		private OutputStream out;
	
		/**
		 * Creates a TCP-socket connection with the host and port from the given URI.
		 * @param uri
		 * 	The endpoint of the connection.
		 * @throws EtiLocalException
		 * 	if something goes wrong while connecting ({@link UnknownHostException}, {@link IOException}).
		 */
		public Connection(URI uri) throws EtiLocalException {
			try {
				socket = new Socket(uri.getHost(), uri.getPort());
				in = socket.getInputStream();
				out = socket.getOutputStream();
			} catch (UnknownHostException e) {
				log.debug(e.getMessage(), e);
				throw new EtiLocalException("Unknown Host:" + uri.getHost(), e);
			} catch (IOException e) {
				log.debug(e.getMessage(), e);
				throw new EtiLocalException("Cannot connect to host " + uri.getHost() + "\nerror:" + e.getMessage(), e);
			}
		}
		
		/**
		 * Creates a session at the jETI server.
		 * Username and password are not supported by the server at the moment. You need to supply them anyway.
		 * 
		 * @param user
		 * 	the username to send.
		 * @param pass
		 * 	the password to send.
		 * @return
		 * 	a session id.
		 * @throws EtiLocalException
		 * 	if there is a problem connecting to the server ({@link IOException}).
		 * @throws EtiRemoteException
		 *  if the server sends an error message.
		 */
		public String login (String user, String pass) throws EtiLocalException, EtiRemoteException {
			try {
				out.write(("login\0" + user + "\0" + pass + "\0").getBytes("utf-8"));
				out.flush();
				int code;
				if ( (code = in.read()) == NEWSESSION ) {
					String sid = readBytes(SID_LENGTH);
					this.close();
					return sid;
				} else {
					throw new EtiRemoteException("error " + code + ": " + readBytes(1024));
				}
			} catch (UnsupportedEncodingException e) {
				// should not happen with utf8?
				throw new EtiLocalException(e.getMessage(), e);
			} catch (IOException e) {
				throw new EtiLocalException(e.getMessage(), e);
			}
		}
	
		/**
		 * Closes this connection.
		 */
		public void close() throws IOException {
			socket.close();
		}
	
		/**
		 * Tests for errors and wraps them in an {@link EtiRemoteException}.
		 * If the next byte read from the connection is not the OKAY-return code, an {@link EtiRemoteException} is thrown.
		 * @throws EtiLocalException
		 * 	if something goes wrong with the connection.
		 * @throws EtiRemoteException
		 * 	if the server signals an error.
		 */
		public void handleError() throws EtiLocalException, EtiRemoteException {
			try {
    			int code = getInput().read();
    			if ( code != OKAY ) {
    				throw new EtiRemoteException("error " + code + ": " + getErrorMessage(), code);
    			}
			} catch (IOException e) {
				throw new EtiLocalException(e.getMessage(), e);
			}
		}

		/**
		 * Read given number of bytes or until EOF and return them as us-ascii-String.
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
				readBytes = in.read(buffer, readBytes, length - readBytes);
				if ( readBytes == -1 ) break;
			} while ( (totalBytes += readBytes) < length);
			
			return new String(buffer, 0, totalBytes, "us-ascii");
		}
		
		public void preamble(String method, String sid) throws EtiLocalException, EtiRemoteException {
			try {
				out.write((method + "\0" + sid + "\0").getBytes("us-ascii"));
				out.flush();
				handleError();
			} catch (IOException e) {
				throw new EtiLocalException(e.getMessage(), e);
			}
		}
	
		/**
		 * @return
		 * 	the output-stream of this connection.
		 */
		public OutputStream getOutput() {
			return out;
		}
	
		/**
		 * @return
		 * 	the input-stream of this connection.
		 */
		public InputStream getInput() {
			return in;
		}
	
		/**
		 * Reads the next kb and returns it as a String.
		 * 
		 * @return
		 * 	at most a kb from the connection represented as a string.
		 * @throws IOException
		 * 	if {@link #readBytes(int)} fails.
		 */
		public String getErrorMessage() throws IOException {
			return readBytes(1024);
		}
		
	}

	/**
	 * Saves the URI for later use and creates temporary directory. Nothing unusual.
	 * 
	 * @param uri
	 * 	The endpoint of this Connection.
	 */
	public EtiConnectionSepp(URI uri) {
		this.uri = uri;
	}

	/**
	 * Ends the currently used session.
	 */
	public void endSession() throws EtiLocalException, EtiRemoteException {
		checkSession();
		Connection c = new Connection(uri);
		c.preamble("endsession", sessionId);
		sessionId = null;
	}

	/**
	 * Execute the given tool with the given parameters.
	 * 
	 * @param tool
	 * 	the tool to execute.
	 * @param parameters
	 * 	the key-value-paired parameters to use.
	 * @throws EtiLocalException
	 * 	if anything goes wrong with the connection ({@link IOException}).
	 * @throws EtiRemoteException
	 * 	if the server sends an error code and message.
	 */
	public void exec(String tool, Map<String, String> parameters) throws EtiLocalException, EtiRemoteException {
		checkSession();
		Connection c = new Connection(uri);
		c.preamble("exec", sessionId);
		OutputStream out = c.getOutput();
		try {
			out.write(tool.getBytes("utf-8"));
    		out.write(SEP);
    		sendParameterList(parameters, out);
    		
    		c.handleError();
		} catch (IOException e) {
			throw new EtiLocalException(e.getMessage(),e);
		}
		
	}

	/**
	 * Delegate to {@link #retrieve(Set)}.
	 */
	public List<VirtualFile> retrieve(List<String> filenames) throws EtiLocalException, EtiRemoteException {
		return new ArrayList<VirtualFile>(retrieve (new HashSet<String>(filenames)));
	}
	
	/**
	 * Retrieve the named files from the server.
	 * 
	 * @param filenames
	 * 	the files to retrieve.
	 * @return
	 * 	the lst of retrieved files.
	 * @throws EtiLocalException
	 * 	if anything goes wrong with the connection ({@link IOException}).
	 * @throws EtiRemoteException
	 * 	if the server sends an error code and message.
	 */
	public Set<VirtualFile> retrieve(Set<String> filenames) throws EtiLocalException, EtiRemoteException {
		checkSession();
		
		if ( filenames.size() == 0 ) throw new EtiLocalException("Please provide some filenames.");
		
		Connection c = new Connection(uri);
		c.preamble("retrieve", sessionId);
		
		OutputStream out = c.getOutput();
		sendFilelist(filenames, out);
		
		InputStream in = c.getInput();
		try {
			int code;
			if ( (code = in.read()) == DATA ) {
				ZipInputStream zip = new ZipInputStream(c.getInput());
				return zipToFiles(zip);
			} else {
				throw new EtiRemoteException("error " + code + ": " + c.getErrorMessage());
			}
		} catch (IOException e) {
			throw new EtiLocalException(e.getMessage(), e);
		} finally {
			try {
				c.close();
			} catch (IOException e) {
				throw new EtiLocalException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Delegate to {@link #store(Set)}.
	 */
	public void store(List<VirtualFile> files) throws EtiLocalException, EtiRemoteException {
		store(new HashSet<VirtualFile>(files));
	}

	/**
	 * Store the named files on the server.
	 * 
	 * @param files
	 * 	the files to send.
	 * @throws EtiLocalException
	 * 	if anything goes wrong with the connection ({@link IOException}).
	 * @throws EtiRemoteException
	 * 	if the server sends an error code and message.
	 */
	public void store(Set<VirtualFile> files) throws EtiLocalException,	EtiRemoteException {
		checkSession();
		String uid = String.valueOf(uuidNumber());
		Connection c = new Connection(uri);
		c.preamble("store", sessionId);
		try {
			c.getOutput().write(uid.getBytes("us-ascii"));
			c.getOutput().write(SEP);
			c.handleError();
			
    		ZipOutputStream zip = new ZipOutputStream(c.getOutput());
			for (VirtualFile f : files) {
				zip.putNextEntry(new ZipEntry(f.getFilename()));
				InputStream in = f.getInputStream();
				
				byte[] buffer = new byte[32000];
				int readBytes = -1;
				while ( (readBytes = in.read(buffer)) != -1 ) {
					zip.write(buffer, 0, readBytes);
				}
				zip.closeEntry();
			}
			zip.flush();
			zip.close();
			verify(uid);
		} catch (IOException e ) {
			throw new EtiLocalException(e.getMessage(), e);
		} finally {
			try {
	    		c.close();
			} catch (IOException e) {
				log.debug(e.getMessage(),e);
			}
		}
	}
	
	/**
	 * Check for a successful store.
	 * 
	 * @param uid
	 * 	the unique id for the store.
	 * @throws EtiLocalException
	 * 	if something goes wrong with the TCP connection.
	 * @throws EtiRemoteException
	 * 	if the server sends an error.
	 */
	private void verify(String uid) throws EtiLocalException, EtiRemoteException {
		Connection c = new Connection(uri);
		c.preamble("store", sessionId);
		try {
			c.getOutput().write(uid.getBytes("us-ascii"));
			c.getOutput().write(SEP);
			c.handleError();
		} catch (IOException e ) {
			throw new EtiLocalException(e.getMessage(), e);
		} finally {
			try {
	    		c.close();
			} catch (IOException e) {
				log.debug(e.getMessage(),e);
			}
		}
		
	}
	
	private synchronized int uuidNumber() {
		return this.counter++;
	}

	/**
	 * Delegate to {@link #forward(Set, EtiConnection)}.
	 */
	public void forward(List<String> filenames, EtiConnection toServer) throws EtiLocalException, EtiRemoteException {
		forward(new HashSet<String>(filenames), toServer);
	}
	
	/**
	 * Tell the server to forward the specified files to another server.
	 * 
	 * @param filenames
	 * 	the files to forward.
	 * @param toServer
	 * 	the server to send the files to.
	 * @throws EtiLocalException
	 * 	if anything goes wrong with the connection ({@link IOException}).
	 * @throws EtiRemoteException
	 * 	if the server sends an error code and message.
	 */
	public void forward(Set<String> filenames, EtiConnection toServer) throws EtiLocalException, EtiRemoteException {
		checkSession();
		Connection c = new Connection(uri);
		c.preamble("forward", sessionId);
		try {
			c.getOutput().write(toServer.getServerURI().toString().getBytes("utf-8"));
			c.getOutput().write(SEP);
			c.getOutput().write(toServer.getSession().getBytes("utf-8"));
			c.getOutput().write(SEP);
			sendFilelist(filenames, c.getOutput());
			c.handleError();
		} catch (IOException e) {
			throw new EtiLocalException(e.getMessage(), e);
		}
	}

	public URI getServerURI() {
		return uri;
	}

	public String getSession() {
		return sessionId;
	}

	/**
	 * Create a session on the server.
	 * Authentication is not supported by the server
	 * 	at the moment. Username and password have to be supplied anyway.
	 * 
	 * @param username
	 * 	the username to send. 
	 * @param password
	 * 	the password to send.
	 * @throws EtiLocalException
	 * 	if anything goes wrong with the connection ({@link IOException}).
	 * @throws EtiRemoteException
	 * 	if the server sends an error code and message.
	 */
	public void login(String username, String password) throws EtiLocalException, EtiRemoteException {
		Connection c = new Connection(uri);
		this.sessionId = c.login(username, password);
	}

	/**
	 * Sends the encoded list of files to the server. Needed for retrieve. 
	 * 
	 * @param filenames
	 * 	the files to send.
	 * @param out
	 * 	the stream to send them to.
	 * 	
	 * @throws EtiLocalException
	 * 	if anything goes wrong with the connection ({@link IOException}).
	 */
	private void sendFilelist(Set<String> filenames, OutputStream out) throws EtiLocalException {
		try {			
			sendCount(filenames.size(), out);
			for ( String nameStr : filenames ) {
				byte[] name = nameStr.getBytes("utf-8");
				out.write(toByteArray(name.length));
				out.write(name);
			}
		} catch (IOException e) {
			throw new EtiLocalException(e.getMessage(), e);
		} finally {
			try {
				out.flush();
			} catch (IOException e) {
				throw new EtiLocalException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Sends the lower two bytes of the given integer to the server.
	 * 
	 * @param count
	 * 	the number to send the lower two bytes from.
	 * @param out
	 * 	the stream to send them to.
	 * 	
	 * @throws IOException
	 * 	if anything goes wrong with the connection. 
	 */
	private void sendCount(int count, OutputStream out) throws IOException {
		// num -> 2 bytes
		byte hi, lo;
		hi = (byte)((count&(255<<8))>>8);
		lo = (byte)(count&255);
		out.write(hi);
		out.write(lo);
	}

	/**
	 * Sends the encoded list of name-value-paired parameters to the server. Needed for exec. 
	 * 
	 * @param parameters
	 * 	the parameters to send.
	 * @param out
	 * 	the stream to send them to.
	 * 	
	 * @throws EtiLocalException
	 * 	if anything goes wrong with the connection ({@link IOException}).
	 */
	private void sendParameterList(Map<String, String> parameters, OutputStream out) throws EtiLocalException {
		try {
			int num = parameters.size();
			sendCount(num, out);
			for ( Entry<String, String> entry : parameters.entrySet()) {
				byte[] name = entry.getKey().getBytes("utf-8");
				byte[] value = entry.getValue().getBytes("utf-8");
				out.write(toByteArray(name.length));
				out.write(toByteArray(value.length));
				out.write(name);
				out.write(value);
			}
		} catch ( Exception e) {
			throw new EtiLocalException(e.getMessage(), e);
		} finally {
			try {
				out.flush();
			} catch (IOException e) {
				throw new EtiLocalException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Converts an integer into 4 bytes (big-endian, hi-lo). 
	 * @param l
	 * 	the integer to convert.
	 * @return
	 * 	the encoded integer.
	 */
	private byte[] toByteArray(int l) {
		byte[] byteLength = new byte[] {(byte)((l&(255<<24))>>24), (byte)((l&(255<<16))>>16), (byte)((l&(255<<8))>>8), (byte)(l&255)};
		return byteLength;
	}

	/**
	 * Deflates all files from the zip-stream to temporary files and wraps them in VirtualFiles.
	 * @param zip
	 * 	the zip-stream to read from.
	 * @return
	 * 	VirtualFiles representing the files from the zip-stream.
	 * @throws IOException
	 * 	if anything goes wrong with the connection or file operations.
	 * @throws FileNotFoundException
	 * 	if the temporary file could not be created.
	 */
	private Set<VirtualFile> zipToFiles(ZipInputStream zip) throws IOException, FileNotFoundException {
		Set<VirtualFile> ret = new HashSet<VirtualFile>();
		ZipEntry entry;
		while ( (entry = zip.getNextEntry()) != null) {
			File tmp = File.createTempFile("jeti-sepp-client", "virtual");
			FileOutputStream outFile = new FileOutputStream(tmp);
			byte[] buffer = new byte[32000];
			int readBytes;
			while ( (readBytes = zip.read(buffer)) != -1 ) {
				outFile.write(buffer, 0, readBytes);
			}
			outFile.flush();
			outFile.close();
			tmp.deleteOnExit();
			
			VirtualFile vf = new FileVirtualFile(tmp, entry.getName());
			ret.add(vf);
		}
		return ret;
	}

	public void setSession(String sessionId) {
		this.sessionId = sessionId;
	}
	
	private void checkSession() throws EtiLocalException {
		if (sessionId == null) 
			throw new EtiLocalException("No session id found.\nPlease login first.");
	}

	public void exec(String tool, String... parameters) throws EtiLocalException, EtiRemoteException {
		
		if ((parameters.length % 2) != 0) {
			throw new EtiLocalException("amount of parameters must be even (key,value pairs)");
		}
		else {
			Map<String, String> parameterMap = new HashMap<String, String>();
			int i = 0;
			while (i < (parameters.length-1)) {
				parameterMap.put(parameters[i], parameters[i+1]);
				i+=2;
			}
			exec(tool, parameterMap);
		}
		
	}

	/**
	 * Delegates to {@link #retrieve(Set)}.
	 */
	public VirtualFile retrieve(String filename) throws EtiLocalException, EtiRemoteException {
		Set<String> inset = new HashSet<String>();
		inset.add(filename);
		Set<VirtualFile> outset = retrieve(inset);
		if (outset.iterator().hasNext())
			return outset.iterator().next();
		else {
			return null;
		}
	}

	/**
	 * Delegates to {@link #store(Set)}.
	 */
	public void store(VirtualFile file) throws EtiLocalException, EtiRemoteException {
		Set<VirtualFile> inset = new HashSet<VirtualFile>();
		inset.add(file);
		store(inset);
	}
	
	/**
	 * Returns "EtiConnectionSepp($sessionid)".
	 */
	public String toString() {
		return "EtiConnectionSepp(" + sessionId + ")";
	}
		

}
