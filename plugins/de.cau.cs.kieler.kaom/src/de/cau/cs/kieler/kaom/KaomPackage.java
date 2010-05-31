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
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.AnnotatableImpl <em>Annotatable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.AnnotatableImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getAnnotatable()
     * @generated
     */
    int ANNOTATABLE = 6;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ANNOTATABLE__ANNOTATIONS = 0;

    /**
     * The number of structural features of the '<em>Annotatable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ANNOTATABLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.NamedObjectImpl <em>Named Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.NamedObjectImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getNamedObject()
     * @generated
     */
    int NAMED_OBJECT = 5;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_OBJECT__ANNOTATIONS = ANNOTATABLE__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_OBJECT__NAME = ANNOTATABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Named Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_OBJECT_FEATURE_COUNT = ANNOTATABLE_FEATURE_COUNT + 1;

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
    int ENTITY__ANNOTATIONS = NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__NAME = NAMED_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__OUTGOING_LINKS = NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__INCOMING_LINKS = NAMED_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Child Entities</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__CHILD_ENTITIES = NAMED_OBJECT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Child Links</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__CHILD_LINKS = NAMED_OBJECT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Child Ports</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__CHILD_PORTS = NAMED_OBJECT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Child Relations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY__CHILD_RELATIONS = NAMED_OBJECT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Entity</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ENTITY_FEATURE_COUNT = NAMED_OBJECT_FEATURE_COUNT + 6;

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
    int PORT__ANNOTATIONS = NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__NAME = NAMED_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__OUTGOING_LINKS = NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT__INCOMING_LINKS = NAMED_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Port</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PORT_FEATURE_COUNT = NAMED_OBJECT_FEATURE_COUNT + 2;

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
    int RELATION__ANNOTATIONS = NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__NAME = NAMED_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Outgoing Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__OUTGOING_LINKS = NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION__INCOMING_LINKS = NAMED_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Relation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RELATION_FEATURE_COUNT = NAMED_OBJECT_FEATURE_COUNT + 2;

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
    int LINK__ANNOTATIONS = NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__NAME = NAMED_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__SOURCE = NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__TARGET = NAMED_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_FEATURE_COUNT = NAMED_OBJECT_FEATURE_COUNT + 2;

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
     * The number of structural features of the '<em>Linkable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINKABLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.AnnotationImpl <em>Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.AnnotationImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getAnnotation()
     * @generated
     */
    int ANNOTATION = 7;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ANNOTATION__ANNOTATIONS = NAMED_OBJECT__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ANNOTATION__NAME = NAMED_OBJECT__NAME;

    /**
     * The number of structural features of the '<em>Annotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ANNOTATION_FEATURE_COUNT = NAMED_OBJECT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.StringAnnotationImpl <em>String Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.StringAnnotationImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getStringAnnotation()
     * @generated
     */
    int STRING_ANNOTATION = 8;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_ANNOTATION__ANNOTATIONS = ANNOTATION__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_ANNOTATION__NAME = ANNOTATION__NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_ANNOTATION__VALUE = ANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>String Annotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRING_ANNOTATION_FEATURE_COUNT = ANNOTATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.ReferenceAnnotationImpl <em>Reference Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.ReferenceAnnotationImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getReferenceAnnotation()
     * @generated
     */
    int REFERENCE_ANNOTATION = 9;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_ANNOTATION__ANNOTATIONS = ANNOTATION__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_ANNOTATION__NAME = ANNOTATION__NAME;

    /**
     * The feature id for the '<em><b>Object</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_ANNOTATION__OBJECT = ANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Reference Annotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REFERENCE_ANNOTATION_FEATURE_COUNT = ANNOTATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.BooleanAnnotationImpl <em>Boolean Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.BooleanAnnotationImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getBooleanAnnotation()
     * @generated
     */
    int BOOLEAN_ANNOTATION = 10;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_ANNOTATION__ANNOTATIONS = ANNOTATION__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_ANNOTATION__NAME = ANNOTATION__NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_ANNOTATION__VALUE = ANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Boolean Annotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOLEAN_ANNOTATION_FEATURE_COUNT = ANNOTATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.IntAnnotationImpl <em>Int Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.IntAnnotationImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getIntAnnotation()
     * @generated
     */
    int INT_ANNOTATION = 11;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_ANNOTATION__ANNOTATIONS = ANNOTATION__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_ANNOTATION__NAME = ANNOTATION__NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_ANNOTATION__VALUE = ANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Int Annotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_ANNOTATION_FEATURE_COUNT = ANNOTATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link de.cau.cs.kieler.kaom.impl.FloatAnnotationImpl <em>Float Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.kaom.impl.FloatAnnotationImpl
     * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getFloatAnnotation()
     * @generated
     */
    int FLOAT_ANNOTATION = 12;

    /**
     * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOAT_ANNOTATION__ANNOTATIONS = ANNOTATION__ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOAT_ANNOTATION__NAME = ANNOTATION__NAME;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOAT_ANNOTATION__VALUE = ANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Float Annotation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLOAT_ANNOTATION_FEATURE_COUNT = ANNOTATION_FEATURE_COUNT + 1;


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
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.NamedObject <em>Named Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Named Object</em>'.
     * @see de.cau.cs.kieler.kaom.NamedObject
     * @generated
     */
    EClass getNamedObject();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kaom.NamedObject#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see de.cau.cs.kieler.kaom.NamedObject#getName()
     * @see #getNamedObject()
     * @generated
     */
    EAttribute getNamedObject_Name();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.Annotatable <em>Annotatable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Annotatable</em>'.
     * @see de.cau.cs.kieler.kaom.Annotatable
     * @generated
     */
    EClass getAnnotatable();

    /**
     * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.kaom.Annotatable#getAnnotations <em>Annotations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Annotations</em>'.
     * @see de.cau.cs.kieler.kaom.Annotatable#getAnnotations()
     * @see #getAnnotatable()
     * @generated
     */
    EReference getAnnotatable_Annotations();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.Annotation <em>Annotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Annotation</em>'.
     * @see de.cau.cs.kieler.kaom.Annotation
     * @generated
     */
    EClass getAnnotation();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.StringAnnotation <em>String Annotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>String Annotation</em>'.
     * @see de.cau.cs.kieler.kaom.StringAnnotation
     * @generated
     */
    EClass getStringAnnotation();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kaom.StringAnnotation#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.kaom.StringAnnotation#getValue()
     * @see #getStringAnnotation()
     * @generated
     */
    EAttribute getStringAnnotation_Value();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.ReferenceAnnotation <em>Reference Annotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Reference Annotation</em>'.
     * @see de.cau.cs.kieler.kaom.ReferenceAnnotation
     * @generated
     */
    EClass getReferenceAnnotation();

    /**
     * Returns the meta object for the reference '{@link de.cau.cs.kieler.kaom.ReferenceAnnotation#getObject <em>Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Object</em>'.
     * @see de.cau.cs.kieler.kaom.ReferenceAnnotation#getObject()
     * @see #getReferenceAnnotation()
     * @generated
     */
    EReference getReferenceAnnotation_Object();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.BooleanAnnotation <em>Boolean Annotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Boolean Annotation</em>'.
     * @see de.cau.cs.kieler.kaom.BooleanAnnotation
     * @generated
     */
    EClass getBooleanAnnotation();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kaom.BooleanAnnotation#isValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.kaom.BooleanAnnotation#isValue()
     * @see #getBooleanAnnotation()
     * @generated
     */
    EAttribute getBooleanAnnotation_Value();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.IntAnnotation <em>Int Annotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Int Annotation</em>'.
     * @see de.cau.cs.kieler.kaom.IntAnnotation
     * @generated
     */
    EClass getIntAnnotation();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kaom.IntAnnotation#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.kaom.IntAnnotation#getValue()
     * @see #getIntAnnotation()
     * @generated
     */
    EAttribute getIntAnnotation_Value();

    /**
     * Returns the meta object for class '{@link de.cau.cs.kieler.kaom.FloatAnnotation <em>Float Annotation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Float Annotation</em>'.
     * @see de.cau.cs.kieler.kaom.FloatAnnotation
     * @generated
     */
    EClass getFloatAnnotation();

    /**
     * Returns the meta object for the attribute '{@link de.cau.cs.kieler.kaom.FloatAnnotation#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see de.cau.cs.kieler.kaom.FloatAnnotation#getValue()
     * @see #getFloatAnnotation()
     * @generated
     */
    EAttribute getFloatAnnotation_Value();

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
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.NamedObjectImpl <em>Named Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.NamedObjectImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getNamedObject()
         * @generated
         */
        EClass NAMED_OBJECT = eINSTANCE.getNamedObject();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NAMED_OBJECT__NAME = eINSTANCE.getNamedObject_Name();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.AnnotatableImpl <em>Annotatable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.AnnotatableImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getAnnotatable()
         * @generated
         */
        EClass ANNOTATABLE = eINSTANCE.getAnnotatable();

        /**
         * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ANNOTATABLE__ANNOTATIONS = eINSTANCE.getAnnotatable_Annotations();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.AnnotationImpl <em>Annotation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.AnnotationImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getAnnotation()
         * @generated
         */
        EClass ANNOTATION = eINSTANCE.getAnnotation();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.StringAnnotationImpl <em>String Annotation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.StringAnnotationImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getStringAnnotation()
         * @generated
         */
        EClass STRING_ANNOTATION = eINSTANCE.getStringAnnotation();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute STRING_ANNOTATION__VALUE = eINSTANCE.getStringAnnotation_Value();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.ReferenceAnnotationImpl <em>Reference Annotation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.ReferenceAnnotationImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getReferenceAnnotation()
         * @generated
         */
        EClass REFERENCE_ANNOTATION = eINSTANCE.getReferenceAnnotation();

        /**
         * The meta object literal for the '<em><b>Object</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference REFERENCE_ANNOTATION__OBJECT = eINSTANCE.getReferenceAnnotation_Object();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.BooleanAnnotationImpl <em>Boolean Annotation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.BooleanAnnotationImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getBooleanAnnotation()
         * @generated
         */
        EClass BOOLEAN_ANNOTATION = eINSTANCE.getBooleanAnnotation();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOOLEAN_ANNOTATION__VALUE = eINSTANCE.getBooleanAnnotation_Value();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.IntAnnotationImpl <em>Int Annotation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.IntAnnotationImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getIntAnnotation()
         * @generated
         */
        EClass INT_ANNOTATION = eINSTANCE.getIntAnnotation();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute INT_ANNOTATION__VALUE = eINSTANCE.getIntAnnotation_Value();

        /**
         * The meta object literal for the '{@link de.cau.cs.kieler.kaom.impl.FloatAnnotationImpl <em>Float Annotation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see de.cau.cs.kieler.kaom.impl.FloatAnnotationImpl
         * @see de.cau.cs.kieler.kaom.impl.KaomPackageImpl#getFloatAnnotation()
         * @generated
         */
        EClass FLOAT_ANNOTATION = eINSTANCE.getFloatAnnotation();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FLOAT_ANNOTATION__VALUE = eINSTANCE.getFloatAnnotation_Value();

    }

} //KaomPackage
