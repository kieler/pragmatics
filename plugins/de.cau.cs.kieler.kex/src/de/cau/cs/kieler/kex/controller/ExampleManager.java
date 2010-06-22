package de.cau.cs.kieler.kex.controller;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;

import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.collection.extensionpoint.ExtPointExampleCollector;
import de.cau.cs.kieler.kex.model.collection.online.OnlineExampleCollector;
import de.cau.cs.kieler.kex.model.creation.ExtPointExampleCreator;

public class ExampleManager {

	private ExtPointExampleCollector extPointExampleCollector;
	private OnlineExampleCollector onlineExampleCollector;
	private ExtPointExampleCreator extensionCreation;
	
// TODO Thesis, begr�nden weshalb hier instance genommen wurde.
	// da wir den Examplepool nicht jedes mal erneut laden wollen, wenn
	// wir darauf zugreifen wollen, k�nnen unter anderem viele werden.
	private static ExampleManager instance;
  
	private ExampleManager() {
    this.extPointExampleCollector = new ExtPointExampleCollector();
    extPointExampleCollector.loadExamples();
    this.onlineExampleCollector = new OnlineExampleCollector();
    onlineExampleCollector.loadExamples();
    extensionCreation = new ExtPointExampleCreator();
	}
	
	public synchronized static ExampleManager get(){
	  if(instance == null)
	    instance = new ExampleManager();
	  return instance;
	}

	/**
	 * reloads examples of extenders.
	 * 
	 */
	public void reload() {
		this.extPointExampleCollector.reload();
	}

	public Map<String, Example> getExtPointExamples() {
		return this.extPointExampleCollector.getExamplePool();
	}

	public List<String>getExtPointCategories(){
		return this.extPointExampleCollector.getCategoryPool();
	}
	
	public List<String> getExamplesAsStrings() {
		List<String> examplesAsStrings = extPointExampleCollector.getExamplesAsStrings();
				    examplesAsStrings.addAll(onlineExampleCollector.getExamplesAsStrings());
		return examplesAsStrings;
	}

	public List<String> getCategories(){
		//TODO onlineExampleCollector categories hinzufuegen
		return extPointExampleCollector.getCategoryPool();
	}
	
//	public List<String> getErrorsAsString() {
//		return ExceptionHandler.get().getExceptionsAsStrings();
//	}

	public IProject[] getLocalProjects(){
	  return extensionCreation.getLocalProjects();
	}
	
}
