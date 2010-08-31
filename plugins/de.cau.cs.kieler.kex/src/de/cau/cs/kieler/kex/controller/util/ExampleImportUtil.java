package de.cau.cs.kieler.kex.controller.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

public class ExampleImportUtil {

	private final static String workspaceLocation = Platform.getLocation()
			.toString();

	/**
	 * @param selectedResource
	 * @param selectedExamples
	 * @throws KielerException
	 */
	public static List<String> importExamples(IPath selectedResource,
			List<Example> selectedExamples) throws KielerException {
		List<String> directOpens = new ArrayList<String>();
		for (Example example : selectedExamples) {

			List<ExampleResource> resources = example.getResources();
			String destFolder = workspaceLocation + selectedResource.toString()
					+ "/";
			Bundle bundle = Platform.getBundle(example.getNamespaceId());
			String rootResource = example.getRootResource();
			int exampleBeginIndex = 0;
			if (rootResource != null && rootResource.length() > 1) {
				exampleBeginIndex = rootResource.length();
			}

			for (ExampleResource resource : resources) {
				try {
					String localPath = resource.getLocalPath();
					String destPath = localPath.substring(exampleBeginIndex);
					// searching for subfiles and folders.
					switch (resource.getResourceType()) {
					case PROJECT:
						// TODO vielleicht geht das auch ohne project datei, da
						// man programmatisch bestimmt projecte erstellen kann.
						break;
					case FOLDER:
						IOHandler.createFolder(destFolder + "/" + destPath);
						break;
					case FILE:
						URL entry = bundle.getEntry(localPath);
						IOHandler.writeFile(entry, destFolder + destPath, true);
						if (resource.isDirectOpen())
							directOpens.add(destFolder + destPath);
						break;
					}
				} catch (FileNotFoundException e) {
					throw new KielerException("Can't import example!", e);
				} catch (IOException e1) {
					throw new KielerException("Can't import example!", e1);
				}
			}
		}
		return directOpens;

	}

}
