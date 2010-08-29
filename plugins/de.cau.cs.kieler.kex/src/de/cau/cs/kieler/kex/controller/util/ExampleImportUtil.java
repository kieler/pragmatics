package de.cau.cs.kieler.kex.controller.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
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
	 * 
	 * @param selectedResource
	 * @param selectedExamples
	 * @throws KielerException
	 */
	public static void importExamples(IPath selectedResource,
			List<Example> selectedExamples) throws KielerException {
		for (Example example : selectedExamples) {

			List<ExampleResource> resources = example.getResources();
			String destFolder = workspaceLocation + selectedResource.toString()
					+ "/";
			// not permantly create a example parent folder with its name.
			// it will be triggered by the export mechanism.

			Bundle bundle = Platform.getBundle(example.getNamespaceId());
			String rootResource = example.getRootResource();
			int exampleBeginIndex = 0;
			if (rootResource != null) {
				exampleBeginIndex = rootResource.length();
			}

			for (ExampleResource resource : resources) {
				try {
					String localPath = resource.getLocalPath();
					String destPath = localPath.substring(exampleBeginIndex);
					// searching for subfiles and folders.
					Enumeration<?> dict = bundle.findEntries(localPath, null,
							false);
					if (dict != null) {
						// geht nur wenn das ganze committed ist, denn dann ist
						// auch in jedem folder eine .svn datei.
						IOHandler.createFolder(destFolder + "/" + destPath);
					} else {
						URL entry = bundle.getEntry(localPath);
						IOHandler.writeFile(entry, destFolder + destPath, true);
					}
				} catch (FileNotFoundException e) {
					throw new KielerException("Can't import example!", e);
				} catch (IOException e1) {
					throw new KielerException("Can't import example!", e1);
				}
			}
		}

	}
}
