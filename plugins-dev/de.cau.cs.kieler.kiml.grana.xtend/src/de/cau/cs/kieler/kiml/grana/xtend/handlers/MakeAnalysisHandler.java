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
package de.cau.cs.kieler.kiml.grana.xtend.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kiml.grana.xtend.GranaXtendPlugin;
import de.cau.cs.kieler.kiml.grana.xtend.wizards.AddXtendAnalysisWizard;

/**
 * The handler for making an analysis from a xtend extension defined in a
 * selected file.
 * 
 * @author mri
 */
public class MakeAnalysisHandler extends AbstractHandler {

    /** the message for telling the user that the input couldn't be determined. */
    private static final String MESSAGE_NO_INPUT =
            "Failed to create analysis from xtend extension. Could not determine input file.";
    /** the message for telling the user that the no file was selected when invoking this handler. */
    private static final String MESSAGE_NO_FILE_SELECTED =
            "Failed to create analysis from xtend extension. "
                    + "Could not determine input file. No file selected.";

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
        Status myStatus = null;
        try {
            // get input model from currently selected file in package explorer
            ISelection selection =
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                            .getSelectionService().getSelection();
            IFile file = (IFile) ((TreeSelection) selection).getFirstElement();

            AddXtendAnalysisWizard wizard = new AddXtendAnalysisWizard(file);
            WizardDialog dialog = new WizardDialog(shell, wizard);
            dialog.create();
            dialog.open();
        } catch (NullPointerException exception) {
            myStatus =
                    new Status(IStatus.ERROR, GranaXtendPlugin.PLUGIN_ID,
                            MESSAGE_NO_INPUT, exception);
        } catch (ClassCastException exception) {
            myStatus =
                    new Status(IStatus.WARNING, GranaXtendPlugin.PLUGIN_ID,
                            MESSAGE_NO_FILE_SELECTED, exception);
        } finally {
            if (myStatus != null) {
                StatusManager.getManager().handle(myStatus, StatusManager.SHOW);
            }
        }
        return null;
    }
}
