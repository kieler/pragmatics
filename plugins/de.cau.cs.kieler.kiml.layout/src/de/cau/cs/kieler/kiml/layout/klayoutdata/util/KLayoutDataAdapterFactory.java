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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage
 * @generated
 */
public class KLayoutDataAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static KLayoutDataPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLayoutDataAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = KLayoutDataPackage.eINSTANCE;
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
    protected KLayoutDataSwitch<Adapter> modelSwitch =
        new KLayoutDataSwitch<Adapter>() {
            @Override
            public Adapter caseKLayoutData(KLayoutData object) {
                return createKLayoutDataAdapter();
            }
            @Override
            public Adapter caseKShapeLayout(KShapeLayout object) {
                return createKShapeLayoutAdapter();
            }
            @Override
            public Adapter caseKEdgeLayout(KEdgeLayout object) {
                return createKEdgeLayoutAdapter();
            }
            @Override
            public Adapter caseKPoint(KPoint object) {
                return createKPointAdapter();
            }
            @Override
            public Adapter caseKOption(KOption object) {
                return createKOptionAdapter();
            }
            @Override
            public Adapter caseKStringOption(KStringOption object) {
                return createKStringOptionAdapter();
            }
            @Override
            public Adapter caseKIntOption(KIntOption object) {
                return createKIntOptionAdapter();
            }
            @Override
            public Adapter caseKBooleanOption(KBooleanOption object) {
                return createKBooleanOptionAdapter();
            }
            @Override
            public Adapter caseKFloatOption(KFloatOption object) {
                return createKFloatOptionAdapter();
            }
            @Override
            public Adapter caseKObjectOption(KObjectOption object) {
                return createKObjectOptionAdapter();
            }
            @Override
            public Adapter caseKInsets(KInsets object) {
                return createKInsetsAdapter();
            }
            @Override
            public Adapter caseKGraphData(KGraphData object) {
                return createKGraphDataAdapter();
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
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData <em>KLayout Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutData
     * @generated
     */
    public Adapter createKLayoutDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout <em>KShape Layout</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout
     * @generated
     */
    public Adapter createKShapeLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout <em>KEdge Layout</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout
     * @generated
     */
    public Adapter createKEdgeLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint <em>KPoint</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint
     * @generated
     */
    public Adapter createKPointAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KOption <em>KOption</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KOption
     * @generated
     */
    public Adapter createKOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption <em>KString Option</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption
     * @generated
     */
    public Adapter createKStringOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption <em>KInt Option</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KIntOption
     * @generated
     */
    public Adapter createKIntOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption <em>KBoolean Option</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KBooleanOption
     * @generated
     */
    public Adapter createKBooleanOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption <em>KFloat Option</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KFloatOption
     * @generated
     */
    public Adapter createKFloatOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption <em>KObject Option</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KObjectOption
     * @generated
     */
    public Adapter createKObjectOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets <em>KInsets</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets
     * @generated
     */
    public Adapter createKInsetsAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.cau.cs.kieler.core.kgraph.KGraphData <em>Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see de.cau.cs.kieler.core.kgraph.KGraphData
     * @generated
     */
    public Adapter createKGraphDataAdapter() {
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

} //KLayoutDataAdapterFactory
