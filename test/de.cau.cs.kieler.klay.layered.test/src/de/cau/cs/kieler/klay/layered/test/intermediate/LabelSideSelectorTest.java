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
import de.cau.cs.kieler.klay.layered.graph.LEdge;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LLabel;
import de.cau.cs.kieler.klay.layered.graph.LLabel.LabelSide;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LabelSideSelector;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.layered.test.config.OrthogonalEdgeRoutingLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic tests for the {@link LabelSideSelector}.
 * 
 * @author uru
 */
public class LabelSideSelectorTest extends AbstractLayeredProcessorTest {

    // CHECKSTYLEOFF javadoc
    public LabelSideSelectorTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> list = Lists.newArrayList();
        list.add(new OrthogonalEdgeRoutingLayoutConfigurator() {

            @Override
            public void applyConfiguration(final KNode root) {
                super.applyConfiguration(root);

                // simulate labels placed at the center of the edge
                for (KNode n : root.getChildren()) {
                    for (KEdge e : n.getOutgoingEdges()) {
                        KLabel label = KimlUtil.createInitializedLabel(e);
                        label.setText(String.valueOf(random.nextDouble()));
                        // CHECKSTYLEOFF Magic Numbers
                        label.getData(KShapeLayout.class).setProperty(
                                LayoutOptions.EDGE_LABEL_PLACEMENT,
                                random.nextDouble() < 0.5 ? EdgeLabelPlacement.CENTER
                                        : EdgeLabelPlacement.TAIL);
                    }
                }
            }
        });
        return list;
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = { new TestPath("random", false, false, TestPath.Type.KGRAPH),
        // TODO LabelSideSelector is not added as processor if no edges are present
        // new TestPath("label_placement", false, false, TestPath.Type.GMF)
                };
        return testPaths;
    }

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        lgraphs = layered.runLayoutTestUntil(LabelSideSelector.class);
    }

    /**
     * All labels on ports and edges have an assigned {@link LabelSide}.
     */
    @Test
    public void testRemovedNodes() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {

                    // port labels
                    for (LPort port : node.getPorts()) {
                        for (LLabel label : port.getLabels()) {
                            assertTrue(label.getSide() != LabelSide.UNKNOWN);
                        }
                    }

                    for (LEdge edge : node.getOutgoingEdges()) {
                        for (LLabel label : edge.getLabels()) {
                            assertTrue(label.getSide() != LabelSide.UNKNOWN);
                        }
                    }
                }
            }
        }
    }

}
