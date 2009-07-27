package de.cau.cs.kieler.ksbase.parser.antlr.internal; 

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
import de.cau.cs.kieler.ksbase.services.FeatureDefinitionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalFeatureDefinitionParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'ModelPackage = '", "';'", "'ModelPackagePath = '", "'FeatureMenuTitle = '", "'TransformationFile = '", "'Feature'", "'{'", "'File = '", "'MethodName = '", "'NumParameter = '", "'Parameter = '", "'MenuEntry = '", "'}'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

        public InternalFeatureDefinitionParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g"; }


     
     	private FeatureDefinitionGrammarAccess grammarAccess;
     	
        public InternalFeatureDefinitionParser(TokenStream input, IAstFactory factory, FeatureDefinitionGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "FeatureDefinitions";	
       	} 



    // $ANTLR start entryRuleFeatureDefinitions
    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:72:1: entryRuleFeatureDefinitions returns [EObject current=null] : iv_ruleFeatureDefinitions= ruleFeatureDefinitions EOF ;
    public final EObject entryRuleFeatureDefinitions() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureDefinitions = null;


        try {
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:72:60: (iv_ruleFeatureDefinitions= ruleFeatureDefinitions EOF )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:73:2: iv_ruleFeatureDefinitions= ruleFeatureDefinitions EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFeatureDefinitionsRule(), currentNode); 
            pushFollow(FOLLOW_ruleFeatureDefinitions_in_entryRuleFeatureDefinitions73);
            iv_ruleFeatureDefinitions=ruleFeatureDefinitions();
            _fsp--;

             current =iv_ruleFeatureDefinitions; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureDefinitions83); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleFeatureDefinitions


    // $ANTLR start ruleFeatureDefinitions
    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:80:1: ruleFeatureDefinitions returns [EObject current=null] : ( 'ModelPackage = ' (lv_modelName_1= RULE_STRING ) ';' ( 'ModelPackagePath = ' (lv_modelPath_4= RULE_STRING ) ';' )? 'FeatureMenuTitle = ' (lv_featureMenuTitle_7= RULE_STRING ) ';' ( 'TransformationFile = ' (lv_featureFile_10= RULE_STRING ) ';' )? (lv_elements_12= ruleFeatureType )* ) ;
    public final EObject ruleFeatureDefinitions() throws RecognitionException {
        EObject current = null;

        Token lv_modelName_1=null;
        Token lv_modelPath_4=null;
        Token lv_featureMenuTitle_7=null;
        Token lv_featureFile_10=null;
        EObject lv_elements_12 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:85:6: ( ( 'ModelPackage = ' (lv_modelName_1= RULE_STRING ) ';' ( 'ModelPackagePath = ' (lv_modelPath_4= RULE_STRING ) ';' )? 'FeatureMenuTitle = ' (lv_featureMenuTitle_7= RULE_STRING ) ';' ( 'TransformationFile = ' (lv_featureFile_10= RULE_STRING ) ';' )? (lv_elements_12= ruleFeatureType )* ) )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:86:1: ( 'ModelPackage = ' (lv_modelName_1= RULE_STRING ) ';' ( 'ModelPackagePath = ' (lv_modelPath_4= RULE_STRING ) ';' )? 'FeatureMenuTitle = ' (lv_featureMenuTitle_7= RULE_STRING ) ';' ( 'TransformationFile = ' (lv_featureFile_10= RULE_STRING ) ';' )? (lv_elements_12= ruleFeatureType )* )
            {
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:86:1: ( 'ModelPackage = ' (lv_modelName_1= RULE_STRING ) ';' ( 'ModelPackagePath = ' (lv_modelPath_4= RULE_STRING ) ';' )? 'FeatureMenuTitle = ' (lv_featureMenuTitle_7= RULE_STRING ) ';' ( 'TransformationFile = ' (lv_featureFile_10= RULE_STRING ) ';' )? (lv_elements_12= ruleFeatureType )* )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:86:2: 'ModelPackage = ' (lv_modelName_1= RULE_STRING ) ';' ( 'ModelPackagePath = ' (lv_modelPath_4= RULE_STRING ) ';' )? 'FeatureMenuTitle = ' (lv_featureMenuTitle_7= RULE_STRING ) ';' ( 'TransformationFile = ' (lv_featureFile_10= RULE_STRING ) ';' )? (lv_elements_12= ruleFeatureType )*
            {
            match(input,11,FOLLOW_11_in_ruleFeatureDefinitions117); 

                    createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getModelPackageKeyword_0(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:90:1: (lv_modelName_1= RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:92:6: lv_modelName_1= RULE_STRING
            {
            lv_modelName_1=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFeatureDefinitions139); 

            		createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getModelNameSTRINGTerminalRuleCall_1_0(), "modelName"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFeatureDefinitionsRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "modelName", lv_modelName_1, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,12,FOLLOW_12_in_ruleFeatureDefinitions156); 

                    createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_2(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:114:1: ( 'ModelPackagePath = ' (lv_modelPath_4= RULE_STRING ) ';' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==13) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:114:2: 'ModelPackagePath = ' (lv_modelPath_4= RULE_STRING ) ';'
                    {
                    match(input,13,FOLLOW_13_in_ruleFeatureDefinitions166); 

                            createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getModelPackagePathKeyword_3_0(), null); 
                        
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:118:1: (lv_modelPath_4= RULE_STRING )
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:120:6: lv_modelPath_4= RULE_STRING
                    {
                    lv_modelPath_4=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFeatureDefinitions188); 

                    		createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getModelPathSTRINGTerminalRuleCall_3_1_0(), "modelPath"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFeatureDefinitionsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "modelPath", lv_modelPath_4, "STRING", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleFeatureDefinitions205); 

                            createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_3_2(), null); 
                        

                    }
                    break;

            }

            match(input,14,FOLLOW_14_in_ruleFeatureDefinitions216); 

                    createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleKeyword_4(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:146:1: (lv_featureMenuTitle_7= RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:148:6: lv_featureMenuTitle_7= RULE_STRING
            {
            lv_featureMenuTitle_7=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFeatureDefinitions238); 

            		createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getFeatureMenuTitleSTRINGTerminalRuleCall_5_0(), "featureMenuTitle"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFeatureDefinitionsRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "featureMenuTitle", lv_featureMenuTitle_7, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,12,FOLLOW_12_in_ruleFeatureDefinitions255); 

                    createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_6(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:170:1: ( 'TransformationFile = ' (lv_featureFile_10= RULE_STRING ) ';' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==15) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:170:2: 'TransformationFile = ' (lv_featureFile_10= RULE_STRING ) ';'
                    {
                    match(input,15,FOLLOW_15_in_ruleFeatureDefinitions265); 

                            createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getTransformationFileKeyword_7_0(), null); 
                        
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:174:1: (lv_featureFile_10= RULE_STRING )
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:176:6: lv_featureFile_10= RULE_STRING
                    {
                    lv_featureFile_10=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFeatureDefinitions287); 

                    		createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getFeatureFileSTRINGTerminalRuleCall_7_1_0(), "featureFile"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFeatureDefinitionsRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "featureFile", lv_featureFile_10, "STRING", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleFeatureDefinitions304); 

                            createLeafNode(grammarAccess.getFeatureDefinitionsAccess().getSemicolonKeyword_7_2(), null); 
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:198:3: (lv_elements_12= ruleFeatureType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:201:6: lv_elements_12= ruleFeatureType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getFeatureDefinitionsAccess().getElementsFeatureTypeParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleFeatureType_in_ruleFeatureDefinitions340);
            	    lv_elements_12=ruleFeatureType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getFeatureDefinitionsRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "elements", lv_elements_12, "FeatureType", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleFeatureDefinitions


    // $ANTLR start entryRuleFeatureType
    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:226:1: entryRuleFeatureType returns [EObject current=null] : iv_ruleFeatureType= ruleFeatureType EOF ;
    public final EObject entryRuleFeatureType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureType = null;


        try {
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:226:53: (iv_ruleFeatureType= ruleFeatureType EOF )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:227:2: iv_ruleFeatureType= ruleFeatureType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getFeatureTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleFeatureType_in_entryRuleFeatureType378);
            iv_ruleFeatureType=ruleFeatureType();
            _fsp--;

             current =iv_ruleFeatureType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureType388); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleFeatureType


    // $ANTLR start ruleFeatureType
    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:234:1: ruleFeatureType returns [EObject current=null] : ( 'Feature' (lv_name_1= RULE_ID ) '{' ( 'File = ' (lv_fileName_4= RULE_STRING ) ';' )? 'MethodName = ' (lv_methodName_7= RULE_STRING ) ';' ( 'NumParameter = ' (lv_numParameter_10= RULE_INT ) ';' )? ( 'Parameter = ' (lv_parameter_13= RULE_STRING ) ';' )+ 'MenuEntry = ' (lv_menuEntry_16= RULE_STRING ) ';' '}' ) ;
    public final EObject ruleFeatureType() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;
        Token lv_fileName_4=null;
        Token lv_methodName_7=null;
        Token lv_numParameter_10=null;
        Token lv_parameter_13=null;
        Token lv_menuEntry_16=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:239:6: ( ( 'Feature' (lv_name_1= RULE_ID ) '{' ( 'File = ' (lv_fileName_4= RULE_STRING ) ';' )? 'MethodName = ' (lv_methodName_7= RULE_STRING ) ';' ( 'NumParameter = ' (lv_numParameter_10= RULE_INT ) ';' )? ( 'Parameter = ' (lv_parameter_13= RULE_STRING ) ';' )+ 'MenuEntry = ' (lv_menuEntry_16= RULE_STRING ) ';' '}' ) )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:240:1: ( 'Feature' (lv_name_1= RULE_ID ) '{' ( 'File = ' (lv_fileName_4= RULE_STRING ) ';' )? 'MethodName = ' (lv_methodName_7= RULE_STRING ) ';' ( 'NumParameter = ' (lv_numParameter_10= RULE_INT ) ';' )? ( 'Parameter = ' (lv_parameter_13= RULE_STRING ) ';' )+ 'MenuEntry = ' (lv_menuEntry_16= RULE_STRING ) ';' '}' )
            {
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:240:1: ( 'Feature' (lv_name_1= RULE_ID ) '{' ( 'File = ' (lv_fileName_4= RULE_STRING ) ';' )? 'MethodName = ' (lv_methodName_7= RULE_STRING ) ';' ( 'NumParameter = ' (lv_numParameter_10= RULE_INT ) ';' )? ( 'Parameter = ' (lv_parameter_13= RULE_STRING ) ';' )+ 'MenuEntry = ' (lv_menuEntry_16= RULE_STRING ) ';' '}' )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:240:2: 'Feature' (lv_name_1= RULE_ID ) '{' ( 'File = ' (lv_fileName_4= RULE_STRING ) ';' )? 'MethodName = ' (lv_methodName_7= RULE_STRING ) ';' ( 'NumParameter = ' (lv_numParameter_10= RULE_INT ) ';' )? ( 'Parameter = ' (lv_parameter_13= RULE_STRING ) ';' )+ 'MenuEntry = ' (lv_menuEntry_16= RULE_STRING ) ';' '}'
            {
            match(input,16,FOLLOW_16_in_ruleFeatureType422); 

                    createLeafNode(grammarAccess.getFeatureTypeAccess().getFeatureKeyword_0(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:244:1: (lv_name_1= RULE_ID )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:246:6: lv_name_1= RULE_ID
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFeatureType444); 

            		createLeafNode(grammarAccess.getFeatureTypeAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFeatureTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "ID", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,17,FOLLOW_17_in_ruleFeatureType461); 

                    createLeafNode(grammarAccess.getFeatureTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:268:1: ( 'File = ' (lv_fileName_4= RULE_STRING ) ';' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:268:2: 'File = ' (lv_fileName_4= RULE_STRING ) ';'
                    {
                    match(input,18,FOLLOW_18_in_ruleFeatureType471); 

                            createLeafNode(grammarAccess.getFeatureTypeAccess().getFileKeyword_3_0(), null); 
                        
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:272:1: (lv_fileName_4= RULE_STRING )
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:274:6: lv_fileName_4= RULE_STRING
                    {
                    lv_fileName_4=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFeatureType493); 

                    		createLeafNode(grammarAccess.getFeatureTypeAccess().getFileNameSTRINGTerminalRuleCall_3_1_0(), "fileName"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFeatureTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "fileName", lv_fileName_4, "STRING", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleFeatureType510); 

                            createLeafNode(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_3_2(), null); 
                        

                    }
                    break;

            }

            match(input,19,FOLLOW_19_in_ruleFeatureType521); 

                    createLeafNode(grammarAccess.getFeatureTypeAccess().getMethodNameKeyword_4(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:300:1: (lv_methodName_7= RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:302:6: lv_methodName_7= RULE_STRING
            {
            lv_methodName_7=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFeatureType543); 

            		createLeafNode(grammarAccess.getFeatureTypeAccess().getMethodNameSTRINGTerminalRuleCall_5_0(), "methodName"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFeatureTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "methodName", lv_methodName_7, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,12,FOLLOW_12_in_ruleFeatureType560); 

                    createLeafNode(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_6(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:324:1: ( 'NumParameter = ' (lv_numParameter_10= RULE_INT ) ';' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==20) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:324:2: 'NumParameter = ' (lv_numParameter_10= RULE_INT ) ';'
                    {
                    match(input,20,FOLLOW_20_in_ruleFeatureType570); 

                            createLeafNode(grammarAccess.getFeatureTypeAccess().getNumParameterKeyword_7_0(), null); 
                        
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:328:1: (lv_numParameter_10= RULE_INT )
                    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:330:6: lv_numParameter_10= RULE_INT
                    {
                    lv_numParameter_10=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleFeatureType592); 

                    		createLeafNode(grammarAccess.getFeatureTypeAccess().getNumParameterINTTerminalRuleCall_7_1_0(), "numParameter"); 
                    	

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getFeatureTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		add(current, "numParameter", lv_numParameter_10, "INT", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }

                    match(input,12,FOLLOW_12_in_ruleFeatureType609); 

                            createLeafNode(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_7_2(), null); 
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:352:3: ( 'Parameter = ' (lv_parameter_13= RULE_STRING ) ';' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==21) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:352:4: 'Parameter = ' (lv_parameter_13= RULE_STRING ) ';'
            	    {
            	    match(input,21,FOLLOW_21_in_ruleFeatureType621); 

            	            createLeafNode(grammarAccess.getFeatureTypeAccess().getParameterKeyword_8_0(), null); 
            	        
            	    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:356:1: (lv_parameter_13= RULE_STRING )
            	    // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:358:6: lv_parameter_13= RULE_STRING
            	    {
            	    lv_parameter_13=(Token)input.LT(1);
            	    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFeatureType643); 

            	    		createLeafNode(grammarAccess.getFeatureTypeAccess().getParameterSTRINGTerminalRuleCall_8_1_0(), "parameter"); 
            	    	

            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getFeatureTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode, current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "parameter", lv_parameter_13, "STRING", lastConsumedNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	    

            	    }

            	    match(input,12,FOLLOW_12_in_ruleFeatureType660); 

            	            createLeafNode(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_8_2(), null); 
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            match(input,22,FOLLOW_22_in_ruleFeatureType671); 

                    createLeafNode(grammarAccess.getFeatureTypeAccess().getMenuEntryKeyword_9(), null); 
                
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:384:1: (lv_menuEntry_16= RULE_STRING )
            // ../de.cau.cs.kieler.ksbase.dsl/src-gen/de/cau/cs/kieler/ksbase/parser/antlr/internal/InternalFeatureDefinition.g:386:6: lv_menuEntry_16= RULE_STRING
            {
            lv_menuEntry_16=(Token)input.LT(1);
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleFeatureType693); 

            		createLeafNode(grammarAccess.getFeatureTypeAccess().getMenuEntrySTRINGTerminalRuleCall_10_0(), "menuEntry"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getFeatureTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "menuEntry", lv_menuEntry_16, "STRING", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,12,FOLLOW_12_in_ruleFeatureType710); 

                    createLeafNode(grammarAccess.getFeatureTypeAccess().getSemicolonKeyword_11(), null); 
                
            match(input,23,FOLLOW_23_in_ruleFeatureType719); 

                    createLeafNode(grammarAccess.getFeatureTypeAccess().getRightCurlyBracketKeyword_12(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleFeatureType


 

    public static final BitSet FOLLOW_ruleFeatureDefinitions_in_entryRuleFeatureDefinitions73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureDefinitions83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleFeatureDefinitions117 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFeatureDefinitions139 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureDefinitions156 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleFeatureDefinitions166 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFeatureDefinitions188 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureDefinitions205 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleFeatureDefinitions216 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFeatureDefinitions238 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureDefinitions255 = new BitSet(new long[]{0x0000000000018002L});
    public static final BitSet FOLLOW_15_in_ruleFeatureDefinitions265 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFeatureDefinitions287 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureDefinitions304 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleFeatureType_in_ruleFeatureDefinitions340 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleFeatureType_in_entryRuleFeatureType378 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureType388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleFeatureType422 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFeatureType444 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleFeatureType461 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_18_in_ruleFeatureType471 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFeatureType493 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureType510 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleFeatureType521 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFeatureType543 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureType560 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_20_in_ruleFeatureType570 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleFeatureType592 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureType609 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleFeatureType621 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFeatureType643 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureType660 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_22_in_ruleFeatureType671 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleFeatureType693 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleFeatureType710 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleFeatureType719 = new BitSet(new long[]{0x0000000000000002L});

}