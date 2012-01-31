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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.kiml.grana.ui.GranaUIUtil;
import de.cau.cs.kieler.kiml.grana.util.DiagramAnalyzer;
import de.cau.cs.kieler.kiml.grana.util.GranaUtil;
import de.cau.cs.kieler.kiml.grana.visualization.VisualizationServices;
import de.cau.cs.kieler.kiml.service.grana.AnalysisData;

/**
 * The handler that is responsible for receiving user input on the analyses to
 * perform and performing them on a graphical diagram.
 * 
 * @author mri
 */
public class AnalysisHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
       
        // get the active editor
        IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
        // set the current shell
        Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
        GranaUIUtil.setCurrentShell(shell);
        // get the last selected analyses
        List<AnalysisData> analyses = GranaUtil.getLastAnalysesSelection();
        
        if (analyses.isEmpty()) {
            MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "No Analyses",
                    "No analyses are selected. Please configure analyses in the button menu.");
        } else {
            // perform the analyses on the active diagram
            Map<String, Object> results = DiagramAnalyzer.analyze(editorPart, null, analyses, true);
            // visualize the results
            VisualizationServices.getInstance().visualize(analyses, results, false);
        }
        return null;
    }
}
