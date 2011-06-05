package de.cau.cs.kieler.klighd.combinations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.menu.ButtonTrigger.ButtonState;
import de.cau.cs.kieler.core.kivi.menu.KiviMenuContributionService;
import de.cau.cs.kieler.core.model.GraphicalFrameworkService;
import de.cau.cs.kieler.core.model.IGraphicalFrameworkBridge;


/**
 * The (experimental) combination initializing the KlighD view.
 * 
 * @author chsch
 * 
 */
public class KlighdInitializeCombination extends AbstractCombination {

    // we identify buttons only by an ID
    private final String buttonId = "de.cau.cs.kieler.klighd.kivi.button1";
    
    
    /**
     * Constructs and initializes the combination and its trigger button.
     */
    public KlighdInitializeCombination() {
        KiviMenuContributionService.INSTANCE.addToolbarButton(this, buttonId, "Init KlighDView");
    }
    
    
    /**
     * 
     * @param button
     */
    public void execute(final ButtonState button) {
        
        final GraphicalFrameworkService GAservice = GraphicalFrameworkService.getInstance();
        
        if (button.getButtonId().equals(buttonId)) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

                public void run() {
                    IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                            .getActivePage().getActivePart();
                    if (part instanceof IEditorPart) {
                        IGraphicalFrameworkBridge GAbridge = GAservice.getBridge(part);
                        EObject view = GAbridge.getNotationElement(part);
                        
                        // FIXME to be continued
                    }
                }
            });
        }
    }
}
