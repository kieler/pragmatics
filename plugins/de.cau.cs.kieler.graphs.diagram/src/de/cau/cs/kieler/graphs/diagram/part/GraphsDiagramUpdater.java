package de.cau.cs.kieler.graphs.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.graphs.Edge;
import de.cau.cs.kieler.graphs.Node;
import de.cau.cs.kieler.graphs.Port;
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
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeCompartment2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeCompartmentEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class GraphsDiagramUpdater {

    /**
     * @generated
     */
    public static List<GraphsNodeDescriptor> getSemanticChildren(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case NodeEditPart.VISUAL_ID:
            return getNode_1000SemanticChildren(view);
        case Node2EditPart.VISUAL_ID:
            return getNode_2001SemanticChildren(view);
        case Node4EditPart.VISUAL_ID:
            return getNode_3001SemanticChildren(view);
        case NodeNodeCompartmentEditPart.VISUAL_ID:
            return getNodeNodeCompartment_7001SemanticChildren(view);
        case NodeNodeCompartment2EditPart.VISUAL_ID:
            return getNodeNodeCompartment_7002SemanticChildren(view);
        }
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsNodeDescriptor> getNode_2001SemanticChildren(
            View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsNodeDescriptor> result =
                new LinkedList<GraphsNodeDescriptor>();
        for (Iterator<?> it = modelElement.getPorts().iterator(); it.hasNext();) {
            KPort childElement = (KPort) it.next();
            int visualID =
                    GraphsVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == PortEditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsNodeDescriptor> getNode_3001SemanticChildren(
            View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsNodeDescriptor> result =
                new LinkedList<GraphsNodeDescriptor>();
        for (Iterator<?> it = modelElement.getPorts().iterator(); it.hasNext();) {
            KPort childElement = (KPort) it.next();
            int visualID =
                    GraphsVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == PortEditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsNodeDescriptor> getNodeNodeCompartment_7001SemanticChildren(
            View view) {
        if (false == view.eContainer() instanceof View) {
            return Collections.emptyList();
        }
        View containerView = (View) view.eContainer();
        if (!containerView.isSetElement()) {
            return Collections.emptyList();
        }
        Node modelElement = (Node) containerView.getElement();
        LinkedList<GraphsNodeDescriptor> result =
                new LinkedList<GraphsNodeDescriptor>();
        for (Iterator<?> it = modelElement.getChildren().iterator(); it
                .hasNext();) {
            KNode childElement = (KNode) it.next();
            int visualID =
                    GraphsVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == Node4EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Node5EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsNodeDescriptor> getNodeNodeCompartment_7002SemanticChildren(
            View view) {
        if (false == view.eContainer() instanceof View) {
            return Collections.emptyList();
        }
        View containerView = (View) view.eContainer();
        if (!containerView.isSetElement()) {
            return Collections.emptyList();
        }
        Node modelElement = (Node) containerView.getElement();
        LinkedList<GraphsNodeDescriptor> result =
                new LinkedList<GraphsNodeDescriptor>();
        for (Iterator<?> it = modelElement.getChildren().iterator(); it
                .hasNext();) {
            KNode childElement = (KNode) it.next();
            int visualID =
                    GraphsVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == Node4EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Node5EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsNodeDescriptor> getNode_1000SemanticChildren(
            View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsNodeDescriptor> result =
                new LinkedList<GraphsNodeDescriptor>();
        for (Iterator<?> it = modelElement.getChildren().iterator(); it
                .hasNext();) {
            KNode childElement = (KNode) it.next();
            int visualID =
                    GraphsVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == Node2EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == Node3EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getContainedLinks(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case NodeEditPart.VISUAL_ID:
            return getNode_1000ContainedLinks(view);
        case Node2EditPart.VISUAL_ID:
            return getNode_2001ContainedLinks(view);
        case Node3EditPart.VISUAL_ID:
            return getNode_2002ContainedLinks(view);
        case Node4EditPart.VISUAL_ID:
            return getNode_3001ContainedLinks(view);
        case PortEditPart.VISUAL_ID:
            return getPort_3002ContainedLinks(view);
        case Node5EditPart.VISUAL_ID:
            return getNode_3003ContainedLinks(view);
        case EdgeEditPart.VISUAL_ID:
            return getEdge_4001ContainedLinks(view);
        case Edge2EditPart.VISUAL_ID:
            return getEdge_4002ContainedLinks(view);
        case Edge3EditPart.VISUAL_ID:
            return getEdge_4003ContainedLinks(view);
        case Edge4EditPart.VISUAL_ID:
            return getEdge_4004ContainedLinks(view);
        case Edge5EditPart.VISUAL_ID:
            return getEdge_4005ContainedLinks(view);
        case Edge6EditPart.VISUAL_ID:
            return getEdge_4006ContainedLinks(view);
        case Edge7EditPart.VISUAL_ID:
            return getEdge_4007ContainedLinks(view);
        case Edge8EditPart.VISUAL_ID:
            return getEdge_4008ContainedLinks(view);
        }
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getIncomingLinks(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case Node2EditPart.VISUAL_ID:
            return getNode_2001IncomingLinks(view);
        case Node3EditPart.VISUAL_ID:
            return getNode_2002IncomingLinks(view);
        case Node4EditPart.VISUAL_ID:
            return getNode_3001IncomingLinks(view);
        case PortEditPart.VISUAL_ID:
            return getPort_3002IncomingLinks(view);
        case Node5EditPart.VISUAL_ID:
            return getNode_3003IncomingLinks(view);
        case EdgeEditPart.VISUAL_ID:
            return getEdge_4001IncomingLinks(view);
        case Edge2EditPart.VISUAL_ID:
            return getEdge_4002IncomingLinks(view);
        case Edge3EditPart.VISUAL_ID:
            return getEdge_4003IncomingLinks(view);
        case Edge4EditPart.VISUAL_ID:
            return getEdge_4004IncomingLinks(view);
        case Edge5EditPart.VISUAL_ID:
            return getEdge_4005IncomingLinks(view);
        case Edge6EditPart.VISUAL_ID:
            return getEdge_4006IncomingLinks(view);
        case Edge7EditPart.VISUAL_ID:
            return getEdge_4007IncomingLinks(view);
        case Edge8EditPart.VISUAL_ID:
            return getEdge_4008IncomingLinks(view);
        }
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getOutgoingLinks(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case Node2EditPart.VISUAL_ID:
            return getNode_2001OutgoingLinks(view);
        case Node3EditPart.VISUAL_ID:
            return getNode_2002OutgoingLinks(view);
        case Node4EditPart.VISUAL_ID:
            return getNode_3001OutgoingLinks(view);
        case PortEditPart.VISUAL_ID:
            return getPort_3002OutgoingLinks(view);
        case Node5EditPart.VISUAL_ID:
            return getNode_3003OutgoingLinks(view);
        case EdgeEditPart.VISUAL_ID:
            return getEdge_4001OutgoingLinks(view);
        case Edge2EditPart.VISUAL_ID:
            return getEdge_4002OutgoingLinks(view);
        case Edge3EditPart.VISUAL_ID:
            return getEdge_4003OutgoingLinks(view);
        case Edge4EditPart.VISUAL_ID:
            return getEdge_4004OutgoingLinks(view);
        case Edge5EditPart.VISUAL_ID:
            return getEdge_4005OutgoingLinks(view);
        case Edge6EditPart.VISUAL_ID:
            return getEdge_4006OutgoingLinks(view);
        case Edge7EditPart.VISUAL_ID:
            return getEdge_4007OutgoingLinks(view);
        case Edge8EditPart.VISUAL_ID:
            return getEdge_4008OutgoingLinks(view);
        }
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_1000ContainedLinks(
            View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getContainedTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4003(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4004(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4005(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4006(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_2001ContainedLinks(
            View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getContainedTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4003(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4004(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4005(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4006(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_2002ContainedLinks(
            View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getContainedTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4003(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4004(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4005(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4006(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_3001ContainedLinks(
            View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getContainedTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4003(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4004(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4005(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4006(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getPort_3002ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_3003ContainedLinks(
            View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getContainedTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4003(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4004(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4005(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4006(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getContainedTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4001ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4002ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4003ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4004ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4005ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4006ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4007ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4008ContainedLinks(
            View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_2001IncomingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences =
                EcoreUtil.CrossReferencer.find(view.eResource()
                        .getResourceSet().getResources());
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4001(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4002(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4005(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4006(modelElement,
                crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_2002IncomingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences =
                EcoreUtil.CrossReferencer.find(view.eResource()
                        .getResourceSet().getResources());
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4001(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4002(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4005(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4006(modelElement,
                crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_3001IncomingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences =
                EcoreUtil.CrossReferencer.find(view.eResource()
                        .getResourceSet().getResources());
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4001(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4002(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4005(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4006(modelElement,
                crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getPort_3002IncomingLinks(View view) {
        Port modelElement = (Port) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences =
                EcoreUtil.CrossReferencer.find(view.eResource()
                        .getResourceSet().getResources());
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4003(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4004(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4007(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4008(modelElement,
                crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_3003IncomingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences =
                EcoreUtil.CrossReferencer.find(view.eResource()
                        .getResourceSet().getResources());
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4001(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4002(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4005(modelElement,
                crossReferences));
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4006(modelElement,
                crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4001IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4002IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4003IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4004IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4005IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4006IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4007IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4008IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_2001OutgoingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_2002OutgoingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_3001OutgoingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getPort_3002OutgoingLinks(View view) {
        Port modelElement = (Port) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4003(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4004(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4005(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4006(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getNode_3003OutgoingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4001(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4002(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4007(modelElement));
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4008(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4001OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4002OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4003OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4004OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4005OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4006OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4007OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    public static List<GraphsLinkDescriptor> getEdge_4008OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getContainedTypeModelFacetLinks_Edge_4001(
            KNode container) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (EdgeEditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode dst = link.getTarget();
            KNode src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4001, EdgeEditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getContainedTypeModelFacetLinks_Edge_4002(
            KNode container) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge2EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode dst = link.getTarget();
            KNode src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4002, Edge2EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getContainedTypeModelFacetLinks_Edge_4003(
            KNode container) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge3EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort dst = link.getTargetPort();
            KPort src = link.getSourcePort();
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4003, Edge3EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getContainedTypeModelFacetLinks_Edge_4004(
            KNode container) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge4EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort dst = link.getTargetPort();
            KPort src = link.getSourcePort();
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4004, Edge4EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getContainedTypeModelFacetLinks_Edge_4005(
            KNode container) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge5EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode dst = link.getTarget();
            KPort src = link.getSourcePort();
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4005, Edge5EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getContainedTypeModelFacetLinks_Edge_4006(
            KNode container) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge6EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode dst = link.getTarget();
            KPort src = link.getSourcePort();
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4006, Edge6EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getContainedTypeModelFacetLinks_Edge_4007(
            KNode container) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge7EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort dst = link.getTargetPort();
            KNode src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4007, Edge7EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getContainedTypeModelFacetLinks_Edge_4008(
            KNode container) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge8EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort dst = link.getTargetPort();
            KNode src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4008, Edge8EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getIncomingTypeModelFacetLinks_Edge_4001(
            KNode target,
            Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings =
                crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            if (setting.getEStructuralFeature() != KGraphPackage.eINSTANCE
                    .getKEdge_Target()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (EdgeEditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, target, link,
                    GraphsElementTypes.Edge_4001, EdgeEditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getIncomingTypeModelFacetLinks_Edge_4002(
            KNode target,
            Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings =
                crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            if (setting.getEStructuralFeature() != KGraphPackage.eINSTANCE
                    .getKEdge_Target()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (Edge2EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, target, link,
                    GraphsElementTypes.Edge_4002, Edge2EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getIncomingTypeModelFacetLinks_Edge_4003(
            KPort target,
            Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings =
                crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            if (setting.getEStructuralFeature() != KGraphPackage.eINSTANCE
                    .getKEdge_TargetPort()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (Edge3EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort src = link.getSourcePort();
            result.add(new GraphsLinkDescriptor(src, target, link,
                    GraphsElementTypes.Edge_4003, Edge3EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getIncomingTypeModelFacetLinks_Edge_4004(
            KPort target,
            Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings =
                crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            if (setting.getEStructuralFeature() != KGraphPackage.eINSTANCE
                    .getKEdge_TargetPort()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (Edge4EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort src = link.getSourcePort();
            result.add(new GraphsLinkDescriptor(src, target, link,
                    GraphsElementTypes.Edge_4004, Edge4EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getIncomingTypeModelFacetLinks_Edge_4005(
            KNode target,
            Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings =
                crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            if (setting.getEStructuralFeature() != KGraphPackage.eINSTANCE
                    .getKEdge_Target()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (Edge5EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort src = link.getSourcePort();
            result.add(new GraphsLinkDescriptor(src, target, link,
                    GraphsElementTypes.Edge_4005, Edge5EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getIncomingTypeModelFacetLinks_Edge_4006(
            KNode target,
            Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings =
                crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            if (setting.getEStructuralFeature() != KGraphPackage.eINSTANCE
                    .getKEdge_Target()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (Edge6EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort src = link.getSourcePort();
            result.add(new GraphsLinkDescriptor(src, target, link,
                    GraphsElementTypes.Edge_4006, Edge6EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getIncomingTypeModelFacetLinks_Edge_4007(
            KPort target,
            Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings =
                crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            if (setting.getEStructuralFeature() != KGraphPackage.eINSTANCE
                    .getKEdge_TargetPort()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (Edge7EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, target, link,
                    GraphsElementTypes.Edge_4007, Edge7EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getIncomingTypeModelFacetLinks_Edge_4008(
            KPort target,
            Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings =
                crossReferences.get(target);
        for (EStructuralFeature.Setting setting : settings) {
            if (setting.getEStructuralFeature() != KGraphPackage.eINSTANCE
                    .getKEdge_TargetPort()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (Edge8EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, target, link,
                    GraphsElementTypes.Edge_4008, Edge8EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getOutgoingTypeModelFacetLinks_Edge_4001(
            KNode source) {
        KNode container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element =
                element.eContainer()) {
            if (element instanceof KNode) {
                container = (KNode) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (EdgeEditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode dst = link.getTarget();
            KNode src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4001, EdgeEditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getOutgoingTypeModelFacetLinks_Edge_4002(
            KNode source) {
        KNode container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element =
                element.eContainer()) {
            if (element instanceof KNode) {
                container = (KNode) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge2EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode dst = link.getTarget();
            KNode src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4002, Edge2EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getOutgoingTypeModelFacetLinks_Edge_4003(
            KPort source) {
        KNode container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element =
                element.eContainer()) {
            if (element instanceof KNode) {
                container = (KNode) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge3EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort dst = link.getTargetPort();
            KPort src = link.getSourcePort();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4003, Edge3EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getOutgoingTypeModelFacetLinks_Edge_4004(
            KPort source) {
        KNode container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element =
                element.eContainer()) {
            if (element instanceof KNode) {
                container = (KNode) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge4EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort dst = link.getTargetPort();
            KPort src = link.getSourcePort();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4004, Edge4EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getOutgoingTypeModelFacetLinks_Edge_4005(
            KPort source) {
        KNode container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element =
                element.eContainer()) {
            if (element instanceof KNode) {
                container = (KNode) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge5EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode dst = link.getTarget();
            KPort src = link.getSourcePort();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4005, Edge5EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getOutgoingTypeModelFacetLinks_Edge_4006(
            KPort source) {
        KNode container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element =
                element.eContainer()) {
            if (element instanceof KNode) {
                container = (KNode) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge6EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KNode dst = link.getTarget();
            KPort src = link.getSourcePort();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4006, Edge6EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getOutgoingTypeModelFacetLinks_Edge_4007(
            KNode source) {
        KNode container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element =
                element.eContainer()) {
            if (element instanceof KNode) {
                container = (KNode) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge7EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort dst = link.getTargetPort();
            KNode src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4007, Edge7EditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection<GraphsLinkDescriptor> getOutgoingTypeModelFacetLinks_Edge_4008(
            KNode source) {
        KNode container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element =
                element.eContainer()) {
            if (element instanceof KNode) {
                container = (KNode) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        LinkedList<GraphsLinkDescriptor> result =
                new LinkedList<GraphsLinkDescriptor>();
        for (Iterator<?> links = container.getOutgoingEdges().iterator(); links
                .hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (Edge8EditPart.VISUAL_ID != GraphsVisualIDRegistry
                    .getLinkWithClassVisualID(link)) {
                continue;
            }
            KPort dst = link.getTargetPort();
            KNode src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link,
                    GraphsElementTypes.Edge_4008, Edge8EditPart.VISUAL_ID));
        }
        return result;
    }

}
