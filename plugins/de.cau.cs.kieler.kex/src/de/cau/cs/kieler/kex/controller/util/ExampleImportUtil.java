package de.cau.cs.kieler.kex.controller.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

public class ExampleImportUtil {

	public static void importExamples(IPath selectedResource,
			List<Example> selectedExamples) throws KielerException {
		for (Example example : selectedExamples) {

			List<ExampleResource> resources = example.getResources();
			// TODO pr�fen, ob parameter sinnvoll...
			// wenn mehrere examples mit gleichem namen laufen, brauchen wir
			// eine art index
			// bzw. den identifierer... als datei oder in project properties
			// oder im namen
			// src/Hankees.txt
			String localWorkspaceLocation = Platform.getLocation().toString();
			String destFolder = localWorkspaceLocation
					+ selectedResource.toString() + "/" + example.getName()
					+ "/";
			ExampleIOUtil.createFolder(destFolder);
			for (ExampleResource exampleResource : resources) {
				for (URL resource : exampleResource.getResources()) {
					try {
						String resourcePath = resource.getPath();
						if (ExampleIOUtil.isFolder(resourcePath)) {
							ExampleIOUtil.createFolder(resourcePath);
						} else {
							String[] resourceSplits = resourcePath.split("/");
							ExampleIOUtil
									.writeFile(
											resource,
											destFolder
													+ resourceSplits[resourceSplits.length - 1],
											true);
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

}
