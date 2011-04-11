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
package de.cau.cs.kieler.kiml.viewer;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.kiml.viewer.views.ExecutionView;
import de.cau.cs.kieler.kiml.viewer.views.LayoutGraphView;

/**
 * Effect for updating the KIML Viewer.
 * 
 * @author soh
 */
public class UpdateViewerEffect extends AbstractEffect {

    /** Constant indicating that a layout is about to be performed. */
    public static final String PRE_LAYOUT = "PRE_LAYOUT";
    /** Constant indicating that a layout is just finished. */
    public static final String POST_LAYOUT = "POST_LAYOUT";

    private KNode layoutGraph;

    private String state;

    /**
     * 
     * Creates a new UpdateViewerEffect.
     * 
     */
    public UpdateViewerEffect() {
        super();
    }

    /**
     * Creates a new UpdateViewerEffect.
     * 
     * @param layoutGraphParam
     *            the layout graph
     * @param stateParam
     *            the state
     */
    public UpdateViewerEffect(final KNode layoutGraphParam,
            final String stateParam) {
        super();
        this.layoutGraph = layoutGraphParam;
        this.state = stateParam;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void execute() {
        if (state.equals(POST_LAYOUT)) {
            layoutPerformed(null);
        } else if (state.equals(PRE_LAYOUT)) {
            layoutRequested();
        }

    }

    /** the currently open layout graph view. */
    private LayoutGraphView layoutGraphView;
    /** the currently open execution view. */
    private ExecutionView executionView;

    /**
     * A layout is requested an the layout graph built.
     */
    private void layoutRequested() {
        final KNode nodeCopy = EcoreUtil.copy(layoutGraph);
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                findViews();
                if (layoutGraphView != null) {
                    layoutGraphView.setLayoutGraph(nodeCopy,
                            LayoutGraphView.PRE);
                    // the last post-layout graph is deleted to avoid
                    // inconsistent
                    // graphs
                    layoutGraphView.setLayoutGraph(null, LayoutGraphView.POST);
                }
            }
        });
    }

    /**
     * The layout has finished.
     * 
     * @param monitor
     *            optional progress monitor
     */
    private void layoutPerformed(final IKielerProgressMonitor monitor) {
        final KNode nodeCopy = EcoreUtil.copy(layoutGraph);
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                findViews();
                if (layoutGraphView != null) {
                    layoutGraphView.setLayoutGraph(nodeCopy,
                            LayoutGraphView.POST);
                }
                if (executionView != null) {
                    executionView.addExecution(monitor);
                }
            }
        });
    }

    /**
     * Tries to find the relevant currently open views.
     */
    private void findViews() {
        layoutGraphView = null;
        executionView = null;

        IWorkbenchWindow activeWindow =
                PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWindow == null) {
            return;
        }

        IWorkbenchPage activePage = activeWindow.getActivePage();
        if (activePage == null) {
            return;
        }

        IViewPart viewPart = activePage.findView(LayoutGraphView.VIEW_ID);
        if (viewPart instanceof LayoutGraphView) {
            layoutGraphView = (LayoutGraphView) viewPart;
        }
        viewPart = activePage.findView(ExecutionView.VIEW_ID);
        if (viewPart instanceof ExecutionView) {
            executionView = (ExecutionView) viewPart;
        }
    }
    
}
