package de.cau.cs.kieler.kex.model.extensionpoint;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.osgi.framework.Version;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.ExampleResource;

public class ExtPointCollectionUtil {

	// TODO gedanken drueber machen, ob das ganze model package in das model
	// plugin ueberfuehrt werden sollte

	/**
	 * creates an new Example with properties of given exampleElement
	 * which should generally comes from extension of extension point.
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
		// TODO Versuch: ueber getAttributes elemente holen... also generischer
		// loesen.
		//TODO ID null pr�fung
		String idAttribute = exampleElement.getAttribute("id");
		//TODO name null pr�fung
		String nameAttribute = exampleElement.getAttribute("name");
		String versionAttribute = exampleElement.getAttribute("version");
		
		// TODO eine art versions validator bauen. evtl. kann Version sowas schon
		if (versionAttribute != null)
			// FIXME IllegalArgumentException sehr wahrscheinlich, da das
			// version feld
			// ein freier string, min. default besser noch regex.
			example = new Example(idAttribute,nameAttribute,
			    Version.parseVersion(versionAttribute));
		else
			example = new Example(idAttribute,nameAttribute);
		example.setDescription(exampleElement.getAttribute("description"));
		example.setContact(exampleElement.getAttribute("contact"));
		List<ExampleResource> exampleResources = ExtPointCollectionUtil
				.filterExampleResources(exampleElement);
		example.addResources(exampleResources);
		return example;
	}

  public static List<ExampleResource> filterExampleResources(final
      IConfigurationElement exampleElement) throws KielerException {

      // TODO resource stimmt nicht, muss noch richtig ueberdacht werden.
      List<ExampleResource> exampleResources = new ArrayList<ExampleResource>();
    for (IConfigurationElement configElement : exampleElement
        .getChildren("resource")) {
      ExampleResource exampleResource = new ExampleResource();
      exampleResource.setCategory(configElement.getAttribute("category"));
      exampleResource.addResource(filterResource(configElement));
      exampleResources.add(exampleResource);
    }
    return exampleResources;
  }
  
  private static File filterResource(final IConfigurationElement configElement) {
    String resourcePath = configElement.getAttribute("resource");
    if (resourcePath == null || resourcePath.length() < 4) {
      // throw new KielerModelException(...);
    }
    // TODO hier muss sicherlich noch der Projekt Pfad mit angegeben werden.
    File file = new File(resourcePath);
    validateFile(file);
    return file;
  }
  
  private static void validateFile(final File file) {
	  
  }
	
}