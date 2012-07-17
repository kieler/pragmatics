package de.cau.cs.kieler.kaom.diagram.providers;

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

import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Entity3EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartment2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityEntityCompartmentEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.EntityNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.LinkNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.PortNameEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.Relation2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationName2EditPart;
import de.cau.cs.kieler.kaom.diagram.edit.parts.RelationNameEditPart;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;

/**
 * @generated
 */
public class KaomViewProvider extends AbstractProvider implements IViewProvider {

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
        return EntityEditPart.MODEL_ID.equals(op.getSemanticHint())
                && KaomVisualIDRegistry.getDiagramVisualID(getSemanticElement(op
                        .getSemanticAdapter())) != -1;
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
            visualID = KaomVisualIDRegistry.getNodeVisualID(op.getContainerView(), domainElement);
        } else {
            visualID = KaomVisualIDRegistry.getVisualID(op.getSemanticHint());
            if (elementType != null) {
                if (!KaomElementTypes.isKnownElementType(elementType)
                        || (!(elementType instanceof IHintedType))) {
                    return false; // foreign element type
                }
                String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
                if (!op.getSemanticHint().equals(elementTypeHint)) {
                    return false; // if semantic hint is specified it should be the same as in element type
                }
                if (domainElement != null
                        && visualID != KaomVisualIDRegistry.getNodeVisualID(op.getContainerView(),
                                domainElement)) {
                    return false; // visual id for node EClass should match visual id from element type
                }
            } else {
                if (!EntityEditPart.MODEL_ID.equals(KaomVisualIDRegistry.getModelID(op
                        .getContainerView()))) {
                    return false; // foreign diagram
                }
                switch (visualID) {
                case PortEditPart.VISUAL_ID:
                case Relation2EditPart.VISUAL_ID:
                case Entity2EditPart.VISUAL_ID:
                case RelationEditPart.VISUAL_ID:
                case Entity3EditPart.VISUAL_ID:
                    if (domainElement == null
                            || visualID != KaomVisualIDRegistry.getNodeVisualID(
                                    op.getContainerView(), domainElement)) {
                        return false; // visual id in semantic hint should match visual id for domain element
                    }
                    break;
                default:
                    return false;
                }
            }
        }
        return Entity2EditPart.VISUAL_ID == visualID || RelationEditPart.VISUAL_ID == visualID
                || PortEditPart.VISUAL_ID == visualID || Entity3EditPart.VISUAL_ID == visualID
                || Relation2EditPart.VISUAL_ID == visualID;
    }

    /**
     * @generated
     */
    protected boolean provides(CreateEdgeViewOperation op) {
        IElementType elementType = getSemanticElementType(op.getSemanticAdapter());
        if (!KaomElementTypes.isKnownElementType(elementType)
                || (!(elementType instanceof IHintedType))) {
            return false; // foreign element type
        }
        String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
        if (elementTypeHint == null
                || (op.getSemanticHint() != null && !elementTypeHint.equals(op.getSemanticHint()))) {
            return false; // our hint is visual id and must be specified, and it should be the same as in element type
        }
        int visualID = KaomVisualIDRegistry.getVisualID(elementTypeHint);
        EObject domainElement = getSemanticElement(op.getSemanticAdapter());
        if (domainElement != null
                && visualID != KaomVisualIDRegistry.getLinkWithClassVisualID(domainElement)) {
            return false; // visual id for link EClass should match visual id from element type
        }
        return true;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    public Diagram createDiagram(IAdaptable semanticAdapter, String diagramKind,
            PreferencesHint preferencesHint) {
        Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
        diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
        diagram.setType(EntityEditPart.MODEL_ID);
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
            visualID = KaomVisualIDRegistry.getNodeVisualID(containerView, domainElement);
        } else {
            visualID = KaomVisualIDRegistry.getVisualID(semanticHint);
        }
        switch (visualID) {
        case Entity2EditPart.VISUAL_ID:
            return createEntity_2001(domainElement, containerView, index, persisted,
                    preferencesHint);
        case RelationEditPart.VISUAL_ID:
            return createRelation_2002(domainElement, containerView, index, persisted,
                    preferencesHint);
        case PortEditPart.VISUAL_ID:
            return createPort_3001(domainElement, containerView, index, persisted, preferencesHint);
        case Entity3EditPart.VISUAL_ID:
            return createEntity_3002(domainElement, containerView, index, persisted,
                    preferencesHint);
        case Relation2EditPart.VISUAL_ID:
            return createRelation_3003(domainElement, containerView, index, persisted,
                    preferencesHint);
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
        switch (KaomVisualIDRegistry.getVisualID(elementTypeHint)) {
        case LinkEditPart.VISUAL_ID:
            return createLink_4001(getSemanticElement(semanticAdapter), containerView, index,
                    persisted, preferencesHint);
        }
        // can never happen, provided #provides(CreateEdgeViewOperation) is correct
        return null;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    public Node createEntity_2001(EObject domainElement, View containerView, int index,
            boolean persisted, PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(KaomVisualIDRegistry.getType(Entity2EditPart.VISUAL_ID));
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
        Node label5004 = createLabel(node,
                KaomVisualIDRegistry.getType(EntityNameEditPart.VISUAL_ID));
        label5004.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location5004 = (Location) label5004.getLayoutConstraint();
        location5004.setX(0);
        location5004.setY(5);
        createCompartment(node,
                KaomVisualIDRegistry.getType(EntityEntityCompartmentEditPart.VISUAL_ID), true,
                false, false, false);
        return node;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    public Node createRelation_2002(EObject domainElement, View containerView, int index,
            boolean persisted, PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(KaomVisualIDRegistry.getType(RelationEditPart.VISUAL_ID));
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
        Node label5005 = createLabel(node,
                KaomVisualIDRegistry.getType(RelationNameEditPart.VISUAL_ID));
        label5005.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location5005 = (Location) label5005.getLayoutConstraint();
        location5005.setX(0);
        location5005.setY(5);
        return node;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    public Node createPort_3001(EObject domainElement, View containerView, int index,
            boolean persisted, PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(KaomVisualIDRegistry.getType(PortEditPart.VISUAL_ID));
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
        Node label5001 = createLabel(node, KaomVisualIDRegistry.getType(PortNameEditPart.VISUAL_ID));
        label5001.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location5001 = (Location) label5001.getLayoutConstraint();
        location5001.setX(0);
        location5001.setY(5);
        return node;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    public Node createEntity_3002(EObject domainElement, View containerView, int index,
            boolean persisted, PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(KaomVisualIDRegistry.getType(Entity3EditPart.VISUAL_ID));
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
        Node label5003 = createLabel(node,
                KaomVisualIDRegistry.getType(EntityName2EditPart.VISUAL_ID));
        label5003.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location5003 = (Location) label5003.getLayoutConstraint();
        location5003.setX(0);
        location5003.setY(5);
        createCompartment(node,
                KaomVisualIDRegistry.getType(EntityEntityCompartment2EditPart.VISUAL_ID), true,
                false, false, false);
        return node;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    public Node createRelation_3003(EObject domainElement, View containerView, int index,
            boolean persisted, PreferencesHint preferencesHint) {
        Node node = NotationFactory.eINSTANCE.createNode();
        node.getStyles().add(NotationFactory.eINSTANCE.createDescriptionStyle());
        node.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
        node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
        node.setType(KaomVisualIDRegistry.getType(Relation2EditPart.VISUAL_ID));
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
                KaomVisualIDRegistry.getType(RelationName2EditPart.VISUAL_ID));
        label5002.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location5002 = (Location) label5002.getLayoutConstraint();
        location5002.setX(0);
        location5002.setY(5);
        return node;
    }

    /**
     * @generated
     */
    @SuppressWarnings("unchecked")
    public Edge createLink_4001(EObject domainElement, View containerView, int index,
            boolean persisted, PreferencesHint preferencesHint) {
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
        edge.setType(KaomVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
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
        Node label6001 = createLabel(edge, KaomVisualIDRegistry.getType(LinkNameEditPart.VISUAL_ID));
        label6001.setLayoutConstraint(NotationFactory.eINSTANCE.createLocation());
        Location location6001 = (Location) label6001.getLayoutConstraint();
        location6001.setX(0);
        location6001.setY(40);
        return edge;
    }

    /**
     * @generated
     */
    private void stampShortcut(View containerView, Node target) {
        if (!EntityEditPart.MODEL_ID.equals(KaomVisualIDRegistry.getModelID(containerView))) {
            EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
            shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
            shortcutAnnotation.getDetails().put("modelID", EntityEditPart.MODEL_ID); //$NON-NLS-1$
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
    @SuppressWarnings("unchecked")
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
