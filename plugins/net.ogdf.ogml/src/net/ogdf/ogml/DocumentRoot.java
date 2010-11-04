/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getBool <em>Bool</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getComposed <em>Composed</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getData <em>Data</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getEdge <em>Edge</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getEdgeConstraint <em>Edge Constraint</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getEdgeLayout <em>Edge Layout</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getEdgeStyleTemplate <em>Edge Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getFill <em>Fill</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getFont <em>Font</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getFontStretch <em>Font Stretch</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getFontStyle <em>Font Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getFontVariant <em>Font Variant</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getFontWeight <em>Font Weight</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getGraph <em>Graph</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getGraphStyle <em>Graph Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getInt <em>Int</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getKey <em>Key</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getKeys <em>Keys</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getKeyValue <em>Key Value</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLabel <em>Label</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLabelConstraint <em>Label Constraint</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLabelLayout <em>Label Layout</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLabelStyleTemplate <em>Label Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLayout <em>Layout</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLine <em>Line</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLineStyleType <em>Line Style Type</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getLocation <em>Location</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getNode <em>Node</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getNodeConstraint <em>Node Constraint</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getNodeLayout <em>Node Layout</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getNodeStyleTemplate <em>Node Style Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getNumber <em>Number</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getOgml <em>Ogml</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getPattern <em>Pattern</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getPoint <em>Point</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getSegment <em>Segment</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getShape <em>Shape</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getSourceStyle <em>Source Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getSourceTarget <em>Source Target</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getStructure <em>Structure</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getStyles <em>Styles</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getStyleTemplates <em>Style Templates</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getTargetStyle <em>Target Style</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getTemplate <em>Template</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getText <em>Text</em>}</li>
 *   <li>{@link net.ogdf.ogml.DocumentRoot#getUri <em>Uri</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
     * @generated
     */
    EMap<String, String> getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
    EMap<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>Bool</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bool</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bool</em>' containment reference.
     * @see #setBool(BoolType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Bool()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='bool' namespace='##targetNamespace'"
     * @generated
     */
    BoolType getBool();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getBool <em>Bool</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bool</em>' containment reference.
     * @see #getBool()
     * @generated
     */
    void setBool(BoolType value);

    /**
     * Returns the value of the '<em><b>Boolean</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Boolean</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Boolean</em>' containment reference.
     * @see #setBoolean(BooleanType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Boolean()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='boolean' namespace='##targetNamespace'"
     * @generated
     */
    BooleanType getBoolean();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getBoolean <em>Boolean</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Boolean</em>' containment reference.
     * @see #getBoolean()
     * @generated
     */
    void setBoolean(BooleanType value);

    /**
     * Returns the value of the '<em><b>Composed</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Composed</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Composed</em>' containment reference.
     * @see #setComposed(ComposedType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Composed()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='composed' namespace='##targetNamespace'"
     * @generated
     */
    ComposedType getComposed();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getComposed <em>Composed</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Composed</em>' containment reference.
     * @see #getComposed()
     * @generated
     */
    void setComposed(ComposedType value);

    /**
     * Returns the value of the '<em><b>Constraints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Constraints</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Constraints</em>' containment reference.
     * @see #setConstraints(ConstraintsType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Constraints()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='constraints' namespace='##targetNamespace'"
     * @generated
     */
    ConstraintsType getConstraints();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getConstraints <em>Constraints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Constraints</em>' containment reference.
     * @see #getConstraints()
     * @generated
     */
    void setConstraints(ConstraintsType value);

    /**
     * Returns the value of the '<em><b>Data</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data</em>' containment reference.
     * @see #setData(DataType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Data()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
     * @generated
     */
    DataType getData();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getData <em>Data</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data</em>' containment reference.
     * @see #getData()
     * @generated
     */
    void setData(DataType value);

    /**
     * Returns the value of the '<em><b>Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge</em>' containment reference.
     * @see #setEdge(EdgeType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Edge()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edge' namespace='##targetNamespace'"
     * @generated
     */
    EdgeType getEdge();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getEdge <em>Edge</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Edge</em>' containment reference.
     * @see #getEdge()
     * @generated
     */
    void setEdge(EdgeType value);

    /**
     * Returns the value of the '<em><b>Edge Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge Constraint</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge Constraint</em>' containment reference.
     * @see #setEdgeConstraint(EdgeConstraintType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_EdgeConstraint()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edge_constraint' namespace='##targetNamespace'"
     * @generated
     */
    EdgeConstraintType getEdgeConstraint();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getEdgeConstraint <em>Edge Constraint</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Edge Constraint</em>' containment reference.
     * @see #getEdgeConstraint()
     * @generated
     */
    void setEdgeConstraint(EdgeConstraintType value);

    /**
     * Returns the value of the '<em><b>Edge Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge Layout</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge Layout</em>' containment reference.
     * @see #setEdgeLayout(EdgeLayoutType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_EdgeLayout()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edge_layout' namespace='##targetNamespace'"
     * @generated
     */
    EdgeLayoutType getEdgeLayout();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getEdgeLayout <em>Edge Layout</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Edge Layout</em>' containment reference.
     * @see #getEdgeLayout()
     * @generated
     */
    void setEdgeLayout(EdgeLayoutType value);

    /**
     * Returns the value of the '<em><b>Edge Style Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edge Style Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Edge Style Template</em>' containment reference.
     * @see #setEdgeStyleTemplate(EdgeStyleTemplateType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_EdgeStyleTemplate()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='edgeStyleTemplate' namespace='##targetNamespace'"
     * @generated
     */
    EdgeStyleTemplateType getEdgeStyleTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getEdgeStyleTemplate <em>Edge Style Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Edge Style Template</em>' containment reference.
     * @see #getEdgeStyleTemplate()
     * @generated
     */
    void setEdgeStyleTemplate(EdgeStyleTemplateType value);

    /**
     * Returns the value of the '<em><b>Endpoint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Endpoint</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Endpoint</em>' containment reference.
     * @see #setEndpoint(EndpointType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Endpoint()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='endpoint' namespace='##targetNamespace'"
     * @generated
     */
    EndpointType getEndpoint();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getEndpoint <em>Endpoint</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Endpoint</em>' containment reference.
     * @see #getEndpoint()
     * @generated
     */
    void setEndpoint(EndpointType value);

    /**
     * Returns the value of the '<em><b>Fill</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Fill</em>' containment reference.
     * @see #setFill(FillType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Fill()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='fill' namespace='##targetNamespace'"
     * @generated
     */
    FillType getFill();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getFill <em>Fill</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fill</em>' containment reference.
     * @see #getFill()
     * @generated
     */
    void setFill(FillType value);

    /**
     * Returns the value of the '<em><b>Font</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font</em>' containment reference.
     * @see #setFont(FontType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Font()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='font' namespace='##targetNamespace'"
     * @generated
     */
    FontType getFont();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getFont <em>Font</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font</em>' containment reference.
     * @see #getFont()
     * @generated
     */
    void setFont(FontType value);

    /**
     * Returns the value of the '<em><b>Font Stretch</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.FontStretchType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font Stretch</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font Stretch</em>' attribute.
     * @see net.ogdf.ogml.FontStretchType
     * @see #setFontStretch(FontStretchType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_FontStretch()
     * @model unique="false" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='fontStretch' namespace='##targetNamespace'"
     * @generated
     */
    FontStretchType getFontStretch();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getFontStretch <em>Font Stretch</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font Stretch</em>' attribute.
     * @see net.ogdf.ogml.FontStretchType
     * @see #getFontStretch()
     * @generated
     */
    void setFontStretch(FontStretchType value);

    /**
     * Returns the value of the '<em><b>Font Style</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.FontStyleType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font Style</em>' attribute.
     * @see net.ogdf.ogml.FontStyleType
     * @see #setFontStyle(FontStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_FontStyle()
     * @model unique="false" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='fontStyle' namespace='##targetNamespace'"
     * @generated
     */
    FontStyleType getFontStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getFontStyle <em>Font Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font Style</em>' attribute.
     * @see net.ogdf.ogml.FontStyleType
     * @see #getFontStyle()
     * @generated
     */
    void setFontStyle(FontStyleType value);

    /**
     * Returns the value of the '<em><b>Font Variant</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.FontVariantType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font Variant</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font Variant</em>' attribute.
     * @see net.ogdf.ogml.FontVariantType
     * @see #setFontVariant(FontVariantType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_FontVariant()
     * @model unique="false" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='fontVariant' namespace='##targetNamespace'"
     * @generated
     */
    FontVariantType getFontVariant();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getFontVariant <em>Font Variant</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font Variant</em>' attribute.
     * @see net.ogdf.ogml.FontVariantType
     * @see #getFontVariant()
     * @generated
     */
    void setFontVariant(FontVariantType value);

    /**
     * Returns the value of the '<em><b>Font Weight</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.FontWeightType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Font Weight</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font Weight</em>' attribute.
     * @see net.ogdf.ogml.FontWeightType
     * @see #setFontWeight(FontWeightType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_FontWeight()
     * @model unique="false" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='fontWeight' namespace='##targetNamespace'"
     * @generated
     */
    FontWeightType getFontWeight();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getFontWeight <em>Font Weight</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font Weight</em>' attribute.
     * @see net.ogdf.ogml.FontWeightType
     * @see #getFontWeight()
     * @generated
     */
    void setFontWeight(FontWeightType value);

    /**
     * Returns the value of the '<em><b>Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Graph</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Graph</em>' containment reference.
     * @see #setGraph(GraphType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Graph()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='graph' namespace='##targetNamespace'"
     * @generated
     */
    GraphType getGraph();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getGraph <em>Graph</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Graph</em>' containment reference.
     * @see #getGraph()
     * @generated
     */
    void setGraph(GraphType value);

    /**
     * Returns the value of the '<em><b>Graph Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Graph Style</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Graph Style</em>' containment reference.
     * @see #setGraphStyle(GraphStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_GraphStyle()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='graphStyle' namespace='##targetNamespace'"
     * @generated
     */
    GraphStyleType getGraphStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getGraphStyle <em>Graph Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Graph Style</em>' containment reference.
     * @see #getGraphStyle()
     * @generated
     */
    void setGraphStyle(GraphStyleType value);

    /**
     * Returns the value of the '<em><b>Int</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Int</em>' containment reference.
     * @see #setInt(IntType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Int()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='int' namespace='##targetNamespace'"
     * @generated
     */
    IntType getInt();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getInt <em>Int</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Int</em>' containment reference.
     * @see #getInt()
     * @generated
     */
    void setInt(IntType value);

    /**
     * Returns the value of the '<em><b>Key</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Key</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Key</em>' containment reference.
     * @see #setKey(KeyType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Key()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='key' namespace='##targetNamespace'"
     * @generated
     */
    KeyType getKey();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getKey <em>Key</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Key</em>' containment reference.
     * @see #getKey()
     * @generated
     */
    void setKey(KeyType value);

    /**
     * Returns the value of the '<em><b>Keys</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Keys</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Keys</em>' containment reference.
     * @see #setKeys(KeysType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Keys()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='keys' namespace='##targetNamespace'"
     * @generated
     */
    KeysType getKeys();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getKeys <em>Keys</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Keys</em>' containment reference.
     * @see #getKeys()
     * @generated
     */
    void setKeys(KeysType value);

    /**
     * Returns the value of the '<em><b>Key Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Key Value</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Key Value</em>' containment reference.
     * @see #setKeyValue(KeyValueType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_KeyValue()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='keyValue' namespace='##targetNamespace'"
     * @generated
     */
    KeyValueType getKeyValue();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getKeyValue <em>Key Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Key Value</em>' containment reference.
     * @see #getKeyValue()
     * @generated
     */
    void setKeyValue(KeyValueType value);

    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference.
     * @see #setLabel(LabelType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Label()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='label' namespace='##targetNamespace'"
     * @generated
     */
    LabelType getLabel();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
    void setLabel(LabelType value);

    /**
     * Returns the value of the '<em><b>Label Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Constraint</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Constraint</em>' containment reference.
     * @see #setLabelConstraint(LabelConstraintType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_LabelConstraint()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='label_constraint' namespace='##targetNamespace'"
     * @generated
     */
    LabelConstraintType getLabelConstraint();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLabelConstraint <em>Label Constraint</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label Constraint</em>' containment reference.
     * @see #getLabelConstraint()
     * @generated
     */
    void setLabelConstraint(LabelConstraintType value);

    /**
     * Returns the value of the '<em><b>Label Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Layout</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Layout</em>' containment reference.
     * @see #setLabelLayout(LabelLayoutType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_LabelLayout()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='label_layout' namespace='##targetNamespace'"
     * @generated
     */
    LabelLayoutType getLabelLayout();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLabelLayout <em>Label Layout</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label Layout</em>' containment reference.
     * @see #getLabelLayout()
     * @generated
     */
    void setLabelLayout(LabelLayoutType value);

    /**
     * Returns the value of the '<em><b>Label Style Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Style Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Style Template</em>' containment reference.
     * @see #setLabelStyleTemplate(LabelStyleTemplateType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_LabelStyleTemplate()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='labelStyleTemplate' namespace='##targetNamespace'"
     * @generated
     */
    LabelStyleTemplateType getLabelStyleTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLabelStyleTemplate <em>Label Style Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label Style Template</em>' containment reference.
     * @see #getLabelStyleTemplate()
     * @generated
     */
    void setLabelStyleTemplate(LabelStyleTemplateType value);

    /**
     * Returns the value of the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Layout</em>' containment reference.
     * @see #setLayout(LayoutType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Layout()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='layout' namespace='##targetNamespace'"
     * @generated
     */
    LayoutType getLayout();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLayout <em>Layout</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Layout</em>' containment reference.
     * @see #getLayout()
     * @generated
     */
    void setLayout(LayoutType value);

    /**
     * Returns the value of the '<em><b>Line</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Line</em>' containment reference.
     * @see #setLine(LineType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Line()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='line' namespace='##targetNamespace'"
     * @generated
     */
    LineType getLine();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLine <em>Line</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line</em>' containment reference.
     * @see #getLine()
     * @generated
     */
    void setLine(LineType value);

    /**
     * Returns the value of the '<em><b>Line Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Line Style</em>' containment reference.
     * @see #setLineStyle(LineStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_LineStyle()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='lineStyle' namespace='##targetNamespace'"
     * @generated
     */
    LineStyleType getLineStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLineStyle <em>Line Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Style</em>' containment reference.
     * @see #getLineStyle()
     * @generated
     */
    void setLineStyle(LineStyleType value);

    /**
     * Returns the value of the '<em><b>Line Style Type</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.LineStyleTypeType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Style Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Line Style Type</em>' attribute.
     * @see net.ogdf.ogml.LineStyleTypeType
     * @see #setLineStyleType(LineStyleTypeType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_LineStyleType()
     * @model unique="false" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='lineStyleType' namespace='##targetNamespace'"
     * @generated
     */
    LineStyleTypeType getLineStyleType();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLineStyleType <em>Line Style Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Line Style Type</em>' attribute.
     * @see net.ogdf.ogml.LineStyleTypeType
     * @see #getLineStyleType()
     * @generated
     */
    void setLineStyleType(LineStyleTypeType value);

    /**
     * Returns the value of the '<em><b>Location</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Location</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Location</em>' containment reference.
     * @see #setLocation(LocationType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Location()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='location' namespace='##targetNamespace'"
     * @generated
     */
    LocationType getLocation();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getLocation <em>Location</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Location</em>' containment reference.
     * @see #getLocation()
     * @generated
     */
    void setLocation(LocationType value);

    /**
     * Returns the value of the '<em><b>Node</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node</em>' containment reference.
     * @see #setNode(NodeType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Node()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='node' namespace='##targetNamespace'"
     * @generated
     */
    NodeType getNode();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getNode <em>Node</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node</em>' containment reference.
     * @see #getNode()
     * @generated
     */
    void setNode(NodeType value);

    /**
     * Returns the value of the '<em><b>Node Constraint</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Constraint</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Constraint</em>' containment reference.
     * @see #setNodeConstraint(NodeConstraintType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_NodeConstraint()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='node_constraint' namespace='##targetNamespace'"
     * @generated
     */
    NodeConstraintType getNodeConstraint();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getNodeConstraint <em>Node Constraint</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node Constraint</em>' containment reference.
     * @see #getNodeConstraint()
     * @generated
     */
    void setNodeConstraint(NodeConstraintType value);

    /**
     * Returns the value of the '<em><b>Node Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Layout</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Layout</em>' containment reference.
     * @see #setNodeLayout(NodeLayoutType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_NodeLayout()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='node_layout' namespace='##targetNamespace'"
     * @generated
     */
    NodeLayoutType getNodeLayout();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getNodeLayout <em>Node Layout</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node Layout</em>' containment reference.
     * @see #getNodeLayout()
     * @generated
     */
    void setNodeLayout(NodeLayoutType value);

    /**
     * Returns the value of the '<em><b>Node Style Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Style Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Style Template</em>' containment reference.
     * @see #setNodeStyleTemplate(NodeStyleTemplateType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_NodeStyleTemplate()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='nodeStyleTemplate' namespace='##targetNamespace'"
     * @generated
     */
    NodeStyleTemplateType getNodeStyleTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getNodeStyleTemplate <em>Node Style Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node Style Template</em>' containment reference.
     * @see #getNodeStyleTemplate()
     * @generated
     */
    void setNodeStyleTemplate(NodeStyleTemplateType value);

    /**
     * Returns the value of the '<em><b>Number</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Number</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Number</em>' containment reference.
     * @see #setNumber(NumberType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Number()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='number' namespace='##targetNamespace'"
     * @generated
     */
    NumberType getNumber();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getNumber <em>Number</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Number</em>' containment reference.
     * @see #getNumber()
     * @generated
     */
    void setNumber(NumberType value);

    /**
     * Returns the value of the '<em><b>Ogml</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ogml</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ogml</em>' containment reference.
     * @see #setOgml(OgmlType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Ogml()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='ogml' namespace='##targetNamespace'"
     * @generated
     */
    OgmlType getOgml();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getOgml <em>Ogml</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ogml</em>' containment reference.
     * @see #getOgml()
     * @generated
     */
    void setOgml(OgmlType value);

    /**
     * Returns the value of the '<em><b>Pattern</b></em>' attribute.
     * The literals are from the enumeration {@link net.ogdf.ogml.PatternType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pattern</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern</em>' attribute.
     * @see net.ogdf.ogml.PatternType
     * @see #setPattern(PatternType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Pattern()
     * @model unique="false" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='pattern' namespace='##targetNamespace'"
     * @generated
     */
    PatternType getPattern();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getPattern <em>Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern</em>' attribute.
     * @see net.ogdf.ogml.PatternType
     * @see #getPattern()
     * @generated
     */
    void setPattern(PatternType value);

    /**
     * Returns the value of the '<em><b>Point</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Point</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Point</em>' containment reference.
     * @see #setPoint(PointType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Point()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='point' namespace='##targetNamespace'"
     * @generated
     */
    PointType getPoint();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getPoint <em>Point</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Point</em>' containment reference.
     * @see #getPoint()
     * @generated
     */
    void setPoint(PointType value);

    /**
     * Returns the value of the '<em><b>Segment</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Segment</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Segment</em>' containment reference.
     * @see #setSegment(SegmentType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Segment()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='segment' namespace='##targetNamespace'"
     * @generated
     */
    SegmentType getSegment();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getSegment <em>Segment</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Segment</em>' containment reference.
     * @see #getSegment()
     * @generated
     */
    void setSegment(SegmentType value);

    /**
     * Returns the value of the '<em><b>Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Shape</em>' containment reference.
     * @see #setShape(ShapeType1)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Shape()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='shape' namespace='##targetNamespace'"
     * @generated
     */
    ShapeType1 getShape();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getShape <em>Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Shape</em>' containment reference.
     * @see #getShape()
     * @generated
     */
    void setShape(ShapeType1 value);

    /**
     * Returns the value of the '<em><b>Source Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Source Style</em>' containment reference.
     * @see #setSourceStyle(SourceStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_SourceStyle()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='sourceStyle' namespace='##targetNamespace'"
     * @generated
     */
    SourceStyleType getSourceStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getSourceStyle <em>Source Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Style</em>' containment reference.
     * @see #getSourceStyle()
     * @generated
     */
    void setSourceStyle(SourceStyleType value);

    /**
     * Returns the value of the '<em><b>Source Target</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Target</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Target</em>' containment reference.
     * @see #setSourceTarget(SourceTargetType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_SourceTarget()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='sourceTarget' namespace='##targetNamespace'"
     * @generated
     */
    SourceTargetType getSourceTarget();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getSourceTarget <em>Source Target</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Target</em>' containment reference.
     * @see #getSourceTarget()
     * @generated
     */
    void setSourceTarget(SourceTargetType value);

    /**
     * Returns the value of the '<em><b>Structure</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Structure</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Structure</em>' containment reference.
     * @see #setStructure(StructureType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Structure()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='structure' namespace='##targetNamespace'"
     * @generated
     */
    StructureType getStructure();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getStructure <em>Structure</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Structure</em>' containment reference.
     * @see #getStructure()
     * @generated
     */
    void setStructure(StructureType value);

    /**
     * Returns the value of the '<em><b>Styles</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Styles</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Styles</em>' containment reference.
     * @see #setStyles(StylesType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Styles()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='styles' namespace='##targetNamespace'"
     * @generated
     */
    StylesType getStyles();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getStyles <em>Styles</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Styles</em>' containment reference.
     * @see #getStyles()
     * @generated
     */
    void setStyles(StylesType value);

    /**
     * Returns the value of the '<em><b>Style Templates</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style Templates</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Style Templates</em>' containment reference.
     * @see #setStyleTemplates(StyleTemplatesType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_StyleTemplates()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='styleTemplates' namespace='##targetNamespace'"
     * @generated
     */
    StyleTemplatesType getStyleTemplates();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getStyleTemplates <em>Style Templates</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Style Templates</em>' containment reference.
     * @see #getStyleTemplates()
     * @generated
     */
    void setStyleTemplates(StyleTemplatesType value);

    /**
     * Returns the value of the '<em><b>Target Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     * <!-- something missing -->
     *     
     * <!-- end-model-doc -->
     * @return the value of the '<em>Target Style</em>' containment reference.
     * @see #setTargetStyle(TargetStyleType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_TargetStyle()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='targetStyle' namespace='##targetNamespace'"
     * @generated
     */
    TargetStyleType getTargetStyle();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getTargetStyle <em>Target Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Style</em>' containment reference.
     * @see #getTargetStyle()
     * @generated
     */
    void setTargetStyle(TargetStyleType value);

    /**
     * Returns the value of the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Template</em>' containment reference.
     * @see #setTemplate(TemplateType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Template()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='template' namespace='##targetNamespace'"
     * @generated
     */
    TemplateType getTemplate();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getTemplate <em>Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Template</em>' containment reference.
     * @see #getTemplate()
     * @generated
     */
    void setTemplate(TemplateType value);

    /**
     * Returns the value of the '<em><b>Text</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' containment reference.
     * @see #setText(TextType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Text()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='text' namespace='##targetNamespace'"
     * @generated
     */
    TextType getText();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getText <em>Text</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' containment reference.
     * @see #getText()
     * @generated
     */
    void setText(TextType value);

    /**
     * Returns the value of the '<em><b>Uri</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Uri</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Uri</em>' containment reference.
     * @see #setUri(UriType)
     * @see net.ogdf.ogml.OgmlPackage#getDocumentRoot_Uri()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='uri' namespace='##targetNamespace'"
     * @generated
     */
    UriType getUri();

    /**
     * Sets the value of the '{@link net.ogdf.ogml.DocumentRoot#getUri <em>Uri</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Uri</em>' containment reference.
     * @see #getUri()
     * @generated
     */
    void setUri(UriType value);

} // DocumentRoot
