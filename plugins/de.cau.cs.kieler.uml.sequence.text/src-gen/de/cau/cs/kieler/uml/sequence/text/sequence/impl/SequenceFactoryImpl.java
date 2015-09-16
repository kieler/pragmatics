/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class SequenceFactoryImpl extends EFactoryImpl implements SequenceFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SequenceFactory init()
  {
    try
    {
      SequenceFactory theSequenceFactory = (SequenceFactory)EPackage.Registry.INSTANCE.getEFactory(SequencePackage.eNS_URI);
      if (theSequenceFactory != null)
      {
        return theSequenceFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SequenceFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequenceFactoryImpl()
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
      case SequencePackage.SEQUENCE_DIAGRAM: return createSequenceDiagram();
      case SequencePackage.LIFELINE: return createLifeline();
      case SequencePackage.INTERACTION: return createInteraction();
      case SequencePackage.TWO_LIFELINE_MESSAGE: return createTwoLifelineMessage();
      case SequencePackage.ONE_LIFELINE_MESSAGE: return createOneLifelineMessage();
      case SequencePackage.SELF_MESSAGE: return createSelfMessage();
      case SequencePackage.ONE_LIFELINE_NOTE: return createOneLifelineNote();
      case SequencePackage.DESTROY_LIFELINE_EVENT: return createDestroyLifelineEvent();
      case SequencePackage.FRAGMENT: return createFragment();
      case SequencePackage.SECTION: return createSection();
      case SequencePackage.REFINEMENT: return createRefinement();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case SequencePackage.MESSAGE_TYPE:
        return createMessageTypeFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case SequencePackage.MESSAGE_TYPE:
        return convertMessageTypeToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequenceDiagram createSequenceDiagram()
  {
    SequenceDiagramImpl sequenceDiagram = new SequenceDiagramImpl();
    return sequenceDiagram;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Lifeline createLifeline()
  {
    LifelineImpl lifeline = new LifelineImpl();
    return lifeline;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Interaction createInteraction()
  {
    InteractionImpl interaction = new InteractionImpl();
    return interaction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TwoLifelineMessage createTwoLifelineMessage()
  {
    TwoLifelineMessageImpl twoLifelineMessage = new TwoLifelineMessageImpl();
    return twoLifelineMessage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OneLifelineMessage createOneLifelineMessage()
  {
    OneLifelineMessageImpl oneLifelineMessage = new OneLifelineMessageImpl();
    return oneLifelineMessage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SelfMessage createSelfMessage()
  {
    SelfMessageImpl selfMessage = new SelfMessageImpl();
    return selfMessage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OneLifelineNote createOneLifelineNote()
  {
    OneLifelineNoteImpl oneLifelineNote = new OneLifelineNoteImpl();
    return oneLifelineNote;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DestroyLifelineEvent createDestroyLifelineEvent()
  {
    DestroyLifelineEventImpl destroyLifelineEvent = new DestroyLifelineEventImpl();
    return destroyLifelineEvent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Fragment createFragment()
  {
    FragmentImpl fragment = new FragmentImpl();
    return fragment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Section createSection()
  {
    SectionImpl section = new SectionImpl();
    return section;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Refinement createRefinement()
  {
    RefinementImpl refinement = new RefinementImpl();
    return refinement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MessageType createMessageTypeFromString(EDataType eDataType, String initialValue)
  {
    MessageType result = MessageType.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMessageTypeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequencePackage getSequencePackage()
  {
    return (SequencePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SequencePackage getPackage()
  {
    return SequencePackage.eINSTANCE;
  }

} //SequenceFactoryImpl
