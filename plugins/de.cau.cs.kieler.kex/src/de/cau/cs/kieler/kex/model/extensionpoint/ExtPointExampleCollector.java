package de.cau.cs.kieler.kex.model.extensionpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
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
					String exampleId = element
							.getAttribute(ExtPointConstants.ID);
					if (getExamplePool().containsKey(exampleId)) {
						// TODO darf eigentlich nicht passieren
						// RUNTIME Exception schmeiﬂen...
						// oder einfach annehmen, dass dies nicht geschieht
						continue;
					}
					Example example = ExtPointCollectionUtil.toExample(element);
					this.examplePool.put(exampleId, example);
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
}