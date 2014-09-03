/*
* generated by Xtext
*/
package de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess;

public class GranaParser extends AbstractContentAssistParser {
	
	@Inject
	private GranaGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal.InternalGranaParser createParser() {
		de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal.InternalGranaParser result = new de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal.InternalGranaParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getResourceAccess().getAlternatives(), "rule__Resource__Alternatives");
					put(grammarAccess.getPropertyValueAccess().getAlternatives(), "rule__PropertyValue__Alternatives");
					put(grammarAccess.getFloatAccess().getAlternatives(), "rule__Float__Alternatives");
					put(grammarAccess.getGranaAccess().getGroup(), "rule__Grana__Group__0");
					put(grammarAccess.getGranaAccess().getGroup_0(), "rule__Grana__Group_0__0");
					put(grammarAccess.getJobAccess().getGroup(), "rule__Job__Group__0");
					put(grammarAccess.getResourceReferenceAccess().getGroup(), "rule__ResourceReference__Group__0");
					put(grammarAccess.getGlobalResourceRefAccess().getGroup(), "rule__GlobalResourceRef__Group__0");
					put(grammarAccess.getLocalResourceAccess().getGroup(), "rule__LocalResource__Group__0");
					put(grammarAccess.getLocalResourceAccess().getGroup_1(), "rule__LocalResource__Group_1__0");
					put(grammarAccess.getKIdentifierAccess().getGroup(), "rule__KIdentifier__Group__0");
					put(grammarAccess.getKIdentifierAccess().getGroup_3(), "rule__KIdentifier__Group_3__0");
					put(grammarAccess.getPersistentEntryAccess().getGroup(), "rule__PersistentEntry__Group__0");
					put(grammarAccess.getQualifiedIDAccess().getGroup(), "rule__QualifiedID__Group__0");
					put(grammarAccess.getQualifiedIDAccess().getGroup_1(), "rule__QualifiedID__Group_1__0");
					put(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1(), "rule__Grana__GlobalResourcesAssignment_0_1");
					put(grammarAccess.getGranaAccess().getJobsAssignment_1(), "rule__Grana__JobsAssignment_1");
					put(grammarAccess.getJobAccess().getNameAssignment_2(), "rule__Job__NameAssignment_2");
					put(grammarAccess.getJobAccess().getLayoutBeforeAnalysisAssignment_3(), "rule__Job__LayoutBeforeAnalysisAssignment_3");
					put(grammarAccess.getJobAccess().getResourcesAssignment_5(), "rule__Job__ResourcesAssignment_5");
					put(grammarAccess.getJobAccess().getLayoutOptionsAssignment_7(), "rule__Job__LayoutOptionsAssignment_7");
					put(grammarAccess.getJobAccess().getAnalysesAssignment_9(), "rule__Job__AnalysesAssignment_9");
					put(grammarAccess.getJobAccess().getOutputAssignment_11(), "rule__Job__OutputAssignment_11");
					put(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1(), "rule__ResourceReference__ResourceRefsAssignment_1");
					put(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0(), "rule__GlobalResourceRef__NameAssignment_0");
					put(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1(), "rule__GlobalResourceRef__ResourcesAssignment_1");
					put(grammarAccess.getLocalResourceAccess().getPathAssignment_0(), "rule__LocalResource__PathAssignment_0");
					put(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1(), "rule__LocalResource__FilterAssignment_1_1");
					put(grammarAccess.getAnalysisAccess().getNameAssignment(), "rule__Analysis__NameAssignment");
					put(grammarAccess.getKIdentifierAccess().getIdAssignment_1(), "rule__KIdentifier__IdAssignment_1");
					put(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0(), "rule__KIdentifier__PersistentEntriesAssignment_3_0");
					put(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1(), "rule__KIdentifier__PersistentEntriesAssignment_3_1");
					put(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0(), "rule__PersistentEntry__KeyAssignment_0");
					put(grammarAccess.getPersistentEntryAccess().getValueAssignment_2(), "rule__PersistentEntry__ValueAssignment_2");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal.InternalGranaParser typedParser = (de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal.InternalGranaParser) parser;
			typedParser.entryRuleGrana();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public GranaGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(GranaGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
