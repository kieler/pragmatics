package de.cau.cs.kieler.kex.controller.util;

import java.util.Map;

import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleElement;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleCollector;
import de.cau.cs.kieler.kex.model.ImportType;

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
				(String) properties.get(ExampleElement.ID), (String) properties
						.get(ExampleElement.NAME), Version
						.parseVersion((String) properties
								.get(ExampleElement.VERSION)),
				(ImportType) properties.get(ExampleElement.IMPORTTYPE));
		result.setDescription((String) properties
				.get(ExampleElement.DESCRIPTION));
		result.setContact((String) properties.get(ExampleElement.CONTACT));
		return result;
	}

	public static void validateLocation(String projectId, String location)
			throws KielerException {
		// TODO to implement.S
	}

	/**
	 * 
	 * @param example
	 * @throws KielerException
	 */
	public static void validateExample(Example example,
			ExampleCollector... collectors) throws KielerException {
		if (example.getId() == null)
			throw new KielerException("ID of an example could not be null.");
		for (ExampleCollector collector : collectors) {
			if (collector.getExamplePool().containsKey(example.getId()))
				throw new KielerException(
						"Duplicate example id. Please choose an other one!");
		}
	}

}
