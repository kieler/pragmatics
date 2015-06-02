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

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.kiml.grana.AnalysisContext;
import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.service.KimlServicePlugin;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.klay.test.KlayAutomatedJUnitTest;
import de.cau.cs.kieler.klay.test.config.ILayoutConfigurator;
import de.cau.cs.kieler.klay.test.runner.KlayTestRunner;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;
import de.cau.cs.kieler.klay.test.utils.GraphTestUtil;

/**
 * Abstract implementation for a GrAna test file. Implementing classes have to specify which test cases
 * they use within the KIELER Models Repository by implementing the {@link #getBundleTestPath()} method.
 * To specify the desired analyses the {@link #getAnalysesIdsToRun()} method is implemented.
 * 
 * The identify the currently tested filed the {@link #testObject} can be accessed, results of the 
 * executed analyses are stored in the {@link #result} field.
 * 
 * 
 * @author uru
 */
@RunWith(KlayTestRunner.class)
public abstract class AbstractGranaTest extends KlayAutomatedJUnitTest {

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
    
    public AbstractGranaTest(final GraphTestObject testObject) {
        this.testObject = testObject;
    }
    
    @Before
    public void analyze() {
        // execute analyses
        Collection<AnalysisData> analyses = Lists.newArrayList();
        for (String id : getAnalysesIdsToRun()) {
            AnalysisData ad = AnalysisService.getInstance().getAnalysis(id);
            if (ad == null) {
                throw new IllegalArgumentException(id + " is not a valid analysis id.");
            }
            analyses.add(ad);
        }

        result =
                AnalysisService.getInstance().analyze(testObject.getKnode(), analyses,
                        new BasicProgressMonitor());
    }

    /**
     * Usually the layout configuration should be specified within the 
     * test file, ie the *.kgt file.
     */
    @Override
    protected List<ILayoutConfigurator> getConfigurators() {
        return null;
    }
    
    
    /* -------------------------------------------------
     *  To be used and implemented by the actual tests. 
     * ------------------------------------------------- */
    
    /** Holds the currently processed test case. */
    protected GraphTestObject testObject;

    /** Results of the executed analyses. */
    protected AnalysisContext result;
    
    /** Collection with analysis ids that should be executed. */
    protected abstract Collection<String> getAnalysesIdsToRun();

}
