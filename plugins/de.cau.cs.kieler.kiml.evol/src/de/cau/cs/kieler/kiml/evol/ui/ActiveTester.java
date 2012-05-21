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
package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.core.expressions.PropertyTester;

import de.cau.cs.kieler.kiml.evol.EvolLayoutConfig;
import de.cau.cs.kieler.kiml.service.LayoutInfoService;

/**
 * Property tester that Determines whether evolutionary layout is active.
 *
 * @author msp
 */
public class ActiveTester extends PropertyTester {

    /**
     * {@inheritDoc}
     */
    public boolean test(final Object receiver, final String property, final Object[] args,
            final Object expectedValue) {
        return LayoutInfoService.getInstance().getConfigProperties()
                .getProperty(EvolLayoutConfig.ACTIVATION);
    }

}
