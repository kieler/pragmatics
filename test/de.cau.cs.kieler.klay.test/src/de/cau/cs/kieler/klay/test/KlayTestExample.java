/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * An Example test Class.
 * 
 * @author wah
 */
public class KlayTestExample extends KlayAutomatedJUnitTest {

    // Object containing the current graphTestObject that contains both, a File and a KNode.
    private GraphTestObject graphObject;

    /**
     * Instantiates a new KlayTestExample test and set the graphObject to the current graph to test.
     * 
     * @param testObject the test object
     */
    public KlayTestExample(final GraphTestObject testObject) {
        graphObject = testObject;
    }

    /**
     * {@inheritDoc}
     */
    protected TestPath[] getBundleTestPath() {

        TestPath[] testPaths = { new TestPath("Esterel", true, false),
                new TestPath("Esterel", false, false) };
        return testPaths;
    }

    /**
     * A dummy test method that tests if the current graphFile equals a given file name..
     */
    @Test
    public void testA() {
        assertTrue(graphObject.getFile().getName().equals("backhoe-adapted.kids"));
    }

    /**
     * A dummy test method that tests if the current graphFile equals a given file name..
     */
    @Test
    public void testB() {
        assertTrue(graphObject.getFile().getName().equals("backhoe-adapted.kids"));
    }

}
