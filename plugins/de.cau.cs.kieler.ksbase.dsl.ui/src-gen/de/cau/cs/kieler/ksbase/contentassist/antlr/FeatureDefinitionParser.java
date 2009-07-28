/*
* generated by Xtext
*/
package de.cau.cs.kieler.ksbase.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.common.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import de.cau.cs.kieler.ksbase.services.FeatureDefinitionGrammarAccess;

public class FeatureDefinitionParser extends AbstractContentAssistParser {
	
	@Inject
	private FeatureDefinitionGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected de.cau.cs.kieler.ksbase.contentassist.antlr.internal.InternalFeatureDefinitionLexer createLexer(CharStream stream) {
		return new de.cau.cs.kieler.ksbase.contentassist.antlr.internal.InternalFeatureDefinitionLexer(stream);
	}
	
	@Override
	protected de.cau.cs.kieler.ksbase.contentassist.antlr.internal.InternalFeatureDefinitionParser createParser() {
		de.cau.cs.kieler.ksbase.contentassist.antlr.internal.InternalFeatureDefinitionParser result = new de.cau.cs.kieler.ksbase.contentassist.antlr.internal.InternalFeatureDefinitionParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				{
					put(grammarAccess.getFeatureDefinitionsAccess().getGroup(), "rule__FeatureDefinitions__Group__0");
					put(grammarAccess.getFeatureDefinitionsAccess().getGroup_3(), "rule__FeatureDefinitions__Group_3__0");
					put(grammarAccess.getFeatureDefinitionsAccess().getGroup_4(), "rule__FeatureDefinitions__Group_4__0");
					put(grammarAccess.getFeatureDefinitionsAccess().getGroup_8(), "rule__FeatureDefinitions__Group_8__0");
					put(grammarAccess.getFeatureTypeAccess().getGroup(), "rule__FeatureType__Group__0");
					put(grammarAccess.getFeatureTypeAccess().getGroup_3(), "rule__FeatureType__Group_3__0");
					put(grammarAccess.getFeatureTypeAccess().getGroup_7(), "rule__FeatureType__Group_7__0");
					put(grammarAccess.getFeatureDefinitionsAccess().getModelNameAssignment_1(), "rule__FeatureDefinitions__ModelNameAssignment_1");
					put(grammarAccess.getFeatureDefinitionsAccess().getModelPathAssignment_3_1(), "rule__FeatureDefinitions__ModelPathAssignment_3_1");
					put(grammarAccess.getFeatureDefinitionsAccess().getDiagramPackageAssignment_4_1(), "rule__FeatureDefinitions__DiagramPackageAssignment_4_1");
					put(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleAssignment_6(), "rule__FeatureDefinitions__FeatureMenuTitleAssignment_6");
					put(grammarAccess.getFeatureDefinitionsAccess().getFeatureFileAssignment_8_1(), "rule__FeatureDefinitions__FeatureFileAssignment_8_1");
					put(grammarAccess.getFeatureDefinitionsAccess().getElementsAssignment_9(), "rule__FeatureDefinitions__ElementsAssignment_9");
					put(grammarAccess.getFeatureTypeAccess().getNameAssignment_1(), "rule__FeatureType__NameAssignment_1");
					put(grammarAccess.getFeatureTypeAccess().getFileNameAssignment_3_1(), "rule__FeatureType__FileNameAssignment_3_1");
					put(grammarAccess.getFeatureTypeAccess().getMethodNameAssignment_5(), "rule__FeatureType__MethodNameAssignment_5");
					put(grammarAccess.getFeatureTypeAccess().getParameterAssignment_7_1(), "rule__FeatureType__ParameterAssignment_7_1");
					put(grammarAccess.getFeatureTypeAccess().getMenuEntryAssignment_9(), "rule__FeatureType__MenuEntryAssignment_9");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			de.cau.cs.kieler.ksbase.contentassist.antlr.internal.InternalFeatureDefinitionParser typedParser = (de.cau.cs.kieler.ksbase.contentassist.antlr.internal.InternalFeatureDefinitionParser) parser;
			typedParser.entryRuleFeatureDefinitions();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public FeatureDefinitionGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(FeatureDefinitionGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
