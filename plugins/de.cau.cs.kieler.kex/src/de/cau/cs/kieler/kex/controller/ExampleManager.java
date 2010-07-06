package de.cau.cs.kieler.kex.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCollector;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCreator;
import de.cau.cs.kieler.kex.model.online.OnlineExampleCollector;

public class ExampleManager {

	private final ExtPointExampleCollector extensionCollector;
	private final OnlineExampleCollector onlineCollector;
	private ExtPointExampleCreator extensionCreation;

	private boolean isLoaded;

	// TODO Thesis, begrï¿½nden weshalb hier instance genommen wurde.
	// da wir den Examplepool nicht jedes mal erneut laden wollen, wenn
	// wir darauf zugreifen wollen, kï¿½nnen unter anderem viele werden.
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

	// TODO überlegen, ob die load methode in die abstrakte example collector
	// klasse soll.
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
	 * mapping of properties onto an example.
	 * 
	 * @param properties
	 *            , Map<String, Object>
	 * @return Example
	 */
	private Example mapToExample(Map<ExampleElement, Object> properties) {
		Example result = new Example(
				(String) properties.get(ExampleElement.ID), (String) properties
						.get(ExampleElement.NAME), Version
						.parseVersion((String) properties
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

	// TODO auch diese hier in eine geeignete klasse auslagern..
	public void importExamples(IPath selectedResource,
			List<Example> selectedExamples) throws KielerException {
		for (Example example : selectedExamples) {

			// TODO auslagern
			// createHeadFolder()

			List<ExampleResource> resources = example.getResources();
			// TODO prüfen, ob parameter sinnvoll...
			// wenn mehrere examples mit gleichem namen laufen, brauchen wir
			// eine art index
			// bzw. den identifierer... als datei oder in project properties
			// oder im namen
			// TODO geht so nicht, falscher src und dest param.
			String destFolder = selectedResource.toString()
					+ /* File.separator */"/" + example.getName();
			for (ExampleResource exampleResource : resources) {
				for (File resource : exampleResource.getResources())
					copyfile(resource.getAbsolutePath(), destFolder + /*
																	 * File.separator
																	 */"/"
							+ resource.getName(), false);
			}
		}
		// for (File file : resources) {
		// File tmpFile = new File(workspacePath
		// + File.separator + ((projects.length > 0) ?
		// projects[0].getName() :
		// "test")+ File.separator +"test.file");
		//
		// if (!tmpFile.exists()){
		// tmpFile.getParentFile().mkdirs();
		// try {
		// tmpFile.createNewFile();
		// }
		// catch (IOException e) {
		// //TODO fehlerhandling ueberlegen
		// e.printStackTrace();
		// }
		// }
		// }
	}

	/**
	 * 
	 * @param srFile
	 *            , source file
	 * @param dtFile
	 *            , destination file
	 * @param isUpdated
	 *            , boolean
	 */
	private static void copyfile(String srFile, String dtFile, boolean isUpdated) {
		try {
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);

			// For Append the file.
			OutputStream out = new FileOutputStream(f2, isUpdated);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			System.out.println("File copied.");
		} catch (FileNotFoundException ex) {
			System.out
					.println(ex.getMessage() + " in the specified directory.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * lists all projects of instantiating workspace.
	 * 
	 * @return
	 */
	public IProject[] getLocalProjects() {
		// TODO in andere model klasse auslagern.
		return ResourcesPlugin.getWorkspace().getRoot().getProjects();
	}

}
