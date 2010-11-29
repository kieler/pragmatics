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
package de.cau.cs.kieler.kiml.grana.ui;

import org.eclipse.swt.widgets.Shell;

/**
 * A utility class for Grana UI functionality.
 * 
 * @author mri
 */
public final class GranaUIUtil {

    /** the current shell used in recent handlers. */
    private static Shell currentShell;

    /**
     * A private contructor to make this class not instantiable.
     */
    private GranaUIUtil() {
        // do nothing
    }

    /**
     * Returns the shell used in recent handler calls.
     * 
     * @return the shell
     */
    public static Shell getCurrentShell() {
        return currentShell;
    }

    /**
     * Sets the current shell.
     * 
     * @param shell
     *            the shell
     */
    public static void setCurrentShell(final Shell shell) {
        currentShell = shell;
    }
}
