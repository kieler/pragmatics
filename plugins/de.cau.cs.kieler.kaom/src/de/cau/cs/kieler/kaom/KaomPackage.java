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
package de.cau.cs.kieler.kaom;

import de.cau.cs.kieler.core.annotations.AnnotationsPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.cs.kieler.kaom.KaomFactory
 * @model kind="package"
 * @generated
 */
public interface KaomPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "kaom";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://kieler.cs.cau.de/KAOM";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "kaom";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    KaomPackage eINSTANCE = de.cau.cs.kieler.kaom.impl.KaomPackageImpl.init();

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.LinkableImpl <em>Linkable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.LinkableImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getLinkable()
     * @generated
     */
    int LINKABLE = 4;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.EntityImpl <em>Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.EntityImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getEntity()
     * @generated
     */
    int ENTITY = 0;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__ANNOTATIONS = AnnotationsPackage.NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__NAME = AnnotationsPackage.NAMED_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__OUTGOING_LINKS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__INCOMING_LINKS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__ID = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Child Entities</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__CHILD_ENTITIES = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Child Links</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__CHILD_LINKS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Child Ports</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__CHILD_PORTS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Child Relations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__CHILD_RELATIONS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Entity</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY_FEATURE_COUNT = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.PortImpl <em>Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.PortImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getPort()
     * @generated
     */
    int PORT = 1;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__ANNOTATIONS = AnnotationsPackage.NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__NAME = AnnotationsPackage.NAMED_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__OUTGOING_LINKS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__INCOMING_LINKS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__ID = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Port</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_FEATURE_COUNT = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.RelationImpl <em>Relation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.RelationImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getRelation()
     * @generated
     */
    int RELATION = 2;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__ANNOTATIONS = AnnotationsPackage.NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__NAME = AnnotationsPackage.NAMED_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__OUTGOING_LINKS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__INCOMING_LINKS = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__ID = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Relation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FEATURE_COUNT = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.LinkImpl <em>Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.LinkImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getLink()
     * @generated
     */
    int LINK = 3;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__ANNOTATIONS = AnnotationsPackage.NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__NAME = AnnotationsPackage.NAMED_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__SOURCE = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__TARGET = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_FEATURE_COUNT = AnnotationsPackage.NAMED_OBJECT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINKABLE__OUTGOING_LINKS = 0;

    /**
     * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINKABLE__INCOMING_LINKS = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINKABLE__ID = 2;

    /**
     * The number of structural features of the '<em>Linkable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINKABLE_FEATURE_COUNT = 3;


    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.Entity <em>Entity</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Entity</em>'.
     * @see de.cau.cs.kieler.kaom.Entity
     * @generated
     */
    EClass getEntity();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kaom.Entity#getChildEntities <em>Child Entities</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Child Entities</em>'.
     * @see de.cau.cs.kieler.kaom.Entity#getChildEntities()
     * @see #getEntity()
     * @generated
     */
    EReference getEntity_ChildEntities();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kaom.Entity#getChildLinks <em>Child Links</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Child Links</em>'.
     * @see de.cau.cs.kieler.kaom.Entity#getChildLinks()
     * @see #getEntity()
     * @generated
     */
    EReference getEntity_ChildLinks();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kaom.Entity#getChildPorts <em>Child Ports</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Child Ports</em>'.
     * @see de.cau.cs.kieler.kaom.Entity#getChildPorts()
     * @see #getEntity()
     * @generated
     */
    EReference getEntity_ChildPorts();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kaom.Entity#getChildRelations <em>Child Relations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Child Relations</em>'.
     * @see de.cau.cs.kieler.kaom.Entity#getChildRelations()
     * @see #getEntity()
     * @generated
     */
    EReference getEntity_ChildRelations();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.Port <em>Port</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Port</em>'.
     * @see de.cau.cs.kieler.kaom.Port
     * @generated
     */
    EClass getPort();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.Relation <em>Relation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Relation</em>'.
     * @see de.cau.cs.kieler.kaom.Relation
     * @generated
     */
    EClass getRelation();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.Link <em>Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Link</em>'.
     * @see de.cau.cs.kieler.kaom.Link
     * @generated
     */
    EClass getLink();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.kaom.Link#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see de.cau.cs.kieler.kaom.Link#getSource()
     * @see #getLink()
     * @generated
     */
    EReference getLink_Source();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.kaom.Link#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see de.cau.cs.kieler.kaom.Link#getTarget()
     * @see #getLink()
     * @generated
     */
    EReference getLink_Target();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.Linkable <em>Linkable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Linkable</em>'.
     * @see de.cau.cs.kieler.kaom.Linkable
     * @generated
     */
    EClass getLinkable();

    /**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.kaom.Linkable#getOutgoingLinks <em>Outgoing Links</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Outgoing Links</em>'.
     * @see de.cau.cs.kieler.kaom.Linkable#getOutgoingLinks()
     * @see #getLinkable()
     * @generated
     */
    EReference getLinkable_OutgoingLinks();

    /**
     * Returns the meta object for the reference list '{@link de.cau.cs.kieler.kaom.Linkable#getIncomingLinks <em>Incoming Links</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Incoming Links</em>'.
     * @see de.cau.cs.kieler.kaom.Linkable#getIncomingLinks()
     * @see #getLinkable()
     * @generated
     */
    EReference getLinkable_IncomingLinks();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kaom.Linkable#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see de.cau.cs.kieler.kaom.Linkable#getId()
     * @see #getLinkable()
     * @generated
     */
    EAttribute getLinkable_Id();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    KaomFactory getKaomFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.EntityImpl <em>Entity</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.EntityImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getEntity()
         * @generated
         */
        EClass ENTITY = eINSTANCE.getEntity();

        /**
         * The meta object literal for the '<em><b>Child Entities</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ENTITY__CHILD_ENTITIES = eINSTANCE.getEntity_ChildEntities();

        /**
         * The meta object literal for the '<em><b>Child Links</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ENTITY__CHILD_LINKS = eINSTANCE.getEntity_ChildLinks();

        /**
         * The meta object literal for the '<em><b>Child Ports</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ENTITY__CHILD_PORTS = eINSTANCE.getEntity_ChildPorts();

        /**
         * The meta object literal for the '<em><b>Child Relations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ENTITY__CHILD_RELATIONS = eINSTANCE.getEntity_ChildRelations();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.PortImpl <em>Port</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.PortImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getPort()
         * @generated
         */
        EClass PORT = eINSTANCE.getPort();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.RelationImpl <em>Relation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.RelationImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getRelation()
         * @generated
         */
        EClass RELATION = eINSTANCE.getRelation();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.LinkImpl <em>Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.LinkImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getLink()
         * @generated
         */
        EClass LINK = eINSTANCE.getLink();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LINK__SOURCE = eINSTANCE.getLink_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LINK__TARGET = eINSTANCE.getLink_Target();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.LinkableImpl <em>Linkable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.LinkableImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getLinkable()
         * @generated
         */
        EClass LINKABLE = eINSTANCE.getLinkable();

        /**
         * The meta object literal for the '<em><b>Outgoing Links</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LINKABLE__OUTGOING_LINKS = eINSTANCE.getLinkable_OutgoingLinks();

        /**
         * The meta object literal for the '<em><b>Incoming Links</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference LINKABLE__INCOMING_LINKS = eINSTANCE.getLinkable_IncomingLinks();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINKABLE__ID = eINSTANCE.getLinkable_Id();

    }

} //KaomPackage
