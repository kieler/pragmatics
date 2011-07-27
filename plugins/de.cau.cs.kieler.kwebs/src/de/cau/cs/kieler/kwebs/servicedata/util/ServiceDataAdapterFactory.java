/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.cs.kieler.kwebs.servicedata.util;

import de.cau.cs.kieler.kwebs.servicedata.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kwebs.servicedata.ServiceDataPackage
 * @generated
 */
public class ServiceDataAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ServiceDataPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServiceDataAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ServiceDataPackage.eINSTANCE;
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
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
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
    protected ServiceDataSwitch<Adapter> modelSwitch =
        new ServiceDataSwitch<Adapter>() {
            @Override
            public Adapter caseServiceData(ServiceData object) {
                return createServiceDataAdapter();
            }
            @Override
            public Adapter caseLayoutAlgorithm(LayoutAlgorithm object) {
                return createLayoutAlgorithmAdapter();
            }
            @Override
            public Adapter caseLayoutType(LayoutType object) {
                return createLayoutTypeAdapter();
            }
            @Override
            public Adapter caseLayoutOption(LayoutOption object) {
                return createLayoutOptionAdapter();
            }
            @Override
            public Adapter caseCategory(Category object) {
                return createCategoryAdapter();
            }
            @Override
            public Adapter caseKnownOption(KnownOption object) {
                return createKnownOptionAdapter();
            }
            @Override
            public Adapter caseSupportedDiagram(SupportedDiagram object) {
                return createSupportedDiagramAdapter();
            }
            @Override
            public Adapter caseRemoteEnum(RemoteEnum object) {
                return createRemoteEnumAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
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
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kwebs.servicedata.ServiceData <em>Service Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kwebs.servicedata.ServiceData
     * @generated
     */
    public Adapter createServiceDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm <em>Layout Algorithm</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm
     * @generated
     */
    public Adapter createLayoutAlgorithmAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutType <em>Layout Type</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutType
     * @generated
     */
    public Adapter createLayoutTypeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kwebs.servicedata.LayoutOption <em>Layout Option</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kwebs.servicedata.LayoutOption
     * @generated
     */
    public Adapter createLayoutOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kwebs.servicedata.Category <em>Category</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kwebs.servicedata.Category
     * @generated
     */
    public Adapter createCategoryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kwebs.servicedata.KnownOption <em>Known Option</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kwebs.servicedata.KnownOption
     * @generated
     */
    public Adapter createKnownOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram <em>Supported Diagram</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram
     * @generated
     */
    public Adapter createSupportedDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kwebs.servicedata.RemoteEnum <em>Remote Enum</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kwebs.servicedata.RemoteEnum
     * @generated
     */
    public Adapter createRemoteEnumAdapter() {
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
    public Adapter createEObjectAdapter() {
        return null;
    }

} //ServiceDataAdapterFactory
