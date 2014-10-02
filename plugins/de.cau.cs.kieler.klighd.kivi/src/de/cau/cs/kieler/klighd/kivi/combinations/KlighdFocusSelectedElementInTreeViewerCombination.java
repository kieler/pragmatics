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
package de.cau.cs.kieler.klighd.kivi.combinations;

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.klighd.kivi.effects.KlighdFocusInTreeViewerEffect;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.KlighdTreeSelection;

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

        final Viewer sourceViewer =
                getSourceWorkbenchPartViewer(selection.getViewContext().getSourceWorkbenchPart());

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

    /**
     * Returns the source workbench part viewer (experimental).
     *
     * @param workbenchPart the source model's {@link IWorkbenchPart}
     *
     * @return the source workbench part viewer(viewer the source model has been chosen in)
     */
    public Viewer getSourceWorkbenchPartViewer(final IWorkbenchPart workbenchPart) {
        if (workbenchPart == null) {
            return null;
        }

        try {
            return (Viewer) workbenchPart.getClass().getMethod("getViewer").invoke(workbenchPart);
        } catch (final Exception e) {
            final String msg = "KLighD: Determination of a viewer widget (beyond the KLighD viewer) "
                    + "showing the currently depicted model failed." + KlighdPlugin.LINE_SEPARATOR
                    + "This error may occured while trying to focus a depicted model "
                    + "element in a related editor.";
            StatusManager.getManager().addLoggedStatus(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID, msg));
        }
        return null;
    }
}
