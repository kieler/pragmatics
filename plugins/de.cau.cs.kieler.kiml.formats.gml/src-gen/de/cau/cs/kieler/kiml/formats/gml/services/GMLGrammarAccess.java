/*
* generated by Xtext
*/
package de.cau.cs.kieler.kiml.formats.gml.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import java.util.List;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;


@Singleton
public class GMLGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class GmlModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "GmlModel");
		private final Assignment cElementsAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cElementsElementParserRuleCall_0 = (RuleCall)cElementsAssignment.eContents().get(0);
		
		//GmlModel:
		//
		//	elements+=Element*;
		public ParserRule getRule() { return rule; }

		//elements+=Element*
		public Assignment getElementsAssignment() { return cElementsAssignment; }

		//Element
		public RuleCall getElementsElementParserRuleCall_0() { return cElementsElementParserRuleCall_0; }
	}

	public class ElementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Element");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cKeyAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cKeyIDTerminalRuleCall_0_0 = (RuleCall)cKeyAssignment_0.eContents().get(0);
		private final Alternatives cAlternatives_1 = (Alternatives)cGroup.eContents().get(1);
		private final Assignment cValueAssignment_1_0 = (Assignment)cAlternatives_1.eContents().get(0);
		private final RuleCall cValueValueParserRuleCall_1_0_0 = (RuleCall)cValueAssignment_1_0.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cAlternatives_1.eContents().get(1);
		private final Keyword cLeftSquareBracketKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final Assignment cElementsAssignment_1_1_1 = (Assignment)cGroup_1_1.eContents().get(1);
		private final RuleCall cElementsElementParserRuleCall_1_1_1_0 = (RuleCall)cElementsAssignment_1_1_1.eContents().get(0);
		private final Keyword cRightSquareBracketKeyword_1_1_2 = (Keyword)cGroup_1_1.eContents().get(2);
		
		//Element:
		//
		//	key=ID (value=Value | "[" elements+=Element* "]");
		public ParserRule getRule() { return rule; }

		//key=ID (value=Value | "[" elements+=Element* "]")
		public Group getGroup() { return cGroup; }

		//key=ID
		public Assignment getKeyAssignment_0() { return cKeyAssignment_0; }

		//ID
		public RuleCall getKeyIDTerminalRuleCall_0_0() { return cKeyIDTerminalRuleCall_0_0; }

		//value=Value | "[" elements+=Element* "]"
		public Alternatives getAlternatives_1() { return cAlternatives_1; }

		//value=Value
		public Assignment getValueAssignment_1_0() { return cValueAssignment_1_0; }

		//Value
		public RuleCall getValueValueParserRuleCall_1_0_0() { return cValueValueParserRuleCall_1_0_0; }

		//"[" elements+=Element* "]"
		public Group getGroup_1_1() { return cGroup_1_1; }

		//"["
		public Keyword getLeftSquareBracketKeyword_1_1_0() { return cLeftSquareBracketKeyword_1_1_0; }

		//elements+=Element*
		public Assignment getElementsAssignment_1_1_1() { return cElementsAssignment_1_1_1; }

		//Element
		public RuleCall getElementsElementParserRuleCall_1_1_1_0() { return cElementsElementParserRuleCall_1_1_1_0; }

		//"]"
		public Keyword getRightSquareBracketKeyword_1_1_2() { return cRightSquareBracketKeyword_1_1_2; }
	}

	public class ValueElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Value");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cGML_INTTerminalRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cGML_FLOATTerminalRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cSTRINGTerminalRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		
		//Value:
		//
		//	GML_INT | GML_FLOAT | STRING;
		public ParserRule getRule() { return rule; }

		//GML_INT | GML_FLOAT | STRING
		public Alternatives getAlternatives() { return cAlternatives; }

		//GML_INT
		public RuleCall getGML_INTTerminalRuleCall_0() { return cGML_INTTerminalRuleCall_0; }

		//GML_FLOAT
		public RuleCall getGML_FLOATTerminalRuleCall_1() { return cGML_FLOATTerminalRuleCall_1; }

		//STRING
		public RuleCall getSTRINGTerminalRuleCall_2() { return cSTRINGTerminalRuleCall_2; }
	}
	
	
	private GmlModelElements pGmlModel;
	private ElementElements pElement;
	private ValueElements pValue;
	private TerminalRule tGML_INT;
	private TerminalRule tGML_FLOAT;
	private TerminalRule tSTRING;
	private TerminalRule tID;
	private TerminalRule tPREC_LINE;
	private TerminalRule tWS;
	
	private final Grammar grammar;

	@Inject
	public GMLGrammarAccess(GrammarProvider grammarProvider) {
		this.grammar = internalFindGrammar(grammarProvider);
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("de.cau.cs.kieler.kiml.formats.gml.GML".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	
	public Grammar getGrammar() {
		return grammar;
	}
	

	
	//GmlModel:
	//
	//	elements+=Element*;
	public GmlModelElements getGmlModelAccess() {
		return (pGmlModel != null) ? pGmlModel : (pGmlModel = new GmlModelElements());
	}
	
	public ParserRule getGmlModelRule() {
		return getGmlModelAccess().getRule();
	}

	//Element:
	//
	//	key=ID (value=Value | "[" elements+=Element* "]");
	public ElementElements getElementAccess() {
		return (pElement != null) ? pElement : (pElement = new ElementElements());
	}
	
	public ParserRule getElementRule() {
		return getElementAccess().getRule();
	}

	//Value:
	//
	//	GML_INT | GML_FLOAT | STRING;
	public ValueElements getValueAccess() {
		return (pValue != null) ? pValue : (pValue = new ValueElements());
	}
	
	public ParserRule getValueRule() {
		return getValueAccess().getRule();
	}

	//terminal GML_INT:
	//
	//	("-" | "+")? "0".."9"+;
	public TerminalRule getGML_INTRule() {
		return (tGML_INT != null) ? tGML_INT : (tGML_INT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "GML_INT"));
	} 

	//terminal GML_FLOAT:
	//
	//	("-" | "+")? "0".."9"* "." "0".."9"* (("E" | "e") ("-" | "+")? "0".."9"+)?;
	public TerminalRule getGML_FLOATRule() {
		return (tGML_FLOAT != null) ? tGML_FLOAT : (tGML_FLOAT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "GML_FLOAT"));
	} 

	//terminal STRING:
	//
	//	"\"" ("&" ("a".."z" | "A".."Z" | "0".."9" | "#")+ ";" | !("&" | "\"") | "\\\"")* "\"";
	public TerminalRule getSTRINGRule() {
		return (tSTRING != null) ? tSTRING : (tSTRING = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "STRING"));
	} 

	//terminal ID:
	//
	//	("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return (tID != null) ? tID : (tID = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "ID"));
	} 

	//terminal PREC_LINE:
	//
	//	"#" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getPREC_LINERule() {
		return (tPREC_LINE != null) ? tPREC_LINE : (tPREC_LINE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "PREC_LINE"));
	} 

	//terminal WS:
	//
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return (tWS != null) ? tWS : (tWS = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "WS"));
	} 
}
