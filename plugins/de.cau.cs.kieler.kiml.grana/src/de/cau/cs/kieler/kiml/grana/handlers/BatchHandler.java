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
package de.cau.cs.kieler.kiml.grana.handlers;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.kiml.grana.GranaPlugin;
import de.cau.cs.kieler.kiml.grana.batch.Batch;
import de.cau.cs.kieler.kiml.grana.batch.BatchJob;
import de.cau.cs.kieler.kiml.grana.batch.BatchResult;
import de.cau.cs.kieler.kiml.grana.batch.CSVResultSerializer;
import de.cau.cs.kieler.kiml.grana.batch.DiagramKGraphProvider;
import de.cau.cs.kieler.kiml.grana.batch.IBatchResultSerializer;
import de.cau.cs.kieler.kiml.grana.ui.BatchWizard;

/**
 * The handler which is responsible for setting up an analysis batch run by launching the
 * appropriate wizard and executing it.
 * 
 * @author mri
 */
public class BatchHandler extends AbstractHandler {

    /** the error message for a failed batch analysis. */
    private static final String MESSAGE_BATCH_FAILED = "The batch analysis failed.";

    private static final int WORK_ALL = 6;
    private static final int WORK_BATCH = 1;
    private static final int WORK_EXECUTE = 4;
    private static final int WORK_SERIALIZE = 1;

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
        // get current selection
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getSelectionService().getSelection();
        // launch wizard
        final BatchWizard wizard;
        if (selection instanceof IStructuredSelection) {
            wizard = new BatchWizard((IStructuredSelection) selection);
        } else {
            wizard = new BatchWizard();
        }
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.create();
        // on successful wizard completion start the batch
        int code = dialog.open();
        if (code == Dialog.OK) {
            try {
                IRunnableWithProgress runnable = new IRunnableWithProgress() {
                    public void run(final IProgressMonitor uiMonitor) {
                        IKielerProgressMonitor monitor = new KielerProgressMonitor(uiMonitor);
                        monitor.begin("Starting analysis batch", WORK_ALL);
                        try {
                            // create the batch
                            Batch batch = new Batch(wizard.getAnalyses());
                            // create a batch job for every selected file
                            for (IPath file : wizard.getSelectedFiles()) {
                                DiagramKGraphProvider provider = new DiagramKGraphProvider();
                                provider.setLayoutBeforeAnalysis(wizard.getLayoutBeforeAnalysis());
                                BatchJob<IPath> batchJob = new BatchJob<IPath>(file, provider);
                                batch.appendJob(batchJob);
                            }
                            monitor.worked(WORK_BATCH);
                            // execute the batch
                            BatchResult result = batch.execute(monitor.subTask(WORK_EXECUTE));
                            if (monitor.isCanceled()) {
                                return;
                            }
                            // serialize the batch result
                            URI fileURI = URI.createPlatformResourceURI(wizard.getResultFile()
                                    .toOSString(), true);
                            URIConverter uriConverter = new ExtensibleURIConverterImpl();
                            OutputStream outputStream = uriConverter.createOutputStream(fileURI);
                            IBatchResultSerializer serializer = new CSVResultSerializer();
                            serializer.serialize(outputStream, result,
                                    monitor.subTask(WORK_SERIALIZE));
                            outputStream.close();
                        } catch (Exception e) {
                            IStatus status = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, 0,
                                    MESSAGE_BATCH_FAILED, e);
                            StatusManager.getManager().handle(status, StatusManager.SHOW);
                        } finally {
                            monitor.done();
                        }
                    }
                };
                PlatformUI.getWorkbench().getProgressService().run(true, true, runnable);
            } catch (InvocationTargetException e) {
                IStatus status = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, 0,
                        MESSAGE_BATCH_FAILED, e);
                StatusManager.getManager().handle(status, StatusManager.SHOW);
            } catch (InterruptedException e) {
                IStatus status = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, 0,
                        MESSAGE_BATCH_FAILED, e);
                StatusManager.getManager().handle(status, StatusManager.SHOW);
            }
        }

        return null;
    }
}
