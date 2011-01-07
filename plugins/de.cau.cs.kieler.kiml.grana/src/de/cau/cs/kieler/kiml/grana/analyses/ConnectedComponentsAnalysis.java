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

package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.Map;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.IAnalysis;


/**
 * A graph analysis that finds the number of connected components in a graph. Returns
 * a single-component result {@code (int components)}.
 * 
 * @author cds
 */
public class ConnectedComponentsAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) throws KielerException {
        
        progressMonitor.begin("Connected Components Analysis", 1);
        
        traverse(parentNode);
        
        progressMonitor.done();
        return new AnalysisFailed(AnalysisFailed.Type.Failed);
    }
    
    private void traverse(final KNode node) {
        System.out.println("Node " + (node.getLabel() != null ? node.getLabel().getText() : ""));
        
        for (KEdge edge : node.getOutgoingEdges()) {
            System.out.println("  >>> " + (edge.getTarget().getLabel() != null
                    ? edge.getTarget().getLabel().getText()
                    : ""));
        }
        
        for (KNode child : node.getChildren()) {
            traverse(child);
        }
    }

}
