/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.analyses;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.ImmutableSet;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.kiml.util.KimlUtil;

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
    public static final String ID = "de.cau.cs.kieler.kiml.grana.axisParallelEdgeSegments";

    //
    // Internal property definitions to check the precondition.
    //
    private static final String KLAY_LAYERED_ID = "de.cau.cs.kieler.klay.layered";
    private static final IProperty<String> ALGORITHM = new Property<String>(
            "de.cau.cs.kieler.algorithm");
    
    private static final Set<Direction> LEFT_TO_RIGHT = ImmutableSet.of(Direction.UNDEFINED,
            Direction.LEFT, Direction.RIGHT);
    private static final Set<PortSide> NORTH_SOUTH = ImmutableSet
            .of(PortSide.NORTH, PortSide.SOUTH);
    private static final Set<PortSide> EAST_WEST = ImmutableSet.of(PortSide.WEST, PortSide.EAST);

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {

        // check that the precondition is met
        KLayoutData ld = parentNode.getData(KLayoutData.class);
        if (
                (ld.getProperty(ALGORITHM) != null 
                && !ld.getProperty(ALGORITHM).equals(KLAY_LAYERED_ID))
                // edge routing
                || ld.getProperty(LayoutOptions.EDGE_ROUTING) != EdgeRouting.ORTHOGONAL
            ) {
            return new AnalysisFailed(AnalysisFailed.Type.Failed, new IllegalStateException(
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
        
        // north/southports need to be handled specially 
        // every edge that starts or ends in an ns-port
        // contributes exactly one bendpoint. Such an 
        // edge is not considered axis-parallel. For an easy 
        // calculation we add a second (pseudo) bendpoint,
        // just to mark the edge as not being axis-parallel.
        Iterator<EObject> allContents = parentNode.eAllContents();
        int specialBendpoints = 0;
        while (allContents.hasNext()) {
            EObject e = allContents.next();
            if (e instanceof KEdge) {
                KEdge kedge = (KEdge) e;
                
                // get the direction of the edge's parent node
                KNode parent = kedge.getSource().getParent();
                if (kedge.getTarget().getParent() != kedge.getSource().getParent()) {
                    // external port dummy
                    if (KimlUtil.isDescendant(kedge.getTarget(), kedge.getSource())) {
                        parent = kedge.getSource();
                    }
                }
                
                KLayoutData parentLayout = parent.getData(KLayoutData.class);
                Direction direction = parentLayout.getProperty(LayoutOptions.DIRECTION);
                
                // edge's source
                KPort src = kedge.getSourcePort();
                if (src != null) {
                    KLayoutData pld = src.getData(KLayoutData.class);
                    if (LEFT_TO_RIGHT.contains(direction)) {
                        if (NORTH_SOUTH.contains(pld.getProperty(LayoutOptions.PORT_SIDE))) {
                            specialBendpoints++;
                        }
                    } else {
                        if (EAST_WEST.contains(pld.getProperty(LayoutOptions.PORT_SIDE))) {
                            specialBendpoints++;
                        }
                    }
                }
                
                // edge's target
                KPort tgt = kedge.getTargetPort();
                if (tgt != null) {
                    KLayoutData pld = tgt.getData(KLayoutData.class);
                    if (LEFT_TO_RIGHT.contains(direction)) {
                        if (NORTH_SOUTH.contains(pld.getProperty(LayoutOptions.PORT_SIDE))) {
                            specialBendpoints++;
                        }
                    } else {
                        if (EAST_WEST.contains(pld.getProperty(LayoutOptions.PORT_SIDE))) {
                            specialBendpoints++;
                        }
                    }
                }
            }
        }

        int notAxisParallel = (bendpoints + specialBendpoints) / 2;
        int axisParallel = edgeCount + dummies - notAxisParallel;

        progressMonitor.done();

        return axisParallel;
    }

}
