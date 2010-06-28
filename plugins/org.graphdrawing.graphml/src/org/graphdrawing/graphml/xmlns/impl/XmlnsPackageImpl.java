/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.graphdrawing.graphml.xmlns.impl;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.graphdrawing.graphml.xmlns.DataExtensionType;
import org.graphdrawing.graphml.xmlns.DataType;
import org.graphdrawing.graphml.xmlns.DefaultType;
import org.graphdrawing.graphml.xmlns.DocumentRoot;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.EndpointType;
import org.graphdrawing.graphml.xmlns.EndpointTypeType;
import org.graphdrawing.graphml.xmlns.GraphEdgedefaultType;
import org.graphdrawing.graphml.xmlns.GraphEdgeidsType;
import org.graphdrawing.graphml.xmlns.GraphNodeidsType;
import org.graphdrawing.graphml.xmlns.GraphOrderType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.HyperedgeType;
import org.graphdrawing.graphml.xmlns.KeyForType;
import org.graphdrawing.graphml.xmlns.KeyType;
import org.graphdrawing.graphml.xmlns.KeyTypeType;
import org.graphdrawing.graphml.xmlns.LocatorType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.graphdrawing.graphml.xmlns.PortType;
import org.graphdrawing.graphml.xmlns.XmlnsFactory;
import org.graphdrawing.graphml.xmlns.XmlnsPackage;

import org.w3._1999.xlink.XlinkPackage;

