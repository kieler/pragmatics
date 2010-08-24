package de.cau.cs.kieler.kex.model.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.SourceType;

/**
 * this is an example for extending KEX with online examples.
 * 
 * @author pkl
 * 
 */
public class DBExampleCollector extends ExampleCollector {

	private final Map<String, Example> examplePool;

	private final List<String> categories;

	private final DatabaseHandler database;

	public DBExampleCollector() {
		examplePool = new HashMap<String, Example>();
		categories = new ArrayList<String>();
		database = new DatabaseHandler();
	}

	@Override
	public Map<String, Example> getExamplePool() {
		return examplePool;

	}

	@Override
	public void loadExamples() {
		database.loadExample();
	}

	public List<String> getCategories() {
		return categories;
	}

	@Override
	public SourceType getSourceType() {
		return SourceType.PUBLIC;
	}

}
