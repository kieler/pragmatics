/*
 * generated by Xtext
 */
package de.cau.cs.kieler.grana.text.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import de.cau.cs.kieler.grana.text.services.GranaGrammarAccess;

public class GranaParser extends AbstractContentAssistParser {
	
	@Inject
	private GranaGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected de.cau.cs.kieler.grana.text.ui.contentassist.antlr.internal.InternalGranaParser createParser() {
		de.cau.cs.kieler.grana.text.ui.contentassist.antlr.internal.InternalGranaParser result = new de.cau.cs.kieler.grana.text.ui.contentassist.antlr.internal.InternalGranaParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getGranaAccess().getAlternatives_2_2(), "rule__Grana__Alternatives_2_2");
					put(grammarAccess.getJobAccess().getAlternatives(), "rule__Job__Alternatives");
					put(grammarAccess.getRangeJobAccess().getAlternatives_12(), "rule__RangeJob__Alternatives_12");
					put(grammarAccess.getRangeAccess().getAlternatives(), "rule__Range__Alternatives");
					put(grammarAccess.getIntRangeAccess().getAlternatives(), "rule__IntRange__Alternatives");
					put(grammarAccess.getResourceAccess().getAlternatives(), "rule__Resource__Alternatives");
					put(grammarAccess.getOutputAccess().getAlternatives(), "rule__Output__Alternatives");
					put(grammarAccess.getRootNodeAccess().getAlternatives_3(), "rule__RootNode__Alternatives_3");
					put(grammarAccess.getElkNodeAccess().getAlternatives_2_3(), "rule__ElkNode__Alternatives_2_3");
					put(grammarAccess.getEdgeLayoutAccess().getAlternatives_2(), "rule__EdgeLayout__Alternatives_2");
					put(grammarAccess.getNumberAccess().getAlternatives(), "rule__Number__Alternatives");
					put(grammarAccess.getPropertyAccess().getAlternatives_2(), "rule__Property__Alternatives_2");
					put(grammarAccess.getNumberValueAccess().getAlternatives(), "rule__NumberValue__Alternatives");
					put(grammarAccess.getBooleanValueAccess().getAlternatives(), "rule__BooleanValue__Alternatives");
					put(grammarAccess.getOutputTypeAccess().getAlternatives(), "rule__OutputType__Alternatives");
					put(grammarAccess.getGranaAccess().getGroup(), "rule__Grana__Group__0");
					put(grammarAccess.getGranaAccess().getGroup_0(), "rule__Grana__Group_0__0");
					put(grammarAccess.getGranaAccess().getGroup_1(), "rule__Grana__Group_1__0");
					put(grammarAccess.getGranaAccess().getGroup_2(), "rule__Grana__Group_2__0");
					put(grammarAccess.getRegularJobAccess().getGroup(), "rule__RegularJob__Group__0");
					put(grammarAccess.getCompareJobAccess().getGroup(), "rule__CompareJob__Group__0");
					put(grammarAccess.getRangeJobAccess().getGroup(), "rule__RangeJob__Group__0");
					put(grammarAccess.getRangeJobAccess().getGroup_12_0(), "rule__RangeJob__Group_12_0__0");
					put(grammarAccess.getRangeJobAccess().getGroup_12_0_2(), "rule__RangeJob__Group_12_0_2__0");
					put(grammarAccess.getRangeJobAccess().getGroup_12_1(), "rule__RangeJob__Group_12_1__0");
					put(grammarAccess.getFloatRangeAccess().getGroup(), "rule__FloatRange__Group__0");
					put(grammarAccess.getFloatRangeAccess().getGroup_2(), "rule__FloatRange__Group_2__0");
					put(grammarAccess.getIntRangeValuesAccess().getGroup(), "rule__IntRangeValues__Group__0");
					put(grammarAccess.getIntRangeValuesAccess().getGroup_2(), "rule__IntRangeValues__Group_2__0");
					put(grammarAccess.getIntRangeRangeAccess().getGroup(), "rule__IntRangeRange__Group__0");
					put(grammarAccess.getResourceReferenceAccess().getGroup(), "rule__ResourceReference__Group__0");
					put(grammarAccess.getGlobalResourceRefAccess().getGroup(), "rule__GlobalResourceRef__Group__0");
					put(grammarAccess.getLocalResourceAccess().getGroup(), "rule__LocalResource__Group__0");
					put(grammarAccess.getLocalResourceAccess().getGroup_1(), "rule__LocalResource__Group_1__0");
					put(grammarAccess.getGlobalOutputRefAccess().getGroup(), "rule__GlobalOutputRef__Group__0");
					put(grammarAccess.getOutputReferenceAccess().getGroup(), "rule__OutputReference__Group__0");
					put(grammarAccess.getLayoutConfigAccess().getGroup(), "rule__LayoutConfig__Group__0");
					put(grammarAccess.getRootNodeAccess().getGroup(), "rule__RootNode__Group__0");
					put(grammarAccess.getRootNodeAccess().getGroup_1(), "rule__RootNode__Group_1__0");
					put(grammarAccess.getElkNodeAccess().getGroup(), "rule__ElkNode__Group__0");
					put(grammarAccess.getElkNodeAccess().getGroup_2(), "rule__ElkNode__Group_2__0");
					put(grammarAccess.getElkLabelAccess().getGroup(), "rule__ElkLabel__Group__0");
					put(grammarAccess.getElkLabelAccess().getGroup_1(), "rule__ElkLabel__Group_1__0");
					put(grammarAccess.getElkLabelAccess().getGroup_3(), "rule__ElkLabel__Group_3__0");
					put(grammarAccess.getElkPortAccess().getGroup(), "rule__ElkPort__Group__0");
					put(grammarAccess.getElkPortAccess().getGroup_2(), "rule__ElkPort__Group_2__0");
					put(grammarAccess.getShapeLayoutAccess().getGroup(), "rule__ShapeLayout__Group__0");
					put(grammarAccess.getShapeLayoutAccess().getGroup_2_0(), "rule__ShapeLayout__Group_2_0__0");
					put(grammarAccess.getShapeLayoutAccess().getGroup_2_1(), "rule__ShapeLayout__Group_2_1__0");
					put(grammarAccess.getElkEdgeAccess().getGroup(), "rule__ElkEdge__Group__0");
					put(grammarAccess.getElkEdgeAccess().getGroup_1(), "rule__ElkEdge__Group_1__0");
					put(grammarAccess.getElkEdgeAccess().getGroup_3(), "rule__ElkEdge__Group_3__0");
					put(grammarAccess.getElkEdgeAccess().getGroup_6(), "rule__ElkEdge__Group_6__0");
					put(grammarAccess.getElkEdgeAccess().getGroup_7(), "rule__ElkEdge__Group_7__0");
					put(grammarAccess.getEdgeLayoutAccess().getGroup(), "rule__EdgeLayout__Group__0");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getGroup(), "rule__ElkSingleEdgeSection__Group__0");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1(), "rule__ElkSingleEdgeSection__Group_1__0");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_0(), "rule__ElkSingleEdgeSection__Group_1_0_0__0");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_1(), "rule__ElkSingleEdgeSection__Group_1_0_1__0");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_2(), "rule__ElkSingleEdgeSection__Group_1_0_2__0");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_3(), "rule__ElkSingleEdgeSection__Group_1_0_3__0");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1(), "rule__ElkSingleEdgeSection__Group_1_1__0");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1_3(), "rule__ElkSingleEdgeSection__Group_1_1_3__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup(), "rule__ElkEdgeSection__Group__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_2(), "rule__ElkEdgeSection__Group_2__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2(), "rule__ElkEdgeSection__Group_2_2__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_4(), "rule__ElkEdgeSection__Group_4__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_0(), "rule__ElkEdgeSection__Group_4_0_0__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_1(), "rule__ElkEdgeSection__Group_4_0_1__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_2(), "rule__ElkEdgeSection__Group_4_0_2__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_3(), "rule__ElkEdgeSection__Group_4_0_3__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1(), "rule__ElkEdgeSection__Group_4_1__0");
					put(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1_3(), "rule__ElkEdgeSection__Group_4_1_3__0");
					put(grammarAccess.getElkBendPointAccess().getGroup(), "rule__ElkBendPoint__Group__0");
					put(grammarAccess.getQualifiedIdAccess().getGroup(), "rule__QualifiedId__Group__0");
					put(grammarAccess.getQualifiedIdAccess().getGroup_1(), "rule__QualifiedId__Group_1__0");
					put(grammarAccess.getPropertyAccess().getGroup(), "rule__Property__Group__0");
					put(grammarAccess.getPropertyKeyAccess().getGroup(), "rule__PropertyKey__Group__0");
					put(grammarAccess.getPropertyKeyAccess().getGroup_1(), "rule__PropertyKey__Group_1__0");
					put(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1(), "rule__Grana__GlobalResourcesAssignment_0_1");
					put(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1(), "rule__Grana__GloobalOutputsAssignment_1_1");
					put(grammarAccess.getGranaAccess().getParallelAssignment_2_1(), "rule__Grana__ParallelAssignment_2_1");
					put(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_2_0(), "rule__Grana__ExecuteAllAssignment_2_2_0");
					put(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1(), "rule__Grana__ExecuteAssignment_2_2_1");
					put(grammarAccess.getGranaAccess().getJobsAssignment_3(), "rule__Grana__JobsAssignment_3");
					put(grammarAccess.getRegularJobAccess().getNameAssignment_1(), "rule__RegularJob__NameAssignment_1");
					put(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisAssignment_2(), "rule__RegularJob__LayoutBeforeAnalysisAssignment_2");
					put(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeAssignment_3(), "rule__RegularJob__MeasureExecutionTimeAssignment_3");
					put(grammarAccess.getRegularJobAccess().getResourcesAssignment_5(), "rule__RegularJob__ResourcesAssignment_5");
					put(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7(), "rule__RegularJob__LayoutOptionsAssignment_7");
					put(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9(), "rule__RegularJob__AnalysesAssignment_9");
					put(grammarAccess.getRegularJobAccess().getOutputTypeAssignment_11(), "rule__RegularJob__OutputTypeAssignment_11");
					put(grammarAccess.getRegularJobAccess().getOutputAssignment_12(), "rule__RegularJob__OutputAssignment_12");
					put(grammarAccess.getCompareJobAccess().getNameAssignment_1(), "rule__CompareJob__NameAssignment_1");
					put(grammarAccess.getCompareJobAccess().getResourcesAssignment_3(), "rule__CompareJob__ResourcesAssignment_3");
					put(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_5(), "rule__CompareJob__LayoutOptionsAssignment_5");
					put(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_6(), "rule__CompareJob__LayoutOptionsAssignment_6");
					put(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8(), "rule__CompareJob__AnalysesAssignment_8");
					put(grammarAccess.getCompareJobAccess().getOutputTypeAssignment_10(), "rule__CompareJob__OutputTypeAssignment_10");
					put(grammarAccess.getCompareJobAccess().getOutputAssignment_11(), "rule__CompareJob__OutputAssignment_11");
					put(grammarAccess.getRangeJobAccess().getNameAssignment_1(), "rule__RangeJob__NameAssignment_1");
					put(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeAssignment_2(), "rule__RangeJob__MeasureExecutionTimeAssignment_2");
					put(grammarAccess.getRangeJobAccess().getResourcesAssignment_4(), "rule__RangeJob__ResourcesAssignment_4");
					put(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6(), "rule__RangeJob__LayoutOptionsAssignment_6");
					put(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8(), "rule__RangeJob__AnalysesAssignment_8");
					put(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_10(), "rule__RangeJob__RangeOptionAssignment_10");
					put(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_11(), "rule__RangeJob__RangeValuesAssignment_11");
					put(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12_0_1(), "rule__RangeJob__RangeAnalysisAssignment_12_0_1");
					put(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_12_0_2_1(), "rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1");
					put(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1(), "rule__RangeJob__RangeAnalysesAssignment_12_1_1");
					put(grammarAccess.getRangeJobAccess().getOutputTypeAssignment_14(), "rule__RangeJob__OutputTypeAssignment_14");
					put(grammarAccess.getRangeJobAccess().getOutputAssignment_15(), "rule__RangeJob__OutputAssignment_15");
					put(grammarAccess.getFloatRangeAccess().getValuesAssignment_1(), "rule__FloatRange__ValuesAssignment_1");
					put(grammarAccess.getFloatRangeAccess().getValuesAssignment_2_1(), "rule__FloatRange__ValuesAssignment_2_1");
					put(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_1(), "rule__IntRangeValues__ValuesAssignment_1");
					put(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_2_1(), "rule__IntRangeValues__ValuesAssignment_2_1");
					put(grammarAccess.getIntRangeRangeAccess().getStartAssignment_1(), "rule__IntRangeRange__StartAssignment_1");
					put(grammarAccess.getIntRangeRangeAccess().getEndAssignment_3(), "rule__IntRangeRange__EndAssignment_3");
					put(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1(), "rule__ResourceReference__ResourceRefsAssignment_1");
					put(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0(), "rule__GlobalResourceRef__NameAssignment_0");
					put(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1(), "rule__GlobalResourceRef__ResourcesAssignment_1");
					put(grammarAccess.getLocalResourceAccess().getPathAssignment_0(), "rule__LocalResource__PathAssignment_0");
					put(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1(), "rule__LocalResource__FilterAssignment_1_1");
					put(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0(), "rule__GlobalOutputRef__NameAssignment_0");
					put(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1(), "rule__GlobalOutputRef__OutputAssignment_1");
					put(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1(), "rule__OutputReference__OutputRefAssignment_1");
					put(grammarAccess.getLocalOutputAccess().getPathAssignment(), "rule__LocalOutput__PathAssignment");
					put(grammarAccess.getAnalysisAccess().getNameAssignment(), "rule__Analysis__NameAssignment");
					put(grammarAccess.getLayoutConfigAccess().getIdentifierAssignment_0(), "rule__LayoutConfig__IdentifierAssignment_0");
					put(grammarAccess.getLayoutConfigAccess().getPropertiesAssignment_2(), "rule__LayoutConfig__PropertiesAssignment_2");
					put(grammarAccess.getRootNodeAccess().getIdentifierAssignment_1_1(), "rule__RootNode__IdentifierAssignment_1_1");
					put(grammarAccess.getRootNodeAccess().getPropertiesAssignment_2(), "rule__RootNode__PropertiesAssignment_2");
					put(grammarAccess.getRootNodeAccess().getLabelsAssignment_3_0(), "rule__RootNode__LabelsAssignment_3_0");
					put(grammarAccess.getRootNodeAccess().getPortsAssignment_3_1(), "rule__RootNode__PortsAssignment_3_1");
					put(grammarAccess.getRootNodeAccess().getChildrenAssignment_3_2(), "rule__RootNode__ChildrenAssignment_3_2");
					put(grammarAccess.getRootNodeAccess().getContainedEdgesAssignment_3_3(), "rule__RootNode__ContainedEdgesAssignment_3_3");
					put(grammarAccess.getElkNodeAccess().getIdentifierAssignment_1(), "rule__ElkNode__IdentifierAssignment_1");
					put(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2_2(), "rule__ElkNode__PropertiesAssignment_2_2");
					put(grammarAccess.getElkNodeAccess().getLabelsAssignment_2_3_0(), "rule__ElkNode__LabelsAssignment_2_3_0");
					put(grammarAccess.getElkNodeAccess().getPortsAssignment_2_3_1(), "rule__ElkNode__PortsAssignment_2_3_1");
					put(grammarAccess.getElkNodeAccess().getChildrenAssignment_2_3_2(), "rule__ElkNode__ChildrenAssignment_2_3_2");
					put(grammarAccess.getElkNodeAccess().getContainedEdgesAssignment_2_3_3(), "rule__ElkNode__ContainedEdgesAssignment_2_3_3");
					put(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0(), "rule__ElkLabel__IdentifierAssignment_1_0");
					put(grammarAccess.getElkLabelAccess().getTextAssignment_2(), "rule__ElkLabel__TextAssignment_2");
					put(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2(), "rule__ElkLabel__PropertiesAssignment_3_2");
					put(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3(), "rule__ElkLabel__LabelsAssignment_3_3");
					put(grammarAccess.getElkPortAccess().getIdentifierAssignment_1(), "rule__ElkPort__IdentifierAssignment_1");
					put(grammarAccess.getElkPortAccess().getPropertiesAssignment_2_2(), "rule__ElkPort__PropertiesAssignment_2_2");
					put(grammarAccess.getElkPortAccess().getLabelsAssignment_2_3(), "rule__ElkPort__LabelsAssignment_2_3");
					put(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2(), "rule__ShapeLayout__XAssignment_2_0_2");
					put(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4(), "rule__ShapeLayout__YAssignment_2_0_4");
					put(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2(), "rule__ShapeLayout__WidthAssignment_2_1_2");
					put(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4(), "rule__ShapeLayout__HeightAssignment_2_1_4");
					put(grammarAccess.getElkEdgeAccess().getIdentifierAssignment_1_0(), "rule__ElkEdge__IdentifierAssignment_1_0");
					put(grammarAccess.getElkEdgeAccess().getSourcesAssignment_2(), "rule__ElkEdge__SourcesAssignment_2");
					put(grammarAccess.getElkEdgeAccess().getSourcesAssignment_3_1(), "rule__ElkEdge__SourcesAssignment_3_1");
					put(grammarAccess.getElkEdgeAccess().getTargetsAssignment_5(), "rule__ElkEdge__TargetsAssignment_5");
					put(grammarAccess.getElkEdgeAccess().getTargetsAssignment_6_1(), "rule__ElkEdge__TargetsAssignment_6_1");
					put(grammarAccess.getElkEdgeAccess().getPropertiesAssignment_7_2(), "rule__ElkEdge__PropertiesAssignment_7_2");
					put(grammarAccess.getElkEdgeAccess().getLabelsAssignment_7_3(), "rule__ElkEdge__LabelsAssignment_7_3");
					put(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_0(), "rule__EdgeLayout__SectionsAssignment_2_0");
					put(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1(), "rule__EdgeLayout__SectionsAssignment_2_1");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_0_2(), "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_0_1_2(), "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_0_2_2(), "rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_0_2_4(), "rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_0_3_2(), "rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_0_3_4(), "rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_2(), "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_3_1(), "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesAssignment_1_2(), "rule__ElkSingleEdgeSection__PropertiesAssignment_1_2");
					put(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1(), "rule__ElkEdgeSection__IdentifierAssignment_1");
					put(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1(), "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1");
					put(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1(), "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1");
					put(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_0_2(), "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2");
					put(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_0_1_2(), "rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2");
					put(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_0_2_2(), "rule__ElkEdgeSection__StartXAssignment_4_0_2_2");
					put(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_0_2_4(), "rule__ElkEdgeSection__StartYAssignment_4_0_2_4");
					put(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_0_3_2(), "rule__ElkEdgeSection__EndXAssignment_4_0_3_2");
					put(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_0_3_4(), "rule__ElkEdgeSection__EndYAssignment_4_0_3_4");
					put(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_2(), "rule__ElkEdgeSection__BendPointsAssignment_4_1_2");
					put(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_3_1(), "rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1");
					put(grammarAccess.getElkEdgeSectionAccess().getPropertiesAssignment_4_2(), "rule__ElkEdgeSection__PropertiesAssignment_4_2");
					put(grammarAccess.getElkBendPointAccess().getXAssignment_0(), "rule__ElkBendPoint__XAssignment_0");
					put(grammarAccess.getElkBendPointAccess().getYAssignment_2(), "rule__ElkBendPoint__YAssignment_2");
					put(grammarAccess.getPropertyAccess().getKeyAssignment_0(), "rule__Property__KeyAssignment_0");
					put(grammarAccess.getPropertyAccess().getValueAssignment_2_0(), "rule__Property__ValueAssignment_2_0");
					put(grammarAccess.getPropertyAccess().getValueAssignment_2_1(), "rule__Property__ValueAssignment_2_1");
					put(grammarAccess.getPropertyAccess().getValueAssignment_2_2(), "rule__Property__ValueAssignment_2_2");
					put(grammarAccess.getPropertyAccess().getValueAssignment_2_3(), "rule__Property__ValueAssignment_2_3");
					put(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), "rule__ShapeLayout__UnorderedGroup_2");
					put(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), "rule__ElkSingleEdgeSection__UnorderedGroup_1_0");
					put(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), "rule__ElkEdgeSection__UnorderedGroup_4_0");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			de.cau.cs.kieler.grana.text.ui.contentassist.antlr.internal.InternalGranaParser typedParser = (de.cau.cs.kieler.grana.text.ui.contentassist.antlr.internal.InternalGranaParser) parser;
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