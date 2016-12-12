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
package de.cau.cs.kieler.kiml.formats.matrix;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.kiml.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.formats.TransformationData;

/**
 * Importer for matrices into the KGraph format.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class MatrixImporter implements IGraphTransformer<Matrix, ElkNode> {

    /** the nodes that are created by this transformer, in order as given by the matrix. */
    private static final IProperty<ElkNode[]> NODES = new Property<>("matrixImporter.nodes");
    /** the edges that are created by this transformer. */
    private static final IProperty<ElkEdge[]> EDGES = new Property<>("matrixImporter.edges");
    /** the index that is attached to a node. */
    private static final IProperty<Integer> NODE_INDEX = new Property<Integer>(
            "matrixImporter.nodeIndex", -1);
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<Matrix, ElkNode> transData) {
        Matrix matrix = transData.getSourceGraph();
        ElkNode parent = ElkGraphUtil.createGraph();
        int nodec = Math.max(matrix.getRows(), matrix.getColumns());
        ElkNode[] nodes = new ElkNode[nodec];
        for (int i = 0; i < nodec; i++) {
            nodes[i] = ElkGraphUtil.createNode(parent);
            nodes[i].setProperty(NODE_INDEX, i + 1);
        }
        List<ElkEdge> edgeList = new LinkedList<ElkEdge>();
        
        // transform matrix form
        int[][] m = matrix.getMatrix();
        if (m != null) {
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++) {
                    if (m[i][j] != 0) {
                        if (m[i][j] > 0) {
                            ElkGraphUtil.createSimpleEdge(nodes[i], nodes[j]);
                        } else if (m[i][j] < 0) {
                            ElkGraphUtil.createSimpleEdge(nodes[j], nodes[i]);
                        }
                    }
                }
            }
        }
        
        // transform coordinates form
        List<Matrix.Entry> list = matrix.getList();
        if (list != null) {
            for (Matrix.Entry entry : list) {
                if (entry.i < nodec && entry.j < nodec && entry.value != 0) {
                    if (entry.value > 0) {
                        ElkGraphUtil.createSimpleEdge(nodes[entry.i], nodes[entry.j]);
                    } else {
                        ElkGraphUtil.createSimpleEdge(nodes[entry.j], nodes[entry.i]);
                    }
                }
            }
        }

        transData.getTargetGraphs().add(parent);
        transData.setProperty(NODES, nodes);
        transData.setProperty(EDGES, edgeList.toArray(new ElkEdge[edgeList.size()]));
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<Matrix, ElkNode> transData) {
        List<KVectorChain> layout = transData.getSourceGraph().createLayout();
        // first lines: coordinates of node positions
        for (ElkNode node : transData.getProperty(NODES)) {
            KVectorChain chain = new KVectorChain();
            chain.add(node.getX() + node.getWidth() / 2,
                    node.getY() + node.getHeight() / 2);
            layout.add(chain);
        }
        
        // remaining lines: coordinates of edge bend points
        for (ElkEdge edge : transData.getProperty(EDGES)) {
            if (edge.getSections().isEmpty()) {
                continue;
            }
            
            ElkEdgeSection edgeSection = edge.getSections().get(0);
            if (!edgeSection.getBendPoints().isEmpty()) {
                KVectorChain vectorChain = new KVectorChain();
                
                // the first pair of numbers indicates the source and target node
                ElkNode sourceNode = (ElkNode) edge.getSources().get(0);
                ElkNode targetNode = (ElkNode) edge.getTargets().get(0);
                
                vectorChain.add(
                        sourceNode.getProperty(NODE_INDEX).doubleValue(),
                        targetNode.getProperty(NODE_INDEX).doubleValue());
                
                // the remaining numbers are bend point coordinates
                for (ElkBendPoint bendPoint : edgeSection.getBendPoints()) {
                    vectorChain.add(bendPoint.getX(), bendPoint.getY());
                }
                layout.add(vectorChain);
            }
        }
    }

}
