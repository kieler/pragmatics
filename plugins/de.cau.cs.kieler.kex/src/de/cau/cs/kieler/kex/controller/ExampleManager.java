package de.cau.cs.kieler.kex.controller;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCollector;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCreator;
import de.cau.cs.kieler.kex.model.online.OnlineExampleCollector;

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
    try {
		extensionCreation = new ExtPointExampleCreator();
	} catch (KielerException e) {
		//FIXME plugin id sollte nicht null sein.
		StatusManager.getManager().addLoggedStatus(new Status(Status.ERROR, null, e.getMessage()));
	}
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
	
	/**
	 * lists all projects of instantiating workspace.
	 * 
	 * @return
	 */
	public IProject[] getLocalProjects(){
		//TODO in andere model klasse auslagern.
		return ResourcesPlugin.getWorkspace().getRoot().getProjects();
	}
	
	public void addExample(String projectId, String location, Map<String,Object> properties){
		//TODO ueberlegen, ob hier auch Example uebergeben werden koennte problematisch ist nur,
		// dass dann VIEW element von MODEL enthaelt, nicht gut nach mvc
		try {
			extensionCreation.addExtension(projectId, location, mapToExample(properties));
		} catch (KielerException e) {
			StatusManager.getManager().addLoggedStatus(new Status(Status.ERROR, projectId, e.getMessage()));

		}
	}
	/**
	 * mapping of properties onto an example.
	 * @param properties, Map<String, Object>
	 * @return Example
	 */
	public Example mapToExample(Map<String, Object> properties){
		//TODO implementieren
		Example result = new Example(null, null);
//		try {
			// zusaetzliche sicherung...
//			validateExample(result);
			
//		} catch (KielerException e) {
			//TODO vernuenftig behandeln, es reicht nicht ihn nur in den statusmanager zu ueberreichen,
			// wie kann dann die action abgebrochen werden, bzw der wizard dennoch weiterlaufen...???
//		}
		return result;
	}
	
	// TODO sollte schon waehrend des erstelllens aufgerufen werden, um sicher zu gehen,dass kein mist eingecheckt wird.
	/**
	 * 
	 * @param example
	 * @throws KielerException
	 */
	public void validateExample(Example example) throws KielerException{
		if(example.getId() == null)
			throw new KielerException("ID of an example could not be null.");
		boolean extPointContain = extPointExampleCollector.getExamplePool().containsKey(example.getId());
		boolean onlineContain = onlineExampleCollector.getExamplePool().containsKey(example.getId());
		if(extPointContain || onlineContain)
			throw new KielerException("Duplicate example id. Please choose an other one!");
	}

}
