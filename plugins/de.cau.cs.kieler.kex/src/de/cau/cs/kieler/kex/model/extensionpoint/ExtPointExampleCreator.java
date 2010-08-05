package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
		xmlHandler = new PluginXMLHandler();
	}

	public void addExtension(final String projectId, final String destLocation,
			Example example) throws KielerException {
		File destFile = new File(destLocation);
		if (!destFile.exists()) {
			throw new KielerException(
					"There is no file at destination location:" + destLocation);
		}
		IProject sourceProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectId);
		if (sourceProject == null)
			throw new KielerException("There is no project for given project:"
					+ projectId);
		xmlHandler.addExtension(destFile, example);

		// TODO example resource aus project id zusammenbasteln bzw. resource
		// mapping betreiben !VIEL SPASS!
		createExampleResources(destFile, example.getResources());
	}

	/**
	 * creates example files to given location
	 */
	private void createExampleResources(File destFile,
			List<ExampleResource> resources) {
		String workspacePath = ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toString();
		for (ExampleResource resource : resources) {
			// TODO muss das eine URL sein... geht da nicht auch ein
			// path, string oder file...
			// for(URL url : resource.getResources())
			// Just a Test
			String loc = workspacePath + File.separator + "test";

			File tmpFile = new File(loc);
			if (!tmpFile.exists()) {
				tmpFile.getParentFile().mkdirs();
				try {
					tmpFile.createNewFile();
				} catch (IOException e) {
					// TODO fehlerhandling ueberlegen
					e.printStackTrace();
				}
			}
		}
	}

}
