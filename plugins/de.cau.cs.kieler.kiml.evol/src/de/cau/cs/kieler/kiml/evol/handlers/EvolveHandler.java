package de.cau.cs.kieler.kiml.evol.handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.ui.EvolView;

/**
 * Command for performing a step of the evolutionary algorithm. This creates a
 * new generation.
 * 
 * @author bdu
 * 
 */
public class EvolveHandler extends AbstractHandler {
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IViewPart view =
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);
        if (view instanceof EvolView) {
            ((EvolView) view).evolve();
        }
        return null;
    }
}
