/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramFactory
 * @model kind="package"
 * @generated
 */
public interface KDiagramPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "kDiagram";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.cau.de/cs/kieler/klighd/kdiagram/KDiagram";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "kDiagram";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  KDiagramPackage eINSTANCE = de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl.init();

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.DiagramSynthesisImpl <em>Diagram Synthesis</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.DiagramSynthesisImpl
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getDiagramSynthesis()
   * @generated
   */
  int DIAGRAM_SYNTHESIS = 0;

  /**
   * The feature id for the '<em><b>Package Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_SYNTHESIS__PACKAGE_NAME = 0;

  /**
   * The feature id for the '<em><b>Imports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_SYNTHESIS__IMPORTS = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_SYNTHESIS__NAME = 2;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_SYNTHESIS__MAPPING = 3;

  /**
   * The number of structural features of the '<em>Diagram Synthesis</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_SYNTHESIS_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.XVariableDeclarationImpl <em>XVariable Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.XVariableDeclarationImpl
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getXVariableDeclaration()
   * @generated
   */
  int XVARIABLE_DECLARATION = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XVARIABLE_DECLARATION__TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XVARIABLE_DECLARATION__NAME = 1;

  /**
   * The feature id for the '<em><b>Node Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XVARIABLE_DECLARATION__NODE_MAPPINGS = 2;

  /**
   * The number of structural features of the '<em>XVariable Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XVARIABLE_DECLARATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl <em>Node Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getNodeMapping()
   * @generated
   */
  int NODE_MAPPING = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_MAPPING__NAME = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_MAPPING__ELEMENTS = 1;

  /**
   * The feature id for the '<em><b>Figure Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_MAPPING__FIGURE_TYPE = 2;

  /**
   * The number of structural features of the '<em>Node Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_MAPPING_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.EdgeMappingImpl <em>Edge Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.EdgeMappingImpl
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getEdgeMapping()
   * @generated
   */
  int EDGE_MAPPING = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_MAPPING__NAME = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_MAPPING__ELEMENTS = 1;

  /**
   * The feature id for the '<em><b>From</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_MAPPING__FROM = 2;

  /**
   * The feature id for the '<em><b>To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_MAPPING__TO = 3;

  /**
   * The feature id for the '<em><b>Figure Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_MAPPING__FIGURE_TYPE = 4;

  /**
   * The number of structural features of the '<em>Edge Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EDGE_MAPPING_FEATURE_COUNT = 5;


  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis <em>Diagram Synthesis</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Diagram Synthesis</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis
   * @generated
   */
  EClass getDiagramSynthesis();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getPackageName <em>Package Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Package Name</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getPackageName()
   * @see #getDiagramSynthesis()
   * @generated
   */
  EAttribute getDiagramSynthesis_PackageName();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getImports <em>Imports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Imports</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getImports()
   * @see #getDiagramSynthesis()
   * @generated
   */
  EReference getDiagramSynthesis_Imports();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getName()
   * @see #getDiagramSynthesis()
   * @generated
   */
  EAttribute getDiagramSynthesis_Name();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mapping</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getMapping()
   * @see #getDiagramSynthesis()
   * @generated
   */
  EReference getDiagramSynthesis_Mapping();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration <em>XVariable Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XVariable Declaration</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration
   * @generated
   */
  EClass getXVariableDeclaration();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration#getType()
   * @see #getXVariableDeclaration()
   * @generated
   */
  EReference getXVariableDeclaration_Type();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration#getName()
   * @see #getXVariableDeclaration()
   * @generated
   */
  EAttribute getXVariableDeclaration_Name();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration#getNodeMappings <em>Node Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Node Mappings</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.XVariableDeclaration#getNodeMappings()
   * @see #getXVariableDeclaration()
   * @generated
   */
  EReference getXVariableDeclaration_NodeMappings();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping <em>Node Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node Mapping</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping
   * @generated
   */
  EClass getNodeMapping();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getName()
   * @see #getNodeMapping()
   * @generated
   */
  EAttribute getNodeMapping_Name();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Elements</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getElements()
   * @see #getNodeMapping()
   * @generated
   */
  EReference getNodeMapping_Elements();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getFigureType <em>Figure Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Figure Type</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getFigureType()
   * @see #getNodeMapping()
   * @generated
   */
  EReference getNodeMapping_FigureType();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping <em>Edge Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Edge Mapping</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping
   * @generated
   */
  EClass getEdgeMapping();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getName()
   * @see #getEdgeMapping()
   * @generated
   */
  EAttribute getEdgeMapping_Name();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Elements</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getElements()
   * @see #getEdgeMapping()
   * @generated
   */
  EReference getEdgeMapping_Elements();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getFrom <em>From</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>From</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getFrom()
   * @see #getEdgeMapping()
   * @generated
   */
  EReference getEdgeMapping_From();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getTo <em>To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getTo()
   * @see #getEdgeMapping()
   * @generated
   */
  EReference getEdgeMapping_To();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getFigureType <em>Figure Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Figure Type</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping#getFigureType()
   * @see #getEdgeMapping()
   * @generated
   */
  EReference getEdgeMapping_FigureType();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  KDiagramFactory getKDiagramFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.DiagramSynthesisImpl <em>Diagram Synthesis</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.DiagramSynthesisImpl
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getDiagramSynthesis()
     * @generated
     */
    EClass DIAGRAM_SYNTHESIS = eINSTANCE.getDiagramSynthesis();

    /**
     * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIAGRAM_SYNTHESIS__PACKAGE_NAME = eINSTANCE.getDiagramSynthesis_PackageName();

    /**
     * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIAGRAM_SYNTHESIS__IMPORTS = eINSTANCE.getDiagramSynthesis_Imports();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIAGRAM_SYNTHESIS__NAME = eINSTANCE.getDiagramSynthesis_Name();

    /**
     * The meta object literal for the '<em><b>Mapping</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIAGRAM_SYNTHESIS__MAPPING = eINSTANCE.getDiagramSynthesis_Mapping();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.XVariableDeclarationImpl <em>XVariable Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.XVariableDeclarationImpl
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getXVariableDeclaration()
     * @generated
     */
    EClass XVARIABLE_DECLARATION = eINSTANCE.getXVariableDeclaration();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XVARIABLE_DECLARATION__TYPE = eINSTANCE.getXVariableDeclaration_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XVARIABLE_DECLARATION__NAME = eINSTANCE.getXVariableDeclaration_Name();

    /**
     * The meta object literal for the '<em><b>Node Mappings</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XVARIABLE_DECLARATION__NODE_MAPPINGS = eINSTANCE.getXVariableDeclaration_NodeMappings();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl <em>Node Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.NodeMappingImpl
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getNodeMapping()
     * @generated
     */
    EClass NODE_MAPPING = eINSTANCE.getNodeMapping();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NODE_MAPPING__NAME = eINSTANCE.getNodeMapping_Name();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_MAPPING__ELEMENTS = eINSTANCE.getNodeMapping_Elements();

    /**
     * The meta object literal for the '<em><b>Figure Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_MAPPING__FIGURE_TYPE = eINSTANCE.getNodeMapping_FigureType();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.EdgeMappingImpl <em>Edge Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.EdgeMappingImpl
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getEdgeMapping()
     * @generated
     */
    EClass EDGE_MAPPING = eINSTANCE.getEdgeMapping();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EDGE_MAPPING__NAME = eINSTANCE.getEdgeMapping_Name();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EDGE_MAPPING__ELEMENTS = eINSTANCE.getEdgeMapping_Elements();

    /**
     * The meta object literal for the '<em><b>From</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EDGE_MAPPING__FROM = eINSTANCE.getEdgeMapping_From();

    /**
     * The meta object literal for the '<em><b>To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EDGE_MAPPING__TO = eINSTANCE.getEdgeMapping_To();

    /**
     * The meta object literal for the '<em><b>Figure Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EDGE_MAPPING__FIGURE_TYPE = eINSTANCE.getEdgeMapping_FigureType();

  }

} //KDiagramPackage
