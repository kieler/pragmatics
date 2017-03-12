package de.cau.cs.kieler.grana.text.parser.antlr.internal; 

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
import de.cau.cs.kieler.grana.text.services.GranaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGranaParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_SIGNED_INT", "RULE_FLOAT", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'globalResources'", "'globalOutputs'", "'execute'", "'parallel'", "'all'", "'job'", "'layoutBeforeAnalysis'", "'measureExecutionTime'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'comparejob'", "'rangejob'", "'rangeoption'", "'rangeanalysis'", "'component'", "'rangeanalyses'", "'floatvalues'", "','", "'intvalues'", "'intrange'", "'to'", "'enumvalues'", "'ref'", "'filter'", "'recurse'", "'{'", "'}'", "'node'", "'label'", "':'", "'port'", "'layout'", "'['", "'position'", "'size'", "']'", "'edge'", "'->'", "'incoming'", "'outgoing'", "'start'", "'end'", "'bends'", "'|'", "'section'", "'.'", "'null'", "'true'", "'false'", "'csv'", "'json'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__59=59;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__13=13;
    public static final int T__57=57;
    public static final int T__14=14;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=8;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__64=64;
    public static final int T__21=21;
    public static final int T__65=65;
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=11;
    public static final int RULE_SIGNED_INT=5;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int RULE_FLOAT=6;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

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

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalGrana.g:81:4: otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )*
                    {
                    otherlv_0=(Token)match(input,13,FOLLOW_3); 

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
                    	            		"de.cau.cs.kieler.grana.text.Grana.GlobalResourceRef");
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

            if ( (LA4_0==14) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalGrana.g:103:7: otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )*
                    {
                    otherlv_2=(Token)match(input,14,FOLLOW_4); 

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
                    	            		"de.cau.cs.kieler.grana.text.Grana.GlobalOutputRef");
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
            otherlv_4=(Token)match(input,15,FOLLOW_5); 

                	newLeafNode(otherlv_4, grammarAccess.getGranaAccess().getExecuteKeyword_2_0());
                
            // InternalGrana.g:129:1: ( (lv_parallel_5_0= 'parallel' ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==16) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalGrana.g:130:1: (lv_parallel_5_0= 'parallel' )
                    {
                    // InternalGrana.g:130:1: (lv_parallel_5_0= 'parallel' )
                    // InternalGrana.g:131:3: lv_parallel_5_0= 'parallel'
                    {
                    lv_parallel_5_0=(Token)match(input,16,FOLLOW_6); 

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

            if ( (LA7_0==17) ) {
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
                    lv_executeAll_6_0=(Token)match(input,17,FOLLOW_7); 

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

                if ( (LA8_0==18||(LA8_0>=25 && LA8_0<=26)) ) {
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
            	            		"de.cau.cs.kieler.grana.text.Grana.Job");
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
            case 18:
                {
                alt9=1;
                }
                break;
            case 26:
                {
                alt9=2;
                }
                break;
            case 25:
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
    // InternalGrana.g:258:1: ruleRegularJob returns [EObject current=null] : (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleLayoutConfig ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) ) ) ;
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
            // InternalGrana.g:261:28: ( (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleLayoutConfig ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) ) ) )
            // InternalGrana.g:262:1: (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleLayoutConfig ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) ) )
            {
            // InternalGrana.g:262:1: (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleLayoutConfig ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) ) )
            // InternalGrana.g:262:3: otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleLayoutConfig ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_outputType_11_0= ruleOutputType ) )? ( (lv_output_12_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_10); 

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
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            // InternalGrana.g:284:2: ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==19) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalGrana.g:285:1: (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' )
                    {
                    // InternalGrana.g:285:1: (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' )
                    // InternalGrana.g:286:3: lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis'
                    {
                    lv_layoutBeforeAnalysis_2_0=(Token)match(input,19,FOLLOW_12); 

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

            if ( (LA11_0==20) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalGrana.g:300:1: (lv_measureExecutionTime_3_0= 'measureExecutionTime' )
                    {
                    // InternalGrana.g:300:1: (lv_measureExecutionTime_3_0= 'measureExecutionTime' )
                    // InternalGrana.g:301:3: lv_measureExecutionTime_3_0= 'measureExecutionTime'
                    {
                    lv_measureExecutionTime_3_0=(Token)match(input,20,FOLLOW_13); 

                            newLeafNode(lv_measureExecutionTime_3_0, grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRegularJobRule());
                    	        }
                           		setWithLastConsumed(current, "measureExecutionTime", true, "measureExecutionTime");
                    	    

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,21,FOLLOW_14); 

                	newLeafNode(otherlv_4, grammarAccess.getRegularJobAccess().getResourcesKeyword_4());
                
            // InternalGrana.g:318:1: ( (lv_resources_5_0= ruleResource ) )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_STRING||LA12_0==37) ) {
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
            	            		"de.cau.cs.kieler.grana.text.Grana.Resource");
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

            otherlv_6=(Token)match(input,22,FOLLOW_10); 

                	newLeafNode(otherlv_6, grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6());
                
            // InternalGrana.g:340:1: ( (lv_layoutOptions_7_0= ruleLayoutConfig ) )+
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
            	    // InternalGrana.g:341:1: (lv_layoutOptions_7_0= ruleLayoutConfig )
            	    {
            	    // InternalGrana.g:341:1: (lv_layoutOptions_7_0= ruleLayoutConfig )
            	    // InternalGrana.g:342:3: lv_layoutOptions_7_0= ruleLayoutConfig
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_7_0()); 
            	    	    
            	    pushFollow(FOLLOW_16);
            	    lv_layoutOptions_7_0=ruleLayoutConfig();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"layoutOptions",
            	            		lv_layoutOptions_7_0, 
            	            		"de.cau.cs.kieler.grana.text.Grana.LayoutConfig");
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

            otherlv_8=(Token)match(input,23,FOLLOW_10); 

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
            	            		"de.cau.cs.kieler.grana.text.Grana.Analysis");
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

            otherlv_10=(Token)match(input,24,FOLLOW_18); 

                	newLeafNode(otherlv_10, grammarAccess.getRegularJobAccess().getOutputKeyword_10());
                
            // InternalGrana.g:384:1: ( (lv_outputType_11_0= ruleOutputType ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=64 && LA15_0<=65)) ) {
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
                            		"de.cau.cs.kieler.grana.text.Grana.OutputType");
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
                    		"de.cau.cs.kieler.grana.text.Grana.Output");
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
    // InternalGrana.g:437:1: ruleCompareJob returns [EObject current=null] : (otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleLayoutConfig ) ) ( (lv_layoutOptions_6_0= ruleLayoutConfig ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) ) ) ;
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
            // InternalGrana.g:440:28: ( (otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleLayoutConfig ) ) ( (lv_layoutOptions_6_0= ruleLayoutConfig ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) ) ) )
            // InternalGrana.g:441:1: (otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleLayoutConfig ) ) ( (lv_layoutOptions_6_0= ruleLayoutConfig ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) ) )
            {
            // InternalGrana.g:441:1: (otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleLayoutConfig ) ) ( (lv_layoutOptions_6_0= ruleLayoutConfig ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) ) )
            // InternalGrana.g:441:3: otherlv_0= 'comparejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleLayoutConfig ) ) ( (lv_layoutOptions_6_0= ruleLayoutConfig ) ) otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'output' ( (lv_outputType_10_0= ruleOutputType ) )? ( (lv_output_11_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,25,FOLLOW_10); 

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
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_14); 

                	newLeafNode(otherlv_2, grammarAccess.getCompareJobAccess().getResourcesKeyword_2());
                
            // InternalGrana.g:467:1: ( (lv_resources_3_0= ruleResource ) )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==RULE_STRING||LA16_0==37) ) {
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
            	            		"de.cau.cs.kieler.grana.text.Grana.Resource");
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

            otherlv_4=(Token)match(input,22,FOLLOW_10); 

                	newLeafNode(otherlv_4, grammarAccess.getCompareJobAccess().getLayoutoptionsKeyword_4());
                
            // InternalGrana.g:489:1: ( (lv_layoutOptions_5_0= ruleLayoutConfig ) )
            // InternalGrana.g:490:1: (lv_layoutOptions_5_0= ruleLayoutConfig )
            {
            // InternalGrana.g:490:1: (lv_layoutOptions_5_0= ruleLayoutConfig )
            // InternalGrana.g:491:3: lv_layoutOptions_5_0= ruleLayoutConfig
            {
             
            	        newCompositeNode(grammarAccess.getCompareJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_10);
            lv_layoutOptions_5_0=ruleLayoutConfig();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCompareJobRule());
            	        }
                   		add(
                   			current, 
                   			"layoutOptions",
                    		lv_layoutOptions_5_0, 
                    		"de.cau.cs.kieler.grana.text.Grana.LayoutConfig");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:507:2: ( (lv_layoutOptions_6_0= ruleLayoutConfig ) )
            // InternalGrana.g:508:1: (lv_layoutOptions_6_0= ruleLayoutConfig )
            {
            // InternalGrana.g:508:1: (lv_layoutOptions_6_0= ruleLayoutConfig )
            // InternalGrana.g:509:3: lv_layoutOptions_6_0= ruleLayoutConfig
            {
             
            	        newCompositeNode(grammarAccess.getCompareJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_19);
            lv_layoutOptions_6_0=ruleLayoutConfig();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCompareJobRule());
            	        }
                   		add(
                   			current, 
                   			"layoutOptions",
                    		lv_layoutOptions_6_0, 
                    		"de.cau.cs.kieler.grana.text.Grana.LayoutConfig");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,23,FOLLOW_10); 

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
            	            		"de.cau.cs.kieler.grana.text.Grana.Analysis");
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

            otherlv_9=(Token)match(input,24,FOLLOW_18); 

                	newLeafNode(otherlv_9, grammarAccess.getCompareJobAccess().getOutputKeyword_9());
                
            // InternalGrana.g:551:1: ( (lv_outputType_10_0= ruleOutputType ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=64 && LA18_0<=65)) ) {
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
                            		"de.cau.cs.kieler.grana.text.Grana.OutputType");
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
                    		"de.cau.cs.kieler.grana.text.Grana.Output");
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
    // InternalGrana.g:604:1: ruleRangeJob returns [EObject current=null] : (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleLayoutConfig ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedId ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) ) ) ;
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
            // InternalGrana.g:607:28: ( (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleLayoutConfig ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedId ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) ) ) )
            // InternalGrana.g:608:1: (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleLayoutConfig ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedId ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) ) )
            {
            // InternalGrana.g:608:1: (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleLayoutConfig ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedId ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) ) )
            // InternalGrana.g:608:3: otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )? otherlv_3= 'resources' ( (lv_resources_4_0= ruleResource ) )+ otherlv_5= 'layoutoptions' ( (lv_layoutOptions_6_0= ruleLayoutConfig ) )+ otherlv_7= 'analyses' ( (lv_analyses_8_0= ruleAnalysis ) )+ otherlv_9= 'rangeoption' ( (lv_rangeOption_10_0= ruleQualifiedId ) ) ( (lv_rangeValues_11_0= ruleRange ) ) ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) ) otherlv_18= 'output' ( (lv_outputType_19_0= ruleOutputType ) )? ( (lv_output_20_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_10); 

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
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            // InternalGrana.g:630:2: ( (lv_measureExecutionTime_2_0= 'measureExecutionTime' ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==20) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalGrana.g:631:1: (lv_measureExecutionTime_2_0= 'measureExecutionTime' )
                    {
                    // InternalGrana.g:631:1: (lv_measureExecutionTime_2_0= 'measureExecutionTime' )
                    // InternalGrana.g:632:3: lv_measureExecutionTime_2_0= 'measureExecutionTime'
                    {
                    lv_measureExecutionTime_2_0=(Token)match(input,20,FOLLOW_13); 

                            newLeafNode(lv_measureExecutionTime_2_0, grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeJobRule());
                    	        }
                           		setWithLastConsumed(current, "measureExecutionTime", true, "measureExecutionTime");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,21,FOLLOW_14); 

                	newLeafNode(otherlv_3, grammarAccess.getRangeJobAccess().getResourcesKeyword_3());
                
            // InternalGrana.g:649:1: ( (lv_resources_4_0= ruleResource ) )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_STRING||LA20_0==37) ) {
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
            	            		"de.cau.cs.kieler.grana.text.Grana.Resource");
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

            otherlv_5=(Token)match(input,22,FOLLOW_10); 

                	newLeafNode(otherlv_5, grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_5());
                
            // InternalGrana.g:671:1: ( (lv_layoutOptions_6_0= ruleLayoutConfig ) )+
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
            	    // InternalGrana.g:672:1: (lv_layoutOptions_6_0= ruleLayoutConfig )
            	    {
            	    // InternalGrana.g:672:1: (lv_layoutOptions_6_0= ruleLayoutConfig )
            	    // InternalGrana.g:673:3: lv_layoutOptions_6_0= ruleLayoutConfig
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_6_0()); 
            	    	    
            	    pushFollow(FOLLOW_16);
            	    lv_layoutOptions_6_0=ruleLayoutConfig();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"layoutOptions",
            	            		lv_layoutOptions_6_0, 
            	            		"de.cau.cs.kieler.grana.text.Grana.LayoutConfig");
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

            otherlv_7=(Token)match(input,23,FOLLOW_10); 

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
            	            		"de.cau.cs.kieler.grana.text.Grana.Analysis");
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

            otherlv_9=(Token)match(input,27,FOLLOW_10); 

                	newLeafNode(otherlv_9, grammarAccess.getRangeJobAccess().getRangeoptionKeyword_9());
                
            // InternalGrana.g:715:1: ( (lv_rangeOption_10_0= ruleQualifiedId ) )
            // InternalGrana.g:716:1: (lv_rangeOption_10_0= ruleQualifiedId )
            {
            // InternalGrana.g:716:1: (lv_rangeOption_10_0= ruleQualifiedId )
            // InternalGrana.g:717:3: lv_rangeOption_10_0= ruleQualifiedId
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIdParserRuleCall_10_0()); 
            	    
            pushFollow(FOLLOW_21);
            lv_rangeOption_10_0=ruleQualifiedId();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeOption",
                    		lv_rangeOption_10_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.QualifiedId");
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
                    		"de.cau.cs.kieler.grana.text.Grana.Range");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:751:2: ( (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )? ) | (otherlv_16= 'rangeanalyses' ( (lv_rangeAnalyses_17_0= ruleAnalysis ) )+ ) )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==28) ) {
                alt25=1;
            }
            else if ( (LA25_0==30) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalGrana.g:751:3: (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )? )
                    {
                    // InternalGrana.g:751:3: (otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )? )
                    // InternalGrana.g:751:5: otherlv_12= 'rangeanalysis' ( (lv_rangeAnalysis_13_0= ruleAnalysis ) ) (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )?
                    {
                    otherlv_12=(Token)match(input,28,FOLLOW_10); 

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
                            		"de.cau.cs.kieler.grana.text.Grana.Analysis");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalGrana.g:773:2: (otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) ) )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==29) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // InternalGrana.g:773:4: otherlv_14= 'component' ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) )
                            {
                            otherlv_14=(Token)match(input,29,FOLLOW_24); 

                                	newLeafNode(otherlv_14, grammarAccess.getRangeJobAccess().getComponentKeyword_12_0_2_0());
                                
                            // InternalGrana.g:777:1: ( (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT ) )
                            // InternalGrana.g:778:1: (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT )
                            {
                            // InternalGrana.g:778:1: (lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT )
                            // InternalGrana.g:779:3: lv_rangeAnalysisComponent_15_0= RULE_SIGNED_INT
                            {
                            lv_rangeAnalysisComponent_15_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_25); 

                            			newLeafNode(lv_rangeAnalysisComponent_15_0, grammarAccess.getRangeJobAccess().getRangeAnalysisComponentSIGNED_INTTerminalRuleCall_12_0_2_1_0()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getRangeJobRule());
                            	        }
                                   		setWithLastConsumed(
                                   			current, 
                                   			"rangeAnalysisComponent",
                                    		lv_rangeAnalysisComponent_15_0, 
                                    		"org.eclipse.elk.graph.text.ElkGraph.SIGNED_INT");
                            	    

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
                    otherlv_16=(Token)match(input,30,FOLLOW_10); 

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
                    	            		"de.cau.cs.kieler.grana.text.Grana.Analysis");
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

            otherlv_18=(Token)match(input,24,FOLLOW_18); 

                	newLeafNode(otherlv_18, grammarAccess.getRangeJobAccess().getOutputKeyword_13());
                
            // InternalGrana.g:822:1: ( (lv_outputType_19_0= ruleOutputType ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=64 && LA26_0<=65)) ) {
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
                            		"de.cau.cs.kieler.grana.text.Grana.OutputType");
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
                    		"de.cau.cs.kieler.grana.text.Grana.Output");
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
    // InternalGrana.g:875:1: ruleRange returns [EObject current=null] : (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange | this_EnumRange_2= ruleEnumRange ) ;
    public final EObject ruleRange() throws RecognitionException {
        EObject current = null;

        EObject this_FloatRange_0 = null;

        EObject this_IntRange_1 = null;

        EObject this_EnumRange_2 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:878:28: ( (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange | this_EnumRange_2= ruleEnumRange ) )
            // InternalGrana.g:879:1: (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange | this_EnumRange_2= ruleEnumRange )
            {
            // InternalGrana.g:879:1: (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange | this_EnumRange_2= ruleEnumRange )
            int alt27=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt27=1;
                }
                break;
            case 33:
            case 34:
                {
                alt27=2;
                }
                break;
            case 36:
                {
                alt27=3;
                }
                break;
            default:
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
                case 3 :
                    // InternalGrana.g:900:5: this_EnumRange_2= ruleEnumRange
                    {
                     
                            newCompositeNode(grammarAccess.getRangeAccess().getEnumRangeParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_2);
                    this_EnumRange_2=ruleEnumRange();

                    state._fsp--;

                     
                            current = this_EnumRange_2; 
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
    // InternalGrana.g:916:1: entryRuleFloatRange returns [EObject current=null] : iv_ruleFloatRange= ruleFloatRange EOF ;
    public final EObject entryRuleFloatRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatRange = null;


        try {
            // InternalGrana.g:917:2: (iv_ruleFloatRange= ruleFloatRange EOF )
            // InternalGrana.g:918:2: iv_ruleFloatRange= ruleFloatRange EOF
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
    // InternalGrana.g:925:1: ruleFloatRange returns [EObject current=null] : (otherlv_0= 'floatvalues' ( (lv_values_1_0= RULE_FLOAT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_FLOAT ) ) )* ) ;
    public final EObject ruleFloatRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:928:28: ( (otherlv_0= 'floatvalues' ( (lv_values_1_0= RULE_FLOAT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_FLOAT ) ) )* ) )
            // InternalGrana.g:929:1: (otherlv_0= 'floatvalues' ( (lv_values_1_0= RULE_FLOAT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_FLOAT ) ) )* )
            {
            // InternalGrana.g:929:1: (otherlv_0= 'floatvalues' ( (lv_values_1_0= RULE_FLOAT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_FLOAT ) ) )* )
            // InternalGrana.g:929:3: otherlv_0= 'floatvalues' ( (lv_values_1_0= RULE_FLOAT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_FLOAT ) ) )*
            {
            otherlv_0=(Token)match(input,31,FOLLOW_26); 

                	newLeafNode(otherlv_0, grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0());
                
            // InternalGrana.g:933:1: ( (lv_values_1_0= RULE_FLOAT ) )
            // InternalGrana.g:934:1: (lv_values_1_0= RULE_FLOAT )
            {
            // InternalGrana.g:934:1: (lv_values_1_0= RULE_FLOAT )
            // InternalGrana.g:935:3: lv_values_1_0= RULE_FLOAT
            {
            lv_values_1_0=(Token)match(input,RULE_FLOAT,FOLLOW_27); 

            			newLeafNode(lv_values_1_0, grammarAccess.getFloatRangeAccess().getValuesFLOATTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getFloatRangeRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.FLOAT");
            	    

            }


            }

            // InternalGrana.g:951:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_FLOAT ) ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==32) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalGrana.g:951:4: otherlv_2= ',' ( (lv_values_3_0= RULE_FLOAT ) )
            	    {
            	    otherlv_2=(Token)match(input,32,FOLLOW_26); 

            	        	newLeafNode(otherlv_2, grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0());
            	        
            	    // InternalGrana.g:955:1: ( (lv_values_3_0= RULE_FLOAT ) )
            	    // InternalGrana.g:956:1: (lv_values_3_0= RULE_FLOAT )
            	    {
            	    // InternalGrana.g:956:1: (lv_values_3_0= RULE_FLOAT )
            	    // InternalGrana.g:957:3: lv_values_3_0= RULE_FLOAT
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_FLOAT,FOLLOW_27); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getFloatRangeAccess().getValuesFLOATTerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getFloatRangeRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.FLOAT");
            	    	    

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
    // InternalGrana.g:981:1: entryRuleIntRange returns [EObject current=null] : iv_ruleIntRange= ruleIntRange EOF ;
    public final EObject entryRuleIntRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRange = null;


        try {
            // InternalGrana.g:982:2: (iv_ruleIntRange= ruleIntRange EOF )
            // InternalGrana.g:983:2: iv_ruleIntRange= ruleIntRange EOF
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
    // InternalGrana.g:990:1: ruleIntRange returns [EObject current=null] : (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues ) ;
    public final EObject ruleIntRange() throws RecognitionException {
        EObject current = null;

        EObject this_IntRangeRange_0 = null;

        EObject this_IntRangeValues_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:993:28: ( (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues ) )
            // InternalGrana.g:994:1: (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues )
            {
            // InternalGrana.g:994:1: (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==34) ) {
                alt29=1;
            }
            else if ( (LA29_0==33) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // InternalGrana.g:995:5: this_IntRangeRange_0= ruleIntRangeRange
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
                    // InternalGrana.g:1005:5: this_IntRangeValues_1= ruleIntRangeValues
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
    // InternalGrana.g:1021:1: entryRuleIntRangeValues returns [EObject current=null] : iv_ruleIntRangeValues= ruleIntRangeValues EOF ;
    public final EObject entryRuleIntRangeValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRangeValues = null;


        try {
            // InternalGrana.g:1022:2: (iv_ruleIntRangeValues= ruleIntRangeValues EOF )
            // InternalGrana.g:1023:2: iv_ruleIntRangeValues= ruleIntRangeValues EOF
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
    // InternalGrana.g:1030:1: ruleIntRangeValues returns [EObject current=null] : (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_SIGNED_INT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_INT ) ) )* ) ;
    public final EObject ruleIntRangeValues() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1033:28: ( (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_SIGNED_INT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_INT ) ) )* ) )
            // InternalGrana.g:1034:1: (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_SIGNED_INT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_INT ) ) )* )
            {
            // InternalGrana.g:1034:1: (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_SIGNED_INT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_INT ) ) )* )
            // InternalGrana.g:1034:3: otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_SIGNED_INT ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_INT ) ) )*
            {
            otherlv_0=(Token)match(input,33,FOLLOW_24); 

                	newLeafNode(otherlv_0, grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0());
                
            // InternalGrana.g:1038:1: ( (lv_values_1_0= RULE_SIGNED_INT ) )
            // InternalGrana.g:1039:1: (lv_values_1_0= RULE_SIGNED_INT )
            {
            // InternalGrana.g:1039:1: (lv_values_1_0= RULE_SIGNED_INT )
            // InternalGrana.g:1040:3: lv_values_1_0= RULE_SIGNED_INT
            {
            lv_values_1_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_27); 

            			newLeafNode(lv_values_1_0, grammarAccess.getIntRangeValuesAccess().getValuesSIGNED_INTTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeValuesRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.SIGNED_INT");
            	    

            }


            }

            // InternalGrana.g:1056:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_INT ) ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==32) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalGrana.g:1056:4: otherlv_2= ',' ( (lv_values_3_0= RULE_SIGNED_INT ) )
            	    {
            	    otherlv_2=(Token)match(input,32,FOLLOW_24); 

            	        	newLeafNode(otherlv_2, grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0());
            	        
            	    // InternalGrana.g:1060:1: ( (lv_values_3_0= RULE_SIGNED_INT ) )
            	    // InternalGrana.g:1061:1: (lv_values_3_0= RULE_SIGNED_INT )
            	    {
            	    // InternalGrana.g:1061:1: (lv_values_3_0= RULE_SIGNED_INT )
            	    // InternalGrana.g:1062:3: lv_values_3_0= RULE_SIGNED_INT
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_27); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getIntRangeValuesAccess().getValuesSIGNED_INTTerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getIntRangeValuesRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.SIGNED_INT");
            	    	    

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
    // InternalGrana.g:1086:1: entryRuleIntRangeRange returns [EObject current=null] : iv_ruleIntRangeRange= ruleIntRangeRange EOF ;
    public final EObject entryRuleIntRangeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRangeRange = null;


        try {
            // InternalGrana.g:1087:2: (iv_ruleIntRangeRange= ruleIntRangeRange EOF )
            // InternalGrana.g:1088:2: iv_ruleIntRangeRange= ruleIntRangeRange EOF
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
    // InternalGrana.g:1095:1: ruleIntRangeRange returns [EObject current=null] : (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_SIGNED_INT ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_SIGNED_INT ) ) ) ;
    public final EObject ruleIntRangeRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_start_1_0=null;
        Token otherlv_2=null;
        Token lv_end_3_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1098:28: ( (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_SIGNED_INT ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_SIGNED_INT ) ) ) )
            // InternalGrana.g:1099:1: (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_SIGNED_INT ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_SIGNED_INT ) ) )
            {
            // InternalGrana.g:1099:1: (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_SIGNED_INT ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_SIGNED_INT ) ) )
            // InternalGrana.g:1099:3: otherlv_0= 'intrange' ( (lv_start_1_0= RULE_SIGNED_INT ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_SIGNED_INT ) )
            {
            otherlv_0=(Token)match(input,34,FOLLOW_24); 

                	newLeafNode(otherlv_0, grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0());
                
            // InternalGrana.g:1103:1: ( (lv_start_1_0= RULE_SIGNED_INT ) )
            // InternalGrana.g:1104:1: (lv_start_1_0= RULE_SIGNED_INT )
            {
            // InternalGrana.g:1104:1: (lv_start_1_0= RULE_SIGNED_INT )
            // InternalGrana.g:1105:3: lv_start_1_0= RULE_SIGNED_INT
            {
            lv_start_1_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_28); 

            			newLeafNode(lv_start_1_0, grammarAccess.getIntRangeRangeAccess().getStartSIGNED_INTTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeRangeRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"start",
                    		lv_start_1_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.SIGNED_INT");
            	    

            }


            }

            otherlv_2=(Token)match(input,35,FOLLOW_24); 

                	newLeafNode(otherlv_2, grammarAccess.getIntRangeRangeAccess().getToKeyword_2());
                
            // InternalGrana.g:1125:1: ( (lv_end_3_0= RULE_SIGNED_INT ) )
            // InternalGrana.g:1126:1: (lv_end_3_0= RULE_SIGNED_INT )
            {
            // InternalGrana.g:1126:1: (lv_end_3_0= RULE_SIGNED_INT )
            // InternalGrana.g:1127:3: lv_end_3_0= RULE_SIGNED_INT
            {
            lv_end_3_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_2); 

            			newLeafNode(lv_end_3_0, grammarAccess.getIntRangeRangeAccess().getEndSIGNED_INTTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeRangeRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"end",
                    		lv_end_3_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.SIGNED_INT");
            	    

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


    // $ANTLR start "entryRuleEnumRange"
    // InternalGrana.g:1151:1: entryRuleEnumRange returns [EObject current=null] : iv_ruleEnumRange= ruleEnumRange EOF ;
    public final EObject entryRuleEnumRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumRange = null;


        try {
            // InternalGrana.g:1152:2: (iv_ruleEnumRange= ruleEnumRange EOF )
            // InternalGrana.g:1153:2: iv_ruleEnumRange= ruleEnumRange EOF
            {
             newCompositeNode(grammarAccess.getEnumRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEnumRange=ruleEnumRange();

            state._fsp--;

             current =iv_ruleEnumRange; 
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
    // $ANTLR end "entryRuleEnumRange"


    // $ANTLR start "ruleEnumRange"
    // InternalGrana.g:1160:1: ruleEnumRange returns [EObject current=null] : (otherlv_0= 'enumvalues' ( (lv_values_1_0= ruleQualifiedIdValue ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleQualifiedIdValue ) ) )* ) ;
    public final EObject ruleEnumRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_values_1_0 = null;

        AntlrDatatypeRuleToken lv_values_3_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1163:28: ( (otherlv_0= 'enumvalues' ( (lv_values_1_0= ruleQualifiedIdValue ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleQualifiedIdValue ) ) )* ) )
            // InternalGrana.g:1164:1: (otherlv_0= 'enumvalues' ( (lv_values_1_0= ruleQualifiedIdValue ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleQualifiedIdValue ) ) )* )
            {
            // InternalGrana.g:1164:1: (otherlv_0= 'enumvalues' ( (lv_values_1_0= ruleQualifiedIdValue ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleQualifiedIdValue ) ) )* )
            // InternalGrana.g:1164:3: otherlv_0= 'enumvalues' ( (lv_values_1_0= ruleQualifiedIdValue ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleQualifiedIdValue ) ) )*
            {
            otherlv_0=(Token)match(input,36,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getEnumRangeAccess().getEnumvaluesKeyword_0());
                
            // InternalGrana.g:1168:1: ( (lv_values_1_0= ruleQualifiedIdValue ) )
            // InternalGrana.g:1169:1: (lv_values_1_0= ruleQualifiedIdValue )
            {
            // InternalGrana.g:1169:1: (lv_values_1_0= ruleQualifiedIdValue )
            // InternalGrana.g:1170:3: lv_values_1_0= ruleQualifiedIdValue
            {
             
            	        newCompositeNode(grammarAccess.getEnumRangeAccess().getValuesQualifiedIdValueParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_27);
            lv_values_1_0=ruleQualifiedIdValue();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getEnumRangeRule());
            	        }
                   		add(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.QualifiedIdValue");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:1186:2: (otherlv_2= ',' ( (lv_values_3_0= ruleQualifiedIdValue ) ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==32) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalGrana.g:1186:4: otherlv_2= ',' ( (lv_values_3_0= ruleQualifiedIdValue ) )
            	    {
            	    otherlv_2=(Token)match(input,32,FOLLOW_10); 

            	        	newLeafNode(otherlv_2, grammarAccess.getEnumRangeAccess().getCommaKeyword_2_0());
            	        
            	    // InternalGrana.g:1190:1: ( (lv_values_3_0= ruleQualifiedIdValue ) )
            	    // InternalGrana.g:1191:1: (lv_values_3_0= ruleQualifiedIdValue )
            	    {
            	    // InternalGrana.g:1191:1: (lv_values_3_0= ruleQualifiedIdValue )
            	    // InternalGrana.g:1192:3: lv_values_3_0= ruleQualifiedIdValue
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getEnumRangeAccess().getValuesQualifiedIdValueParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_27);
            	    lv_values_3_0=ruleQualifiedIdValue();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getEnumRangeRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.QualifiedIdValue");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
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
    // $ANTLR end "ruleEnumRange"


    // $ANTLR start "entryRuleResource"
    // InternalGrana.g:1216:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // InternalGrana.g:1217:2: (iv_ruleResource= ruleResource EOF )
            // InternalGrana.g:1218:2: iv_ruleResource= ruleResource EOF
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
    // InternalGrana.g:1225:1: ruleResource returns [EObject current=null] : (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        EObject this_ResourceReference_0 = null;

        EObject this_LocalResource_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1228:28: ( (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) )
            // InternalGrana.g:1229:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            {
            // InternalGrana.g:1229:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==37) ) {
                alt32=1;
            }
            else if ( (LA32_0==RULE_STRING) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // InternalGrana.g:1230:5: this_ResourceReference_0= ruleResourceReference
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
                    // InternalGrana.g:1240:5: this_LocalResource_1= ruleLocalResource
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
    // InternalGrana.g:1256:1: entryRuleResourceReference returns [EObject current=null] : iv_ruleResourceReference= ruleResourceReference EOF ;
    public final EObject entryRuleResourceReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceReference = null;


        try {
            // InternalGrana.g:1257:2: (iv_ruleResourceReference= ruleResourceReference EOF )
            // InternalGrana.g:1258:2: iv_ruleResourceReference= ruleResourceReference EOF
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
    // InternalGrana.g:1265:1: ruleResourceReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) ;
    public final EObject ruleResourceReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1268:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) )
            // InternalGrana.g:1269:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            {
            // InternalGrana.g:1269:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            // InternalGrana.g:1269:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+
            {
            otherlv_0=(Token)match(input,37,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getResourceReferenceAccess().getRefKeyword_0());
                
            // InternalGrana.g:1273:1: ( (otherlv_1= RULE_ID ) )+
            int cnt33=0;
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalGrana.g:1274:1: (otherlv_1= RULE_ID )
            	    {
            	    // InternalGrana.g:1274:1: (otherlv_1= RULE_ID )
            	    // InternalGrana.g:1275:3: otherlv_1= RULE_ID
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
            	    if ( cnt33 >= 1 ) break loop33;
                        EarlyExitException eee =
                            new EarlyExitException(33, input);
                        throw eee;
                }
                cnt33++;
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
    // InternalGrana.g:1294:1: entryRuleGlobalResourceRef returns [EObject current=null] : iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF ;
    public final EObject entryRuleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalResourceRef = null;


        try {
            // InternalGrana.g:1295:2: (iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF )
            // InternalGrana.g:1296:2: iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF
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
    // InternalGrana.g:1303:1: ruleGlobalResourceRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) ;
    public final EObject ruleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_resources_1_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1306:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) )
            // InternalGrana.g:1307:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            {
            // InternalGrana.g:1307:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            // InternalGrana.g:1307:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) )
            {
            // InternalGrana.g:1307:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalGrana.g:1308:1: (lv_name_0_0= RULE_ID )
            {
            // InternalGrana.g:1308:1: (lv_name_0_0= RULE_ID )
            // InternalGrana.g:1309:3: lv_name_0_0= RULE_ID
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
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            // InternalGrana.g:1325:2: ( (lv_resources_1_0= ruleLocalResource ) )
            // InternalGrana.g:1326:1: (lv_resources_1_0= ruleLocalResource )
            {
            // InternalGrana.g:1326:1: (lv_resources_1_0= ruleLocalResource )
            // InternalGrana.g:1327:3: lv_resources_1_0= ruleLocalResource
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
                    		"de.cau.cs.kieler.grana.text.Grana.LocalResource");
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
    // InternalGrana.g:1351:1: entryRuleLocalResource returns [EObject current=null] : iv_ruleLocalResource= ruleLocalResource EOF ;
    public final EObject entryRuleLocalResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalResource = null;


        try {
            // InternalGrana.g:1352:2: (iv_ruleLocalResource= ruleLocalResource EOF )
            // InternalGrana.g:1353:2: iv_ruleLocalResource= ruleLocalResource EOF
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
    // InternalGrana.g:1360:1: ruleLocalResource returns [EObject current=null] : ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ( (lv_recurse_3_0= 'recurse' ) )? ) ;
    public final EObject ruleLocalResource() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;
        Token otherlv_1=null;
        Token lv_filter_2_0=null;
        Token lv_recurse_3_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1363:28: ( ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ( (lv_recurse_3_0= 'recurse' ) )? ) )
            // InternalGrana.g:1364:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ( (lv_recurse_3_0= 'recurse' ) )? )
            {
            // InternalGrana.g:1364:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ( (lv_recurse_3_0= 'recurse' ) )? )
            // InternalGrana.g:1364:2: ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ( (lv_recurse_3_0= 'recurse' ) )?
            {
            // InternalGrana.g:1364:2: ( (lv_path_0_0= RULE_STRING ) )
            // InternalGrana.g:1365:1: (lv_path_0_0= RULE_STRING )
            {
            // InternalGrana.g:1365:1: (lv_path_0_0= RULE_STRING )
            // InternalGrana.g:1366:3: lv_path_0_0= RULE_STRING
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
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalGrana.g:1382:2: (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            // InternalGrana.g:1382:4: otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) )
            {
            otherlv_1=(Token)match(input,38,FOLLOW_31); 

                	newLeafNode(otherlv_1, grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0());
                
            // InternalGrana.g:1386:1: ( (lv_filter_2_0= RULE_STRING ) )
            // InternalGrana.g:1387:1: (lv_filter_2_0= RULE_STRING )
            {
            // InternalGrana.g:1387:1: (lv_filter_2_0= RULE_STRING )
            // InternalGrana.g:1388:3: lv_filter_2_0= RULE_STRING
            {
            lv_filter_2_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

            			newLeafNode(lv_filter_2_0, grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalResourceRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"filter",
                    		lv_filter_2_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }


            }

            // InternalGrana.g:1404:3: ( (lv_recurse_3_0= 'recurse' ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==39) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalGrana.g:1405:1: (lv_recurse_3_0= 'recurse' )
                    {
                    // InternalGrana.g:1405:1: (lv_recurse_3_0= 'recurse' )
                    // InternalGrana.g:1406:3: lv_recurse_3_0= 'recurse'
                    {
                    lv_recurse_3_0=(Token)match(input,39,FOLLOW_2); 

                            newLeafNode(lv_recurse_3_0, grammarAccess.getLocalResourceAccess().getRecurseRecurseKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getLocalResourceRule());
                    	        }
                           		setWithLastConsumed(current, "recurse", true, "recurse");
                    	    

                    }


                    }
                    break;

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
    // InternalGrana.g:1427:1: entryRuleOutput returns [EObject current=null] : iv_ruleOutput= ruleOutput EOF ;
    public final EObject entryRuleOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutput = null;


        try {
            // InternalGrana.g:1428:2: (iv_ruleOutput= ruleOutput EOF )
            // InternalGrana.g:1429:2: iv_ruleOutput= ruleOutput EOF
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
    // InternalGrana.g:1436:1: ruleOutput returns [EObject current=null] : (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) ;
    public final EObject ruleOutput() throws RecognitionException {
        EObject current = null;

        EObject this_OutputReference_0 = null;

        EObject this_LocalOutput_1 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1439:28: ( (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) )
            // InternalGrana.g:1440:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            {
            // InternalGrana.g:1440:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==37) ) {
                alt35=1;
            }
            else if ( (LA35_0==RULE_STRING) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // InternalGrana.g:1441:5: this_OutputReference_0= ruleOutputReference
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
                    // InternalGrana.g:1451:5: this_LocalOutput_1= ruleLocalOutput
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
    // InternalGrana.g:1467:1: entryRuleGlobalOutputRef returns [EObject current=null] : iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF ;
    public final EObject entryRuleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalOutputRef = null;


        try {
            // InternalGrana.g:1468:2: (iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF )
            // InternalGrana.g:1469:2: iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF
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
    // InternalGrana.g:1476:1: ruleGlobalOutputRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) ;
    public final EObject ruleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_output_1_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1479:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) )
            // InternalGrana.g:1480:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            {
            // InternalGrana.g:1480:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            // InternalGrana.g:1480:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) )
            {
            // InternalGrana.g:1480:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalGrana.g:1481:1: (lv_name_0_0= RULE_ID )
            {
            // InternalGrana.g:1481:1: (lv_name_0_0= RULE_ID )
            // InternalGrana.g:1482:3: lv_name_0_0= RULE_ID
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
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            // InternalGrana.g:1498:2: ( (lv_output_1_0= ruleLocalOutput ) )
            // InternalGrana.g:1499:1: (lv_output_1_0= ruleLocalOutput )
            {
            // InternalGrana.g:1499:1: (lv_output_1_0= ruleLocalOutput )
            // InternalGrana.g:1500:3: lv_output_1_0= ruleLocalOutput
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
                    		"de.cau.cs.kieler.grana.text.Grana.LocalOutput");
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
    // InternalGrana.g:1524:1: entryRuleOutputReference returns [EObject current=null] : iv_ruleOutputReference= ruleOutputReference EOF ;
    public final EObject entryRuleOutputReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputReference = null;


        try {
            // InternalGrana.g:1525:2: (iv_ruleOutputReference= ruleOutputReference EOF )
            // InternalGrana.g:1526:2: iv_ruleOutputReference= ruleOutputReference EOF
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
    // InternalGrana.g:1533:1: ruleOutputReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleOutputReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1536:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) )
            // InternalGrana.g:1537:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalGrana.g:1537:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            // InternalGrana.g:1537:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,37,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getOutputReferenceAccess().getRefKeyword_0());
                
            // InternalGrana.g:1541:1: ( (otherlv_1= RULE_ID ) )
            // InternalGrana.g:1542:1: (otherlv_1= RULE_ID )
            {
            // InternalGrana.g:1542:1: (otherlv_1= RULE_ID )
            // InternalGrana.g:1543:3: otherlv_1= RULE_ID
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
    // InternalGrana.g:1562:1: entryRuleLocalOutput returns [EObject current=null] : iv_ruleLocalOutput= ruleLocalOutput EOF ;
    public final EObject entryRuleLocalOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalOutput = null;


        try {
            // InternalGrana.g:1563:2: (iv_ruleLocalOutput= ruleLocalOutput EOF )
            // InternalGrana.g:1564:2: iv_ruleLocalOutput= ruleLocalOutput EOF
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
    // InternalGrana.g:1571:1: ruleLocalOutput returns [EObject current=null] : ( (lv_path_0_0= RULE_STRING ) ) ;
    public final EObject ruleLocalOutput() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:1574:28: ( ( (lv_path_0_0= RULE_STRING ) ) )
            // InternalGrana.g:1575:1: ( (lv_path_0_0= RULE_STRING ) )
            {
            // InternalGrana.g:1575:1: ( (lv_path_0_0= RULE_STRING ) )
            // InternalGrana.g:1576:1: (lv_path_0_0= RULE_STRING )
            {
            // InternalGrana.g:1576:1: (lv_path_0_0= RULE_STRING )
            // InternalGrana.g:1577:3: lv_path_0_0= RULE_STRING
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
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

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
    // InternalGrana.g:1601:1: entryRuleAnalysis returns [EObject current=null] : iv_ruleAnalysis= ruleAnalysis EOF ;
    public final EObject entryRuleAnalysis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysis = null;


        try {
            // InternalGrana.g:1602:2: (iv_ruleAnalysis= ruleAnalysis EOF )
            // InternalGrana.g:1603:2: iv_ruleAnalysis= ruleAnalysis EOF
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
    // InternalGrana.g:1610:1: ruleAnalysis returns [EObject current=null] : ( (lv_name_0_0= ruleQualifiedId ) ) ;
    public final EObject ruleAnalysis() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1613:28: ( ( (lv_name_0_0= ruleQualifiedId ) ) )
            // InternalGrana.g:1614:1: ( (lv_name_0_0= ruleQualifiedId ) )
            {
            // InternalGrana.g:1614:1: ( (lv_name_0_0= ruleQualifiedId ) )
            // InternalGrana.g:1615:1: (lv_name_0_0= ruleQualifiedId )
            {
            // InternalGrana.g:1615:1: (lv_name_0_0= ruleQualifiedId )
            // InternalGrana.g:1616:3: lv_name_0_0= ruleQualifiedId
            {
             
            	        newCompositeNode(grammarAccess.getAnalysisAccess().getNameQualifiedIdParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_name_0_0=ruleQualifiedId();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getAnalysisRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.QualifiedId");
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


    // $ANTLR start "entryRuleLayoutConfig"
    // InternalGrana.g:1640:1: entryRuleLayoutConfig returns [EObject current=null] : iv_ruleLayoutConfig= ruleLayoutConfig EOF ;
    public final EObject entryRuleLayoutConfig() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLayoutConfig = null;


        try {
            // InternalGrana.g:1641:2: (iv_ruleLayoutConfig= ruleLayoutConfig EOF )
            // InternalGrana.g:1642:2: iv_ruleLayoutConfig= ruleLayoutConfig EOF
            {
             newCompositeNode(grammarAccess.getLayoutConfigRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLayoutConfig=ruleLayoutConfig();

            state._fsp--;

             current =iv_ruleLayoutConfig; 
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
    // $ANTLR end "entryRuleLayoutConfig"


    // $ANTLR start "ruleLayoutConfig"
    // InternalGrana.g:1649:1: ruleLayoutConfig returns [EObject current=null] : ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}' ) ;
    public final EObject ruleLayoutConfig() throws RecognitionException {
        EObject current = null;

        Token lv_identifier_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_properties_2_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1652:28: ( ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}' ) )
            // InternalGrana.g:1653:1: ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}' )
            {
            // InternalGrana.g:1653:1: ( ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}' )
            // InternalGrana.g:1653:2: ( (lv_identifier_0_0= RULE_ID ) ) otherlv_1= '{' ( (lv_properties_2_0= ruleProperty ) )* otherlv_3= '}'
            {
            // InternalGrana.g:1653:2: ( (lv_identifier_0_0= RULE_ID ) )
            // InternalGrana.g:1654:1: (lv_identifier_0_0= RULE_ID )
            {
            // InternalGrana.g:1654:1: (lv_identifier_0_0= RULE_ID )
            // InternalGrana.g:1655:3: lv_identifier_0_0= RULE_ID
            {
            lv_identifier_0_0=(Token)match(input,RULE_ID,FOLLOW_33); 

            			newLeafNode(lv_identifier_0_0, grammarAccess.getLayoutConfigAccess().getIdentifierIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLayoutConfigRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"identifier",
                    		lv_identifier_0_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            otherlv_1=(Token)match(input,40,FOLLOW_34); 

                	newLeafNode(otherlv_1, grammarAccess.getLayoutConfigAccess().getLeftCurlyBracketKeyword_1());
                
            // InternalGrana.g:1675:1: ( (lv_properties_2_0= ruleProperty ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_ID) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalGrana.g:1676:1: (lv_properties_2_0= ruleProperty )
            	    {
            	    // InternalGrana.g:1676:1: (lv_properties_2_0= ruleProperty )
            	    // InternalGrana.g:1677:3: lv_properties_2_0= ruleProperty
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getLayoutConfigAccess().getPropertiesPropertyParserRuleCall_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_34);
            	    lv_properties_2_0=ruleProperty();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getLayoutConfigRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"properties",
            	            		lv_properties_2_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Property");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            otherlv_3=(Token)match(input,41,FOLLOW_2); 

                	newLeafNode(otherlv_3, grammarAccess.getLayoutConfigAccess().getRightCurlyBracketKeyword_3());
                

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
    // $ANTLR end "ruleLayoutConfig"


    // $ANTLR start "entryRuleElkNode"
    // InternalGrana.g:1707:1: entryRuleElkNode returns [EObject current=null] : iv_ruleElkNode= ruleElkNode EOF ;
    public final EObject entryRuleElkNode() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkNode = null;


        try {
            // InternalGrana.g:1708:2: (iv_ruleElkNode= ruleElkNode EOF )
            // InternalGrana.g:1709:2: iv_ruleElkNode= ruleElkNode EOF
            {
             newCompositeNode(grammarAccess.getElkNodeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkNode=ruleElkNode();

            state._fsp--;

             current =iv_ruleElkNode; 
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
    // $ANTLR end "entryRuleElkNode"


    // $ANTLR start "ruleElkNode"
    // InternalGrana.g:1716:1: ruleElkNode returns [EObject current=null] : (otherlv_0= 'node' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( ( (lv_labels_5_0= ruleElkLabel ) ) | ( (lv_ports_6_0= ruleElkPort ) ) | ( (lv_children_7_0= ruleElkNode ) ) | ( (lv_containedEdges_8_0= ruleElkEdge ) ) )* otherlv_9= '}' )? ) ;
    public final EObject ruleElkNode() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_identifier_1_0=null;
        Token otherlv_2=null;
        Token otherlv_9=null;
        EObject this_ShapeLayout_3 = null;

        EObject lv_properties_4_0 = null;

        EObject lv_labels_5_0 = null;

        EObject lv_ports_6_0 = null;

        EObject lv_children_7_0 = null;

        EObject lv_containedEdges_8_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1719:28: ( (otherlv_0= 'node' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( ( (lv_labels_5_0= ruleElkLabel ) ) | ( (lv_ports_6_0= ruleElkPort ) ) | ( (lv_children_7_0= ruleElkNode ) ) | ( (lv_containedEdges_8_0= ruleElkEdge ) ) )* otherlv_9= '}' )? ) )
            // InternalGrana.g:1720:1: (otherlv_0= 'node' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( ( (lv_labels_5_0= ruleElkLabel ) ) | ( (lv_ports_6_0= ruleElkPort ) ) | ( (lv_children_7_0= ruleElkNode ) ) | ( (lv_containedEdges_8_0= ruleElkEdge ) ) )* otherlv_9= '}' )? )
            {
            // InternalGrana.g:1720:1: (otherlv_0= 'node' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( ( (lv_labels_5_0= ruleElkLabel ) ) | ( (lv_ports_6_0= ruleElkPort ) ) | ( (lv_children_7_0= ruleElkNode ) ) | ( (lv_containedEdges_8_0= ruleElkEdge ) ) )* otherlv_9= '}' )? )
            // InternalGrana.g:1720:3: otherlv_0= 'node' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( ( (lv_labels_5_0= ruleElkLabel ) ) | ( (lv_ports_6_0= ruleElkPort ) ) | ( (lv_children_7_0= ruleElkNode ) ) | ( (lv_containedEdges_8_0= ruleElkEdge ) ) )* otherlv_9= '}' )?
            {
            otherlv_0=(Token)match(input,42,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getElkNodeAccess().getNodeKeyword_0());
                
            // InternalGrana.g:1724:1: ( (lv_identifier_1_0= RULE_ID ) )
            // InternalGrana.g:1725:1: (lv_identifier_1_0= RULE_ID )
            {
            // InternalGrana.g:1725:1: (lv_identifier_1_0= RULE_ID )
            // InternalGrana.g:1726:3: lv_identifier_1_0= RULE_ID
            {
            lv_identifier_1_0=(Token)match(input,RULE_ID,FOLLOW_35); 

            			newLeafNode(lv_identifier_1_0, grammarAccess.getElkNodeAccess().getIdentifierIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getElkNodeRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"identifier",
                    		lv_identifier_1_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            // InternalGrana.g:1742:2: (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( ( (lv_labels_5_0= ruleElkLabel ) ) | ( (lv_ports_6_0= ruleElkPort ) ) | ( (lv_children_7_0= ruleElkNode ) ) | ( (lv_containedEdges_8_0= ruleElkEdge ) ) )* otherlv_9= '}' )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==40) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalGrana.g:1742:4: otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( ( (lv_labels_5_0= ruleElkLabel ) ) | ( (lv_ports_6_0= ruleElkPort ) ) | ( (lv_children_7_0= ruleElkNode ) ) | ( (lv_containedEdges_8_0= ruleElkEdge ) ) )* otherlv_9= '}'
                    {
                    otherlv_2=(Token)match(input,40,FOLLOW_36); 

                        	newLeafNode(otherlv_2, grammarAccess.getElkNodeAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // InternalGrana.g:1746:1: (this_ShapeLayout_3= ruleShapeLayout[$current] )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==46) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // InternalGrana.g:1747:5: this_ShapeLayout_3= ruleShapeLayout[$current]
                            {
                             
                            		if (current==null) {
                            			current = createModelElement(grammarAccess.getElkNodeRule());
                            		}
                                    newCompositeNode(grammarAccess.getElkNodeAccess().getShapeLayoutParserRuleCall_2_1()); 
                                
                            pushFollow(FOLLOW_37);
                            this_ShapeLayout_3=ruleShapeLayout(current);

                            state._fsp--;

                             
                                    current = this_ShapeLayout_3; 
                                    afterParserOrEnumRuleCall();
                                

                            }
                            break;

                    }

                    // InternalGrana.g:1758:3: ( (lv_properties_4_0= ruleProperty ) )*
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==RULE_ID) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // InternalGrana.g:1759:1: (lv_properties_4_0= ruleProperty )
                    	    {
                    	    // InternalGrana.g:1759:1: (lv_properties_4_0= ruleProperty )
                    	    // InternalGrana.g:1760:3: lv_properties_4_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkNodeAccess().getPropertiesPropertyParserRuleCall_2_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_37);
                    	    lv_properties_4_0=ruleProperty();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"properties",
                    	            		lv_properties_4_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.Property");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop38;
                        }
                    } while (true);

                    // InternalGrana.g:1776:3: ( ( (lv_labels_5_0= ruleElkLabel ) ) | ( (lv_ports_6_0= ruleElkPort ) ) | ( (lv_children_7_0= ruleElkNode ) ) | ( (lv_containedEdges_8_0= ruleElkEdge ) ) )*
                    loop39:
                    do {
                        int alt39=5;
                        switch ( input.LA(1) ) {
                        case 43:
                            {
                            alt39=1;
                            }
                            break;
                        case 45:
                            {
                            alt39=2;
                            }
                            break;
                        case 42:
                            {
                            alt39=3;
                            }
                            break;
                        case 51:
                            {
                            alt39=4;
                            }
                            break;

                        }

                        switch (alt39) {
                    	case 1 :
                    	    // InternalGrana.g:1776:4: ( (lv_labels_5_0= ruleElkLabel ) )
                    	    {
                    	    // InternalGrana.g:1776:4: ( (lv_labels_5_0= ruleElkLabel ) )
                    	    // InternalGrana.g:1777:1: (lv_labels_5_0= ruleElkLabel )
                    	    {
                    	    // InternalGrana.g:1777:1: (lv_labels_5_0= ruleElkLabel )
                    	    // InternalGrana.g:1778:3: lv_labels_5_0= ruleElkLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkNodeAccess().getLabelsElkLabelParserRuleCall_2_3_0_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_38);
                    	    lv_labels_5_0=ruleElkLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_5_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalGrana.g:1795:6: ( (lv_ports_6_0= ruleElkPort ) )
                    	    {
                    	    // InternalGrana.g:1795:6: ( (lv_ports_6_0= ruleElkPort ) )
                    	    // InternalGrana.g:1796:1: (lv_ports_6_0= ruleElkPort )
                    	    {
                    	    // InternalGrana.g:1796:1: (lv_ports_6_0= ruleElkPort )
                    	    // InternalGrana.g:1797:3: lv_ports_6_0= ruleElkPort
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkNodeAccess().getPortsElkPortParserRuleCall_2_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_38);
                    	    lv_ports_6_0=ruleElkPort();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"ports",
                    	            		lv_ports_6_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkPort");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalGrana.g:1814:6: ( (lv_children_7_0= ruleElkNode ) )
                    	    {
                    	    // InternalGrana.g:1814:6: ( (lv_children_7_0= ruleElkNode ) )
                    	    // InternalGrana.g:1815:1: (lv_children_7_0= ruleElkNode )
                    	    {
                    	    // InternalGrana.g:1815:1: (lv_children_7_0= ruleElkNode )
                    	    // InternalGrana.g:1816:3: lv_children_7_0= ruleElkNode
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkNodeAccess().getChildrenElkNodeParserRuleCall_2_3_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_38);
                    	    lv_children_7_0=ruleElkNode();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"children",
                    	            		lv_children_7_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkNode");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // InternalGrana.g:1833:6: ( (lv_containedEdges_8_0= ruleElkEdge ) )
                    	    {
                    	    // InternalGrana.g:1833:6: ( (lv_containedEdges_8_0= ruleElkEdge ) )
                    	    // InternalGrana.g:1834:1: (lv_containedEdges_8_0= ruleElkEdge )
                    	    {
                    	    // InternalGrana.g:1834:1: (lv_containedEdges_8_0= ruleElkEdge )
                    	    // InternalGrana.g:1835:3: lv_containedEdges_8_0= ruleElkEdge
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkNodeAccess().getContainedEdgesElkEdgeParserRuleCall_2_3_3_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_38);
                    	    lv_containedEdges_8_0=ruleElkEdge();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkNodeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"containedEdges",
                    	            		lv_containedEdges_8_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkEdge");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,41,FOLLOW_2); 

                        	newLeafNode(otherlv_9, grammarAccess.getElkNodeAccess().getRightCurlyBracketKeyword_2_4());
                        

                    }
                    break;

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
    // $ANTLR end "ruleElkNode"


    // $ANTLR start "entryRuleElkLabel"
    // InternalGrana.g:1863:1: entryRuleElkLabel returns [EObject current=null] : iv_ruleElkLabel= ruleElkLabel EOF ;
    public final EObject entryRuleElkLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkLabel = null;


        try {
            // InternalGrana.g:1864:2: (iv_ruleElkLabel= ruleElkLabel EOF )
            // InternalGrana.g:1865:2: iv_ruleElkLabel= ruleElkLabel EOF
            {
             newCompositeNode(grammarAccess.getElkLabelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkLabel=ruleElkLabel();

            state._fsp--;

             current =iv_ruleElkLabel; 
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
    // $ANTLR end "entryRuleElkLabel"


    // $ANTLR start "ruleElkLabel"
    // InternalGrana.g:1872:1: ruleElkLabel returns [EObject current=null] : (otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )? ) ;
    public final EObject ruleElkLabel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_identifier_1_0=null;
        Token otherlv_2=null;
        Token lv_text_3_0=null;
        Token otherlv_4=null;
        Token otherlv_8=null;
        EObject this_ShapeLayout_5 = null;

        EObject lv_properties_6_0 = null;

        EObject lv_labels_7_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1875:28: ( (otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )? ) )
            // InternalGrana.g:1876:1: (otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )? )
            {
            // InternalGrana.g:1876:1: (otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )? )
            // InternalGrana.g:1876:3: otherlv_0= 'label' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( (lv_text_3_0= RULE_STRING ) ) (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )?
            {
            otherlv_0=(Token)match(input,43,FOLLOW_39); 

                	newLeafNode(otherlv_0, grammarAccess.getElkLabelAccess().getLabelKeyword_0());
                
            // InternalGrana.g:1880:1: ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==RULE_ID) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalGrana.g:1880:2: ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':'
                    {
                    // InternalGrana.g:1880:2: ( (lv_identifier_1_0= RULE_ID ) )
                    // InternalGrana.g:1881:1: (lv_identifier_1_0= RULE_ID )
                    {
                    // InternalGrana.g:1881:1: (lv_identifier_1_0= RULE_ID )
                    // InternalGrana.g:1882:3: lv_identifier_1_0= RULE_ID
                    {
                    lv_identifier_1_0=(Token)match(input,RULE_ID,FOLLOW_40); 

                    			newLeafNode(lv_identifier_1_0, grammarAccess.getElkLabelAccess().getIdentifierIDTerminalRuleCall_1_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getElkLabelRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"identifier",
                            		lv_identifier_1_0, 
                            		"org.eclipse.xtext.common.Terminals.ID");
                    	    

                    }


                    }

                    otherlv_2=(Token)match(input,44,FOLLOW_31); 

                        	newLeafNode(otherlv_2, grammarAccess.getElkLabelAccess().getColonKeyword_1_1());
                        

                    }
                    break;

            }

            // InternalGrana.g:1902:3: ( (lv_text_3_0= RULE_STRING ) )
            // InternalGrana.g:1903:1: (lv_text_3_0= RULE_STRING )
            {
            // InternalGrana.g:1903:1: (lv_text_3_0= RULE_STRING )
            // InternalGrana.g:1904:3: lv_text_3_0= RULE_STRING
            {
            lv_text_3_0=(Token)match(input,RULE_STRING,FOLLOW_35); 

            			newLeafNode(lv_text_3_0, grammarAccess.getElkLabelAccess().getTextSTRINGTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getElkLabelRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"text",
                    		lv_text_3_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalGrana.g:1920:2: (otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==40) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalGrana.g:1920:4: otherlv_4= '{' (this_ShapeLayout_5= ruleShapeLayout[$current] )? ( (lv_properties_6_0= ruleProperty ) )* ( (lv_labels_7_0= ruleElkLabel ) )* otherlv_8= '}'
                    {
                    otherlv_4=(Token)match(input,40,FOLLOW_41); 

                        	newLeafNode(otherlv_4, grammarAccess.getElkLabelAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // InternalGrana.g:1924:1: (this_ShapeLayout_5= ruleShapeLayout[$current] )?
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==46) ) {
                        alt42=1;
                    }
                    switch (alt42) {
                        case 1 :
                            // InternalGrana.g:1925:5: this_ShapeLayout_5= ruleShapeLayout[$current]
                            {
                             
                            		if (current==null) {
                            			current = createModelElement(grammarAccess.getElkLabelRule());
                            		}
                                    newCompositeNode(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 
                                
                            pushFollow(FOLLOW_42);
                            this_ShapeLayout_5=ruleShapeLayout(current);

                            state._fsp--;

                             
                                    current = this_ShapeLayout_5; 
                                    afterParserOrEnumRuleCall();
                                

                            }
                            break;

                    }

                    // InternalGrana.g:1936:3: ( (lv_properties_6_0= ruleProperty ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==RULE_ID) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // InternalGrana.g:1937:1: (lv_properties_6_0= ruleProperty )
                    	    {
                    	    // InternalGrana.g:1937:1: (lv_properties_6_0= ruleProperty )
                    	    // InternalGrana.g:1938:3: lv_properties_6_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkLabelAccess().getPropertiesPropertyParserRuleCall_3_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_42);
                    	    lv_properties_6_0=ruleProperty();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkLabelRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"properties",
                    	            		lv_properties_6_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.Property");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);

                    // InternalGrana.g:1954:3: ( (lv_labels_7_0= ruleElkLabel ) )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==43) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // InternalGrana.g:1955:1: (lv_labels_7_0= ruleElkLabel )
                    	    {
                    	    // InternalGrana.g:1955:1: (lv_labels_7_0= ruleElkLabel )
                    	    // InternalGrana.g:1956:3: lv_labels_7_0= ruleElkLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkLabelAccess().getLabelsElkLabelParserRuleCall_3_3_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_43);
                    	    lv_labels_7_0=ruleElkLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkLabelRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_7_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop44;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,41,FOLLOW_2); 

                        	newLeafNode(otherlv_8, grammarAccess.getElkLabelAccess().getRightCurlyBracketKeyword_3_4());
                        

                    }
                    break;

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
    // $ANTLR end "ruleElkLabel"


    // $ANTLR start "entryRuleElkPort"
    // InternalGrana.g:1984:1: entryRuleElkPort returns [EObject current=null] : iv_ruleElkPort= ruleElkPort EOF ;
    public final EObject entryRuleElkPort() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkPort = null;


        try {
            // InternalGrana.g:1985:2: (iv_ruleElkPort= ruleElkPort EOF )
            // InternalGrana.g:1986:2: iv_ruleElkPort= ruleElkPort EOF
            {
             newCompositeNode(grammarAccess.getElkPortRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkPort=ruleElkPort();

            state._fsp--;

             current =iv_ruleElkPort; 
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
    // $ANTLR end "entryRuleElkPort"


    // $ANTLR start "ruleElkPort"
    // InternalGrana.g:1993:1: ruleElkPort returns [EObject current=null] : (otherlv_0= 'port' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( (lv_labels_5_0= ruleElkLabel ) )* otherlv_6= '}' )? ) ;
    public final EObject ruleElkPort() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_identifier_1_0=null;
        Token otherlv_2=null;
        Token otherlv_6=null;
        EObject this_ShapeLayout_3 = null;

        EObject lv_properties_4_0 = null;

        EObject lv_labels_5_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:1996:28: ( (otherlv_0= 'port' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( (lv_labels_5_0= ruleElkLabel ) )* otherlv_6= '}' )? ) )
            // InternalGrana.g:1997:1: (otherlv_0= 'port' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( (lv_labels_5_0= ruleElkLabel ) )* otherlv_6= '}' )? )
            {
            // InternalGrana.g:1997:1: (otherlv_0= 'port' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( (lv_labels_5_0= ruleElkLabel ) )* otherlv_6= '}' )? )
            // InternalGrana.g:1997:3: otherlv_0= 'port' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( (lv_labels_5_0= ruleElkLabel ) )* otherlv_6= '}' )?
            {
            otherlv_0=(Token)match(input,45,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getElkPortAccess().getPortKeyword_0());
                
            // InternalGrana.g:2001:1: ( (lv_identifier_1_0= RULE_ID ) )
            // InternalGrana.g:2002:1: (lv_identifier_1_0= RULE_ID )
            {
            // InternalGrana.g:2002:1: (lv_identifier_1_0= RULE_ID )
            // InternalGrana.g:2003:3: lv_identifier_1_0= RULE_ID
            {
            lv_identifier_1_0=(Token)match(input,RULE_ID,FOLLOW_35); 

            			newLeafNode(lv_identifier_1_0, grammarAccess.getElkPortAccess().getIdentifierIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getElkPortRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"identifier",
                    		lv_identifier_1_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            // InternalGrana.g:2019:2: (otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( (lv_labels_5_0= ruleElkLabel ) )* otherlv_6= '}' )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==40) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalGrana.g:2019:4: otherlv_2= '{' (this_ShapeLayout_3= ruleShapeLayout[$current] )? ( (lv_properties_4_0= ruleProperty ) )* ( (lv_labels_5_0= ruleElkLabel ) )* otherlv_6= '}'
                    {
                    otherlv_2=(Token)match(input,40,FOLLOW_41); 

                        	newLeafNode(otherlv_2, grammarAccess.getElkPortAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // InternalGrana.g:2023:1: (this_ShapeLayout_3= ruleShapeLayout[$current] )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==46) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // InternalGrana.g:2024:5: this_ShapeLayout_3= ruleShapeLayout[$current]
                            {
                             
                            		if (current==null) {
                            			current = createModelElement(grammarAccess.getElkPortRule());
                            		}
                                    newCompositeNode(grammarAccess.getElkPortAccess().getShapeLayoutParserRuleCall_2_1()); 
                                
                            pushFollow(FOLLOW_42);
                            this_ShapeLayout_3=ruleShapeLayout(current);

                            state._fsp--;

                             
                                    current = this_ShapeLayout_3; 
                                    afterParserOrEnumRuleCall();
                                

                            }
                            break;

                    }

                    // InternalGrana.g:2035:3: ( (lv_properties_4_0= ruleProperty ) )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==RULE_ID) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // InternalGrana.g:2036:1: (lv_properties_4_0= ruleProperty )
                    	    {
                    	    // InternalGrana.g:2036:1: (lv_properties_4_0= ruleProperty )
                    	    // InternalGrana.g:2037:3: lv_properties_4_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkPortAccess().getPropertiesPropertyParserRuleCall_2_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_42);
                    	    lv_properties_4_0=ruleProperty();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkPortRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"properties",
                    	            		lv_properties_4_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.Property");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop47;
                        }
                    } while (true);

                    // InternalGrana.g:2053:3: ( (lv_labels_5_0= ruleElkLabel ) )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==43) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // InternalGrana.g:2054:1: (lv_labels_5_0= ruleElkLabel )
                    	    {
                    	    // InternalGrana.g:2054:1: (lv_labels_5_0= ruleElkLabel )
                    	    // InternalGrana.g:2055:3: lv_labels_5_0= ruleElkLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkPortAccess().getLabelsElkLabelParserRuleCall_2_3_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_43);
                    	    lv_labels_5_0=ruleElkLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkPortRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_5_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop48;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,41,FOLLOW_2); 

                        	newLeafNode(otherlv_6, grammarAccess.getElkPortAccess().getRightCurlyBracketKeyword_2_4());
                        

                    }
                    break;

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
    // $ANTLR end "ruleElkPort"


    // $ANTLR start "ruleShapeLayout"
    // InternalGrana.g:2084:1: ruleShapeLayout[EObject in_current] returns [EObject current=in_current] : (otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']' ) ;
    public final EObject ruleShapeLayout(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_x_5_0 = null;

        AntlrDatatypeRuleToken lv_y_7_0 = null;

        AntlrDatatypeRuleToken lv_width_10_0 = null;

        AntlrDatatypeRuleToken lv_height_12_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:2087:28: ( (otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']' ) )
            // InternalGrana.g:2088:1: (otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']' )
            {
            // InternalGrana.g:2088:1: (otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']' )
            // InternalGrana.g:2088:3: otherlv_0= 'layout' otherlv_1= '[' ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) ) otherlv_13= ']'
            {
            otherlv_0=(Token)match(input,46,FOLLOW_44); 

                	newLeafNode(otherlv_0, grammarAccess.getShapeLayoutAccess().getLayoutKeyword_0());
                
            otherlv_1=(Token)match(input,47,FOLLOW_45); 

                	newLeafNode(otherlv_1, grammarAccess.getShapeLayoutAccess().getLeftSquareBracketKeyword_1());
                
            // InternalGrana.g:2096:1: ( ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) ) )
            // InternalGrana.g:2098:1: ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) )
            {
            // InternalGrana.g:2098:1: ( ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* ) )
            // InternalGrana.g:2099:2: ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	
            // InternalGrana.g:2102:2: ( ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )* )
            // InternalGrana.g:2103:3: ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )*
            {
            // InternalGrana.g:2103:3: ( ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) ) )*
            loop50:
            do {
                int alt50=3;
                int LA50_0 = input.LA(1);

                if ( LA50_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                    alt50=1;
                }
                else if ( LA50_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                    alt50=2;
                }


                switch (alt50) {
            	case 1 :
            	    // InternalGrana.g:2105:4: ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2105:4: ({...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) ) )
            	    // InternalGrana.g:2106:5: {...}? => ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
            	    }
            	    // InternalGrana.g:2106:108: ( ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) ) )
            	    // InternalGrana.g:2107:6: ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
            	    	 				
            	    // InternalGrana.g:2110:6: ({...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) ) )
            	    // InternalGrana.g:2110:7: {...}? => (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleShapeLayout", "true");
            	    }
            	    // InternalGrana.g:2110:16: (otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) ) )
            	    // InternalGrana.g:2110:18: otherlv_3= 'position' otherlv_4= ':' ( (lv_x_5_0= ruleNumber ) ) otherlv_6= ',' ( (lv_y_7_0= ruleNumber ) )
            	    {
            	    otherlv_3=(Token)match(input,48,FOLLOW_40); 

            	        	newLeafNode(otherlv_3, grammarAccess.getShapeLayoutAccess().getPositionKeyword_2_0_0());
            	        
            	    otherlv_4=(Token)match(input,44,FOLLOW_46); 

            	        	newLeafNode(otherlv_4, grammarAccess.getShapeLayoutAccess().getColonKeyword_2_0_1());
            	        
            	    // InternalGrana.g:2118:1: ( (lv_x_5_0= ruleNumber ) )
            	    // InternalGrana.g:2119:1: (lv_x_5_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2119:1: (lv_x_5_0= ruleNumber )
            	    // InternalGrana.g:2120:3: lv_x_5_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getShapeLayoutAccess().getXNumberParserRuleCall_2_0_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_47);
            	    lv_x_5_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getShapeLayoutRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"x",
            	            		lv_x_5_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_6=(Token)match(input,32,FOLLOW_46); 

            	        	newLeafNode(otherlv_6, grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_0_3());
            	        
            	    // InternalGrana.g:2140:1: ( (lv_y_7_0= ruleNumber ) )
            	    // InternalGrana.g:2141:1: (lv_y_7_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2141:1: (lv_y_7_0= ruleNumber )
            	    // InternalGrana.g:2142:3: lv_y_7_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getShapeLayoutAccess().getYNumberParserRuleCall_2_0_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_45);
            	    lv_y_7_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getShapeLayoutRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"y",
            	            		lv_y_7_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalGrana.g:2165:4: ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2165:4: ({...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) ) )
            	    // InternalGrana.g:2166:5: {...}? => ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleShapeLayout", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
            	    }
            	    // InternalGrana.g:2166:108: ( ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) ) )
            	    // InternalGrana.g:2167:6: ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
            	    	 				
            	    // InternalGrana.g:2170:6: ({...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) ) )
            	    // InternalGrana.g:2170:7: {...}? => (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleShapeLayout", "true");
            	    }
            	    // InternalGrana.g:2170:16: (otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) ) )
            	    // InternalGrana.g:2170:18: otherlv_8= 'size' otherlv_9= ':' ( (lv_width_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_height_12_0= ruleNumber ) )
            	    {
            	    otherlv_8=(Token)match(input,49,FOLLOW_40); 

            	        	newLeafNode(otherlv_8, grammarAccess.getShapeLayoutAccess().getSizeKeyword_2_1_0());
            	        
            	    otherlv_9=(Token)match(input,44,FOLLOW_46); 

            	        	newLeafNode(otherlv_9, grammarAccess.getShapeLayoutAccess().getColonKeyword_2_1_1());
            	        
            	    // InternalGrana.g:2178:1: ( (lv_width_10_0= ruleNumber ) )
            	    // InternalGrana.g:2179:1: (lv_width_10_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2179:1: (lv_width_10_0= ruleNumber )
            	    // InternalGrana.g:2180:3: lv_width_10_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getShapeLayoutAccess().getWidthNumberParserRuleCall_2_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_47);
            	    lv_width_10_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getShapeLayoutRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"width",
            	            		lv_width_10_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_11=(Token)match(input,32,FOLLOW_46); 

            	        	newLeafNode(otherlv_11, grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_1_3());
            	        
            	    // InternalGrana.g:2200:1: ( (lv_height_12_0= ruleNumber ) )
            	    // InternalGrana.g:2201:1: (lv_height_12_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2201:1: (lv_height_12_0= ruleNumber )
            	    // InternalGrana.g:2202:3: lv_height_12_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getShapeLayoutAccess().getHeightNumberParserRuleCall_2_1_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_45);
            	    lv_height_12_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getShapeLayoutRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"height",
            	            		lv_height_12_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	    	 				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            }


            }

             
            	  getUnorderedGroupHelper().leave(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	

            }

            otherlv_13=(Token)match(input,50,FOLLOW_2); 

                	newLeafNode(otherlv_13, grammarAccess.getShapeLayoutAccess().getRightSquareBracketKeyword_3());
                

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
    // $ANTLR end "ruleShapeLayout"


    // $ANTLR start "entryRuleElkEdge"
    // InternalGrana.g:2244:1: entryRuleElkEdge returns [EObject current=null] : iv_ruleElkEdge= ruleElkEdge EOF ;
    public final EObject entryRuleElkEdge() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkEdge = null;


        try {
            // InternalGrana.g:2245:2: (iv_ruleElkEdge= ruleElkEdge EOF )
            // InternalGrana.g:2246:2: iv_ruleElkEdge= ruleElkEdge EOF
            {
             newCompositeNode(grammarAccess.getElkEdgeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkEdge=ruleElkEdge();

            state._fsp--;

             current =iv_ruleElkEdge; 
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
    // $ANTLR end "entryRuleElkEdge"


    // $ANTLR start "ruleElkEdge"
    // InternalGrana.g:2253:1: ruleElkEdge returns [EObject current=null] : (otherlv_0= 'edge' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( ( ruleQualifiedId ) ) (otherlv_4= ',' ( ( ruleQualifiedId ) ) )* otherlv_6= '->' ( ( ruleQualifiedId ) ) (otherlv_8= ',' ( ( ruleQualifiedId ) ) )* (otherlv_10= '{' (this_EdgeLayout_11= ruleEdgeLayout[$current] )? ( (lv_properties_12_0= ruleProperty ) )* ( (lv_labels_13_0= ruleElkLabel ) )* otherlv_14= '}' )? ) ;
    public final EObject ruleElkEdge() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_identifier_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_14=null;
        EObject this_EdgeLayout_11 = null;

        EObject lv_properties_12_0 = null;

        EObject lv_labels_13_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:2256:28: ( (otherlv_0= 'edge' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( ( ruleQualifiedId ) ) (otherlv_4= ',' ( ( ruleQualifiedId ) ) )* otherlv_6= '->' ( ( ruleQualifiedId ) ) (otherlv_8= ',' ( ( ruleQualifiedId ) ) )* (otherlv_10= '{' (this_EdgeLayout_11= ruleEdgeLayout[$current] )? ( (lv_properties_12_0= ruleProperty ) )* ( (lv_labels_13_0= ruleElkLabel ) )* otherlv_14= '}' )? ) )
            // InternalGrana.g:2257:1: (otherlv_0= 'edge' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( ( ruleQualifiedId ) ) (otherlv_4= ',' ( ( ruleQualifiedId ) ) )* otherlv_6= '->' ( ( ruleQualifiedId ) ) (otherlv_8= ',' ( ( ruleQualifiedId ) ) )* (otherlv_10= '{' (this_EdgeLayout_11= ruleEdgeLayout[$current] )? ( (lv_properties_12_0= ruleProperty ) )* ( (lv_labels_13_0= ruleElkLabel ) )* otherlv_14= '}' )? )
            {
            // InternalGrana.g:2257:1: (otherlv_0= 'edge' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( ( ruleQualifiedId ) ) (otherlv_4= ',' ( ( ruleQualifiedId ) ) )* otherlv_6= '->' ( ( ruleQualifiedId ) ) (otherlv_8= ',' ( ( ruleQualifiedId ) ) )* (otherlv_10= '{' (this_EdgeLayout_11= ruleEdgeLayout[$current] )? ( (lv_properties_12_0= ruleProperty ) )* ( (lv_labels_13_0= ruleElkLabel ) )* otherlv_14= '}' )? )
            // InternalGrana.g:2257:3: otherlv_0= 'edge' ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )? ( ( ruleQualifiedId ) ) (otherlv_4= ',' ( ( ruleQualifiedId ) ) )* otherlv_6= '->' ( ( ruleQualifiedId ) ) (otherlv_8= ',' ( ( ruleQualifiedId ) ) )* (otherlv_10= '{' (this_EdgeLayout_11= ruleEdgeLayout[$current] )? ( (lv_properties_12_0= ruleProperty ) )* ( (lv_labels_13_0= ruleElkLabel ) )* otherlv_14= '}' )?
            {
            otherlv_0=(Token)match(input,51,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getElkEdgeAccess().getEdgeKeyword_0());
                
            // InternalGrana.g:2261:1: ( ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':' )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==RULE_ID) ) {
                int LA51_1 = input.LA(2);

                if ( (LA51_1==44) ) {
                    alt51=1;
                }
            }
            switch (alt51) {
                case 1 :
                    // InternalGrana.g:2261:2: ( (lv_identifier_1_0= RULE_ID ) ) otherlv_2= ':'
                    {
                    // InternalGrana.g:2261:2: ( (lv_identifier_1_0= RULE_ID ) )
                    // InternalGrana.g:2262:1: (lv_identifier_1_0= RULE_ID )
                    {
                    // InternalGrana.g:2262:1: (lv_identifier_1_0= RULE_ID )
                    // InternalGrana.g:2263:3: lv_identifier_1_0= RULE_ID
                    {
                    lv_identifier_1_0=(Token)match(input,RULE_ID,FOLLOW_40); 

                    			newLeafNode(lv_identifier_1_0, grammarAccess.getElkEdgeAccess().getIdentifierIDTerminalRuleCall_1_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getElkEdgeRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"identifier",
                            		lv_identifier_1_0, 
                            		"org.eclipse.xtext.common.Terminals.ID");
                    	    

                    }


                    }

                    otherlv_2=(Token)match(input,44,FOLLOW_10); 

                        	newLeafNode(otherlv_2, grammarAccess.getElkEdgeAccess().getColonKeyword_1_1());
                        

                    }
                    break;

            }

            // InternalGrana.g:2283:3: ( ( ruleQualifiedId ) )
            // InternalGrana.g:2284:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:2284:1: ( ruleQualifiedId )
            // InternalGrana.g:2285:3: ruleQualifiedId
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getElkEdgeRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_2_0()); 
            	    
            pushFollow(FOLLOW_48);
            ruleQualifiedId();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:2298:2: (otherlv_4= ',' ( ( ruleQualifiedId ) ) )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==32) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // InternalGrana.g:2298:4: otherlv_4= ',' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_4=(Token)match(input,32,FOLLOW_10); 

            	        	newLeafNode(otherlv_4, grammarAccess.getElkEdgeAccess().getCommaKeyword_3_0());
            	        
            	    // InternalGrana.g:2302:1: ( ( ruleQualifiedId ) )
            	    // InternalGrana.g:2303:1: ( ruleQualifiedId )
            	    {
            	    // InternalGrana.g:2303:1: ( ruleQualifiedId )
            	    // InternalGrana.g:2304:3: ruleQualifiedId
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getElkEdgeRule());
            	    	        }
            	            
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_48);
            	    ruleQualifiedId();

            	    state._fsp--;

            	     
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);

            otherlv_6=(Token)match(input,52,FOLLOW_10); 

                	newLeafNode(otherlv_6, grammarAccess.getElkEdgeAccess().getHyphenMinusGreaterThanSignKeyword_4());
                
            // InternalGrana.g:2321:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:2322:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:2322:1: ( ruleQualifiedId )
            // InternalGrana.g:2323:3: ruleQualifiedId
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getElkEdgeRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_5_0()); 
            	    
            pushFollow(FOLLOW_49);
            ruleQualifiedId();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalGrana.g:2336:2: (otherlv_8= ',' ( ( ruleQualifiedId ) ) )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==32) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // InternalGrana.g:2336:4: otherlv_8= ',' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_8=(Token)match(input,32,FOLLOW_10); 

            	        	newLeafNode(otherlv_8, grammarAccess.getElkEdgeAccess().getCommaKeyword_6_0());
            	        
            	    // InternalGrana.g:2340:1: ( ( ruleQualifiedId ) )
            	    // InternalGrana.g:2341:1: ( ruleQualifiedId )
            	    {
            	    // InternalGrana.g:2341:1: ( ruleQualifiedId )
            	    // InternalGrana.g:2342:3: ruleQualifiedId
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getElkEdgeRule());
            	    	        }
            	            
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_6_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_49);
            	    ruleQualifiedId();

            	    state._fsp--;

            	     
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);

            // InternalGrana.g:2355:4: (otherlv_10= '{' (this_EdgeLayout_11= ruleEdgeLayout[$current] )? ( (lv_properties_12_0= ruleProperty ) )* ( (lv_labels_13_0= ruleElkLabel ) )* otherlv_14= '}' )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==40) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // InternalGrana.g:2355:6: otherlv_10= '{' (this_EdgeLayout_11= ruleEdgeLayout[$current] )? ( (lv_properties_12_0= ruleProperty ) )* ( (lv_labels_13_0= ruleElkLabel ) )* otherlv_14= '}'
                    {
                    otherlv_10=(Token)match(input,40,FOLLOW_41); 

                        	newLeafNode(otherlv_10, grammarAccess.getElkEdgeAccess().getLeftCurlyBracketKeyword_7_0());
                        
                    // InternalGrana.g:2359:1: (this_EdgeLayout_11= ruleEdgeLayout[$current] )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==46) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // InternalGrana.g:2360:5: this_EdgeLayout_11= ruleEdgeLayout[$current]
                            {
                             
                            		if (current==null) {
                            			current = createModelElement(grammarAccess.getElkEdgeRule());
                            		}
                                    newCompositeNode(grammarAccess.getElkEdgeAccess().getEdgeLayoutParserRuleCall_7_1()); 
                                
                            pushFollow(FOLLOW_42);
                            this_EdgeLayout_11=ruleEdgeLayout(current);

                            state._fsp--;

                             
                                    current = this_EdgeLayout_11; 
                                    afterParserOrEnumRuleCall();
                                

                            }
                            break;

                    }

                    // InternalGrana.g:2371:3: ( (lv_properties_12_0= ruleProperty ) )*
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==RULE_ID) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // InternalGrana.g:2372:1: (lv_properties_12_0= ruleProperty )
                    	    {
                    	    // InternalGrana.g:2372:1: (lv_properties_12_0= ruleProperty )
                    	    // InternalGrana.g:2373:3: lv_properties_12_0= ruleProperty
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkEdgeAccess().getPropertiesPropertyParserRuleCall_7_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_42);
                    	    lv_properties_12_0=ruleProperty();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkEdgeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"properties",
                    	            		lv_properties_12_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.Property");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop55;
                        }
                    } while (true);

                    // InternalGrana.g:2389:3: ( (lv_labels_13_0= ruleElkLabel ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==43) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // InternalGrana.g:2390:1: (lv_labels_13_0= ruleElkLabel )
                    	    {
                    	    // InternalGrana.g:2390:1: (lv_labels_13_0= ruleElkLabel )
                    	    // InternalGrana.g:2391:3: lv_labels_13_0= ruleElkLabel
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkEdgeAccess().getLabelsElkLabelParserRuleCall_7_3_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_43);
                    	    lv_labels_13_0=ruleElkLabel();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkEdgeRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"labels",
                    	            		lv_labels_13_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkLabel");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);

                    otherlv_14=(Token)match(input,41,FOLLOW_2); 

                        	newLeafNode(otherlv_14, grammarAccess.getElkEdgeAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

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
    // $ANTLR end "ruleElkEdge"


    // $ANTLR start "ruleEdgeLayout"
    // InternalGrana.g:2420:1: ruleEdgeLayout[EObject in_current] returns [EObject current=in_current] : (otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']' ) ;
    public final EObject ruleEdgeLayout(EObject in_current) throws RecognitionException {
        EObject current = in_current;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_4=null;
        EObject lv_sections_2_0 = null;

        EObject lv_sections_3_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:2423:28: ( (otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']' ) )
            // InternalGrana.g:2424:1: (otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']' )
            {
            // InternalGrana.g:2424:1: (otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']' )
            // InternalGrana.g:2424:3: otherlv_0= 'layout' otherlv_1= '[' ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,46,FOLLOW_44); 

                	newLeafNode(otherlv_0, grammarAccess.getEdgeLayoutAccess().getLayoutKeyword_0());
                
            otherlv_1=(Token)match(input,47,FOLLOW_50); 

                	newLeafNode(otherlv_1, grammarAccess.getEdgeLayoutAccess().getLeftSquareBracketKeyword_1());
                
            // InternalGrana.g:2432:1: ( ( (lv_sections_2_0= ruleElkSingleEdgeSection ) ) | ( (lv_sections_3_0= ruleElkEdgeSection ) )+ )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==RULE_ID||LA59_0==50||(LA59_0>=53 && LA59_0<=57)) ) {
                alt59=1;
            }
            else if ( (LA59_0==59) ) {
                alt59=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }
            switch (alt59) {
                case 1 :
                    // InternalGrana.g:2432:2: ( (lv_sections_2_0= ruleElkSingleEdgeSection ) )
                    {
                    // InternalGrana.g:2432:2: ( (lv_sections_2_0= ruleElkSingleEdgeSection ) )
                    // InternalGrana.g:2433:1: (lv_sections_2_0= ruleElkSingleEdgeSection )
                    {
                    // InternalGrana.g:2433:1: (lv_sections_2_0= ruleElkSingleEdgeSection )
                    // InternalGrana.g:2434:3: lv_sections_2_0= ruleElkSingleEdgeSection
                    {
                     
                    	        newCompositeNode(grammarAccess.getEdgeLayoutAccess().getSectionsElkSingleEdgeSectionParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_51);
                    lv_sections_2_0=ruleElkSingleEdgeSection();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getEdgeLayoutRule());
                    	        }
                           		add(
                           			current, 
                           			"sections",
                            		lv_sections_2_0, 
                            		"org.eclipse.elk.graph.text.ElkGraph.ElkSingleEdgeSection");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:2451:6: ( (lv_sections_3_0= ruleElkEdgeSection ) )+
                    {
                    // InternalGrana.g:2451:6: ( (lv_sections_3_0= ruleElkEdgeSection ) )+
                    int cnt58=0;
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==59) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // InternalGrana.g:2452:1: (lv_sections_3_0= ruleElkEdgeSection )
                    	    {
                    	    // InternalGrana.g:2452:1: (lv_sections_3_0= ruleElkEdgeSection )
                    	    // InternalGrana.g:2453:3: lv_sections_3_0= ruleElkEdgeSection
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getEdgeLayoutAccess().getSectionsElkEdgeSectionParserRuleCall_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_50);
                    	    lv_sections_3_0=ruleElkEdgeSection();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getEdgeLayoutRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"sections",
                    	            		lv_sections_3_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkEdgeSection");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt58 >= 1 ) break loop58;
                                EarlyExitException eee =
                                    new EarlyExitException(58, input);
                                throw eee;
                        }
                        cnt58++;
                    } while (true);


                    }
                    break;

            }

            otherlv_4=(Token)match(input,50,FOLLOW_2); 

                	newLeafNode(otherlv_4, grammarAccess.getEdgeLayoutAccess().getRightSquareBracketKeyword_3());
                

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
    // $ANTLR end "ruleEdgeLayout"


    // $ANTLR start "entryRuleElkSingleEdgeSection"
    // InternalGrana.g:2481:1: entryRuleElkSingleEdgeSection returns [EObject current=null] : iv_ruleElkSingleEdgeSection= ruleElkSingleEdgeSection EOF ;
    public final EObject entryRuleElkSingleEdgeSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkSingleEdgeSection = null;


        try {
            // InternalGrana.g:2482:2: (iv_ruleElkSingleEdgeSection= ruleElkSingleEdgeSection EOF )
            // InternalGrana.g:2483:2: iv_ruleElkSingleEdgeSection= ruleElkSingleEdgeSection EOF
            {
             newCompositeNode(grammarAccess.getElkSingleEdgeSectionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkSingleEdgeSection=ruleElkSingleEdgeSection();

            state._fsp--;

             current =iv_ruleElkSingleEdgeSection; 
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
    // $ANTLR end "entryRuleElkSingleEdgeSection"


    // $ANTLR start "ruleElkSingleEdgeSection"
    // InternalGrana.g:2490:1: ruleElkSingleEdgeSection returns [EObject current=null] : ( () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* ) ) ;
    public final EObject ruleElkSingleEdgeSection() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        AntlrDatatypeRuleToken lv_startX_10_0 = null;

        AntlrDatatypeRuleToken lv_startY_12_0 = null;

        AntlrDatatypeRuleToken lv_endX_15_0 = null;

        AntlrDatatypeRuleToken lv_endY_17_0 = null;

        EObject lv_bendPoints_20_0 = null;

        EObject lv_bendPoints_22_0 = null;

        EObject lv_properties_23_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:2493:28: ( ( () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* ) ) )
            // InternalGrana.g:2494:1: ( () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* ) )
            {
            // InternalGrana.g:2494:1: ( () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* ) )
            // InternalGrana.g:2494:2: () ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* )
            {
            // InternalGrana.g:2494:2: ()
            // InternalGrana.g:2495:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0(),
                        current);
                

            }

            // InternalGrana.g:2500:2: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )* )
            // InternalGrana.g:2500:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_23_0= ruleProperty ) )*
            {
            // InternalGrana.g:2500:3: ( ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) ) )
            // InternalGrana.g:2502:1: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) )
            {
            // InternalGrana.g:2502:1: ( ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* ) )
            // InternalGrana.g:2503:2: ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	
            // InternalGrana.g:2506:2: ( ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )* )
            // InternalGrana.g:2507:3: ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )*
            {
            // InternalGrana.g:2507:3: ( ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) ) )*
            loop60:
            do {
                int alt60=5;
                int LA60_0 = input.LA(1);

                if ( LA60_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                    alt60=1;
                }
                else if ( LA60_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                    alt60=2;
                }
                else if ( LA60_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                    alt60=3;
                }
                else if ( LA60_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                    alt60=4;
                }


                switch (alt60) {
            	case 1 :
            	    // InternalGrana.g:2509:4: ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2509:4: ({...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    // InternalGrana.g:2510:5: {...}? => ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0)");
            	    }
            	    // InternalGrana.g:2510:119: ( ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    // InternalGrana.g:2511:6: ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0);
            	    	 				
            	    // InternalGrana.g:2514:6: ({...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) ) )
            	    // InternalGrana.g:2514:7: {...}? => (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "true");
            	    }
            	    // InternalGrana.g:2514:16: (otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) ) )
            	    // InternalGrana.g:2514:18: otherlv_2= 'incoming' otherlv_3= ':' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_2=(Token)match(input,53,FOLLOW_40); 

            	        	newLeafNode(otherlv_2, grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0_0());
            	        
            	    otherlv_3=(Token)match(input,44,FOLLOW_10); 

            	        	newLeafNode(otherlv_3, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_0_1());
            	        
            	    // InternalGrana.g:2522:1: ( ( ruleQualifiedId ) )
            	    // InternalGrana.g:2523:1: ( ruleQualifiedId )
            	    {
            	    // InternalGrana.g:2523:1: ( ruleQualifiedId )
            	    // InternalGrana.g:2524:3: ruleQualifiedId
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getElkSingleEdgeSectionRule());
            	    	        }
            	            
            	     
            	    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_0_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_52);
            	    ruleQualifiedId();

            	    state._fsp--;

            	     
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalGrana.g:2544:4: ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2544:4: ({...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    // InternalGrana.g:2545:5: {...}? => ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1)");
            	    }
            	    // InternalGrana.g:2545:119: ( ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    // InternalGrana.g:2546:6: ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1);
            	    	 				
            	    // InternalGrana.g:2549:6: ({...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) ) )
            	    // InternalGrana.g:2549:7: {...}? => (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "true");
            	    }
            	    // InternalGrana.g:2549:16: (otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) ) )
            	    // InternalGrana.g:2549:18: otherlv_5= 'outgoing' otherlv_6= ':' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_5=(Token)match(input,54,FOLLOW_40); 

            	        	newLeafNode(otherlv_5, grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_0_1_0());
            	        
            	    otherlv_6=(Token)match(input,44,FOLLOW_10); 

            	        	newLeafNode(otherlv_6, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1_1());
            	        
            	    // InternalGrana.g:2557:1: ( ( ruleQualifiedId ) )
            	    // InternalGrana.g:2558:1: ( ruleQualifiedId )
            	    {
            	    // InternalGrana.g:2558:1: ( ruleQualifiedId )
            	    // InternalGrana.g:2559:3: ruleQualifiedId
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getElkSingleEdgeSectionRule());
            	    	        }
            	            
            	     
            	    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_0_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_52);
            	    ruleQualifiedId();

            	    state._fsp--;

            	     
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalGrana.g:2579:4: ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2579:4: ({...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) ) )
            	    // InternalGrana.g:2580:5: {...}? => ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2)");
            	    }
            	    // InternalGrana.g:2580:119: ( ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) ) )
            	    // InternalGrana.g:2581:6: ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2);
            	    	 				
            	    // InternalGrana.g:2584:6: ({...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) ) )
            	    // InternalGrana.g:2584:7: {...}? => (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "true");
            	    }
            	    // InternalGrana.g:2584:16: (otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) ) )
            	    // InternalGrana.g:2584:18: otherlv_8= 'start' otherlv_9= ':' ( (lv_startX_10_0= ruleNumber ) ) otherlv_11= ',' ( (lv_startY_12_0= ruleNumber ) )
            	    {
            	    otherlv_8=(Token)match(input,55,FOLLOW_40); 

            	        	newLeafNode(otherlv_8, grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_0_2_0());
            	        
            	    otherlv_9=(Token)match(input,44,FOLLOW_46); 

            	        	newLeafNode(otherlv_9, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_2_1());
            	        
            	    // InternalGrana.g:2592:1: ( (lv_startX_10_0= ruleNumber ) )
            	    // InternalGrana.g:2593:1: (lv_startX_10_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2593:1: (lv_startX_10_0= ruleNumber )
            	    // InternalGrana.g:2594:3: lv_startX_10_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_0_2_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_47);
            	    lv_startX_10_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"startX",
            	            		lv_startX_10_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_11=(Token)match(input,32,FOLLOW_46); 

            	        	newLeafNode(otherlv_11, grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_2_3());
            	        
            	    // InternalGrana.g:2614:1: ( (lv_startY_12_0= ruleNumber ) )
            	    // InternalGrana.g:2615:1: (lv_startY_12_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2615:1: (lv_startY_12_0= ruleNumber )
            	    // InternalGrana.g:2616:3: lv_startY_12_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_0_2_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_52);
            	    lv_startY_12_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"startY",
            	            		lv_startY_12_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalGrana.g:2639:4: ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2639:4: ({...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) ) )
            	    // InternalGrana.g:2640:5: {...}? => ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3)");
            	    }
            	    // InternalGrana.g:2640:119: ( ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) ) )
            	    // InternalGrana.g:2641:6: ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3);
            	    	 				
            	    // InternalGrana.g:2644:6: ({...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) ) )
            	    // InternalGrana.g:2644:7: {...}? => (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkSingleEdgeSection", "true");
            	    }
            	    // InternalGrana.g:2644:16: (otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) ) )
            	    // InternalGrana.g:2644:18: otherlv_13= 'end' otherlv_14= ':' ( (lv_endX_15_0= ruleNumber ) ) otherlv_16= ',' ( (lv_endY_17_0= ruleNumber ) )
            	    {
            	    otherlv_13=(Token)match(input,56,FOLLOW_40); 

            	        	newLeafNode(otherlv_13, grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_0_3_0());
            	        
            	    otherlv_14=(Token)match(input,44,FOLLOW_46); 

            	        	newLeafNode(otherlv_14, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_3_1());
            	        
            	    // InternalGrana.g:2652:1: ( (lv_endX_15_0= ruleNumber ) )
            	    // InternalGrana.g:2653:1: (lv_endX_15_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2653:1: (lv_endX_15_0= ruleNumber )
            	    // InternalGrana.g:2654:3: lv_endX_15_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_0_3_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_47);
            	    lv_endX_15_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"endX",
            	            		lv_endX_15_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_16=(Token)match(input,32,FOLLOW_46); 

            	        	newLeafNode(otherlv_16, grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_3_3());
            	        
            	    // InternalGrana.g:2674:1: ( (lv_endY_17_0= ruleNumber ) )
            	    // InternalGrana.g:2675:1: (lv_endY_17_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2675:1: (lv_endY_17_0= ruleNumber )
            	    // InternalGrana.g:2676:3: lv_endY_17_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_0_3_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_52);
            	    lv_endY_17_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"endY",
            	            		lv_endY_17_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);


            }


            }

             
            	  getUnorderedGroupHelper().leave(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	

            }

            // InternalGrana.g:2706:2: (otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )* )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==57) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalGrana.g:2706:4: otherlv_18= 'bends' otherlv_19= ':' ( (lv_bendPoints_20_0= ruleElkBendPoint ) ) (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )*
                    {
                    otherlv_18=(Token)match(input,57,FOLLOW_40); 

                        	newLeafNode(otherlv_18, grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_1_0());
                        
                    otherlv_19=(Token)match(input,44,FOLLOW_46); 

                        	newLeafNode(otherlv_19, grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_1_1());
                        
                    // InternalGrana.g:2714:1: ( (lv_bendPoints_20_0= ruleElkBendPoint ) )
                    // InternalGrana.g:2715:1: (lv_bendPoints_20_0= ruleElkBendPoint )
                    {
                    // InternalGrana.g:2715:1: (lv_bendPoints_20_0= ruleElkBendPoint )
                    // InternalGrana.g:2716:3: lv_bendPoints_20_0= ruleElkBendPoint
                    {
                     
                    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_53);
                    lv_bendPoints_20_0=ruleElkBendPoint();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
                    	        }
                           		add(
                           			current, 
                           			"bendPoints",
                            		lv_bendPoints_20_0, 
                            		"org.eclipse.elk.graph.text.ElkGraph.ElkBendPoint");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalGrana.g:2732:2: (otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) ) )*
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==58) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // InternalGrana.g:2732:4: otherlv_21= '|' ( (lv_bendPoints_22_0= ruleElkBendPoint ) )
                    	    {
                    	    otherlv_21=(Token)match(input,58,FOLLOW_46); 

                    	        	newLeafNode(otherlv_21, grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_1_3_0());
                    	        
                    	    // InternalGrana.g:2736:1: ( (lv_bendPoints_22_0= ruleElkBendPoint ) )
                    	    // InternalGrana.g:2737:1: (lv_bendPoints_22_0= ruleElkBendPoint )
                    	    {
                    	    // InternalGrana.g:2737:1: (lv_bendPoints_22_0= ruleElkBendPoint )
                    	    // InternalGrana.g:2738:3: lv_bendPoints_22_0= ruleElkBendPoint
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_53);
                    	    lv_bendPoints_22_0=ruleElkBendPoint();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"bendPoints",
                    	            		lv_bendPoints_22_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkBendPoint");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop61;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalGrana.g:2754:6: ( (lv_properties_23_0= ruleProperty ) )*
            loop63:
            do {
                int alt63=2;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==RULE_ID) ) {
                    alt63=1;
                }


                switch (alt63) {
            	case 1 :
            	    // InternalGrana.g:2755:1: (lv_properties_23_0= ruleProperty )
            	    {
            	    // InternalGrana.g:2755:1: (lv_properties_23_0= ruleProperty )
            	    // InternalGrana.g:2756:3: lv_properties_23_0= ruleProperty
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesPropertyParserRuleCall_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_29);
            	    lv_properties_23_0=ruleProperty();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkSingleEdgeSectionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"properties",
            	            		lv_properties_23_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Property");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop63;
                }
            } while (true);


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
    // $ANTLR end "ruleElkSingleEdgeSection"


    // $ANTLR start "entryRuleElkEdgeSection"
    // InternalGrana.g:2780:1: entryRuleElkEdgeSection returns [EObject current=null] : iv_ruleElkEdgeSection= ruleElkEdgeSection EOF ;
    public final EObject entryRuleElkEdgeSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkEdgeSection = null;


        try {
            // InternalGrana.g:2781:2: (iv_ruleElkEdgeSection= ruleElkEdgeSection EOF )
            // InternalGrana.g:2782:2: iv_ruleElkEdgeSection= ruleElkEdgeSection EOF
            {
             newCompositeNode(grammarAccess.getElkEdgeSectionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkEdgeSection=ruleElkEdgeSection();

            state._fsp--;

             current =iv_ruleElkEdgeSection; 
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
    // $ANTLR end "entryRuleElkEdgeSection"


    // $ANTLR start "ruleElkEdgeSection"
    // InternalGrana.g:2789:1: ruleElkEdgeSection returns [EObject current=null] : (otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']' ) ;
    public final EObject ruleElkEdgeSection() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_identifier_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_30=null;
        AntlrDatatypeRuleToken lv_startX_16_0 = null;

        AntlrDatatypeRuleToken lv_startY_18_0 = null;

        AntlrDatatypeRuleToken lv_endX_21_0 = null;

        AntlrDatatypeRuleToken lv_endY_23_0 = null;

        EObject lv_bendPoints_26_0 = null;

        EObject lv_bendPoints_28_0 = null;

        EObject lv_properties_29_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:2792:28: ( (otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']' ) )
            // InternalGrana.g:2793:1: (otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']' )
            {
            // InternalGrana.g:2793:1: (otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']' )
            // InternalGrana.g:2793:3: otherlv_0= 'section' ( (lv_identifier_1_0= RULE_ID ) ) (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )? otherlv_6= '[' ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* ) otherlv_30= ']'
            {
            otherlv_0=(Token)match(input,59,FOLLOW_10); 

                	newLeafNode(otherlv_0, grammarAccess.getElkEdgeSectionAccess().getSectionKeyword_0());
                
            // InternalGrana.g:2797:1: ( (lv_identifier_1_0= RULE_ID ) )
            // InternalGrana.g:2798:1: (lv_identifier_1_0= RULE_ID )
            {
            // InternalGrana.g:2798:1: (lv_identifier_1_0= RULE_ID )
            // InternalGrana.g:2799:3: lv_identifier_1_0= RULE_ID
            {
            lv_identifier_1_0=(Token)match(input,RULE_ID,FOLLOW_54); 

            			newLeafNode(lv_identifier_1_0, grammarAccess.getElkEdgeSectionAccess().getIdentifierIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getElkEdgeSectionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"identifier",
                    		lv_identifier_1_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            // InternalGrana.g:2815:2: (otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )* )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==52) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalGrana.g:2815:4: otherlv_2= '->' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
                    {
                    otherlv_2=(Token)match(input,52,FOLLOW_10); 

                        	newLeafNode(otherlv_2, grammarAccess.getElkEdgeSectionAccess().getHyphenMinusGreaterThanSignKeyword_2_0());
                        
                    // InternalGrana.g:2819:1: ( (otherlv_3= RULE_ID ) )
                    // InternalGrana.g:2820:1: (otherlv_3= RULE_ID )
                    {
                    // InternalGrana.g:2820:1: (otherlv_3= RULE_ID )
                    // InternalGrana.g:2821:3: otherlv_3= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getElkEdgeSectionRule());
                    	        }
                            
                    otherlv_3=(Token)match(input,RULE_ID,FOLLOW_55); 

                    		newLeafNode(otherlv_3, grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 
                    	

                    }


                    }

                    // InternalGrana.g:2832:2: (otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==32) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // InternalGrana.g:2832:4: otherlv_4= ',' ( (otherlv_5= RULE_ID ) )
                    	    {
                    	    otherlv_4=(Token)match(input,32,FOLLOW_10); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_2_2_0());
                    	        
                    	    // InternalGrana.g:2836:1: ( (otherlv_5= RULE_ID ) )
                    	    // InternalGrana.g:2837:1: (otherlv_5= RULE_ID )
                    	    {
                    	    // InternalGrana.g:2837:1: (otherlv_5= RULE_ID )
                    	    // InternalGrana.g:2838:3: otherlv_5= RULE_ID
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getElkEdgeSectionRule());
                    	    	        }
                    	            
                    	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_55); 

                    	    		newLeafNode(otherlv_5, grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 
                    	    	

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop64;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,47,FOLLOW_56); 

                	newLeafNode(otherlv_6, grammarAccess.getElkEdgeSectionAccess().getLeftSquareBracketKeyword_3());
                
            // InternalGrana.g:2853:1: ( ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )* )
            // InternalGrana.g:2853:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) ) (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )? ( (lv_properties_29_0= ruleProperty ) )*
            {
            // InternalGrana.g:2853:2: ( ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) ) )
            // InternalGrana.g:2855:1: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) )
            {
            // InternalGrana.g:2855:1: ( ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* ) )
            // InternalGrana.g:2856:2: ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	
            // InternalGrana.g:2859:2: ( ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )* )
            // InternalGrana.g:2860:3: ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )*
            {
            // InternalGrana.g:2860:3: ( ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) ) )*
            loop66:
            do {
                int alt66=5;
                int LA66_0 = input.LA(1);

                if ( LA66_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                    alt66=1;
                }
                else if ( LA66_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                    alt66=2;
                }
                else if ( LA66_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                    alt66=3;
                }
                else if ( LA66_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                    alt66=4;
                }


                switch (alt66) {
            	case 1 :
            	    // InternalGrana.g:2862:4: ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2862:4: ({...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    // InternalGrana.g:2863:5: {...}? => ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0)");
            	    }
            	    // InternalGrana.g:2863:113: ( ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    // InternalGrana.g:2864:6: ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0);
            	    	 				
            	    // InternalGrana.g:2867:6: ({...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) ) )
            	    // InternalGrana.g:2867:7: {...}? => (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "true");
            	    }
            	    // InternalGrana.g:2867:16: (otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) ) )
            	    // InternalGrana.g:2867:18: otherlv_8= 'incoming' otherlv_9= ':' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_8=(Token)match(input,53,FOLLOW_40); 

            	        	newLeafNode(otherlv_8, grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0_0());
            	        
            	    otherlv_9=(Token)match(input,44,FOLLOW_10); 

            	        	newLeafNode(otherlv_9, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_0_1());
            	        
            	    // InternalGrana.g:2875:1: ( ( ruleQualifiedId ) )
            	    // InternalGrana.g:2876:1: ( ruleQualifiedId )
            	    {
            	    // InternalGrana.g:2876:1: ( ruleQualifiedId )
            	    // InternalGrana.g:2877:3: ruleQualifiedId
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getElkEdgeSectionRule());
            	    	        }
            	            
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_0_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_56);
            	    ruleQualifiedId();

            	    state._fsp--;

            	     
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalGrana.g:2897:4: ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2897:4: ({...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) ) )
            	    // InternalGrana.g:2898:5: {...}? => ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1)");
            	    }
            	    // InternalGrana.g:2898:113: ( ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) ) )
            	    // InternalGrana.g:2899:6: ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1);
            	    	 				
            	    // InternalGrana.g:2902:6: ({...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) ) )
            	    // InternalGrana.g:2902:7: {...}? => (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "true");
            	    }
            	    // InternalGrana.g:2902:16: (otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) ) )
            	    // InternalGrana.g:2902:18: otherlv_11= 'outgoing' otherlv_12= ':' ( ( ruleQualifiedId ) )
            	    {
            	    otherlv_11=(Token)match(input,54,FOLLOW_40); 

            	        	newLeafNode(otherlv_11, grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_0_1_0());
            	        
            	    otherlv_12=(Token)match(input,44,FOLLOW_10); 

            	        	newLeafNode(otherlv_12, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1_1());
            	        
            	    // InternalGrana.g:2910:1: ( ( ruleQualifiedId ) )
            	    // InternalGrana.g:2911:1: ( ruleQualifiedId )
            	    {
            	    // InternalGrana.g:2911:1: ( ruleQualifiedId )
            	    // InternalGrana.g:2912:3: ruleQualifiedId
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getElkEdgeSectionRule());
            	    	        }
            	            
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_0_1_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_56);
            	    ruleQualifiedId();

            	    state._fsp--;

            	     
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalGrana.g:2932:4: ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2932:4: ({...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) ) )
            	    // InternalGrana.g:2933:5: {...}? => ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2)");
            	    }
            	    // InternalGrana.g:2933:113: ( ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) ) )
            	    // InternalGrana.g:2934:6: ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2);
            	    	 				
            	    // InternalGrana.g:2937:6: ({...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) ) )
            	    // InternalGrana.g:2937:7: {...}? => (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "true");
            	    }
            	    // InternalGrana.g:2937:16: (otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) ) )
            	    // InternalGrana.g:2937:18: otherlv_14= 'start' otherlv_15= ':' ( (lv_startX_16_0= ruleNumber ) ) otherlv_17= ',' ( (lv_startY_18_0= ruleNumber ) )
            	    {
            	    otherlv_14=(Token)match(input,55,FOLLOW_40); 

            	        	newLeafNode(otherlv_14, grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_0_2_0());
            	        
            	    otherlv_15=(Token)match(input,44,FOLLOW_46); 

            	        	newLeafNode(otherlv_15, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_2_1());
            	        
            	    // InternalGrana.g:2945:1: ( (lv_startX_16_0= ruleNumber ) )
            	    // InternalGrana.g:2946:1: (lv_startX_16_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2946:1: (lv_startX_16_0= ruleNumber )
            	    // InternalGrana.g:2947:3: lv_startX_16_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_0_2_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_47);
            	    lv_startX_16_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"startX",
            	            		lv_startX_16_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_17=(Token)match(input,32,FOLLOW_46); 

            	        	newLeafNode(otherlv_17, grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_2_3());
            	        
            	    // InternalGrana.g:2967:1: ( (lv_startY_18_0= ruleNumber ) )
            	    // InternalGrana.g:2968:1: (lv_startY_18_0= ruleNumber )
            	    {
            	    // InternalGrana.g:2968:1: (lv_startY_18_0= ruleNumber )
            	    // InternalGrana.g:2969:3: lv_startY_18_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_0_2_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_56);
            	    lv_startY_18_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"startY",
            	            		lv_startY_18_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalGrana.g:2992:4: ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) )
            	    {
            	    // InternalGrana.g:2992:4: ({...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) ) )
            	    // InternalGrana.g:2993:5: {...}? => ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3)");
            	    }
            	    // InternalGrana.g:2993:113: ( ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) ) )
            	    // InternalGrana.g:2994:6: ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3);
            	    	 				
            	    // InternalGrana.g:2997:6: ({...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) ) )
            	    // InternalGrana.g:2997:7: {...}? => (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleElkEdgeSection", "true");
            	    }
            	    // InternalGrana.g:2997:16: (otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) ) )
            	    // InternalGrana.g:2997:18: otherlv_19= 'end' otherlv_20= ':' ( (lv_endX_21_0= ruleNumber ) ) otherlv_22= ',' ( (lv_endY_23_0= ruleNumber ) )
            	    {
            	    otherlv_19=(Token)match(input,56,FOLLOW_40); 

            	        	newLeafNode(otherlv_19, grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_0_3_0());
            	        
            	    otherlv_20=(Token)match(input,44,FOLLOW_46); 

            	        	newLeafNode(otherlv_20, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_3_1());
            	        
            	    // InternalGrana.g:3005:1: ( (lv_endX_21_0= ruleNumber ) )
            	    // InternalGrana.g:3006:1: (lv_endX_21_0= ruleNumber )
            	    {
            	    // InternalGrana.g:3006:1: (lv_endX_21_0= ruleNumber )
            	    // InternalGrana.g:3007:3: lv_endX_21_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_0_3_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_47);
            	    lv_endX_21_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"endX",
            	            		lv_endX_21_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }

            	    otherlv_22=(Token)match(input,32,FOLLOW_46); 

            	        	newLeafNode(otherlv_22, grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_3_3());
            	        
            	    // InternalGrana.g:3027:1: ( (lv_endY_23_0= ruleNumber ) )
            	    // InternalGrana.g:3028:1: (lv_endY_23_0= ruleNumber )
            	    {
            	    // InternalGrana.g:3028:1: (lv_endY_23_0= ruleNumber )
            	    // InternalGrana.g:3029:3: lv_endY_23_0= ruleNumber
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_0_3_4_0()); 
            	    	    
            	    pushFollow(FOLLOW_56);
            	    lv_endY_23_0=ruleNumber();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"endY",
            	            		lv_endY_23_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop66;
                }
            } while (true);


            }


            }

             
            	  getUnorderedGroupHelper().leave(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	

            }

            // InternalGrana.g:3059:2: (otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )* )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==57) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalGrana.g:3059:4: otherlv_24= 'bends' otherlv_25= ':' ( (lv_bendPoints_26_0= ruleElkBendPoint ) ) (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )*
                    {
                    otherlv_24=(Token)match(input,57,FOLLOW_40); 

                        	newLeafNode(otherlv_24, grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_1_0());
                        
                    otherlv_25=(Token)match(input,44,FOLLOW_46); 

                        	newLeafNode(otherlv_25, grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_1_1());
                        
                    // InternalGrana.g:3067:1: ( (lv_bendPoints_26_0= ruleElkBendPoint ) )
                    // InternalGrana.g:3068:1: (lv_bendPoints_26_0= ruleElkBendPoint )
                    {
                    // InternalGrana.g:3068:1: (lv_bendPoints_26_0= ruleElkBendPoint )
                    // InternalGrana.g:3069:3: lv_bendPoints_26_0= ruleElkBendPoint
                    {
                     
                    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_2_0()); 
                    	    
                    pushFollow(FOLLOW_57);
                    lv_bendPoints_26_0=ruleElkBendPoint();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
                    	        }
                           		add(
                           			current, 
                           			"bendPoints",
                            		lv_bendPoints_26_0, 
                            		"org.eclipse.elk.graph.text.ElkGraph.ElkBendPoint");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalGrana.g:3085:2: (otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==58) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // InternalGrana.g:3085:4: otherlv_27= '|' ( (lv_bendPoints_28_0= ruleElkBendPoint ) )
                    	    {
                    	    otherlv_27=(Token)match(input,58,FOLLOW_46); 

                    	        	newLeafNode(otherlv_27, grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_1_3_0());
                    	        
                    	    // InternalGrana.g:3089:1: ( (lv_bendPoints_28_0= ruleElkBendPoint ) )
                    	    // InternalGrana.g:3090:1: (lv_bendPoints_28_0= ruleElkBendPoint )
                    	    {
                    	    // InternalGrana.g:3090:1: (lv_bendPoints_28_0= ruleElkBendPoint )
                    	    // InternalGrana.g:3091:3: lv_bendPoints_28_0= ruleElkBendPoint
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_57);
                    	    lv_bendPoints_28_0=ruleElkBendPoint();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"bendPoints",
                    	            		lv_bendPoints_28_0, 
                    	            		"org.eclipse.elk.graph.text.ElkGraph.ElkBendPoint");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop67;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalGrana.g:3107:6: ( (lv_properties_29_0= ruleProperty ) )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==RULE_ID) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // InternalGrana.g:3108:1: (lv_properties_29_0= ruleProperty )
            	    {
            	    // InternalGrana.g:3108:1: (lv_properties_29_0= ruleProperty )
            	    // InternalGrana.g:3109:3: lv_properties_29_0= ruleProperty
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getElkEdgeSectionAccess().getPropertiesPropertyParserRuleCall_4_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_58);
            	    lv_properties_29_0=ruleProperty();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getElkEdgeSectionRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"properties",
            	            		lv_properties_29_0, 
            	            		"org.eclipse.elk.graph.text.ElkGraph.Property");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);


            }

            otherlv_30=(Token)match(input,50,FOLLOW_2); 

                	newLeafNode(otherlv_30, grammarAccess.getElkEdgeSectionAccess().getRightSquareBracketKeyword_5());
                

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
    // $ANTLR end "ruleElkEdgeSection"


    // $ANTLR start "entryRuleElkBendPoint"
    // InternalGrana.g:3137:1: entryRuleElkBendPoint returns [EObject current=null] : iv_ruleElkBendPoint= ruleElkBendPoint EOF ;
    public final EObject entryRuleElkBendPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElkBendPoint = null;


        try {
            // InternalGrana.g:3138:2: (iv_ruleElkBendPoint= ruleElkBendPoint EOF )
            // InternalGrana.g:3139:2: iv_ruleElkBendPoint= ruleElkBendPoint EOF
            {
             newCompositeNode(grammarAccess.getElkBendPointRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElkBendPoint=ruleElkBendPoint();

            state._fsp--;

             current =iv_ruleElkBendPoint; 
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
    // $ANTLR end "entryRuleElkBendPoint"


    // $ANTLR start "ruleElkBendPoint"
    // InternalGrana.g:3146:1: ruleElkBendPoint returns [EObject current=null] : ( ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) ) ) ;
    public final EObject ruleElkBendPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_x_0_0 = null;

        AntlrDatatypeRuleToken lv_y_2_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:3149:28: ( ( ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) ) ) )
            // InternalGrana.g:3150:1: ( ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) ) )
            {
            // InternalGrana.g:3150:1: ( ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) ) )
            // InternalGrana.g:3150:2: ( (lv_x_0_0= ruleNumber ) ) otherlv_1= ',' ( (lv_y_2_0= ruleNumber ) )
            {
            // InternalGrana.g:3150:2: ( (lv_x_0_0= ruleNumber ) )
            // InternalGrana.g:3151:1: (lv_x_0_0= ruleNumber )
            {
            // InternalGrana.g:3151:1: (lv_x_0_0= ruleNumber )
            // InternalGrana.g:3152:3: lv_x_0_0= ruleNumber
            {
             
            	        newCompositeNode(grammarAccess.getElkBendPointAccess().getXNumberParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_47);
            lv_x_0_0=ruleNumber();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getElkBendPointRule());
            	        }
                   		set(
                   			current, 
                   			"x",
                    		lv_x_0_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.Number");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,32,FOLLOW_46); 

                	newLeafNode(otherlv_1, grammarAccess.getElkBendPointAccess().getCommaKeyword_1());
                
            // InternalGrana.g:3172:1: ( (lv_y_2_0= ruleNumber ) )
            // InternalGrana.g:3173:1: (lv_y_2_0= ruleNumber )
            {
            // InternalGrana.g:3173:1: (lv_y_2_0= ruleNumber )
            // InternalGrana.g:3174:3: lv_y_2_0= ruleNumber
            {
             
            	        newCompositeNode(grammarAccess.getElkBendPointAccess().getYNumberParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_2);
            lv_y_2_0=ruleNumber();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getElkBendPointRule());
            	        }
                   		set(
                   			current, 
                   			"y",
                    		lv_y_2_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.Number");
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
    // $ANTLR end "ruleElkBendPoint"


    // $ANTLR start "entryRuleQualifiedId"
    // InternalGrana.g:3198:1: entryRuleQualifiedId returns [String current=null] : iv_ruleQualifiedId= ruleQualifiedId EOF ;
    public final String entryRuleQualifiedId() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedId = null;


        try {
            // InternalGrana.g:3199:2: (iv_ruleQualifiedId= ruleQualifiedId EOF )
            // InternalGrana.g:3200:2: iv_ruleQualifiedId= ruleQualifiedId EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIdRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedId=ruleQualifiedId();

            state._fsp--;

             current =iv_ruleQualifiedId.getText(); 
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
    // $ANTLR end "entryRuleQualifiedId"


    // $ANTLR start "ruleQualifiedId"
    // InternalGrana.g:3207:1: ruleQualifiedId returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedId() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:3210:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalGrana.g:3211:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalGrana.g:3211:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalGrana.g:3211:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_59); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_0()); 
                
            // InternalGrana.g:3218:1: (kw= '.' this_ID_2= RULE_ID )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( (LA70_0==60) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // InternalGrana.g:3219:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,60,FOLLOW_10); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIdAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_59); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop70;
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
    // $ANTLR end "ruleQualifiedId"


    // $ANTLR start "entryRuleNumber"
    // InternalGrana.g:3239:1: entryRuleNumber returns [String current=null] : iv_ruleNumber= ruleNumber EOF ;
    public final String entryRuleNumber() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumber = null;


        try {
            // InternalGrana.g:3240:2: (iv_ruleNumber= ruleNumber EOF )
            // InternalGrana.g:3241:2: iv_ruleNumber= ruleNumber EOF
            {
             newCompositeNode(grammarAccess.getNumberRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumber=ruleNumber();

            state._fsp--;

             current =iv_ruleNumber.getText(); 
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
    // $ANTLR end "entryRuleNumber"


    // $ANTLR start "ruleNumber"
    // InternalGrana.g:3248:1: ruleNumber returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT ) ;
    public final AntlrDatatypeRuleToken ruleNumber() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIGNED_INT_0=null;
        Token this_FLOAT_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:3251:28: ( (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT ) )
            // InternalGrana.g:3252:1: (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT )
            {
            // InternalGrana.g:3252:1: (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==RULE_SIGNED_INT) ) {
                alt71=1;
            }
            else if ( (LA71_0==RULE_FLOAT) ) {
                alt71=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // InternalGrana.g:3252:6: this_SIGNED_INT_0= RULE_SIGNED_INT
                    {
                    this_SIGNED_INT_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_2); 

                    		current.merge(this_SIGNED_INT_0);
                        
                     
                        newLeafNode(this_SIGNED_INT_0, grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:3260:10: this_FLOAT_1= RULE_FLOAT
                    {
                    this_FLOAT_1=(Token)match(input,RULE_FLOAT,FOLLOW_2); 

                    		current.merge(this_FLOAT_1);
                        
                     
                        newLeafNode(this_FLOAT_1, grammarAccess.getNumberAccess().getFLOATTerminalRuleCall_1()); 
                        

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
    // $ANTLR end "ruleNumber"


    // $ANTLR start "entryRuleProperty"
    // InternalGrana.g:3275:1: entryRuleProperty returns [EObject current=null] : iv_ruleProperty= ruleProperty EOF ;
    public final EObject entryRuleProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProperty = null;


        try {
            // InternalGrana.g:3276:2: (iv_ruleProperty= ruleProperty EOF )
            // InternalGrana.g:3277:2: iv_ruleProperty= ruleProperty EOF
            {
             newCompositeNode(grammarAccess.getPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProperty=ruleProperty();

            state._fsp--;

             current =iv_ruleProperty; 
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
    // $ANTLR end "entryRuleProperty"


    // $ANTLR start "ruleProperty"
    // InternalGrana.g:3284:1: ruleProperty returns [EObject current=null] : ( ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' ) ) ;
    public final EObject ruleProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;

        AntlrDatatypeRuleToken lv_value_3_0 = null;

        AntlrDatatypeRuleToken lv_value_4_0 = null;

        AntlrDatatypeRuleToken lv_value_5_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:3287:28: ( ( ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' ) ) )
            // InternalGrana.g:3288:1: ( ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' ) )
            {
            // InternalGrana.g:3288:1: ( ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' ) )
            // InternalGrana.g:3288:2: ( (lv_key_0_0= rulePropertyKey ) ) otherlv_1= ':' ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' )
            {
            // InternalGrana.g:3288:2: ( (lv_key_0_0= rulePropertyKey ) )
            // InternalGrana.g:3289:1: (lv_key_0_0= rulePropertyKey )
            {
            // InternalGrana.g:3289:1: (lv_key_0_0= rulePropertyKey )
            // InternalGrana.g:3290:3: lv_key_0_0= rulePropertyKey
            {
             
            	        newCompositeNode(grammarAccess.getPropertyAccess().getKeyPropertyKeyParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_40);
            lv_key_0_0=rulePropertyKey();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPropertyRule());
            	        }
                   		set(
                   			current, 
                   			"key",
                    		lv_key_0_0, 
                    		"org.eclipse.elk.graph.text.ElkGraph.PropertyKey");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_1=(Token)match(input,44,FOLLOW_60); 

                	newLeafNode(otherlv_1, grammarAccess.getPropertyAccess().getColonKeyword_1());
                
            // InternalGrana.g:3310:1: ( ( (lv_value_2_0= ruleStringValue ) ) | ( (lv_value_3_0= ruleQualifiedIdValue ) ) | ( (lv_value_4_0= ruleNumberValue ) ) | ( (lv_value_5_0= ruleBooleanValue ) ) | otherlv_6= 'null' )
            int alt72=5;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt72=1;
                }
                break;
            case RULE_ID:
                {
                alt72=2;
                }
                break;
            case RULE_SIGNED_INT:
            case RULE_FLOAT:
                {
                alt72=3;
                }
                break;
            case 62:
            case 63:
                {
                alt72=4;
                }
                break;
            case 61:
                {
                alt72=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }

            switch (alt72) {
                case 1 :
                    // InternalGrana.g:3310:2: ( (lv_value_2_0= ruleStringValue ) )
                    {
                    // InternalGrana.g:3310:2: ( (lv_value_2_0= ruleStringValue ) )
                    // InternalGrana.g:3311:1: (lv_value_2_0= ruleStringValue )
                    {
                    // InternalGrana.g:3311:1: (lv_value_2_0= ruleStringValue )
                    // InternalGrana.g:3312:3: lv_value_2_0= ruleStringValue
                    {
                     
                    	        newCompositeNode(grammarAccess.getPropertyAccess().getValueStringValueParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_value_2_0=ruleStringValue();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPropertyRule());
                    	        }
                           		set(
                           			current, 
                           			"value",
                            		lv_value_2_0, 
                            		"org.eclipse.elk.graph.text.ElkGraph.StringValue");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:3329:6: ( (lv_value_3_0= ruleQualifiedIdValue ) )
                    {
                    // InternalGrana.g:3329:6: ( (lv_value_3_0= ruleQualifiedIdValue ) )
                    // InternalGrana.g:3330:1: (lv_value_3_0= ruleQualifiedIdValue )
                    {
                    // InternalGrana.g:3330:1: (lv_value_3_0= ruleQualifiedIdValue )
                    // InternalGrana.g:3331:3: lv_value_3_0= ruleQualifiedIdValue
                    {
                     
                    	        newCompositeNode(grammarAccess.getPropertyAccess().getValueQualifiedIdValueParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_value_3_0=ruleQualifiedIdValue();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPropertyRule());
                    	        }
                           		set(
                           			current, 
                           			"value",
                            		lv_value_3_0, 
                            		"org.eclipse.elk.graph.text.ElkGraph.QualifiedIdValue");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:3348:6: ( (lv_value_4_0= ruleNumberValue ) )
                    {
                    // InternalGrana.g:3348:6: ( (lv_value_4_0= ruleNumberValue ) )
                    // InternalGrana.g:3349:1: (lv_value_4_0= ruleNumberValue )
                    {
                    // InternalGrana.g:3349:1: (lv_value_4_0= ruleNumberValue )
                    // InternalGrana.g:3350:3: lv_value_4_0= ruleNumberValue
                    {
                     
                    	        newCompositeNode(grammarAccess.getPropertyAccess().getValueNumberValueParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_value_4_0=ruleNumberValue();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPropertyRule());
                    	        }
                           		set(
                           			current, 
                           			"value",
                            		lv_value_4_0, 
                            		"org.eclipse.elk.graph.text.ElkGraph.NumberValue");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:3367:6: ( (lv_value_5_0= ruleBooleanValue ) )
                    {
                    // InternalGrana.g:3367:6: ( (lv_value_5_0= ruleBooleanValue ) )
                    // InternalGrana.g:3368:1: (lv_value_5_0= ruleBooleanValue )
                    {
                    // InternalGrana.g:3368:1: (lv_value_5_0= ruleBooleanValue )
                    // InternalGrana.g:3369:3: lv_value_5_0= ruleBooleanValue
                    {
                     
                    	        newCompositeNode(grammarAccess.getPropertyAccess().getValueBooleanValueParserRuleCall_2_3_0()); 
                    	    
                    pushFollow(FOLLOW_2);
                    lv_value_5_0=ruleBooleanValue();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPropertyRule());
                    	        }
                           		set(
                           			current, 
                           			"value",
                            		lv_value_5_0, 
                            		"org.eclipse.elk.graph.text.ElkGraph.BooleanValue");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalGrana.g:3386:7: otherlv_6= 'null'
                    {
                    otherlv_6=(Token)match(input,61,FOLLOW_2); 

                        	newLeafNode(otherlv_6, grammarAccess.getPropertyAccess().getNullKeyword_2_4());
                        

                    }
                    break;

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
    // $ANTLR end "ruleProperty"


    // $ANTLR start "entryRulePropertyKey"
    // InternalGrana.g:3398:1: entryRulePropertyKey returns [String current=null] : iv_rulePropertyKey= rulePropertyKey EOF ;
    public final String entryRulePropertyKey() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyKey = null;


         
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
        	
        try {
            // InternalGrana.g:3402:2: (iv_rulePropertyKey= rulePropertyKey EOF )
            // InternalGrana.g:3403:2: iv_rulePropertyKey= rulePropertyKey EOF
            {
             newCompositeNode(grammarAccess.getPropertyKeyRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePropertyKey=rulePropertyKey();

            state._fsp--;

             current =iv_rulePropertyKey.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRulePropertyKey"


    // $ANTLR start "rulePropertyKey"
    // InternalGrana.g:3413:1: rulePropertyKey returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken rulePropertyKey() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
            
        try {
            // InternalGrana.g:3417:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalGrana.g:3418:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalGrana.g:3418:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalGrana.g:3418:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_59); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_0()); 
                
            // InternalGrana.g:3425:1: (kw= '.' this_ID_2= RULE_ID )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==60) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // InternalGrana.g:3426:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,60,FOLLOW_10); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getPropertyKeyAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_59); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop73;
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

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "rulePropertyKey"


    // $ANTLR start "entryRuleStringValue"
    // InternalGrana.g:3449:1: entryRuleStringValue returns [String current=null] : iv_ruleStringValue= ruleStringValue EOF ;
    public final String entryRuleStringValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringValue = null;


        try {
            // InternalGrana.g:3450:2: (iv_ruleStringValue= ruleStringValue EOF )
            // InternalGrana.g:3451:2: iv_ruleStringValue= ruleStringValue EOF
            {
             newCompositeNode(grammarAccess.getStringValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStringValue=ruleStringValue();

            state._fsp--;

             current =iv_ruleStringValue.getText(); 
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
    // $ANTLR end "entryRuleStringValue"


    // $ANTLR start "ruleStringValue"
    // InternalGrana.g:3458:1: ruleStringValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_STRING_0= RULE_STRING ;
    public final AntlrDatatypeRuleToken ruleStringValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:3461:28: (this_STRING_0= RULE_STRING )
            // InternalGrana.g:3462:5: this_STRING_0= RULE_STRING
            {
            this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            		current.merge(this_STRING_0);
                
             
                newLeafNode(this_STRING_0, grammarAccess.getStringValueAccess().getSTRINGTerminalRuleCall()); 
                

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
    // $ANTLR end "ruleStringValue"


    // $ANTLR start "entryRuleQualifiedIdValue"
    // InternalGrana.g:3477:1: entryRuleQualifiedIdValue returns [String current=null] : iv_ruleQualifiedIdValue= ruleQualifiedIdValue EOF ;
    public final String entryRuleQualifiedIdValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedIdValue = null;


        try {
            // InternalGrana.g:3478:2: (iv_ruleQualifiedIdValue= ruleQualifiedIdValue EOF )
            // InternalGrana.g:3479:2: iv_ruleQualifiedIdValue= ruleQualifiedIdValue EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIdValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedIdValue=ruleQualifiedIdValue();

            state._fsp--;

             current =iv_ruleQualifiedIdValue.getText(); 
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
    // $ANTLR end "entryRuleQualifiedIdValue"


    // $ANTLR start "ruleQualifiedIdValue"
    // InternalGrana.g:3486:1: ruleQualifiedIdValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_QualifiedId_0= ruleQualifiedId ;
    public final AntlrDatatypeRuleToken ruleQualifiedIdValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_QualifiedId_0 = null;


         enterRule(); 
            
        try {
            // InternalGrana.g:3489:28: (this_QualifiedId_0= ruleQualifiedId )
            // InternalGrana.g:3491:5: this_QualifiedId_0= ruleQualifiedId
            {
             
                    newCompositeNode(grammarAccess.getQualifiedIdValueAccess().getQualifiedIdParserRuleCall()); 
                
            pushFollow(FOLLOW_2);
            this_QualifiedId_0=ruleQualifiedId();

            state._fsp--;


            		current.merge(this_QualifiedId_0);
                
             
                    afterParserOrEnumRuleCall();
                

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
    // $ANTLR end "ruleQualifiedIdValue"


    // $ANTLR start "entryRuleNumberValue"
    // InternalGrana.g:3509:1: entryRuleNumberValue returns [String current=null] : iv_ruleNumberValue= ruleNumberValue EOF ;
    public final String entryRuleNumberValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumberValue = null;


        try {
            // InternalGrana.g:3510:2: (iv_ruleNumberValue= ruleNumberValue EOF )
            // InternalGrana.g:3511:2: iv_ruleNumberValue= ruleNumberValue EOF
            {
             newCompositeNode(grammarAccess.getNumberValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNumberValue=ruleNumberValue();

            state._fsp--;

             current =iv_ruleNumberValue.getText(); 
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
    // $ANTLR end "entryRuleNumberValue"


    // $ANTLR start "ruleNumberValue"
    // InternalGrana.g:3518:1: ruleNumberValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT ) ;
    public final AntlrDatatypeRuleToken ruleNumberValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIGNED_INT_0=null;
        Token this_FLOAT_1=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:3521:28: ( (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT ) )
            // InternalGrana.g:3522:1: (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT )
            {
            // InternalGrana.g:3522:1: (this_SIGNED_INT_0= RULE_SIGNED_INT | this_FLOAT_1= RULE_FLOAT )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==RULE_SIGNED_INT) ) {
                alt74=1;
            }
            else if ( (LA74_0==RULE_FLOAT) ) {
                alt74=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }
            switch (alt74) {
                case 1 :
                    // InternalGrana.g:3522:6: this_SIGNED_INT_0= RULE_SIGNED_INT
                    {
                    this_SIGNED_INT_0=(Token)match(input,RULE_SIGNED_INT,FOLLOW_2); 

                    		current.merge(this_SIGNED_INT_0);
                        
                     
                        newLeafNode(this_SIGNED_INT_0, grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:3530:10: this_FLOAT_1= RULE_FLOAT
                    {
                    this_FLOAT_1=(Token)match(input,RULE_FLOAT,FOLLOW_2); 

                    		current.merge(this_FLOAT_1);
                        
                     
                        newLeafNode(this_FLOAT_1, grammarAccess.getNumberValueAccess().getFLOATTerminalRuleCall_1()); 
                        

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
    // $ANTLR end "ruleNumberValue"


    // $ANTLR start "entryRuleBooleanValue"
    // InternalGrana.g:3545:1: entryRuleBooleanValue returns [String current=null] : iv_ruleBooleanValue= ruleBooleanValue EOF ;
    public final String entryRuleBooleanValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBooleanValue = null;


        try {
            // InternalGrana.g:3546:2: (iv_ruleBooleanValue= ruleBooleanValue EOF )
            // InternalGrana.g:3547:2: iv_ruleBooleanValue= ruleBooleanValue EOF
            {
             newCompositeNode(grammarAccess.getBooleanValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBooleanValue=ruleBooleanValue();

            state._fsp--;

             current =iv_ruleBooleanValue.getText(); 
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
    // $ANTLR end "entryRuleBooleanValue"


    // $ANTLR start "ruleBooleanValue"
    // InternalGrana.g:3554:1: ruleBooleanValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleBooleanValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // InternalGrana.g:3557:28: ( (kw= 'true' | kw= 'false' ) )
            // InternalGrana.g:3558:1: (kw= 'true' | kw= 'false' )
            {
            // InternalGrana.g:3558:1: (kw= 'true' | kw= 'false' )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==62) ) {
                alt75=1;
            }
            else if ( (LA75_0==63) ) {
                alt75=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // InternalGrana.g:3559:2: kw= 'true'
                    {
                    kw=(Token)match(input,62,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // InternalGrana.g:3566:2: kw= 'false'
                    {
                    kw=(Token)match(input,63,FOLLOW_2); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getBooleanValueAccess().getFalseKeyword_1()); 
                        

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
    // $ANTLR end "ruleBooleanValue"


    // $ANTLR start "ruleOutputType"
    // InternalGrana.g:3579:1: ruleOutputType returns [Enumerator current=null] : ( (enumLiteral_0= 'csv' ) | (enumLiteral_1= 'json' ) ) ;
    public final Enumerator ruleOutputType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalGrana.g:3581:28: ( ( (enumLiteral_0= 'csv' ) | (enumLiteral_1= 'json' ) ) )
            // InternalGrana.g:3582:1: ( (enumLiteral_0= 'csv' ) | (enumLiteral_1= 'json' ) )
            {
            // InternalGrana.g:3582:1: ( (enumLiteral_0= 'csv' ) | (enumLiteral_1= 'json' ) )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==64) ) {
                alt76=1;
            }
            else if ( (LA76_0==65) ) {
                alt76=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }
            switch (alt76) {
                case 1 :
                    // InternalGrana.g:3582:2: (enumLiteral_0= 'csv' )
                    {
                    // InternalGrana.g:3582:2: (enumLiteral_0= 'csv' )
                    // InternalGrana.g:3582:4: enumLiteral_0= 'csv'
                    {
                    enumLiteral_0=(Token)match(input,64,FOLLOW_2); 

                            current = grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:3588:6: (enumLiteral_1= 'json' )
                    {
                    // InternalGrana.g:3588:6: (enumLiteral_1= 'json' )
                    // InternalGrana.g:3588:8: enumLiteral_1= 'json'
                    {
                    enumLiteral_1=(Token)match(input,65,FOLLOW_2); 

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
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000000000C010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000030010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000006040000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000006040010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000006040002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000380000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000002000000080L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000002000400080L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000800010L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000010L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000002000000080L,0x0000000000000003L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000008000010L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000001680000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000021000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000020000000010L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x00086E0000000010L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x00082E0000000010L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x00082E0000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x00004A0000000010L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x00000A0000000010L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x00000A0000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0007000000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0010000100000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000010100000002L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0BE4000000000010L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x03E0000000000012L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0400000000000012L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0010800000000000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000800100000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x03E4000000000010L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0404000000000010L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0004000000000010L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0xE0000000000000F0L});

}
