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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_NATURAL", "RULE_STRING", "RULE_BOOLEAN", "RULE_TFLOAT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'globalResources'", "'globalOutputs'", "'execute'", "'all'", "'job'", "'layoutBeforeAnalysis'", "'measureExecutionTime'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'rangejob'", "'rangeoption'", "'rangeanalysis'", "'component'", "'floatvalues'", "','", "'intvalues'", "'intrange'", "'to'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'"
    };
    public static final int RULE_BOOLEAN=7;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_TFLOAT=8;
    public static final int RULE_ID=4;
    public static final int RULE_WS=11;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_NATURAL=5;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalGranaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalGranaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalGranaParser.tokenNames; }
    public String getGrammarFileName() { return "InternalGrana.g"; }



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
    // InternalGrana.g:67:1: entryRuleGrana returns [EObject current=null] : iv_ruleGrana= ruleGrana EOF ;
    public final EObject entryRuleGrana() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGrana = null;


        try {
            // InternalGrana.g:68:2: (iv_ruleGrana= ruleGrana EOF )
            // InternalGrana.g:69:2: iv_ruleGrana= ruleGrana EOF
            {
             newCompositeNode(grammarAccess.getGranaRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGrana=ruleGrana();

            state._fsp--;

             current =iv_ruleGrana; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:76:1: ruleGrana returns [EObject current=null] : ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+ ) ;
    public final EObject ruleGrana() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_executeAll_5_0=null;
        Token otherlv_6=null;
        EObject lv_globalResources_1_0 = null;

        EObject lv_gloobalOutputs_3_0 = null;

        EObject lv_jobs_7_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:79:28: ( ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+ ) )
            // InternalGrana.g:80:1: ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+ )
            {
            // InternalGrana.g:80:1: ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+ )
            // InternalGrana.g:80:2: (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+
            {
            // InternalGrana.g:80:2: (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==12) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalGrana.g:80:4: otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )*
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_3); 

                        	newLeafNode(otherlv_0, grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0());
                        
                    // InternalGrana.g:84:1: ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalGrana.g:85:1: (lv_globalResources_1_0= ruleGlobalResourceRef )
                    	    {
                    	    // InternalGrana.g:85:1: (lv_globalResources_1_0= ruleGlobalResourceRef )
                    	    // InternalGrana.g:86:3: lv_globalResources_1_0= ruleGlobalResourceRef
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGranaAccess().getGlobalResourcesGlobalResourceRefParserRuleCall_0_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_3);
                    	    lv_globalResources_1_0=ruleGlobalResourceRef();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getGranaRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"globalResources",
                    	            		lv_globalResources_1_0, 
                    	            		"de.cau.cs.kieler.kiml.grana.text.Grana.GlobalResourceRef");
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

            // InternalGrana.g:102:5: (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==13) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalGrana.g:102:7: otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )*
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_4); 

                        	newLeafNode(otherlv_2, grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0());
                        
                    // InternalGrana.g:106:1: ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==RULE_ID) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalGrana.g:107:1: (lv_gloobalOutputs_3_0= ruleGlobalOutputRef )
                    	    {
                    	    // InternalGrana.g:107:1: (lv_gloobalOutputs_3_0= ruleGlobalOutputRef )
                    	    // InternalGrana.g:108:3: lv_gloobalOutputs_3_0= ruleGlobalOutputRef
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGranaAccess().getGloobalOutputsGlobalOutputRefParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_4);
                    	    lv_gloobalOutputs_3_0=ruleGlobalOutputRef();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getGranaRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"gloobalOutputs",
                    	            		lv_gloobalOutputs_3_0, 
                    	            		"de.cau.cs.kieler.kiml.grana.text.Grana.GlobalOutputRef");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalGrana.g:124:5: (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) )
            // InternalGrana.g:124:7: otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ )
            {
            otherlv_4=(Token)match(input,14,FOLLOW_5); 

                	newLeafNode(otherlv_4, grammarAccess.getGranaAccess().getExecuteKeyword_2_0());
                
            // InternalGrana.g:128:1: ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_ID) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalGrana.g:128:2: ( (lv_executeAll_5_0= 'all' ) )
                    {
                    // InternalGrana.g:128:2: ( (lv_executeAll_5_0= 'all' ) )
                    // InternalGrana.g:129:1: (lv_executeAll_5_0= 'all' )
                    {
                    // InternalGrana.g:129:1: (lv_executeAll_5_0= 'all' )
                    // InternalGrana.g:130:3: lv_executeAll_5_0= 'all'
                    {
                    lv_executeAll_5_0=(Token)match(input,15,FOLLOW_6); 

                            newLeafNode(lv_executeAll_5_0, grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getGranaRule());
                    	        }
                           		setWithLastConsumed(current, "executeAll", true, "all");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:144:6: ( (otherlv_6= RULE_ID ) )+
                    {
                    // InternalGrana.g:144:6: ( (otherlv_6= RULE_ID ) )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==RULE_ID) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalGrana.g:145:1: (otherlv_6= RULE_ID )
                    	    {
                    	    // InternalGrana.g:145:1: (otherlv_6= RULE_ID )
                    	    // InternalGrana.g:146:3: otherlv_6= RULE_ID
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGranaRule());
                    	    	        }
                    	            
                    	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_7); 

                    	    		newLeafNode(otherlv_6, grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_1_1_0()); 
                    	    	

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);


                    }
                    break;

            }


            }

            // InternalGrana.g:157:5: ( (lv_jobs_7_0= ruleJob ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16||LA7_0==23) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalGrana.g:158:1: (lv_jobs_7_0= ruleJob )
            	    {
            	    // InternalGrana.g:158:1: (lv_jobs_7_0= ruleJob )
            	    // InternalGrana.g:159:3: lv_jobs_7_0= ruleJob
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_8);
            	    lv_jobs_7_0=ruleJob();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getGranaRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"jobs",
            	            		lv_jobs_7_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Job");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
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
    // InternalGrana.g:183:1: entryRuleJob returns [EObject current=null] : iv_ruleJob= ruleJob EOF ;
    public final EObject entryRuleJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJob = null;


        try {
            // InternalGrana.g:184:2: (iv_ruleJob= ruleJob EOF )
            // InternalGrana.g:185:2: iv_ruleJob= ruleJob EOF
            {
             newCompositeNode(grammarAccess.getJobRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleJob=ruleJob();

            state._fsp--;

             current =iv_ruleJob; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:192:1: ruleJob returns [EObject current=null] : (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob ) ;
    public final EObject ruleJob() throws RecognitionException {
        EObject current = null;

        EObject this_RegularJob_0 = null;

        EObject this_RangeJob_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:195:28: ( (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob ) )
            // InternalGrana.g:196:1: (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob )
            {
            // InternalGrana.g:196:1: (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==16) ) {
                alt8=1;
            }
            else if ( (LA8_0==23) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalGrana.g:197:5: this_RegularJob_0= ruleRegularJob
                    {
                     
                            newCompositeNode(grammarAccess.getJobAccess().getRegularJobParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_RegularJob_0=ruleRegularJob();

                    state._fsp--;

                     
                            current = this_RegularJob_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:207:5: this_RangeJob_1= ruleRangeJob
                    {
                     
                            newCompositeNode(grammarAccess.getJobAccess().getRangeJobParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_2);
                    this_RangeJob_1=ruleRangeJob();

                    state._fsp--;

                     
                            current = this_RangeJob_1; 
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
    // $ANTLR end "ruleJob"


    // $ANTLR start "entryRuleRegularJob"
    // InternalGrana.g:223:1: entryRuleRegularJob returns [EObject current=null] : iv_ruleRegularJob= ruleRegularJob EOF ;
    public final EObject entryRuleRegularJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRegularJob = null;


        try {
            // InternalGrana.g:224:2: (iv_ruleRegularJob= ruleRegularJob EOF )
            // InternalGrana.g:225:2: iv_ruleRegularJob= ruleRegularJob EOF
            {
             newCompositeNode(grammarAccess.getRegularJobRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRegularJob=ruleRegularJob();

            state._fsp--;

             current =iv_ruleRegularJob; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRegularJob"


    // $ANTLR start "ruleRegularJob"
    // InternalGrana.g:232:1: ruleRegularJob returns [EObject current=null] : (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) ) ) ;
    public final EObject ruleRegularJob() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_layoutBeforeAnalysis_2_0=null;
        Token lv_measureExecutionTime_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_resources_5_0 = null;

        EObject lv_layoutOptions_7_0 = null;

        EObject lv_analyses_9_0 = null;

        EObject lv_output_11_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:235:28: ( (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) ) ) )
            // InternalGrana.g:236:1: (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) ) )
            {
            // InternalGrana.g:236:1: (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) ) )
            // InternalGrana.g:236:3: otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_9); 

                	newLeafNode(otherlv_0, grammarAccess.getRegularJobAccess().getJobKeyword_0());
                
            // InternalGrana.g:240:1: ( (lv_name_1_0= RULE_ID ) )
            // InternalGrana.g:241:1: (lv_name_1_0= RULE_ID )
            {
            // InternalGrana.g:241:1: (lv_name_1_0= RULE_ID )
            // InternalGrana.g:242:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            			newLeafNode(lv_name_1_0, grammarAccess.getRegularJobAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRegularJobRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.ID");
            	    

            }


            }

            // InternalGrana.g:258:2: ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==17) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalGrana.g:259:1: (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' )
                    {
                    // InternalGrana.g:259:1: (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' )
                    // InternalGrana.g:260:3: lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis'
                    {
                    lv_layoutBeforeAnalysis_2_0=(Token)match(input,17,FOLLOW_11); 

                            newLeafNode(lv_layoutBeforeAnalysis_2_0, grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRegularJobRule());
                    	        }
                           		setWithLastConsumed(current, "layoutBeforeAnalysis", true, "layoutBeforeAnalysis");
                    	    

                    }


                    }
                    break;

            }

            // InternalGrana.g:273:3: ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==18) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalGrana.g:274:1: (lv_measureExecutionTime_3_0= 'measureExecutionTime' )
                    {
                    // InternalGrana.g:274:1: (lv_measureExecutionTime_3_0= 'measureExecutionTime' )
                    // InternalGrana.g:275:3: lv_measureExecutionTime_3_0= 'measureExecutionTime'
                    {
                    lv_measureExecutionTime_3_0=(Token)match(input,18,FOLLOW_12); 

                            newLeafNode(lv_measureExecutionTime_3_0, grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRegularJobRule());
                    	        }
                           		setWithLastConsumed(current, "measureExecutionTime", true, "measureExecutionTime");
                    	    

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,19,FOLLOW_13); 

                	newLeafNode(otherlv_4, grammarAccess.getRegularJobAccess().getResourcesKeyword_4());
                
            // InternalGrana.g:292:1: ( (lv_resources_5_0= ruleResource ) )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_STRING||LA11_0==32) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalGrana.g:293:1: (lv_resources_5_0= ruleResource )
            	    {
            	    // InternalGrana.g:293:1: (lv_resources_5_0= ruleResource )
            	    // InternalGrana.g:294:3: lv_resources_5_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getResourcesResourceParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_14);
            	    lv_resources_5_0=ruleResource();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"resources",
            	            		lv_resources_5_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Resource");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);

            otherlv_6=(Token)match(input,20,FOLLOW_9); 

                	newLeafNode(otherlv_6, grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6());
                
            // InternalGrana.g:314:1: ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_ID) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalGrana.g:315:1: (lv_layoutOptions_7_0= ruleKIdentifier )
            	    {
            	    // InternalGrana.g:315:1: (lv_layoutOptions_7_0= ruleKIdentifier )
            	    // InternalGrana.g:316:3: lv_layoutOptions_7_0= ruleKIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_layoutOptions_7_0=ruleKIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"layoutOptions",
            	            		lv_layoutOptions_7_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.KIdentifier");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            otherlv_8=(Token)match(input,21,FOLLOW_9); 

                	newLeafNode(otherlv_8, grammarAccess.getRegularJobAccess().getAnalysesKeyword_8());
                
            // InternalGrana.g:336:1: ( (lv_analyses_9_0= ruleAnalysis ) )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_ID) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalGrana.g:337:1: (lv_analyses_9_0= ruleAnalysis )
            	    {
            	    // InternalGrana.g:337:1: (lv_analyses_9_0= ruleAnalysis )
            	    // InternalGrana.g:338:3: lv_analyses_9_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 
            	    	    
            	    pushFollow(FOLLOW_16);
            	    lv_analyses_9_0=ruleAnalysis();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"analyses",
            	            		lv_analyses_9_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Analysis");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);

            otherlv_10=(Token)match(input,22,FOLLOW_13); 

                	newLeafNode(otherlv_10, grammarAccess.getRegularJobAccess().getOutputKeyword_10());
                
            // InternalGrana.g:358:1: ( (lv_output_11_0= ruleOutput ) )
            // InternalGrana.g:359:1: (lv_output_11_0= ruleOutput )
            {
            // InternalGrana.g:359:1: (lv_output_11_0= ruleOutput )
            // InternalGrana.g:360:3: lv_output_11_0= ruleOutput
            {
             
            	        newCompositeNode(grammarAccess.getRegularJobAccess().getOutputOutputParserRuleCall_11_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_output_11_0=ruleOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_11_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.Output");
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
    // $ANTLR end "ruleRegularJob"


    // $ANTLR start "entryRuleRangeJob"
    // InternalGrana.g:384:1: entryRuleRangeJob returns [EObject current=null] : iv_ruleRangeJob= ruleRangeJob EOF ;
    public final EObject entryRuleRangeJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRangeJob = null;


        try {
            // InternalGrana.g:385:2: (iv_ruleRangeJob= ruleRangeJob EOF )
            // InternalGrana.g:386:2: iv_ruleRangeJob= ruleRangeJob EOF
            {
             newCompositeNode(grammarAccess.getRangeJobRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRangeJob=ruleRangeJob();

            state._fsp--;

             current =iv_ruleRangeJob; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRangeJob"


    // $ANTLR start "ruleRangeJob"
    // InternalGrana.g:393:1: ruleRangeJob returns [EObject current=null] : (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) ) ) ;
    public final EObject ruleRangeJob() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token lv_rangeAnalysisComponent_14_0=null;
        Token otherlv_15=null;
        EObject lv_resources_3_0 = null;

        EObject lv_layoutOptions_5_0 = null;

        EObject lv_analyses_7_0 = null;

        AntlrDatatypeRuleToken lv_rangeOption_9_0 = null;

        EObject lv_rangeValues_10_0 = null;

        EObject lv_rangeAnalysis_12_0 = null;

        EObject lv_output_16_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:396:28: ( (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) ) ) )
            // InternalGrana.g:397:1: (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) ) )
            {
            // InternalGrana.g:397:1: (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) ) )
            // InternalGrana.g:397:3: otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_9); 

                	newLeafNode(otherlv_0, grammarAccess.getRangeJobAccess().getRangejobKeyword_0());
                
            // InternalGrana.g:401:1: ( (lv_name_1_0= RULE_ID ) )
            // InternalGrana.g:402:1: (lv_name_1_0= RULE_ID )
            {
            // InternalGrana.g:402:1: (lv_name_1_0= RULE_ID )
            // InternalGrana.g:403:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_12); 

            			newLeafNode(lv_name_1_0, grammarAccess.getRangeJobAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRangeJobRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,19,FOLLOW_13); 

                	newLeafNode(otherlv_2, grammarAccess.getRangeJobAccess().getResourcesKeyword_2());
                
            // InternalGrana.g:423:1: ( (lv_resources_3_0= ruleResource ) )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_STRING||LA14_0==32) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalGrana.g:424:1: (lv_resources_3_0= ruleResource )
            	    {
            	    // InternalGrana.g:424:1: (lv_resources_3_0= ruleResource )
            	    // InternalGrana.g:425:3: lv_resources_3_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getResourcesResourceParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_14);
            	    lv_resources_3_0=ruleResource();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"resources",
            	            		lv_resources_3_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Resource");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);

            otherlv_4=(Token)match(input,20,FOLLOW_9); 

                	newLeafNode(otherlv_4, grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_4());
                
            // InternalGrana.g:445:1: ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_ID) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalGrana.g:446:1: (lv_layoutOptions_5_0= ruleKIdentifier )
            	    {
            	    // InternalGrana.g:446:1: (lv_layoutOptions_5_0= ruleKIdentifier )
            	    // InternalGrana.g:447:3: lv_layoutOptions_5_0= ruleKIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getLayoutOptionsKIdentifierParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_layoutOptions_5_0=ruleKIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"layoutOptions",
            	            		lv_layoutOptions_5_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.KIdentifier");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);

            otherlv_6=(Token)match(input,21,FOLLOW_9); 

                	newLeafNode(otherlv_6, grammarAccess.getRangeJobAccess().getAnalysesKeyword_6());
                
            // InternalGrana.g:467:1: ( (lv_analyses_7_0= ruleAnalysis ) )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_ID) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalGrana.g:468:1: (lv_analyses_7_0= ruleAnalysis )
            	    {
            	    // InternalGrana.g:468:1: (lv_analyses_7_0= ruleAnalysis )
            	    // InternalGrana.g:469:3: lv_analyses_7_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getAnalysesAnalysisParserRuleCall_7_0()); 
            	    	    
            	    pushFollow(FOLLOW_17);
            	    lv_analyses_7_0=ruleAnalysis();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"analyses",
            	            		lv_analyses_7_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Analysis");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);

            otherlv_8=(Token)match(input,24,FOLLOW_9); 

                	newLeafNode(otherlv_8, grammarAccess.getRangeJobAccess().getRangeoptionKeyword_8());
                
            // InternalGrana.g:489:1: ( (lv_rangeOption_9_0= ruleQualifiedID ) )
            // InternalGrana.g:490:1: (lv_rangeOption_9_0= ruleQualifiedID )
            {
            // InternalGrana.g:490:1: (lv_rangeOption_9_0= ruleQualifiedID )
            // InternalGrana.g:491:3: lv_rangeOption_9_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIDParserRuleCall_9_0()); 
            	    
            pushFollow(FOLLOW_18);
            lv_rangeOption_9_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeOption",
                    		lv_rangeOption_9_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.QualifiedID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:507:2: ( (lv_rangeValues_10_0= ruleRange ) )
            // InternalGrana.g:508:1: (lv_rangeValues_10_0= ruleRange )
            {
            // InternalGrana.g:508:1: (lv_rangeValues_10_0= ruleRange )
            // InternalGrana.g:509:3: lv_rangeValues_10_0= ruleRange
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeValuesRangeParserRuleCall_10_0()); 
            	    
            pushFollow(FOLLOW_19);
            lv_rangeValues_10_0=ruleRange();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeValues",
                    		lv_rangeValues_10_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.Range");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_11=(Token)match(input,25,FOLLOW_9); 

                	newLeafNode(otherlv_11, grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_11());
                
            // InternalGrana.g:529:1: ( (lv_rangeAnalysis_12_0= ruleAnalysis ) )
            // InternalGrana.g:530:1: (lv_rangeAnalysis_12_0= ruleAnalysis )
            {
            // InternalGrana.g:530:1: (lv_rangeAnalysis_12_0= ruleAnalysis )
            // InternalGrana.g:531:3: lv_rangeAnalysis_12_0= ruleAnalysis
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeAnalysisAnalysisParserRuleCall_12_0()); 
            	    
            pushFollow(FOLLOW_20);
            lv_rangeAnalysis_12_0=ruleAnalysis();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeAnalysis",
                    		lv_rangeAnalysis_12_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.Analysis");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:547:2: (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==26) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalGrana.g:547:4: otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) )
                    {
                    otherlv_13=(Token)match(input,26,FOLLOW_21); 

                        	newLeafNode(otherlv_13, grammarAccess.getRangeJobAccess().getComponentKeyword_13_0());
                        
                    // InternalGrana.g:551:1: ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) )
                    // InternalGrana.g:552:1: (lv_rangeAnalysisComponent_14_0= RULE_NATURAL )
                    {
                    // InternalGrana.g:552:1: (lv_rangeAnalysisComponent_14_0= RULE_NATURAL )
                    // InternalGrana.g:553:3: lv_rangeAnalysisComponent_14_0= RULE_NATURAL
                    {
                    lv_rangeAnalysisComponent_14_0=(Token)match(input,RULE_NATURAL,FOLLOW_22); 

                    			newLeafNode(lv_rangeAnalysisComponent_14_0, grammarAccess.getRangeJobAccess().getRangeAnalysisComponentNATURALTerminalRuleCall_13_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeJobRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"rangeAnalysisComponent",
                            		lv_rangeAnalysisComponent_14_0, 
                            		"de.cau.cs.kieler.kiml.grana.text.Grana.NATURAL");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_15=(Token)match(input,22,FOLLOW_13); 

                	newLeafNode(otherlv_15, grammarAccess.getRangeJobAccess().getOutputKeyword_14());
                
            // InternalGrana.g:573:1: ( (lv_output_16_0= ruleOutput ) )
            // InternalGrana.g:574:1: (lv_output_16_0= ruleOutput )
            {
            // InternalGrana.g:574:1: (lv_output_16_0= ruleOutput )
            // InternalGrana.g:575:3: lv_output_16_0= ruleOutput
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getOutputOutputParserRuleCall_15_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_output_16_0=ruleOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_16_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.Output");
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
    // $ANTLR end "ruleRangeJob"


    // $ANTLR start "entryRuleRange"
    // InternalGrana.g:599:1: entryRuleRange returns [EObject current=null] : iv_ruleRange= ruleRange EOF ;
    public final EObject entryRuleRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRange = null;


        try {
            // InternalGrana.g:600:2: (iv_ruleRange= ruleRange EOF )
            // InternalGrana.g:601:2: iv_ruleRange= ruleRange EOF
            {
             newCompositeNode(grammarAccess.getRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRange=ruleRange();

            state._fsp--;

             current =iv_ruleRange; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleRange"


    // $ANTLR start "ruleRange"
    // InternalGrana.g:608:1: ruleRange returns [EObject current=null] : (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange ) ;
    public final EObject ruleRange() throws RecognitionException {
        EObject current = null;

        EObject this_FloatRange_0 = null;

        EObject this_IntRange_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:611:28: ( (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange ) )
            // InternalGrana.g:612:1: (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange )
            {
            // InternalGrana.g:612:1: (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==27) ) {
                alt18=1;
            }
            else if ( ((LA18_0>=29 && LA18_0<=30)) ) {
                alt18=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalGrana.g:613:5: this_FloatRange_0= ruleFloatRange
                    {
                     
                            newCompositeNode(grammarAccess.getRangeAccess().getFloatRangeParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_FloatRange_0=ruleFloatRange();

                    state._fsp--;

                     
                            current = this_FloatRange_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:623:5: this_IntRange_1= ruleIntRange
                    {
                     
                            newCompositeNode(grammarAccess.getRangeAccess().getIntRangeParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_2);
                    this_IntRange_1=ruleIntRange();

                    state._fsp--;

                     
                            current = this_IntRange_1; 
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
    // $ANTLR end "ruleRange"


    // $ANTLR start "entryRuleFloatRange"
    // InternalGrana.g:639:1: entryRuleFloatRange returns [EObject current=null] : iv_ruleFloatRange= ruleFloatRange EOF ;
    public final EObject entryRuleFloatRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatRange = null;


        try {
            // InternalGrana.g:640:2: (iv_ruleFloatRange= ruleFloatRange EOF )
            // InternalGrana.g:641:2: iv_ruleFloatRange= ruleFloatRange EOF
            {
             newCompositeNode(grammarAccess.getFloatRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFloatRange=ruleFloatRange();

            state._fsp--;

             current =iv_ruleFloatRange; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleFloatRange"


    // $ANTLR start "ruleFloatRange"
    // InternalGrana.g:648:1: ruleFloatRange returns [EObject current=null] : (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* ) ;
    public final EObject ruleFloatRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_values_1_0 = null;

        AntlrDatatypeRuleToken lv_values_3_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:651:28: ( (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* ) )
            // InternalGrana.g:652:1: (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* )
            {
            // InternalGrana.g:652:1: (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* )
            // InternalGrana.g:652:3: otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )*
            {
            otherlv_0=(Token)match(input,27,FOLLOW_23); 

                	newLeafNode(otherlv_0, grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0());
                
            // InternalGrana.g:656:1: ( (lv_values_1_0= ruleFloat ) )
            // InternalGrana.g:657:1: (lv_values_1_0= ruleFloat )
            {
            // InternalGrana.g:657:1: (lv_values_1_0= ruleFloat )
            // InternalGrana.g:658:3: lv_values_1_0= ruleFloat
            {
             
            	        newCompositeNode(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_24);
            lv_values_1_0=ruleFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFloatRangeRule());
            	        }
                   		add(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.Float");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:674:2: (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==28) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalGrana.g:674:4: otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) )
            	    {
            	    otherlv_2=(Token)match(input,28,FOLLOW_23); 

            	        	newLeafNode(otherlv_2, grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0());
            	        
            	    // InternalGrana.g:678:1: ( (lv_values_3_0= ruleFloat ) )
            	    // InternalGrana.g:679:1: (lv_values_3_0= ruleFloat )
            	    {
            	    // InternalGrana.g:679:1: (lv_values_3_0= ruleFloat )
            	    // InternalGrana.g:680:3: lv_values_3_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_24);
            	    lv_values_3_0=ruleFloat();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFloatRangeRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Float");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // $ANTLR end "ruleFloatRange"


    // $ANTLR start "entryRuleIntRange"
    // InternalGrana.g:704:1: entryRuleIntRange returns [EObject current=null] : iv_ruleIntRange= ruleIntRange EOF ;
    public final EObject entryRuleIntRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRange = null;


        try {
            // InternalGrana.g:705:2: (iv_ruleIntRange= ruleIntRange EOF )
            // InternalGrana.g:706:2: iv_ruleIntRange= ruleIntRange EOF
            {
             newCompositeNode(grammarAccess.getIntRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntRange=ruleIntRange();

            state._fsp--;

             current =iv_ruleIntRange; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleIntRange"


    // $ANTLR start "ruleIntRange"
    // InternalGrana.g:713:1: ruleIntRange returns [EObject current=null] : (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues ) ;
    public final EObject ruleIntRange() throws RecognitionException {
        EObject current = null;

        EObject this_IntRangeRange_0 = null;

        EObject this_IntRangeValues_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:716:28: ( (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues ) )
            // InternalGrana.g:717:1: (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues )
            {
            // InternalGrana.g:717:1: (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==30) ) {
                alt20=1;
            }
            else if ( (LA20_0==29) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // InternalGrana.g:718:5: this_IntRangeRange_0= ruleIntRangeRange
                    {
                     
                            newCompositeNode(grammarAccess.getIntRangeAccess().getIntRangeRangeParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_IntRangeRange_0=ruleIntRangeRange();

                    state._fsp--;

                     
                            current = this_IntRangeRange_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:728:5: this_IntRangeValues_1= ruleIntRangeValues
                    {
                     
                            newCompositeNode(grammarAccess.getIntRangeAccess().getIntRangeValuesParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_2);
                    this_IntRangeValues_1=ruleIntRangeValues();

                    state._fsp--;

                     
                            current = this_IntRangeValues_1; 
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
    // $ANTLR end "ruleIntRange"


    // $ANTLR start "entryRuleIntRangeValues"
    // InternalGrana.g:744:1: entryRuleIntRangeValues returns [EObject current=null] : iv_ruleIntRangeValues= ruleIntRangeValues EOF ;
    public final EObject entryRuleIntRangeValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRangeValues = null;


        try {
            // InternalGrana.g:745:2: (iv_ruleIntRangeValues= ruleIntRangeValues EOF )
            // InternalGrana.g:746:2: iv_ruleIntRangeValues= ruleIntRangeValues EOF
            {
             newCompositeNode(grammarAccess.getIntRangeValuesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntRangeValues=ruleIntRangeValues();

            state._fsp--;

             current =iv_ruleIntRangeValues; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleIntRangeValues"


    // $ANTLR start "ruleIntRangeValues"
    // InternalGrana.g:753:1: ruleIntRangeValues returns [EObject current=null] : (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* ) ;
    public final EObject ruleIntRangeValues() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:756:28: ( (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* ) )
            // InternalGrana.g:757:1: (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* )
            {
            // InternalGrana.g:757:1: (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* )
            // InternalGrana.g:757:3: otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )*
            {
            otherlv_0=(Token)match(input,29,FOLLOW_21); 

                	newLeafNode(otherlv_0, grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0());
                
            // InternalGrana.g:761:1: ( (lv_values_1_0= RULE_NATURAL ) )
            // InternalGrana.g:762:1: (lv_values_1_0= RULE_NATURAL )
            {
            // InternalGrana.g:762:1: (lv_values_1_0= RULE_NATURAL )
            // InternalGrana.g:763:3: lv_values_1_0= RULE_NATURAL
            {
            lv_values_1_0=(Token)match(input,RULE_NATURAL,FOLLOW_24); 

            			newLeafNode(lv_values_1_0, grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeValuesRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.NATURAL");
            	    

            }


            }

            // InternalGrana.g:779:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==28) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalGrana.g:779:4: otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) )
            	    {
            	    otherlv_2=(Token)match(input,28,FOLLOW_21); 

            	        	newLeafNode(otherlv_2, grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0());
            	        
            	    // InternalGrana.g:783:1: ( (lv_values_3_0= RULE_NATURAL ) )
            	    // InternalGrana.g:784:1: (lv_values_3_0= RULE_NATURAL )
            	    {
            	    // InternalGrana.g:784:1: (lv_values_3_0= RULE_NATURAL )
            	    // InternalGrana.g:785:3: lv_values_3_0= RULE_NATURAL
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_NATURAL,FOLLOW_24); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getIntRangeValuesRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.NATURAL");
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
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
    // $ANTLR end "ruleIntRangeValues"


    // $ANTLR start "entryRuleIntRangeRange"
    // InternalGrana.g:809:1: entryRuleIntRangeRange returns [EObject current=null] : iv_ruleIntRangeRange= ruleIntRangeRange EOF ;
    public final EObject entryRuleIntRangeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRangeRange = null;


        try {
            // InternalGrana.g:810:2: (iv_ruleIntRangeRange= ruleIntRangeRange EOF )
            // InternalGrana.g:811:2: iv_ruleIntRangeRange= ruleIntRangeRange EOF
            {
             newCompositeNode(grammarAccess.getIntRangeRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntRangeRange=ruleIntRangeRange();

            state._fsp--;

             current =iv_ruleIntRangeRange; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleIntRangeRange"


    // $ANTLR start "ruleIntRangeRange"
    // InternalGrana.g:818:1: ruleIntRangeRange returns [EObject current=null] : (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) ) ;
    public final EObject ruleIntRangeRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_start_1_0=null;
        Token otherlv_2=null;
        Token lv_end_3_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:821:28: ( (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) ) )
            // InternalGrana.g:822:1: (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) )
            {
            // InternalGrana.g:822:1: (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) )
            // InternalGrana.g:822:3: otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) )
            {
            otherlv_0=(Token)match(input,30,FOLLOW_21); 

                	newLeafNode(otherlv_0, grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0());
                
            // InternalGrana.g:826:1: ( (lv_start_1_0= RULE_NATURAL ) )
            // InternalGrana.g:827:1: (lv_start_1_0= RULE_NATURAL )
            {
            // InternalGrana.g:827:1: (lv_start_1_0= RULE_NATURAL )
            // InternalGrana.g:828:3: lv_start_1_0= RULE_NATURAL
            {
            lv_start_1_0=(Token)match(input,RULE_NATURAL,FOLLOW_25); 

            			newLeafNode(lv_start_1_0, grammarAccess.getIntRangeRangeAccess().getStartNATURALTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeRangeRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"start",
                    		lv_start_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.NATURAL");
            	    

            }


            }

            otherlv_2=(Token)match(input,31,FOLLOW_21); 

                	newLeafNode(otherlv_2, grammarAccess.getIntRangeRangeAccess().getToKeyword_2());
                
            // InternalGrana.g:848:1: ( (lv_end_3_0= RULE_NATURAL ) )
            // InternalGrana.g:849:1: (lv_end_3_0= RULE_NATURAL )
            {
            // InternalGrana.g:849:1: (lv_end_3_0= RULE_NATURAL )
            // InternalGrana.g:850:3: lv_end_3_0= RULE_NATURAL
            {
            lv_end_3_0=(Token)match(input,RULE_NATURAL,FOLLOW_2); 

            			newLeafNode(lv_end_3_0, grammarAccess.getIntRangeRangeAccess().getEndNATURALTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeRangeRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"end",
                    		lv_end_3_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.NATURAL");
            	    

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
    // $ANTLR end "ruleIntRangeRange"


    // $ANTLR start "entryRuleResource"
    // InternalGrana.g:874:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // InternalGrana.g:875:2: (iv_ruleResource= ruleResource EOF )
            // InternalGrana.g:876:2: iv_ruleResource= ruleResource EOF
            {
             newCompositeNode(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResource=ruleResource();

            state._fsp--;

             current =iv_ruleResource; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:883:1: ruleResource returns [EObject current=null] : (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        EObject this_ResourceReference_0 = null;

        EObject this_LocalResource_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:886:28: ( (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) )
            // InternalGrana.g:887:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            {
            // InternalGrana.g:887:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==32) ) {
                alt22=1;
            }
            else if ( (LA22_0==RULE_STRING) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalGrana.g:888:5: this_ResourceReference_0= ruleResourceReference
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_ResourceReference_0=ruleResourceReference();

                    state._fsp--;

                     
                            current = this_ResourceReference_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:898:5: this_LocalResource_1= ruleLocalResource
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:914:1: entryRuleResourceReference returns [EObject current=null] : iv_ruleResourceReference= ruleResourceReference EOF ;
    public final EObject entryRuleResourceReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceReference = null;


        try {
            // InternalGrana.g:915:2: (iv_ruleResourceReference= ruleResourceReference EOF )
            // InternalGrana.g:916:2: iv_ruleResourceReference= ruleResourceReference EOF
            {
             newCompositeNode(grammarAccess.getResourceReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResourceReference=ruleResourceReference();

            state._fsp--;

             current =iv_ruleResourceReference; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:923:1: ruleResourceReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) ;
    public final EObject ruleResourceReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:926:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) )
            // InternalGrana.g:927:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            {
            // InternalGrana.g:927:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            // InternalGrana.g:927:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+
            {
            otherlv_0=(Token)match(input,32,FOLLOW_9); 

                	newLeafNode(otherlv_0, grammarAccess.getResourceReferenceAccess().getRefKeyword_0());
                
            // InternalGrana.g:931:1: ( (otherlv_1= RULE_ID ) )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==RULE_ID) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalGrana.g:932:1: (otherlv_1= RULE_ID )
            	    {
            	    // InternalGrana.g:932:1: (otherlv_1= RULE_ID )
            	    // InternalGrana.g:933:3: otherlv_1= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getResourceReferenceRule());
            	    	        }
            	            
            	    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_26); 

            	    		newLeafNode(otherlv_1, grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            	    	

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
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
    // InternalGrana.g:952:1: entryRuleGlobalResourceRef returns [EObject current=null] : iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF ;
    public final EObject entryRuleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalResourceRef = null;


        try {
            // InternalGrana.g:953:2: (iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF )
            // InternalGrana.g:954:2: iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF
            {
             newCompositeNode(grammarAccess.getGlobalResourceRefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGlobalResourceRef=ruleGlobalResourceRef();

            state._fsp--;

             current =iv_ruleGlobalResourceRef; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:961:1: ruleGlobalResourceRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) ;
    public final EObject ruleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_resources_1_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:964:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) )
            // InternalGrana.g:965:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            {
            // InternalGrana.g:965:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            // InternalGrana.g:965:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) )
            {
            // InternalGrana.g:965:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalGrana.g:966:1: (lv_name_0_0= RULE_ID )
            {
            // InternalGrana.g:966:1: (lv_name_0_0= RULE_ID )
            // InternalGrana.g:967:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            			newLeafNode(lv_name_0_0, grammarAccess.getGlobalResourceRefAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getGlobalResourceRefRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.ID");
            	    

            }


            }

            // InternalGrana.g:983:2: ( (lv_resources_1_0= ruleLocalResource ) )
            // InternalGrana.g:984:1: (lv_resources_1_0= ruleLocalResource )
            {
            // InternalGrana.g:984:1: (lv_resources_1_0= ruleLocalResource )
            // InternalGrana.g:985:3: lv_resources_1_0= ruleLocalResource
            {
             
            	        newCompositeNode(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_resources_1_0=ruleLocalResource();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGlobalResourceRefRule());
            	        }
                   		add(
                   			current, 
                   			"resources",
                    		lv_resources_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.LocalResource");
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
    // InternalGrana.g:1009:1: entryRuleLocalResource returns [EObject current=null] : iv_ruleLocalResource= ruleLocalResource EOF ;
    public final EObject entryRuleLocalResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalResource = null;


        try {
            // InternalGrana.g:1010:2: (iv_ruleLocalResource= ruleLocalResource EOF )
            // InternalGrana.g:1011:2: iv_ruleLocalResource= ruleLocalResource EOF
            {
             newCompositeNode(grammarAccess.getLocalResourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLocalResource=ruleLocalResource();

            state._fsp--;

             current =iv_ruleLocalResource; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:1018:1: ruleLocalResource returns [EObject current=null] : ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleLocalResource() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;
        Token otherlv_1=null;
        Token lv_filter_2_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1021:28: ( ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) )
            // InternalGrana.g:1022:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            {
            // InternalGrana.g:1022:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            // InternalGrana.g:1022:2: ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            {
            // InternalGrana.g:1022:2: ( (lv_path_0_0= RULE_STRING ) )
            // InternalGrana.g:1023:1: (lv_path_0_0= RULE_STRING )
            {
            // InternalGrana.g:1023:1: (lv_path_0_0= RULE_STRING )
            // InternalGrana.g:1024:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_27); 

            			newLeafNode(lv_path_0_0, grammarAccess.getLocalResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalResourceRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"path",
                    		lv_path_0_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.STRING");
            	    

            }


            }

            // InternalGrana.g:1040:2: (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            // InternalGrana.g:1040:4: otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) )
            {
            otherlv_1=(Token)match(input,33,FOLLOW_28); 

                	newLeafNode(otherlv_1, grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0());
                
            // InternalGrana.g:1044:1: ( (lv_filter_2_0= RULE_STRING ) )
            // InternalGrana.g:1045:1: (lv_filter_2_0= RULE_STRING )
            {
            // InternalGrana.g:1045:1: (lv_filter_2_0= RULE_STRING )
            // InternalGrana.g:1046:3: lv_filter_2_0= RULE_STRING
            {
            lv_filter_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            			newLeafNode(lv_filter_2_0, grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalResourceRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"filter",
                    		lv_filter_2_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.STRING");
            	    

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


    // $ANTLR start "entryRuleOutput"
    // InternalGrana.g:1070:1: entryRuleOutput returns [EObject current=null] : iv_ruleOutput= ruleOutput EOF ;
    public final EObject entryRuleOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutput = null;


        try {
            // InternalGrana.g:1071:2: (iv_ruleOutput= ruleOutput EOF )
            // InternalGrana.g:1072:2: iv_ruleOutput= ruleOutput EOF
            {
             newCompositeNode(grammarAccess.getOutputRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOutput=ruleOutput();

            state._fsp--;

             current =iv_ruleOutput; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleOutput"


    // $ANTLR start "ruleOutput"
    // InternalGrana.g:1079:1: ruleOutput returns [EObject current=null] : (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) ;
    public final EObject ruleOutput() throws RecognitionException {
        EObject current = null;

        EObject this_OutputReference_0 = null;

        EObject this_LocalOutput_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1082:28: ( (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) )
            // InternalGrana.g:1083:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            {
            // InternalGrana.g:1083:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==32) ) {
                alt24=1;
            }
            else if ( (LA24_0==RULE_STRING) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // InternalGrana.g:1084:5: this_OutputReference_0= ruleOutputReference
                    {
                     
                            newCompositeNode(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_OutputReference_0=ruleOutputReference();

                    state._fsp--;

                     
                            current = this_OutputReference_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:1094:5: this_LocalOutput_1= ruleLocalOutput
                    {
                     
                            newCompositeNode(grammarAccess.getOutputAccess().getLocalOutputParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_2);
                    this_LocalOutput_1=ruleLocalOutput();

                    state._fsp--;

                     
                            current = this_LocalOutput_1; 
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
    // $ANTLR end "ruleOutput"


    // $ANTLR start "entryRuleGlobalOutputRef"
    // InternalGrana.g:1110:1: entryRuleGlobalOutputRef returns [EObject current=null] : iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF ;
    public final EObject entryRuleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalOutputRef = null;


        try {
            // InternalGrana.g:1111:2: (iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF )
            // InternalGrana.g:1112:2: iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF
            {
             newCompositeNode(grammarAccess.getGlobalOutputRefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGlobalOutputRef=ruleGlobalOutputRef();

            state._fsp--;

             current =iv_ruleGlobalOutputRef; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleGlobalOutputRef"


    // $ANTLR start "ruleGlobalOutputRef"
    // InternalGrana.g:1119:1: ruleGlobalOutputRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) ;
    public final EObject ruleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_output_1_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1122:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) )
            // InternalGrana.g:1123:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            {
            // InternalGrana.g:1123:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            // InternalGrana.g:1123:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) )
            {
            // InternalGrana.g:1123:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalGrana.g:1124:1: (lv_name_0_0= RULE_ID )
            {
            // InternalGrana.g:1124:1: (lv_name_0_0= RULE_ID )
            // InternalGrana.g:1125:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            			newLeafNode(lv_name_0_0, grammarAccess.getGlobalOutputRefAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getGlobalOutputRefRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.ID");
            	    

            }


            }

            // InternalGrana.g:1141:2: ( (lv_output_1_0= ruleLocalOutput ) )
            // InternalGrana.g:1142:1: (lv_output_1_0= ruleLocalOutput )
            {
            // InternalGrana.g:1142:1: (lv_output_1_0= ruleLocalOutput )
            // InternalGrana.g:1143:3: lv_output_1_0= ruleLocalOutput
            {
             
            	        newCompositeNode(grammarAccess.getGlobalOutputRefAccess().getOutputLocalOutputParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_output_1_0=ruleLocalOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGlobalOutputRefRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.LocalOutput");
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
    // $ANTLR end "ruleGlobalOutputRef"


    // $ANTLR start "entryRuleOutputReference"
    // InternalGrana.g:1167:1: entryRuleOutputReference returns [EObject current=null] : iv_ruleOutputReference= ruleOutputReference EOF ;
    public final EObject entryRuleOutputReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputReference = null;


        try {
            // InternalGrana.g:1168:2: (iv_ruleOutputReference= ruleOutputReference EOF )
            // InternalGrana.g:1169:2: iv_ruleOutputReference= ruleOutputReference EOF
            {
             newCompositeNode(grammarAccess.getOutputReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOutputReference=ruleOutputReference();

            state._fsp--;

             current =iv_ruleOutputReference; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleOutputReference"


    // $ANTLR start "ruleOutputReference"
    // InternalGrana.g:1176:1: ruleOutputReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleOutputReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1179:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) )
            // InternalGrana.g:1180:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalGrana.g:1180:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            // InternalGrana.g:1180:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,32,FOLLOW_9); 

                	newLeafNode(otherlv_0, grammarAccess.getOutputReferenceAccess().getRefKeyword_0());
                
            // InternalGrana.g:1184:1: ( (otherlv_1= RULE_ID ) )
            // InternalGrana.g:1185:1: (otherlv_1= RULE_ID )
            {
            // InternalGrana.g:1185:1: (otherlv_1= RULE_ID )
            // InternalGrana.g:1186:3: otherlv_1= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOutputReferenceRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_2); 

            		newLeafNode(otherlv_1, grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 
            	

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
    // $ANTLR end "ruleOutputReference"


    // $ANTLR start "entryRuleLocalOutput"
    // InternalGrana.g:1205:1: entryRuleLocalOutput returns [EObject current=null] : iv_ruleLocalOutput= ruleLocalOutput EOF ;
    public final EObject entryRuleLocalOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalOutput = null;


        try {
            // InternalGrana.g:1206:2: (iv_ruleLocalOutput= ruleLocalOutput EOF )
            // InternalGrana.g:1207:2: iv_ruleLocalOutput= ruleLocalOutput EOF
            {
             newCompositeNode(grammarAccess.getLocalOutputRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLocalOutput=ruleLocalOutput();

            state._fsp--;

             current =iv_ruleLocalOutput; 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleLocalOutput"


    // $ANTLR start "ruleLocalOutput"
    // InternalGrana.g:1214:1: ruleLocalOutput returns [EObject current=null] : ( (lv_path_0_0= RULE_STRING ) ) ;
    public final EObject ruleLocalOutput() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1217:28: ( ( (lv_path_0_0= RULE_STRING ) ) )
            // InternalGrana.g:1218:1: ( (lv_path_0_0= RULE_STRING ) )
            {
            // InternalGrana.g:1218:1: ( (lv_path_0_0= RULE_STRING ) )
            // InternalGrana.g:1219:1: (lv_path_0_0= RULE_STRING )
            {
            // InternalGrana.g:1219:1: (lv_path_0_0= RULE_STRING )
            // InternalGrana.g:1220:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            			newLeafNode(lv_path_0_0, grammarAccess.getLocalOutputAccess().getPathSTRINGTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalOutputRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"path",
                    		lv_path_0_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.STRING");
            	    

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
    // $ANTLR end "ruleLocalOutput"


    // $ANTLR start "entryRuleAnalysis"
    // InternalGrana.g:1244:1: entryRuleAnalysis returns [EObject current=null] : iv_ruleAnalysis= ruleAnalysis EOF ;
    public final EObject entryRuleAnalysis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysis = null;


        try {
            // InternalGrana.g:1245:2: (iv_ruleAnalysis= ruleAnalysis EOF )
            // InternalGrana.g:1246:2: iv_ruleAnalysis= ruleAnalysis EOF
            {
             newCompositeNode(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnalysis=ruleAnalysis();

            state._fsp--;

             current =iv_ruleAnalysis; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:1253:1: ruleAnalysis returns [EObject current=null] : ( (lv_name_0_0= ruleQualifiedID ) ) ;
    public final EObject ruleAnalysis() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1256:28: ( ( (lv_name_0_0= ruleQualifiedID ) ) )
            // InternalGrana.g:1257:1: ( (lv_name_0_0= ruleQualifiedID ) )
            {
            // InternalGrana.g:1257:1: ( (lv_name_0_0= ruleQualifiedID ) )
            // InternalGrana.g:1258:1: (lv_name_0_0= ruleQualifiedID )
            {
            // InternalGrana.g:1258:1: (lv_name_0_0= ruleQualifiedID )
            // InternalGrana.g:1259:3: lv_name_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_name_0_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAnalysisRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.QualifiedID");
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
    // InternalGrana.g:1283:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // InternalGrana.g:1284:2: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // InternalGrana.g:1285:2: iv_ruleKIdentifier= ruleKIdentifier EOF
            {
             newCompositeNode(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKIdentifier=ruleKIdentifier();

            state._fsp--;

             current =iv_ruleKIdentifier; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:1292:1: ruleKIdentifier returns [EObject current=null] : ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_persistentEntries_3_0 = null;

        EObject lv_persistentEntries_4_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1295:28: ( ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) )
            // InternalGrana.g:1296:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            {
            // InternalGrana.g:1296:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            // InternalGrana.g:1296:2: () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}'
            {
            // InternalGrana.g:1296:2: ()
            // InternalGrana.g:1297:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKIdentifierAccess().getKIdentifierAction_0(),
                        current);
                

            }

            // InternalGrana.g:1302:2: ( (lv_id_1_0= RULE_ID ) )
            // InternalGrana.g:1303:1: (lv_id_1_0= RULE_ID )
            {
            // InternalGrana.g:1303:1: (lv_id_1_0= RULE_ID )
            // InternalGrana.g:1304:3: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_29); 

            			newLeafNode(lv_id_1_0, grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKIdentifierRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"id",
                    		lv_id_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,34,FOLLOW_30); 

                	newLeafNode(otherlv_2, grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalGrana.g:1324:1: ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalGrana.g:1324:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    {
                    // InternalGrana.g:1324:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) )
                    // InternalGrana.g:1325:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    {
                    // InternalGrana.g:1325:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    // InternalGrana.g:1326:3: lv_persistentEntries_3_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_30);
                    lv_persistentEntries_3_0=rulePersistentEntry();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKIdentifierRule());
                    	        }
                           		add(
                           			current, 
                           			"persistentEntries",
                            		lv_persistentEntries_3_0, 
                            		"de.cau.cs.kieler.kiml.grana.text.Grana.PersistentEntry");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalGrana.g:1342:2: ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==RULE_ID) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // InternalGrana.g:1343:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    {
                    	    // InternalGrana.g:1343:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    // InternalGrana.g:1344:3: lv_persistentEntries_4_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_30);
                    	    lv_persistentEntries_4_0=rulePersistentEntry();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKIdentifierRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"persistentEntries",
                    	            		lv_persistentEntries_4_0, 
                    	            		"de.cau.cs.kieler.kiml.grana.text.Grana.PersistentEntry");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,35,FOLLOW_2); 

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
    // InternalGrana.g:1372:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // InternalGrana.g:1373:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // InternalGrana.g:1374:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:1381:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1384:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // InternalGrana.g:1385:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // InternalGrana.g:1385:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            // InternalGrana.g:1385:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // InternalGrana.g:1385:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // InternalGrana.g:1386:1: (lv_key_0_0= ruleQualifiedID )
            {
            // InternalGrana.g:1386:1: (lv_key_0_0= ruleQualifiedID )
            // InternalGrana.g:1387:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_31);
            lv_key_0_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
            	        }
                   		set(
                   			current, 
                   			"key",
                    		lv_key_0_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.QualifiedID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,36,FOLLOW_32); 

                	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getColonKeyword_1());
                
            // InternalGrana.g:1407:1: ( (lv_value_2_0= rulePropertyValue ) )
            // InternalGrana.g:1408:1: (lv_value_2_0= rulePropertyValue )
            {
            // InternalGrana.g:1408:1: (lv_value_2_0= rulePropertyValue )
            // InternalGrana.g:1409:3: lv_value_2_0= rulePropertyValue
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_value_2_0=rulePropertyValue();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPersistentEntryRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_2_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.PropertyValue");
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
    // InternalGrana.g:1433:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // InternalGrana.g:1434:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // InternalGrana.g:1435:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:1442:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1445:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalGrana.g:1446:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalGrana.g:1446:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalGrana.g:1446:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_33); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // InternalGrana.g:1453:1: (kw= '.' this_ID_2= RULE_ID )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==37) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalGrana.g:1454:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,37,FOLLOW_9); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_33); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop27;
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
    // InternalGrana.g:1474:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // InternalGrana.g:1475:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // InternalGrana.g:1476:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
             newCompositeNode(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;

             current =iv_rulePropertyValue.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:1483:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1486:28: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // InternalGrana.g:1487:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // InternalGrana.g:1487:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            int alt28=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt28=1;
                }
                break;
            case RULE_STRING:
                {
                alt28=2;
                }
                break;
            case RULE_NATURAL:
            case RULE_TFLOAT:
                {
                alt28=3;
                }
                break;
            case RULE_ID:
                {
                alt28=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }

            switch (alt28) {
                case 1 :
                    // InternalGrana.g:1487:6: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_2); 

                    		current.merge(this_BOOLEAN_0);
                        
                     
                        newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:1495:10: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    		current.merge(this_STRING_1);
                        
                     
                        newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // InternalGrana.g:1504:5: this_Float_2= ruleFloat
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_2);
                    this_Float_2=ruleFloat();

                    state._fsp--;


                    		current.merge(this_Float_2);
                        
                     
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // InternalGrana.g:1516:5: this_QualifiedID_3= ruleQualifiedID
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1534:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // InternalGrana.g:1535:2: (iv_ruleFloat= ruleFloat EOF )
            // InternalGrana.g:1536:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:1543:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1546:28: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // InternalGrana.g:1547:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // InternalGrana.g:1547:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==RULE_TFLOAT) ) {
                alt29=1;
            }
            else if ( (LA29_0==RULE_NATURAL) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // InternalGrana.g:1547:6: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FOLLOW_2); 

                    		current.merge(this_TFLOAT_0);
                        
                     
                        newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:1555:10: this_NATURAL_1= RULE_NATURAL
                    {
                    this_NATURAL_1=(Token)match(input,RULE_NATURAL,FOLLOW_2); 

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


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000006010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000810000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000810010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000810002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000000000E0000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000100000040L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000100100040L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000010L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000068000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000004400000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000002000000002L});

}