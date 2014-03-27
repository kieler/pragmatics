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

import org.adaptagrams.ACAEdgeOffsets;
import org.adaptagrams.ACALayout;
import org.adaptagrams.ACASepFlag;
import org.adaptagrams.ACASepFlagsStruct;
import org.adaptagrams.DoublePair;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters;
import de.cau.cs.kieler.kiml.util.adapters.KGraphAdapters.KGraphAdapter;
import de.cau.cs.kieler.kiml.util.nodespacing.KimlNodeDimensionCalculation;
import de.cau.cs.kieler.kiml.util.nodespacing.Spacing.Margins;
import de.cau.cs.kieler.klay.cola.graph.CEdge;
import de.cau.cs.kieler.klay.cola.graph.CGraph;
import de.cau.cs.kieler.klay.cola.graph.CNode;
import de.cau.cs.kieler.klay.cola.graph.CPort;
import de.cau.cs.kieler.klay.cola.graphimport.KGraphImporter;
import de.cau.cs.kieler.klay.cola.processors.DirectionConstraintProcessor;
import de.cau.cs.kieler.klay.cola.processors.NonUniformEdgeLengthProcessor;
import de.cau.cs.kieler.klay.cola.properties.ColaProperties;

/**
 * @author uru
 * 
 */
public class ACALayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(KNode parentNode, IKielerProgressMonitor progressMonitor) {

        progressMonitor.begin("ACA Layout", 1);

        parentNode.getData(KShapeLayout.class).setProperty(ColaProperties.PORT_DUMMIES, false);

        KGraphAdapter adapter = KGraphAdapters.adapt(parentNode);
        KimlNodeDimensionCalculation.sortPortLists(adapter);
        KimlNodeDimensionCalculation.calculateLabelAndNodeSizes(adapter);
        KimlNodeDimensionCalculation.getNodeMarginCalculator(adapter).process();

        KGraphImporter importer = new KGraphImporter();

        final CGraph graph = importer.importGraph(parentNode);
        graph.init();
        
        new DirectionConstraintProcessor().process(graph, progressMonitor.subTask(1));
        new NonUniformEdgeLengthProcessor().process(graph, progressMonitor.subTask(1));
        
        // run ACA
        ACALayout aca = new ACALayout(graph.nodes, graph.edges, graph.constraints, 100, true);
        //ACALayout aca = new ACALayout(graph.nodes, graph.edges, graph.constraints, 100, true, graph.getIdealEdgeLengths());

        // add flags that restrict edges to being aligned horizontally
        ACASepFlagsStruct struct = new ACASepFlagsStruct();
        for (int i = 0; i < graph.edges.size(); ++i) {
            struct.addFlag(ACASepFlag.ACAEAST);
        }
        aca.setAllowedSeparations(struct);

        ACAEdgeOffsets edgeOffsets = new ACAEdgeOffsets(graph.edges.size());

        for (CNode n : graph.getChildren()) {
            for (CEdge e : n.getOutgoingEdges()) {

                KEdge kedge = (KEdge) e.getProperty(ColaProperties.ORIGIN);
                KPort srcPort = kedge.getSourcePort();
                KPort tgtPort = kedge.getTargetPort();
                
//                CPort srcPort = e.getSourcePort();
//                CPort tgtPort = e.getTargetPort();

                DoublePair st = new DoublePair(0, 0);
                if (srcPort != null) {
                    st.setFirst(-calculatePortOffset(n, srcPort));
                }
                if (tgtPort != null) {
                    st.setSecond(-calculatePortOffset(e.getTarget(), tgtPort));
                }
                System.out.println("DoublesPair " + st.getFirst() + " " + st.getSecond() + "\t\t" + e);
                edgeOffsets.set(e.cIndex, st);

            }
        }
        aca.setAlignmentOffsetsForCompassDirection(ACASepFlag.ACAEAST, edgeOffsets);
        
        int i = 0;
//        while (aca.createOneAlignment()) {
//            aca.getFDLayout().outputInstanceToSVG("aca_output_" + (i++));
//        }
        
        aca.createAlignments();
        //aca.layout();

        aca.getFDLayout().outputInstanceToSVG();

        System.out.println("Done");

        importer.applyLayout(graph);

        progressMonitor.done();
    }

    private double calculatePortOffset(final CNode n, final KPort p) {

        Margins margins = n.getMargins();
        double nodeHeight = n.rect.height();

        System.out.println("\t" + margins.top + " " + nodeHeight + " " + n);
        
        KShapeLayout portLayout = p.getData(KShapeLayout.class);
        // TODO not sure about the validity of selecting the port's pos and size
        double dy =
                -(nodeHeight / 2f) + margins.top + portLayout.getYpos()
                        + (portLayout.getHeight() / 2);

        return dy;
    }
}
