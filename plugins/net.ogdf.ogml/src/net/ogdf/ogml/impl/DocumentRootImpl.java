/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import net.ogdf.ogml.BoolType;
import net.ogdf.ogml.BooleanType;
import net.ogdf.ogml.ComposedType;
import net.ogdf.ogml.ConstraintsType;
import net.ogdf.ogml.DataType;
import net.ogdf.ogml.DocumentRoot;
import net.ogdf.ogml.EdgeConstraintType;
import net.ogdf.ogml.EdgeLayoutType;
import net.ogdf.ogml.EdgeStyleTemplateType;
import net.ogdf.ogml.EdgeType;
import net.ogdf.ogml.EndpointType;
import net.ogdf.ogml.FillType;
import net.ogdf.ogml.FontStretchType;
import net.ogdf.ogml.FontStyleType;
import net.ogdf.ogml.FontType;
import net.ogdf.ogml.FontVariantType;
import net.ogdf.ogml.FontWeightType;
import net.ogdf.ogml.GraphStyleType;
import net.ogdf.ogml.GraphType;
import net.ogdf.ogml.IntType;
import net.ogdf.ogml.KeyType;
import net.ogdf.ogml.KeyValueType;
import net.ogdf.ogml.KeysType;
import net.ogdf.ogml.LabelConstraintType;
import net.ogdf.ogml.LabelLayoutType;
import net.ogdf.ogml.LabelStyleTemplateType;
import net.ogdf.ogml.LabelType;
import net.ogdf.ogml.LayoutType;
import net.ogdf.ogml.LineStyleType;
import net.ogdf.ogml.LineStyleTypeType;
import net.ogdf.ogml.LineType;
import net.ogdf.ogml.LocationType;
import net.ogdf.ogml.NodeConstraintType;
import net.ogdf.ogml.NodeLayoutType;
import net.ogdf.ogml.NodeStyleTemplateType;
import net.ogdf.ogml.NodeType;
import net.ogdf.ogml.NumberType;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.OgmlType;
import net.ogdf.ogml.PatternType;
import net.ogdf.ogml.PointType;
import net.ogdf.ogml.SegmentType;
import net.ogdf.ogml.ShapeType1;
import net.ogdf.ogml.SourceStyleType;
import net.ogdf.ogml.SourceTargetType;
import net.ogdf.ogml.StructureType;
import net.ogdf.ogml.StyleTemplatesType;
import net.ogdf.ogml.StylesType;
import net.ogdf.ogml.TargetStyleType;
import net.ogdf.ogml.TemplateType;
import net.ogdf.ogml.TextType;
import net.ogdf.ogml.UriType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getBool <em>Bool</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getComposed <em>Composed</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getEdge <em>Edge</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getEdgeConstraint <em>Edge Constraint</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getEdgeLayout <em>Edge Layout</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getEdgeStyleTemplate <em>Edge Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getFill <em>Fill</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getFont <em>Font</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getFontStretch <em>Font Stretch</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getFontStyle <em>Font Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getFontVariant <em>Font Variant</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getFontWeight <em>Font Weight</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getGraph <em>Graph</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getGraphStyle <em>Graph Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getInt <em>Int</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getKey <em>Key</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getKeys <em>Keys</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getKeyValue <em>Key Value</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLabelConstraint <em>Label Constraint</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLabelLayout <em>Label Layout</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLabelStyleTemplate <em>Label Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLayout <em>Layout</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLine <em>Line</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLineStyleType <em>Line Style Type</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getNode <em>Node</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getNodeConstraint <em>Node Constraint</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getNodeLayout <em>Node Layout</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getNodeStyleTemplate <em>Node Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getOgml <em>Ogml</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getPattern <em>Pattern</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getPoint <em>Point</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getSegment <em>Segment</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getShape <em>Shape</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getSourceStyle <em>Source Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getSourceTarget <em>Source Target</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getStructure <em>Structure</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getStyles <em>Styles</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getStyleTemplates <em>Style Templates</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getTargetStyle <em>Target Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getTemplate <em>Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getText <em>Text</em>}</li>
 *   <li>{@link net.ogdf.ogml.impl.DocumentRootImpl#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
    /**
     * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMixed()
     * @generated
     * @ordered
     */
    protected FeatureMap mixed;

    /**
     * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXMLNSPrefixMap()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xMLNSPrefixMap;

    /**
     * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXSISchemaLocation()
     * @generated
     * @ordered
     */
    protected EMap<String, String> xSISchemaLocation;

    /**
     * The default value of the '{@link #getFontStretch() <em>Font Stretch</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFontStretch()
     * @generated
     * @ordered
     */
    protected static final FontStretchType FONT_STRETCH_EDEFAULT = FontStretchType.NORMAL;

    /**
     * The default value of the '{@link #getFontStyle() <em>Font Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFontStyle()
     * @generated
     * @ordered
     */
    protected static final FontStyleType FONT_STYLE_EDEFAULT = FontStyleType.NORMAL;

    /**
     * The default value of the '{@link #getFontVariant() <em>Font Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFontVariant()
     * @generated
     * @ordered
     */
    protected static final FontVariantType FONT_VARIANT_EDEFAULT = FontVariantType.NORMAL;

    /**
     * The default value of the '{@link #getFontWeight() <em>Font Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFontWeight()
     * @generated
     * @ordered
     */
    protected static final FontWeightType FONT_WEIGHT_EDEFAULT = FontWeightType.NORMAL;

    /**
     * The default value of the '{@link #getLineStyleType() <em>Line Style Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineStyleType()
     * @generated
     * @ordered
     */
    protected static final LineStyleTypeType LINE_STYLE_TYPE_EDEFAULT = LineStyleTypeType.SOLID;

    /**
     * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPattern()
     * @generated
     * @ordered
     */
    protected static final PatternType PATTERN_EDEFAULT = PatternType.SOLID;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DocumentRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return OgmlPackage.Literals.DOCUMENT_ROOT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FeatureMap getMixed() {
        if (mixed == null) {
            mixed = new BasicFeatureMap(this, OgmlPackage.DOCUMENT_ROOT__MIXED);
        }
        return mixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXMLNSPrefixMap() {
        if (xMLNSPrefixMap == null) {
            xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, OgmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        }
        return xMLNSPrefixMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, String> getXSISchemaLocation() {
        if (xSISchemaLocation == null) {
            xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, OgmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        }
        return xSISchemaLocation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoolType getBool() {
        return (BoolType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__BOOL, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBool(BoolType newBool, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__BOOL, newBool, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBool(BoolType newBool) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__BOOL, newBool);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BooleanType getBoolean() {
        return (BooleanType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__BOOLEAN, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBoolean(BooleanType newBoolean, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__BOOLEAN, newBoolean, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBoolean(BooleanType newBoolean) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__BOOLEAN, newBoolean);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComposedType getComposed() {
        return (ComposedType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__COMPOSED, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetComposed(ComposedType newComposed, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__COMPOSED, newComposed, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComposed(ComposedType newComposed) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__COMPOSED, newComposed);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConstraintsType getConstraints() {
        return (ConstraintsType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__CONSTRAINTS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConstraints(ConstraintsType newConstraints, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__CONSTRAINTS, newConstraints, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConstraints(ConstraintsType newConstraints) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__CONSTRAINTS, newConstraints);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DataType getData() {
        return (DataType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__DATA, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetData(DataType newData, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__DATA, newData, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setData(DataType newData) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__DATA, newData);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeType getEdge() {
        return (EdgeType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEdge(EdgeType newEdge, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE, newEdge, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEdge(EdgeType newEdge) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE, newEdge);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeConstraintType getEdgeConstraint() {
        return (EdgeConstraintType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_CONSTRAINT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEdgeConstraint(EdgeConstraintType newEdgeConstraint, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_CONSTRAINT, newEdgeConstraint, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEdgeConstraint(EdgeConstraintType newEdgeConstraint) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_CONSTRAINT, newEdgeConstraint);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeLayoutType getEdgeLayout() {
        return (EdgeLayoutType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_LAYOUT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEdgeLayout(EdgeLayoutType newEdgeLayout, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_LAYOUT, newEdgeLayout, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEdgeLayout(EdgeLayoutType newEdgeLayout) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_LAYOUT, newEdgeLayout);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EdgeStyleTemplateType getEdgeStyleTemplate() {
        return (EdgeStyleTemplateType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEdgeStyleTemplate(EdgeStyleTemplateType newEdgeStyleTemplate, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE, newEdgeStyleTemplate, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEdgeStyleTemplate(EdgeStyleTemplateType newEdgeStyleTemplate) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE, newEdgeStyleTemplate);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndpointType getEndpoint() {
        return (EndpointType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__ENDPOINT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEndpoint(EndpointType newEndpoint, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__ENDPOINT, newEndpoint, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEndpoint(EndpointType newEndpoint) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__ENDPOINT, newEndpoint);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FillType getFill() {
        return (FillType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__FILL, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFill(FillType newFill, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__FILL, newFill, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFill(FillType newFill) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__FILL, newFill);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontType getFont() {
        return (FontType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__FONT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFont(FontType newFont, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__FONT, newFont, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFont(FontType newFont) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__FONT, newFont);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontStretchType getFontStretch() {
        return (FontStretchType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__FONT_STRETCH, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFontStretch(FontStretchType newFontStretch) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__FONT_STRETCH, newFontStretch);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontStyleType getFontStyle() {
        return (FontStyleType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__FONT_STYLE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFontStyle(FontStyleType newFontStyle) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__FONT_STYLE, newFontStyle);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontVariantType getFontVariant() {
        return (FontVariantType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__FONT_VARIANT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFontVariant(FontVariantType newFontVariant) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__FONT_VARIANT, newFontVariant);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FontWeightType getFontWeight() {
        return (FontWeightType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__FONT_WEIGHT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFontWeight(FontWeightType newFontWeight) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__FONT_WEIGHT, newFontWeight);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphType getGraph() {
        return (GraphType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__GRAPH, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGraph(GraphType newGraph, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__GRAPH, newGraph, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGraph(GraphType newGraph) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__GRAPH, newGraph);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphStyleType getGraphStyle() {
        return (GraphStyleType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__GRAPH_STYLE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGraphStyle(GraphStyleType newGraphStyle, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__GRAPH_STYLE, newGraphStyle, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGraphStyle(GraphStyleType newGraphStyle) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__GRAPH_STYLE, newGraphStyle);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntType getInt() {
        return (IntType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__INT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInt(IntType newInt, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__INT, newInt, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setInt(IntType newInt) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__INT, newInt);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyType getKey() {
        return (KeyType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__KEY, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKey(KeyType newKey, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__KEY, newKey, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKey(KeyType newKey) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__KEY, newKey);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeysType getKeys() {
        return (KeysType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__KEYS, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKeys(KeysType newKeys, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__KEYS, newKeys, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKeys(KeysType newKeys) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__KEYS, newKeys);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KeyValueType getKeyValue() {
        return (KeyValueType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__KEY_VALUE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetKeyValue(KeyValueType newKeyValue, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__KEY_VALUE, newKeyValue, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKeyValue(KeyValueType newKeyValue) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__KEY_VALUE, newKeyValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelType getLabel() {
        return (LabelType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLabel(LabelType newLabel, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL, newLabel, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(LabelType newLabel) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL, newLabel);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelConstraintType getLabelConstraint() {
        return (LabelConstraintType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_CONSTRAINT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLabelConstraint(LabelConstraintType newLabelConstraint, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_CONSTRAINT, newLabelConstraint, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabelConstraint(LabelConstraintType newLabelConstraint) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_CONSTRAINT, newLabelConstraint);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelLayoutType getLabelLayout() {
        return (LabelLayoutType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_LAYOUT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLabelLayout(LabelLayoutType newLabelLayout, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_LAYOUT, newLabelLayout, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabelLayout(LabelLayoutType newLabelLayout) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_LAYOUT, newLabelLayout);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LabelStyleTemplateType getLabelStyleTemplate() {
        return (LabelStyleTemplateType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLabelStyleTemplate(LabelStyleTemplateType newLabelStyleTemplate, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE, newLabelStyleTemplate, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabelStyleTemplate(LabelStyleTemplateType newLabelStyleTemplate) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE, newLabelStyleTemplate);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LayoutType getLayout() {
        return (LayoutType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LAYOUT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLayout(LayoutType newLayout, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__LAYOUT, newLayout, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLayout(LayoutType newLayout) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LAYOUT, newLayout);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineType getLine() {
        return (LineType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LINE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLine(LineType newLine, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__LINE, newLine, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLine(LineType newLine) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LINE, newLine);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineStyleType getLineStyle() {
        return (LineStyleType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LINE_STYLE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLineStyle(LineStyleType newLineStyle, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__LINE_STYLE, newLineStyle, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLineStyle(LineStyleType newLineStyle) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LINE_STYLE, newLineStyle);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LineStyleTypeType getLineStyleType() {
        return (LineStyleTypeType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LINE_STYLE_TYPE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLineStyleType(LineStyleTypeType newLineStyleType) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LINE_STYLE_TYPE, newLineStyleType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LocationType getLocation() {
        return (LocationType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__LOCATION, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLocation(LocationType newLocation, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__LOCATION, newLocation, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLocation(LocationType newLocation) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__LOCATION, newLocation);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeType getNode() {
        return (NodeType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__NODE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNode(NodeType newNode, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__NODE, newNode, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNode(NodeType newNode) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__NODE, newNode);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeConstraintType getNodeConstraint() {
        return (NodeConstraintType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_CONSTRAINT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNodeConstraint(NodeConstraintType newNodeConstraint, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_CONSTRAINT, newNodeConstraint, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNodeConstraint(NodeConstraintType newNodeConstraint) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_CONSTRAINT, newNodeConstraint);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeLayoutType getNodeLayout() {
        return (NodeLayoutType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_LAYOUT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNodeLayout(NodeLayoutType newNodeLayout, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_LAYOUT, newNodeLayout, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNodeLayout(NodeLayoutType newNodeLayout) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_LAYOUT, newNodeLayout);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeStyleTemplateType getNodeStyleTemplate() {
        return (NodeStyleTemplateType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_STYLE_TEMPLATE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNodeStyleTemplate(NodeStyleTemplateType newNodeStyleTemplate, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_STYLE_TEMPLATE, newNodeStyleTemplate, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNodeStyleTemplate(NodeStyleTemplateType newNodeStyleTemplate) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__NODE_STYLE_TEMPLATE, newNodeStyleTemplate);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberType getNumber() {
        return (NumberType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__NUMBER, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNumber(NumberType newNumber, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__NUMBER, newNumber, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNumber(NumberType newNumber) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__NUMBER, newNumber);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OgmlType getOgml() {
        return (OgmlType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__OGML, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetOgml(OgmlType newOgml, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__OGML, newOgml, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOgml(OgmlType newOgml) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__OGML, newOgml);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PatternType getPattern() {
        return (PatternType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__PATTERN, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPattern(PatternType newPattern) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__PATTERN, newPattern);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PointType getPoint() {
        return (PointType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__POINT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPoint(PointType newPoint, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__POINT, newPoint, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPoint(PointType newPoint) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__POINT, newPoint);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SegmentType getSegment() {
        return (SegmentType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__SEGMENT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSegment(SegmentType newSegment, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__SEGMENT, newSegment, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSegment(SegmentType newSegment) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__SEGMENT, newSegment);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ShapeType1 getShape() {
        return (ShapeType1)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__SHAPE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetShape(ShapeType1 newShape, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__SHAPE, newShape, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setShape(ShapeType1 newShape) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__SHAPE, newShape);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceStyleType getSourceStyle() {
        return (SourceStyleType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__SOURCE_STYLE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSourceStyle(SourceStyleType newSourceStyle, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__SOURCE_STYLE, newSourceStyle, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceStyle(SourceStyleType newSourceStyle) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__SOURCE_STYLE, newSourceStyle);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceTargetType getSourceTarget() {
        return (SourceTargetType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__SOURCE_TARGET, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSourceTarget(SourceTargetType newSourceTarget, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__SOURCE_TARGET, newSourceTarget, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceTarget(SourceTargetType newSourceTarget) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__SOURCE_TARGET, newSourceTarget);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StructureType getStructure() {
        return (StructureType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__STRUCTURE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStructure(StructureType newStructure, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__STRUCTURE, newStructure, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStructure(StructureType newStructure) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__STRUCTURE, newStructure);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StylesType getStyles() {
        return (StylesType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__STYLES, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStyles(StylesType newStyles, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__STYLES, newStyles, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyles(StylesType newStyles) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__STYLES, newStyles);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StyleTemplatesType getStyleTemplates() {
        return (StyleTemplatesType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__STYLE_TEMPLATES, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetStyleTemplates(StyleTemplatesType newStyleTemplates, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__STYLE_TEMPLATES, newStyleTemplates, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyleTemplates(StyleTemplatesType newStyleTemplates) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__STYLE_TEMPLATES, newStyleTemplates);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TargetStyleType getTargetStyle() {
        return (TargetStyleType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__TARGET_STYLE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTargetStyle(TargetStyleType newTargetStyle, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__TARGET_STYLE, newTargetStyle, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetStyle(TargetStyleType newTargetStyle) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__TARGET_STYLE, newTargetStyle);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TemplateType getTemplate() {
        return (TemplateType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__TEMPLATE, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTemplate(TemplateType newTemplate, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__TEMPLATE, newTemplate, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTemplate(TemplateType newTemplate) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__TEMPLATE, newTemplate);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TextType getText() {
        return (TextType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__TEXT, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetText(TextType newText, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__TEXT, newText, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setText(TextType newText) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__TEXT, newText);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UriType getUri() {
        return (UriType)getMixed().get(OgmlPackage.Literals.DOCUMENT_ROOT__URI, true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUri(UriType newUri, NotificationChain msgs) {
        return ((FeatureMap.Internal)getMixed()).basicAdd(OgmlPackage.Literals.DOCUMENT_ROOT__URI, newUri, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUri(UriType newUri) {
        ((FeatureMap.Internal)getMixed()).set(OgmlPackage.Literals.DOCUMENT_ROOT__URI, newUri);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case OgmlPackage.DOCUMENT_ROOT__MIXED:
                return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
            case OgmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
            case OgmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
            case OgmlPackage.DOCUMENT_ROOT__BOOL:
                return basicSetBool(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__BOOLEAN:
                return basicSetBoolean(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__COMPOSED:
                return basicSetComposed(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__CONSTRAINTS:
                return basicSetConstraints(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__DATA:
                return basicSetData(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__EDGE:
                return basicSetEdge(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__EDGE_CONSTRAINT:
                return basicSetEdgeConstraint(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__EDGE_LAYOUT:
                return basicSetEdgeLayout(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE:
                return basicSetEdgeStyleTemplate(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__ENDPOINT:
                return basicSetEndpoint(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__FILL:
                return basicSetFill(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__FONT:
                return basicSetFont(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__GRAPH:
                return basicSetGraph(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__GRAPH_STYLE:
                return basicSetGraphStyle(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__INT:
                return basicSetInt(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__KEY:
                return basicSetKey(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__KEYS:
                return basicSetKeys(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__KEY_VALUE:
                return basicSetKeyValue(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__LABEL:
                return basicSetLabel(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__LABEL_CONSTRAINT:
                return basicSetLabelConstraint(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__LABEL_LAYOUT:
                return basicSetLabelLayout(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE:
                return basicSetLabelStyleTemplate(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__LAYOUT:
                return basicSetLayout(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__LINE:
                return basicSetLine(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE:
                return basicSetLineStyle(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__LOCATION:
                return basicSetLocation(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__NODE:
                return basicSetNode(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__NODE_CONSTRAINT:
                return basicSetNodeConstraint(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__NODE_LAYOUT:
                return basicSetNodeLayout(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__NODE_STYLE_TEMPLATE:
                return basicSetNodeStyleTemplate(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__NUMBER:
                return basicSetNumber(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__OGML:
                return basicSetOgml(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__POINT:
                return basicSetPoint(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__SEGMENT:
                return basicSetSegment(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__SHAPE:
                return basicSetShape(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_STYLE:
                return basicSetSourceStyle(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_TARGET:
                return basicSetSourceTarget(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__STRUCTURE:
                return basicSetStructure(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__STYLES:
                return basicSetStyles(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__STYLE_TEMPLATES:
                return basicSetStyleTemplates(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__TARGET_STYLE:
                return basicSetTargetStyle(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__TEMPLATE:
                return basicSetTemplate(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__TEXT:
                return basicSetText(null, msgs);
            case OgmlPackage.DOCUMENT_ROOT__URI:
                return basicSetUri(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case OgmlPackage.DOCUMENT_ROOT__MIXED:
                if (coreType) return getMixed();
                return ((FeatureMap.Internal)getMixed()).getWrapper();
            case OgmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                if (coreType) return getXMLNSPrefixMap();
                else return getXMLNSPrefixMap().map();
            case OgmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                if (coreType) return getXSISchemaLocation();
                else return getXSISchemaLocation().map();
            case OgmlPackage.DOCUMENT_ROOT__BOOL:
                return getBool();
            case OgmlPackage.DOCUMENT_ROOT__BOOLEAN:
                return getBoolean();
            case OgmlPackage.DOCUMENT_ROOT__COMPOSED:
                return getComposed();
            case OgmlPackage.DOCUMENT_ROOT__CONSTRAINTS:
                return getConstraints();
            case OgmlPackage.DOCUMENT_ROOT__DATA:
                return getData();
            case OgmlPackage.DOCUMENT_ROOT__EDGE:
                return getEdge();
            case OgmlPackage.DOCUMENT_ROOT__EDGE_CONSTRAINT:
                return getEdgeConstraint();
            case OgmlPackage.DOCUMENT_ROOT__EDGE_LAYOUT:
                return getEdgeLayout();
            case OgmlPackage.DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE:
                return getEdgeStyleTemplate();
            case OgmlPackage.DOCUMENT_ROOT__ENDPOINT:
                return getEndpoint();
            case OgmlPackage.DOCUMENT_ROOT__FILL:
                return getFill();
            case OgmlPackage.DOCUMENT_ROOT__FONT:
                return getFont();
            case OgmlPackage.DOCUMENT_ROOT__FONT_STRETCH:
                return getFontStretch();
            case OgmlPackage.DOCUMENT_ROOT__FONT_STYLE:
                return getFontStyle();
            case OgmlPackage.DOCUMENT_ROOT__FONT_VARIANT:
                return getFontVariant();
            case OgmlPackage.DOCUMENT_ROOT__FONT_WEIGHT:
                return getFontWeight();
            case OgmlPackage.DOCUMENT_ROOT__GRAPH:
                return getGraph();
            case OgmlPackage.DOCUMENT_ROOT__GRAPH_STYLE:
                return getGraphStyle();
            case OgmlPackage.DOCUMENT_ROOT__INT:
                return getInt();
            case OgmlPackage.DOCUMENT_ROOT__KEY:
                return getKey();
            case OgmlPackage.DOCUMENT_ROOT__KEYS:
                return getKeys();
            case OgmlPackage.DOCUMENT_ROOT__KEY_VALUE:
                return getKeyValue();
            case OgmlPackage.DOCUMENT_ROOT__LABEL:
                return getLabel();
            case OgmlPackage.DOCUMENT_ROOT__LABEL_CONSTRAINT:
                return getLabelConstraint();
            case OgmlPackage.DOCUMENT_ROOT__LABEL_LAYOUT:
                return getLabelLayout();
            case OgmlPackage.DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE:
                return getLabelStyleTemplate();
            case OgmlPackage.DOCUMENT_ROOT__LAYOUT:
                return getLayout();
            case OgmlPackage.DOCUMENT_ROOT__LINE:
                return getLine();
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE:
                return getLineStyle();
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE_TYPE:
                return getLineStyleType();
            case OgmlPackage.DOCUMENT_ROOT__LOCATION:
                return getLocation();
            case OgmlPackage.DOCUMENT_ROOT__NODE:
                return getNode();
            case OgmlPackage.DOCUMENT_ROOT__NODE_CONSTRAINT:
                return getNodeConstraint();
            case OgmlPackage.DOCUMENT_ROOT__NODE_LAYOUT:
                return getNodeLayout();
            case OgmlPackage.DOCUMENT_ROOT__NODE_STYLE_TEMPLATE:
                return getNodeStyleTemplate();
            case OgmlPackage.DOCUMENT_ROOT__NUMBER:
                return getNumber();
            case OgmlPackage.DOCUMENT_ROOT__OGML:
                return getOgml();
            case OgmlPackage.DOCUMENT_ROOT__PATTERN:
                return getPattern();
            case OgmlPackage.DOCUMENT_ROOT__POINT:
                return getPoint();
            case OgmlPackage.DOCUMENT_ROOT__SEGMENT:
                return getSegment();
            case OgmlPackage.DOCUMENT_ROOT__SHAPE:
                return getShape();
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_STYLE:
                return getSourceStyle();
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_TARGET:
                return getSourceTarget();
            case OgmlPackage.DOCUMENT_ROOT__STRUCTURE:
                return getStructure();
            case OgmlPackage.DOCUMENT_ROOT__STYLES:
                return getStyles();
            case OgmlPackage.DOCUMENT_ROOT__STYLE_TEMPLATES:
                return getStyleTemplates();
            case OgmlPackage.DOCUMENT_ROOT__TARGET_STYLE:
                return getTargetStyle();
            case OgmlPackage.DOCUMENT_ROOT__TEMPLATE:
                return getTemplate();
            case OgmlPackage.DOCUMENT_ROOT__TEXT:
                return getText();
            case OgmlPackage.DOCUMENT_ROOT__URI:
                return getUri();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case OgmlPackage.DOCUMENT_ROOT__MIXED:
                ((FeatureMap.Internal)getMixed()).set(newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__BOOL:
                setBool((BoolType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__BOOLEAN:
                setBoolean((BooleanType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__COMPOSED:
                setComposed((ComposedType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__CONSTRAINTS:
                setConstraints((ConstraintsType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__DATA:
                setData((DataType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__EDGE:
                setEdge((EdgeType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_CONSTRAINT:
                setEdgeConstraint((EdgeConstraintType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_LAYOUT:
                setEdgeLayout((EdgeLayoutType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE:
                setEdgeStyleTemplate((EdgeStyleTemplateType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__ENDPOINT:
                setEndpoint((EndpointType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FILL:
                setFill((FillType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT:
                setFont((FontType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT_STRETCH:
                setFontStretch((FontStretchType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT_STYLE:
                setFontStyle((FontStyleType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT_VARIANT:
                setFontVariant((FontVariantType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT_WEIGHT:
                setFontWeight((FontWeightType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__GRAPH:
                setGraph((GraphType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__GRAPH_STYLE:
                setGraphStyle((GraphStyleType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__INT:
                setInt((IntType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__KEY:
                setKey((KeyType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__KEYS:
                setKeys((KeysType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__KEY_VALUE:
                setKeyValue((KeyValueType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LABEL:
                setLabel((LabelType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_CONSTRAINT:
                setLabelConstraint((LabelConstraintType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_LAYOUT:
                setLabelLayout((LabelLayoutType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE:
                setLabelStyleTemplate((LabelStyleTemplateType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LAYOUT:
                setLayout((LayoutType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LINE:
                setLine((LineType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE:
                setLineStyle((LineStyleType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE_TYPE:
                setLineStyleType((LineStyleTypeType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LOCATION:
                setLocation((LocationType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NODE:
                setNode((NodeType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NODE_CONSTRAINT:
                setNodeConstraint((NodeConstraintType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NODE_LAYOUT:
                setNodeLayout((NodeLayoutType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NODE_STYLE_TEMPLATE:
                setNodeStyleTemplate((NodeStyleTemplateType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NUMBER:
                setNumber((NumberType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__OGML:
                setOgml((OgmlType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__PATTERN:
                setPattern((PatternType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__POINT:
                setPoint((PointType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__SEGMENT:
                setSegment((SegmentType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__SHAPE:
                setShape((ShapeType1)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_STYLE:
                setSourceStyle((SourceStyleType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_TARGET:
                setSourceTarget((SourceTargetType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__STRUCTURE:
                setStructure((StructureType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__STYLES:
                setStyles((StylesType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__STYLE_TEMPLATES:
                setStyleTemplates((StyleTemplatesType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__TARGET_STYLE:
                setTargetStyle((TargetStyleType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__TEMPLATE:
                setTemplate((TemplateType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__TEXT:
                setText((TextType)newValue);
                return;
            case OgmlPackage.DOCUMENT_ROOT__URI:
                setUri((UriType)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case OgmlPackage.DOCUMENT_ROOT__MIXED:
                getMixed().clear();
                return;
            case OgmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                getXMLNSPrefixMap().clear();
                return;
            case OgmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                getXSISchemaLocation().clear();
                return;
            case OgmlPackage.DOCUMENT_ROOT__BOOL:
                setBool((BoolType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__BOOLEAN:
                setBoolean((BooleanType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__COMPOSED:
                setComposed((ComposedType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__CONSTRAINTS:
                setConstraints((ConstraintsType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__DATA:
                setData((DataType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__EDGE:
                setEdge((EdgeType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_CONSTRAINT:
                setEdgeConstraint((EdgeConstraintType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_LAYOUT:
                setEdgeLayout((EdgeLayoutType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE:
                setEdgeStyleTemplate((EdgeStyleTemplateType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__ENDPOINT:
                setEndpoint((EndpointType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FILL:
                setFill((FillType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT:
                setFont((FontType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT_STRETCH:
                setFontStretch(FONT_STRETCH_EDEFAULT);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT_STYLE:
                setFontStyle(FONT_STYLE_EDEFAULT);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT_VARIANT:
                setFontVariant(FONT_VARIANT_EDEFAULT);
                return;
            case OgmlPackage.DOCUMENT_ROOT__FONT_WEIGHT:
                setFontWeight(FONT_WEIGHT_EDEFAULT);
                return;
            case OgmlPackage.DOCUMENT_ROOT__GRAPH:
                setGraph((GraphType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__GRAPH_STYLE:
                setGraphStyle((GraphStyleType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__INT:
                setInt((IntType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__KEY:
                setKey((KeyType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__KEYS:
                setKeys((KeysType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__KEY_VALUE:
                setKeyValue((KeyValueType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LABEL:
                setLabel((LabelType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_CONSTRAINT:
                setLabelConstraint((LabelConstraintType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_LAYOUT:
                setLabelLayout((LabelLayoutType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE:
                setLabelStyleTemplate((LabelStyleTemplateType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LAYOUT:
                setLayout((LayoutType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LINE:
                setLine((LineType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE:
                setLineStyle((LineStyleType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE_TYPE:
                setLineStyleType(LINE_STYLE_TYPE_EDEFAULT);
                return;
            case OgmlPackage.DOCUMENT_ROOT__LOCATION:
                setLocation((LocationType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NODE:
                setNode((NodeType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NODE_CONSTRAINT:
                setNodeConstraint((NodeConstraintType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NODE_LAYOUT:
                setNodeLayout((NodeLayoutType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NODE_STYLE_TEMPLATE:
                setNodeStyleTemplate((NodeStyleTemplateType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__NUMBER:
                setNumber((NumberType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__OGML:
                setOgml((OgmlType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__PATTERN:
                setPattern(PATTERN_EDEFAULT);
                return;
            case OgmlPackage.DOCUMENT_ROOT__POINT:
                setPoint((PointType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__SEGMENT:
                setSegment((SegmentType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__SHAPE:
                setShape((ShapeType1)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_STYLE:
                setSourceStyle((SourceStyleType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_TARGET:
                setSourceTarget((SourceTargetType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__STRUCTURE:
                setStructure((StructureType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__STYLES:
                setStyles((StylesType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__STYLE_TEMPLATES:
                setStyleTemplates((StyleTemplatesType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__TARGET_STYLE:
                setTargetStyle((TargetStyleType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__TEMPLATE:
                setTemplate((TemplateType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__TEXT:
                setText((TextType)null);
                return;
            case OgmlPackage.DOCUMENT_ROOT__URI:
                setUri((UriType)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case OgmlPackage.DOCUMENT_ROOT__MIXED:
                return mixed != null && !mixed.isEmpty();
            case OgmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
                return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
            case OgmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
                return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
            case OgmlPackage.DOCUMENT_ROOT__BOOL:
                return getBool() != null;
            case OgmlPackage.DOCUMENT_ROOT__BOOLEAN:
                return getBoolean() != null;
            case OgmlPackage.DOCUMENT_ROOT__COMPOSED:
                return getComposed() != null;
            case OgmlPackage.DOCUMENT_ROOT__CONSTRAINTS:
                return getConstraints() != null;
            case OgmlPackage.DOCUMENT_ROOT__DATA:
                return getData() != null;
            case OgmlPackage.DOCUMENT_ROOT__EDGE:
                return getEdge() != null;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_CONSTRAINT:
                return getEdgeConstraint() != null;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_LAYOUT:
                return getEdgeLayout() != null;
            case OgmlPackage.DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE:
                return getEdgeStyleTemplate() != null;
            case OgmlPackage.DOCUMENT_ROOT__ENDPOINT:
                return getEndpoint() != null;
            case OgmlPackage.DOCUMENT_ROOT__FILL:
                return getFill() != null;
            case OgmlPackage.DOCUMENT_ROOT__FONT:
                return getFont() != null;
            case OgmlPackage.DOCUMENT_ROOT__FONT_STRETCH:
                return getFontStretch() != FONT_STRETCH_EDEFAULT;
            case OgmlPackage.DOCUMENT_ROOT__FONT_STYLE:
                return getFontStyle() != FONT_STYLE_EDEFAULT;
            case OgmlPackage.DOCUMENT_ROOT__FONT_VARIANT:
                return getFontVariant() != FONT_VARIANT_EDEFAULT;
            case OgmlPackage.DOCUMENT_ROOT__FONT_WEIGHT:
                return getFontWeight() != FONT_WEIGHT_EDEFAULT;
            case OgmlPackage.DOCUMENT_ROOT__GRAPH:
                return getGraph() != null;
            case OgmlPackage.DOCUMENT_ROOT__GRAPH_STYLE:
                return getGraphStyle() != null;
            case OgmlPackage.DOCUMENT_ROOT__INT:
                return getInt() != null;
            case OgmlPackage.DOCUMENT_ROOT__KEY:
                return getKey() != null;
            case OgmlPackage.DOCUMENT_ROOT__KEYS:
                return getKeys() != null;
            case OgmlPackage.DOCUMENT_ROOT__KEY_VALUE:
                return getKeyValue() != null;
            case OgmlPackage.DOCUMENT_ROOT__LABEL:
                return getLabel() != null;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_CONSTRAINT:
                return getLabelConstraint() != null;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_LAYOUT:
                return getLabelLayout() != null;
            case OgmlPackage.DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE:
                return getLabelStyleTemplate() != null;
            case OgmlPackage.DOCUMENT_ROOT__LAYOUT:
                return getLayout() != null;
            case OgmlPackage.DOCUMENT_ROOT__LINE:
                return getLine() != null;
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE:
                return getLineStyle() != null;
            case OgmlPackage.DOCUMENT_ROOT__LINE_STYLE_TYPE:
                return getLineStyleType() != LINE_STYLE_TYPE_EDEFAULT;
            case OgmlPackage.DOCUMENT_ROOT__LOCATION:
                return getLocation() != null;
            case OgmlPackage.DOCUMENT_ROOT__NODE:
                return getNode() != null;
            case OgmlPackage.DOCUMENT_ROOT__NODE_CONSTRAINT:
                return getNodeConstraint() != null;
            case OgmlPackage.DOCUMENT_ROOT__NODE_LAYOUT:
                return getNodeLayout() != null;
            case OgmlPackage.DOCUMENT_ROOT__NODE_STYLE_TEMPLATE:
                return getNodeStyleTemplate() != null;
            case OgmlPackage.DOCUMENT_ROOT__NUMBER:
                return getNumber() != null;
            case OgmlPackage.DOCUMENT_ROOT__OGML:
                return getOgml() != null;
            case OgmlPackage.DOCUMENT_ROOT__PATTERN:
                return getPattern() != PATTERN_EDEFAULT;
            case OgmlPackage.DOCUMENT_ROOT__POINT:
                return getPoint() != null;
            case OgmlPackage.DOCUMENT_ROOT__SEGMENT:
                return getSegment() != null;
            case OgmlPackage.DOCUMENT_ROOT__SHAPE:
                return getShape() != null;
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_STYLE:
                return getSourceStyle() != null;
            case OgmlPackage.DOCUMENT_ROOT__SOURCE_TARGET:
                return getSourceTarget() != null;
            case OgmlPackage.DOCUMENT_ROOT__STRUCTURE:
                return getStructure() != null;
            case OgmlPackage.DOCUMENT_ROOT__STYLES:
                return getStyles() != null;
            case OgmlPackage.DOCUMENT_ROOT__STYLE_TEMPLATES:
                return getStyleTemplates() != null;
            case OgmlPackage.DOCUMENT_ROOT__TARGET_STYLE:
                return getTargetStyle() != null;
            case OgmlPackage.DOCUMENT_ROOT__TEMPLATE:
                return getTemplate() != null;
            case OgmlPackage.DOCUMENT_ROOT__TEXT:
                return getText() != null;
            case OgmlPackage.DOCUMENT_ROOT__URI:
                return getUri() != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (mixed: ");
        result.append(mixed);
        result.append(')');
        return result.toString();
    }

} //DocumentRootImpl
