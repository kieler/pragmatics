/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

/**
 * Classes implementing this interface provide a viewer for one or more types of models.
 * 
 * @author mri
 */
public interface IViewerProvider {

    /**
     * Returns a viewer for the model types supported by this provider attached as to the given
     * composite.
     * 
     * @param parent
     *            the parent composite
     * 
     * @return a viewer for the supported model types
     */
    Viewer getViewer(final Composite parent);

    /**
     * Returns whether the given model is supported by this provider.
     * 
     * @param model
     *            the model
     * @return true if the given model is supported by this provider; false else
     */
    boolean isModelSupported(final Object model);

}
