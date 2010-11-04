/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.util;

import net.ogdf.ogml.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.ogdf.ogml.OgmlPackage
 * @generated
 */
public class OgmlAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static OgmlPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OgmlAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = OgmlPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected OgmlSwitch<Adapter> modelSwitch =
        new OgmlSwitch<Adapter>() {
            @Override
            public Adapter caseBooleanType(BooleanType object) {
                return createBooleanTypeAdapter();
            }
            @Override
            public Adapter caseBoolType(BoolType object) {
                return createBoolTypeAdapter();
            }
            @Override
            public Adapter caseComposedType(ComposedType object) {
                return createComposedTypeAdapter();
            }
            @Override
            public Adapter caseConstraintsType(ConstraintsType object) {
                return createConstraintsTypeAdapter();
            }
            @Override
            public Adapter caseDataType(DataType object) {
                return createDataTypeAdapter();
            }
            @Override
            public Adapter caseDocumentRoot(DocumentRoot object) {
                return createDocumentRootAdapter();
            }
            @Override
            public Adapter caseEdgeConstraintType(EdgeConstraintType object) {
                return createEdgeConstraintTypeAdapter();
            }
            @Override
            public Adapter caseEdgeLayoutType(EdgeLayoutType object) {
                return createEdgeLayoutTypeAdapter();
            }
            @Override
            public Adapter caseEdgeStyleTemplateType(EdgeStyleTemplateType object) {
                return createEdgeStyleTemplateTypeAdapter();
            }
            @Override
            public Adapter caseEdgeType(EdgeType object) {
                return createEdgeTypeAdapter();
            }
            @Override
            public Adapter caseEndpointType(EndpointType object) {
                return createEndpointTypeAdapter();
            }
            @Override
            public Adapter caseFillType(FillType object) {
                return createFillTypeAdapter();
            }
            @Override
            public Adapter caseFontType(FontType object) {
                return createFontTypeAdapter();
            }
            @Override
            public Adapter caseGraphStyleType(GraphStyleType object) {
                return createGraphStyleTypeAdapter();
            }
            @Override
            public Adapter caseGraphType(GraphType object) {
                return createGraphTypeAdapter();
            }
            @Override
            public Adapter caseIntType(IntType object) {
                return createIntTypeAdapter();
            }
            @Override
            public Adapter caseKeysType(KeysType object) {
                return createKeysTypeAdapter();
            }
            @Override
            public Adapter caseKeyType(KeyType object) {
                return createKeyTypeAdapter();
            }
            @Override
            public Adapter caseKeyValueType(KeyValueType object) {
                return createKeyValueTypeAdapter();
            }
            @Override
            public Adapter caseLabelConstraintType(LabelConstraintType object) {
                return createLabelConstraintTypeAdapter();
            }
            @Override
            public Adapter caseLabelLayoutType(LabelLayoutType object) {
                return createLabelLayoutTypeAdapter();
            }
            @Override
            public Adapter caseLabelStyleTemplateType(LabelStyleTemplateType object) {
                return createLabelStyleTemplateTypeAdapter();
            }
            @Override
            public Adapter caseLabelType(LabelType object) {
                return createLabelTypeAdapter();
            }
            @Override
            public Adapter caseLayoutType(LayoutType object) {
                return createLayoutTypeAdapter();
            }
            @Override
            public Adapter caseLineStyleType(LineStyleType object) {
                return createLineStyleTypeAdapter();
            }
            @Override
            public Adapter caseLineType(LineType object) {
                return createLineTypeAdapter();
            }
            @Override
            public Adapter caseLocationType(LocationType object) {
                return createLocationTypeAdapter();
            }
            @Override
            public Adapter caseNodeConstraintType(NodeConstraintType object) {
                return createNodeConstraintTypeAdapter();
            }
            @Override
            public Adapter caseNodeLayoutType(NodeLayoutType object) {
                return createNodeLayoutTypeAdapter();
            }
            @Override
            public Adapter caseNodeStyleTemplateType(NodeStyleTemplateType object) {
                return createNodeStyleTemplateTypeAdapter();
            }
            @Override
            public Adapter caseNodeType(NodeType object) {
                return createNodeTypeAdapter();
            }
            @Override
            public Adapter caseNumberType(NumberType object) {
                return createNumberTypeAdapter();
            }
            @Override
            public Adapter caseOgmlType(OgmlType object) {
                return createOgmlTypeAdapter();
            }
            @Override
            public Adapter casePointType(PointType object) {
                return createPointTypeAdapter();
            }
            @Override
            public Adapter caseSegmentType(SegmentType object) {
                return createSegmentTypeAdapter();
            }
            @Override
            public Adapter caseShapeType1(ShapeType1 object) {
                return createShapeType1Adapter();
            }
            @Override
            public Adapter caseSourceStyleType(SourceStyleType object) {
                return createSourceStyleTypeAdapter();
            }
            @Override
            public Adapter caseSourceTargetType(SourceTargetType object) {
                return createSourceTargetTypeAdapter();
            }
            @Override
            public Adapter caseStructureType(StructureType object) {
                return createStructureTypeAdapter();
            }
            @Override
            public Adapter caseStylesType(StylesType object) {
                return createStylesTypeAdapter();
            }
            @Override
            public Adapter caseStyleTemplatesType(StyleTemplatesType object) {
                return createStyleTemplatesTypeAdapter();
            }
            @Override
            public Adapter caseTargetStyleType(TargetStyleType object) {
                return createTargetStyleTypeAdapter();
            }
            @Override
            public Adapter caseTemplateType(TemplateType object) {
                return createTemplateTypeAdapter();
            }
            @Override
            public Adapter caseTextType(TextType object) {
                return createTextTypeAdapter();
            }
            @Override
            public Adapter caseUriType(UriType object) {
                return createUriTypeAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.BooleanType <em>Boolean Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.BooleanType
     * @generated
     */
    public Adapter createBooleanTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.BoolType <em>Bool Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.BoolType
     * @generated
     */
    public Adapter createBoolTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.ComposedType <em>Composed Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.ComposedType
     * @generated
     */
    public Adapter createComposedTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.ConstraintsType <em>Constraints Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.ConstraintsType
     * @generated
     */
    public Adapter createConstraintsTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.DataType <em>Data Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.DataType
     * @generated
     */
    public Adapter createDataTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.DocumentRoot
     * @generated
     */
    public Adapter createDocumentRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.EdgeConstraintType <em>Edge Constraint Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.EdgeConstraintType
     * @generated
     */
    public Adapter createEdgeConstraintTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.EdgeLayoutType <em>Edge Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.EdgeLayoutType
     * @generated
     */
    public Adapter createEdgeLayoutTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.EdgeStyleTemplateType <em>Edge Style Template Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.EdgeStyleTemplateType
     * @generated
     */
    public Adapter createEdgeStyleTemplateTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.EdgeType <em>Edge Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.EdgeType
     * @generated
     */
    public Adapter createEdgeTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.EndpointType <em>Endpoint Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.EndpointType
     * @generated
     */
    public Adapter createEndpointTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.FillType <em>Fill Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.FillType
     * @generated
     */
    public Adapter createFillTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.FontType <em>Font Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.FontType
     * @generated
     */
    public Adapter createFontTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.GraphStyleType <em>Graph Style Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.GraphStyleType
     * @generated
     */
    public Adapter createGraphStyleTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.GraphType <em>Graph Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.GraphType
     * @generated
     */
    public Adapter createGraphTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.IntType <em>Int Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.IntType
     * @generated
     */
    public Adapter createIntTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.KeysType <em>Keys Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.KeysType
     * @generated
     */
    public Adapter createKeysTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.KeyType <em>Key Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.KeyType
     * @generated
     */
    public Adapter createKeyTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.KeyValueType <em>Key Value Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.KeyValueType
     * @generated
     */
    public Adapter createKeyValueTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.LabelConstraintType <em>Label Constraint Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.LabelConstraintType
     * @generated
     */
    public Adapter createLabelConstraintTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.LabelLayoutType <em>Label Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.LabelLayoutType
     * @generated
     */
    public Adapter createLabelLayoutTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.LabelStyleTemplateType <em>Label Style Template Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.LabelStyleTemplateType
     * @generated
     */
    public Adapter createLabelStyleTemplateTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.LabelType <em>Label Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.LabelType
     * @generated
     */
    public Adapter createLabelTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.LayoutType <em>Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.LayoutType
     * @generated
     */
    public Adapter createLayoutTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.LineStyleType <em>Line Style Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.LineStyleType
     * @generated
     */
    public Adapter createLineStyleTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.LineType <em>Line Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.LineType
     * @generated
     */
    public Adapter createLineTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.LocationType <em>Location Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.LocationType
     * @generated
     */
    public Adapter createLocationTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.NodeConstraintType <em>Node Constraint Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.NodeConstraintType
     * @generated
     */
    public Adapter createNodeConstraintTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.NodeLayoutType <em>Node Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.NodeLayoutType
     * @generated
     */
    public Adapter createNodeLayoutTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.NodeStyleTemplateType <em>Node Style Template Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.NodeStyleTemplateType
     * @generated
     */
    public Adapter createNodeStyleTemplateTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.NodeType <em>Node Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.NodeType
     * @generated
     */
    public Adapter createNodeTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.NumberType <em>Number Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.NumberType
     * @generated
     */
    public Adapter createNumberTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.OgmlType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.OgmlType
     * @generated
     */
    public Adapter createOgmlTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.PointType <em>Point Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.PointType
     * @generated
     */
    public Adapter createPointTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.SegmentType <em>Segment Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.SegmentType
     * @generated
     */
    public Adapter createSegmentTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.ShapeType1 <em>Shape Type1</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.ShapeType1
     * @generated
     */
    public Adapter createShapeType1Adapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.SourceStyleType <em>Source Style Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.SourceStyleType
     * @generated
     */
    public Adapter createSourceStyleTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.SourceTargetType <em>Source Target Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.SourceTargetType
     * @generated
     */
    public Adapter createSourceTargetTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.StructureType <em>Structure Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.StructureType
     * @generated
     */
    public Adapter createStructureTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.StylesType <em>Styles Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.StylesType
     * @generated
     */
    public Adapter createStylesTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.StyleTemplatesType <em>Style Templates Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.StyleTemplatesType
     * @generated
     */
    public Adapter createStyleTemplatesTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.TargetStyleType <em>Target Style Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.TargetStyleType
     * @generated
     */
    public Adapter createTargetStyleTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.TemplateType <em>Template Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.TemplateType
     * @generated
     */
    public Adapter createTemplateTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.TextType <em>Text Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.TextType
     * @generated
     */
    public Adapter createTextTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link net.ogdf.ogml.UriType <em>Uri Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see net.ogdf.ogml.UriType
     * @generated
     */
    public Adapter createUriTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //OgmlAdapterFactory
