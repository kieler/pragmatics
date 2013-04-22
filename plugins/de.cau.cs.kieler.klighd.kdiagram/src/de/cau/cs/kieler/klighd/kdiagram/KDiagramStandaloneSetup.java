
package de.cau.cs.kieler.klighd.kdiagram;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class KDiagramStandaloneSetup extends KDiagramStandaloneSetupGenerated{

	public static void doSetup() {
		new KDiagramStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

