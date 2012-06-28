package de.cau.cs.kieler.kaom.diagram.navigator;

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

import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;
import de.cau.cs.kieler.kaom.diagram.part.Messages;

/**
 * @generated
 */
public class KaomNavigatorContentProvider implements ICommonContentProvider {

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
    public KaomNavigatorContentProvider() {
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
            ArrayList<KaomNavigatorItem> result = new ArrayList<KaomNavigatorItem>();
            ArrayList<View> topViews = new ArrayList<View>(resource.getContents().size());
            for (EObject o : resource.getContents()) {
                if (o instanceof View) {
                    topViews.add((View) o);
                }
            }
            result.addAll(createNavigatorItems(
                    selectViewsByType(topViews, EntityEditPart.MODEL_ID), file, false));
            return result.toArray();
        }

        if (parentElement instanceof KaomNavigatorGroup) {
            KaomNavigatorGroup group = (KaomNavigatorGroup) parentElement;
            return group.getChildren();
        }

        if (parentElement instanceof KaomNavigatorItem) {
            KaomNavigatorItem navigatorItem = (KaomNavigatorItem) parentElement;
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
        switch (KaomVisualIDRegistry.getVisualID(view)) {

        case Entity3EditPart.VISUAL_ID: {
            LinkedList<KaomAbstractNavigatorItem> result = new LinkedList<KaomAbstractNavigatorItem>();
            Node sv = (Node) view;
            KaomNavigatorGroup incominglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Entity_3002_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            KaomNavigatorGroup outgoinglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Entity_3002_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(EntityEntityCompartment2EditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews,
                    KaomVisualIDRegistry.getType(Entity3EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(EntityEntityCompartment2EditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews,
                    KaomVisualIDRegistry.getType(Relation2EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case Entity2EditPart.VISUAL_ID: {
            LinkedList<KaomAbstractNavigatorItem> result = new LinkedList<KaomAbstractNavigatorItem>();
            Node sv = (Node) view;
            KaomNavigatorGroup incominglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Entity_2001_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            KaomNavigatorGroup outgoinglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Entity_2001_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(EntityEntityCompartmentEditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews,
                    KaomVisualIDRegistry.getType(Entity3EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(EntityEntityCompartmentEditPart.VISUAL_ID));
            connectedViews = getChildrenByType(connectedViews,
                    KaomVisualIDRegistry.getType(Relation2EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case Relation2EditPart.VISUAL_ID: {
            LinkedList<KaomAbstractNavigatorItem> result = new LinkedList<KaomAbstractNavigatorItem>();
            Node sv = (Node) view;
            KaomNavigatorGroup incominglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Relation_3003_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            KaomNavigatorGroup outgoinglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Relation_3003_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case EntityEditPart.VISUAL_ID: {
            LinkedList<KaomAbstractNavigatorItem> result = new LinkedList<KaomAbstractNavigatorItem>();
            Diagram sv = (Diagram) view;
            KaomNavigatorGroup links = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Entity_1000_links,
                    "icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(Entity2EditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getChildrenByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(RelationEditPart.VISUAL_ID));
            result.addAll(createNavigatorItems(connectedViews, parentElement, false));
            connectedViews = getDiagramLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            links.addChildren(createNavigatorItems(connectedViews, links, false));
            if (!links.isEmpty()) {
                result.add(links);
            }
            return result.toArray();
        }

        case RelationEditPart.VISUAL_ID: {
            LinkedList<KaomAbstractNavigatorItem> result = new LinkedList<KaomAbstractNavigatorItem>();
            Node sv = (Node) view;
            KaomNavigatorGroup incominglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Relation_2002_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            KaomNavigatorGroup outgoinglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Relation_2002_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case PortEditPart.VISUAL_ID: {
            LinkedList<KaomAbstractNavigatorItem> result = new LinkedList<KaomAbstractNavigatorItem>();
            Node sv = (Node) view;
            KaomNavigatorGroup incominglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Port_3001_incominglinks,
                    "icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            KaomNavigatorGroup outgoinglinks = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Port_3001_outgoinglinks,
                    "icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getIncomingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            incominglinks.addChildren(createNavigatorItems(connectedViews, incominglinks, true));
            connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
            outgoinglinks.addChildren(createNavigatorItems(connectedViews, outgoinglinks, true));
            if (!incominglinks.isEmpty()) {
                result.add(incominglinks);
            }
            if (!outgoinglinks.isEmpty()) {
                result.add(outgoinglinks);
            }
            return result.toArray();
        }

        case LinkEditPart.VISUAL_ID: {
            LinkedList<KaomAbstractNavigatorItem> result = new LinkedList<KaomAbstractNavigatorItem>();
            Edge sv = (Edge) view;
            KaomNavigatorGroup target = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Link_4001_target,
                    "icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            KaomNavigatorGroup source = new KaomNavigatorGroup(
                    Messages.NavigatorGroupName_Link_4001_source,
                    "icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
            Collection<View> connectedViews;
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(Entity2EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(RelationEditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(Entity3EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksTargetByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(Relation2EditPart.VISUAL_ID));
            target.addChildren(createNavigatorItems(connectedViews, target, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(Entity2EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(RelationEditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(Entity3EditPart.VISUAL_ID));
            source.addChildren(createNavigatorItems(connectedViews, source, true));
            connectedViews = getLinksSourceByType(Collections.singleton(sv),
                    KaomVisualIDRegistry.getType(Relation2EditPart.VISUAL_ID));
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
        return EntityEditPart.MODEL_ID.equals(KaomVisualIDRegistry.getModelID(view));
    }

    /**
     * @generated
     */
    private Collection<KaomNavigatorItem> createNavigatorItems(Collection<View> views,
            Object parent, boolean isLeafs) {
        ArrayList<KaomNavigatorItem> result = new ArrayList<KaomNavigatorItem>(views.size());
        for (View nextView : views) {
            result.add(new KaomNavigatorItem(nextView, parent, isLeafs));
        }
        return result;
    }

    /**
     * @generated
     */
    public Object getParent(Object element) {
        if (element instanceof KaomAbstractNavigatorItem) {
            KaomAbstractNavigatorItem abstractNavigatorItem = (KaomAbstractNavigatorItem) element;
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
