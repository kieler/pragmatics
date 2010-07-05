package de.cau.cs.kieler.kiml.evol.handlers;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.ui.EvolView;


public class ResetHandler extends AbstractHandler {

    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final IViewPart view =
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);
        if (view instanceof EvolView) {
            ((EvolView) view).reset();
        }
        return null;
    }
}
