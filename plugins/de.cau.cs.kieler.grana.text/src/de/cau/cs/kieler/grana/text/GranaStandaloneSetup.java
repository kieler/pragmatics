/*
* generated by Xtext
*/
package de.cau.cs.kieler.grana.text;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class GranaStandaloneSetup extends GranaStandaloneSetupGenerated{

	public static void doSetup() {
		new GranaStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
