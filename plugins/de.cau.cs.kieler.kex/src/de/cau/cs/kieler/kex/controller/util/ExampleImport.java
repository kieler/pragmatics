package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

public class ExampleImport {

	private final static String workspaceLocation = Platform.getLocation()
			.toString();

	private final static String standardPicPath = "files/noPreview.png";
	private final static String kexNamespaceId = "de.cau.cs.kieler.kex";

	/**
	 * @param selectedResource
	 * @param selectedExamples
	 * @throws KielerException
	 */
	public static List<String> importExamples(final IPath selectedResource,
			final List<Example> selectedExamples, boolean checkDuplicate)
			throws KielerException {

		List<String> directOpens = new ArrayList<String>();
		List<String> finishedResources = new ArrayList<String>();

		StringBuilder destFolder = new StringBuilder();
		destFolder
				.append(workspaceLocation)
				.append((selectedResource != null ? selectedResource.toString()
						: "")).append("/");
		try {

			for (Example example : selectedExamples) {

				List<ExampleResource> resources = example.getResources();

				String rootDirectory = example.getRootDir();
				int exampleBeginIndex = 0;
				if (rootDirectory != null && rootDirectory.length() > 1) {
					exampleBeginIndex = rootDirectory.length();
				}

				handleResources(directOpens, resources, destFolder.toString(),
						example.getNamespaceId(), exampleBeginIndex,
						checkDuplicate, finishedResources);
			}
		} catch (KielerException e) {
			deleteResources(finishedResources);
			throw e;
		}
		return directOpens;
	}

	private static void deleteResources(List<String> finishedResources) {
		// TODO implement
	}

	private static void handleResources(List<String> directOpens,
			List<ExampleResource> resources, String destFolder,
			String nameSpaceId, int exampleBeginIndex, boolean checkDuplicate,
			List<String> finishedResources) throws KielerException {
		Bundle bundle = Platform.getBundle(nameSpaceId);

		for (ExampleResource resource : resources) {
			try {
				String localPath = resource.getLocalPath();
				String destPath = localPath.substring(exampleBeginIndex);

				switch (resource.getResourceType()) {
				case PROJECT:
					// creates a new project
					IProgressMonitor progressMonitor = new NullProgressMonitor();
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace()
							.getRoot();
					destPath = solveDuplicate(root, destPath);
					IProject project = root.getProject(destPath);
					project.create(progressMonitor);
					project.open(progressMonitor);
					destFolder = root.getLocation().toPortableString();
					break;

				case FOLDER:
					File destFile = new File(destFolder + "/" + destPath);
					finishedResources.add(destFile.getPath());
					if (checkDuplicate && destFile.exists()) {
						throw new KielerModelException(
								ErrorMessage.DUPLICATE_EXAMPLE,
								destFile.getName());
					}
					IOHandler.createFolder(destFolder + "/" + destPath);
					break;

				case FILE:
					URL entry = bundle.getEntry(localPath);
					String dest = destFolder + destPath;
					finishedResources.add(dest);
					IOHandler.writeFile(entry, dest, checkDuplicate);
					if (resource.isDirectOpen())
						directOpens.add(destFolder + destPath);
					break;

				}
			} catch (FileNotFoundException e) {
				throw new KielerException(ErrorMessage.NO_Import, e);
			} catch (IOException e1) {
				throw new KielerException(ErrorMessage.NO_Import, e1);
			} catch (CoreException e2) {
				throw new KielerException(ErrorMessage.NO_Import
						+ e2.getMessage());
			}
		}
	}

	private static String solveDuplicate(IWorkspaceRoot root, String destPath)
			throws KielerException {
		// FIXME it´s not enough, cause other resources of example would import
		// to wrong project. But that´s the way to solve that problem.
		// IProject project = root.getProject(destPath);
		// if (project != null && project.exists()) {
		// int i = 2;
		// while (true) {
		// IProject project2 = root.getProject(destPath + i);
		// if (project2 == null || !project2.exists()) {
		// return destPath + i;
		// }
		// }
		// }
		IProject project = root.getProject(destPath);
		if (project != null && project.exists()) {
			throw new KielerException(
					"Duplicate Project, you maybe inserted it before.");
		}
		return destPath;
	}

	public static InputStream loadOverviewPic(Example example)
			throws KielerException {
		Bundle bundle = Platform.getBundle(example.getNamespaceId());
		URL entry = bundle.getEntry(example.getOverviewPic());
		try {
			return entry.openStream();
		} catch (IOException e) {
			throw new KielerException(ErrorMessage.PREVIEW_LOAD_ERROR
					+ example.getTitle());
		}
	}

	public static InputStream loadStandardPic() {
		Bundle bundle = Platform.getBundle(ExampleImport.kexNamespaceId);
		URL entry = bundle.getEntry(ExampleImport.standardPicPath);
		try {
			return entry.openStream();
		} catch (IOException e) {
			// should not happen at runtime
			e.printStackTrace();
		}
		return null;
	}

	public static void validate(IPath selectedResource,
			List<Example> selectedExamples, boolean checkDuplicate)
			throws KielerException {
		if (selectedExamples == null || selectedExamples.size() == 0) {
			throw new KielerException(ErrorMessage.NO_EXAMPLE_SELECTED);
		}
		// could happen that user wants to import a project and a example in a
		// other project
		boolean allProjects = false;
		for (Example example : selectedExamples) {
			if (example.isProject()) {
				allProjects = true;
			} else {
				allProjects = false;
				break;
			}
		}
		// FIXME it is possible to choose a project and additional files at
		// export.
		// that would cause a BANG! :-), that shouldn´t be possible

		// projects do not need a destinatin resource
		if (!allProjects) {
			if (selectedResource == null
					|| selectedResource.segmentCount() == 0) {
				throw new KielerException(ErrorMessage.NO_DEST_SET);
			}
		}
	}
}
