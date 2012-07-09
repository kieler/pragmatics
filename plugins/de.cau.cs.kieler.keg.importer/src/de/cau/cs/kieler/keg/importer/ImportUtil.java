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
package de.cau.cs.kieler.keg.importer;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
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
import de.cau.cs.kieler.core.model.m2m.ITransformationContext;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.model.m2m.TransformationDescriptor;
import de.cau.cs.kieler.core.model.xtend.m2m.XtendTransformationContext;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.ui.diagram.IDiagramLayoutManager;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * A utility class for KEG import.
 * 
 * @author mri
 */
public final class ImportUtil {

    /** a dummy file extension. */
    private static final String FILE_EXT_DUMMY = "dummyext"; //$NON-NLS-1$

    /**
     * A private constructor to make the class not instantiable.
     */
    private ImportUtil() {
        // do nothing
    }

    /**
     * Transforms a model to a KEG graph using a given Xtend transformation file. The model instance
     * is read from an input stream.
     * 
     * @param xtendFile
     *            the xtend file containing the transformation
     * @param extension
     *            the name of the extension that starts the transformation inside the xtend file
     * @param parameters
     *            a list of additional parameters for the transformation or null if no additional
     *            parameters are required
     * @param inputStream
     *            the input stream the source model instance is read from
     * @param resourceFactory
     *            the resource factory used to read the model or null for the standard factory
     * @param monitor
     *            the progress monitor
     * @param involvedMetamodels
     *            the metamodels involved in the transformation
     * @return the parent node of the KEG graph
     * @throws IOException
     *             thrown when the the xtend file could not be found or opened
     * @throws TransformException
     *             thrown when the execution of the xtend transformation failed
     */
    public static Node transformModel2KEGGraph(final String xtendFile, final String extension,
            final List<Object> parameters, final InputStream inputStream,
            final Resource.Factory resourceFactory, final IKielerProgressMonitor monitor,
            final String... involvedMetamodels) throws IOException, TransformException {
        // read the model
        ResourceSet resourceSet = new ResourceSetImpl();
        if (resourceFactory != null) {
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                    .put(FILE_EXT_DUMMY, resourceFactory);
        }
        Resource resource =
                resourceSet.createResource(URI.createURI("http:///My." + FILE_EXT_DUMMY)); //$NON-NLS-1$
        // Map<String, Object> options = new HashMap<String, Object>();
        // options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        // read from the stream
        resource.load(inputStream, null);
        return transformModel2KEGGraph(xtendFile, extension, parameters, resource, monitor,
                involvedMetamodels);
    }

    /**
     * Transforms a model to a KEG graph using a given Xtend transformation file. The model instance
     * is read from a resource.
     * 
     * @param xtendFile
     *            the xtend file containing the transformation
     * @param extension
     *            the name of the extension that starts the transformation inside the xtend file
     * @param parameters
     *            a list of additional parameters for the transformation or null if no additional
     *            parameters are required
     * @param resource
     *            the resource from which to read the model
     * @param monitor
     *            the progress monitor
     * @param involvedMetamodels
     *            the metamodels involved in the transformation
     * @return the parent node of the KEG graph
     * @throws IOException
     *             thrown when the the xtend file could not be found or opened
     * @throws TransformException
     *             thrown when the execution of the xtend transformation failed
     */
    public static Node transformModel2KEGGraph(final String xtendFile, final String extension,
            final List<Object> parameters, final Resource resource,
            final IKielerProgressMonitor monitor, final String... involvedMetamodels)
            throws IOException, TransformException {
        monitor.begin(Messages.ImportUtil_m2m_task, 1);
        EObject model = resource.getContents().get(0);
        // initialize the xtend framework
        Object[] params = null;
        if (parameters != null && parameters.size() > 0) {
            // additional parameters
            params = new Object[parameters.size() + 1];
            params[0] = model;
            int i = 1;
            for (Object parameter : parameters) {
                params[i++] = parameter;
            }
        } else {
            params = new Object[1];
            params[0] = model;
        }
        // assemble the list of required metamodels
        String[] metamodels = new String[involvedMetamodels.length + 1];
        metamodels[0] = "de.cau.cs.kieler.keg.KEGPackage"; //$NON-NLS-1$
        int i = 1;
        for (String metamodel : involvedMetamodels) {
            metamodels[i++] = metamodel;
        }
        // initialize the transformation
        ITransformationContext transformationContext =
                new XtendTransformationContext(xtendFile, metamodels, null, null);
        TransformationDescriptor transformationDescriptor =
                new TransformationDescriptor(extension, params);
        // execute the transformation
        transformationContext.execute(transformationDescriptor);
        Object resultModel = transformationDescriptor.getResult();
        // serialize the model
        Node node = null;
        if (resultModel instanceof Node) {
            node = (Node) resultModel;
        } else {
            monitor.done();
            // FIXME throw a more specific exception
            throw new RuntimeException(Messages.ImportUtil_no_node_error);
        }
        monitor.done();
        return node;
    }

