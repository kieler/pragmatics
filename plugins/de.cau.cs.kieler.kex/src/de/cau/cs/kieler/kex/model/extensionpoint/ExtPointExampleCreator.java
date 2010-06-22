package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

/**
 * can be used for creating new extensions for KEX extension point.<br>
 * So it allows to create new examples or example categories.
 * 
 * @author pkl
 * 
 */
public class ExtPointExampleCreator {

	PluginXMLHandler xmlHandler;

	// TODO ist das so sinnvoll mit der KielerException im konstruktor.
	// vlt. sollte man lieber die saxparser factory in einer init() methode
	// aufnehmen, diese dann aufrufen und eine exception werfen lassen...
	public ExtPointExampleCreator() throws KielerException {
		new PluginXMLHandler();
	}

	public void addExtension(String projectId, String location, Example example)
			throws KielerException {
		validateProject(projectId, location);
		xmlHandler.addExtension(projectId, location, example);
	}

	/**
	 * creates example files to given project path
	 */
	public void createExampleResources(String location, ExampleResource resource) {
		// String workspacePath = ResourcesPlugin.getWorkspace().getRoot()
		// .getLocation().toString();
		// List<File> resources = resource.getResources();
		// for (File file : resources) {
		// File tmpFile = new File(workspacePath
		// + File.separator + ((projects.length > 0) ? projects[0].getName() :
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

	
	private void validateProject(String projectId, String location)
			throws KielerException {
		File file = new File(location);
		if (!file.exists()) {
			throw new KielerException("There is no file for given location:"
					+ location);
		}
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				projectId);
		if (project == null)
			throw new KielerException(
					"There is no project for given project id:" + projectId);
	}

}
