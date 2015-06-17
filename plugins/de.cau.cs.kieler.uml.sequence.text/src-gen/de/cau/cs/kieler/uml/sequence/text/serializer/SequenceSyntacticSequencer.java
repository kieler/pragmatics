package de.cau.cs.kieler.uml.sequence.text.serializer;

import com.google.inject.Inject;
import de.cau.cs.kieler.uml.sequence.text.services.SequenceGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class SequenceSyntacticSequencer extends AbstractSyntacticSequencer {

	protected SequenceGrammarAccess grammarAccess;
	protected AbstractElementAlias match_OneLifelineMessage_FoundKeyword_2_1_or_LostKeyword_2_0;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (SequenceGrammarAccess) access;
		match_OneLifelineMessage_FoundKeyword_2_1_or_LostKeyword_2_0 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getOneLifelineMessageAccess().getFoundKeyword_2_1()), new TokenAlias(false, false, grammarAccess.getOneLifelineMessageAccess().getLostKeyword_2_0()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_OneLifelineMessage_FoundKeyword_2_1_or_LostKeyword_2_0.equals(syntax))
				emit_OneLifelineMessage_FoundKeyword_2_1_or_LostKeyword_2_0(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     'lost' | 'found'
	 */
	protected void emit_OneLifelineMessage_FoundKeyword_2_1_or_LostKeyword_2_0(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
