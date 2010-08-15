package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.ExportResource;
import de.cau.cs.kieler.kex.model.ExportType;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCreator;

public class ExampleExportUtil {

	private static final Path workspacePath = (Path) ResourcesPlugin
			.getWorkspace().getRoot().getLocation();

	/**
	 * mapping of properties onto an example.
	 * 
	 * @param properties
	 *            , Map<String, Object>
	 * @return Example
	 */
	@SuppressWarnings("unchecked")
	public static Example mapToExample(Map<ExampleElement, Object> properties) {
		Example result = new Example(
				(String) properties.get(ExampleElement.ID),
				(String) properties.get(ExampleElement.NAME),
				Version.parseVersion((String) properties
						.get(ExampleElement.VERSION)),
				(ExportType) properties.get(ExampleElement.EXPORTTYPE));
		result.setDescription((String) properties
				.get(ExampleElement.DESCRIPTION));
		result.setContact((String) properties.get(ExampleElement.CONTACT));
		result.setCategories((List<String>) properties
				.get(ExampleElement.CATEGORIES));
		return result;
	}

	/**
	 * 
	 * @param example
	 * @throws KielerException
	 */
	public static void checkDuplicate(Example example,
			ExampleCollector... collectors) throws KielerException {
		if (example.getId() == null)
			throw new KielerException("ID of an example could not be null.");
		for (ExampleCollector collector : collectors) {
			if (collector.getExamplePool().containsKey(example.getId()))
				throw new KielerException(
						"Duplicate example id. Please choose an other one!");
		}
	}

	@SuppressWarnings("unchecked")
	public static void exportExample(
			final Map<ExampleElement, Object> properties,
			ExtPointExampleCreator extensionCreator,
			final ExampleCollector... collectors) throws KielerException {

		Example mappedExample = ExampleExportUtil.mapToExample(properties);
		ExampleExportUtil.checkDuplicate(mappedExample, collectors);

		String destLocation = (String) properties
				.get(ExampleElement.DEST_LOCATION);
		File destFile = new File(destLocation);
		// TODO that has to be made updatable.
		if (!destFile.exists())
			throw new KielerException(
					"There is no file at destination location:" + destLocation);

		List<ExportResource> resources = (List<ExportResource>) properties
				.get(ExampleElement.RESOURCES);
		try {
			List<IPath> destResources = copyResources(destFile, resources);
			extensionCreator.addExtension(destFile, mappedExample,
					destResources);
		} catch (KielerModelException e) {
			if (e.getModelObject() instanceof List<?>) {
				deleteExampleResource((List<IPath>) e.getModelObject());
			}
			throw e;
		}
	}

	/**
	 * creates example files to given location
	 * 
	 * @param sourceProject
	 */
	private static List<IPath> copyResources(File destFile,
			List<ExportResource> resources) throws KielerException {
		List<IPath> errorList = new ArrayList<IPath>();
		List<IPath> result = new ArrayList<IPath>();
		try {
			for (ExportResource resource : resources) {
				copyFile(resource, destFile.getPath(), errorList);
				result.add(resource.getLocalPath());
			}
		} catch (KielerException e) {
			throw new KielerModelException(e.getLocalizedMessage(), errorList);
		}
		return result;
	}

	private static void copyFile(ExportResource resource, String destPath,
			List<IPath> errorList) throws KielerException {
		StringBuffer destLocation = new StringBuffer();
		try {

			String sourcePath = ExampleExportUtil.workspacePath
					.toPortableString()
					+ resource.getResource().getFullPath().toPortableString();

			destLocation.append(destPath).append(File.separatorChar)
					.append(resource.getLocalPath());
			Path destination = new Path(destLocation.toString());
			errorList.add(destination);

			IOHandler.writeFile(new File(sourcePath), destination.toFile());
		} catch (IOException e) {
			// TODO ErrorHandling überlegen.
		}
	}

	private static void deleteExampleResource(List<IPath> resources) {
		for (IPath path : resources)
			IOHandler.deleteFile(path.toFile());
	}
}
