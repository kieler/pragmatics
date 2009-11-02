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
package de.cau.cs.kieler.kiml.viewer.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.layout.services.RecursiveLayouterEngine;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.viewer.KimlViewerPlugin;
import de.cau.cs.kieler.kiml.viewer.Messages;
import de.cau.cs.kieler.kiml.viewer.views.LayoutGraphView;

/**
 * Action that performs layout on the currently loaded XMI resource.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class PerformLayoutAction extends Action {

    /** identifier string for this action. */
    private static final String ACTION_ID = "de.cau.cs.kieler.kiml.viewer.performLayout";
    /** relative path to the icon to use for this action. */
    private static final String ICON_PATH = "icons/kieler-arrange.png";

    /** the layout graph view associated with this action. */
    private LayoutGraphView view;

    /**
     * Creates a perform layout action for a given layout graph view.
     * 
     * @param theview layout graph view that created this action
     */
    public PerformLayoutAction(final LayoutGraphView theview) {
        this.view = theview;
        setId(ACTION_ID);
        setText(Messages.getString("kiml.viewer.11"));
        setToolTipText(Messages.getString("kiml.viewer.12"));
        setImageDescriptor(KimlViewerPlugin.imageDescriptorFromPlugin(KimlViewerPlugin.PLUGIN_ID,
                ICON_PATH));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        KNode layoutGraph = view.getXmiGraph();
        if (layoutGraph != null) {
            // display the layout graph in the pre canvas
            view.setLayoutGraph(layoutGraph, LayoutGraphView.PRE);

            // get a layouter engine for layout
            if (LayoutServices.getInstance() == null) {
                EclipseLayoutServices.createLayoutServices();
            }
            final RecursiveLayouterEngine layouterEngine = new RecursiveLayouterEngine();

            // copy the layout graph
            final KNode layoutGraphCopy = (KNode) EcoreUtil.copy(layoutGraph);

            // perform layout on the new copy
            try {
                PlatformUI.getWorkbench().getProgressService().run(false, false,
                        new IRunnableWithProgress() {
                            public void run(final IProgressMonitor monitor) {
                                try {
                                    layouterEngine.layout(layoutGraphCopy, new KielerProgressMonitor(
                                            monitor));
                                } catch (Throwable throwable) {
                                    String message = "Failed to perform layout.";
                                    if (layouterEngine.getLastLayoutProvider() != null) {
                                        message += " ("
                                                + layouterEngine.getLastLayoutProvider().toString()
                                                + ")";
                                    }
                                    handleError(throwable, message);
                                }
                            }
                        });
            } catch (Throwable throwable) {
                handleError(throwable, "Failed to perform layout: Could not run progress service.");
            }

            // display the layouted layout graph in the post canvas
            view.setLayoutGraph(layoutGraphCopy, LayoutGraphView.POST);
        }
    }

    /**
     * Handles an error by delegating it to the status manager.
     * 
     * @param throwable error that has been thrown
     * @param message additional message to show
     */
    private static void handleError(final Throwable throwable, final String message) {
        Status status = new Status(IStatus.ERROR, KimlViewerPlugin.PLUGIN_ID, message, throwable);
        StatusManager.getManager().handle(status, StatusManager.SHOW);
        StatusManager.getManager().handle(status, StatusManager.LOG);
    }

}
