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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.intermediate.EdgeAndLayerConstraintEdgeReverser;
import de.cau.cs.kieler.klay.layered.properties.LayerConstraint;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic tests for the {@link EdgeAndLayerConstraintEdgeReverser}.
 * 
 * @author uru
 */
public class EdgeAndLayerConstraintEdgeReverserTest extends AbstractLayeredProcessorTest {

    private static final String TEST_FOLDER = "klay_layered/layer_constraints";

    // CHECKSTYLEOFF javadoc
    public EdgeAndLayerConstraintEdgeReverserTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
        super(testObject, config);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = { new TestPath(TEST_FOLDER, false, false, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return Lists.newArrayList();
    }

    /**
     * Run the layout algorithm until the specified strategy finished.
     */
    @Before
    public void runUntil() {
        layered.runLayoutTestUntil(EdgeAndLayerConstraintEdgeReverser.class, state);
    }

    /**
     * Every node with a FIRST or FIRST_SEPARATE {@link LayerConstraint} must not have any incoming
     * edge. Analog for the LAST and LAST_SEPARATE constraints.
     */
    @Test
    public void testLayerConstraints() {
        for (LGraph g : state.getGraphs()) {
            for (LNode node : g.getLayerlessNodes()) {
                LayerConstraint constr = node.getProperty(Properties.LAYER_CONSTRAINT);
                if (constr == LayerConstraint.FIRST || constr == LayerConstraint.FIRST_SEPARATE) {
                    assertTrue(Iterables.isEmpty(node.getIncomingEdges()));
                } else if (constr == LayerConstraint.LAST
                        || constr == LayerConstraint.LAST_SEPARATE) {
                    assertTrue(Iterables.isEmpty(node.getOutgoingEdges()));
                }
            }
        }
    }

}
