package de.cau.cs.kieler.kiml.grana.text.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.kiml.grana.text.grana.Analysis;
import de.cau.cs.kieler.kiml.grana.text.grana.GlobalResourceRef;
import de.cau.cs.kieler.kiml.grana.text.grana.Grana;
import de.cau.cs.kieler.kiml.grana.text.grana.GranaPackage;
import de.cau.cs.kieler.kiml.grana.text.grana.Job;
import de.cau.cs.kieler.kiml.grana.text.grana.LocalResource;
import de.cau.cs.kieler.kiml.grana.text.grana.ResourceReference;
import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess;
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
public class GranaSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private GranaGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == GranaPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case GranaPackage.ANALYSIS:
				if(context == grammarAccess.getAnalysisRule()) {
					sequence_Analysis(context, (Analysis) semanticObject); 
					return; 
				}
				else break;
			case GranaPackage.GLOBAL_RESOURCE_REF:
				if(context == grammarAccess.getGlobalResourceRefRule()) {
					sequence_GlobalResourceRef(context, (GlobalResourceRef) semanticObject); 
					return; 
				}
				else break;
			case GranaPackage.GRANA:
				if(context == grammarAccess.getGranaRule()) {
					sequence_Grana(context, (Grana) semanticObject); 
					return; 
				}
				else break;
			case GranaPackage.JOB:
				if(context == grammarAccess.getJobRule()) {
					sequence_Job(context, (Job) semanticObject); 
					return; 
				}
				else break;
			case GranaPackage.LOCAL_RESOURCE:
				if(context == grammarAccess.getLocalResourceRule() ||
				   context == grammarAccess.getResourceRule()) {
					sequence_LocalResource(context, (LocalResource) semanticObject); 
					return; 
				}
				else break;
			case GranaPackage.RESOURCE_REFERENCE:
				if(context == grammarAccess.getResourceRule() ||
				   context == grammarAccess.getResourceReferenceRule()) {
					sequence_ResourceReference(context, (ResourceReference) semanticObject); 
					return; 
				}
				else break;
			}
		else if(semanticObject.eClass().getEPackage() == KGraphPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
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
	 *     name=QualifiedID
	 */
	protected void sequence_Analysis(EObject context, Analysis semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, GranaPackage.Literals.ANALYSIS__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, GranaPackage.Literals.ANALYSIS__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID resources+=LocalResource)
	 */
	protected void sequence_GlobalResourceRef(EObject context, GlobalResourceRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (globalResources+=GlobalResourceRef* jobs+=Job+)
	 */
	protected void sequence_Grana(EObject context, Grana semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID? 
	 *         layoutBeforeAnalysis?='layoutBeforeAnalysis'? 
	 *         measureExecutionTime?='measureExecutionTime'? 
	 *         resources+=Resource* 
	 *         layoutOptions+=KIdentifier 
	 *         analyses+=Analysis* 
	 *         output=STRING
	 *     )
	 */
	protected void sequence_Job(EObject context, Job semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=ID (persistentEntries+=PersistentEntry persistentEntries+=PersistentEntry*)?)
	 */
	protected void sequence_KIdentifier(EObject context, KIdentifier semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (path=STRING filter=STRING)
	 */
	protected void sequence_LocalResource(EObject context, LocalResource semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, GranaPackage.Literals.LOCAL_RESOURCE__PATH) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, GranaPackage.Literals.LOCAL_RESOURCE__PATH));
			if(transientValues.isValueTransient(semanticObject, GranaPackage.Literals.LOCAL_RESOURCE__FILTER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, GranaPackage.Literals.LOCAL_RESOURCE__FILTER));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLocalResourceAccess().getPathSTRINGTerminalRuleCall_0_0(), semanticObject.getPath());
		feeder.accept(grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0(), semanticObject.getFilter());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (key=QualifiedID value=PropertyValue)
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
		feeder.accept(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     resourceRefs+=[GlobalResourceRef|ID]+
	 */
	protected void sequence_ResourceReference(EObject context, ResourceReference semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
