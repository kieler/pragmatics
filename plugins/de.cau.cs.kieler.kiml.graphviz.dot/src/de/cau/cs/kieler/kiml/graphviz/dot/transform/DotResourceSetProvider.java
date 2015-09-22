/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.dot.transform;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * A graph format handler for Graphviz Dot. Instantiate through a proper injector:
 * 
 * <code>
 * new GraphvizDotStandaloneSetup()
 *     .createInjectorAndDoEMFRegistration()
 *     .getInstance(DotResourceSetProvider.class);
 * </code>
 *
 * @author cds
 */
public final class DotResourceSetProvider {
    
    @Inject private Provider<XtextResourceSet> resourceSetProvider;
    
    /**
     * Returns a new resource set for dot files.
     * 
     * @return resource set.
     */
    public ResourceSet createResourceSet() {
        return resourceSetProvider.get();
    }
    
}
