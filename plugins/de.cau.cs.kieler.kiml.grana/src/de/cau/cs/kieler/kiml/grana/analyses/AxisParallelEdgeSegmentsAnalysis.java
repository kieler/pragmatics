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

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.options.EdgeRouting;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

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
                        .getResult(BendsAnalysis.ID))[BendsAnalysis.INDEX_SUM];

        int notAxisParallel = bendpoints / 2;
        int axisParallel = edgeCount + dummies - notAxisParallel;

        progressMonitor.done();

        return axisParallel;
    }

}
