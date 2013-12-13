package de.cau.cs.kieler.kiml.config.text.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.kiml.config.text.services.LayoutConfigGrammarAccess;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataPackage;
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
public abstract class AbstractLayoutConfigSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private LayoutConfigGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == KGraphPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KGraphPackage.KNODE:
				if(context == grammarAccess.getKGraphElementRule()) {
					sequence_KGraphElement(context, (KNode) semanticObject); 
					return; 
				}
				else break;
			case KGraphPackage.PERSISTENT_ENTRY:
				if(context == grammarAccess.getPersistentEntryRule()) {
					sequence_PersistentEntry(context, (PersistentEntry) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KLayoutDataPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case KLayoutDataPackage.KIDENTIFIER:
				if(context == grammarAccess.getKIdentifierRule()) {
					sequence_KIdentifier(context, (KIdentifier) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (data+=KIdentifier*)
	 */
	protected void sequence_KGraphElement(EObject context, KNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=EString (persistentEntries+=PersistentEntry persistentEntries+=PersistentEntry*)?)
	 */
	protected void sequence_KIdentifier(EObject context, KIdentifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (key=QualifiedID value=QualifiedID)
	 */
	protected void sequence_PersistentEntry(EObject context, PersistentEntry semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, KGraphPackage.Literals.PERSISTENT_ENTRY__KEY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KGraphPackage.Literals.PERSISTENT_ENTRY__KEY));
			if(transientValues.isValueTransient(semanticObject, KGraphPackage.Literals.PERSISTENT_ENTRY__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, KGraphPackage.Literals.PERSISTENT_ENTRY__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0(), semanticObject.getKey());
		feeder.accept(grammarAccess.getPersistentEntryAccess().getValueQualifiedIDParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
}
