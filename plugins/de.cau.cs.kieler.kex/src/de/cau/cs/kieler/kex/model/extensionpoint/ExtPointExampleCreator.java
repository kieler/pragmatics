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
	public void createExampleResources(ExampleResource resource) {
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

	/**
	 * lists all projects of working workspace.
	 * 
	 * @return
	 */
	public IProject[] getLocalProjects() {
		// TODO am besten nicht mit den ganzen projekten umher werfen,
		// vielleicht kann man die dateipfade irgendwie raus holen...
		return ResourcesPlugin.getWorkspace().getRoot().getProjects();
	}

	// TODO give workspace as default folder for export new examples from local
	// project.
	// public void getWorkspaceProjects() {
	// // Location instanceLocation = Platform.getInstanceLocation();
	// // IPath location = Platform.getLocation();
	// IExtensionRegistry reg = Platform.getExtensionRegistry();
	// IExtensionPoint point = reg
	// .getExtensionPoint("org.eclipse.core.filesystem.filesystems");
	// IConfigurationElement[] elements = point.getConfigurationElements();
	//
	// System.out.println("There are " + elements.length + " implementations");
	// for (int i = 0; i < elements.length; i++) {
	// for (String attrString : elements[i].getAttributeNames()) {
	// System.out.print(elements[i].getName());
	// System.out.print(" " + attrString + ": "
	// + elements[i].getAttribute(attrString));
	// System.out.println();
	// }
	// }
	// }

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
