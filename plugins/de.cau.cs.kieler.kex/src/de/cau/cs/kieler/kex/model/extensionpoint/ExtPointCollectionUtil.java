package de.cau.cs.kieler.kex.model.extensionpoint;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExtPointConstants;
import de.cau.cs.kieler.kex.model.SourceType;

public class ExtPointCollectionUtil {

	// TODO gedanken drueber machen, ob das ganze model package in das model
	// plugin ueberfuehrt werden sollte

	/**
	 * creates an new Example with properties of given exampleElement which
	 * should generally comes from extension of extension point.
	 * 
	 * @param exampleElement
	 * @return
	 * @throws InvalidRegistryObjectException
	 *             , if configurationElement.getAttribute(...) throws it.
	 * @throws IllegalArgumentException
	 *             , if Version.parseVersion(...) throws it.
	 * @throws KielerException
	 * @see IConfigurationElement
	 * @see Version
	 */
	public static Example toExample(final IConfigurationElement exampleElement)
			throws InvalidRegistryObjectException, IllegalArgumentException,
			KielerException {

		String idAttribute = exampleElement.getAttribute(ExtPointConstants.ID);
		String nameAttribute = exampleElement
				.getAttribute(ExtPointConstants.NAME);
		String versionAttribute = exampleElement
				.getAttribute(ExtPointConstants.VERSION);
		// FIXME IllegalArgumentException sehr wahrscheinlich, da das
		// version feld
		// ein freier string, min. default besser noch regex.
		Example example = new Example(idAttribute, nameAttribute,
				Version.parseVersion(versionAttribute), SourceType.KIELER);
		example.setDescription(exampleElement
				.getAttribute(ExtPointConstants.DESCRIPTION));
		example.setContact(exampleElement
				.getAttribute(ExtPointConstants.CONTACT));
		String exNamespaceId = exampleElement.getNamespaceIdentifier();
		example.setNamespaceId(exNamespaceId);
		List<String> categories = filterExampleCategories(exampleElement);
		example.addCategories(categories);
		example.setHeadResource(filterHeadResource(exampleElement,
				exNamespaceId));
		// TODO Pr�fung, ob head_resource schon in resources enthalten ansonsten
		// hinzuf�gen.
		List<URL> resources = ExtPointCollectionUtil.filterExampleResources(
				exampleElement, exNamespaceId);
		example.addResources(resources);
		return example;
	}

	private static URL filterHeadResource(IConfigurationElement exampleElement,
			String exNamespaceId) {
		Bundle bundle = Platform.getBundle(exNamespaceId);
		String attribute = exampleElement
				.getAttribute(ExtPointConstants.HEAD_RESOURCE);
		if (attribute != null)
			return bundle.getResource(attribute);
		return null;
	}

	private static List<String> filterExampleCategories(
			IConfigurationElement exampleElement) {
		List<String> result = new ArrayList<String>();
		for (IConfigurationElement configurationElement : exampleElement
				.getChildren(ExtPointConstants.CATEGORY)) {
			result.add(configurationElement.getAttribute(ExtPointConstants.ID));
		}
		return result;
	}

	public static List<URL> filterExampleResources(
			final IConfigurationElement exampleElement, String exNamespaceId)
			throws KielerException {
		List<URL> result = new ArrayList<URL>();
		for (IConfigurationElement configElement : exampleElement
				.getChildren(ExtPointConstants.EXAMPLE_RESOURCE)) {

			Bundle bundle = Platform.getBundle(exNamespaceId);
			URL resourceURL = bundle.getResource(configElement
					.getAttribute(ExtPointConstants.RESOURCE));
			validateURL(resourceURL);
			result.add(resourceURL);

			// searching for subfiles and folders.
			Enumeration<?> dict = bundle.findEntries(resourceURL.getPath(),
					null, true);
			if (dict != null) {
				while (dict.hasMoreElements()) {
					URL dictElement = (URL) dict.nextElement();
					// hidden files check
					if (!dictElement.getFile().startsWith(".")) {
						result.add(dictElement);
					}
				}
			}
		}
		return result;
	}

	private static void validateURL(final URL resourceURL)
			throws KielerException {
		if (resourceURL == null || resourceURL.getPath().length() < 4) {
			throw new KielerModelException("Filtered URL is not valid.",
					resourceURL);
		}
	}

}