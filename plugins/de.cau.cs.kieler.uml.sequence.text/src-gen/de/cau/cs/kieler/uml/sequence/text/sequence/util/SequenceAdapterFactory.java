/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.util;

import de.cau.cs.kieler.uml.sequence.text.sequence.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage
 * @generated
 */
public class SequenceAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SequencePackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequenceAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = SequencePackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SequenceSwitch<Adapter> modelSwitch =
    new SequenceSwitch<Adapter>()
    {
      @Override
      public Adapter caseSequenceDiagram(SequenceDiagram object)
      {
        return createSequenceDiagramAdapter();
      }
      @Override
      public Adapter caseLocalVariable(LocalVariable object)
      {
        return createLocalVariableAdapter();
      }
      @Override
      public Adapter caseLifeline(Lifeline object)
      {
        return createLifelineAdapter();
      }
      @Override
      public Adapter caseInteraction(Interaction object)
      {
        return createInteractionAdapter();
      }
      @Override
      public Adapter caseTwoLifelineMessage(TwoLifelineMessage object)
      {
        return createTwoLifelineMessageAdapter();
      }
      @Override
      public Adapter caseOneLifelineMessage(OneLifelineMessage object)
      {
        return createOneLifelineMessageAdapter();
      }
      @Override
      public Adapter caseOneLifelineEndBlock(OneLifelineEndBlock object)
      {
        return createOneLifelineEndBlockAdapter();
      }
      @Override
      public Adapter caseOneLifelineNote(OneLifelineNote object)
      {
        return createOneLifelineNoteAdapter();
      }
      @Override
      public Adapter caseDestroy(Destroy object)
      {
        return createDestroyAdapter();
      }
      @Override
      public Adapter caseFragment(Fragment object)
      {
        return createFragmentAdapter();
      }
      @Override
      public Adapter caseFragmentContent(FragmentContent object)
      {
        return createFragmentContentAdapter();
      }
      @Override
      public Adapter caseRefinement(Refinement object)
      {
        return createRefinementAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram <em>Diagram</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram
   * @generated
   */
  public Adapter createSequenceDiagramAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable <em>Local Variable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable
   * @generated
   */
  public Adapter createLocalVariableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline <em>Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline
   * @generated
   */
  public Adapter createLifelineAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Interaction <em>Interaction</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Interaction
   * @generated
   */
  public Adapter createInteractionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage <em>Two Lifeline Message</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage
   * @generated
   */
  public Adapter createTwoLifelineMessageAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage <em>One Lifeline Message</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage
   * @generated
   */
  public Adapter createOneLifelineMessageAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock <em>One Lifeline End Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock
   * @generated
   */
  public Adapter createOneLifelineEndBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote <em>One Lifeline Note</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote
   * @generated
   */
  public Adapter createOneLifelineNoteAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Destroy <em>Destroy</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Destroy
   * @generated
   */
  public Adapter createDestroyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Fragment <em>Fragment</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Fragment
   * @generated
   */
  public Adapter createFragmentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent <em>Fragment Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent
   * @generated
   */
  public Adapter createFragmentContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Refinement <em>Refinement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Refinement
   * @generated
   */
  public Adapter createRefinementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //SequenceAdapterFactory
