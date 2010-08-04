package de.cau.cs.kieler.kex.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.util.ExampleExportUtil;
import de.cau.cs.kieler.kex.controller.util.ExampleImportUtil;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ImportType;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCollector;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCreator;
import de.cau.cs.kieler.kex.model.online.OnlineExampleCollector;

public class ExampleManager {

	private static ExampleManager instance;

	private boolean isLoaded;

	private final ExtPointExampleCollector extensionCollector;
	private final OnlineExampleCollector onlineCollector;

	private ExtPointExampleCreator extensionCreation;

	// TODO wenn in ui ein editor offen ist, dann macht er den wizard nicht auf.

	// TODO Thesis, begrï¿½nden weshalb hier instance genommen wurde.
	// da wir den Examplepool nicht jedes mal erneut laden wollen, wenn
	// wir darauf zugreifen wollen, kï¿½nnen unter anderem viele werden.

	// TODO category refactoring, das wird so alles nicht mehr gebraucht...
	// da wir die kategorien aus den examples filtern.

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

	public synchronized static ExampleManager get() {
		if (instance == null)
			instance = new ExampleManager();
		return instance;
	}

	/**
	 * loads examples of extenders, if not loaded before.
	 */
	public void load() {
		if (!this.isLoaded) {
			loadExamples();
			// after completely loaded
			this.isLoaded = true;
		}
	}

	/**
	 * reloads examples of extenders. Doesn't care about examples loaded before.
	 * 
	 */
	public void reload() {
		loadExamples();
	}

	private void loadExamples() {
		this.extensionCollector.loadExamples();
		this.onlineCollector.loadExamples();
	}

	public Map<String, Example> getExtPointExamples() {
		return this.extensionCollector.getExamplePool();
	}

	public List<String> getCategories() {
		// TODO onlineExampleCollector categories hinzufuegen
		return extensionCollector.getCategoryPool();
	}

	public void importExamples(IPath selectedResource,
			List<Example> selectedExamples) throws KielerException {
		ExampleImportUtil.importExamples(selectedResource, selectedExamples);
	}

	public void exportExample(Map<ExampleElement, Object> properties)
			throws KielerException {
		// TODO implementiernen der project id.
		String projectId = (String) properties.get(ExampleElement.PROJECTID);

		// just a test
		projectId = "testpro";
		// TODO implementiernen der location.
		// String destLocation = (String) properties
		// .get(ExampleElement.DEST_LOCATION);
		// TODO plattform unabhängiger pfadbau in externe methode auslagern,
		// wenn nciht schon der richte pfad vom ui runtergereicht wird.
		// just a test
		StringBuffer destLocation = new StringBuffer();
		destLocation.append("E:").append(File.separatorChar);
		destLocation.append("bachelorarbeit").append(File.separatorChar);
		destLocation.append("3_6 Workspace").append(File.separatorChar);
		destLocation.append("de.cau.cs.kieler.core.kex.models");
		Example mappedExample = ExampleExportUtil.mapToExample(properties);
		ExampleExportUtil.checkDuplicate(mappedExample, extensionCollector,
				onlineCollector);
		extensionCreation.addExtension(projectId, destLocation.toString(),
				mappedExample);
	}

	/**
	 * gives a list of examples which are already imported in local workspace.
	 * 
	 * @return
	 */
	public List<Example> getImportedExamples() {
		// TODO implement

		// Test Example
		ArrayList<Example> result = new ArrayList<Example>();
		Example testExample = new Example("ImportedTestExample",
				"ImportedTestExample", ImportType.EXTENSIONPOINT);
		result.add(testExample);
		return result;
	}

}
