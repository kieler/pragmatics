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
package de.cau.cs.kieler.keg.importer.importer;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.properties.IPropertyHolder;
import de.cau.cs.kieler.core.ui.util.MonitoredOperation;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.importer.AbstractImporter;
import de.cau.cs.kieler.keg.importer.ImportManager;
import de.cau.cs.kieler.keg.importer.ImportUtil;
import de.cau.cs.kieler.keg.importer.ImporterOption;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;

/**
 * A KEG importer for the KGraph.
 * 
 * @author mri
 */
public class KGraphImporter extends AbstractImporter {

    /** the supported file extensions. */
    private static final String[] SUPPORTED_FILE_EXTENSIONS = { "kgraph" }; //$NON-NLS-1$
    /** the xtend transformation file. */
    private static final String XTEND_TRANSFORMATION_FILE = "kgraph2keg.ext"; //$NON-NLS-1$
    /** the xtend extension which is performing the transformation. */
    private static final String XTEND_TRANSFORMATION = "transform"; //$NON-NLS-1$

    /** the option for the transfer of layout information. */
    private static final ImporterOption<Boolean> OPTION_TRANSFER_LAYOUT =
            new ImporterOption<Boolean>("kgraph.transferLayout", //$NON-NLS-1$
                    Messages.KGraphImporter_transfer_layout_description, true);

    /**
     * The edge direction.
     */
    private enum EdgeDirection {
        DIRECTED, UNDIRECTED
    }

    /** the option for the edge direction. */
    private static final ImporterOption<EdgeDirection> OPTION_EDGE_DIRECTION =
            new ImporterOption<EdgeDirection>("kgraph.edgeDirection",
                    Messages.KGraphImporter_edge_direction_description, //$NON-NLS-1$
                    EdgeDirection.DIRECTED);

    /** the last exception. */
    private Exception lastException;

