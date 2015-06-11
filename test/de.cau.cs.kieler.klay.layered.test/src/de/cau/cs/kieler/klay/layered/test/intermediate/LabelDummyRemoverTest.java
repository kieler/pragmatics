/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlUtil;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LNode.NodeType;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LabelDummyRemover;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.layered.test.config.OrthogonalEdgeRoutingLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the {@link LabelDummyRemover}.
 * 
 * @author uru
 */
public class LabelDummyRemoverTest extends AbstractLayeredProcessorTest {

    private int noOverallNodes = 0;
    private int noTypeNodes = 0;

    // CHECKSTYLEOFF javadoc
    public LabelDummyRemoverTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> list = Lists.newArrayList();

        // assure that the processor is added to the layout configuration
        list.add(new OrthogonalEdgeRoutingLayoutConfigurator() {

            @Override
            public void applyConfiguration(final KNode root) {
                super.applyConfiguration(root);

                // simulate labels placed at the center of the edge
                for (KNode n : root.getChildren()) {
                    for (KEdge e : n.getOutgoingEdges()) {
                        KLabel label = KimlUtil.createInitializedLabel(e);
                        label.setText(String.valueOf(random.nextDouble()));
                        label.getData(KShapeLayout.class).setProperty(
                                LayoutOptions.EDGE_LABEL_PLACEMENT, EdgeLabelPlacement.CENTER);
                    }
                }
            }
        });
        return list;
    }

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(LabelDummyRemover.class, false, state);

        // count the number of overall nodes and of the tested type
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    noOverallNodes++;
                    if (node.getNodeType() == NodeType.LABEL) {
                        noTypeNodes++;
                    }
                }
            }
        }

        layered.runLayoutTestUntil(LabelDummyRemover.class, true, state);

    }

    /**
     * No node exists with {@link NodeType} {@link NodeType#LABEL}. Only such nodes were deleted.
     */
    @Test
    public void testRemovedNodes() {
        int noNodesAfter = 0;
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    assertTrue(node.getNodeType() != NodeType.LABEL);
                    noNodesAfter++;
                }
            }
        }

        assertTrue(noNodesAfter == (noOverallNodes - noTypeNodes));
    }

}
