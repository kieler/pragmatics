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
package de.cau.cs.kieler.kiml.grana.ui.views;

import java.util.List;

import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.grana.ui.visualization.BoundVisualization;
import de.cau.cs.kieler.kiml.grana.ui.visualization.IVisualizationMethod;

/**
 * A visualization method which updates the result view.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class ViewVisualizationMethod implements IVisualizationMethod {

    /**
     * {@inheritDoc}
     */
    public void visualize(final String type,
            final List<BoundVisualization> visualizations) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                // refresh the result view
                AnalysisResultViewPart view = AnalysisResultViewPart.findView();
                if (view != null) {
                    view.setAnalysisResults(visualizations);
                }
            }
        });
    }

}
