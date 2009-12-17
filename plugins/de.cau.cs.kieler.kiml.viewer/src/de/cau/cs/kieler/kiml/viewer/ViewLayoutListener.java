/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.viewer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.ILayoutListener;
import de.cau.cs.kieler.kiml.viewer.views.ExecutionView;
import de.cau.cs.kieler.kiml.viewer.views.LayoutGraphView;

/**
 * Layout listener implementation that displays the layout graphs in a view.
 * 
 * @author msp
 */
public class ViewLayoutListener implements ILayoutListener {

    /** the currently open layout graph view. */
    private LayoutGraphView layoutGraphView;
    /** the currently open execution view. */
    private ExecutionView executionView;

    /**
     * {@inheritDoc}
     */
    public void layoutRequested(final KNode layoutGraph) {
        findViews();
        if (layoutGraphView != null) {
            layoutGraphView.setLayoutGraph(cloneLayoutGraph(layoutGraph), LayoutGraphView.PRE);
            // the last post-layout graph is deleted to avoid inconsistent
            // graphs
            layoutGraphView.setLayoutGraph(null, LayoutGraphView.POST);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void layoutPerformed(final KNode layoutGraph, final IKielerProgressMonitor monitor) {
        findViews();
        if (layoutGraphView != null) {
            layoutGraphView.setLayoutGraph(this.cloneLayoutGraph(layoutGraph), LayoutGraphView.POST);
        }
        if (executionView != null) {
            executionView.addExecution(monitor);
        }
    }

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

        IViewPart viewPart = activePage.findView(LayoutGraphView.VIEW_ID);
        if (viewPart instanceof LayoutGraphView) {
            layoutGraphView = (LayoutGraphView) viewPart;
        }
        viewPart = activePage.findView(ExecutionView.VIEW_ID);
        if (viewPart instanceof ExecutionView) {
            executionView = (ExecutionView) viewPart;
        }
    }

    /**
     * Clones an instance of a layout graph.
     * 
     * @param input parent node to clone
     * @return a cloned instance
     */
    private KNode cloneLayoutGraph(final KNode input) {
        EObject result = EcoreUtil.copy(input);
        return (KNode) result;
    }

}
