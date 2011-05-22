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
package de.cau.cs.kieler.klighd.graphiti.piccolo;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

import de.cau.cs.kieler.klighd.IViewerProvider;

/**
 * A viewer provider for Graphiti diagrams.
 * 
 * @author mri
 */
public class GraphitiViewerProvider implements IViewerProvider {

    /**
     * {@inheritDoc}
     */
    public Viewer getViewer(final Composite parent) {
        return new PictogramViewer(parent);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isModelSupported(final Object model) {
        return model instanceof Diagram;
    }

}
