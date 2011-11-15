/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.formats.matrix;

import java.util.LinkedList;
import java.util.List;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Importer for matrices into the KGraph format.
 *
 * @author msp
 */
public class MatrixImporter implements IGraphTransformer<Matrix, KNode> {

    /** the nodes that are created by this transformer, in order as given by the matrix. */
    private static final IProperty<KNode[]> NODES = new Property<KNode[]>("matrixImporter.nodes");
    /** the edges that are created by this transformer. */
    private static final IProperty<KEdge[]> EDGES = new Property<KEdge[]>("matrixImporter.edges");
    /** the index that is attached to a node. */
    private static final IProperty<Integer> NODE_INDEX = new Property<Integer>(
            "matrixImporter.nodeIndex", -1);
    
    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<Matrix, KNode> transData) {
        Matrix matrix = transData.getSourceGraph();
        KNode parent = KimlUtil.createInitializedNode();
        int nodec = Math.max(matrix.getRows(), matrix.getColumns());
        KNode[] nodes = new KNode[nodec];
        for (int i = 0; i < nodec; i++) {
            nodes[i] = KimlUtil.createInitializedNode();
            nodes[i].setParent(parent);
            nodes[i].getData(KShapeLayout.class).setProperty(NODE_INDEX, i + 1);
        }
        List<KEdge> edgeList = new LinkedList<KEdge>();
        
        // transform matrix form
        int[][] m = matrix.getMatrix();
        if (m != null) {
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++) {
                    if (m[i][j] != 0) {
                        KEdge kedge = KimlUtil.createInitializedEdge();
                        edgeList.add(kedge);
                        if (m[i][j] > 0) {
                            kedge.setSource(nodes[i]);
                            kedge.setTarget(nodes[j]);
                        } else if (m[i][j] < 0) {
                            kedge.setSource(nodes[j]);
                            kedge.setTarget(nodes[i]);
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
                    KEdge kedge = KimlUtil.createInitializedEdge();
                    edgeList.add(kedge);
                    if (entry.value > 0) {
                        kedge.setSource(nodes[entry.i]);
                        kedge.setTarget(nodes[entry.j]);
                    } else {
                        kedge.setSource(nodes[entry.j]);
                        kedge.setTarget(nodes[entry.i]);
                    }
                }
            }
        }

        transData.getTargetGraphs().add(parent);
        transData.setProperty(NODES, nodes);
        transData.setProperty(EDGES, edgeList.toArray(new KEdge[edgeList.size()]));
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<Matrix, KNode> transData) {
        List<KVectorChain> layout = transData.getSourceGraph().createLayout();
        // first lines: coordinates of node positions
        for (KNode node : transData.getProperty(NODES)) {
            KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
            KVectorChain chain = new KVectorChain();
            chain.add(nodeLayout.getXpos() + nodeLayout.getWidth() / 2,
                    nodeLayout.getYpos() + nodeLayout.getHeight() / 2);
            layout.add(chain);
        }
        // remaining lines: coordinates of edge bend points
        for (KEdge edge : transData.getProperty(EDGES)) {
            KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
            if (!edgeLayout.getBendPoints().isEmpty()) {
                try {
                    KVectorChain vectorChain = new KVectorChain();
                    // the first pair of numbers indicates the source and target node
                    vectorChain.add(
                            edge.getSource().getData(KShapeLayout.class).getProperty(NODE_INDEX),
                            edge.getTarget().getData(KShapeLayout.class).getProperty(NODE_INDEX));
                    // the remaining numbers are bend point coordinates
                    for (KPoint bendPoint : edgeLayout.getBendPoints()) {
                        vectorChain.add(bendPoint.getX(), bendPoint.getY());
                    }
                    layout.add(vectorChain);
                } catch (NumberFormatException exception) {
                    // ignore exception
                }
            }
        }
    }

}
