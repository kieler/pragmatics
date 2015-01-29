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
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.google.common.collect.ImmutableSet;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.AnalysisOptions;
import de.cau.cs.kieler.kiml.grana.IAnalysis;
import de.cau.cs.kieler.kiml.grana.analyses.LayersAnalysis.Layer;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutData;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.Direction;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * This analysis reports the number of inter-layer edge segments that 
 * are axis-aligned with the axis corresponding to the layout direction.
 * That is, for layout directions {@link Direction#LEFT} and {@link Direction#RIGHT}
 * the number of edge segments aligned with the x-axis is reported. An edge segment
 * counts when its start and end points lie in different layers as determined by the 
 * {@link LayersAnalysis}. Likewise for the vertical directions. 
 * This implies that for a horizontal layout direction the analysis results
 * for TOP and DOWN will be zero.
 * 
 * Preconditions: 
 * <ul>
 *  <li>Layout has to be performed using {@code de.cau.cs.kieler.klay.layered}.</li>
 *  <li>The layout option {@code de.cau.cs.kieler.klay.layered.unnecessaryBendpoints} 
 *      has to be set to {@code true}.</li>
 * </ul> 
 * 
 * @author uru
 */
public class AxisParallelEdgeSegmentsAnalysis implements IAnalysis {

    /**
     * Identifier of the edge crossings analysis.
     */
    public static final String ID = "de.cau.cs.kieler.kiml.grana.axisParallelEdgeSegments";

    /** Tolerance for float equality. */
    private static final float TOLERANCE = 0.00001f;

    //
    // Indexes for the result array.
    //
    private static final int INDEX_UP = 0;
    private static final int INDEX_LEFT = 1;
    private static final int INDEX_DOWN = 2;
    private static final int INDEX_RIGHT = 3;
    private static final int INDEX_SUM = 4;

    //
    // Internal property definitions to check the precondition.
    //
    private static final String KLAY_LAYERED_ID = "de.cau.cs.kieler.klay.layered";
    private static final IProperty<String> ALGORITHM = new Property<String>(
            "de.cau.cs.kieler.algorithm");
    private static final IProperty<Boolean> ADD_UNNECESSARY_BENDPOINTS = new Property<Boolean>(
            "de.cau.cs.kieler.klay.layered.unnecessaryBendpoints", false);

    /** Current layout direction. */
    private Direction direction;
    /** Whether hierarchy should be analyzed. */
    private boolean hierarchy = false;
    /** A finished instance of the layer analysis we depend on. */ 
    private LayersAnalysis layersAnalysis;
    
    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode, final AnalysisContext context,
            final IKielerProgressMonitor progressMonitor) {

        // check that the precondition is met
        KLayoutData ld = parentNode.getData(KLayoutData.class);
        if ((ld.getProperty(ALGORITHM) != null && !ld.getProperty(ALGORITHM)
                .equals(KLAY_LAYERED_ID)) || !ld.getProperty(ADD_UNNECESSARY_BENDPOINTS)) {
            return new AnalysisFailed(AnalysisFailed.Type.Failed, new IllegalStateException(
                    "KlayLayered has to be used and the "
                            + "unnecessaryBendpoints option must be set to true"));
        }

        progressMonitor.begin("Axis-Parallel edge segments analysis", 1);

        direction = ld.getProperty(LayoutOptions.DIRECTION);
        // retrieve layer analysis instance
        layersAnalysis = context.getAnalysisInstance(LayersAnalysis.class);
        hierarchy =
                parentNode.getData(KShapeLayout.class).getProperty(
                        AnalysisOptions.ANALYZE_HIERARCHY);

        // result collector
        int[] edgeDirections = { 0, 0, 0, 0, 0 };

        analyzeHierarchyLevel(parentNode, edgeDirections);
        
        // sum up all axis aligned edges
        edgeDirections[INDEX_SUM] = edgeDirections[INDEX_DOWN] + edgeDirections[INDEX_UP]
                        + edgeDirections[INDEX_RIGHT] + edgeDirections[INDEX_LEFT];
        
        progressMonitor.done();
        
        return new Object[] {
                edgeDirections[INDEX_UP],         
                edgeDirections[INDEX_LEFT],
                edgeDirections[INDEX_DOWN],
                edgeDirections[INDEX_RIGHT],
                edgeDirections[INDEX_SUM]
        };
    }

    private void analyzeHierarchyLevel(final KNode parentNode, final int[] edgeDirections) {
        if (parentNode.getChildren().isEmpty()) {
            return;
        }
        
        // get the layers for this hierarchy level
        List<Layer> layers;
        if (ImmutableSet.of(Direction.UP, Direction.DOWN).contains(direction)) {
            layers = layersAnalysis.getAllHorizontalLayers().get(parentNode);
        } else {
            layers = layersAnalysis.getAllVerticalLayers().get(parentNode);
        }
        
        // allow the query the layers by their coordinates to 
        //  decide whether two coordinates lie in the same layer
        NavigableMap<Float, Integer> map = new TreeMap<Float, Integer>();
        int index = 0;
        for (Layer l : layers) {
            map.put(l.start, index++);
        }

        for (KNode n : parentNode.getChildren()) {
            for (KEdge e : n.getOutgoingEdges()) {
                KEdgeLayout el = e.getData(KEdgeLayout.class);
                
                Iterator<KVector> it = el.createVectorChain().iterator();
                // the iterator is guaranteed to contain at least 2 elements
                KVector last = it.next();
                while (it.hasNext()) {
                    KVector current = it.next();

                    if (equals(last.y, current.y)) {

                        // only consider segments that span from 
                        //  one layer to another
                        int lastLayer = map.floorEntry((float) last.x).getValue();
                        int nowLayer = map.floorEntry((float) current.x).getValue();
                        if (lastLayer == nowLayer) {
                            continue;
                        }

                        if (last.x < current.x) {
                            edgeDirections[INDEX_RIGHT]++;
                        } else {
                            edgeDirections[INDEX_LEFT]++;
                        }
                    } else if (equals(last.x, current.x)) {

                        // only consider segments that span from 
                        //  one layer to another
                        int lastLayer = map.floorEntry((float) last.y).getValue();
                        int nowLayer = map.floorEntry((float) current.y).getValue();
                        if (lastLayer == nowLayer) {
                            continue;
                        }
                        
                        if (last.y < current.y) {
                            edgeDirections[INDEX_DOWN]++;
                        } else {
                            edgeDirections[INDEX_UP]++;
                        }
                    }

                    last = current;
                }
            }
        }

        // process further hierarchy levels
        if (hierarchy) {
            for (KNode n : parentNode.getChildren()) {
                analyzeHierarchyLevel(n, edgeDirections);
            }
        }

    }
    
    private boolean equals(final double x, final double y) {
        return Math.abs(x - y) < TOLERANCE;
    }

}
