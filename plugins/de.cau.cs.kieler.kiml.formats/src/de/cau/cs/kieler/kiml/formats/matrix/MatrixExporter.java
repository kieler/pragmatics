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
import de.cau.cs.kieler.kiml.service.formats.IGraphTransformer;
import de.cau.cs.kieler.kiml.service.formats.TransformationData;

/**
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
        
        data.getTargetGraphs().add(matrix);
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<KNode, Matrix> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for matrix export.");
    }

}
