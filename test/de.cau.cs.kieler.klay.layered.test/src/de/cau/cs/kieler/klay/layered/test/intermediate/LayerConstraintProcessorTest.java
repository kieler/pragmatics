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

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.LayerConstraintProcessor;
import de.cau.cs.kieler.klay.layered.properties.LayerConstraint;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Basic tests for the {@link LayerConstraintProcessor}.
 * 
 * @author uru
 */
public class LayerConstraintProcessorTest extends AbstractLayeredProcessorTest {

    private static final String TEST_FOLDER = "klay_layered/layer_constraints";

    // CHECKSTYLEOFF javadoc
    public LayerConstraintProcessorTest(final GraphTestObject testObject,
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
        layered.runLayoutTestUntil(LayerConstraintProcessor.class, state);
    }

    /**
     * If the first layer contains FIRST_SEPARATE no other type is allowed there. All FIRST
     * constraints must be in the next layer. Analog for LAST_SEPARATE and LAST.
     */
    @Test
    public void testLayerConstraints() {
        for (LGraph g : state.getGraphs()) {

            Multimap<LayerConstraint, Integer> constraintMap = HashMultimap.create();

            for (Layer layer : g.getLayers()) {
                for (LNode node : layer.getNodes()) {
                    constraintMap.put(node.getProperty(Properties.LAYER_CONSTRAINT),
                            layer.getIndex());
                }
            }

            // if FIRST_SEPARATE nodes are available
            if (constraintMap.containsKey(LayerConstraint.FIRST_SEPARATE)) {
                Collection<Integer> firstSep = constraintMap.get(LayerConstraint.FIRST_SEPARATE);
                assertTrue(firstSep.size() == 1 && firstSep.contains(0));

                // first layer must not contain a NONE node
                assertTrue(!constraintMap.get(LayerConstraint.NONE).contains(0));

                // all FIRST nodes have to be at position 1
                Collection<Integer> first = constraintMap.get(LayerConstraint.FIRST);
                if (!first.isEmpty()) {
                    assertTrue(first.size() == 1 && first.contains(1));
                }
            } else {
                // all FIRST nodes must be at position 0
                Collection<Integer> first = constraintMap.get(LayerConstraint.FIRST);
                if (!first.isEmpty()) {
                    assertTrue(first.size() == 1 && first.contains(0));
                }
            }

            int lastLayer = g.getLayers().size();
            // if LAST_SEPARATE nodes are available
            if (constraintMap.containsKey(LayerConstraint.LAST_SEPARATE)) {
                Collection<Integer> lastSep = constraintMap.get(LayerConstraint.LAST_SEPARATE);
                assertTrue(lastSep.size() == 1 && lastSep.contains(lastLayer - 1));

                // last layer must not contain a NONE node
                assertTrue(!constraintMap.get(LayerConstraint.NONE).contains(lastLayer - 1));

                // all LAST nodes have to be at position lastLayer-2
                Collection<Integer> last = constraintMap.get(LayerConstraint.LAST);
                if (!last.isEmpty()) {
                    assertTrue(last.size() == 1 && last.contains(lastLayer - 2));
                }
            } else {
                // all LAST nodes must be at position lastLayer-1
                Collection<Integer> last = constraintMap.get(LayerConstraint.LAST);
                if (!last.isEmpty()) {
                    assertTrue(last.size() == 1 && last.contains(lastLayer - 1));
                }
            }
        }
    }
    
    /**
     * After constraint processing no empty layers should be left behind.
     * 
     * @see KIPRA-1561
     */
    @Test
    public void noEmptyLayerTest() {
        for (LGraph g : state.getGraphs()) {
            for (Layer layer : g.getLayers()) {
                assertTrue(!layer.getNodes().isEmpty());
            }
        }
    }
}
