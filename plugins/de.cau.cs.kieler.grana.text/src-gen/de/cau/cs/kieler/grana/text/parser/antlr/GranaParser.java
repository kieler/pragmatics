/*
 * generated by Xtext
 */
package de.cau.cs.kieler.grana.text.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import de.cau.cs.kieler.grana.text.services.GranaGrammarAccess;

public class GranaParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private GranaGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected de.cau.cs.kieler.grana.text.parser.antlr.internal.InternalGranaParser createParser(XtextTokenStream stream) {
		return new de.cau.cs.kieler.grana.text.parser.antlr.internal.InternalGranaParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "Grana";
	}
	
	public GranaGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(GranaGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}