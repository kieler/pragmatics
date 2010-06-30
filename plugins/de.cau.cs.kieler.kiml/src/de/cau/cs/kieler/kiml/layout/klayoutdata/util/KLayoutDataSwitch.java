/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 *
 * $Id$
 */
package de.cau.cs.kieler.kiml.layout.klayoutdata.util;

import de.cau.cs.kieler.core.kgraph.KGraphData;

import de.cau.cs.kieler.kiml.layout.klayoutdata.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage
 * @generated
 */
public class KLayoutDataSwitch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static KLayoutDataPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLayoutDataSwitch() {
        if (modelPackage == null) {
            modelPackage = KLayoutDataPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case KLayoutDataPackage.KLAYOUT_DATA: {
                KLayoutData kLayoutData = (KLayoutData)theEObject;
                T result = caseKLayoutData(kLayoutData);
                if (result == null) result = caseKGraphData(kLayoutData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KSHAPE_LAYOUT: {
                KShapeLayout kShapeLayout = (KShapeLayout)theEObject;
                T result = caseKShapeLayout(kShapeLayout);
                if (result == null) result = caseKLayoutData(kShapeLayout);
                if (result == null) result = caseKGraphData(kShapeLayout);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KEDGE_LAYOUT: {
                KEdgeLayout kEdgeLayout = (KEdgeLayout)theEObject;
                T result = caseKEdgeLayout(kEdgeLayout);
                if (result == null) result = caseKLayoutData(kEdgeLayout);
                if (result == null) result = caseKGraphData(kEdgeLayout);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KPOINT: {
                KPoint kPoint = (KPoint)theEObject;
                T result = caseKPoint(kPoint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KOPTION: {
                KOption kOption = (KOption)theEObject;
                T result = caseKOption(kOption);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KSTRING_OPTION: {
                KStringOption kStringOption = (KStringOption)theEObject;
                T result = caseKStringOption(kStringOption);
                if (result == null) result = caseKOption(kStringOption);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KINT_OPTION: {
                KIntOption kIntOption = (KIntOption)theEObject;
                T result = caseKIntOption(kIntOption);
                if (result == null) result = caseKOption(kIntOption);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KBOOLEAN_OPTION: {
                KBooleanOption kBooleanOption = (KBooleanOption)theEObject;
                T result = caseKBooleanOption(kBooleanOption);
                if (result == null) result = caseKOption(kBooleanOption);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KFLOAT_OPTION: {
                KFloatOption kFloatOption = (KFloatOption)theEObject;
                T result = caseKFloatOption(kFloatOption);
                if (result == null) result = caseKOption(kFloatOption);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KOBJECT_OPTION: {
                KObjectOption kObjectOption = (KObjectOption)theEObject;
                T result = caseKObjectOption(kObjectOption);
                if (result == null) result = caseKOption(kObjectOption);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KLayoutDataPackage.KINSETS: {
                KInsets kInsets = (KInsets)theEObject;
                T result = caseKInsets(kInsets);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KLayout Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KLayout Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKLayoutData(KLayoutData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KShape Layout</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KShape Layout</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKShapeLayout(KShapeLayout object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KEdge Layout</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KEdge Layout</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKEdgeLayout(KEdgeLayout object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KPoint</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPoint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKPoint(KPoint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KOption</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KOption</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKOption(KOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KString Option</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KString Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKStringOption(KStringOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KInt Option</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KInt Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKIntOption(KIntOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KBoolean Option</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KBoolean Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKBooleanOption(KBooleanOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KFloat Option</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KFloat Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKFloatOption(KFloatOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KObject Option</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KObject Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKObjectOption(KObjectOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>KInsets</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KInsets</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKInsets(KInsets object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKGraphData(KGraphData object) {
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
    public T defaultCase(EObject object) {
        return null;
    }

} //KLayoutDataSwitch
