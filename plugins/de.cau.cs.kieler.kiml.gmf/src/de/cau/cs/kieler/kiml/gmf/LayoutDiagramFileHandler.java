/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.gmf;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.service.util.ProgressMonitorAdapter;

/**
 * Command handler for applying layout directly to GMF diagram files.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class LayoutDiagramFileHandler extends AbstractHandler {

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        if (selection instanceof IStructuredSelection) {
            final Object[] elements = ((IStructuredSelection) selection).toArray();
            Job job = new Job("Layout diagram file") {
                protected IStatus run(final IProgressMonitor monitor) {
                    ProgressMonitorAdapter kielerMonitor = new ProgressMonitorAdapter(monitor);
                    kielerMonitor.begin("Layout selected files", elements.length);
                    try {
                        for (Object object : elements) {
                            if (monitor.isCanceled()) {
                                break;
                            }
                            if (object instanceof IFile) {
                                layoutDiagram((IFile) object, kielerMonitor.subTask(1));
                            }
                        }
                    } catch (Exception exception) {
                        return new Status(IStatus.ERROR, "de.cau.cs.kieler.kiml.gmf",
                                "Failed to layout the selected diagram file." , exception);
                    }
                    kielerMonitor.done();
                    return Status.OK_STATUS;
                }
            };
            job.setUser(true);
            job.schedule();
        }
        return null;
    }

    /**
     * Perform layout on the given diagram file.
     * 
     * @param file a diagram file
     * @param monitor a progress monitor
     * @throws IOException if loading or saving the file fails
     */
    private static void layoutDiagram(final IFile file, final IKielerProgressMonitor monitor)
            throws IOException {
        monitor.begin("Layout diagram file " + file.toString(), 2);
        // load the notation diagram element
        URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(uri);   
        resource.load(Collections.emptyMap());
        if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Diagram)) {
            throw new IllegalArgumentException("The selected file does not contain a diagram.");
        }
        
        // create a diagram edit part
        TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
        final Maybe<DiagramEditPart> editPart = new Maybe<DiagramEditPart>();
        final Maybe<RuntimeException> wrappedException = new Maybe<RuntimeException>();
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
                    Diagram diagram = (Diagram) resource.getContents().get(0);
                    OffscreenEditPartFactory offscreenFactory = OffscreenEditPartFactory.getInstance();
                    editPart.set(offscreenFactory.createDiagramEditPart(diagram, new Shell()));
                } catch (RuntimeException re) {
                    wrappedException.set(re);
                }
            }
        });
        if (wrappedException.get() != null) {
            throw wrappedException.get();
        }
        monitor.worked(1);
        
        // perform layout on the diagram
        DiagramLayoutEngine.INSTANCE.layout(null, editPart.get(), monitor.subTask(1));
        
        // save the modified diagram
        resource.save(Collections.emptyMap());
        monitor.done();
    }

}
