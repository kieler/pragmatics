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

import java.util.List;

import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.grana.visualization.BoundVisualization;
import de.cau.cs.kieler.kiml.grana.visualization.IVisualizationMethod;

/**
 * A visualization method which displays the results in a dialog.
 * 
 * @author mri
 */
public class DialogVisualizationMethod implements IVisualizationMethod {

    /**
     * {@inheritDoc}
     */
    public void visualize(final String type,
            final List<BoundVisualization> visualizations) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                // prepare the result dialog
                AnalysisResultDialog resultDialog =
                        new AnalysisResultDialog(GranaUIUtil.getCurrentShell(),
                                visualizations);
                // only show the result dialog if there is something to show
                if (!resultDialog.isEmpty()) {
                    resultDialog.open();
                }
            }
        });
    }

}
