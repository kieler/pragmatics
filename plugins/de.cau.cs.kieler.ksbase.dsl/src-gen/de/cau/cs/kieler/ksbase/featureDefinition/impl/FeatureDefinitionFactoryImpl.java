/**
 * <copyright>
 * </copyright>
 *
 */
package de.cau.cs.kieler.ksbase.featureDefinition.impl;

import de.cau.cs.kieler.ksbase.featureDefinition.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FeatureDefinitionFactoryImpl extends EFactoryImpl implements FeatureDefinitionFactory
{
  /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public static FeatureDefinitionFactory init()
  {
        try {
            FeatureDefinitionFactory theFeatureDefinitionFactory = (FeatureDefinitionFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cau.de/cs/kieler/ksbase/FeatureDefinition"); 
            if (theFeatureDefinitionFactory != null) {
                return theFeatureDefinitionFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FeatureDefinitionFactoryImpl();
    }

  /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public FeatureDefinitionFactoryImpl()
  {
        super();
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
  public EObject create(EClass eClass)
  {
        switch (eClass.getClassifierID()) {
            case FeatureDefinitionPackage.FEATURE_DEFINITIONS: return createFeatureDefinitions();
            case FeatureDefinitionPackage.FEATURE_TYPE: return createFeatureType();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public FeatureDefinitions createFeatureDefinitions()
  {
        FeatureDefinitionsImpl featureDefinitions = new FeatureDefinitionsImpl();
        return featureDefinitions;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public FeatureType createFeatureType()
  {
        FeatureTypeImpl featureType = new FeatureTypeImpl();
        return featureType;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public FeatureDefinitionPackage getFeatureDefinitionPackage()
  {
        return (FeatureDefinitionPackage)getEPackage();
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
  @Deprecated
  public static FeatureDefinitionPackage getPackage()
  {
        return FeatureDefinitionPackage.eINSTANCE;
    }

} //FeatureDefinitionFactoryImpl
