/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.moml;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.ptolemy.moml.DocumentRoot;
import org.ptolemy.moml.MomlPackage;
import org.ptolemy.moml.util.MomlResourceFactoryImpl;

import de.cau.cs.kieler.formats.AbstractEmfHandler;
import de.cau.cs.kieler.formats.GraphFormatData;
import de.cau.cs.kieler.formats.GraphFormatsService;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.formats.kgraph.KGraphHandler;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.kgraph.KNode;
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
   
    private IGraphTransformer<DocumentRoot, ElkNode> importer = new MomlImporter();
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<DocumentRoot, ElkNode> getImporter() {
        return importer;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, DocumentRoot> getExporter() {
        throw new UnsupportedOperationException("Exporting KNodes to MoML is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deserialize(String serializedGraph,
            TransformationData<DocumentRoot, ElkNode> transData) {
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

    private class MomlImporter implements IGraphTransformer<DocumentRoot, ElkNode> {
        /**
         * {@inheritDoc}
         */
        public void transform(TransformationData<DocumentRoot, ElkNode> data) {
            DocumentRoot model = data.getSourceGraph();
            if (model != null) {
                KlighdSynthesisProperties props = KlighdSynthesisProperties.create();
                
                // always use ptolemy diagram synthesis
                props.useDiagramSynthesis(PtolemyDiagramSynthesis.ID);

                // here we don't want comments
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.SHOW_COMMENTS, false);
                
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.SHOW_DIRECTORS, false);
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.SHOW_PROPERTIES, false);
                
                // some optional options
                // props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.FLATTEN, true);
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.TRANSFORM_STATES, false);
                
                KNode kgraph = LightDiagramServices.translateModel(model, null, props);
                
                
                // now make it a elk graph
                GraphFormatData kgraphFormat =
                        GraphFormatsService.getInstance().getFormatData(KGraphHandler.ID);
                @SuppressWarnings("unchecked")
                IGraphTransformer<KNode, ElkNode> importer =
                        (IGraphTransformer<KNode, ElkNode>) kgraphFormat.getHandler().getImporter();
                TransformationData<KNode, ElkNode> td = new TransformationData<>();
                td.setSourceGraph(kgraph);
                importer.transform(td);
                ElkNode elkGraph = td.getTargetGraphs().get(0);
          
                exportLayoutGraph(elkGraph);

                data.getTargetGraphs().clear();
                data.getTargetGraphs().add(elkGraph);
            }
        }

        /**
         * {@inheritDoc}
         */
        public void transferLayout(TransformationData<DocumentRoot, ElkNode> data) {
            throw new UnsupportedOperationException(
                    "Applying layout to a MoML file is not supported.");
        }
    }
    
    
    /**
     * Export the given layout graph in KGraph format.
     * 
     * @param graph the parent node of the layout graph
     */
    protected void exportLayoutGraph(final ElkNode graph) {
        URI exportUri = getExportURI(graph);
        if (exportUri != null) {
            // save the graph to a file
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource = resourceSet.createResource(exportUri);
            resource.getContents().add(graph);
            try {
                resource.save(Collections.emptyMap());
            } catch (IOException e) {
                // ignore the exception and abort the layout graph exporting
            }
        }
    }
    
    /**
     * Return a file URI to use for exporting graphs.
     * 
     * @param graph the parent node of the layout graph
     */
    protected URI getExportURI(final ElkNode graph) {
        String path = ElkUtil.debugFolderPath("moml_export")
                + Integer.toHexString(graph.hashCode()) + ".elkg";
        return URI.createFileURI(path);
    }

}
