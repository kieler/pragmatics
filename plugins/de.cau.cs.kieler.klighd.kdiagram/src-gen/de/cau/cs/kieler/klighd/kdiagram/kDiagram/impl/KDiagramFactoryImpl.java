/**
 */
package de.cau.cs.kieler.klighd.kdiagram.kDiagram.impl;

import de.cau.cs.kieler.klighd.kdiagram.kDiagram.*;

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
public class KDiagramFactoryImpl extends EFactoryImpl implements KDiagramFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static KDiagramFactory init()
  {
    try
    {
      KDiagramFactory theKDiagramFactory = (KDiagramFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cau.de/cs/kieler/klighd/kdiagram/KDiagram"); 
      if (theKDiagramFactory != null)
      {
        return theKDiagramFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new KDiagramFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KDiagramFactoryImpl()
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
    switch (eClass.getClassifierID())
    {
      case KDiagramPackage.DIAGRAM_SYNTHESIS: return createDiagramSynthesis();
      case KDiagramPackage.XVARIABLE_DECLARATION: return createXVariableDeclaration();
      case KDiagramPackage.NODE_MAPPING: return createNodeMapping();
      case KDiagramPackage.EDGE_MAPPING: return createEdgeMapping();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiagramSynthesis createDiagramSynthesis()
  {
    DiagramSynthesisImpl diagramSynthesis = new DiagramSynthesisImpl();
    return diagramSynthesis;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XVariableDeclaration createXVariableDeclaration()
  {
    XVariableDeclarationImpl xVariableDeclaration = new XVariableDeclarationImpl();
    return xVariableDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NodeMapping createNodeMapping()
  {
    NodeMappingImpl nodeMapping = new NodeMappingImpl();
    return nodeMapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EdgeMapping createEdgeMapping()
  {
    EdgeMappingImpl edgeMapping = new EdgeMappingImpl();
    return edgeMapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KDiagramPackage getKDiagramPackage()
  {
    return (KDiagramPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static KDiagramPackage getPackage()
  {
    return KDiagramPackage.eINSTANCE;
  }

} //KDiagramFactoryImpl
