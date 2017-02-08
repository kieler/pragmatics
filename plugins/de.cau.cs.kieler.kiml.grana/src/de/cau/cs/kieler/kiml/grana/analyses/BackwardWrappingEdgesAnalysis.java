/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.Iterator;
import java.util.stream.StreamSupport;

import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.core.util.Maybe;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.util.ElkGraphUtil;

import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.IAnalysis;

/**
 * A backward wrapping edge is a directed edge whose path has at least one segment that points into
 * the layout direction and at least one segment that points against the layout direction.
 * 
 * Careful when the graphs contains feedback edges. They will be included in the result.
 * 
 * @author uru
 */
public class BackwardWrappingEdgesAnalysis implements IAnalysis {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {

        final Maybe<DirectionChecker> d1 = new Maybe<>(), d2 = new Maybe<>();
        Direction dir = parentNode.getProperty(CoreOptions.DIRECTION);
        switch (dir) {
        case LEFT:
        case RIGHT:
            d1.set(LEFT);
            d2.set(RIGHT);
            break;
        case UP:
        case DOWN:
            d1.set(UP);
            d2.set(DOWN);
            break;
        default:
            throw new IllegalStateException(BackwardWrappingEdgesAnalysis.class.getSimpleName()
                    + " doesn't support an undefined layout direction.");
        }
        
        long backwardEdgeCount = parentNode.getChildren().stream()
            .flatMap(n -> StreamSupport.stream(ElkGraphUtil.allOutgoingEdges(n).spliterator(), true))
            .filter(e -> isBackwardWrapping(e, d1.get(), d2.get()))
            .count();
        
        return backwardEdgeCount;
    }

    private boolean isBackwardWrapping(final ElkEdge edge, final DirectionChecker fc,
            final DirectionChecker bc) {

        if (edge.getSections().isEmpty() || edge.getSections().size() > 1) {
            throw new IllegalStateException(BackwardWrappingEdgesAnalysis.class.getSimpleName()
                    + " doesn't support edges without or with multiple edge sections.");
        }

        ElkEdgeSection section = edge.getSections().get(0);
        KVectorChain vc = ElkUtil.createVectorChain(section);

        boolean forwardSection = false;
        boolean backwardSection = false;

        // vc contains at least two vectors
        KVector last = vc.get(0);
        Iterator<KVector> vIt = vc.iterator();
        vIt.next(); // skip first element
        while (vIt.hasNext()) {
            KVector cur = vIt.next();

            forwardSection |= fc.apply(last, cur);
            backwardSection |= bc.apply(last, cur);
            
            if (forwardSection && backwardSection) {
                return true;
            }
            
            last = cur;
        }

        return false;
    }

    // SUPPRESS CHECKSTYLE NEXT Javadoc
    @FunctionalInterface
    private interface DirectionChecker {
        boolean apply(KVector x, KVector y);
    }
    
    private static final DirectionChecker RIGHT = (v1, v2) -> v1.x < v2.x;
    private static final DirectionChecker LEFT = (v1, v2) -> v1.x > v2.x;
    private static final DirectionChecker DOWN = (v1, v2) -> v1.y < v2.y;
    private static final DirectionChecker UP = (v1, v2) -> v1.y > v2.y;
}
