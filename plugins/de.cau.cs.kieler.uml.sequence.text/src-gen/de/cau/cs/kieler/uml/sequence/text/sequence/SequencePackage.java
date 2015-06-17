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
   * The feature id for the '<em><b>Locals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DIAGRAM__LOCALS = 1;

  /**
   * The feature id for the '<em><b>Lifelines</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DIAGRAM__LIFELINES = 2;

  /**
   * The feature id for the '<em><b>Interactions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DIAGRAM__INTERACTIONS = 3;

  /**
   * The number of structural features of the '<em>Diagram</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SEQUENCE_DIAGRAM_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.LocalVariableImpl <em>Local Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.LocalVariableImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getLocalVariable()
   * @generated
   */
  int LOCAL_VARIABLE = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_VARIABLE__TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_VARIABLE__NAME = 1;

  /**
   * The number of structural features of the '<em>Local Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_VARIABLE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.LifelineImpl <em>Lifeline</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.LifelineImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getLifeline()
   * @generated
   */
  int LIFELINE = 2;

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
   * The number of structural features of the '<em>Lifeline</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIFELINE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.InteractionImpl <em>Interaction</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.InteractionImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getInteraction()
   * @generated
   */
  int INTERACTION = 3;

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
  int TWO_LIFELINE_MESSAGE = 4;

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
   * The feature id for the '<em><b>Source Start Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_START_BLOCK = INTERACTION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Source End Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK = INTERACTION_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Source End Block Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK_COUNT = INTERACTION_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Target Start Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_START_BLOCK = INTERACTION_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Target End Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK = INTERACTION_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Target End Block Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK_COUNT = INTERACTION_FEATURE_COUNT + 9;

  /**
   * The feature id for the '<em><b>Source Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__SOURCE_NOTE = INTERACTION_FEATURE_COUNT + 10;

  /**
   * The feature id for the '<em><b>Target Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_NOTE = INTERACTION_FEATURE_COUNT + 11;

  /**
   * The number of structural features of the '<em>Two Lifeline Message</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 12;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl <em>One Lifeline Message</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineMessageImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getOneLifelineMessage()
   * @generated
   */
  int ONE_LIFELINE_MESSAGE = 5;

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
   * The feature id for the '<em><b>Caption</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__CAPTION = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Start Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__START_BLOCK = INTERACTION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>End Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__END_BLOCK = INTERACTION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>End Block Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__END_BLOCK_COUNT = INTERACTION_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__NOTE = INTERACTION_FEATURE_COUNT + 6;

  /**
   * The number of structural features of the '<em>One Lifeline Message</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 7;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineEndBlockImpl <em>One Lifeline End Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineEndBlockImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getOneLifelineEndBlock()
   * @generated
   */
  int ONE_LIFELINE_END_BLOCK = 6;

  /**
   * The feature id for the '<em><b>Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_END_BLOCK__LIFELINE = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>End Block Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_END_BLOCK__END_BLOCK_COUNT = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>One Lifeline End Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_END_BLOCK_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineNoteImpl <em>One Lifeline Note</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineNoteImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getOneLifelineNote()
   * @generated
   */
  int ONE_LIFELINE_NOTE = 7;

  /**
   * The feature id for the '<em><b>Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_NOTE__LIFELINE = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Note</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_NOTE__NOTE = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>One Lifeline Note</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_NOTE_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyImpl <em>Destroy</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getDestroy()
   * @generated
   */
  int DESTROY = 8;

  /**
   * The feature id for the '<em><b>Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESTROY__LIFELINE = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Destroy</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESTROY_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentImpl <em>Fragment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getFragment()
   * @generated
   */
  int FRAGMENT = 9;

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
  int SECTION = 10;

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
  int REFINEMENT = 11;

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
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageType <em>Message Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageType
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getMessageType()
   * @generated
   */
  int MESSAGE_TYPE = 12;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.DataType <em>Data Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.DataType
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getDataType()
   * @generated
   */
  int DATA_TYPE = 13;


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
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getLocals <em>Locals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Locals</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram#getLocals()
   * @see #getSequenceDiagram()
   * @generated
   */
  EReference getSequenceDiagram_Locals();

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
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable <em>Local Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Local Variable</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable
   * @generated
   */
  EClass getLocalVariable();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable#getType()
   * @see #getLocalVariable()
   * @generated
   */
  EAttribute getLocalVariable_Type();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable#getName()
   * @see #getLocalVariable()
   * @generated
   */
  EAttribute getLocalVariable_Name();

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
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartBlock <em>Source Start Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source Start Block</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceStartBlock()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_SourceStartBlock();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceEndBlock <em>Source End Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source End Block</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isSourceEndBlock()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_SourceEndBlock();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceEndBlockCount <em>Source End Block Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source End Block Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getSourceEndBlockCount()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_SourceEndBlockCount();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartBlock <em>Target Start Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target Start Block</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetStartBlock()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TargetStartBlock();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetEndBlock <em>Target End Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target End Block</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isTargetEndBlock()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TargetEndBlock();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetEndBlockCount <em>Target End Block Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target End Block Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTargetEndBlockCount()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TargetEndBlockCount();

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
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getCaption <em>Caption</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Caption</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getCaption()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_Caption();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartBlock <em>Start Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start Block</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isStartBlock()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_StartBlock();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isEndBlock <em>End Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Block</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#isEndBlock()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_EndBlock();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getEndBlockCount <em>End Block Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Block Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getEndBlockCount()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_EndBlockCount();

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
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock <em>One Lifeline End Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>One Lifeline End Block</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock
   * @generated
   */
  EClass getOneLifelineEndBlock();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#getLifeline <em>Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#getLifeline()
   * @see #getOneLifelineEndBlock()
   * @generated
   */
  EReference getOneLifelineEndBlock_Lifeline();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#getEndBlockCount <em>End Block Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Block Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#getEndBlockCount()
   * @see #getOneLifelineEndBlock()
   * @generated
   */
  EAttribute getOneLifelineEndBlock_EndBlockCount();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote <em>One Lifeline Note</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>One Lifeline Note</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote
   * @generated
   */
  EClass getOneLifelineNote();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote#getLifeline <em>Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote#getLifeline()
   * @see #getOneLifelineNote()
   * @generated
   */
  EReference getOneLifelineNote_Lifeline();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote#getNote <em>Note</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Note</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote#getNote()
   * @see #getOneLifelineNote()
   * @generated
   */
  EAttribute getOneLifelineNote_Note();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Destroy <em>Destroy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Destroy</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Destroy
   * @generated
   */
  EClass getDestroy();

  /**
   * Returns the meta object for the reference '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Destroy#getLifeline <em>Lifeline</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Lifeline</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Destroy#getLifeline()
   * @see #getDestroy()
   * @generated
   */
  EReference getDestroy_Lifeline();

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
   * Returns the meta object for enum '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageType <em>Message Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Message Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageType
   * @generated
   */
  EEnum getMessageType();

  /**
   * Returns the meta object for enum '{@link de.cau.cs.kieler.uml.sequence.text.sequence.DataType <em>Data Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Data Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.DataType
   * @generated
   */
  EEnum getDataType();

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
     * The meta object literal for the '<em><b>Locals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SEQUENCE_DIAGRAM__LOCALS = eINSTANCE.getSequenceDiagram_Locals();

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
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.LocalVariableImpl <em>Local Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.LocalVariableImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getLocalVariable()
     * @generated
     */
    EClass LOCAL_VARIABLE = eINSTANCE.getLocalVariable();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCAL_VARIABLE__TYPE = eINSTANCE.getLocalVariable_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCAL_VARIABLE__NAME = eINSTANCE.getLocalVariable_Name();

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
     * The meta object literal for the '<em><b>Source Start Block</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__SOURCE_START_BLOCK = eINSTANCE.getTwoLifelineMessage_SourceStartBlock();

    /**
     * The meta object literal for the '<em><b>Source End Block</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK = eINSTANCE.getTwoLifelineMessage_SourceEndBlock();

    /**
     * The meta object literal for the '<em><b>Source End Block Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__SOURCE_END_BLOCK_COUNT = eINSTANCE.getTwoLifelineMessage_SourceEndBlockCount();

    /**
     * The meta object literal for the '<em><b>Target Start Block</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TARGET_START_BLOCK = eINSTANCE.getTwoLifelineMessage_TargetStartBlock();

    /**
     * The meta object literal for the '<em><b>Target End Block</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK = eINSTANCE.getTwoLifelineMessage_TargetEndBlock();

    /**
     * The meta object literal for the '<em><b>Target End Block Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TARGET_END_BLOCK_COUNT = eINSTANCE.getTwoLifelineMessage_TargetEndBlockCount();

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
     * The meta object literal for the '<em><b>Caption</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__CAPTION = eINSTANCE.getOneLifelineMessage_Caption();

    /**
     * The meta object literal for the '<em><b>Start Block</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__START_BLOCK = eINSTANCE.getOneLifelineMessage_StartBlock();

    /**
     * The meta object literal for the '<em><b>End Block</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__END_BLOCK = eINSTANCE.getOneLifelineMessage_EndBlock();

    /**
     * The meta object literal for the '<em><b>End Block Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__END_BLOCK_COUNT = eINSTANCE.getOneLifelineMessage_EndBlockCount();

    /**
     * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__NOTE = eINSTANCE.getOneLifelineMessage_Note();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineEndBlockImpl <em>One Lifeline End Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineEndBlockImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getOneLifelineEndBlock()
     * @generated
     */
    EClass ONE_LIFELINE_END_BLOCK = eINSTANCE.getOneLifelineEndBlock();

    /**
     * The meta object literal for the '<em><b>Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ONE_LIFELINE_END_BLOCK__LIFELINE = eINSTANCE.getOneLifelineEndBlock_Lifeline();

    /**
     * The meta object literal for the '<em><b>End Block Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_END_BLOCK__END_BLOCK_COUNT = eINSTANCE.getOneLifelineEndBlock_EndBlockCount();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineNoteImpl <em>One Lifeline Note</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.OneLifelineNoteImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getOneLifelineNote()
     * @generated
     */
    EClass ONE_LIFELINE_NOTE = eINSTANCE.getOneLifelineNote();

    /**
     * The meta object literal for the '<em><b>Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ONE_LIFELINE_NOTE__LIFELINE = eINSTANCE.getOneLifelineNote_Lifeline();

    /**
     * The meta object literal for the '<em><b>Note</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_NOTE__NOTE = eINSTANCE.getOneLifelineNote_Note();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyImpl <em>Destroy</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.DestroyImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getDestroy()
     * @generated
     */
    EClass DESTROY = eINSTANCE.getDestroy();

    /**
     * The meta object literal for the '<em><b>Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DESTROY__LIFELINE = eINSTANCE.getDestroy_Lifeline();

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
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.MessageType <em>Message Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.MessageType
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getMessageType()
     * @generated
     */
    EEnum MESSAGE_TYPE = eINSTANCE.getMessageType();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.DataType <em>Data Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.DataType
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getDataType()
     * @generated
     */
    EEnum DATA_TYPE = eINSTANCE.getDataType();

  }

} //SequencePackage
