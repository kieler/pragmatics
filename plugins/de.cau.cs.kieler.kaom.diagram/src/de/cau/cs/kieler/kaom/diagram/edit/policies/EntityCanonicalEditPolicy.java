package de.cau.cs.kieler.kaom.diagram.edit.policies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetViewMutabilityCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.kaom.KaomPackage;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomDiagramUpdater;
import de.cau.cs.kieler.kaom.diagram.part.KaomLinkDescriptor;
import de.cau.cs.kieler.kaom.diagram.part.KaomNodeDescriptor;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;

/**
 * @generated
 */
public class EntityCanonicalEditPolicy extends CanonicalEditPolicy {

    /**
     * @generated
     */
    private Set<EStructuralFeature> myFeaturesToSynchronize;

    /**
     * @generated
     */
    @SuppressWarnings("rawtypes")
    protected List getSemanticChildrenList() {
        View viewObject = (View) getHost().getModel();
        LinkedList<EObject> result = new LinkedList<EObject>();
        List<KaomNodeDescriptor> childDescriptors = KaomDiagramUpdater
                .getEntity_1000SemanticChildren(viewObject);
        for (KaomNodeDescriptor d : childDescriptors) {
            result.add(d.getModelElement());
        }
        return result;
    }

    /**
     * @generated
     */
    protected boolean isOrphaned(Collection<EObject> semanticChildren, final View view) {
        return isMyDiagramElement(view) && !semanticChildren.contains(view.getElement());
    }

    /**
     * @generated
     */
    private boolean isMyDiagramElement(View view) {
        int visualID = KaomVisualIDRegistry.getVisualID(view);
        return visualID == Entity2EditPart.VISUAL_ID || visualID == RelationEditPart.VISUAL_ID;
    }

