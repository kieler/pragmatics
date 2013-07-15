package de.cau.cs.kieler.keg.diagram.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

import de.cau.cs.kieler.keg.diagram.edit.parts.Edge2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge6EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge7EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge8EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeCompartment2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeCompartmentEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry;
import de.cau.cs.kieler.keg.diagram.part.Messages;

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
    @SuppressWarnings({ "unchecked", "serial", "rawtypes" })
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
                        unloadAllResources();
                        asyncRefresh();
                        return true;
                    }

                    public boolean handleResourceDeleted(Resource resource) {
                        unloadAllResources();
                        asyncRefresh();
                        return true;
                    }

                    public boolean handleResourceMoved(Resource resource, final URI newURI) {
                        unloadAllResources();
                        asyncRefresh();
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
        myViewer = null;
        unloadAllResources();
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
    void unloadAllResources() {
        for (Resource nextResource : myEditingDomain.getResourceSet().getResources()) {
            nextResource.unload();
        }
    }

    /**
     * @generated
     */
    void asyncRefresh() {
        if (myViewer != null && !myViewer.getControl().isDisposed()) {
            myViewer.getControl().getDisplay().asyncExec(myViewerRefreshRunnable);
        }
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
            ArrayList<GraphsNavigatorItem> result = new ArrayList<GraphsNavigatorItem>();
            ArrayList<View> topViews = new ArrayList<View>(resource.getContents().size());
            for (EObject o : resource.getContents()) {
                if (o instanceof View) {
                    topViews.add((View) o);
                }
            }
            result.addAll(createNavigatorItems(selectViewsByType(topViews, NodeEditPart.MODEL_ID),
                    file, false));
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

        case Edge4EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4004_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4004_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            if (!target.isEmpty()) {
                result.add(target);
            }
            if (!source.isEmpty()) {
                result.add(source);
            }
            return result.toArray();
        }

        case Node2EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Node sv = (Node) view;
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_2001_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_2001_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(NodeNodeCompartmentEditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews,
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(NodeNodeCompartmentEditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews,
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge5EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge6EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge7EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge8EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case Edge6EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4006_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4006_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            if (!target.isEmpty()) {
                result.add(target);
            }
            if (!source.isEmpty()) {
                result.add(source);
            }
            return result.toArray();
        }

        case Node5EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Node sv = (Node) view;
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_3003_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_3003_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge5EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge6EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge7EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge8EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case Node4EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Node sv = (Node) view;
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_3001_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_3001_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(NodeNodeCompartment2EditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews,
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(NodeNodeCompartment2EditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews,
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge5EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge6EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge7EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge8EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case Edge7EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4007_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4007_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            if (!target.isEmpty()) {
                result.add(target);
            }
            if (!source.isEmpty()) {
                result.add(source);
            }
            return result.toArray();
        }

        case PortEditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Node sv = (Node) view;
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Port_3002_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Port_3002_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge3EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge3EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge4EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge4EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge5EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge6EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge7EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge8EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case Edge3EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4003_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4003_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            if (!target.isEmpty()) {
                result.add(target);
            }
            if (!source.isEmpty()) {
                result.add(source);
            }
            return result.toArray();
        }

        case Edge8EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4008_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4008_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            if (!target.isEmpty()) {
                result.add(target);
            }
            if (!source.isEmpty()) {
                result.add(source);
            }
            return result.toArray();
        }

        case NodeEditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Diagram sv = (Diagram) view;
            GraphsNavigatorGroup links = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_1000_links,
                    "icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge3EditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge4EditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge5EditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge6EditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge7EditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge8EditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            if (!links.isEmpty()) {
                result.add(links);
            }
            return result.toArray();
        }

        case Edge2EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4002_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4002_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            if (!target.isEmpty()) {
                result.add(target);
            }
            if (!source.isEmpty()) {
                result.add(source);
            }
            return result.toArray();
        }

        case Edge5EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4005_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4005_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            if (!target.isEmpty()) {
                result.add(target);
            }
            if (!source.isEmpty()) {
                result.add(source);
            }
            return result.toArray();
        }

        case Node3EditPart.VISUAL_ID: {
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Node sv = (Node) view;
            GraphsNavigatorGroup incominglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_2002_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup outgoinglinks = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Node_2002_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge5EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge6EditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge7EditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Edge8EditPart.VISUAL_ID));
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
            LinkedList<GraphsAbstractNavigatorItem> result = new LinkedList<GraphsAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            GraphsNavigatorGroup target = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4001_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            GraphsNavigatorGroup source = new GraphsNavigatorGroup(
                    Messages.NavigatorGroupName_Edge_4001_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
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
    private Collection<View> getLinksSourceByType(Collection<Edge> edges, String type) {
        LinkedList<View> result = new LinkedList<View>();
        for (Edge nextEdge : edges) {
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
    private Collection<View> getLinksTargetByType(Collection<Edge> edges, String type) {
        LinkedList<View> result = new LinkedList<View>();
        for (Edge nextEdge : edges) {
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
    @SuppressWarnings("unchecked")
    private Collection<View> getOutgoingLinksByType(Collection<? extends View> nodes, String type) {
        LinkedList<View> result = new LinkedList<View>();
        for (View nextNode : nodes) {
            result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    private Collection<View> getIncomingLinksByType(Collection<? extends View> nodes, String type) {
        LinkedList<View> result = new LinkedList<View>();
        for (View nextNode : nodes) {
            result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    private Collection<View> getChildrenByType(Collection<? extends View> nodes, String type) {
        LinkedList<View> result = new LinkedList<View>();
        for (View nextNode : nodes) {
            result.addAll(selectViewsByType(nextNode.getChildren(), type));
        }
        return result;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    private Collection<View> getDiagramLinksByType(Collection<Diagram> diagrams, String type) {
        ArrayList<View> result = new ArrayList<View>();
        for (Diagram nextDiagram : diagrams) {
            result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
        }
        return result;
    }

    // TODO refactor as static method
    /**
     * @generated
     */
    private Collection<View> selectViewsByType(Collection<View> views, String type) {
        ArrayList<View> result = new ArrayList<View>();
        for (View nextView : views) {
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
        return NodeEditPart.MODEL_ID.equals(GraphsVisualIDRegistry.getModelID(view));
    }

    /**
     * @generated
     */
    private Collection<GraphsNavigatorItem> createNavigatorItems(Collection<View> views,
            Object parent, boolean isLeafs) {
        ArrayList<GraphsNavigatorItem> result = new ArrayList<GraphsNavigatorItem>(views.size());
        for (View nextView : views) {
            result.add(new GraphsNavigatorItem(nextView, parent, isLeafs));
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
