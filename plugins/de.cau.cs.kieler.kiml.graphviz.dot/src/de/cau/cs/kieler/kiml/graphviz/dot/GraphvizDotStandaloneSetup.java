
package de.cau.cs.kieler.kiml.graphviz.dot;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class GraphvizDotStandaloneSetup extends GraphvizDotStandaloneSetupGenerated{

	public static void doSetup() {
		new GraphvizDotStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

