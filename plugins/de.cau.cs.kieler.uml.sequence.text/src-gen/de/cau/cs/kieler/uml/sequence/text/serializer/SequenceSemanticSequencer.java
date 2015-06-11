package de.cau.cs.kieler.uml.sequence.text.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.uml.sequence.text.sequence.Destroy;
import de.cau.cs.kieler.uml.sequence.text.sequence.Fragment;
import de.cau.cs.kieler.uml.sequence.text.sequence.FragmentContent;
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline;
import de.cau.cs.kieler.uml.sequence.text.sequence.LocalVariable;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineEndBlock;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineMessage;
import de.cau.cs.kieler.uml.sequence.text.sequence.OneLifelineNote;
import de.cau.cs.kieler.uml.sequence.text.sequence.Refinement;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram;
import de.cau.cs.kieler.uml.sequence.text.sequence.SequencePackage;
import de.cau.cs.kieler.uml.sequence.text.sequence.TwoLifelineMessage;
import de.cau.cs.kieler.uml.sequence.text.services.SequenceGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class SequenceSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private SequenceGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == SequencePackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case SequencePackage.DESTROY:
				if(context == grammarAccess.getDestroyRule() ||
				   context == grammarAccess.getInteractionRule()) {
					sequence_Destroy(context, (Destroy) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.FRAGMENT:
				if(context == grammarAccess.getFragmentRule() ||
				   context == grammarAccess.getInteractionRule()) {
					sequence_Fragment(context, (Fragment) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.FRAGMENT_CONTENT:
				if(context == grammarAccess.getFragmentContentRule()) {
					sequence_FragmentContent(context, (FragmentContent) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.LIFELINE:
				if(context == grammarAccess.getLifelineRule()) {
					sequence_Lifeline(context, (Lifeline) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.LOCAL_VARIABLE:
				if(context == grammarAccess.getLocalVariableRule()) {
					sequence_LocalVariable(context, (LocalVariable) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.ONE_LIFELINE_END_BLOCK:
				if(context == grammarAccess.getInteractionRule() ||
				   context == grammarAccess.getOneLifelineEndBlockRule()) {
					sequence_OneLifelineEndBlock(context, (OneLifelineEndBlock) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.ONE_LIFELINE_MESSAGE:
				if(context == grammarAccess.getInteractionRule() ||
				   context == grammarAccess.getOneLifelineMessageRule()) {
					sequence_OneLifelineMessage(context, (OneLifelineMessage) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.ONE_LIFELINE_NOTE:
				if(context == grammarAccess.getInteractionRule() ||
				   context == grammarAccess.getOneLifelineNoteRule()) {
					sequence_OneLifelineNote(context, (OneLifelineNote) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.REFINEMENT:
				if(context == grammarAccess.getInteractionRule() ||
				   context == grammarAccess.getRefinementRule()) {
					sequence_Refinement(context, (Refinement) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.SEQUENCE_DIAGRAM:
				if(context == grammarAccess.getSequenceDiagramRule()) {
					sequence_SequenceDiagram(context, (SequenceDiagram) semanticObject); 
					return; 
				}
				else break;
			case SequencePackage.TWO_LIFELINE_MESSAGE:
				if(context == grammarAccess.getInteractionRule() ||
				   context == grammarAccess.getTwoLifelineMessageRule()) {
					sequence_TwoLifelineMessage(context, (TwoLifelineMessage) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (Lifeline=[Lifeline|ID] destroy?='destroy')
	 */
	protected void sequence_Destroy(EObject context, Destroy semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SequencePackage.Literals.DESTROY__LIFELINE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencePackage.Literals.DESTROY__LIFELINE));
			if(transientValues.isValueTransient(semanticObject, SequencePackage.Literals.DESTROY__DESTROY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencePackage.Literals.DESTROY__DESTROY));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDestroyAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1(), semanticObject.getLifeline());
		feeder.accept(grammarAccess.getDestroyAccess().getDestroyDestroyKeyword_1_0(), semanticObject.isDestroy());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (label=STRING? interactions+=Interaction interactions+=Interaction*)
	 */
	protected void sequence_FragmentContent(EObject context, FragmentContent semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=STRING fragmentContents+=FragmentContent fragmentContents+=FragmentContent*)
	 */
	protected void sequence_Fragment(EObject context, Fragment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (caption=STRING name=ID)
	 */
	protected void sequence_Lifeline(EObject context, Lifeline semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SequencePackage.Literals.LIFELINE__CAPTION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencePackage.Literals.LIFELINE__CAPTION));
			if(transientValues.isValueTransient(semanticObject, SequencePackage.Literals.LIFELINE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencePackage.Literals.LIFELINE__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLifelineAccess().getCaptionSTRINGTerminalRuleCall_1_0(), semanticObject.getCaption());
		feeder.accept(grammarAccess.getLifelineAccess().getNameIDTerminalRuleCall_3_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID | name=ID | name=ID | name=ID)
	 */
	protected void sequence_LocalVariable(EObject context, LocalVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Lifeline=[Lifeline|ID] endBlock?='endBlock' endBlockCount=INT_GREATER_ZERO?)
	 */
	protected void sequence_OneLifelineEndBlock(EObject context, OneLifelineEndBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         Lifeline=[Lifeline|ID] 
	 *         transitionType=TransitionType 
	 *         caption=STRING 
	 *         (startBlock?='startBlock' | (endBlock?='endBlock' endBlockCount=INT_GREATER_ZERO?))? 
	 *         note=STRING?
	 *     )
	 */
	protected void sequence_OneLifelineMessage(EObject context, OneLifelineMessage semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Lifeline=[Lifeline|ID] note=STRING)
	 */
	protected void sequence_OneLifelineNote(EObject context, OneLifelineNote semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SequencePackage.Literals.ONE_LIFELINE_NOTE__LIFELINE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencePackage.Literals.ONE_LIFELINE_NOTE__LIFELINE));
			if(transientValues.isValueTransient(semanticObject, SequencePackage.Literals.ONE_LIFELINE_NOTE__NOTE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SequencePackage.Literals.ONE_LIFELINE_NOTE__NOTE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOneLifelineNoteAccess().getLifelineLifelineIDTerminalRuleCall_0_0_1(), semanticObject.getLifeline());
		feeder.accept(grammarAccess.getOneLifelineNoteAccess().getNoteSTRINGTerminalRuleCall_2_0(), semanticObject.getNote());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (lifelines+=[Lifeline|ID] lifelines+=[Lifeline|ID]* label=STRING)
	 */
	protected void sequence_Refinement(EObject context, Refinement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (DiagramName=STRING locals+=LocalVariable* lifelines+=Lifeline* interactions+=Interaction*)
	 */
	protected void sequence_SequenceDiagram(EObject context, SequenceDiagram semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         sourceLifeline=[Lifeline|ID] 
	 *         transitionType=TransitionType 
	 *         caption=STRING 
	 *         targetLifeline=[Lifeline|ID] 
	 *         (startBlockLeft?='sourceStartBlock' | (endBlockLeft?='sourceEndBlock' endBlockLeftCount=INT_GREATER_ZERO?))? 
	 *         (startBlockRight?='targetStartBlock' | (endBlockRight?='targetEndBlock' endBlockRightCount=INT_GREATER_ZERO?))? 
	 *         sourceNote=STRING? 
	 *         targetNote=STRING?
	 *     )
	 */
	protected void sequence_TwoLifelineMessage(EObject context, TwoLifelineMessage semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
