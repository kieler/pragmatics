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
package de.cau.cs.kieler.kiml.grana.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.kiml.grana.ui.AnalysisSelectionDialog;
import de.cau.cs.kieler.kiml.grana.util.GranaUtil;
import de.cau.cs.kieler.kiml.service.AnalysisService;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * The handler which responsibility is to configure the analyses selection.
 * 
 * @author mri
 */
public class ConfigureAnalysesHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        // let the user select the analyses
        Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
        AnalysisSelectionDialog selectionDialog = new AnalysisSelectionDialog(shell,
                AnalysisService.getInstance().getCategories(), GranaUtil.getLastAnalysesSelection());
        int code = selectionDialog.open();
        if (code == Dialog.OK) {
            // get the selected analyses
            List<AnalysisData> analyses = selectionDialog.getAnalyses();
            // save the last user selection
            GranaUtil.setLastAnalysesSelection(analyses);
        }

        return null;
    }

}
