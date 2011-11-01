/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.kiml.formats.gml.gml;

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
 * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlFactory
 * @model kind="package"
 * @generated
 */
public interface GmlPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "gml";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://kieler.cs.cau.de/GML";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "gml";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  GmlPackage eINSTANCE = de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlPackageImpl.init();

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlModelImpl
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlPackageImpl#getGmlModel()
   * @generated
   */
  int GML_MODEL = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GML_MODEL__ELEMENTS = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GML_MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.kiml.formats.gml.gml.impl.ElementImpl <em>Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.impl.ElementImpl
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlPackageImpl#getElement()
   * @generated
   */
  int ELEMENT = 1;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__VALUE = 1;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__ELEMENTS = 2;

  /**
   * The number of structural features of the '<em>Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_FEATURE_COUNT = 3;


  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel
   * @generated
   */
  EClass getGmlModel();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.GmlModel#getElements()
   * @see #getGmlModel()
   * @generated
   */
  EReference getGmlModel_Elements();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element</em>'.
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.Element
   * @generated
   */
  EClass getElement();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element#getKey <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.Element#getKey()
   * @see #getElement()
   * @generated
   */
  EAttribute getElement_Key();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.Element#getValue()
   * @see #getElement()
   * @generated
   */
  EAttribute getElement_Value();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kiml.formats.gml.gml.Element#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see de.cau.cs.kieler.kiml.formats.gml.gml.Element#getElements()
   * @see #getElement()
   * @generated
   */
  EReference getElement_Elements();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  GmlFactory getGmlFactory();

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
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlModelImpl
     * @see de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlPackageImpl#getGmlModel()
     * @generated
     */
    EClass GML_MODEL = eINSTANCE.getGmlModel();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GML_MODEL__ELEMENTS = eINSTANCE.getGmlModel_Elements();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.kiml.formats.gml.gml.impl.ElementImpl <em>Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kiml.formats.gml.gml.impl.ElementImpl
     * @see de.cau.cs.kieler.kiml.formats.gml.gml.impl.GmlPackageImpl#getElement()
     * @generated
     */
    EClass ELEMENT = eINSTANCE.getElement();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT__KEY = eINSTANCE.getElement_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT__VALUE = eINSTANCE.getElement_Value();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT__ELEMENTS = eINSTANCE.getElement_Elements();

  }

} //GmlPackage
