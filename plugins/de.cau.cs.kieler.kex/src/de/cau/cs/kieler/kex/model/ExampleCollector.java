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
package de.cau.cs.kieler.kex.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.KielerException;

public abstract class ExampleCollector {

	public abstract void load() throws KielerException;

	/**
	 * returns the loaded examplePool
	 * 
	 * @return a Map with exampleId as key and example as value.
	 */
	public abstract Map<String, Example> getExamplePool();

	public abstract SourceType getSourceType();

	public List<String> getExamplesAsStrings() {
		List<String> examplesAsString = new ArrayList<String>();
		for (Example example : getExamplePool().values()) {
			examplesAsString.add(example.toString());
		}
		return examplesAsString;
	}

}
