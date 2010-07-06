package de.cau.cs.kieler.kex.model.extensionpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;

public class ExtPointExampleCollector extends ExampleCollector {

	// ID of KEX extension-points
	private static final String KEX_EXT_ID = "de.cau.cs.kieler.kex";

	// leichter HashMap, da wir eine Pruefung auf enthalten sein machen.
	// und wir dann bei einer liste komplett ueber alle elemente iterieren
	// muessten
	// um die id abzugleichen.

	private final Map<String, Example> examplePool;

	private final List<String> categoryPool;

	public ExtPointExampleCollector() {
		categoryPool = new ArrayList<String>();
		examplePool = new HashMap<String, Example>();

	}

	public void collectCategories(IConfigurationElement categoryElement) {
		String categoryId = categoryElement.getAttribute("id");
		if (categoryId == null || categoryId.length() < 4) {
			// TODO StatusManager als globalen Exceptionhandler
			// ansprechen...
		} else {
			if (!getCategoryPool().contains(categoryId))
				getCategoryPool().add(categoryId);
			else {
				// TODO StatusManager ansprechen

			}
		}
	}

	/**
	 * loads examples of extenders.
	 */
	@Override
	public void loadExamples() {

		IConfigurationElement[] configElements = Platform
				.getExtensionRegistry().getConfigurationElementsFor(KEX_EXT_ID);
		// Platform.getUserLocation()
		// Versuche für die Projekt Workspace Ansprechung und so weiter...
		// IExtension[] extensions =
		// Platform.getExtensionRegistry().getExtensions("de.cau.cs.kieler.core.kex.model");
		//
		// IProject project =
		// ResourcesPlugin.getWorkspace().getRoot().getProject(
		// "de.cau.cs.kieler.kex");

		for (IConfigurationElement element : configElements) {
			try {
				String elementName = element.getName();
				if ("example".equals(elementName)) {
					String exampleId = element.getAttribute("id");
					if (getExamplePool().containsKey(exampleId))
						continue;
					Example example = ExtPointCollectionUtil.toExample(element);
					this.examplePool.put(exampleId, example);
				} else if ("category".equals(elementName)) {
					collectCategories(element);

				} else {
					// ExceptionHandler
					// .get()
					// .addUnique(
					// new KielerException(
					// "Extension: "
					// + element.getName()
					// + ", was found after searching with example id: "
					// + KEX_EXT_ID));
					// TODO Statusmanager benutzen
				}
			} catch (Exception e) {
				// if (e instanceof KielerException)
				// TODO auch hier besser machen, den ganzen mechanismus und
				// natuerlich ueber statusmanager
				// ExceptionHandler.get().add(
				// new KielerModelException(e.getMessage(), element));
				// else
				// e.printStackTrace();
			}
		}
	}

	@Override
	public Map<String, Example> getExamplePool() {
		return this.examplePool;
	}

	public List<String> getCategoryPool() {
		return categoryPool;
	}

}