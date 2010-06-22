package de.cau.cs.kieler.kex.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.LegacyResourceSupport;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.util.Util;

import de.cau.cs.kieler.kex.ui.wizards.KEXWizard;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class KEXStartAction implements IWorkbenchWindowActionDelegate {
	
	private IWorkbenchWindow workbenchWindow;
	
    /**
     * The wizard dialog width
     */
    private static final int SIZING_WIZARD_WIDTH = 500;

    /**
     * The wizard dialog height
     */
    private static final int SIZING_WIZARD_HEIGHT = 500;
	
	
	/**
	 * The constructor.
	 */
	public KEXStartAction() {
		
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
//		KEXWizard wizard = new KEXWizard();
//		ExampleDialog dialog = new ExampleDialog(window.getShell(), wizard);
//		dialog.open();
		
		if (workbenchWindow == null) {
            // action has been disposed
            return;
        }
        KEXWizard wizard = new KEXWizard();
        // TODO Kategorie ueberdenken.
//        wizard.setCategoryId("KEX Wizards");

        ISelection selection = workbenchWindow.getSelectionService()
                .getSelection();
        IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
        if (selection instanceof IStructuredSelection) {
            selectionToPass = (IStructuredSelection) selection;
        } else {
            // @issue the following is resource-specific legacy code
            // Build the selection from the IFile of the editor
            Class<?> resourceClass = LegacyResourceSupport.getResourceClass();
            if (resourceClass != null) {
                IWorkbenchPart part = workbenchWindow.getPartService()
                        .getActivePart();
                if (part instanceof IEditorPart) {
                    IEditorInput input = ((IEditorPart) part).getEditorInput();
                    Object resource = Util.getAdapter(input, resourceClass);
                    if (resource != null) {
                        selectionToPass = new StructuredSelection(resource);
                    }
                }
            }
        }

        wizard.init(workbenchWindow.getWorkbench(), selectionToPass);
        IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault()
                .getDialogSettings();
        IDialogSettings wizardSettings = workbenchSettings
                .getSection("NewWizardAction"); //$NON-NLS-1$
        if (wizardSettings == null) {
			wizardSettings = workbenchSettings.addNewSection("NewWizardAction"); //$NON-NLS-1$
		}
        wizard.setDialogSettings(wizardSettings);
        wizard.setForcePreviousAndNextButtons(true);

        Shell parent = workbenchWindow.getShell();
        WizardDialog dialog = new WizardDialog(parent, wizard);
        dialog.create();
        dialog.getShell().setSize(
                Math.max(SIZING_WIZARD_WIDTH, dialog.getShell().getSize().x),
                SIZING_WIZARD_HEIGHT);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(dialog.getShell(),
				IWorkbenchHelpContextIds.NEW_WIZARD);
        dialog.open();
		
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
    public void dispose() {
        if (workbenchWindow == null) {
            // action has already been disposed
            return;
        }
        workbenchWindow = null;
    }

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.workbenchWindow = window;
	}
}