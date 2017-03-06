/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * An analysis for hierarchy level crossing edges in compound graphs. The first returned component
 * is the number of hierarchy crossing edges, the second component is the maximal number of crossed
 * hierarchy levels, the third component is the number of edges incident to compound nodes.
 *
 * <b>Warning:</b> Does not work for hyperedges.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow 2012-07-10 msp
 */
public class CompoundEdgeAnalysis implements IAnalysis {
    
    /** identifier of the compound edge analysis. */
    public static final String ID = "de.cau.cs.kieler.grana.compoundEdge";

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {
        progressMonitor.begin("Compound edge analysis", 1);

        int edgeCount = 0, maxLevels = 0, compoundEdges = 0;
        List<ElkNode> nodeQueue = new LinkedList<ElkNode>();
        nodeQueue.addAll(parentNode.getChildren());
        while (!nodeQueue.isEmpty()) {
            ElkNode node = nodeQueue.remove(0);
            for (ElkEdge edge : ElkGraphUtil.allOutgoingEdges(node)) {
                ElkNode source = ElkGraphUtil.connectableShapeToNode(edge.getSources().get(0));
                ElkNode target = ElkGraphUtil.connectableShapeToNode(edge.getTargets().get(0));
                int crossedLevels = crossedLevels(source, target);
                if (crossedLevels > 0) {
                    edgeCount++;
                }
                if (crossedLevels > maxLevels) {
                    maxLevels = crossedLevels;
                }
                if (!source.getChildren().isEmpty()
                        || !target.getChildren().isEmpty()) {
                    compoundEdges++;
                }
            }
            
            nodeQueue.addAll(node.getChildren());
        }
        
        progressMonitor.done();
        return new Object[] { edgeCount, maxLevels, compoundEdges };
    }
    
    /**
     * Determine the number of crossed levels between the two nodes.
     * 
     * @param node1 the first node
     * @param node2 the second node
     * @return the number of crossed levels
     */
    private int crossedLevels(final ElkNode node1, final ElkNode node2) {
        List<ElkNode> parentList1 = new LinkedList<ElkNode>();
        ElkNode parent1 = node1.getParent();
        while (parent1 != null) {
            parentList1.add(parent1);
            parent1 = parent1.getParent();
        }
        parentList1.add(null);
        
        ElkNode parent2 = node2.getParent();
        int levels1 = parentList1.indexOf(parent2);
        int levels2 = 0;
        while (levels1 < 0) {
            parent2 = parent2.getParent();
            levels1 = parentList1.indexOf(parent2);
            levels2++;
        }
        
        return levels1 + levels2;
    }

}