    /**
     * Constructs a KGraphImporter.
     */
    public KGraphImporter() {
        addOption(OPTION_TRANSFER_LAYOUT);
        addOption(OPTION_EDGE_DIRECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return Messages.KGraphImporter_kgraph_name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultExtension() {
        return SUPPORTED_FILE_EXTENSIONS[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getExtensions() {
        return SUPPORTED_FILE_EXTENSIONS;
    }

    /**
     * {@inheritDoc}
     */
    public Node doImport(final InputStream inputStream, final IPropertyHolder options,
            final IKielerProgressMonitor monitor) {
        Node node = null;
        try {
            List<Object> parameters = new LinkedList<Object>();
            parameters.add(options.getProperty(OPTION_EDGE_DIRECTION) == EdgeDirection.DIRECTED);
            node =
                    ImportUtil.transformModel2KEGGraph(XTEND_TRANSFORMATION_FILE,
                            XTEND_TRANSFORMATION, parameters, inputStream, null, monitor,
                            "de.cau.cs.kieler.core.kgraph.KGraphPackage"); //$NON-NLS-1$
        } catch (IOException e) {
            throw new RuntimeException(Messages.KGraphImporter_import_failed_error, e);
        } catch (TransformException e) {
            throw new RuntimeException(Messages.KGraphImporter_import_failed_error, e);
        }
        return node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDiagramPostProcess(final IPath diagramPath, final IPropertyHolder options) {
        if (options.getProperty(OPTION_TRANSFER_LAYOUT)) {
            final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
            lastException = null;
            MonitoredOperation.runInUI(new Runnable() {
                public void run() {
                    IWorkbenchPage page =
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    // open the diagram file in an editor
                    IEditorDescriptor editorDescriptor = IDE.getDefaultEditor(diagramFile);
                    if (editorDescriptor == null || editorDescriptor.isOpenExternal()) {
                        throw new RuntimeException(
                                Messages.KGraphImporter_open_diagram_failed_error);
                    }
                    IEditorPart editorPart;
                    try {
                        editorPart =
                                IDE.openEditor(page, diagramFile, editorDescriptor.getId(), true);
                    } catch (PartInitException e) {
                        lastException = e;
                        return;
                    }
                    // get the layout manager for the editor
                    DiagramLayoutManager layoutManager =
                            EclipseLayoutServices.getInstance().getManager(editorPart, null);
                    if (layoutManager == null) {
                        lastException =
                                new RuntimeException(
                                        Messages.KGraphImporter_unsupported_editor_error);
                        return;
                    }
                    // build the graph
                    KNode graph = layoutManager.buildLayoutGraph(editorPart, null, false);
                    // transfer the layout
                    int numberOfNodes = transferLayoutData(layoutManager, graph, editorPart);
                    // apply the layout
                    layoutManager.applyAnimatedLayout(false, false, numberOfNodes);
                    // close diagram or leave it open if requested
                    if (options.getProperty(ImportManager.OPTION_OPEN_DIAGRAM)) {
                        page.saveEditor(editorPart, false);
                    } else {
                        // FIXME causes exceptions but works anyway
                        page.saveEditor(editorPart, false);
                        page.closeEditor(editorPart, true);
                    }
                }
            }, true);
            if (lastException != null) {
                throw new RuntimeException(lastException);
            }
        }
    }

    private int transferLayoutData(final DiagramLayoutManager layoutManager, final KNode graph,
            final IEditorPart editorPart) {
        int numberOfNodes = 0;
        Queue<KNode> nodes = new LinkedList<KNode>();
        nodes.addAll(graph.getChildren());
        while (!nodes.isEmpty()) {
            numberOfNodes++;
            KNode node = nodes.poll();
            Node modelNode = getModelNode(layoutManager, node);
            if (modelNode != null) {
                transferLayoutData(layoutManager, modelNode, node);
            }
            nodes.addAll(node.getChildren());
        }
        return numberOfNodes;
    }

    private void transferLayoutData(final DiagramLayoutManager layoutManager, final Node source,
            final KNode target) {
        // transfer node layout
        KShapeLayout sourceLayout = source.getData(KShapeLayout.class);
        if (sourceLayout != null) {
            KShapeLayout targetLayout = target.getData(KShapeLayout.class);
            transferNodeLayout(sourceLayout, targetLayout);
        }
        // transfer edge layout
        Queue<KEdge> edges = new LinkedList<KEdge>();
        edges.addAll(source.getOutgoingEdges());
        for (KEdge targetEdge : target.getOutgoingEdges()) {
            KEdge sourceEdge = edges.poll();
            KEdgeLayout edgeSourceLayout = sourceEdge.getData(KEdgeLayout.class);
            KEdgeLayout edgeTargetLayout = targetEdge.getData(KEdgeLayout.class);
            transferEdgeLayout(edgeSourceLayout, edgeTargetLayout);
        }
        // transfer port layout
        Queue<KPort> ports = new LinkedList<KPort>();
        ports.addAll(source.getPorts());
        for (KPort targetPort : target.getPorts()) {
            KPort sourcePort = ports.poll();
            KShapeLayout portSourceLayout = sourcePort.getData(KShapeLayout.class);
            KShapeLayout portTargetLayout = targetPort.getData(KShapeLayout.class);
            transferNodeLayout(portSourceLayout, portTargetLayout);
        }
    }

    private Node getModelNode(final DiagramLayoutManager layoutManager, final KNode node) {
        EditPart editPart = layoutManager.getEditPart(node);
        org.eclipse.gmf.runtime.notation.Node gmfNode =
                (org.eclipse.gmf.runtime.notation.Node) editPart.getModel();
        EObject modelObject = gmfNode.getElement();
        if (modelObject instanceof Node) {
            Node modelNode = (Node) modelObject;
            return modelNode;
        }
        return null;
    }

    private void transferNodeLayout(final KShapeLayout sourceLayout, final KShapeLayout targetLayout) {
        targetLayout.setXpos(sourceLayout.getXpos());
        targetLayout.setYpos(sourceLayout.getYpos());
        targetLayout.setWidth(sourceLayout.getWidth());
        targetLayout.setHeight(sourceLayout.getHeight());
    }

    private void transferEdgeLayout(final KEdgeLayout sourceLayout, final KEdgeLayout targetLayout) {
        KPoint sourcePoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        KPoint targetPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
        sourcePoint.setX(sourceLayout.getSourcePoint().getX());
        sourcePoint.setY(sourceLayout.getSourcePoint().getY());
        targetPoint.setX(sourceLayout.getTargetPoint().getX());
        targetPoint.setY(sourceLayout.getTargetPoint().getY());
        targetLayout.setSourcePoint(sourcePoint);
        targetLayout.setTargetPoint(targetPoint);
        targetLayout.getBendPoints().clear();
        for (KPoint sourceBendPoint : sourceLayout.getBendPoints()) {
            KPoint targetBendPoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            targetBendPoint.setX(sourceBendPoint.getX());
            targetBendPoint.setY(sourceBendPoint.getY());
            targetLayout.getBendPoints().add(targetBendPoint);
        }
    }

}
