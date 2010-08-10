/*
* generated by Xtext
*/
package de.cau.cs.kieler.core.annotations.parser.antlr;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

import com.google.inject.Inject;

import de.cau.cs.kieler.core.annotations.services.AnnotationsGrammarAccess;

public class AnnotationsParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private AnnotationsGrammarAccess grammarAccess;
	
	@Override
	protected IParseResult parse(String ruleName, CharStream in) {
		TokenSource tokenSource = createLexer(in);
		XtextTokenStream tokenStream = createTokenStream(tokenSource);
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
		de.cau.cs.kieler.core.annotations.parser.antlr.internal.InternalAnnotationsParser parser = createParser(tokenStream);
		parser.setTokenTypeMap(getTokenDefProvider().getTokenDefMap());
		parser.setSyntaxErrorProvider(getSyntaxErrorProvider());
		parser.setUnorderedGroupHelper(getUnorderedGroupHelper().get());
		try {
			if(ruleName != null)
				return parser.parse(ruleName);
			return parser.parse();
		} catch (Exception re) {
			throw new ParseException(re.getMessage(),re);
		}
	}
	
	protected de.cau.cs.kieler.core.annotations.parser.antlr.internal.InternalAnnotationsParser createParser(XtextTokenStream stream) {
		return new de.cau.cs.kieler.core.annotations.parser.antlr.internal.InternalAnnotationsParser(stream, getElementFactory(), getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "StringAnnotation";
	}
	
	public AnnotationsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(AnnotationsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
