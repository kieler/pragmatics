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
package de.cau.cs.kieler.kiml.grana.ui.views;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.grana.GranaPlugin;
import de.cau.cs.kieler.kiml.grana.ui.HtmlResultGenerator;
import de.cau.cs.kieler.kiml.grana.ui.visualization.BoundVisualization;
import de.cau.cs.kieler.kiml.grana.ui.visualization.VisualizationService;

/**
 * A view that is an alternative way to display analysis results.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class AnalysisResultViewPart extends ViewPart {

    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.grana.views.analysisResults";

    /** the displayed html. */
    private String html;
    /** the html browser. */
    private Browser browser = null;

    /**
     * Finds the active analysis result view, if it exists.
     * 
     * @return the active analysis result view, or {@code null} if there is none
     */
    public static AnalysisResultViewPart findView() {
        IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWindow != null) {
            IWorkbenchPage activePage = activeWindow.getActivePage();
            if (activePage != null) {
                return (AnalysisResultViewPart) activePage.findView(VIEW_ID);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void createPartControl(final Composite parent) {
        // Create actions in the view toolbar
        IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
        toolBarManager.add(new AnalyzeAction());
        toolBarManager.add(new ConfigureAnalysesAction(this));
        
        try {
            browser = new Browser(parent, SWT.NONE);
            browser.setLayoutData(new GridData(GridData.FILL_BOTH));

            // activate the view visualization method
            VisualizationService.getInstance().setActive(ViewVisualizationMethod.class, true);
        } catch (SWTError e) {
            IStatus status = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID,
                    "Could not instantiate Browser.", e);
            StatusManager.getManager().handle(status, StatusManager.LOG);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        VisualizationService.getInstance().setActive(ViewVisualizationMethod.class, false);
        super.dispose();
    }

    /**
     * {@inheritDoc}
     */
    public void setFocus() {
    }

    /**
     * Sets the analysis results.
     * 
     * @param boundVisualizations
     *            the visualizations
     */
    public void setAnalysisResults(final List<BoundVisualization> boundVisualizations) {
        html = HtmlResultGenerator.generate(boundVisualizations);
        if (html != null && browser != null) {
            browser.setText(html);
        }
    }
}
