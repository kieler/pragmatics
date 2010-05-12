package de.cau.cs.kieler.graphs.diagram.edit.policies;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Ratio;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge6EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge7EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge8EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramUpdater;
import de.cau.cs.kieler.graphs.diagram.part.GraphsLinkDescriptor;
import de.cau.cs.kieler.graphs.diagram.part.GraphsNodeDescriptor;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class NodeCanonicalEditPolicy extends CanonicalConnectionEditPolicy {

    /**
     * @generated
     */
    Set myFeaturesToSynchronize;

    /**
     * @generated
     */
    protected List getSemanticChildrenList() {
        View viewObject = (View) getHost().getModel();
        List result = new LinkedList();
        for (Iterator it = GraphsDiagramUpdater.getNode_1000SemanticChildren(
                viewObject).iterator(); it.hasNext();) {
            result.add(((GraphsNodeDescriptor) it.next()).getModelElement());
        }
        return result;
    }

    /**
     * @generated
     */
    protected boolean shouldDeleteView(View view) {
        return true;
    }

    /**
     * @generated
     */
    protected boolean isOrphaned(Collection semanticChildren, final View view) {
        int visualID = GraphsVisualIDRegistry.getVisualID(view);
        switch (visualID) {
        case Node2EditPart.VISUAL_ID:
        case Node3EditPart.VISUAL_ID:
            if (!semanticChildren.contains(view.getElement())) {
                return true;
            }
            EObject domainModelElement = view.getElement();
            if (visualID != GraphsVisualIDRegistry.getNodeVisualID(
                    (View) getHost().getModel(), domainModelElement)) {
                List createdViews = createViews(Collections
                        .singletonList(domainModelElement));
                assert createdViews.size() == 1;
                final View createdView = (View) ((IAdaptable) createdViews
                        .get(0)).getAdapter(View.class);
                if (createdView != null) {
                    try {
                        new AbstractEMFOperation(host().getEditingDomain(),
                                StringStatics.BLANK, Collections.singletonMap(
                                        Transaction.OPTION_UNPROTECTED,
                                        Boolean.TRUE)) {
                            protected IStatus doExecute(
                                    IProgressMonitor monitor, IAdaptable info)
                                    throws ExecutionException {
                                populateViewProperties(view, createdView);
                                return Status.OK_STATUS;
                            }
                        }.execute(new NullProgressMonitor(), null);
                    } catch (ExecutionException e) {
                        GraphsDiagramEditorPlugin
                                .getInstance()
                                .logError(
                                        "Error while copyign view information to newly created view", e); //$NON-NLS-1$
                    }
                }
                deleteViews(Collections.singletonList(view).iterator());
            }
        }
        return false;
    }

    /**
     * @generated
     */
    private void populateViewProperties(View oldView, View newView) {
        if (oldView instanceof Node && newView instanceof Node) {
            Node oldNode = (Node) oldView;
            Node newNode = (Node) newView;
            if (oldNode.getLayoutConstraint() instanceof Location
                    && newNode.getLayoutConstraint() instanceof Location) {
                ((Location) newNode.getLayoutConstraint())
                        .setX(((Location) oldNode.getLayoutConstraint()).getX());
                ((Location) newNode.getLayoutConstraint())
                        .setY(((Location) oldNode.getLayoutConstraint()).getY());
            }
            if (oldNode.getLayoutConstraint() instanceof Size
                    && newNode.getLayoutConstraint() instanceof Size) {
                ((Size) newNode.getLayoutConstraint()).setWidth(((Size) oldNode
                        .getLayoutConstraint()).getWidth());
                ((Size) newNode.getLayoutConstraint())
                        .setHeight(((Size) oldNode.getLayoutConstraint())
                                .getHeight());
            }
            if (oldNode.getLayoutConstraint() instanceof Ratio
                    && newNode.getLayoutConstraint() instanceof Ratio) {
                ((Ratio) newNode.getLayoutConstraint())
                        .setValue(((Ratio) oldNode.getLayoutConstraint())
                                .getValue());
            }
            newNode.persist();
        }
    }

    /**
     * @generated
     */
    protected String getDefaultFactoryHint() {
        return null;
    }

    /**
     * @generated
     */
    protected Set getFeaturesToSynchronize() {
        if (myFeaturesToSynchronize == null) {
            myFeaturesToSynchronize = new HashSet();
            myFeaturesToSynchronize.add(KGraphPackage.eINSTANCE
                    .getKNode_Children());
        }
        return myFeaturesToSynchronize;
    }

    /**
     * @generated
     */
    protected List getSemanticConnectionsList() {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    protected EObject getSourceElement(EObject relationship) {
        return null;
    }

    /**
     * @generated
     */
    protected EObject getTargetElement(EObject relationship) {
        return null;
    }

    /**
     * @generated
     */
    protected boolean shouldIncludeConnection(Edge connector,
            Collection children) {
        return false;
    }

    /**
     * @generated
     */
    protected void refreshSemantic() {
        List createdViews = new LinkedList();
        createdViews.addAll(refreshSemanticChildren());
        List createdConnectionViews = new LinkedList();
        createdConnectionViews.addAll(refreshSemanticConnections());
        createdConnectionViews.addAll(refreshConnections());

        if (createdViews.size() > 1) {
            // perform a layout of the container
            DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host()
                    .getEditingDomain(), createdViews, host());
            executeCommand(new ICommandProxy(layoutCmd));
        }

        createdViews.addAll(createdConnectionViews);
        makeViewsImmutable(createdViews);
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
    private Collection refreshConnections() {
        Map domain2NotationMap = new HashMap();
        Collection linkDescriptors = collectAllLinks(getDiagram(),
                domain2NotationMap);
        Collection existingLinks = new LinkedList(getDiagram().getEdges());
        for (Iterator linksIterator = existingLinks.iterator(); linksIterator
                .hasNext();) {
            Edge nextDiagramLink = (Edge) linksIterator.next();
            int diagramLinkVisualID = GraphsVisualIDRegistry
                    .getVisualID(nextDiagramLink);
            if (diagramLinkVisualID == -1) {
                if (nextDiagramLink.getSource() != null
                        && nextDiagramLink.getTarget() != null) {
                    linksIterator.remove();
                }
                continue;
            }
            EObject diagramLinkObject = nextDiagramLink.getElement();
            EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
            EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
            for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator
                    .hasNext();) {
                GraphsLinkDescriptor nextLinkDescriptor = (GraphsLinkDescriptor) linkDescriptorsIterator
                        .next();
                if (diagramLinkObject == nextLinkDescriptor.getModelElement()
                        && diagramLinkSrc == nextLinkDescriptor.getSource()
                        && diagramLinkDst == nextLinkDescriptor
                                .getDestination()
                        && diagramLinkVisualID == nextLinkDescriptor
                                .getVisualID()) {
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
    private Collection collectAllLinks(View view, Map domain2NotationMap) {
        if (!NodeEditPart.MODEL_ID.equals(GraphsVisualIDRegistry
                .getModelID(view))) {
            return Collections.EMPTY_LIST;
        }
        Collection result = new LinkedList();
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case NodeEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getNode_1000ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Node2EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getNode_2001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Node3EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getNode_2002ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Node4EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getNode_3001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case PortEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getPort_3002ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Node5EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getNode_3003ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case EdgeEditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getEdge_4001ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge2EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getEdge_4002ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge3EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getEdge_4003ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge4EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getEdge_4004ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge5EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getEdge_4005ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge6EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getEdge_4006ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge7EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getEdge_4007ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        case Edge8EditPart.VISUAL_ID: {
            if (!domain2NotationMap.containsKey(view.getElement())) {
                result.addAll(GraphsDiagramUpdater
                        .getEdge_4008ContainedLinks(view));
            }
            if (!domain2NotationMap.containsKey(view.getElement())
                    || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
                domain2NotationMap.put(view.getElement(), view);
            }
            break;
        }
        }
        for (Iterator children = view.getChildren().iterator(); children
                .hasNext();) {
            result.addAll(collectAllLinks((View) children.next(),
                    domain2NotationMap));
        }
        for (Iterator edges = view.getSourceEdges().iterator(); edges.hasNext();) {
            result.addAll(collectAllLinks((View) edges.next(),
                    domain2NotationMap));
        }
        return result;
    }

    /**
     * @generated
     */
    private Collection createConnections(Collection linkDescriptors,
            Map domain2NotationMap) {
        List adapters = new LinkedList();
        for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator
                .hasNext();) {
            final GraphsLinkDescriptor nextLinkDescriptor = (GraphsLinkDescriptor) linkDescriptorsIterator
                    .next();
            EditPart sourceEditPart = getEditPart(nextLinkDescriptor
                    .getSource(), domain2NotationMap);
            EditPart targetEditPart = getEditPart(nextLinkDescriptor
                    .getDestination(), domain2NotationMap);
            if (sourceEditPart == null || targetEditPart == null) {
                continue;
            }
            CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(
                    nextLinkDescriptor.getSemanticAdapter(), String
                            .valueOf(nextLinkDescriptor.getVisualID()),
                    ViewUtil.APPEND, false, ((IGraphicalEditPart) getHost())
                            .getDiagramPreferencesHint());
            CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(
                    descriptor);
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
    private EditPart getEditPart(EObject domainModelElement,
            Map domain2NotationMap) {
        View view = (View) domain2NotationMap.get(domainModelElement);
        if (view != null) {
            return (EditPart) getHost().getViewer().getEditPartRegistry().get(
                    view);
        }
        return null;
    }
}
