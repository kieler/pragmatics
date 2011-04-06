/*
* generated by Xtext
*/
package de.cau.cs.kieler.kaom.text.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import de.cau.cs.kieler.kaom.text.services.KaomGrammarAccess;

public class KaomParser extends AbstractContentAssistParser {
	
	@Inject
	private KaomGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected de.cau.cs.kieler.kaom.text.ui.contentassist.antlr.internal.InternalKaomParser createParser() {
		de.cau.cs.kieler.kaom.text.ui.contentassist.antlr.internal.InternalKaomParser result = new de.cau.cs.kieler.kaom.text.ui.contentassist.antlr.internal.InternalKaomParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getTopLevelEntityAccess().getAlternatives_2_4(), "rule__TopLevelEntity__Alternatives_2_4");
					put(grammarAccess.getTopLevelEntityAccess().getAlternatives_2_4_0_1(), "rule__TopLevelEntity__Alternatives_2_4_0_1");
					put(grammarAccess.getEntityAccess().getAlternatives_5(), "rule__Entity__Alternatives_5");
					put(grammarAccess.getEntityAccess().getAlternatives_5_0_1(), "rule__Entity__Alternatives_5_0_1");
					put(grammarAccess.getLinkableAccess().getAlternatives(), "rule__Linkable__Alternatives");
					put(grammarAccess.getAnnotationAccess().getAlternatives(), "rule__Annotation__Alternatives");
					put(grammarAccess.getEStringAccess().getAlternatives(), "rule__EString__Alternatives");
					put(grammarAccess.getTopLevelEntityAccess().getGroup(), "rule__TopLevelEntity__Group__0");
					put(grammarAccess.getTopLevelEntityAccess().getGroup_2(), "rule__TopLevelEntity__Group_2__0");
					put(grammarAccess.getTopLevelEntityAccess().getGroup_2_4_0(), "rule__TopLevelEntity__Group_2_4_0__0");
					put(grammarAccess.getEntityAccess().getGroup(), "rule__Entity__Group__0");
					put(grammarAccess.getEntityAccess().getGroup_5_0(), "rule__Entity__Group_5_0__0");
					put(grammarAccess.getLinkAccess().getGroup(), "rule__Link__Group__0");
					put(grammarAccess.getPortAccess().getGroup(), "rule__Port__Group__0");
					put(grammarAccess.getRelationAccess().getGroup(), "rule__Relation__Group__0");
					put(grammarAccess.getTagAnnotationAccess().getGroup(), "rule__TagAnnotation__Group__0");
					put(grammarAccess.getTagAnnotationAccess().getGroup_2(), "rule__TagAnnotation__Group_2__0");
					put(grammarAccess.getKeyStringValueAnnotationAccess().getGroup(), "rule__KeyStringValueAnnotation__Group__0");
					put(grammarAccess.getKeyStringValueAnnotationAccess().getGroup_3(), "rule__KeyStringValueAnnotation__Group_3__0");
					put(grammarAccess.getTypedStringAnnotationAccess().getGroup(), "rule__TypedStringAnnotation__Group__0");
					put(grammarAccess.getTypedStringAnnotationAccess().getGroup_4(), "rule__TypedStringAnnotation__Group_4__0");
					put(grammarAccess.getKeyBooleanValueAnnotationAccess().getGroup(), "rule__KeyBooleanValueAnnotation__Group__0");
					put(grammarAccess.getKeyBooleanValueAnnotationAccess().getGroup_3(), "rule__KeyBooleanValueAnnotation__Group_3__0");
					put(grammarAccess.getKeyIntValueAnnotationAccess().getGroup(), "rule__KeyIntValueAnnotation__Group__0");
					put(grammarAccess.getKeyIntValueAnnotationAccess().getGroup_3(), "rule__KeyIntValueAnnotation__Group_3__0");
					put(grammarAccess.getKeyFloatValueAnnotationAccess().getGroup(), "rule__KeyFloatValueAnnotation__Group__0");
					put(grammarAccess.getKeyFloatValueAnnotationAccess().getGroup_3(), "rule__KeyFloatValueAnnotation__Group_3__0");
					put(grammarAccess.getImportAnnotationAccess().getGroup(), "rule__ImportAnnotation__Group__0");
					put(grammarAccess.getTopLevelEntityAccess().getAnnotationsAssignment_1(), "rule__TopLevelEntity__AnnotationsAssignment_1");
					put(grammarAccess.getTopLevelEntityAccess().getAnnotationsAssignment_2_0(), "rule__TopLevelEntity__AnnotationsAssignment_2_0");
					put(grammarAccess.getTopLevelEntityAccess().getIdAssignment_2_2(), "rule__TopLevelEntity__IdAssignment_2_2");
					put(grammarAccess.getTopLevelEntityAccess().getNameAssignment_2_3(), "rule__TopLevelEntity__NameAssignment_2_3");
					put(grammarAccess.getTopLevelEntityAccess().getChildEntitiesAssignment_2_4_0_1_0(), "rule__TopLevelEntity__ChildEntitiesAssignment_2_4_0_1_0");
					put(grammarAccess.getTopLevelEntityAccess().getChildLinksAssignment_2_4_0_1_1(), "rule__TopLevelEntity__ChildLinksAssignment_2_4_0_1_1");
					put(grammarAccess.getTopLevelEntityAccess().getChildPortsAssignment_2_4_0_1_2(), "rule__TopLevelEntity__ChildPortsAssignment_2_4_0_1_2");
					put(grammarAccess.getTopLevelEntityAccess().getChildRelationsAssignment_2_4_0_1_3(), "rule__TopLevelEntity__ChildRelationsAssignment_2_4_0_1_3");
					put(grammarAccess.getEntityAccess().getAnnotationsAssignment_1(), "rule__Entity__AnnotationsAssignment_1");
					put(grammarAccess.getEntityAccess().getIdAssignment_3(), "rule__Entity__IdAssignment_3");
					put(grammarAccess.getEntityAccess().getNameAssignment_4(), "rule__Entity__NameAssignment_4");
					put(grammarAccess.getEntityAccess().getChildEntitiesAssignment_5_0_1_0(), "rule__Entity__ChildEntitiesAssignment_5_0_1_0");
					put(grammarAccess.getEntityAccess().getChildLinksAssignment_5_0_1_1(), "rule__Entity__ChildLinksAssignment_5_0_1_1");
					put(grammarAccess.getEntityAccess().getChildPortsAssignment_5_0_1_2(), "rule__Entity__ChildPortsAssignment_5_0_1_2");
					put(grammarAccess.getEntityAccess().getChildRelationsAssignment_5_0_1_3(), "rule__Entity__ChildRelationsAssignment_5_0_1_3");
					put(grammarAccess.getLinkAccess().getAnnotationsAssignment_0(), "rule__Link__AnnotationsAssignment_0");
					put(grammarAccess.getLinkAccess().getNameAssignment_2(), "rule__Link__NameAssignment_2");
					put(grammarAccess.getLinkAccess().getSourceAssignment_3(), "rule__Link__SourceAssignment_3");
					put(grammarAccess.getLinkAccess().getTargetAssignment_5(), "rule__Link__TargetAssignment_5");
					put(grammarAccess.getPortAccess().getAnnotationsAssignment_1(), "rule__Port__AnnotationsAssignment_1");
					put(grammarAccess.getPortAccess().getIdAssignment_3(), "rule__Port__IdAssignment_3");
					put(grammarAccess.getPortAccess().getNameAssignment_4(), "rule__Port__NameAssignment_4");
					put(grammarAccess.getRelationAccess().getAnnotationsAssignment_1(), "rule__Relation__AnnotationsAssignment_1");
					put(grammarAccess.getRelationAccess().getIdAssignment_3(), "rule__Relation__IdAssignment_3");
					put(grammarAccess.getRelationAccess().getNameAssignment_4(), "rule__Relation__NameAssignment_4");
					put(grammarAccess.getCommentAnnotationAccess().getValueAssignment(), "rule__CommentAnnotation__ValueAssignment");
					put(grammarAccess.getTagAnnotationAccess().getNameAssignment_1(), "rule__TagAnnotation__NameAssignment_1");
					put(grammarAccess.getTagAnnotationAccess().getAnnotationsAssignment_2_1(), "rule__TagAnnotation__AnnotationsAssignment_2_1");
					put(grammarAccess.getKeyStringValueAnnotationAccess().getNameAssignment_1(), "rule__KeyStringValueAnnotation__NameAssignment_1");
					put(grammarAccess.getKeyStringValueAnnotationAccess().getValueAssignment_2(), "rule__KeyStringValueAnnotation__ValueAssignment_2");
					put(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAssignment_3_1(), "rule__KeyStringValueAnnotation__AnnotationsAssignment_3_1");
					put(grammarAccess.getTypedStringAnnotationAccess().getNameAssignment_1(), "rule__TypedStringAnnotation__NameAssignment_1");
					put(grammarAccess.getTypedStringAnnotationAccess().getTypeAssignment_2(), "rule__TypedStringAnnotation__TypeAssignment_2");
					put(grammarAccess.getTypedStringAnnotationAccess().getValueAssignment_3(), "rule__TypedStringAnnotation__ValueAssignment_3");
					put(grammarAccess.getTypedStringAnnotationAccess().getAnnotationsAssignment_4_1(), "rule__TypedStringAnnotation__AnnotationsAssignment_4_1");
					put(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameAssignment_1(), "rule__KeyBooleanValueAnnotation__NameAssignment_1");
					put(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueAssignment_2(), "rule__KeyBooleanValueAnnotation__ValueAssignment_2");
					put(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAssignment_3_1(), "rule__KeyBooleanValueAnnotation__AnnotationsAssignment_3_1");
					put(grammarAccess.getKeyIntValueAnnotationAccess().getNameAssignment_1(), "rule__KeyIntValueAnnotation__NameAssignment_1");
					put(grammarAccess.getKeyIntValueAnnotationAccess().getValueAssignment_2(), "rule__KeyIntValueAnnotation__ValueAssignment_2");
					put(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAssignment_3_1(), "rule__KeyIntValueAnnotation__AnnotationsAssignment_3_1");
					put(grammarAccess.getKeyFloatValueAnnotationAccess().getNameAssignment_1(), "rule__KeyFloatValueAnnotation__NameAssignment_1");
					put(grammarAccess.getKeyFloatValueAnnotationAccess().getValueAssignment_2(), "rule__KeyFloatValueAnnotation__ValueAssignment_2");
					put(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAssignment_3_1(), "rule__KeyFloatValueAnnotation__AnnotationsAssignment_3_1");
					put(grammarAccess.getImportAnnotationAccess().getImportURIAssignment_1(), "rule__ImportAnnotation__ImportURIAssignment_1");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			de.cau.cs.kieler.kaom.text.ui.contentassist.antlr.internal.InternalKaomParser typedParser = (de.cau.cs.kieler.kaom.text.ui.contentassist.antlr.internal.InternalKaomParser) parser;
			typedParser.entryRuleTopLevelEntity();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public KaomGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(KaomGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
