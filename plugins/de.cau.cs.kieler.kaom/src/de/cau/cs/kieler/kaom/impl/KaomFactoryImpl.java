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
package de.cau.cs.kieler.kaom.impl;

import de.cau.cs.kieler.kaom.*;

import java.util.Map;

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
public class KaomFactoryImpl extends EFactoryImpl implements KaomFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static KaomFactory init() {
        try {
            KaomFactory theKaomFactory = (KaomFactory)EPackage.Registry.INSTANCE.getEFactory("http://kieler.cs.cau.de/KAOM"); 
            if (theKaomFactory != null) {
                return theKaomFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new KaomFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KaomFactoryImpl() {
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
            case KaomPackage.PORT: return createPort();
            case KaomPackage.RELATION: return createRelation();
            case KaomPackage.LINK: return createLink();
            case KaomPackage.ACTOR: return createActor();
            case KaomPackage.STATE: return createState();
            case KaomPackage.ANNOTATION_MAP_ENTRY: return (EObject)createAnnotationMapEntry();
            case KaomPackage.STRING_ANNOTATION: return createStringAnnotation();
            case KaomPackage.REFERENCE_ANNOTATION: return createReferenceAnnotation();
            case KaomPackage.BOOLEAN_ANNOTATION: return createBooleanAnnotation();
            case KaomPackage.INT_ANNOTATION: return createIntAnnotation();
            case KaomPackage.FLOAT_ANNOTATION: return createFloatAnnotation();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Port createPort() {
        PortImpl port = new PortImpl();
        return port;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Relation createRelation() {
        RelationImpl relation = new RelationImpl();
        return relation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Link createLink() {
        LinkImpl link = new LinkImpl();
        return link;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Actor createActor() {
        ActorImpl actor = new ActorImpl();
        return actor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public State createState() {
        StateImpl state = new StateImpl();
        return state;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<String, Annotation> createAnnotationMapEntry() {
        AnnotationMapEntryImpl annotationMapEntry = new AnnotationMapEntryImpl();
        return annotationMapEntry;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StringAnnotation createStringAnnotation() {
        StringAnnotationImpl stringAnnotation = new StringAnnotationImpl();
        return stringAnnotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReferenceAnnotation createReferenceAnnotation() {
        ReferenceAnnotationImpl referenceAnnotation = new ReferenceAnnotationImpl();
        return referenceAnnotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BooleanAnnotation createBooleanAnnotation() {
        BooleanAnnotationImpl booleanAnnotation = new BooleanAnnotationImpl();
        return booleanAnnotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntAnnotation createIntAnnotation() {
        IntAnnotationImpl intAnnotation = new IntAnnotationImpl();
        return intAnnotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FloatAnnotation createFloatAnnotation() {
        FloatAnnotationImpl floatAnnotation = new FloatAnnotationImpl();
        return floatAnnotation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KaomPackage getKaomPackage() {
        return (KaomPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static KaomPackage getPackage() {
        return KaomPackage.eINSTANCE;
    }

} //KaomFactoryImpl
