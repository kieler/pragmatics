package de.cau.cs.kieler.keg.diagram.providers;

import de.cau.cs.kieler.keg.Edge;
import de.cau.cs.kieler.keg.EdgeType;
import de.cau.cs.kieler.keg.KEGPackage;
import de.cau.cs.kieler.keg.Node;
import de.cau.cs.kieler.keg.diagram.expressions.GraphsAbstractExpression;
import de.cau.cs.kieler.keg.diagram.expressions.GraphsOCLFactory;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

    protected ElementInitializers() {
        // use #getInstance to access cached instance
    }

    /**
     * @generated
     */
    public void init_Node_2002(Node instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(3,
                            KEGPackage.eINSTANCE.getNode(), null).evaluate(
                            instance);
            instance.setIsHypernode(((Boolean) value_0).booleanValue());
            Object value_1 =
                    GraphsOCLFactory.getExpression(4,
                            KEGPackage.eINSTANCE.getNode(), null).evaluate(
                            instance);
            instance.setNodeLabel((String) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public void init_Node_3003(Node instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(3,
                            KEGPackage.eINSTANCE.getNode(), null).evaluate(
                            instance);
            instance.setIsHypernode(((Boolean) value_0).booleanValue());
            Object value_1 =
                    GraphsOCLFactory.getExpression(4,
                            KEGPackage.eINSTANCE.getNode(), null).evaluate(
                            instance);
            instance.setNodeLabel((String) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public void init_Edge_4001(Edge instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(6,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);
            instance.setIsDirected(((Boolean) value_0).booleanValue());
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public void init_Edge_4003(Edge instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(9,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);
            instance.setIsDirected(((Boolean) value_0).booleanValue());
            Object value_1 =
                    GraphsOCLFactory.getExpression(10,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);

            value_1 =
                    GraphsAbstractExpression.performCast(value_1,
                            KEGPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public void init_Edge_4004(Edge instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(12,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);

            value_0 =
                    GraphsAbstractExpression.performCast(value_0,
                            KEGPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_0);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public void init_Edge_4005(Edge instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(14,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);
            instance.setIsDirected(((Boolean) value_0).booleanValue());
            Object value_1 =
                    GraphsOCLFactory.getExpression(15,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);

            value_1 =
                    GraphsAbstractExpression.performCast(value_1,
                            KEGPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public void init_Edge_4006(Edge instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(17,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);

            value_0 =
                    GraphsAbstractExpression.performCast(value_0,
                            KEGPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_0);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public void init_Edge_4007(Edge instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(19,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);
            instance.setIsDirected(((Boolean) value_0).booleanValue());
            Object value_1 =
                    GraphsOCLFactory.getExpression(20,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);

            value_1 =
                    GraphsAbstractExpression.performCast(value_1,
                            KEGPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_1);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public void init_Edge_4008(Edge instance) {
        try {
            Object value_0 =
                    GraphsOCLFactory.getExpression(22,
                            KEGPackage.eINSTANCE.getEdge(), null).evaluate(
                            instance);

            value_0 =
                    GraphsAbstractExpression.performCast(value_0,
                            KEGPackage.eINSTANCE.getEdgeType());
            instance.setType((EdgeType) value_0);
        } catch (RuntimeException e) {
            GraphsDiagramEditorPlugin.getInstance().logError(
                    "Element initialization failed", e); //$NON-NLS-1$						
        }
    }

    /**
     * @generated
     */
    public static ElementInitializers getInstance() {
        ElementInitializers cached =
                GraphsDiagramEditorPlugin.getInstance()
                        .getElementInitializers();
        if (cached == null) {
            GraphsDiagramEditorPlugin.getInstance().setElementInitializers(
                    cached = new ElementInitializers());
        }
        return cached;
    }
}
