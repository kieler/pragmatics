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
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.ExtPointConstants;
import de.cau.cs.kieler.kex.model.ImportType;

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

		Example example = null;
		String idAttribute = exampleElement.getAttribute(ExtPointConstants.ID);
		String nameAttribute = exampleElement
				.getAttribute(ExtPointConstants.NAME);
		String versionAttribute = exampleElement
				.getAttribute(ExtPointConstants.VERSION);

		// TODO eine art versions validator bauen. evtl. kann Version sowas
		// schon
		if (versionAttribute != null)
			// FIXME IllegalArgumentException sehr wahrscheinlich, da das
			// version feld
			// ein freier string, min. default besser noch regex.
			example = new Example(idAttribute, nameAttribute,
					Version.parseVersion(versionAttribute),
					ImportType.EXTENSIONPOINT);
		else
			example = new Example(idAttribute, nameAttribute,
					ImportType.EXTENSIONPOINT);
		example.setDescription(exampleElement
				.getAttribute(ExtPointConstants.DESCRIPTION));
		example.setContact(exampleElement
				.getAttribute(ExtPointConstants.CONTACT));
		String exNamespaceId = exampleElement.getNamespaceIdentifier();
		example.setNamespaceId(exNamespaceId);
		List<ExampleResource> exampleResources = ExtPointCollectionUtil
				.filterExampleResources(exampleElement, exNamespaceId);
		example.addResources(exampleResources);
		return example;
	}

	public static List<ExampleResource> filterExampleResources(
			final IConfigurationElement exampleElement, String exNamespaceId)
			throws KielerException {
		List<ExampleResource> exampleResources = new ArrayList<ExampleResource>();
		for (IConfigurationElement configElement : exampleElement
				.getChildren(ExtPointConstants.EXAMPLE_RESOURCE)) {
			ExampleResource exampleResource = new ExampleResource();
			exampleResource.setCategory(configElement
					.getAttribute(ExtPointConstants.CATEGORY));
			exampleResource.setHeadResource(Boolean.valueOf(configElement
					.getAttribute(ExtPointConstants.IS_HEAD_RESOURCE)));
			addResource(exampleResource, exNamespaceId, configElement);
			exampleResources.add(exampleResource);
		}
		return exampleResources;
	}

	private static void addResource(ExampleResource exResource,
			final String exNamespaceId,
			final IConfigurationElement configElement) throws KielerException {
		Bundle bundle = Platform.getBundle(exNamespaceId);
		URL resourceURL = bundle.getResource(configElement
				.getAttribute(ExtPointConstants.RESOURCE));
		validateURL(resourceURL);
		exResource.addResource(resourceURL);

		// searching for subfiles and folders.
		Enumeration<?> dict = bundle.findEntries(resourceURL.getPath(), null,
				true);
		// TODO filter for .svn and .cvs files have to be added
		// properly you can set this to bundle.findEntries()...
		// adding subs

		if (dict != null) {
			while (dict.hasMoreElements()) {
				URL dictElement = (URL) dict.nextElement();
				// TODO ausnahme liste draus machen, eigene klasse mit ausnahmen
				// und die dann alle abprüfen,
				// damit noch ausnahmen hinzugefügt, bzw. entfernt werden können

				// oder dateien mit prefix "." entfernen, also keine versteckten
				// dateien mit berücksichtigen
				if (!dictElement.getPath().contains(".svn")
						&& !dictElement.getPath().contains(".cvs"))
					exResource.addResource(dictElement);
			}
		}
	}

	private static void validateURL(final URL resourceURL)
			throws KielerException {
		if (resourceURL == null || resourceURL.getPath().length() < 4) {
			throw new KielerModelException("Filtered URL is not valid.",
					resourceURL);
		}
	}

}