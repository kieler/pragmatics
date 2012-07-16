package de.cau.cs.kieler.kaom.diagram.part;

import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.EnhancedPrintActionHelper;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.RenderedPrintPreviewAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;

/**
 * @generated
 */
public class KaomDiagramActionBarContributor extends DiagramActionBarContributor {

    /**
     * @generated
     */
    @SuppressWarnings("rawtypes")
    protected Class getEditorClass() {
        return KaomDiagramEditor.class;
    }

    /**
     * @generated
     */
    protected String getEditorId() {
        return KaomDiagramEditor.ID;
    }

    /**
     * @generated
     */
    public void init(IActionBars bars, IWorkbenchPage page) {
        super.init(bars, page);
        // print preview
        IMenuManager fileMenu = bars.getMenuManager().findMenuUsingPath(
                IWorkbenchActionConstants.M_FILE);
        assert fileMenu != null;
        IAction printPreviewAction = new RenderedPrintPreviewAction(new EnhancedPrintActionHelper());
        fileMenu.insertBefore("print", printPreviewAction); //$NON-NLS-1$
        
        //ckru: bugfix for save button disabled while an object is selected 
        bars.setGlobalActionHandler(GlobalActionId.SAVE, null);
    }
}
