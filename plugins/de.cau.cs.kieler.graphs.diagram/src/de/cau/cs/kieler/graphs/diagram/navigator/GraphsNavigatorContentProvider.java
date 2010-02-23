package de.cau.cs.kieler.graphs.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNode2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeNodeCompartment2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeNodeCompartmentEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.GraphEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;
import de.cau.cs.kieler.graphs.diagram.part.Messages;

/**
 * @generated
 */
public class GraphsNavigatorContentProvider implements ICommonContentProvider {

    /**
     * @generated
     */
    private static final Object[] EMPTY_ARRAY = new Object[0];

    /**
     * @generated
     */
    private Viewer myViewer;

    /**
     * @generated
     */
    private AdapterFactoryEditingDomain myEditingDomain;

    /**
     * @generated
     */
    private WorkspaceSynchronizer myWorkspaceSynchronizer;

    /**
     * @generated
     */
    private Runnable myViewerRefreshRunnable;

    /**
     * @generated
     */
    public GraphsNavigatorContentProvider() {
        TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
                .createEditingDomain();
        myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
        myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
            public Object get(Object key) {
                if (!containsKey(key)) {
                    put(key, Boolean.TRUE);
                }
                return super.get(key);
            }
        });
        myViewerRefreshRunnable = new Runnable() {
            public void run() {
                if (myViewer != null) {
                    myViewer.refresh();
                }
            }
        };
        myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain,
                new WorkspaceSynchronizer.Delegate() {
                    public void dispose() {
                    }

                    public boolean handleResourceChanged(final Resource resource) {
                        for (Iterator it = myEditingDomain.getResourceSet().getResources().iterator(); it
                                .hasNext();) {
                            Resource nextResource = (Resource) it.next();
                            nextResource.unload();
                        }
                        if (myViewer != null) {
                            myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
                        }
                        return true;
                    }

                    public boolean handleResourceDeleted(Resource resource) {
                        for (Iterator it = myEditingDomain.getResourceSet().getResources().iterator(); it
                                .hasNext();) {
                            Resource nextResource = (Resource) it.next();
                            nextResource.unload();
                        }
                        if (myViewer != null) {
                            myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
                        }
                        return true;
                    }

                    public boolean handleResourceMoved(Resource resource, final URI newURI) {
                        for (Iterator it = myEditingDomain.getResourceSet().getResources().iterator(); it
                                .hasNext();) {
                            Resource nextResource = (Resource) it.next();
                            nextResource.unload();
                        }
                        if (myViewer != null) {
                            myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
                        }
                        return true;
                    }
                });
    }

    /**
     * @generated
     */
    public void dispose() {
        myWorkspaceSynchronizer.dispose();
        myWorkspaceSynchronizer = null;
        myViewerRefreshRunnable = null;
        for (Iterator it = myEditingDomain.getResourceSet().getResources().iterator(); it.hasNext();) {
            Resource resource = (Resource) it.next();
            resource.unload();
        }
        ((TransactionalEditingDomain) myEditingDomain).dispose();
        myEditingDomain = null;
    }

    /**
     * @generated
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        myViewer = viewer;
    }

    /**
     * @generated
     */
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    /**
     * @generated
     */
    public void restoreState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public void saveState(IMemento aMemento) {
    }

    /**
     * @generated
     */
    public void init(ICommonContentExtensionSite aConfig) {
    }

    /**
     * @generated
     */
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof IFile) {
            IFile file = (IFile) parentElement;
            URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
            Resource resource = myEditingDomain.getResourceSet().getResource(fileURI, true);
            Collection result = new ArrayList();
            result.addAll(createNavigatorItems(selectViewsByType(resource.getContents(),
                    GraphEditPart.MODEL_ID), file, false));
            return result.toArray();
        }

        if (parentElement instanceof GraphsNavigatorGroup) {
            GraphsNavigatorGroup group = (GraphsNavigatorGroup) parentElement;
            return group.getChildren();
        }

        if (parentElement instanceof GraphsNavigatorItem) {
            GraphsNavigatorItem navigatorItem = (GraphsNavigatorItem) parentElement;
            if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
                return EMPTY_ARRAY;
            }
            return getViewChildren(navigatorItem.getView(), parentElement);
        }

        return EMPTY_ARRAY;
    }

    /**
     * @generated
     */
    private Object[] getViewChildren(View view, Object parentElement) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {

        case GraphEditPart.VISUAL_ID: {
            Collection result = new ArrayList();
            GraphsNavigatorGroup links = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Graph_1000_links,
                    "icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection connectedViews = getChildrenByType(Collections.singleton(view),
                    GraphsVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(CompoundNodeEditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(EdgeEditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            if (!links.isEmpty()) {
                result.add(links);
            }
            return result.toArray();
        }

        case NodeEditPart.VISUAL_ID: {
            Collection result = new ArrayList();
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_2001_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_2001_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection connectedViews = getIncomingLinksByType(Collections.singleton(view),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(EdgeEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case CompoundNodeEditPart.VISUAL_ID: {
            Collection result = new ArrayList();
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_CompoundNode_2002_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_CompoundNode_2002_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection connectedViews = getChildrenByType(Collections.singleton(view),
                    GraphsVisualIDRegistry.getType(CompoundNodeNodeCompartmentEditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews, GraphsVisualIDRegistry
                    .getType(Node2EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(CompoundNodeNodeCompartmentEditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews, GraphsVisualIDRegistry
                    .getType(CompoundNode2EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getIncomingLinksByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(EdgeEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(EdgeEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case Node2EditPart.VISUAL_ID: {
            Collection result = new ArrayList();
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_3001_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_3001_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection connectedViews = getIncomingLinksByType(Collections.singleton(view),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(EdgeEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case CompoundNode2EditPart.VISUAL_ID: {
            Collection result = new ArrayList();
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_CompoundNode_3002_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_CompoundNode_3002_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection connectedViews = getChildrenByType(Collections.singleton(view),
                    GraphsVisualIDRegistry.getType(CompoundNodeNodeCompartment2EditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews, GraphsVisualIDRegistry
                    .getType(Node2EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(CompoundNodeNodeCompartment2EditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews, GraphsVisualIDRegistry
                    .getType(CompoundNode2EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getIncomingLinksByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(EdgeEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(EdgeEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case EdgeEditPart.VISUAL_ID: {
            Collection result = new ArrayList();
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4001_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4001_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection connectedViews = getLinksTargetByType(Collections.singleton(view),
                    GraphsVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(CompoundNodeEditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(Node2EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(CompoundNode2EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(NodeEditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(CompoundNodeEditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(Node2EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(view), GraphsVisualIDRegistry
                    .getType(CompoundNode2EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            if (!target.isEmpty()) {
                result.add(target);
            }
            if (!source.isEmpty()) {
                result.add(source);
            }
            return result.toArray();
        }
        }
        return EMPTY_ARRAY;
    }

    /**
     * @generated
     */
    private Collection getLinksSourceByType(Collection edges, String type) {
        Collection result = new ArrayList();
        for (Iterator it = edges.iterator(); it.hasNext();) {
            Edge nextEdge = (Edge) it.next();
            View nextEdgeSource = nextEdge.getSource();
            if (type.equals(nextEdgeSource.getType()) && isOwnView(nextEdgeSource)) {
                result.add(nextEdgeSource);
            }
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getLinksTargetByType(Collection edges, String type) {
        Collection result = new ArrayList();
        for (Iterator it = edges.iterator(); it.hasNext();) {
            Edge nextEdge = (Edge) it.next();
            View nextEdgeTarget = nextEdge.getTarget();
            if (type.equals(nextEdgeTarget.getType()) && isOwnView(nextEdgeTarget)) {
                result.add(nextEdgeTarget);
            }
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getOutgoingLinksByType(Collection nodes, String type) {
        Collection result = new ArrayList();
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            View nextNode = (View) it.next();
            result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getIncomingLinksByType(Collection nodes, String type) {
        Collection result = new ArrayList();
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            View nextNode = (View) it.next();
            result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getChildrenByType(Collection nodes, String type) {
        Collection result = new ArrayList();
        for (Iterator it = nodes.iterator(); it.hasNext();) {
            View nextNode = (View) it.next();
            result.addAll(selectViewsByType(nextNode.getChildren(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection getDiagramLinksByType(Collection diagrams, String type) {
        Collection result = new ArrayList();
        for (Iterator it = diagrams.iterator(); it.hasNext();) {
            Diagram nextDiagram = (Diagram) it.next();
            result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection selectViewsByType(Collection views, String type) {
        Collection result = new ArrayList();
        for (Iterator it = views.iterator(); it.hasNext();) {
            View nextView = (View) it.next();
            if (type.equals(nextView.getType()) && isOwnView(nextView)) {
                result.add(nextView);
            }
        }
        return result;
    }

    /**
     * @generated
     */
    private boolean isOwnView(View view) {
        return GraphEditPart.MODEL_ID.equals(GraphsVisualIDRegistry.getModelID(view));
    }

    /**
     * @generated
     */
    private Collection createNavigatorItems(Collection views, Object parent, boolean isLeafs) {
        Collection result = new ArrayList();
        for (Iterator it = views.iterator(); it.hasNext();) {
            result.add(new GraphsNavigatorItem((View) it.next(), parent, isLeafs));
        }
        return result;
    }

    /**
     * @generated
     */
    public Object getParent(Object element) {
        if (element instanceof GraphsAbstractNavigatorItem) {
            GraphsAbstractNavigatorItem abstractNavigatorItem = (GraphsAbstractNavigatorItem) element;
            return abstractNavigatorItem.getParent();
        }
        return null;
    }

    /**
     * @generated
     */
    public boolean hasChildren(Object element) {
        return element instanceof IFile || getChildren(element).length > 0;
    }

}
