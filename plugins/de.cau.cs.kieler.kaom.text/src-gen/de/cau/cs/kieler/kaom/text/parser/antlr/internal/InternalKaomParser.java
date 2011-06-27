package de.cau.cs.kieler.kaom.text.parser.antlr.internal; 

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
import de.cau.cs.kieler.kaom.text.services.KaomGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalKaomParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_COMMENT_ANNOTATION", "RULE_BOOLEAN", "RULE_INT", "RULE_FLOAT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'entity'", "'{'", "'}'", "';'", "'link'", "'to'", "'port'", "'relation'", "'@'", "'('", "')'", "'['", "']'", "'import'", "'.'"
    };
    public static final int RULE_BOOLEAN=7;
    public static final int RULE_ID=4;
    public static final int RULE_STRING=5;
    public static final int RULE_ANY_OTHER=13;
    public static final int RULE_INT=8;
    public static final int RULE_WS=12;
    public static final int RULE_FLOAT=9;
    public static final int RULE_SL_COMMENT=11;
    public static final int EOF=-1;
    public static final int RULE_COMMENT_ANNOTATION=6;
    public static final int RULE_ML_COMMENT=10;

        public InternalKaomParser(TokenStream input) {
            super(input);
            ruleMemo = new HashMap[74+1];
         }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */
     
     	private KaomGrammarAccess grammarAccess;
     	
        public InternalKaomParser(TokenStream input, IAstFactory factory, KaomGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "TopLevelEntity";	
       	}
       	
       	@Override
       	protected KaomGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRuleTopLevelEntity
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:83:1: entryRuleTopLevelEntity returns [EObject current=null] : iv_ruleTopLevelEntity= ruleTopLevelEntity EOF ;
    public final EObject entryRuleTopLevelEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTopLevelEntity = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:84:2: (iv_ruleTopLevelEntity= ruleTopLevelEntity EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:85:2: iv_ruleTopLevelEntity= ruleTopLevelEntity EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getTopLevelEntityRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity81);
            iv_ruleTopLevelEntity=ruleTopLevelEntity();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleTopLevelEntity; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTopLevelEntity91); if (failed) return current;

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
    // $ANTLR end entryRuleTopLevelEntity


    // $ANTLR start ruleTopLevelEntity
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:92:1: ruleTopLevelEntity returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' ) )? ) ;
    public final EObject ruleTopLevelEntity() throws RecognitionException {
        EObject current = null;

        Token lv_id_4_0=null;
        Token lv_name_5_0=null;
        EObject lv_annotations_1_0 = null;

        EObject lv_annotations_2_0 = null;

        EObject lv_childEntities_7_0 = null;

        EObject lv_childLinks_8_0 = null;

        EObject lv_childPorts_9_0 = null;

        EObject lv_childRelations_10_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:97:6: ( ( () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' ) )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:98:1: ( () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' ) )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:98:1: ( () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' ) )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:98:2: () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' ) )?
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:98:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:99:2: 
            {
            if ( backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( backtracking==0 ) {
               
                      temp=factory.create(grammarAccess.getTopLevelEntityAccess().getEntityAction_0().getType().getClassifier());
                      current = temp; 
                      temp = null;
                      CompositeNode newNode = createCompositeNode(grammarAccess.getTopLevelEntityAccess().getEntityAction_0(), currentNode.getParent());
                  newNode.getChildren().add(currentNode);
                  moveLookaheadInfo(currentNode, newNode);
                  currentNode = newNode; 
                      associateNodeWithAstElement(currentNode, current); 
                  
            }

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:112:2: ( (lv_annotations_1_0= ruleImportAnnotation ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==27) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:113:1: (lv_annotations_1_0= ruleImportAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:113:1: (lv_annotations_1_0= ruleImportAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:114:3: lv_annotations_1_0= ruleImportAnnotation
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getAnnotationsImportAnnotationParserRuleCall_1_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleImportAnnotation_in_ruleTopLevelEntity149);
            	    lv_annotations_1_0=ruleImportAnnotation();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"annotations",
            	      	        		lv_annotations_1_0, 
            	      	        		"ImportAnnotation", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:136:3: ( ( (lv_annotations_2_0= ruleAnnotation ) )* 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_COMMENT_ANNOTATION||LA6_0==14||LA6_0==22) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:136:4: ( (lv_annotations_2_0= ruleAnnotation ) )* 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:136:4: ( (lv_annotations_2_0= ruleAnnotation ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==RULE_COMMENT_ANNOTATION||LA2_0==22) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:137:1: (lv_annotations_2_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:137:1: (lv_annotations_2_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:138:3: lv_annotations_2_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getAnnotationsAnnotationParserRuleCall_2_0_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTopLevelEntity172);
                    	    lv_annotations_2_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_2_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    match(input,14,FollowSets000.FOLLOW_14_in_ruleTopLevelEntity183); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTopLevelEntityAccess().getEntityKeyword_2_1(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:164:1: ( (lv_id_4_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:165:1: (lv_id_4_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:165:1: (lv_id_4_0= RULE_ID )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:166:3: lv_id_4_0= RULE_ID
                    {
                    lv_id_4_0=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleTopLevelEntity200); if (failed) return current;
                    if ( backtracking==0 ) {

                      			createLeafNode(grammarAccess.getTopLevelEntityAccess().getIdIDTerminalRuleCall_2_2_0(), "id"); 
                      		
                    }
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode, current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"id",
                      	        		lv_id_4_0, 
                      	        		"ID", 
                      	        		lastConsumedNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:188:2: ( (lv_name_5_0= RULE_STRING ) )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==RULE_STRING) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:189:1: (lv_name_5_0= RULE_STRING )
                            {
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:189:1: (lv_name_5_0= RULE_STRING )
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:190:3: lv_name_5_0= RULE_STRING
                            {
                            lv_name_5_0=(Token)input.LT(1);
                            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleTopLevelEntity222); if (failed) return current;
                            if ( backtracking==0 ) {

                              			createLeafNode(grammarAccess.getTopLevelEntityAccess().getNameSTRINGTerminalRuleCall_2_3_0(), "name"); 
                              		
                            }
                            if ( backtracking==0 ) {

                              	        if (current==null) {
                              	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                              	            associateNodeWithAstElement(currentNode, current);
                              	        }
                              	        try {
                              	       		set(
                              	       			current, 
                              	       			"name",
                              	        		lv_name_5_0, 
                              	        		"STRING", 
                              	        		lastConsumedNode);
                              	        } catch (ValueConverterException vce) {
                              				handleValueConverterException(vce);
                              	        }
                              	    
                            }

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:212:3: ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' )
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==15) ) {
                        alt5=1;
                    }
                    else if ( (LA5_0==17) ) {
                        alt5=2;
                    }
                    else {
                        if (backtracking>0) {failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("212:3: ( ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' ) | ';' )", 5, 0, input);

                        throw nvae;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:212:4: ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' )
                            {
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:212:4: ( '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}' )
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:212:6: '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* '}'
                            {
                            match(input,15,FollowSets000.FOLLOW_15_in_ruleTopLevelEntity240); if (failed) return current;
                            if ( backtracking==0 ) {

                                      createLeafNode(grammarAccess.getTopLevelEntityAccess().getLeftCurlyBracketKeyword_2_4_0_0(), null); 
                                  
                            }
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:216:1: ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )*
                            loop4:
                            do {
                                int alt4=5;
                                switch ( input.LA(1) ) {
                                case RULE_COMMENT_ANNOTATION:
                                    {
                                    int LA4_2 = input.LA(2);

                                    if ( (synpred4()) ) {
                                        alt4=1;
                                    }
                                    else if ( (synpred5()) ) {
                                        alt4=2;
                                    }
                                    else if ( (synpred6()) ) {
                                        alt4=3;
                                    }
                                    else if ( (synpred7()) ) {
                                        alt4=4;
                                    }


                                    }
                                    break;
                                case 22:
                                    {
                                    int LA4_3 = input.LA(2);

                                    if ( (synpred4()) ) {
                                        alt4=1;
                                    }
                                    else if ( (synpred5()) ) {
                                        alt4=2;
                                    }
                                    else if ( (synpred6()) ) {
                                        alt4=3;
                                    }
                                    else if ( (synpred7()) ) {
                                        alt4=4;
                                    }


                                    }
                                    break;
                                case 14:
                                    {
                                    alt4=1;
                                    }
                                    break;
                                case 18:
                                    {
                                    alt4=2;
                                    }
                                    break;
                                case 20:
                                    {
                                    alt4=3;
                                    }
                                    break;
                                case 21:
                                    {
                                    alt4=4;
                                    }
                                    break;

                                }

                                switch (alt4) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:216:2: ( (lv_childEntities_7_0= ruleEntity ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:216:2: ( (lv_childEntities_7_0= ruleEntity ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:217:1: (lv_childEntities_7_0= ruleEntity )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:217:1: (lv_childEntities_7_0= ruleEntity )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:218:3: lv_childEntities_7_0= ruleEntity
                            	    {
                            	    if ( backtracking==0 ) {
                            	       
                            	      	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_2_4_0_1_0_0(), currentNode); 
                            	      	    
                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_ruleEntity_in_ruleTopLevelEntity262);
                            	    lv_childEntities_7_0=ruleEntity();
                            	    _fsp--;
                            	    if (failed) return current;
                            	    if ( backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	      	        }
                            	      	        try {
                            	      	       		add(
                            	      	       			current, 
                            	      	       			"childEntities",
                            	      	        		lv_childEntities_7_0, 
                            	      	        		"Entity", 
                            	      	        		currentNode);
                            	      	        } catch (ValueConverterException vce) {
                            	      				handleValueConverterException(vce);
                            	      	        }
                            	      	        currentNode = currentNode.getParent();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;
                            	case 2 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:241:6: ( (lv_childLinks_8_0= ruleLink ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:241:6: ( (lv_childLinks_8_0= ruleLink ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:242:1: (lv_childLinks_8_0= ruleLink )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:242:1: (lv_childLinks_8_0= ruleLink )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:243:3: lv_childLinks_8_0= ruleLink
                            	    {
                            	    if ( backtracking==0 ) {
                            	       
                            	      	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_2_4_0_1_1_0(), currentNode); 
                            	      	    
                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleTopLevelEntity289);
                            	    lv_childLinks_8_0=ruleLink();
                            	    _fsp--;
                            	    if (failed) return current;
                            	    if ( backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	      	        }
                            	      	        try {
                            	      	       		add(
                            	      	       			current, 
                            	      	       			"childLinks",
                            	      	        		lv_childLinks_8_0, 
                            	      	        		"Link", 
                            	      	        		currentNode);
                            	      	        } catch (ValueConverterException vce) {
                            	      				handleValueConverterException(vce);
                            	      	        }
                            	      	        currentNode = currentNode.getParent();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;
                            	case 3 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:266:6: ( (lv_childPorts_9_0= rulePort ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:266:6: ( (lv_childPorts_9_0= rulePort ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:267:1: (lv_childPorts_9_0= rulePort )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:267:1: (lv_childPorts_9_0= rulePort )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:268:3: lv_childPorts_9_0= rulePort
                            	    {
                            	    if ( backtracking==0 ) {
                            	       
                            	      	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_2_4_0_1_2_0(), currentNode); 
                            	      	    
                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_rulePort_in_ruleTopLevelEntity316);
                            	    lv_childPorts_9_0=rulePort();
                            	    _fsp--;
                            	    if (failed) return current;
                            	    if ( backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	      	        }
                            	      	        try {
                            	      	       		add(
                            	      	       			current, 
                            	      	       			"childPorts",
                            	      	        		lv_childPorts_9_0, 
                            	      	        		"Port", 
                            	      	        		currentNode);
                            	      	        } catch (ValueConverterException vce) {
                            	      				handleValueConverterException(vce);
                            	      	        }
                            	      	        currentNode = currentNode.getParent();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;
                            	case 4 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:291:6: ( (lv_childRelations_10_0= ruleRelation ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:291:6: ( (lv_childRelations_10_0= ruleRelation ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:292:1: (lv_childRelations_10_0= ruleRelation )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:292:1: (lv_childRelations_10_0= ruleRelation )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:293:3: lv_childRelations_10_0= ruleRelation
                            	    {
                            	    if ( backtracking==0 ) {
                            	       
                            	      	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_2_4_0_1_3_0(), currentNode); 
                            	      	    
                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_ruleRelation_in_ruleTopLevelEntity343);
                            	    lv_childRelations_10_0=ruleRelation();
                            	    _fsp--;
                            	    if (failed) return current;
                            	    if ( backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = factory.create(grammarAccess.getTopLevelEntityRule().getType().getClassifier());
                            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                            	      	        }
                            	      	        try {
                            	      	       		add(
                            	      	       			current, 
                            	      	       			"childRelations",
                            	      	        		lv_childRelations_10_0, 
                            	      	        		"Relation", 
                            	      	        		currentNode);
                            	      	        } catch (ValueConverterException vce) {
                            	      				handleValueConverterException(vce);
                            	      	        }
                            	      	        currentNode = currentNode.getParent();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop4;
                                }
                            } while (true);

                            match(input,16,FollowSets000.FOLLOW_16_in_ruleTopLevelEntity355); if (failed) return current;
                            if ( backtracking==0 ) {

                                      createLeafNode(grammarAccess.getTopLevelEntityAccess().getRightCurlyBracketKeyword_2_4_0_2(), null); 
                                  
                            }

                            }


                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:320:7: ';'
                            {
                            match(input,17,FollowSets000.FOLLOW_17_in_ruleTopLevelEntity372); if (failed) return current;
                            if ( backtracking==0 ) {

                                      createLeafNode(grammarAccess.getTopLevelEntityAccess().getSemicolonKeyword_2_4_1(), null); 
                                  
                            }

                            }
                            break;

                    }


                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleTopLevelEntity


    // $ANTLR start entryRuleEntity
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:332:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:333:2: (iv_ruleEntity= ruleEntity EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:334:2: iv_ruleEntity= ruleEntity EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getEntityRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_entryRuleEntity411);
            iv_ruleEntity=ruleEntity();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleEntity; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEntity421); if (failed) return current;

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
    // $ANTLR end entryRuleEntity


    // $ANTLR start ruleEntity
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:341:1: ruleEntity returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        EObject lv_annotations_1_0 = null;

        EObject lv_childEntities_6_0 = null;

        EObject lv_childLinks_7_0 = null;

        EObject lv_childPorts_8_0 = null;

        EObject lv_childRelations_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:346:6: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:347:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:347:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:347:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:347:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:348:2: 
            {
            if ( backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( backtracking==0 ) {
               
                      temp=factory.create(grammarAccess.getEntityAccess().getEntityAction_0().getType().getClassifier());
                      current = temp; 
                      temp = null;
                      CompositeNode newNode = createCompositeNode(grammarAccess.getEntityAccess().getEntityAction_0(), currentNode.getParent());
                  newNode.getChildren().add(currentNode);
                  moveLookaheadInfo(currentNode, newNode);
                  currentNode = newNode; 
                      associateNodeWithAstElement(currentNode, current); 
                  
            }

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:361:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_COMMENT_ANNOTATION||LA7_0==22) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:362:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:362:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:363:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleEntity479);
            	    lv_annotations_1_0=ruleAnnotation();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"annotations",
            	      	        		lv_annotations_1_0, 
            	      	        		"Annotation", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match(input,14,FollowSets000.FOLLOW_14_in_ruleEntity490); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getEntityAccess().getEntityKeyword_2(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:389:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:390:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:390:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:391:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEntity507); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getEntityAccess().getIdIDTerminalRuleCall_3_0(), "id"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"id",
              	        		lv_id_3_0, 
              	        		"ID", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:413:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_STRING) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:414:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:414:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:415:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEntity529); if (failed) return current;
                    if ( backtracking==0 ) {

                      			createLeafNode(grammarAccess.getEntityAccess().getNameSTRINGTerminalRuleCall_4_0(), "name"); 
                      		
                    }
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode, current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"name",
                      	        		lv_name_4_0, 
                      	        		"STRING", 
                      	        		lastConsumedNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:437:3: ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==15) ) {
                alt10=1;
            }
            else if ( (LA10_0==17) ) {
                alt10=2;
            }
            else {
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("437:3: ( ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' ) | ';' )", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:437:4: ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:437:4: ( '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}' )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:437:6: '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* '}'
                    {
                    match(input,15,FollowSets000.FOLLOW_15_in_ruleEntity547); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_0_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:441:1: ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )*
                    loop9:
                    do {
                        int alt9=5;
                        switch ( input.LA(1) ) {
                        case RULE_COMMENT_ANNOTATION:
                            {
                            int LA9_2 = input.LA(2);

                            if ( (synpred12()) ) {
                                alt9=1;
                            }
                            else if ( (synpred13()) ) {
                                alt9=2;
                            }
                            else if ( (synpred14()) ) {
                                alt9=3;
                            }
                            else if ( (synpred15()) ) {
                                alt9=4;
                            }


                            }
                            break;
                        case 22:
                            {
                            int LA9_3 = input.LA(2);

                            if ( (synpred12()) ) {
                                alt9=1;
                            }
                            else if ( (synpred13()) ) {
                                alt9=2;
                            }
                            else if ( (synpred14()) ) {
                                alt9=3;
                            }
                            else if ( (synpred15()) ) {
                                alt9=4;
                            }


                            }
                            break;
                        case 14:
                            {
                            alt9=1;
                            }
                            break;
                        case 18:
                            {
                            alt9=2;
                            }
                            break;
                        case 20:
                            {
                            alt9=3;
                            }
                            break;
                        case 21:
                            {
                            alt9=4;
                            }
                            break;

                        }

                        switch (alt9) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:441:2: ( (lv_childEntities_6_0= ruleEntity ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:441:2: ( (lv_childEntities_6_0= ruleEntity ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:442:1: (lv_childEntities_6_0= ruleEntity )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:442:1: (lv_childEntities_6_0= ruleEntity )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:443:3: lv_childEntities_6_0= ruleEntity
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleEntity_in_ruleEntity569);
                    	    lv_childEntities_6_0=ruleEntity();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"childEntities",
                    	      	        		lv_childEntities_6_0, 
                    	      	        		"Entity", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:466:6: ( (lv_childLinks_7_0= ruleLink ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:466:6: ( (lv_childLinks_7_0= ruleLink ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:467:1: (lv_childLinks_7_0= ruleLink )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:467:1: (lv_childLinks_7_0= ruleLink )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:468:3: lv_childLinks_7_0= ruleLink
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleEntity596);
                    	    lv_childLinks_7_0=ruleLink();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"childLinks",
                    	      	        		lv_childLinks_7_0, 
                    	      	        		"Link", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:491:6: ( (lv_childPorts_8_0= rulePort ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:491:6: ( (lv_childPorts_8_0= rulePort ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:492:1: (lv_childPorts_8_0= rulePort )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:492:1: (lv_childPorts_8_0= rulePort )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:493:3: lv_childPorts_8_0= rulePort
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_rulePort_in_ruleEntity623);
                    	    lv_childPorts_8_0=rulePort();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"childPorts",
                    	      	        		lv_childPorts_8_0, 
                    	      	        		"Port", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:516:6: ( (lv_childRelations_9_0= ruleRelation ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:516:6: ( (lv_childRelations_9_0= ruleRelation ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:517:1: (lv_childRelations_9_0= ruleRelation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:517:1: (lv_childRelations_9_0= ruleRelation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:518:3: lv_childRelations_9_0= ruleRelation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleRelation_in_ruleEntity650);
                    	    lv_childRelations_9_0=ruleRelation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"childRelations",
                    	      	        		lv_childRelations_9_0, 
                    	      	        		"Relation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    match(input,16,FollowSets000.FOLLOW_16_in_ruleEntity662); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_0_2(), null); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:545:7: ';'
                    {
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleEntity679); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getEntityAccess().getSemicolonKeyword_5_1(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleEntity


    // $ANTLR start entryRuleLink
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:559:1: entryRuleLink returns [EObject current=null] : iv_ruleLink= ruleLink EOF ;
    public final EObject entryRuleLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLink = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:560:2: (iv_ruleLink= ruleLink EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:561:2: iv_ruleLink= ruleLink EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getLinkRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_entryRuleLink718);
            iv_ruleLink=ruleLink();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleLink; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLink728); if (failed) return current;

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
    // $ANTLR end entryRuleLink


    // $ANTLR start ruleLink
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:568:1: ruleLink returns [EObject current=null] : ( ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';' ) ;
    public final EObject ruleLink() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_0=null;
        EObject lv_annotations_0_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:573:6: ( ( ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:574:1: ( ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:574:1: ( ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:574:2: ( (lv_annotations_0_0= ruleAnnotation ) )* 'link' ( (lv_name_2_0= RULE_STRING ) )? ( ( RULE_ID ) ) 'to' ( ( RULE_ID ) ) ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:574:2: ( (lv_annotations_0_0= ruleAnnotation ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_COMMENT_ANNOTATION||LA11_0==22) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:575:1: (lv_annotations_0_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:575:1: (lv_annotations_0_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:576:3: lv_annotations_0_0= ruleAnnotation
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getLinkAccess().getAnnotationsAnnotationParserRuleCall_0_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleLink774);
            	    lv_annotations_0_0=ruleAnnotation();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getLinkRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"annotations",
            	      	        		lv_annotations_0_0, 
            	      	        		"Annotation", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            match(input,18,FollowSets000.FOLLOW_18_in_ruleLink785); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getLinkAccess().getLinkKeyword_1(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:602:1: ( (lv_name_2_0= RULE_STRING ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_STRING) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:603:1: (lv_name_2_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:603:1: (lv_name_2_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:604:3: lv_name_2_0= RULE_STRING
                    {
                    lv_name_2_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleLink802); if (failed) return current;
                    if ( backtracking==0 ) {

                      			createLeafNode(grammarAccess.getLinkAccess().getNameSTRINGTerminalRuleCall_2_0(), "name"); 
                      		
                    }
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getLinkRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode, current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"name",
                      	        		lv_name_2_0, 
                      	        		"STRING", 
                      	        		lastConsumedNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:626:3: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:627:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:627:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:628:3: RULE_ID
            {
            if ( backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( backtracking==0 ) {

              			if (current==null) {
              	            current = factory.create(grammarAccess.getLinkRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
                      
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleLink830); if (failed) return current;
            if ( backtracking==0 ) {

              		createLeafNode(grammarAccess.getLinkAccess().getSourceLinkableCrossReference_3_0(), "source"); 
              	
            }

            }


            }

            match(input,19,FollowSets000.FOLLOW_19_in_ruleLink840); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getLinkAccess().getToKeyword_4(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:647:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:648:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:648:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:649:3: RULE_ID
            {
            if ( backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( backtracking==0 ) {

              			if (current==null) {
              	            current = factory.create(grammarAccess.getLinkRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
                      
            }
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleLink862); if (failed) return current;
            if ( backtracking==0 ) {

              		createLeafNode(grammarAccess.getLinkAccess().getTargetLinkableCrossReference_5_0(), "target"); 
              	
            }

            }


            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleLink872); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getLinkAccess().getSemicolonKeyword_6(), null); 
                  
            }

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleLink


    // $ANTLR start entryRulePort
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:676:1: entryRulePort returns [EObject current=null] : iv_rulePort= rulePort EOF ;
    public final EObject entryRulePort() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePort = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:677:2: (iv_rulePort= rulePort EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:678:2: iv_rulePort= rulePort EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getPortRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_rulePort_in_entryRulePort908);
            iv_rulePort=rulePort();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_rulePort; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePort918); if (failed) return current;

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
    // $ANTLR end entryRulePort


    // $ANTLR start rulePort
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:685:1: rulePort returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' ) ;
    public final EObject rulePort() throws RecognitionException {
        EObject current = null;

        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        EObject lv_annotations_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:690:6: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:691:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:691:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:691:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:691:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:692:2: 
            {
            if ( backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( backtracking==0 ) {
               
                      temp=factory.create(grammarAccess.getPortAccess().getPortAction_0().getType().getClassifier());
                      current = temp; 
                      temp = null;
                      CompositeNode newNode = createCompositeNode(grammarAccess.getPortAccess().getPortAction_0(), currentNode.getParent());
                  newNode.getChildren().add(currentNode);
                  moveLookaheadInfo(currentNode, newNode);
                  currentNode = newNode; 
                      associateNodeWithAstElement(currentNode, current); 
                  
            }

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:705:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_COMMENT_ANNOTATION||LA13_0==22) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:706:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:706:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:707:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getPortAccess().getAnnotationsAnnotationParserRuleCall_1_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rulePort976);
            	    lv_annotations_1_0=ruleAnnotation();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"annotations",
            	      	        		lv_annotations_1_0, 
            	      	        		"Annotation", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match(input,20,FollowSets000.FOLLOW_20_in_rulePort987); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getPortAccess().getPortKeyword_2(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:733:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:734:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:734:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:735:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rulePort1004); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getPortAccess().getIdIDTerminalRuleCall_3_0(), "id"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"id",
              	        		lv_id_3_0, 
              	        		"ID", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:757:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_STRING) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:758:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:758:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:759:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rulePort1026); if (failed) return current;
                    if ( backtracking==0 ) {

                      			createLeafNode(grammarAccess.getPortAccess().getNameSTRINGTerminalRuleCall_4_0(), "name"); 
                      		
                    }
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getPortRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode, current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"name",
                      	        		lv_name_4_0, 
                      	        		"STRING", 
                      	        		lastConsumedNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	    
                    }

                    }


                    }
                    break;

            }

            match(input,17,FollowSets000.FOLLOW_17_in_rulePort1042); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getPortAccess().getSemicolonKeyword_5(), null); 
                  
            }

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end rulePort


    // $ANTLR start entryRuleRelation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:793:1: entryRuleRelation returns [EObject current=null] : iv_ruleRelation= ruleRelation EOF ;
    public final EObject entryRuleRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:794:2: (iv_ruleRelation= ruleRelation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:795:2: iv_ruleRelation= ruleRelation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getRelationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_entryRuleRelation1078);
            iv_ruleRelation=ruleRelation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleRelation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleRelation1088); if (failed) return current;

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
    // $ANTLR end entryRuleRelation


    // $ANTLR start ruleRelation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:802:1: ruleRelation returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' ) ;
    public final EObject ruleRelation() throws RecognitionException {
        EObject current = null;

        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        EObject lv_annotations_1_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:807:6: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:808:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:808:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:808:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:808:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:809:2: 
            {
            if ( backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( backtracking==0 ) {
               
                      temp=factory.create(grammarAccess.getRelationAccess().getRelationAction_0().getType().getClassifier());
                      current = temp; 
                      temp = null;
                      CompositeNode newNode = createCompositeNode(grammarAccess.getRelationAccess().getRelationAction_0(), currentNode.getParent());
                  newNode.getChildren().add(currentNode);
                  moveLookaheadInfo(currentNode, newNode);
                  currentNode = newNode; 
                      associateNodeWithAstElement(currentNode, current); 
                  
            }

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:822:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_COMMENT_ANNOTATION||LA15_0==22) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:823:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:823:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:824:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	    if ( backtracking==0 ) {
            	       
            	      	        currentNode=createCompositeNode(grammarAccess.getRelationAccess().getAnnotationsAnnotationParserRuleCall_1_0(), currentNode); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleRelation1146);
            	    lv_annotations_1_0=ruleAnnotation();
            	    _fsp--;
            	    if (failed) return current;
            	    if ( backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
            	      	            associateNodeWithAstElement(currentNode.getParent(), current);
            	      	        }
            	      	        try {
            	      	       		add(
            	      	       			current, 
            	      	       			"annotations",
            	      	        		lv_annotations_1_0, 
            	      	        		"Annotation", 
            	      	        		currentNode);
            	      	        } catch (ValueConverterException vce) {
            	      				handleValueConverterException(vce);
            	      	        }
            	      	        currentNode = currentNode.getParent();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            match(input,21,FollowSets000.FOLLOW_21_in_ruleRelation1157); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getRelationAccess().getRelationKeyword_2(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:850:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:851:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:851:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:852:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleRelation1174); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getRelationAccess().getIdIDTerminalRuleCall_3_0(), "id"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"id",
              	        		lv_id_3_0, 
              	        		"ID", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:874:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_STRING) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:875:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:875:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:876:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleRelation1196); if (failed) return current;
                    if ( backtracking==0 ) {

                      			createLeafNode(grammarAccess.getRelationAccess().getNameSTRINGTerminalRuleCall_4_0(), "name"); 
                      		
                    }
                    if ( backtracking==0 ) {

                      	        if (current==null) {
                      	            current = factory.create(grammarAccess.getRelationRule().getType().getClassifier());
                      	            associateNodeWithAstElement(currentNode, current);
                      	        }
                      	        try {
                      	       		set(
                      	       			current, 
                      	       			"name",
                      	        		lv_name_4_0, 
                      	        		"STRING", 
                      	        		lastConsumedNode);
                      	        } catch (ValueConverterException vce) {
                      				handleValueConverterException(vce);
                      	        }
                      	    
                    }

                    }


                    }
                    break;

            }

            match(input,17,FollowSets000.FOLLOW_17_in_ruleRelation1212); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getRelationAccess().getSemicolonKeyword_5(), null); 
                  
            }

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleRelation


    // $ANTLR start entryRuleAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:910:1: entryRuleAnnotation returns [EObject current=null] : iv_ruleAnnotation= ruleAnnotation EOF ;
    public final EObject entryRuleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:911:2: (iv_ruleAnnotation= ruleAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:912:2: iv_ruleAnnotation= ruleAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_entryRuleAnnotation1248);
            iv_ruleAnnotation=ruleAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleAnnotation1258); if (failed) return current;

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
    // $ANTLR end entryRuleAnnotation


    // $ANTLR start ruleAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:919:1: ruleAnnotation returns [EObject current=null] : (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation ) ;
    public final EObject ruleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject this_CommentAnnotation_0 = null;

        EObject this_TagAnnotation_1 = null;

        EObject this_KeyStringValueAnnotation_2 = null;

        EObject this_TypedKeyStringValueAnnotation_3 = null;

        EObject this_KeyBooleanValueAnnotation_4 = null;

        EObject this_KeyIntValueAnnotation_5 = null;

        EObject this_KeyFloatValueAnnotation_6 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:924:6: ( (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:925:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:925:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )
            int alt17=7;
            alt17 = dfa17.predict(input);
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:926:2: this_CommentAnnotation_0= ruleCommentAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_ruleAnnotation1308);
                    this_CommentAnnotation_0=ruleCommentAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_CommentAnnotation_0; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:939:2: this_TagAnnotation_1= ruleTagAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_ruleAnnotation1338);
                    this_TagAnnotation_1=ruleTagAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_TagAnnotation_1; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:952:2: this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_ruleAnnotation1368);
                    this_KeyStringValueAnnotation_2=ruleKeyStringValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_KeyStringValueAnnotation_2; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:965:2: this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getTypedKeyStringValueAnnotationParserRuleCall_3(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTypedKeyStringValueAnnotation_in_ruleAnnotation1398);
                    this_TypedKeyStringValueAnnotation_3=ruleTypedKeyStringValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_TypedKeyStringValueAnnotation_3; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:978:2: this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_4(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_ruleAnnotation1428);
                    this_KeyBooleanValueAnnotation_4=ruleKeyBooleanValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_KeyBooleanValueAnnotation_4; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:991:2: this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_5(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_ruleAnnotation1458);
                    this_KeyIntValueAnnotation_5=ruleKeyIntValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_KeyIntValueAnnotation_5; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1004:2: this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation
                    {
                    if ( backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( backtracking==0 ) {
                       
                              currentNode=createCompositeNode(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_6(), currentNode); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_ruleAnnotation1488);
                    this_KeyFloatValueAnnotation_6=ruleKeyFloatValueAnnotation();
                    _fsp--;
                    if (failed) return current;
                    if ( backtracking==0 ) {
                       
                              current = this_KeyFloatValueAnnotation_6; 
                              currentNode = currentNode.getParent();
                          
                    }

                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleAnnotation


    // $ANTLR start entryRuleCommentAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1023:1: entryRuleCommentAnnotation returns [EObject current=null] : iv_ruleCommentAnnotation= ruleCommentAnnotation EOF ;
    public final EObject entryRuleCommentAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommentAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1024:2: (iv_ruleCommentAnnotation= ruleCommentAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1025:2: iv_ruleCommentAnnotation= ruleCommentAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getCommentAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation1523);
            iv_ruleCommentAnnotation=ruleCommentAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleCommentAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCommentAnnotation1533); if (failed) return current;

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
    // $ANTLR end entryRuleCommentAnnotation


    // $ANTLR start ruleCommentAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1032:1: ruleCommentAnnotation returns [EObject current=null] : ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) ) ;
    public final EObject ruleCommentAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1037:6: ( ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1038:1: ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1038:1: ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1039:1: (lv_value_0_0= RULE_COMMENT_ANNOTATION )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1039:1: (lv_value_0_0= RULE_COMMENT_ANNOTATION )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1040:3: lv_value_0_0= RULE_COMMENT_ANNOTATION
            {
            lv_value_0_0=(Token)input.LT(1);
            match(input,RULE_COMMENT_ANNOTATION,FollowSets000.FOLLOW_RULE_COMMENT_ANNOTATION_in_ruleCommentAnnotation1574); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0(), "value"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getCommentAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_0_0, 
              	        		"COMMENT_ANNOTATION", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleCommentAnnotation


    // $ANTLR start entryRuleTagAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1070:1: entryRuleTagAnnotation returns [EObject current=null] : iv_ruleTagAnnotation= ruleTagAnnotation EOF ;
    public final EObject entryRuleTagAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTagAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1071:2: (iv_ruleTagAnnotation= ruleTagAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1072:2: iv_ruleTagAnnotation= ruleTagAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getTagAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation1614);
            iv_ruleTagAnnotation=ruleTagAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleTagAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTagAnnotation1624); if (failed) return current;

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
    // $ANTLR end entryRuleTagAnnotation


    // $ANTLR start ruleTagAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1079:1: ruleTagAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleTagAnnotation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1084:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1085:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1085:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1085:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )?
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleTagAnnotation1659); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1089:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1090:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1090:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1091:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getTagAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTagAnnotation1680);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getTagAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1113:2: ( '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==23) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1113:4: '(' ( (lv_annotations_3_0= ruleAnnotation ) )* ')'
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleTagAnnotation1691); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTagAnnotationAccess().getLeftParenthesisKeyword_2_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1117:1: ( (lv_annotations_3_0= ruleAnnotation ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==RULE_COMMENT_ANNOTATION||LA18_0==22) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1118:1: (lv_annotations_3_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1118:1: (lv_annotations_3_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1119:3: lv_annotations_3_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getTagAnnotationAccess().getAnnotationsAnnotationParserRuleCall_2_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTagAnnotation1712);
                    	    lv_annotations_3_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getTagAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_3_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    match(input,24,FollowSets000.FOLLOW_24_in_ruleTagAnnotation1723); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTagAnnotationAccess().getRightParenthesisKeyword_2_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleTagAnnotation


    // $ANTLR start entryRuleKeyStringValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1153:1: entryRuleKeyStringValueAnnotation returns [EObject current=null] : iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF ;
    public final EObject entryRuleKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyStringValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1154:2: (iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1155:2: iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getKeyStringValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation1761);
            iv_ruleKeyStringValueAnnotation=ruleKeyStringValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleKeyStringValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation1771); if (failed) return current;

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
    // $ANTLR end entryRuleKeyStringValueAnnotation


    // $ANTLR start ruleKeyStringValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1162:1: ruleKeyStringValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;

        EObject lv_annotations_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1167:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1168:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1168:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1168:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyStringValueAnnotation1806); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1172:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1173:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1173:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1174:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyStringValueAnnotation1827);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1196:2: ( (lv_value_2_0= ruleEString ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1197:1: (lv_value_2_0= ruleEString )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1197:1: (lv_value_2_0= ruleEString )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1198:3: lv_value_2_0= ruleEString
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKeyStringValueAnnotation1848);
            lv_value_2_0=ruleEString();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_2_0, 
              	        		"EString", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1220:2: ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==23) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1220:4: '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')'
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleKeyStringValueAnnotation1859); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1224:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==RULE_COMMENT_ANNOTATION||LA20_0==22) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1225:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1225:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1226:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyStringValueAnnotation1880);
                    	    lv_annotations_4_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getKeyStringValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_4_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    match(input,24,FollowSets000.FOLLOW_24_in_ruleKeyStringValueAnnotation1891); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyStringValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleKeyStringValueAnnotation


    // $ANTLR start entryRuleTypedKeyStringValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1260:1: entryRuleTypedKeyStringValueAnnotation returns [EObject current=null] : iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF ;
    public final EObject entryRuleTypedKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedKeyStringValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1261:2: (iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1262:2: iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedKeyStringValueAnnotation_in_entryRuleTypedKeyStringValueAnnotation1929);
            iv_ruleTypedKeyStringValueAnnotation=ruleTypedKeyStringValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleTypedKeyStringValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTypedKeyStringValueAnnotation1939); if (failed) return current;

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
    // $ANTLR end entryRuleTypedKeyStringValueAnnotation


    // $ANTLR start ruleTypedKeyStringValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1269:1: ruleTypedKeyStringValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleTypedKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_type_3_0 = null;

        AntlrDatatypeRuleToken lv_value_5_0 = null;

        EObject lv_annotations_7_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1274:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1275:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1275:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1275:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) '[' ( (lv_type_3_0= ruleExtendedID ) ) ']' ( (lv_value_5_0= ruleEString ) ) ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )?
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleTypedKeyStringValueAnnotation1974); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1279:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1280:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1280:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1281:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation1995);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getTypedKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            match(input,25,FollowSets000.FOLLOW_25_in_ruleTypedKeyStringValueAnnotation2005); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftSquareBracketKeyword_2(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1307:1: ( (lv_type_3_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1308:1: (lv_type_3_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1308:1: (lv_type_3_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1309:3: lv_type_3_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getTypeExtendedIDParserRuleCall_3_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation2026);
            lv_type_3_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getTypedKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"type",
              	        		lv_type_3_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            match(input,26,FollowSets000.FOLLOW_26_in_ruleTypedKeyStringValueAnnotation2036); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightSquareBracketKeyword_4(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1335:1: ( (lv_value_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1336:1: (lv_value_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1336:1: (lv_value_5_0= ruleEString )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1337:3: lv_value_5_0= ruleEString
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_5_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleTypedKeyStringValueAnnotation2057);
            lv_value_5_0=ruleEString();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getTypedKeyStringValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_5_0, 
              	        		"EString", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1359:2: ( '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==23) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1359:4: '(' ( (lv_annotations_7_0= ruleAnnotation ) )* ')'
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleTypedKeyStringValueAnnotation2068); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_6_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1363:1: ( (lv_annotations_7_0= ruleAnnotation ) )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==RULE_COMMENT_ANNOTATION||LA22_0==22) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1364:1: (lv_annotations_7_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1364:1: (lv_annotations_7_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1365:3: lv_annotations_7_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_6_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTypedKeyStringValueAnnotation2089);
                    	    lv_annotations_7_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getTypedKeyStringValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_7_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);

                    match(input,24,FollowSets000.FOLLOW_24_in_ruleTypedKeyStringValueAnnotation2100); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightParenthesisKeyword_6_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleTypedKeyStringValueAnnotation


    // $ANTLR start entryRuleKeyBooleanValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1399:1: entryRuleKeyBooleanValueAnnotation returns [EObject current=null] : iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF ;
    public final EObject entryRuleKeyBooleanValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyBooleanValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1400:2: (iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1401:2: iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation2138);
            iv_ruleKeyBooleanValueAnnotation=ruleKeyBooleanValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleKeyBooleanValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation2148); if (failed) return current;

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
    // $ANTLR end entryRuleKeyBooleanValueAnnotation


    // $ANTLR start ruleKeyBooleanValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1408:1: ruleKeyBooleanValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleKeyBooleanValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1413:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1414:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1414:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1414:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyBooleanValueAnnotation2183); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1418:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1419:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1419:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1420:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyBooleanValueAnnotation2204);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1442:2: ( (lv_value_2_0= RULE_BOOLEAN ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1443:1: (lv_value_2_0= RULE_BOOLEAN )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1443:1: (lv_value_2_0= RULE_BOOLEAN )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1444:3: lv_value_2_0= RULE_BOOLEAN
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_RULE_BOOLEAN_in_ruleKeyBooleanValueAnnotation2221); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBooleanTerminalRuleCall_2_0(), "value"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_2_0, 
              	        		"Boolean", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1466:2: ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==23) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1466:4: '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')'
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleKeyBooleanValueAnnotation2237); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1470:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==RULE_COMMENT_ANNOTATION||LA24_0==22) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1471:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1471:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1472:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyBooleanValueAnnotation2258);
                    	    lv_annotations_4_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getKeyBooleanValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_4_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);

                    match(input,24,FollowSets000.FOLLOW_24_in_ruleKeyBooleanValueAnnotation2269); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleKeyBooleanValueAnnotation


    // $ANTLR start entryRuleKeyIntValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1506:1: entryRuleKeyIntValueAnnotation returns [EObject current=null] : iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF ;
    public final EObject entryRuleKeyIntValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyIntValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1507:2: (iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1508:2: iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getKeyIntValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation2307);
            iv_ruleKeyIntValueAnnotation=ruleKeyIntValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleKeyIntValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation2317); if (failed) return current;

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
    // $ANTLR end entryRuleKeyIntValueAnnotation


    // $ANTLR start ruleKeyIntValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1515:1: ruleKeyIntValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleKeyIntValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1520:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1521:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1521:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1521:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyIntValueAnnotation2352); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1525:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1526:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1526:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1527:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyIntValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyIntValueAnnotation2373);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1549:2: ( (lv_value_2_0= RULE_INT ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1550:1: (lv_value_2_0= RULE_INT )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1550:1: (lv_value_2_0= RULE_INT )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1551:3: lv_value_2_0= RULE_INT
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleKeyIntValueAnnotation2390); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0(), "value"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_2_0, 
              	        		"INT", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1573:2: ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==23) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1573:4: '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')'
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleKeyIntValueAnnotation2406); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1577:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==RULE_COMMENT_ANNOTATION||LA26_0==22) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1578:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1578:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1579:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyIntValueAnnotation2427);
                    	    lv_annotations_4_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getKeyIntValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_4_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);

                    match(input,24,FollowSets000.FOLLOW_24_in_ruleKeyIntValueAnnotation2438); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyIntValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleKeyIntValueAnnotation


    // $ANTLR start entryRuleKeyFloatValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1613:1: entryRuleKeyFloatValueAnnotation returns [EObject current=null] : iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF ;
    public final EObject entryRuleKeyFloatValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyFloatValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1614:2: (iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1615:2: iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getKeyFloatValueAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation2476);
            iv_ruleKeyFloatValueAnnotation=ruleKeyFloatValueAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleKeyFloatValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation2486); if (failed) return current;

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
    // $ANTLR end entryRuleKeyFloatValueAnnotation


    // $ANTLR start ruleKeyFloatValueAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1622:1: ruleKeyFloatValueAnnotation returns [EObject current=null] : ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) ;
    public final EObject ruleKeyFloatValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1627:6: ( ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1628:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1628:1: ( '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1628:3: '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            {
            match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyFloatValueAnnotation2521); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1632:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1633:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1633:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1634:3: lv_name_1_0= ruleExtendedID
            {
            if ( backtracking==0 ) {
               
              	        currentNode=createCompositeNode(grammarAccess.getKeyFloatValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0(), currentNode); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyFloatValueAnnotation2542);
            lv_name_1_0=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode.getParent(), current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"name",
              	        		lv_name_1_0, 
              	        		"ExtendedID", 
              	        		currentNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	        currentNode = currentNode.getParent();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1656:2: ( (lv_value_2_0= RULE_FLOAT ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1657:1: (lv_value_2_0= RULE_FLOAT )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1657:1: (lv_value_2_0= RULE_FLOAT )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1658:3: lv_value_2_0= RULE_FLOAT
            {
            lv_value_2_0=(Token)input.LT(1);
            match(input,RULE_FLOAT,FollowSets000.FOLLOW_RULE_FLOAT_in_ruleKeyFloatValueAnnotation2559); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getValueFloatTerminalRuleCall_2_0(), "value"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"value",
              	        		lv_value_2_0, 
              	        		"Float", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1680:2: ( '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==23) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1680:4: '(' ( (lv_annotations_4_0= ruleAnnotation ) )* ')'
                    {
                    match(input,23,FollowSets000.FOLLOW_23_in_ruleKeyFloatValueAnnotation2575); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getLeftParenthesisKeyword_3_0(), null); 
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1684:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==RULE_COMMENT_ANNOTATION||LA28_0==22) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1685:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1685:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1686:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( backtracking==0 ) {
                    	       
                    	      	        currentNode=createCompositeNode(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0(), currentNode); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyFloatValueAnnotation2596);
                    	    lv_annotations_4_0=ruleAnnotation();
                    	    _fsp--;
                    	    if (failed) return current;
                    	    if ( backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = factory.create(grammarAccess.getKeyFloatValueAnnotationRule().getType().getClassifier());
                    	      	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	      	        }
                    	      	        try {
                    	      	       		add(
                    	      	       			current, 
                    	      	       			"annotations",
                    	      	        		lv_annotations_4_0, 
                    	      	        		"Annotation", 
                    	      	        		currentNode);
                    	      	        } catch (ValueConverterException vce) {
                    	      				handleValueConverterException(vce);
                    	      	        }
                    	      	        currentNode = currentNode.getParent();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    match(input,24,FollowSets000.FOLLOW_24_in_ruleKeyFloatValueAnnotation2607); if (failed) return current;
                    if ( backtracking==0 ) {

                              createLeafNode(grammarAccess.getKeyFloatValueAnnotationAccess().getRightParenthesisKeyword_3_2(), null); 
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleKeyFloatValueAnnotation


    // $ANTLR start entryRuleImportAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1720:1: entryRuleImportAnnotation returns [EObject current=null] : iv_ruleImportAnnotation= ruleImportAnnotation EOF ;
    public final EObject entryRuleImportAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImportAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1721:2: (iv_ruleImportAnnotation= ruleImportAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1722:2: iv_ruleImportAnnotation= ruleImportAnnotation EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getImportAnnotationRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleImportAnnotation_in_entryRuleImportAnnotation2645);
            iv_ruleImportAnnotation=ruleImportAnnotation();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleImportAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleImportAnnotation2655); if (failed) return current;

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
    // $ANTLR end entryRuleImportAnnotation


    // $ANTLR start ruleImportAnnotation
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1729:1: ruleImportAnnotation returns [EObject current=null] : ( 'import' ( (lv_importURI_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleImportAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_importURI_1_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1734:6: ( ( 'import' ( (lv_importURI_1_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1735:1: ( 'import' ( (lv_importURI_1_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1735:1: ( 'import' ( (lv_importURI_1_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1735:3: 'import' ( (lv_importURI_1_0= RULE_STRING ) )
            {
            match(input,27,FollowSets000.FOLLOW_27_in_ruleImportAnnotation2690); if (failed) return current;
            if ( backtracking==0 ) {

                      createLeafNode(grammarAccess.getImportAnnotationAccess().getImportKeyword_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1739:1: ( (lv_importURI_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1740:1: (lv_importURI_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1740:1: (lv_importURI_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1741:3: lv_importURI_1_0= RULE_STRING
            {
            lv_importURI_1_0=(Token)input.LT(1);
            match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleImportAnnotation2707); if (failed) return current;
            if ( backtracking==0 ) {

              			createLeafNode(grammarAccess.getImportAnnotationAccess().getImportURISTRINGTerminalRuleCall_1_0(), "importURI"); 
              		
            }
            if ( backtracking==0 ) {

              	        if (current==null) {
              	            current = factory.create(grammarAccess.getImportAnnotationRule().getType().getClassifier());
              	            associateNodeWithAstElement(currentNode, current);
              	        }
              	        try {
              	       		set(
              	       			current, 
              	       			"importURI",
              	        		lv_importURI_1_0, 
              	        		"STRING", 
              	        		lastConsumedNode);
              	        } catch (ValueConverterException vce) {
              				handleValueConverterException(vce);
              	        }
              	    
            }

            }


            }


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
                  	lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleImportAnnotation


    // $ANTLR start entryRuleEString
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1771:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1772:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1773:2: iv_ruleEString= ruleEString EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString2749);
            iv_ruleEString=ruleEString();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleEString.getText(); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString2760); if (failed) return current;

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
    // $ANTLR end entryRuleEString


    // $ANTLR start ruleEString
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1780:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1785:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1786:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1786:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==RULE_STRING) ) {
                alt30=1;
            }
            else if ( (LA30_0==RULE_ID) ) {
                alt30=2;
            }
            else {
                if (backtracking>0) {failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("1786:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1786:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString2800); if (failed) return current;
                    if ( backtracking==0 ) {

                      		current.merge(this_STRING_0);
                          
                    }
                    if ( backtracking==0 ) {
                       
                          createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1794:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString2826); if (failed) return current;
                    if ( backtracking==0 ) {

                      		current.merge(this_ID_1);
                          
                    }
                    if ( backtracking==0 ) {
                       
                          createLeafNode(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1(), null); 
                          
                    }

                    }
                    break;

            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
              	    lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleEString


    // $ANTLR start entryRuleExtendedID
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1809:1: entryRuleExtendedID returns [String current=null] : iv_ruleExtendedID= ruleExtendedID EOF ;
    public final String entryRuleExtendedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleExtendedID = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1810:2: (iv_ruleExtendedID= ruleExtendedID EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1811:2: iv_ruleExtendedID= ruleExtendedID EOF
            {
            if ( backtracking==0 ) {
               currentNode = createCompositeNode(grammarAccess.getExtendedIDRule(), currentNode); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_entryRuleExtendedID2872);
            iv_ruleExtendedID=ruleExtendedID();
            _fsp--;
            if (failed) return current;
            if ( backtracking==0 ) {
               current =iv_ruleExtendedID.getText(); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleExtendedID2883); if (failed) return current;

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
    // $ANTLR end entryRuleExtendedID


    // $ANTLR start ruleExtendedID
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1818:1: ruleExtendedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleExtendedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1823:6: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1824:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1824:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1824:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)input.LT(1);
            match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleExtendedID2923); if (failed) return current;
            if ( backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( backtracking==0 ) {
               
                  createLeafNode(grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_0(), null); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1831:1: (kw= '.' this_ID_2= RULE_ID )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==28) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1832:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)input.LT(1);
            	    match(input,28,FollowSets000.FOLLOW_28_in_ruleExtendedID2942); if (failed) return current;
            	    if ( backtracking==0 ) {

            	              current.merge(kw);
            	              createLeafNode(grammarAccess.getExtendedIDAccess().getFullStopKeyword_1_0(), null); 
            	          
            	    }
            	    this_ID_2=(Token)input.LT(1);
            	    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleExtendedID2957); if (failed) return current;
            	    if ( backtracking==0 ) {

            	      		current.merge(this_ID_2);
            	          
            	    }
            	    if ( backtracking==0 ) {
            	       
            	          createLeafNode(grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_1_1(), null); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            }


            }

            if ( backtracking==0 ) {
               resetLookahead(); 
              	    lastConsumedNode = currentNode;
                  
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
    // $ANTLR end ruleExtendedID

    // $ANTLR start synpred4
    public final void synpred4_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:216:2: ( ( ( ruleEntity ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:216:2: ( ( ruleEntity ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:216:2: ( ( ruleEntity ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:217:1: ( ruleEntity )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:217:1: ( ruleEntity )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:218:3: ruleEntity
        {
        if ( backtracking==0 ) {
           
          	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_2_4_0_1_0_0(), currentNode); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleEntity_in_synpred4262);
        ruleEntity();
        _fsp--;
        if (failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred4

    // $ANTLR start synpred5
    public final void synpred5_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:241:6: ( ( ( ruleLink ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:241:6: ( ( ruleLink ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:241:6: ( ( ruleLink ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:242:1: ( ruleLink )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:242:1: ( ruleLink )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:243:3: ruleLink
        {
        if ( backtracking==0 ) {
           
          	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_2_4_0_1_1_0(), currentNode); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleLink_in_synpred5289);
        ruleLink();
        _fsp--;
        if (failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred5

    // $ANTLR start synpred6
    public final void synpred6_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:266:6: ( ( ( rulePort ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:266:6: ( ( rulePort ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:266:6: ( ( rulePort ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:267:1: ( rulePort )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:267:1: ( rulePort )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:268:3: rulePort
        {
        if ( backtracking==0 ) {
           
          	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_2_4_0_1_2_0(), currentNode); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_rulePort_in_synpred6316);
        rulePort();
        _fsp--;
        if (failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred6

    // $ANTLR start synpred7
    public final void synpred7_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:291:6: ( ( ( ruleRelation ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:291:6: ( ( ruleRelation ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:291:6: ( ( ruleRelation ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:292:1: ( ruleRelation )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:292:1: ( ruleRelation )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:293:3: ruleRelation
        {
        if ( backtracking==0 ) {
           
          	        currentNode=createCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_2_4_0_1_3_0(), currentNode); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleRelation_in_synpred7343);
        ruleRelation();
        _fsp--;
        if (failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred7

    // $ANTLR start synpred12
    public final void synpred12_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:441:2: ( ( ( ruleEntity ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:441:2: ( ( ruleEntity ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:441:2: ( ( ruleEntity ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:442:1: ( ruleEntity )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:442:1: ( ruleEntity )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:443:3: ruleEntity
        {
        if ( backtracking==0 ) {
           
          	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0(), currentNode); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleEntity_in_synpred12569);
        ruleEntity();
        _fsp--;
        if (failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred12

    // $ANTLR start synpred13
    public final void synpred13_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:466:6: ( ( ( ruleLink ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:466:6: ( ( ruleLink ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:466:6: ( ( ruleLink ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:467:1: ( ruleLink )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:467:1: ( ruleLink )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:468:3: ruleLink
        {
        if ( backtracking==0 ) {
           
          	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0(), currentNode); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleLink_in_synpred13596);
        ruleLink();
        _fsp--;
        if (failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred13

    // $ANTLR start synpred14
    public final void synpred14_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:491:6: ( ( ( rulePort ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:491:6: ( ( rulePort ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:491:6: ( ( rulePort ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:492:1: ( rulePort )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:492:1: ( rulePort )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:493:3: rulePort
        {
        if ( backtracking==0 ) {
           
          	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0(), currentNode); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_rulePort_in_synpred14623);
        rulePort();
        _fsp--;
        if (failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred14

    // $ANTLR start synpred15
    public final void synpred15_fragment() throws RecognitionException {   
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:516:6: ( ( ( ruleRelation ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:516:6: ( ( ruleRelation ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:516:6: ( ( ruleRelation ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:517:1: ( ruleRelation )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:517:1: ( ruleRelation )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:518:3: ruleRelation
        {
        if ( backtracking==0 ) {
           
          	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0(), currentNode); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleRelation_in_synpred15650);
        ruleRelation();
        _fsp--;
        if (failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred15

    public final boolean synpred14() {
        backtracking++;
        int start = input.mark();
        try {
            synpred14_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred5() {
        backtracking++;
        int start = input.mark();
        try {
            synpred5_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred15() {
        backtracking++;
        int start = input.mark();
        try {
            synpred15_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred6() {
        backtracking++;
        int start = input.mark();
        try {
            synpred6_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred7() {
        backtracking++;
        int start = input.mark();
        try {
            synpred7_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred12() {
        backtracking++;
        int start = input.mark();
        try {
            synpred12_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred13() {
        backtracking++;
        int start = input.mark();
        try {
            synpred13_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred4() {
        backtracking++;
        int start = input.mark();
        try {
            synpred4_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }


    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA17_eotS =
        "\14\uffff";
    static final String DFA17_eofS =
        "\3\uffff\1\6\7\uffff\1\6";
    static final String DFA17_minS =
        "\1\6\1\uffff\3\4\6\uffff\1\4";
    static final String DFA17_maxS =
        "\1\26\1\uffff\1\4\1\34\1\4\6\uffff\1\34";
    static final String DFA17_acceptS =
        "\1\uffff\1\1\3\uffff\1\5\1\2\1\4\1\7\1\6\1\3\1\uffff";
    static final String DFA17_specialS =
        "\14\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\1\17\uffff\1\2",
            "",
            "\1\3",
            "\2\12\1\6\1\5\1\11\1\10\4\uffff\1\6\3\uffff\1\6\1\uffff\5\6"+
            "\1\7\2\uffff\1\4",
            "\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\12\1\6\1\5\1\11\1\10\4\uffff\1\6\3\uffff\1\6\1\uffff\5\6"+
            "\1\7\2\uffff\1\4"
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "925:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity81 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTopLevelEntity91 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleImportAnnotation_in_ruleTopLevelEntity149 = new BitSet(new long[]{0x0000000008404042L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTopLevelEntity172 = new BitSet(new long[]{0x0000000000404040L});
        public static final BitSet FOLLOW_14_in_ruleTopLevelEntity183 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleTopLevelEntity200 = new BitSet(new long[]{0x0000000000028020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleTopLevelEntity222 = new BitSet(new long[]{0x0000000000028000L});
        public static final BitSet FOLLOW_15_in_ruleTopLevelEntity240 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleEntity_in_ruleTopLevelEntity262 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleLink_in_ruleTopLevelEntity289 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_rulePort_in_ruleTopLevelEntity316 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleRelation_in_ruleTopLevelEntity343 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_16_in_ruleTopLevelEntity355 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_ruleTopLevelEntity372 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_entryRuleEntity411 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEntity421 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleEntity479 = new BitSet(new long[]{0x0000000000404040L});
        public static final BitSet FOLLOW_14_in_ruleEntity490 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEntity507 = new BitSet(new long[]{0x0000000000028020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEntity529 = new BitSet(new long[]{0x0000000000028000L});
        public static final BitSet FOLLOW_15_in_ruleEntity547 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleEntity_in_ruleEntity569 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleLink_in_ruleEntity596 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_rulePort_in_ruleEntity623 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleRelation_in_ruleEntity650 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_16_in_ruleEntity662 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_ruleEntity679 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_entryRuleLink718 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLink728 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleLink774 = new BitSet(new long[]{0x0000000000440040L});
        public static final BitSet FOLLOW_18_in_ruleLink785 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleLink802 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleLink830 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_19_in_ruleLink840 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleLink862 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLink872 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_entryRulePort908 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePort918 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rulePort976 = new BitSet(new long[]{0x0000000000500040L});
        public static final BitSet FOLLOW_20_in_rulePort987 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_rulePort1004 = new BitSet(new long[]{0x0000000000020020L});
        public static final BitSet FOLLOW_RULE_STRING_in_rulePort1026 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_rulePort1042 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_entryRuleRelation1078 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleRelation1088 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleRelation1146 = new BitSet(new long[]{0x0000000000600040L});
        public static final BitSet FOLLOW_21_in_ruleRelation1157 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleRelation1174 = new BitSet(new long[]{0x0000000000020020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleRelation1196 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleRelation1212 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_entryRuleAnnotation1248 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleAnnotation1258 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_ruleAnnotation1308 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_ruleAnnotation1338 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_ruleAnnotation1368 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedKeyStringValueAnnotation_in_ruleAnnotation1398 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_ruleAnnotation1428 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_ruleAnnotation1458 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_ruleAnnotation1488 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation1523 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCommentAnnotation1533 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_COMMENT_ANNOTATION_in_ruleCommentAnnotation1574 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation1614 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTagAnnotation1624 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleTagAnnotation1659 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTagAnnotation1680 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleTagAnnotation1691 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTagAnnotation1712 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleTagAnnotation1723 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation1761 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation1771 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyStringValueAnnotation1806 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyStringValueAnnotation1827 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKeyStringValueAnnotation1848 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleKeyStringValueAnnotation1859 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyStringValueAnnotation1880 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleKeyStringValueAnnotation1891 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedKeyStringValueAnnotation_in_entryRuleTypedKeyStringValueAnnotation1929 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTypedKeyStringValueAnnotation1939 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleTypedKeyStringValueAnnotation1974 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation1995 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_25_in_ruleTypedKeyStringValueAnnotation2005 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation2026 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleTypedKeyStringValueAnnotation2036 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleTypedKeyStringValueAnnotation2057 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleTypedKeyStringValueAnnotation2068 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTypedKeyStringValueAnnotation2089 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleTypedKeyStringValueAnnotation2100 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation2138 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation2148 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyBooleanValueAnnotation2183 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyBooleanValueAnnotation2204 = new BitSet(new long[]{0x0000000000000080L});
        public static final BitSet FOLLOW_RULE_BOOLEAN_in_ruleKeyBooleanValueAnnotation2221 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleKeyBooleanValueAnnotation2237 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyBooleanValueAnnotation2258 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleKeyBooleanValueAnnotation2269 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation2307 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation2317 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyIntValueAnnotation2352 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyIntValueAnnotation2373 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleKeyIntValueAnnotation2390 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleKeyIntValueAnnotation2406 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyIntValueAnnotation2427 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleKeyIntValueAnnotation2438 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation2476 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation2486 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyFloatValueAnnotation2521 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyFloatValueAnnotation2542 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_RULE_FLOAT_in_ruleKeyFloatValueAnnotation2559 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleKeyFloatValueAnnotation2575 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyFloatValueAnnotation2596 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleKeyFloatValueAnnotation2607 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleImportAnnotation_in_entryRuleImportAnnotation2645 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleImportAnnotation2655 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleImportAnnotation2690 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleImportAnnotation2707 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString2749 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString2760 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString2800 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString2826 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_entryRuleExtendedID2872 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleExtendedID2883 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleExtendedID2923 = new BitSet(new long[]{0x0000000010000002L});
        public static final BitSet FOLLOW_28_in_ruleExtendedID2942 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleExtendedID2957 = new BitSet(new long[]{0x0000000010000002L});
        public static final BitSet FOLLOW_ruleEntity_in_synpred4262 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_synpred5289 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_synpred6316 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_synpred7343 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_synpred12569 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_synpred13596 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_synpred14623 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_synpred15650 = new BitSet(new long[]{0x0000000000000002L});
    }


}