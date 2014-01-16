/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.evol.ui;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.evol.EvolPlugin;
import de.cau.cs.kieler.kiml.formats.GraphFormatsService;

/**
 * A command handler that measures the execution time of evolutionary layout.
 * 
 * @author msp
 */
public class ExecutionTimeHandler extends AbstractHandler {
    
    /**
     * {@inheritDoc}
     */
    public final Object execute(final ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            
            final Object[] elements = ((IStructuredSelection) selection).toArray();
            Job job = new Job("Test Layout Metrics") {
                protected IStatus run(final IProgressMonitor monitor) {
                    monitor.beginTask("Test Layout Metrics", elements.length);
                    for (Object object : elements) {
                        if (monitor.isCanceled()) {
                            break;
                        }
                        if (object instanceof IFile) {
                            IFile inputFile = (IFile) object;
                            // measure execution time for the current input file
                            measureFile(inputFile);
                        }
                        monitor.worked(1);
                    }
                    monitor.done();
                    return Status.OK_STATUS;
                }
                
            };
            job.setUser(true);
            job.schedule();
        }
        
        return null;
    }
    
    private void measureFile(final IFile file) {
        try {
            System.out.print(file.getName() + ", ");
            KNode[] graphs = GraphFormatsService.getInstance().loadKGraph(file);
            
            System.out.print(graphs[0].getChildren().size() + ", ");
            measureGraph(graphs[0]);
            
        } catch (CoreException exception) {
            StatusManager.getManager().handle(exception, EvolPlugin.PLUGIN_ID);
        } catch (IOException exception) {
            IStatus status = new Status(IStatus.ERROR, EvolPlugin.PLUGIN_ID,
                    "Error while loading the graph file " + file.getName(), exception);
            StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
        }
    }
    
    private void measureGraph(final KNode graph) {
        
    }
   
}
