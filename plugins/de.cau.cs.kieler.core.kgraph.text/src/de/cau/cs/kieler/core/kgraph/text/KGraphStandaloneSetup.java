/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kgraph.text;

/**
 * Initialization support for running Xtext languages without equinox extension registry.
 * 
 * @author msp
 */
public class KGraphStandaloneSetup extends KGraphStandaloneSetupGenerated {

    /**
     * Create an injector and do EMF registration.
     */
    public static void doSetup() {
        new KGraphStandaloneSetup().createInjectorAndDoEMFRegistration();
    }
    
}
