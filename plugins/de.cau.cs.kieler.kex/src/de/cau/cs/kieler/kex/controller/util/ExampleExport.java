package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.controller.ErrorMessage;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.controller.ExportResource;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.SourceType;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCreator;

public class ExampleExport {

	/**
	 * mapping of properties onto an example.
	 * 
	 * @param properties
	 *            , Map<String, Object>
	 * @param rootResource
	 * @return Example
	 */
	@SuppressWarnings("unchecked")
	public static Example mapToExample(Map<ExampleElement, Object> properties,
			String rootResource) {
		Example result = new Example((String) properties
				.get(ExampleElement.TITLE), (SourceType) properties
				.get(ExampleElement.SOURCETYPE));
		result.setDescription((String) properties
				.get(ExampleElement.DESCRIPTION));
		result.setContact((String) properties.get(ExampleElement.CONTACT));
		result.addCategories((List<String>) properties
				.get(ExampleElement.CATEGORIES));
		result.setAuthor((String) properties.get(ExampleElement.AUTHOR));
		result.setRootDir(rootResource);
		return result;
	}

	/**
	 * 
	 * @param example
	 * @throws KielerException
	 */
	public static void checkDuplicate(String exampleTitle,
			ExampleCollector... collectors) throws KielerException {
		if (exampleTitle == null)
			throw new KielerException("ID of an example could not be null.");
		for (ExampleCollector collector : collectors) {
			if (collector.getExamplePool().containsKey(exampleTitle))
				throw new KielerException(
						"Duplicate example id. Please choose an other one!");
		}
	}

	@SuppressWarnings("unchecked")
	public static void export(final Map<ExampleElement, Object> properties,
			ExtPointExampleCreator extensionCreator,
			final ExampleCollector... collectors) throws KielerException {

		// first duplicate check
		ExampleExport.checkDuplicate((String) properties
				.get(ExampleElement.TITLE), collectors);

		Example mappedExample = ExampleExport.mapToExample(properties,
				(String) properties.get(ExampleElement.DEST_LOCATION));

		File destFile = new File(mappedExample.getRootDir());
		if (!destFile.exists())
			throw new KielerException(ErrorMessage.DESTFILE_NOT_EXIST
					+ mappedExample.getRootDir());

		List<ExportResource> exportResources = (List<ExportResource>) properties
				.get(ExampleElement.RESOURCES);
		extensionCreator.copyResources(destFile, exportResources);

		mappedExample.addResources(ExampleExport
				.mapToExampleResource(exportResources));
		try {
			extensionCreator.addExtension(destFile, mappedExample,
					(List<String>) properties
							.get(ExampleElement.CREATE_CATEGORIES));
		} catch (KielerModelException e) {
			if (e.getModelObject() instanceof List<?>) {
				extensionCreator.deleteExampleResource((List<IPath>) e
						.getModelObject());
			}
			throw e;
		}
	}

	private static List<ExampleResource> mapToExampleResource(
			List<ExportResource> exportResources) {
		List<ExampleResource> result = new ArrayList<ExampleResource>();
		for (ExportResource exRe : exportResources) {
			ExampleResource resultItem = new ExampleResource(exRe
					.getLocalPath().toPortableString(), ExampleResource.Type
					.valueOf(exRe.getResource().getClass().getSimpleName()));
			resultItem.setDirectOpen(exRe.isDirectOpen());
			result.add(resultItem);
		}
		return result;
	}
}
