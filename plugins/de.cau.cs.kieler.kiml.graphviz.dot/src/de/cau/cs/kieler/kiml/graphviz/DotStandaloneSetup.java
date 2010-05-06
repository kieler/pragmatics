
package de.cau.cs.kieler.kiml.graphviz;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class DotStandaloneSetup extends DotStandaloneSetupGenerated{

	public static void doSetup() {
		new DotStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

