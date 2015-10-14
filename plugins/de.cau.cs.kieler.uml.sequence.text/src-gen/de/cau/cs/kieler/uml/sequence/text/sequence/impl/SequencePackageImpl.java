/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence.impl;

import de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent;
import de.cau.cs.kieler.uml.sequence.text.sequence.Fragment;
import de.cau.cs.kieler.uml.sequence.text.sequence.Interaction;
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeLostAndFound;
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne;
import de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage;
import de.cau.cs.kieler.uml.sequence.text.sequence.Refinement;
import de.cau.cs.kieler.uml.sequence.text.sequence.Section;
import de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage;
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
  private EClass selfMessageEClass = null;

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
  private EEnum messageTypeOneEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum messageTypeTwoEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum messageTypeLostAndFoundEEnum = null;

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
  public EAttribute getLifeline_UsecaseCaption()
  {
    return (EAttribute)lifelineEClass.getEStructuralFeatures().get(2);
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
  public EAttribute getTwoLifelineMessage_SourceStartEndExec()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_SourceStartExec()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_SourceEndExec()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_SourceEndExecCount()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetStartEndExec()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetStartExec()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetEndExec()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetEndExecCount()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_SourceNote()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTwoLifelineMessage_TargetNote()
  {
    return (EAttribute)twoLifelineMessageEClass.getEStructuralFeatures().get(13);
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
  public EAttribute getOneLifelineMessage_Message()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_StartEndExec()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_StartExec()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_EndExec()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_EndExecCount()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOneLifelineMessage_Note()
  {
    return (EAttribute)oneLifelineMessageEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSelfMessage()
  {
    return selfMessageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSelfMessage_Lifeline()
  {
    return (EReference)selfMessageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelfMessage_MessageType()
  {
    return (EAttribute)selfMessageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelfMessage_Message()
  {
    return (EAttribute)selfMessageEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelfMessage_StartEndExec()
  {
    return (EAttribute)selfMessageEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelfMessage_StartExec()
  {
    return (EAttribute)selfMessageEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelfMessage_EndExec()
  {
    return (EAttribute)selfMessageEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelfMessage_EndExecCount()
  {
    return (EAttribute)selfMessageEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSelfMessage_Note()
  {
    return (EAttribute)selfMessageEClass.getEStructuralFeatures().get(7);
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
  public EEnum getMessageTypeOne()
  {
    return messageTypeOneEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getMessageTypeTwo()
  {
    return messageTypeTwoEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getMessageTypeLostAndFound()
  {
    return messageTypeLostAndFoundEEnum;
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
    createEAttribute(lifelineEClass, LIFELINE__USECASE_CAPTION);

    interactionEClass = createEClass(INTERACTION);

    twoLifelineMessageEClass = createEClass(TWO_LIFELINE_MESSAGE);
    createEReference(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__MESSAGE_TYPE);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__MESSAGE);
    createEReference(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_LIFELINE);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_START_END_EXEC);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_START_EXEC);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC_COUNT);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_START_END_EXEC);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_START_EXEC);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_END_EXEC);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_END_EXEC_COUNT);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__SOURCE_NOTE);
    createEAttribute(twoLifelineMessageEClass, TWO_LIFELINE_MESSAGE__TARGET_NOTE);

    oneLifelineMessageEClass = createEClass(ONE_LIFELINE_MESSAGE);
    createEReference(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__LIFELINE);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__MESSAGE_TYPE);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__MESSAGE);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__START_END_EXEC);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__START_EXEC);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__END_EXEC);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__END_EXEC_COUNT);
    createEAttribute(oneLifelineMessageEClass, ONE_LIFELINE_MESSAGE__NOTE);

    selfMessageEClass = createEClass(SELF_MESSAGE);
    createEReference(selfMessageEClass, SELF_MESSAGE__LIFELINE);
    createEAttribute(selfMessageEClass, SELF_MESSAGE__MESSAGE_TYPE);
    createEAttribute(selfMessageEClass, SELF_MESSAGE__MESSAGE);
    createEAttribute(selfMessageEClass, SELF_MESSAGE__START_END_EXEC);
    createEAttribute(selfMessageEClass, SELF_MESSAGE__START_EXEC);
    createEAttribute(selfMessageEClass, SELF_MESSAGE__END_EXEC);
    createEAttribute(selfMessageEClass, SELF_MESSAGE__END_EXEC_COUNT);
    createEAttribute(selfMessageEClass, SELF_MESSAGE__NOTE);

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
    messageTypeOneEEnum = createEEnum(MESSAGE_TYPE_ONE);
    messageTypeTwoEEnum = createEEnum(MESSAGE_TYPE_TWO);
    messageTypeLostAndFoundEEnum = createEEnum(MESSAGE_TYPE_LOST_AND_FOUND);
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
    selfMessageEClass.getESuperTypes().add(this.getInteraction());
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
    initEAttribute(getLifeline_UsecaseCaption(), ecorePackage.getEString(), "usecaseCaption", null, 0, 1, Lifeline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(interactionEClass, Interaction.class, "Interaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(twoLifelineMessageEClass, TwoLifelineMessage.class, "TwoLifelineMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTwoLifelineMessage_SourceLifeline(), this.getLifeline(), null, "sourceLifeline", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_MessageType(), this.getMessageTypeTwo(), "messageType", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_Message(), ecorePackage.getEString(), "message", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTwoLifelineMessage_TargetLifeline(), this.getLifeline(), null, "targetLifeline", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceStartEndExec(), ecorePackage.getEBoolean(), "sourceStartEndExec", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceStartExec(), ecorePackage.getEBoolean(), "sourceStartExec", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceEndExec(), ecorePackage.getEBoolean(), "sourceEndExec", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceEndExecCount(), ecorePackage.getEInt(), "sourceEndExecCount", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetStartEndExec(), ecorePackage.getEBoolean(), "targetStartEndExec", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetStartExec(), ecorePackage.getEBoolean(), "targetStartExec", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetEndExec(), ecorePackage.getEBoolean(), "targetEndExec", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetEndExecCount(), ecorePackage.getEInt(), "targetEndExecCount", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_SourceNote(), ecorePackage.getEString(), "sourceNote", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTwoLifelineMessage_TargetNote(), ecorePackage.getEString(), "targetNote", null, 0, 1, TwoLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(oneLifelineMessageEClass, OneLifelineMessage.class, "OneLifelineMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOneLifelineMessage_Lifeline(), this.getLifeline(), null, "Lifeline", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_MessageType(), this.getMessageTypeOne(), "messageType", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_MessageTypeLostAndFound(), this.getMessageTypeLostAndFound(), "messageTypeLostAndFound", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_Message(), ecorePackage.getEString(), "message", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_StartEndExec(), ecorePackage.getEBoolean(), "startEndExec", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_StartExec(), ecorePackage.getEBoolean(), "startExec", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_EndExec(), ecorePackage.getEBoolean(), "endExec", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_EndExecCount(), ecorePackage.getEInt(), "endExecCount", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOneLifelineMessage_Note(), ecorePackage.getEString(), "note", null, 0, 1, OneLifelineMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(selfMessageEClass, SelfMessage.class, "SelfMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSelfMessage_Lifeline(), this.getLifeline(), null, "Lifeline", null, 0, 1, SelfMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSelfMessage_MessageType(), this.getMessageTypeOne(), "messageType", null, 0, 1, SelfMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSelfMessage_Message(), ecorePackage.getEString(), "message", null, 0, 1, SelfMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSelfMessage_StartEndExec(), ecorePackage.getEBoolean(), "startEndExec", null, 0, 1, SelfMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSelfMessage_StartExec(), ecorePackage.getEBoolean(), "startExec", null, 0, 1, SelfMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSelfMessage_EndExec(), ecorePackage.getEBoolean(), "endExec", null, 0, 1, SelfMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSelfMessage_EndExecCount(), ecorePackage.getEInt(), "endExecCount", null, 0, 1, SelfMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSelfMessage_Note(), ecorePackage.getEString(), "note", null, 0, 1, SelfMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
    initEEnum(messageTypeOneEEnum, MessageTypeOne.class, "MessageTypeOne");
    addEEnumLiteral(messageTypeOneEEnum, MessageTypeOne.ASYNC);
    addEEnumLiteral(messageTypeOneEEnum, MessageTypeOne.RESPONSE);
    addEEnumLiteral(messageTypeOneEEnum, MessageTypeOne.SYNC);

    initEEnum(messageTypeTwoEEnum, MessageTypeTwo.class, "MessageTypeTwo");
    addEEnumLiteral(messageTypeTwoEEnum, MessageTypeTwo.ASYNC);
    addEEnumLiteral(messageTypeTwoEEnum, MessageTypeTwo.RESPONSE);
    addEEnumLiteral(messageTypeTwoEEnum, MessageTypeTwo.SYNC);
    addEEnumLiteral(messageTypeTwoEEnum, MessageTypeTwo.CREATE);

    initEEnum(messageTypeLostAndFoundEEnum, MessageTypeLostAndFound.class, "MessageTypeLostAndFound");
    addEEnumLiteral(messageTypeLostAndFoundEEnum, MessageTypeLostAndFound.LOST);
    addEEnumLiteral(messageTypeLostAndFoundEEnum, MessageTypeLostAndFound.FOUND);

    // Create resource
    createResource(eNS_URI);
  }

} //SequencePackageImpl
