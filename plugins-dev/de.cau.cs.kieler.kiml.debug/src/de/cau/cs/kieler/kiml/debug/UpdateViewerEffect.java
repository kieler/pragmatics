/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.debug;

import org.eclipse.elk.core.util.IElkProgressMonitor;
import org.eclipse.elk.graph.KNode;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.debug.views.ExecutionView;
import de.cau.cs.kieler.kiml.debug.views.LayoutGraphView;

/**
 * Effect for updating the layout graph view.
 * 
 * @author soh
 * @author msp
 */
public class UpdateViewerEffect {

    /** the layout graph that should be displayed. */
    private KNode layoutGraph;
    /** the progress monitor that was used for layout. */
    private IElkProgressMonitor progressMonitor;

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
    public UpdateViewerEffect(final IElkProgressMonitor progressMonitor) {
        super();
        this.progressMonitor = progressMonitor;
    }
    
    /**
     * Creates a new update viewer effect, updating both the layout graph and the execution time.
     * 
     * @param layoutGraph the layout graph
     * @param progressMonitor the progress monitor
     */
    public UpdateViewerEffect(final KNode layoutGraph, final IElkProgressMonitor progressMonitor) {
        super();
        this.layoutGraph = layoutGraph;
        this.progressMonitor = progressMonitor;
    }

    /**
     * Trigger the update viewer effect asynchronously.
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
            }
        });
    }

    /** the currently open layout graph view. */
    private LayoutGraphView layoutGraphView;
    /** the currently open execution view. */
    private ExecutionView executionView;

    /**
     * Tries to find the relevant currently open views.
     */
    private void findViews() {
        layoutGraphView = null;
        executionView = null;

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
    }
    
}
