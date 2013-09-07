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
   * The feature id for the '<em><b>Import Section</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAGRAM_SYNTHESIS__IMPORT_SECTION = 1;

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
   * The meta object id for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl <em>Mapping Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getMappingDefinition()
   * @generated
   */
  int MAPPING_DEFINITION = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_DEFINITION__TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_DEFINITION__NAME = 1;

  /**
   * The feature id for the '<em><b>Node Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_DEFINITION__NODE_MAPPINGS = 2;

  /**
   * The feature id for the '<em><b>Port Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_DEFINITION__PORT_MAPPINGS = 3;

  /**
   * The feature id for the '<em><b>Edge Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_DEFINITION__EDGE_MAPPINGS = 4;

  /**
   * The number of structural features of the '<em>Mapping Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_DEFINITION_FEATURE_COUNT = 5;

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
   * The feature id for the '<em><b>With Ports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_MAPPING__WITH_PORTS = 2;

  /**
   * The feature id for the '<em><b>Figure Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_MAPPING__FIGURE_TYPE = 3;

  /**
   * The feature id for the '<em><b>Port Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_MAPPING__PORT_MAPPINGS = 4;

  /**
   * The number of structural features of the '<em>Node Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NODE_MAPPING_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl <em>Port Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getPortMapping()
   * @generated
   */
  int PORT_MAPPING = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__NAME = 0;

  /**
   * The feature id for the '<em><b>Node Element Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__NODE_ELEMENT_TYPE = 1;

  /**
   * The feature id for the '<em><b>Node Element Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__NODE_ELEMENT_NAME = 2;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__ELEMENTS = 3;

  /**
   * The feature id for the '<em><b>Identified By</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__IDENTIFIED_BY = 4;

  /**
   * The feature id for the '<em><b>Indentified By</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__INDENTIFIED_BY = 5;

  /**
   * The feature id for the '<em><b>Figure Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING__FIGURE_TYPE = 6;

  /**
   * The number of structural features of the '<em>Port Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PORT_MAPPING_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.EdgeMappingImpl <em>Edge Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.EdgeMappingImpl
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getEdgeMapping()
   * @generated
   */
  int EDGE_MAPPING = 4;

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
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getImportSection <em>Import Section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Import Section</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getImportSection()
   * @see #getDiagramSynthesis()
   * @generated
   */
  EReference getDiagramSynthesis_ImportSection();

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
   * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition <em>Mapping Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mapping Definition</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition
   * @generated
   */
  EClass getMappingDefinition();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getType()
   * @see #getMappingDefinition()
   * @generated
   */
  EReference getMappingDefinition_Type();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getName()
   * @see #getMappingDefinition()
   * @generated
   */
  EAttribute getMappingDefinition_Name();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getNodeMappings <em>Node Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Node Mappings</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getNodeMappings()
   * @see #getMappingDefinition()
   * @generated
   */
  EReference getMappingDefinition_NodeMappings();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getPortMappings <em>Port Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Port Mappings</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getPortMappings()
   * @see #getMappingDefinition()
   * @generated
   */
  EReference getMappingDefinition_PortMappings();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getEdgeMappings <em>Edge Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Edge Mappings</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.MappingDefinition#getEdgeMappings()
   * @see #getMappingDefinition()
   * @generated
   */
  EReference getMappingDefinition_EdgeMappings();

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
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getWithPorts <em>With Ports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>With Ports</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getWithPorts()
   * @see #getNodeMapping()
   * @generated
   */
  EReference getNodeMapping_WithPorts();

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
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getPortMappings <em>Port Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Port Mappings</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping#getPortMappings()
   * @see #getNodeMapping()
   * @generated
   */
  EReference getNodeMapping_PortMappings();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping <em>Port Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Port Mapping</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping
   * @generated
   */
  EClass getPortMapping();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getName()
   * @see #getPortMapping()
   * @generated
   */
  EAttribute getPortMapping_Name();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getNodeElementType <em>Node Element Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Node Element Type</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getNodeElementType()
   * @see #getPortMapping()
   * @generated
   */
  EReference getPortMapping_NodeElementType();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getNodeElementName <em>Node Element Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Node Element Name</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getNodeElementName()
   * @see #getPortMapping()
   * @generated
   */
  EAttribute getPortMapping_NodeElementName();

  /**
   * Returns the meta object for the containment reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Elements</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getElements()
   * @see #getPortMapping()
   * @generated
   */
  EReference getPortMapping_Elements();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getIdentifiedBy <em>Identified By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Identified By</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getIdentifiedBy()
   * @see #getPortMapping()
   * @generated
   */
  EReference getPortMapping_IdentifiedBy();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getIndentifiedBy <em>Indentified By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Indentified By</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getIndentifiedBy()
   * @see #getPortMapping()
   * @generated
   */
  EReference getPortMapping_IndentifiedBy();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getFigureType <em>Figure Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Figure Type</em>'.
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.PortMapping#getFigureType()
   * @see #getPortMapping()
   * @generated
   */
  EReference getPortMapping_FigureType();

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
     * The meta object literal for the '<em><b>Import Section</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIAGRAM_SYNTHESIS__IMPORT_SECTION = eINSTANCE.getDiagramSynthesis_ImportSection();

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
     * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl <em>Mapping Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.MappingDefinitionImpl
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getMappingDefinition()
     * @generated
     */
    EClass MAPPING_DEFINITION = eINSTANCE.getMappingDefinition();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_DEFINITION__TYPE = eINSTANCE.getMappingDefinition_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_DEFINITION__NAME = eINSTANCE.getMappingDefinition_Name();

    /**
     * The meta object literal for the '<em><b>Node Mappings</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_DEFINITION__NODE_MAPPINGS = eINSTANCE.getMappingDefinition_NodeMappings();

    /**
     * The meta object literal for the '<em><b>Port Mappings</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_DEFINITION__PORT_MAPPINGS = eINSTANCE.getMappingDefinition_PortMappings();

    /**
     * The meta object literal for the '<em><b>Edge Mappings</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_DEFINITION__EDGE_MAPPINGS = eINSTANCE.getMappingDefinition_EdgeMappings();

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
     * The meta object literal for the '<em><b>With Ports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_MAPPING__WITH_PORTS = eINSTANCE.getNodeMapping_WithPorts();

    /**
     * The meta object literal for the '<em><b>Figure Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_MAPPING__FIGURE_TYPE = eINSTANCE.getNodeMapping_FigureType();

    /**
     * The meta object literal for the '<em><b>Port Mappings</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NODE_MAPPING__PORT_MAPPINGS = eINSTANCE.getNodeMapping_PortMappings();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl <em>Port Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.PortMappingImpl
     * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramPackageImpl#getPortMapping()
     * @generated
     */
    EClass PORT_MAPPING = eINSTANCE.getPortMapping();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PORT_MAPPING__NAME = eINSTANCE.getPortMapping_Name();

    /**
     * The meta object literal for the '<em><b>Node Element Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PORT_MAPPING__NODE_ELEMENT_TYPE = eINSTANCE.getPortMapping_NodeElementType();

    /**
     * The meta object literal for the '<em><b>Node Element Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PORT_MAPPING__NODE_ELEMENT_NAME = eINSTANCE.getPortMapping_NodeElementName();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PORT_MAPPING__ELEMENTS = eINSTANCE.getPortMapping_Elements();

    /**
     * The meta object literal for the '<em><b>Identified By</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PORT_MAPPING__IDENTIFIED_BY = eINSTANCE.getPortMapping_IdentifiedBy();

    /**
     * The meta object literal for the '<em><b>Indentified By</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PORT_MAPPING__INDENTIFIED_BY = eINSTANCE.getPortMapping_IndentifiedBy();

    /**
     * The meta object literal for the '<em><b>Figure Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PORT_MAPPING__FIGURE_TYPE = eINSTANCE.getPortMapping_FigureType();

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