    /**
     * @generated
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Set getFeaturesToSynchronize() {
        if (myFeaturesToSynchronize == null) {
            myFeaturesToSynchronize = new HashSet<EStructuralFeature>();
            myFeaturesToSynchronize.add(KaomPackage.eINSTANCE.getEntity_ChildEntities());
            myFeaturesToSynchronize.add(KaomPackage.eINSTANCE.getEntity_ChildRelations());
        }
        return myFeaturesToSynchronize;
    }

    /**
     * @generated
     */
    protected void refreshSemantic() {
        if (resolveSemanticElement() == null) {
            return;
        }
        LinkedList<IAdaptable> createdViews = new LinkedList<IAdaptable>();
        List<KaomNodeDescriptor> childDescriptors = KaomDiagramUpdater
                .getEntity_1000SemanticChildren((View) getHost().getModel());
        LinkedList<View> orphaned = new LinkedList<View>();
        // we care to check only views we recognize as ours
        LinkedList<View> knownViewChildren = new LinkedList<View>();
        for (View v : getViewChildren()) {
            if (isMyDiagramElement(v)) {
                knownViewChildren.add(v);
            }
        }
        // alternative to #cleanCanonicalSemanticChildren(getViewChildren(), semanticChildren)
        //
        // iteration happens over list of desired semantic elements, trying to find best matching View, while original CEP
        // iterates views, potentially losing view (size/bounds) information - i.e. if there are few views to reference same EObject, only last one 
        // to answer isOrphaned == true will be used for the domain element representation, see #cleanCanonicalSemanticChildren()
        for (Iterator<KaomNodeDescriptor> descriptorsIterator = childDescriptors.iterator(); descriptorsIterator
                .hasNext();) {
            KaomNodeDescriptor next = descriptorsIterator.next();
            String hint = KaomVisualIDRegistry.getType(next.getVisualID());
            LinkedList<View> perfectMatch = new LinkedList<View>(); // both semanticElement and hint match that of NodeDescriptor
            for (View childView : getViewChildren()) {
                EObject semanticElement = childView.getElement();
                if (next.getModelElement().equals(semanticElement)) {
                    if (hint.equals(childView.getType())) {
                        perfectMatch.add(childView);
                        // actually, can stop iteration over view children here, but
                        // may want to use not the first view but last one as a 'real' match (the way original CEP does
                        // with its trick with viewToSemanticMap inside #cleanCanonicalSemanticChildren
                    }
                }
            }
            if (perfectMatch.size() > 0) {
                descriptorsIterator.remove(); // precise match found no need to create anything for the NodeDescriptor
                // use only one view (first or last?), keep rest as orphaned for further consideration
                knownViewChildren.remove(perfectMatch.getFirst());
            }
        }
        // those left in knownViewChildren are subject to removal - they are our diagram elements we didn't find match to,
        // or those we have potential matches to, and thus need to be recreated, preserving size/location information.
        orphaned.addAll(knownViewChildren);
        //
        ArrayList<CreateViewRequest.ViewDescriptor> viewDescriptors = new ArrayList<CreateViewRequest.ViewDescriptor>(
                childDescriptors.size());
        for (KaomNodeDescriptor next : childDescriptors) {
            String hint = KaomVisualIDRegistry.getType(next.getVisualID());
            IAdaptable elementAdapter = new CanonicalElementAdapter(next.getModelElement(), hint);
            CreateViewRequest.ViewDescriptor descriptor = new CreateViewRequest.ViewDescriptor(
                    elementAdapter, Node.class, hint, ViewUtil.APPEND, false, host()
                            .getDiagramPreferencesHint());
            viewDescriptors.add(descriptor);
        }

        boolean changed = deleteViews(orphaned.iterator());
        //
        CreateViewRequest request = getCreateViewRequest(viewDescriptors);
        Command cmd = getCreateViewCommand(request);
        if (cmd != null && cmd.canExecute()) {
            SetViewMutabilityCommand.makeMutable(new EObjectAdapter(host().getNotationView()))
                    .execute();
            executeCommand(cmd);
            @SuppressWarnings("unchecked")
            List<IAdaptable> nl = (List<IAdaptable>) request.getNewObject();
            createdViews.addAll(nl);
        }
        if (changed || createdViews.size() > 0) {
            postProcessRefreshSemantic(createdViews);
        }

        Collection<IAdaptable> createdConnectionViews = refreshConnections();

        if (createdViews.size() > 1) {
            // perform a layout of the container
            DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host().getEditingDomain(),
                    createdViews, host());
            executeCommand(new ICommandProxy(layoutCmd));
        }

        createdViews.addAll(createdConnectionViews);

        makeViewsImmutable(createdViews);

