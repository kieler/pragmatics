package de.cau.cs.kieler.kex.model.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.kex.model.Example;

public abstract class ExampleCollector {

	// TODO ueberlegen, ob example collector als class definiert werden soll
	// bspw. examplesToString koennte dann darin implementiert sein.

	public abstract void loadExamples();

	/**
	 * returns the loaded examplePool
	 * 
	 * @return a Map with exampleId as key and example as value.
	 */
	public abstract Map<String, Example> getExamplePool();

	public void reload() {
		loadExamples();
	}

	public List<String> getExamplesAsStrings() {
		List<String> examplesAsString = new ArrayList<String>();
		for (Example example : getExamplePool().values()) {
			examplesAsString.add(example.toString());
		}
		return examplesAsString;
	}

}
