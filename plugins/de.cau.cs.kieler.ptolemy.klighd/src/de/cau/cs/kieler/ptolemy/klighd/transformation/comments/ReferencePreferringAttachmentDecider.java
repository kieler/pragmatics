/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2016 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.ptolemy.klighd.transformation.comments;

import java.util.Map;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.kiml.comments.DistanceHeuristic;
import de.cau.cs.kieler.kiml.comments.IAttachmentDecider;
import de.cau.cs.kieler.kiml.comments.IHeuristic;
import de.cau.cs.kieler.kiml.comments.NodeReferenceHeuristic;

/**
 * An attachment decider that prefers to attach comments to nodes they mention.
 * 
 * @author cds
 */
public class ReferencePreferringAttachmentDecider implements IAttachmentDecider {

    /**
     * {@inheritDoc}
     */
    @Override
    public KGraphElement makeAttachmentDecision(
            Map<KGraphElement, Map<Class<? extends IHeuristic>, Double>> normalizedHeuristics) {
        
        double bestResult = 0;
        KGraphElement bestCandidate = null;
        
        for (Map.Entry<KGraphElement, Map<Class<? extends IHeuristic>, Double>> candidate :
            normalizedHeuristics.entrySet()) {
            
            // If the node reference heuristic produced something worthwhile, use this node
            Double referenceValue = candidate.getValue().get(NodeReferenceHeuristic.class);
            if (referenceValue != null && referenceValue > 0) {
                return candidate.getKey();
            }
            
            // Use the distance heuristic
            referenceValue = candidate.getValue().get(DistanceHeuristic.class);
            if (referenceValue != null && referenceValue > bestResult) {
                bestResult = referenceValue;
                bestCandidate = candidate.getKey();
            }
        }
        
        return bestCandidate;
    }

}
