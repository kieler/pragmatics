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

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.graph.LNode;
import de.cau.cs.kieler.klay.layered.graph.Layer;
import de.cau.cs.kieler.klay.layered.intermediate.InLayerConstraintProcessor;
import de.cau.cs.kieler.klay.layered.properties.InLayerConstraint;
import de.cau.cs.kieler.klay.layered.properties.Properties;
import de.cau.cs.kieler.klay.layered.test.AbstractLayeredProcessorTest;
import de.cau.cs.kieler.klay.test.config.BasicLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * Basic tests for the {@link InLayerConstraintProcessor}.
 * 
 * @author uru
 */
public class InLayerConstraintProcessorTest extends AbstractLayeredProcessorTest {

    /** Should existing properties be overwritten by random constraints? */
    private static final boolean RANDOM_CONSTRAINTS = true;

    // CHECKSTYLEOFF javadoc
    public InLayerConstraintProcessorTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
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

                if (RANDOM_CONSTRAINTS) {
                    // assure that in layer constraints are set, assign random values if required
                    for (KNode n : root.getChildren()) {
                        KShapeLayout layout = n.getData(KShapeLayout.class);
                        double r = random.nextDouble();
                        // CHECKSTYLEOFF Magic Numbers
                        if (r < 0.2) {
                            layout.setProperty(Properties.IN_LAYER_CONSTRAINT,
                                    InLayerConstraint.TOP);
                        } else if (r < 0.8) {
                            layout.setProperty(Properties.IN_LAYER_CONSTRAINT,
                                    InLayerConstraint.NONE);
                        } else {
                            layout.setProperty(Properties.IN_LAYER_CONSTRAINT,
                                    InLayerConstraint.BOTTOM);
                        }
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
        lgraphs = layered.runLayoutTestUntil(InLayerConstraintProcessor.class);

    }

    /**
     * Nodes are ordered according to their value of {@link InLayerConstraint}.
     */
    @Test
    public void testNodeMargins() {
        for (LGraph g : lgraphs) {
            for (Layer layer : g.getLayers()) {
                InLayerConstraint last = null;
                for (LNode node : layer.getNodes()) {
                    InLayerConstraint current = node.getProperty(Properties.IN_LAYER_CONSTRAINT);

                    System.out.println(current);

                    if (last == null) {
                        last = current;
                    } else if (current != last) {
                        // if the value changes check valid transitions
                        if (current == InLayerConstraint.NONE) {
                            assertTrue(last == InLayerConstraint.TOP);
                        } else if (current == InLayerConstraint.BOTTOM) {
                            assertTrue(last == InLayerConstraint.TOP
                                    || last == InLayerConstraint.NONE);
                        } else {
                            // fail!
                            assertTrue(1 == 0);
                        }

                        last = current;
                    }
                }
            }
        }
    }

}
