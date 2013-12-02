/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.ui.views;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.grana.AnalysisData;
import de.cau.cs.kieler.kiml.grana.AnalysisService;
import de.cau.cs.kieler.kiml.grana.GranaPlugin;
import de.cau.cs.kieler.kiml.grana.ui.visualization.VisualizationService;
import de.cau.cs.kieler.kiml.grana.util.DiagramAnalyzer;
import de.cau.cs.kieler.kiml.grana.util.GranaUtil;

/**
 * An action for configuration of analyses.
 * 
 * @author cds
 */
public class ConfigureAnalysesAction extends Action {
    /**
     * Unique ID of this action.
     */
    public static final String ACTION_ID = "de.cau.cs.kieler.kiml.grana.configureAnalyses";
    
    /**
     * The analysis results view this action was created for.
     */
    private AnalysisResultViewPart analysisView = null;
    
    
    /**
     * Creates a new action for the given view part.
     * 
     * @param analysisView the view part to create the action for.
     */
    public ConfigureAnalysesAction(final AnalysisResultViewPart analysisView) {
        this.analysisView = analysisView;
        
        // Configure how the action is displayed
        setId(ACTION_ID);
        setToolTipText("Configure Analyses...");
        setImageDescriptor(GranaPlugin.imageDescriptorFromPlugin(
                GranaPlugin.PLUGIN_ID,
                "icons/menu16/configure.gif"));
    }

    @Override
    public void run() {
        Shell shell = analysisView.getSite().getShell();
        AnalysisSelectionDialog selectionDialog = new AnalysisSelectionDialog(
                shell,
                AnalysisService.getInstance().getCategories(),
                GranaUtil.getLastAnalysesSelection());
        int code = selectionDialog.open();
        
        if (code == Dialog.OK) {
            // Apply the selection
            List<AnalysisData> analyses = selectionDialog.getAnalyses();
            GranaUtil.setLastAnalysesSelection(analyses);
            
            // Rerun analyses, if necessary
            if (VisualizationService.getInstance().findActiveMethod(true)) {
                // There is a visualization method; find the active editor, if any
                IEditorPart activeEditorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                                .getActivePage().getActiveEditor();
                
                if (activeEditorPart != null) {
                    // Analyze the diagram and visualize the results
                    Map<String, Object> results = DiagramAnalyzer.analyze(
                            activeEditorPart, null, analyses, false);
                    VisualizationService.getInstance().visualize(analyses, results, true);
                }
            }
        }
    }
}
