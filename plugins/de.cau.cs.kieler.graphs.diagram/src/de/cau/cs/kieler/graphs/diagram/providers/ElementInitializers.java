package de.cau.cs.kieler.graphs.diagram.providers;

import de.cau.cs.kieler.graphs.Edge;
import de.cau.cs.kieler.graphs.EdgeType;
import de.cau.cs.kieler.graphs.GraphsPackage;
import de.cau.cs.kieler.graphs.Node;
import de.cau.cs.kieler.graphs.diagram.expressions.GraphsAbstractExpression;
import de.cau.cs.kieler.graphs.diagram.expressions.GraphsOCLFactory;
import de.cau.cs.kieler.graphs.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {
    /**
     * @generated
     */
    public static void init_Node_2002(Node instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression("true",
                    GraphsPackage.eINSTANCE.getNode()).evaluate(instance);
            instance.setIsHypernode((Boolean) value_0);
            Object value_1 = GraphsOCLFactory.getExpression("\'Hypernode\'",
                    GraphsPackage.eINSTANCE.getNode()).evaluate(instance);
            instance.setNodeLabel((String) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Node_3003(Node instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression("true",
                    GraphsPackage.eINSTANCE.getNode()).evaluate(instance);
            instance.setIsHypernode((Boolean) value_0);
            Object value_1 = GraphsOCLFactory.getExpression("\'Hypernode\'",
                    GraphsPackage.eINSTANCE.getNode()).evaluate(instance);
            instance.setNodeLabel((String) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Edge_4001(Edge instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression("true",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);
            instance.setIsDirected(((Boolean) value_0).booleanValue());
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Edge_4003(Edge instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression("true",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);
            instance.setIsDirected(((Boolean) value_0).booleanValue());
            Object value_1 = GraphsOCLFactory.getExpression(
                    "graphs::EdgeType::Port2Port",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);

            value_1 = GraphsAbstractExpression.performCast(value_1,
                    GraphsPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Edge_4004(Edge instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression(
                    "graphs::EdgeType::Port2Port",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);

            value_0 = GraphsAbstractExpression.performCast(value_0,
                    GraphsPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_0);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Edge_4005(Edge instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression("true",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);
            instance.setIsDirected(((Boolean) value_0).booleanValue());
            Object value_1 = GraphsOCLFactory.getExpression(
                    "graphs::EdgeType::Port2Node",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);

            value_1 = GraphsAbstractExpression.performCast(value_1,
                    GraphsPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Edge_4006(Edge instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression(
                    "graphs::EdgeType::Port2Node",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);

            value_0 = GraphsAbstractExpression.performCast(value_0,
                    GraphsPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_0);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Edge_4007(Edge instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression("true",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);
            instance.setIsDirected(((Boolean) value_0).booleanValue());
            Object value_1 = GraphsOCLFactory.getExpression(
                    "graphs::EdgeType::Node2Port",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);

            value_1 = GraphsAbstractExpression.performCast(value_1,
                    GraphsPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static void init_Edge_4008(Edge instance) {
        try {
            Object value_0 = GraphsOCLFactory.getExpression(
                    "graphs::EdgeType::Node2Port",
                    GraphsPackage.eINSTANCE.getEdge()).evaluate(instance);

            value_0 = GraphsAbstractExpression.performCast(value_0,
                    GraphsPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_0);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

}
