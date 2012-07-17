package de.cau.cs.kieler.kaom.text.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
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
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=13;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_FLOAT=9;
    public static final int RULE_SL_COMMENT=11;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=10;
    public static final int T__19=19;
    public static final int RULE_STRING=5;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__14=14;
    public static final int RULE_INT=8;
    public static final int RULE_WS=12;
    public static final int RULE_COMMENT_ANNOTATION=6;

    // delegates
    // delegators


        public InternalKaomParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalKaomParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalKaomParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */
     
     	private KaomGrammarAccess grammarAccess;
     	
        public InternalKaomParser(TokenStream input, KaomGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "TopLevelEntity";	
       	}
       	
       	@Override
       	protected KaomGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleTopLevelEntity"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:73:1: entryRuleTopLevelEntity returns [EObject current=null] : iv_ruleTopLevelEntity= ruleTopLevelEntity EOF ;
    public final EObject entryRuleTopLevelEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTopLevelEntity = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:74:2: (iv_ruleTopLevelEntity= ruleTopLevelEntity EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:75:2: iv_ruleTopLevelEntity= ruleTopLevelEntity EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTopLevelEntityRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity81);
            iv_ruleTopLevelEntity=ruleTopLevelEntity();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTopLevelEntity; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTopLevelEntity91); if (state.failed) return current;

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
    // $ANTLR end "entryRuleTopLevelEntity"


    // $ANTLR start "ruleTopLevelEntity"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:82:1: ruleTopLevelEntity returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* otherlv_3= 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' ) | otherlv_12= ';' ) )? ) ;
    public final EObject ruleTopLevelEntity() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token lv_id_4_0=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        EObject lv_annotations_1_0 = null;

        EObject lv_annotations_2_0 = null;

        EObject lv_childEntities_7_0 = null;

        EObject lv_childLinks_8_0 = null;

        EObject lv_childPorts_9_0 = null;

        EObject lv_childRelations_10_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:85:28: ( ( () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* otherlv_3= 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' ) | otherlv_12= ';' ) )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:86:1: ( () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* otherlv_3= 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' ) | otherlv_12= ';' ) )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:86:1: ( () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* otherlv_3= 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' ) | otherlv_12= ';' ) )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:86:2: () ( (lv_annotations_1_0= ruleImportAnnotation ) )* ( ( (lv_annotations_2_0= ruleAnnotation ) )* otherlv_3= 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' ) | otherlv_12= ';' ) )?
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:86:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:87:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getTopLevelEntityAccess().getEntityAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:95:2: ( (lv_annotations_1_0= ruleImportAnnotation ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==27) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:96:1: (lv_annotations_1_0= ruleImportAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:96:1: (lv_annotations_1_0= ruleImportAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:97:3: lv_annotations_1_0= ruleImportAnnotation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getAnnotationsImportAnnotationParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleImportAnnotation_in_ruleTopLevelEntity149);
            	    lv_annotations_1_0=ruleImportAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTopLevelEntityRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"annotations",
            	              		lv_annotations_1_0, 
            	              		"ImportAnnotation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:113:3: ( ( (lv_annotations_2_0= ruleAnnotation ) )* otherlv_3= 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' ) | otherlv_12= ';' ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_COMMENT_ANNOTATION||LA6_0==14||LA6_0==22) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:113:4: ( (lv_annotations_2_0= ruleAnnotation ) )* otherlv_3= 'entity' ( (lv_id_4_0= RULE_ID ) ) ( (lv_name_5_0= RULE_STRING ) )? ( (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' ) | otherlv_12= ';' )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:113:4: ( (lv_annotations_2_0= ruleAnnotation ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==RULE_COMMENT_ANNOTATION||LA2_0==22) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:114:1: (lv_annotations_2_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:114:1: (lv_annotations_2_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:115:3: lv_annotations_2_0= ruleAnnotation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getAnnotationsAnnotationParserRuleCall_2_0_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTopLevelEntity172);
                    	    lv_annotations_2_0=ruleAnnotation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTopLevelEntityRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"annotations",
                    	              		lv_annotations_2_0, 
                    	              		"Annotation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleTopLevelEntity185); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getTopLevelEntityAccess().getEntityKeyword_2_1());
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:135:1: ( (lv_id_4_0= RULE_ID ) )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:136:1: (lv_id_4_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:136:1: (lv_id_4_0= RULE_ID )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:137:3: lv_id_4_0= RULE_ID
                    {
                    lv_id_4_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleTopLevelEntity202); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_id_4_0, grammarAccess.getTopLevelEntityAccess().getIdIDTerminalRuleCall_2_2_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getTopLevelEntityRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"id",
                              		lv_id_4_0, 
                              		"ID");
                      	    
                    }

                    }


                    }

                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:153:2: ( (lv_name_5_0= RULE_STRING ) )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==RULE_STRING) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:154:1: (lv_name_5_0= RULE_STRING )
                            {
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:154:1: (lv_name_5_0= RULE_STRING )
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:155:3: lv_name_5_0= RULE_STRING
                            {
                            lv_name_5_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleTopLevelEntity224); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_name_5_0, grammarAccess.getTopLevelEntityAccess().getNameSTRINGTerminalRuleCall_2_3_0()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getTopLevelEntityRule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"name",
                                      		lv_name_5_0, 
                                      		"STRING");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:171:3: ( (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' ) | otherlv_12= ';' )
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==15) ) {
                        alt5=1;
                    }
                    else if ( (LA5_0==17) ) {
                        alt5=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 0, input);

                        throw nvae;
                    }
                    switch (alt5) {
                        case 1 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:171:4: (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' )
                            {
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:171:4: (otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}' )
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:171:6: otherlv_6= '{' ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )* otherlv_11= '}'
                            {
                            otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleTopLevelEntity244); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_6, grammarAccess.getTopLevelEntityAccess().getLeftCurlyBracketKeyword_2_4_0_0());
                                  
                            }
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:175:1: ( ( (lv_childEntities_7_0= ruleEntity ) ) | ( (lv_childLinks_8_0= ruleLink ) ) | ( (lv_childPorts_9_0= rulePort ) ) | ( (lv_childRelations_10_0= ruleRelation ) ) )*
                            loop4:
                            do {
                                int alt4=5;
                                switch ( input.LA(1) ) {
                                case RULE_COMMENT_ANNOTATION:
                                    {
                                    int LA4_2 = input.LA(2);

                                    if ( (synpred4_InternalKaom()) ) {
                                        alt4=1;
                                    }
                                    else if ( (synpred5_InternalKaom()) ) {
                                        alt4=2;
                                    }
                                    else if ( (synpred6_InternalKaom()) ) {
                                        alt4=3;
                                    }
                                    else if ( (synpred7_InternalKaom()) ) {
                                        alt4=4;
                                    }


                                    }
                                    break;
                                case 22:
                                    {
                                    int LA4_3 = input.LA(2);

                                    if ( (synpred4_InternalKaom()) ) {
                                        alt4=1;
                                    }
                                    else if ( (synpred5_InternalKaom()) ) {
                                        alt4=2;
                                    }
                                    else if ( (synpred6_InternalKaom()) ) {
                                        alt4=3;
                                    }
                                    else if ( (synpred7_InternalKaom()) ) {
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
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:175:2: ( (lv_childEntities_7_0= ruleEntity ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:175:2: ( (lv_childEntities_7_0= ruleEntity ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:176:1: (lv_childEntities_7_0= ruleEntity )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:176:1: (lv_childEntities_7_0= ruleEntity )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:177:3: lv_childEntities_7_0= ruleEntity
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_2_4_0_1_0_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_ruleEntity_in_ruleTopLevelEntity266);
                            	    lv_childEntities_7_0=ruleEntity();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getTopLevelEntityRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"childEntities",
                            	              		lv_childEntities_7_0, 
                            	              		"Entity");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;
                            	case 2 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:194:6: ( (lv_childLinks_8_0= ruleLink ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:194:6: ( (lv_childLinks_8_0= ruleLink ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:195:1: (lv_childLinks_8_0= ruleLink )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:195:1: (lv_childLinks_8_0= ruleLink )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:196:3: lv_childLinks_8_0= ruleLink
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_2_4_0_1_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleTopLevelEntity293);
                            	    lv_childLinks_8_0=ruleLink();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getTopLevelEntityRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"childLinks",
                            	              		lv_childLinks_8_0, 
                            	              		"Link");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;
                            	case 3 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:213:6: ( (lv_childPorts_9_0= rulePort ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:213:6: ( (lv_childPorts_9_0= rulePort ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:214:1: (lv_childPorts_9_0= rulePort )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:214:1: (lv_childPorts_9_0= rulePort )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:215:3: lv_childPorts_9_0= rulePort
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_2_4_0_1_2_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_rulePort_in_ruleTopLevelEntity320);
                            	    lv_childPorts_9_0=rulePort();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getTopLevelEntityRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"childPorts",
                            	              		lv_childPorts_9_0, 
                            	              		"Port");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;
                            	case 4 :
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:232:6: ( (lv_childRelations_10_0= ruleRelation ) )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:232:6: ( (lv_childRelations_10_0= ruleRelation ) )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:233:1: (lv_childRelations_10_0= ruleRelation )
                            	    {
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:233:1: (lv_childRelations_10_0= ruleRelation )
                            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:234:3: lv_childRelations_10_0= ruleRelation
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_2_4_0_1_3_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_ruleRelation_in_ruleTopLevelEntity347);
                            	    lv_childRelations_10_0=ruleRelation();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getTopLevelEntityRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"childRelations",
                            	              		lv_childRelations_10_0, 
                            	              		"Relation");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop4;
                                }
                            } while (true);

                            otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleTopLevelEntity361); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_11, grammarAccess.getTopLevelEntityAccess().getRightCurlyBracketKeyword_2_4_0_2());
                                  
                            }

                            }


                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:255:7: otherlv_12= ';'
                            {
                            otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleTopLevelEntity380); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_12, grammarAccess.getTopLevelEntityAccess().getSemicolonKeyword_2_4_1());
                                  
                            }

                            }
                            break;

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleTopLevelEntity"


    // $ANTLR start "entryRuleEntity"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:267:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:268:2: (iv_ruleEntity= ruleEntity EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:269:2: iv_ruleEntity= ruleEntity EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEntityRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEntity_in_entryRuleEntity419);
            iv_ruleEntity=ruleEntity();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEntity; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEntity429); if (state.failed) return current;

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
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:276:1: ruleEntity returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( (otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}' ) | otherlv_11= ';' ) ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        EObject lv_annotations_1_0 = null;

        EObject lv_childEntities_6_0 = null;

        EObject lv_childLinks_7_0 = null;

        EObject lv_childPorts_8_0 = null;

        EObject lv_childRelations_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:279:28: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( (otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}' ) | otherlv_11= ';' ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:280:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( (otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}' ) | otherlv_11= ';' ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:280:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( (otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}' ) | otherlv_11= ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:280:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'entity' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? ( (otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}' ) | otherlv_11= ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:280:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:281:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getEntityAccess().getEntityAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:289:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_COMMENT_ANNOTATION||LA7_0==22) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:290:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:290:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:291:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getEntityAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleEntity487);
            	    lv_annotations_1_0=ruleAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getEntityRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"annotations",
            	              		lv_annotations_1_0, 
            	              		"Annotation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleEntity500); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getEntityAccess().getEntityKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:311:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:312:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:312:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:313:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEntity517); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_id_3_0, grammarAccess.getEntityAccess().getIdIDTerminalRuleCall_3_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getEntityRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"id",
                      		lv_id_3_0, 
                      		"ID");
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:329:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_STRING) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:330:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:330:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:331:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEntity539); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_4_0, grammarAccess.getEntityAccess().getNameSTRINGTerminalRuleCall_4_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getEntityRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"name",
                              		lv_name_4_0, 
                              		"STRING");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:347:3: ( (otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}' ) | otherlv_11= ';' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==15) ) {
                alt10=1;
            }
            else if ( (LA10_0==17) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:347:4: (otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}' )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:347:4: (otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}' )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:347:6: otherlv_5= '{' ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )* otherlv_10= '}'
                    {
                    otherlv_5=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleEntity559); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_0_0());
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:351:1: ( ( (lv_childEntities_6_0= ruleEntity ) ) | ( (lv_childLinks_7_0= ruleLink ) ) | ( (lv_childPorts_8_0= rulePort ) ) | ( (lv_childRelations_9_0= ruleRelation ) ) )*
                    loop9:
                    do {
                        int alt9=5;
                        switch ( input.LA(1) ) {
                        case RULE_COMMENT_ANNOTATION:
                            {
                            int LA9_2 = input.LA(2);

                            if ( (synpred12_InternalKaom()) ) {
                                alt9=1;
                            }
                            else if ( (synpred13_InternalKaom()) ) {
                                alt9=2;
                            }
                            else if ( (synpred14_InternalKaom()) ) {
                                alt9=3;
                            }
                            else if ( (synpred15_InternalKaom()) ) {
                                alt9=4;
                            }


                            }
                            break;
                        case 22:
                            {
                            int LA9_3 = input.LA(2);

                            if ( (synpred12_InternalKaom()) ) {
                                alt9=1;
                            }
                            else if ( (synpred13_InternalKaom()) ) {
                                alt9=2;
                            }
                            else if ( (synpred14_InternalKaom()) ) {
                                alt9=3;
                            }
                            else if ( (synpred15_InternalKaom()) ) {
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
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:351:2: ( (lv_childEntities_6_0= ruleEntity ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:351:2: ( (lv_childEntities_6_0= ruleEntity ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:352:1: (lv_childEntities_6_0= ruleEntity )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:352:1: (lv_childEntities_6_0= ruleEntity )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:353:3: lv_childEntities_6_0= ruleEntity
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleEntity_in_ruleEntity581);
                    	    lv_childEntities_6_0=ruleEntity();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getEntityRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"childEntities",
                    	              		lv_childEntities_6_0, 
                    	              		"Entity");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:370:6: ( (lv_childLinks_7_0= ruleLink ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:370:6: ( (lv_childLinks_7_0= ruleLink ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:371:1: (lv_childLinks_7_0= ruleLink )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:371:1: (lv_childLinks_7_0= ruleLink )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:372:3: lv_childLinks_7_0= ruleLink
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleEntity608);
                    	    lv_childLinks_7_0=ruleLink();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getEntityRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"childLinks",
                    	              		lv_childLinks_7_0, 
                    	              		"Link");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:389:6: ( (lv_childPorts_8_0= rulePort ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:389:6: ( (lv_childPorts_8_0= rulePort ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:390:1: (lv_childPorts_8_0= rulePort )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:390:1: (lv_childPorts_8_0= rulePort )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:391:3: lv_childPorts_8_0= rulePort
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_rulePort_in_ruleEntity635);
                    	    lv_childPorts_8_0=rulePort();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getEntityRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"childPorts",
                    	              		lv_childPorts_8_0, 
                    	              		"Port");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:408:6: ( (lv_childRelations_9_0= ruleRelation ) )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:408:6: ( (lv_childRelations_9_0= ruleRelation ) )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:409:1: (lv_childRelations_9_0= ruleRelation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:409:1: (lv_childRelations_9_0= ruleRelation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:410:3: lv_childRelations_9_0= ruleRelation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleRelation_in_ruleEntity662);
                    	    lv_childRelations_9_0=ruleRelation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getEntityRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"childRelations",
                    	              		lv_childRelations_9_0, 
                    	              		"Relation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleEntity676); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_0_2());
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:431:7: otherlv_11= ';'
                    {
                    otherlv_11=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleEntity695); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getEntityAccess().getSemicolonKeyword_5_1());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleLink"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:445:1: entryRuleLink returns [EObject current=null] : iv_ruleLink= ruleLink EOF ;
    public final EObject entryRuleLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLink = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:446:2: (iv_ruleLink= ruleLink EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:447:2: iv_ruleLink= ruleLink EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLinkRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_entryRuleLink734);
            iv_ruleLink=ruleLink();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLink; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLink744); if (state.failed) return current;

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
    // $ANTLR end "entryRuleLink"


    // $ANTLR start "ruleLink"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:454:1: ruleLink returns [EObject current=null] : ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'link' ( (lv_name_2_0= RULE_STRING ) )? ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) otherlv_6= ';' ) ;
    public final EObject ruleLink() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_annotations_0_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:457:28: ( ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'link' ( (lv_name_2_0= RULE_STRING ) )? ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) otherlv_6= ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:458:1: ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'link' ( (lv_name_2_0= RULE_STRING ) )? ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) otherlv_6= ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:458:1: ( ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'link' ( (lv_name_2_0= RULE_STRING ) )? ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) otherlv_6= ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:458:2: ( (lv_annotations_0_0= ruleAnnotation ) )* otherlv_1= 'link' ( (lv_name_2_0= RULE_STRING ) )? ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) otherlv_6= ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:458:2: ( (lv_annotations_0_0= ruleAnnotation ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_COMMENT_ANNOTATION||LA11_0==22) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:459:1: (lv_annotations_0_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:459:1: (lv_annotations_0_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:460:3: lv_annotations_0_0= ruleAnnotation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getLinkAccess().getAnnotationsAnnotationParserRuleCall_0_0()); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleLink790);
            	    lv_annotations_0_0=ruleAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getLinkRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"annotations",
            	              		lv_annotations_0_0, 
            	              		"Annotation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_1=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleLink803); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getLinkAccess().getLinkKeyword_1());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:480:1: ( (lv_name_2_0= RULE_STRING ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_STRING) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:481:1: (lv_name_2_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:481:1: (lv_name_2_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:482:3: lv_name_2_0= RULE_STRING
                    {
                    lv_name_2_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleLink820); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_2_0, grammarAccess.getLinkAccess().getNameSTRINGTerminalRuleCall_2_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getLinkRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"name",
                              		lv_name_2_0, 
                              		"STRING");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:498:3: ( (otherlv_3= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:499:1: (otherlv_3= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:499:1: (otherlv_3= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:500:3: otherlv_3= RULE_ID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getLinkRule());
              	        }
                      
            }
            otherlv_3=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleLink850); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_3, grammarAccess.getLinkAccess().getSourceLinkableCrossReference_3_0()); 
              	
            }

            }


            }

            otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleLink862); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLinkAccess().getToKeyword_4());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:518:1: ( (otherlv_5= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:519:1: (otherlv_5= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:519:1: (otherlv_5= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:520:3: otherlv_5= RULE_ID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getLinkRule());
              	        }
                      
            }
            otherlv_5=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleLink886); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_5, grammarAccess.getLinkAccess().getTargetLinkableCrossReference_5_0()); 
              	
            }

            }


            }

            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleLink898); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getLinkAccess().getSemicolonKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleLink"


    // $ANTLR start "entryRulePort"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:546:1: entryRulePort returns [EObject current=null] : iv_rulePort= rulePort EOF ;
    public final EObject entryRulePort() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePort = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:547:2: (iv_rulePort= rulePort EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:548:2: iv_rulePort= rulePort EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPortRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_rulePort_in_entryRulePort934);
            iv_rulePort=rulePort();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePort; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePort944); if (state.failed) return current;

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
    // $ANTLR end "entryRulePort"


    // $ANTLR start "rulePort"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:555:1: rulePort returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';' ) ;
    public final EObject rulePort() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        EObject lv_annotations_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:558:28: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:559:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:559:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:559:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'port' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:559:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:560:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getPortAccess().getPortAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:568:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_COMMENT_ANNOTATION||LA13_0==22) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:569:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:569:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:570:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getPortAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_rulePort1002);
            	    lv_annotations_1_0=ruleAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPortRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"annotations",
            	              		lv_annotations_1_0, 
            	              		"Annotation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            otherlv_2=(Token)match(input,20,FollowSets000.FOLLOW_20_in_rulePort1015); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getPortAccess().getPortKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:590:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:591:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:591:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:592:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rulePort1032); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_id_3_0, grammarAccess.getPortAccess().getIdIDTerminalRuleCall_3_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getPortRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"id",
                      		lv_id_3_0, 
                      		"ID");
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:608:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_STRING) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:609:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:609:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:610:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rulePort1054); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_4_0, grammarAccess.getPortAccess().getNameSTRINGTerminalRuleCall_4_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getPortRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"name",
                              		lv_name_4_0, 
                              		"STRING");
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_rulePort1072); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getPortAccess().getSemicolonKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "rulePort"


    // $ANTLR start "entryRuleRelation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:638:1: entryRuleRelation returns [EObject current=null] : iv_ruleRelation= ruleRelation EOF ;
    public final EObject entryRuleRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:639:2: (iv_ruleRelation= ruleRelation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:640:2: iv_ruleRelation= ruleRelation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleRelation_in_entryRuleRelation1108);
            iv_ruleRelation=ruleRelation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleRelation1118); if (state.failed) return current;

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
    // $ANTLR end "entryRuleRelation"


    // $ANTLR start "ruleRelation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:647:1: ruleRelation returns [EObject current=null] : ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';' ) ;
    public final EObject ruleRelation() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token lv_id_3_0=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        EObject lv_annotations_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:650:28: ( ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';' ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:651:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';' )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:651:1: ( () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';' )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:651:2: () ( (lv_annotations_1_0= ruleAnnotation ) )* otherlv_2= 'relation' ( (lv_id_3_0= RULE_ID ) ) ( (lv_name_4_0= RULE_STRING ) )? otherlv_5= ';'
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:651:2: ()
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:652:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getRelationAccess().getRelationAction_0(),
                          current);
                  
            }

            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:660:2: ( (lv_annotations_1_0= ruleAnnotation ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_COMMENT_ANNOTATION||LA15_0==22) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:661:1: (lv_annotations_1_0= ruleAnnotation )
            	    {
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:661:1: (lv_annotations_1_0= ruleAnnotation )
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:662:3: lv_annotations_1_0= ruleAnnotation
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationAccess().getAnnotationsAnnotationParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleRelation1176);
            	    lv_annotations_1_0=ruleAnnotation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getRelationRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"annotations",
            	              		lv_annotations_1_0, 
            	              		"Annotation");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            otherlv_2=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleRelation1189); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getRelationAccess().getRelationKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:682:1: ( (lv_id_3_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:683:1: (lv_id_3_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:683:1: (lv_id_3_0= RULE_ID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:684:3: lv_id_3_0= RULE_ID
            {
            lv_id_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleRelation1206); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_id_3_0, grammarAccess.getRelationAccess().getIdIDTerminalRuleCall_3_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getRelationRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"id",
                      		lv_id_3_0, 
                      		"ID");
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:700:2: ( (lv_name_4_0= RULE_STRING ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_STRING) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:701:1: (lv_name_4_0= RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:701:1: (lv_name_4_0= RULE_STRING )
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:702:3: lv_name_4_0= RULE_STRING
                    {
                    lv_name_4_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleRelation1228); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_4_0, grammarAccess.getRelationAccess().getNameSTRINGTerminalRuleCall_4_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getRelationRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"name",
                              		lv_name_4_0, 
                              		"STRING");
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleRelation1246); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getRelationAccess().getSemicolonKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleRelation"


    // $ANTLR start "entryRuleAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:730:1: entryRuleAnnotation returns [EObject current=null] : iv_ruleAnnotation= ruleAnnotation EOF ;
    public final EObject entryRuleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:731:2: (iv_ruleAnnotation= ruleAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:732:2: iv_ruleAnnotation= ruleAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_entryRuleAnnotation1282);
            iv_ruleAnnotation=ruleAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleAnnotation1292); if (state.failed) return current;

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
    // $ANTLR end "entryRuleAnnotation"


    // $ANTLR start "ruleAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:739:1: ruleAnnotation returns [EObject current=null] : (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation ) ;
    public final EObject ruleAnnotation() throws RecognitionException {
        EObject current = null;

        EObject this_CommentAnnotation_0 = null;

        EObject this_TagAnnotation_1 = null;

        EObject this_KeyStringValueAnnotation_2 = null;

        EObject this_TypedKeyStringValueAnnotation_3 = null;

        EObject this_KeyBooleanValueAnnotation_4 = null;

        EObject this_KeyIntValueAnnotation_5 = null;

        EObject this_KeyFloatValueAnnotation_6 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:742:28: ( (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:743:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:743:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )
            int alt17=7;
            alt17 = dfa17.predict(input);
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:744:2: this_CommentAnnotation_0= ruleCommentAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAnnotationAccess().getCommentAnnotationParserRuleCall_0()); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_ruleAnnotation1342);
                    this_CommentAnnotation_0=ruleCommentAnnotation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_CommentAnnotation_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:757:2: this_TagAnnotation_1= ruleTagAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAnnotationAccess().getTagAnnotationParserRuleCall_1()); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_ruleAnnotation1372);
                    this_TagAnnotation_1=ruleTagAnnotation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_TagAnnotation_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:770:2: this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAnnotationAccess().getKeyStringValueAnnotationParserRuleCall_2()); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_ruleAnnotation1402);
                    this_KeyStringValueAnnotation_2=ruleKeyStringValueAnnotation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_KeyStringValueAnnotation_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:783:2: this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAnnotationAccess().getTypedKeyStringValueAnnotationParserRuleCall_3()); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTypedKeyStringValueAnnotation_in_ruleAnnotation1432);
                    this_TypedKeyStringValueAnnotation_3=ruleTypedKeyStringValueAnnotation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_TypedKeyStringValueAnnotation_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:796:2: this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAnnotationAccess().getKeyBooleanValueAnnotationParserRuleCall_4()); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_ruleAnnotation1462);
                    this_KeyBooleanValueAnnotation_4=ruleKeyBooleanValueAnnotation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_KeyBooleanValueAnnotation_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:809:2: this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAnnotationAccess().getKeyIntValueAnnotationParserRuleCall_5()); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_ruleAnnotation1492);
                    this_KeyIntValueAnnotation_5=ruleKeyIntValueAnnotation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_KeyIntValueAnnotation_5; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:822:2: this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getAnnotationAccess().getKeyFloatValueAnnotationParserRuleCall_6()); 
                          
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_ruleAnnotation1522);
                    this_KeyFloatValueAnnotation_6=ruleKeyFloatValueAnnotation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_KeyFloatValueAnnotation_6; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleAnnotation"


    // $ANTLR start "entryRuleCommentAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:843:1: entryRuleCommentAnnotation returns [EObject current=null] : iv_ruleCommentAnnotation= ruleCommentAnnotation EOF ;
    public final EObject entryRuleCommentAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommentAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:844:2: (iv_ruleCommentAnnotation= ruleCommentAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:845:2: iv_ruleCommentAnnotation= ruleCommentAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCommentAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation1559);
            iv_ruleCommentAnnotation=ruleCommentAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCommentAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCommentAnnotation1569); if (state.failed) return current;

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
    // $ANTLR end "entryRuleCommentAnnotation"


    // $ANTLR start "ruleCommentAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:852:1: ruleCommentAnnotation returns [EObject current=null] : ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) ) ;
    public final EObject ruleCommentAnnotation() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:855:28: ( ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:856:1: ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:856:1: ( (lv_value_0_0= RULE_COMMENT_ANNOTATION ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:857:1: (lv_value_0_0= RULE_COMMENT_ANNOTATION )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:857:1: (lv_value_0_0= RULE_COMMENT_ANNOTATION )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:858:3: lv_value_0_0= RULE_COMMENT_ANNOTATION
            {
            lv_value_0_0=(Token)match(input,RULE_COMMENT_ANNOTATION,FollowSets000.FOLLOW_RULE_COMMENT_ANNOTATION_in_ruleCommentAnnotation1610); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_0_0, grammarAccess.getCommentAnnotationAccess().getValueCOMMENT_ANNOTATIONTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getCommentAnnotationRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_0_0, 
                      		"COMMENT_ANNOTATION");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleCommentAnnotation"


    // $ANTLR start "entryRuleTagAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:882:1: entryRuleTagAnnotation returns [EObject current=null] : iv_ruleTagAnnotation= ruleTagAnnotation EOF ;
    public final EObject entryRuleTagAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTagAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:883:2: (iv_ruleTagAnnotation= ruleTagAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:884:2: iv_ruleTagAnnotation= ruleTagAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTagAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation1650);
            iv_ruleTagAnnotation=ruleTagAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTagAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTagAnnotation1660); if (state.failed) return current;

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
    // $ANTLR end "entryRuleTagAnnotation"


    // $ANTLR start "ruleTagAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:891:1: ruleTagAnnotation returns [EObject current=null] : (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) (otherlv_2= '(' ( (lv_annotations_3_0= ruleAnnotation ) )* otherlv_4= ')' )? ) ;
    public final EObject ruleTagAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:894:28: ( (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) (otherlv_2= '(' ( (lv_annotations_3_0= ruleAnnotation ) )* otherlv_4= ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:895:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) (otherlv_2= '(' ( (lv_annotations_3_0= ruleAnnotation ) )* otherlv_4= ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:895:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) (otherlv_2= '(' ( (lv_annotations_3_0= ruleAnnotation ) )* otherlv_4= ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:895:3: otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) (otherlv_2= '(' ( (lv_annotations_3_0= ruleAnnotation ) )* otherlv_4= ')' )?
            {
            otherlv_0=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleTagAnnotation1697); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getTagAnnotationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:899:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:900:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:900:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:901:3: lv_name_1_0= ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTagAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTagAnnotation1718);
            lv_name_1_0=ruleExtendedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTagAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ExtendedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:917:2: (otherlv_2= '(' ( (lv_annotations_3_0= ruleAnnotation ) )* otherlv_4= ')' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==23) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:917:4: otherlv_2= '(' ( (lv_annotations_3_0= ruleAnnotation ) )* otherlv_4= ')'
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleTagAnnotation1731); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getTagAnnotationAccess().getLeftParenthesisKeyword_2_0());
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:921:1: ( (lv_annotations_3_0= ruleAnnotation ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==RULE_COMMENT_ANNOTATION||LA18_0==22) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:922:1: (lv_annotations_3_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:922:1: (lv_annotations_3_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:923:3: lv_annotations_3_0= ruleAnnotation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTagAnnotationAccess().getAnnotationsAnnotationParserRuleCall_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTagAnnotation1752);
                    	    lv_annotations_3_0=ruleAnnotation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTagAnnotationRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"annotations",
                    	              		lv_annotations_3_0, 
                    	              		"Annotation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    otherlv_4=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleTagAnnotation1765); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getTagAnnotationAccess().getRightParenthesisKeyword_2_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleTagAnnotation"


    // $ANTLR start "entryRuleKeyStringValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:951:1: entryRuleKeyStringValueAnnotation returns [EObject current=null] : iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF ;
    public final EObject entryRuleKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyStringValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:952:2: (iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:953:2: iv_ruleKeyStringValueAnnotation= ruleKeyStringValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getKeyStringValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation1803);
            iv_ruleKeyStringValueAnnotation=ruleKeyStringValueAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleKeyStringValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation1813); if (state.failed) return current;

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
    // $ANTLR end "entryRuleKeyStringValueAnnotation"


    // $ANTLR start "ruleKeyStringValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:960:1: ruleKeyStringValueAnnotation returns [EObject current=null] : (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? ) ;
    public final EObject ruleKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;

        EObject lv_annotations_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:963:28: ( (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:964:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:964:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:964:3: otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= ruleEString ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )?
            {
            otherlv_0=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyStringValueAnnotation1850); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getKeyStringValueAnnotationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:968:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:969:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:969:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:970:3: lv_name_1_0= ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyStringValueAnnotation1871);
            lv_name_1_0=ruleExtendedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getKeyStringValueAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ExtendedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:986:2: ( (lv_value_2_0= ruleEString ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:987:1: (lv_value_2_0= ruleEString )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:987:1: (lv_value_2_0= ruleEString )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:988:3: lv_value_2_0= ruleEString
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKeyStringValueAnnotation1892);
            lv_value_2_0=ruleEString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getKeyStringValueAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"value",
                      		lv_value_2_0, 
                      		"EString");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1004:2: (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==23) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1004:4: otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKeyStringValueAnnotation1905); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_3_0());
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1008:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==RULE_COMMENT_ANNOTATION||LA20_0==22) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1009:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1009:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1010:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyStringValueAnnotation1926);
                    	    lv_annotations_4_0=ruleAnnotation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getKeyStringValueAnnotationRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"annotations",
                    	              		lv_annotations_4_0, 
                    	              		"Annotation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKeyStringValueAnnotation1939); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getKeyStringValueAnnotationAccess().getRightParenthesisKeyword_3_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleKeyStringValueAnnotation"


    // $ANTLR start "entryRuleTypedKeyStringValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1038:1: entryRuleTypedKeyStringValueAnnotation returns [EObject current=null] : iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF ;
    public final EObject entryRuleTypedKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedKeyStringValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1039:2: (iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1040:2: iv_ruleTypedKeyStringValueAnnotation= ruleTypedKeyStringValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedKeyStringValueAnnotation_in_entryRuleTypedKeyStringValueAnnotation1977);
            iv_ruleTypedKeyStringValueAnnotation=ruleTypedKeyStringValueAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypedKeyStringValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTypedKeyStringValueAnnotation1987); if (state.failed) return current;

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
    // $ANTLR end "entryRuleTypedKeyStringValueAnnotation"


    // $ANTLR start "ruleTypedKeyStringValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1047:1: ruleTypedKeyStringValueAnnotation returns [EObject current=null] : (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) otherlv_2= '[' ( (lv_type_3_0= ruleExtendedID ) ) otherlv_4= ']' ( (lv_value_5_0= ruleEString ) ) (otherlv_6= '(' ( (lv_annotations_7_0= ruleAnnotation ) )* otherlv_8= ')' )? ) ;
    public final EObject ruleTypedKeyStringValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_type_3_0 = null;

        AntlrDatatypeRuleToken lv_value_5_0 = null;

        EObject lv_annotations_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1050:28: ( (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) otherlv_2= '[' ( (lv_type_3_0= ruleExtendedID ) ) otherlv_4= ']' ( (lv_value_5_0= ruleEString ) ) (otherlv_6= '(' ( (lv_annotations_7_0= ruleAnnotation ) )* otherlv_8= ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1051:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) otherlv_2= '[' ( (lv_type_3_0= ruleExtendedID ) ) otherlv_4= ']' ( (lv_value_5_0= ruleEString ) ) (otherlv_6= '(' ( (lv_annotations_7_0= ruleAnnotation ) )* otherlv_8= ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1051:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) otherlv_2= '[' ( (lv_type_3_0= ruleExtendedID ) ) otherlv_4= ']' ( (lv_value_5_0= ruleEString ) ) (otherlv_6= '(' ( (lv_annotations_7_0= ruleAnnotation ) )* otherlv_8= ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1051:3: otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) otherlv_2= '[' ( (lv_type_3_0= ruleExtendedID ) ) otherlv_4= ']' ( (lv_value_5_0= ruleEString ) ) (otherlv_6= '(' ( (lv_annotations_7_0= ruleAnnotation ) )* otherlv_8= ')' )?
            {
            otherlv_0=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleTypedKeyStringValueAnnotation2024); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getTypedKeyStringValueAnnotationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1055:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1056:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1056:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1057:3: lv_name_1_0= ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation2045);
            lv_name_1_0=ruleExtendedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTypedKeyStringValueAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ExtendedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleTypedKeyStringValueAnnotation2057); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftSquareBracketKeyword_2());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1077:1: ( (lv_type_3_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1078:1: (lv_type_3_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1078:1: (lv_type_3_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1079:3: lv_type_3_0= ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getTypeExtendedIDParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation2078);
            lv_type_3_0=ruleExtendedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTypedKeyStringValueAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_3_0, 
                      		"ExtendedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleTypedKeyStringValueAnnotation2090); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightSquareBracketKeyword_4());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1099:1: ( (lv_value_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1100:1: (lv_value_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1100:1: (lv_value_5_0= ruleEString )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1101:3: lv_value_5_0= ruleEString
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getValueEStringParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleTypedKeyStringValueAnnotation2111);
            lv_value_5_0=ruleEString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTypedKeyStringValueAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"value",
                      		lv_value_5_0, 
                      		"EString");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1117:2: (otherlv_6= '(' ( (lv_annotations_7_0= ruleAnnotation ) )* otherlv_8= ')' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==23) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1117:4: otherlv_6= '(' ( (lv_annotations_7_0= ruleAnnotation ) )* otherlv_8= ')'
                    {
                    otherlv_6=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleTypedKeyStringValueAnnotation2124); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getTypedKeyStringValueAnnotationAccess().getLeftParenthesisKeyword_6_0());
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1121:1: ( (lv_annotations_7_0= ruleAnnotation ) )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==RULE_COMMENT_ANNOTATION||LA22_0==22) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1122:1: (lv_annotations_7_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1122:1: (lv_annotations_7_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1123:3: lv_annotations_7_0= ruleAnnotation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getTypedKeyStringValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_6_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleTypedKeyStringValueAnnotation2145);
                    	    lv_annotations_7_0=ruleAnnotation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTypedKeyStringValueAnnotationRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"annotations",
                    	              		lv_annotations_7_0, 
                    	              		"Annotation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleTypedKeyStringValueAnnotation2158); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getTypedKeyStringValueAnnotationAccess().getRightParenthesisKeyword_6_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleTypedKeyStringValueAnnotation"


    // $ANTLR start "entryRuleKeyBooleanValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1151:1: entryRuleKeyBooleanValueAnnotation returns [EObject current=null] : iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF ;
    public final EObject entryRuleKeyBooleanValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyBooleanValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1152:2: (iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1153:2: iv_ruleKeyBooleanValueAnnotation= ruleKeyBooleanValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getKeyBooleanValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation2196);
            iv_ruleKeyBooleanValueAnnotation=ruleKeyBooleanValueAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleKeyBooleanValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation2206); if (state.failed) return current;

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
    // $ANTLR end "entryRuleKeyBooleanValueAnnotation"


    // $ANTLR start "ruleKeyBooleanValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1160:1: ruleKeyBooleanValueAnnotation returns [EObject current=null] : (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? ) ;
    public final EObject ruleKeyBooleanValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_value_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1163:28: ( (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1164:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1164:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1164:3: otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_BOOLEAN ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )?
            {
            otherlv_0=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyBooleanValueAnnotation2243); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getKeyBooleanValueAnnotationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1168:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1169:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1169:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1170:3: lv_name_1_0= ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyBooleanValueAnnotation2264);
            lv_name_1_0=ruleExtendedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getKeyBooleanValueAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ExtendedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1186:2: ( (lv_value_2_0= RULE_BOOLEAN ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1187:1: (lv_value_2_0= RULE_BOOLEAN )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1187:1: (lv_value_2_0= RULE_BOOLEAN )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1188:3: lv_value_2_0= RULE_BOOLEAN
            {
            lv_value_2_0=(Token)match(input,RULE_BOOLEAN,FollowSets000.FOLLOW_RULE_BOOLEAN_in_ruleKeyBooleanValueAnnotation2281); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_2_0, grammarAccess.getKeyBooleanValueAnnotationAccess().getValueBOOLEANTerminalRuleCall_2_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getKeyBooleanValueAnnotationRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_2_0, 
                      		"BOOLEAN");
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1204:2: (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==23) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1204:4: otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKeyBooleanValueAnnotation2299); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getKeyBooleanValueAnnotationAccess().getLeftParenthesisKeyword_3_0());
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1208:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==RULE_COMMENT_ANNOTATION||LA24_0==22) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1209:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1209:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1210:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getKeyBooleanValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyBooleanValueAnnotation2320);
                    	    lv_annotations_4_0=ruleAnnotation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getKeyBooleanValueAnnotationRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"annotations",
                    	              		lv_annotations_4_0, 
                    	              		"Annotation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKeyBooleanValueAnnotation2333); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getKeyBooleanValueAnnotationAccess().getRightParenthesisKeyword_3_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleKeyBooleanValueAnnotation"


    // $ANTLR start "entryRuleKeyIntValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1238:1: entryRuleKeyIntValueAnnotation returns [EObject current=null] : iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF ;
    public final EObject entryRuleKeyIntValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyIntValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1239:2: (iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1240:2: iv_ruleKeyIntValueAnnotation= ruleKeyIntValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getKeyIntValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation2371);
            iv_ruleKeyIntValueAnnotation=ruleKeyIntValueAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleKeyIntValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation2381); if (state.failed) return current;

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
    // $ANTLR end "entryRuleKeyIntValueAnnotation"


    // $ANTLR start "ruleKeyIntValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1247:1: ruleKeyIntValueAnnotation returns [EObject current=null] : (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? ) ;
    public final EObject ruleKeyIntValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_value_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1250:28: ( (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1251:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1251:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1251:3: otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_INT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )?
            {
            otherlv_0=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyIntValueAnnotation2418); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getKeyIntValueAnnotationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1255:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1256:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1256:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1257:3: lv_name_1_0= ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getKeyIntValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyIntValueAnnotation2439);
            lv_name_1_0=ruleExtendedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getKeyIntValueAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ExtendedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1273:2: ( (lv_value_2_0= RULE_INT ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1274:1: (lv_value_2_0= RULE_INT )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1274:1: (lv_value_2_0= RULE_INT )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1275:3: lv_value_2_0= RULE_INT
            {
            lv_value_2_0=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleKeyIntValueAnnotation2456); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_2_0, grammarAccess.getKeyIntValueAnnotationAccess().getValueINTTerminalRuleCall_2_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getKeyIntValueAnnotationRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_2_0, 
                      		"INT");
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1291:2: (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==23) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1291:4: otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKeyIntValueAnnotation2474); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getKeyIntValueAnnotationAccess().getLeftParenthesisKeyword_3_0());
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1295:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==RULE_COMMENT_ANNOTATION||LA26_0==22) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1296:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1296:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1297:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getKeyIntValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyIntValueAnnotation2495);
                    	    lv_annotations_4_0=ruleAnnotation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getKeyIntValueAnnotationRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"annotations",
                    	              		lv_annotations_4_0, 
                    	              		"Annotation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKeyIntValueAnnotation2508); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getKeyIntValueAnnotationAccess().getRightParenthesisKeyword_3_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleKeyIntValueAnnotation"


    // $ANTLR start "entryRuleKeyFloatValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1325:1: entryRuleKeyFloatValueAnnotation returns [EObject current=null] : iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF ;
    public final EObject entryRuleKeyFloatValueAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyFloatValueAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1326:2: (iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1327:2: iv_ruleKeyFloatValueAnnotation= ruleKeyFloatValueAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getKeyFloatValueAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation2546);
            iv_ruleKeyFloatValueAnnotation=ruleKeyFloatValueAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleKeyFloatValueAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation2556); if (state.failed) return current;

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
    // $ANTLR end "entryRuleKeyFloatValueAnnotation"


    // $ANTLR start "ruleKeyFloatValueAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1334:1: ruleKeyFloatValueAnnotation returns [EObject current=null] : (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? ) ;
    public final EObject ruleKeyFloatValueAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_value_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_annotations_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1337:28: ( (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1338:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1338:1: (otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )? )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1338:3: otherlv_0= '@' ( (lv_name_1_0= ruleExtendedID ) ) ( (lv_value_2_0= RULE_FLOAT ) ) (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )?
            {
            otherlv_0=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKeyFloatValueAnnotation2593); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getKeyFloatValueAnnotationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1342:1: ( (lv_name_1_0= ruleExtendedID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1343:1: (lv_name_1_0= ruleExtendedID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1343:1: (lv_name_1_0= ruleExtendedID )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1344:3: lv_name_1_0= ruleExtendedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getKeyFloatValueAnnotationAccess().getNameExtendedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_ruleKeyFloatValueAnnotation2614);
            lv_name_1_0=ruleExtendedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getKeyFloatValueAnnotationRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"ExtendedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1360:2: ( (lv_value_2_0= RULE_FLOAT ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1361:1: (lv_value_2_0= RULE_FLOAT )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1361:1: (lv_value_2_0= RULE_FLOAT )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1362:3: lv_value_2_0= RULE_FLOAT
            {
            lv_value_2_0=(Token)match(input,RULE_FLOAT,FollowSets000.FOLLOW_RULE_FLOAT_in_ruleKeyFloatValueAnnotation2631); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_2_0, grammarAccess.getKeyFloatValueAnnotationAccess().getValueFLOATTerminalRuleCall_2_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getKeyFloatValueAnnotationRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"value",
                      		lv_value_2_0, 
                      		"FLOAT");
              	    
            }

            }


            }

            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1378:2: (otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==23) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1378:4: otherlv_3= '(' ( (lv_annotations_4_0= ruleAnnotation ) )* otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKeyFloatValueAnnotation2649); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getKeyFloatValueAnnotationAccess().getLeftParenthesisKeyword_3_0());
                          
                    }
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1382:1: ( (lv_annotations_4_0= ruleAnnotation ) )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==RULE_COMMENT_ANNOTATION||LA28_0==22) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1383:1: (lv_annotations_4_0= ruleAnnotation )
                    	    {
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1383:1: (lv_annotations_4_0= ruleAnnotation )
                    	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1384:3: lv_annotations_4_0= ruleAnnotation
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getKeyFloatValueAnnotationAccess().getAnnotationsAnnotationParserRuleCall_3_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_ruleAnnotation_in_ruleKeyFloatValueAnnotation2670);
                    	    lv_annotations_4_0=ruleAnnotation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getKeyFloatValueAnnotationRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"annotations",
                    	              		lv_annotations_4_0, 
                    	              		"Annotation");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKeyFloatValueAnnotation2683); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getKeyFloatValueAnnotationAccess().getRightParenthesisKeyword_3_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleKeyFloatValueAnnotation"


    // $ANTLR start "entryRuleImportAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1412:1: entryRuleImportAnnotation returns [EObject current=null] : iv_ruleImportAnnotation= ruleImportAnnotation EOF ;
    public final EObject entryRuleImportAnnotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImportAnnotation = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1413:2: (iv_ruleImportAnnotation= ruleImportAnnotation EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1414:2: iv_ruleImportAnnotation= ruleImportAnnotation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportAnnotationRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleImportAnnotation_in_entryRuleImportAnnotation2721);
            iv_ruleImportAnnotation=ruleImportAnnotation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImportAnnotation; 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleImportAnnotation2731); if (state.failed) return current;

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
    // $ANTLR end "entryRuleImportAnnotation"


    // $ANTLR start "ruleImportAnnotation"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1421:1: ruleImportAnnotation returns [EObject current=null] : (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleImportAnnotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_importURI_1_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1424:28: ( (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1425:1: (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1425:1: (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1425:3: otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleImportAnnotation2768); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getImportAnnotationAccess().getImportKeyword_0());
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1429:1: ( (lv_importURI_1_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1430:1: (lv_importURI_1_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1430:1: (lv_importURI_1_0= RULE_STRING )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1431:3: lv_importURI_1_0= RULE_STRING
            {
            lv_importURI_1_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleImportAnnotation2785); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_importURI_1_0, grammarAccess.getImportAnnotationAccess().getImportURISTRINGTerminalRuleCall_1_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getImportAnnotationRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"importURI",
                      		lv_importURI_1_0, 
                      		"STRING");
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleImportAnnotation"


    // $ANTLR start "entryRuleEString"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1455:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1456:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1457:2: iv_ruleEString= ruleEString EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEStringRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString2827);
            iv_ruleEString=ruleEString();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEString.getText(); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString2838); if (state.failed) return current;

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
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1464:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1467:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1468:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1468:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==RULE_STRING) ) {
                alt30=1;
            }
            else if ( (LA30_0==RULE_ID) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1468:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString2878); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_STRING_0);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1476:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString2904); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_ID_1);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleExtendedID"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1491:1: entryRuleExtendedID returns [String current=null] : iv_ruleExtendedID= ruleExtendedID EOF ;
    public final String entryRuleExtendedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleExtendedID = null;


        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1492:2: (iv_ruleExtendedID= ruleExtendedID EOF )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1493:2: iv_ruleExtendedID= ruleExtendedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExtendedIDRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleExtendedID_in_entryRuleExtendedID2950);
            iv_ruleExtendedID=ruleExtendedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExtendedID.getText(); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleExtendedID2961); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExtendedID"


    // $ANTLR start "ruleExtendedID"
    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1500:1: ruleExtendedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleExtendedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1503:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1504:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1504:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1504:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleExtendedID3001); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_0()); 
                  
            }
            // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1511:1: (kw= '.' this_ID_2= RULE_ID )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==28) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:1512:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleExtendedID3020); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getExtendedIDAccess().getFullStopKeyword_1_0()); 
            	          
            	    }
            	    this_ID_2=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleExtendedID3035); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ID_2);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_ID_2, grammarAccess.getExtendedIDAccess().getIDTerminalRuleCall_1_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleExtendedID"

    // $ANTLR start synpred4_InternalKaom
    public final void synpred4_InternalKaom_fragment() throws RecognitionException {   
        EObject lv_childEntities_7_0 = null;


        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:175:2: ( ( (lv_childEntities_7_0= ruleEntity ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:175:2: ( (lv_childEntities_7_0= ruleEntity ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:175:2: ( (lv_childEntities_7_0= ruleEntity ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:176:1: (lv_childEntities_7_0= ruleEntity )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:176:1: (lv_childEntities_7_0= ruleEntity )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:177:3: lv_childEntities_7_0= ruleEntity
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildEntitiesEntityParserRuleCall_2_4_0_1_0_0()); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleEntity_in_synpred4_InternalKaom266);
        lv_childEntities_7_0=ruleEntity();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred4_InternalKaom

    // $ANTLR start synpred5_InternalKaom
    public final void synpred5_InternalKaom_fragment() throws RecognitionException {   
        EObject lv_childLinks_8_0 = null;


        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:194:6: ( ( (lv_childLinks_8_0= ruleLink ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:194:6: ( (lv_childLinks_8_0= ruleLink ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:194:6: ( (lv_childLinks_8_0= ruleLink ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:195:1: (lv_childLinks_8_0= ruleLink )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:195:1: (lv_childLinks_8_0= ruleLink )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:196:3: lv_childLinks_8_0= ruleLink
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildLinksLinkParserRuleCall_2_4_0_1_1_0()); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleLink_in_synpred5_InternalKaom293);
        lv_childLinks_8_0=ruleLink();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred5_InternalKaom

    // $ANTLR start synpred6_InternalKaom
    public final void synpred6_InternalKaom_fragment() throws RecognitionException {   
        EObject lv_childPorts_9_0 = null;


        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:213:6: ( ( (lv_childPorts_9_0= rulePort ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:213:6: ( (lv_childPorts_9_0= rulePort ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:213:6: ( (lv_childPorts_9_0= rulePort ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:214:1: (lv_childPorts_9_0= rulePort )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:214:1: (lv_childPorts_9_0= rulePort )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:215:3: lv_childPorts_9_0= rulePort
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildPortsPortParserRuleCall_2_4_0_1_2_0()); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_rulePort_in_synpred6_InternalKaom320);
        lv_childPorts_9_0=rulePort();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred6_InternalKaom

    // $ANTLR start synpred7_InternalKaom
    public final void synpred7_InternalKaom_fragment() throws RecognitionException {   
        EObject lv_childRelations_10_0 = null;


        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:232:6: ( ( (lv_childRelations_10_0= ruleRelation ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:232:6: ( (lv_childRelations_10_0= ruleRelation ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:232:6: ( (lv_childRelations_10_0= ruleRelation ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:233:1: (lv_childRelations_10_0= ruleRelation )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:233:1: (lv_childRelations_10_0= ruleRelation )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:234:3: lv_childRelations_10_0= ruleRelation
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getTopLevelEntityAccess().getChildRelationsRelationParserRuleCall_2_4_0_1_3_0()); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleRelation_in_synpred7_InternalKaom347);
        lv_childRelations_10_0=ruleRelation();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred7_InternalKaom

    // $ANTLR start synpred12_InternalKaom
    public final void synpred12_InternalKaom_fragment() throws RecognitionException {   
        EObject lv_childEntities_6_0 = null;


        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:351:2: ( ( (lv_childEntities_6_0= ruleEntity ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:351:2: ( (lv_childEntities_6_0= ruleEntity ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:351:2: ( (lv_childEntities_6_0= ruleEntity ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:352:1: (lv_childEntities_6_0= ruleEntity )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:352:1: (lv_childEntities_6_0= ruleEntity )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:353:3: lv_childEntities_6_0= ruleEntity
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getEntityAccess().getChildEntitiesEntityParserRuleCall_5_0_1_0_0()); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleEntity_in_synpred12_InternalKaom581);
        lv_childEntities_6_0=ruleEntity();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred12_InternalKaom

    // $ANTLR start synpred13_InternalKaom
    public final void synpred13_InternalKaom_fragment() throws RecognitionException {   
        EObject lv_childLinks_7_0 = null;


        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:370:6: ( ( (lv_childLinks_7_0= ruleLink ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:370:6: ( (lv_childLinks_7_0= ruleLink ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:370:6: ( (lv_childLinks_7_0= ruleLink ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:371:1: (lv_childLinks_7_0= ruleLink )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:371:1: (lv_childLinks_7_0= ruleLink )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:372:3: lv_childLinks_7_0= ruleLink
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getEntityAccess().getChildLinksLinkParserRuleCall_5_0_1_1_0()); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleLink_in_synpred13_InternalKaom608);
        lv_childLinks_7_0=ruleLink();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred13_InternalKaom

    // $ANTLR start synpred14_InternalKaom
    public final void synpred14_InternalKaom_fragment() throws RecognitionException {   
        EObject lv_childPorts_8_0 = null;


        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:389:6: ( ( (lv_childPorts_8_0= rulePort ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:389:6: ( (lv_childPorts_8_0= rulePort ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:389:6: ( (lv_childPorts_8_0= rulePort ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:390:1: (lv_childPorts_8_0= rulePort )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:390:1: (lv_childPorts_8_0= rulePort )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:391:3: lv_childPorts_8_0= rulePort
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getEntityAccess().getChildPortsPortParserRuleCall_5_0_1_2_0()); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_rulePort_in_synpred14_InternalKaom635);
        lv_childPorts_8_0=rulePort();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred14_InternalKaom

    // $ANTLR start synpred15_InternalKaom
    public final void synpred15_InternalKaom_fragment() throws RecognitionException {   
        EObject lv_childRelations_9_0 = null;


        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:408:6: ( ( (lv_childRelations_9_0= ruleRelation ) ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:408:6: ( (lv_childRelations_9_0= ruleRelation ) )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:408:6: ( (lv_childRelations_9_0= ruleRelation ) )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:409:1: (lv_childRelations_9_0= ruleRelation )
        {
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:409:1: (lv_childRelations_9_0= ruleRelation )
        // ../de.cau.cs.kieler.kaom.text/src-gen/de/cau/cs/kieler/kaom/text/parser/antlr/internal/InternalKaom.g:410:3: lv_childRelations_9_0= ruleRelation
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getEntityAccess().getChildRelationsRelationParserRuleCall_5_0_1_3_0()); 
          	    
        }
        pushFollow(FollowSets000.FOLLOW_ruleRelation_in_synpred15_InternalKaom662);
        lv_childRelations_9_0=ruleRelation();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred15_InternalKaom

    // Delegated rules

    public final boolean synpred15_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_InternalKaom() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_InternalKaom_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA17_eotS =
        "\14\uffff";
    static final String DFA17_eofS =
        "\3\uffff\1\10\7\uffff\1\10";
    static final String DFA17_minS =
        "\1\6\1\uffff\3\4\6\uffff\1\4";
    static final String DFA17_maxS =
        "\1\26\1\uffff\1\4\1\34\1\4\6\uffff\1\34";
    static final String DFA17_acceptS =
        "\1\uffff\1\1\3\uffff\1\4\1\3\1\5\1\2\1\6\1\7\1\uffff";
    static final String DFA17_specialS =
        "\14\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\1\17\uffff\1\2",
            "",
            "\1\3",
            "\2\6\1\10\1\7\1\11\1\12\4\uffff\1\10\3\uffff\1\10\1\uffff\5"+
            "\10\1\5\2\uffff\1\4",
            "\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\6\1\10\1\7\1\11\1\12\4\uffff\1\10\3\uffff\1\10\1\uffff\5"+
            "\10\1\5\2\uffff\1\4"
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
            return "743:1: (this_CommentAnnotation_0= ruleCommentAnnotation | this_TagAnnotation_1= ruleTagAnnotation | this_KeyStringValueAnnotation_2= ruleKeyStringValueAnnotation | this_TypedKeyStringValueAnnotation_3= ruleTypedKeyStringValueAnnotation | this_KeyBooleanValueAnnotation_4= ruleKeyBooleanValueAnnotation | this_KeyIntValueAnnotation_5= ruleKeyIntValueAnnotation | this_KeyFloatValueAnnotation_6= ruleKeyFloatValueAnnotation )";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleTopLevelEntity_in_entryRuleTopLevelEntity81 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTopLevelEntity91 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleImportAnnotation_in_ruleTopLevelEntity149 = new BitSet(new long[]{0x0000000008404042L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTopLevelEntity172 = new BitSet(new long[]{0x0000000000404040L});
        public static final BitSet FOLLOW_14_in_ruleTopLevelEntity185 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleTopLevelEntity202 = new BitSet(new long[]{0x0000000000028020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleTopLevelEntity224 = new BitSet(new long[]{0x0000000000028000L});
        public static final BitSet FOLLOW_15_in_ruleTopLevelEntity244 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleEntity_in_ruleTopLevelEntity266 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleLink_in_ruleTopLevelEntity293 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_rulePort_in_ruleTopLevelEntity320 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleRelation_in_ruleTopLevelEntity347 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_16_in_ruleTopLevelEntity361 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_ruleTopLevelEntity380 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_entryRuleEntity419 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEntity429 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleEntity487 = new BitSet(new long[]{0x0000000000404040L});
        public static final BitSet FOLLOW_14_in_ruleEntity500 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEntity517 = new BitSet(new long[]{0x0000000000028020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEntity539 = new BitSet(new long[]{0x0000000000028000L});
        public static final BitSet FOLLOW_15_in_ruleEntity559 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleEntity_in_ruleEntity581 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleLink_in_ruleEntity608 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_rulePort_in_ruleEntity635 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_ruleRelation_in_ruleEntity662 = new BitSet(new long[]{0x0000000000754040L});
        public static final BitSet FOLLOW_16_in_ruleEntity676 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_ruleEntity695 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_entryRuleLink734 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLink744 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleLink790 = new BitSet(new long[]{0x0000000000440040L});
        public static final BitSet FOLLOW_18_in_ruleLink803 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleLink820 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleLink850 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_19_in_ruleLink862 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleLink886 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleLink898 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_entryRulePort934 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePort944 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_rulePort1002 = new BitSet(new long[]{0x0000000000500040L});
        public static final BitSet FOLLOW_20_in_rulePort1015 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_rulePort1032 = new BitSet(new long[]{0x0000000000020020L});
        public static final BitSet FOLLOW_RULE_STRING_in_rulePort1054 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_rulePort1072 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_entryRuleRelation1108 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleRelation1118 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleRelation1176 = new BitSet(new long[]{0x0000000000600040L});
        public static final BitSet FOLLOW_21_in_ruleRelation1189 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleRelation1206 = new BitSet(new long[]{0x0000000000020020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleRelation1228 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleRelation1246 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleAnnotation_in_entryRuleAnnotation1282 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleAnnotation1292 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_ruleAnnotation1342 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_ruleAnnotation1372 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_ruleAnnotation1402 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedKeyStringValueAnnotation_in_ruleAnnotation1432 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_ruleAnnotation1462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_ruleAnnotation1492 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_ruleAnnotation1522 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCommentAnnotation_in_entryRuleCommentAnnotation1559 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCommentAnnotation1569 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_COMMENT_ANNOTATION_in_ruleCommentAnnotation1610 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTagAnnotation_in_entryRuleTagAnnotation1650 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTagAnnotation1660 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleTagAnnotation1697 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTagAnnotation1718 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleTagAnnotation1731 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTagAnnotation1752 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleTagAnnotation1765 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyStringValueAnnotation_in_entryRuleKeyStringValueAnnotation1803 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyStringValueAnnotation1813 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyStringValueAnnotation1850 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyStringValueAnnotation1871 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKeyStringValueAnnotation1892 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleKeyStringValueAnnotation1905 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyStringValueAnnotation1926 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleKeyStringValueAnnotation1939 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedKeyStringValueAnnotation_in_entryRuleTypedKeyStringValueAnnotation1977 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTypedKeyStringValueAnnotation1987 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleTypedKeyStringValueAnnotation2024 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation2045 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_25_in_ruleTypedKeyStringValueAnnotation2057 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleTypedKeyStringValueAnnotation2078 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleTypedKeyStringValueAnnotation2090 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleTypedKeyStringValueAnnotation2111 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleTypedKeyStringValueAnnotation2124 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleTypedKeyStringValueAnnotation2145 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleTypedKeyStringValueAnnotation2158 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyBooleanValueAnnotation_in_entryRuleKeyBooleanValueAnnotation2196 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyBooleanValueAnnotation2206 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyBooleanValueAnnotation2243 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyBooleanValueAnnotation2264 = new BitSet(new long[]{0x0000000000000080L});
        public static final BitSet FOLLOW_RULE_BOOLEAN_in_ruleKeyBooleanValueAnnotation2281 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleKeyBooleanValueAnnotation2299 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyBooleanValueAnnotation2320 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleKeyBooleanValueAnnotation2333 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyIntValueAnnotation_in_entryRuleKeyIntValueAnnotation2371 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyIntValueAnnotation2381 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyIntValueAnnotation2418 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyIntValueAnnotation2439 = new BitSet(new long[]{0x0000000000000100L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleKeyIntValueAnnotation2456 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleKeyIntValueAnnotation2474 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyIntValueAnnotation2495 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleKeyIntValueAnnotation2508 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKeyFloatValueAnnotation_in_entryRuleKeyFloatValueAnnotation2546 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKeyFloatValueAnnotation2556 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKeyFloatValueAnnotation2593 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_ruleExtendedID_in_ruleKeyFloatValueAnnotation2614 = new BitSet(new long[]{0x0000000000000200L});
        public static final BitSet FOLLOW_RULE_FLOAT_in_ruleKeyFloatValueAnnotation2631 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_23_in_ruleKeyFloatValueAnnotation2649 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_ruleAnnotation_in_ruleKeyFloatValueAnnotation2670 = new BitSet(new long[]{0x0000000001400040L});
        public static final BitSet FOLLOW_24_in_ruleKeyFloatValueAnnotation2683 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleImportAnnotation_in_entryRuleImportAnnotation2721 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleImportAnnotation2731 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleImportAnnotation2768 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleImportAnnotation2785 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString2827 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString2838 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString2878 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString2904 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleExtendedID_in_entryRuleExtendedID2950 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleExtendedID2961 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleExtendedID3001 = new BitSet(new long[]{0x0000000010000002L});
        public static final BitSet FOLLOW_28_in_ruleExtendedID3020 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleExtendedID3035 = new BitSet(new long[]{0x0000000010000002L});
        public static final BitSet FOLLOW_ruleEntity_in_synpred4_InternalKaom266 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_synpred5_InternalKaom293 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_synpred6_InternalKaom320 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_synpred7_InternalKaom347 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEntity_in_synpred12_InternalKaom581 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_synpred13_InternalKaom608 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePort_in_synpred14_InternalKaom635 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleRelation_in_synpred15_InternalKaom662 = new BitSet(new long[]{0x0000000000000002L});
    }


}