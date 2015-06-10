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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_BOOLEAN", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'globalResources'", "'globalOutputs'", "'execute'", "'all'", "'job'", "'layoutBeforeAnalysis'", "'measureExecutionTime'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'"
    };
    public static final int RULE_BOOLEAN=6;
    public static final int RULE_ID=4;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:76:1: ruleGrana returns [EObject current=null] : ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+ ) ;
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
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:79:28: ( ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+ ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:80:1: ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+ )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:80:1: ( (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+ )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:80:2: (otherlv_0= 'globalResources' ( (lv_globalResources_1_0= ruleGlobalResourceRef ) )* )? (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )? (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) ) ( (lv_jobs_7_0= ruleJob ) )+
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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:102:5: (otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==13) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:102:7: otherlv_2= 'globalOutputs' ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )*
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleGrana160); 

                        	newLeafNode(otherlv_2, grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:106:1: ( (lv_gloobalOutputs_3_0= ruleGlobalOutputRef ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==RULE_ID) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:107:1: (lv_gloobalOutputs_3_0= ruleGlobalOutputRef )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:107:1: (lv_gloobalOutputs_3_0= ruleGlobalOutputRef )
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:108:3: lv_gloobalOutputs_3_0= ruleGlobalOutputRef
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getGranaAccess().getGloobalOutputsGlobalOutputRefParserRuleCall_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleGlobalOutputRef_in_ruleGrana181);
                    	    lv_gloobalOutputs_3_0=ruleGlobalOutputRef();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getGranaRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"gloobalOutputs",
                    	            		lv_gloobalOutputs_3_0, 
                    	            		"GlobalOutputRef");
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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:124:5: (otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:124:7: otherlv_4= 'execute' ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ )
            {
            otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleGrana197); 

                	newLeafNode(otherlv_4, grammarAccess.getGranaAccess().getExecuteKeyword_2_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:128:1: ( ( (lv_executeAll_5_0= 'all' ) ) | ( (otherlv_6= RULE_ID ) )+ )
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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:128:2: ( (lv_executeAll_5_0= 'all' ) )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:128:2: ( (lv_executeAll_5_0= 'all' ) )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:129:1: (lv_executeAll_5_0= 'all' )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:129:1: (lv_executeAll_5_0= 'all' )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:130:3: lv_executeAll_5_0= 'all'
                    {
                    lv_executeAll_5_0=(Token)match(input,15,FOLLOW_15_in_ruleGrana216); 

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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:144:6: ( (otherlv_6= RULE_ID ) )+
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:144:6: ( (otherlv_6= RULE_ID ) )+
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
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:145:1: (otherlv_6= RULE_ID )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:145:1: (otherlv_6= RULE_ID )
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:146:3: otherlv_6= RULE_ID
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getGranaRule());
                    	    	        }
                    	            
                    	    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGrana255); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:157:5: ( (lv_jobs_7_0= ruleJob ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:158:1: (lv_jobs_7_0= ruleJob )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:158:1: (lv_jobs_7_0= ruleJob )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:159:3: lv_jobs_7_0= ruleJob
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleJob_in_ruleGrana279);
            	    lv_jobs_7_0=ruleJob();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getGranaRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"jobs",
            	            		lv_jobs_7_0, 
            	            		"Job");
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:183:1: entryRuleJob returns [EObject current=null] : iv_ruleJob= ruleJob EOF ;
    public final EObject entryRuleJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJob = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:184:2: (iv_ruleJob= ruleJob EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:185:2: iv_ruleJob= ruleJob EOF
            {
             newCompositeNode(grammarAccess.getJobRule()); 
            pushFollow(FOLLOW_ruleJob_in_entryRuleJob316);
            iv_ruleJob=ruleJob();

            state._fsp--;

             current =iv_ruleJob; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJob326); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:192:1: ruleJob returns [EObject current=null] : ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) )* otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= ruleOutput ) ) ) ;
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
        EObject lv_resources_6_0 = null;

        EObject lv_layoutOptions_8_0 = null;

        EObject lv_analyses_10_0 = null;

        EObject lv_output_12_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:195:28: ( ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) )* otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= ruleOutput ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:196:1: ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) )* otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= ruleOutput ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:196:1: ( () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) )* otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= ruleOutput ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:196:2: () otherlv_1= 'job' ( (lv_name_2_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )? otherlv_5= 'resources' ( (lv_resources_6_0= ruleResource ) )* otherlv_7= 'layoutoptions' ( (lv_layoutOptions_8_0= ruleKIdentifier ) )* otherlv_9= 'analyses' ( (lv_analyses_10_0= ruleAnalysis ) )* otherlv_11= 'output' ( (lv_output_12_0= ruleOutput ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:196:2: ()
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:197:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getJobAccess().getJobAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,16,FOLLOW_16_in_ruleJob372); 

                	newLeafNode(otherlv_1, grammarAccess.getJobAccess().getJobKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:206:1: ( (lv_name_2_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:207:1: (lv_name_2_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:207:1: (lv_name_2_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:208:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJob389); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:224:2: ( (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==17) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:225:1: (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:225:1: (lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis' )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:226:3: lv_layoutBeforeAnalysis_3_0= 'layoutBeforeAnalysis'
                    {
                    lv_layoutBeforeAnalysis_3_0=(Token)match(input,17,FOLLOW_17_in_ruleJob412); 

                            newLeafNode(lv_layoutBeforeAnalysis_3_0, grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getJobRule());
                    	        }
                           		setWithLastConsumed(current, "layoutBeforeAnalysis", true, "layoutBeforeAnalysis");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:239:3: ( (lv_measureExecutionTime_4_0= 'measureExecutionTime' ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==18) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:240:1: (lv_measureExecutionTime_4_0= 'measureExecutionTime' )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:240:1: (lv_measureExecutionTime_4_0= 'measureExecutionTime' )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:241:3: lv_measureExecutionTime_4_0= 'measureExecutionTime'
                    {
                    lv_measureExecutionTime_4_0=(Token)match(input,18,FOLLOW_18_in_ruleJob444); 

                            newLeafNode(lv_measureExecutionTime_4_0, grammarAccess.getJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_4_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getJobRule());
                    	        }
                           		setWithLastConsumed(current, "measureExecutionTime", true, "measureExecutionTime");
                    	    

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,19,FOLLOW_19_in_ruleJob470); 

                	newLeafNode(otherlv_5, grammarAccess.getJobAccess().getResourcesKeyword_5());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:258:1: ( (lv_resources_6_0= ruleResource ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_STRING||LA10_0==23) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:259:1: (lv_resources_6_0= ruleResource )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:259:1: (lv_resources_6_0= ruleResource )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:260:3: lv_resources_6_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_6_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleResource_in_ruleJob491);
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
            	    break loop10;
                }
            } while (true);

            otherlv_7=(Token)match(input,20,FOLLOW_20_in_ruleJob504); 

                	newLeafNode(otherlv_7, grammarAccess.getJobAccess().getLayoutoptionsKeyword_7());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:280:1: ( (lv_layoutOptions_8_0= ruleKIdentifier ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_ID) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:281:1: (lv_layoutOptions_8_0= ruleKIdentifier )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:281:1: (lv_layoutOptions_8_0= ruleKIdentifier )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:282:3: lv_layoutOptions_8_0= ruleKIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_8_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleKIdentifier_in_ruleJob525);
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
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_9=(Token)match(input,21,FOLLOW_21_in_ruleJob538); 

                	newLeafNode(otherlv_9, grammarAccess.getJobAccess().getAnalysesKeyword_9());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:302:1: ( (lv_analyses_10_0= ruleAnalysis ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_ID) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:303:1: (lv_analyses_10_0= ruleAnalysis )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:303:1: (lv_analyses_10_0= ruleAnalysis )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:304:3: lv_analyses_10_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_10_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnalysis_in_ruleJob559);
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
            	    break loop12;
                }
            } while (true);

            otherlv_11=(Token)match(input,22,FOLLOW_22_in_ruleJob572); 

                	newLeafNode(otherlv_11, grammarAccess.getJobAccess().getOutputKeyword_11());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:324:1: ( (lv_output_12_0= ruleOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:325:1: (lv_output_12_0= ruleOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:325:1: (lv_output_12_0= ruleOutput )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:326:3: lv_output_12_0= ruleOutput
            {
             
            	        newCompositeNode(grammarAccess.getJobAccess().getOutputOutputParserRuleCall_12_0()); 
            	    
            pushFollow(FOLLOW_ruleOutput_in_ruleJob593);
            lv_output_12_0=ruleOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getJobRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_12_0, 
                    		"Output");
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
    // $ANTLR end "ruleJob"


    // $ANTLR start "entryRuleResource"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:350:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:351:2: (iv_ruleResource= ruleResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:352:2: iv_ruleResource= ruleResource EOF
            {
             newCompositeNode(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_ruleResource_in_entryRuleResource629);
            iv_ruleResource=ruleResource();

            state._fsp--;

             current =iv_ruleResource; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResource639); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:359:1: ruleResource returns [EObject current=null] : (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        EObject this_ResourceReference_0 = null;

        EObject this_LocalResource_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:362:28: ( (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:363:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:363:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==23) ) {
                alt13=1;
            }
            else if ( (LA13_0==RULE_STRING) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:364:5: this_ResourceReference_0= ruleResourceReference
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleResourceReference_in_ruleResource686);
                    this_ResourceReference_0=ruleResourceReference();

                    state._fsp--;

                     
                            current = this_ResourceReference_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:374:5: this_LocalResource_1= ruleLocalResource
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLocalResource_in_ruleResource713);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:390:1: entryRuleResourceReference returns [EObject current=null] : iv_ruleResourceReference= ruleResourceReference EOF ;
    public final EObject entryRuleResourceReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceReference = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:391:2: (iv_ruleResourceReference= ruleResourceReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:392:2: iv_ruleResourceReference= ruleResourceReference EOF
            {
             newCompositeNode(grammarAccess.getResourceReferenceRule()); 
            pushFollow(FOLLOW_ruleResourceReference_in_entryRuleResourceReference748);
            iv_ruleResourceReference=ruleResourceReference();

            state._fsp--;

             current =iv_ruleResourceReference; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResourceReference758); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:399:1: ruleResourceReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) ;
    public final EObject ruleResourceReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:402:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:403:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:403:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:403:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleResourceReference795); 

                	newLeafNode(otherlv_0, grammarAccess.getResourceReferenceAccess().getRefKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:407:1: ( (otherlv_1= RULE_ID ) )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:408:1: (otherlv_1= RULE_ID )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:408:1: (otherlv_1= RULE_ID )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:409:3: otherlv_1= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getResourceReferenceRule());
            	    	        }
            	            
            	    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleResourceReference815); 

            	    		newLeafNode(otherlv_1, grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            	    	

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:428:1: entryRuleGlobalResourceRef returns [EObject current=null] : iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF ;
    public final EObject entryRuleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalResourceRef = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:429:2: (iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:430:2: iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF
            {
             newCompositeNode(grammarAccess.getGlobalResourceRefRule()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef852);
            iv_ruleGlobalResourceRef=ruleGlobalResourceRef();

            state._fsp--;

             current =iv_ruleGlobalResourceRef; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalResourceRef862); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:437:1: ruleGlobalResourceRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) ;
    public final EObject ruleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_resources_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:440:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:441:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:441:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:441:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:441:2: ( (lv_name_0_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:442:1: (lv_name_0_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:442:1: (lv_name_0_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:443:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGlobalResourceRef904); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:459:2: ( (lv_resources_1_0= ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:460:1: (lv_resources_1_0= ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:460:1: (lv_resources_1_0= ruleLocalResource )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:461:3: lv_resources_1_0= ruleLocalResource
            {
             
            	        newCompositeNode(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLocalResource_in_ruleGlobalResourceRef930);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:485:1: entryRuleLocalResource returns [EObject current=null] : iv_ruleLocalResource= ruleLocalResource EOF ;
    public final EObject entryRuleLocalResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalResource = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:486:2: (iv_ruleLocalResource= ruleLocalResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:487:2: iv_ruleLocalResource= ruleLocalResource EOF
            {
             newCompositeNode(grammarAccess.getLocalResourceRule()); 
            pushFollow(FOLLOW_ruleLocalResource_in_entryRuleLocalResource966);
            iv_ruleLocalResource=ruleLocalResource();

            state._fsp--;

             current =iv_ruleLocalResource; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalResource976); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:494:1: ruleLocalResource returns [EObject current=null] : ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleLocalResource() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;
        Token otherlv_1=null;
        Token lv_filter_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:497:28: ( ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:498:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:498:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:498:2: ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:498:2: ( (lv_path_0_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:499:1: (lv_path_0_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:499:1: (lv_path_0_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:500:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalResource1018); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:516:2: (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:516:4: otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) )
            {
            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleLocalResource1036); 

                	newLeafNode(otherlv_1, grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:520:1: ( (lv_filter_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:521:1: (lv_filter_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:521:1: (lv_filter_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:522:3: lv_filter_2_0= RULE_STRING
            {
            lv_filter_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalResource1053); 

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


    // $ANTLR start "entryRuleOutput"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:546:1: entryRuleOutput returns [EObject current=null] : iv_ruleOutput= ruleOutput EOF ;
    public final EObject entryRuleOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutput = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:547:2: (iv_ruleOutput= ruleOutput EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:548:2: iv_ruleOutput= ruleOutput EOF
            {
             newCompositeNode(grammarAccess.getOutputRule()); 
            pushFollow(FOLLOW_ruleOutput_in_entryRuleOutput1095);
            iv_ruleOutput=ruleOutput();

            state._fsp--;

             current =iv_ruleOutput; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutput1105); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:555:1: ruleOutput returns [EObject current=null] : (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) ;
    public final EObject ruleOutput() throws RecognitionException {
        EObject current = null;

        EObject this_OutputReference_0 = null;

        EObject this_LocalOutput_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:558:28: ( (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:559:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:559:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==23) ) {
                alt15=1;
            }
            else if ( (LA15_0==RULE_STRING) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:560:5: this_OutputReference_0= ruleOutputReference
                    {
                     
                            newCompositeNode(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleOutputReference_in_ruleOutput1152);
                    this_OutputReference_0=ruleOutputReference();

                    state._fsp--;

                     
                            current = this_OutputReference_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:570:5: this_LocalOutput_1= ruleLocalOutput
                    {
                     
                            newCompositeNode(grammarAccess.getOutputAccess().getLocalOutputParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLocalOutput_in_ruleOutput1179);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:586:1: entryRuleGlobalOutputRef returns [EObject current=null] : iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF ;
    public final EObject entryRuleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalOutputRef = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:587:2: (iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:588:2: iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF
            {
             newCompositeNode(grammarAccess.getGlobalOutputRefRule()); 
            pushFollow(FOLLOW_ruleGlobalOutputRef_in_entryRuleGlobalOutputRef1214);
            iv_ruleGlobalOutputRef=ruleGlobalOutputRef();

            state._fsp--;

             current =iv_ruleGlobalOutputRef; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalOutputRef1224); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:595:1: ruleGlobalOutputRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) ;
    public final EObject ruleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_output_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:598:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:599:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:599:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:599:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:599:2: ( (lv_name_0_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:600:1: (lv_name_0_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:600:1: (lv_name_0_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:601:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGlobalOutputRef1266); 

            			newLeafNode(lv_name_0_0, grammarAccess.getGlobalOutputRefAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getGlobalOutputRefRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:617:2: ( (lv_output_1_0= ruleLocalOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:618:1: (lv_output_1_0= ruleLocalOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:618:1: (lv_output_1_0= ruleLocalOutput )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:619:3: lv_output_1_0= ruleLocalOutput
            {
             
            	        newCompositeNode(grammarAccess.getGlobalOutputRefAccess().getOutputLocalOutputParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLocalOutput_in_ruleGlobalOutputRef1292);
            lv_output_1_0=ruleLocalOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getGlobalOutputRefRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_1_0, 
                    		"LocalOutput");
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:643:1: entryRuleOutputReference returns [EObject current=null] : iv_ruleOutputReference= ruleOutputReference EOF ;
    public final EObject entryRuleOutputReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputReference = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:644:2: (iv_ruleOutputReference= ruleOutputReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:645:2: iv_ruleOutputReference= ruleOutputReference EOF
            {
             newCompositeNode(grammarAccess.getOutputReferenceRule()); 
            pushFollow(FOLLOW_ruleOutputReference_in_entryRuleOutputReference1328);
            iv_ruleOutputReference=ruleOutputReference();

            state._fsp--;

             current =iv_ruleOutputReference; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutputReference1338); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:652:1: ruleOutputReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleOutputReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:655:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:656:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:656:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:656:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleOutputReference1375); 

                	newLeafNode(otherlv_0, grammarAccess.getOutputReferenceAccess().getRefKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:660:1: ( (otherlv_1= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:661:1: (otherlv_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:661:1: (otherlv_1= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:662:3: otherlv_1= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOutputReferenceRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOutputReference1395); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:681:1: entryRuleLocalOutput returns [EObject current=null] : iv_ruleLocalOutput= ruleLocalOutput EOF ;
    public final EObject entryRuleLocalOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalOutput = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:682:2: (iv_ruleLocalOutput= ruleLocalOutput EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:683:2: iv_ruleLocalOutput= ruleLocalOutput EOF
            {
             newCompositeNode(grammarAccess.getLocalOutputRule()); 
            pushFollow(FOLLOW_ruleLocalOutput_in_entryRuleLocalOutput1431);
            iv_ruleLocalOutput=ruleLocalOutput();

            state._fsp--;

             current =iv_ruleLocalOutput; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalOutput1441); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:690:1: ruleLocalOutput returns [EObject current=null] : ( (lv_path_0_0= RULE_STRING ) ) ;
    public final EObject ruleLocalOutput() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:693:28: ( ( (lv_path_0_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:694:1: ( (lv_path_0_0= RULE_STRING ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:694:1: ( (lv_path_0_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:695:1: (lv_path_0_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:695:1: (lv_path_0_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:696:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalOutput1482); 

            			newLeafNode(lv_path_0_0, grammarAccess.getLocalOutputAccess().getPathSTRINGTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getLocalOutputRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"path",
                    		lv_path_0_0, 
                    		"STRING");
            	    

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:720:1: entryRuleAnalysis returns [EObject current=null] : iv_ruleAnalysis= ruleAnalysis EOF ;
    public final EObject entryRuleAnalysis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysis = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:721:2: (iv_ruleAnalysis= ruleAnalysis EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:722:2: iv_ruleAnalysis= ruleAnalysis EOF
            {
             newCompositeNode(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_ruleAnalysis_in_entryRuleAnalysis1522);
            iv_ruleAnalysis=ruleAnalysis();

            state._fsp--;

             current =iv_ruleAnalysis; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalysis1532); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:729:1: ruleAnalysis returns [EObject current=null] : ( (lv_name_0_0= ruleQualifiedID ) ) ;
    public final EObject ruleAnalysis() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:732:28: ( ( (lv_name_0_0= ruleQualifiedID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:733:1: ( (lv_name_0_0= ruleQualifiedID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:733:1: ( (lv_name_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:734:1: (lv_name_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:734:1: (lv_name_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:735:3: lv_name_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleAnalysis1577);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:759:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:760:2: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:761:2: iv_ruleKIdentifier= ruleKIdentifier EOF
            {
             newCompositeNode(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier1612);
            iv_ruleKIdentifier=ruleKIdentifier();

            state._fsp--;

             current =iv_ruleKIdentifier; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKIdentifier1622); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:768:1: ruleKIdentifier returns [EObject current=null] : ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_persistentEntries_3_0 = null;

        EObject lv_persistentEntries_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:771:28: ( ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:772:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:772:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:772:2: () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:772:2: ()
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:773:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKIdentifierAccess().getKIdentifierAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:778:2: ( (lv_id_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:779:1: (lv_id_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:779:1: (lv_id_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:780:3: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleKIdentifier1673); 

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

            otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleKIdentifier1690); 

                	newLeafNode(otherlv_2, grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:800:1: ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_ID) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:800:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:800:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:801:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:801:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:802:3: lv_persistentEntries_3_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulePersistentEntry_in_ruleKIdentifier1712);
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

                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:818:2: ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==RULE_ID) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:819:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:819:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:820:3: lv_persistentEntries_4_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePersistentEntry_in_ruleKIdentifier1733);
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
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,26,FOLLOW_26_in_ruleKIdentifier1748); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:848:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:849:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:850:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry1784);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistentEntry1794); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:857:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:860:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:861:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:861:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:861:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:861:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:862:1: (lv_key_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:862:1: (lv_key_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:863:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_rulePersistentEntry1840);
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

            otherlv_1=(Token)match(input,27,FOLLOW_27_in_rulePersistentEntry1852); 

                	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getColonKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:883:1: ( (lv_value_2_0= rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:884:1: (lv_value_2_0= rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:884:1: (lv_value_2_0= rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:885:3: lv_value_2_0= rulePropertyValue
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_rulePropertyValue_in_rulePersistentEntry1873);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:909:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:910:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:911:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID1910);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID1921); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:918:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:921:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:922:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:922:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:922:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID1961); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:929:1: (kw= '.' this_ID_2= RULE_ID )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==28) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:930:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,28,FOLLOW_28_in_ruleQualifiedID1980); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID1995); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop18;
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:950:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:951:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:952:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
             newCompositeNode(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue2043);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;

             current =iv_rulePropertyValue.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue2054); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:959:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:962:28: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:963:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:963:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            int alt19=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt19=1;
                }
                break;
            case RULE_STRING:
                {
                alt19=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt19=3;
                }
                break;
            case RULE_ID:
                {
                alt19=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:963:6: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rulePropertyValue2094); 

                    		current.merge(this_BOOLEAN_0);
                        
                     
                        newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:971:10: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePropertyValue2120); 

                    		current.merge(this_STRING_1);
                        
                     
                        newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:980:5: this_Float_2= ruleFloat
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleFloat_in_rulePropertyValue2153);
                    this_Float_2=ruleFloat();

                    state._fsp--;


                    		current.merge(this_Float_2);
                        
                     
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:992:5: this_QualifiedID_3= ruleQualifiedID
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleQualifiedID_in_rulePropertyValue2186);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1010:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1011:2: (iv_ruleFloat= ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1012:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_ruleFloat_in_entryRuleFloat2232);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloat2243); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1019:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1022:28: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1023:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1023:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_TFLOAT) ) {
                alt20=1;
            }
            else if ( (LA20_0==RULE_NATURAL) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1023:6: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FOLLOW_RULE_TFLOAT_in_ruleFloat2283); 

                    		current.merge(this_TFLOAT_0);
                        
                     
                        newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1031:10: this_NATURAL_1= RULE_NATURAL
                    {
                    this_NATURAL_1=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleFloat2309); 

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
    public static final BitSet FOLLOW_12_in_ruleGrana123 = new BitSet(new long[]{0x0000000000006010L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_ruleGrana144 = new BitSet(new long[]{0x0000000000006010L});
    public static final BitSet FOLLOW_13_in_ruleGrana160 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_ruleGlobalOutputRef_in_ruleGrana181 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_14_in_ruleGrana197 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_15_in_ruleGrana216 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGrana255 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_ruleJob_in_ruleGrana279 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleJob_in_entryRuleJob316 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJob326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleJob372 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJob389 = new BitSet(new long[]{0x00000000000E0000L});
    public static final BitSet FOLLOW_17_in_ruleJob412 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_18_in_ruleJob444 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleJob470 = new BitSet(new long[]{0x0000000000900020L});
    public static final BitSet FOLLOW_ruleResource_in_ruleJob491 = new BitSet(new long[]{0x0000000000900020L});
    public static final BitSet FOLLOW_20_in_ruleJob504 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_ruleJob525 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_21_in_ruleJob538 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_ruleAnalysis_in_ruleJob559 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_22_in_ruleJob572 = new BitSet(new long[]{0x0000000000800020L});
    public static final BitSet FOLLOW_ruleOutput_in_ruleJob593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_entryRuleResource629 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResource639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_ruleResource686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_ruleResource713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_entryRuleResourceReference748 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResourceReference758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleResourceReference795 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleResourceReference815 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef852 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalResourceRef862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGlobalResourceRef904 = new BitSet(new long[]{0x0000000000800020L});
    public static final BitSet FOLLOW_ruleLocalResource_in_ruleGlobalResourceRef930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_entryRuleLocalResource966 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalResource976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalResource1018 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleLocalResource1036 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalResource1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutput_in_entryRuleOutput1095 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutput1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutputReference_in_ruleOutput1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_ruleOutput1179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalOutputRef_in_entryRuleGlobalOutputRef1214 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalOutputRef1224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGlobalOutputRef1266 = new BitSet(new long[]{0x0000000000800020L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_ruleGlobalOutputRef1292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutputReference_in_entryRuleOutputReference1328 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutputReference1338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleOutputReference1375 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOutputReference1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_entryRuleLocalOutput1431 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalOutput1441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalOutput1482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_entryRuleAnalysis1522 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalysis1532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleAnalysis1577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier1612 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier1622 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleKIdentifier1673 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleKIdentifier1690 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier1712 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier1733 = new BitSet(new long[]{0x0000000004000010L});
    public static final BitSet FOLLOW_26_in_ruleKIdentifier1748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry1784 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry1794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rulePersistentEntry1840 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_rulePersistentEntry1852 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rulePropertyValue_in_rulePersistentEntry1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID1910 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID1921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID1961 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleQualifiedID1980 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID1995 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue2043 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue2054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rulePropertyValue2094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePropertyValue2120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rulePropertyValue2153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rulePropertyValue2186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat2232 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloat2243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TFLOAT_in_ruleFloat2283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleFloat2309 = new BitSet(new long[]{0x0000000000000002L});

}