    /**
     * Given the path to a KEG diagram (kegdi) layout data contained in the KEG model is applied to
     * the KEG diagram.
     * 
     * @param diagramPath
     *            the diagram path
     * @param openDiagram
     *            whether to keep the diagram opened in an editor
     * @param <T> type of layout mapping
     */
    public static <T> void applyContainedLayout(final IPath diagramPath, final boolean openDiagram) {
        final IFile diagramFile = ResourcesPlugin.getWorkspace().getRoot().getFile(diagramPath);
        final Maybe<Exception> lastException = new Maybe<Exception>();
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page =
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                // open the diagram file in an editor
                IEditorDescriptor editorDescriptor = IDE.getDefaultEditor(diagramFile);
                if (editorDescriptor == null || editorDescriptor.isOpenExternal()) {
                    // FIXME throw a more specific exception
                    throw new RuntimeException(Messages.ImportUtil_open_diagram_failed_error);
                }
                IEditorPart editorPart;
                try {
                    editorPart = IDE.openEditor(page, diagramFile, editorDescriptor.getId(), true);
                } catch (PartInitException e) {
                    lastException.set(e);
                    return;
                }
                // get the layout manager for the editor
                @SuppressWarnings("unchecked")
                IDiagramLayoutManager<T> layoutManager = (IDiagramLayoutManager<T>)
                        EclipseLayoutInfoService.getInstance().getManager(editorPart, null);
                if (layoutManager == null) {
                    // FIXME throw a more specific exception
                    lastException.set(new RuntimeException(
                            Messages.ImportUtil_unsupported_editor_error));
                    return;
                }
                // build the graph
                LayoutMapping<T> mapping = layoutManager.buildLayoutGraph(editorPart, null);
                // transfer the layout
                transferLayoutData(mapping);
                // apply the layout
                layoutManager.applyLayout(mapping, false, 0);
                // close diagram or leave it open if requested
                if (openDiagram) {
                    page.saveEditor(editorPart, false);
                } else {
                    // FIXME causes exceptions but works anyway
                    page.saveEditor(editorPart, false);
                    page.closeEditor(editorPart, true);
                }
            }
        });
        if (lastException.get() != null) {
            throw new RuntimeException(lastException.get());
        }
    }

    private static void transferLayoutData(final LayoutMapping<?> mapping) {
        Queue<KNode> nodes = new LinkedList<KNode>();
        nodes.addAll(mapping.getLayoutGraph().getChildren());
        while (!nodes.isEmpty()) {
            KNode node = nodes.poll();
            Node modelNode = getModelNode(mapping, node);
            if (modelNode != null) {
                transferLayoutData(modelNode, node);
            }
            nodes.addAll(node.getChildren());
        }
    }

    private static void transferLayoutData(final Node source, final KNode target) {
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

    private static Node getModelNode(final LayoutMapping<?> mapping, final KNode node) {
        Object editPart = mapping.getGraphMap().get(node);
        if (editPart instanceof EditPart) {
            org.eclipse.gmf.runtime.notation.Node gmfNode =
                    (org.eclipse.gmf.runtime.notation.Node) ((EditPart) editPart).getModel();
            EObject modelObject = gmfNode.getElement();
            if (modelObject instanceof Node) {
                Node modelNode = (Node) modelObject;
                return modelNode;
            }
        }
        return null;
    }

    private static void transferNodeLayout(final KShapeLayout sourceLayout,
            final KShapeLayout targetLayout) {
        targetLayout.setPos(sourceLayout.getXpos(), sourceLayout.getYpos());
        targetLayout.setSize(sourceLayout.getWidth(), sourceLayout.getHeight());
    }

    private static void transferEdgeLayout(final KEdgeLayout sourceLayout,
            final KEdgeLayout targetLayout) {
        if (sourceLayout.getSourcePoint() == null || sourceLayout.getTargetPoint() == null) {
            return;
        }
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
