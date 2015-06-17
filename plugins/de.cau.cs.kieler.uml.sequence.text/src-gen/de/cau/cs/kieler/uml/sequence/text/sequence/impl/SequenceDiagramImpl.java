/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.Interaction;
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceDiagramImpl#getDiagramName <em>Diagram Name</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceDiagramImpl#getLocals <em>Locals</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceDiagramImpl#getLifelines <em>Lifelines</em>}</li>
 *   <li>{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceDiagramImpl#getInteractions <em>Interactions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequenceDiagramImpl extends MinimalEObjectImpl.Container implements SequenceDiagram
{
  /**
   * The default value of the '{@link #getDiagramName() <em>Diagram Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDiagramName()
   * @generated
   * @ordered
   */
  protected static final String DIAGRAM_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDiagramName() <em>Diagram Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDiagramName()
   * @generated
   * @ordered
   */
  protected String diagramName = DIAGRAM_NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getLocals() <em>Locals</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocals()
   * @generated
   * @ordered
   */
  protected EList<LocalVariable> locals;

  /**
   * The cached value of the '{@link #getLifelines() <em>Lifelines</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLifelines()
   * @generated
   * @ordered
   */
  protected EList<Lifeline> lifelines;

  /**
   * The cached value of the '{@link #getInteractions() <em>Interactions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInteractions()
   * @generated
   * @ordered
   */
  protected EList<Interaction> interactions;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SequenceDiagramImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SequencePackage.Literals.SEQUENCE_DIAGRAM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDiagramName()
  {
    return diagramName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDiagramName(String newDiagramName)
  {
    String oldDiagramName = diagramName;
    diagramName = newDiagramName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SequencePackage.SEQUENCE_DIAGRAM__DIAGRAM_NAME, oldDiagramName, diagramName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<LocalVariable> getLocals()
  {
    if (locals == null)
    {
      locals = new EObjectContainmentEList<LocalVariable>(LocalVariable.class, this, SequencePackage.SEQUENCE_DIAGRAM__LOCALS);
    }
    return locals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Lifeline> getLifelines()
  {
    if (lifelines == null)
    {
      lifelines = new EObjectContainmentEList<Lifeline>(Lifeline.class, this, SequencePackage.SEQUENCE_DIAGRAM__LIFELINES);
    }
    return lifelines;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Interaction> getInteractions()
  {
    if (interactions == null)
    {
      interactions = new EObjectContainmentEList<Interaction>(Interaction.class, this, SequencePackage.SEQUENCE_DIAGRAM__INTERACTIONS);
    }
    return interactions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SequencePackage.SEQUENCE_DIAGRAM__LOCALS:
        return ((InternalEList<?>)getLocals()).basicRemove(otherEnd, msgs);
      case SequencePackage.SEQUENCE_DIAGRAM__LIFELINES:
        return ((InternalEList<?>)getLifelines()).basicRemove(otherEnd, msgs);
      case SequencePackage.SEQUENCE_DIAGRAM__INTERACTIONS:
        return ((InternalEList<?>)getInteractions()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SequencePackage.SEQUENCE_DIAGRAM__DIAGRAM_NAME:
        return getDiagramName();
      case SequencePackage.SEQUENCE_DIAGRAM__LOCALS:
        return getLocals();
      case SequencePackage.SEQUENCE_DIAGRAM__LIFELINES:
        return getLifelines();
      case SequencePackage.SEQUENCE_DIAGRAM__INTERACTIONS:
        return getInteractions();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SequencePackage.SEQUENCE_DIAGRAM__DIAGRAM_NAME:
        setDiagramName((String)newValue);
        return;
      case SequencePackage.SEQUENCE_DIAGRAM__LOCALS:
        getLocals().clear();
        getLocals().addAll((Collection<? extends LocalVariable>)newValue);
        return;
      case SequencePackage.SEQUENCE_DIAGRAM__LIFELINES:
        getLifelines().clear();
        getLifelines().addAll((Collection<? extends Lifeline>)newValue);
        return;
      case SequencePackage.SEQUENCE_DIAGRAM__INTERACTIONS:
        getInteractions().clear();
        getInteractions().addAll((Collection<? extends Interaction>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SequencePackage.SEQUENCE_DIAGRAM__DIAGRAM_NAME:
        setDiagramName(DIAGRAM_NAME_EDEFAULT);
        return;
      case SequencePackage.SEQUENCE_DIAGRAM__LOCALS:
        getLocals().clear();
        return;
      case SequencePackage.SEQUENCE_DIAGRAM__LIFELINES:
        getLifelines().clear();
        return;
      case SequencePackage.SEQUENCE_DIAGRAM__INTERACTIONS:
        getInteractions().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SequencePackage.SEQUENCE_DIAGRAM__DIAGRAM_NAME:
        return DIAGRAM_NAME_EDEFAULT == null ? diagramName != null : !DIAGRAM_NAME_EDEFAULT.equals(diagramName);
      case SequencePackage.SEQUENCE_DIAGRAM__LOCALS:
        return locals != null && !locals.isEmpty();
      case SequencePackage.SEQUENCE_DIAGRAM__LIFELINES:
        return lifelines != null && !lifelines.isEmpty();
      case SequencePackage.SEQUENCE_DIAGRAM__INTERACTIONS:
        return interactions != null && !interactions.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (diagramName: ");
    result.append(diagramName);
    result.append(')');
    return result.toString();
  }

} //SequenceDiagramImpl
