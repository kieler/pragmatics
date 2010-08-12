package de.cau.cs.kieler.kex.model.online;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;

/**
 * this is an example for extending KEX with online examples.
 * 
 * @author pkl
 * 
 */
public class OnlineExampleCollector extends ExampleCollector {

	private final Map<String, Example> examplePool;

	private final List<String> categories;

	public OnlineExampleCollector() {
		examplePool = new HashMap<String, Example>();
		categories = new ArrayList<String>();
	}

	@Override
	public Map<String, Example> getExamplePool() {
		return examplePool;
	}

	@Override
	public void loadExamples() {
		// TODO adapt this for online examples...
	}

	public List<String> getCategories() {
		return categories;
	}

}
