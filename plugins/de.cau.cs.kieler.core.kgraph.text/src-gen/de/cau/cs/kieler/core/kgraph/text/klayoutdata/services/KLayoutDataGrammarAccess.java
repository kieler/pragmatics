/*
* generated by Xtext
*/

package de.cau.cs.kieler.core.kgraph.text.klayoutdata.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import java.util.List;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class KLayoutDataGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class KShapeLayoutElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "KShapeLayout");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cKShapeLayoutAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cKShapeLayoutKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cLeftCurlyBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cXposKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cXposAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cXposEFloatParserRuleCall_3_1_0 = (RuleCall)cXposAssignment_3_1.eContents().get(0);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cYposKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cYposAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cYposEFloatParserRuleCall_4_1_0 = (RuleCall)cYposAssignment_4_1.eContents().get(0);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cWidthKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Assignment cWidthAssignment_5_1 = (Assignment)cGroup_5.eContents().get(1);
		private final RuleCall cWidthEFloatParserRuleCall_5_1_0 = (RuleCall)cWidthAssignment_5_1.eContents().get(0);
		private final Group cGroup_6 = (Group)cGroup.eContents().get(6);
		private final Keyword cHeightKeyword_6_0 = (Keyword)cGroup_6.eContents().get(0);
		private final Assignment cHeightAssignment_6_1 = (Assignment)cGroup_6.eContents().get(1);
		private final RuleCall cHeightEFloatParserRuleCall_6_1_0 = (RuleCall)cHeightAssignment_6_1.eContents().get(0);
		private final Group cGroup_7 = (Group)cGroup.eContents().get(7);
		private final Keyword cInsetsKeyword_7_0 = (Keyword)cGroup_7.eContents().get(0);
		private final Assignment cInsetsAssignment_7_1 = (Assignment)cGroup_7.eContents().get(1);
		private final RuleCall cInsetsKInsetsParserRuleCall_7_1_0 = (RuleCall)cInsetsAssignment_7_1.eContents().get(0);
		private final Group cGroup_8 = (Group)cGroup.eContents().get(8);
		private final Keyword cMapPropertiesKeyword_8_0 = (Keyword)cGroup_8.eContents().get(0);
		private final Keyword cColonKeyword_8_1 = (Keyword)cGroup_8.eContents().get(1);
		private final Assignment cPersistentEntriesAssignment_8_2 = (Assignment)cGroup_8.eContents().get(2);
		private final RuleCall cPersistentEntriesPersistentEntryParserRuleCall_8_2_0 = (RuleCall)cPersistentEntriesAssignment_8_2.eContents().get(0);
		private final Group cGroup_8_3 = (Group)cGroup_8.eContents().get(3);
		private final Keyword cCommaKeyword_8_3_0 = (Keyword)cGroup_8_3.eContents().get(0);
		private final Assignment cPersistentEntriesAssignment_8_3_1 = (Assignment)cGroup_8_3.eContents().get(1);
		private final RuleCall cPersistentEntriesPersistentEntryParserRuleCall_8_3_1_0 = (RuleCall)cPersistentEntriesAssignment_8_3_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_9 = (Keyword)cGroup.eContents().get(9);
		
		//KShapeLayout: //	{KShapeLayout}
		////	'KShapeLayout'
		////	'{'
		////		('xpos' xpos=EFloat)?
		////		('ypos' ypos=EFloat)?
		////		('width' width=EFloat)?
		////		('height' height=EFloat)?
		////		('persistentEntries' '{' persistentEntries+=PersistentEntry ( "," persistentEntries+=PersistentEntry)* '}' )?
		////		('insets' insets=KInsets)?
		////    '}';
		//	{KShapeLayout} "KShapeLayout" "{" ("xpos" xpos=EFloat)? ("ypos" ypos=EFloat)? ("width" width=EFloat)? ("height"
		//	height=EFloat)? ("insets" insets=KInsets)? ("mapProperties" ":" persistentEntries+=PersistentEntry (","?
		//	persistentEntries+=PersistentEntry)*)? "}";
		public ParserRule getRule() { return rule; }

		////	{KShapeLayout}
		////	'KShapeLayout'
		////	'{'
		////		('xpos' xpos=EFloat)?
		////		('ypos' ypos=EFloat)?
		////		('width' width=EFloat)?
		////		('height' height=EFloat)?
		////		('persistentEntries' '{' persistentEntries+=PersistentEntry ( "," persistentEntries+=PersistentEntry)* '}' )?
		////		('insets' insets=KInsets)?
		////    '}';
		//{KShapeLayout} "KShapeLayout" "{" ("xpos" xpos=EFloat)? ("ypos" ypos=EFloat)? ("width" width=EFloat)? ("height"
		//height=EFloat)? ("insets" insets=KInsets)? ("mapProperties" ":" persistentEntries+=PersistentEntry (","?
		//persistentEntries+=PersistentEntry)*)? "}"
		public Group getGroup() { return cGroup; }

		////	{KShapeLayout}
		////	'KShapeLayout'
		////	'{'
		////		('xpos' xpos=EFloat)?
		////		('ypos' ypos=EFloat)?
		////		('width' width=EFloat)?
		////		('height' height=EFloat)?
		////		('persistentEntries' '{' persistentEntries+=PersistentEntry ( "," persistentEntries+=PersistentEntry)* '}' )?
		////		('insets' insets=KInsets)?
		////    '}';
		//{KShapeLayout}
		public Action getKShapeLayoutAction_0() { return cKShapeLayoutAction_0; }

		//"KShapeLayout"
		public Keyword getKShapeLayoutKeyword_1() { return cKShapeLayoutKeyword_1; }

		//"{"
		public Keyword getLeftCurlyBracketKeyword_2() { return cLeftCurlyBracketKeyword_2; }

		//("xpos" xpos=EFloat)?
		public Group getGroup_3() { return cGroup_3; }

		//"xpos"
		public Keyword getXposKeyword_3_0() { return cXposKeyword_3_0; }

		//xpos=EFloat
		public Assignment getXposAssignment_3_1() { return cXposAssignment_3_1; }

		//EFloat
		public RuleCall getXposEFloatParserRuleCall_3_1_0() { return cXposEFloatParserRuleCall_3_1_0; }

		//("ypos" ypos=EFloat)?
		public Group getGroup_4() { return cGroup_4; }

		//"ypos"
		public Keyword getYposKeyword_4_0() { return cYposKeyword_4_0; }

		//ypos=EFloat
		public Assignment getYposAssignment_4_1() { return cYposAssignment_4_1; }

		//EFloat
		public RuleCall getYposEFloatParserRuleCall_4_1_0() { return cYposEFloatParserRuleCall_4_1_0; }

		//("width" width=EFloat)?
		public Group getGroup_5() { return cGroup_5; }

		//"width"
		public Keyword getWidthKeyword_5_0() { return cWidthKeyword_5_0; }

		//width=EFloat
		public Assignment getWidthAssignment_5_1() { return cWidthAssignment_5_1; }

		//EFloat
		public RuleCall getWidthEFloatParserRuleCall_5_1_0() { return cWidthEFloatParserRuleCall_5_1_0; }

		//("height" height=EFloat)?
		public Group getGroup_6() { return cGroup_6; }

		//"height"
		public Keyword getHeightKeyword_6_0() { return cHeightKeyword_6_0; }

		//height=EFloat
		public Assignment getHeightAssignment_6_1() { return cHeightAssignment_6_1; }

		//EFloat
		public RuleCall getHeightEFloatParserRuleCall_6_1_0() { return cHeightEFloatParserRuleCall_6_1_0; }

		//("insets" insets=KInsets)?
		public Group getGroup_7() { return cGroup_7; }

		//"insets"
		public Keyword getInsetsKeyword_7_0() { return cInsetsKeyword_7_0; }

		//insets=KInsets
		public Assignment getInsetsAssignment_7_1() { return cInsetsAssignment_7_1; }

		//KInsets
		public RuleCall getInsetsKInsetsParserRuleCall_7_1_0() { return cInsetsKInsetsParserRuleCall_7_1_0; }

		//("mapProperties" ":" persistentEntries+=PersistentEntry (","? persistentEntries+=PersistentEntry)*)?
		public Group getGroup_8() { return cGroup_8; }

		//"mapProperties"
		public Keyword getMapPropertiesKeyword_8_0() { return cMapPropertiesKeyword_8_0; }

		//":"
		public Keyword getColonKeyword_8_1() { return cColonKeyword_8_1; }

		//persistentEntries+=PersistentEntry
		public Assignment getPersistentEntriesAssignment_8_2() { return cPersistentEntriesAssignment_8_2; }

		//PersistentEntry
		public RuleCall getPersistentEntriesPersistentEntryParserRuleCall_8_2_0() { return cPersistentEntriesPersistentEntryParserRuleCall_8_2_0; }

		//(","? persistentEntries+=PersistentEntry)*
		public Group getGroup_8_3() { return cGroup_8_3; }

		//","?
		public Keyword getCommaKeyword_8_3_0() { return cCommaKeyword_8_3_0; }

		//persistentEntries+=PersistentEntry
		public Assignment getPersistentEntriesAssignment_8_3_1() { return cPersistentEntriesAssignment_8_3_1; }

		//PersistentEntry
		public RuleCall getPersistentEntriesPersistentEntryParserRuleCall_8_3_1_0() { return cPersistentEntriesPersistentEntryParserRuleCall_8_3_1_0; }

		//"}"
		public Keyword getRightCurlyBracketKeyword_9() { return cRightCurlyBracketKeyword_9; }
	}

	public class KInsetsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "KInsets");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cKInsetsAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cKInsetsKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cLeftCurlyBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cTopKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cTopAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cTopEFloatParserRuleCall_3_1_0 = (RuleCall)cTopAssignment_3_1.eContents().get(0);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cBottomKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cBottomAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cBottomEFloatParserRuleCall_4_1_0 = (RuleCall)cBottomAssignment_4_1.eContents().get(0);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cLeftKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Assignment cLeftAssignment_5_1 = (Assignment)cGroup_5.eContents().get(1);
		private final RuleCall cLeftEFloatParserRuleCall_5_1_0 = (RuleCall)cLeftAssignment_5_1.eContents().get(0);
		private final Group cGroup_6 = (Group)cGroup.eContents().get(6);
		private final Keyword cRightKeyword_6_0 = (Keyword)cGroup_6.eContents().get(0);
		private final Assignment cRightAssignment_6_1 = (Assignment)cGroup_6.eContents().get(1);
		private final RuleCall cRightEFloatParserRuleCall_6_1_0 = (RuleCall)cRightAssignment_6_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_7 = (Keyword)cGroup.eContents().get(7);
		
		//KInsets:
		//	{KInsets} "KInsets" "{" ("top" top=EFloat)? ("bottom" bottom=EFloat)? ("left" left=EFloat)? ("right" right=EFloat)?
		//	"}";
		public ParserRule getRule() { return rule; }

		//{KInsets} "KInsets" "{" ("top" top=EFloat)? ("bottom" bottom=EFloat)? ("left" left=EFloat)? ("right" right=EFloat)? "}"
		public Group getGroup() { return cGroup; }

		//{KInsets}
		public Action getKInsetsAction_0() { return cKInsetsAction_0; }

		//"KInsets"
		public Keyword getKInsetsKeyword_1() { return cKInsetsKeyword_1; }

		//"{"
		public Keyword getLeftCurlyBracketKeyword_2() { return cLeftCurlyBracketKeyword_2; }

		//("top" top=EFloat)?
		public Group getGroup_3() { return cGroup_3; }

		//"top"
		public Keyword getTopKeyword_3_0() { return cTopKeyword_3_0; }

		//top=EFloat
		public Assignment getTopAssignment_3_1() { return cTopAssignment_3_1; }

		//EFloat
		public RuleCall getTopEFloatParserRuleCall_3_1_0() { return cTopEFloatParserRuleCall_3_1_0; }

		//("bottom" bottom=EFloat)?
		public Group getGroup_4() { return cGroup_4; }

		//"bottom"
		public Keyword getBottomKeyword_4_0() { return cBottomKeyword_4_0; }

		//bottom=EFloat
		public Assignment getBottomAssignment_4_1() { return cBottomAssignment_4_1; }

		//EFloat
		public RuleCall getBottomEFloatParserRuleCall_4_1_0() { return cBottomEFloatParserRuleCall_4_1_0; }

		//("left" left=EFloat)?
		public Group getGroup_5() { return cGroup_5; }

		//"left"
		public Keyword getLeftKeyword_5_0() { return cLeftKeyword_5_0; }

		//left=EFloat
		public Assignment getLeftAssignment_5_1() { return cLeftAssignment_5_1; }

		//EFloat
		public RuleCall getLeftEFloatParserRuleCall_5_1_0() { return cLeftEFloatParserRuleCall_5_1_0; }

		//("right" right=EFloat)?
		public Group getGroup_6() { return cGroup_6; }

		//"right"
		public Keyword getRightKeyword_6_0() { return cRightKeyword_6_0; }

		//right=EFloat
		public Assignment getRightAssignment_6_1() { return cRightAssignment_6_1; }

		//EFloat
		public RuleCall getRightEFloatParserRuleCall_6_1_0() { return cRightEFloatParserRuleCall_6_1_0; }

		//"}"
		public Keyword getRightCurlyBracketKeyword_7() { return cRightCurlyBracketKeyword_7; }
	}

	public class KEdgeLayoutElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "KEdgeLayout");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cKEdgeLayoutKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cSourcePointKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cSourcePointAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cSourcePointKPointParserRuleCall_2_1_0 = (RuleCall)cSourcePointAssignment_2_1.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cTargetPointKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cTargetPointAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cTargetPointKPointParserRuleCall_3_1_0 = (RuleCall)cTargetPointAssignment_3_1.eContents().get(0);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cBendPointsKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Keyword cColonKeyword_4_1 = (Keyword)cGroup_4.eContents().get(1);
		private final Assignment cBendPointsAssignment_4_2 = (Assignment)cGroup_4.eContents().get(2);
		private final RuleCall cBendPointsKPointParserRuleCall_4_2_0 = (RuleCall)cBendPointsAssignment_4_2.eContents().get(0);
		private final Group cGroup_4_3 = (Group)cGroup_4.eContents().get(3);
		private final Keyword cCommaKeyword_4_3_0 = (Keyword)cGroup_4_3.eContents().get(0);
		private final Assignment cBendPointsAssignment_4_3_1 = (Assignment)cGroup_4_3.eContents().get(1);
		private final RuleCall cBendPointsKPointParserRuleCall_4_3_1_0 = (RuleCall)cBendPointsAssignment_4_3_1.eContents().get(0);
		private final Group cGroup_5 = (Group)cGroup.eContents().get(5);
		private final Keyword cMapPropertiesKeyword_5_0 = (Keyword)cGroup_5.eContents().get(0);
		private final Keyword cColonKeyword_5_1 = (Keyword)cGroup_5.eContents().get(1);
		private final Assignment cPersistentEntriesAssignment_5_2 = (Assignment)cGroup_5.eContents().get(2);
		private final RuleCall cPersistentEntriesPersistentEntryParserRuleCall_5_2_0 = (RuleCall)cPersistentEntriesAssignment_5_2.eContents().get(0);
		private final Group cGroup_5_3 = (Group)cGroup_5.eContents().get(3);
		private final Keyword cCommaKeyword_5_3_0 = (Keyword)cGroup_5_3.eContents().get(0);
		private final Assignment cPersistentEntriesAssignment_5_3_1 = (Assignment)cGroup_5_3.eContents().get(1);
		private final RuleCall cPersistentEntriesPersistentEntryParserRuleCall_5_3_1_0 = (RuleCall)cPersistentEntriesAssignment_5_3_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_6 = (Keyword)cGroup.eContents().get(6);
		
		//KEdgeLayout: //	'KEdgeLayout'
		////	'{'
		////		('persistentEntries' '{' persistentEntries+=PersistentEntry ( "," persistentEntries+=PersistentEntry)* '}' )?
		////		('bendPoints' '{' bendPoints+=KPoint ( "," bendPoints+=KPoint)* '}' )?
		////		'sourcePoint' sourcePoint=KPoint
		////		'targetPoint' targetPoint=KPoint
		////    '}';
		//	"KEdgeLayout" "{" ("sourcePoint" sourcePoint=KPoint)? ("targetPoint" targetPoint=KPoint)? ("bendPoints" ":"
		//	bendPoints+=KPoint (","? bendPoints+=KPoint)*)? ("mapProperties" ":" persistentEntries+=PersistentEntry (","?
		//	persistentEntries+=PersistentEntry)*)? "}";
		public ParserRule getRule() { return rule; }

		////	'KEdgeLayout'
		////	'{'
		////		('persistentEntries' '{' persistentEntries+=PersistentEntry ( "," persistentEntries+=PersistentEntry)* '}' )?
		////		('bendPoints' '{' bendPoints+=KPoint ( "," bendPoints+=KPoint)* '}' )?
		////		'sourcePoint' sourcePoint=KPoint
		////		'targetPoint' targetPoint=KPoint
		////    '}';
		//"KEdgeLayout" "{" ("sourcePoint" sourcePoint=KPoint)? ("targetPoint" targetPoint=KPoint)? ("bendPoints" ":"
		//bendPoints+=KPoint (","? bendPoints+=KPoint)*)? ("mapProperties" ":" persistentEntries+=PersistentEntry (","?
		//persistentEntries+=PersistentEntry)*)? "}"
		public Group getGroup() { return cGroup; }

		////	'KEdgeLayout'
		////	'{'
		////		('persistentEntries' '{' persistentEntries+=PersistentEntry ( "," persistentEntries+=PersistentEntry)* '}' )?
		////		('bendPoints' '{' bendPoints+=KPoint ( "," bendPoints+=KPoint)* '}' )?
		////		'sourcePoint' sourcePoint=KPoint
		////		'targetPoint' targetPoint=KPoint
		////    '}';
		//"KEdgeLayout"
		public Keyword getKEdgeLayoutKeyword_0() { return cKEdgeLayoutKeyword_0; }

		//"{"
		public Keyword getLeftCurlyBracketKeyword_1() { return cLeftCurlyBracketKeyword_1; }

		//("sourcePoint" sourcePoint=KPoint)?
		public Group getGroup_2() { return cGroup_2; }

		//"sourcePoint"
		public Keyword getSourcePointKeyword_2_0() { return cSourcePointKeyword_2_0; }

		//sourcePoint=KPoint
		public Assignment getSourcePointAssignment_2_1() { return cSourcePointAssignment_2_1; }

		//KPoint
		public RuleCall getSourcePointKPointParserRuleCall_2_1_0() { return cSourcePointKPointParserRuleCall_2_1_0; }

		//("targetPoint" targetPoint=KPoint)?
		public Group getGroup_3() { return cGroup_3; }

		//"targetPoint"
		public Keyword getTargetPointKeyword_3_0() { return cTargetPointKeyword_3_0; }

		//targetPoint=KPoint
		public Assignment getTargetPointAssignment_3_1() { return cTargetPointAssignment_3_1; }

		//KPoint
		public RuleCall getTargetPointKPointParserRuleCall_3_1_0() { return cTargetPointKPointParserRuleCall_3_1_0; }

		//("bendPoints" ":" bendPoints+=KPoint (","? bendPoints+=KPoint)*)?
		public Group getGroup_4() { return cGroup_4; }

		//"bendPoints"
		public Keyword getBendPointsKeyword_4_0() { return cBendPointsKeyword_4_0; }

		//":"
		public Keyword getColonKeyword_4_1() { return cColonKeyword_4_1; }

		//bendPoints+=KPoint
		public Assignment getBendPointsAssignment_4_2() { return cBendPointsAssignment_4_2; }

		//KPoint
		public RuleCall getBendPointsKPointParserRuleCall_4_2_0() { return cBendPointsKPointParserRuleCall_4_2_0; }

		//(","? bendPoints+=KPoint)*
		public Group getGroup_4_3() { return cGroup_4_3; }

		//","?
		public Keyword getCommaKeyword_4_3_0() { return cCommaKeyword_4_3_0; }

		//bendPoints+=KPoint
		public Assignment getBendPointsAssignment_4_3_1() { return cBendPointsAssignment_4_3_1; }

		//KPoint
		public RuleCall getBendPointsKPointParserRuleCall_4_3_1_0() { return cBendPointsKPointParserRuleCall_4_3_1_0; }

		//("mapProperties" ":" persistentEntries+=PersistentEntry (","? persistentEntries+=PersistentEntry)*)?
		public Group getGroup_5() { return cGroup_5; }

		//"mapProperties"
		public Keyword getMapPropertiesKeyword_5_0() { return cMapPropertiesKeyword_5_0; }

		//":"
		public Keyword getColonKeyword_5_1() { return cColonKeyword_5_1; }

		//persistentEntries+=PersistentEntry
		public Assignment getPersistentEntriesAssignment_5_2() { return cPersistentEntriesAssignment_5_2; }

		//PersistentEntry
		public RuleCall getPersistentEntriesPersistentEntryParserRuleCall_5_2_0() { return cPersistentEntriesPersistentEntryParserRuleCall_5_2_0; }

		//(","? persistentEntries+=PersistentEntry)*
		public Group getGroup_5_3() { return cGroup_5_3; }

		//","?
		public Keyword getCommaKeyword_5_3_0() { return cCommaKeyword_5_3_0; }

		//persistentEntries+=PersistentEntry
		public Assignment getPersistentEntriesAssignment_5_3_1() { return cPersistentEntriesAssignment_5_3_1; }

		//PersistentEntry
		public RuleCall getPersistentEntriesPersistentEntryParserRuleCall_5_3_1_0() { return cPersistentEntriesPersistentEntryParserRuleCall_5_3_1_0; }

		//"}"
		public Keyword getRightCurlyBracketKeyword_6() { return cRightCurlyBracketKeyword_6; }
	}

	public class KPointElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "KPoint");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cKPointAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cKPointKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cXKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final Assignment cXAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final RuleCall cXEFloatParserRuleCall_2_1_0 = (RuleCall)cXAssignment_2_1.eContents().get(0);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cYKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cYAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final RuleCall cYEFloatParserRuleCall_3_1_0 = (RuleCall)cYAssignment_3_1.eContents().get(0);
		
		//KPoint: //	{KPoint}
		////	'KPoint'
		////	'{'
		////		('x' x=EFloat)?
		////		('y' y=EFloat)?
		////    '}';
		//	{KPoint} "KPoint" ("x" x=EFloat) ("y" y=EFloat);
		public ParserRule getRule() { return rule; }

		////	{KPoint}
		////	'KPoint'
		////	'{'
		////		('x' x=EFloat)?
		////		('y' y=EFloat)?
		////    '}';
		//{KPoint} "KPoint" ("x" x=EFloat) ("y" y=EFloat)
		public Group getGroup() { return cGroup; }

		////	{KPoint}
		////	'KPoint'
		////	'{'
		////		('x' x=EFloat)?
		////		('y' y=EFloat)?
		////    '}';
		//{KPoint}
		public Action getKPointAction_0() { return cKPointAction_0; }

		//"KPoint"
		public Keyword getKPointKeyword_1() { return cKPointKeyword_1; }

		//"x" x=EFloat
		public Group getGroup_2() { return cGroup_2; }

		//"x"
		public Keyword getXKeyword_2_0() { return cXKeyword_2_0; }

		//x=EFloat
		public Assignment getXAssignment_2_1() { return cXAssignment_2_1; }

		//EFloat
		public RuleCall getXEFloatParserRuleCall_2_1_0() { return cXEFloatParserRuleCall_2_1_0; }

		//"y" y=EFloat
		public Group getGroup_3() { return cGroup_3; }

		//"y"
		public Keyword getYKeyword_3_0() { return cYKeyword_3_0; }

		//y=EFloat
		public Assignment getYAssignment_3_1() { return cYAssignment_3_1; }

		//EFloat
		public RuleCall getYEFloatParserRuleCall_3_1_0() { return cYEFloatParserRuleCall_3_1_0; }
	}

	public class PersistentEntryElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "PersistentEntry");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cKeyAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cKeyEStringParserRuleCall_0_0 = (RuleCall)cKeyAssignment_0.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cEqualsSignKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final Assignment cValueAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cValueEStringParserRuleCall_1_1_0 = (RuleCall)cValueAssignment_1_1.eContents().get(0);
		
		//PersistentEntry returns kgraph::PersistentEntry: //	'PersistentEntry'
		////	'{'
		////		'key' key=EString
		////		('value' value=EString)?
		////    '}';
		//	key=EString ("=" value=EString)?;
		public ParserRule getRule() { return rule; }

		////	'PersistentEntry'
		////	'{'
		////		'key' key=EString
		////		('value' value=EString)?
		////    '}';
		//key=EString ("=" value=EString)?
		public Group getGroup() { return cGroup; }

		////	'PersistentEntry'
		////	'{'
		////		'key' key=EString
		////		('value' value=EString)?
		////    '}';
		//key=EString
		public Assignment getKeyAssignment_0() { return cKeyAssignment_0; }

		//EString
		public RuleCall getKeyEStringParserRuleCall_0_0() { return cKeyEStringParserRuleCall_0_0; }

		//("=" value=EString)?
		public Group getGroup_1() { return cGroup_1; }

		//"="
		public Keyword getEqualsSignKeyword_1_0() { return cEqualsSignKeyword_1_0; }

		//value=EString
		public Assignment getValueAssignment_1_1() { return cValueAssignment_1_1; }

		//EString
		public RuleCall getValueEStringParserRuleCall_1_1_0() { return cValueEStringParserRuleCall_1_1_0; }
	}

	public class EStringElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "EString");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cSTRINGTerminalRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//EString returns ecore::EString:
		//	STRING | ID;
		public ParserRule getRule() { return rule; }

		//STRING | ID
		public Alternatives getAlternatives() { return cAlternatives; }

		//STRING
		public RuleCall getSTRINGTerminalRuleCall_0() { return cSTRINGTerminalRuleCall_0; }

		//ID
		public RuleCall getIDTerminalRuleCall_1() { return cIDTerminalRuleCall_1; }
	}

	public class EFloatElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "EFloat");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cHyphenMinusKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final RuleCall cINTTerminalRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Keyword cFullStopKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final RuleCall cINTTerminalRuleCall_3 = (RuleCall)cGroup.eContents().get(3);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Alternatives cAlternatives_4_0 = (Alternatives)cGroup_4.eContents().get(0);
		private final Keyword cEKeyword_4_0_0 = (Keyword)cAlternatives_4_0.eContents().get(0);
		private final Keyword cEKeyword_4_0_1 = (Keyword)cAlternatives_4_0.eContents().get(1);
		private final Keyword cHyphenMinusKeyword_4_1 = (Keyword)cGroup_4.eContents().get(1);
		private final RuleCall cINTTerminalRuleCall_4_2 = (RuleCall)cGroup_4.eContents().get(2);
		
		//EFloat returns ecore::EFloat:
		//	"-"? INT? "." INT (("E" | "e") "-"? INT)?;
		public ParserRule getRule() { return rule; }

		//"-"? INT? "." INT (("E" | "e") "-"? INT)?
		public Group getGroup() { return cGroup; }

		//"-"?
		public Keyword getHyphenMinusKeyword_0() { return cHyphenMinusKeyword_0; }

		//INT?
		public RuleCall getINTTerminalRuleCall_1() { return cINTTerminalRuleCall_1; }

		//"."
		public Keyword getFullStopKeyword_2() { return cFullStopKeyword_2; }

		//INT
		public RuleCall getINTTerminalRuleCall_3() { return cINTTerminalRuleCall_3; }

		//(("E" | "e") "-"? INT)?
		public Group getGroup_4() { return cGroup_4; }

		//"E" | "e"
		public Alternatives getAlternatives_4_0() { return cAlternatives_4_0; }

		//"E"
		public Keyword getEKeyword_4_0_0() { return cEKeyword_4_0_0; }

		//"e"
		public Keyword getEKeyword_4_0_1() { return cEKeyword_4_0_1; }

		//"-"?
		public Keyword getHyphenMinusKeyword_4_1() { return cHyphenMinusKeyword_4_1; }

		//INT
		public RuleCall getINTTerminalRuleCall_4_2() { return cINTTerminalRuleCall_4_2; }
	}
	
	
	private KShapeLayoutElements pKShapeLayout;
	private KInsetsElements pKInsets;
	private KEdgeLayoutElements pKEdgeLayout;
	private KPointElements pKPoint;
	private PersistentEntryElements pPersistentEntry;
	private EStringElements pEString;
	private EFloatElements pEFloat;
	
	private final Grammar grammar;

	private TerminalsGrammarAccess gaTerminals;

	@Inject
	public KLayoutDataGrammarAccess(GrammarProvider grammarProvider,
		TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("de.cau.cs.kieler.core.kgraph.text.klayoutdata.KLayoutData".equals(grammar.getName())) {
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
	

	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//KShapeLayout: //	{KShapeLayout}
	////	'KShapeLayout'
	////	'{'
	////		('xpos' xpos=EFloat)?
	////		('ypos' ypos=EFloat)?
	////		('width' width=EFloat)?
	////		('height' height=EFloat)?
	////		('persistentEntries' '{' persistentEntries+=PersistentEntry ( "," persistentEntries+=PersistentEntry)* '}' )?
	////		('insets' insets=KInsets)?
	////    '}';
	//	{KShapeLayout} "KShapeLayout" "{" ("xpos" xpos=EFloat)? ("ypos" ypos=EFloat)? ("width" width=EFloat)? ("height"
	//	height=EFloat)? ("insets" insets=KInsets)? ("mapProperties" ":" persistentEntries+=PersistentEntry (","?
	//	persistentEntries+=PersistentEntry)*)? "}";
	public KShapeLayoutElements getKShapeLayoutAccess() {
		return (pKShapeLayout != null) ? pKShapeLayout : (pKShapeLayout = new KShapeLayoutElements());
	}
	
	public ParserRule getKShapeLayoutRule() {
		return getKShapeLayoutAccess().getRule();
	}

	//KInsets:
	//	{KInsets} "KInsets" "{" ("top" top=EFloat)? ("bottom" bottom=EFloat)? ("left" left=EFloat)? ("right" right=EFloat)?
	//	"}";
	public KInsetsElements getKInsetsAccess() {
		return (pKInsets != null) ? pKInsets : (pKInsets = new KInsetsElements());
	}
	
	public ParserRule getKInsetsRule() {
		return getKInsetsAccess().getRule();
	}

	//KEdgeLayout: //	'KEdgeLayout'
	////	'{'
	////		('persistentEntries' '{' persistentEntries+=PersistentEntry ( "," persistentEntries+=PersistentEntry)* '}' )?
	////		('bendPoints' '{' bendPoints+=KPoint ( "," bendPoints+=KPoint)* '}' )?
	////		'sourcePoint' sourcePoint=KPoint
	////		'targetPoint' targetPoint=KPoint
	////    '}';
	//	"KEdgeLayout" "{" ("sourcePoint" sourcePoint=KPoint)? ("targetPoint" targetPoint=KPoint)? ("bendPoints" ":"
	//	bendPoints+=KPoint (","? bendPoints+=KPoint)*)? ("mapProperties" ":" persistentEntries+=PersistentEntry (","?
	//	persistentEntries+=PersistentEntry)*)? "}";
	public KEdgeLayoutElements getKEdgeLayoutAccess() {
		return (pKEdgeLayout != null) ? pKEdgeLayout : (pKEdgeLayout = new KEdgeLayoutElements());
	}
	
	public ParserRule getKEdgeLayoutRule() {
		return getKEdgeLayoutAccess().getRule();
	}

	//KPoint: //	{KPoint}
	////	'KPoint'
	////	'{'
	////		('x' x=EFloat)?
	////		('y' y=EFloat)?
	////    '}';
	//	{KPoint} "KPoint" ("x" x=EFloat) ("y" y=EFloat);
	public KPointElements getKPointAccess() {
		return (pKPoint != null) ? pKPoint : (pKPoint = new KPointElements());
	}
	
	public ParserRule getKPointRule() {
		return getKPointAccess().getRule();
	}

	//PersistentEntry returns kgraph::PersistentEntry: //	'PersistentEntry'
	////	'{'
	////		'key' key=EString
	////		('value' value=EString)?
	////    '}';
	//	key=EString ("=" value=EString)?;
	public PersistentEntryElements getPersistentEntryAccess() {
		return (pPersistentEntry != null) ? pPersistentEntry : (pPersistentEntry = new PersistentEntryElements());
	}
	
	public ParserRule getPersistentEntryRule() {
		return getPersistentEntryAccess().getRule();
	}

	//EString returns ecore::EString:
	//	STRING | ID;
	public EStringElements getEStringAccess() {
		return (pEString != null) ? pEString : (pEString = new EStringElements());
	}
	
	public ParserRule getEStringRule() {
		return getEStringAccess().getRule();
	}

	//EFloat returns ecore::EFloat:
	//	"-"? INT? "." INT (("E" | "e") "-"? INT)?;
	public EFloatElements getEFloatAccess() {
		return (pEFloat != null) ? pEFloat : (pEFloat = new EFloatElements());
	}
	
	public ParserRule getEFloatRule() {
		return getEFloatAccess().getRule();
	}

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"" | "\'" ("\\" ("b" | "t" |
	//	"n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\'"))* "\'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	} 
}
