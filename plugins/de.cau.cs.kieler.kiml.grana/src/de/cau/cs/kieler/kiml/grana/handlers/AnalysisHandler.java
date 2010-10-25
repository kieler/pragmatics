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
import java.util.Map;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.ui.AnalysisResultDialog;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyzer;
import de.cau.cs.kieler.kiml.grana.views.AnalysisResultViewPart;

/**
 * The handler that is responsible for receiving user input on the analyses to
 * perform and performing them on a graphical diagram.
 * 
 * @author mri
 */
public class AnalysisHandler extends AbstractAnalysisHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
       
        // get the active editor
        IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
        // get the last selected analyses
        Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
        List<AbstractInfoAnalysis> analyses = getLastAnalysesSelection();
        // perform the analyses on the active diagram
        Map<String, Object> results =
                DiagramAnalyzer.analyse(editorPart, null, analyses, true);
        // refresh the result view
        AnalysisResultViewPart view = AnalysisResultViewPart.findView();
        if (view != null) {
            view.setAnalysisResults(analyses, results);
        }
        // prepare the result dialog
        AnalysisResultDialog resultDialog =
                new AnalysisResultDialog(shell, analyses, results);
        // only show the result dialog if there is something to show
        if (!resultDialog.isEmpty()) {
            resultDialog.open();
        }

        return null;
    }
}
