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
/**
 * 
 */
package de.unido.ls5.eti.sps.apis;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Comparator that enables sorting of URIs due to a priority setting with {@link Properties}.
 * 
 * The keys must have the form "jeti.connector.priority.${i}" with ${i} starting from 0.
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class UriSchemeComparator implements Comparator<String> {
	
	private Map<String, Integer> priorities;
	
	private static final Logger logger = Logger.getLogger(UriSchemeComparator.class);
	
	/**
	 * You should not use this Constructor as it results in some random order of 
	 * URIs. Use {@link #UriSchemeComparator(Properties)} instead.
	 */
	public UriSchemeComparator() {
		priorities = new HashMap<String, Integer>();		
	}
	
	/**
	 * Instanciate with given properties 
	 * 
	 * @param properties
	 */
	public UriSchemeComparator(Properties properties) {
		priorities = new HashMap<String, Integer>();
		int i = 0;
		while (true) {
			String scheme = properties.getProperty("jeti.connector.priority." + i);
			if (scheme != null) {
				if (priorities.containsKey(scheme)) {
					logger.warn("There are duplicate connector priority entries for URI scheme '" + scheme + "' in properties file. Ignoring lower priority '" + i + "'.");
					i++;
				}
				else {
					logger.info("Setting connector URI scheme priority: " + scheme + "=" + i);
					priorities.put(scheme, i);
					i++;
				}
			}
			else {
				break;
			}
		}
		
		if (priorities.size() ==  0) {
			logger.warn("No priorities for connectors defined in properties file. Note that priority can now be random");
		}
		
	}

	/**
	 * Compare two URIs.
	 * 
	 * @param o1 First one
	 * @param o2 Second one
	 * 
	 * @return
	 * 	-1 if o1 < o2
	 * 	0 if o1 = o2
	 * 	1 if o1 > o2
	 * 
	 * @throws ClassCastException if one of the provided Strings doesn't have valid {@link URI} syntax.
	 */
	public int compare(String o1, String o2) {
		URI u1, u2;
		try {
			u1 = new URI(o1);
			u2 = new URI(o2);
		}
		catch (URISyntaxException e) {
			throw new ClassCastException("Compared Strings both must have valid URI syntax");
		}
		
		String scheme1 = u1.getScheme();
		String scheme2 = u2.getScheme();
		//FIXME: what if provided URIs do not contain scheme part? ~naujokat
		
		Integer prio1 = priorities.get(scheme1);
		Integer prio2 = priorities.get(scheme2);
		
		if (prio1 == null && prio2 == null) {
			logger.warn("None of URI schemes '" + scheme1 + "' and '" + scheme2 + "' has a priority defined in properties file. I take them for equal for failsafe reasons");
			return 0;				
		}
		else {
			if (prio2 == null || (prio1.intValue() < prio2.intValue())) {
				if (prio2 == null) 
					logger.warn("URI scheme '" + scheme2 + "' has no priority defined in properties file. Assuming lowest priority.");
				logger.debug("URI priority compare: '" + o1 + "' < '" + o2 + "'");
				return -1;
			}
			else if (prio1 == null || (prio2.intValue() < prio1.intValue())) {
				if (prio1 == null) 
					logger.warn("URI scheme '" + scheme1 + "' has no priority defined in properties file");
				logger.debug("URI priority compare: '" + o2 + "' < '" + o1 + "'");
				return 1;
			}
			else {
				//This should not happen... correct code above...
				logger.warn("Connector URIs '" + "' and '" + "' seem to be uncomparable, but this should not happen. Please fix broken code. I take them for equal for failsafe reasons");
				return 0;
			}
		}
		
	}
	
}