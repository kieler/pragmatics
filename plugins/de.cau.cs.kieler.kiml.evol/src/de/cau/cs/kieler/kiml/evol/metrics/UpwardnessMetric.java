/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
/**
 *
 */
package de.cau.cs.kieler.kiml.evol.metrics;

import java.util.Map;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.service.grana.IAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeCountAnalysis;
import de.cau.cs.kieler.kiml.service.grana.analyses.EdgeDirectionAnalysis;

/**
 * Measures the fraction of upward edges in the given KGraph. Returns a Float
 * object that indicates the degree of 'upwardness' within the range of (0.0,
 * 1.0), where 0.0 means no upwardness and 1.0 means maximum upwardness.
 *
 * <p>The base class is abstract and can be instantiated only by the four concrete
 * subclasses that aim at the four edge directions.
 *
 * <p>NOTE: A more sophisticated, fuzzy metric could take angles into account, so
 * that for example an edge that is pointing diagonally upward would be counted
 * as a "50% upward" edge.
 * 
 * @author bdu
 * @author msp
 */
public abstract class UpwardnessMetric implements IAnalysis {

    /** the direction that is analyzed by the concrete metric instance. */
    private Direction targetDirection;
    
    /**
     * Creates an upwardness metric for the given direction.
     * 
     * @param theDirection the target direction
     */
    public UpwardnessMetric(final Direction theDirection) {
        this.targetDirection = theDirection;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(
            final KNode parentNode, final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Upwardness metric analysis", 1);
        Float result;

        // load numbers from analyses
        Object edgesResult = results.get(EdgeCountAnalysis.ID);
        Object[] edgeDirectionResult = (Object[]) results.get(EdgeDirectionAnalysis.ID);

        int totalEdgesCount = (Integer) edgesResult;
        int upwardEdgesCount = 0;
        switch (targetDirection) {
        case RIGHT:
            upwardEdgesCount = (Integer) edgeDirectionResult[EdgeDirectionAnalysis.INDEX_RIGHT];
            break;
        case DOWN:
            upwardEdgesCount = (Integer) edgeDirectionResult[EdgeDirectionAnalysis.INDEX_DOWN];
            break;
        case LEFT:
            upwardEdgesCount = (Integer) edgeDirectionResult[EdgeDirectionAnalysis.INDEX_LEFT];
            break;
        case UP:
            upwardEdgesCount = (Integer) edgeDirectionResult[EdgeDirectionAnalysis.INDEX_UP];
            break;
        }

        result = (float) upwardEdgesCount / totalEdgesCount;
        assert result >= 0 && result <= 1;

        progressMonitor.done();
        return result;
    }
    
    /**
     * Specialized metric for the UP direction.
     */
    public static class UpMetric extends UpwardnessMetric {
        /**
         * Create an up metric.
         */
        public UpMetric() {
            super(Direction.UP);
        }
    }
    
    /**
     * Specialized metric for the DOWN direction.
     */
    public static class DownMetric extends UpwardnessMetric {
        /**
         * Create a down metric.
         */
        public DownMetric() {
            super(Direction.DOWN);
        }
    }
    
    /**
     * Specialized metric for the LEFT direction.
     */
    public static class LeftMetric extends UpwardnessMetric {
        /**
         * Create a left metric.
         */
        public LeftMetric() {
            super(Direction.LEFT);
        }
    }
    
    /**
     * Specialized metric for the RIGHT direction.
     */
    public static class RightMetric extends UpwardnessMetric {
        /**
         * Create a right metric.
         */
        public RightMetric() {
            super(Direction.RIGHT);
        }
    }

}
