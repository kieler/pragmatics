/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisFailed;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.service.KimlServicePlugin;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.klay.test.KlayAutomatedJUnitTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.runner.KlayTestRunner;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.GraphTestUtil;
import de.cau.cs.kieler.klay.test.utils.TestPath;

/**
 * Executes all available grana analyses multiple times and checks that the results of consecutive
 * runs are the same.
 * 
 * Note: When an analysis fails, we assure that it fails every time. Failure might occur due to bad
 * test graphs. For instance, the planarity test does not support hierarchical ports.
 * 
 * @author uru
 */
@RunWith(KlayTestRunner.class)
public class BasicGranaTest extends KlayAutomatedJUnitTest {

    /** The graph files. */
    private static List<GraphTestObject> graphsList;

    /**
     * Initialization - Load the graphs to be tested. This is called in
     * {@link KlayTestRunner#initialize(Object)}.
     */
    public void graphAutomatedTestInitialization() {
        // make sure the ui plugin is loaded, as it holds required options.
        KimlUiPlugin.getDefault();
        // as well as the kiml.service plugin
        KimlServicePlugin.getDefault();

        graphsList = GraphTestUtil.loadGraphs(getBundleTestPath());
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
                Object[] objectArray = new Object[1];
                objectArray[0] = file;
                parameters.add(objectArray);
            }
        }

        return parameters;
    }

    private GraphTestObject testObject;

    public BasicGranaTest(final GraphTestObject testObject) {
        this.testObject = testObject;
    }

    /**
     * Test that consecutive analyses runs on the same graph yield the same results.
     */
    @Test
    public void runConsecutiveAnalysesSameResults() {

        Map<String, Object> prevResults = null;
        for (int i = 0; i < 10; i++) {

            // create the batch
            Collection<AnalysisData> analyses = AnalysisService.getInstance().getAnalyses();

            // create a batch job for every selected file

            Map<String, Object> currResults =
                    AnalysisService.getInstance().analyze(testObject.getKnode(), analyses,
                            new BasicProgressMonitor());

            if (prevResults != null) {
                for (Entry<String, Object> e : prevResults.entrySet()) {
                    Assert.assertTrue(currResults.containsKey(e.getKey()));

                    Object nValue = currResults.get(e.getKey());
                    if (e.getValue() instanceof AnalysisFailed && nValue instanceof AnalysisFailed) {
                        System.err.println("Analysis failed internally: "
                                + testObject.getFile().getAbsolutePath() + " "
                                + ((AnalysisFailed) nValue).getException());
                    } else if (e.getValue() instanceof Object[]) {
                        Assert.assertArrayEquals((Object[]) nValue, (Object[]) e.getValue());
                    } else {
                        Assert.assertEquals(nValue, e.getValue());
                    }
                }
            }
            prevResults = currResults;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TestPath[] getBundleTestPath() {
        // all models that we can find
        TestPath[] testPaths = { new TestPath("klay_layered", true, true, TestPath.Type.KGRAPH) };
        return testPaths;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return null;
    }

}
