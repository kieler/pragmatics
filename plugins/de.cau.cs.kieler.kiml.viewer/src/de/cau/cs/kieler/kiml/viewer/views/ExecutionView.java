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
package de.cau.cs.kieler.kiml.viewer.views;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.kiml.viewer.actions.ClearExecutionsAction;

/**
 * View that can be used to display execution results of an algorithm.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ExecutionView extends ViewPart {

    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.viewer.execution";

    /** the tree viewer used to display content. */
    private TreeViewer viewer;
    /** the list of executions. */
    private List<IKielerProgressMonitor> executions = new LinkedList<IKielerProgressMonitor>();

    /**
     * Creates an execution view.
     */
    public ExecutionView() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        // create actions in the view toolbar
        getViewSite().getActionBars().getToolBarManager().add(new ClearExecutionsAction(this));

        // create tree viewer
        viewer = new TreeViewer(parent);
        viewer.setContentProvider(new ExecutionContentProvider());
        viewer.setLabelProvider(new ExecutionLabelProvider());
        viewer.setInput(executions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }

    /**
     * Adds an execution and updates the tree viewer.
     * 
     * @param progressMonitor progress monitor with execution information
     */
    public void addExecution(final IKielerProgressMonitor progressMonitor) {
        executions.add(progressMonitor);
        viewer.refresh();
    }

    /**
     * Clears all executions and updates the tree viewer.
     */
    public void clearExecutions() {
        executions.clear();
        viewer.refresh();
    }

}
