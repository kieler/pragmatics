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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.graphviz.dot.dot.GraphvizModel;

/**
 * A graph format handler for Graphviz Dot.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class DotFormatHandler extends AbstractEmfHandler<GraphvizModel> {
    
    /** The graph format identifier for Graphviz Dot. */
    public static final String ID = "org.graphviz.dot";
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected String getFileExtension() {
        return "graphviz_dot";
    }
    
    @Inject private Provider<XtextResourceSet> resourceSetProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResourceSet createResourceSet() {
        return resourceSetProvider.get();
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
