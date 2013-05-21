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
package de.cau.cs.kieler.klay.layered.test;

import java.util.List;
import java.util.Random;

import org.junit.Before;

import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.klay.layered.test.phases.SimplePhaseLayoutConfigurator;
import de.cau.cs.kieler.klay.test.KlayAutomatedJUnitTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Common setup for processor tests of the layered layout algorithm.
 * 
 * @author uru
 */
public abstract class AbstractLayeredProcessorTest extends KlayAutomatedJUnitTest {

    // CHECKSTYLEOFF Modifier - Members used in subclasses.

    /** The internal graph information, i.e., the file and the KNode. */
    protected GraphTestObject graphObject;
    /** Holds the LGraphs resulting from a layout run. <b>Not</b> assigned by default! */
    protected List<LGraph> lgraphs;
    /** Holds the layered layout provider that is used to perform the layout. */
    protected LayeredLayoutProvider layeredProvider = new LayeredLayoutProvider();
    /** Holds the current layout configurator. */
    protected ILayoutConfigurator configurator;
    /**
     * Holds an instance of the layered algorithm with
     * {@link LayeredLayoutProvider#startLayoutTest(de.cau.cs.kieler.core.kgraph.KNode)} already
     * called with the root node.
     */
    protected KlayLayered layered;

    /** Chose a fixed seed to allow reproducibility of failing tests. */
    protected static final int SEED = 1337;
    /** random object if required. */
    protected Random random = new Random(SEED);

    // CHECKSTYLEON Modifier

    /**
     * @param testObject
     *            the test object
     * @param config
     *            the layout configuration for a certain layout run
     */
    public AbstractLayeredProcessorTest(final GraphTestObject testObject,
            final ILayoutConfigurator config) {
        this.graphObject = testObject;
        this.configurator = config;
    }

    /**
     * Configuration cannot be performed in the constructor. @Before methods in subclasses are
     * called <em>after</em> this one.
     */
    @Before
    public void configure() {
        // apply the configurator
        configurator.applyConfiguration(graphObject.getKnode());
        
        // get an instance of layered
        this.layered = layeredProvider.startLayoutTest(graphObject.getKnode());
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        TestPath[] testPaths = { new TestPath("random", false, false, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * @return the current layout configurator casted to a {@link SimplePhaseLayoutConfigurator}.
     * @throws IllegalArgumentException
     *             if a different type of configurator is present.
     */
    protected SimplePhaseLayoutConfigurator getAndCheckSimpleConfig() {
        if (configurator instanceof SimplePhaseLayoutConfigurator) {
            return (SimplePhaseLayoutConfigurator) configurator;
        } else {
            throw new IllegalArgumentException("Test " + getClass().getSimpleName()
                    + " requires a " + SimplePhaseLayoutConfigurator.class.getSimpleName() + ".");
        }
    }
}
