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
package de.cau.cs.kieler.formats.sccharts;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.circuit.Actor;
import de.cau.cs.kieler.circuit.klighd.CircuitDiagramSynthesis;
import de.cau.cs.kieler.formats.AbstractEmfHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.graphs.testcases.FiltersAndModifiers;
import de.cau.cs.kieler.graphs.testcases.ITestCaseGraphProvider;
import de.cau.cs.kieler.kico.CompilationResult;
import de.cau.cs.kieler.kico.KielerCompiler;
import de.cau.cs.kieler.kico.KielerCompilerContext;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;
import de.cau.cs.kieler.sccharts.State;
import de.cau.cs.kieler.sccharts.klighd.synthesis.SCChartsSynthesis;
import de.cau.cs.kieler.scg.SCGraph;
import de.cau.cs.kieler.scg.klighd.SCGraphDiagramSynthesis;

/**
 * Supports the generation of test graphs from SCCharts, SCGraphs, and Circuits.
 */
public class SCChartsFormatHandler extends AbstractEmfHandler<State> 
    implements ITestCaseGraphProvider {

    public static final String SCT_EXTENSION = "sct"; 
   
    private IGraphTransformer<State, ElkNode> importer = new Importer();
    
    /** The {@link ViewContext} that has been used by klighd. */
    private ViewContext viewContext;
    
    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<State, ElkNode> getImporter() {
        return importer;
    }

    /**
     * {@inheritDoc}
     */
    public IGraphTransformer<ElkNode, State> getExporter() {
        throw new UnsupportedOperationException("Exporting SCCharts is not supported.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResourceSet createResourceSet() {
        return new ResourceSetImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ViewContext getViewContext() {
        return this.viewContext;
    }

    
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    // !! This is the switch to configure the output graph type
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    private Target target = Target.SCG_SEQUENTIAL;
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<Predicate<ElkNode>> getGraphFilters() {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<IGraphElementVisitor> getGraphModifiers() {
        List<IGraphElementVisitor> visis = Lists.newArrayList();
            
        switch (target) {
            case SCG:
            case SCG_BASIC_BLOCK:
            case SCG_SEQUENTIAL:
                visis.add(FiltersAndModifiers.REMOVE_UNCONNECTED_PORTS);
                break;
        }

        switch (target) {
            case SCG_SEQUENTIAL:
                visis.add(
                    FiltersAndModifiers.REMOVE_PROPERTY.apply(LayeredOptions.NORTH_OR_SOUTH_PORT));
            break;
        }

        return visis;
    }
    
    enum Target {
        SCCHART,
        SCCHART_CORE,
        SCG,
        SCG_BASIC_BLOCK,
        SCG_SEQUENTIAL,
        CIRCUIT
    }
    
    private class Importer implements IGraphTransformer<State, ElkNode> {
        /**
         * {@inheritDoc}
         */
        public void transform(TransformationData<State, ElkNode> data) {
            State model = data.getSourceGraph();
                        
            // the following fall-throughs are intended
            // the order in which the transformations are added doesn't matter, kico figures it out
            CompileChain cc = new CompileChain();
            switch (target) {
            case CIRCUIT:
                cc.toCircuit();
            case SCG_SEQUENTIAL:
                cc.toSequential();
            case SCG_BASIC_BLOCK:
                cc.toBasicBlocks();
            case SCG:
                cc.toSCG();
            case SCCHART_CORE:
                cc.toNormalized();
            default:
            }

            // Compile with KiCo
            String compileChain = cc.compileString();
            KielerCompilerContext context = new KielerCompilerContext(compileChain, model);
            
            CompilationResult result = KielerCompiler.compile(context);
            EObject resultModel = result.getEObject();
            if (resultModel == null) {
                throw new IllegalArgumentException("KIELER Compiler was not able to compile input model.");
            } else if (!result.getPostponedErrors().isEmpty() || !result.getPostponedWarnings().isEmpty()) {
                throw new IllegalArgumentException(
                        "KIELER Compiler was not able to compile input model. Error or warning during compilation.");
            }
            
            if (resultModel != null) {

                // configure based on the type of the result model, SCChart, SCGraph, or Circuit 
                KlighdSynthesisProperties props = KlighdSynthesisProperties.create();
                if (resultModel instanceof State) {
                    // sccharts
                    props.useDiagramSynthesis(SCChartsSynthesis.ID);
                    
                } else if (resultModel instanceof SCGraph) {
                    // scg
                    props.useDiagramSynthesis(SCGraphDiagramSynthesis.ID);
                    
                    // some basic styling
                    props.configureSynthesisOptionValue(SCGraphDiagramSynthesis.SHOW_CAPTION, true);
                    props.configureSynthesisOptionValue(SCGraphDiagramSynthesis.SHOW_SHADOW, true);
                    
                    // omit elements that are not related to layout
                    props.configureSynthesisOptionValue(SCGraphDiagramSynthesis.SHOW_DEPENDENCIES, false);
                    props.configureSynthesisOptionValue(SCGraphDiagramSynthesis.SHOW_SCHEDULINGPATH, false);
                    props.configureSynthesisOptionValue(SCGraphDiagramSynthesis.SHOW_POTENTIALPROBLEMS, false);
                    
                    // similar to ptolemy's 'flatten' option
                    props.configureSynthesisOptionValue(SCGraphDiagramSynthesis.SHOW_HIERARCHY, true);
                    
                    // when compiling until basic blocks, we want to see them, but not the scheduling blocks
                    // the options have no effect for the basic scgs or the sequentialized scgs
                    props.configureSynthesisOptionValue(SCGraphDiagramSynthesis.SHOW_BASICBLOCKS, true);
                    props.configureSynthesisOptionValue(SCGraphDiagramSynthesis.SHOW_SCHEDULINGBLOCKS, false);
                    
                } else if (resultModel instanceof Actor) {
                    // circuit
                    props.useDiagramSynthesis(CircuitDiagramSynthesis.ID);
                    
                    props.configureSynthesisOptionValue(CircuitDiagramSynthesis.SHOW_TICK, false);
                    props.configureSynthesisOptionValue(CircuitDiagramSynthesis.SHOW_RESET, false);
                    props.configureSynthesisOptionValue(CircuitDiagramSynthesis.SHOW_ALL_REGIONS, false);

                } else {
                    throw new IllegalStateException("Unknonw model type: " + resultModel.getClass());
                }
                
                ViewContext vc = LightDiagramServices.translateModel2(resultModel, null, props);
                ElkNode elkGraph = layoutAndToElkNode(vc);
          
                data.getTargetGraphs().clear();
                data.getTargetGraphs().add(elkGraph);
                
                SCChartsFormatHandler.this.viewContext = vc;
            }
        }

        /**
         * {@inheritDoc}
         */
        public void transferLayout(TransformationData<State, ElkNode> data) {
            throw new UnsupportedOperationException(
                    "Applying layout to a MoML file is not supported.");
        }
    }

    /**
     * Helper class to assemble compilation strings for KiCo. 
     */
    @SuppressWarnings("serial")
    private class CompileChain extends ArrayList<String> {
        
        public CompileChain toNormalized() {
            this.addAll(Lists.newArrayList(
                    "HISTORY", 
                    "WEAKSUSPEND", 
                    "DEFERRED", 
                    "STATIC", 
                    "SIGNAL", 
                    "COUNTDELAY", 
                    "PRE", 
                    "SUSPEND", 
                    "COMPLEXFINALSTATE", 
                    "ABORT", 
                    "T_ABORT", 
                    "DURING",
                    "INITIALIZATION", 
                    "ENTRY", 
                    "EXIT", 
                    "CONNECTOR", 
                    "REFERENCE", 
                    "CONST", 
                    "EXPANSION", 
                    // 
                    "TRIGGEREFFECT", 
                    "SURFACEDEPTH"));
            return this;
        }
        
        public CompileChain toSCG() {
            this.add("scg.basic");
            return this;
        }
        
        public CompileChain toBasicBlocks() {
            this.add("scg.dependency");
            this.add("scg.basicblock");
            this.add("T_scg.basicblock.sc");
            return this;
        }
        
        public CompileChain toSequential() {
            this.addAll(Lists.newArrayList(
                "scg.guardExpressions",
                "scg.guards",
                "scg.scheduling",
                "scg.sequentialize"));
            return this;
        }
        
        public CompileChain toCircuit() {
            this.add("scg.seqssa");
            this.add("circuit.basic");
            return this;
        }
        
        public String compileString() {
            return Joiner.on(", ").join(this);
        }
    }
    
}
