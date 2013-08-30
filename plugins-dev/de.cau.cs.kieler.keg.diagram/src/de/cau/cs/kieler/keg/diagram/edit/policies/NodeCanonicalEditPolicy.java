package de.cau.cs.kieler.keg.diagram.edit.policies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetViewMutabilityCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
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
import de.cau.cs.kieler.keg.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramUpdater;
import de.cau.cs.kieler.keg.diagram.part.GraphsLinkDescriptor;
import de.cau.cs.kieler.keg.diagram.part.GraphsNodeDescriptor;
import de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class NodeCanonicalEditPolicy extends CanonicalEditPolicy {

    /**
     * @generated
     */
    protected void refreshOnActivate() {
        // Need to activate editpart children before invoking the canonical refresh for EditParts to add event listeners
        List<?> c = getHost().getChildren();
        for (int i = 0; i < c.size(); i++) {
            ((EditPart) c.get(i)).activate();
        }
        super.refreshOnActivate();
    }

    /**
     * @generated
     */
    protected EStructuralFeature getFeatureToSynchronize() {
        return KGraphPackage.eINSTANCE.getKNode_Children();
    }

    /**
     * @generated
     */
    @SuppressWarnings("rawtypes")
    protected List getSemanticChildrenList() {
        View viewObject = (View) getHost().getModel();
        LinkedList<EObject> result = new LinkedList<EObject>();
        List<GraphsNodeDescriptor> childDescriptors = GraphsDiagramUpdater
                .getNode_1000SemanticChildren(viewObject);
        for (GraphsNodeDescriptor d : childDescriptors) {
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
        int visualID = GraphsVisualIDRegistry.getVisualID(view);
        return visualID == Node2EditPart.VISUAL_ID || visualID == Node3EditPart.VISUAL_ID;
    }

    /**
     * @generated
     */
    protected void refreshSemantic() {
        if (resolveSemanticElement() == null) {
            return;
        }
        LinkedList<IAdaptable> createdViews = new LinkedList<IAdaptable>();
        List<GraphsNodeDescriptor> childDescriptors = GraphsDiagramUpdater
                .getNode_1000SemanticChildren((View) getHost().getModel());
        LinkedList<View> orphaned = new LinkedList<View>();
        // we care to check only views we recognize as ours
        LinkedList<View> knownViewChildren = new LinkedList<View>();
        for (View v : getViewChildren()) {
            if (isMyDiagramElement(v)) {
                knownViewChildren.add(v);
            }
        }
        // alternative to #cleanCanonicalSemanticChildren(getViewChildren(), semanticChildren)
        HashMap<GraphsNodeDescriptor, LinkedList<View>> potentialViews = new HashMap<GraphsNodeDescriptor, LinkedList<View>>();
        //
        // iteration happens over list of desired semantic elements, trying to find best matching View, while original CEP
        // iterates views, potentially losing view (size/bounds) information - i.e. if there are few views to reference same EObject, only last one 
        // to answer isOrphaned == true will be used for the domain element representation, see #cleanCanonicalSemanticChildren()
        for (Iterator<GraphsNodeDescriptor> descriptorsIterator = childDescriptors.iterator(); descriptorsIterator
                .hasNext();) {
            GraphsNodeDescriptor next = descriptorsIterator.next();
            String hint = GraphsVisualIDRegistry.getType(next.getVisualID());
            LinkedList<View> perfectMatch = new LinkedList<View>(); // both semanticElement and hint match that of NodeDescriptor
            LinkedList<View> potentialMatch = new LinkedList<View>(); // semanticElement matches, hint does not
            for (View childView : getViewChildren()) {
                EObject semanticElement = childView.getElement();
                if (next.getModelElement().equals(semanticElement)) {
                    if (hint.equals(childView.getType())) {
                        perfectMatch.add(childView);
                        // actually, can stop iteration over view children here, but
                        // may want to use not the first view but last one as a 'real' match (the way original CEP does
                        // with its trick with viewToSemanticMap inside #cleanCanonicalSemanticChildren
                    } else {
                        potentialMatch.add(childView);
                    }
                }
            }
            if (perfectMatch.size() > 0) {
                descriptorsIterator.remove(); // precise match found no need to create anything for the NodeDescriptor
                // use only one view (first or last?), keep rest as orphaned for further consideration
                knownViewChildren.remove(perfectMatch.getFirst());
            } else if (potentialMatch.size() > 0) {
                potentialViews.put(next, potentialMatch);
            }
        }
        // those left in knownViewChildren are subject to removal - they are our diagram elements we didn't find match to,
        // or those we have potential matches to, and thus need to be recreated, preserving size/location information.
        orphaned.addAll(knownViewChildren);
        //
        CompositeTransactionalCommand boundsCommand = new CompositeTransactionalCommand(host()
                .getEditingDomain(), DiagramUIMessages.SetLocationCommand_Label_Resize);
        ArrayList<CreateViewRequest.ViewDescriptor> viewDescriptors = new ArrayList<CreateViewRequest.ViewDescriptor>(
                childDescriptors.size());
        for (GraphsNodeDescriptor next : childDescriptors) {
            String hint = GraphsVisualIDRegistry.getType(next.getVisualID());
            IAdaptable elementAdapter = new CanonicalElementAdapter(next.getModelElement(), hint);
            CreateViewRequest.ViewDescriptor descriptor = new CreateViewRequest.ViewDescriptor(
                    elementAdapter, Node.class, hint, ViewUtil.APPEND, false, host()
                            .getDiagramPreferencesHint());
            viewDescriptors.add(descriptor);

            LinkedList<View> possibleMatches = potentialViews.get(next);
            if (possibleMatches != null) {
                // from potential matches, leave those that were not eventually used for some other NodeDescriptor (i.e. those left as orphaned)
                possibleMatches.retainAll(knownViewChildren);
            }
            if (possibleMatches != null && !possibleMatches.isEmpty()) {
                View originalView = possibleMatches.getFirst();
                knownViewChildren.remove(originalView); // remove not to copy properties of the same view again and again
                // add command to copy properties
                if (originalView instanceof Node) {
                    if (((Node) originalView).getLayoutConstraint() instanceof Bounds) {
                        Bounds b = (Bounds) ((Node) originalView).getLayoutConstraint();
                        boundsCommand.add(new SetBoundsCommand(boundsCommand.getEditingDomain(),
                                boundsCommand.getLabel(), descriptor, new Rectangle(b.getX(), b
                                        .getY(), b.getWidth(), b.getHeight())));
                    } else if (((Node) originalView).getLayoutConstraint() instanceof Location) {
                        Location l = (Location) ((Node) originalView).getLayoutConstraint();
                        boundsCommand
                                .add(new SetBoundsCommand(boundsCommand.getEditingDomain(),
                                        boundsCommand.getLabel(), descriptor, new Point(l.getX(), l
                                                .getY())));
                    } else if (((Node) originalView).getLayoutConstraint() instanceof Size) {
                        Size s = (Size) ((Node) originalView).getLayoutConstraint();
                        boundsCommand.add(new SetBoundsCommand(boundsCommand.getEditingDomain(),
                                boundsCommand.getLabel(), descriptor, new Dimension(s.getWidth(), s
                                        .getHeight())));
                    }
                }
            }
        }

        boolean changed = deleteViews(orphaned.iterator());
        //
        CreateViewRequest request = getCreateViewRequest(viewDescriptors);
        Command cmd = getCreateViewCommand(request);
        if (cmd != null && cmd.canExecute()) {
            SetViewMutabilityCommand.makeMutable(new EObjectAdapter(host().getNotationView()))
                    .execute();
            executeCommand(cmd);
            if (boundsCommand.canExecute()) {
                executeCommand(new ICommandProxy(boundsCommand.reduce()));
            }
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Collection<IAdaptable> refreshConnections() {
        Map<EObject, View> domain2NotationMap = new HashMap<EObject, View>();
        Collection<GraphsLinkDescriptor> linkDescriptors = collectAllLinks(getDiagram(),
                domain2NotationMap);
        Collection<View> existingLinks = new LinkedList(getDiagram().getEdges());
        for (Iterator<View> linksIterator = existingLinks.iterator(); linksIterator.hasNext();) {
            Edge nextDiagramLink = (Edge) linksIterator.next();
            int diagramLinkVisualID = GraphsVisualIDRegistry.getVisualID(nextDiagramLink);
            if (diagramLinkVisualID == -1) {
                if (nextDiagramLink.getSource() != null && nextDiagramLink.getTarget() != null) {
                    linksIterator.remove();
                }
                continue;
            }
            EObject diagramLinkObject = nextDiagramLink.getElement();
            EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
            EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
            for (Iterator<GraphsLinkDescriptor> linkDescriptorsIterator = linkDescriptors
                    .iterator(); linkDescriptorsIterator.hasNext();) {
                GraphsLinkDescriptor nextLinkDescriptor = linkDescriptorsIterator.next();
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
    private Collection<GraphsLinkDescriptor> collectAllLinks(View view,
            Map<EObject, View> domain2NotationMap) {
        if (!NodeEditPart.MODEL_ID.equals(GraphsVisualIDRegistry.getModelID(view))) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result = new LinkedList<GraphsLinkDescriptor>();
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case NodeEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getNode_1000ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Node2EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getNode_2001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Node3EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getNode_2002ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Node4EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getNode_3001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case PortEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getPort_3002ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Node5EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getNode_3003ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case EdgeEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getEdge_4001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge2EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getEdge_4002ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge3EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getEdge_4003ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge4EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getEdge_4004ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge5EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getEdge_4005ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge6EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getEdge_4006ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge7EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getEdge_4007ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge8EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater.getEdge_4008ContainedLinks(view));
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
            Collection<GraphsLinkDescriptor> linkDescriptors, Map<EObject, View> domain2NotationMap) {
        LinkedList<IAdaptable> adapters = new LinkedList<IAdaptable>();
        for (GraphsLinkDescriptor nextLinkDescriptor : linkDescriptors) {
            EditPart sourceEditPart = getEditPart(nextLinkDescriptor.getSource(),
                    domain2NotationMap);
            EditPart targetEditPart = getEditPart(nextLinkDescriptor.getDestination(),
                    domain2NotationMap);
            if (sourceEditPart == null || targetEditPart == null) {
                continue;
            }
            CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(
                    nextLinkDescriptor.getSemanticAdapter(),
                    GraphsVisualIDRegistry.getType(nextLinkDescriptor.getVisualID()),
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

    /**
     * @generated
     */
    private Diagram getDiagram() {
        return ((View) getHost().getModel()).getDiagram();
    }
}
