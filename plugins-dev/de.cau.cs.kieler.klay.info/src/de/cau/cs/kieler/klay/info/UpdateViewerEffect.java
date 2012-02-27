/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.info;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.klay.info.views.ExecutionView;
import de.cau.cs.kieler.klay.info.views.LayoutGraphView;
import de.cau.cs.kieler.klay.info.views.SmartLayoutView;

/**
 * Effect for updating the layout graph view.
 * 
 * @author soh
 * @author msp
 */
public class UpdateViewerEffect extends AbstractEffect {

    /** the layout graph that should be displayed. */
    private KNode layoutGraph;
    /** the progress monitor that was used for layout. */
    private IKielerProgressMonitor progressMonitor;

    /**
     * Creates a new update viewer effect, updating only the layout graph.
     * 
     * @param layoutGraph the layout graph
     */
    public UpdateViewerEffect(final KNode layoutGraph) {
        super();
        this.layoutGraph = layoutGraph;
    }
    
    /**
     * Creates a new update viewer effect, updating only the execution time.
     * 
     * @param progressMonitor the progress monitor
     */
    public UpdateViewerEffect(final IKielerProgressMonitor progressMonitor) {
        super();
        this.progressMonitor = progressMonitor;
    }
    
    /**
     * Creates a new update viewer effect, updating both the layout graph and the execution time.
     * 
     * @param layoutGraph the layout graph
     * @param progressMonitor the progress monitor
     */
    public UpdateViewerEffect(final KNode layoutGraph, final IKielerProgressMonitor progressMonitor) {
        super();
        this.layoutGraph = layoutGraph;
        this.progressMonitor = progressMonitor;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void execute() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                findViews();
                if (layoutGraph != null && layoutGraphView != null) {
                    KNode nodeCopy = EcoreUtil.copy(layoutGraph);
                    layoutGraphView.getCanvas().setLayoutGraph(nodeCopy);
                }
                if (progressMonitor != null && executionView != null) {
                    executionView.addExecution(progressMonitor);
                }
                if (smartLayoutView != null) {
                    smartLayoutView.updateContent();
                }
            }
        });
    }

    /** the currently open layout graph view. */
    private LayoutGraphView layoutGraphView;
    /** the currently open execution view. */
    private ExecutionView executionView;
    /** the currently open smart layout view. */
    private SmartLayoutView smartLayoutView;

    /**
     * Tries to find the relevant currently open views.
     */
    private void findViews() {
        layoutGraphView = null;
        executionView = null;
        smartLayoutView = null;

        IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWindow == null) {
            return;
        }

        IWorkbenchPage activePage = activeWindow.getActivePage();
        if (activePage == null) {
            return;
        }

        // find layout graph view
        if (layoutGraph != null) {
            IViewPart viewPart = activePage.findView(LayoutGraphView.VIEW_ID);
            if (viewPart instanceof LayoutGraphView) {
                layoutGraphView = (LayoutGraphView) viewPart;
            }
        }
        // find execution time view
        if (progressMonitor != null) {
            IViewPart viewPart = activePage.findView(ExecutionView.VIEW_ID);
            if (viewPart instanceof ExecutionView) {
                executionView = (ExecutionView) viewPart;
            }
        }
        // find smart layout view
        IViewPart viewPart = activePage.findView(SmartLayoutView.VIEW_ID);
        if (viewPart instanceof SmartLayoutView) {
            smartLayoutView = (SmartLayoutView) viewPart;
        }
    }
    
}
