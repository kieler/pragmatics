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
package de.cau.cs.kieler.kivi.examples;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.ui.handler.ZoomToFitHandler;
import de.cau.cs.kieler.core.ui.util.EditorUtils;
import de.cau.cs.kieler.kivi.core.impl.AbstractEffect;

/**
 * Performs zoom-to-fit.
 * 
 * @author mmu
 *
 */
public class ZoomEffect extends AbstractEffect {

    @Override
    public void execute() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        workbench.getDisplay().asyncExec(new Runnable() {
            public void run() {
                final IEditorPart editor = EditorUtils.getLastActiveEditor();
                ZoomToFitHandler.zoomToFitAllNodes(editor);
                ZoomToFitHandler.resetViewLocation(editor);
            }
        });
    }

}
