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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.keg.impl.KEGPackageImpl;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.klay.test.config.DummyLayoutConfigurator;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
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
    private static List<GraphTestObject> graphsList;
    private static List<ILayoutConfigurator> configurators;

    /**
     * Initialization - Load the graphs to be tested. This is called in
     * {@link KlayTestRunner#initialize(Object)}.
     * 
     * FIXME A static field is set in a method that is bound to a dummy instance. This is very bad.
     */
    public void graphAutomatedTestInitialization() {
        // make sure the ui plugin is loaded, as it holds required options.
        KimlUiPlugin.getDefault();
        KEGPackageImpl.init();
        
        graphsList = GraphTestUtil.loadGraphs(getBundleTestPath());
        configurators = getConfigurators();

        // guarantee that there is at least one configurator
        if (configurators == null) {
            configurators = Lists.newArrayList();
        }
        if (configurators.isEmpty()) {
            configurators.add(new DummyLayoutConfigurator());
        }
    }

    /**
     * Gets all graph files and provides the parameters for the consecutive tests run by the
     * {@link KlayTestRunner}.
     * 
     * @return a Collection of GraphTestObject Objects
     */
    @Parameters
    public static Collection<Object[]> getParameters() {
        LinkedList<Object[]> parameters = new LinkedList<Object[]>();

        // create a parameter for each graph with each configurator
        if (graphsList != null) {
            for (GraphTestObject file : graphsList) {
                for (ILayoutConfigurator c : configurators) {
                    Object[] objectArray = new Object[2];
                    objectArray[0] = file;
                    objectArray[1] = c;
                    parameters.add(objectArray);
                }
            }
        }

        return parameters;
    }

    /**
     * Wraps up klay pointer that is stored statically.
     */
    @AfterClass
    public static void graphAutomatedTestWrapup() {
        // Clear all static fields
        graphsList = null;
        configurators = null;
    }

    /**
     * Important for scanning files is the Plugin ID.
     * 
     * FIXME Is this used anywhere?
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

    /**
     * @return a list with layout configurators.
     */
    protected abstract List<ILayoutConfigurator> getConfigurators();

}
