/*
* generated by Xtext
*/
package de.cau.cs.kieler.klighd.kdiagram.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import de.cau.cs.kieler.klighd.kdiagram.services.KDiagramGrammarAccess;

public class KDiagramParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private KDiagramGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected de.cau.cs.kieler.klighd.kdiagram.parser.antlr.internal.InternalKDiagramParser createParser(XtextTokenStream stream) {
		return new de.cau.cs.kieler.klighd.kdiagram.parser.antlr.internal.InternalKDiagramParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "DiagramSynthesis";
	}
	
	public KDiagramGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(KDiagramGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
