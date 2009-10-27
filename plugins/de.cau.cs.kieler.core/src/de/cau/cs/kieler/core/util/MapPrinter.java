/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.util;

import java.util.Map;

/**
 * Debugger Class to visualize the contents of a Map. Simply prints the Map
 * contents into a String.
 * 
 * @author <a href="mailto:haf@informatik.uni-kiel.de">Hauke Fuhrmann</a>
 */
public class MapPrinter {
	
    /**
     * Prints the contents of the given map.
     * 
     * @param map the map to be printed
     * @return a string representation of the map
     */
	public static String toString(Map<?,?> map){
		StringBuffer buffer = new StringBuffer();
		
		for (Object key : map.keySet()) {
			if (key == null) {
			    buffer.append("null ");
			}
			else {
			    buffer.append(key + " (" + key.hashCode() + ") ");
			}
		    buffer.append(" = " + map.get(key) + "\n");
		}
		
		return buffer.toString();
	}
	
}
