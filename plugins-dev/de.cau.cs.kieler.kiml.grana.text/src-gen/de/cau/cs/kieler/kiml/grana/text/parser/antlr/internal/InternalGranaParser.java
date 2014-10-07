package de.cau.cs.kieler.kiml.grana.text.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGranaParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_BOOLEAN", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'globalResources'", "'job'", "'layoutBeforeAnalysis'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'"
    };
    public static final int RULE_BOOLEAN=6;
    public static final int RULE_ID=4;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_NATURAL=8;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=9;
    public static final int RULE_TFLOAT=7;
    public static final int T__19=19;
    public static final int RULE_STRING=5;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_WS=11;

    // delegates
    // delegators


        public InternalGranaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalGranaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalGranaParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g"; }



     	private GranaGrammarAccess grammarAccess;
     	
        public InternalGranaParser(TokenStream input, GranaGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Grana";	
       	}
       	
       	@Override
       	protected GranaGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleGrana"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:67:1: entryRuleGrana returns [EObject current=null] : iv_ruleGrana= ruleGrana EOF ;
    public final EObject entryRuleGrana() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGrana = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:68:2: (iv_ruleGrana= ruleGrana EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:69:2: iv_ruleGrana= ruleGrana EOF
            {
             newCompositeNode(grammarAccess.getGranaRule()); 
            pushFollow(FOLLOW_ruleGrana_in_entryRuleGrana75);
            iv_ruleGrana=ruleGrana();

            state._fsp--;

             current =iv_ruleGrana; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGrana85); 

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
    // $ANTLR end "entryRuleGrana"


    // $ANTLR start "ruleGrana"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:76:1: ruleGrana returns [EObject current=null] : ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? ( (lv_jobs_2_0= ruleJob ) )+ ) ;
    public final EObject ruleGrana() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_globalResources_1_0 = null;

        EObject lv_jobs_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:79:28: ( ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? ( (lv_jobs_2_0= ruleJob ) )+ ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:80:1: ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? ( (lv_jobs_2_0= ruleJob ) )+ )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:80:1: ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? ( (lv_jobs_2_0= ruleJob ) )+ )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:80:2: (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? ( (lv_jobs_2_0= ruleJob ) )+
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:80:2: (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==12) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:80:4: otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )*
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleGrana123); 

                        	newLeafNode(otherlv_0, grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0());
                        
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:84:1: ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:85:1: (lv_globalResources_1_0= ruleGlobalResourceRef )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:85:1: (lv_globalResources_1_0= ruleGlobalResourceRef )
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:86:3: lv_globalResources_1_0= ruleGlobalResourceRef
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGranaAccess().getGlobalResourcesGlobalResourceRefParserRuleCall_0_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGlobalResourceRef_in_ruleGrana144);
                    	    lv_globalResources_1_0=ruleGlobalResourceRef();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getGranaRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"globalResources",
                    	            		lv_globalResources_1_0, 
                    	            		"GlobalResourceRef");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:102:5: ( (lv_jobs_2_0= ruleJob ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==13) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:103:1: (lv_jobs_2_0= ruleJob )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:103:1: (lv_jobs_2_0= ruleJob )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:104:3: lv_jobs_2_0= ruleJob
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleJob_in_ruleGrana168);
            	    lv_jobs_2_0=ruleJob();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getGranaRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"jobs",
            	            		lv_jobs_2_0, 
            	            		"Job");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGrana"


    // $ANTLR start "entryRuleJob"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:128:1: entryRuleJob returns [EObject current=null] : iv_ruleJob= ruleJob EOF ;
    public final EObject entryRuleJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJob = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:129:2: (iv_ruleJob= ruleJob EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:130:2: iv_ruleJob= ruleJob EOF
            {
             newCompositeNode(grammarAccess.getJobRule()); 
            pushFollow(FOLLOW_ruleJob_in_entryRuleJob205);
            iv_ruleJob=ruleJob();

            state._fsp--;

             current =iv_ruleJob; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJob215); 

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
    // $ANTLR end "entryRuleJob"


    // $ANTLR start "ruleJob"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:137:1: ruleJob returns [EObject current=null] : ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )* otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) ) otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )* otherlv_10= 'output' ( (lv_output_11_0= RULE_STRING ) ) ) ;
    public final EObject ruleJob() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token lv_layoutBeforeAnalysis_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token lv_output_11_0=null;
        EObject lv_resources_5_0 = null;

        EObject lv_layoutOptions_7_0 = null;

        EObject lv_analyses_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:140:28: ( ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )* otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) ) otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )* otherlv_10= 'output' ( (lv_output_11_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:141:1: ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )* otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) ) otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )* otherlv_10= 'output' ( (lv_output_11_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:141:1: ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )* otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) ) otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )* otherlv_10= 'output' ( (lv_output_11_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:141:2: () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )* otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) ) otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )* otherlv_10= 'output' ( (lv_output_11_0= RULE_STRING ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:141:2: ()
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:142:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getJobAccess().getJobAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,13,FOLLOW_13_in_ruleJob261); 

                	newLeafNode(otherlv_1, grammarAccess.getJobAccess().getJobKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:151:1: ( (lv_name_2_0= RULE_ID ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:152:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:152:1: (lv_name_2_0= RULE_ID )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:153:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJob278); 

                    			newLeafNode(lv_name_2_0, grammarAccess.getJobAccess().getNameIDTerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getJobRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_2_0, 
                            		"ID");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:169:3: ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==14) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:170:1: (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:170:1: (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:171:3: lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis'
                    {
                    lv_layoutBeforeAnalysis_3_0=(Token)match(input,14,FOLLOW_14_in_ruleJob302); 

                            newLeafNode(lv_layoutBeforeAnalysis_3_0, grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getJobRule());
                    	        }
                           		setWithLastConsumed(current, "layoutBeforeAnalysis", true, "layoutBeforeAnalysis");
                    	    

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,15,FOLLOW_15_in_ruleJob328); 

                	newLeafNode(otherlv_4, grammarAccess.getJobAccess().getResourcesKeyword_4());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:188:1: ( (lv_resources_5_0= ruleResource ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_STRING||LA6_0==19) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:189:1: (lv_resources_5_0= ruleResource )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:189:1: (lv_resources_5_0= ruleResource )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:190:3: lv_resources_5_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleResource_in_ruleJob349);
            	    lv_resources_5_0=ruleResource();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"resources",
            	            		lv_resources_5_0, 
            	            		"Resource");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            otherlv_6=(Token)match(input,16,FOLLOW_16_in_ruleJob362); 

                	newLeafNode(otherlv_6, grammarAccess.getJobAccess().getLayoutoptionsKeyword_6());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:210:1: ( (lv_layoutOptions_7_0= ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:211:1: (lv_layoutOptions_7_0= ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:211:1: (lv_layoutOptions_7_0= ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:212:3: lv_layoutOptions_7_0= ruleKIdentifier
            {
             
            	        newCompositeNode(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 
            	    
            pushFollow(FOLLOW_ruleKIdentifier_in_ruleJob383);
            lv_layoutOptions_7_0=ruleKIdentifier();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getJobRule());
            	        }
                   		add(
                   			current, 
                   			"layoutOptions",
                    		lv_layoutOptions_7_0, 
                    		"KIdentifier");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_8=(Token)match(input,17,FOLLOW_17_in_ruleJob395); 

                	newLeafNode(otherlv_8, grammarAccess.getJobAccess().getAnalysesKeyword_8());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:232:1: ( (lv_analyses_9_0= ruleAnalysis ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:233:1: (lv_analyses_9_0= ruleAnalysis )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:233:1: (lv_analyses_9_0= ruleAnalysis )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:234:3: lv_analyses_9_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnalysis_in_ruleJob416);
            	    lv_analyses_9_0=ruleAnalysis();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"analyses",
            	            		lv_analyses_9_0, 
            	            		"Analysis");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            otherlv_10=(Token)match(input,18,FOLLOW_18_in_ruleJob429); 

                	newLeafNode(otherlv_10, grammarAccess.getJobAccess().getOutputKeyword_10());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:254:1: ( (lv_output_11_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:255:1: (lv_output_11_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:255:1: (lv_output_11_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:256:3: lv_output_11_0= RULE_STRING
            {
            lv_output_11_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleJob446); 

            			newLeafNode(lv_output_11_0, grammarAccess.getJobAccess().getOutputSTRINGTerminalRuleCall_11_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getJobRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"output",
                    		lv_output_11_0, 
                    		"STRING");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJob"


    // $ANTLR start "entryRuleResource"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:280:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:281:2: (iv_ruleResource= ruleResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:282:2: iv_ruleResource= ruleResource EOF
            {
             newCompositeNode(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_ruleResource_in_entryRuleResource487);
            iv_ruleResource=ruleResource();

            state._fsp--;

             current =iv_ruleResource; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResource497); 

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
    // $ANTLR end "entryRuleResource"


    // $ANTLR start "ruleResource"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:289:1: ruleResource returns [EObject current=null] : (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        EObject this_ResourceReference_0 = null;

        EObject this_LocalResource_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:292:28: ( (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:293:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:293:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            else if ( (LA8_0==RULE_STRING) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:294:5: this_ResourceReference_0= ruleResourceReference
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleResourceReference_in_ruleResource544);
                    this_ResourceReference_0=ruleResourceReference();

                    state._fsp--;

                     
                            current = this_ResourceReference_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:304:5: this_LocalResource_1= ruleLocalResource
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLocalResource_in_ruleResource571);
                    this_LocalResource_1=ruleLocalResource();

                    state._fsp--;

                     
                            current = this_LocalResource_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleResource"


    // $ANTLR start "entryRuleResourceReference"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:320:1: entryRuleResourceReference returns [EObject current=null] : iv_ruleResourceReference= ruleResourceReference EOF ;
    public final EObject entryRuleResourceReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceReference = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:321:2: (iv_ruleResourceReference= ruleResourceReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:322:2: iv_ruleResourceReference= ruleResourceReference EOF
            {
             newCompositeNode(grammarAccess.getResourceReferenceRule()); 
            pushFollow(FOLLOW_ruleResourceReference_in_entryRuleResourceReference606);
            iv_ruleResourceReference=ruleResourceReference();

            state._fsp--;

             current =iv_ruleResourceReference; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResourceReference616); 

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
    // $ANTLR end "entryRuleResourceReference"


    // $ANTLR start "ruleResourceReference"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:329:1: ruleResourceReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) ;
    public final EObject ruleResourceReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:332:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:333:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:333:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:333:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleResourceReference653); 

                	newLeafNode(otherlv_0, grammarAccess.getResourceReferenceAccess().getRefKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:337:1: ( (otherlv_1= RULE_ID ) )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:338:1: (otherlv_1= RULE_ID )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:338:1: (otherlv_1= RULE_ID )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:339:3: otherlv_1= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getResourceReferenceRule());
            	    	        }
            	            
            	    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleResourceReference673); 

            	    		newLeafNode(otherlv_1, grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            	    	

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleResourceReference"


    // $ANTLR start "entryRuleGlobalResourceRef"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:358:1: entryRuleGlobalResourceRef returns [EObject current=null] : iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF ;
    public final EObject entryRuleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalResourceRef = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:359:2: (iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:360:2: iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF
            {
             newCompositeNode(grammarAccess.getGlobalResourceRefRule()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef710);
            iv_ruleGlobalResourceRef=ruleGlobalResourceRef();

            state._fsp--;

             current =iv_ruleGlobalResourceRef; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalResourceRef720); 

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
    // $ANTLR end "entryRuleGlobalResourceRef"


    // $ANTLR start "ruleGlobalResourceRef"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:367:1: ruleGlobalResourceRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) ;
    public final EObject ruleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_resources_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:370:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:371:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:371:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:371:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:371:2: ( (lv_name_0_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:372:1: (lv_name_0_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:372:1: (lv_name_0_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:373:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGlobalResourceRef762); 

            			newLeafNode(lv_name_0_0, grammarAccess.getGlobalResourceRefAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getGlobalResourceRefRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:389:2: ( (lv_resources_1_0= ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:390:1: (lv_resources_1_0= ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:390:1: (lv_resources_1_0= ruleLocalResource )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:391:3: lv_resources_1_0= ruleLocalResource
            {
             
            	        newCompositeNode(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLocalResource_in_ruleGlobalResourceRef788);
            lv_resources_1_0=ruleLocalResource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGlobalResourceRefRule());
            	        }
                   		add(
                   			current, 
                   			"resources",
                    		lv_resources_1_0, 
                    		"LocalResource");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGlobalResourceRef"


    // $ANTLR start "entryRuleLocalResource"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:415:1: entryRuleLocalResource returns [EObject current=null] : iv_ruleLocalResource= ruleLocalResource EOF ;
    public final EObject entryRuleLocalResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalResource = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:416:2: (iv_ruleLocalResource= ruleLocalResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:417:2: iv_ruleLocalResource= ruleLocalResource EOF
            {
             newCompositeNode(grammarAccess.getLocalResourceRule()); 
            pushFollow(FOLLOW_ruleLocalResource_in_entryRuleLocalResource824);
            iv_ruleLocalResource=ruleLocalResource();

            state._fsp--;

             current =iv_ruleLocalResource; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalResource834); 

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
    // $ANTLR end "entryRuleLocalResource"


    // $ANTLR start "ruleLocalResource"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:424:1: ruleLocalResource returns [EObject current=null] : ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleLocalResource() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;
        Token otherlv_1=null;
        Token lv_filter_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:427:28: ( ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:428:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:428:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:428:2: ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:428:2: ( (lv_path_0_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:429:1: (lv_path_0_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:429:1: (lv_path_0_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:430:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalResource876); 

            			newLeafNode(lv_path_0_0, grammarAccess.getLocalResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalResourceRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"path",
                    		lv_path_0_0, 
                    		"STRING");
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:446:2: (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:446:4: otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) )
            {
            otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleLocalResource894); 

                	newLeafNode(otherlv_1, grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:450:1: ( (lv_filter_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:451:1: (lv_filter_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:451:1: (lv_filter_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:452:3: lv_filter_2_0= RULE_STRING
            {
            lv_filter_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalResource911); 

            			newLeafNode(lv_filter_2_0, grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalResourceRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"filter",
                    		lv_filter_2_0, 
                    		"STRING");
            	    

            }


            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLocalResource"


    // $ANTLR start "entryRuleAnalysis"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:476:1: entryRuleAnalysis returns [EObject current=null] : iv_ruleAnalysis= ruleAnalysis EOF ;
    public final EObject entryRuleAnalysis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysis = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:477:2: (iv_ruleAnalysis= ruleAnalysis EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:478:2: iv_ruleAnalysis= ruleAnalysis EOF
            {
             newCompositeNode(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_ruleAnalysis_in_entryRuleAnalysis953);
            iv_ruleAnalysis=ruleAnalysis();

            state._fsp--;

             current =iv_ruleAnalysis; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalysis963); 

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
    // $ANTLR end "entryRuleAnalysis"


    // $ANTLR start "ruleAnalysis"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:485:1: ruleAnalysis returns [EObject current=null] : ( (lv_name_0_0= ruleQualifiedID ) ) ;
    public final EObject ruleAnalysis() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:488:28: ( ( (lv_name_0_0= ruleQualifiedID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:489:1: ( (lv_name_0_0= ruleQualifiedID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:489:1: ( (lv_name_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:490:1: (lv_name_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:490:1: (lv_name_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:491:3: lv_name_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleAnalysis1008);
            lv_name_0_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAnalysisRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"QualifiedID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnalysis"


    // $ANTLR start "entryRuleKIdentifier"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:515:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:516:2: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:517:2: iv_ruleKIdentifier= ruleKIdentifier EOF
            {
             newCompositeNode(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier1043);
            iv_ruleKIdentifier=ruleKIdentifier();

            state._fsp--;

             current =iv_ruleKIdentifier; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKIdentifier1053); 

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
    // $ANTLR end "entryRuleKIdentifier"


    // $ANTLR start "ruleKIdentifier"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:524:1: ruleKIdentifier returns [EObject current=null] : ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_persistentEntries_3_0 = null;

        EObject lv_persistentEntries_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:527:28: ( ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:528:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:528:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:528:2: () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:528:2: ()
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:529:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKIdentifierAccess().getKIdentifierAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:534:2: ( (lv_id_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:535:1: (lv_id_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:535:1: (lv_id_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:536:3: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleKIdentifier1104); 

            			newLeafNode(lv_id_1_0, grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKIdentifierRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleKIdentifier1121); 

                	newLeafNode(otherlv_2, grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:556:1: ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:556:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:556:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:557:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:557:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:558:3: lv_persistentEntries_3_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulePersistentEntry_in_ruleKIdentifier1143);
                    lv_persistentEntries_3_0=rulePersistentEntry();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKIdentifierRule());
                    	        }
                           		add(
                           			current, 
                           			"persistentEntries",
                            		lv_persistentEntries_3_0, 
                            		"PersistentEntry");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:574:2: ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==RULE_ID) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:575:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:575:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:576:3: lv_persistentEntries_4_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePersistentEntry_in_ruleKIdentifier1164);
                    	    lv_persistentEntries_4_0=rulePersistentEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKIdentifierRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"persistentEntries",
                    	            		lv_persistentEntries_4_0, 
                    	            		"PersistentEntry");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,22,FOLLOW_22_in_ruleKIdentifier1179); 

                	newLeafNode(otherlv_5, grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKIdentifier"


    // $ANTLR start "entryRulePersistentEntry"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:604:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:605:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:606:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry1215);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistentEntry1225); 

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
    // $ANTLR end "entryRulePersistentEntry"


    // $ANTLR start "rulePersistentEntry"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:613:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:616:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:617:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:617:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:617:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:617:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:618:1: (lv_key_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:618:1: (lv_key_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:619:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_rulePersistentEntry1271);
            lv_key_0_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
            	        }
                   		set(
                   			current, 
                   			"key",
                    		lv_key_0_0, 
                    		"QualifiedID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,23,FOLLOW_23_in_rulePersistentEntry1283); 

                	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getColonKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:639:1: ( (lv_value_2_0= rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:640:1: (lv_value_2_0= rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:640:1: (lv_value_2_0= rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:641:3: lv_value_2_0= rulePropertyValue
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_rulePropertyValue_in_rulePersistentEntry1304);
            lv_value_2_0=rulePropertyValue();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_2_0, 
                    		"PropertyValue");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePersistentEntry"


    // $ANTLR start "entryRuleQualifiedID"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:665:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:666:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:667:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID1341);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID1352); 

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
    // $ANTLR end "entryRuleQualifiedID"


    // $ANTLR start "ruleQualifiedID"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:674:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:677:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:678:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:678:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:678:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID1392); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:685:1: (kw= '.' this_ID_2= RULE_ID )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==24) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:686:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,24,FOLLOW_24_in_ruleQualifiedID1411); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID1426); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedID"


    // $ANTLR start "entryRulePropertyValue"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:706:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:707:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:708:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
             newCompositeNode(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue1474);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;

             current =iv_rulePropertyValue.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue1485); 

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
    // $ANTLR end "entryRulePropertyValue"


    // $ANTLR start "rulePropertyValue"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:715:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:718:28: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:719:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:719:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            int alt13=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt13=1;
                }
                break;
            case RULE_STRING:
                {
                alt13=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt13=3;
                }
                break;
            case RULE_ID:
                {
                alt13=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:719:6: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rulePropertyValue1525); 

                    		current.merge(this_BOOLEAN_0);
                        
                     
                        newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:727:10: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePropertyValue1551); 

                    		current.merge(this_STRING_1);
                        
                     
                        newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:736:5: this_Float_2= ruleFloat
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleFloat_in_rulePropertyValue1584);
                    this_Float_2=ruleFloat();

                    state._fsp--;


                    		current.merge(this_Float_2);
                        
                     
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:748:5: this_QualifiedID_3= ruleQualifiedID
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleQualifiedID_in_rulePropertyValue1617);
                    this_QualifiedID_3=ruleQualifiedID();

                    state._fsp--;


                    		current.merge(this_QualifiedID_3);
                        
                     
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePropertyValue"


    // $ANTLR start "entryRuleFloat"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:766:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:767:2: (iv_ruleFloat= ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:768:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_ruleFloat_in_entryRuleFloat1663);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloat1674); 

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
    // $ANTLR end "entryRuleFloat"


    // $ANTLR start "ruleFloat"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:775:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:778:28: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:779:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:779:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_TFLOAT) ) {
                alt14=1;
            }
            else if ( (LA14_0==RULE_NATURAL) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:779:6: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FOLLOW_RULE_TFLOAT_in_ruleFloat1714); 

                    		current.merge(this_TFLOAT_0);
                        
                     
                        newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:787:10: this_NATURAL_1= RULE_NATURAL
                    {
                    this_NATURAL_1=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleFloat1740); 

                    		current.merge(this_NATURAL_1);
                        
                     
                        newLeafNode(this_NATURAL_1, grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFloat"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleGrana_in_entryRuleGrana75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGrana85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleGrana123 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_ruleGrana144 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_ruleJob_in_ruleGrana168 = new BitSet(new long[]{0x0000000000002012L});
    public static final BitSet FOLLOW_ruleJob_in_entryRuleJob205 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJob215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleJob261 = new BitSet(new long[]{0x000000000000C010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJob278 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_14_in_ruleJob302 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleJob328 = new BitSet(new long[]{0x0000000000090020L});
    public static final BitSet FOLLOW_ruleResource_in_ruleJob349 = new BitSet(new long[]{0x0000000000090020L});
    public static final BitSet FOLLOW_16_in_ruleJob362 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_ruleJob383 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleJob395 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_ruleAnalysis_in_ruleJob416 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_18_in_ruleJob429 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleJob446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_entryRuleResource487 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResource497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_ruleResource544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_ruleResource571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_entryRuleResourceReference606 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResourceReference616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleResourceReference653 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleResourceReference673 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef710 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalResourceRef720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGlobalResourceRef762 = new BitSet(new long[]{0x0000000000080020L});
    public static final BitSet FOLLOW_ruleLocalResource_in_ruleGlobalResourceRef788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_entryRuleLocalResource824 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalResource834 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalResource876 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleLocalResource894 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalResource911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_entryRuleAnalysis953 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalysis963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleAnalysis1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier1043 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleKIdentifier1104 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleKIdentifier1121 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier1143 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier1164 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_22_in_ruleKIdentifier1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry1215 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry1225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rulePersistentEntry1271 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_rulePersistentEntry1283 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rulePropertyValue_in_rulePersistentEntry1304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID1341 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID1352 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID1392 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_ruleQualifiedID1411 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID1426 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue1474 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue1485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rulePropertyValue1525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePropertyValue1551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rulePropertyValue1584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rulePropertyValue1617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat1663 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloat1674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TFLOAT_in_ruleFloat1714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleFloat1740 = new BitSet(new long[]{0x0000000000000002L});

}