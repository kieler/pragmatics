/*
* generated by Xtext
*/
grammar InternalAnnotations;

options {
	superClass=AbstractInternalAntlrParser;
	backtrack=true;
	
}

@lexer::header {
package de.cau.cs.kieler.core.annotations.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package de.cau.cs.kieler.core.annotations.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import de.cau.cs.kieler.core.annotations.services.AnnotationsGrammarAccess;

}

@parser::members {

/*
  This grammar contains a lot of empty actions to work around a bug in ANTLR.
  Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
*/
 
 	private AnnotationsGrammarAccess grammarAccess;
 	
    public InternalAnnotationsParser(TokenStream input, IAstFactory factory, AnnotationsGrammarAccess grammarAccess) {
        this(input);
        this.factory = factory;
        registerRules(grammarAccess.getGrammar());
        this.grammarAccess = grammarAccess;
    }
    
    @Override
    protected InputStream getTokenFile() {
    	ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("de/cau/cs/kieler/core/annotations/parser/antlr/internal/InternalAnnotations.tokens");
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Annotation";	
   	}
   	
   	@Override
   	protected AnnotationsGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleAnnotation
entryRuleAnnotation returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getAnnotationRule(), currentNode); }
	 iv_ruleAnnotation=ruleAnnotation 
	 { $current=$iv_ruleAnnotation.current; } 
	 EOF 
;

// Rule Annotation
ruleAnnotation returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(
	{ 
	  /* */ 
	}
    { 
        currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0(), currentNode); 
    }
    this_CommentAnnotation_0=ruleCommentAnnotation
    { 
        $current = $this_CommentAnnotation_0.current; 
        currentNode = currentNode.getParent();
    }

    |
	{ 
	  /* */ 
	}
    { 
        currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1(), currentNode); 
    }
    this_TagAnnotation_1=ruleTagAnnotation
    { 
        $current = $this_TagAnnotation_1.current; 
        currentNode = currentNode.getParent();
    }

    |
	{ 
	  /* */ 
	}
    { 
        currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2(), currentNode); 
    }
    this_KeyStringValueAnnotation_2=ruleKeyStringValueAnnotation
    { 
        $current = $this_KeyStringValueAnnotation_2.current; 
        currentNode = currentNode.getParent();
    }

    |
	{ 
	  /* */ 
	}
    { 
        currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getTypedStringAnnotationParserRuleCall_3(), currentNode); 
    }
    this_TypedStringAnnotation_3=ruleTypedStringAnnotation
    { 
        $current = $this_TypedStringAnnotation_3.current; 
        currentNode = currentNode.getParent();
    }

    |
	{ 
	  /* */ 
	}
    { 
        currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_4(), currentNode); 
    }
    this_KeyBooleanValueAnnotation_4=ruleKeyBooleanValueAnnotation
    { 
        $current = $this_KeyBooleanValueAnnotation_4.current; 
        currentNode = currentNode.getParent();
    }

    |
	{ 
	  /* */ 
	}
    { 
        currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_5(), currentNode); 
    }
    this_KeyIntValueAnnotation_5=ruleKeyIntValueAnnotation
    { 
        $current = $this_KeyIntValueAnnotation_5.current; 
        currentNode = currentNode.getParent();
    }

    |
	{ 
	  /* */ 
	}
    { 
        currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_6(), currentNode); 
    }
    this_KeyFloatValueAnnotation_6=ruleKeyFloatValueAnnotation
    { 
        $current = $this_KeyFloatValueAnnotation_6.current; 
        currentNode = currentNode.getParent();
    }
)
;





// Entry rule entryRuleCommentAnnotation
entryRuleCommentAnnotation returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getCommentAnnotationRule(), currentNode); }
	 iv_ruleCommentAnnotation=ruleCommentAnnotation 
	 { $current=$iv_ruleCommentAnnotation.current; } 
	 EOF 
;

