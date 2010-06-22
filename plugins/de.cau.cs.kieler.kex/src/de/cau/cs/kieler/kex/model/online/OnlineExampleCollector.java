package de.cau.cs.kieler.kex.model.online;

import java.util.HashMap;
import java.util.Map;

import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;


public class OnlineExampleCollector extends ExampleCollector{

	
	private Map<String, Example> examplePool;
	
	public OnlineExampleCollector(){
		examplePool = new HashMap<String, Example>();
	}
	
	public Map<String, Example> getExamplePool() {
		return examplePool;
	}

	public void loadExamples() {
		// TODO Auto-generated method stub
	}

}
