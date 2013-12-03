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
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.NorthSouthPortPostprocessor;
import de.cau.cs.kieler.klay.layered.properties.NodeType;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.layered.test.config.OrthogonalEdgeRoutingLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic tests for the {@link NorthSouthPortPostprocessor}.
 * 
 * @author uru
 */
@Ignore
public class NorthSouthPortPostprocessorTest extends AbstractLayeredProcessorTest {

    private int noOverallNodes = 0;
    private int noTypeNodes = 0;

    // CHECKSTYLEOFF javadoc
    public NorthSouthPortPostprocessorTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        List<ILayoutConfigurator> list = Lists.newArrayList();
        list.add(new OrthogonalEdgeRoutingLayoutConfigurator());
        return list;
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths =
                { new TestPath("klay_layered/north_south_ports", false, false, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        lgraphs = layered.runLayoutTestUntil(NorthSouthPortPostprocessor.class, false);

        // count the number of overall nodes and of the tested type
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    noOverallNodes++;
                    if (node.getProperty(Properties.NODE_TYPE) == NodeType.NORTH_SOUTH_PORT) {
                        noTypeNodes++;
                    }
                }
            }
        }

        lgraphs = layered.runLayoutTestUntil(NorthSouthPortPostprocessor.class, true);
    }

    /**
     * No node exists with {@link NodeType} {@link NodeType#NORTH_SOUTH_PORT}. Only such nodes are
     * deleted.
     */
    @Test
    public void testRemovedNodes() {
        int noNodesAfter = 0;
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    assertTrue(node.getProperty(Properties.NODE_TYPE) != NodeType.NORTH_SOUTH_PORT);
                    noNodesAfter++;
                }
            }
        }

        assertTrue(noNodesAfter == (noOverallNodes - noTypeNodes));
    }

}
