/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent;
import de.cau.cs.kieler.uml.sequence.text.sequence.Fragment;
import de.cau.cs.kieler.uml.sequence.text.sequence.Interaction;
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageType;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote;
import de.cau.cs.kieler.uml.sequence.text.sequence.Refinement;
import de.cau.cs.kieler.uml.sequence.text.sequence.Section;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceFactory;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;
import de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SequencePackageImpl extends EPackageImpl implements SequencePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sequenceDiagramEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass lifelineEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass interactionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass twoLifelineMessageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass oneLifelineMessageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass oneLifelineNoteEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass destroyLifelineEventEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fragmentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sectionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass refinementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum messageTypeEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private SequencePackageImpl()
  {
    super(eNS_URI, SequenceFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link SequencePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static SequencePackage init()
  {
    if (isInited) return (SequencePackage)EPackage.Registry.INSTANCE.getEPackage(SequencePackage.eNS_URI);

    // Obtain or create and register package
    SequencePackageImpl theSequencePackage = (SequencePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SequencePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SequencePackageImpl());

    isInited = true;

    // Create package meta-data objects
    theSequencePackage.createPackageContents();

    // Initialize created meta-data
    theSequencePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theSequencePackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(SequencePackage.eNS_URI, theSequencePackage);
    return theSequencePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSequenceDiagram()
  {
    return sequenceDiagramEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSequenceDiagram_DiagramName()
  {
    return (EAttribute)sequenceDiagramEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSequenceDiagram_Lifelines()
  {
    return (EReference)sequenceDiagramEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSequenceDiagram_Interactions()
  {
    return (EReference)sequenceDiagramEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLifeline()
  {
    return lifelineEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLifeline_Caption()
  {
    return (EAttribute)lifelineEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLifeline_Name()
  {
    return (EAttribute)lifelineEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInteraction()
  {
    return interactionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTwoLifelineMessage()
  {
    return twoLifelineMessageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTwoLifelineMessage_SourceLifeline()
  {
    return (EReference)twoLifelineMessageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_MessageType()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_Message()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTwoLifelineMessage_TargetLifeline()
  {
    return (EReference)twoLifelineMessageEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_SourceStartBlock()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_SourceEndBlock()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_SourceEndBlockCount()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetStartBlock()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetEndBlock()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetEndBlockCount()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_SourceNote()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetNote()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOneLifelineMessage()
  {
    return oneLifelineMessageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOneLifelineMessage_Lifeline()
  {
    return (EReference)oneLifelineMessageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_MessageType()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_MessageTypeLostAndFound()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_Caption()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_StartBlock()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_EndBlock()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_EndBlockCount()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_Note()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOneLifelineNote()
  {
    return oneLifelineNoteEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOneLifelineNote_Lifeline()
  {
    return (EReference)oneLifelineNoteEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineNote_Note()
  {
    return (EAttribute)oneLifelineNoteEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDestroyLifelineEvent()
  {
    return destroyLifelineEventEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDestroyLifelineEvent_Lifeline()
  {
    return (EReference)destroyLifelineEventEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFragment()
  {
    return fragmentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFragment_Name()
  {
    return (EAttribute)fragmentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFragment_Sections()
  {
    return (EReference)fragmentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSection()
  {
    return sectionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSection_Label()
  {
    return (EAttribute)sectionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSection_Interactions()
  {
    return (EReference)sectionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRefinement()
  {
    return refinementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRefinement_Label()
  {
    return (EAttribute)refinementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRefinement_Lifelines()
  {
    return (EReference)refinementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getMessageType()
  {
    return messageTypeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SequenceFactory getSequenceFactory()
  {
    return (SequenceFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    sequenceDiagramEClass = createEClass(SEQUENCE_DIAGRAM);
    createEAttribute(sequenceDiagramEClass, SEQUENCE_DIAGRAM__DIAGRAM_NAME);
    createEReference(sequenceDiagramEClass, SEQUENCE_DIAGRAM__LIFELINES);
    createEReference(sequenceDiagramEClass, SEQUENCE_DIAGRAM__INTERACTIONS);

    lifelineEClass = createEClass(LIFELINE);
    createEAttribute(lifelineEClass, LIFELINE__CAPTION);
    createEAttribute(lifelineEClass, LIFELINE__NAME);

    interactionEClass = createEClass(INTERACTION);

    twoLifelineMessageEClass = createEClass(TWO_LIFELINE_MESSAGE);
    createEReference(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__MESSAGE_TYPE);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__MESSAGE);
    createEReference(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_LIFELINE);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_START_BLOCK);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK_COUNT);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_START_BLOCK);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK_COUNT);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_NOTE);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_NOTE);

    oneLifelineMessageEClass = createEClass(ONE_LIFELINE_MESSAGE);
    createEReference(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__LIFELINE);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__MESSAGE_TYPE);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__CAPTION);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__START_BLOCK);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__END_BLOCK);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__END_BLOCK_COUNT);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__NOTE);

    oneLifelineNoteEClass = createEClass(ONE_LIFELINE_NOTE);
    createEReference(oneLifelineNoteEClass, ONE_LIFELINE_NOTE__LIFELINE);
    createEAttribute(oneLifelineNoteEClass, ONE_LIFELINE_NOTE__NOTE);

    destroyLifelineEventEClass = createEClass(DESTROY_LIFELINE_EVENT);
    createEReference(destroyLifelineEventEClass, DESTROY_LIFELINE_EVENT__LIFELINE);

    fragmentEClass = createEClass(FRAGMENT);
    createEAttribute(fragmentEClass, FRAGMENT__NAME);
    createEReference(fragmentEClass, FRAGMENT__SECTIONS);

    sectionEClass = createEClass(SECTION);
    createEAttribute(sectionEClass, SECTION__LABEL);
    createEReference(sectionEClass, SECTION__INTERACTIONS);

    refinementEClass = createEClass(REFINEMENT);
    createEAttribute(refinementEClass, REFINEMENT__LABEL);
    createEReference(refinementEClass, REFINEMENT__LIFELINES);

    // Create enums
    messageTypeEEnum = createEEnum(MESSAGE_TYPE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    twoLifelineMessageEClass.getESuperTypes().add(this.getInteraction());
    oneLifelineMessageEClass.getESuperTypes().add(this.getInteraction());
    oneLifelineNoteEClass.getESuperTypes().add(this.getInteraction());
    destroyLifelineEventEClass.getESuperTypes().add(this.getInteraction());
    fragmentEClass.getESuperTypes().add(this.getInteraction());
    refinementEClass.getESuperTypes().add(this.getInteraction());

    // Initialize classes and features; add operations and parameters
    initEClass(sequenceDiagramEClass, SequenceDiagram.class, "SequenceDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSequenceDiagram_DiagramName(), ecorePackage.getEString(), "diagramName", null, 0, 1, SequenceDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSequenceDiagram_Lifelines(), this.getLifeline(), null, "lifelines", null, 0, -1, SequenceDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSequenceDiagram_Interactions(), this.getInteraction(), null, "interactions", null, 0, -1, SequenceDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(lifelineEClass, Lifeline.class, "Lifeline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLifeline_Caption(), ecorePackage.getEString(), "caption", null, 0, 1, Lifeline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLifeline_Name(), ecorePackage.getEString(), "name", null, 0, 1, Lifeline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(interactionEClass, Interaction.class, "Interaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(twoLifelineMessageEClass, TwoLifelineMessage.class, "TwoLifelineMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTwoLifelineMessage_SourceLifeline(), this.getLifeline(), null, "sourceLifeline", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_MessageType(), this.getMessageType(), "messageType", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_Message(), ecorePackage.getEString(), "message", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTwoLifelineMessage_TargetLifeline(), this.getLifeline(), null, "targetLifeline", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceStartBlock(), ecorePackage.getEBoolean(), "sourceStartBlock", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceEndBlock(), ecorePackage.getEBoolean(), "sourceEndBlock", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceEndBlockCount(), ecorePackage.getEInt(), "sourceEndBlockCount", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetStartBlock(), ecorePackage.getEBoolean(), "targetStartBlock", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetEndBlock(), ecorePackage.getEBoolean(), "targetEndBlock", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetEndBlockCount(), ecorePackage.getEInt(), "targetEndBlockCount", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceNote(), ecorePackage.getEString(), "sourceNote", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetNote(), ecorePackage.getEString(), "targetNote", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(oneLifelineMessageEClass, OneLifelineMessage.class, "OneLifelineMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOneLifelineMessage_Lifeline(), this.getLifeline(), null, "Lifeline", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_MessageType(), this.getMessageType(), "messageType", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_MessageTypeLostAndFound(), ecorePackage.getEString(), "messageTypeLostAndFound", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_Caption(), ecorePackage.getEString(), "caption", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_StartBlock(), ecorePackage.getEBoolean(), "startBlock", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_EndBlock(), ecorePackage.getEBoolean(), "endBlock", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_EndBlockCount(), ecorePackage.getEInt(), "endBlockCount", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_Note(), ecorePackage.getEString(), "note", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(oneLifelineNoteEClass, OneLifelineNote.class, "OneLifelineNote", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOneLifelineNote_Lifeline(), this.getLifeline(), null, "Lifeline", null, 0, 1, OneLifelineNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineNote_Note(), ecorePackage.getEString(), "note", null, 0, 1, OneLifelineNote.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(destroyLifelineEventEClass, DestroyLifelineEvent.class, "DestroyLifelineEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDestroyLifelineEvent_Lifeline(), this.getLifeline(), null, "Lifeline", null, 0, 1, DestroyLifelineEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fragmentEClass, Fragment.class, "Fragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFragment_Name(), ecorePackage.getEString(), "name", null, 0, 1, Fragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFragment_Sections(), this.getSection(), null, "sections", null, 0, -1, Fragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sectionEClass, Section.class, "Section", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSection_Label(), ecorePackage.getEString(), "label", null, 0, 1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSection_Interactions(), this.getInteraction(), null, "interactions", null, 0, -1, Section.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(refinementEClass, Refinement.class, "Refinement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRefinement_Label(), ecorePackage.getEString(), "label", null, 0, 1, Refinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRefinement_Lifelines(), this.getLifeline(), null, "lifelines", null, 0, -1, Refinement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(messageTypeEEnum, MessageType.class, "MessageType");
    addEEnumLiteral(messageTypeEEnum, MessageType.ASYNC);
    addEEnumLiteral(messageTypeEEnum, MessageType.CREATE);
    addEEnumLiteral(messageTypeEEnum, MessageType.RESPONSE);
    addEEnumLiteral(messageTypeEEnum, MessageType.SYNC);

    // Create resource
    createResource(eNS_URI);
  }

} //SequencePackageImpl
