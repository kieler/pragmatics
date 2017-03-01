/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.analyses;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.elk.core.UnsupportedGraphException;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.Direction;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.options.PortSide;
import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.properties.IProperty;
import org.eclipse.elk.graph.properties.Property;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.ImmutableSet;

import de.cau.cs.kieler.grana.AnalysisContext;
import de.cau.cs.kieler.grana.AnalysisFailed;
import de.cau.cs.kieler.grana.IAnalysis;

/**
 * This analysis reports the number of inter-layer edge segments that are axis-aligned with the axis
 * corresponding to the layout direction. That is, for layout directions
 * {@link de.cau.cs.kieler.kiml.options.Direction Direction#LEFT} and
 * {@link de.cau.cs.kieler.kiml.options.Direction Direction#RIGHT} the number of edge segments
 * aligned with the x-axis is reported. An edge segment counts when its start and end points lie in
 * different layers as determined by the {@link LayersAnalysis}.
 * 
 * Preconditions:
 * <ul>
 * <li>Layout has to be performed using {@code de.cau.cs.kieler.klay.layered}.</li>
 * <li>{@link EdgeRouting#ORTHOGONAL} has to be used.</li>
 * </ul>
 * 
 * @author uru
 */
public class AxisParallelEdgeSegmentsAnalysis implements IAnalysis {

    /**
     * Identifier of this analysis.
     */
    public static final String ID = "de.cau.cs.kieler.grana.axisParallelEdgeSegments";

    //
    // Internal property definitions to check the precondition.
    //
    private static final String KLAY_LAYERED_ID = "de.cau.cs.kieler.klay.layered";
    private static final IProperty<String> ALGORITHM = new Property<String>(
            "de.cau.cs.kieler.algorithm");
    private static final IProperty<Object> SIMPLE_NODE_PLACE = new Property<Object>(
            "de.cau.cs.kieler.klay.layered.nodePlace", "");

    private static final Set<Direction> LEFT_TO_RIGHT = ImmutableSet.of(Direction.UNDEFINED,
            Direction.LEFT, Direction.RIGHT);
    private static final Set<PortSide> NORTH_SOUTH = ImmutableSet
            .of(PortSide.NORTH, PortSide.SOUTH);
    private static final Set<PortSide> EAST_WEST = ImmutableSet.of(PortSide.WEST, PortSide.EAST);

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final ElkNode parentNode, final AnalysisContext context,
            final IElkProgressMonitor progressMonitor) {

        // check that the precondition is met
        if (
                (parentNode.getProperty(ALGORITHM) != null 
                && !parentNode.getProperty(ALGORITHM).equals(KLAY_LAYERED_ID))
                // edge routing
                || parentNode.getProperty(CoreOptions.EDGE_ROUTING) != EdgeRouting.ORTHOGONAL
            ) {
            return new AnalysisFailed(AnalysisFailed.Type.Configuration, new IllegalStateException(
                    "KlayLayered has to be used in conjunction with ORTHOGONAL edge routing."));
        }
        progressMonitor.begin("Axis-Parallel edge segments analysis", 1);

        Integer edgeCount = (Integer) context.getResult(EdgeCountAnalysis.ID);
        Integer dummies =
                (Integer) ((Object[]) context
                        .getResult(LayersAnalysis.ID))[LayersAnalysis.INDEX_DUMMIES];
        Integer bendpoints =
                (Integer) ((Object[]) context
                        .getResult(BendsAnalysis.ID))[BendsAnalysis.INDEX_SUM_NON_UNIQUE];
        
        // North/southports need to be handled specially. 
        //  Every edge that starts or ends in an ns-port
        //  contributes exactly one bendpoint. Such an 
        //  edge is not considered axis-parallel. For an easy 
        //  calculation we add a second (pseudo) bendpoint,
        //  just to mark the edge as not being axis-parallel.
        // Note, however, that this is only true if the connected
        //  edge segment is drawn straight, e.g. with the SIMPLE node
        //  placer this assumption is not true.
        Iterator<EObject> allContents = parentNode.eAllContents();
        int specialBendpoints = 0;
        while (allContents.hasNext()) {
            EObject e = allContents.next();
            if (e instanceof ElkEdge) {
                ElkEdge elkedge = (ElkEdge) e;
                
                // currently no self-loop support
                if (elkedge.isSelfloop()) {
                    throw new UnsupportedGraphException("Self-loops are not supported by the "
                            + this.getClass().getSimpleName());
                } else if (elkedge.isHyperedge()) {
                    throw new UnsupportedGraphException("Hyperedges are not supported by the "
                            + this.getClass().getSimpleName());
                }
                
                // get the direction of the edge's parent node
                Direction direction =
                        elkedge.getContainingNode().getProperty(CoreOptions.DIRECTION);
                
                // edge's source
                ElkPort src = ElkGraphUtil.connectableShapeToPort(elkedge.getSources().get(0));
                if (src != null) {
                    if (LEFT_TO_RIGHT.contains(direction)) {
                        if (NORTH_SOUTH.contains(src.getProperty(CoreOptions.PORT_SIDE))) {
                            specialBendpoints++;
                        }
                    } else {
                        if (EAST_WEST.contains(src.getProperty(CoreOptions.PORT_SIDE))) {
                            specialBendpoints++;
                        }
                    }
                }
                
                // edge's target
                ElkPort tgt = ElkGraphUtil.connectableShapeToPort(elkedge.getSources().get(0));
                if (tgt != null) {
                    if (LEFT_TO_RIGHT.contains(direction)) {
                        if (NORTH_SOUTH.contains(tgt.getProperty(CoreOptions.PORT_SIDE))) {
                            specialBendpoints++;
                        }
                    } else {
                        if (EAST_WEST.contains(tgt.getProperty(CoreOptions.PORT_SIDE))) {
                            specialBendpoints++;
                        }
                    }
                }
            }
        }

        int notAxisParallel = 0;
        if ("SIMPLE".equals(parentNode.getProperty(SIMPLE_NODE_PLACE).toString())) {
            notAxisParallel = (bendpoints - specialBendpoints) / 2;
        } else {
            notAxisParallel = (bendpoints + specialBendpoints) / 2;
        }
        int axisParallel = edgeCount + dummies - notAxisParallel;

        progressMonitor.done();

        return axisParallel;
    }

}
