/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.util;

import de.cau.cs.kieler.kwebs.servicedata.*;

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
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage
 * @generated
 */
public class ServiceDataSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ServiceDataPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceDataSwitch() {
        if (modelPackage == null) {
            modelPackage = ServiceDataPackage.eINSTANCE;
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
    protected boolean isSwitchFor(EPackage ePackage) {
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
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case ServiceDataPackage.SERVICE_DATA: {
                ServiceData serviceData = (ServiceData)theEObject;
                T result = caseServiceData(serviceData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ServiceDataPackage.LAYOUT_ALGORITHM: {
                LayoutAlgorithm layoutAlgorithm = (LayoutAlgorithm)theEObject;
                T result = caseLayoutAlgorithm(layoutAlgorithm);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ServiceDataPackage.LAYOUT_TYPE: {
                LayoutType layoutType = (LayoutType)theEObject;
                T result = caseLayoutType(layoutType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ServiceDataPackage.LAYOUT_OPTION: {
                LayoutOption layoutOption = (LayoutOption)theEObject;
                T result = caseLayoutOption(layoutOption);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ServiceDataPackage.CATEGORY: {
                Category category = (Category)theEObject;
                T result = caseCategory(category);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ServiceDataPackage.KNOWN_OPTION: {
                KnownOption knownOption = (KnownOption)theEObject;
                T result = caseKnownOption(knownOption);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ServiceDataPackage.SUPPORTED_DIAGRAM: {
                SupportedDiagram supportedDiagram = (SupportedDiagram)theEObject;
                T result = caseSupportedDiagram(supportedDiagram);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ServiceDataPackage.REMOTE_ENUM: {
                RemoteEnum remoteEnum = (RemoteEnum)theEObject;
                T result = caseRemoteEnum(remoteEnum);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Service Data</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Service Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseServiceData(ServiceData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Layout Algorithm</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout Algorithm</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayoutAlgorithm(LayoutAlgorithm object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Layout Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayoutType(LayoutType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Layout Option</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayoutOption(LayoutOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Category</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Category</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCategory(Category object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Known Option</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Known Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseKnownOption(KnownOption object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Supported Diagram</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Supported Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSupportedDiagram(SupportedDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Remote Enum</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Remote Enum</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRemoteEnum(RemoteEnum object) {
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
    public T defaultCase(EObject object) {
        return null;
    }

} //ServiceDataSwitch
