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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_BOOLEAN", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'globalResources'", "'job'", "'layoutBeforeAnalysis'", "'measureExecutionTime'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'"
    };
    public static final int RULE_BOOLEAN=6;
    public static final int RULE_ID=4;
    public static final int T__25=25;
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:137:1: ruleJob returns [EObject current=null] : ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) ) otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= RULE_STRING ) ) ) ;
    public final EObject ruleJob() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token lv_layoutBeforeAnalysis_3_0=null;
        Token lv_measureExecutionTime_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token lv_output_12_0=null;
        EObject lv_resources_6_0 = null;

        EObject lv_layoutOptions_8_0 = null;

        EObject lv_analyses_10_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:140:28: ( ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) ) otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:141:1: ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) ) otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:141:1: ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) ) otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:141:2: () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) )? ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) ) otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= RULE_STRING ) )
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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:184:3: ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:185:1: (lv_measureExecutionTime_4_0= 'measureExecutionTime' )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:185:1: (lv_measureExecutionTime_4_0= 'measureExecutionTime' )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:186:3: lv_measureExecutionTime_4_0= 'measureExecutionTime'
                    {
                    lv_measureExecutionTime_4_0=(Token)match(input,15,FOLLOW_15_in_ruleJob334); 

                            newLeafNode(lv_measureExecutionTime_4_0, grammarAccess.getJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_4_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getJobRule());
                    	        }
                           		setWithLastConsumed(current, "measureExecutionTime", true, "measureExecutionTime");
                    	    

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,16,FOLLOW_16_in_ruleJob360); 

                	newLeafNode(otherlv_5, grammarAccess.getJobAccess().getResourcesKeyword_5());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:203:1: ( (lv_resources_6_0= ruleResource ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_STRING||LA7_0==20) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:204:1: (lv_resources_6_0= ruleResource )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:204:1: (lv_resources_6_0= ruleResource )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:205:3: lv_resources_6_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_6_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleResource_in_ruleJob381);
            	    lv_resources_6_0=ruleResource();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"resources",
            	            		lv_resources_6_0, 
            	            		"Resource");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            otherlv_7=(Token)match(input,17,FOLLOW_17_in_ruleJob394); 

                	newLeafNode(otherlv_7, grammarAccess.getJobAccess().getLayoutoptionsKeyword_7());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:225:1: ( (lv_layoutOptions_8_0= ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:226:1: (lv_layoutOptions_8_0= ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:226:1: (lv_layoutOptions_8_0= ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:227:3: lv_layoutOptions_8_0= ruleKIdentifier
            {
             
            	        newCompositeNode(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_8_0()); 
            	    
            pushFollow(FOLLOW_ruleKIdentifier_in_ruleJob415);
            lv_layoutOptions_8_0=ruleKIdentifier();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getJobRule());
            	        }
                   		add(
                   			current, 
                   			"layoutOptions",
                    		lv_layoutOptions_8_0, 
                    		"KIdentifier");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_9=(Token)match(input,18,FOLLOW_18_in_ruleJob427); 

                	newLeafNode(otherlv_9, grammarAccess.getJobAccess().getAnalysesKeyword_9());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:247:1: ( (lv_analyses_10_0= ruleAnalysis ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:248:1: (lv_analyses_10_0= ruleAnalysis )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:248:1: (lv_analyses_10_0= ruleAnalysis )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:249:3: lv_analyses_10_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_10_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnalysis_in_ruleJob448);
            	    lv_analyses_10_0=ruleAnalysis();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"analyses",
            	            		lv_analyses_10_0, 
            	            		"Analysis");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            otherlv_11=(Token)match(input,19,FOLLOW_19_in_ruleJob461); 

                	newLeafNode(otherlv_11, grammarAccess.getJobAccess().getOutputKeyword_11());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:269:1: ( (lv_output_12_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:270:1: (lv_output_12_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:270:1: (lv_output_12_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:271:3: lv_output_12_0= RULE_STRING
            {
            lv_output_12_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleJob478); 

            			newLeafNode(lv_output_12_0, grammarAccess.getJobAccess().getOutputSTRINGTerminalRuleCall_12_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getJobRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"output",
                    		lv_output_12_0, 
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:295:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:296:2: (iv_ruleResource= ruleResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:297:2: iv_ruleResource= ruleResource EOF
            {
             newCompositeNode(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_ruleResource_in_entryRuleResource519);
            iv_ruleResource=ruleResource();

            state._fsp--;

             current =iv_ruleResource; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResource529); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:304:1: ruleResource returns [EObject current=null] : (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        EObject this_ResourceReference_0 = null;

        EObject this_LocalResource_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:307:28: ( (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:308:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:308:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==20) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_STRING) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:309:5: this_ResourceReference_0= ruleResourceReference
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleResourceReference_in_ruleResource576);
                    this_ResourceReference_0=ruleResourceReference();

                    state._fsp--;

                     
                            current = this_ResourceReference_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:319:5: this_LocalResource_1= ruleLocalResource
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLocalResource_in_ruleResource603);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:335:1: entryRuleResourceReference returns [EObject current=null] : iv_ruleResourceReference= ruleResourceReference EOF ;
    public final EObject entryRuleResourceReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceReference = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:336:2: (iv_ruleResourceReference= ruleResourceReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:337:2: iv_ruleResourceReference= ruleResourceReference EOF
            {
             newCompositeNode(grammarAccess.getResourceReferenceRule()); 
            pushFollow(FOLLOW_ruleResourceReference_in_entryRuleResourceReference638);
            iv_ruleResourceReference=ruleResourceReference();

            state._fsp--;

             current =iv_ruleResourceReference; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResourceReference648); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:344:1: ruleResourceReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) ;
    public final EObject ruleResourceReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:347:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:348:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:348:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:348:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+
            {
            otherlv_0=(Token)match(input,20,FOLLOW_20_in_ruleResourceReference685); 

                	newLeafNode(otherlv_0, grammarAccess.getResourceReferenceAccess().getRefKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:352:1: ( (otherlv_1= RULE_ID ) )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_ID) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:353:1: (otherlv_1= RULE_ID )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:353:1: (otherlv_1= RULE_ID )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:354:3: otherlv_1= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getResourceReferenceRule());
            	    	        }
            	            
            	    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleResourceReference705); 

            	    		newLeafNode(otherlv_1, grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            	    	

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:373:1: entryRuleGlobalResourceRef returns [EObject current=null] : iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF ;
    public final EObject entryRuleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalResourceRef = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:374:2: (iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:375:2: iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF
            {
             newCompositeNode(grammarAccess.getGlobalResourceRefRule()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef742);
            iv_ruleGlobalResourceRef=ruleGlobalResourceRef();

            state._fsp--;

             current =iv_ruleGlobalResourceRef; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalResourceRef752); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:382:1: ruleGlobalResourceRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) ;
    public final EObject ruleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_resources_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:385:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:386:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:386:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:386:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:386:2: ( (lv_name_0_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:387:1: (lv_name_0_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:387:1: (lv_name_0_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:388:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGlobalResourceRef794); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:404:2: ( (lv_resources_1_0= ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:405:1: (lv_resources_1_0= ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:405:1: (lv_resources_1_0= ruleLocalResource )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:406:3: lv_resources_1_0= ruleLocalResource
            {
             
            	        newCompositeNode(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLocalResource_in_ruleGlobalResourceRef820);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:430:1: entryRuleLocalResource returns [EObject current=null] : iv_ruleLocalResource= ruleLocalResource EOF ;
    public final EObject entryRuleLocalResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalResource = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:431:2: (iv_ruleLocalResource= ruleLocalResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:432:2: iv_ruleLocalResource= ruleLocalResource EOF
            {
             newCompositeNode(grammarAccess.getLocalResourceRule()); 
            pushFollow(FOLLOW_ruleLocalResource_in_entryRuleLocalResource856);
            iv_ruleLocalResource=ruleLocalResource();

            state._fsp--;

             current =iv_ruleLocalResource; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalResource866); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:439:1: ruleLocalResource returns [EObject current=null] : ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleLocalResource() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;
        Token otherlv_1=null;
        Token lv_filter_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:442:28: ( ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:443:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:443:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:443:2: ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:443:2: ( (lv_path_0_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:444:1: (lv_path_0_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:444:1: (lv_path_0_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:445:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalResource908); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:461:2: (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:461:4: otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) )
            {
            otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleLocalResource926); 

                	newLeafNode(otherlv_1, grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:465:1: ( (lv_filter_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:466:1: (lv_filter_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:466:1: (lv_filter_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:467:3: lv_filter_2_0= RULE_STRING
            {
            lv_filter_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalResource943); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:491:1: entryRuleAnalysis returns [EObject current=null] : iv_ruleAnalysis= ruleAnalysis EOF ;
    public final EObject entryRuleAnalysis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysis = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:492:2: (iv_ruleAnalysis= ruleAnalysis EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:493:2: iv_ruleAnalysis= ruleAnalysis EOF
            {
             newCompositeNode(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_ruleAnalysis_in_entryRuleAnalysis985);
            iv_ruleAnalysis=ruleAnalysis();

            state._fsp--;

             current =iv_ruleAnalysis; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalysis995); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:500:1: ruleAnalysis returns [EObject current=null] : ( (lv_name_0_0= ruleQualifiedID ) ) ;
    public final EObject ruleAnalysis() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:503:28: ( ( (lv_name_0_0= ruleQualifiedID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:504:1: ( (lv_name_0_0= ruleQualifiedID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:504:1: ( (lv_name_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:505:1: (lv_name_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:505:1: (lv_name_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:506:3: lv_name_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleAnalysis1040);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:530:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:531:2: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:532:2: iv_ruleKIdentifier= ruleKIdentifier EOF
            {
             newCompositeNode(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier1075);
            iv_ruleKIdentifier=ruleKIdentifier();

            state._fsp--;

             current =iv_ruleKIdentifier; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKIdentifier1085); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:539:1: ruleKIdentifier returns [EObject current=null] : ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_persistentEntries_3_0 = null;

        EObject lv_persistentEntries_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:542:28: ( ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:543:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:543:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:543:2: () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:543:2: ()
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:544:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKIdentifierAccess().getKIdentifierAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:549:2: ( (lv_id_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:550:1: (lv_id_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:550:1: (lv_id_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:551:3: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleKIdentifier1136); 

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

            otherlv_2=(Token)match(input,22,FOLLOW_22_in_ruleKIdentifier1153); 

                	newLeafNode(otherlv_2, grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:571:1: ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:571:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:571:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:572:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:572:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:573:3: lv_persistentEntries_3_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulePersistentEntry_in_ruleKIdentifier1175);
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

                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:589:2: ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==RULE_ID) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:590:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:590:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:591:3: lv_persistentEntries_4_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePersistentEntry_in_ruleKIdentifier1196);
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
                    	    break loop11;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,23,FOLLOW_23_in_ruleKIdentifier1211); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:619:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:620:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:621:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry1247);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistentEntry1257); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:628:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:631:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:632:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:632:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:632:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:632:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:633:1: (lv_key_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:633:1: (lv_key_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:634:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_rulePersistentEntry1303);
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

            otherlv_1=(Token)match(input,24,FOLLOW_24_in_rulePersistentEntry1315); 

                	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getColonKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:654:1: ( (lv_value_2_0= rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:655:1: (lv_value_2_0= rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:655:1: (lv_value_2_0= rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:656:3: lv_value_2_0= rulePropertyValue
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_rulePropertyValue_in_rulePersistentEntry1336);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:680:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:681:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:682:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID1373);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID1384); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:689:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:692:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:693:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:693:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:693:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID1424); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:700:1: (kw= '.' this_ID_2= RULE_ID )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==25) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:701:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,25,FOLLOW_25_in_ruleQualifiedID1443); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID1458); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop13;
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:721:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:722:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:723:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
             newCompositeNode(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue1506);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;

             current =iv_rulePropertyValue.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue1517); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:730:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:733:28: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:734:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:734:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            int alt14=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt14=1;
                }
                break;
            case RULE_STRING:
                {
                alt14=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt14=3;
                }
                break;
            case RULE_ID:
                {
                alt14=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:734:6: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rulePropertyValue1557); 

                    		current.merge(this_BOOLEAN_0);
                        
                     
                        newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:742:10: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePropertyValue1583); 

                    		current.merge(this_STRING_1);
                        
                     
                        newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:751:5: this_Float_2= ruleFloat
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleFloat_in_rulePropertyValue1616);
                    this_Float_2=ruleFloat();

                    state._fsp--;


                    		current.merge(this_Float_2);
                        
                     
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:763:5: this_QualifiedID_3= ruleQualifiedID
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleQualifiedID_in_rulePropertyValue1649);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:781:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:782:2: (iv_ruleFloat= ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:783:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_ruleFloat_in_entryRuleFloat1695);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloat1706); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:790:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:793:28: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:794:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:794:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_TFLOAT) ) {
                alt15=1;
            }
            else if ( (LA15_0==RULE_NATURAL) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:794:6: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FOLLOW_RULE_TFLOAT_in_ruleFloat1746); 

                    		current.merge(this_TFLOAT_0);
                        
                     
                        newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:802:10: this_NATURAL_1= RULE_NATURAL
                    {
                    this_NATURAL_1=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleFloat1772); 

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
    public static final BitSet FOLLOW_13_in_ruleJob261 = new BitSet(new long[]{0x000000000001C010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJob278 = new BitSet(new long[]{0x000000000001C000L});
    public static final BitSet FOLLOW_14_in_ruleJob302 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_15_in_ruleJob334 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleJob360 = new BitSet(new long[]{0x0000000000120020L});
    public static final BitSet FOLLOW_ruleResource_in_ruleJob381 = new BitSet(new long[]{0x0000000000120020L});
    public static final BitSet FOLLOW_17_in_ruleJob394 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_ruleJob415 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleJob427 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_ruleAnalysis_in_ruleJob448 = new BitSet(new long[]{0x0000000000080010L});
    public static final BitSet FOLLOW_19_in_ruleJob461 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleJob478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_entryRuleResource519 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResource529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_ruleResource576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_ruleResource603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_entryRuleResourceReference638 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResourceReference648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleResourceReference685 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleResourceReference705 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef742 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalResourceRef752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGlobalResourceRef794 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_ruleLocalResource_in_ruleGlobalResourceRef820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_entryRuleLocalResource856 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalResource866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalResource908 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleLocalResource926 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalResource943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_entryRuleAnalysis985 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalysis995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleAnalysis1040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier1075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier1085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleKIdentifier1136 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleKIdentifier1153 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier1175 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier1196 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_23_in_ruleKIdentifier1211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry1247 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry1257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rulePersistentEntry1303 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_rulePersistentEntry1315 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rulePropertyValue_in_rulePersistentEntry1336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID1373 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID1424 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_ruleQualifiedID1443 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID1458 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue1506 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rulePropertyValue1557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePropertyValue1583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rulePropertyValue1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rulePropertyValue1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat1695 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloat1706 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TFLOAT_in_ruleFloat1746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleFloat1772 = new BitSet(new long[]{0x0000000000000002L});

}