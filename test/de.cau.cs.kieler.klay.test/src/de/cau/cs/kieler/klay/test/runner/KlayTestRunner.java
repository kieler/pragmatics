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

import java.util.List;

import org.junit.runners.model.FrameworkMethod;

import de.cau.cs.kieler.core.test.runners.KielerTestRunner;
import de.cau.cs.kieler.klay.test.KlayAutomatedJUnitTest;
import de.cau.cs.kieler.klay.test.utils.GraphTestObject;

/**
 * This test runner is used by the GraphAutomatedTest.
 * 
 * In the initialize() method it calls the GraphAutomatedTestInitialization() explicitly that will
 * load the graphs to test with the appropriate options.
 * 
 * It further provides names for the tests derived from their corresponding file's path names.
 * 
 * @author wah
 */
public class KlayTestRunner extends KielerTestRunner {

    /**
     * Instantiates a new KielerTestRunner. It further does the initialization with a first instance
     * of the class to test (parameter values given to the constructor are NULL) and afterwards
     * calls the method for getting the parameters for the parameterized test run.
     * 
     * @param klass
     * @throws Throwable
     */
    public KlayTestRunner(Class<?> klass) throws Throwable {
        super(klass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Object object) {
        if (object instanceof KlayAutomatedJUnitTest)
            // the object is always a KlayAutomatedJUnitTest that is why we don't need to throw an
            // exception
            ((KlayAutomatedJUnitTest) object).GraphAutomatedTestInitialization();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRunnerName(List<Object[]> parameterObjectList, int parameterIndex) {
        Object[] objectArray = parameterObjectList.get(parameterIndex);
        // The objectArray is always affected with a GraphTestObject that is why this method is save
        GraphTestObject file = (GraphTestObject) objectArray[0];
        return file.getFile().toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTestName(List<Object[]> parameterObjectList, int parameterIndex,
            FrameworkMethod method) {
        Object[] objectArray = parameterObjectList.get(parameterIndex);
        GraphTestObject file = (GraphTestObject) objectArray[0];
        return method.getName().concat(" - ").concat(file.getFile().toString());
    }

}
