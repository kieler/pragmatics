/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.xtype.XImportSection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Synthesis</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getImportSection <em>Import Section</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getName <em>Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getDiagramSynthesis()
 * @model
 * @generated
 */
public interface DiagramSynthesis extends EObject
{
  /**
   * Returns the value of the '<em><b>Package Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Package Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Package Name</em>' attribute.
   * @see #setPackageName(String)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getDiagramSynthesis_PackageName()
   * @model
   * @generated
   */
  String getPackageName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getPackageName <em>Package Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Package Name</em>' attribute.
   * @see #getPackageName()
   * @generated
   */
  void setPackageName(String value);

  /**
   * Returns the value of the '<em><b>Import Section</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Import Section</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Import Section</em>' containment reference.
   * @see #setImportSection(XImportSection)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getDiagramSynthesis_ImportSection()
   * @model containment="true"
   * @generated
   */
  XImportSection getImportSection();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getImportSection <em>Import Section</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Import Section</em>' containment reference.
   * @see #getImportSection()
   * @generated
   */
  void setImportSection(XImportSection value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getDiagramSynthesis_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapping</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapping</em>' containment reference.
   * @see #setMapping(MappingDefinition)
   * @see de.cau.cs.kieler.klighd.kdiagram.kDiagram.KDiagramPackage#getDiagramSynthesis_Mapping()
   * @model containment="true"
   * @generated
   */
  MappingDefinition getMapping();

  /**
   * Sets the value of the '{@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.DiagramSynthesis#getMapping <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapping</em>' containment reference.
   * @see #getMapping()
   * @generated
   */
  void setMapping(MappingDefinition value);

} // DiagramSynthesis
