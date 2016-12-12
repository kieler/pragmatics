/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext.transformations;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.formats.GraphFormatsService;
import de.cau.cs.kieler.klighd.KlighdTreeSelection;
import de.cau.cs.kieler.klighd.ui.DiagramViewManager;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

/**
 * Handler for opening general graph diagrams.
 * 
 * @author uru
 */
public class OpenGraphDiagramHandler extends AbstractHandler {

    /**
     * This plugin's id.
     */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.klighd.xtext";

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final ISelection selection = HandlerUtil.getCurrentSelection(event);

        if (selection instanceof IStructuredSelection) {
            final IStructuredSelection sSelection = (IStructuredSelection) selection;
            if (selection instanceof KlighdTreeSelection) {
                // do not react on selections in KLighD diagrams
                return null;
            }

            for (Object o : sSelection.toArray()) {
                if (o instanceof IFile) {
                    IFile f = (IFile) o;

                    try {
                        
                        // if we know how to handle the graph's format, load it
                        ElkNode[] kgraph = GraphFormatsService.getInstance().loadElkGraph(f);

                        // and show a kgraph visualization of the graph
                        DiagramViewManager.createView(f.getName(),
                                f.getName(), kgraph[0], KlighdSynthesisProperties.create());

                        return null;

                    } catch (Exception e) {
                        handleUnknownSelection(selection, e);
                    }

                }
            }
        }

        handleUnknownSelection(selection, null);

        return null;
    }

    private void handleUnknownSelection(final ISelection selection, final Exception e) {
        StatusManager.getManager().handle(
                new Status(IStatus.ERROR, PLUGIN_ID,
                        "KLighD diagram synthesis is unsupported for the current selection "
                                + selection.toString() + ".", e), StatusManager.SHOW);
    }
}
