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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import de.cau.cs.kieler.klay.test.runner.KlayTestRunner;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.GraphTestUtil;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * The class KlayAutomatedJUnitTest enables the integration of several KLAY execution runs into a
 * JUnit plugin test.
 * 
 * A new test plugin has to have a class C extending KlayAutomatedJUnitTest. The new class C has to
 * have "Test" in its name to be found as a JUnit class by the build system.
 * 
 * Test model files must be provided with the bundle implementing the KiemAutomatedJUnitTest
 * abstract class in a bundle directory specified by getBundleTestPath().
 * 
 * @author wah
 */
@RunWith(KlayTestRunner.class)
public abstract class KlayAutomatedJUnitTest {

    /** The graph files. */
    private static List<GraphTestObject> graphsList = new LinkedList<GraphTestObject>();

    /**
     * Gets all graph files and provides the parameters for the consecutive tests run by the
     * KlayTestRunner.
     * 
     * @return a Collection of GraphTestObject Objects
     */
    @Parameters
    public static Collection<Object[]> getGraphs() {
        LinkedList<Object[]> graphFilesList = new LinkedList<Object[]>();

        for (GraphTestObject file : graphsList) {
            Object[] objectArray = new Object[1];
            objectArray[0] = file;
            graphFilesList.add(objectArray);
        }
        return graphFilesList;
    }

    /**
     * Initialization - Load the graphs to be tested.
     */
    @Before
    public void graphAutomatedTestInitialization() {
        graphsList = GraphTestUtil.loadGraphs(getBundleTestPath());
    }

    /**
     * Wraps up klay pointer that is stored statically.
     */
    @AfterClass
    public static void kiemAutomatedJUnitTestWrapup() {
        // Clear all static fields
        graphsList.clear();
    }

    /**
     * Important for scanning files is the Plugin ID.
     * 
     * @return the plugin id
     */
    protected String getPluginId() {
        return "de.cau.cs.kieler.klay.test";
    }

    // -------------------------------------------------------------------------

    /**
     * Defines the directories where the graph files are located and its load options.
     * 
     * @return the Path
     */
    protected abstract TestPath[] getBundleTestPath();

}
