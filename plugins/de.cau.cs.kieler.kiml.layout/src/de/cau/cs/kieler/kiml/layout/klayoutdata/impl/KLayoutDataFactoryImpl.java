/**
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
package de.cau.cs.kieler.kiml.layout.klayoutdata.impl;

import de.cau.cs.kieler.kiml.layout.klayoutdata.*;

import org.eclipse.emf.ecore.EClass;
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
public class KLayoutDataFactoryImpl extends EFactoryImpl implements KLayoutDataFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static KLayoutDataFactory init() {
        try {
            KLayoutDataFactory theKLayoutDataFactory = (KLayoutDataFactory)EPackage.Registry.INSTANCE.getEFactory("http://rtsys.informatik.uni-kiel.de/trac/kieler/wiki/KLayoutData"); 
            if (theKLayoutDataFactory != null) {
                return theKLayoutDataFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new KLayoutDataFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLayoutDataFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case KLayoutDataPackage.KSHAPE_LAYOUT: return createKShapeLayout();
            case KLayoutDataPackage.KEDGE_LAYOUT: return createKEdgeLayout();
            case KLayoutDataPackage.KPOINT: return createKPoint();
            case KLayoutDataPackage.KOPTION: return createKOption();
            case KLayoutDataPackage.KSTRING_OPTION: return createKStringOption();
            case KLayoutDataPackage.KINT_OPTION: return createKIntOption();
            case KLayoutDataPackage.KFLOAT_OPTION: return createKFloatOption();
            case KLayoutDataPackage.KOBJECT_OPTION: return createKObjectOption();
            case KLayoutDataPackage.KINSETS: return createKInsets();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KShapeLayout createKShapeLayout() {
        KShapeLayoutImpl kShapeLayout = new KShapeLayoutImpl();
        return kShapeLayout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KEdgeLayout createKEdgeLayout() {
        KEdgeLayoutImpl kEdgeLayout = new KEdgeLayoutImpl();
        return kEdgeLayout;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KPoint createKPoint() {
        KPointImpl kPoint = new KPointImpl();
        return kPoint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KOption createKOption() {
        KOptionImpl kOption = new KOptionImpl();
        return kOption;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KStringOption createKStringOption() {
        KStringOptionImpl kStringOption = new KStringOptionImpl();
        return kStringOption;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KIntOption createKIntOption() {
        KIntOptionImpl kIntOption = new KIntOptionImpl();
        return kIntOption;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KFloatOption createKFloatOption() {
        KFloatOptionImpl kFloatOption = new KFloatOptionImpl();
        return kFloatOption;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KObjectOption createKObjectOption() {
        KObjectOptionImpl kObjectOption = new KObjectOptionImpl();
        return kObjectOption;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KInsets createKInsets() {
        KInsetsImpl kInsets = new KInsetsImpl();
        return kInsets;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KLayoutDataPackage getKLayoutDataPackage() {
        return (KLayoutDataPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static KLayoutDataPackage getPackage() {
        return KLayoutDataPackage.eINSTANCE;
    }

} //KLayoutDataFactoryImpl
