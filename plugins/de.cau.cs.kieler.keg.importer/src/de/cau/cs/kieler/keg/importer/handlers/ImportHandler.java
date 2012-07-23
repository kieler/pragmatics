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
package de.cau.cs.kieler.keg.importer.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.keg.importer.KEGImporterPlugin;
import de.cau.cs.kieler.keg.importer.Messages;
import de.cau.cs.kieler.keg.importer.wizards.ImportGraphWizard;

/**
 * The handler that is responsible for importing graphs using the context menu.
 * 
 * @author mri
 * @kieler.ignore (excluded from review process)
 */
public class ImportHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        // open the export dialog
        Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
        Status myStatus = null;
        try {
            // get input models from currently selected files in package explorer
            ISelection selection =
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
                            .getSelection();
            // open the wizard
            ImportGraphWizard wizard = new ImportGraphWizard();
            wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) selection);
            WizardDialog dialog = new WizardDialog(shell, wizard);
            dialog.create();
            dialog.open();
        } catch (NullPointerException exception) {
            exception.printStackTrace();
            myStatus =
                    new Status(IStatus.ERROR, KEGImporterPlugin.PLUGIN_ID,
                            Messages.ImportHandler_import_failed_error, exception);
        } catch (ClassCastException exception) {
            exception.printStackTrace();
            myStatus =
                    new Status(IStatus.WARNING, KEGImporterPlugin.PLUGIN_ID,
                            Messages.ImportHandler_no_input_error, exception);
        } finally {
            if (myStatus != null) {
                StatusManager.getManager().handle(myStatus,
                        StatusManager.BLOCK | StatusManager.SHOW);
            }
        }
        return null;
    }
}
