/**
 */
package de.cau.cs.kieler.kiml.grana.text.grana.impl;

import de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.kiml.grana.text.grana.RegularJob;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Regular Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.RegularJobImpl#isLayoutBeforeAnalysis <em>Layout Before Analysis</em>}</li>
 *   <li>{@link de.cau.cs.kieler.kiml.grana.text.grana.impl.RegularJobImpl#isMeasureExecutionTime <em>Measure Execution Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RegularJobImpl extends JobImpl implements RegularJob
{
  /**
   * The default value of the '{@link #isLayoutBeforeAnalysis() <em>Layout Before Analysis</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLayoutBeforeAnalysis()
   * @generated
   * @ordered
   */
  protected static final boolean LAYOUT_BEFORE_ANALYSIS_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isLayoutBeforeAnalysis() <em>Layout Before Analysis</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLayoutBeforeAnalysis()
   * @generated
   * @ordered
   */
  protected boolean layoutBeforeAnalysis = LAYOUT_BEFORE_ANALYSIS_EDEFAULT;

  /**
   * The default value of the '{@link #isMeasureExecutionTime() <em>Measure Execution Time</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMeasureExecutionTime()
   * @generated
   * @ordered
   */
  protected static final boolean MEASURE_EXECUTION_TIME_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isMeasureExecutionTime() <em>Measure Execution Time</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isMeasureExecutionTime()
   * @generated
   * @ordered
   */
  protected boolean measureExecutionTime = MEASURE_EXECUTION_TIME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RegularJobImpl()
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
    return GranaPackage.Literals.REGULAR_JOB;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isLayoutBeforeAnalysis()
  {
    return layoutBeforeAnalysis;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLayoutBeforeAnalysis(boolean newLayoutBeforeAnalysis)
  {
    boolean oldLayoutBeforeAnalysis = layoutBeforeAnalysis;
    layoutBeforeAnalysis = newLayoutBeforeAnalysis;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.REGULAR_JOB__LAYOUT_BEFORE_ANALYSIS, oldLayoutBeforeAnalysis, layoutBeforeAnalysis));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isMeasureExecutionTime()
  {
    return measureExecutionTime;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMeasureExecutionTime(boolean newMeasureExecutionTime)
  {
    boolean oldMeasureExecutionTime = measureExecutionTime;
    measureExecutionTime = newMeasureExecutionTime;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.REGULAR_JOB__MEASURE_EXECUTION_TIME, oldMeasureExecutionTime, measureExecutionTime));
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
      case GranaPackage.REGULAR_JOB__LAYOUT_BEFORE_ANALYSIS:
        return isLayoutBeforeAnalysis();
      case GranaPackage.REGULAR_JOB__MEASURE_EXECUTION_TIME:
        return isMeasureExecutionTime();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case GranaPackage.REGULAR_JOB__LAYOUT_BEFORE_ANALYSIS:
        setLayoutBeforeAnalysis((Boolean)newValue);
        return;
      case GranaPackage.REGULAR_JOB__MEASURE_EXECUTION_TIME:
        setMeasureExecutionTime((Boolean)newValue);
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
      case GranaPackage.REGULAR_JOB__LAYOUT_BEFORE_ANALYSIS:
        setLayoutBeforeAnalysis(LAYOUT_BEFORE_ANALYSIS_EDEFAULT);
        return;
      case GranaPackage.REGULAR_JOB__MEASURE_EXECUTION_TIME:
        setMeasureExecutionTime(MEASURE_EXECUTION_TIME_EDEFAULT);
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
      case GranaPackage.REGULAR_JOB__LAYOUT_BEFORE_ANALYSIS:
        return layoutBeforeAnalysis != LAYOUT_BEFORE_ANALYSIS_EDEFAULT;
      case GranaPackage.REGULAR_JOB__MEASURE_EXECUTION_TIME:
        return measureExecutionTime != MEASURE_EXECUTION_TIME_EDEFAULT;
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
    result.append(" (layoutBeforeAnalysis: ");
    result.append(layoutBeforeAnalysis);
    result.append(", measureExecutionTime: ");
    result.append(measureExecutionTime);
    result.append(')');
    return result.toString();
  }

} //RegularJobImpl
