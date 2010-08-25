package de.cau.cs.kieler.kex.controller.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;

public class ExampleImportUtil {

	private final static String workspaceLocation = Platform.getLocation()
			.toString();

	public static void importExamples(IPath selectedResource,
			List<Example> selectedExamples) throws KielerException {
		for (Example example : selectedExamples) {

			List<String> resources = example.getResources();
			String destFolder = workspaceLocation + selectedResource.toString()
					+ "/";
			// not permantly create a example parent folder with its name.
			// it will be triggered by the export mechanism.
			// createFolder(destFolder+ "/"+ example.getName() + "/");

			Bundle bundle = Platform.getBundle(example.getNamespaceId());

			for (String path : resources) {
				try {
					// searching for subfiles and folders.
					Enumeration<?> dict = bundle.findEntries(path, null, false);
					// TODO filter for .svn and .cvs files have to be added
					// properly you can set this to bundle.findEntries()...
					// adding subs

					if (dict != null) {
						IOHandler.createFolder(destFolder + "/" + path);
					} else {
						IOHandler.writeFile(bundle.getEntry(path), destFolder
								+ path, true);
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
