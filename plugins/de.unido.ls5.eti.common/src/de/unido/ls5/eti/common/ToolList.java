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
package de.unido.ls5.eti.common;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * This class stores all information about the tools.
 * This list is send to the componentserver.
 * That's why it is just a string list.
 * 
 * @author David Karla
 *
 */
public class ToolList {
	
	private static Logger logger = Logger.getLogger(ToolList.class);
	private Vector<String> list;
	
	/**
	 * Load the tools.xml and store for each tool:
	 * name, description
	 * That is all the ComponentServer needs to know (until now).
	 *
	 */
	public ToolList () {
		this.list = new Vector<String>();				
	}
	
	public boolean load (String serverURI) {
		logger.info("Loading tool list...");
		boolean error = false;
		Map tools = new HashMap<String, Tool>();
		try {
			tools = ToolXmlHelper.parseToolXML(new File("./config/tools.xml"));
		} catch (Exception e) {
			logger.warn("Could not load tools.xml");
			error = true;
		}
		logger.info("Loaded " + tools.size()+ " tools for server "+ serverURI);
		if (!error){
			list.add(serverURI);
			Iterator<Tool> iter = tools.values().iterator();
			while (iter.hasNext()){
				// add each active tool's name and description and certs to String list
				Tool current = iter.next();
				if (current.isActive() && (current.getCertificateTypes().size() > 0)) {
					list.add(current.getName());
					list.add(current.getDescription());
					String certs = current.getCertificateTypes().get(0);
					// create string which contains the certtypes seperated by commas
					for (int i = 1; i < current.getCertificateTypes().size(); i++ ){
						certs = certs + "," + current.getCertificateTypes().get(i);
					}						
					list.add(certs);
					// logger.info("Adding " + current.getName() + " - " +current.getDescription()+ " - "+certs);
				}
			}
			// finally a EOF string
			list.add("#EOF#");
		}
		else {
			logger.info("Could not create tool list.");
			error = true;
		}		
		return !error;
	}

	public Vector<String> getList() {		
		return list;
	}
	
	public String toString () {
		Iterator<String> iter = this.list.iterator();
		String result = "";
		while (iter.hasNext()) {
			result = result + iter.next() + "\n";
		}
		return result;
	}

}