import org.w3._1999.xlink.impl.XlinkPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XmlnsPackageImpl extends EPackageImpl implements XmlnsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataExtensionTypeEClass = null;

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
	private EClass defaultTypeEClass = null;

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
	private EClass graphmlTypeEClass = null;

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
	private EClass hyperedgeTypeEClass = null;

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
	private EClass locatorTypeEClass = null;

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
	private EClass portTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum endpointTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum graphEdgedefaultTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum graphEdgeidsTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum graphNodeidsTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum graphOrderTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum keyForTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum keyTypeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType endpointTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphEdgedefaultTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphEdgeidsTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphEdgesTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphMaxindegreeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphMaxoutdegreeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphNodeidsTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphNodesTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType graphOrderTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType keyForTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType keyNameTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType keyTypeTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nodeIndegreeTypeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType nodeOutdegreeTypeEDataType = null;

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
	 * @see org.graphdrawing.graphml.xmlns.XmlnsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private XmlnsPackageImpl() {
		super(eNS_URI, XmlnsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link XmlnsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static XmlnsPackage init() {
		if (isInited) return (XmlnsPackage)EPackage.Registry.INSTANCE.getEPackage(XmlnsPackage.eNS_URI);

		// Obtain or create and register package
		XmlnsPackageImpl theXmlnsPackage = (XmlnsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof XmlnsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new XmlnsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		XlinkPackageImpl theXlinkPackage = (XlinkPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XlinkPackage.eNS_URI) instanceof XlinkPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XlinkPackage.eNS_URI) : XlinkPackage.eINSTANCE);

		// Create package meta-data objects
		theXmlnsPackage.createPackageContents();
		theXlinkPackage.createPackageContents();

		// Initialize created meta-data
		theXmlnsPackage.initializePackageContents();
		theXlinkPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theXmlnsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(XmlnsPackage.eNS_URI, theXmlnsPackage);
		return theXmlnsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataExtensionType() {
		return dataExtensionTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataExtensionType_Mixed() {
		return (EAttribute)dataExtensionTypeEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getDataType_Id() {
		return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataType_Key() {
		return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataType_Time() {
		return (EAttribute)dataTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDefaultType() {
		return defaultTypeEClass;
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
	public EReference getDocumentRoot_Data() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Default() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Desc() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Edge() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Endpoint() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Graph() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Graphml() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Hyperedge() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Key() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Locator() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Node() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Port() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
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
	public EAttribute getEdgeType_Desc() {
		return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdgeType_Data() {
		return (EReference)edgeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdgeType_Graph() {
		return (EReference)edgeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdgeType_Directed() {
		return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdgeType_Id() {
		return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdgeType_Source() {
		return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdgeType_Sourceport() {
		return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdgeType_Target() {
		return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdgeType_Targetport() {
		return (EAttribute)edgeTypeEClass.getEStructuralFeatures().get(8);
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
	public EAttribute getEndpointType_Desc() {
		return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndpointType_Id() {
		return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndpointType_Node() {
		return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndpointType_Port() {
		return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEndpointType_Type() {
		return (EAttribute)endpointTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphmlType() {
		return graphmlTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphmlType_Desc() {
		return (EAttribute)graphmlTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphmlType_Key() {
		return (EReference)graphmlTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphmlType_Group() {
		return (EAttribute)graphmlTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphmlType_Graph() {
		return (EReference)graphmlTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphmlType_Data() {
		return (EReference)graphmlTypeEClass.getEStructuralFeatures().get(4);
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
	public EAttribute getGraphType_Desc() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_Group() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphType_Data() {
		return (EReference)graphTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphType_Node() {
		return (EReference)graphTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphType_Edge() {
		return (EReference)graphTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphType_Hyperedge() {
		return (EReference)graphTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraphType_Locator() {
		return (EReference)graphTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_Edgedefault() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_Id() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_ParseEdgeids() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_ParseEdges() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_ParseMaxindegree() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_ParseMaxoutdegree() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_ParseNodeids() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_ParseNodes() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphType_ParseOrder() {
		return (EAttribute)graphTypeEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHyperedgeType() {
		return hyperedgeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHyperedgeType_Desc() {
		return (EAttribute)hyperedgeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHyperedgeType_Group() {
		return (EAttribute)hyperedgeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHyperedgeType_Data() {
		return (EReference)hyperedgeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHyperedgeType_Endpoint() {
		return (EReference)hyperedgeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHyperedgeType_Graph() {
		return (EReference)hyperedgeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHyperedgeType_Id() {
		return (EAttribute)hyperedgeTypeEClass.getEStructuralFeatures().get(5);
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
	public EAttribute getKeyType_Desc() {
		return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKeyType_Default() {
		return (EReference)keyTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKeyType_AttrName() {
		return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKeyType_AttrType() {
		return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKeyType_Dynamic() {
		return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKeyType_For() {
		return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKeyType_Id() {
		return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocatorType() {
		return locatorTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocatorType_Href() {
		return (EAttribute)locatorTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLocatorType_Type() {
		return (EAttribute)locatorTypeEClass.getEStructuralFeatures().get(1);
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
	public EAttribute getNodeType_Desc() {
		return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(0);
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
	public EReference getNodeType_Data() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Port() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Graph() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodeType_Locator() {
		return (EReference)nodeTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeType_Id() {
		return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeType_ParseIndegree() {
		return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNodeType_ParseOutdegree() {
		return (EAttribute)nodeTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortType() {
		return portTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortType_Desc() {
		return (EAttribute)portTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortType_Group() {
		return (EAttribute)portTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortType_Data() {
		return (EReference)portTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortType_Port() {
		return (EReference)portTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortType_Name() {
		return (EAttribute)portTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEndpointTypeType() {
		return endpointTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGraphEdgedefaultType() {
		return graphEdgedefaultTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGraphEdgeidsType() {
		return graphEdgeidsTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGraphNodeidsType() {
		return graphNodeidsTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGraphOrderType() {
		return graphOrderTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getKeyForType() {
		return keyForTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getKeyTypeType() {
		return keyTypeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getEndpointTypeTypeObject() {
		return endpointTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphEdgedefaultTypeObject() {
		return graphEdgedefaultTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphEdgeidsTypeObject() {
		return graphEdgeidsTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphEdgesType() {
		return graphEdgesTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphMaxindegreeType() {
		return graphMaxindegreeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphMaxoutdegreeType() {
		return graphMaxoutdegreeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphNodeidsTypeObject() {
		return graphNodeidsTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphNodesType() {
		return graphNodesTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getGraphOrderTypeObject() {
		return graphOrderTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getKeyForTypeObject() {
		return keyForTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getKeyNameType() {
		return keyNameTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getKeyTypeTypeObject() {
		return keyTypeTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNodeIndegreeType() {
		return nodeIndegreeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getNodeOutdegreeType() {
		return nodeOutdegreeTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XmlnsFactory getXmlnsFactory() {
		return (XmlnsFactory)getEFactoryInstance();
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
		dataExtensionTypeEClass = createEClass(DATA_EXTENSION_TYPE);
		createEAttribute(dataExtensionTypeEClass, DATA_EXTENSION_TYPE__MIXED);

		dataTypeEClass = createEClass(DATA_TYPE);
		createEAttribute(dataTypeEClass, DATA_TYPE__ID);
		createEAttribute(dataTypeEClass, DATA_TYPE__KEY);
		createEAttribute(dataTypeEClass, DATA_TYPE__TIME);

		defaultTypeEClass = createEClass(DEFAULT_TYPE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DATA);
		createEReference(documentRootEClass, DOCUMENT_ROOT__DEFAULT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__DESC);
		createEReference(documentRootEClass, DOCUMENT_ROOT__EDGE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ENDPOINT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__GRAPH);
		createEReference(documentRootEClass, DOCUMENT_ROOT__GRAPHML);
		createEReference(documentRootEClass, DOCUMENT_ROOT__HYPEREDGE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__KEY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__LOCATOR);
		createEReference(documentRootEClass, DOCUMENT_ROOT__NODE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PORT);

		edgeTypeEClass = createEClass(EDGE_TYPE);
		createEAttribute(edgeTypeEClass, EDGE_TYPE__DESC);
		createEReference(edgeTypeEClass, EDGE_TYPE__DATA);
		createEReference(edgeTypeEClass, EDGE_TYPE__GRAPH);
		createEAttribute(edgeTypeEClass, EDGE_TYPE__DIRECTED);
		createEAttribute(edgeTypeEClass, EDGE_TYPE__ID);
		createEAttribute(edgeTypeEClass, EDGE_TYPE__SOURCE);
		createEAttribute(edgeTypeEClass, EDGE_TYPE__SOURCEPORT);
		createEAttribute(edgeTypeEClass, EDGE_TYPE__TARGET);
		createEAttribute(edgeTypeEClass, EDGE_TYPE__TARGETPORT);

		endpointTypeEClass = createEClass(ENDPOINT_TYPE);
		createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__DESC);
		createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__ID);
		createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__NODE);
		createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__PORT);
		createEAttribute(endpointTypeEClass, ENDPOINT_TYPE__TYPE);

		graphmlTypeEClass = createEClass(GRAPHML_TYPE);
		createEAttribute(graphmlTypeEClass, GRAPHML_TYPE__DESC);
		createEReference(graphmlTypeEClass, GRAPHML_TYPE__KEY);
		createEAttribute(graphmlTypeEClass, GRAPHML_TYPE__GROUP);
		createEReference(graphmlTypeEClass, GRAPHML_TYPE__GRAPH);
		createEReference(graphmlTypeEClass, GRAPHML_TYPE__DATA);

		graphTypeEClass = createEClass(GRAPH_TYPE);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__DESC);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__GROUP);
		createEReference(graphTypeEClass, GRAPH_TYPE__DATA);
		createEReference(graphTypeEClass, GRAPH_TYPE__NODE);
		createEReference(graphTypeEClass, GRAPH_TYPE__EDGE);
		createEReference(graphTypeEClass, GRAPH_TYPE__HYPEREDGE);
		createEReference(graphTypeEClass, GRAPH_TYPE__LOCATOR);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__EDGEDEFAULT);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__ID);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__PARSE_EDGEIDS);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__PARSE_EDGES);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__PARSE_MAXINDEGREE);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__PARSE_MAXOUTDEGREE);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__PARSE_NODEIDS);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__PARSE_NODES);
		createEAttribute(graphTypeEClass, GRAPH_TYPE__PARSE_ORDER);

		hyperedgeTypeEClass = createEClass(HYPEREDGE_TYPE);
		createEAttribute(hyperedgeTypeEClass, HYPEREDGE_TYPE__DESC);
		createEAttribute(hyperedgeTypeEClass, HYPEREDGE_TYPE__GROUP);
		createEReference(hyperedgeTypeEClass, HYPEREDGE_TYPE__DATA);
		createEReference(hyperedgeTypeEClass, HYPEREDGE_TYPE__ENDPOINT);
		createEReference(hyperedgeTypeEClass, HYPEREDGE_TYPE__GRAPH);
		createEAttribute(hyperedgeTypeEClass, HYPEREDGE_TYPE__ID);

		keyTypeEClass = createEClass(KEY_TYPE);
		createEAttribute(keyTypeEClass, KEY_TYPE__DESC);
		createEReference(keyTypeEClass, KEY_TYPE__DEFAULT);
		createEAttribute(keyTypeEClass, KEY_TYPE__ATTR_NAME);
		createEAttribute(keyTypeEClass, KEY_TYPE__ATTR_TYPE);
		createEAttribute(keyTypeEClass, KEY_TYPE__DYNAMIC);
		createEAttribute(keyTypeEClass, KEY_TYPE__FOR);
		createEAttribute(keyTypeEClass, KEY_TYPE__ID);

		locatorTypeEClass = createEClass(LOCATOR_TYPE);
		createEAttribute(locatorTypeEClass, LOCATOR_TYPE__HREF);
		createEAttribute(locatorTypeEClass, LOCATOR_TYPE__TYPE);

		nodeTypeEClass = createEClass(NODE_TYPE);
		createEAttribute(nodeTypeEClass, NODE_TYPE__DESC);
		createEAttribute(nodeTypeEClass, NODE_TYPE__GROUP);
		createEReference(nodeTypeEClass, NODE_TYPE__DATA);
		createEReference(nodeTypeEClass, NODE_TYPE__PORT);
		createEReference(nodeTypeEClass, NODE_TYPE__GRAPH);
		createEReference(nodeTypeEClass, NODE_TYPE__LOCATOR);
		createEAttribute(nodeTypeEClass, NODE_TYPE__ID);
		createEAttribute(nodeTypeEClass, NODE_TYPE__PARSE_INDEGREE);
		createEAttribute(nodeTypeEClass, NODE_TYPE__PARSE_OUTDEGREE);

		portTypeEClass = createEClass(PORT_TYPE);
		createEAttribute(portTypeEClass, PORT_TYPE__DESC);
		createEAttribute(portTypeEClass, PORT_TYPE__GROUP);
		createEReference(portTypeEClass, PORT_TYPE__DATA);
		createEReference(portTypeEClass, PORT_TYPE__PORT);
		createEAttribute(portTypeEClass, PORT_TYPE__NAME);

		// Create enums
		endpointTypeTypeEEnum = createEEnum(ENDPOINT_TYPE_TYPE);
		graphEdgedefaultTypeEEnum = createEEnum(GRAPH_EDGEDEFAULT_TYPE);
		graphEdgeidsTypeEEnum = createEEnum(GRAPH_EDGEIDS_TYPE);
		graphNodeidsTypeEEnum = createEEnum(GRAPH_NODEIDS_TYPE);
		graphOrderTypeEEnum = createEEnum(GRAPH_ORDER_TYPE);
		keyForTypeEEnum = createEEnum(KEY_FOR_TYPE);
		keyTypeTypeEEnum = createEEnum(KEY_TYPE_TYPE);

		// Create data types
		endpointTypeTypeObjectEDataType = createEDataType(ENDPOINT_TYPE_TYPE_OBJECT);
		graphEdgedefaultTypeObjectEDataType = createEDataType(GRAPH_EDGEDEFAULT_TYPE_OBJECT);
		graphEdgeidsTypeObjectEDataType = createEDataType(GRAPH_EDGEIDS_TYPE_OBJECT);
		graphEdgesTypeEDataType = createEDataType(GRAPH_EDGES_TYPE);
		graphMaxindegreeTypeEDataType = createEDataType(GRAPH_MAXINDEGREE_TYPE);
		graphMaxoutdegreeTypeEDataType = createEDataType(GRAPH_MAXOUTDEGREE_TYPE);
		graphNodeidsTypeObjectEDataType = createEDataType(GRAPH_NODEIDS_TYPE_OBJECT);
		graphNodesTypeEDataType = createEDataType(GRAPH_NODES_TYPE);
		graphOrderTypeObjectEDataType = createEDataType(GRAPH_ORDER_TYPE_OBJECT);
		keyForTypeObjectEDataType = createEDataType(KEY_FOR_TYPE_OBJECT);
		keyNameTypeEDataType = createEDataType(KEY_NAME_TYPE);
		keyTypeTypeObjectEDataType = createEDataType(KEY_TYPE_TYPE_OBJECT);
		nodeIndegreeTypeEDataType = createEDataType(NODE_INDEGREE_TYPE);
		nodeOutdegreeTypeEDataType = createEDataType(NODE_OUTDEGREE_TYPE);
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
		XlinkPackage theXlinkPackage = (XlinkPackage)EPackage.Registry.INSTANCE.getEPackage(XlinkPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		dataTypeEClass.getESuperTypes().add(this.getDataExtensionType());
		defaultTypeEClass.getESuperTypes().add(this.getDataExtensionType());

		// Initialize classes and features; add operations and parameters
		initEClass(dataExtensionTypeEClass, DataExtensionType.class, "DataExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataExtensionType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, DataExtensionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataTypeEClass, DataType.class, "DataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataType_Key(), theXMLTypePackage.getNMTOKEN(), "key", null, 1, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataType_Time(), theXMLTypePackage.getLong(), "time", "0", 0, 1, DataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(defaultTypeEClass, DefaultType.class, "DefaultType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Data(), this.getDataType(), null, "data", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Default(), this.getDefaultType(), null, "default", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Desc(), theXMLTypePackage.getString(), "desc", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Edge(), this.getEdgeType(), null, "edge", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Endpoint(), this.getEndpointType(), null, "endpoint", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Graph(), this.getGraphType(), null, "graph", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Graphml(), this.getGraphmlType(), null, "graphml", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Hyperedge(), this.getHyperedgeType(), null, "hyperedge", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Key(), this.getKeyType(), null, "key", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Locator(), this.getLocatorType(), null, "locator", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Node(), this.getNodeType(), null, "node", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Port(), this.getPortType(), null, "port", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(edgeTypeEClass, EdgeType.class, "EdgeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEdgeType_Desc(), theXMLTypePackage.getString(), "desc", null, 0, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeType_Data(), this.getDataType(), null, "data", null, 0, -1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeType_Graph(), this.getGraphType(), null, "graph", null, 0, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeType_Directed(), theXMLTypePackage.getBoolean(), "directed", null, 0, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 0, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeType_Source(), theXMLTypePackage.getNMTOKEN(), "source", null, 1, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeType_Sourceport(), theXMLTypePackage.getNMTOKEN(), "sourceport", null, 0, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeType_Target(), theXMLTypePackage.getNMTOKEN(), "target", null, 1, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdgeType_Targetport(), theXMLTypePackage.getNMTOKEN(), "targetport", null, 0, 1, EdgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(endpointTypeEClass, EndpointType.class, "EndpointType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEndpointType_Desc(), theXMLTypePackage.getString(), "desc", null, 0, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndpointType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 0, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndpointType_Node(), theXMLTypePackage.getNMTOKEN(), "node", null, 1, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndpointType_Port(), theXMLTypePackage.getNMTOKEN(), "port", null, 0, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEndpointType_Type(), this.getEndpointTypeType(), "type", "undir", 0, 1, EndpointType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(graphmlTypeEClass, GraphmlType.class, "GraphmlType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGraphmlType_Desc(), theXMLTypePackage.getString(), "desc", null, 0, 1, GraphmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraphmlType_Key(), this.getKeyType(), null, "key", null, 0, -1, GraphmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphmlType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, GraphmlType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraphmlType_Graph(), this.getGraphType(), null, "graph", null, 0, -1, GraphmlType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getGraphmlType_Data(), this.getDataType(), null, "data", null, 0, -1, GraphmlType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(graphTypeEClass, GraphType.class, "GraphType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGraphType_Desc(), theXMLTypePackage.getString(), "desc", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraphType_Data(), this.getDataType(), null, "data", null, 0, -1, GraphType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getGraphType_Node(), this.getNodeType(), null, "node", null, 0, -1, GraphType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getGraphType_Edge(), this.getEdgeType(), null, "edge", null, 0, -1, GraphType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getGraphType_Hyperedge(), this.getHyperedgeType(), null, "hyperedge", null, 0, -1, GraphType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getGraphType_Locator(), this.getLocatorType(), null, "locator", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_Edgedefault(), this.getGraphEdgedefaultType(), "edgedefault", null, 1, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_ParseEdgeids(), this.getGraphEdgeidsType(), "parseEdgeids", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_ParseEdges(), this.getGraphEdgesType(), "parseEdges", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_ParseMaxindegree(), this.getGraphMaxindegreeType(), "parseMaxindegree", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_ParseMaxoutdegree(), this.getGraphMaxoutdegreeType(), "parseMaxoutdegree", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_ParseNodeids(), this.getGraphNodeidsType(), "parseNodeids", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_ParseNodes(), this.getGraphNodesType(), "parseNodes", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGraphType_ParseOrder(), this.getGraphOrderType(), "parseOrder", null, 0, 1, GraphType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hyperedgeTypeEClass, HyperedgeType.class, "HyperedgeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHyperedgeType_Desc(), theXMLTypePackage.getString(), "desc", null, 0, 1, HyperedgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHyperedgeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, HyperedgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHyperedgeType_Data(), this.getDataType(), null, "data", null, 0, -1, HyperedgeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHyperedgeType_Endpoint(), this.getEndpointType(), null, "endpoint", null, 0, -1, HyperedgeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getHyperedgeType_Graph(), this.getGraphType(), null, "graph", null, 0, 1, HyperedgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHyperedgeType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 0, 1, HyperedgeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(keyTypeEClass, KeyType.class, "KeyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKeyType_Desc(), theXMLTypePackage.getString(), "desc", null, 0, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKeyType_Default(), this.getDefaultType(), null, "default", null, 0, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyType_AttrName(), this.getKeyNameType(), "attrName", null, 0, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyType_AttrType(), this.getKeyTypeType(), "attrType", null, 0, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyType_Dynamic(), theXMLTypePackage.getBoolean(), "dynamic", "false", 0, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyType_For(), this.getKeyForType(), "for", "all", 0, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKeyType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(locatorTypeEClass, LocatorType.class, "LocatorType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocatorType_Href(), theXMLTypePackage.getAnyURI(), "href", null, 1, 1, LocatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocatorType_Type(), theXlinkPackage.getTypeType(), "type", null, 0, 1, LocatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeTypeEClass, NodeType.class, "NodeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNodeType_Desc(), theXMLTypePackage.getString(), "desc", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Data(), this.getDataType(), null, "data", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Port(), this.getPortType(), null, "port", null, 0, -1, NodeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Graph(), this.getGraphType(), null, "graph", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeType_Locator(), this.getLocatorType(), null, "locator", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeType_Id(), theXMLTypePackage.getNMTOKEN(), "id", null, 1, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeType_ParseIndegree(), this.getNodeIndegreeType(), "parseIndegree", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeType_ParseOutdegree(), this.getNodeOutdegreeType(), "parseOutdegree", null, 0, 1, NodeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portTypeEClass, PortType.class, "PortType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortType_Desc(), theXMLTypePackage.getString(), "desc", null, 0, 1, PortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, PortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortType_Data(), this.getDataType(), null, "data", null, 0, -1, PortType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getPortType_Port(), this.getPortType(), null, "port", null, 0, -1, PortType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortType_Name(), theXMLTypePackage.getNMTOKEN(), "name", null, 1, 1, PortType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(endpointTypeTypeEEnum, EndpointTypeType.class, "EndpointTypeType");
		addEEnumLiteral(endpointTypeTypeEEnum, EndpointTypeType.IN);
		addEEnumLiteral(endpointTypeTypeEEnum, EndpointTypeType.OUT);
		addEEnumLiteral(endpointTypeTypeEEnum, EndpointTypeType.UNDIR);

		initEEnum(graphEdgedefaultTypeEEnum, GraphEdgedefaultType.class, "GraphEdgedefaultType");
		addEEnumLiteral(graphEdgedefaultTypeEEnum, GraphEdgedefaultType.DIRECTED);
		addEEnumLiteral(graphEdgedefaultTypeEEnum, GraphEdgedefaultType.UNDIRECTED);

		initEEnum(graphEdgeidsTypeEEnum, GraphEdgeidsType.class, "GraphEdgeidsType");
		addEEnumLiteral(graphEdgeidsTypeEEnum, GraphEdgeidsType.CANONICAL);
		addEEnumLiteral(graphEdgeidsTypeEEnum, GraphEdgeidsType.FREE);

		initEEnum(graphNodeidsTypeEEnum, GraphNodeidsType.class, "GraphNodeidsType");
		addEEnumLiteral(graphNodeidsTypeEEnum, GraphNodeidsType.CANONICAL);
		addEEnumLiteral(graphNodeidsTypeEEnum, GraphNodeidsType.FREE);

		initEEnum(graphOrderTypeEEnum, GraphOrderType.class, "GraphOrderType");
		addEEnumLiteral(graphOrderTypeEEnum, GraphOrderType.FREE);
		addEEnumLiteral(graphOrderTypeEEnum, GraphOrderType.NODESFIRST);
		addEEnumLiteral(graphOrderTypeEEnum, GraphOrderType.ADJACENCYLIST);

		initEEnum(keyForTypeEEnum, KeyForType.class, "KeyForType");
		addEEnumLiteral(keyForTypeEEnum, KeyForType.ALL);
		addEEnumLiteral(keyForTypeEEnum, KeyForType.GRAPHML);
		addEEnumLiteral(keyForTypeEEnum, KeyForType.GRAPH);
		addEEnumLiteral(keyForTypeEEnum, KeyForType.NODE);
		addEEnumLiteral(keyForTypeEEnum, KeyForType.EDGE);
		addEEnumLiteral(keyForTypeEEnum, KeyForType.HYPEREDGE);
		addEEnumLiteral(keyForTypeEEnum, KeyForType.PORT);
		addEEnumLiteral(keyForTypeEEnum, KeyForType.ENDPOINT);

		initEEnum(keyTypeTypeEEnum, KeyTypeType.class, "KeyTypeType");
		addEEnumLiteral(keyTypeTypeEEnum, KeyTypeType.BOOLEAN);
		addEEnumLiteral(keyTypeTypeEEnum, KeyTypeType.INT);
		addEEnumLiteral(keyTypeTypeEEnum, KeyTypeType.LONG);
		addEEnumLiteral(keyTypeTypeEEnum, KeyTypeType.FLOAT);
		addEEnumLiteral(keyTypeTypeEEnum, KeyTypeType.DOUBLE);
		addEEnumLiteral(keyTypeTypeEEnum, KeyTypeType.STRING);

		// Initialize data types
		initEDataType(endpointTypeTypeObjectEDataType, EndpointTypeType.class, "EndpointTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphEdgedefaultTypeObjectEDataType, GraphEdgedefaultType.class, "GraphEdgedefaultTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphEdgeidsTypeObjectEDataType, GraphEdgeidsType.class, "GraphEdgeidsTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphEdgesTypeEDataType, BigInteger.class, "GraphEdgesType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphMaxindegreeTypeEDataType, BigInteger.class, "GraphMaxindegreeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphMaxoutdegreeTypeEDataType, BigInteger.class, "GraphMaxoutdegreeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphNodeidsTypeObjectEDataType, GraphNodeidsType.class, "GraphNodeidsTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphNodesTypeEDataType, BigInteger.class, "GraphNodesType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(graphOrderTypeObjectEDataType, GraphOrderType.class, "GraphOrderTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(keyForTypeObjectEDataType, KeyForType.class, "KeyForTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(keyNameTypeEDataType, String.class, "KeyNameType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(keyTypeTypeObjectEDataType, KeyTypeType.class, "KeyTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(nodeIndegreeTypeEDataType, BigInteger.class, "NodeIndegreeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(nodeOutdegreeTypeEDataType, BigInteger.class, "NodeOutdegreeType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
		  (dataExtensionTypeEClass, 
		   source, 
		   new String[] {
			 "name", "data-extension.type",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDataExtensionType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });			
		addAnnotation
		  (dataTypeEClass, 
		   source, 
		   new String[] {
			 "name", "data.type",
			 "kind", "mixed"
		   });			
		addAnnotation
		  (getDataType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });			
		addAnnotation
		  (getDataType_Key(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "key"
		   });			
		addAnnotation
		  (getDataType_Time(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "time"
		   });			
		addAnnotation
		  (defaultTypeEClass, 
		   source, 
		   new String[] {
			 "name", "default.type",
			 "kind", "mixed"
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
		  (getDocumentRoot_Data(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "data",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_Default(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "default",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
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
		  (getDocumentRoot_Endpoint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "endpoint",
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
		  (getDocumentRoot_Graphml(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "graphml",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getDocumentRoot_Hyperedge(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "hyperedge",
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
		  (getDocumentRoot_Locator(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "locator",
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
		  (getDocumentRoot_Port(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "port",
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
		  (getEdgeType_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
			 "namespace", "##targetNamespace"
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
		  (getEdgeType_Graph(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "graph",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getEdgeType_Directed(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "directed"
		   });			
		addAnnotation
		  (getEdgeType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });			
		addAnnotation
		  (getEdgeType_Source(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "source"
		   });			
		addAnnotation
		  (getEdgeType_Sourceport(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "sourceport"
		   });			
		addAnnotation
		  (getEdgeType_Target(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "target"
		   });			
		addAnnotation
		  (getEdgeType_Targetport(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "targetport"
		   });			
		addAnnotation
		  (endpointTypeEClass, 
		   source, 
		   new String[] {
			 "name", "endpoint.type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getEndpointType_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getEndpointType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });			
		addAnnotation
		  (getEndpointType_Node(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "node"
		   });			
		addAnnotation
		  (getEndpointType_Port(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "port"
		   });			
		addAnnotation
		  (getEndpointType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });			
		addAnnotation
		  (endpointTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "endpoint.type.type"
		   });		
		addAnnotation
		  (endpointTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "endpoint.type.type:Object",
			 "baseType", "endpoint.type.type"
		   });			
		addAnnotation
		  (graphEdgedefaultTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "graph.edgedefault.type"
		   });		
		addAnnotation
		  (graphEdgedefaultTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "graph.edgedefault.type:Object",
			 "baseType", "graph.edgedefault.type"
		   });			
		addAnnotation
		  (graphEdgeidsTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "graph.edgeids.type"
		   });		
		addAnnotation
		  (graphEdgeidsTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "graph.edgeids.type:Object",
			 "baseType", "graph.edgeids.type"
		   });			
		addAnnotation
		  (graphEdgesTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "graph.edges.type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger"
		   });			
		addAnnotation
		  (graphMaxindegreeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "graph.maxindegree.type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger"
		   });			
		addAnnotation
		  (graphMaxoutdegreeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "graph.maxoutdegree.type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger"
		   });			
		addAnnotation
		  (graphmlTypeEClass, 
		   source, 
		   new String[] {
			 "name", "graphml.type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getGraphmlType_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getGraphmlType_Key(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "key",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getGraphmlType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:2"
		   });			
		addAnnotation
		  (getGraphmlType_Graph(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "graph",
			 "namespace", "##targetNamespace",
			 "group", "#group:2"
		   });			
		addAnnotation
		  (getGraphmlType_Data(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "data",
			 "namespace", "##targetNamespace",
			 "group", "#group:2"
		   });			
		addAnnotation
		  (graphNodeidsTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "graph.nodeids.type"
		   });		
		addAnnotation
		  (graphNodeidsTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "graph.nodeids.type:Object",
			 "baseType", "graph.nodeids.type"
		   });			
		addAnnotation
		  (graphNodesTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "graph.nodes.type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger"
		   });			
		addAnnotation
		  (graphOrderTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "graph.order.type"
		   });		
		addAnnotation
		  (graphOrderTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "graph.order.type:Object",
			 "baseType", "graph.order.type"
		   });			
		addAnnotation
		  (graphTypeEClass, 
		   source, 
		   new String[] {
			 "name", "graph.type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getGraphType_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getGraphType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:1"
		   });			
		addAnnotation
		  (getGraphType_Data(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "data",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getGraphType_Node(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "node",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getGraphType_Edge(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "edge",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getGraphType_Hyperedge(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "hyperedge",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getGraphType_Locator(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "locator",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getGraphType_Edgedefault(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "edgedefault"
		   });			
		addAnnotation
		  (getGraphType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getGraphType_ParseEdgeids(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.edgeids"
		   });		
		addAnnotation
		  (getGraphType_ParseEdges(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.edges"
		   });		
		addAnnotation
		  (getGraphType_ParseMaxindegree(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.maxindegree"
		   });		
		addAnnotation
		  (getGraphType_ParseMaxoutdegree(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.maxoutdegree"
		   });		
		addAnnotation
		  (getGraphType_ParseNodeids(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.nodeids"
		   });		
		addAnnotation
		  (getGraphType_ParseNodes(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.nodes"
		   });		
		addAnnotation
		  (getGraphType_ParseOrder(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.order"
		   });			
		addAnnotation
		  (hyperedgeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "hyperedge.type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getHyperedgeType_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getHyperedgeType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:1"
		   });			
		addAnnotation
		  (getHyperedgeType_Data(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "data",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getHyperedgeType_Endpoint(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "endpoint",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getHyperedgeType_Graph(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "graph",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getHyperedgeType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });			
		addAnnotation
		  (keyForTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "key.for.type"
		   });		
		addAnnotation
		  (keyForTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "key.for.type:Object",
			 "baseType", "key.for.type"
		   });			
		addAnnotation
		  (keyNameTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "key.name.type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#NMTOKEN"
		   });			
		addAnnotation
		  (keyTypeEClass, 
		   source, 
		   new String[] {
			 "name", "key.type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getKeyType_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getKeyType_Default(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "default",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getKeyType_AttrName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "attr.name"
		   });		
		addAnnotation
		  (getKeyType_AttrType(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "attr.type"
		   });			
		addAnnotation
		  (getKeyType_Dynamic(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "dynamic"
		   });			
		addAnnotation
		  (getKeyType_For(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "for"
		   });			
		addAnnotation
		  (getKeyType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });			
		addAnnotation
		  (keyTypeTypeEEnum, 
		   source, 
		   new String[] {
			 "name", "key.type.type"
		   });		
		addAnnotation
		  (keyTypeTypeObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "key.type.type:Object",
			 "baseType", "key.type.type"
		   });			
		addAnnotation
		  (locatorTypeEClass, 
		   source, 
		   new String[] {
			 "name", "locator.type",
			 "kind", "empty"
		   });			
		addAnnotation
		  (getLocatorType_Href(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "href",
			 "namespace", "http://www.w3.org/1999/xlink"
		   });			
		addAnnotation
		  (getLocatorType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type",
			 "namespace", "http://www.w3.org/1999/xlink"
		   });			
		addAnnotation
		  (nodeIndegreeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "node.indegree.type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger"
		   });			
		addAnnotation
		  (nodeOutdegreeTypeEDataType, 
		   source, 
		   new String[] {
			 "name", "node.outdegree.type",
			 "baseType", "http://www.eclipse.org/emf/2003/XMLType#nonNegativeInteger"
		   });			
		addAnnotation
		  (nodeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "node.type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getNodeType_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
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
		  (getNodeType_Data(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "data",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getNodeType_Port(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "port",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getNodeType_Graph(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "graph",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getNodeType_Locator(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "locator",
			 "namespace", "##targetNamespace"
		   });			
		addAnnotation
		  (getNodeType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getNodeType_ParseIndegree(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.indegree"
		   });		
		addAnnotation
		  (getNodeType_ParseOutdegree(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parse.outdegree"
		   });			
		addAnnotation
		  (portTypeEClass, 
		   source, 
		   new String[] {
			 "name", "port.type",
			 "kind", "elementOnly"
		   });			
		addAnnotation
		  (getPortType_Desc(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "desc",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (getPortType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:1"
		   });			
		addAnnotation
		  (getPortType_Data(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "data",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getPortType_Port(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "port",
			 "namespace", "##targetNamespace",
			 "group", "#group:1"
		   });			
		addAnnotation
		  (getPortType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });
	}

} //XmlnsPackageImpl
