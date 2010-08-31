package de.cau.cs.kieler.kex.controller.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
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
			List<Example> selectedExamples, boolean isQuickStart)
			throws KielerException {
		List<String> directOpens = new ArrayList<String>();
		if (isQuickStart) {

			// wurde example gew‰hlt? ansonsten standard example nutzen.

		}
		for (Example example : selectedExamples) {

			// check contain project,
			// falls ja projekt erzeuge und alle example dateien da rein
			// schmeiﬂen.

			List<ExampleResource> resources = example.getResources();
			String destFolder = workspaceLocation
					+ (selectedResource != null ? selectedResource.toString()
							: "") + "/";
			Bundle bundle = Platform.getBundle(example.getNamespaceId());
			String rootResource = example.getRootResource();
			int exampleBeginIndex = 0;
			if (rootResource != null && rootResource.length() > 1) {
				exampleBeginIndex = rootResource.length();
			}

			handleResources(directOpens, resources, destFolder, bundle,
					exampleBeginIndex);
		}
		return directOpens;

	}

	private static void handleResources(List<String> directOpens,
			List<ExampleResource> resources, String destFolder, Bundle bundle,
			int exampleBeginIndex) throws KielerException {
		for (ExampleResource resource : resources) {
			try {
				String localPath = resource.getLocalPath();
				String destPath = localPath.substring(exampleBeginIndex);
				// searching for subfiles and folders.
				switch (resource.getResourceType()) {
				case PROJECT:
					// creates a new project
					IProgressMonitor progressMonitor = new NullProgressMonitor();
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace()
							.getRoot();
					IProject project = root.getProject(destPath);
					project.create(progressMonitor);
					project.open(progressMonitor);
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
			} catch (CoreException e2) {
				throw new KielerException("Can't import example!", e2);
			}
		}
	}
}
