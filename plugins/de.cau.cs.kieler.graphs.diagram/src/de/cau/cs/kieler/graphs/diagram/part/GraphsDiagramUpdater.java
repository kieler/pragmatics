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

import de.cau.cs.kieler.graphs.CompoundNode;
import de.cau.cs.kieler.graphs.Edge;
import de.cau.cs.kieler.graphs.Graph;
import de.cau.cs.kieler.graphs.GraphsPackage;
import de.cau.cs.kieler.graphs.Node;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNode2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeNodeCompartment2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeNodeCompartmentEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.GraphEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class GraphsDiagramUpdater {

    /**
     * @generated
     */
    public static List getSemanticChildren(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case CompoundNodeNodeCompartmentEditPart.VISUAL_ID:
            return getCompoundNodeNodeCompartment_7003SemanticChildren(view);
        case CompoundNodeNodeCompartment2EditPart.VISUAL_ID:
            return getCompoundNodeNodeCompartment_7004SemanticChildren(view);
        case GraphEditPart.VISUAL_ID:
            return getGraph_1000SemanticChildren(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getCompoundNodeNodeCompartment_7003SemanticChildren(View view) {
        if (false == view.eContainer() instanceof View) {
            return Collections.EMPTY_LIST;
        }
        View containerView = (View) view.eContainer();
        if (!containerView.isSetElement()) {
            return Collections.EMPTY_LIST;
        }
        CompoundNode modelElement = (CompoundNode) containerView.getElement();
        List result = new LinkedList();
        for (Iterator it = modelElement.getNodes().iterator(); it.hasNext();) {
            Node childElement = (Node) it.next();
            int visualID = GraphsVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == Node2EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == CompoundNode2EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List getCompoundNodeNodeCompartment_7004SemanticChildren(View view) {
        if (false == view.eContainer() instanceof View) {
            return Collections.EMPTY_LIST;
        }
        View containerView = (View) view.eContainer();
        if (!containerView.isSetElement()) {
            return Collections.EMPTY_LIST;
        }
        CompoundNode modelElement = (CompoundNode) containerView.getElement();
        List result = new LinkedList();
        for (Iterator it = modelElement.getNodes().iterator(); it.hasNext();) {
            Node childElement = (Node) it.next();
            int visualID = GraphsVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == Node2EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == CompoundNode2EditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List getGraph_1000SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.EMPTY_LIST;
        }
        Graph modelElement = (Graph) view.getElement();
        List result = new LinkedList();
        for (Iterator it = modelElement.getNodes().iterator(); it.hasNext();) {
            Node childElement = (Node) it.next();
            int visualID = GraphsVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == NodeEditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == CompoundNodeEditPart.VISUAL_ID) {
                result.add(new GraphsNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @generated
     */
    public static List getContainedLinks(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case GraphEditPart.VISUAL_ID:
            return getGraph_1000ContainedLinks(view);
        case NodeEditPart.VISUAL_ID:
            return getNode_2003ContainedLinks(view);
        case CompoundNodeEditPart.VISUAL_ID:
            return getCompoundNode_2004ContainedLinks(view);
        case Node2EditPart.VISUAL_ID:
            return getNode_3003ContainedLinks(view);
        case CompoundNode2EditPart.VISUAL_ID:
            return getCompoundNode_3004ContainedLinks(view);
        case EdgeEditPart.VISUAL_ID:
            return getEdge_4002ContainedLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getIncomingLinks(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case NodeEditPart.VISUAL_ID:
            return getNode_2003IncomingLinks(view);
        case CompoundNodeEditPart.VISUAL_ID:
            return getCompoundNode_2004IncomingLinks(view);
        case Node2EditPart.VISUAL_ID:
            return getNode_3003IncomingLinks(view);
        case CompoundNode2EditPart.VISUAL_ID:
            return getCompoundNode_3004IncomingLinks(view);
        case EdgeEditPart.VISUAL_ID:
            return getEdge_4002IncomingLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getOutgoingLinks(View view) {
        switch (GraphsVisualIDRegistry.getVisualID(view)) {
        case NodeEditPart.VISUAL_ID:
            return getNode_2003OutgoingLinks(view);
        case CompoundNodeEditPart.VISUAL_ID:
            return getCompoundNode_2004OutgoingLinks(view);
        case Node2EditPart.VISUAL_ID:
            return getNode_3003OutgoingLinks(view);
        case CompoundNode2EditPart.VISUAL_ID:
            return getCompoundNode_3004OutgoingLinks(view);
        case EdgeEditPart.VISUAL_ID:
            return getEdge_4002OutgoingLinks(view);
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getGraph_1000ContainedLinks(View view) {
        Graph modelElement = (Graph) view.getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Edge_4002(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getNode_2003ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getCompoundNode_2004ContainedLinks(View view) {
        CompoundNode modelElement = (CompoundNode) view.getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Edge_4002(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getNode_3003ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getCompoundNode_3004ContainedLinks(View view) {
        CompoundNode modelElement = (CompoundNode) view.getElement();
        List result = new LinkedList();
        result.addAll(getContainedTypeModelFacetLinks_Edge_4002(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getEdge_4002ContainedLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getNode_2003IncomingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet()
                .getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4002(modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getCompoundNode_2004IncomingLinks(View view) {
        CompoundNode modelElement = (CompoundNode) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet()
                .getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4002(modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getNode_3003IncomingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet()
                .getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4002(modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getCompoundNode_3004IncomingLinks(View view) {
        CompoundNode modelElement = (CompoundNode) view.getElement();
        Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet()
                .getResources());
        List result = new LinkedList();
        result.addAll(getIncomingTypeModelFacetLinks_Edge_4002(modelElement, crossReferences));
        return result;
    }

    /**
     * @generated
     */
    public static List getEdge_4002IncomingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    public static List getNode_2003OutgoingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        List result = new LinkedList();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4002(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getCompoundNode_2004OutgoingLinks(View view) {
        CompoundNode modelElement = (CompoundNode) view.getElement();
        List result = new LinkedList();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4002(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getNode_3003OutgoingLinks(View view) {
        Node modelElement = (Node) view.getElement();
        List result = new LinkedList();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4002(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getCompoundNode_3004OutgoingLinks(View view) {
        CompoundNode modelElement = (CompoundNode) view.getElement();
        List result = new LinkedList();
        result.addAll(getOutgoingTypeModelFacetLinks_Edge_4002(modelElement));
        return result;
    }

    /**
     * @generated
     */
    public static List getEdge_4002OutgoingLinks(View view) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @generated
     */
    private static Collection getContainedTypeModelFacetLinks_Edge_4002(Graph container) {
        Collection result = new LinkedList();
        for (Iterator links = container.getEdges().iterator(); links.hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (EdgeEditPart.VISUAL_ID != GraphsVisualIDRegistry.getLinkWithClassVisualID(link)) {
                continue;
            }
            Node dst = link.getTarget();
            Node src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, dst, link, GraphsElementTypes.Edge_4002,
                    EdgeEditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getIncomingTypeModelFacetLinks_Edge_4002(Node target, Map crossReferences) {
        Collection result = new LinkedList();
        Collection settings = (Collection) crossReferences.get(target);
        for (Iterator it = settings.iterator(); it.hasNext();) {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
            if (setting.getEStructuralFeature() != GraphsPackage.eINSTANCE.getEdge_Target()
                    || false == setting.getEObject() instanceof Edge) {
                continue;
            }
            Edge link = (Edge) setting.getEObject();
            if (EdgeEditPart.VISUAL_ID != GraphsVisualIDRegistry.getLinkWithClassVisualID(link)) {
                continue;
            }
            Node src = link.getSource();
            result.add(new GraphsLinkDescriptor(src, target, link, GraphsElementTypes.Edge_4002,
                    EdgeEditPart.VISUAL_ID));
        }
        return result;
    }

    /**
     * @generated
     */
    private static Collection getOutgoingTypeModelFacetLinks_Edge_4002(Node source) {
        Graph container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element = element
                .eContainer()) {
            if (element instanceof Graph) {
                container = (Graph) element;
            }
        }
        if (container == null) {
            return Collections.EMPTY_LIST;
        }
        Collection result = new LinkedList();
        for (Iterator links = container.getEdges().iterator(); links.hasNext();) {
            EObject linkObject = (EObject) links.next();
            if (false == linkObject instanceof Edge) {
                continue;
            }
            Edge link = (Edge) linkObject;
            if (EdgeEditPart.VISUAL_ID != GraphsVisualIDRegistry.getLinkWithClassVisualID(link)) {
                continue;
            }
            Node dst = link.getTarget();
            Node src = link.getSource();
            if (src != source) {
                continue;
            }
            result.add(new GraphsLinkDescriptor(src, dst, link, GraphsElementTypes.Edge_4002,
                    EdgeEditPart.VISUAL_ID));
        }
        return result;
    }

}
