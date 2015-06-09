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

import java.util.Random;

import org.junit.After;
import org.junit.Before;

import de.cau.cs.kieler.klay.layered.KlayLayered;
import de.cau.cs.kieler.klay.layered.LayeredLayoutProvider;
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

    /** The layered layout provider that is used to perform the layout. */
    protected final LayeredLayoutProvider layeredProvider = new LayeredLayoutProvider();
    /** The internal graph information, i.e., the file and the KNode. */
    protected GraphTestObject graphObject;
    /** The current layout configurator. */
    protected ILayoutConfigurator configurator;
    /** The layout algorithm. */
    protected KlayLayered layered;
    /** The test execution state of the {@link KlayLayered} layout algorithm. */
    protected KlayLayered.TestExecutionState state;

    /** Chose a fixed seed to allow reproducibility of failing tests. */
    protected static final int SEED = 1337;
    /** random object if required. */
    protected final Random random = new Random(SEED);

    /** Epsilon value for double comparisons (equal). */
    private static final double COMPARE_EPSILON = 0.0001d;

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

        // start the test
        state = layeredProvider.startLayoutTest(graphObject.getKnode());
        layered = layeredProvider.getLayoutAlgorithm();
    }

    /**
     * Cleanup some data.
     */
    @After
    public void cleanup() {
        graphObject = null;
        layered = null;
        state = null;
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {
        // some default test graphs that should be run by every test
        TestPath[] testPaths = { 
                new TestPath("misc/random", false, false, TestPath.Type.KGRAPH),
                // graphs that were attached to bug reports and should not fail anymore
                new TestPath("misc/bugs", true, false, TestPath.Type.KGRAPH)
        };
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

    /**
     * @param d1
     *            first value
     * @param d2
     *            second value
     * @return {@code true} if the two doubles are close enough to be considered equal.
     */
    protected boolean isCloseEnough(final double d1, final double d2) {
        return (Math.abs(d1 - d2) < COMPARE_EPSILON);
    }
}
