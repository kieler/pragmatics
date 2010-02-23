package de.cau.cs.kieler.graphs.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.graphs.Graph;
import de.cau.cs.kieler.graphs.GraphsPackage;
import de.cau.cs.kieler.graphs.Node;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNode2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeNodeCompartment2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeNodeCompartmentEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.GraphEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.expressions.GraphsAbstractExpression;
import de.cau.cs.kieler.graphs.diagram.expressions.GraphsOCLFactory;

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
    private static final String DEBUG_KEY = "de.cau.cs.kieler.graphs.diagram/debug/visualID"; //$NON-NLS-1$

    /**
     * @generated
     */
    private static GraphsAbstractExpression Node_2001_Constraint;
    /**
     * @generated
     */
    private static GraphsAbstractExpression Node_3001_Constraint;

    /**
     * @generated
     */
    public static int getVisualID(View view) {
        if (view instanceof Diagram) {
            if (GraphEditPart.MODEL_ID.equals(view.getType())) {
                return GraphEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        return de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry.getVisualID(view.getType());
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
        return String.valueOf(visualID);
    }

    /**
     * @generated
     */
    public static int getDiagramVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (GraphsPackage.eINSTANCE.getGraph().isSuperTypeOf(domainElement.eClass())
                && isDiagram((Graph) domainElement)) {
            return GraphEditPart.VISUAL_ID;
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
        String containerModelID = de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry
                .getModelID(containerView);
        if (!GraphEditPart.MODEL_ID.equals(containerModelID)) {
            return -1;
        }
        int containerVisualID;
        if (GraphEditPart.MODEL_ID.equals(containerModelID)) {
            containerVisualID = de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry
                    .getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = GraphEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        switch (containerVisualID) {
        case CompoundNodeNodeCompartmentEditPart.VISUAL_ID:
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_3001((Node) domainElement)) {
                return Node2EditPart.VISUAL_ID;
            }
            if (GraphsPackage.eINSTANCE.getCompoundNode().isSuperTypeOf(domainElement.eClass())) {
                return CompoundNode2EditPart.VISUAL_ID;
            }
            break;
        case CompoundNodeNodeCompartment2EditPart.VISUAL_ID:
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_3001((Node) domainElement)) {
                return Node2EditPart.VISUAL_ID;
            }
            if (GraphsPackage.eINSTANCE.getCompoundNode().isSuperTypeOf(domainElement.eClass())) {
                return CompoundNode2EditPart.VISUAL_ID;
            }
            break;
        case GraphEditPart.VISUAL_ID:
            if (GraphsPackage.eINSTANCE.getNode().isSuperTypeOf(domainElement.eClass())
                    && isNode_2001((Node) domainElement)) {
                return NodeEditPart.VISUAL_ID;
            }
            if (GraphsPackage.eINSTANCE.getCompoundNode().isSuperTypeOf(domainElement.eClass())) {
                return CompoundNodeEditPart.VISUAL_ID;
            }
            break;
        }
        return -1;
    }

    /**
     * @generated
     */
    public static boolean canCreateNode(View containerView, int nodeVisualID) {
        String containerModelID = de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry
                .getModelID(containerView);
        if (!GraphEditPart.MODEL_ID.equals(containerModelID)) {
            return false;
        }
        int containerVisualID;
        if (GraphEditPart.MODEL_ID.equals(containerModelID)) {
            containerVisualID = de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry
                    .getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = GraphEditPart.VISUAL_ID;
            } else {
                return false;
            }
        }
        switch (containerVisualID) {
        case NodeEditPart.VISUAL_ID:
            if (NodeLabelEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case CompoundNodeEditPart.VISUAL_ID:
            if (CompoundNodeLabelEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CompoundNodeNodeCompartmentEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case Node2EditPart.VISUAL_ID:
            if (NodeLabel2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case CompoundNode2EditPart.VISUAL_ID:
            if (CompoundNodeLabel2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CompoundNodeNodeCompartment2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case CompoundNodeNodeCompartmentEditPart.VISUAL_ID:
            if (Node2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CompoundNode2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case CompoundNodeNodeCompartment2EditPart.VISUAL_ID:
            if (Node2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CompoundNode2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case GraphEditPart.VISUAL_ID:
            if (NodeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CompoundNodeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case EdgeEditPart.VISUAL_ID:
            if (EdgeLabelEditPart.VISUAL_ID == nodeVisualID) {
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
        if (GraphsPackage.eINSTANCE.getEdge().isSuperTypeOf(domainElement.eClass())) {
            return EdgeEditPart.VISUAL_ID;
        }
        return -1;
    }

    /**
     * User can change implementation of this method to handle some specific
     * situations not covered by default logic.
     * 
     * @generated
     */
    private static boolean isDiagram(Graph element) {
        return true;
    }

    /**
     * @generated
     */
    private static boolean isNode_2001(Node domainElement) {
        if (Node_2001_Constraint == null) { // lazy initialization
            Node_2001_Constraint = GraphsOCLFactory.getExpression(
                    "not self.oclIsTypeOf(graphs::CompoundNode)", GraphsPackage.eINSTANCE.getNode()); //$NON-NLS-1$
        }
        Object result = Node_2001_Constraint.evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

    /**
     * @generated
     */
    private static boolean isNode_3001(Node domainElement) {
        if (Node_3001_Constraint == null) { // lazy initialization
            Node_3001_Constraint = GraphsOCLFactory.getExpression(
                    "not self.oclIsTypeOf(graphs::CompoundNode)", GraphsPackage.eINSTANCE.getNode()); //$NON-NLS-1$
        }
        Object result = Node_3001_Constraint.evaluate(domainElement);
        return result instanceof Boolean && ((Boolean) result).booleanValue();
    }

}
