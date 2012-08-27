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
package de.cau.cs.kieler.klay.test.runner;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * An Example test Class
 * 
 * @author Wahbi
 */
public class GraphUnitTest extends KlayAutomatedJUnitTest {

    // Object containing the current graphTestObject(File,KNode)
    private GraphTestObject graphObject;

    public GraphUnitTest(final GraphTestObject testObject) {
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

    @Test
    public void testA() {
        assertTrue(graphObject.getFile().getName().equals("backhoe-adapted.kids"));
    }

    @Test
    public void testB() {
        assertTrue(graphObject.getFile().getName().equals("backhoe-adapted.kids"));
    }

}
