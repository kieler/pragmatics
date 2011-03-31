
package de.cau.cs.kieler.kaom.text;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class KaomStandaloneSetup extends KaomStandaloneSetupGenerated{

	public static void doSetup() {
		new KaomStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

