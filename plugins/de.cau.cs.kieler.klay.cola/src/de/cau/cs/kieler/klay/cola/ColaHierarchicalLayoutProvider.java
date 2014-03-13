/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.cola;

import java.util.Arrays;

import org.adaptagrams.ConstrainedFDLayout;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.processors.DirectionConstraintProcessor;
import de.cau.cs.kieler.klay.cola.processors.NonUniformEdgeLengthProcessor;
import de.cau.cs.kieler.klay.cola.processors.PortConstraintProcessor;

/**
 * @author uru
 */
public class ColaHierarchicalLayoutProvider extends AbstractLayoutProvider{

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor) {
       
        CGraph graph = new CGraph();
        
        // TODO import etc
        
        BasicProgressMonitor bpm = new BasicProgressMonitor();
        new DirectionConstraintProcessor().process(graph, bpm);
        new PortConstraintProcessor().process(graph, bpm);
        new NonUniformEdgeLengthProcessor().process(graph, bpm);

        System.out.println(parentNode);
        System.out.println(Arrays.toString(graph.idealEdgeLengths));

        // for the moment fix the issue where the edgelengths do not allow 0
        for (int i = 0; i < graph.idealEdgeLengths.length; ++i) {
            if (graph.idealEdgeLengths[i] == 0) {
                graph.idealEdgeLengths[i] = 20;
            }
        }

        ConstrainedFDLayout algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 50, false,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());

        algo.setClusterHierarchy(graph.rootCluster);
        
        // run some w/o overlap
        algo.makeFeasible();

        int runs = 10;
        
        for (int i = 0; i < runs; i++) {
            algo.runOnce();
            
            graph.rootCluster.computeBoundary(graph.nodes);

            algo.outputInstanceToSVG("out" + i + ".svg");

            // System.out.println(i);
        }
        
        // do some with overlap
        algo =
                new ConstrainedFDLayout(graph.getNodes(), graph.getEdges(), 1, true,
                        graph.getIdealEdgeLengths());

        algo.setConstraints(graph.getConstraints());

        algo.makeFeasible();

        for (int i = 0; i < runs; i++) {
            algo.runOnce();
            
            graph.rootCluster.computeBoundary(graph.nodes);

            algo.outputInstanceToSVG("out99overlap" + i + ".svg");

            // System.out.println(i);
        }

        
    }
    
    
}
