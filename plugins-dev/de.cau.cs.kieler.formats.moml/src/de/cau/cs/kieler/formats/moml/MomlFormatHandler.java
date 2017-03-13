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

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.ptolemy.moml.DocumentRoot;
import org.ptolemy.moml.MomlPackage;
import org.ptolemy.moml.util.MomlResourceFactoryImpl;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.formats.AbstractEmfHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.graphs.testcases.FiltersAndModifiers;
import de.cau.cs.kieler.graphs.testcases.ITestCaseGraphProvider;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.ptolemy.klighd.PtolemyDiagramSynthesis;

/**
 * Supports importing Ptolemy MoML files using KLighD's Ptolemy diagram synthesis via KIML's graph
 * formats service. This allows, for instance, to directly execute GrAna analyses on Ptolemy models.
 * 
 * @author uru
 */
public class MomlFormatHandler extends AbstractEmfHandler<DocumentRoot> 
    implements ITestCaseGraphProvider {

    /** File extension of MoML files, i.e. Ptolemy models. */
    public static final String MOML_EXTENSION = "moml"; 
   
    private IGraphTransformer<DocumentRoot, ElkNode> importer = new MomlImporter();
    
    /** The {@link ViewContext} that has been used by klighd. */
    private ViewContext viewContext;
    
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

    
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*                                   Test Graph Creation                                 */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    private static boolean FLATTEN = false;
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Iterable<Predicate<ElkNode>> getGraphFilters() {
        List<Predicate<ElkNode>> predicates = Lists.newArrayList(
                FiltersAndModifiers.MINIMUM_NODES.apply(10),
                FiltersAndModifiers.NO_HYPERNODES);
        if (FLATTEN) {
            predicates.add(FiltersAndModifiers.NO_HIERARCHICAL_NODES); 
        }
        return predicates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<IGraphElementVisitor> getGraphModifiers() {
        return Collections.emptyList();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ViewContext getViewContext() {
        return this.viewContext;
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
                // or directors, or properties 
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.SHOW_DIRECTORS, false);
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.SHOW_PROPERTIES, false);
                
                // some optional options
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.TRANSFORM_STATES, false);
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.INITIALLY_COLLAPSED, false);
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.SHOW_PORT_LABELS, false);
                
                // flatten the graph?
                props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.FLATTEN, FLATTEN) ;
                
                // invoke the diagram synthesis
                ViewContext vc = LightDiagramServices.translateModel2(model, null, props);
                
                // remove isolated nodes
                KNode kgraph = vc.getViewModel();
                Lists.newArrayList(Iterators.filter(kgraph.eAllContents(), KNode.class))
                    .stream().filter(n -> n.getChildren().isEmpty())
                    .filter(n -> n.getOutgoingEdges().size() + n.getIncomingEdges().size() == 0)
                    .forEach(n -> n.setParent(null));
               
                // convert kgraph to elkgraph
                ElkNode elkGraph = layoutAndToElkNode(vc);
                data.getTargetGraphs().clear();
                data.getTargetGraphs().add(elkGraph);
                
                MomlFormatHandler.this.viewContext = vc;
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

}
