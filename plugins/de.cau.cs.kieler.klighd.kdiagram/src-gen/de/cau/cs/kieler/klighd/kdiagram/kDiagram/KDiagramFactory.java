/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage
 * @generated
 */
public interface KDiagramFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  KDiagramFactory eINSTANCE = de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl.KDiagramFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Diagram Synthesis</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Diagram Synthesis</em>'.
   * @generated
   */
  DiagramSynthesis createDiagramSynthesis();

  /**
   * Returns a new object of class '<em>Mapping Definition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mapping Definition</em>'.
   * @generated
   */
  MappingDefinition createMappingDefinition();

  /**
   * Returns a new object of class '<em>Node Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Node Mapping</em>'.
   * @generated
   */
  NodeMapping createNodeMapping();

  /**
   * Returns a new object of class '<em>Port Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Port Mapping</em>'.
   * @generated
   */
  PortMapping createPortMapping();

  /**
   * Returns a new object of class '<em>Edge Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Edge Mapping</em>'.
   * @generated
   */
  EdgeMapping createEdgeMapping();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  KDiagramPackage getKDiagramPackage();

} //KDiagramFactory
