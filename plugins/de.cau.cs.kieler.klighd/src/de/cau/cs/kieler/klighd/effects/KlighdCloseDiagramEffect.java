/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.effects;

import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.klighd.views.DiagramViewManager;

/**
 * A view management effect for closing KLighD views.
 * 
 * @author mri
 */
public class KlighdCloseDiagramEffect extends AbstractEffect {

    /** the identifier of the view to be closed. */
    private String viewId;

    /**
     * Constructs an effect that closes the diagram view associated with the given id.
     * 
     * @param viewId
     *            identifier of the view to be closed.
     */
    public KlighdCloseDiagramEffect(final String viewId) {
        this.viewId = viewId;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                DiagramViewManager.getInstance().closeView(viewId);
            }
        });
    }

}
