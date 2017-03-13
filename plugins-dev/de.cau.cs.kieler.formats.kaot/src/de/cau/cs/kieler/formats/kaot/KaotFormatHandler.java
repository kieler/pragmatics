/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.kaot;

import java.util.function.Predicate;

import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.formats.AbstractEmfHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.graphs.testcases.FiltersAndModifiers;
import de.cau.cs.kieler.graphs.testcases.ITestCaseGraphProvider;
import de.cau.cs.kieler.kaom.Entity;
import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 */
public class KaotFormatHandler extends AbstractEmfHandler<Entity> implements ITestCaseGraphProvider {

    public static final String KAOT_EXTENSION = "kaot"; 
   
    private IGraphTransformer<Entity, ElkNode> importer = new KaotImporter();
    
    private ViewContext viewContext;
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<Entity, ElkNode> getImporter() {
        return importer;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, Entity> getExporter() {
        throw new UnsupportedOperationException("Exporting KNodes to kaot is not supported.");
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*                                   Test Graph Creation                                 */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewContext getViewContext() {
        return this.viewContext;
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Iterable<Predicate<ElkNode>> getGraphFilters() {
        return Lists.newArrayList(FiltersAndModifiers.MINIMUM_NODES.apply(10));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<IGraphElementVisitor> getGraphModifiers() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceSet createResourceSet() {
        // create a new resource set and register the kaot extension to the
        // EMF resource factory
        ResourceSet resourceset = new ResourceSetImpl();
        resourceset.getPackageRegistry().put(KaomPackage.eNS_URI, KaomPackage.eINSTANCE);
        return resourceset;
    }
    
    private class KaotImporter implements IGraphTransformer<Entity, ElkNode> {
        /**
         * {@inheritDoc}
         */
        public void transform(TransformationData<Entity, ElkNode> data) {
            Entity model = data.getSourceGraph();
            if (model != null) {
                KlighdSynthesisProperties props = KlighdSynthesisProperties.create();
                
                // always use ptolemy diagram synthesis
                props.useDiagramSynthesis("de.cau.cs.kieler.scade.synthesis.OperatorEntityDiagramSynthesis");

                // configure diagram synthesis, eg like this:
                // props.configureSynthesisOptionValue(PtolemyDiagramSynthesis.SHOW_COMMENTS, false);
                
                // invoke the diagram synthesis
                ViewContext vc = LightDiagramServices.translateModel2(model, null, props);
                ElkNode elkGraph = layoutAndToElkNode(vc);
                
                data.getTargetGraphs().clear();
                data.getTargetGraphs().add(elkGraph);
                
                KaotFormatHandler.this.viewContext = vc;
            }
        }

        /**
         * {@inheritDoc}
         */
        public void transferLayout(TransformationData<Entity, ElkNode> data) {
            throw new UnsupportedOperationException(
                    "Applying layout to a kaot file is not supported.");
        }
        
    }
    
}
