/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.ogdf.ogml.impl;

import net.ogdf.ogml.AlignmentType;
import net.ogdf.ogml.BoolIntType;
import net.ogdf.ogml.BoolTFType;
import net.ogdf.ogml.BoolType;
import net.ogdf.ogml.BooleanType;
import net.ogdf.ogml.ComposedType;
import net.ogdf.ogml.ConstraintsType;
import net.ogdf.ogml.DataType;
import net.ogdf.ogml.DecorationType;
import net.ogdf.ogml.DocumentRoot;
import net.ogdf.ogml.EdgeConstraintType;
import net.ogdf.ogml.EdgeLayoutType;
import net.ogdf.ogml.EdgeStyleTemplateType;
import net.ogdf.ogml.EdgeType;
import net.ogdf.ogml.EndpointStylesType;
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
import net.ogdf.ogml.OgmlFactory;
import net.ogdf.ogml.OgmlPackage;
import net.ogdf.ogml.OgmlType;
import net.ogdf.ogml.PatternType;
import net.ogdf.ogml.PointType;
import net.ogdf.ogml.SegmentType;
import net.ogdf.ogml.ShapeType;
import net.ogdf.ogml.ShapeType1;
import net.ogdf.ogml.SourceStyleType;
import net.ogdf.ogml.SourceTargetType;
import net.ogdf.ogml.StructureType;
import net.ogdf.ogml.StyleTemplatesType;
import net.ogdf.ogml.StylesType;
import net.ogdf.ogml.TargetStyleType;
import net.ogdf.ogml.TemplateType;
import net.ogdf.ogml.TextType;
import net.ogdf.ogml.TransformType;
import net.ogdf.ogml.UriType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OgmlPackageImpl extends EPackageImpl implements OgmlPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass booleanTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass boolTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass composedTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass constraintsTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dataTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass edgeConstraintTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass edgeLayoutTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass edgeStyleTemplateTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass edgeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass endpointTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass fillTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass fontTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass graphStyleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass graphTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass intTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass keysTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass keyTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass keyValueTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelConstraintTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelLayoutTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelStyleTemplateTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass layoutTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass lineStyleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass lineTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass locationTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nodeConstraintTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nodeLayoutTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nodeStyleTemplateTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nodeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass numberTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass ogmlTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass pointTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass segmentTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass shapeType1EClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sourceStyleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass sourceTargetTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass structureTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass stylesTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass styleTemplatesTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass targetStyleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass templateTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass textTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass uriTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum alignmentTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum boolIntTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum boolTFTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum decorationTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum endpointStylesTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum fontStretchTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum fontStyleTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum fontVariantTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum fontWeightTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum lineStyleTypeTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum patternTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum shapeTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum transformTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType alignmentTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType boolIntTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType boolTFTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType decorationTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType endpointStylesTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType fontStretchTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType fontStyleTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType fontVariantTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType fontWeightTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType lineStyleTypeTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType patternTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType shapeTypeObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType transformTypeObjectEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see net.ogdf.ogml.OgmlPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private OgmlPackageImpl() {
        super(eNS_URI, OgmlFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link OgmlPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static OgmlPackage init() {
        if (isInited) return (OgmlPackage)EPackage.Registry.INSTANCE.getEPackage(OgmlPackage.eNS_URI);

        // Obtain or create and register package
        OgmlPackageImpl theOgmlPackage = (OgmlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OgmlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OgmlPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theOgmlPackage.createPackageContents();

        // Initialize created meta-data
        theOgmlPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theOgmlPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(OgmlPackage.eNS_URI, theOgmlPackage);
        return theOgmlPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBooleanType() {
        return booleanTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBooleanType_Name() {
        return (EAttribute)booleanTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBooleanType_Value() {
        return (EAttribute)booleanTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBoolType() {
        return boolTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBoolType_Name() {
        return (EAttribute)boolTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBoolType_Value() {
        return (EAttribute)boolTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComposedType() {
        return composedTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComposedType_Group() {
        return (EAttribute)composedTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComposedType_Number() {
        return (EReference)composedTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComposedType_Int() {
        return (EReference)composedTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComposedType_Boolean() {
        return (EReference)composedTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComposedType_Node() {
        return (EReference)composedTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComposedType_Edge() {
        return (EReference)composedTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComposedType_Label() {
        return (EReference)composedTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComposedType_Composed() {
        return (EReference)composedTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComposedType_Name() {
        return (EAttribute)composedTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConstraintsType() {
        return constraintsTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getConstraintsType_Group() {
        return (EAttribute)constraintsTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConstraintsType_Number() {
        return (EReference)constraintsTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConstraintsType_Int() {
        return (EReference)constraintsTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConstraintsType_Boolean() {
        return (EReference)constraintsTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConstraintsType_NodeRef() {
        return (EReference)constraintsTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConstraintsType_EdgeRef() {
        return (EReference)constraintsTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConstraintsType_LabelRef() {
        return (EReference)constraintsTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConstraintsType_Composed() {
        return (EReference)constraintsTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDataType() {
        return dataTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataType_Value() {
        return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDataType_IdRef() {
        return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDocumentRoot() {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Bool() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Boolean() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Composed() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Constraints() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Data() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Edge() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EdgeConstraint() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EdgeLayout() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_EdgeStyleTemplate() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Endpoint() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Fill() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Font() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_FontStretch() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_FontStyle() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_FontVariant() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_FontWeight() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Graph() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_GraphStyle() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Int() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Key() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Keys() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_KeyValue() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Label() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LabelConstraint() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LabelLayout() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LabelStyleTemplate() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Layout() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Line() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_LineStyle() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_LineStyleType() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Location() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Node() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(34);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NodeConstraint() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(35);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NodeLayout() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(36);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_NodeStyleTemplate() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(37);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Number() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(38);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Ogml() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(39);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDocumentRoot_Pattern() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(40);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Point() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(41);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Segment() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(42);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Shape() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(43);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_SourceStyle() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(44);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_SourceTarget() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(45);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Structure() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(46);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Styles() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(47);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_StyleTemplates() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(48);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_TargetStyle() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(49);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Template() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(50);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Text() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(51);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDocumentRoot_Uri() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(52);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEdgeConstraintType() {
        return edgeConstraintTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdgeConstraintType_IdRef() {
        return (EAttribute)edgeConstraintTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdgeConstraintType_Name() {
        return (EAttribute)edgeConstraintTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEdgeLayoutType() {
        return edgeLayoutTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeLayoutType_Data() {
        return (EReference)edgeLayoutTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeLayoutType_Template() {
        return (EReference)edgeLayoutTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeLayoutType_Line() {
        return (EReference)edgeLayoutTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeLayoutType_SourceStyle() {
        return (EReference)edgeLayoutTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeLayoutType_TargetStyle() {
        return (EReference)edgeLayoutTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeLayoutType_Point() {
        return (EReference)edgeLayoutTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeLayoutType_Segment() {
        return (EReference)edgeLayoutTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdgeLayoutType_IdRef() {
        return (EAttribute)edgeLayoutTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEdgeStyleTemplateType() {
        return edgeStyleTemplateTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeStyleTemplateType_Data() {
        return (EReference)edgeStyleTemplateTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeStyleTemplateType_Template() {
        return (EReference)edgeStyleTemplateTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeStyleTemplateType_Line() {
        return (EReference)edgeStyleTemplateTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeStyleTemplateType_SourceStyle() {
        return (EReference)edgeStyleTemplateTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeStyleTemplateType_TargetStyle() {
        return (EReference)edgeStyleTemplateTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdgeStyleTemplateType_Id() {
        return (EAttribute)edgeStyleTemplateTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEdgeType() {
        return edgeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeType_Data() {
        return (EReference)edgeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeType_Label() {
        return (EReference)edgeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdgeType_Group() {
        return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeType_Source() {
        return (EReference)edgeTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeType_Target() {
        return (EReference)edgeTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEdgeType_Label1() {
        return (EReference)edgeTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEdgeType_Id() {
        return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEndpointType() {
        return endpointTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndpointType_Color() {
        return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndpointType_IdRef() {
        return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndpointType_Port() {
        return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndpointType_Size() {
        return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEndpointType_Style() {
        return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFillType() {
        return fillTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFillType_Color() {
        return (EAttribute)fillTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFillType_Pattern() {
        return (EAttribute)fillTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFillType_PatternColor() {
        return (EAttribute)fillTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFontType() {
        return fontTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFontType_Color() {
        return (EAttribute)fontTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFontType_Family() {
        return (EAttribute)fontTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFontType_Size() {
        return (EAttribute)fontTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFontType_Stretch() {
        return (EAttribute)fontTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFontType_Style() {
        return (EAttribute)fontTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFontType_Variant() {
        return (EAttribute)fontTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFontType_Weight() {
        return (EAttribute)fontTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGraphStyleType() {
        return graphStyleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getGraphStyleType_Data() {
        return (EReference)graphStyleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGraphStyleType_DefaultEdgeTemplate() {
        return (EAttribute)graphStyleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGraphStyleType_DefaultLabelTemplate() {
        return (EAttribute)graphStyleTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGraphStyleType_DefaultNodeTemplate() {
        return (EAttribute)graphStyleTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGraphType() {
        return graphTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getGraphType_Data() {
        return (EReference)graphTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getGraphType_Structure() {
        return (EReference)graphTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getGraphType_Layout() {
        return (EReference)graphTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIntType() {
        return intTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIntType_Name() {
        return (EAttribute)intTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIntType_Value() {
        return (EAttribute)intTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKeysType() {
        return keysTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKeysType_Key() {
        return (EReference)keysTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKeyType() {
        return keyTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getKeyType_KeyValue() {
        return (EReference)keyTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKeyType_Id() {
        return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getKeyValueType() {
        return keyValueTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKeyValueType_Dafault() {
        return (EAttribute)keyValueTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKeyValueType_Name() {
        return (EAttribute)keyValueTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getKeyValueType_Type() {
        return (EAttribute)keyValueTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabelConstraintType() {
        return labelConstraintTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLabelConstraintType_IdRef() {
        return (EAttribute)labelConstraintTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLabelConstraintType_Name() {
        return (EAttribute)labelConstraintTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabelLayoutType() {
        return labelLayoutTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelLayoutType_Data() {
        return (EReference)labelLayoutTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelLayoutType_Template() {
        return (EReference)labelLayoutTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelLayoutType_Location() {
        return (EReference)labelLayoutTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelLayoutType_Text() {
        return (EReference)labelLayoutTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelLayoutType_Font() {
        return (EReference)labelLayoutTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLabelLayoutType_IdRef() {
        return (EAttribute)labelLayoutTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabelStyleTemplateType() {
        return labelStyleTemplateTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelStyleTemplateType_Data() {
        return (EReference)labelStyleTemplateTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelStyleTemplateType_Template() {
        return (EReference)labelStyleTemplateTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelStyleTemplateType_Text() {
        return (EReference)labelStyleTemplateTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelStyleTemplateType_Font() {
        return (EReference)labelStyleTemplateTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLabelStyleTemplateType_Id() {
        return (EAttribute)labelStyleTemplateTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabelType() {
        return labelTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLabelType_Data() {
        return (EReference)labelTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLabelType_Content() {
        return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLabelType_Id() {
        return (EAttribute)labelTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLayoutType() {
        return layoutTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutType_Data() {
        return (EReference)layoutTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutType_StyleTemplates() {
        return (EReference)layoutTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutType_Styles() {
        return (EReference)layoutTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLayoutType_Constraints() {
        return (EReference)layoutTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLineStyleType() {
        return lineStyleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLineStyleType_Color() {
        return (EAttribute)lineStyleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLineStyleType_Style() {
        return (EAttribute)lineStyleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLineStyleType_Width() {
        return (EAttribute)lineStyleTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLineType() {
        return lineTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLineType_Color() {
        return (EAttribute)lineTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLineType_Type() {
        return (EAttribute)lineTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLineType_Width() {
        return (EAttribute)lineTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLocationType() {
        return locationTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLocationType_X() {
        return (EAttribute)locationTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLocationType_Y() {
        return (EAttribute)locationTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLocationType_Z() {
        return (EAttribute)locationTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNodeConstraintType() {
        return nodeConstraintTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeConstraintType_IdRef() {
        return (EAttribute)nodeConstraintTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeConstraintType_Name() {
        return (EAttribute)nodeConstraintTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNodeLayoutType() {
        return nodeLayoutTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeLayoutType_Data() {
        return (EReference)nodeLayoutTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeLayoutType_Template() {
        return (EReference)nodeLayoutTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeLayoutType_Location() {
        return (EReference)nodeLayoutTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeLayoutType_Shape() {
        return (EReference)nodeLayoutTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeLayoutType_Fill() {
        return (EReference)nodeLayoutTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeLayoutType_Line() {
        return (EReference)nodeLayoutTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeLayoutType_IdRef() {
        return (EAttribute)nodeLayoutTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNodeStyleTemplateType() {
        return nodeStyleTemplateTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeStyleTemplateType_Data() {
        return (EReference)nodeStyleTemplateTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeStyleTemplateType_Template() {
        return (EReference)nodeStyleTemplateTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeStyleTemplateType_Shape() {
        return (EReference)nodeStyleTemplateTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeStyleTemplateType_Fill() {
        return (EReference)nodeStyleTemplateTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeStyleTemplateType_Line() {
        return (EReference)nodeStyleTemplateTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeStyleTemplateType_Id() {
        return (EAttribute)nodeStyleTemplateTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNodeType() {
        return nodeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeType_Data() {
        return (EReference)nodeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_Group() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeType_Label() {
        return (EReference)nodeTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNodeType_Node() {
        return (EReference)nodeTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNodeType_Id() {
        return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNumberType() {
        return numberTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNumberType_Name() {
        return (EAttribute)numberTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNumberType_Value() {
        return (EAttribute)numberTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOgmlType() {
        return ogmlTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOgmlType_Keys() {
        return (EReference)ogmlTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getOgmlType_Graph() {
        return (EReference)ogmlTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPointType() {
        return pointTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPointType_Data() {
        return (EReference)pointTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPointType_Id() {
        return (EAttribute)pointTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPointType_X() {
        return (EAttribute)pointTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPointType_Y() {
        return (EAttribute)pointTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPointType_Z() {
        return (EAttribute)pointTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSegmentType() {
        return segmentTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSegmentType_Data() {
        return (EReference)segmentTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSegmentType_Line() {
        return (EReference)segmentTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSegmentType_Endpoint() {
        return (EReference)segmentTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getShapeType1() {
        return shapeType1EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getShapeType1_Height() {
        return (EAttribute)shapeType1EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getShapeType1_Type() {
        return (EAttribute)shapeType1EClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getShapeType1_Uri() {
        return (EAttribute)shapeType1EClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getShapeType1_Width() {
        return (EAttribute)shapeType1EClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSourceStyleType() {
        return sourceStyleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceStyleType_Color() {
        return (EAttribute)sourceStyleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceStyleType_Type() {
        return (EAttribute)sourceStyleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceStyleType_Uri() {
        return (EAttribute)sourceStyleTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceStyleType_Width() {
        return (EAttribute)sourceStyleTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSourceTargetType() {
        return sourceTargetTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSourceTargetType_Data() {
        return (EReference)sourceTargetTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSourceTargetType_Label() {
        return (EReference)sourceTargetTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceTargetType_Id() {
        return (EAttribute)sourceTargetTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSourceTargetType_IdRef() {
        return (EAttribute)sourceTargetTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStructureType() {
        return structureTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStructureType_Group() {
        return (EAttribute)structureTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStructureType_Data() {
        return (EReference)structureTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStructureType_Node() {
        return (EReference)structureTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStructureType_Edge() {
        return (EReference)structureTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStructureType_Label() {
        return (EReference)structureTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStylesType() {
        return stylesTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStylesType_GraphStyle() {
        return (EReference)stylesTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStylesType_Group() {
        return (EAttribute)stylesTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStylesType_Data() {
        return (EReference)stylesTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStylesType_NodeStyle() {
        return (EReference)stylesTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStylesType_EdgeStyle() {
        return (EReference)stylesTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStylesType_LabelStyle() {
        return (EReference)stylesTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getStyleTemplatesType() {
        return styleTemplatesTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getStyleTemplatesType_Group() {
        return (EAttribute)styleTemplatesTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStyleTemplatesType_Data() {
        return (EReference)styleTemplatesTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStyleTemplatesType_NodeStyleTemplate() {
        return (EReference)styleTemplatesTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStyleTemplatesType_EdgeStyleTemplate() {
        return (EReference)styleTemplatesTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getStyleTemplatesType_LabelStyleTemplate() {
        return (EReference)styleTemplatesTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTargetStyleType() {
        return targetStyleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTargetStyleType_Color() {
        return (EAttribute)targetStyleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTargetStyleType_Type() {
        return (EAttribute)targetStyleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTargetStyleType_Uri() {
        return (EAttribute)targetStyleTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTargetStyleType_Width() {
        return (EAttribute)targetStyleTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTemplateType() {
        return templateTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTemplateType_IdRef() {
        return (EAttribute)templateTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTextType() {
        return textTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTextType_Alignment() {
        return (EAttribute)textTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTextType_Decoration() {
        return (EAttribute)textTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTextType_Rotation() {
        return (EAttribute)textTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTextType_Transform() {
        return (EAttribute)textTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUriType() {
        return uriTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getUriType_Uri() {
        return (EAttribute)uriTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getAlignmentType() {
        return alignmentTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getBoolIntType() {
        return boolIntTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getBoolTFType() {
        return boolTFTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getDecorationType() {
        return decorationTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getEndpointStylesType() {
        return endpointStylesTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFontStretchType() {
        return fontStretchTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFontStyleType() {
        return fontStyleTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFontVariantType() {
        return fontVariantTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFontWeightType() {
        return fontWeightTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getLineStyleTypeType() {
        return lineStyleTypeTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getPatternType() {
        return patternTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getShapeType() {
        return shapeTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getTransformType() {
        return transformTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getAlignmentTypeObject() {
        return alignmentTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getBoolIntTypeObject() {
        return boolIntTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getBoolTFTypeObject() {
        return boolTFTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getDecorationTypeObject() {
        return decorationTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getEndpointStylesTypeObject() {
        return endpointStylesTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getFontStretchTypeObject() {
        return fontStretchTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getFontStyleTypeObject() {
        return fontStyleTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getFontVariantTypeObject() {
        return fontVariantTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getFontWeightTypeObject() {
        return fontWeightTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getLineStyleTypeTypeObject() {
        return lineStyleTypeTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getPatternTypeObject() {
        return patternTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getShapeTypeObject() {
        return shapeTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getTransformTypeObject() {
        return transformTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OgmlFactory getOgmlFactory() {
        return (OgmlFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        booleanTypeEClass = createEClass(BOOLEAN_TYPE);
        createEAttribute(booleanTypeEClass, BOOLEAN_TYPE__NAME);
        createEAttribute(booleanTypeEClass, BOOLEAN_TYPE__VALUE);

        boolTypeEClass = createEClass(BOOL_TYPE);
        createEAttribute(boolTypeEClass, BOOL_TYPE__NAME);
        createEAttribute(boolTypeEClass, BOOL_TYPE__VALUE);

        composedTypeEClass = createEClass(COMPOSED_TYPE);
        createEAttribute(composedTypeEClass, COMPOSED_TYPE__GROUP);
        createEReference(composedTypeEClass, COMPOSED_TYPE__NUMBER);
        createEReference(composedTypeEClass, COMPOSED_TYPE__INT);
        createEReference(composedTypeEClass, COMPOSED_TYPE__BOOLEAN);
        createEReference(composedTypeEClass, COMPOSED_TYPE__NODE);
        createEReference(composedTypeEClass, COMPOSED_TYPE__EDGE);
        createEReference(composedTypeEClass, COMPOSED_TYPE__LABEL);
        createEReference(composedTypeEClass, COMPOSED_TYPE__COMPOSED);
        createEAttribute(composedTypeEClass, COMPOSED_TYPE__NAME);

        constraintsTypeEClass = createEClass(CONSTRAINTS_TYPE);
        createEAttribute(constraintsTypeEClass, CONSTRAINTS_TYPE__GROUP);
        createEReference(constraintsTypeEClass, CONSTRAINTS_TYPE__NUMBER);
        createEReference(constraintsTypeEClass, CONSTRAINTS_TYPE__INT);
        createEReference(constraintsTypeEClass, CONSTRAINTS_TYPE__BOOLEAN);
        createEReference(constraintsTypeEClass, CONSTRAINTS_TYPE__NODE_REF);
        createEReference(constraintsTypeEClass, CONSTRAINTS_TYPE__EDGE_REF);
        createEReference(constraintsTypeEClass, CONSTRAINTS_TYPE__LABEL_REF);
        createEReference(constraintsTypeEClass, CONSTRAINTS_TYPE__COMPOSED);

        dataTypeEClass = createEClass(DATA_TYPE);
        createEAttribute(dataTypeEClass, DATA_TYPE__VALUE);
        createEAttribute(dataTypeEClass, DATA_TYPE__ID_REF);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BOOL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BOOLEAN);
        createEReference(documentRootEClass, DOCUMENT_ROOT__COMPOSED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONSTRAINTS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DATA);
        createEReference(documentRootEClass, DOCUMENT_ROOT__EDGE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__EDGE_CONSTRAINT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__EDGE_LAYOUT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__EDGE_STYLE_TEMPLATE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ENDPOINT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FILL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FONT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__FONT_STRETCH);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__FONT_STYLE);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__FONT_VARIANT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__FONT_WEIGHT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__GRAPH);
        createEReference(documentRootEClass, DOCUMENT_ROOT__GRAPH_STYLE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__INT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__KEY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__KEYS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__KEY_VALUE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABEL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABEL_CONSTRAINT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABEL_LAYOUT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABEL_STYLE_TEMPLATE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LAYOUT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LINE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LINE_STYLE);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__LINE_STYLE_TYPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NODE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NODE_CONSTRAINT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NODE_LAYOUT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NODE_STYLE_TEMPLATE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NUMBER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__OGML);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__PATTERN);
        createEReference(documentRootEClass, DOCUMENT_ROOT__POINT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SEGMENT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SHAPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SOURCE_STYLE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SOURCE_TARGET);
        createEReference(documentRootEClass, DOCUMENT_ROOT__STRUCTURE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__STYLES);
        createEReference(documentRootEClass, DOCUMENT_ROOT__STYLE_TEMPLATES);
        createEReference(documentRootEClass, DOCUMENT_ROOT__TARGET_STYLE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__TEMPLATE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__TEXT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__URI);

        edgeConstraintTypeEClass = createEClass(EDGE_CONSTRAINT_TYPE);
        createEAttribute(edgeConstraintTypeEClass, EDGE_CONSTRAINT_TYPE__ID_REF);
        createEAttribute(edgeConstraintTypeEClass, EDGE_CONSTRAINT_TYPE__NAME);

        edgeLayoutTypeEClass = createEClass(EDGE_LAYOUT_TYPE);
        createEReference(edgeLayoutTypeEClass, EDGE_LAYOUT_TYPE__DATA);
        createEReference(edgeLayoutTypeEClass, EDGE_LAYOUT_TYPE__TEMPLATE);
        createEReference(edgeLayoutTypeEClass, EDGE_LAYOUT_TYPE__LINE);
        createEReference(edgeLayoutTypeEClass, EDGE_LAYOUT_TYPE__SOURCE_STYLE);
        createEReference(edgeLayoutTypeEClass, EDGE_LAYOUT_TYPE__TARGET_STYLE);
        createEReference(edgeLayoutTypeEClass, EDGE_LAYOUT_TYPE__POINT);
        createEReference(edgeLayoutTypeEClass, EDGE_LAYOUT_TYPE__SEGMENT);
        createEAttribute(edgeLayoutTypeEClass, EDGE_LAYOUT_TYPE__ID_REF);

        edgeStyleTemplateTypeEClass = createEClass(EDGE_STYLE_TEMPLATE_TYPE);
        createEReference(edgeStyleTemplateTypeEClass, EDGE_STYLE_TEMPLATE_TYPE__DATA);
        createEReference(edgeStyleTemplateTypeEClass, EDGE_STYLE_TEMPLATE_TYPE__TEMPLATE);
        createEReference(edgeStyleTemplateTypeEClass, EDGE_STYLE_TEMPLATE_TYPE__LINE);
        createEReference(edgeStyleTemplateTypeEClass, EDGE_STYLE_TEMPLATE_TYPE__SOURCE_STYLE);
        createEReference(edgeStyleTemplateTypeEClass, EDGE_STYLE_TEMPLATE_TYPE__TARGET_STYLE);
        createEAttribute(edgeStyleTemplateTypeEClass, EDGE_STYLE_TEMPLATE_TYPE__ID);

        edgeTypeEClass = createEClass(EDGE_TYPE);
        createEReference(edgeTypeEClass, EDGE_TYPE__DATA);
        createEReference(edgeTypeEClass, EDGE_TYPE__LABEL);
        createEAttribute(edgeTypeEClass, EDGE_TYPE__GROUP);
        createEReference(edgeTypeEClass, EDGE_TYPE__SOURCE);
        createEReference(edgeTypeEClass, EDGE_TYPE__TARGET);
        createEReference(edgeTypeEClass, EDGE_TYPE__LABEL1);
        createEAttribute(edgeTypeEClass, EDGE_TYPE__ID);

        endpointTypeEClass = createEClass(ENDPOINT_TYPE);
        createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__COLOR);
        createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__ID_REF);
        createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__PORT);
        createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__SIZE);
        createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__STYLE);

        fillTypeEClass = createEClass(FILL_TYPE);
        createEAttribute(fillTypeEClass, FILL_TYPE__COLOR);
        createEAttribute(fillTypeEClass, FILL_TYPE__PATTERN);
        createEAttribute(fillTypeEClass, FILL_TYPE__PATTERN_COLOR);

        fontTypeEClass = createEClass(FONT_TYPE);
        createEAttribute(fontTypeEClass, FONT_TYPE__COLOR);
        createEAttribute(fontTypeEClass, FONT_TYPE__FAMILY);
        createEAttribute(fontTypeEClass, FONT_TYPE__SIZE);
        createEAttribute(fontTypeEClass, FONT_TYPE__STRETCH);
        createEAttribute(fontTypeEClass, FONT_TYPE__STYLE);
        createEAttribute(fontTypeEClass, FONT_TYPE__VARIANT);
        createEAttribute(fontTypeEClass, FONT_TYPE__WEIGHT);

        graphStyleTypeEClass = createEClass(GRAPH_STYLE_TYPE);
        createEReference(graphStyleTypeEClass, GRAPH_STYLE_TYPE__DATA);
        createEAttribute(graphStyleTypeEClass, GRAPH_STYLE_TYPE__DEFAULT_EDGE_TEMPLATE);
        createEAttribute(graphStyleTypeEClass, GRAPH_STYLE_TYPE__DEFAULT_LABEL_TEMPLATE);
        createEAttribute(graphStyleTypeEClass, GRAPH_STYLE_TYPE__DEFAULT_NODE_TEMPLATE);

        graphTypeEClass = createEClass(GRAPH_TYPE);
        createEReference(graphTypeEClass, GRAPH_TYPE__DATA);
        createEReference(graphTypeEClass, GRAPH_TYPE__STRUCTURE);
        createEReference(graphTypeEClass, GRAPH_TYPE__LAYOUT);

        intTypeEClass = createEClass(INT_TYPE);
        createEAttribute(intTypeEClass, INT_TYPE__NAME);
        createEAttribute(intTypeEClass, INT_TYPE__VALUE);

        keysTypeEClass = createEClass(KEYS_TYPE);
        createEReference(keysTypeEClass, KEYS_TYPE__KEY);

        keyTypeEClass = createEClass(KEY_TYPE);
        createEReference(keyTypeEClass, KEY_TYPE__KEY_VALUE);
        createEAttribute(keyTypeEClass, KEY_TYPE__ID);

        keyValueTypeEClass = createEClass(KEY_VALUE_TYPE);
        createEAttribute(keyValueTypeEClass, KEY_VALUE_TYPE__DAFAULT);
        createEAttribute(keyValueTypeEClass, KEY_VALUE_TYPE__NAME);
        createEAttribute(keyValueTypeEClass, KEY_VALUE_TYPE__TYPE);

        labelConstraintTypeEClass = createEClass(LABEL_CONSTRAINT_TYPE);
        createEAttribute(labelConstraintTypeEClass, LABEL_CONSTRAINT_TYPE__ID_REF);
        createEAttribute(labelConstraintTypeEClass, LABEL_CONSTRAINT_TYPE__NAME);

        labelLayoutTypeEClass = createEClass(LABEL_LAYOUT_TYPE);
        createEReference(labelLayoutTypeEClass, LABEL_LAYOUT_TYPE__DATA);
        createEReference(labelLayoutTypeEClass, LABEL_LAYOUT_TYPE__TEMPLATE);
        createEReference(labelLayoutTypeEClass, LABEL_LAYOUT_TYPE__LOCATION);
        createEReference(labelLayoutTypeEClass, LABEL_LAYOUT_TYPE__TEXT);
        createEReference(labelLayoutTypeEClass, LABEL_LAYOUT_TYPE__FONT);
        createEAttribute(labelLayoutTypeEClass, LABEL_LAYOUT_TYPE__ID_REF);

        labelStyleTemplateTypeEClass = createEClass(LABEL_STYLE_TEMPLATE_TYPE);
        createEReference(labelStyleTemplateTypeEClass, LABEL_STYLE_TEMPLATE_TYPE__DATA);
        createEReference(labelStyleTemplateTypeEClass, LABEL_STYLE_TEMPLATE_TYPE__TEMPLATE);
        createEReference(labelStyleTemplateTypeEClass, LABEL_STYLE_TEMPLATE_TYPE__TEXT);
        createEReference(labelStyleTemplateTypeEClass, LABEL_STYLE_TEMPLATE_TYPE__FONT);
        createEAttribute(labelStyleTemplateTypeEClass, LABEL_STYLE_TEMPLATE_TYPE__ID);

        labelTypeEClass = createEClass(LABEL_TYPE);
        createEReference(labelTypeEClass, LABEL_TYPE__DATA);
        createEAttribute(labelTypeEClass, LABEL_TYPE__CONTENT);
        createEAttribute(labelTypeEClass, LABEL_TYPE__ID);

        layoutTypeEClass = createEClass(LAYOUT_TYPE);
        createEReference(layoutTypeEClass, LAYOUT_TYPE__DATA);
        createEReference(layoutTypeEClass, LAYOUT_TYPE__STYLE_TEMPLATES);
        createEReference(layoutTypeEClass, LAYOUT_TYPE__STYLES);
        createEReference(layoutTypeEClass, LAYOUT_TYPE__CONSTRAINTS);

        lineStyleTypeEClass = createEClass(LINE_STYLE_TYPE);
        createEAttribute(lineStyleTypeEClass, LINE_STYLE_TYPE__COLOR);
        createEAttribute(lineStyleTypeEClass, LINE_STYLE_TYPE__STYLE);
        createEAttribute(lineStyleTypeEClass, LINE_STYLE_TYPE__WIDTH);

        lineTypeEClass = createEClass(LINE_TYPE);
        createEAttribute(lineTypeEClass, LINE_TYPE__COLOR);
        createEAttribute(lineTypeEClass, LINE_TYPE__TYPE);
        createEAttribute(lineTypeEClass, LINE_TYPE__WIDTH);

        locationTypeEClass = createEClass(LOCATION_TYPE);
        createEAttribute(locationTypeEClass, LOCATION_TYPE__X);
        createEAttribute(locationTypeEClass, LOCATION_TYPE__Y);
        createEAttribute(locationTypeEClass, LOCATION_TYPE__Z);

        nodeConstraintTypeEClass = createEClass(NODE_CONSTRAINT_TYPE);
        createEAttribute(nodeConstraintTypeEClass, NODE_CONSTRAINT_TYPE__ID_REF);
        createEAttribute(nodeConstraintTypeEClass, NODE_CONSTRAINT_TYPE__NAME);

        nodeLayoutTypeEClass = createEClass(NODE_LAYOUT_TYPE);
        createEReference(nodeLayoutTypeEClass, NODE_LAYOUT_TYPE__DATA);
        createEReference(nodeLayoutTypeEClass, NODE_LAYOUT_TYPE__TEMPLATE);
        createEReference(nodeLayoutTypeEClass, NODE_LAYOUT_TYPE__LOCATION);
        createEReference(nodeLayoutTypeEClass, NODE_LAYOUT_TYPE__SHAPE);
        createEReference(nodeLayoutTypeEClass, NODE_LAYOUT_TYPE__FILL);
        createEReference(nodeLayoutTypeEClass, NODE_LAYOUT_TYPE__LINE);
        createEAttribute(nodeLayoutTypeEClass, NODE_LAYOUT_TYPE__ID_REF);

        nodeStyleTemplateTypeEClass = createEClass(NODE_STYLE_TEMPLATE_TYPE);
        createEReference(nodeStyleTemplateTypeEClass, NODE_STYLE_TEMPLATE_TYPE__DATA);
        createEReference(nodeStyleTemplateTypeEClass, NODE_STYLE_TEMPLATE_TYPE__TEMPLATE);
        createEReference(nodeStyleTemplateTypeEClass, NODE_STYLE_TEMPLATE_TYPE__SHAPE);
        createEReference(nodeStyleTemplateTypeEClass, NODE_STYLE_TEMPLATE_TYPE__FILL);
        createEReference(nodeStyleTemplateTypeEClass, NODE_STYLE_TEMPLATE_TYPE__LINE);
        createEAttribute(nodeStyleTemplateTypeEClass, NODE_STYLE_TEMPLATE_TYPE__ID);

        nodeTypeEClass = createEClass(NODE_TYPE);
        createEReference(nodeTypeEClass, NODE_TYPE__DATA);
        createEAttribute(nodeTypeEClass, NODE_TYPE__GROUP);
        createEReference(nodeTypeEClass, NODE_TYPE__LABEL);
        createEReference(nodeTypeEClass, NODE_TYPE__NODE);
        createEAttribute(nodeTypeEClass, NODE_TYPE__ID);

        numberTypeEClass = createEClass(NUMBER_TYPE);
        createEAttribute(numberTypeEClass, NUMBER_TYPE__NAME);
        createEAttribute(numberTypeEClass, NUMBER_TYPE__VALUE);

        ogmlTypeEClass = createEClass(OGML_TYPE);
        createEReference(ogmlTypeEClass, OGML_TYPE__KEYS);
        createEReference(ogmlTypeEClass, OGML_TYPE__GRAPH);

        pointTypeEClass = createEClass(POINT_TYPE);
        createEReference(pointTypeEClass, POINT_TYPE__DATA);
        createEAttribute(pointTypeEClass, POINT_TYPE__ID);
        createEAttribute(pointTypeEClass, POINT_TYPE__X);
        createEAttribute(pointTypeEClass, POINT_TYPE__Y);
        createEAttribute(pointTypeEClass, POINT_TYPE__Z);

        segmentTypeEClass = createEClass(SEGMENT_TYPE);
        createEReference(segmentTypeEClass, SEGMENT_TYPE__DATA);
        createEReference(segmentTypeEClass, SEGMENT_TYPE__LINE);
        createEReference(segmentTypeEClass, SEGMENT_TYPE__ENDPOINT);

        shapeType1EClass = createEClass(SHAPE_TYPE1);
        createEAttribute(shapeType1EClass, SHAPE_TYPE1__HEIGHT);
        createEAttribute(shapeType1EClass, SHAPE_TYPE1__TYPE);
        createEAttribute(shapeType1EClass, SHAPE_TYPE1__URI);
        createEAttribute(shapeType1EClass, SHAPE_TYPE1__WIDTH);

        sourceStyleTypeEClass = createEClass(SOURCE_STYLE_TYPE);
        createEAttribute(sourceStyleTypeEClass, SOURCE_STYLE_TYPE__COLOR);
        createEAttribute(sourceStyleTypeEClass, SOURCE_STYLE_TYPE__TYPE);
        createEAttribute(sourceStyleTypeEClass, SOURCE_STYLE_TYPE__URI);
        createEAttribute(sourceStyleTypeEClass, SOURCE_STYLE_TYPE__WIDTH);

        sourceTargetTypeEClass = createEClass(SOURCE_TARGET_TYPE);
        createEReference(sourceTargetTypeEClass, SOURCE_TARGET_TYPE__DATA);
        createEReference(sourceTargetTypeEClass, SOURCE_TARGET_TYPE__LABEL);
        createEAttribute(sourceTargetTypeEClass, SOURCE_TARGET_TYPE__ID);
        createEAttribute(sourceTargetTypeEClass, SOURCE_TARGET_TYPE__ID_REF);

        structureTypeEClass = createEClass(STRUCTURE_TYPE);
        createEAttribute(structureTypeEClass, STRUCTURE_TYPE__GROUP);
        createEReference(structureTypeEClass, STRUCTURE_TYPE__DATA);
        createEReference(structureTypeEClass, STRUCTURE_TYPE__NODE);
        createEReference(structureTypeEClass, STRUCTURE_TYPE__EDGE);
        createEReference(structureTypeEClass, STRUCTURE_TYPE__LABEL);

        stylesTypeEClass = createEClass(STYLES_TYPE);
        createEReference(stylesTypeEClass, STYLES_TYPE__GRAPH_STYLE);
        createEAttribute(stylesTypeEClass, STYLES_TYPE__GROUP);
        createEReference(stylesTypeEClass, STYLES_TYPE__DATA);
        createEReference(stylesTypeEClass, STYLES_TYPE__NODE_STYLE);
        createEReference(stylesTypeEClass, STYLES_TYPE__EDGE_STYLE);
        createEReference(stylesTypeEClass, STYLES_TYPE__LABEL_STYLE);

        styleTemplatesTypeEClass = createEClass(STYLE_TEMPLATES_TYPE);
        createEAttribute(styleTemplatesTypeEClass, STYLE_TEMPLATES_TYPE__GROUP);
        createEReference(styleTemplatesTypeEClass, STYLE_TEMPLATES_TYPE__DATA);
        createEReference(styleTemplatesTypeEClass, STYLE_TEMPLATES_TYPE__NODE_STYLE_TEMPLATE);
        createEReference(styleTemplatesTypeEClass, STYLE_TEMPLATES_TYPE__EDGE_STYLE_TEMPLATE);
        createEReference(styleTemplatesTypeEClass, STYLE_TEMPLATES_TYPE__LABEL_STYLE_TEMPLATE);

        targetStyleTypeEClass = createEClass(TARGET_STYLE_TYPE);
        createEAttribute(targetStyleTypeEClass, TARGET_STYLE_TYPE__COLOR);
        createEAttribute(targetStyleTypeEClass, TARGET_STYLE_TYPE__TYPE);
        createEAttribute(targetStyleTypeEClass, TARGET_STYLE_TYPE__URI);
        createEAttribute(targetStyleTypeEClass, TARGET_STYLE_TYPE__WIDTH);

        templateTypeEClass = createEClass(TEMPLATE_TYPE);
        createEAttribute(templateTypeEClass, TEMPLATE_TYPE__ID_REF);

        textTypeEClass = createEClass(TEXT_TYPE);
        createEAttribute(textTypeEClass, TEXT_TYPE__ALIGNMENT);
        createEAttribute(textTypeEClass, TEXT_TYPE__DECORATION);
        createEAttribute(textTypeEClass, TEXT_TYPE__ROTATION);
        createEAttribute(textTypeEClass, TEXT_TYPE__TRANSFORM);

        uriTypeEClass = createEClass(URI_TYPE);
        createEAttribute(uriTypeEClass, URI_TYPE__URI);

        // Create enums
        alignmentTypeEEnum = createEEnum(ALIGNMENT_TYPE);
        boolIntTypeEEnum = createEEnum(BOOL_INT_TYPE);
        boolTFTypeEEnum = createEEnum(BOOL_TF_TYPE);
        decorationTypeEEnum = createEEnum(DECORATION_TYPE);
        endpointStylesTypeEEnum = createEEnum(ENDPOINT_STYLES_TYPE);
        fontStretchTypeEEnum = createEEnum(FONT_STRETCH_TYPE);
        fontStyleTypeEEnum = createEEnum(FONT_STYLE_TYPE);
        fontVariantTypeEEnum = createEEnum(FONT_VARIANT_TYPE);
        fontWeightTypeEEnum = createEEnum(FONT_WEIGHT_TYPE);
        lineStyleTypeTypeEEnum = createEEnum(LINE_STYLE_TYPE_TYPE);
        patternTypeEEnum = createEEnum(PATTERN_TYPE);
        shapeTypeEEnum = createEEnum(SHAPE_TYPE);
        transformTypeEEnum = createEEnum(TRANSFORM_TYPE);

        // Create data types
        alignmentTypeObjectEDataType = createEDataType(ALIGNMENT_TYPE_OBJECT);
        boolIntTypeObjectEDataType = createEDataType(BOOL_INT_TYPE_OBJECT);
        boolTFTypeObjectEDataType = createEDataType(BOOL_TF_TYPE_OBJECT);
        decorationTypeObjectEDataType = createEDataType(DECORATION_TYPE_OBJECT);
        endpointStylesTypeObjectEDataType = createEDataType(ENDPOINT_STYLES_TYPE_OBJECT);
        fontStretchTypeObjectEDataType = createEDataType(FONT_STRETCH_TYPE_OBJECT);
        fontStyleTypeObjectEDataType = createEDataType(FONT_STYLE_TYPE_OBJECT);
        fontVariantTypeObjectEDataType = createEDataType(FONT_VARIANT_TYPE_OBJECT);
        fontWeightTypeObjectEDataType = createEDataType(FONT_WEIGHT_TYPE_OBJECT);
        lineStyleTypeTypeObjectEDataType = createEDataType(LINE_STYLE_TYPE_TYPE_OBJECT);
        patternTypeObjectEDataType = createEDataType(PATTERN_TYPE_OBJECT);
        shapeTypeObjectEDataType = createEDataType(SHAPE_TYPE_OBJECT);
        transformTypeObjectEDataType = createEDataType(TRANSFORM_TYPE_OBJECT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(booleanTypeEClass, BooleanType.class, "BooleanType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBooleanType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, BooleanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBooleanType_Value(), this.getBoolIntType(), "value", null, 0, 1, BooleanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(boolTypeEClass, BoolType.class, "BoolType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBoolType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, BoolType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBoolType_Value(), this.getBoolTFType(), "value", null, 0, 1, BoolType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(composedTypeEClass, ComposedType.class, "ComposedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComposedType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ComposedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComposedType_Number(), this.getNumberType(), null, "number", null, 0, -1, ComposedType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getComposedType_Int(), this.getIntType(), null, "int", null, 0, -1, ComposedType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getComposedType_Boolean(), this.getBooleanType(), null, "boolean", null, 0, -1, ComposedType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getComposedType_Node(), this.getNodeConstraintType(), null, "node", null, 0, -1, ComposedType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getComposedType_Edge(), this.getEdgeConstraintType(), null, "edge", null, 0, -1, ComposedType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getComposedType_Label(), this.getLabelConstraintType(), null, "label", null, 0, -1, ComposedType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getComposedType_Composed(), this.getComposedType(), null, "composed", null, 0, -1, ComposedType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getComposedType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ComposedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(constraintsTypeEClass, ConstraintsType.class, "ConstraintsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConstraintsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ConstraintsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConstraintsType_Number(), this.getNumberType(), null, "number", null, 0, -1, ConstraintsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getConstraintsType_Int(), this.getIntType(), null, "int", null, 0, -1, ConstraintsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getConstraintsType_Boolean(), this.getBooleanType(), null, "boolean", null, 0, -1, ConstraintsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getConstraintsType_NodeRef(), this.getNodeConstraintType(), null, "nodeRef", null, 0, -1, ConstraintsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getConstraintsType_EdgeRef(), this.getEdgeConstraintType(), null, "edgeRef", null, 0, -1, ConstraintsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getConstraintsType_LabelRef(), this.getLabelConstraintType(), null, "labelRef", null, 0, -1, ConstraintsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getConstraintsType_Composed(), this.getComposedType(), null, "composed", null, 0, -1, ConstraintsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(dataTypeEClass, DataType.class, "DataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDataType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDataType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Bool(), this.getBoolType(), null, "bool", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Boolean(), this.getBooleanType(), null, "boolean", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Composed(), this.getComposedType(), null, "composed", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Constraints(), this.getConstraintsType(), null, "constraints", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Data(), this.getDataType(), null, "data", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Edge(), this.getEdgeType(), null, "edge", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_EdgeConstraint(), this.getEdgeConstraintType(), null, "edgeConstraint", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_EdgeLayout(), this.getEdgeLayoutType(), null, "edgeLayout", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_EdgeStyleTemplate(), this.getEdgeStyleTemplateType(), null, "edgeStyleTemplate", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Endpoint(), this.getEndpointType(), null, "endpoint", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Fill(), this.getFillType(), null, "fill", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Font(), this.getFontType(), null, "font", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentRoot_FontStretch(), this.getFontStretchType(), "fontStretch", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentRoot_FontStyle(), this.getFontStyleType(), "fontStyle", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentRoot_FontVariant(), this.getFontVariantType(), "fontVariant", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentRoot_FontWeight(), this.getFontWeightType(), "fontWeight", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Graph(), this.getGraphType(), null, "graph", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_GraphStyle(), this.getGraphStyleType(), null, "graphStyle", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Int(), this.getIntType(), null, "int", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Key(), this.getKeyType(), null, "key", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Keys(), this.getKeysType(), null, "keys", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_KeyValue(), this.getKeyValueType(), null, "keyValue", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Label(), this.getLabelType(), null, "label", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_LabelConstraint(), this.getLabelConstraintType(), null, "labelConstraint", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_LabelLayout(), this.getLabelLayoutType(), null, "labelLayout", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_LabelStyleTemplate(), this.getLabelStyleTemplateType(), null, "labelStyleTemplate", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Layout(), this.getLayoutType(), null, "layout", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Line(), this.getLineType(), null, "line", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_LineStyle(), this.getLineStyleType(), null, "lineStyle", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentRoot_LineStyleType(), this.getLineStyleTypeType(), "lineStyleType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Location(), this.getLocationType(), null, "location", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Node(), this.getNodeType(), null, "node", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_NodeConstraint(), this.getNodeConstraintType(), null, "nodeConstraint", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_NodeLayout(), this.getNodeLayoutType(), null, "nodeLayout", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_NodeStyleTemplate(), this.getNodeStyleTemplateType(), null, "nodeStyleTemplate", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Number(), this.getNumberType(), null, "number", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Ogml(), this.getOgmlType(), null, "ogml", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentRoot_Pattern(), this.getPatternType(), "pattern", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Point(), this.getPointType(), null, "point", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Segment(), this.getSegmentType(), null, "segment", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Shape(), this.getShapeType1(), null, "shape", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_SourceStyle(), this.getSourceStyleType(), null, "sourceStyle", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_SourceTarget(), this.getSourceTargetType(), null, "sourceTarget", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Structure(), this.getStructureType(), null, "structure", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Styles(), this.getStylesType(), null, "styles", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_StyleTemplates(), this.getStyleTemplatesType(), null, "styleTemplates", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_TargetStyle(), this.getTargetStyleType(), null, "targetStyle", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Template(), this.getTemplateType(), null, "template", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Text(), this.getTextType(), null, "text", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Uri(), this.getUriType(), null, "uri", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(edgeConstraintTypeEClass, EdgeConstraintType.class, "EdgeConstraintType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEdgeConstraintType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 1, 1, EdgeConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeConstraintType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, EdgeConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeLayoutTypeEClass, EdgeLayoutType.class, "EdgeLayoutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeLayoutType_Data(), this.getDataType(), null, "data", null, 0, -1, EdgeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeLayoutType_Template(), this.getTemplateType(), null, "template", null, 0, 1, EdgeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeLayoutType_Line(), this.getLineType(), null, "line", null, 0, 1, EdgeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeLayoutType_SourceStyle(), this.getSourceStyleType(), null, "sourceStyle", null, 0, 1, EdgeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeLayoutType_TargetStyle(), this.getTargetStyleType(), null, "targetStyle", null, 0, 1, EdgeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeLayoutType_Point(), this.getPointType(), null, "point", null, 0, -1, EdgeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeLayoutType_Segment(), this.getSegmentType(), null, "segment", null, 0, -1, EdgeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeLayoutType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 0, 1, EdgeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeStyleTemplateTypeEClass, EdgeStyleTemplateType.class, "EdgeStyleTemplateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeStyleTemplateType_Data(), this.getDataType(), null, "data", null, 0, -1, EdgeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleTemplateType_Template(), this.getTemplateType(), null, "template", null, 0, 1, EdgeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleTemplateType_Line(), this.getLineType(), null, "line", null, 0, 1, EdgeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleTemplateType_SourceStyle(), this.getSourceStyleType(), null, "sourceStyle", null, 0, 1, EdgeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleTemplateType_TargetStyle(), this.getTargetStyleType(), null, "targetStyle", null, 0, 1, EdgeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleTemplateType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, EdgeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeTypeEClass, EdgeType.class, "EdgeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeType_Data(), this.getDataType(), null, "data", null, 0, -1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeType_Label(), this.getLabelType(), null, "label", null, 0, -1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeType_Source(), this.getSourceTargetType(), null, "source", null, 0, -1, EdgeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeType_Target(), this.getSourceTargetType(), null, "target", null, 0, -1, EdgeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeType_Label1(), this.getLabelType(), null, "label1", null, 0, -1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(endpointTypeEClass, EndpointType.class, "EndpointType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEndpointType_Color(), theXMLTypePackage.getString(), "color", null, 0, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEndpointType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 1, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEndpointType_Port(), theXMLTypePackage.getInteger(), "port", null, 0, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEndpointType_Size(), theXMLTypePackage.getDouble(), "size", null, 0, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEndpointType_Style(), this.getEndpointStylesType(), "style", null, 0, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fillTypeEClass, FillType.class, "FillType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFillType_Color(), theXMLTypePackage.getString(), "color", null, 0, 1, FillType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFillType_Pattern(), this.getPatternType(), "pattern", null, 0, 1, FillType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFillType_PatternColor(), theXMLTypePackage.getString(), "patternColor", null, 0, 1, FillType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(fontTypeEClass, FontType.class, "FontType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFontType_Color(), theXMLTypePackage.getString(), "color", null, 1, 1, FontType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFontType_Family(), theXMLTypePackage.getString(), "family", null, 0, 1, FontType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFontType_Size(), theXMLTypePackage.getInteger(), "size", null, 0, 1, FontType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFontType_Stretch(), this.getFontStretchType(), "stretch", null, 0, 1, FontType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFontType_Style(), this.getFontStyleType(), "style", null, 0, 1, FontType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFontType_Variant(), this.getFontVariantType(), "variant", null, 0, 1, FontType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFontType_Weight(), this.getFontWeightType(), "weight", null, 0, 1, FontType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(graphStyleTypeEClass, GraphStyleType.class, "GraphStyleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGraphStyleType_Data(), this.getDataType(), null, "data", null, 0, -1, GraphStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGraphStyleType_DefaultEdgeTemplate(), theXMLTypePackage.getIDREF(), "defaultEdgeTemplate", null, 0, 1, GraphStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGraphStyleType_DefaultLabelTemplate(), theXMLTypePackage.getIDREF(), "defaultLabelTemplate", null, 0, 1, GraphStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGraphStyleType_DefaultNodeTemplate(), theXMLTypePackage.getIDREF(), "defaultNodeTemplate", null, 0, 1, GraphStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(graphTypeEClass, GraphType.class, "GraphType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGraphType_Data(), this.getDataType(), null, "data", null, 0, -1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGraphType_Structure(), this.getStructureType(), null, "structure", null, 1, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGraphType_Layout(), this.getLayoutType(), null, "layout", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(intTypeEClass, IntType.class, "IntType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIntType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, IntType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntType_Value(), theXMLTypePackage.getInteger(), "value", null, 0, 1, IntType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(keysTypeEClass, KeysType.class, "KeysType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKeysType_Key(), this.getKeyType(), null, "key", null, 0, -1, KeysType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(keyTypeEClass, KeyType.class, "KeyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getKeyType_KeyValue(), this.getKeyValueType(), null, "keyValue", null, 1, -1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKeyType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(keyValueTypeEClass, KeyValueType.class, "KeyValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getKeyValueType_Dafault(), theXMLTypePackage.getString(), "dafault", null, 0, 1, KeyValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKeyValueType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, KeyValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getKeyValueType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, KeyValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelConstraintTypeEClass, LabelConstraintType.class, "LabelConstraintType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelConstraintType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 1, 1, LabelConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLabelConstraintType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, LabelConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelLayoutTypeEClass, LabelLayoutType.class, "LabelLayoutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabelLayoutType_Data(), this.getDataType(), null, "data", null, 0, -1, LabelLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLabelLayoutType_Template(), this.getTemplateType(), null, "template", null, 0, 1, LabelLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLabelLayoutType_Location(), this.getLocationType(), null, "location", null, 0, 1, LabelLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLabelLayoutType_Text(), this.getTextType(), null, "text", null, 0, 1, LabelLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLabelLayoutType_Font(), this.getFontType(), null, "font", null, 0, 1, LabelLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLabelLayoutType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 0, 1, LabelLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelStyleTemplateTypeEClass, LabelStyleTemplateType.class, "LabelStyleTemplateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabelStyleTemplateType_Data(), this.getDataType(), null, "data", null, 0, -1, LabelStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLabelStyleTemplateType_Template(), this.getTemplateType(), null, "template", null, 0, 1, LabelStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLabelStyleTemplateType_Text(), this.getTextType(), null, "text", null, 1, 1, LabelStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLabelStyleTemplateType_Font(), this.getFontType(), null, "font", null, 1, 1, LabelStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLabelStyleTemplateType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, LabelStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelTypeEClass, LabelType.class, "LabelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabelType_Data(), this.getDataType(), null, "data", null, 0, -1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLabelType_Content(), theXMLTypePackage.getString(), "content", null, 1, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLabelType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, LabelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutTypeEClass, LayoutType.class, "LayoutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLayoutType_Data(), this.getDataType(), null, "data", null, 0, -1, LayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutType_StyleTemplates(), this.getStyleTemplatesType(), null, "styleTemplates", null, 0, 1, LayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutType_Styles(), this.getStylesType(), null, "styles", null, 0, 1, LayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLayoutType_Constraints(), this.getConstraintsType(), null, "constraints", null, 0, 1, LayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(lineStyleTypeEClass, LineStyleType.class, "LineStyleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLineStyleType_Color(), theXMLTypePackage.getString(), "color", null, 0, 1, LineStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLineStyleType_Style(), this.getLineStyleTypeType(), "style", null, 0, 1, LineStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLineStyleType_Width(), theXMLTypePackage.getString(), "width", null, 0, 1, LineStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(lineTypeEClass, LineType.class, "LineType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLineType_Color(), theXMLTypePackage.getString(), "color", null, 0, 1, LineType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLineType_Type(), this.getLineStyleTypeType(), "type", null, 0, 1, LineType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLineType_Width(), theXMLTypePackage.getInteger(), "width", null, 0, 1, LineType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(locationTypeEClass, LocationType.class, "LocationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLocationType_X(), theXMLTypePackage.getDouble(), "x", null, 1, 1, LocationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLocationType_Y(), theXMLTypePackage.getDouble(), "y", null, 1, 1, LocationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLocationType_Z(), theXMLTypePackage.getDouble(), "z", null, 0, 1, LocationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeConstraintTypeEClass, NodeConstraintType.class, "NodeConstraintType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNodeConstraintType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 1, 1, NodeConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeConstraintType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, NodeConstraintType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeLayoutTypeEClass, NodeLayoutType.class, "NodeLayoutType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNodeLayoutType_Data(), this.getDataType(), null, "data", null, 0, -1, NodeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeLayoutType_Template(), this.getTemplateType(), null, "template", null, 0, 1, NodeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeLayoutType_Location(), this.getLocationType(), null, "location", null, 0, 1, NodeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeLayoutType_Shape(), this.getShapeType1(), null, "shape", null, 0, 1, NodeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeLayoutType_Fill(), this.getFillType(), null, "fill", null, 0, 1, NodeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeLayoutType_Line(), this.getLineType(), null, "line", null, 0, 1, NodeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeLayoutType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 0, 1, NodeLayoutType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeStyleTemplateTypeEClass, NodeStyleTemplateType.class, "NodeStyleTemplateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNodeStyleTemplateType_Data(), this.getDataType(), null, "data", null, 0, -1, NodeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeStyleTemplateType_Template(), this.getTemplateType(), null, "template", null, 0, 1, NodeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeStyleTemplateType_Shape(), this.getShapeType1(), null, "shape", null, 0, 1, NodeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeStyleTemplateType_Fill(), this.getFillType(), null, "fill", null, 0, 1, NodeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeStyleTemplateType_Line(), this.getLineType(), null, "line", null, 0, 1, NodeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyleTemplateType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, NodeStyleTemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeTypeEClass, NodeType.class, "NodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNodeType_Data(), this.getDataType(), null, "data", null, 0, -1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNodeType_Label(), this.getLabelType(), null, "label", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getNodeType_Node(), this.getNodeType(), null, "node", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(numberTypeEClass, NumberType.class, "NumberType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNumberType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, NumberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNumberType_Value(), theXMLTypePackage.getDouble(), "value", null, 0, 1, NumberType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ogmlTypeEClass, OgmlType.class, "OgmlType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getOgmlType_Keys(), this.getKeysType(), null, "keys", null, 0, 1, OgmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getOgmlType_Graph(), this.getGraphType(), null, "graph", null, 1, 1, OgmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(pointTypeEClass, PointType.class, "PointType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPointType_Data(), this.getDataType(), null, "data", null, 0, -1, PointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPointType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPointType_X(), theXMLTypePackage.getDouble(), "x", null, 1, 1, PointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPointType_Y(), theXMLTypePackage.getDouble(), "y", null, 1, 1, PointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPointType_Z(), theXMLTypePackage.getDouble(), "z", null, 0, 1, PointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(segmentTypeEClass, SegmentType.class, "SegmentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSegmentType_Data(), this.getDataType(), null, "data", null, 0, -1, SegmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSegmentType_Line(), this.getLineType(), null, "line", null, 0, 1, SegmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSegmentType_Endpoint(), this.getEndpointType(), null, "endpoint", null, 2, 2, SegmentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(shapeType1EClass, ShapeType1.class, "ShapeType1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getShapeType1_Height(), theXMLTypePackage.getInteger(), "height", null, 0, 1, ShapeType1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getShapeType1_Type(), this.getShapeType(), "type", null, 0, 1, ShapeType1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getShapeType1_Uri(), theXMLTypePackage.getAnyURI(), "uri", null, 0, 1, ShapeType1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getShapeType1_Width(), theXMLTypePackage.getInteger(), "width", null, 0, 1, ShapeType1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sourceStyleTypeEClass, SourceStyleType.class, "SourceStyleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSourceStyleType_Color(), theXMLTypePackage.getString(), "color", null, 0, 1, SourceStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceStyleType_Type(), this.getEndpointStylesType(), "type", null, 0, 1, SourceStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceStyleType_Uri(), theXMLTypePackage.getAnyURI(), "uri", null, 0, 1, SourceStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceStyleType_Width(), theXMLTypePackage.getInteger(), "width", null, 0, 1, SourceStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sourceTargetTypeEClass, SourceTargetType.class, "SourceTargetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSourceTargetType_Data(), this.getDataType(), null, "data", null, 0, -1, SourceTargetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSourceTargetType_Label(), this.getLabelType(), null, "label", null, 0, 1, SourceTargetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceTargetType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SourceTargetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSourceTargetType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 1, 1, SourceTargetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(structureTypeEClass, StructureType.class, "StructureType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStructureType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, StructureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStructureType_Data(), this.getDataType(), null, "data", null, 0, -1, StructureType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStructureType_Node(), this.getNodeType(), null, "node", null, 0, -1, StructureType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStructureType_Edge(), this.getEdgeType(), null, "edge", null, 0, -1, StructureType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStructureType_Label(), this.getLabelType(), null, "label", null, 0, -1, StructureType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(stylesTypeEClass, StylesType.class, "StylesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getStylesType_GraphStyle(), this.getGraphStyleType(), null, "graphStyle", null, 0, 1, StylesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getStylesType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, StylesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStylesType_Data(), this.getDataType(), null, "data", null, 0, -1, StylesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStylesType_NodeStyle(), this.getNodeLayoutType(), null, "nodeStyle", null, 0, -1, StylesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStylesType_EdgeStyle(), this.getEdgeLayoutType(), null, "edgeStyle", null, 0, -1, StylesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStylesType_LabelStyle(), this.getLabelLayoutType(), null, "labelStyle", null, 0, -1, StylesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(styleTemplatesTypeEClass, StyleTemplatesType.class, "StyleTemplatesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getStyleTemplatesType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, StyleTemplatesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getStyleTemplatesType_Data(), this.getDataType(), null, "data", null, 0, -1, StyleTemplatesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStyleTemplatesType_NodeStyleTemplate(), this.getNodeStyleTemplateType(), null, "nodeStyleTemplate", null, 0, -1, StyleTemplatesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStyleTemplatesType_EdgeStyleTemplate(), this.getEdgeStyleTemplateType(), null, "edgeStyleTemplate", null, 0, -1, StyleTemplatesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getStyleTemplatesType_LabelStyleTemplate(), this.getLabelStyleTemplateType(), null, "labelStyleTemplate", null, 0, -1, StyleTemplatesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(targetStyleTypeEClass, TargetStyleType.class, "TargetStyleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTargetStyleType_Color(), theXMLTypePackage.getString(), "color", null, 0, 1, TargetStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTargetStyleType_Type(), this.getEndpointStylesType(), "type", null, 0, 1, TargetStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTargetStyleType_Uri(), theXMLTypePackage.getAnyURI(), "uri", null, 0, 1, TargetStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTargetStyleType_Width(), theXMLTypePackage.getInteger(), "width", null, 0, 1, TargetStyleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(templateTypeEClass, TemplateType.class, "TemplateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTemplateType_IdRef(), theXMLTypePackage.getIDREF(), "idRef", null, 0, 1, TemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(textTypeEClass, TextType.class, "TextType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTextType_Alignment(), this.getAlignmentType(), "alignment", null, 0, 1, TextType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTextType_Decoration(), this.getDecorationType(), "decoration", null, 0, 1, TextType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTextType_Rotation(), theXMLTypePackage.getInteger(), "rotation", null, 0, 1, TextType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTextType_Transform(), this.getTransformType(), "transform", null, 0, 1, TextType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(uriTypeEClass, UriType.class, "UriType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUriType_Uri(), theXMLTypePackage.getString(), "uri", null, 1, 1, UriType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(alignmentTypeEEnum, AlignmentType.class, "AlignmentType");
        addEEnumLiteral(alignmentTypeEEnum, AlignmentType.LEFT);
        addEEnumLiteral(alignmentTypeEEnum, AlignmentType.CENTER);
        addEEnumLiteral(alignmentTypeEEnum, AlignmentType.RIGHT);
        addEEnumLiteral(alignmentTypeEEnum, AlignmentType.JUSTIFY);

        initEEnum(boolIntTypeEEnum, BoolIntType.class, "BoolIntType");
        addEEnumLiteral(boolIntTypeEEnum, BoolIntType._1);
        addEEnumLiteral(boolIntTypeEEnum, BoolIntType._0);

        initEEnum(boolTFTypeEEnum, BoolTFType.class, "BoolTFType");
        addEEnumLiteral(boolTFTypeEEnum, BoolTFType.TRUE);
        addEEnumLiteral(boolTFTypeEEnum, BoolTFType.FALSE);

        initEEnum(decorationTypeEEnum, DecorationType.class, "DecorationType");
        addEEnumLiteral(decorationTypeEEnum, DecorationType.UNDERLINE);
        addEEnumLiteral(decorationTypeEEnum, DecorationType.OVERLINE);
        addEEnumLiteral(decorationTypeEEnum, DecorationType.BLINK);
        addEEnumLiteral(decorationTypeEEnum, DecorationType.LINE_THROUGH);
        addEEnumLiteral(decorationTypeEEnum, DecorationType.NONE);

        initEEnum(endpointStylesTypeEEnum, EndpointStylesType.class, "EndpointStylesType");
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.ARROW);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.CIRCLE);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.HALF_CIRCLE);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.FILLED_CIRCLE);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.FILLED_HALF_CIRCLE);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.BOX);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.HALF_BOX);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.FILLED_BOX);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.FILLED_HALF_BOX);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.RHOMB);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.HALF_RHOMB);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.FILLED_RHOMB);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.FILLED_HALF_RHOMB);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.DIAMOND);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.HALF_DIAMOND);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.FILLED_DIAMOND);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.FILLED_HALF_DIAMOND);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.SLASH);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.DOUBLE_SLASH);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.SOLID);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.LINE);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.NONE);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.SMURF);
        addEEnumLiteral(endpointStylesTypeEEnum, EndpointStylesType.IMAGE);

        initEEnum(fontStretchTypeEEnum, FontStretchType.class, "FontStretchType");
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.NORMAL);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.WIDER);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.NARROWER);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.ULTRA_CONDENSED);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.EXTRA_CONDENSED);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.CONDENSED);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.SEMI_CONDENSED);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.SEMI_EXPANDED);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.EXPANDED);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.EXTRA_EXPANDED);
        addEEnumLiteral(fontStretchTypeEEnum, FontStretchType.ULTRA_EXPANDED);

        initEEnum(fontStyleTypeEEnum, FontStyleType.class, "FontStyleType");
        addEEnumLiteral(fontStyleTypeEEnum, FontStyleType.NORMAL);
        addEEnumLiteral(fontStyleTypeEEnum, FontStyleType.ITALIC);
        addEEnumLiteral(fontStyleTypeEEnum, FontStyleType.OBLIQUE);

        initEEnum(fontVariantTypeEEnum, FontVariantType.class, "FontVariantType");
        addEEnumLiteral(fontVariantTypeEEnum, FontVariantType.NORMAL);
        addEEnumLiteral(fontVariantTypeEEnum, FontVariantType.SMALL_CPAPS);

        initEEnum(fontWeightTypeEEnum, FontWeightType.class, "FontWeightType");
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType.NORMAL);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType.BOLD);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType.BOLDER);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._100);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._200);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._300);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._400);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._500);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._600);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._700);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._800);
        addEEnumLiteral(fontWeightTypeEEnum, FontWeightType._900);

        initEEnum(lineStyleTypeTypeEEnum, LineStyleTypeType.class, "LineStyleTypeType");
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.SOLID);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.DOTTED);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.DASHED);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.DOUBLE);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.TRIPLE);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.GROOVE);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.RIDGE);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.INSET);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.OUTSET);
        addEEnumLiteral(lineStyleTypeTypeEEnum, LineStyleTypeType.NONE);

        initEEnum(patternTypeEEnum, PatternType.class, "PatternType");
        addEEnumLiteral(patternTypeEEnum, PatternType.SOLID);
        addEEnumLiteral(patternTypeEEnum, PatternType.STRIPED);
        addEEnumLiteral(patternTypeEEnum, PatternType.CHECKED);
        addEEnumLiteral(patternTypeEEnum, PatternType.DOTTED);
        addEEnumLiteral(patternTypeEEnum, PatternType.NONE);

        initEEnum(shapeTypeEEnum, ShapeType.class, "ShapeType");
        addEEnumLiteral(shapeTypeEEnum, ShapeType.RECT);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.TRIANGLE);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.CIRCLE);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.ELLIPSE);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.HEXAGON);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.RHOMB);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.TRAPEZE);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.UP_TRAPEZE);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.LPARALLELOGRAM);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.RPARALLELOGRAM);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.PENTAGON);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.OCTAGON);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.UML_CLASS);
        addEEnumLiteral(shapeTypeEEnum, ShapeType.IMAGE);

        initEEnum(transformTypeEEnum, TransformType.class, "TransformType");
        addEEnumLiteral(transformTypeEEnum, TransformType.CAPITALIZE);
        addEEnumLiteral(transformTypeEEnum, TransformType.UPPERCASE);
        addEEnumLiteral(transformTypeEEnum, TransformType.LOWERCASE);
        addEEnumLiteral(transformTypeEEnum, TransformType.NONE);

        // Initialize data types
        initEDataType(alignmentTypeObjectEDataType, AlignmentType.class, "AlignmentTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(boolIntTypeObjectEDataType, BoolIntType.class, "BoolIntTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(boolTFTypeObjectEDataType, BoolTFType.class, "BoolTFTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(decorationTypeObjectEDataType, DecorationType.class, "DecorationTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(endpointStylesTypeObjectEDataType, EndpointStylesType.class, "EndpointStylesTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(fontStretchTypeObjectEDataType, FontStretchType.class, "FontStretchTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(fontStyleTypeObjectEDataType, FontStyleType.class, "FontStyleTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(fontVariantTypeObjectEDataType, FontVariantType.class, "FontVariantTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(fontWeightTypeObjectEDataType, FontWeightType.class, "FontWeightTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(lineStyleTypeTypeObjectEDataType, LineStyleTypeType.class, "LineStyleTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(patternTypeObjectEDataType, PatternType.class, "PatternTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(shapeTypeObjectEDataType, ShapeType.class, "ShapeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(transformTypeObjectEDataType, TransformType.class, "TransformTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
        addAnnotation
          (this, 
           source, 
           new String[] {
             "qualified", "false"
           });		
        addAnnotation
          (alignmentTypeEEnum, 
           source, 
           new String[] {
             "name", "alignment.type"
           });		
        addAnnotation
          (alignmentTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "alignment.type:Object",
             "baseType", "alignment.type"
           });		
        addAnnotation
          (booleanTypeEClass, 
           source, 
           new String[] {
             "name", "boolean.type",
             "kind", "empty"
           });		
        addAnnotation
          (getBooleanType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getBooleanType_Value(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "value",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (boolIntTypeEEnum, 
           source, 
           new String[] {
             "name", "boolInt.type"
           });		
        addAnnotation
          (boolIntTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "boolInt.type:Object",
             "baseType", "boolInt.type"
           });			
        addAnnotation
          (boolTFTypeEEnum, 
           source, 
           new String[] {
             "name", "boolTF.type"
           });		
        addAnnotation
          (boolTFTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "boolTF.type:Object",
             "baseType", "boolTF.type"
           });		
        addAnnotation
          (boolTypeEClass, 
           source, 
           new String[] {
             "name", "bool.type",
             "kind", "empty"
           });		
        addAnnotation
          (getBoolType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getBoolType_Value(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "value",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (composedTypeEClass, 
           source, 
           new String[] {
             "name", "composed.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getComposedType_Group(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "group:0"
           });		
        addAnnotation
          (getComposedType_Number(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "number",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getComposedType_Int(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "int",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getComposedType_Boolean(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "boolean",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getComposedType_Node(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "node",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getComposedType_Edge(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edge",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getComposedType_Label(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getComposedType_Composed(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "composed",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getComposedType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (constraintsTypeEClass, 
           source, 
           new String[] {
             "name", "constraints.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getConstraintsType_Group(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "group:0"
           });		
        addAnnotation
          (getConstraintsType_Number(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "number",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getConstraintsType_Int(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "int",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getConstraintsType_Boolean(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "boolean",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getConstraintsType_NodeRef(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "nodeRef",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getConstraintsType_EdgeRef(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edgeRef",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getConstraintsType_LabelRef(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "labelRef",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getConstraintsType_Composed(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "composed",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });			
        addAnnotation
          (dataTypeEClass, 
           source, 
           new String[] {
             "name", "data.type",
             "kind", "simple"
           });		
        addAnnotation
          (getDataType_Value(), 
           source, 
           new String[] {
             "name", ":0",
             "kind", "simple"
           });		
        addAnnotation
          (getDataType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (decorationTypeEEnum, 
           source, 
           new String[] {
             "name", "decoration.type"
           });		
        addAnnotation
          (decorationTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "decoration.type:Object",
             "baseType", "decoration.type"
           });		
        addAnnotation
          (documentRootEClass, 
           source, 
           new String[] {
             "name", "",
             "kind", "mixed"
           });		
        addAnnotation
          (getDocumentRoot_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard",
             "name", ":mixed"
           });		
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "xmlns:prefix"
           });		
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "xsi:schemaLocation"
           });		
        addAnnotation
          (getDocumentRoot_Bool(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "bool",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Boolean(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "boolean",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Composed(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "composed",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Constraints(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "constraints",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Edge(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edge",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_EdgeConstraint(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edge_constraint",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_EdgeLayout(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edge_layout",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_EdgeStyleTemplate(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edgeStyleTemplate",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Endpoint(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "endpoint",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getDocumentRoot_Fill(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "fill",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Font(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "font",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_FontStretch(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "fontStretch",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_FontStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "fontStyle",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_FontVariant(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "fontVariant",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_FontWeight(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "fontWeight",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Graph(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "graph",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_GraphStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "graphStyle",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Int(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "int",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Key(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "key",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Keys(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "keys",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_KeyValue(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "keyValue",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Label(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_LabelConstraint(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label_constraint",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_LabelLayout(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label_layout",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_LabelStyleTemplate(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "labelStyleTemplate",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Layout(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "layout",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getDocumentRoot_Line(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "line",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getDocumentRoot_LineStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "lineStyle",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_LineStyleType(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "lineStyleType",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Location(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "location",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Node(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "node",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_NodeConstraint(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "node_constraint",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_NodeLayout(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "node_layout",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_NodeStyleTemplate(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "nodeStyleTemplate",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Number(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "number",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Ogml(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "ogml",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Pattern(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "pattern",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Point(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "point",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Segment(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "segment",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getDocumentRoot_Shape(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "shape",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getDocumentRoot_SourceStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "sourceStyle",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_SourceTarget(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "sourceTarget",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Structure(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "structure",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Styles(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "styles",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_StyleTemplates(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "styleTemplates",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getDocumentRoot_TargetStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "targetStyle",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Template(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "template",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Text(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "text",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getDocumentRoot_Uri(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "uri",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (edgeConstraintTypeEClass, 
           source, 
           new String[] {
             "name", "edge_constraint.type",
             "kind", "empty"
           });		
        addAnnotation
          (getEdgeConstraintType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeConstraintType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (edgeLayoutTypeEClass, 
           source, 
           new String[] {
             "name", "edge_layout.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getEdgeLayoutType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeLayoutType_Template(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "template",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getEdgeLayoutType_Line(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "line",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getEdgeLayoutType_SourceStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "sourceStyle",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getEdgeLayoutType_TargetStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "targetStyle",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeLayoutType_Point(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "point",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeLayoutType_Segment(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "segment",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeLayoutType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (edgeStyleTemplateTypeEClass, 
           source, 
           new String[] {
             "name", "edgeStyleTemplate.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getEdgeStyleTemplateType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeStyleTemplateType_Template(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "template",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getEdgeStyleTemplateType_Line(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "line",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getEdgeStyleTemplateType_SourceStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "sourceStyle",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getEdgeStyleTemplateType_TargetStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "targetStyle",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeStyleTemplateType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (edgeTypeEClass, 
           source, 
           new String[] {
             "name", "edge.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getEdgeType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeType_Label(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeType_Group(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "group:2"
           });		
        addAnnotation
          (getEdgeType_Source(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "source",
             "namespace", "##targetNamespace",
             "group", "group:2"
           });		
        addAnnotation
          (getEdgeType_Target(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "target",
             "namespace", "##targetNamespace",
             "group", "group:2"
           });		
        addAnnotation
          (getEdgeType_Label1(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEdgeType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (endpointStylesTypeEEnum, 
           source, 
           new String[] {
             "name", "endpointStyles.type"
           });		
        addAnnotation
          (endpointStylesTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "endpointStyles.type:Object",
             "baseType", "endpointStyles.type"
           });			
        addAnnotation
          (endpointTypeEClass, 
           source, 
           new String[] {
             "name", "endpoint.type",
             "kind", "empty"
           });		
        addAnnotation
          (getEndpointType_Color(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "color",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEndpointType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEndpointType_Port(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "port",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEndpointType_Size(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "size",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEndpointType_Style(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "style",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (fillTypeEClass, 
           source, 
           new String[] {
             "name", "fill_._type",
             "kind", "empty"
           });		
        addAnnotation
          (getFillType_Color(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "color",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getFillType_Pattern(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "pattern",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getFillType_PatternColor(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "patternColor",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (fontStretchTypeEEnum, 
           source, 
           new String[] {
             "name", "fontStretch.type"
           });		
        addAnnotation
          (fontStretchTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "fontStretch.type:Object",
             "baseType", "fontStretch.type"
           });		
        addAnnotation
          (fontStyleTypeEEnum, 
           source, 
           new String[] {
             "name", "fontStyle.type"
           });		
        addAnnotation
          (fontStyleTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "fontStyle.type:Object",
             "baseType", "fontStyle.type"
           });			
        addAnnotation
          (fontTypeEClass, 
           source, 
           new String[] {
             "name", "font.type",
             "kind", "empty"
           });		
        addAnnotation
          (getFontType_Color(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "color",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getFontType_Family(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "family",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getFontType_Size(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "size",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getFontType_Stretch(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "stretch",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getFontType_Style(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "style",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getFontType_Variant(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "variant",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getFontType_Weight(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "weight",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (fontVariantTypeEEnum, 
           source, 
           new String[] {
             "name", "fontVariant.type"
           });		
        addAnnotation
          (fontVariantTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "fontVariant.type:Object",
             "baseType", "fontVariant.type"
           });		
        addAnnotation
          (fontWeightTypeEEnum, 
           source, 
           new String[] {
             "name", "fontWeight.type"
           });		
        addAnnotation
          (fontWeightTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "fontWeight.type:Object",
             "baseType", "fontWeight.type"
           });			
        addAnnotation
          (graphStyleTypeEClass, 
           source, 
           new String[] {
             "name", "graphStyle.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getGraphStyleType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getGraphStyleType_DefaultEdgeTemplate(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "defaultEdgeTemplate",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getGraphStyleType_DefaultLabelTemplate(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "defaultLabelTemplate",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getGraphStyleType_DefaultNodeTemplate(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "defaultNodeTemplate",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (graphTypeEClass, 
           source, 
           new String[] {
             "name", "graph.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getGraphType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getGraphType_Structure(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "structure",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getGraphType_Layout(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "layout",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (intTypeEClass, 
           source, 
           new String[] {
             "name", "int.type",
             "kind", "empty"
           });		
        addAnnotation
          (getIntType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getIntType_Value(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "value",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (keysTypeEClass, 
           source, 
           new String[] {
             "name", "keys.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getKeysType_Key(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "key",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (keyTypeEClass, 
           source, 
           new String[] {
             "name", "key.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getKeyType_KeyValue(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "keyValue",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getKeyType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (keyValueTypeEClass, 
           source, 
           new String[] {
             "name", "keyValue.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getKeyValueType_Dafault(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "dafault",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getKeyValueType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getKeyValueType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (labelConstraintTypeEClass, 
           source, 
           new String[] {
             "name", "label_constraint.type",
             "kind", "empty"
           });		
        addAnnotation
          (getLabelConstraintType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelConstraintType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (labelLayoutTypeEClass, 
           source, 
           new String[] {
             "name", "label_layout.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getLabelLayoutType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelLayoutType_Template(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "template",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelLayoutType_Location(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "location",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelLayoutType_Text(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "text",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelLayoutType_Font(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "font",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelLayoutType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (labelStyleTemplateTypeEClass, 
           source, 
           new String[] {
             "name", "labelStyleTemplate.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getLabelStyleTemplateType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelStyleTemplateType_Template(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "template",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelStyleTemplateType_Text(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "text",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelStyleTemplateType_Font(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "font",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelStyleTemplateType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (labelTypeEClass, 
           source, 
           new String[] {
             "name", "label.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getLabelType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelType_Content(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "content",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLabelType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (layoutTypeEClass, 
           source, 
           new String[] {
             "name", "layout.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getLayoutType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLayoutType_StyleTemplates(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "styleTemplates",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLayoutType_Styles(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "styles",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLayoutType_Constraints(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "constraints",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (lineStyleTypeEClass, 
           source, 
           new String[] {
             "name", "lineStyle_._type",
             "kind", "empty"
           });		
        addAnnotation
          (getLineStyleType_Color(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "color",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLineStyleType_Style(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "style",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLineStyleType_Width(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "width",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (lineStyleTypeTypeEEnum, 
           source, 
           new String[] {
             "name", "lineStyleType.type"
           });		
        addAnnotation
          (lineStyleTypeTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "lineStyleType.type:Object",
             "baseType", "lineStyleType.type"
           });		
        addAnnotation
          (lineTypeEClass, 
           source, 
           new String[] {
             "name", "line_._type",
             "kind", "empty"
           });		
        addAnnotation
          (getLineType_Color(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "color",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLineType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLineType_Width(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "width",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (locationTypeEClass, 
           source, 
           new String[] {
             "name", "location.type",
             "kind", "empty"
           });		
        addAnnotation
          (getLocationType_X(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "x",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLocationType_Y(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "y",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getLocationType_Z(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "z",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (nodeConstraintTypeEClass, 
           source, 
           new String[] {
             "name", "node_constraint.type",
             "kind", "empty"
           });		
        addAnnotation
          (getNodeConstraintType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getNodeConstraintType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (nodeLayoutTypeEClass, 
           source, 
           new String[] {
             "name", "node_layout.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getNodeLayoutType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getNodeLayoutType_Template(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "template",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getNodeLayoutType_Location(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "location",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getNodeLayoutType_Shape(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "shape",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getNodeLayoutType_Fill(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "fill",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getNodeLayoutType_Line(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "line",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getNodeLayoutType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (nodeStyleTemplateTypeEClass, 
           source, 
           new String[] {
             "name", "nodeStyleTemplate.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getNodeStyleTemplateType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getNodeStyleTemplateType_Template(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "template",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getNodeStyleTemplateType_Shape(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "shape",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getNodeStyleTemplateType_Fill(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "fill",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getNodeStyleTemplateType_Line(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "line",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getNodeStyleTemplateType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (nodeTypeEClass, 
           source, 
           new String[] {
             "name", "node.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getNodeType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getNodeType_Group(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "group:1"
           });		
        addAnnotation
          (getNodeType_Label(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label",
             "namespace", "##targetNamespace",
             "group", "group:1"
           });		
        addAnnotation
          (getNodeType_Node(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "node",
             "namespace", "##targetNamespace",
             "group", "group:1"
           });		
        addAnnotation
          (getNodeType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (numberTypeEClass, 
           source, 
           new String[] {
             "name", "number.type",
             "kind", "empty"
           });		
        addAnnotation
          (getNumberType_Name(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "name",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getNumberType_Value(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "value",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (ogmlTypeEClass, 
           source, 
           new String[] {
             "name", "ogml.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getOgmlType_Keys(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "keys",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getOgmlType_Graph(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "graph",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (patternTypeEEnum, 
           source, 
           new String[] {
             "name", "pattern.type"
           });		
        addAnnotation
          (patternTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "pattern.type:Object",
             "baseType", "pattern.type"
           });			
        addAnnotation
          (pointTypeEClass, 
           source, 
           new String[] {
             "name", "point.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getPointType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getPointType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getPointType_X(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "x",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getPointType_Y(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "y",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getPointType_Z(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "z",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (segmentTypeEClass, 
           source, 
           new String[] {
             "name", "segment.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getSegmentType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getSegmentType_Line(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "line",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSegmentType_Endpoint(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "endpoint",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (shapeTypeEEnum, 
           source, 
           new String[] {
             "name", "shape.type"
           });		
        addAnnotation
          (shapeType1EClass, 
           source, 
           new String[] {
             "name", "shape_._type",
             "kind", "empty"
           });		
        addAnnotation
          (getShapeType1_Height(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "height",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getShapeType1_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getShapeType1_Uri(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "uri",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getShapeType1_Width(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "width",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (shapeTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "shape.type:Object",
             "baseType", "shape.type"
           });		
        addAnnotation
          (sourceStyleTypeEClass, 
           source, 
           new String[] {
             "name", "sourceStyle_._type",
             "kind", "empty"
           });		
        addAnnotation
          (getSourceStyleType_Color(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "color",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSourceStyleType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSourceStyleType_Uri(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "uri",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSourceStyleType_Width(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "width",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (sourceTargetTypeEClass, 
           source, 
           new String[] {
             "name", "sourceTarget.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getSourceTargetType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSourceTargetType_Label(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSourceTargetType_Id(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "id",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSourceTargetType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (structureTypeEClass, 
           source, 
           new String[] {
             "name", "structure.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getStructureType_Group(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "group:0"
           });		
        addAnnotation
          (getStructureType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getStructureType_Node(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "node",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getStructureType_Edge(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edge",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getStructureType_Label(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "label",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });			
        addAnnotation
          (stylesTypeEClass, 
           source, 
           new String[] {
             "name", "styles.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getStylesType_GraphStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "graphStyle",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getStylesType_Group(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "group:1"
           });		
        addAnnotation
          (getStylesType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace",
             "group", "group:1"
           });		
        addAnnotation
          (getStylesType_NodeStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "nodeStyle",
             "namespace", "##targetNamespace",
             "group", "group:1"
           });		
        addAnnotation
          (getStylesType_EdgeStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edgeStyle",
             "namespace", "##targetNamespace",
             "group", "group:1"
           });		
        addAnnotation
          (getStylesType_LabelStyle(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "labelStyle",
             "namespace", "##targetNamespace",
             "group", "group:1"
           });			
        addAnnotation
          (styleTemplatesTypeEClass, 
           source, 
           new String[] {
             "name", "styleTemplates.type",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getStyleTemplatesType_Group(), 
           source, 
           new String[] {
             "kind", "group",
             "name", "group:0"
           });		
        addAnnotation
          (getStyleTemplatesType_Data(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "data",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getStyleTemplatesType_NodeStyleTemplate(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "nodeStyleTemplate",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getStyleTemplatesType_EdgeStyleTemplate(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "edgeStyleTemplate",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (getStyleTemplatesType_LabelStyleTemplate(), 
           source, 
           new String[] {
             "kind", "element",
             "name", "labelStyleTemplate",
             "namespace", "##targetNamespace",
             "group", "group:0"
           });		
        addAnnotation
          (targetStyleTypeEClass, 
           source, 
           new String[] {
             "name", "targetStyle_._type",
             "kind", "empty"
           });		
        addAnnotation
          (getTargetStyleType_Color(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "color",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getTargetStyleType_Type(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "type",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getTargetStyleType_Uri(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "uri",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getTargetStyleType_Width(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "width",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (templateTypeEClass, 
           source, 
           new String[] {
             "name", "template.type",
             "kind", "empty"
           });		
        addAnnotation
          (getTemplateType_IdRef(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "idRef",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (textTypeEClass, 
           source, 
           new String[] {
             "name", "text.type",
             "kind", "empty"
           });		
        addAnnotation
          (getTextType_Alignment(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "alignment",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getTextType_Decoration(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "decoration",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getTextType_Rotation(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "rotation",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getTextType_Transform(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "transform",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (transformTypeEEnum, 
           source, 
           new String[] {
             "name", "transform.type"
           });		
        addAnnotation
          (transformTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "transform.type:Object",
             "baseType", "transform.type"
           });			
        addAnnotation
          (uriTypeEClass, 
           source, 
           new String[] {
             "name", "uri.type",
             "kind", "empty"
           });		
        addAnnotation
          (getUriType_Uri(), 
           source, 
           new String[] {
             "kind", "attribute",
             "name", "uri",
             "namespace", "##targetNamespace"
           });
    }

} //OgmlPackageImpl
