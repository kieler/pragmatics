/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.ksbase.featureDefinition;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.ksbase.featureDefinition.FeatureDefinitionPackage
 * @generated
 */
public interface FeatureDefinitionFactory extends EFactory
{
  /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  FeatureDefinitionFactory eINSTANCE = de.cau.cs.kieler.ksbase.featureDefinition.impl.FeatureDefinitionFactoryImpl.init();

  /**
     * Returns a new object of class '<em>Feature Definitions</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Feature Definitions</em>'.
     * @generated
     */
  FeatureDefinitions createFeatureDefinitions();

  /**
     * Returns a new object of class '<em>Feature Type</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Feature Type</em>'.
     * @generated
     */
  FeatureType createFeatureType();

  /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
  FeatureDefinitionPackage getFeatureDefinitionPackage();

} //FeatureDefinitionFactory