        SetViewMutabilityCommand.makeImmutable(createdViews).execute();
    }

    /**
     * @generated
     */
    private Diagram getDiagram() {
        return ((View) getHost().getModel()).getDiagram();
    }

    /**
     * @generated
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Collection<IAdaptable> refreshConnections() {
        Map<EObject, View> domain2NotationMap = new HashMap<EObject, View>();
        Collection<KaomLinkDescriptor> linkDescriptors = collectAllLinks(getDiagram(),
                domain2NotationMap);
        Collection existingLinks = new LinkedList(getDiagram().getEdges());
        for (Iterator<?> linksIterator = existingLinks.iterator(); linksIterator.hasNext();) {
            Edge nextDiagramLink = (Edge) linksIterator.next();
            int diagramLinkVisualID = KaomVisualIDRegistry.getVisualID(nextDiagramLink);
            if (diagramLinkVisualID == -1) {
                if (nextDiagramLink.getSource() != null && nextDiagramLink.getTarget() != null) {
                    linksIterator.remove();
                }
                continue;
            }
            EObject diagramLinkObject = nextDiagramLink.getElement();
            EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
            EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
            for (Iterator<KaomLinkDescriptor> linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator
                    .hasNext();) {
                KaomLinkDescriptor nextLinkDescriptor = linkDescriptorsIterator.next();
                if (diagramLinkObject == nextLinkDescriptor.getModelElement()
                        && diagramLinkSrc == nextLinkDescriptor.getSource()
                        && diagramLinkDst == nextLinkDescriptor.getDestination()
                        && diagramLinkVisualID == nextLinkDescriptor.getVisualID()) {
                    linksIterator.remove();
                    linkDescriptorsIterator.remove();
                    break;
                }
            }
        }
        deleteViews(existingLinks.iterator());
        return createConnections(linkDescriptors, domain2NotationMap);
    }

    /**
     * @generated
     */
    private Collection<KaomLinkDescriptor> collectAllLinks(View view,
            Map<EObject, View> domain2NotationMap) {
        if (!EntityEditPart.MODEL_ID.equals(KaomVisualIDRegistry.getModelID(view))) {
            return Collections.emptyList();
        }
        LinkedList<KaomLinkDescriptor> result = new LinkedList<KaomLinkDescriptor>();
        switch (KaomVisualIDRegistry.getVisualID(view)) {
        case EntityEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(KaomDiagramUpdater.getEntity_1000ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Entity2EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(KaomDiagramUpdater.getEntity_2001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case RelationEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(KaomDiagramUpdater.getRelation_2002ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case PortEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(KaomDiagramUpdater.getPort_3001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Entity3EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(KaomDiagramUpdater.getEntity_3002ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Relation2EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(KaomDiagramUpdater.getRelation_3003ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case LinkEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(KaomDiagramUpdater.getLink_4001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        }
        for (Iterator<?> children = view.getChildren().iterator(); children.hasNext();) {
            result.addAll(collectAllLinks((View) children.next(), domain2NotationMap));
        }
        for (Iterator<?> edges = view.getSourceEdges().iterator(); edges.hasNext();) {
            result.addAll(collectAllLinks((View) edges.next(), domain2NotationMap));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection<IAdaptable> createConnections(
            Collection<KaomLinkDescriptor> linkDescriptors, Map<EObject, View> domain2NotationMap) {
        LinkedList<IAdaptable> adapters = new LinkedList<IAdaptable>();
        for (KaomLinkDescriptor nextLinkDescriptor : linkDescriptors) {
            EditPart sourceEditPart = getEditPart(nextLinkDescriptor.getSource(),
                    domain2NotationMap);
            EditPart targetEditPart = getEditPart(nextLinkDescriptor.getDestination(),
                    domain2NotationMap);
            if (sourceEditPart == null || targetEditPart == null) {
                continue;
            }
            CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(
                    nextLinkDescriptor.getSemanticAdapter(),
                    KaomVisualIDRegistry.getType(nextLinkDescriptor.getVisualID()),
                    ViewUtil.APPEND, false,
                    ((IGraphicalEditPart) getHost()).getDiagramPreferencesHint());
            CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(descriptor);
            ccr.setType(RequestConstants.REQ_CONNECTION_START);
            ccr.setSourceEditPart(sourceEditPart);
            sourceEditPart.getCommand(ccr);
            ccr.setTargetEditPart(targetEditPart);
            ccr.setType(RequestConstants.REQ_CONNECTION_END);
            Command cmd = targetEditPart.getCommand(ccr);
            if (cmd != null && cmd.canExecute()) {
                executeCommand(cmd);
                IAdaptable viewAdapter = (IAdaptable) ccr.getNewObject();
                if (viewAdapter != null) {
                    adapters.add(viewAdapter);
                }
            }
        }
        return adapters;
    }

    /**
     * @generated
     */
    private EditPart getEditPart(EObject domainModelElement, Map<EObject, View> domain2NotationMap) {
        View view = (View) domain2NotationMap.get(domainModelElement);
        if (view != null) {
            return (EditPart) getHost().getViewer().getEditPartRegistry().get(view);
        }
        return null;
    }
}
