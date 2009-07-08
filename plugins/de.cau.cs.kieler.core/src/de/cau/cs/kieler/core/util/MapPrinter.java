/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
	
	public static String toString(Map<?,?> m){
		StringBuffer b = new StringBuffer();
		
		for (Object key : m.keySet()) {
			if(key != null){
				b.append(key.toString());
				b.append(" ("+key.hashCode()+") ");
				b.append(" = ");
				b.append(m.get(key)+"\n");
			}
		}
		
		return b.toString();
	}
	
}
