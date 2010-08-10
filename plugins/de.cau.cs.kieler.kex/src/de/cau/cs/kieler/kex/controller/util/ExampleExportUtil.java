package de.cau.cs.kieler.kex.controller.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.ImportType;
import de.cau.cs.kieler.kex.model.extensionpoint.ExtPointExampleCreator;

public class ExampleExportUtil {

	/**
	 * mapping of properties onto an example.
	 * 
	 * @param properties
	 *            , Map<String, Object>
	 * @return Example
	 */
	public static Example mapToExample(Map<ExampleElement, Object> properties) {
		Example result = new Example(
				(String) properties.get(ExampleElement.ID),
				(String) properties.get(ExampleElement.NAME),
				Version.parseVersion((String) properties
						.get(ExampleElement.VERSION)),
				(ImportType) properties.get(ExampleElement.IMPORTTYPE));
		result.setDescription((String) properties
				.get(ExampleElement.DESCRIPTION));
		result.setContact((String) properties.get(ExampleElement.CONTACT));
		// TODO resource krams fehlt hier noch.
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
		// TODO nullpointer check einbauen; überall prüfen, wenn ein File
		// erzeugt wird muss vorher ein nullcheck des strings erfolgen
		// ansonsten unerwartete exception...
		// muss schon vorher in der ui geprüft werden, sollte aber dennoch hier
		// abgeprüft werden.
		File destFile = new File(destLocation);
		if (!destFile.exists())
			throw new KielerException(
					"There is no file at destination location:" + destLocation);

		List<ExampleResource> resources = (List<ExampleResource>) properties
				.get(ExampleElement.RESOURCES);
		try {
			List<ExampleResource> destResources = createExampleResources(
					destFile, resources);
			mappedExample.addResources(destResources);
			extensionCreator.addExtension(destFile, mappedExample);
		} catch (KielerModelException e) {
			if (e.getModelObject() instanceof List<?>) {
				deleteExampleResource((List<ExampleResource>) e
						.getModelObject());
			}
			throw e;
		}
	}

	/**
	 * creates example files to given location
	 * 
	 * @param sourceProject
	 */
	private static List<ExampleResource> createExampleResources(File destFile,
			List<ExampleResource> resources) throws KielerException {
		List<ExampleResource> result = new ArrayList<ExampleResource>();
		for (ExampleResource exResource : resources) {
			ExampleResource resource = new ExampleResource();
			resource.setCategory(exResource.getCategory());
			resource.setHeadResource(exResource.isHeadResource());
			resource.getResources().addAll(
					copyFiles(exResource.getResources(), destFile.getPath()));
			// TODO muss das eine URL sein... geht da nicht auch ein
			// path, string oder file...
		}
		return result;
	}

	// TODO wenn der dialog resource anbietet zu dessen ordner es schon
	// TODO auch subdirs mit ansehen und hidden files berücksichtigen oder
	// nicht...
	// FIXME URLs ausbauen, das muss auch ohne gehen...
	private static List<URL> copyFiles(List<URL> resources, String destPath)
			throws KielerModelException {
		List<URL> result = new ArrayList<URL>();
		for (URL url : resources) {
			String path = url.getPath();
			// String[] split = path.split(String.valueOf(File.separatorChar));
			int nameStart = 0;
			for (int i = 0; i < path.length(); i++) {
				if (File.separatorChar == path.charAt(i))
					nameStart = i;
			}
			String fileName = path.substring(nameStart + 1);
			// String fileName = split[split.length - 1];
			StringBuffer sb = new StringBuffer();
			sb.append(destPath).append(File.separatorChar).append(fileName);

			try {
				// TODO unbedingt hier url ausbauen, braucht kein mensch.
				result.add(new URL("http", "localhost", sb.toString()));
				IOHandler.writeFile(new File(url.getPath()),
						new File(sb.toString()));
			} catch (MalformedURLException e) {
				throw new KielerModelException("could not transform \" " + sb
						+ "\" to URL!", result);
			} catch (IOException e) {
				throw new KielerModelException("could not transform \" " + sb
						+ "\" to URL!", result);
			}
		}
		return result;
	}

	private static void deleteExampleResource(List<ExampleResource> resources) {
		for (ExampleResource exampleResource : resources) {
			for (URL url : exampleResource.getResources())
				IOHandler.deleteFile(new File(url.getPath()));
		}
	}
}
