/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.format.moml;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.ptolemy.moml.DocumentRoot;
import org.ptolemy.moml.MomlPackage;
import org.ptolemy.moml.util.MomlResourceFactoryImpl;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.formats.AbstractEmfHandler;
import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.ptolemy.klighd.PtolemyDiagramSynthesis;

/**
 * Supports importing Ptolemy MoML files using KLighD's Ptolemy diagram synthesis via KIML's graph
 * formats service. This allows, for instance, to directly execute GrAna analyses on Ptolemy models.
 * 
 * @author uru
 */
public class MomlFormatHandler extends AbstractEmfHandler<DocumentRoot> {

    /** File extension of MoML files, i.e. Ptolemy models. */
    public static final String MOML_EXTENSION = "moml"; 
   
    private IGraphTransformer<DocumentRoot, KNode> importer = new MomlImporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<DocumentRoot, KNode> getImporter() {
        return importer;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<KNode, DocumentRoot> getExporter() {
        throw new UnsupportedOperationException("Exporting KNodes to MoML is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deserialize(String serializedGraph,
            TransformationData<DocumentRoot, KNode> transData) {
        // In default configuration of the xmi parser, unknown
        // features lead to a failure of the parsing process.
        // Moml model may contain svgs which are not part of the moml meta model.
        // Thus, we ignore but record unknown features.
        transData.getProperty(XML_OPTIONS).put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, true);

        super.deserialize(serializedGraph, transData);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceSet createResourceSet() {
        // create a new resource set and register the moml extension to the
        // EMF resource factory
        ResourceSet resourceset = new ResourceSetImpl();
        resourceset.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(MOML_EXTENSION, new MomlResourceFactoryImpl());
        resourceset.getPackageRegistry().put(MomlPackage.eNS_URI, MomlPackage.eINSTANCE);
        return resourceset;
    }

    private class MomlImporter implements IGraphTransformer<DocumentRoot, KNode> {
        /**
         * {@inheritDoc}
         */
        public void transform(TransformationData<DocumentRoot, KNode> data) {
            DocumentRoot model = data.getSourceGraph();
            if (model != null) {
                KlighdSynthesisProperties props = KlighdSynthesisProperties.create();
                
                // always use ptolemy diagram synthesis
                props.useDiagramSynthesis(PtolemyDiagramSynthesis.ID);

                // here we don't want comments
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.SHOW_COMMENTS, false);
                
                // some optional options
                // props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.FLATTEN, true);
                
                KNode graph = LightDiagramServices.translateModel(model, null, props);
                                        
                data.getTargetGraphs().clear();
                data.getTargetGraphs().add(graph);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void transferLayout(TransformationData<DocumentRoot, KNode> data) {
            throw new UnsupportedOperationException(
                    "Applying layout to a MoML file is not supported.");
        }
    }

}
