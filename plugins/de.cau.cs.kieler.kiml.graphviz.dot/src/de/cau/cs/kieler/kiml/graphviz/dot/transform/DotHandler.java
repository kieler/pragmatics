/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.dot.transform;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.graphviz.dot.GraphvizDotStandaloneSetup;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphvizModel;
import de.cau.cs.kieler.kiml.service.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;

/**
 * A transformer for Graphviz Dot.
 *
 * @author msp
 */
public class DotHandler extends AbstractEmfHandler<GraphvizModel> {
    
    /** the injector for creation of resources. */
    private static Injector injector;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileExtension() {
        return "graphviz_dot";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceSet createResourceSet() {
        if (injector == null) {
            injector = new GraphvizDotStandaloneSetup().createInjectorAndDoEMFRegistration();
        }
        return injector.getInstance(XtextResourceSet.class);
    }

    private DotImporter importer = new DotImporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<GraphvizModel, KNode> getImporter() {
        return importer;
    }

    private DotExporter exporter = new DotExporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, GraphvizModel> getExporter() {
        return exporter;
    }
    
}
