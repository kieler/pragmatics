package de.cau.cs.kieler.kiml.evol.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.evol.EvolModel;
import de.cau.cs.kieler.kiml.evol.ui.EvolView;

/**
 *
 * @author bdu
 *
 */
public class InfoHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent theEvent) throws ExecutionException {

        final EvolView view =
                (EvolView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                        .findView(EvolView.ID);

        if ((view == null)) {
            return null;
        }

        final EvolModel model = view.getEvolModel();
        if (((model == null) || !model.isValid())) {
            return null;
        }

        System.out.println();
        System.out.println(model.getPopulation());

        return null;
    }
}
