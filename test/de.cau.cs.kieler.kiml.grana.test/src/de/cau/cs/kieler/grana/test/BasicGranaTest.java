/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.grana.test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.elk.core.service.ElkServicePlugin;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.ImmutableSet;

import de.cau.cs.kieler.grana.AnalysisData;
import de.cau.cs.kieler.grana.AnalysisFailed;
import de.cau.cs.kieler.grana.AnalysisService;
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
        //KimlUiPlugin.getDefault();
        // FIXME elkMigrate necessary?
        
        // as well as the kiml.service plugin
        //KimlServicePlugin.getDefault();
        ElkServicePlugin.getInstance();

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
        for (int i = 0; i < 2; i++) {

            // create the batch
            Collection<AnalysisData> analyses = AnalysisService.getInstance().getAnalyses();

            // create a batch job for every selected file

            Map<String, Object> currResults =
                    AnalysisService.getInstance().analyze(testObject.getKnode(), analyses,
                            new BasicProgressMonitor()).getResults();

            if (prevResults != null) {
                for (Entry<String, Object> e : prevResults.entrySet()) {
                    Assert.assertTrue(currResults.containsKey(e.getKey()));

                    Object nValue = currResults.get(e.getKey());
                    if (e.getValue() instanceof AnalysisFailed && nValue instanceof AnalysisFailed) {
                        if (((AnalysisFailed) e.getValue()).getType()
                                    != AnalysisFailed.Type.Configuration
                                && ((AnalysisFailed) nValue).getType()
                                    != AnalysisFailed.Type.Configuration) {
                            System.err.println("Analysis "
                                    + e.getKey()
                                    + " failed internally: "
                                    + testObject.getFile().getAbsolutePath() + " "
                                    + ((AnalysisFailed) nValue).getException());
                        }
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
    
    // exclude some models from testing as we know they take very long
    final Set<String> exclude = ImmutableSet.of(
            "node_placement",
            "greedy_switch_testgraphs",
            "sausagefolding");
    // other than that use as many models as possible
    final TestPath[] testPaths = {
        new TestPath("klay_layered", true, true, TestPath.Type.KGRAPH, exclude)
    };
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected TestPath[] getBundleTestPath() {
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
