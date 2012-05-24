/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutHandler;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * Handler for opening the evolution dialog.
 *
 * @author msp
 */
public class ShowEvolutionHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
        IDiagramLayoutManager<?> layoutManager = EclipseLayoutInfoService.getInstance().getManager(
                editorPart, null);
        if (layoutManager != null) {
            LayoutMapping<?> layoutMapping = layoutManager.buildLayoutGraph(editorPart, null);
            if (layoutMapping != null) {
                EvolutionDialog dialog = new EvolutionDialog(HandlerUtil.getActiveShell(event),
                        layoutMapping);
                if (dialog.open() == EvolutionDialog.OK) {
                    new LayoutHandler().execute(event);
                }
            }
        }
        return null;
    }

}
