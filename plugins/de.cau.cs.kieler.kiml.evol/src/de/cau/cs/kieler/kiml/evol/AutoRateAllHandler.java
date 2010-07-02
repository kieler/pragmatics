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
package de.cau.cs.kieler.kiml.evol;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.ui.EvolView;
import de.cau.cs.kieler.kiml.evol.ui.EvolView.TargetIndividuals;

/**
 *
 * @author bdu
 *
 */
public class AutoRateAllHandler extends AbstractHandler {
    /**
     *
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {

        final IViewPart view =
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);
        if (view instanceof EvolView) {
            ((EvolView) view).autorateIndividuals(TargetIndividuals.ALL);
        }
        return null;
    }
}
