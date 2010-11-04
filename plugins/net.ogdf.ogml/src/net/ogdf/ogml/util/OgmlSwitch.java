/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.util;

import java.util.List;

import net.ogdf.ogml.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see net.ogdf.ogml.OgmlPackage
 * @generated
 */
public class OgmlSwitch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static OgmlPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OgmlSwitch() {
        if (modelPackage == null) {
            modelPackage = OgmlPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case OgmlPackage.BOOLEAN_TYPE: {
                BooleanType booleanType = (BooleanType)theEObject;
                T result = caseBooleanType(booleanType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.BOOL_TYPE: {
                BoolType boolType = (BoolType)theEObject;
                T result = caseBoolType(boolType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.COMPOSED_TYPE: {
                ComposedType composedType = (ComposedType)theEObject;
                T result = caseComposedType(composedType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.CONSTRAINTS_TYPE: {
                ConstraintsType constraintsType = (ConstraintsType)theEObject;
                T result = caseConstraintsType(constraintsType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.DATA_TYPE: {
                DataType dataType = (DataType)theEObject;
                T result = caseDataType(dataType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.EDGE_CONSTRAINT_TYPE: {
                EdgeConstraintType edgeConstraintType = (EdgeConstraintType)theEObject;
                T result = caseEdgeConstraintType(edgeConstraintType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.EDGE_LAYOUT_TYPE: {
                EdgeLayoutType edgeLayoutType = (EdgeLayoutType)theEObject;
                T result = caseEdgeLayoutType(edgeLayoutType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.EDGE_STYLE_TEMPLATE_TYPE: {
                EdgeStyleTemplateType edgeStyleTemplateType = (EdgeStyleTemplateType)theEObject;
                T result = caseEdgeStyleTemplateType(edgeStyleTemplateType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.EDGE_TYPE: {
                EdgeType edgeType = (EdgeType)theEObject;
                T result = caseEdgeType(edgeType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.ENDPOINT_TYPE: {
                EndpointType endpointType = (EndpointType)theEObject;
                T result = caseEndpointType(endpointType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.FILL_TYPE: {
                FillType fillType = (FillType)theEObject;
                T result = caseFillType(fillType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.FONT_TYPE: {
                FontType fontType = (FontType)theEObject;
                T result = caseFontType(fontType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.GRAPH_STYLE_TYPE: {
                GraphStyleType graphStyleType = (GraphStyleType)theEObject;
                T result = caseGraphStyleType(graphStyleType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.GRAPH_TYPE: {
                GraphType graphType = (GraphType)theEObject;
                T result = caseGraphType(graphType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.INT_TYPE: {
                IntType intType = (IntType)theEObject;
                T result = caseIntType(intType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.KEYS_TYPE: {
                KeysType keysType = (KeysType)theEObject;
                T result = caseKeysType(keysType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.KEY_TYPE: {
                KeyType keyType = (KeyType)theEObject;
                T result = caseKeyType(keyType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.KEY_VALUE_TYPE: {
                KeyValueType keyValueType = (KeyValueType)theEObject;
                T result = caseKeyValueType(keyValueType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.LABEL_CONSTRAINT_TYPE: {
                LabelConstraintType labelConstraintType = (LabelConstraintType)theEObject;
                T result = caseLabelConstraintType(labelConstraintType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.LABEL_LAYOUT_TYPE: {
                LabelLayoutType labelLayoutType = (LabelLayoutType)theEObject;
                T result = caseLabelLayoutType(labelLayoutType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.LABEL_STYLE_TEMPLATE_TYPE: {
                LabelStyleTemplateType labelStyleTemplateType = (LabelStyleTemplateType)theEObject;
                T result = caseLabelStyleTemplateType(labelStyleTemplateType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.LABEL_TYPE: {
                LabelType labelType = (LabelType)theEObject;
                T result = caseLabelType(labelType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.LAYOUT_TYPE: {
                LayoutType layoutType = (LayoutType)theEObject;
                T result = caseLayoutType(layoutType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.LINE_STYLE_TYPE: {
                LineStyleType lineStyleType = (LineStyleType)theEObject;
                T result = caseLineStyleType(lineStyleType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.LINE_TYPE: {
                LineType lineType = (LineType)theEObject;
                T result = caseLineType(lineType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.LOCATION_TYPE: {
                LocationType locationType = (LocationType)theEObject;
                T result = caseLocationType(locationType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.NODE_CONSTRAINT_TYPE: {
                NodeConstraintType nodeConstraintType = (NodeConstraintType)theEObject;
                T result = caseNodeConstraintType(nodeConstraintType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.NODE_LAYOUT_TYPE: {
                NodeLayoutType nodeLayoutType = (NodeLayoutType)theEObject;
                T result = caseNodeLayoutType(nodeLayoutType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.NODE_STYLE_TEMPLATE_TYPE: {
                NodeStyleTemplateType nodeStyleTemplateType = (NodeStyleTemplateType)theEObject;
                T result = caseNodeStyleTemplateType(nodeStyleTemplateType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.NODE_TYPE: {
                NodeType nodeType = (NodeType)theEObject;
                T result = caseNodeType(nodeType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.NUMBER_TYPE: {
                NumberType numberType = (NumberType)theEObject;
                T result = caseNumberType(numberType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.OGML_TYPE: {
                OgmlType ogmlType = (OgmlType)theEObject;
                T result = caseOgmlType(ogmlType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.POINT_TYPE: {
                PointType pointType = (PointType)theEObject;
                T result = casePointType(pointType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.SEGMENT_TYPE: {
                SegmentType segmentType = (SegmentType)theEObject;
                T result = caseSegmentType(segmentType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.SHAPE_TYPE1: {
                ShapeType1 shapeType1 = (ShapeType1)theEObject;
                T result = caseShapeType1(shapeType1);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.SOURCE_STYLE_TYPE: {
                SourceStyleType sourceStyleType = (SourceStyleType)theEObject;
                T result = caseSourceStyleType(sourceStyleType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.SOURCE_TARGET_TYPE: {
                SourceTargetType sourceTargetType = (SourceTargetType)theEObject;
                T result = caseSourceTargetType(sourceTargetType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.STRUCTURE_TYPE: {
                StructureType structureType = (StructureType)theEObject;
                T result = caseStructureType(structureType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.STYLES_TYPE: {
                StylesType stylesType = (StylesType)theEObject;
                T result = caseStylesType(stylesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.STYLE_TEMPLATES_TYPE: {
                StyleTemplatesType styleTemplatesType = (StyleTemplatesType)theEObject;
                T result = caseStyleTemplatesType(styleTemplatesType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.TARGET_STYLE_TYPE: {
                TargetStyleType targetStyleType = (TargetStyleType)theEObject;
                T result = caseTargetStyleType(targetStyleType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.TEMPLATE_TYPE: {
                TemplateType templateType = (TemplateType)theEObject;
                T result = caseTemplateType(templateType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.TEXT_TYPE: {
                TextType textType = (TextType)theEObject;
                T result = caseTextType(textType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case OgmlPackage.URI_TYPE: {
                UriType uriType = (UriType)theEObject;
                T result = caseUriType(uriType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBooleanType(BooleanType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bool Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bool Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBoolType(BoolType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Composed Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Composed Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComposedType(ComposedType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Constraints Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Constraints Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConstraintsType(ConstraintsType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDataType(DataType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentRoot(DocumentRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Constraint Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Constraint Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeConstraintType(EdgeConstraintType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Layout Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Layout Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeLayoutType(EdgeLayoutType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Style Template Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Style Template Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeStyleTemplateType(EdgeStyleTemplateType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeType(EdgeType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Endpoint Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Endpoint Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndpointType(EndpointType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Fill Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Fill Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFillType(FillType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Font Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Font Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFontType(FontType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Graph Style Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Graph Style Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGraphStyleType(GraphStyleType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Graph Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Graph Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGraphType(GraphType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Int Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Int Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntType(IntType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Keys Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Keys Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKeysType(KeysType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Key Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Key Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKeyType(KeyType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Key Value Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Key Value Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKeyValueType(KeyValueType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Constraint Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Constraint Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelConstraintType(LabelConstraintType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Layout Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Layout Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelLayoutType(LabelLayoutType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Style Template Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Style Template Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelStyleTemplateType(LabelStyleTemplateType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelType(LabelType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Layout Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayoutType(LayoutType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Line Style Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Line Style Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLineStyleType(LineStyleType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Line Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Line Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLineType(LineType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Location Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Location Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLocationType(LocationType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Constraint Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Constraint Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeConstraintType(NodeConstraintType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Layout Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Layout Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeLayoutType(NodeLayoutType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Style Template Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Style Template Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeStyleTemplateType(NodeStyleTemplateType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeType(NodeType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Number Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Number Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNumberType(NumberType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOgmlType(OgmlType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Point Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Point Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePointType(PointType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Segment Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Segment Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSegmentType(SegmentType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Shape Type1</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Shape Type1</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShapeType1(ShapeType1 object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Source Style Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Source Style Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSourceStyleType(SourceStyleType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Source Target Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Source Target Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSourceTargetType(SourceTargetType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Structure Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Structure Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStructureType(StructureType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Styles Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Styles Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStylesType(StylesType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Style Templates Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Style Templates Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyleTemplatesType(StyleTemplatesType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Target Style Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Target Style Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTargetStyleType(TargetStyleType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Template Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Template Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTemplateType(TemplateType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextType(TextType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Uri Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Uri Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUriType(UriType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} //OgmlSwitch
