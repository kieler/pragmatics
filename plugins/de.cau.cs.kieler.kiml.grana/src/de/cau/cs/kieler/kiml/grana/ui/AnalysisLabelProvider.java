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

import org.eclipse.jface.viewers.LabelProvider;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.AnalysisCategory;

/**
 * The label provider for the analysis selection dialog.
 * 
 * @author mri
 */
public class AnalysisLabelProvider extends LabelProvider {

    /**
     * {@inheritDoc}
     */
    public String getText(final Object element) {

        if (element instanceof AbstractInfoAnalysis) {
            AbstractInfoAnalysis analysis = (AbstractInfoAnalysis) element;
            return analysis.getName();
        } else if (element instanceof AnalysisCategory) {
            AnalysisCategory category = (AnalysisCategory) element;
            return category.getName();
        }

        return super.getText(element);
    }
}
