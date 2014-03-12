/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.actions;

import de.cau.cs.kieler.klighd.IAction;

/**
 * Adds the given {@link de.cau.cs.kieler.core.kgraph.KGraphElement KGraphElement} to the viewer's
 * draw focus.
 * 
 * @author chsch
 */
public class DrawFocussedAction implements IAction {

    /**
     * {@inheritDoc}
     */
    public ActionResult execute(final ActionContext context) {        
        context.getActiveViewer().drawFocused(context.getKGraphElement());

        return ActionResult.createResult(false);
    }
}
