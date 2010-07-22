package de.cau.cs.kieler.klay.planar.tests;

import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.klay.planar.alg.IPlanarizer;
import de.cau.cs.kieler.klay.planar.alg.impl.EdgeInsertionPlanarization;
import de.cau.cs.kieler.klay.planar.graph.IGraph;
import de.cau.cs.kieler.klay.planar.graph.INode;
import de.cau.cs.kieler.klay.planar.graph.InconsistentGraphModelException;
import de.cau.cs.kieler.klay.planar.graph.impl.PGraph;

public class EdgeInsertionTestClass {

    public static void main(String[] args) throws InconsistentGraphModelException {
        // build test graph
        IGraph testGraph = new PGraph();
        IGraph testGraph2 = new PGraph();

        INode nodeZero = testGraph.addNode();
        INode nodeOne = testGraph.addNode();
        INode nodeTwo = testGraph.addNode();
        INode nodeThree = testGraph.addNode();
        INode nodeFour = testGraph.addNode();
        INode nodeFive = testGraph.addNode();
        INode nodeSix = testGraph.addNode();
        INode nodeSeven = testGraph.addNode();

        INode nodeZero2 = testGraph2.addNode();
        INode nodeOne2 = testGraph2.addNode();
        INode nodeTwo2 = testGraph2.addNode();

        try {
            testGraph.addEdge(nodeZero, nodeOne);
            testGraph.addEdge(nodeZero, nodeFour);
            testGraph.addEdge(nodeZero, nodeThree);
            testGraph.addEdge(nodeZero, nodeTwo);

            testGraph.addEdge(nodeOne, nodeFive);
            testGraph.addEdge(nodeOne, nodeFour);

            testGraph.addEdge(nodeFive, nodeSeven);

            testGraph.addEdge(nodeSeven, nodeSix);
            testGraph.addEdge(nodeSeven, nodeFour);

            testGraph.addEdge(nodeSix, nodeTwo);
            testGraph.addEdge(nodeSix, nodeThree);

            testGraph.addEdge(nodeFour, nodeSix);

            testGraph2.addEdge(nodeZero2, nodeOne2);
            testGraph2.addEdge(nodeOne2, nodeTwo2);

        } catch (InconsistentGraphModelException e) {

            e.printStackTrace();
        }
        IPlanarizer planarizer = new EdgeInsertionPlanarization();

        System.out.println("Eingabe Graph \n\n");
        System.out.println(testGraph.toString());
        System.out.println("----------------------\n");

        List<Pair<INode, INode>> edges = new ArrayList<Pair<INode, INode>>();
        edges.add(new Pair<INode, INode>(nodeFour, nodeTwo));
        // edges.add(new Pair<INode, INode>(nodeFour, nodeTwo));
        planarizer.planarize(testGraph, edges);
        System.out.println("NEUER GRAPH");
        System.out.println(testGraph.toString());

        // System.out.println("Eingabe Graph \n\n");
        // System.out.println(testGraph2.toString());
        // System.out.println("----------------------\n");
        //
        // IGraph dualgraph = testGraph2.createDualGraph();
        // System.out.println("----------------------\n");
        // System.out.println("DualerGraph \n\n");
        // System.out.println(dualgraph.toString());
        // System.out.println("----------------------\n");

        // System.out.println("parent-Array:");
        // int[] test = searchParents(nodeThree, testGraph);
        // for (int i = 0; i < test.length; i++) {
        // System.out.println(test[i] + ", ");
        // }

        // System.out.println("The matching Face:");
        // IFace face = findInherentFace(nodeOne, testGraph);
        // System.out.println(face.toString());

        // INode newNode = planrizer.splitUpEdge(testGraph.getEdges().iterator()
        // .next(), testGraph);
        // System.out.println("neuerGraph:");
        // System.out.println(testGraph);

    }
}
