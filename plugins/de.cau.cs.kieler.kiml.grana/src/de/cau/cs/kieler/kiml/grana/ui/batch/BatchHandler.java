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
package de.cau.cs.kieler.kiml.grana.ui.batch;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
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
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.grana.GranaPlugin;
import de.cau.cs.kieler.kiml.grana.ui.batch.wizard.BatchWizard;
import de.cau.cs.kieler.kiml.service.util.ProgressMonitorAdapter;

/**
 * The handler which is responsible for setting up an analysis batch run by launching the
 * appropriate wizard and executing it.
 * 
 * @author mri
 * @author uru
 * @kieler.ignore (excluded from review process)
 */
public class BatchHandler extends AbstractHandler {

    /** the error message for a failed batch analysis. */
    private static final String MESSAGE_BATCH_FAILED = "The batch analysis failed.";

    private static final int WORK_ALL = 6;
    private static final int WORK_BATCH = 1;
    private static final int WORK_EXECUTE = 4;
    private static final int WORK_SERIALIZE = 1;
    
    /**
     * Property holding the results of an execution time measurement for a layout run performed
     * prior to other graph analyses.
     */
    public static final IProperty<Map<String, Double>> EXECUTION_TIME_RESULTS =
            new Property<Map<String, Double>>("grana.execTimeResults");

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
                        IKielerProgressMonitor monitor = new ProgressMonitorAdapter(uiMonitor);
                        monitor.begin("Starting analysis batch", WORK_ALL);
                        try {
                            // create the batch
                            Batch batch = new Batch(wizard.getAnalyses());
                            
                            // create a batch job for every selected file
                            for (IPath file : wizard.getSelectedFiles()) {
                                FileKGraphProvider provider = new FileKGraphProvider();
                                provider.setLayoutBeforeAnalysis(wizard.getLayoutBeforeAnalysis());
                                provider.setExecutionTimeAnalysis(wizard.getExecutionTimeAnalysis());
                                provider.setLayoutConfigurator(wizard.getLayoutConfig());
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
                            
                            // display problems
                            if (!result.getFailedJobs().isEmpty()) {
                                IStatus[] stati = new IStatus[result.getFailedJobs().size()];
                                int i = 0;
                                for (Pair<IBatchJob<?>, Throwable> entry : result.getFailedJobs()) {
                                    stati[i++] = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID,
                                            "Failed analysis of " + entry.getFirst().getParameter(),
                                            entry.getSecond());
                                }
                                StatusManager.getManager().handle(new MultiStatus(GranaPlugin.PLUGIN_ID,
                                        0, stati, MESSAGE_BATCH_FAILED, null),
                                        StatusManager.SHOW | StatusManager.LOG);
                            }
                            
                        } catch (Exception e) {
                            IStatus status = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, 0,
                                    MESSAGE_BATCH_FAILED, e);
                            StatusManager.getManager().handle(status,
                                    StatusManager.SHOW | StatusManager.LOG);
                        } finally {
                            monitor.done();
                        }
                    }
                };
                PlatformUI.getWorkbench().getProgressService().run(true, true, runnable);
            } catch (InvocationTargetException e) {
                IStatus status = new Status(IStatus.ERROR, GranaPlugin.PLUGIN_ID, 0,
                        MESSAGE_BATCH_FAILED, e.getCause());
                StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
            } catch (InterruptedException e) {
                // ignore the exception
            }
        }

        return null;
    }
}