// Rule CommentAnnotation
ruleCommentAnnotation returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(
(
		lv_value_0_0=RULE_COMMENT_ANNOTATION
		{
			createLeafNode(grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0(), "value"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getCommentAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"value",
	        		lv_value_0_0, 
	        		"COMMENT_ANNOTATION", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)
;





// Entry rule entryRuleTagAnnotation
entryRuleTagAnnotation returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getTagAnnotationRule(), currentNode); }
	 iv_ruleTagAnnotation=ruleTagAnnotation 
	 { $current=$iv_ruleTagAnnotation.current; } 
	 EOF 
;

// Rule TagAnnotation
ruleTagAnnotation returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(	'@' 
    {
        createLeafNode(grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0(), null); 
    }
(
(
		lv_name_1_0=RULE_ID
		{
			createLeafNode(grammarAccess.getTagAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getTagAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"name",
	        		lv_name_1_0, 
	        		"ID", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(	'(' 
    {
        createLeafNode(grammarAccess.getTagAnnotationAccess().getLeftParenthesisKeyword_2_0(), null); 
    }
(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getTagAnnotationAccess().getAnnotationsAnnotationParserRuleCall_2_1_0(), currentNode); 
	    }
		lv_annotations_3_0=ruleAnnotation		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getTagAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		add(
	       			$current, 
	       			"annotations",
	        		lv_annotations_3_0, 
	        		"Annotation", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
)*	')' 
    {
        createLeafNode(grammarAccess.getTagAnnotationAccess().getRightParenthesisKeyword_2_2(), null); 
    }
)?)
;





// Entry rule entryRuleKeyStringValueAnnotation
entryRuleKeyStringValueAnnotation returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getKeyStringValueAnnotationRule(), currentNode); }
	 iv_ruleKeyStringValueAnnotation=ruleKeyStringValueAnnotation 
	 { $current=$iv_ruleKeyStringValueAnnotation.current; } 
	 EOF 
;

// Rule KeyStringValueAnnotation
ruleKeyStringValueAnnotation returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(	'@' 
    {
        createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
    }
(
(
		lv_name_1_0=RULE_ID
		{
			createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"name",
	        		lv_name_1_0, 
	        		"ID", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0(), currentNode); 
	    }
		lv_value_2_0=ruleEString		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"value",
	        		lv_value_2_0, 
	        		"EString", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
)(	'(' 
    {
        createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
    }
(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
	    }
		lv_annotations_4_0=ruleAnnotation		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		add(
	       			$current, 
	       			"annotations",
	        		lv_annotations_4_0, 
	        		"Annotation", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
)*	')' 
    {
        createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
    }
)?)
;





// Entry rule entryRuleTypedStringAnnotation
entryRuleTypedStringAnnotation returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getTypedStringAnnotationRule(), currentNode); }
	 iv_ruleTypedStringAnnotation=ruleTypedStringAnnotation 
	 { $current=$iv_ruleTypedStringAnnotation.current; } 
	 EOF 
;

