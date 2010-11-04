/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import net.ogdf.ogml.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OgmlFactoryImpl extends EFactoryImpl implements OgmlFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static OgmlFactory init() {
        try {
            OgmlFactory theOgmlFactory = (OgmlFactory)EPackage.Registry.INSTANCE.getEFactory("platform:/resource/net.ogdf.ogml/model/ogml.xsd"); 
            if (theOgmlFactory != null) {
                return theOgmlFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new OgmlFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OgmlFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case OgmlPackage.BOOLEAN_TYPE: return createBooleanType();
            case OgmlPackage.BOOL_TYPE: return createBoolType();
            case OgmlPackage.COMPOSED_TYPE: return createComposedType();
            case OgmlPackage.CONSTRAINTS_TYPE: return createConstraintsType();
            case OgmlPackage.DATA_TYPE: return createDataType();
            case OgmlPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case OgmlPackage.EDGE_CONSTRAINT_TYPE: return createEdgeConstraintType();
            case OgmlPackage.EDGE_LAYOUT_TYPE: return createEdgeLayoutType();
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE: return createEdgeStyleTemplateType();
            case OgmlPackage.EDGE_TYPE: return createEdgeType();
            case OgmlPackage.ENDPOINT_TYPE: return createEndpointType();
            case OgmlPackage.FILL_TYPE: return createFillType();
            case OgmlPackage.FONT_TYPE: return createFontType();
            case OgmlPackage.GRAPH_STYLE_TYPE: return createGraphStyleType();
            case OgmlPackage.GRAPH_TYPE: return createGraphType();
            case OgmlPackage.INT_TYPE: return createIntType();
            case OgmlPackage.KEYS_TYPE: return createKeysType();
            case OgmlPackage.KEY_TYPE: return createKeyType();
            case OgmlPackage.KEY_VALUE_TYPE: return createKeyValueType();
            case OgmlPackage.LABEL_CONSTRAINT_TYPE: return createLabelConstraintType();
            case OgmlPackage.LABEL_LAYOUT_TYPE: return createLabelLayoutType();
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE: return createLabelStyleTemplateType();
            case OgmlPackage.LABEL_TYPE: return createLabelType();
            case OgmlPackage.LAYOUT_TYPE: return createLayoutType();
            case OgmlPackage.LINE_STYLE_TYPE: return createLineStyleType();
            case OgmlPackage.LINE_TYPE: return createLineType();
            case OgmlPackage.LOCATION_TYPE: return createLocationType();
            case OgmlPackage.NODE_CONSTRAINT_TYPE: return createNodeConstraintType();
            case OgmlPackage.NODE_LAYOUT_TYPE: return createNodeLayoutType();
            case OgmlPackage.NODE_STYLE_TEMPLATE_TYPE: return createNodeStyleTemplateType();
            case OgmlPackage.NODE_TYPE: return createNodeType();
            case OgmlPackage.NUMBER_TYPE: return createNumberType();
            case OgmlPackage.OGML_TYPE: return createOgmlType();
            case OgmlPackage.POINT_TYPE: return createPointType();
            case OgmlPackage.SEGMENT_TYPE: return createSegmentType();
            case OgmlPackage.SHAPE_TYPE1: return createShapeType1();
            case OgmlPackage.SOURCE_STYLE_TYPE: return createSourceStyleType();
            case OgmlPackage.SOURCE_TARGET_TYPE: return createSourceTargetType();
            case OgmlPackage.STRUCTURE_TYPE: return createStructureType();
            case OgmlPackage.STYLES_TYPE: return createStylesType();
            case OgmlPackage.STYLE_TEMPLATES_TYPE: return createStyleTemplatesType();
            case OgmlPackage.TARGET_STYLE_TYPE: return createTargetStyleType();
            case OgmlPackage.TEMPLATE_TYPE: return createTemplateType();
            case OgmlPackage.TEXT_TYPE: return createTextType();
            case OgmlPackage.URI_TYPE: return createUriType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case OgmlPackage.ALIGNMENT_TYPE:
                return createAlignmentTypeFromString(eDataType, initialValue);
            case OgmlPackage.BOOL_INT_TYPE:
                return createBoolIntTypeFromString(eDataType, initialValue);
            case OgmlPackage.BOOL_TF_TYPE:
                return createBoolTFTypeFromString(eDataType, initialValue);
            case OgmlPackage.DECORATION_TYPE:
                return createDecorationTypeFromString(eDataType, initialValue);
            case OgmlPackage.ENDPOINT_STYLES_TYPE:
                return createEndpointStylesTypeFromString(eDataType, initialValue);
            case OgmlPackage.FONT_STRETCH_TYPE:
                return createFontStretchTypeFromString(eDataType, initialValue);
            case OgmlPackage.FONT_STYLE_TYPE:
                return createFontStyleTypeFromString(eDataType, initialValue);
            case OgmlPackage.FONT_VARIANT_TYPE:
                return createFontVariantTypeFromString(eDataType, initialValue);
            case OgmlPackage.FONT_WEIGHT_TYPE:
                return createFontWeightTypeFromString(eDataType, initialValue);
            case OgmlPackage.LINE_STYLE_TYPE_TYPE:
                return createLineStyleTypeTypeFromString(eDataType, initialValue);
            case OgmlPackage.PATTERN_TYPE:
                return createPatternTypeFromString(eDataType, initialValue);
            case OgmlPackage.SHAPE_TYPE:
                return createShapeTypeFromString(eDataType, initialValue);
            case OgmlPackage.TRANSFORM_TYPE:
                return createTransformTypeFromString(eDataType, initialValue);
            case OgmlPackage.ALIGNMENT_TYPE_OBJECT:
                return createAlignmentTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.BOOL_INT_TYPE_OBJECT:
                return createBoolIntTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.BOOL_TF_TYPE_OBJECT:
                return createBoolTFTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.DECORATION_TYPE_OBJECT:
                return createDecorationTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.ENDPOINT_STYLES_TYPE_OBJECT:
                return createEndpointStylesTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.FONT_STRETCH_TYPE_OBJECT:
                return createFontStretchTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.FONT_STYLE_TYPE_OBJECT:
                return createFontStyleTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.FONT_VARIANT_TYPE_OBJECT:
                return createFontVariantTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.FONT_WEIGHT_TYPE_OBJECT:
                return createFontWeightTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.LINE_STYLE_TYPE_TYPE_OBJECT:
                return createLineStyleTypeTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.PATTERN_TYPE_OBJECT:
                return createPatternTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.SHAPE_TYPE_OBJECT:
                return createShapeTypeObjectFromString(eDataType, initialValue);
            case OgmlPackage.TRANSFORM_TYPE_OBJECT:
                return createTransformTypeObjectFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case OgmlPackage.ALIGNMENT_TYPE:
                return convertAlignmentTypeToString(eDataType, instanceValue);
            case OgmlPackage.BOOL_INT_TYPE:
                return convertBoolIntTypeToString(eDataType, instanceValue);
            case OgmlPackage.BOOL_TF_TYPE:
                return convertBoolTFTypeToString(eDataType, instanceValue);
            case OgmlPackage.DECORATION_TYPE:
                return convertDecorationTypeToString(eDataType, instanceValue);
            case OgmlPackage.ENDPOINT_STYLES_TYPE:
                return convertEndpointStylesTypeToString(eDataType, instanceValue);
            case OgmlPackage.FONT_STRETCH_TYPE:
                return convertFontStretchTypeToString(eDataType, instanceValue);
            case OgmlPackage.FONT_STYLE_TYPE:
                return convertFontStyleTypeToString(eDataType, instanceValue);
            case OgmlPackage.FONT_VARIANT_TYPE:
                return convertFontVariantTypeToString(eDataType, instanceValue);
            case OgmlPackage.FONT_WEIGHT_TYPE:
                return convertFontWeightTypeToString(eDataType, instanceValue);
            case OgmlPackage.LINE_STYLE_TYPE_TYPE:
                return convertLineStyleTypeTypeToString(eDataType, instanceValue);
            case OgmlPackage.PATTERN_TYPE:
                return convertPatternTypeToString(eDataType, instanceValue);
            case OgmlPackage.SHAPE_TYPE:
                return convertShapeTypeToString(eDataType, instanceValue);
            case OgmlPackage.TRANSFORM_TYPE:
                return convertTransformTypeToString(eDataType, instanceValue);
            case OgmlPackage.ALIGNMENT_TYPE_OBJECT:
                return convertAlignmentTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.BOOL_INT_TYPE_OBJECT:
                return convertBoolIntTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.BOOL_TF_TYPE_OBJECT:
                return convertBoolTFTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.DECORATION_TYPE_OBJECT:
                return convertDecorationTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.ENDPOINT_STYLES_TYPE_OBJECT:
                return convertEndpointStylesTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.FONT_STRETCH_TYPE_OBJECT:
                return convertFontStretchTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.FONT_STYLE_TYPE_OBJECT:
                return convertFontStyleTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.FONT_VARIANT_TYPE_OBJECT:
                return convertFontVariantTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.FONT_WEIGHT_TYPE_OBJECT:
                return convertFontWeightTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.LINE_STYLE_TYPE_TYPE_OBJECT:
                return convertLineStyleTypeTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.PATTERN_TYPE_OBJECT:
                return convertPatternTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.SHAPE_TYPE_OBJECT:
                return convertShapeTypeObjectToString(eDataType, instanceValue);
            case OgmlPackage.TRANSFORM_TYPE_OBJECT:
                return convertTransformTypeObjectToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BooleanType createBooleanType() {
        BooleanTypeImpl booleanType = new BooleanTypeImpl();
        return booleanType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoolType createBoolType() {
        BoolTypeImpl boolType = new BoolTypeImpl();
        return boolType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComposedType createComposedType() {
        ComposedTypeImpl composedType = new ComposedTypeImpl();
        return composedType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConstraintsType createConstraintsType() {
        ConstraintsTypeImpl constraintsType = new ConstraintsTypeImpl();
        return constraintsType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataType createDataType() {
        DataTypeImpl dataType = new DataTypeImpl();
        return dataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeConstraintType createEdgeConstraintType() {
        EdgeConstraintTypeImpl edgeConstraintType = new EdgeConstraintTypeImpl();
        return edgeConstraintType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeLayoutType createEdgeLayoutType() {
        EdgeLayoutTypeImpl edgeLayoutType = new EdgeLayoutTypeImpl();
        return edgeLayoutType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeStyleTemplateType createEdgeStyleTemplateType() {
        EdgeStyleTemplateTypeImpl edgeStyleTemplateType = new EdgeStyleTemplateTypeImpl();
        return edgeStyleTemplateType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeType createEdgeType() {
        EdgeTypeImpl edgeType = new EdgeTypeImpl();
        return edgeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndpointType createEndpointType() {
        EndpointTypeImpl endpointType = new EndpointTypeImpl();
        return endpointType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FillType createFillType() {
        FillTypeImpl fillType = new FillTypeImpl();
        return fillType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontType createFontType() {
        FontTypeImpl fontType = new FontTypeImpl();
        return fontType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphStyleType createGraphStyleType() {
        GraphStyleTypeImpl graphStyleType = new GraphStyleTypeImpl();
        return graphStyleType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphType createGraphType() {
        GraphTypeImpl graphType = new GraphTypeImpl();
        return graphType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntType createIntType() {
        IntTypeImpl intType = new IntTypeImpl();
        return intType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeysType createKeysType() {
        KeysTypeImpl keysType = new KeysTypeImpl();
        return keysType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyType createKeyType() {
        KeyTypeImpl keyType = new KeyTypeImpl();
        return keyType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyValueType createKeyValueType() {
        KeyValueTypeImpl keyValueType = new KeyValueTypeImpl();
        return keyValueType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelConstraintType createLabelConstraintType() {
        LabelConstraintTypeImpl labelConstraintType = new LabelConstraintTypeImpl();
        return labelConstraintType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelLayoutType createLabelLayoutType() {
        LabelLayoutTypeImpl labelLayoutType = new LabelLayoutTypeImpl();
        return labelLayoutType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelStyleTemplateType createLabelStyleTemplateType() {
        LabelStyleTemplateTypeImpl labelStyleTemplateType = new LabelStyleTemplateTypeImpl();
        return labelStyleTemplateType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelType createLabelType() {
        LabelTypeImpl labelType = new LabelTypeImpl();
        return labelType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutType createLayoutType() {
        LayoutTypeImpl layoutType = new LayoutTypeImpl();
        return layoutType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineStyleType createLineStyleType() {
        LineStyleTypeImpl lineStyleType = new LineStyleTypeImpl();
        return lineStyleType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineType createLineType() {
        LineTypeImpl lineType = new LineTypeImpl();
        return lineType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LocationType createLocationType() {
        LocationTypeImpl locationType = new LocationTypeImpl();
        return locationType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeConstraintType createNodeConstraintType() {
        NodeConstraintTypeImpl nodeConstraintType = new NodeConstraintTypeImpl();
        return nodeConstraintType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeLayoutType createNodeLayoutType() {
        NodeLayoutTypeImpl nodeLayoutType = new NodeLayoutTypeImpl();
        return nodeLayoutType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeStyleTemplateType createNodeStyleTemplateType() {
        NodeStyleTemplateTypeImpl nodeStyleTemplateType = new NodeStyleTemplateTypeImpl();
        return nodeStyleTemplateType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeType createNodeType() {
        NodeTypeImpl nodeType = new NodeTypeImpl();
        return nodeType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberType createNumberType() {
        NumberTypeImpl numberType = new NumberTypeImpl();
        return numberType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OgmlType createOgmlType() {
        OgmlTypeImpl ogmlType = new OgmlTypeImpl();
        return ogmlType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PointType createPointType() {
        PointTypeImpl pointType = new PointTypeImpl();
        return pointType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SegmentType createSegmentType() {
        SegmentTypeImpl segmentType = new SegmentTypeImpl();
        return segmentType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeType1 createShapeType1() {
        ShapeType1Impl shapeType1 = new ShapeType1Impl();
        return shapeType1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceStyleType createSourceStyleType() {
        SourceStyleTypeImpl sourceStyleType = new SourceStyleTypeImpl();
        return sourceStyleType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceTargetType createSourceTargetType() {
        SourceTargetTypeImpl sourceTargetType = new SourceTargetTypeImpl();
        return sourceTargetType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StructureType createStructureType() {
        StructureTypeImpl structureType = new StructureTypeImpl();
        return structureType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StylesType createStylesType() {
        StylesTypeImpl stylesType = new StylesTypeImpl();
        return stylesType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StyleTemplatesType createStyleTemplatesType() {
        StyleTemplatesTypeImpl styleTemplatesType = new StyleTemplatesTypeImpl();
        return styleTemplatesType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TargetStyleType createTargetStyleType() {
        TargetStyleTypeImpl targetStyleType = new TargetStyleTypeImpl();
        return targetStyleType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TemplateType createTemplateType() {
        TemplateTypeImpl templateType = new TemplateTypeImpl();
        return templateType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextType createTextType() {
        TextTypeImpl textType = new TextTypeImpl();
        return textType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UriType createUriType() {
        UriTypeImpl uriType = new UriTypeImpl();
        return uriType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AlignmentType createAlignmentTypeFromString(EDataType eDataType, String initialValue) {
        AlignmentType result = AlignmentType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertAlignmentTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoolIntType createBoolIntTypeFromString(EDataType eDataType, String initialValue) {
        BoolIntType result = BoolIntType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertBoolIntTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoolTFType createBoolTFTypeFromString(EDataType eDataType, String initialValue) {
        BoolTFType result = BoolTFType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertBoolTFTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DecorationType createDecorationTypeFromString(EDataType eDataType, String initialValue) {
        DecorationType result = DecorationType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertDecorationTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndpointStylesType createEndpointStylesTypeFromString(EDataType eDataType, String initialValue) {
        EndpointStylesType result = EndpointStylesType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertEndpointStylesTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontStretchType createFontStretchTypeFromString(EDataType eDataType, String initialValue) {
        FontStretchType result = FontStretchType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFontStretchTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontStyleType createFontStyleTypeFromString(EDataType eDataType, String initialValue) {
        FontStyleType result = FontStyleType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFontStyleTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontVariantType createFontVariantTypeFromString(EDataType eDataType, String initialValue) {
        FontVariantType result = FontVariantType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFontVariantTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontWeightType createFontWeightTypeFromString(EDataType eDataType, String initialValue) {
        FontWeightType result = FontWeightType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFontWeightTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineStyleTypeType createLineStyleTypeTypeFromString(EDataType eDataType, String initialValue) {
        LineStyleTypeType result = LineStyleTypeType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertLineStyleTypeTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternType createPatternTypeFromString(EDataType eDataType, String initialValue) {
        PatternType result = PatternType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertPatternTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeType createShapeTypeFromString(EDataType eDataType, String initialValue) {
        ShapeType result = ShapeType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertShapeTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransformType createTransformTypeFromString(EDataType eDataType, String initialValue) {
        TransformType result = TransformType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTransformTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AlignmentType createAlignmentTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createAlignmentTypeFromString(OgmlPackage.Literals.ALIGNMENT_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertAlignmentTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertAlignmentTypeToString(OgmlPackage.Literals.ALIGNMENT_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoolIntType createBoolIntTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createBoolIntTypeFromString(OgmlPackage.Literals.BOOL_INT_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertBoolIntTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertBoolIntTypeToString(OgmlPackage.Literals.BOOL_INT_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoolTFType createBoolTFTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createBoolTFTypeFromString(OgmlPackage.Literals.BOOL_TF_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertBoolTFTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertBoolTFTypeToString(OgmlPackage.Literals.BOOL_TF_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DecorationType createDecorationTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createDecorationTypeFromString(OgmlPackage.Literals.DECORATION_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertDecorationTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertDecorationTypeToString(OgmlPackage.Literals.DECORATION_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndpointStylesType createEndpointStylesTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createEndpointStylesTypeFromString(OgmlPackage.Literals.ENDPOINT_STYLES_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertEndpointStylesTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertEndpointStylesTypeToString(OgmlPackage.Literals.ENDPOINT_STYLES_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontStretchType createFontStretchTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createFontStretchTypeFromString(OgmlPackage.Literals.FONT_STRETCH_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFontStretchTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertFontStretchTypeToString(OgmlPackage.Literals.FONT_STRETCH_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontStyleType createFontStyleTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createFontStyleTypeFromString(OgmlPackage.Literals.FONT_STYLE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFontStyleTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertFontStyleTypeToString(OgmlPackage.Literals.FONT_STYLE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontVariantType createFontVariantTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createFontVariantTypeFromString(OgmlPackage.Literals.FONT_VARIANT_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFontVariantTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertFontVariantTypeToString(OgmlPackage.Literals.FONT_VARIANT_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontWeightType createFontWeightTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createFontWeightTypeFromString(OgmlPackage.Literals.FONT_WEIGHT_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFontWeightTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertFontWeightTypeToString(OgmlPackage.Literals.FONT_WEIGHT_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineStyleTypeType createLineStyleTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createLineStyleTypeTypeFromString(OgmlPackage.Literals.LINE_STYLE_TYPE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertLineStyleTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertLineStyleTypeTypeToString(OgmlPackage.Literals.LINE_STYLE_TYPE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternType createPatternTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createPatternTypeFromString(OgmlPackage.Literals.PATTERN_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertPatternTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertPatternTypeToString(OgmlPackage.Literals.PATTERN_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeType createShapeTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createShapeTypeFromString(OgmlPackage.Literals.SHAPE_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertShapeTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertShapeTypeToString(OgmlPackage.Literals.SHAPE_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransformType createTransformTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createTransformTypeFromString(OgmlPackage.Literals.TRANSFORM_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTransformTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertTransformTypeToString(OgmlPackage.Literals.TRANSFORM_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OgmlPackage getOgmlPackage() {
        return (OgmlPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static OgmlPackage getPackage() {
        return OgmlPackage.eINSTANCE;
    }

} //OgmlFactoryImpl
