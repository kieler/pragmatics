package de.cau.cs.kieler.kex.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ExampleCollector {

	public abstract void loadExamples();

	/**
	 * returns the loaded examplePool
	 * 
	 * @return a Map with exampleId as key and example as value.
	 */
	public abstract Map<String, Example> getExamplePool();

	public List<String> getExamplesAsStrings() {
		List<String> examplesAsString = new ArrayList<String>();
		for (Example example : getExamplePool().values()) {
			examplesAsString.add(example.toString());
		}
		return examplesAsString;
	}

}
