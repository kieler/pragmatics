package de.cau.cs.kieler.kiml.grana.text.serializer;

import com.google.inject.Inject;
import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class GranaSyntacticSequencer extends AbstractSyntacticSequencer {

	protected GranaGrammarAccess grammarAccess;
	protected AbstractElementAlias match_Grana_GlobalResourcesKeyword_0_0_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (GranaGrammarAccess) access;
		match_Grana_GlobalResourcesKeyword_0_0_q = new TokenAlias(false, true, grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0());
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
			if(match_Grana_GlobalResourcesKeyword_0_0_q.equals(syntax))
				emit_Grana_GlobalResourcesKeyword_0_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     'globalResources'?
	 */
	protected void emit_Grana_GlobalResourcesKeyword_0_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
