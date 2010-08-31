package de.cau.cs.kieler.kex.model.extensionpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.ExampleResource;
import de.cau.cs.kieler.kex.model.SourceType;

public class ExtPointExampleCollector extends ExampleCollector {

	// leichter HashMap, da wir eine Pruefung auf enthalten sein machen.
	// und wir dann bei einer liste komplett ueber alle elemente iterieren
	// muessten
	// um die id abzugleichen.

	private final Map<String, Example> examplePool;

	private List<String> categoryPool;

	public ExtPointExampleCollector() {
		examplePool = new HashMap<String, Example>();

	}

	/**
	 * loads examples of extenders.
	 * 
	 * @throws KielerException
	 */
	@Override
	public void loadExamples() throws KielerException {
		if (this.categoryPool == null) {
			this.categoryPool = new ArrayList<String>();
		}
		IConfigurationElement[] configElements = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						ExtPointConstants.EXT_POINT);
		for (IConfigurationElement element : configElements) {
			try {
				String elementName = element.getName();
				if (ExtPointConstants.EXAMPLE.equals(elementName)) {
					String exampleTitle = element
							.getAttribute(ExtPointConstants.TITLE);
					if (getExamplePool().containsKey(exampleTitle)) {
						// TODO darf eigentlich nicht passieren
						// RUNTIME Exception schmei�en...
						// oder einfach annehmen, dass dies nicht geschieht
						continue;
					}
					Example example = toExample(element);
					this.examplePool.put(exampleTitle, example);
				} else if (ExtPointConstants.CATEGORY.equals(elementName)) {
					collectCategory(element);
				}
			} catch (InvalidRegistryObjectException e) {
				throw new KielerException("Error while loading example \""
						+ element.getAttribute(ExtPointConstants.ID) + "\". "
						+ e.getLocalizedMessage());
			} catch (IllegalArgumentException e1) {
				throw new KielerException("Error while loading example \""
						+ element.getAttribute(ExtPointConstants.ID) + "\". "
						+ e1.getLocalizedMessage());
			} catch (KielerException e2) {
				throw new KielerException("Error while loading example \""
						+ element.getAttribute(ExtPointConstants.ID) + "\". "
						+ e2.getLocalizedMessage());
			}
		}
	}

	public void collectCategory(IConfigurationElement categoryElement) {
		String categoryId = categoryElement.getAttribute(ExtPointConstants.ID);
		if (categoryId == null || categoryId.length() < 4) {
			// TODO StatusManager als globalen Exceptionhandler
			// ansprechen...
		} else {
			if (!getCategories().contains(categoryId))
				getCategories().add(categoryId);
			else {
				// TODO StatusManager ansprechen
			}
		}
	}

	@Override
	public Map<String, Example> getExamplePool() {
		return this.examplePool;
	}

	public List<String> getCategories() {
		if (this.categoryPool == null) {
			this.categoryPool = new ArrayList<String>();
			loadCategories();
		}
		return categoryPool;
	}

	private void loadCategories() {
		IConfigurationElement[] configElements = Platform
				.getExtensionRegistry().getConfigurationElementsFor(
						ExtPointConstants.EXT_POINT);
		for (IConfigurationElement element : configElements) {
			if (ExtPointConstants.CATEGORY.equals(element.getName())) {
				collectCategory(element);
			}
		}
	}

	@Override
	public SourceType getSourceType() {
		return SourceType.KIELER;
	}

	public static Example toExample(final IConfigurationElement exampleElement)
			throws InvalidRegistryObjectException, IllegalArgumentException,
			KielerException {

		String titleAttr = exampleElement.getAttribute(ExtPointConstants.TITLE);
		String versionAttr = exampleElement
				.getAttribute(ExtPointConstants.VERSION);
		// FIXME IllegalArgumentException sehr wahrscheinlich, da das
		// version feld
		// ein freier string, min. default besser noch regex.
		Example example = new Example(titleAttr, Version
				.parseVersion(versionAttr), SourceType.KIELER);
		example.setDescription(exampleElement
				.getAttribute(ExtPointConstants.DESCRIPTION));
		example.setContact(exampleElement
				.getAttribute(ExtPointConstants.CONTACT));
		example
				.setAuthor(exampleElement
						.getAttribute(ExtPointConstants.AUTHOR));
		String exNamespaceId = exampleElement.getNamespaceIdentifier();
		example.setNamespaceId(exNamespaceId);
		example.setRootResource(exampleElement
				.getAttribute(ExtPointConstants.ROOT_DIRECTORY));

		List<String> categories = filterElement(exampleElement,
				ExtPointConstants.CATEGORY, ExtPointConstants.ID);
		example.addCategories(categories);
		example.addResources(filterExampleResource(exampleElement));
		return example;
	}

	private static List<String> filterElement(
			IConfigurationElement exampleElement, String elementName,
			String attributeName) {
		List<String> result = new ArrayList<String>();
		for (IConfigurationElement configurationElement : exampleElement
				.getChildren(elementName)) {
			String catName = configurationElement.getAttribute(attributeName);
			if (catName != null)
				result.add(catName);
		}
		return result;
	}

	private static List<ExampleResource> filterExampleResource(
			IConfigurationElement exampleElement) {
		List<ExampleResource> result = new ArrayList<ExampleResource>();
		for (IConfigurationElement configurationElement : exampleElement
				.getChildren(ExtPointConstants.EXAMPLE_RESOURCE)) {
			String resourceType = configurationElement
					.getAttribute(ExtPointConstants.RESOURCE_TYPE);
			String localPath = configurationElement
					.getAttribute(ExtPointConstants.LOCAL_PATH);
			if (resourceType != null && localPath != null) {
				ExampleResource exRe = new ExampleResource(localPath,
						ExampleResource.Type
								.valueOf(resourceType.toUpperCase()));
				String direct_open = configurationElement
						.getAttribute(ExtPointConstants.DIRECT_OPEN);
				if (direct_open != null)
					exRe.setDirectOpen(Boolean.parseBoolean(direct_open));
				result.add(exRe);
			}
		}
		return result;
	}
}