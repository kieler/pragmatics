/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl;

import de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramFactory;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping;
import de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.xtend.core.xtend.XtendPackage;

import org.eclipse.xtext.common.types.TypesPackage;

import org.eclipse.xtext.xbase.XbasePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KDiagramPackageImpl extends EPackageImpl implements KDiagramPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass diagramSynthesisEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xVariableDeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nodeMappingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass edgeMappingEClass = null;

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
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private KDiagramPackageImpl()
  {
    super(eNS_URI, KDiagramFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link KDiagramPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static KDiagramPackage init()
  {
    if (isInited) return (KDiagramPackage)EPackage.Registry.INSTANCE.getEPackage(KDiagramPackage.eNS_URI);

    // Obtain or create and register package
    KDiagramPackageImpl theKDiagramPackage = (KDiagramPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KDiagramPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KDiagramPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XtendPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theKDiagramPackage.createPackageContents();

    // Initialize created meta-data
    theKDiagramPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theKDiagramPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(KDiagramPackage.eNS_URI, theKDiagramPackage);
    return theKDiagramPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDiagramSynthesis()
  {
    return diagramSynthesisEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDiagramSynthesis_PackageName()
  {
    return (EAttribute)diagramSynthesisEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDiagramSynthesis_Imports()
  {
    return (EReference)diagramSynthesisEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDiagramSynthesis_Name()
  {
    return (EAttribute)diagramSynthesisEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDiagramSynthesis_Mapping()
  {
    return (EReference)diagramSynthesisEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXVariableDeclaration()
  {
    return xVariableDeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXVariableDeclaration_Type()
  {
    return (EReference)xVariableDeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXVariableDeclaration_Name()
  {
    return (EAttribute)xVariableDeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXVariableDeclaration_NodeMappings()
  {
    return (EReference)xVariableDeclarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNodeMapping()
  {
    return nodeMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNodeMapping_Name()
  {
    return (EAttribute)nodeMappingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeMapping_Elements()
  {
    return (EReference)nodeMappingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNodeMapping_FigureType()
  {
    return (EReference)nodeMappingEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEdgeMapping()
  {
    return edgeMappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEdgeMapping_Name()
  {
    return (EAttribute)edgeMappingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEdgeMapping_Elements()
  {
    return (EReference)edgeMappingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEdgeMapping_From()
  {
    return (EReference)edgeMappingEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEdgeMapping_To()
  {
    return (EReference)edgeMappingEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEdgeMapping_FigureType()
  {
    return (EReference)edgeMappingEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KDiagramFactory getKDiagramFactory()
  {
    return (KDiagramFactory)getEFactoryInstance();
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
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    diagramSynthesisEClass = createEClass(DIAGRAM_SYNTHESIS);
    createEAttribute(diagramSynthesisEClass, DIAGRAM_SYNTHESIS__PACKAGE_NAME);
    createEReference(diagramSynthesisEClass, DIAGRAM_SYNTHESIS__IMPORTS);
    createEAttribute(diagramSynthesisEClass, DIAGRAM_SYNTHESIS__NAME);
    createEReference(diagramSynthesisEClass, DIAGRAM_SYNTHESIS__MAPPING);

    xVariableDeclarationEClass = createEClass(XVARIABLE_DECLARATION);
    createEReference(xVariableDeclarationEClass, XVARIABLE_DECLARATION__TYPE);
    createEAttribute(xVariableDeclarationEClass, XVARIABLE_DECLARATION__NAME);
    createEReference(xVariableDeclarationEClass, XVARIABLE_DECLARATION__NODE_MAPPINGS);

    nodeMappingEClass = createEClass(NODE_MAPPING);
    createEAttribute(nodeMappingEClass, NODE_MAPPING__NAME);
    createEReference(nodeMappingEClass, NODE_MAPPING__ELEMENTS);
    createEReference(nodeMappingEClass, NODE_MAPPING__FIGURE_TYPE);

    edgeMappingEClass = createEClass(EDGE_MAPPING);
    createEAttribute(edgeMappingEClass, EDGE_MAPPING__NAME);
    createEReference(edgeMappingEClass, EDGE_MAPPING__ELEMENTS);
    createEReference(edgeMappingEClass, EDGE_MAPPING__FROM);
    createEReference(edgeMappingEClass, EDGE_MAPPING__TO);
    createEReference(edgeMappingEClass, EDGE_MAPPING__FIGURE_TYPE);
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
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XtendPackage theXtendPackage = (XtendPackage)EPackage.Registry.INSTANCE.getEPackage(XtendPackage.eNS_URI);
    TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);
    XbasePackage theXbasePackage = (XbasePackage)EPackage.Registry.INSTANCE.getEPackage(XbasePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(diagramSynthesisEClass, DiagramSynthesis.class, "DiagramSynthesis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDiagramSynthesis_PackageName(), ecorePackage.getEString(), "packageName", null, 0, 1, DiagramSynthesis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDiagramSynthesis_Imports(), theXtendPackage.getXtendImport(), null, "imports", null, 0, -1, DiagramSynthesis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDiagramSynthesis_Name(), ecorePackage.getEString(), "name", null, 0, 1, DiagramSynthesis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDiagramSynthesis_Mapping(), this.getXVariableDeclaration(), null, "mapping", null, 0, 1, DiagramSynthesis.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(xVariableDeclarationEClass, XVariableDeclaration.class, "XVariableDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getXVariableDeclaration_Type(), theTypesPackage.getJvmParameterizedTypeReference(), null, "type", null, 0, 1, XVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getXVariableDeclaration_Name(), ecorePackage.getEString(), "name", null, 0, 1, XVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getXVariableDeclaration_NodeMappings(), this.getNodeMapping(), null, "nodeMappings", null, 0, -1, XVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nodeMappingEClass, NodeMapping.class, "NodeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNodeMapping_Name(), ecorePackage.getEString(), "name", null, 0, 1, NodeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeMapping_Elements(), theXbasePackage.getXExpression(), null, "elements", null, 0, 1, NodeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNodeMapping_FigureType(), theTypesPackage.getJvmType(), null, "figureType", null, 0, 1, NodeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(edgeMappingEClass, EdgeMapping.class, "EdgeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEdgeMapping_Name(), ecorePackage.getEString(), "name", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEdgeMapping_Elements(), theXbasePackage.getXExpression(), null, "elements", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEdgeMapping_From(), theXbasePackage.getXExpression(), null, "from", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEdgeMapping_To(), theXbasePackage.getXExpression(), null, "to", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEdgeMapping_FigureType(), theTypesPackage.getJvmType(), null, "figureType", null, 0, 1, EdgeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //KDiagramPackageImpl