// Rule TypedStringAnnotation
ruleTypedStringAnnotation returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(	'@' 
    {
        createLeafNode(grammarAccess.getTypedStringAnnotationAccess().getCommercialAtKeyword_0(), null); 
    }
(
(
		lv_name_1_0=RULE_ID
		{
			createLeafNode(grammarAccess.getTypedStringAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getTypedStringAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"name",
	        		lv_name_1_0, 
	        		"ID", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(
(
		lv_type_2_0=RULE_TYPEID
		{
			createLeafNode(grammarAccess.getTypedStringAnnotationAccess().getTypeTypeIdTerminalRuleCall_2_0(), "type"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getTypedStringAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"type",
	        		lv_type_2_0, 
	        		"TypeId", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)?(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getTypedStringAnnotationAccess().getValueEStringParserRuleCall_3_0(), currentNode); 
	    }
		lv_value_3_0=ruleEString		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getTypedStringAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"value",
	        		lv_value_3_0, 
	        		"EString", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
)(	'(' 
    {
        createLeafNode(grammarAccess.getTypedStringAnnotationAccess().getLeftParenthesisKeyword_4_0(), null); 
    }
(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getTypedStringAnnotationAccess().getAnnotationsAnnotationParserRuleCall_4_1_0(), currentNode); 
	    }
		lv_annotations_5_0=ruleAnnotation		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getTypedStringAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		add(
	       			$current, 
	       			"annotations",
	        		lv_annotations_5_0, 
	        		"Annotation", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
)*	')' 
    {
        createLeafNode(grammarAccess.getTypedStringAnnotationAccess().getRightParenthesisKeyword_4_2(), null); 
    }
)?)
;





// Entry rule entryRuleKeyBooleanValueAnnotation
entryRuleKeyBooleanValueAnnotation returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationRule(), currentNode); }
	 iv_ruleKeyBooleanValueAnnotation=ruleKeyBooleanValueAnnotation 
	 { $current=$iv_ruleKeyBooleanValueAnnotation.current; } 
	 EOF 
;

// Rule KeyBooleanValueAnnotation
ruleKeyBooleanValueAnnotation returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(	'@' 
    {
        createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
    }
(
(
		lv_name_1_0=RULE_ID
		{
			createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"name",
	        		lv_name_1_0, 
	        		"ID", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(
(
		lv_value_2_0=RULE_BOOLEAN
		{
			createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBooleanTerminalRuleCall_2_0(), "value"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"value",
	        		lv_value_2_0, 
	        		"Boolean", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(	'(' 
    {
        createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
    }
(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
	    }
		lv_annotations_4_0=ruleAnnotation		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		add(
	       			$current, 
	       			"annotations",
	        		lv_annotations_4_0, 
	        		"Annotation", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
)*	')' 
    {
        createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
    }
)?)
;





// Entry rule entryRuleKeyIntValueAnnotation
entryRuleKeyIntValueAnnotation returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getKeyIntValueAnnotationRule(), currentNode); }
	 iv_ruleKeyIntValueAnnotation=ruleKeyIntValueAnnotation 
	 { $current=$iv_ruleKeyIntValueAnnotation.current; } 
	 EOF 
;

// Rule KeyIntValueAnnotation
ruleKeyIntValueAnnotation returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(	'@' 
    {
        createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
    }
(
(
		lv_name_1_0=RULE_ID
		{
			createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"name",
	        		lv_name_1_0, 
	        		"ID", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(
(
		lv_value_2_0=RULE_INT
		{
			createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0(), "value"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"value",
	        		lv_value_2_0, 
	        		"INT", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(	'(' 
    {
        createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
    }
(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
	    }
		lv_annotations_4_0=ruleAnnotation		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		add(
	       			$current, 
	       			"annotations",
	        		lv_annotations_4_0, 
	        		"Annotation", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
)*	')' 
    {
        createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
    }
)?)
;





// Entry rule entryRuleKeyFloatValueAnnotation
entryRuleKeyFloatValueAnnotation returns [EObject current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getKeyFloatValueAnnotationRule(), currentNode); }
	 iv_ruleKeyFloatValueAnnotation=ruleKeyFloatValueAnnotation 
	 { $current=$iv_ruleKeyFloatValueAnnotation.current; } 
	 EOF 
;

// Rule KeyFloatValueAnnotation
ruleKeyFloatValueAnnotation returns [EObject current=null] 
    @init { EObject temp=null; setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
    	lastConsumedNode = currentNode;
    }:
(	'@' 
    {
        createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
    }
(
(
		lv_name_1_0=RULE_ID
		{
			createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"name",
	        		lv_name_1_0, 
	        		"ID", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(
(
		lv_value_2_0=RULE_FLOAT
		{
			createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getValueFloatTerminalRuleCall_2_0(), "value"); 
		}
		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode, $current);
	        }
	        try {
	       		set(
	       			$current, 
	       			"value",
	        		lv_value_2_0, 
	        		"Float", 
	        		lastConsumedNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	    }

)
)(	'(' 
    {
        createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
    }
(
(
		{ 
	        currentNode=createCompositeNode(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
	    }
		lv_annotations_4_0=ruleAnnotation		{
	        if ($current==null) {
	            $current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
	            associateNodeWithAstElement(currentNode.getParent(), $current);
	        }
	        try {
	       		add(
	       			$current, 
	       			"annotations",
	        		lv_annotations_4_0, 
	        		"Annotation", 
	        		currentNode);
	        } catch (ValueConverterException vce) {
				handleValueConverterException(vce);
	        }
	        currentNode = currentNode.getParent();
	    }

)
)*	')' 
    {
        createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
    }
)?)
;







// Entry rule entryRuleEString
entryRuleEString returns [String current=null] 
	:
	{ currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); } 
	 iv_ruleEString=ruleEString 
	 { $current=$iv_ruleEString.current.getText(); }  
	 EOF 
;

// Rule EString
ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { setCurrentLookahead(); resetLookahead(); 
    }
    @after { resetLookahead(); 
	    lastConsumedNode = currentNode;
    }:
(    this_STRING_0=RULE_STRING    {
		$current.merge(this_STRING_0);
    }

    { 
    createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
    }

    |    this_ID_1=RULE_ID    {
		$current.merge(this_ID_1);
    }

    { 
    createLeafNode(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1(), null); 
    }
)
    ;





RULE_COMMENT_ANNOTATION : '/**' ( options {greedy=false;} : . )*'*/';

RULE_ML_COMMENT : '/*' ~('*') ( options {greedy=false;} : . )*'*/';

RULE_INT : '-'? ('0'..'9')+;

RULE_FLOAT : ('-'? ('0'..'9')+ '.' ('0'..'9')* (('e'|'E') ('+'|'-')? ('0'..'9')+)? 'f'?|'-'? ('0'..'9')+ 'f');

RULE_BOOLEAN : ('true'|'false');

RULE_STRING : '"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"';

RULE_TYPEID : '[' ('a'..'z'|'A'..'Z'|'_'|'.') ('a'..'z'|'A'..'Z'|'_'|'.'|'0'..'9')* ']';

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


