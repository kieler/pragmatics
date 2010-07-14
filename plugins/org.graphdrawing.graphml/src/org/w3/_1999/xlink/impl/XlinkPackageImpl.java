/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xlink.impl;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.graphdrawing.graphml.GraphMLPackage;

import org.graphdrawing.graphml.impl.GraphMLPackageImpl;

import org.w3._1999.xlink.TypeType;
import org.w3._1999.xlink.XlinkFactory;
import org.w3._1999.xlink.XlinkPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XlinkPackageImpl extends EPackageImpl implements XlinkPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum typeTypeEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType typeTypeObjectEDataType = null;

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
     * @see org.w3._1999.xlink.XlinkPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private XlinkPackageImpl() {
        super(eNS_URI, XlinkFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link XlinkPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static XlinkPackage init() {
        if (isInited) return (XlinkPackage)EPackage.Registry.INSTANCE.getEPackage(XlinkPackage.eNS_URI);

        // Obtain or create and register package
        XlinkPackageImpl theXlinkPackage = (XlinkPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof XlinkPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new XlinkPackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        GraphMLPackageImpl theGraphMLPackage = (GraphMLPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GraphMLPackage.eNS_URI) instanceof GraphMLPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GraphMLPackage.eNS_URI) : GraphMLPackage.eINSTANCE);

        // Create package meta-data objects
        theXlinkPackage.createPackageContents();
        theGraphMLPackage.createPackageContents();

        // Initialize created meta-data
        theXlinkPackage.initializePackageContents();
        theGraphMLPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theXlinkPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(XlinkPackage.eNS_URI, theXlinkPackage);
        return theXlinkPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getTypeType() {
        return typeTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getTypeTypeObject() {
        return typeTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XlinkFactory getXlinkFactory() {
        return (XlinkFactory)getEFactoryInstance();
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

        // Create enums
        typeTypeEEnum = createEEnum(TYPE_TYPE);

        // Create data types
        typeTypeObjectEDataType = createEDataType(TYPE_TYPE_OBJECT);
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

        // Initialize enums and add enum literals
        initEEnum(typeTypeEEnum, TypeType.class, "TypeType");
        addEEnumLiteral(typeTypeEEnum, TypeType.SIMPLE);
        addEEnumLiteral(typeTypeEEnum, TypeType.EXTENDED);
        addEEnumLiteral(typeTypeEEnum, TypeType.LOCATOR);
        addEEnumLiteral(typeTypeEEnum, TypeType.ARC);

        // Initialize data types
        initEDataType(typeTypeObjectEDataType, TypeType.class, "TypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

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
          (typeTypeEEnum, 
           source, 
           new String[] {
             "name", "type_._type"
           });		
        addAnnotation
          (typeTypeObjectEDataType, 
           source, 
           new String[] {
             "name", "type_._type:Object",
             "baseType", "type_._type"
           });
    }

} //XlinkPackageImpl
