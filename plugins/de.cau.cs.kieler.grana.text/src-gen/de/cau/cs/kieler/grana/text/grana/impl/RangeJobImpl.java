/**
 */
package de.cau.cs.kieler.grana.text.grana.impl;

import de.cau.cs.kieler.grana.text.grana.Analysis;
import de.cau.cs.kieler.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.grana.text.grana.Range;
import de.cau.cs.kieler.grana.text.grana.RangeJob;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Range Job</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.RangeJobImpl#isMeasureExecutionTime <em>Measure Execution Time</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.RangeJobImpl#getRangeOption <em>Range Option</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.RangeJobImpl#getRangeValues <em>Range Values</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.RangeJobImpl#getRangeAnalysis <em>Range Analysis</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.RangeJobImpl#getRangeAnalysisComponent <em>Range Analysis Component</em>}</li>
 *   <li>{@link de.cau.cs.kieler.grana.text.grana.impl.RangeJobImpl#getRangeAnalyses <em>Range Analyses</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RangeJobImpl extends JobImpl implements RangeJob
{
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
   * The default value of the '{@link #getRangeOption() <em>Range Option</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeOption()
   * @generated
   * @ordered
   */
  protected static final String RANGE_OPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRangeOption() <em>Range Option</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeOption()
   * @generated
   * @ordered
   */
  protected String rangeOption = RANGE_OPTION_EDEFAULT;

  /**
   * The cached value of the '{@link #getRangeValues() <em>Range Values</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeValues()
   * @generated
   * @ordered
   */
  protected Range rangeValues;

  /**
   * The cached value of the '{@link #getRangeAnalysis() <em>Range Analysis</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeAnalysis()
   * @generated
   * @ordered
   */
  protected Analysis rangeAnalysis;

  /**
   * The default value of the '{@link #getRangeAnalysisComponent() <em>Range Analysis Component</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeAnalysisComponent()
   * @generated
   * @ordered
   */
  protected static final int RANGE_ANALYSIS_COMPONENT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getRangeAnalysisComponent() <em>Range Analysis Component</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeAnalysisComponent()
   * @generated
   * @ordered
   */
  protected int rangeAnalysisComponent = RANGE_ANALYSIS_COMPONENT_EDEFAULT;

  /**
   * The cached value of the '{@link #getRangeAnalyses() <em>Range Analyses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeAnalyses()
   * @generated
   * @ordered
   */
  protected EList<Analysis> rangeAnalyses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RangeJobImpl()
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
    return GranaPackage.Literals.RANGE_JOB;
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
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.RANGE_JOB__MEASURE_EXECUTION_TIME, oldMeasureExecutionTime, measureExecutionTime));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRangeOption()
  {
    return rangeOption;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRangeOption(String newRangeOption)
  {
    String oldRangeOption = rangeOption;
    rangeOption = newRangeOption;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.RANGE_JOB__RANGE_OPTION, oldRangeOption, rangeOption));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Range getRangeValues()
  {
    return rangeValues;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRangeValues(Range newRangeValues, NotificationChain msgs)
  {
    Range oldRangeValues = rangeValues;
    rangeValues = newRangeValues;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GranaPackage.RANGE_JOB__RANGE_VALUES, oldRangeValues, newRangeValues);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRangeValues(Range newRangeValues)
  {
    if (newRangeValues != rangeValues)
    {
      NotificationChain msgs = null;
      if (rangeValues != null)
        msgs = ((InternalEObject)rangeValues).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GranaPackage.RANGE_JOB__RANGE_VALUES, null, msgs);
      if (newRangeValues != null)
        msgs = ((InternalEObject)newRangeValues).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GranaPackage.RANGE_JOB__RANGE_VALUES, null, msgs);
      msgs = basicSetRangeValues(newRangeValues, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.RANGE_JOB__RANGE_VALUES, newRangeValues, newRangeValues));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Analysis getRangeAnalysis()
  {
    return rangeAnalysis;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRangeAnalysis(Analysis newRangeAnalysis, NotificationChain msgs)
  {
    Analysis oldRangeAnalysis = rangeAnalysis;
    rangeAnalysis = newRangeAnalysis;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GranaPackage.RANGE_JOB__RANGE_ANALYSIS, oldRangeAnalysis, newRangeAnalysis);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRangeAnalysis(Analysis newRangeAnalysis)
  {
    if (newRangeAnalysis != rangeAnalysis)
    {
      NotificationChain msgs = null;
      if (rangeAnalysis != null)
        msgs = ((InternalEObject)rangeAnalysis).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GranaPackage.RANGE_JOB__RANGE_ANALYSIS, null, msgs);
      if (newRangeAnalysis != null)
        msgs = ((InternalEObject)newRangeAnalysis).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - GranaPackage.RANGE_JOB__RANGE_ANALYSIS, null, msgs);
      msgs = basicSetRangeAnalysis(newRangeAnalysis, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.RANGE_JOB__RANGE_ANALYSIS, newRangeAnalysis, newRangeAnalysis));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getRangeAnalysisComponent()
  {
    return rangeAnalysisComponent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRangeAnalysisComponent(int newRangeAnalysisComponent)
  {
    int oldRangeAnalysisComponent = rangeAnalysisComponent;
    rangeAnalysisComponent = newRangeAnalysisComponent;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GranaPackage.RANGE_JOB__RANGE_ANALYSIS_COMPONENT, oldRangeAnalysisComponent, rangeAnalysisComponent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Analysis> getRangeAnalyses()
  {
    if (rangeAnalyses == null)
    {
      rangeAnalyses = new EObjectContainmentEList<Analysis>(Analysis.class, this, GranaPackage.RANGE_JOB__RANGE_ANALYSES);
    }
    return rangeAnalyses;
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
      case GranaPackage.RANGE_JOB__RANGE_VALUES:
        return basicSetRangeValues(null, msgs);
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS:
        return basicSetRangeAnalysis(null, msgs);
      case GranaPackage.RANGE_JOB__RANGE_ANALYSES:
        return ((InternalEList<?>)getRangeAnalyses()).basicRemove(otherEnd, msgs);
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
      case GranaPackage.RANGE_JOB__MEASURE_EXECUTION_TIME:
        return isMeasureExecutionTime();
      case GranaPackage.RANGE_JOB__RANGE_OPTION:
        return getRangeOption();
      case GranaPackage.RANGE_JOB__RANGE_VALUES:
        return getRangeValues();
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS:
        return getRangeAnalysis();
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS_COMPONENT:
        return getRangeAnalysisComponent();
      case GranaPackage.RANGE_JOB__RANGE_ANALYSES:
        return getRangeAnalyses();
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
      case GranaPackage.RANGE_JOB__MEASURE_EXECUTION_TIME:
        setMeasureExecutionTime((Boolean)newValue);
        return;
      case GranaPackage.RANGE_JOB__RANGE_OPTION:
        setRangeOption((String)newValue);
        return;
      case GranaPackage.RANGE_JOB__RANGE_VALUES:
        setRangeValues((Range)newValue);
        return;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS:
        setRangeAnalysis((Analysis)newValue);
        return;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS_COMPONENT:
        setRangeAnalysisComponent((Integer)newValue);
        return;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSES:
        getRangeAnalyses().clear();
        getRangeAnalyses().addAll((Collection<? extends Analysis>)newValue);
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
      case GranaPackage.RANGE_JOB__MEASURE_EXECUTION_TIME:
        setMeasureExecutionTime(MEASURE_EXECUTION_TIME_EDEFAULT);
        return;
      case GranaPackage.RANGE_JOB__RANGE_OPTION:
        setRangeOption(RANGE_OPTION_EDEFAULT);
        return;
      case GranaPackage.RANGE_JOB__RANGE_VALUES:
        setRangeValues((Range)null);
        return;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS:
        setRangeAnalysis((Analysis)null);
        return;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS_COMPONENT:
        setRangeAnalysisComponent(RANGE_ANALYSIS_COMPONENT_EDEFAULT);
        return;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSES:
        getRangeAnalyses().clear();
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
      case GranaPackage.RANGE_JOB__MEASURE_EXECUTION_TIME:
        return measureExecutionTime != MEASURE_EXECUTION_TIME_EDEFAULT;
      case GranaPackage.RANGE_JOB__RANGE_OPTION:
        return RANGE_OPTION_EDEFAULT == null ? rangeOption != null : !RANGE_OPTION_EDEFAULT.equals(rangeOption);
      case GranaPackage.RANGE_JOB__RANGE_VALUES:
        return rangeValues != null;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS:
        return rangeAnalysis != null;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSIS_COMPONENT:
        return rangeAnalysisComponent != RANGE_ANALYSIS_COMPONENT_EDEFAULT;
      case GranaPackage.RANGE_JOB__RANGE_ANALYSES:
        return rangeAnalyses != null && !rangeAnalyses.isEmpty();
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
    result.append(" (measureExecutionTime: ");
    result.append(measureExecutionTime);
    result.append(", rangeOption: ");
    result.append(rangeOption);
    result.append(", rangeAnalysisComponent: ");
    result.append(rangeAnalysisComponent);
    result.append(')');
    return result.toString();
  }

} //RangeJobImpl
