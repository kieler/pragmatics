package de.cau.cs.kieler.kex.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCollector;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCreator;
import de.cau.cs.kieler.kex.model.online.OnlineExampleCollector;

public class ExampleManager {

	private final ExtPointExampleCollector extensionCollector;
	private final OnlineExampleCollector onlineCollector;
	private ExtPointExampleCreator extensionCreation;

	private boolean isLoaded;

	// TODO Thesis, begr�nden weshalb hier instance genommen wurde.
	// da wir den Examplepool nicht jedes mal erneut laden wollen, wenn
	// wir darauf zugreifen wollen, k�nnen unter anderem viele werden.
	private static ExampleManager instance;

	private ExampleManager() {
		this.extensionCollector = new ExtPointExampleCollector();
		this.onlineCollector = new OnlineExampleCollector();
		try {
			extensionCreation = new ExtPointExampleCreator();
		} catch (KielerException e) {
			// FIXME plugin id sollte nicht null sein.
			StatusManager.getManager().addLoggedStatus(
					new Status(Status.ERROR, null, e.getMessage()));
		}
	}

	public void load() {
		if (!this.isLoaded) {
			this.isLoaded = true;
			extensionCollector.loadExamples();
			onlineCollector.loadExamples();
		}
	}

	public synchronized static ExampleManager get() {
		if (instance == null)
			instance = new ExampleManager();
		return instance;
	}

	/**
	 * reloads examples of extenders.
	 * 
	 */
	public void reload() {
		this.extensionCollector.loadExamples();
		this.onlineCollector.loadExamples();
	}

	public Map<String, Example> getExtPointExamples() {
		return this.extensionCollector.getExamplePool();
	}

	public List<String> getExtPointCategories() {
		return this.extensionCollector.getCategoryPool();
	}

	public List<String> getExamplesAsStrings() {
		List<String> examplesAsStrings = extensionCollector
				.getExamplesAsStrings();
		examplesAsStrings.addAll(onlineCollector.getExamplesAsStrings());
		return examplesAsStrings;
	}

	public List<String> getCategories() {
		// TODO onlineExampleCollector categories hinzufuegen
		return extensionCollector.getCategoryPool();
	}

	// public List<String> getErrorsAsString() {
	// return ExceptionHandler.get().getExceptionsAsStrings();
	// }

	/**
	 * lists all projects of instantiating workspace.
	 * 
	 * @return
	 */
	public IProject[] getLocalProjects() {
		// TODO in andere model klasse auslagern.
		return ResourcesPlugin.getWorkspace().getRoot().getProjects();
	}

	/**
	 * mapping of properties onto an example.
	 * 
	 * @param properties
	 *            , Map<String, Object>
	 * @return Example
	 */
	private Example mapToExample(Map<ExampleElement, Object> properties) {
		Example result = new Example(
				(String) properties.get(ExampleElement.ID),
				(String) properties.get(ExampleElement.NAME),
				Version.parseVersion((String) properties
						.get(ExampleElement.VERSION)));
		result.setDescription((String) properties
				.get(ExampleElement.DESCRIPTION));
		result.setContact((String) properties.get(ExampleElement.CONTACT));
		return result;
	}

	private void validateLocation(String projectId, String location)
			throws KielerException {
		// TODO to implement.S
	}

	/**
	 * 
	 * @param example
	 * @throws KielerException
	 */
	private void validateExample(Example example) throws KielerException {
		if (example.getId() == null)
			throw new KielerException("ID of an example could not be null.");
		boolean extPointContain = extensionCollector.getExamplePool()
				.containsKey(example.getId());
		boolean onlineContain = onlineCollector.getExamplePool().containsKey(
				example.getId());
		if (extPointContain || onlineContain)
			throw new KielerException(
					"Duplicate example id. Please choose an other one!");
	}

	public void exportExample(Map<ExampleElement, Object> properties)
			throws KielerException {
		String projectId = (String) properties.get(ExampleElement.PROJECTID);
		String location = (String) properties.get(ExampleElement.LOCATION);
		validateLocation(projectId, location);
		Example mappedExample = mapToExample(properties);
		validateExample(mappedExample);
		extensionCreation.addExtension(projectId, location, mappedExample);
	}

	public List<Example> getImportedExamples() {
		// Test Example

		ArrayList<Example> result = new ArrayList<Example>();
		Example testExample = new Example("ImportedTestExample",
				"ImportedTestExample");
		result.add(testExample);
		return result;
	}

	public void importExamples(String selectedProject,
			List<Example> selectedExamples) {
	}
}
