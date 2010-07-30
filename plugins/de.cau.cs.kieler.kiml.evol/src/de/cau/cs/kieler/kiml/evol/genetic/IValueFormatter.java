/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.genetic;

/**
 * Interface for value formatters.
 *
 * @author bdu
 *
 */
public interface IValueFormatter {
    /**
     *
     * @param o
     *            an object
     * @return A string that represents the value of the given object.
     */
    String getString(Object o);
}
