/**
 */
package de.cau.cs.kieler.uml.sequence.text.sequence;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequenceFactory
 * @model kind="package"
 * @generated
 */
public interface SequencePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "sequence";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.cau.de/cs/kieler/uml/sequence/text/Sequence";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "sequence";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SequencePackage eINSTANCE = de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl.init();

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceDiagramImpl <em>Diagram</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceDiagramImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getSequenceDiagram()
   * @generated
   */
  int SEQUENCE_DIAGRAM = 0;

  /**
   * The feature id for the '<em><b>Diagram Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DIAGRAM__DIAGRAM_NAME = 0;

  /**
   * The feature id for the '<em><b>Lifelines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DIAGRAM__LIFELINES = 1;

  /**
   * The feature id for the '<em><b>Interactions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DIAGRAM__INTERACTIONS = 2;

  /**
   * The number of structural features of the '<em>Diagram</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DIAGRAM_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.LifelineImpl <em>Lifeline</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.LifelineImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getLifeline()
   * @generated
   */
  int LIFELINE = 1;

  /**
   * The feature id for the '<em><b>Caption</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFELINE__CAPTION = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFELINE__NAME = 1;

  /**
   * The feature id for the '<em><b>Usecase Caption</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFELINE__USECASE_CAPTION = 2;

  /**
   * The number of structural features of the '<em>Lifeline</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFELINE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.InteractionImpl <em>Interaction</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.InteractionImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getInteraction()
   * @generated
   */
  int INTERACTION = 2;

  /**
   * The number of structural features of the '<em>Interaction</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTERACTION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl <em>Two Lifeline Message</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getTwoLifelineMessage()
   * @generated
   */
  int TWO_LIFELINE_MESSAGE = 3;

  /**
   * The feature id for the '<em><b>Source Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Message Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__MESSAGE_TYPE = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__MESSAGE = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Target Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_LIFELINE = INTERACTION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Source Start End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_START_END_EXEC = INTERACTION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Source Start Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_START_EXEC = INTERACTION_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Source End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC = INTERACTION_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Source End Exec Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC_COUNT = INTERACTION_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Target Start End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_START_END_EXEC = INTERACTION_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Target Start Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_START_EXEC = INTERACTION_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>Target End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_END_EXEC = INTERACTION_FEATURE_COUNT + 10;

  /**
   * The feature id for the '<em><b>Target End Exec Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_END_EXEC_COUNT = INTERACTION_FEATURE_COUNT + 11;

  /**
   * The feature id for the '<em><b>Source Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_NOTE = INTERACTION_FEATURE_COUNT + 12;

  /**
   * The feature id for the '<em><b>Target Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_NOTE = INTERACTION_FEATURE_COUNT + 13;

  /**
   * The number of structural features of the '<em>Two Lifeline Message</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 14;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl <em>One Lifeline Message</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getOneLifelineMessage()
   * @generated
   */
  int ONE_LIFELINE_MESSAGE = 4;

  /**
   * The feature id for the '<em><b>Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__LIFELINE = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Message Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__MESSAGE_TYPE = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Message Type Lost And Found</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__MESSAGE = INTERACTION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Start End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__START_END_EXEC = INTERACTION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Start Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__START_EXEC = INTERACTION_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__END_EXEC = INTERACTION_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>End Exec Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__END_EXEC_COUNT = INTERACTION_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__NOTE = INTERACTION_FEATURE_COUNT + 8;

  /**
   * The number of structural features of the '<em>One Lifeline Message</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 9;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl <em>Self Message</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getSelfMessage()
   * @generated
   */
  int SELF_MESSAGE = 5;

  /**
   * The feature id for the '<em><b>Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE__LIFELINE = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Message Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE__MESSAGE_TYPE = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE__MESSAGE = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Start End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE__START_END_EXEC = INTERACTION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Start Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE__START_EXEC = INTERACTION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>End Exec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE__END_EXEC = INTERACTION_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>End Exec Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE__END_EXEC_COUNT = INTERACTION_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE__NOTE = INTERACTION_FEATURE_COUNT + 7;

  /**
   * The number of structural features of the '<em>Self Message</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELF_MESSAGE_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 8;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyLifelineEventImpl <em>Destroy Lifeline Event</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyLifelineEventImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getDestroyLifelineEvent()
   * @generated
   */
  int DESTROY_LIFELINE_EVENT = 6;

  /**
   * The feature id for the '<em><b>Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESTROY_LIFELINE_EVENT__LIFELINE = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Destroy Lifeline Event</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESTROY_LIFELINE_EVENT_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentImpl <em>Fragment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getFragment()
   * @generated
   */
  int FRAGMENT = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FRAGMENT__NAME = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Sections</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FRAGMENT__SECTIONS = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Fragment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FRAGMENT_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SectionImpl <em>Section</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SectionImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getSection()
   * @generated
   */
  int SECTION = 8;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SECTION__LABEL = 0;

  /**
   * The feature id for the '<em><b>Interactions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SECTION__INTERACTIONS = 1;

  /**
   * The number of structural features of the '<em>Section</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SECTION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.RefinementImpl <em>Refinement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.RefinementImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getRefinement()
   * @generated
   */
  int REFINEMENT = 9;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFINEMENT__LABEL = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Lifelines</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFINEMENT__LIFELINES = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Refinement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFINEMENT_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne <em>Message Type One</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getMessageTypeOne()
   * @generated
   */
  int MESSAGE_TYPE_ONE = 10;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo <em>Message Type Two</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getMessageTypeTwo()
   * @generated
   */
  int MESSAGE_TYPE_TWO = 11;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeLostAndFound <em>Message Type Lost And Found</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeLostAndFound
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getMessageTypeLostAndFound()
   * @generated
   */
  int MESSAGE_TYPE_LOST_AND_FOUND = 12;


  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram <em>Diagram</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Diagram</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram
   * @generated
   */
  EClass getSequenceDiagram();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getDiagramName <em>Diagram Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Diagram Name</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getDiagramName()
   * @see #getSequenceDiagram()
   * @generated
   */
  EAttribute getSequenceDiagram_DiagramName();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getLifelines <em>Lifelines</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Lifelines</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getLifelines()
   * @see #getSequenceDiagram()
   * @generated
   */
  EReference getSequenceDiagram_Lifelines();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getInteractions <em>Interactions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Interactions</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getInteractions()
   * @see #getSequenceDiagram()
   * @generated
   */
  EReference getSequenceDiagram_Interactions();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline <em>Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline
   * @generated
   */
  EClass getLifeline();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getCaption <em>Caption</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Caption</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getCaption()
   * @see #getLifeline()
   * @generated
   */
  EAttribute getLifeline_Caption();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getName()
   * @see #getLifeline()
   * @generated
   */
  EAttribute getLifeline_Name();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getUsecaseCaption <em>Usecase Caption</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Usecase Caption</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline#getUsecaseCaption()
   * @see #getLifeline()
   * @generated
   */
  EAttribute getLifeline_UsecaseCaption();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Interaction <em>Interaction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Interaction</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Interaction
   * @generated
   */
  EClass getInteraction();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage <em>Two Lifeline Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Two Lifeline Message</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage
   * @generated
   */
  EClass getTwoLifelineMessage();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceLifeline <em>Source Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceLifeline()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EReference getTwoLifelineMessage_SourceLifeline();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessageType <em>Message Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessageType()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_MessageType();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessage <em>Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getMessage()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_Message();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetLifeline <em>Target Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetLifeline()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EReference getTwoLifelineMessage_TargetLifeline();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartEndExec <em>Source Start End Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source Start End Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartEndExec()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_SourceStartEndExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartExec <em>Source Start Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source Start Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartExec()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_SourceStartExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceEndExec <em>Source End Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source End Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceEndExec()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_SourceEndExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceEndExecCount <em>Source End Exec Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source End Exec Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceEndExecCount()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_SourceEndExecCount();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartEndExec <em>Target Start End Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Start End Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartEndExec()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TargetStartEndExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartExec <em>Target Start Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Start Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartExec()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TargetStartExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetEndExec <em>Target End Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target End Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetEndExec()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TargetEndExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetEndExecCount <em>Target End Exec Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target End Exec Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetEndExecCount()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TargetEndExecCount();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceNote <em>Source Note</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source Note</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceNote()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_SourceNote();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetNote <em>Target Note</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Note</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetNote()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TargetNote();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage <em>One Lifeline Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>One Lifeline Message</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage
   * @generated
   */
  EClass getOneLifelineMessage();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getLifeline <em>Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getLifeline()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EReference getOneLifelineMessage_Lifeline();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageType <em>Message Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageType()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_MessageType();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageTypeLostAndFound <em>Message Type Lost And Found</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message Type Lost And Found</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessageTypeLostAndFound()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_MessageTypeLostAndFound();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessage <em>Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getMessage()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_Message();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartEndExec <em>Start End Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start End Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartEndExec()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_StartEndExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartExec <em>Start Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartExec()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_StartExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isEndExec <em>End Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isEndExec()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_EndExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getEndExecCount <em>End Exec Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Exec Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getEndExecCount()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_EndExecCount();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getNote <em>Note</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Note</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getNote()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_Note();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage <em>Self Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Self Message</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage
   * @generated
   */
  EClass getSelfMessage();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getLifeline <em>Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getLifeline()
   * @see #getSelfMessage()
   * @generated
   */
  EReference getSelfMessage_Lifeline();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getMessageType <em>Message Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getMessageType()
   * @see #getSelfMessage()
   * @generated
   */
  EAttribute getSelfMessage_MessageType();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getMessage <em>Message</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Message</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getMessage()
   * @see #getSelfMessage()
   * @generated
   */
  EAttribute getSelfMessage_Message();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#isStartEndExec <em>Start End Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start End Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#isStartEndExec()
   * @see #getSelfMessage()
   * @generated
   */
  EAttribute getSelfMessage_StartEndExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#isStartExec <em>Start Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#isStartExec()
   * @see #getSelfMessage()
   * @generated
   */
  EAttribute getSelfMessage_StartExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#isEndExec <em>End Exec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Exec</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#isEndExec()
   * @see #getSelfMessage()
   * @generated
   */
  EAttribute getSelfMessage_EndExec();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getEndExecCount <em>End Exec Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Exec Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getEndExecCount()
   * @see #getSelfMessage()
   * @generated
   */
  EAttribute getSelfMessage_EndExecCount();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getNote <em>Note</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Note</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SelfMessage#getNote()
   * @see #getSelfMessage()
   * @generated
   */
  EAttribute getSelfMessage_Note();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent <em>Destroy Lifeline Event</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Destroy Lifeline Event</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent
   * @generated
   */
  EClass getDestroyLifelineEvent();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent#getLifeline <em>Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.DestroyLifelineEvent#getLifeline()
   * @see #getDestroyLifelineEvent()
   * @generated
   */
  EReference getDestroyLifelineEvent_Lifeline();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Fragment <em>Fragment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fragment</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Fragment
   * @generated
   */
  EClass getFragment();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getName()
   * @see #getFragment()
   * @generated
   */
  EAttribute getFragment_Name();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getSections <em>Sections</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sections</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getSections()
   * @see #getFragment()
   * @generated
   */
  EReference getFragment_Sections();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Section <em>Section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Section</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Section
   * @generated
   */
  EClass getSection();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Section#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Section#getLabel()
   * @see #getSection()
   * @generated
   */
  EAttribute getSection_Label();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Section#getInteractions <em>Interactions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Interactions</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Section#getInteractions()
   * @see #getSection()
   * @generated
   */
  EReference getSection_Interactions();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Refinement <em>Refinement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Refinement</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Refinement
   * @generated
   */
  EClass getRefinement();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Refinement#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Refinement#getLabel()
   * @see #getRefinement()
   * @generated
   */
  EAttribute getRefinement_Label();

  /**
   * Returns the meta object for the reference list '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Refinement#getLifelines <em>Lifelines</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Lifelines</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Refinement#getLifelines()
   * @see #getRefinement()
   * @generated
   */
  EReference getRefinement_Lifelines();

  /**
   * Returns the meta object for enum '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne <em>Message Type One</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Message Type One</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne
   * @generated
   */
  EEnum getMessageTypeOne();

  /**
   * Returns the meta object for enum '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo <em>Message Type Two</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Message Type Two</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo
   * @generated
   */
  EEnum getMessageTypeTwo();

  /**
   * Returns the meta object for enum '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeLostAndFound <em>Message Type Lost And Found</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Message Type Lost And Found</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeLostAndFound
   * @generated
   */
  EEnum getMessageTypeLostAndFound();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SequenceFactory getSequenceFactory();

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
  interface Literals
  {
    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceDiagramImpl <em>Diagram</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequenceDiagramImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getSequenceDiagram()
     * @generated
     */
    EClass SEQUENCE_DIAGRAM = eINSTANCE.getSequenceDiagram();

    /**
     * The meta object literal for the '<em><b>Diagram Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SEQUENCE_DIAGRAM__DIAGRAM_NAME = eINSTANCE.getSequenceDiagram_DiagramName();

    /**
     * The meta object literal for the '<em><b>Lifelines</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SEQUENCE_DIAGRAM__LIFELINES = eINSTANCE.getSequenceDiagram_Lifelines();

    /**
     * The meta object literal for the '<em><b>Interactions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SEQUENCE_DIAGRAM__INTERACTIONS = eINSTANCE.getSequenceDiagram_Interactions();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.LifelineImpl <em>Lifeline</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.LifelineImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getLifeline()
     * @generated
     */
    EClass LIFELINE = eINSTANCE.getLifeline();

    /**
     * The meta object literal for the '<em><b>Caption</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIFELINE__CAPTION = eINSTANCE.getLifeline_Caption();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIFELINE__NAME = eINSTANCE.getLifeline_Name();

    /**
     * The meta object literal for the '<em><b>Usecase Caption</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIFELINE__USECASE_CAPTION = eINSTANCE.getLifeline_UsecaseCaption();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.InteractionImpl <em>Interaction</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.InteractionImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getInteraction()
     * @generated
     */
    EClass INTERACTION = eINSTANCE.getInteraction();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl <em>Two Lifeline Message</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.TwoLifelineMessageImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getTwoLifelineMessage()
     * @generated
     */
    EClass TWO_LIFELINE_MESSAGE = eINSTANCE.getTwoLifelineMessage();

    /**
     * The meta object literal for the '<em><b>Source Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TWO_LIFELINE_MESSAGE__SOURCE_LIFELINE = eINSTANCE.getTwoLifelineMessage_SourceLifeline();

    /**
     * The meta object literal for the '<em><b>Message Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__MESSAGE_TYPE = eINSTANCE.getTwoLifelineMessage_MessageType();

    /**
     * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__MESSAGE = eINSTANCE.getTwoLifelineMessage_Message();

    /**
     * The meta object literal for the '<em><b>Target Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TWO_LIFELINE_MESSAGE__TARGET_LIFELINE = eINSTANCE.getTwoLifelineMessage_TargetLifeline();

    /**
     * The meta object literal for the '<em><b>Source Start End Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__SOURCE_START_END_EXEC = eINSTANCE.getTwoLifelineMessage_SourceStartEndExec();

    /**
     * The meta object literal for the '<em><b>Source Start Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__SOURCE_START_EXEC = eINSTANCE.getTwoLifelineMessage_SourceStartExec();

    /**
     * The meta object literal for the '<em><b>Source End Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC = eINSTANCE.getTwoLifelineMessage_SourceEndExec();

    /**
     * The meta object literal for the '<em><b>Source End Exec Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__SOURCE_END_EXEC_COUNT = eINSTANCE.getTwoLifelineMessage_SourceEndExecCount();

    /**
     * The meta object literal for the '<em><b>Target Start End Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TARGET_START_END_EXEC = eINSTANCE.getTwoLifelineMessage_TargetStartEndExec();

    /**
     * The meta object literal for the '<em><b>Target Start Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TARGET_START_EXEC = eINSTANCE.getTwoLifelineMessage_TargetStartExec();

    /**
     * The meta object literal for the '<em><b>Target End Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TARGET_END_EXEC = eINSTANCE.getTwoLifelineMessage_TargetEndExec();

    /**
     * The meta object literal for the '<em><b>Target End Exec Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TARGET_END_EXEC_COUNT = eINSTANCE.getTwoLifelineMessage_TargetEndExecCount();

    /**
     * The meta object literal for the '<em><b>Source Note</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__SOURCE_NOTE = eINSTANCE.getTwoLifelineMessage_SourceNote();

    /**
     * The meta object literal for the '<em><b>Target Note</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TARGET_NOTE = eINSTANCE.getTwoLifelineMessage_TargetNote();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl <em>One Lifeline Message</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getOneLifelineMessage()
     * @generated
     */
    EClass ONE_LIFELINE_MESSAGE = eINSTANCE.getOneLifelineMessage();

    /**
     * The meta object literal for the '<em><b>Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ONE_LIFELINE_MESSAGE__LIFELINE = eINSTANCE.getOneLifelineMessage_Lifeline();

    /**
     * The meta object literal for the '<em><b>Message Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__MESSAGE_TYPE = eINSTANCE.getOneLifelineMessage_MessageType();

    /**
     * The meta object literal for the '<em><b>Message Type Lost And Found</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__MESSAGE_TYPE_LOST_AND_FOUND = eINSTANCE.getOneLifelineMessage_MessageTypeLostAndFound();

    /**
     * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__MESSAGE = eINSTANCE.getOneLifelineMessage_Message();

    /**
     * The meta object literal for the '<em><b>Start End Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__START_END_EXEC = eINSTANCE.getOneLifelineMessage_StartEndExec();

    /**
     * The meta object literal for the '<em><b>Start Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__START_EXEC = eINSTANCE.getOneLifelineMessage_StartExec();

    /**
     * The meta object literal for the '<em><b>End Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__END_EXEC = eINSTANCE.getOneLifelineMessage_EndExec();

    /**
     * The meta object literal for the '<em><b>End Exec Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__END_EXEC_COUNT = eINSTANCE.getOneLifelineMessage_EndExecCount();

    /**
     * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__NOTE = eINSTANCE.getOneLifelineMessage_Note();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl <em>Self Message</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SelfMessageImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getSelfMessage()
     * @generated
     */
    EClass SELF_MESSAGE = eINSTANCE.getSelfMessage();

    /**
     * The meta object literal for the '<em><b>Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELF_MESSAGE__LIFELINE = eINSTANCE.getSelfMessage_Lifeline();

    /**
     * The meta object literal for the '<em><b>Message Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELF_MESSAGE__MESSAGE_TYPE = eINSTANCE.getSelfMessage_MessageType();

    /**
     * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELF_MESSAGE__MESSAGE = eINSTANCE.getSelfMessage_Message();

    /**
     * The meta object literal for the '<em><b>Start End Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELF_MESSAGE__START_END_EXEC = eINSTANCE.getSelfMessage_StartEndExec();

    /**
     * The meta object literal for the '<em><b>Start Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELF_MESSAGE__START_EXEC = eINSTANCE.getSelfMessage_StartExec();

    /**
     * The meta object literal for the '<em><b>End Exec</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELF_MESSAGE__END_EXEC = eINSTANCE.getSelfMessage_EndExec();

    /**
     * The meta object literal for the '<em><b>End Exec Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELF_MESSAGE__END_EXEC_COUNT = eINSTANCE.getSelfMessage_EndExecCount();

    /**
     * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SELF_MESSAGE__NOTE = eINSTANCE.getSelfMessage_Note();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyLifelineEventImpl <em>Destroy Lifeline Event</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyLifelineEventImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getDestroyLifelineEvent()
     * @generated
     */
    EClass DESTROY_LIFELINE_EVENT = eINSTANCE.getDestroyLifelineEvent();

    /**
     * The meta object literal for the '<em><b>Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DESTROY_LIFELINE_EVENT__LIFELINE = eINSTANCE.getDestroyLifelineEvent_Lifeline();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentImpl <em>Fragment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getFragment()
     * @generated
     */
    EClass FRAGMENT = eINSTANCE.getFragment();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FRAGMENT__NAME = eINSTANCE.getFragment_Name();

    /**
     * The meta object literal for the '<em><b>Sections</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FRAGMENT__SECTIONS = eINSTANCE.getFragment_Sections();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.SectionImpl <em>Section</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SectionImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getSection()
     * @generated
     */
    EClass SECTION = eINSTANCE.getSection();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SECTION__LABEL = eINSTANCE.getSection_Label();

    /**
     * The meta object literal for the '<em><b>Interactions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SECTION__INTERACTIONS = eINSTANCE.getSection_Interactions();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.RefinementImpl <em>Refinement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.RefinementImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getRefinement()
     * @generated
     */
    EClass REFINEMENT = eINSTANCE.getRefinement();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REFINEMENT__LABEL = eINSTANCE.getRefinement_Label();

    /**
     * The meta object literal for the '<em><b>Lifelines</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFINEMENT__LIFELINES = eINSTANCE.getRefinement_Lifelines();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne <em>Message Type One</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeOne
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getMessageTypeOne()
     * @generated
     */
    EEnum MESSAGE_TYPE_ONE = eINSTANCE.getMessageTypeOne();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo <em>Message Type Two</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeTwo
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getMessageTypeTwo()
     * @generated
     */
    EEnum MESSAGE_TYPE_TWO = eINSTANCE.getMessageTypeTwo();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeLostAndFound <em>Message Type Lost And Found</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageTypeLostAndFound
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getMessageTypeLostAndFound()
     * @generated
     */
    EEnum MESSAGE_TYPE_LOST_AND_FOUND = eINSTANCE.getMessageTypeLostAndFound();

  }

} //SequencePackage
