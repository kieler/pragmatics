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
 * Created on 25.12.2004
 */
package de.unido.ls5.eti.toolserver;


import java.io.InputStream;

import org.apache.log4j.Logger;


/**
 * This class is used as default jETI class to execute command line programs.
 * It is inteded to work on Unices.
 * 
 * @see #exec(Object[])
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class RuntimeUnix {
	
	/** Executes an external program with the java.lang.Runtime
	 * 
	 * @param parameters
	 * 		Object array which is converted to String array using the toString method
	 * 		of each element. Then the String array is used for Runtime.exec
	 * @throws Exception
	 * 		with stdout as message, if return Value is not 0
	 */
	public void exec (Object[] parameters) throws Exception {
		
	    Logger logger = Logger.getLogger(RuntimeUnix.class);
	    Logger stdouterrLogger = Logger.getLogger("de.unido.ls5.eti.toolserver.RuntimeUnix-StdOutErr");
				
		String[] cmdarray = new String[parameters.length];
		StringBuffer cmdstring = new StringBuffer();
		
		
		for (int i = 0; i < parameters.length; i++) {
			cmdarray[i] = parameters[i].toString();
			cmdstring.append(cmdarray[i] + " ");
		}
		
		logger.info("Executing shell command: " + cmdstring.toString());
		stdouterrLogger.info("Executing shell command: " + cmdstring.toString());
		
		Process p = null;
		
		try {
			long startTime = System.currentTimeMillis();
			p = new ProcessBuilder(cmdarray).redirectErrorStream(true).start();
			
			int returnCode = p.waitFor();
			long endTime = System.currentTimeMillis();
			
			logger.debug("Command's exit value: " + returnCode);
			stdouterrLogger.info("Command's exit value: " + returnCode);
			stdouterrLogger.info("Execution time: " + (new Double(endTime-startTime)/1000.0) + " seconds.");
			
			InputStream reader = p.getInputStream();
			byte[] stdoutBA = new byte[reader.available()];
			reader.read(stdoutBA ,0,reader.available());
			reader.close();
			String stdout = new String(stdoutBA);
			
			logger.debug("stdout: " + stdout);
			if (stdout.length() > 0) stdouterrLogger.info("Command's output: " + stdout);
			
			if (returnCode != 0) {
				throw new Exception(stdout);
			}
			
		} catch (Exception e) {		
				// TODO: unterscheidung der exceptions
				logger.debug("Error in executing command", e);
			throw e;
		} catch (Throwable t) {
			logger.debug("Caught Throwable...", t);
			throw new Exception(t);
		}
		
	}
	
	
}
