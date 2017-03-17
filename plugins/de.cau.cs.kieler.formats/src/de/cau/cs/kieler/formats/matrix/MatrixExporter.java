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
package de.cau.cs.kieler.formats.matrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkConnectableShape;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import com.google.common.collect.Iterables;

import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;

/**
 * Exporter for matrices from the KGraph format. The option {@link LayoutOptions#NO_LAYOUT}
 * can be used to suppress creation of a graph layout in matrix format, hence only the graph structure
 * is exported in that case.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class MatrixExporter implements IGraphTransformer<ElkNode, Matrix> {

    /**
     * {@inheritDoc}
     */
    public void transform(final TransformationData<ElkNode, Matrix> data) {
        ElkNode parentNode = data.getSourceGraph();
        int n = parentNode.getChildren().size();
        Matrix matrix = new Matrix(n, n);
        
        // count the edges of the graph and index the nodes
        int m = 0, index = 0;
        Map<ElkNode, Integer> nodeIndex = new HashMap<ElkNode, Integer>();
        for (ElkNode node : parentNode.getChildren()) {
            Iterable<ElkEdge> allOutgoing = ElkGraphUtil.allOutgoingEdges(node);
            m += Iterables.size(allOutgoing);
            nodeIndex.put(node, index++);
        }
        
        // SUPPRESS CHECKSTYLE NEXT MagicNumber
        if (3 * m < n * n) {
            // sparse matrix: use coordinate format
            List<Matrix.Entry> adjlist = matrix.createList(m);
            for (ElkNode node : parentNode.getChildren()) {
                int sourceIndex = nodeIndex.get(node);
                Map<ElkNode, Matrix.Entry> targetMap = new HashMap<>();
                
                // Note that this ignores edges connected to ports, which are not supported
                // by the matrix format anyway (we could change that)
                for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                    if (!isDirectSimpleEdgeBetweenNodes(edge)) {
                        // No hyperedges, no edges that connect to ports
                        continue;
                    }
                    
                    ElkConnectableShape targetShape = edge.getTargets().get(0);
                    Integer targetIndex = nodeIndex.get(targetShape);
                    if (targetIndex != null) {
                        Matrix.Entry entry = targetMap.get(targetShape);
                        if (entry == null) {
                            entry = new Matrix.Entry(sourceIndex, targetIndex, 1);
                            adjlist.add(entry);
                            targetMap.put((ElkNode) targetShape, entry);
                        } else {
                            entry.value++;
                        }
                    }
                }
            }
        } else {
            // dense matrix: use array format
            int[][] x = matrix.createMatrix();
            for (ElkNode node : parentNode.getChildren()) {
                int sourceIndex = nodeIndex.get(node);
                for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                    if (!isDirectSimpleEdgeBetweenNodes(edge)) {
                        // No hyperedges, no edges that connect to ports
                        continue;
                    }
                    
                    Integer targetIndex = nodeIndex.get(edge.getTargets().get(0));
                    if (targetIndex != null) {
                        x[sourceIndex][targetIndex]++;
                    }
                }
            }
        }
        
        // transfer the layout of the graph
        if (!data.getProperty(CoreOptions.NO_LAYOUT)) {
            List<KVectorChain> layout = matrix.createLayout();
            // first lines: coordinates of node positions
            for (ElkNode node : parentNode.getChildren()) {
                KVectorChain chain = new KVectorChain();
                chain.add(node.getX() + node.getWidth() / 2, node.getY() + node.getHeight() / 2);
                layout.add(chain);
            }
            
            // remaining lines: coordinates of edge bend points
            for (ElkNode node : parentNode.getChildren()) {
                for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                    if (edge.getSections().isEmpty()) {
                        continue;
                    }
                    ElkEdgeSection edgeSection = edge.getSections().get(0);
                    
                    if (edgeSection.getBendPoints().isEmpty()) {
                        continue;
                    }
                    
                    KVectorChain vectorChain = new KVectorChain();
                    // the first pair of numbers indicates the source and target node
                    vectorChain.add(
                            nodeIndex.get(edge.getSources().get(0)),
                            nodeIndex.get(edge.getTargets().get(0)));
                    
                    // the remaining numbers are bend point coordinates
                    for (ElkBendPoint bendPoint : edgeSection.getBendPoints()) {
                        vectorChain.add(bendPoint.getX(), bendPoint.getY());
                    }
                    
                    layout.add(vectorChain);
                }
            }
        }
        
        data.getTargetGraphs().add(matrix);
    }

    /**
     * {@inheritDoc}
     */
    public void transferLayout(final TransformationData<ElkNode, Matrix> data) {
        throw new UnsupportedOperationException("Layout transfer is not supported for matrix export.");
    }
    
    private boolean isDirectSimpleEdgeBetweenNodes(final ElkEdge edge) {
        if (!(edge.getSources().size() == 1 && edge.getTargets().size() == 1)) {
            return false;
        }
        
        ElkConnectableShape sourceShape = edge.getSources().get(0);
        ElkConnectableShape targetShape = edge.getTargets().get(0);
        
        return sourceShape instanceof ElkNode && targetShape instanceof ElkNode;
    }

}
