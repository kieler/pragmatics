/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.util;

import de.cau.cs.kieler.uml.sequence.text.sequence.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage
 * @generated
 */
public class SequenceSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SequencePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequenceSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = SequencePackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case SequencePackage.SEQUENCE_DIAGRAM:
      {
        SequenceDiagram sequenceDiagram = (SequenceDiagram)theEObject;
        T result = caseSequenceDiagram(sequenceDiagram);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.LOCAL_VARIABLE:
      {
        LocalVariable localVariable = (LocalVariable)theEObject;
        T result = caseLocalVariable(localVariable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.LIFELINE:
      {
        Lifeline lifeline = (Lifeline)theEObject;
        T result = caseLifeline(lifeline);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.INTERACTION:
      {
        Interaction interaction = (Interaction)theEObject;
        T result = caseInteraction(interaction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.TWO_LIFELINE_MESSAGE:
      {
        TwoLifelineMessage twoLifelineMessage = (TwoLifelineMessage)theEObject;
        T result = caseTwoLifelineMessage(twoLifelineMessage);
        if (result == null) result = caseInteraction(twoLifelineMessage);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.ONE_LIFELINE_MESSAGE:
      {
        OneLifelineMessage oneLifelineMessage = (OneLifelineMessage)theEObject;
        T result = caseOneLifelineMessage(oneLifelineMessage);
        if (result == null) result = caseInteraction(oneLifelineMessage);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.ONE_LIFELINE_END_BLOCK:
      {
        OneLifelineEndBlock oneLifelineEndBlock = (OneLifelineEndBlock)theEObject;
        T result = caseOneLifelineEndBlock(oneLifelineEndBlock);
        if (result == null) result = caseInteraction(oneLifelineEndBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.ONE_LIFELINE_NOTE:
      {
        OneLifelineNote oneLifelineNote = (OneLifelineNote)theEObject;
        T result = caseOneLifelineNote(oneLifelineNote);
        if (result == null) result = caseInteraction(oneLifelineNote);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.DESTROY:
      {
        Destroy destroy = (Destroy)theEObject;
        T result = caseDestroy(destroy);
        if (result == null) result = caseInteraction(destroy);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.FRAGMENT:
      {
        Fragment fragment = (Fragment)theEObject;
        T result = caseFragment(fragment);
        if (result == null) result = caseInteraction(fragment);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.FRAGMENT_CONTENT:
      {
        FragmentContent fragmentContent = (FragmentContent)theEObject;
        T result = caseFragmentContent(fragmentContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SequencePackage.REFINEMENT:
      {
        Refinement refinement = (Refinement)theEObject;
        T result = caseRefinement(refinement);
        if (result == null) result = caseInteraction(refinement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSequenceDiagram(SequenceDiagram object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Local Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Local Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLocalVariable(LocalVariable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Lifeline</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Lifeline</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLifeline(Lifeline object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Interaction</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Interaction</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInteraction(Interaction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Two Lifeline Message</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Two Lifeline Message</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTwoLifelineMessage(TwoLifelineMessage object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>One Lifeline Message</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>One Lifeline Message</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOneLifelineMessage(OneLifelineMessage object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>One Lifeline End Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>One Lifeline End Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOneLifelineEndBlock(OneLifelineEndBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>One Lifeline Note</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>One Lifeline Note</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOneLifelineNote(OneLifelineNote object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Destroy</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Destroy</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDestroy(Destroy object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fragment</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fragment</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFragment(Fragment object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fragment Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fragment Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFragmentContent(FragmentContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Refinement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Refinement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRefinement(Refinement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //SequenceSwitch
