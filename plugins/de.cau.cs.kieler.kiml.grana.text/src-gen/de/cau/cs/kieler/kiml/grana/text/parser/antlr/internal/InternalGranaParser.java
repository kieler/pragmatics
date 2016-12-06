package de.cau.cs.kieler.kiml.grana.text.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_NATURAL", "RULE_STRING", "RULE_BOOLEAN", "RULE_TFLOAT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'globalResources'", "'globalOutputs'", "'execute'", "'parallel'", "'all'", "'job'", "'layoutBeforeAnalysis'", "'measureExecutionTime'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'comparejob'", "'rangejob'", "'rangeoption'", "'rangeanalysis'", "'component'", "'rangeanalyses'", "'floatvalues'", "','", "'intvalues'", "'intrange'", "'to'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'", "'csv'", "'json'"
    };
    public static final int RULE_BOOLEAN=7;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
    public static final int T__39=39;
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
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__20=20;
    public static final int T__42=42;
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
    // InternalGrana.g:68:1: entryRuleGrana returns [EObject current=null] : iv_ruleGrana= ruleGrana EOF ;
    public final EObject entryRuleGrana() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGrana = null;


        try {
            // InternalGrana.g:69:2: (iv_ruleGrana= ruleGrana EOF )
            // InternalGrana.g:70:2: iv_ruleGrana= ruleGrana EOF
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
    // InternalGrana.g:77:1: ruleGrana returns [EObject current=null] : ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( (lv_parallel_5_0= 'parallel' ) )? ( ( (lv_executeAll_6_0= 'all' ) ) | ( (otherlv_7= RULE_ID ) )+ ) ) ( (lv_jobs_8_0= ruleJob ) )+ ) ;
    public final EObject ruleGrana() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_parallel_5_0=null;
        Token lv_executeAll_6_0=null;
        Token otherlv_7=null;
        EObject lv_globalResources_1_0 = null;

        EObject lv_gloobalOutputs_3_0 = null;

        EObject lv_jobs_8_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:80:28: ( ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( (lv_parallel_5_0= 'parallel' ) )? ( ( (lv_executeAll_6_0= 'all' ) ) | ( (otherlv_7= RULE_ID ) )+ ) ) ( (lv_jobs_8_0= ruleJob ) )+ ) )
            // InternalGrana.g:81:1: ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( (lv_parallel_5_0= 'parallel' ) )? ( ( (lv_executeAll_6_0= 'all' ) ) | ( (otherlv_7= RULE_ID ) )+ ) ) ( (lv_jobs_8_0= ruleJob ) )+ )
            {
            // InternalGrana.g:81:1: ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( (lv_parallel_5_0= 'parallel' ) )? ( ( (lv_executeAll_6_0= 'all' ) ) | ( (otherlv_7= RULE_ID ) )+ ) ) ( (lv_jobs_8_0= ruleJob ) )+ )
            // InternalGrana.g:81:2: (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( (lv_parallel_5_0= 'parallel' ) )? ( ( (lv_executeAll_6_0= 'all' ) ) | ( (otherlv_7= RULE_ID ) )+ ) ) ( (lv_jobs_8_0= ruleJob ) )+
            {
            // InternalGrana.g:81:2: (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==12) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalGrana.g:81:4: otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )*
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_3); 

                        	newLeafNode(otherlv_0, grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0());
                        
                    // InternalGrana.g:85:1: ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalGrana.g:86:1: (lv_globalResources_1_0= ruleGlobalResourceRef )
                    	    {
                    	    // InternalGrana.g:86:1: (lv_globalResources_1_0= ruleGlobalResourceRef )
                    	    // InternalGrana.g:87:3: lv_globalResources_1_0= ruleGlobalResourceRef
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

            // InternalGrana.g:103:5: (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==13) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalGrana.g:103:7: otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )*
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_4); 

                        	newLeafNode(otherlv_2, grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0());
                        
                    // InternalGrana.g:107:1: ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==RULE_ID) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalGrana.g:108:1: (lv_gloobalOutputs_3_0= ruleGlobalOutputRef )
                    	    {
                    	    // InternalGrana.g:108:1: (lv_gloobalOutputs_3_0= ruleGlobalOutputRef )
                    	    // InternalGrana.g:109:3: lv_gloobalOutputs_3_0= ruleGlobalOutputRef
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

            // InternalGrana.g:125:5: (otherlv_4= 'execute' ( (lv_parallel_5_0= 'parallel' ) )? ( ( (lv_executeAll_6_0= 'all' ) ) | ( (otherlv_7= RULE_ID ) )+ ) )
            // InternalGrana.g:125:7: otherlv_4= 'execute' ( (lv_parallel_5_0= 'parallel' ) )? ( ( (lv_executeAll_6_0= 'all' ) ) | ( (otherlv_7= RULE_ID ) )+ )
            {
            otherlv_4=(Token)match(input,14,FOLLOW_5); 

                	newLeafNode(otherlv_4, grammarAccess.getGranaAccess().getExecuteKeyword_2_0());
                
            // InternalGrana.g:129:1: ( (lv_parallel_5_0= 'parallel' ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==15) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalGrana.g:130:1: (lv_parallel_5_0= 'parallel' )
                    {
                    // InternalGrana.g:130:1: (lv_parallel_5_0= 'parallel' )
                    // InternalGrana.g:131:3: lv_parallel_5_0= 'parallel'
                    {
                    lv_parallel_5_0=(Token)match(input,15,FOLLOW_6); 

                            newLeafNode(lv_parallel_5_0, grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getGranaRule());
                    	        }
                           		setWithLastConsumed(current, "parallel", true, "parallel");
                    	    

                    }


                    }
                    break;

            }

            // InternalGrana.g:144:3: ( ( (lv_executeAll_6_0= 'all' ) ) | ( (otherlv_7= RULE_ID ) )+ )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==16) ) {
                alt7=1;
            }
            else if ( (LA7_0==RULE_ID) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalGrana.g:144:4: ( (lv_executeAll_6_0= 'all' ) )
                    {
                    // InternalGrana.g:144:4: ( (lv_executeAll_6_0= 'all' ) )
                    // InternalGrana.g:145:1: (lv_executeAll_6_0= 'all' )
                    {
                    // InternalGrana.g:145:1: (lv_executeAll_6_0= 'all' )
                    // InternalGrana.g:146:3: lv_executeAll_6_0= 'all'
                    {
                    lv_executeAll_6_0=(Token)match(input,16,FOLLOW_7); 

                            newLeafNode(lv_executeAll_6_0, grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getGranaRule());
                    	        }
                           		setWithLastConsumed(current, "executeAll", true, "all");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:160:6: ( (otherlv_7= RULE_ID ) )+
                    {
                    // InternalGrana.g:160:6: ( (otherlv_7= RULE_ID ) )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==RULE_ID) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalGrana.g:161:1: (otherlv_7= RULE_ID )
                    	    {
                    	    // InternalGrana.g:161:1: (otherlv_7= RULE_ID )
                    	    // InternalGrana.g:162:3: otherlv_7= RULE_ID
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGranaRule());
                    	    	        }
                    	            
                    	    otherlv_7=(Token)match(input,RULE_ID,FOLLOW_8); 

                    	    		newLeafNode(otherlv_7, grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_2_1_0()); 
                    	    	

                    	    }


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


                    }
                    break;

            }


            }

            // InternalGrana.g:173:5: ( (lv_jobs_8_0= ruleJob ) )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==17||(LA8_0>=24 && LA8_0<=25)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalGrana.g:174:1: (lv_jobs_8_0= ruleJob )
            	    {
            	    // InternalGrana.g:174:1: (lv_jobs_8_0= ruleJob )
            	    // InternalGrana.g:175:3: lv_jobs_8_0= ruleJob
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_9);
            	    lv_jobs_8_0=ruleJob();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getGranaRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"jobs",
            	            		lv_jobs_8_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Job");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
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
    // InternalGrana.g:199:1: entryRuleJob returns [EObject current=null] : iv_ruleJob= ruleJob EOF ;
    public final EObject entryRuleJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJob = null;


        try {
            // InternalGrana.g:200:2: (iv_ruleJob= ruleJob EOF )
            // InternalGrana.g:201:2: iv_ruleJob= ruleJob EOF
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
    // InternalGrana.g:208:1: ruleJob returns [EObject current=null] : (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob | this_CompareJob_2= ruleCompareJob ) ;
    public final EObject ruleJob() throws RecognitionException {
        EObject current = null;

        EObject this_RegularJob_0 = null;

        EObject this_RangeJob_1 = null;

        EObject this_CompareJob_2 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:211:28: ( (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob | this_CompareJob_2= ruleCompareJob ) )
            // InternalGrana.g:212:1: (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob | this_CompareJob_2= ruleCompareJob )
            {
            // InternalGrana.g:212:1: (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob | this_CompareJob_2= ruleCompareJob )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt9=1;
                }
                break;
            case 25:
                {
                alt9=2;
                }
                break;
            case 24:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalGrana.g:213:5: this_RegularJob_0= ruleRegularJob
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
                    // InternalGrana.g:223:5: this_RangeJob_1= ruleRangeJob
                    {
                     
                            newCompositeNode(grammarAccess.getJobAccess().getRangeJobParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_2);
                    this_RangeJob_1=ruleRangeJob();

                    state._fsp--;

                     
                            current = this_RangeJob_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // InternalGrana.g:233:5: this_CompareJob_2= ruleCompareJob
                    {
                     
                            newCompositeNode(grammarAccess.getJobAccess().getCompareJobParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_2);
                    this_CompareJob_2=ruleCompareJob();

                    state._fsp--;

                     
                            current = this_CompareJob_2; 
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
    // InternalGrana.g:249:1: entryRuleRegularJob returns [EObject current=null] : iv_ruleRegularJob= ruleRegularJob EOF ;
    public final EObject entryRuleRegularJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRegularJob = null;


        try {
            // InternalGrana.g:250:2: (iv_ruleRegularJob= ruleRegularJob EOF )
            // InternalGrana.g:251:2: iv_ruleRegularJob= ruleRegularJob EOF
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
    // InternalGrana.g:258:1: ruleRegularJob returns [EObject current=null] : (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) ) ) ;
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

        Enumerator lv_outputType_11_0 = null;

        EObject lv_output_12_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:261:28: ( (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) ) ) )
            // InternalGrana.g:262:1: (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) ) )
            {
            // InternalGrana.g:262:1: (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) ) )
            // InternalGrana.g:262:3: otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getRegularJobAccess().getJobKeyword_0());
                
            // InternalGrana.g:266:1: ( (lv_name_1_0= RULE_ID ) )
            // InternalGrana.g:267:1: (lv_name_1_0= RULE_ID )
            {
            // InternalGrana.g:267:1: (lv_name_1_0= RULE_ID )
            // InternalGrana.g:268:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_11); 

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

            // InternalGrana.g:284:2: ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==18) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalGrana.g:285:1: (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' )
                    {
                    // InternalGrana.g:285:1: (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' )
                    // InternalGrana.g:286:3: lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis'
                    {
                    lv_layoutBeforeAnalysis_2_0=(Token)match(input,18,FOLLOW_12); 

                            newLeafNode(lv_layoutBeforeAnalysis_2_0, grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRegularJobRule());
                    	        }
                           		setWithLastConsumed(current, "layoutBeforeAnalysis", true, "layoutBeforeAnalysis");
                    	    

                    }


                    }
                    break;

            }

            // InternalGrana.g:299:3: ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==19) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalGrana.g:300:1: (lv_measureExecutionTime_3_0= 'measureExecutionTime' )
                    {
                    // InternalGrana.g:300:1: (lv_measureExecutionTime_3_0= 'measureExecutionTime' )
                    // InternalGrana.g:301:3: lv_measureExecutionTime_3_0= 'measureExecutionTime'
                    {
                    lv_measureExecutionTime_3_0=(Token)match(input,19,FOLLOW_13); 

                            newLeafNode(lv_measureExecutionTime_3_0, grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRegularJobRule());
                    	        }
                           		setWithLastConsumed(current, "measureExecutionTime", true, "measureExecutionTime");
                    	    

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,20,FOLLOW_14); 

                	newLeafNode(otherlv_4, grammarAccess.getRegularJobAccess().getResourcesKeyword_4());
                
            // InternalGrana.g:318:1: ( (lv_resources_5_0= ruleResource ) )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_STRING||LA12_0==35) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalGrana.g:319:1: (lv_resources_5_0= ruleResource )
            	    {
            	    // InternalGrana.g:319:1: (lv_resources_5_0= ruleResource )
            	    // InternalGrana.g:320:3: lv_resources_5_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getResourcesResourceParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
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
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            otherlv_6=(Token)match(input,21,FOLLOW_10); 

                	newLeafNode(otherlv_6, grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6());
                
            // InternalGrana.g:340:1: ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+
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
            	    // InternalGrana.g:341:1: (lv_layoutOptions_7_0= ruleKIdentifier )
            	    {
            	    // InternalGrana.g:341:1: (lv_layoutOptions_7_0= ruleKIdentifier )
            	    // InternalGrana.g:342:3: lv_layoutOptions_7_0= ruleKIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 
            	    	    
            	    pushFollow(FOLLOW_16);
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
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);

            otherlv_8=(Token)match(input,22,FOLLOW_10); 

                	newLeafNode(otherlv_8, grammarAccess.getRegularJobAccess().getAnalysesKeyword_8());
                
            // InternalGrana.g:362:1: ( (lv_analyses_9_0= ruleAnalysis ) )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalGrana.g:363:1: (lv_analyses_9_0= ruleAnalysis )
            	    {
            	    // InternalGrana.g:363:1: (lv_analyses_9_0= ruleAnalysis )
            	    // InternalGrana.g:364:3: lv_analyses_9_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 
            	    	    
            	    pushFollow(FOLLOW_17);
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
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);

            otherlv_10=(Token)match(input,23,FOLLOW_18); 

                	newLeafNode(otherlv_10, grammarAccess.getRegularJobAccess().getOutputKeyword_10());
                
            // InternalGrana.g:384:1: ( (lv_outputType_11_0= ruleOutputType ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=41 && LA15_0<=42)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalGrana.g:385:1: (lv_outputType_11_0= ruleOutputType )
                    {
                    // InternalGrana.g:385:1: (lv_outputType_11_0= ruleOutputType )
                    // InternalGrana.g:386:3: lv_outputType_11_0= ruleOutputType
                    {
                     
                    	        newCompositeNode(grammarAccess.getRegularJobAccess().getOutputTypeOutputTypeEnumRuleCall_11_0()); 
                    	    
                    pushFollow(FOLLOW_18);
                    lv_outputType_11_0=ruleOutputType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
                    	        }
                           		set(
                           			current, 
                           			"outputType",
                            		lv_outputType_11_0, 
                            		"de.cau.cs.kieler.kiml.grana.text.Grana.OutputType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalGrana.g:402:3: ( (lv_output_12_0= ruleOutput ) )
            // InternalGrana.g:403:1: (lv_output_12_0= ruleOutput )
            {
            // InternalGrana.g:403:1: (lv_output_12_0= ruleOutput )
            // InternalGrana.g:404:3: lv_output_12_0= ruleOutput
            {
             
            	        newCompositeNode(grammarAccess.getRegularJobAccess().getOutputOutputParserRuleCall_12_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_output_12_0=ruleOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_12_0, 
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


    // $ANTLR start "entryRuleCompareJob"
    // InternalGrana.g:428:1: entryRuleCompareJob returns [EObject current=null] : iv_ruleCompareJob= ruleCompareJob EOF ;
    public final EObject entryRuleCompareJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompareJob = null;


        try {
            // InternalGrana.g:429:2: (iv_ruleCompareJob= ruleCompareJob EOF )
            // InternalGrana.g:430:2: iv_ruleCompareJob= ruleCompareJob EOF
            {
             newCompositeNode(grammarAccess.getCompareJobRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCompareJob=ruleCompareJob();

            state._fsp--;

             current =iv_ruleCompareJob; 
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
    // $ANTLR end "entryRuleCompareJob"


    // $ANTLR start "ruleCompareJob"
    // InternalGrana.g:437:1: ruleCompareJob returns [EObject current=null] : (otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) ) ( (lv_layoutOptions_6_0= ruleKIdentifier ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) ) ) ;
    public final EObject ruleCompareJob() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_resources_3_0 = null;

        EObject lv_layoutOptions_5_0 = null;

        EObject lv_layoutOptions_6_0 = null;

        EObject lv_analyses_8_0 = null;

        Enumerator lv_outputType_10_0 = null;

        EObject lv_output_11_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:440:28: ( (otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) ) ( (lv_layoutOptions_6_0= ruleKIdentifier ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) ) ) )
            // InternalGrana.g:441:1: (otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) ) ( (lv_layoutOptions_6_0= ruleKIdentifier ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) ) )
            {
            // InternalGrana.g:441:1: (otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) ) ( (lv_layoutOptions_6_0= ruleKIdentifier ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) ) )
            // InternalGrana.g:441:3: otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) ) ( (lv_layoutOptions_6_0= ruleKIdentifier ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getCompareJobAccess().getComparejobKeyword_0());
                
            // InternalGrana.g:445:1: ( (lv_name_1_0= RULE_ID ) )
            // InternalGrana.g:446:1: (lv_name_1_0= RULE_ID )
            {
            // InternalGrana.g:446:1: (lv_name_1_0= RULE_ID )
            // InternalGrana.g:447:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            			newLeafNode(lv_name_1_0, grammarAccess.getCompareJobAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getCompareJobRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,20,FOLLOW_14); 

                	newLeafNode(otherlv_2, grammarAccess.getCompareJobAccess().getResourcesKeyword_2());
                
            // InternalGrana.g:467:1: ( (lv_resources_3_0= ruleResource ) )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_STRING||LA16_0==35) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalGrana.g:468:1: (lv_resources_3_0= ruleResource )
            	    {
            	    // InternalGrana.g:468:1: (lv_resources_3_0= ruleResource )
            	    // InternalGrana.g:469:3: lv_resources_3_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getCompareJobAccess().getResourcesResourceParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_resources_3_0=ruleResource();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getCompareJobRule());
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
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);

            otherlv_4=(Token)match(input,21,FOLLOW_10); 

                	newLeafNode(otherlv_4, grammarAccess.getCompareJobAccess().getLayoutoptionsKeyword_4());
                
            // InternalGrana.g:489:1: ( (lv_layoutOptions_5_0= ruleKIdentifier ) )
            // InternalGrana.g:490:1: (lv_layoutOptions_5_0= ruleKIdentifier )
            {
            // InternalGrana.g:490:1: (lv_layoutOptions_5_0= ruleKIdentifier )
            // InternalGrana.g:491:3: lv_layoutOptions_5_0= ruleKIdentifier
            {
             
            	        newCompositeNode(grammarAccess.getCompareJobAccess().getLayoutOptionsKIdentifierParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_10);
            lv_layoutOptions_5_0=ruleKIdentifier();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCompareJobRule());
            	        }
                   		add(
                   			current, 
                   			"layoutOptions",
                    		lv_layoutOptions_5_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.KIdentifier");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:507:2: ( (lv_layoutOptions_6_0= ruleKIdentifier ) )
            // InternalGrana.g:508:1: (lv_layoutOptions_6_0= ruleKIdentifier )
            {
            // InternalGrana.g:508:1: (lv_layoutOptions_6_0= ruleKIdentifier )
            // InternalGrana.g:509:3: lv_layoutOptions_6_0= ruleKIdentifier
            {
             
            	        newCompositeNode(grammarAccess.getCompareJobAccess().getLayoutOptionsKIdentifierParserRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_19);
            lv_layoutOptions_6_0=ruleKIdentifier();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCompareJobRule());
            	        }
                   		add(
                   			current, 
                   			"layoutOptions",
                    		lv_layoutOptions_6_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.KIdentifier");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,22,FOLLOW_10); 

                	newLeafNode(otherlv_7, grammarAccess.getCompareJobAccess().getAnalysesKeyword_7());
                
            // InternalGrana.g:529:1: ( (lv_analyses_8_0= ruleAnalysis ) )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_ID) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalGrana.g:530:1: (lv_analyses_8_0= ruleAnalysis )
            	    {
            	    // InternalGrana.g:530:1: (lv_analyses_8_0= ruleAnalysis )
            	    // InternalGrana.g:531:3: lv_analyses_8_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getCompareJobAccess().getAnalysesAnalysisParserRuleCall_8_0()); 
            	    	    
            	    pushFollow(FOLLOW_17);
            	    lv_analyses_8_0=ruleAnalysis();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getCompareJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"analyses",
            	            		lv_analyses_8_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Analysis");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);

            otherlv_9=(Token)match(input,23,FOLLOW_18); 

                	newLeafNode(otherlv_9, grammarAccess.getCompareJobAccess().getOutputKeyword_9());
                
            // InternalGrana.g:551:1: ( (lv_outputType_10_0= ruleOutputType ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=41 && LA18_0<=42)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalGrana.g:552:1: (lv_outputType_10_0= ruleOutputType )
                    {
                    // InternalGrana.g:552:1: (lv_outputType_10_0= ruleOutputType )
                    // InternalGrana.g:553:3: lv_outputType_10_0= ruleOutputType
                    {
                     
                    	        newCompositeNode(grammarAccess.getCompareJobAccess().getOutputTypeOutputTypeEnumRuleCall_10_0()); 
                    	    
                    pushFollow(FOLLOW_18);
                    lv_outputType_10_0=ruleOutputType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getCompareJobRule());
                    	        }
                           		set(
                           			current, 
                           			"outputType",
                            		lv_outputType_10_0, 
                            		"de.cau.cs.kieler.kiml.grana.text.Grana.OutputType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalGrana.g:569:3: ( (lv_output_11_0= ruleOutput ) )
            // InternalGrana.g:570:1: (lv_output_11_0= ruleOutput )
            {
            // InternalGrana.g:570:1: (lv_output_11_0= ruleOutput )
            // InternalGrana.g:571:3: lv_output_11_0= ruleOutput
            {
             
            	        newCompositeNode(grammarAccess.getCompareJobAccess().getOutputOutputParserRuleCall_11_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_output_11_0=ruleOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCompareJobRule());
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
    // $ANTLR end "ruleCompareJob"


    // $ANTLR start "entryRuleRangeJob"
    // InternalGrana.g:595:1: entryRuleRangeJob returns [EObject current=null] : iv_ruleRangeJob= ruleRangeJob EOF ;
    public final EObject entryRuleRangeJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRangeJob = null;


        try {
            // InternalGrana.g:596:2: (iv_ruleRangeJob= ruleRangeJob EOF )
            // InternalGrana.g:597:2: iv_ruleRangeJob= ruleRangeJob EOF
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
    // InternalGrana.g:604:1: ruleRangeJob returns [EObject current=null] : (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleKIdentifier ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedID ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) ) ) ;
    public final EObject ruleRangeJob() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_measureExecutionTime_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token lv_rangeAnalysisComponent_15_0=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        EObject lv_resources_4_0 = null;

        EObject lv_layoutOptions_6_0 = null;

        EObject lv_analyses_8_0 = null;

        AntlrDatatypeRuleToken lv_rangeOption_10_0 = null;

        EObject lv_rangeValues_11_0 = null;

        EObject lv_rangeAnalysis_13_0 = null;

        EObject lv_rangeAnalyses_17_0 = null;

        Enumerator lv_outputType_19_0 = null;

        EObject lv_output_20_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:607:28: ( (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleKIdentifier ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedID ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) ) ) )
            // InternalGrana.g:608:1: (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleKIdentifier ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedID ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) ) )
            {
            // InternalGrana.g:608:1: (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleKIdentifier ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedID ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) ) )
            // InternalGrana.g:608:3: otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleKIdentifier ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedID ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,25,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getRangeJobAccess().getRangejobKeyword_0());
                
            // InternalGrana.g:612:1: ( (lv_name_1_0= RULE_ID ) )
            // InternalGrana.g:613:1: (lv_name_1_0= RULE_ID )
            {
            // InternalGrana.g:613:1: (lv_name_1_0= RULE_ID )
            // InternalGrana.g:614:3: lv_name_1_0= RULE_ID
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

            // InternalGrana.g:630:2: ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==19) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalGrana.g:631:1: (lv_measureExecutionTime_2_0= 'measureExecutionTime' )
                    {
                    // InternalGrana.g:631:1: (lv_measureExecutionTime_2_0= 'measureExecutionTime' )
                    // InternalGrana.g:632:3: lv_measureExecutionTime_2_0= 'measureExecutionTime'
                    {
                    lv_measureExecutionTime_2_0=(Token)match(input,19,FOLLOW_13); 

                            newLeafNode(lv_measureExecutionTime_2_0, grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeJobRule());
                    	        }
                           		setWithLastConsumed(current, "measureExecutionTime", true, "measureExecutionTime");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,20,FOLLOW_14); 

                	newLeafNode(otherlv_3, grammarAccess.getRangeJobAccess().getResourcesKeyword_3());
                
            // InternalGrana.g:649:1: ( (lv_resources_4_0= ruleResource ) )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_STRING||LA20_0==35) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalGrana.g:650:1: (lv_resources_4_0= ruleResource )
            	    {
            	    // InternalGrana.g:650:1: (lv_resources_4_0= ruleResource )
            	    // InternalGrana.g:651:3: lv_resources_4_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getResourcesResourceParserRuleCall_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_resources_4_0=ruleResource();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"resources",
            	            		lv_resources_4_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Resource");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            otherlv_5=(Token)match(input,21,FOLLOW_10); 

                	newLeafNode(otherlv_5, grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_5());
                
            // InternalGrana.g:671:1: ( (lv_layoutOptions_6_0= ruleKIdentifier ) )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalGrana.g:672:1: (lv_layoutOptions_6_0= ruleKIdentifier )
            	    {
            	    // InternalGrana.g:672:1: (lv_layoutOptions_6_0= ruleKIdentifier )
            	    // InternalGrana.g:673:3: lv_layoutOptions_6_0= ruleKIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getLayoutOptionsKIdentifierParserRuleCall_6_0()); 
            	    	    
            	    pushFollow(FOLLOW_16);
            	    lv_layoutOptions_6_0=ruleKIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"layoutOptions",
            	            		lv_layoutOptions_6_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.KIdentifier");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);

            otherlv_7=(Token)match(input,22,FOLLOW_10); 

                	newLeafNode(otherlv_7, grammarAccess.getRangeJobAccess().getAnalysesKeyword_7());
                
            // InternalGrana.g:693:1: ( (lv_analyses_8_0= ruleAnalysis ) )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalGrana.g:694:1: (lv_analyses_8_0= ruleAnalysis )
            	    {
            	    // InternalGrana.g:694:1: (lv_analyses_8_0= ruleAnalysis )
            	    // InternalGrana.g:695:3: lv_analyses_8_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getAnalysesAnalysisParserRuleCall_8_0()); 
            	    	    
            	    pushFollow(FOLLOW_20);
            	    lv_analyses_8_0=ruleAnalysis();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"analyses",
            	            		lv_analyses_8_0, 
            	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Analysis");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);

            otherlv_9=(Token)match(input,26,FOLLOW_10); 

                	newLeafNode(otherlv_9, grammarAccess.getRangeJobAccess().getRangeoptionKeyword_9());
                
            // InternalGrana.g:715:1: ( (lv_rangeOption_10_0= ruleQualifiedID ) )
            // InternalGrana.g:716:1: (lv_rangeOption_10_0= ruleQualifiedID )
            {
            // InternalGrana.g:716:1: (lv_rangeOption_10_0= ruleQualifiedID )
            // InternalGrana.g:717:3: lv_rangeOption_10_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIDParserRuleCall_10_0()); 
            	    
            pushFollow(FOLLOW_21);
            lv_rangeOption_10_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeOption",
                    		lv_rangeOption_10_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.QualifiedID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:733:2: ( (lv_rangeValues_11_0= ruleRange ) )
            // InternalGrana.g:734:1: (lv_rangeValues_11_0= ruleRange )
            {
            // InternalGrana.g:734:1: (lv_rangeValues_11_0= ruleRange )
            // InternalGrana.g:735:3: lv_rangeValues_11_0= ruleRange
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeValuesRangeParserRuleCall_11_0()); 
            	    
            pushFollow(FOLLOW_22);
            lv_rangeValues_11_0=ruleRange();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeValues",
                    		lv_rangeValues_11_0, 
                    		"de.cau.cs.kieler.kiml.grana.text.Grana.Range");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:751:2: ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==27) ) {
                alt25=1;
            }
            else if ( (LA25_0==29) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalGrana.g:751:3: (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )? )
                    {
                    // InternalGrana.g:751:3: (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )? )
                    // InternalGrana.g:751:5: otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )?
                    {
                    otherlv_12=(Token)match(input,27,FOLLOW_10); 

                        	newLeafNode(otherlv_12, grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_12_0_0());
                        
                    // InternalGrana.g:755:1: ( (lv_rangeAnalysis_13_0= ruleAnalysis ) )
                    // InternalGrana.g:756:1: (lv_rangeAnalysis_13_0= ruleAnalysis )
                    {
                    // InternalGrana.g:756:1: (lv_rangeAnalysis_13_0= ruleAnalysis )
                    // InternalGrana.g:757:3: lv_rangeAnalysis_13_0= ruleAnalysis
                    {
                     
                    	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeAnalysisAnalysisParserRuleCall_12_0_1_0()); 
                    	    
                    pushFollow(FOLLOW_23);
                    lv_rangeAnalysis_13_0=ruleAnalysis();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
                    	        }
                           		set(
                           			current, 
                           			"rangeAnalysis",
                            		lv_rangeAnalysis_13_0, 
                            		"de.cau.cs.kieler.kiml.grana.text.Grana.Analysis");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalGrana.g:773:2: (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) ) )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==28) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // InternalGrana.g:773:4: otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) )
                            {
                            otherlv_14=(Token)match(input,28,FOLLOW_24); 

                                	newLeafNode(otherlv_14, grammarAccess.getRangeJobAccess().getComponentKeyword_12_0_2_0());
                                
                            // InternalGrana.g:777:1: ( (lv_rangeAnalysisComponent_15_0= RULE_NATURAL ) )
                            // InternalGrana.g:778:1: (lv_rangeAnalysisComponent_15_0= RULE_NATURAL )
                            {
                            // InternalGrana.g:778:1: (lv_rangeAnalysisComponent_15_0= RULE_NATURAL )
                            // InternalGrana.g:779:3: lv_rangeAnalysisComponent_15_0= RULE_NATURAL
                            {
                            lv_rangeAnalysisComponent_15_0=(Token)match(input,RULE_NATURAL,FOLLOW_25); 

                            			newLeafNode(lv_rangeAnalysisComponent_15_0, grammarAccess.getRangeJobAccess().getRangeAnalysisComponentNATURALTerminalRuleCall_12_0_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getRangeJobRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"rangeAnalysisComponent",
                                    		lv_rangeAnalysisComponent_15_0, 
                                    		"de.cau.cs.kieler.kiml.grana.text.Grana.NATURAL");
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:796:6: (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ )
                    {
                    // InternalGrana.g:796:6: (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ )
                    // InternalGrana.g:796:8: otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+
                    {
                    otherlv_16=(Token)match(input,29,FOLLOW_10); 

                        	newLeafNode(otherlv_16, grammarAccess.getRangeJobAccess().getRangeanalysesKeyword_12_1_0());
                        
                    // InternalGrana.g:800:1: ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+
                    int cnt24=0;
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==RULE_ID) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // InternalGrana.g:801:1: (lv_rangeAnalyses_17_0= ruleAnalysis )
                    	    {
                    	    // InternalGrana.g:801:1: (lv_rangeAnalyses_17_0= ruleAnalysis )
                    	    // InternalGrana.g:802:3: lv_rangeAnalyses_17_0= ruleAnalysis
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeAnalysesAnalysisParserRuleCall_12_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_17);
                    	    lv_rangeAnalyses_17_0=ruleAnalysis();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"rangeAnalyses",
                    	            		lv_rangeAnalyses_17_0, 
                    	            		"de.cau.cs.kieler.kiml.grana.text.Grana.Analysis");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt24 >= 1 ) break loop24;
                                EarlyExitException eee =
                                    new EarlyExitException(24, input);
                                throw eee;
                        }
                        cnt24++;
                    } while (true);


                    }


                    }
                    break;

            }

            otherlv_18=(Token)match(input,23,FOLLOW_18); 

                	newLeafNode(otherlv_18, grammarAccess.getRangeJobAccess().getOutputKeyword_13());
                
            // InternalGrana.g:822:1: ( (lv_outputType_19_0= ruleOutputType ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=41 && LA26_0<=42)) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalGrana.g:823:1: (lv_outputType_19_0= ruleOutputType )
                    {
                    // InternalGrana.g:823:1: (lv_outputType_19_0= ruleOutputType )
                    // InternalGrana.g:824:3: lv_outputType_19_0= ruleOutputType
                    {
                     
                    	        newCompositeNode(grammarAccess.getRangeJobAccess().getOutputTypeOutputTypeEnumRuleCall_14_0()); 
                    	    
                    pushFollow(FOLLOW_18);
                    lv_outputType_19_0=ruleOutputType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
                    	        }
                           		set(
                           			current, 
                           			"outputType",
                            		lv_outputType_19_0, 
                            		"de.cau.cs.kieler.kiml.grana.text.Grana.OutputType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            // InternalGrana.g:840:3: ( (lv_output_20_0= ruleOutput ) )
            // InternalGrana.g:841:1: (lv_output_20_0= ruleOutput )
            {
            // InternalGrana.g:841:1: (lv_output_20_0= ruleOutput )
            // InternalGrana.g:842:3: lv_output_20_0= ruleOutput
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getOutputOutputParserRuleCall_15_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_output_20_0=ruleOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_20_0, 
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
    // InternalGrana.g:866:1: entryRuleRange returns [EObject current=null] : iv_ruleRange= ruleRange EOF ;
    public final EObject entryRuleRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRange = null;


        try {
            // InternalGrana.g:867:2: (iv_ruleRange= ruleRange EOF )
            // InternalGrana.g:868:2: iv_ruleRange= ruleRange EOF
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
    // InternalGrana.g:875:1: ruleRange returns [EObject current=null] : (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange ) ;
    public final EObject ruleRange() throws RecognitionException {
        EObject current = null;

        EObject this_FloatRange_0 = null;

        EObject this_IntRange_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:878:28: ( (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange ) )
            // InternalGrana.g:879:1: (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange )
            {
            // InternalGrana.g:879:1: (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==30) ) {
                alt27=1;
            }
            else if ( ((LA27_0>=32 && LA27_0<=33)) ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // InternalGrana.g:880:5: this_FloatRange_0= ruleFloatRange
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
                    // InternalGrana.g:890:5: this_IntRange_1= ruleIntRange
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
    // InternalGrana.g:906:1: entryRuleFloatRange returns [EObject current=null] : iv_ruleFloatRange= ruleFloatRange EOF ;
    public final EObject entryRuleFloatRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatRange = null;


        try {
            // InternalGrana.g:907:2: (iv_ruleFloatRange= ruleFloatRange EOF )
            // InternalGrana.g:908:2: iv_ruleFloatRange= ruleFloatRange EOF
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
    // InternalGrana.g:915:1: ruleFloatRange returns [EObject current=null] : (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* ) ;
    public final EObject ruleFloatRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_values_1_0 = null;

        AntlrDatatypeRuleToken lv_values_3_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:918:28: ( (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* ) )
            // InternalGrana.g:919:1: (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* )
            {
            // InternalGrana.g:919:1: (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* )
            // InternalGrana.g:919:3: otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )*
            {
            otherlv_0=(Token)match(input,30,FOLLOW_26); 

                	newLeafNode(otherlv_0, grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0());
                
            // InternalGrana.g:923:1: ( (lv_values_1_0= ruleFloat ) )
            // InternalGrana.g:924:1: (lv_values_1_0= ruleFloat )
            {
            // InternalGrana.g:924:1: (lv_values_1_0= ruleFloat )
            // InternalGrana.g:925:3: lv_values_1_0= ruleFloat
            {
             
            	        newCompositeNode(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_27);
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

            // InternalGrana.g:941:2: (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==31) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalGrana.g:941:4: otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) )
            	    {
            	    otherlv_2=(Token)match(input,31,FOLLOW_26); 

            	        	newLeafNode(otherlv_2, grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0());
            	        
            	    // InternalGrana.g:945:1: ( (lv_values_3_0= ruleFloat ) )
            	    // InternalGrana.g:946:1: (lv_values_3_0= ruleFloat )
            	    {
            	    // InternalGrana.g:946:1: (lv_values_3_0= ruleFloat )
            	    // InternalGrana.g:947:3: lv_values_3_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_27);
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
            	    break loop28;
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
    // InternalGrana.g:971:1: entryRuleIntRange returns [EObject current=null] : iv_ruleIntRange= ruleIntRange EOF ;
    public final EObject entryRuleIntRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRange = null;


        try {
            // InternalGrana.g:972:2: (iv_ruleIntRange= ruleIntRange EOF )
            // InternalGrana.g:973:2: iv_ruleIntRange= ruleIntRange EOF
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
    // InternalGrana.g:980:1: ruleIntRange returns [EObject current=null] : (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues ) ;
    public final EObject ruleIntRange() throws RecognitionException {
        EObject current = null;

        EObject this_IntRangeRange_0 = null;

        EObject this_IntRangeValues_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:983:28: ( (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues ) )
            // InternalGrana.g:984:1: (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues )
            {
            // InternalGrana.g:984:1: (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==33) ) {
                alt29=1;
            }
            else if ( (LA29_0==32) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // InternalGrana.g:985:5: this_IntRangeRange_0= ruleIntRangeRange
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
                    // InternalGrana.g:995:5: this_IntRangeValues_1= ruleIntRangeValues
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
    // InternalGrana.g:1011:1: entryRuleIntRangeValues returns [EObject current=null] : iv_ruleIntRangeValues= ruleIntRangeValues EOF ;
    public final EObject entryRuleIntRangeValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRangeValues = null;


        try {
            // InternalGrana.g:1012:2: (iv_ruleIntRangeValues= ruleIntRangeValues EOF )
            // InternalGrana.g:1013:2: iv_ruleIntRangeValues= ruleIntRangeValues EOF
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
    // InternalGrana.g:1020:1: ruleIntRangeValues returns [EObject current=null] : (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* ) ;
    public final EObject ruleIntRangeValues() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1023:28: ( (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* ) )
            // InternalGrana.g:1024:1: (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* )
            {
            // InternalGrana.g:1024:1: (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* )
            // InternalGrana.g:1024:3: otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )*
            {
            otherlv_0=(Token)match(input,32,FOLLOW_24); 

                	newLeafNode(otherlv_0, grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0());
                
            // InternalGrana.g:1028:1: ( (lv_values_1_0= RULE_NATURAL ) )
            // InternalGrana.g:1029:1: (lv_values_1_0= RULE_NATURAL )
            {
            // InternalGrana.g:1029:1: (lv_values_1_0= RULE_NATURAL )
            // InternalGrana.g:1030:3: lv_values_1_0= RULE_NATURAL
            {
            lv_values_1_0=(Token)match(input,RULE_NATURAL,FOLLOW_27); 

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

            // InternalGrana.g:1046:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==31) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalGrana.g:1046:4: otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) )
            	    {
            	    otherlv_2=(Token)match(input,31,FOLLOW_24); 

            	        	newLeafNode(otherlv_2, grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0());
            	        
            	    // InternalGrana.g:1050:1: ( (lv_values_3_0= RULE_NATURAL ) )
            	    // InternalGrana.g:1051:1: (lv_values_3_0= RULE_NATURAL )
            	    {
            	    // InternalGrana.g:1051:1: (lv_values_3_0= RULE_NATURAL )
            	    // InternalGrana.g:1052:3: lv_values_3_0= RULE_NATURAL
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_NATURAL,FOLLOW_27); 

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
            	    break loop30;
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
    // InternalGrana.g:1076:1: entryRuleIntRangeRange returns [EObject current=null] : iv_ruleIntRangeRange= ruleIntRangeRange EOF ;
    public final EObject entryRuleIntRangeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRangeRange = null;


        try {
            // InternalGrana.g:1077:2: (iv_ruleIntRangeRange= ruleIntRangeRange EOF )
            // InternalGrana.g:1078:2: iv_ruleIntRangeRange= ruleIntRangeRange EOF
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
    // InternalGrana.g:1085:1: ruleIntRangeRange returns [EObject current=null] : (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) ) ;
    public final EObject ruleIntRangeRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_start_1_0=null;
        Token otherlv_2=null;
        Token lv_end_3_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1088:28: ( (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) ) )
            // InternalGrana.g:1089:1: (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) )
            {
            // InternalGrana.g:1089:1: (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) )
            // InternalGrana.g:1089:3: otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) )
            {
            otherlv_0=(Token)match(input,33,FOLLOW_24); 

                	newLeafNode(otherlv_0, grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0());
                
            // InternalGrana.g:1093:1: ( (lv_start_1_0= RULE_NATURAL ) )
            // InternalGrana.g:1094:1: (lv_start_1_0= RULE_NATURAL )
            {
            // InternalGrana.g:1094:1: (lv_start_1_0= RULE_NATURAL )
            // InternalGrana.g:1095:3: lv_start_1_0= RULE_NATURAL
            {
            lv_start_1_0=(Token)match(input,RULE_NATURAL,FOLLOW_28); 

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

            otherlv_2=(Token)match(input,34,FOLLOW_24); 

                	newLeafNode(otherlv_2, grammarAccess.getIntRangeRangeAccess().getToKeyword_2());
                
            // InternalGrana.g:1115:1: ( (lv_end_3_0= RULE_NATURAL ) )
            // InternalGrana.g:1116:1: (lv_end_3_0= RULE_NATURAL )
            {
            // InternalGrana.g:1116:1: (lv_end_3_0= RULE_NATURAL )
            // InternalGrana.g:1117:3: lv_end_3_0= RULE_NATURAL
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
    // InternalGrana.g:1141:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // InternalGrana.g:1142:2: (iv_ruleResource= ruleResource EOF )
            // InternalGrana.g:1143:2: iv_ruleResource= ruleResource EOF
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
    // InternalGrana.g:1150:1: ruleResource returns [EObject current=null] : (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        EObject this_ResourceReference_0 = null;

        EObject this_LocalResource_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1153:28: ( (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) )
            // InternalGrana.g:1154:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            {
            // InternalGrana.g:1154:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==35) ) {
                alt31=1;
            }
            else if ( (LA31_0==RULE_STRING) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // InternalGrana.g:1155:5: this_ResourceReference_0= ruleResourceReference
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
                    // InternalGrana.g:1165:5: this_LocalResource_1= ruleLocalResource
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
    // InternalGrana.g:1181:1: entryRuleResourceReference returns [EObject current=null] : iv_ruleResourceReference= ruleResourceReference EOF ;
    public final EObject entryRuleResourceReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceReference = null;


        try {
            // InternalGrana.g:1182:2: (iv_ruleResourceReference= ruleResourceReference EOF )
            // InternalGrana.g:1183:2: iv_ruleResourceReference= ruleResourceReference EOF
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
    // InternalGrana.g:1190:1: ruleResourceReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) ;
    public final EObject ruleResourceReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1193:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) )
            // InternalGrana.g:1194:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            {
            // InternalGrana.g:1194:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            // InternalGrana.g:1194:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+
            {
            otherlv_0=(Token)match(input,35,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getResourceReferenceAccess().getRefKeyword_0());
                
            // InternalGrana.g:1198:1: ( (otherlv_1= RULE_ID ) )+
            int cnt32=0;
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==RULE_ID) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalGrana.g:1199:1: (otherlv_1= RULE_ID )
            	    {
            	    // InternalGrana.g:1199:1: (otherlv_1= RULE_ID )
            	    // InternalGrana.g:1200:3: otherlv_1= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getResourceReferenceRule());
            	    	        }
            	            
            	    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_29); 

            	    		newLeafNode(otherlv_1, grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            	    	

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt32 >= 1 ) break loop32;
                        EarlyExitException eee =
                            new EarlyExitException(32, input);
                        throw eee;
                }
                cnt32++;
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
    // InternalGrana.g:1219:1: entryRuleGlobalResourceRef returns [EObject current=null] : iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF ;
    public final EObject entryRuleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalResourceRef = null;


        try {
            // InternalGrana.g:1220:2: (iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF )
            // InternalGrana.g:1221:2: iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF
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
    // InternalGrana.g:1228:1: ruleGlobalResourceRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) ;
    public final EObject ruleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_resources_1_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1231:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) )
            // InternalGrana.g:1232:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            {
            // InternalGrana.g:1232:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            // InternalGrana.g:1232:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) )
            {
            // InternalGrana.g:1232:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalGrana.g:1233:1: (lv_name_0_0= RULE_ID )
            {
            // InternalGrana.g:1233:1: (lv_name_0_0= RULE_ID )
            // InternalGrana.g:1234:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_14); 

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

            // InternalGrana.g:1250:2: ( (lv_resources_1_0= ruleLocalResource ) )
            // InternalGrana.g:1251:1: (lv_resources_1_0= ruleLocalResource )
            {
            // InternalGrana.g:1251:1: (lv_resources_1_0= ruleLocalResource )
            // InternalGrana.g:1252:3: lv_resources_1_0= ruleLocalResource
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
    // InternalGrana.g:1276:1: entryRuleLocalResource returns [EObject current=null] : iv_ruleLocalResource= ruleLocalResource EOF ;
    public final EObject entryRuleLocalResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalResource = null;


        try {
            // InternalGrana.g:1277:2: (iv_ruleLocalResource= ruleLocalResource EOF )
            // InternalGrana.g:1278:2: iv_ruleLocalResource= ruleLocalResource EOF
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
    // InternalGrana.g:1285:1: ruleLocalResource returns [EObject current=null] : ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleLocalResource() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;
        Token otherlv_1=null;
        Token lv_filter_2_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1288:28: ( ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) )
            // InternalGrana.g:1289:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            {
            // InternalGrana.g:1289:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            // InternalGrana.g:1289:2: ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            {
            // InternalGrana.g:1289:2: ( (lv_path_0_0= RULE_STRING ) )
            // InternalGrana.g:1290:1: (lv_path_0_0= RULE_STRING )
            {
            // InternalGrana.g:1290:1: (lv_path_0_0= RULE_STRING )
            // InternalGrana.g:1291:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

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

            // InternalGrana.g:1307:2: (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            // InternalGrana.g:1307:4: otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) )
            {
            otherlv_1=(Token)match(input,36,FOLLOW_31); 

                	newLeafNode(otherlv_1, grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0());
                
            // InternalGrana.g:1311:1: ( (lv_filter_2_0= RULE_STRING ) )
            // InternalGrana.g:1312:1: (lv_filter_2_0= RULE_STRING )
            {
            // InternalGrana.g:1312:1: (lv_filter_2_0= RULE_STRING )
            // InternalGrana.g:1313:3: lv_filter_2_0= RULE_STRING
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
    // InternalGrana.g:1337:1: entryRuleOutput returns [EObject current=null] : iv_ruleOutput= ruleOutput EOF ;
    public final EObject entryRuleOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutput = null;


        try {
            // InternalGrana.g:1338:2: (iv_ruleOutput= ruleOutput EOF )
            // InternalGrana.g:1339:2: iv_ruleOutput= ruleOutput EOF
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
    // InternalGrana.g:1346:1: ruleOutput returns [EObject current=null] : (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) ;
    public final EObject ruleOutput() throws RecognitionException {
        EObject current = null;

        EObject this_OutputReference_0 = null;

        EObject this_LocalOutput_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1349:28: ( (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) )
            // InternalGrana.g:1350:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            {
            // InternalGrana.g:1350:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==35) ) {
                alt33=1;
            }
            else if ( (LA33_0==RULE_STRING) ) {
                alt33=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // InternalGrana.g:1351:5: this_OutputReference_0= ruleOutputReference
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
                    // InternalGrana.g:1361:5: this_LocalOutput_1= ruleLocalOutput
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
    // InternalGrana.g:1377:1: entryRuleGlobalOutputRef returns [EObject current=null] : iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF ;
    public final EObject entryRuleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalOutputRef = null;


        try {
            // InternalGrana.g:1378:2: (iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF )
            // InternalGrana.g:1379:2: iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF
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
    // InternalGrana.g:1386:1: ruleGlobalOutputRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) ;
    public final EObject ruleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_output_1_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1389:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) )
            // InternalGrana.g:1390:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            {
            // InternalGrana.g:1390:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            // InternalGrana.g:1390:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) )
            {
            // InternalGrana.g:1390:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalGrana.g:1391:1: (lv_name_0_0= RULE_ID )
            {
            // InternalGrana.g:1391:1: (lv_name_0_0= RULE_ID )
            // InternalGrana.g:1392:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_18); 

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

            // InternalGrana.g:1408:2: ( (lv_output_1_0= ruleLocalOutput ) )
            // InternalGrana.g:1409:1: (lv_output_1_0= ruleLocalOutput )
            {
            // InternalGrana.g:1409:1: (lv_output_1_0= ruleLocalOutput )
            // InternalGrana.g:1410:3: lv_output_1_0= ruleLocalOutput
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
    // InternalGrana.g:1434:1: entryRuleOutputReference returns [EObject current=null] : iv_ruleOutputReference= ruleOutputReference EOF ;
    public final EObject entryRuleOutputReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputReference = null;


        try {
            // InternalGrana.g:1435:2: (iv_ruleOutputReference= ruleOutputReference EOF )
            // InternalGrana.g:1436:2: iv_ruleOutputReference= ruleOutputReference EOF
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
    // InternalGrana.g:1443:1: ruleOutputReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleOutputReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1446:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) )
            // InternalGrana.g:1447:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalGrana.g:1447:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            // InternalGrana.g:1447:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,35,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getOutputReferenceAccess().getRefKeyword_0());
                
            // InternalGrana.g:1451:1: ( (otherlv_1= RULE_ID ) )
            // InternalGrana.g:1452:1: (otherlv_1= RULE_ID )
            {
            // InternalGrana.g:1452:1: (otherlv_1= RULE_ID )
            // InternalGrana.g:1453:3: otherlv_1= RULE_ID
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
    // InternalGrana.g:1472:1: entryRuleLocalOutput returns [EObject current=null] : iv_ruleLocalOutput= ruleLocalOutput EOF ;
    public final EObject entryRuleLocalOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalOutput = null;


        try {
            // InternalGrana.g:1473:2: (iv_ruleLocalOutput= ruleLocalOutput EOF )
            // InternalGrana.g:1474:2: iv_ruleLocalOutput= ruleLocalOutput EOF
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
    // InternalGrana.g:1481:1: ruleLocalOutput returns [EObject current=null] : ( (lv_path_0_0= RULE_STRING ) ) ;
    public final EObject ruleLocalOutput() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1484:28: ( ( (lv_path_0_0= RULE_STRING ) ) )
            // InternalGrana.g:1485:1: ( (lv_path_0_0= RULE_STRING ) )
            {
            // InternalGrana.g:1485:1: ( (lv_path_0_0= RULE_STRING ) )
            // InternalGrana.g:1486:1: (lv_path_0_0= RULE_STRING )
            {
            // InternalGrana.g:1486:1: (lv_path_0_0= RULE_STRING )
            // InternalGrana.g:1487:3: lv_path_0_0= RULE_STRING
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
    // InternalGrana.g:1511:1: entryRuleAnalysis returns [EObject current=null] : iv_ruleAnalysis= ruleAnalysis EOF ;
    public final EObject entryRuleAnalysis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysis = null;


        try {
            // InternalGrana.g:1512:2: (iv_ruleAnalysis= ruleAnalysis EOF )
            // InternalGrana.g:1513:2: iv_ruleAnalysis= ruleAnalysis EOF
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
    // InternalGrana.g:1520:1: ruleAnalysis returns [EObject current=null] : ( (lv_name_0_0= ruleQualifiedID ) ) ;
    public final EObject ruleAnalysis() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1523:28: ( ( (lv_name_0_0= ruleQualifiedID ) ) )
            // InternalGrana.g:1524:1: ( (lv_name_0_0= ruleQualifiedID ) )
            {
            // InternalGrana.g:1524:1: ( (lv_name_0_0= ruleQualifiedID ) )
            // InternalGrana.g:1525:1: (lv_name_0_0= ruleQualifiedID )
            {
            // InternalGrana.g:1525:1: (lv_name_0_0= ruleQualifiedID )
            // InternalGrana.g:1526:3: lv_name_0_0= ruleQualifiedID
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
    // InternalGrana.g:1550:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // InternalGrana.g:1551:2: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // InternalGrana.g:1552:2: iv_ruleKIdentifier= ruleKIdentifier EOF
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
    // InternalGrana.g:1559:1: ruleKIdentifier returns [EObject current=null] : ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_persistentEntries_3_0 = null;

        EObject lv_persistentEntries_4_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1562:28: ( ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) )
            // InternalGrana.g:1563:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            {
            // InternalGrana.g:1563:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            // InternalGrana.g:1563:2: () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}'
            {
            // InternalGrana.g:1563:2: ()
            // InternalGrana.g:1564:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKIdentifierAccess().getKIdentifierAction_0(),
                        current);
                

            }

            // InternalGrana.g:1569:2: ( (lv_id_1_0= RULE_ID ) )
            // InternalGrana.g:1570:1: (lv_id_1_0= RULE_ID )
            {
            // InternalGrana.g:1570:1: (lv_id_1_0= RULE_ID )
            // InternalGrana.g:1571:3: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_32); 

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

            otherlv_2=(Token)match(input,37,FOLLOW_33); 

                	newLeafNode(otherlv_2, grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2());
                
            // InternalGrana.g:1591:1: ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==RULE_ID) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalGrana.g:1591:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    {
                    // InternalGrana.g:1591:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) )
                    // InternalGrana.g:1592:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    {
                    // InternalGrana.g:1592:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    // InternalGrana.g:1593:3: lv_persistentEntries_3_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_33);
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

                    // InternalGrana.g:1609:2: ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==RULE_ID) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // InternalGrana.g:1610:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    {
                    	    // InternalGrana.g:1610:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    // InternalGrana.g:1611:3: lv_persistentEntries_4_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_33);
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
                    	    break loop34;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,38,FOLLOW_2); 

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
    // InternalGrana.g:1639:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // InternalGrana.g:1640:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // InternalGrana.g:1641:2: iv_rulePersistentEntry= rulePersistentEntry EOF
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
    // InternalGrana.g:1648:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1651:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // InternalGrana.g:1652:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // InternalGrana.g:1652:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            // InternalGrana.g:1652:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // InternalGrana.g:1652:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // InternalGrana.g:1653:1: (lv_key_0_0= ruleQualifiedID )
            {
            // InternalGrana.g:1653:1: (lv_key_0_0= ruleQualifiedID )
            // InternalGrana.g:1654:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_34);
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

            otherlv_1=(Token)match(input,39,FOLLOW_35); 

                	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getColonKeyword_1());
                
            // InternalGrana.g:1674:1: ( (lv_value_2_0= rulePropertyValue ) )
            // InternalGrana.g:1675:1: (lv_value_2_0= rulePropertyValue )
            {
            // InternalGrana.g:1675:1: (lv_value_2_0= rulePropertyValue )
            // InternalGrana.g:1676:3: lv_value_2_0= rulePropertyValue
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
    // InternalGrana.g:1700:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // InternalGrana.g:1701:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // InternalGrana.g:1702:2: iv_ruleQualifiedID= ruleQualifiedID EOF
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
    // InternalGrana.g:1709:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1712:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalGrana.g:1713:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalGrana.g:1713:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalGrana.g:1713:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_36); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // InternalGrana.g:1720:1: (kw= '.' this_ID_2= RULE_ID )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==40) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalGrana.g:1721:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,40,FOLLOW_10); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_36); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop36;
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
    // InternalGrana.g:1741:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // InternalGrana.g:1742:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // InternalGrana.g:1743:2: iv_rulePropertyValue= rulePropertyValue EOF
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
    // InternalGrana.g:1750:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1753:28: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // InternalGrana.g:1754:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // InternalGrana.g:1754:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            int alt37=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt37=1;
                }
                break;
            case RULE_STRING:
                {
                alt37=2;
                }
                break;
            case RULE_NATURAL:
            case RULE_TFLOAT:
                {
                alt37=3;
                }
                break;
            case RULE_ID:
                {
                alt37=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // InternalGrana.g:1754:6: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_2); 

                    		current.merge(this_BOOLEAN_0);
                        
                     
                        newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:1762:10: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    		current.merge(this_STRING_1);
                        
                     
                        newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // InternalGrana.g:1771:5: this_Float_2= ruleFloat
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
                    // InternalGrana.g:1783:5: this_QualifiedID_3= ruleQualifiedID
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
    // InternalGrana.g:1801:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // InternalGrana.g:1802:2: (iv_ruleFloat= ruleFloat EOF )
            // InternalGrana.g:1803:2: iv_ruleFloat= ruleFloat EOF
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
    // InternalGrana.g:1810:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1813:28: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // InternalGrana.g:1814:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // InternalGrana.g:1814:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==RULE_TFLOAT) ) {
                alt38=1;
            }
            else if ( (LA38_0==RULE_NATURAL) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // InternalGrana.g:1814:6: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FOLLOW_2); 

                    		current.merge(this_TFLOAT_0);
                        
                     
                        newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:1822:10: this_NATURAL_1= RULE_NATURAL
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


    // $ANTLR start "ruleOutputType"
    // InternalGrana.g:1837:1: ruleOutputType returns [Enumerator current=null] : ( (enumLiteral_0= 'csv' ) | (enumLiteral_1= 'json' ) ) ;
    public final Enumerator ruleOutputType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalGrana.g:1839:28: ( ( (enumLiteral_0= 'csv' ) | (enumLiteral_1= 'json' ) ) )
            // InternalGrana.g:1840:1: ( (enumLiteral_0= 'csv' ) | (enumLiteral_1= 'json' ) )
            {
            // InternalGrana.g:1840:1: ( (enumLiteral_0= 'csv' ) | (enumLiteral_1= 'json' ) )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==41) ) {
                alt39=1;
            }
            else if ( (LA39_0==42) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // InternalGrana.g:1840:2: (enumLiteral_0= 'csv' )
                    {
                    // InternalGrana.g:1840:2: (enumLiteral_0= 'csv' )
                    // InternalGrana.g:1840:4: enumLiteral_0= 'csv'
                    {
                    enumLiteral_0=(Token)match(input,41,FOLLOW_2); 

                            current = grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1846:6: (enumLiteral_1= 'json' )
                    {
                    // InternalGrana.g:1846:6: (enumLiteral_1= 'json' )
                    // InternalGrana.g:1846:8: enumLiteral_1= 'json'
                    {
                    enumLiteral_1=(Token)match(input,42,FOLLOW_2); 

                            current = grammarAccess.getOutputTypeAccess().getJsonEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getOutputTypeAccess().getJsonEnumLiteralDeclaration_1()); 
                        

                    }


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
    // $ANTLR end "ruleOutputType"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000006010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000018010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000003020000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000003020010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000003020002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000000001C0000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000800000040L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000800200040L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000060800000040L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000340000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000028000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000010800000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000010000000002L});

}