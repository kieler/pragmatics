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
package de.cau.cs.kieler.kiml.evol.handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.ui.EvolView;

/**
 * Command for changing the rating of an individual.
 *
 * @author bdu
 *
 */
public class ChangeRatingHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        String amount = event.getParameter("de.cau.cs.kieler.kiml.evol.amount");
        double delta = Double.parseDouble(amount);
        IViewPart view =
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);
        if (view instanceof EvolView) {
            EvolView evolView = (EvolView) view;
            // presuming evolView != null

            EvolModel model = evolView.getEvolModel();
            // presuming model != null

            model.changeCurrentRating(delta);
        }
        return null;
    }
}
