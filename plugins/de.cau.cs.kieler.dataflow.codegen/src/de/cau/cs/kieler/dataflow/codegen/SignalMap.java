/******************************************************************************
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
package de.cau.cs.kieler.dataflow.codegen;


import java.util.HashMap;
import java.util.HashSet;


import de.cau.cs.kieler.dataflow.*;

/**
 * @author ctr
 *
 * Basically a MultiMap implementation. Should use 
 * org.apache.commons.collections.MultiValueMap instead.
 */

public class SignalMap {
	private HashMap<Box, HashSet<String>> signals = new HashMap<Box, HashSet<String>>();
	
	/**
	 * @param box
	 * @return all signals ofthe given box
	 */
	public HashSet<String> get(final Box box){
		HashSet<String> s = signals.get(box);
		if (s == null){
			return new HashSet<String>();
		}else{
			return s;
		}
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void put(final Box key, final String value){
		HashSet<String> s = signals.get(key);
		if(s==null){
			s = new HashSet<String>();
			signals.put(key, s);
		}
		s.add(value);
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void remove(final Box key, final String value){
		if(signals.containsKey(key)){
			signals.get(key).remove(value);
		}
	}
}
