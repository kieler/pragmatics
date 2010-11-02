package de.cau.cs.kieler.keg.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.GraphsPackage;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.Port;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge6EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge7EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Edge8EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel12EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel13EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel14EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel15EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel16EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel17EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel18EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel1EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel22EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel23EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel24EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel25EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel26EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel27EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel28EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel6EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel7EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel8EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabelEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel12EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel13EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel14EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel15EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel16EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel17EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel18EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel1EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel22EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel23EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel24EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel25EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel26EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel27EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel28EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.Node5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeCompartment2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeCompartmentEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeLabel2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeLabelEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.PortPortLabelEditPart;
import de.cau.cs.kieler.keg.diagram.expressions.GraphsAbstractExpression;
import de.cau.cs.kieler.keg.diagram.expressions.GraphsOCLFactory;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class GraphsVisualIDRegistry {

    /**
     * @generated
     */
    private static final String DEBUG_KEY = "de.cau.cs.kieler.keg.diagram/debug/visualID"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static int getVisualID(View view) {
        if (view instanceof Diagram) {
            if (NodeEditPart.MODEL_ID.equals(view.getType())) {
                return NodeEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        return de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry.getVisualID(view.getType());
    }

    /**
     * @generated
     */
    public static String getModelID(View view) {
        View diagram = view.getDiagram();
        while (view != diagram) {
            EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
            if (annotation != null) {
                return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
            }
            view = (View) view.eContainer();
        }
        return diagram != null ? diagram.getType() : null;
    }

    /**
     * @generated
     */
    public static int getVisualID(String type) {
        try {
            return Integer.parseInt(type);
        } catch (NumberFormatException e) {
            if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
                GraphsDiagramEditorPlugin.getInstance().logError(
                        "Unable to parse view type as a visualID number: " + type);
            }
        }
        return -1;
    }

    /**
     * @generated
     */
    public static String getType(int visualID) {
        return Integer.toString(visualID);
    }

    /**
     * @generated
     */
    public static int getDiagramVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                && isDiagram((Node) domainElement)) {
            return NodeEditPart.VISUAL_ID;
        }
        return -1;
    }

    /**
     * @generated
     */
    public static int getNodeVisualID(View containerView, EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        String containerModelID = de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry
                .getModelID(containerView);
        if (!NodeEditPart.MODEL_ID.equals(containerModelID)) {
            return -1;
        }
        int containerVisualID;
        if (NodeEditPart.MODEL_ID.equals(containerModelID)) {
            containerVisualID = de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry
                    .getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = NodeEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        switch (containerVisualID) {
        case NodeEditPart.VISUAL_ID:
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_2001((Node) domainElement)) {
                return Node2EditPart.VISUAL_ID;
            }
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_2002((Node) domainElement)) {
                return Node3EditPart.VISUAL_ID;
            }
            break;
        case Node2EditPart.VISUAL_ID:
            if (GraphsPackage.eINSTANCE.getPort().isSuperTypeOf(domainElement.eClass())
                    && isPort_3002((Port) domainElement)) {
                return PortEditPart.VISUAL_ID;
            }
            break;
        case Node4EditPart.VISUAL_ID:
            if (GraphsPackage.eINSTANCE.getPort().isSuperTypeOf(domainElement.eClass())
                    && isPort_3002((Port) domainElement)) {
                return PortEditPart.VISUAL_ID;
            }
            break;
        case NodeNodeCompartmentEditPart.VISUAL_ID:
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_3001((Node) domainElement)) {
                return Node4EditPart.VISUAL_ID;
            }
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_3003((Node) domainElement)) {
                return Node5EditPart.VISUAL_ID;
            }
            break;
        case NodeNodeCompartment2EditPart.VISUAL_ID:
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_3001((Node) domainElement)) {
                return Node4EditPart.VISUAL_ID;
            }
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_3003((Node) domainElement)) {
                return Node5EditPart.VISUAL_ID;
            }
            break;
        }
        return -1;
    }

    /**
     * @generated
     */
    public static boolean canCreateNode(View containerView, int nodeVisualID) {
        String containerModelID = de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry
                .getModelID(containerView);
        if (!NodeEditPart.MODEL_ID.equals(containerModelID)) {
            return false;
        }
        int containerVisualID;
        if (NodeEditPart.MODEL_ID.equals(containerModelID)) {
            containerVisualID = de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry
                    .getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = NodeEditPart.VISUAL_ID;
            } else {
                return false;
            }
        }
        switch (containerVisualID) {
        case NodeEditPart.VISUAL_ID:
            if (Node2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (Node3EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Node2EditPart.VISUAL_ID:
            if (NodeNodeLabelEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (NodeNodeCompartmentEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (PortEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Node4EditPart.VISUAL_ID:
            if (NodeNodeLabel2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (NodeNodeCompartment2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (PortEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case PortEditPart.VISUAL_ID:
            if (PortPortLabelEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case NodeNodeCompartmentEditPart.VISUAL_ID:
            if (Node4EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (Node5EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case NodeNodeCompartment2EditPart.VISUAL_ID:
            if (Node4EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (Node5EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case EdgeEditPart.VISUAL_ID:
            if (EdgeMidLabelEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel1EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel1EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Edge2EditPart.VISUAL_ID:
            if (EdgeMidLabel2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel12EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel22EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel12EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel22EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Edge3EditPart.VISUAL_ID:
            if (EdgeMidLabel3EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel13EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel23EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel13EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel23EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Edge4EditPart.VISUAL_ID:
            if (EdgeMidLabel4EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel14EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel24EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel14EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel24EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Edge5EditPart.VISUAL_ID:
            if (EdgeMidLabel5EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel15EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel25EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel15EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel25EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Edge6EditPart.VISUAL_ID:
            if (EdgeMidLabel6EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel16EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel26EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel16EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel26EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Edge7EditPart.VISUAL_ID:
            if (EdgeMidLabel7EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel17EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel27EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel17EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel27EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Edge8EditPart.VISUAL_ID:
            if (EdgeMidLabel8EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel18EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeHeadLabel28EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel18EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EdgeTailLabel28EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        }
        return false;
    }

    /**
     * @generated
     */
    public static int getLinkWithClassVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())
                && isEdge_4001((Edge) domainElement)) {
            return EdgeEditPart.VISUAL_ID;
        }
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())
                && isEdge_4002((Edge) domainElement)) {
            return Edge2EditPart.VISUAL_ID;
        }
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())
                && isEdge_4003((Edge) domainElement)) {
            return Edge3EditPart.VISUAL_ID;
        }
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())
                && isEdge_4004((Edge) domainElement)) {
            return Edge4EditPart.VISUAL_ID;
        }
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())
                && isEdge_4005((Edge) domainElement)) {
            return Edge5EditPart.VISUAL_ID;
        }
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())
                && isEdge_4006((Edge) domainElement)) {
            return Edge6EditPart.VISUAL_ID;
        }
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())
                && isEdge_4007((Edge) domainElement)) {
            return Edge7EditPart.VISUAL_ID;
        }
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())
                && isEdge_4008((Edge) domainElement)) {
            return Edge8EditPart.VISUAL_ID;
        }
        return -1;
    }

    /**
     * User can change implementation of this method to handle some specific
     * situations not covered by default logic.
     * 
     * @generated
     */
    private static boolean isDiagram(Node element) {
        return true;
    }

    /**
     * @generated
     */
    private static boolean isNode_2001(Node domainElement) {
        Object result = GraphsOCLFactory.getExpression(0, GraphsPackage.eINSTANCE.getNode(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isNode_2002(Node domainElement) {
        Object result = GraphsOCLFactory.getExpression(2, GraphsPackage.eINSTANCE.getNode(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isNode_3001(Node domainElement) {
        Object result = GraphsOCLFactory.getExpression(0, GraphsPackage.eINSTANCE.getNode(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isPort_3002(Port domainElement) {
        Object result = GraphsOCLFactory.getExpression(1, GraphsPackage.eINSTANCE.getPort(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isNode_3003(Node domainElement) {
        Object result = GraphsOCLFactory.getExpression(2, GraphsPackage.eINSTANCE.getNode(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isEdge_4001(Edge domainElement) {
        Object result = GraphsOCLFactory.getExpression(5, GraphsPackage.eINSTANCE.getEdge(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isEdge_4002(Edge domainElement) {
        Object result = GraphsOCLFactory.getExpression(7, GraphsPackage.eINSTANCE.getEdge(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isEdge_4003(Edge domainElement) {
        Object result = GraphsOCLFactory.getExpression(8, GraphsPackage.eINSTANCE.getEdge(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isEdge_4004(Edge domainElement) {
        Object result = GraphsOCLFactory.getExpression(11, GraphsPackage.eINSTANCE.getEdge(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isEdge_4005(Edge domainElement) {
        Object result = GraphsOCLFactory.getExpression(13, GraphsPackage.eINSTANCE.getEdge(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isEdge_4006(Edge domainElement) {
        Object result = GraphsOCLFactory.getExpression(16, GraphsPackage.eINSTANCE.getEdge(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isEdge_4007(Edge domainElement) {
        Object result = GraphsOCLFactory.getExpression(18, GraphsPackage.eINSTANCE.getEdge(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isEdge_4008(Edge domainElement) {
        Object result = GraphsOCLFactory.getExpression(21, GraphsPackage.eINSTANCE.getEdge(), null)
                .evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

}
