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
package de.cau.cs.kieler.kiml.gmf;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeProvider;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutHandler;

/**
 * GMF layout provider that executes KIELER layout. This enables the execution of KIELER layout
 * using the default "Arrange All" button. However, layout of selection is not possible with
 * this provider.
 *
 * @author msp
 */
public class KielerLayoutProvider extends AbstractProvider implements ILayoutNodeProvider {

    /**
     * {@inheritDoc}
     */
    public boolean provides(final IOperation operation) {
        return operation instanceof ILayoutNodeOperation;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    public boolean canLayoutNodes(final List layoutNodes, final boolean shouldOffsetFromBoundingBox,
            final IAdaptable layoutHint) {
        return layoutHint.getAdapter(IGraphicalEditPart.class) instanceof IGraphicalEditPart;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    public Runnable layoutLayoutNodes(final List layoutNodes, final boolean offsetFromBoundingBox,
            final IAdaptable layoutHint) {
        // fetch general settings from preferences
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        boolean animation = preferenceStore.getBoolean(LayoutHandler.PREF_ANIMATION);
        boolean zoomToFit = preferenceStore.getBoolean(LayoutHandler.PREF_ZOOM);
        boolean progressDialog = preferenceStore.getBoolean(LayoutHandler.PREF_PROGRESS);
        
        Object editPart = layoutHint.getAdapter(IGraphicalEditPart.class);
        if (editPart instanceof IGraphicalEditPart) {
            DiagramLayoutEngine.INSTANCE.layout(null, editPart, animation, progressDialog,
                    false, zoomToFit);
        }
        
        // return a runnable that does nothing
        return new Runnable() { public void run() { } };
    }

}
