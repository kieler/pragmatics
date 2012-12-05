/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.batch;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * The KGraph provider that uses GMF services to open a headless diagram, that is without
 * an editor.
 *
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class GmfKGraphProvider implements IKGraphProvider<IPath> {

    /** the 'layout before analysis' option. */
    private boolean layoutBeforeAnalysis;
    
    /**
     * {@inheritDoc}
     */
    public KNode getKGraph(final IPath parameter, final IKielerProgressMonitor monitor) {
        monitor.begin("Retrieving KGraph from " + parameter.toString(), 1);
        
        // load the notation diagram element
        URI uri = URI.createPlatformResourceURI(parameter.toString(), true);
        ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(uri);
        try {
            resource.load(Collections.emptyMap());
        } catch (IOException e) {
            throw new WrappedException(e);
        }
        if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Diagram)) {
            throw new IllegalArgumentException("The selected file does not contain a diagram.");
        }

        // create a diagram edit part
        TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
        final Maybe<DiagramEditPart> editPart = new Maybe<DiagramEditPart>();
        final Maybe<Throwable> wrappedException = new Maybe<Throwable>();
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
                    Diagram diagram = (Diagram) resource.getContents().get(0);
                    OffscreenEditPartFactory offscreenFactory = OffscreenEditPartFactory.getInstance();
                    editPart.set(offscreenFactory.createDiagramEditPart(diagram, new Shell()));
                } catch (Throwable re) {
                    wrappedException.set(re);
                }
            }
        });

        // retrieve a kgraph representation of the diagram
        IDiagramLayoutManager<?> layoutManager = EclipseLayoutInfoService.getInstance()
                .getManager(null, editPart.get());
        if (layoutManager == null) {
            throw new RuntimeException("No layout manager could be retrieved for the selected file.");
        }
        LayoutMapping<?> mapping = layoutManager.buildLayoutGraph(null, editPart.get());
        KNode inputGraph = mapping.getLayoutGraph();
        if (layoutBeforeAnalysis) {
            IStatus status = DiagramLayoutEngine.INSTANCE.layout(mapping, monitor.subTask(1));
            if (!status.isOK()) {
                wrappedException.set(status.getException());
            }
        }

        monitor.done();
        if (wrappedException.get() instanceof RuntimeException) {
            throw (RuntimeException) wrappedException.get();
        } else if (wrappedException.get() != null) {
            throw new RuntimeException(wrappedException.get());
        }
        return inputGraph;
    }

    /**
     * Sets the option which specifies whether layout should be performed before
     * the KGraph is returned.
     * 
     * @param layoutBeforeAnalysisOption
     *            true if layout should be performed
     */
    public void setLayoutBeforeAnalysis(final boolean layoutBeforeAnalysisOption) {
        layoutBeforeAnalysis = layoutBeforeAnalysisOption;
    }

}
