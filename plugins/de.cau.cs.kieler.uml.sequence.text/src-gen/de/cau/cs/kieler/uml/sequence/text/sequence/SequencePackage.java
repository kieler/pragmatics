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
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_VARIABLE__NAME = 0;

  /**
   * The number of structural features of the '<em>Local Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCAL_VARIABLE_FEATURE_COUNT = 1;

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
   * The feature id for the '<em><b>Transition Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TRANSITION_TYPE = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Caption</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__CAPTION = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Target Lifeline</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__TARGET_LIFELINE = INTERACTION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Start Block Left</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__START_BLOCK_LEFT = INTERACTION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>End Block Left</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT = INTERACTION_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>End Block Left Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT_COUNT = INTERACTION_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Start Block Right</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__START_BLOCK_RIGHT = INTERACTION_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>End Block Right</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT = INTERACTION_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>End Block Right Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT_COUNT = INTERACTION_FEATURE_COUNT + 9;

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
   * The feature id for the '<em><b>Transition Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_MESSAGE__TRANSITION_TYPE = INTERACTION_FEATURE_COUNT + 1;

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
   * The feature id for the '<em><b>End Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_END_BLOCK__END_BLOCK = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>End Block Count</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_END_BLOCK__END_BLOCK_COUNT = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>One Lifeline End Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ONE_LIFELINE_END_BLOCK_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 3;

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
   * The feature id for the '<em><b>Destroy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESTROY__DESTROY = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Destroy</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESTROY_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 2;

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
   * The feature id for the '<em><b>Fragment Contents</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FRAGMENT__FRAGMENT_CONTENTS = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Fragment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FRAGMENT_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentContentImpl <em>Fragment Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentContentImpl
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getFragmentContent()
   * @generated
   */
  int FRAGMENT_CONTENT = 10;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FRAGMENT_CONTENT__LABEL = 0;

  /**
   * The feature id for the '<em><b>Interactions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FRAGMENT_CONTENT__INTERACTIONS = 1;

  /**
   * The number of structural features of the '<em>Fragment Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FRAGMENT_CONTENT_FEATURE_COUNT = 2;

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
   * The feature id for the '<em><b>Lifelines</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFINEMENT__LIFELINES = INTERACTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFINEMENT__LABEL = INTERACTION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Refinement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REFINEMENT_FEATURE_COUNT = INTERACTION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType <em>Transition Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getTransitionType()
   * @generated
   */
  int TRANSITION_TYPE = 12;


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
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTransitionType <em>Transition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Transition Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getTransitionType()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_TransitionType();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getCaption <em>Caption</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Caption</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getCaption()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_Caption();

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
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isStartBlockLeft <em>Start Block Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start Block Left</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isStartBlockLeft()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_StartBlockLeft();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isEndBlockLeft <em>End Block Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Block Left</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isEndBlockLeft()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_EndBlockLeft();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getEndBlockLeftCount <em>End Block Left Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Block Left Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getEndBlockLeftCount()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_EndBlockLeftCount();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isStartBlockRight <em>Start Block Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Start Block Right</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isStartBlockRight()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_StartBlockRight();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isEndBlockRight <em>End Block Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Block Right</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#isEndBlockRight()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_EndBlockRight();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getEndBlockRightCount <em>End Block Right Count</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Block Right Count</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage#getEndBlockRightCount()
   * @see #getTwoLifelineMessage()
   * @generated
   */
  EAttribute getTwoLifelineMessage_EndBlockRightCount();

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
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getTransitionType <em>Transition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Transition Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage#getTransitionType()
   * @see #getOneLifelineMessage()
   * @generated
   */
  EAttribute getOneLifelineMessage_TransitionType();

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
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#isEndBlock <em>End Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End Block</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock#isEndBlock()
   * @see #getOneLifelineEndBlock()
   * @generated
   */
  EAttribute getOneLifelineEndBlock_EndBlock();

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
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Destroy#isDestroy <em>Destroy</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Destroy</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Destroy#isDestroy()
   * @see #getDestroy()
   * @generated
   */
  EAttribute getDestroy_Destroy();

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
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getFragmentContents <em>Fragment Contents</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Fragment Contents</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.Fragment#getFragmentContents()
   * @see #getFragment()
   * @generated
   */
  EReference getFragment_FragmentContents();

  /**
   * Returns the meta object for class '{@link de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent <em>Fragment Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fragment Content</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent
   * @generated
   */
  EClass getFragmentContent();

  /**
   * Returns the meta object for the attribute '{@link de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent#getLabel <em>Label</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Label</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent#getLabel()
   * @see #getFragmentContent()
   * @generated
   */
  EAttribute getFragmentContent_Label();

  /**
   * Returns the meta object for the containment reference list '{@link de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent#getInteractions <em>Interactions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Interactions</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent#getInteractions()
   * @see #getFragmentContent()
   * @generated
   */
  EReference getFragmentContent_Interactions();

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
   * Returns the meta object for enum '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType <em>Transition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Transition Type</em>'.
   * @see de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType
   * @generated
   */
  EEnum getTransitionType();

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
     * The meta object literal for the '<em><b>Transition Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__TRANSITION_TYPE = eINSTANCE.getTwoLifelineMessage_TransitionType();

    /**
     * The meta object literal for the '<em><b>Caption</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__CAPTION = eINSTANCE.getTwoLifelineMessage_Caption();

    /**
     * The meta object literal for the '<em><b>Target Lifeline</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TWO_LIFELINE_MESSAGE__TARGET_LIFELINE = eINSTANCE.getTwoLifelineMessage_TargetLifeline();

    /**
     * The meta object literal for the '<em><b>Start Block Left</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__START_BLOCK_LEFT = eINSTANCE.getTwoLifelineMessage_StartBlockLeft();

    /**
     * The meta object literal for the '<em><b>End Block Left</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT = eINSTANCE.getTwoLifelineMessage_EndBlockLeft();

    /**
     * The meta object literal for the '<em><b>End Block Left Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__END_BLOCK_LEFT_COUNT = eINSTANCE.getTwoLifelineMessage_EndBlockLeftCount();

    /**
     * The meta object literal for the '<em><b>Start Block Right</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__START_BLOCK_RIGHT = eINSTANCE.getTwoLifelineMessage_StartBlockRight();

    /**
     * The meta object literal for the '<em><b>End Block Right</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT = eINSTANCE.getTwoLifelineMessage_EndBlockRight();

    /**
     * The meta object literal for the '<em><b>End Block Right Count</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TWO_LIFELINE_MESSAGE__END_BLOCK_RIGHT_COUNT = eINSTANCE.getTwoLifelineMessage_EndBlockRightCount();

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
     * The meta object literal for the '<em><b>Transition Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_MESSAGE__TRANSITION_TYPE = eINSTANCE.getOneLifelineMessage_TransitionType();

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
     * The meta object literal for the '<em><b>End Block</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ONE_LIFELINE_END_BLOCK__END_BLOCK = eINSTANCE.getOneLifelineEndBlock_EndBlock();

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
     * The meta object literal for the '<em><b>Destroy</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DESTROY__DESTROY = eINSTANCE.getDestroy_Destroy();

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
     * The meta object literal for the '<em><b>Fragment Contents</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FRAGMENT__FRAGMENT_CONTENTS = eINSTANCE.getFragment_FragmentContents();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentContentImpl <em>Fragment Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.FragmentContentImpl
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getFragmentContent()
     * @generated
     */
    EClass FRAGMENT_CONTENT = eINSTANCE.getFragmentContent();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FRAGMENT_CONTENT__LABEL = eINSTANCE.getFragmentContent_Label();

    /**
     * The meta object literal for the '<em><b>Interactions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FRAGMENT_CONTENT__INTERACTIONS = eINSTANCE.getFragmentContent_Interactions();

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
     * The meta object literal for the '<em><b>Lifelines</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REFINEMENT__LIFELINES = eINSTANCE.getRefinement_Lifelines();

    /**
     * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REFINEMENT__LABEL = eINSTANCE.getRefinement_Label();

    /**
     * The meta object literal for the '{@link de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType <em>Transition Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.TransitionType
     * @see de.cau.cs.kieler.uml.sequence.text.sequence.impl.SequencePackageImpl#getTransitionType()
     * @generated
     */
    EEnum TRANSITION_TYPE = eINSTANCE.getTransitionType();

  }

} //SequencePackage
