/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui;

import java.util.List;

import de.cau.cs.kieler.kiml.grana.AnalysisData;

/**
 * The interface for listeners on the selection of an {AnalysisSelectionViewer}.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public interface ISelectionListener {

    /**
     * This method is invoked when the selection of analyses changed.
     * 
     * @param selectedAnalyses
     *            the selected analyses
     */
    void selectionChanged(final List<AnalysisData> selectedAnalyses);
    
}
