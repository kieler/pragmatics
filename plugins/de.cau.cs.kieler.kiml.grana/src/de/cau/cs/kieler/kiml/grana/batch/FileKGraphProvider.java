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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * The KGraph provider that derives a graph from a file. It uses GMF services to open a
 * headless diagram, that is without an editor.
 *
 * @author msp
 * @kieler.ignore (excluded from review process)
 */
public class FileKGraphProvider implements IKGraphProvider<IPath> {

    /** the layout engine for graph layout. */
    private static IGraphLayoutEngine layoutEngine;
    
    /** the 'layout before analysis' option. */
    private boolean layoutBeforeAnalysis;
    
    /**
     * {@inheritDoc}
     */
    public KNode getKGraph(final IPath parameter, final IKielerProgressMonitor monitor) {
        monitor.begin("Retrieving KGraph from " + parameter.toString(), 2);
        
        // load the notation diagram element
        URI uri = URI.createPlatformResourceURI(parameter.toString(), true);
        ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.createResource(uri);
        try {
            resource.load(Collections.emptyMap());
        } catch (IOException e) {
            throw new WrappedException(e);
        }
        if (resource.getContents().isEmpty()) {
            throw new IllegalArgumentException("The selected file is empty.");
        }
        monitor.worked(1);

        EObject content = resource.getContents().get(0);
        if (content instanceof KNode) {
            KNode graph = (KNode) content;
            
            if (layoutBeforeAnalysis) {
                if (layoutEngine == null) {
                    layoutEngine = new RecursiveGraphLayoutEngine();
                }
                layoutEngine.layout(graph, monitor.subTask(1));
            }
            
            monitor.done();
            return graph;
        } else if (content instanceof Diagram) {
            KNode graph = retrieveGmfGraph((Diagram) content, resourceSet, monitor.subTask(1));
            monitor.done();
            return graph;
        } else {
            throw new IllegalArgumentException(
                    "The selected file does not contain a supported format.");
        }
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
    
    /**
     * Retrieve a graph from a GMF diagram.
     * 
     * @param diagram a diagram
     * @param resourceSet the resource set
     * @param monitor a progress monitor
     * @return the contained graph
     */
    private KNode retrieveGmfGraph(final Diagram diagram, final ResourceSet resourceSet,
            final IKielerProgressMonitor monitor) {
        monitor.begin("Retrieve KGraph from GMF diagram", 2);
        // create a diagram edit part
        TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
        final Maybe<DiagramEditPart> editPart = new Maybe<DiagramEditPart>();
        final Maybe<RuntimeException> wrappedException = new Maybe<RuntimeException>();
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                try {
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
                StatusManager.getManager().handle(status);
            }
        }
        
        monitor.done();
        return inputGraph;
    }

}
