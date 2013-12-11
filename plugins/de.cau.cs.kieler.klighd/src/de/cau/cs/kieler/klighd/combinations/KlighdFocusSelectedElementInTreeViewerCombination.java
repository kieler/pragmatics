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
package de.cau.cs.kieler.klighd.combinations;

import java.util.Iterator;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.klighd.effects.KlighdFocusInTreeViewerEffect;
import de.cau.cs.kieler.klighd.viewers.KlighdTreeSelection;

/**
 * A combination that realizes the focus of elements selected in KLighD views in related tree viewers.
 * 
 * @author chsch
 */
public class KlighdFocusSelectedElementInTreeViewerCombination extends AbstractCombination {
    
    /**
     * THE execute method!
     * 
     * @param state the trigger state the combination is sensitive to
     */
    public void execute(final SelectionState state) {
        final KlighdTreeSelection selection = state.getSelection(KlighdTreeSelection.class);
        
        if (selection == null || selection.getViewContext() == null) {
            return;
        }
        
        final Viewer sourceViewer = selection.getViewContext().getSourceWorkbenchPartViewer();

        final TreeViewer treeViewer;
        if (sourceViewer instanceof TreeViewer) {
            treeViewer = (TreeViewer) sourceViewer;
        } else {
            // in this case there is nothing to show the selected element in
            return;
        }

        final Iterator<?> it = selection.sourceElementIterator();
        if (it.hasNext()) {
            final Object o = it.next();
            if (o != null) {
                // take just the 1st element for the moment - should be improved sometimes...
                this.schedule(new KlighdFocusInTreeViewerEffect(o, treeViewer));
            }
        }
    }
}
