package de.cau.cs.kieler.graphs.diagram.providers;

import java.util.ArrayList;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewForKindOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.TitleStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;

import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge6EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge7EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Edge8EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel12EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel13EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel14EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel15EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel16EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel17EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel18EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel1EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel22EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel23EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel24EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel25EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel26EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel27EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel28EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel6EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel7EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel8EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel12EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel13EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel14EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel15EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel16EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel17EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel18EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel1EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel22EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel23EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel24EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel25EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel26EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel27EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel28EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.Node5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeCompartment2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeCompartmentEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortPortLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class GraphsViewProvider extends AbstractProvider implements IViewProvider {

    /**
     * @generated
     */
    public final boolean provides(IOperation operation) {
        if (operation instanceof CreateViewForKindOperation) {
            return provides((CreateViewForKindOperation) operation);
        }
        assert operation instanceof CreateViewOperation;
        if (operation instanceof CreateDiagramViewOperation) {
            return provides((CreateDiagramViewOperation) operation);
        } else if (operation instanceof CreateEdgeViewOperation) {
            return provides((CreateEdgeViewOperation) operation);
        } else if (operation instanceof CreateNodeViewOperation) {
            return provides((CreateNodeViewOperation) operation);
        }
        return false;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateViewForKindOperation op) {
        /*
         if (op.getViewKind() == Node.class)
         return getNodeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
         if (op.getViewKind() == Edge.class)
         return getEdgeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
         */
        return true;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateDiagramViewOperation op) {
        return NodeEditPart.MODEL_ID.equals(op.getSemanticHint())
                && GraphsVisualIDRegistry
                        .getDiagramVisualID(getSemanticElement(op.getSemanticAdapter())) != -1;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateNodeViewOperation op) {
        if (op.getContainerView() == null) {
            return false;
        }
        IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
        EObject domainElement = getSemanticElement(op.getSemanticAdapter());
        int visualID;
        if (op.getSemanticHint() == null) {
            // Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
            // In this situation there should be NO elementType, visualID will be determined
            // by VisualIDRegistry.getNodeVisualID() for domainElement.
            if (elementType != null || domainElement == null) {
                return false;
            }
            visualID = GraphsVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement);
        } else {
            visualID = GraphsVisualIDRegistry.getVisualID(op.getSemanticHint());
            if (elementType != null) {
                if (!GraphsElementTypes.isKnownElementType(elementType)
                        || (!(elementType instanceof IHintedType))) {
                    return false; // foreign element type
                }
                String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
                if (!op.getSemanticHint().equals(elementTypeHint)) {
                    return false; // if semantic hint is specified it should be the same as in element type
                }
                if (domainElement != null
                        && visualID != GraphsVisualIDRegistry.getNodeVisualID(op.getContainerView(),
                                domainElement)) {
                    return false; // visual id for node EClass should match visual id from element type
                }
            } else {
                if (!NodeEditPart.MODEL_ID.equals(GraphsVisualIDRegistry.getModelID(op
                        .getContainerView()))) {
                    return false; // foreign diagram
                }
                switch (visualID) {
                case PortEditPart.VISUAL_ID:
                case Node2EditPart.VISUAL_ID:
                case Node3EditPart.VISUAL_ID:
                case Node4EditPart.VISUAL_ID:
                case Node5EditPart.VISUAL_ID:
                    if (domainElement == null
                            || visualID != GraphsVisualIDRegistry.getNodeVisualID(op.getContainerView(),
                                    domainElement)) {
                        return false; // visual id in semantic hint should match visual id for domain element
                    }
                    break;
                default:
                    return false;
                }
            }
        }
        return Node2EditPart.VISUAL_ID == visualID || Node3EditPart.VISUAL_ID == visualID
                || Node4EditPart.VISUAL_ID == visualID || PortEditPart.VISUAL_ID == visualID
                || Node5EditPart.VISUAL_ID == visualID;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateEdgeViewOperation op) {
        IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
        if (!GraphsElementTypes.isKnownElementType(elementType)
                || (!(elementType instanceof IHintedType))) {
            return false; // foreign element type
        }
        String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
        if (elementTypeHint == null
                || (op.getSemanticHint() != null && !elementTypeHint.equals(op.getSemanticHint()))) {
            return false; // our hint is visual id and must be specified, and it should be the same as in element type
        }
        int visualID = GraphsVisualIDRegistry.getVisualID(elementTypeHint);
        EObject domainElement = getSemanticElement(op.getSemanticAdapter());
        if (domainElement != null
                && visualID != GraphsVisualIDRegistry.getLinkWithClassVisualID(domainElement)) {
            return false; // visual id for link EClass should match visual id from element type
        }
        return true;
    }

    /**
     * @generated
     */
    public Diagram createDiagram(IAdaptable semanticAdapter, String diagramKind,
            PreferencesHint preferencesHint) {
        Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
        diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
        diagram.setType(NodeEditPart.MODEL_ID);
        diagram.setElement(getSemanticElement(semanticAdapter));
        diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
        return diagram;
    }

    /**
     * @generated
     */
    public Node createNode(IAdaptable semanticAdapter, View containerView, String semanticHint,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        final EObject domainElement = getSemanticElement(semanticAdapter);
        final int visualID;
        if (semanticHint == null) {
            visualID = GraphsVisualIDRegistry.getNodeVisualID(containerView, domainElement);
        } else {
            visualID = GraphsVisualIDRegistry.getVisualID(semanticHint);
        }
        switch (visualID) {
        case Node2EditPart.VISUAL_ID:
            return createNode_2001(domainElement, containerView, index, persisted, preferencesHint);
        case Node3EditPart.VISUAL_ID:
            return createNode_2002(domainElement, containerView, index, persisted, preferencesHint);
        case Node4EditPart.VISUAL_ID:
            return createNode_3001(domainElement, containerView, index, persisted, preferencesHint);
        case PortEditPart.VISUAL_ID:
            return createPort_3002(domainElement, containerView, index, persisted, preferencesHint);
        case Node5EditPart.VISUAL_ID:
            return createNode_3003(domainElement, containerView, index, persisted, preferencesHint);
        }
        // can't happen, provided #provides(CreateNodeViewOperation) is correct
        return null;
    }

    /**
     * @generated
     */
    public Edge createEdge(IAdaptable semanticAdapter, View containerView, String semanticHint,
            int index, boolean persisted, PreferencesHint preferencesHint) {
        IElementType elementType = getSemanticElementType(semanticAdapter);
        String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
        switch (GraphsVisualIDRegistry.getVisualID(elementTypeHint)) {
        case EdgeEditPart.VISUAL_ID:
            return createEdge_4001(getSemanticElement(semanticAdapter), containerView, index, persisted,
                    preferencesHint);
        case Edge2EditPart.VISUAL_ID:
            return createEdge_4002(getSemanticElement(semanticAdapter), containerView, index, persisted,
                    preferencesHint);
        case Edge3EditPart.VISUAL_ID:
            return createEdge_4003(getSemanticElement(semanticAdapter), containerView, index, persisted,
                    preferencesHint);
        case Edge4EditPart.VISUAL_ID:
            return createEdge_4004(getSemanticElement(semanticAdapter), containerView, index, persisted,
                    preferencesHint);
        case Edge5EditPart.VISUAL_ID:
            return createEdge_4005(getSemanticElement(semanticAdapter), containerView, index, persisted,
                    preferencesHint);
        case Edge6EditPart.VISUAL_ID:
            return createEdge_4006(getSemanticElement(semanticAdapter), containerView, index, persisted,
                    preferencesHint);
        case Edge7EditPart.VISUAL_ID:
            return createEdge_4007(getSemanticElement(semanticAdapter), containerView, index, persisted,
                    preferencesHint);
        case Edge8EditPart.VISUAL_ID:
            return createEdge_4008(getSemanticElement(semanticAdapter), containerView, index, persisted,
                    preferencesHint);
        }
        // can never happen, provided #provides(CreateEdgeViewOperation) is correct
        return null;
    }

    /**
     * @generated
     */
    public Node createNode_2001(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(GraphsVisualIDRegistry.getType(Node2EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Node label5003 = createLabel(node,
                GraphsVisualIDRegistry.getType(NodeNodeLabelEditPart.VISUAL_ID));
        createCompartment(node, GraphsVisualIDRegistry.getType(NodeNodeCompartmentEditPart.VISUAL_ID),
                false, false, false, false);
        return node;
    }

    /**
     * @generated
     */
    public Node createNode_2002(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createLineStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(GraphsVisualIDRegistry.getType(Node3EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        stampShortcut(containerView, node);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore,
                IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(),
                FigureUtilities.RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        return node;
    }

    /**
     * @generated
     */
    public Node createNode_3001(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(GraphsVisualIDRegistry.getType(Node4EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Node label5002 = createLabel(node,
                GraphsVisualIDRegistry.getType(NodeNodeLabel2EditPart.VISUAL_ID));
        createCompartment(node, GraphsVisualIDRegistry.getType(NodeNodeCompartment2EditPart.VISUAL_ID),
                false, false, false, false);
        return node;
    }

    /**
     * @generated
     */
    public Node createPort_3002(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(GraphsVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Node label5001 = createLabel(node,
                GraphsVisualIDRegistry.getType(PortPortLabelEditPart.VISUAL_ID));
        label5001.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location5001 = (Location) label5001.getLayoutConstraint();
        location5001.setX(0);
        location5001.setY(5);
        return node;
    }

    /**
     * @generated
     */
    public Node createNode_3003(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createLineStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(GraphsVisualIDRegistry.getType(Node5EditPart.VISUAL_ID));
        ViewUtil.insertChildView(containerView, node, index, persisted);
        node.setElement(domainElement);
        // initializeFromPreferences 
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();

        org.eclipse.swt.graphics.RGB lineRGB = PreferenceConverter.getColor(prefStore,
                IPreferenceConstants.PREF_LINE_COLOR);
        ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(),
                FigureUtilities.RGBToInteger(lineRGB));
        FontStyle nodeFontStyle = (FontStyle) node.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (nodeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            nodeFontStyle.setFontName(fontData.getName());
            nodeFontStyle.setFontHeight(fontData.getHeight());
            nodeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            nodeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            nodeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        return node;
    }

    /**
     * @generated
     */
    public Edge createEdge_4001(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
        ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(GraphsVisualIDRegistry.getType(EdgeEditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
        }
        Node label6001 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeMidLabelEditPart.VISUAL_ID));
        label6001.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6001 = (Location) label6001.getLayoutConstraint();
        location6001.setX(0);
        location6001.setY(40);
        Node label6002 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel1EditPart.VISUAL_ID));
        label6002.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6002 = (Location) label6002.getLayoutConstraint();
        location6002.setX(0);
        location6002.setY(60);
        Node label6003 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel2EditPart.VISUAL_ID));
        label6003.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6003 = (Location) label6003.getLayoutConstraint();
        location6003.setX(0);
        location6003.setY(80);
        Node label6004 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel1EditPart.VISUAL_ID));
        label6004.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6004 = (Location) label6004.getLayoutConstraint();
        location6004.setX(0);
        location6004.setY(100);
        Node label6005 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel2EditPart.VISUAL_ID));
        label6005.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6005 = (Location) label6005.getLayoutConstraint();
        location6005.setX(0);
        location6005.setY(120);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createEdge_4002(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
        ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(GraphsVisualIDRegistry.getType(Edge2EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
        }
        Node label6006 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeMidLabel2EditPart.VISUAL_ID));
        label6006.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6006 = (Location) label6006.getLayoutConstraint();
        location6006.setX(0);
        location6006.setY(40);
        Node label6007 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel12EditPart.VISUAL_ID));
        label6007.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6007 = (Location) label6007.getLayoutConstraint();
        location6007.setX(0);
        location6007.setY(60);
        Node label6008 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel22EditPart.VISUAL_ID));
        label6008.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6008 = (Location) label6008.getLayoutConstraint();
        location6008.setX(0);
        location6008.setY(80);
        Node label6009 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel12EditPart.VISUAL_ID));
        label6009.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6009 = (Location) label6009.getLayoutConstraint();
        location6009.setX(0);
        location6009.setY(100);
        Node label6010 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel22EditPart.VISUAL_ID));
        label6010.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6010 = (Location) label6010.getLayoutConstraint();
        location6010.setX(0);
        location6010.setY(120);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createEdge_4003(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
        ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(GraphsVisualIDRegistry.getType(Edge3EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
        }
        Node label6011 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeMidLabel3EditPart.VISUAL_ID));
        label6011.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6011 = (Location) label6011.getLayoutConstraint();
        location6011.setX(0);
        location6011.setY(40);
        Node label6012 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel13EditPart.VISUAL_ID));
        label6012.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6012 = (Location) label6012.getLayoutConstraint();
        location6012.setX(0);
        location6012.setY(60);
        Node label6013 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel23EditPart.VISUAL_ID));
        label6013.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6013 = (Location) label6013.getLayoutConstraint();
        location6013.setX(0);
        location6013.setY(80);
        Node label6014 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel13EditPart.VISUAL_ID));
        label6014.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6014 = (Location) label6014.getLayoutConstraint();
        location6014.setX(0);
        location6014.setY(100);
        Node label6015 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel23EditPart.VISUAL_ID));
        label6015.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6015 = (Location) label6015.getLayoutConstraint();
        location6015.setX(0);
        location6015.setY(120);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createEdge_4004(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
        ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(GraphsVisualIDRegistry.getType(Edge4EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
        }
        Node label6016 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeMidLabel4EditPart.VISUAL_ID));
        label6016.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6016 = (Location) label6016.getLayoutConstraint();
        location6016.setX(0);
        location6016.setY(40);
        Node label6017 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel14EditPart.VISUAL_ID));
        label6017.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6017 = (Location) label6017.getLayoutConstraint();
        location6017.setX(0);
        location6017.setY(60);
        Node label6018 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel24EditPart.VISUAL_ID));
        label6018.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6018 = (Location) label6018.getLayoutConstraint();
        location6018.setX(0);
        location6018.setY(80);
        Node label6019 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel14EditPart.VISUAL_ID));
        label6019.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6019 = (Location) label6019.getLayoutConstraint();
        location6019.setX(0);
        location6019.setY(100);
        Node label6020 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel24EditPart.VISUAL_ID));
        label6020.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6020 = (Location) label6020.getLayoutConstraint();
        location6020.setX(0);
        location6020.setY(120);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createEdge_4005(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
        ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(GraphsVisualIDRegistry.getType(Edge5EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
        }
        Node label6021 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeMidLabel5EditPart.VISUAL_ID));
        label6021.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6021 = (Location) label6021.getLayoutConstraint();
        location6021.setX(0);
        location6021.setY(40);
        Node label6022 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel15EditPart.VISUAL_ID));
        label6022.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6022 = (Location) label6022.getLayoutConstraint();
        location6022.setX(0);
        location6022.setY(60);
        Node label6023 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel25EditPart.VISUAL_ID));
        label6023.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6023 = (Location) label6023.getLayoutConstraint();
        location6023.setX(0);
        location6023.setY(80);
        Node label6024 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel15EditPart.VISUAL_ID));
        label6024.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6024 = (Location) label6024.getLayoutConstraint();
        location6024.setX(0);
        location6024.setY(100);
        Node label6025 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel25EditPart.VISUAL_ID));
        label6025.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6025 = (Location) label6025.getLayoutConstraint();
        location6025.setX(0);
        location6025.setY(120);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createEdge_4006(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
        ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(GraphsVisualIDRegistry.getType(Edge6EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
        }
        Node label6026 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeMidLabel6EditPart.VISUAL_ID));
        label6026.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6026 = (Location) label6026.getLayoutConstraint();
        location6026.setX(0);
        location6026.setY(40);
        Node label6027 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel16EditPart.VISUAL_ID));
        label6027.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6027 = (Location) label6027.getLayoutConstraint();
        location6027.setX(0);
        location6027.setY(60);
        Node label6028 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel26EditPart.VISUAL_ID));
        label6028.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6028 = (Location) label6028.getLayoutConstraint();
        location6028.setX(0);
        location6028.setY(80);
        Node label6029 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel16EditPart.VISUAL_ID));
        label6029.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6029 = (Location) label6029.getLayoutConstraint();
        location6029.setX(0);
        location6029.setY(100);
        Node label6030 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel26EditPart.VISUAL_ID));
        label6030.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6030 = (Location) label6030.getLayoutConstraint();
        location6030.setX(0);
        location6030.setY(120);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createEdge_4007(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
        ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(GraphsVisualIDRegistry.getType(Edge7EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
        }
        Node label6031 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeMidLabel7EditPart.VISUAL_ID));
        label6031.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6031 = (Location) label6031.getLayoutConstraint();
        location6031.setX(0);
        location6031.setY(40);
        Node label6032 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel17EditPart.VISUAL_ID));
        label6032.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6032 = (Location) label6032.getLayoutConstraint();
        location6032.setX(0);
        location6032.setY(60);
        Node label6033 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel27EditPart.VISUAL_ID));
        label6033.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6033 = (Location) label6033.getLayoutConstraint();
        location6033.setX(0);
        location6033.setY(80);
        Node label6034 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel17EditPart.VISUAL_ID));
        label6034.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6034 = (Location) label6034.getLayoutConstraint();
        location6034.setX(0);
        location6034.setY(100);
        Node label6035 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel27EditPart.VISUAL_ID));
        label6035.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6035 = (Location) label6035.getLayoutConstraint();
        location6035.setX(0);
        location6035.setY(120);
        return edge;
    }

    /**
     * @generated
     */
    public Edge createEdge_4008(EObject domainElement, View containerView, int index, boolean persisted,
            PreferencesHint preferencesHint) {
        Edge edge = NotationFactory.eINSTANCE.createEdge();
        edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
        edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
        ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(2);
        points.add(new RelativeBendpoint());
        points.add(new RelativeBendpoint());
        bendpoints.setPoints(points);
        edge.setBendpoints(bendpoints);
        ViewUtil.insertChildView(containerView, edge, index, persisted);
        edge.setType(GraphsVisualIDRegistry.getType(Edge8EditPart.VISUAL_ID));
        edge.setElement(domainElement);
        // initializePreferences
        final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
        FontStyle edgeFontStyle = (FontStyle) edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (edgeFontStyle != null) {
            FontData fontData = PreferenceConverter.getFontData(prefStore,
                    IPreferenceConstants.PREF_DEFAULT_FONT);
            edgeFontStyle.setFontName(fontData.getName());
            edgeFontStyle.setFontHeight(fontData.getHeight());
            edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
            edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
            org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter.getColor(prefStore,
                    IPreferenceConstants.PREF_FONT_COLOR);
            edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB).intValue());
        }
        Routing routing = Routing.get(prefStore.getInt(IPreferenceConstants.PREF_LINE_STYLE));
        if (routing != null) {
            ViewUtil.setStructuralFeatureValue(edge,
                    NotationPackage.eINSTANCE.getRoutingStyle_Routing(), routing);
        }
        Node label6036 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeMidLabel8EditPart.VISUAL_ID));
        label6036.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6036 = (Location) label6036.getLayoutConstraint();
        location6036.setX(0);
        location6036.setY(40);
        Node label6037 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel18EditPart.VISUAL_ID));
        label6037.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6037 = (Location) label6037.getLayoutConstraint();
        location6037.setX(0);
        location6037.setY(60);
        Node label6038 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeHeadLabel28EditPart.VISUAL_ID));
        label6038.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6038 = (Location) label6038.getLayoutConstraint();
        location6038.setX(0);
        location6038.setY(80);
        Node label6039 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel18EditPart.VISUAL_ID));
        label6039.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6039 = (Location) label6039.getLayoutConstraint();
        location6039.setX(0);
        location6039.setY(100);
        Node label6040 = createLabel(edge,
                GraphsVisualIDRegistry.getType(EdgeTailLabel28EditPart.VISUAL_ID));
        label6040.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6040 = (Location) label6040.getLayoutConstraint();
        location6040.setX(0);
        location6040.setY(120);
        return edge;
    }

    /**
     * @generated
     */
    private void stampShortcut(View containerView, Node target) {
        if (!NodeEditPart.MODEL_ID.equals(GraphsVisualIDRegistry.getModelID(containerView))) {
            EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
            shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
            shortcutAnnotation.getDetails().put("modelID", NodeEditPart.MODEL_ID); //$NON-NLS-1$
            target.getEAnnotations().add(shortcutAnnotation);
        }
    }

    /**
     * @generated
     */
    private Node createLabel(View owner, String hint) {
        DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
        rv.setType(hint);
        ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
        return rv;
    }

    /**
     * @generated
     */
    private Node createCompartment(View owner, String hint, boolean canCollapse, boolean hasTitle,
            boolean canSort, boolean canFilter) {
        //SemanticListCompartment rv = NotationFactory.eINSTANCE.createSemanticListCompartment();
        //rv.setShowTitle(showTitle);
        //rv.setCollapsed(isCollapsed);
        Node rv;
        if (canCollapse) {
            rv = NotationFactory.eINSTANCE.createBasicCompartment();
        } else {
            rv = NotationFactory.eINSTANCE.createDecorationNode();
        }
        if (hasTitle) {
            TitleStyle ts = NotationFactory.eINSTANCE.createTitleStyle();
            ts.setShowTitle(true);
            rv.getStyles().add(ts);
        }
        if (canSort) {
            rv.getStyles().add(NotationFactory.eINSTANCE.createSortingStyle());
        }
        if (canFilter) {
            rv.getStyles().add(NotationFactory.eINSTANCE.createFilteringStyle());
        }
        rv.setType(hint);
        ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
        return rv;
    }

    /**
     * @generated
     */
    private EObject getSemanticElement(IAdaptable semanticAdapter) {
        if (semanticAdapter == null) {
            return null;
        }
        EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
        if (eObject != null) {
            return EMFCoreUtil.resolve(TransactionUtil.getEditingDomain(eObject), eObject);
        }
        return null;
    }

    /**
     * @generated
     */
    private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
        if (semanticAdapter == null) {
            return null;
        }
        return (IElementType) semanticAdapter.getAdapter(IElementType.class);
    }
}
