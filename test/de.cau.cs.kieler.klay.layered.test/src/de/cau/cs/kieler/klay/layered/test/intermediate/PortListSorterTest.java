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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.options.PortConstraints;
import de.cau.cs.kieler.kiml.options.PortSide;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.LPort;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LongEdgeJoiner;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.BasicLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;
import de.cau.cs.kieler.klay.test.utils.TestPath.Type;

/**
 * Basic tests for the {@link de.cau.cs.kieler.klay.layered.intermediate.PortListSorter
 * PortListSorter}.
 * 
 * The ports on each side are either ordered according to their {@link LayoutOptions#PORT_INDEX} or,
 * if no index is set, depending on the position of the port. However, the indices for each side are
 * distinct and do not necessarily have to be gapless.
 * 
 * E.g.
 * 
 * <pre>
 *      0 1 2
 *   |--------|0
 *   |        |
 *   |________|7
 *      9  2
 * </pre>
 * 
 * 
 * The test assures that only {@link PortConstraints#FIXED_ORDER} is set to nodes, and assigns an
 * index to all ports. The test runs until the actual port positions are set, as after the
 * {@link PortListSorter} only the order within the nodes port list is adapted, not the actual
 * coordinates.
 * 
 * @author uru
 */
public class PortListSorterTest extends AbstractLayeredProcessorTest {

    /**
     * The processor only considers nodes with port constraints equal or more restrictive than
     * FIXED_ORDER.
     */
    private ImmutableList<PortConstraints> invalidConstraints = ImmutableList.of(
            PortConstraints.UNDEFINED, PortConstraints.FREE, PortConstraints.FIXED_SIDE,
            PortConstraints.FIXED_POS); // important to include fixed pos, otherwise the PORT_INDEX
                                        // is ignored.

    // CHECKSTYLEOFF javadoc
    public PortListSorterTest(final GraphTestObject testObject, final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> list = Lists.newArrayList();
        list.add(new BasicLayoutConfigurator("") {

            public void applyConfiguration(final KNode root) {
                // guarantee that all node's port constraints are at least FIXED_ORDER
                // this way it can be guaranteed that the tests are applied at all
                for (KNode n : root.getChildren()) {
                    KShapeLayout layout = n.getData(KShapeLayout.class);
                    if (invalidConstraints.contains(layout
                            .getProperty(LayoutOptions.PORT_CONSTRAINTS))) {
                        layout.setProperty(LayoutOptions.PORT_CONSTRAINTS,
                                PortConstraints.FIXED_ORDER);
                    }
                    int index = 0;
                    for (KPort port : n.getPorts()) {
                        KShapeLayout portLayout = port.getData(KShapeLayout.class);
                        portLayout.setProperty(LayoutOptions.PORT_INDEX, index++);
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
        TestPath[] testPaths = {// new TestPath("misc/random", false, false, TestPath.Type.KGRAPH),
                new TestPath("misc/random_w_ports", false, false, Type.KGRAPH) };
        return testPaths;
    }

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        // FIXME chose the phase in which the port positions are actually decided.
        lgraphs = layered.runLayoutTestUntil(LongEdgeJoiner.class);
    }

    /**
     * All ports have to be ordered strictly monotonically on each side.
     */
    @Test
    public void testNodeConstraints() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    Double lastPos = null;
                    PortSide lastSide = null;

                    for (LPort port : node.getPorts()) {

                        // upon side change reset the reference position
                        if (lastSide == null || lastSide != port.getSide()) {
                            if (port.getSide() == PortSide.NORTH
                                    || port.getSide() == PortSide.SOUTH) {
                                lastPos = port.getPosition().x;
                            } else if (port.getSide() == PortSide.EAST
                                    || port.getSide() == PortSide.WEST) {
                                lastPos = port.getPosition().y;
                            }
                            lastSide = port.getSide();
                            continue;
                        }

                        // check the positions
                        if (port.getSide() == PortSide.NORTH) {
                            assertTrue(lastPos <= port.getPosition().x);
                        } else if (port.getSide() == PortSide.EAST) {
                            assertTrue(lastPos <= port.getPosition().y);
                        } else if (port.getSide() == PortSide.SOUTH) {
                            assertTrue(lastPos >= port.getPosition().x);
                        } else if (port.getSide() == PortSide.WEST) {
                            assertTrue(lastPos >= port.getPosition().y);
                        }

                    }
                }
            }
        }
    }

    /**
     * The port list has to be sorted from NORTH till WEST.
     */
    @Test
    public void testPortSides() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    PortSide lastSide = null;
                    for (LPort port : node.getPorts()) {

                        // upon a side change check that the next side is valid
                        if (lastSide != port.getSide()) {
                            if (lastSide == null) {
                                assertTrue(port.getSide() == PortSide.NORTH
                                        || port.getSide() == PortSide.EAST
                                        || port.getSide() == PortSide.SOUTH
                                        || port.getSide() == PortSide.WEST);
                            } else if (lastSide == PortSide.NORTH) {
                                assertTrue(port.getSide() == PortSide.EAST
                                        || port.getSide() == PortSide.SOUTH
                                        || port.getSide() == PortSide.WEST);
                            } else if (lastSide == PortSide.EAST) {
                                assertTrue(port.getSide() == PortSide.SOUTH
                                        || port.getSide() == PortSide.WEST);
                            } else if (lastSide == PortSide.SOUTH) {
                                assertTrue(port.getSide() == PortSide.WEST);
                            } else if (lastSide == PortSide.WEST) {
                                // not allowed
                                assertTrue(false);
                            }

                            // remember last
                            lastSide = port.getSide();
                        }
                    }
                }
            }
        }
    }
}
