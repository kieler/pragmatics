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

import java.io.File;
import java.util.List;

import org.junit.runners.model.FrameworkMethod;

import de.cau.cs.kieler.core.test.runners.KielerTestRunner;

/**
 * This test runner is used by the GraphAutomatedTest.
 * 
 * @author Wahbi
 */
public class KlayTestRunner extends KielerTestRunner {

    /**
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
            ((KlayAutomatedJUnitTest) object).GraphAutomatedTestInitialization();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRunnerName(List<Object[]> parameterObjectList, int parameterIndex) {
        Object[] objectArray = parameterObjectList.get(parameterIndex);
        File file = (File) objectArray[0];
        return file.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTestName(List<Object[]> parameterObjectList, int parameterIndex,
            FrameworkMethod method) {
        return getRunnerName(parameterObjectList, parameterIndex);
    }

}
