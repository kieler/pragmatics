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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVectorChain;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
 * Exporter for matrices from the KGraph format.
 *
 * @author msp
 */
public class MatrixExporter implements IGraphTransformer<KNode, Matrix> {

    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<KNode, Matrix> data) {
        KNode parentNode = data.getSourceGraph();
        int n = parentNode.getChildren().size();
        Matrix matrix = new Matrix(n, n);
        
        // count the edges of the graph and index the nodes
        int m = 0, index = 0;
        Map<KNode, Integer> nodeIndex = new HashMap<KNode, Integer>();
        for (KNode node : parentNode.getChildren()) {
            m += node.getOutgoingEdges().size();
            nodeIndex.put(node, index++);
        }
        
        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        if (3 * m < n * n) {
            // sparse matrix: use coordinate format
            List<Matrix.Entry> adjlist = matrix.createList(m);
            for (KNode node : parentNode.getChildren()) {
                int sourceIndex = nodeIndex.get(node);
                Map<KNode, Matrix.Entry> targetMap = new HashMap<KNode, Matrix.Entry>();
                for (KEdge edge : node.getOutgoingEdges()) {
                    Integer targetIndex = nodeIndex.get(edge.getTarget());
                    if (targetIndex != null) {
                        Matrix.Entry entry = targetMap.get(edge.getTarget());
                        if (entry == null) {
                            entry = new Matrix.Entry(sourceIndex, targetIndex, 1);
                            adjlist.add(entry);
                            targetMap.put(edge.getTarget(), entry);
                        } else {
                            entry.value++;
                        }
                    }
                }
            }
        } else {
            // dense matrix: use array format
            int[][] x = matrix.createMatrix();
            for (KNode node : parentNode.getChildren()) {
                int sourceIndex = nodeIndex.get(node);
                for (KEdge edge : node.getOutgoingEdges()) {
                    Integer targetIndex = nodeIndex.get(edge.getTarget());
                    if (targetIndex != null) {
                        x[sourceIndex][targetIndex]++;
                    }
                }
            }
        }
        
        // transfer the layout of the graph
        if (!data.getProperty(LayoutOptions.NO_LAYOUT)) {
            List<KVectorChain> layout = matrix.createLayout();
            // first lines: coordinates of node positions
            for (KNode node : parentNode.getChildren()) {
                KShapeLayout nodeLayout = node.getData(KShapeLayout.class);
                KVectorChain chain = new KVectorChain();
                chain.add(nodeLayout.getXpos() + nodeLayout.getWidth() / 2,
                        nodeLayout.getYpos() + nodeLayout.getHeight() / 2);
                layout.add(chain);
            }
            // remaining lines: coordinates of edge bend points
            for (KNode node : parentNode.getChildren()) {
                for (KEdge edge : node.getOutgoingEdges()) {
                    KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                    if (!edgeLayout.getBendPoints().isEmpty()) {
                        try {
                            KVectorChain vectorChain = new KVectorChain();
                            // the first pair of numbers indicates the source and target node
                            vectorChain.add(nodeIndex.get(edge.getSource()),
                                    nodeIndex.get(edge.getTarget()));
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
        
        data.getTargetGraphs().add(matrix);
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<KNode, Matrix> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for matrix export.");
    }

}
