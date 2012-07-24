/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kaom.diagram.custom.commands;

import org.eclipse.core.expressions.PropertyTester;

import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramEditor;

/**
 * This tester determines whether the provided property of activeEditor is
 * called by a kaom editor.
 * 
 * @author soh
 * @kieler.ignore (excluded from review process)
 */
public class KaomPropertyTester extends PropertyTester {

    /**
     * 
     * Creates a new KaomPropertyTester.
     * 
     */
    public KaomPropertyTester() {

    }

    /**
     * {@inheritDoc}
     */
    public boolean test(final Object receiver, final String property,
            final Object[] args, final Object expectedValue) {
        if (property.equals("activeKaomEditor")
                && receiver instanceof KaomDiagramEditor) {
            return true;
        }
        return false;
    }

}
