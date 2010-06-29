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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.kiml.grana.AbstractInfoAnalysis;
import de.cau.cs.kieler.kiml.grana.ui.AnalysisResultDialog;
import de.cau.cs.kieler.kiml.grana.ui.DiagramAnalyser;

/**
 * The handler which is responsible to perform the last selected analyses on a
 * graphical diagram.
 * 
 * @author mri
 */
public class FastAnalysisHandler extends AbstractAnalysisHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        // get the active editor
        IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
        // let the user select the analyses
        Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
        List<AbstractInfoAnalysis> result = getLastAnalysesSelection();
        AbstractInfoAnalysis[] analyses =
                new AbstractInfoAnalysis[result.size()];
        result.toArray(analyses);

        // perform the analyses on the active diagram
        Object[] results =
                DiagramAnalyser.analyse(editorPart, null, analyses, true);

        AnalysisResultDialog resultDialog =
                new AnalysisResultDialog(shell, analyses, results);
        // only show the result dialog if there is something to show
        if (!resultDialog.isEmpty()) {
            resultDialog.open();
        }

        return null;
    }

}
