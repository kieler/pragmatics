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

                if ( (LA7_0==16||LA7_0==23) ) {
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:192:1: ruleJob returns [EObject current=null] : (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob ) ;
    public final EObject ruleJob() throws RecognitionException {
        EObject current = null;

        EObject this_RegularJob_0 = null;

        EObject this_RangeJob_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:195:28: ( (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:196:1: (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:196:1: (this_RegularJob_0= ruleRegularJob | this_RangeJob_1= ruleRangeJob )
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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:197:5: this_RegularJob_0= ruleRegularJob
                    {
                     
                            newCompositeNode(grammarAccess.getJobAccess().getRegularJobParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleRegularJob_in_ruleJob373);
                    this_RegularJob_0=ruleRegularJob();

                    state._fsp--;

                     
                            current = this_RegularJob_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:207:5: this_RangeJob_1= ruleRangeJob
                    {
                     
                            newCompositeNode(grammarAccess.getJobAccess().getRangeJobParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleRangeJob_in_ruleJob400);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:223:1: entryRuleRegularJob returns [EObject current=null] : iv_ruleRegularJob= ruleRegularJob EOF ;
    public final EObject entryRuleRegularJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRegularJob = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:224:2: (iv_ruleRegularJob= ruleRegularJob EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:225:2: iv_ruleRegularJob= ruleRegularJob EOF
            {
             newCompositeNode(grammarAccess.getRegularJobRule()); 
            pushFollow(FOLLOW_ruleRegularJob_in_entryRuleRegularJob435);
            iv_ruleRegularJob=ruleRegularJob();

            state._fsp--;

             current =iv_ruleRegularJob; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRegularJob445); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:232:1: ruleRegularJob returns [EObject current=null] : (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) ) ) ;
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
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:235:28: ( (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:236:1: (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:236:1: (otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:236:3: otherlv_0= 'job' ( (lv_name_1_0= RULE_ID ) ) ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )? ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )? otherlv_4= 'resources' ( (lv_resources_5_0= ruleResource ) )+ otherlv_6= 'layoutoptions' ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+ otherlv_8= 'analyses' ( (lv_analyses_9_0= ruleAnalysis ) )+ otherlv_10= 'output' ( (lv_output_11_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_ruleRegularJob482); 

                	newLeafNode(otherlv_0, grammarAccess.getRegularJobAccess().getJobKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:240:1: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:241:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:241:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:242:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRegularJob499); 

            			newLeafNode(lv_name_1_0, grammarAccess.getRegularJobAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRegularJobRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:258:2: ( (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==17) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:259:1: (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:259:1: (lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis' )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:260:3: lv_layoutBeforeAnalysis_2_0= 'layoutBeforeAnalysis'
                    {
                    lv_layoutBeforeAnalysis_2_0=(Token)match(input,17,FOLLOW_17_in_ruleRegularJob522); 

                            newLeafNode(lv_layoutBeforeAnalysis_2_0, grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRegularJobRule());
                    	        }
                           		setWithLastConsumed(current, "layoutBeforeAnalysis", true, "layoutBeforeAnalysis");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:273:3: ( (lv_measureExecutionTime_3_0= 'measureExecutionTime' ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==18) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:274:1: (lv_measureExecutionTime_3_0= 'measureExecutionTime' )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:274:1: (lv_measureExecutionTime_3_0= 'measureExecutionTime' )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:275:3: lv_measureExecutionTime_3_0= 'measureExecutionTime'
                    {
                    lv_measureExecutionTime_3_0=(Token)match(input,18,FOLLOW_18_in_ruleRegularJob554); 

                            newLeafNode(lv_measureExecutionTime_3_0, grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRegularJobRule());
                    	        }
                           		setWithLastConsumed(current, "measureExecutionTime", true, "measureExecutionTime");
                    	    

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,19,FOLLOW_19_in_ruleRegularJob580); 

                	newLeafNode(otherlv_4, grammarAccess.getRegularJobAccess().getResourcesKeyword_4());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:292:1: ( (lv_resources_5_0= ruleResource ) )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:293:1: (lv_resources_5_0= ruleResource )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:293:1: (lv_resources_5_0= ruleResource )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:294:3: lv_resources_5_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getResourcesResourceParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleResource_in_ruleRegularJob601);
            	    lv_resources_5_0=ruleResource();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
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
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);

            otherlv_6=(Token)match(input,20,FOLLOW_20_in_ruleRegularJob614); 

                	newLeafNode(otherlv_6, grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:314:1: ( (lv_layoutOptions_7_0= ruleKIdentifier ) )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:315:1: (lv_layoutOptions_7_0= ruleKIdentifier )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:315:1: (lv_layoutOptions_7_0= ruleKIdentifier )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:316:3: lv_layoutOptions_7_0= ruleKIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleKIdentifier_in_ruleRegularJob635);
            	    lv_layoutOptions_7_0=ruleKIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"layoutOptions",
            	            		lv_layoutOptions_7_0, 
            	            		"KIdentifier");
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

            otherlv_8=(Token)match(input,21,FOLLOW_21_in_ruleRegularJob648); 

                	newLeafNode(otherlv_8, grammarAccess.getRegularJobAccess().getAnalysesKeyword_8());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:336:1: ( (lv_analyses_9_0= ruleAnalysis ) )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:337:1: (lv_analyses_9_0= ruleAnalysis )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:337:1: (lv_analyses_9_0= ruleAnalysis )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:338:3: lv_analyses_9_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRegularJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnalysis_in_ruleRegularJob669);
            	    lv_analyses_9_0=ruleAnalysis();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
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
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);

            otherlv_10=(Token)match(input,22,FOLLOW_22_in_ruleRegularJob682); 

                	newLeafNode(otherlv_10, grammarAccess.getRegularJobAccess().getOutputKeyword_10());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:358:1: ( (lv_output_11_0= ruleOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:359:1: (lv_output_11_0= ruleOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:359:1: (lv_output_11_0= ruleOutput )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:360:3: lv_output_11_0= ruleOutput
            {
             
            	        newCompositeNode(grammarAccess.getRegularJobAccess().getOutputOutputParserRuleCall_11_0()); 
            	    
            pushFollow(FOLLOW_ruleOutput_in_ruleRegularJob703);
            lv_output_11_0=ruleOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRegularJobRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_11_0, 
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
    // $ANTLR end "ruleRegularJob"


    // $ANTLR start "entryRuleRangeJob"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:384:1: entryRuleRangeJob returns [EObject current=null] : iv_ruleRangeJob= ruleRangeJob EOF ;
    public final EObject entryRuleRangeJob() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRangeJob = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:385:2: (iv_ruleRangeJob= ruleRangeJob EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:386:2: iv_ruleRangeJob= ruleRangeJob EOF
            {
             newCompositeNode(grammarAccess.getRangeJobRule()); 
            pushFollow(FOLLOW_ruleRangeJob_in_entryRuleRangeJob739);
            iv_ruleRangeJob=ruleRangeJob();

            state._fsp--;

             current =iv_ruleRangeJob; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRangeJob749); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:393:1: ruleRangeJob returns [EObject current=null] : (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) ) ) ;
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
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:396:28: ( (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:397:1: (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:397:1: (otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:397:3: otherlv_0= 'rangejob' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'resources' ( (lv_resources_3_0= ruleResource ) )+ otherlv_4= 'layoutoptions' ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+ otherlv_6= 'analyses' ( (lv_analyses_7_0= ruleAnalysis ) )+ otherlv_8= 'rangeoption' ( (lv_rangeOption_9_0= ruleQualifiedID ) ) ( (lv_rangeValues_10_0= ruleRange ) ) otherlv_11= 'rangeanalysis' ( (lv_rangeAnalysis_12_0= ruleAnalysis ) ) (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )? otherlv_15= 'output' ( (lv_output_16_0= ruleOutput ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleRangeJob786); 

                	newLeafNode(otherlv_0, grammarAccess.getRangeJobAccess().getRangejobKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:401:1: ( (lv_name_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:402:1: (lv_name_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:402:1: (lv_name_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:403:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRangeJob803); 

            			newLeafNode(lv_name_1_0, grammarAccess.getRangeJobAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRangeJobRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,19,FOLLOW_19_in_ruleRangeJob820); 

                	newLeafNode(otherlv_2, grammarAccess.getRangeJobAccess().getResourcesKeyword_2());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:423:1: ( (lv_resources_3_0= ruleResource ) )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:424:1: (lv_resources_3_0= ruleResource )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:424:1: (lv_resources_3_0= ruleResource )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:425:3: lv_resources_3_0= ruleResource
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getResourcesResourceParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleResource_in_ruleRangeJob841);
            	    lv_resources_3_0=ruleResource();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"resources",
            	            		lv_resources_3_0, 
            	            		"Resource");
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

            otherlv_4=(Token)match(input,20,FOLLOW_20_in_ruleRangeJob854); 

                	newLeafNode(otherlv_4, grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_4());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:445:1: ( (lv_layoutOptions_5_0= ruleKIdentifier ) )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:446:1: (lv_layoutOptions_5_0= ruleKIdentifier )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:446:1: (lv_layoutOptions_5_0= ruleKIdentifier )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:447:3: lv_layoutOptions_5_0= ruleKIdentifier
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getLayoutOptionsKIdentifierParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleKIdentifier_in_ruleRangeJob875);
            	    lv_layoutOptions_5_0=ruleKIdentifier();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"layoutOptions",
            	            		lv_layoutOptions_5_0, 
            	            		"KIdentifier");
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

            otherlv_6=(Token)match(input,21,FOLLOW_21_in_ruleRangeJob888); 

                	newLeafNode(otherlv_6, grammarAccess.getRangeJobAccess().getAnalysesKeyword_6());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:467:1: ( (lv_analyses_7_0= ruleAnalysis ) )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:468:1: (lv_analyses_7_0= ruleAnalysis )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:468:1: (lv_analyses_7_0= ruleAnalysis )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:469:3: lv_analyses_7_0= ruleAnalysis
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeJobAccess().getAnalysesAnalysisParserRuleCall_7_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleAnalysis_in_ruleRangeJob909);
            	    lv_analyses_7_0=ruleAnalysis();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"analyses",
            	            		lv_analyses_7_0, 
            	            		"Analysis");
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

            otherlv_8=(Token)match(input,24,FOLLOW_24_in_ruleRangeJob922); 

                	newLeafNode(otherlv_8, grammarAccess.getRangeJobAccess().getRangeoptionKeyword_8());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:489:1: ( (lv_rangeOption_9_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:490:1: (lv_rangeOption_9_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:490:1: (lv_rangeOption_9_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:491:3: lv_rangeOption_9_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIDParserRuleCall_9_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleRangeJob943);
            lv_rangeOption_9_0=ruleQualifiedID();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeOption",
                    		lv_rangeOption_9_0, 
                    		"QualifiedID");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:507:2: ( (lv_rangeValues_10_0= ruleRange ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:508:1: (lv_rangeValues_10_0= ruleRange )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:508:1: (lv_rangeValues_10_0= ruleRange )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:509:3: lv_rangeValues_10_0= ruleRange
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeValuesRangeParserRuleCall_10_0()); 
            	    
            pushFollow(FOLLOW_ruleRange_in_ruleRangeJob964);
            lv_rangeValues_10_0=ruleRange();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeValues",
                    		lv_rangeValues_10_0, 
                    		"Range");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_11=(Token)match(input,25,FOLLOW_25_in_ruleRangeJob976); 

                	newLeafNode(otherlv_11, grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_11());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:529:1: ( (lv_rangeAnalysis_12_0= ruleAnalysis ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:530:1: (lv_rangeAnalysis_12_0= ruleAnalysis )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:530:1: (lv_rangeAnalysis_12_0= ruleAnalysis )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:531:3: lv_rangeAnalysis_12_0= ruleAnalysis
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getRangeAnalysisAnalysisParserRuleCall_12_0()); 
            	    
            pushFollow(FOLLOW_ruleAnalysis_in_ruleRangeJob997);
            lv_rangeAnalysis_12_0=ruleAnalysis();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"rangeAnalysis",
                    		lv_rangeAnalysis_12_0, 
                    		"Analysis");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:547:2: (otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==26) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:547:4: otherlv_13= 'component' ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) )
                    {
                    otherlv_13=(Token)match(input,26,FOLLOW_26_in_ruleRangeJob1010); 

                        	newLeafNode(otherlv_13, grammarAccess.getRangeJobAccess().getComponentKeyword_13_0());
                        
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:551:1: ( (lv_rangeAnalysisComponent_14_0= RULE_NATURAL ) )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:552:1: (lv_rangeAnalysisComponent_14_0= RULE_NATURAL )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:552:1: (lv_rangeAnalysisComponent_14_0= RULE_NATURAL )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:553:3: lv_rangeAnalysisComponent_14_0= RULE_NATURAL
                    {
                    lv_rangeAnalysisComponent_14_0=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleRangeJob1027); 

                    			newLeafNode(lv_rangeAnalysisComponent_14_0, grammarAccess.getRangeJobAccess().getRangeAnalysisComponentNATURALTerminalRuleCall_13_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeJobRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"rangeAnalysisComponent",
                            		lv_rangeAnalysisComponent_14_0, 
                            		"NATURAL");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_15=(Token)match(input,22,FOLLOW_22_in_ruleRangeJob1046); 

                	newLeafNode(otherlv_15, grammarAccess.getRangeJobAccess().getOutputKeyword_14());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:573:1: ( (lv_output_16_0= ruleOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:574:1: (lv_output_16_0= ruleOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:574:1: (lv_output_16_0= ruleOutput )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:575:3: lv_output_16_0= ruleOutput
            {
             
            	        newCompositeNode(grammarAccess.getRangeJobAccess().getOutputOutputParserRuleCall_15_0()); 
            	    
            pushFollow(FOLLOW_ruleOutput_in_ruleRangeJob1067);
            lv_output_16_0=ruleOutput();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeJobRule());
            	        }
                   		set(
                   			current, 
                   			"output",
                    		lv_output_16_0, 
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
    // $ANTLR end "ruleRangeJob"


    // $ANTLR start "entryRuleRange"
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:599:1: entryRuleRange returns [EObject current=null] : iv_ruleRange= ruleRange EOF ;
    public final EObject entryRuleRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRange = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:600:2: (iv_ruleRange= ruleRange EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:601:2: iv_ruleRange= ruleRange EOF
            {
             newCompositeNode(grammarAccess.getRangeRule()); 
            pushFollow(FOLLOW_ruleRange_in_entryRuleRange1103);
            iv_ruleRange=ruleRange();

            state._fsp--;

             current =iv_ruleRange; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRange1113); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:608:1: ruleRange returns [EObject current=null] : (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange ) ;
    public final EObject ruleRange() throws RecognitionException {
        EObject current = null;

        EObject this_FloatRange_0 = null;

        EObject this_IntRange_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:611:28: ( (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:612:1: (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:612:1: (this_FloatRange_0= ruleFloatRange | this_IntRange_1= ruleIntRange )
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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:613:5: this_FloatRange_0= ruleFloatRange
                    {
                     
                            newCompositeNode(grammarAccess.getRangeAccess().getFloatRangeParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleFloatRange_in_ruleRange1160);
                    this_FloatRange_0=ruleFloatRange();

                    state._fsp--;

                     
                            current = this_FloatRange_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:623:5: this_IntRange_1= ruleIntRange
                    {
                     
                            newCompositeNode(grammarAccess.getRangeAccess().getIntRangeParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleIntRange_in_ruleRange1187);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:639:1: entryRuleFloatRange returns [EObject current=null] : iv_ruleFloatRange= ruleFloatRange EOF ;
    public final EObject entryRuleFloatRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatRange = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:640:2: (iv_ruleFloatRange= ruleFloatRange EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:641:2: iv_ruleFloatRange= ruleFloatRange EOF
            {
             newCompositeNode(grammarAccess.getFloatRangeRule()); 
            pushFollow(FOLLOW_ruleFloatRange_in_entryRuleFloatRange1222);
            iv_ruleFloatRange=ruleFloatRange();

            state._fsp--;

             current =iv_ruleFloatRange; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloatRange1232); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:648:1: ruleFloatRange returns [EObject current=null] : (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* ) ;
    public final EObject ruleFloatRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_values_1_0 = null;

        AntlrDatatypeRuleToken lv_values_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:651:28: ( (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:652:1: (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:652:1: (otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )* )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:652:3: otherlv_0= 'floatvalues' ( (lv_values_1_0= ruleFloat ) ) (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )*
            {
            otherlv_0=(Token)match(input,27,FOLLOW_27_in_ruleFloatRange1269); 

                	newLeafNode(otherlv_0, grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:656:1: ( (lv_values_1_0= ruleFloat ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:657:1: (lv_values_1_0= ruleFloat )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:657:1: (lv_values_1_0= ruleFloat )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:658:3: lv_values_1_0= ruleFloat
            {
             
            	        newCompositeNode(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFloat_in_ruleFloatRange1290);
            lv_values_1_0=ruleFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFloatRangeRule());
            	        }
                   		add(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"Float");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:674:2: (otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==28) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:674:4: otherlv_2= ',' ( (lv_values_3_0= ruleFloat ) )
            	    {
            	    otherlv_2=(Token)match(input,28,FOLLOW_28_in_ruleFloatRange1303); 

            	        	newLeafNode(otherlv_2, grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0());
            	        
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:678:1: ( (lv_values_3_0= ruleFloat ) )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:679:1: (lv_values_3_0= ruleFloat )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:679:1: (lv_values_3_0= ruleFloat )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:680:3: lv_values_3_0= ruleFloat
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleFloat_in_ruleFloatRange1324);
            	    lv_values_3_0=ruleFloat();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getFloatRangeRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"Float");
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:704:1: entryRuleIntRange returns [EObject current=null] : iv_ruleIntRange= ruleIntRange EOF ;
    public final EObject entryRuleIntRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRange = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:705:2: (iv_ruleIntRange= ruleIntRange EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:706:2: iv_ruleIntRange= ruleIntRange EOF
            {
             newCompositeNode(grammarAccess.getIntRangeRule()); 
            pushFollow(FOLLOW_ruleIntRange_in_entryRuleIntRange1362);
            iv_ruleIntRange=ruleIntRange();

            state._fsp--;

             current =iv_ruleIntRange; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntRange1372); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:713:1: ruleIntRange returns [EObject current=null] : (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues ) ;
    public final EObject ruleIntRange() throws RecognitionException {
        EObject current = null;

        EObject this_IntRangeRange_0 = null;

        EObject this_IntRangeValues_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:716:28: ( (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:717:1: (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:717:1: (this_IntRangeRange_0= ruleIntRangeRange | this_IntRangeValues_1= ruleIntRangeValues )
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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:718:5: this_IntRangeRange_0= ruleIntRangeRange
                    {
                     
                            newCompositeNode(grammarAccess.getIntRangeAccess().getIntRangeRangeParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleIntRangeRange_in_ruleIntRange1419);
                    this_IntRangeRange_0=ruleIntRangeRange();

                    state._fsp--;

                     
                            current = this_IntRangeRange_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:728:5: this_IntRangeValues_1= ruleIntRangeValues
                    {
                     
                            newCompositeNode(grammarAccess.getIntRangeAccess().getIntRangeValuesParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleIntRangeValues_in_ruleIntRange1446);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:744:1: entryRuleIntRangeValues returns [EObject current=null] : iv_ruleIntRangeValues= ruleIntRangeValues EOF ;
    public final EObject entryRuleIntRangeValues() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRangeValues = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:745:2: (iv_ruleIntRangeValues= ruleIntRangeValues EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:746:2: iv_ruleIntRangeValues= ruleIntRangeValues EOF
            {
             newCompositeNode(grammarAccess.getIntRangeValuesRule()); 
            pushFollow(FOLLOW_ruleIntRangeValues_in_entryRuleIntRangeValues1481);
            iv_ruleIntRangeValues=ruleIntRangeValues();

            state._fsp--;

             current =iv_ruleIntRangeValues; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntRangeValues1491); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:753:1: ruleIntRangeValues returns [EObject current=null] : (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* ) ;
    public final EObject ruleIntRangeValues() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_values_1_0=null;
        Token otherlv_2=null;
        Token lv_values_3_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:756:28: ( (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:757:1: (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:757:1: (otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )* )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:757:3: otherlv_0= 'intvalues' ( (lv_values_1_0= RULE_NATURAL ) ) (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )*
            {
            otherlv_0=(Token)match(input,29,FOLLOW_29_in_ruleIntRangeValues1528); 

                	newLeafNode(otherlv_0, grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:761:1: ( (lv_values_1_0= RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:762:1: (lv_values_1_0= RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:762:1: (lv_values_1_0= RULE_NATURAL )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:763:3: lv_values_1_0= RULE_NATURAL
            {
            lv_values_1_0=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleIntRangeValues1545); 

            			newLeafNode(lv_values_1_0, grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeValuesRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"values",
                    		lv_values_1_0, 
                    		"NATURAL");
            	    

            }


            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:779:2: (otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==28) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:779:4: otherlv_2= ',' ( (lv_values_3_0= RULE_NATURAL ) )
            	    {
            	    otherlv_2=(Token)match(input,28,FOLLOW_28_in_ruleIntRangeValues1563); 

            	        	newLeafNode(otherlv_2, grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0());
            	        
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:783:1: ( (lv_values_3_0= RULE_NATURAL ) )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:784:1: (lv_values_3_0= RULE_NATURAL )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:784:1: (lv_values_3_0= RULE_NATURAL )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:785:3: lv_values_3_0= RULE_NATURAL
            	    {
            	    lv_values_3_0=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleIntRangeValues1580); 

            	    			newLeafNode(lv_values_3_0, grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_2_1_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getIntRangeValuesRule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"values",
            	            		lv_values_3_0, 
            	            		"NATURAL");
            	    	    

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:809:1: entryRuleIntRangeRange returns [EObject current=null] : iv_ruleIntRangeRange= ruleIntRangeRange EOF ;
    public final EObject entryRuleIntRangeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntRangeRange = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:810:2: (iv_ruleIntRangeRange= ruleIntRangeRange EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:811:2: iv_ruleIntRangeRange= ruleIntRangeRange EOF
            {
             newCompositeNode(grammarAccess.getIntRangeRangeRule()); 
            pushFollow(FOLLOW_ruleIntRangeRange_in_entryRuleIntRangeRange1623);
            iv_ruleIntRangeRange=ruleIntRangeRange();

            state._fsp--;

             current =iv_ruleIntRangeRange; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntRangeRange1633); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:818:1: ruleIntRangeRange returns [EObject current=null] : (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) ) ;
    public final EObject ruleIntRangeRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_start_1_0=null;
        Token otherlv_2=null;
        Token lv_end_3_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:821:28: ( (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:822:1: (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:822:1: (otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:822:3: otherlv_0= 'intrange' ( (lv_start_1_0= RULE_NATURAL ) ) otherlv_2= 'to' ( (lv_end_3_0= RULE_NATURAL ) )
            {
            otherlv_0=(Token)match(input,30,FOLLOW_30_in_ruleIntRangeRange1670); 

                	newLeafNode(otherlv_0, grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:826:1: ( (lv_start_1_0= RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:827:1: (lv_start_1_0= RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:827:1: (lv_start_1_0= RULE_NATURAL )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:828:3: lv_start_1_0= RULE_NATURAL
            {
            lv_start_1_0=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleIntRangeRange1687); 

            			newLeafNode(lv_start_1_0, grammarAccess.getIntRangeRangeAccess().getStartNATURALTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeRangeRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"start",
                    		lv_start_1_0, 
                    		"NATURAL");
            	    

            }


            }

            otherlv_2=(Token)match(input,31,FOLLOW_31_in_ruleIntRangeRange1704); 

                	newLeafNode(otherlv_2, grammarAccess.getIntRangeRangeAccess().getToKeyword_2());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:848:1: ( (lv_end_3_0= RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:849:1: (lv_end_3_0= RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:849:1: (lv_end_3_0= RULE_NATURAL )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:850:3: lv_end_3_0= RULE_NATURAL
            {
            lv_end_3_0=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleIntRangeRange1721); 

            			newLeafNode(lv_end_3_0, grammarAccess.getIntRangeRangeAccess().getEndNATURALTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getIntRangeRangeRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"end",
                    		lv_end_3_0, 
                    		"NATURAL");
            	    

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:874:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:875:2: (iv_ruleResource= ruleResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:876:2: iv_ruleResource= ruleResource EOF
            {
             newCompositeNode(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_ruleResource_in_entryRuleResource1762);
            iv_ruleResource=ruleResource();

            state._fsp--;

             current =iv_ruleResource; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResource1772); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:883:1: ruleResource returns [EObject current=null] : (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        EObject this_ResourceReference_0 = null;

        EObject this_LocalResource_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:886:28: ( (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:887:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:887:1: (this_ResourceReference_0= ruleResourceReference | this_LocalResource_1= ruleLocalResource )
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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:888:5: this_ResourceReference_0= ruleResourceReference
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleResourceReference_in_ruleResource1819);
                    this_ResourceReference_0=ruleResourceReference();

                    state._fsp--;

                     
                            current = this_ResourceReference_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:898:5: this_LocalResource_1= ruleLocalResource
                    {
                     
                            newCompositeNode(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLocalResource_in_ruleResource1846);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:914:1: entryRuleResourceReference returns [EObject current=null] : iv_ruleResourceReference= ruleResourceReference EOF ;
    public final EObject entryRuleResourceReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceReference = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:915:2: (iv_ruleResourceReference= ruleResourceReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:916:2: iv_ruleResourceReference= ruleResourceReference EOF
            {
             newCompositeNode(grammarAccess.getResourceReferenceRule()); 
            pushFollow(FOLLOW_ruleResourceReference_in_entryRuleResourceReference1881);
            iv_ruleResourceReference=ruleResourceReference();

            state._fsp--;

             current =iv_ruleResourceReference; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResourceReference1891); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:923:1: ruleResourceReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) ;
    public final EObject ruleResourceReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:926:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:927:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:927:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+ )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:927:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )+
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleResourceReference1928); 

                	newLeafNode(otherlv_0, grammarAccess.getResourceReferenceAccess().getRefKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:931:1: ( (otherlv_1= RULE_ID ) )+
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
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:932:1: (otherlv_1= RULE_ID )
            	    {
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:932:1: (otherlv_1= RULE_ID )
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:933:3: otherlv_1= RULE_ID
            	    {

            	    			if (current==null) {
            	    	            current = createModelElement(grammarAccess.getResourceReferenceRule());
            	    	        }
            	            
            	    otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleResourceReference1948); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:952:1: entryRuleGlobalResourceRef returns [EObject current=null] : iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF ;
    public final EObject entryRuleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalResourceRef = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:953:2: (iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:954:2: iv_ruleGlobalResourceRef= ruleGlobalResourceRef EOF
            {
             newCompositeNode(grammarAccess.getGlobalResourceRefRule()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef1985);
            iv_ruleGlobalResourceRef=ruleGlobalResourceRef();

            state._fsp--;

             current =iv_ruleGlobalResourceRef; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalResourceRef1995); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:961:1: ruleGlobalResourceRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) ;
    public final EObject ruleGlobalResourceRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_resources_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:964:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:965:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:965:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:965:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_resources_1_0= ruleLocalResource ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:965:2: ( (lv_name_0_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:966:1: (lv_name_0_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:966:1: (lv_name_0_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:967:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGlobalResourceRef2037); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:983:2: ( (lv_resources_1_0= ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:984:1: (lv_resources_1_0= ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:984:1: (lv_resources_1_0= ruleLocalResource )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:985:3: lv_resources_1_0= ruleLocalResource
            {
             
            	        newCompositeNode(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLocalResource_in_ruleGlobalResourceRef2063);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1009:1: entryRuleLocalResource returns [EObject current=null] : iv_ruleLocalResource= ruleLocalResource EOF ;
    public final EObject entryRuleLocalResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalResource = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1010:2: (iv_ruleLocalResource= ruleLocalResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1011:2: iv_ruleLocalResource= ruleLocalResource EOF
            {
             newCompositeNode(grammarAccess.getLocalResourceRule()); 
            pushFollow(FOLLOW_ruleLocalResource_in_entryRuleLocalResource2099);
            iv_ruleLocalResource=ruleLocalResource();

            state._fsp--;

             current =iv_ruleLocalResource; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalResource2109); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1018:1: ruleLocalResource returns [EObject current=null] : ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleLocalResource() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;
        Token otherlv_1=null;
        Token lv_filter_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1021:28: ( ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1022:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1022:1: ( ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1022:2: ( (lv_path_0_0= RULE_STRING ) ) (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1022:2: ( (lv_path_0_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1023:1: (lv_path_0_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1023:1: (lv_path_0_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1024:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalResource2151); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1040:2: (otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1040:4: otherlv_1= 'filter' ( (lv_filter_2_0= RULE_STRING ) )
            {
            otherlv_1=(Token)match(input,33,FOLLOW_33_in_ruleLocalResource2169); 

                	newLeafNode(otherlv_1, grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1044:1: ( (lv_filter_2_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1045:1: (lv_filter_2_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1045:1: (lv_filter_2_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1046:3: lv_filter_2_0= RULE_STRING
            {
            lv_filter_2_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalResource2186); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1070:1: entryRuleOutput returns [EObject current=null] : iv_ruleOutput= ruleOutput EOF ;
    public final EObject entryRuleOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutput = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1071:2: (iv_ruleOutput= ruleOutput EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1072:2: iv_ruleOutput= ruleOutput EOF
            {
             newCompositeNode(grammarAccess.getOutputRule()); 
            pushFollow(FOLLOW_ruleOutput_in_entryRuleOutput2228);
            iv_ruleOutput=ruleOutput();

            state._fsp--;

             current =iv_ruleOutput; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutput2238); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1079:1: ruleOutput returns [EObject current=null] : (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) ;
    public final EObject ruleOutput() throws RecognitionException {
        EObject current = null;

        EObject this_OutputReference_0 = null;

        EObject this_LocalOutput_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1082:28: ( (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1083:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1083:1: (this_OutputReference_0= ruleOutputReference | this_LocalOutput_1= ruleLocalOutput )
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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1084:5: this_OutputReference_0= ruleOutputReference
                    {
                     
                            newCompositeNode(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleOutputReference_in_ruleOutput2285);
                    this_OutputReference_0=ruleOutputReference();

                    state._fsp--;

                     
                            current = this_OutputReference_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1094:5: this_LocalOutput_1= ruleLocalOutput
                    {
                     
                            newCompositeNode(grammarAccess.getOutputAccess().getLocalOutputParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleLocalOutput_in_ruleOutput2312);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1110:1: entryRuleGlobalOutputRef returns [EObject current=null] : iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF ;
    public final EObject entryRuleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalOutputRef = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1111:2: (iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1112:2: iv_ruleGlobalOutputRef= ruleGlobalOutputRef EOF
            {
             newCompositeNode(grammarAccess.getGlobalOutputRefRule()); 
            pushFollow(FOLLOW_ruleGlobalOutputRef_in_entryRuleGlobalOutputRef2347);
            iv_ruleGlobalOutputRef=ruleGlobalOutputRef();

            state._fsp--;

             current =iv_ruleGlobalOutputRef; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalOutputRef2357); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1119:1: ruleGlobalOutputRef returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) ;
    public final EObject ruleGlobalOutputRef() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        EObject lv_output_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1122:28: ( ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1123:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1123:1: ( ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1123:2: ( (lv_name_0_0= RULE_ID ) ) ( (lv_output_1_0= ruleLocalOutput ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1123:2: ( (lv_name_0_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1124:1: (lv_name_0_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1124:1: (lv_name_0_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1125:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGlobalOutputRef2399); 

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

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1141:2: ( (lv_output_1_0= ruleLocalOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1142:1: (lv_output_1_0= ruleLocalOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1142:1: (lv_output_1_0= ruleLocalOutput )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1143:3: lv_output_1_0= ruleLocalOutput
            {
             
            	        newCompositeNode(grammarAccess.getGlobalOutputRefAccess().getOutputLocalOutputParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleLocalOutput_in_ruleGlobalOutputRef2425);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1167:1: entryRuleOutputReference returns [EObject current=null] : iv_ruleOutputReference= ruleOutputReference EOF ;
    public final EObject entryRuleOutputReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputReference = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1168:2: (iv_ruleOutputReference= ruleOutputReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1169:2: iv_ruleOutputReference= ruleOutputReference EOF
            {
             newCompositeNode(grammarAccess.getOutputReferenceRule()); 
            pushFollow(FOLLOW_ruleOutputReference_in_entryRuleOutputReference2461);
            iv_ruleOutputReference=ruleOutputReference();

            state._fsp--;

             current =iv_ruleOutputReference; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutputReference2471); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1176:1: ruleOutputReference returns [EObject current=null] : (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleOutputReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1179:28: ( (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1180:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1180:1: (otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1180:3: otherlv_0= 'ref' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,32,FOLLOW_32_in_ruleOutputReference2508); 

                	newLeafNode(otherlv_0, grammarAccess.getOutputReferenceAccess().getRefKeyword_0());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1184:1: ( (otherlv_1= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1185:1: (otherlv_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1185:1: (otherlv_1= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1186:3: otherlv_1= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getOutputReferenceRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleOutputReference2528); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1205:1: entryRuleLocalOutput returns [EObject current=null] : iv_ruleLocalOutput= ruleLocalOutput EOF ;
    public final EObject entryRuleLocalOutput() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocalOutput = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1206:2: (iv_ruleLocalOutput= ruleLocalOutput EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1207:2: iv_ruleLocalOutput= ruleLocalOutput EOF
            {
             newCompositeNode(grammarAccess.getLocalOutputRule()); 
            pushFollow(FOLLOW_ruleLocalOutput_in_entryRuleLocalOutput2564);
            iv_ruleLocalOutput=ruleLocalOutput();

            state._fsp--;

             current =iv_ruleLocalOutput; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalOutput2574); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1214:1: ruleLocalOutput returns [EObject current=null] : ( (lv_path_0_0= RULE_STRING ) ) ;
    public final EObject ruleLocalOutput() throws RecognitionException {
        EObject current = null;

        Token lv_path_0_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1217:28: ( ( (lv_path_0_0= RULE_STRING ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1218:1: ( (lv_path_0_0= RULE_STRING ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1218:1: ( (lv_path_0_0= RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1219:1: (lv_path_0_0= RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1219:1: (lv_path_0_0= RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1220:3: lv_path_0_0= RULE_STRING
            {
            lv_path_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleLocalOutput2615); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1244:1: entryRuleAnalysis returns [EObject current=null] : iv_ruleAnalysis= ruleAnalysis EOF ;
    public final EObject entryRuleAnalysis() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnalysis = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1245:2: (iv_ruleAnalysis= ruleAnalysis EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1246:2: iv_ruleAnalysis= ruleAnalysis EOF
            {
             newCompositeNode(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_ruleAnalysis_in_entryRuleAnalysis2655);
            iv_ruleAnalysis=ruleAnalysis();

            state._fsp--;

             current =iv_ruleAnalysis; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalysis2665); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1253:1: ruleAnalysis returns [EObject current=null] : ( (lv_name_0_0= ruleQualifiedID ) ) ;
    public final EObject ruleAnalysis() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1256:28: ( ( (lv_name_0_0= ruleQualifiedID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1257:1: ( (lv_name_0_0= ruleQualifiedID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1257:1: ( (lv_name_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1258:1: (lv_name_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1258:1: (lv_name_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1259:3: lv_name_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleAnalysis2710);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1283:1: entryRuleKIdentifier returns [EObject current=null] : iv_ruleKIdentifier= ruleKIdentifier EOF ;
    public final EObject entryRuleKIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKIdentifier = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1284:2: (iv_ruleKIdentifier= ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1285:2: iv_ruleKIdentifier= ruleKIdentifier EOF
            {
             newCompositeNode(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier2745);
            iv_ruleKIdentifier=ruleKIdentifier();

            state._fsp--;

             current =iv_ruleKIdentifier; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKIdentifier2755); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1292:1: ruleKIdentifier returns [EObject current=null] : ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleKIdentifier() throws RecognitionException {
        EObject current = null;

        Token lv_id_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_persistentEntries_3_0 = null;

        EObject lv_persistentEntries_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1295:28: ( ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1296:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1296:1: ( () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}' )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1296:2: () ( (lv_id_1_0= RULE_ID ) ) otherlv_2= '{' ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )? otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1296:2: ()
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1297:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKIdentifierAccess().getKIdentifierAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1302:2: ( (lv_id_1_0= RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1303:1: (lv_id_1_0= RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1303:1: (lv_id_1_0= RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1304:3: lv_id_1_0= RULE_ID
            {
            lv_id_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleKIdentifier2806); 

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

            otherlv_2=(Token)match(input,34,FOLLOW_34_in_ruleKIdentifier2823); 

                	newLeafNode(otherlv_2, grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1324:1: ( ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )* )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1324:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) ) ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1324:2: ( (lv_persistentEntries_3_0= rulePersistentEntry ) )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1325:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1325:1: (lv_persistentEntries_3_0= rulePersistentEntry )
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1326:3: lv_persistentEntries_3_0= rulePersistentEntry
                    {
                     
                    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_rulePersistentEntry_in_ruleKIdentifier2845);
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

                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1342:2: ( (lv_persistentEntries_4_0= rulePersistentEntry ) )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==RULE_ID) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1343:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    {
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1343:1: (lv_persistentEntries_4_0= rulePersistentEntry )
                    	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1344:3: lv_persistentEntries_4_0= rulePersistentEntry
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePersistentEntry_in_ruleKIdentifier2866);
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
                    	    break loop25;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,35,FOLLOW_35_in_ruleKIdentifier2881); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1372:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1373:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1374:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry2917);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistentEntry2927); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1381:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1384:28: ( ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1385:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1385:1: ( ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1385:2: ( (lv_key_0_0= ruleQualifiedID ) ) otherlv_1= ':' ( (lv_value_2_0= rulePropertyValue ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1385:2: ( (lv_key_0_0= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1386:1: (lv_key_0_0= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1386:1: (lv_key_0_0= ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1387:3: lv_key_0_0= ruleQualifiedID
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_rulePersistentEntry2973);
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

            otherlv_1=(Token)match(input,36,FOLLOW_36_in_rulePersistentEntry2985); 

                	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getColonKeyword_1());
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1407:1: ( (lv_value_2_0= rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1408:1: (lv_value_2_0= rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1408:1: (lv_value_2_0= rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1409:3: lv_value_2_0= rulePropertyValue
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_rulePropertyValue_in_rulePersistentEntry3006);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1433:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1434:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1435:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID3043);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID3054); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1442:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1445:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1446:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1446:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1446:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID3094); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1453:1: (kw= '.' this_ID_2= RULE_ID )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==37) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1454:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,37,FOLLOW_37_in_ruleQualifiedID3113); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID3128); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1474:1: entryRulePropertyValue returns [String current=null] : iv_rulePropertyValue= rulePropertyValue EOF ;
    public final String entryRulePropertyValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePropertyValue = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1475:2: (iv_rulePropertyValue= rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1476:2: iv_rulePropertyValue= rulePropertyValue EOF
            {
             newCompositeNode(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue3176);
            iv_rulePropertyValue=rulePropertyValue();

            state._fsp--;

             current =iv_rulePropertyValue.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue3187); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1483:1: rulePropertyValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) ;
    public final AntlrDatatypeRuleToken rulePropertyValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_BOOLEAN_0=null;
        Token this_STRING_1=null;
        AntlrDatatypeRuleToken this_Float_2 = null;

        AntlrDatatypeRuleToken this_QualifiedID_3 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1486:28: ( (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1487:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1487:1: (this_BOOLEAN_0= RULE_BOOLEAN | this_STRING_1= RULE_STRING | this_Float_2= ruleFloat | this_QualifiedID_3= ruleQualifiedID )
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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1487:6: this_BOOLEAN_0= RULE_BOOLEAN
                    {
                    this_BOOLEAN_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rulePropertyValue3227); 

                    		current.merge(this_BOOLEAN_0);
                        
                     
                        newLeafNode(this_BOOLEAN_0, grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1495:10: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rulePropertyValue3253); 

                    		current.merge(this_STRING_1);
                        
                     
                        newLeafNode(this_STRING_1, grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1504:5: this_Float_2= ruleFloat
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleFloat_in_rulePropertyValue3286);
                    this_Float_2=ruleFloat();

                    state._fsp--;


                    		current.merge(this_Float_2);
                        
                     
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1516:5: this_QualifiedID_3= ruleQualifiedID
                    {
                     
                            newCompositeNode(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleQualifiedID_in_rulePropertyValue3319);
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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1534:1: entryRuleFloat returns [String current=null] : iv_ruleFloat= ruleFloat EOF ;
    public final String entryRuleFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFloat = null;


        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1535:2: (iv_ruleFloat= ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1536:2: iv_ruleFloat= ruleFloat EOF
            {
             newCompositeNode(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_ruleFloat_in_entryRuleFloat3365);
            iv_ruleFloat=ruleFloat();

            state._fsp--;

             current =iv_ruleFloat.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloat3376); 

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
    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1543:1: ruleFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) ;
    public final AntlrDatatypeRuleToken ruleFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TFLOAT_0=null;
        Token this_NATURAL_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1546:28: ( (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1547:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1547:1: (this_TFLOAT_0= RULE_TFLOAT | this_NATURAL_1= RULE_NATURAL )
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
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1547:6: this_TFLOAT_0= RULE_TFLOAT
                    {
                    this_TFLOAT_0=(Token)match(input,RULE_TFLOAT,FOLLOW_RULE_TFLOAT_in_ruleFloat3416); 

                    		current.merge(this_TFLOAT_0);
                        
                     
                        newLeafNode(this_TFLOAT_0, grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text/src-gen/de/cau/cs/kieler/kiml/grana/text/parser/antlr/internal/InternalGrana.g:1555:10: this_NATURAL_1= RULE_NATURAL
                    {
                    this_NATURAL_1=(Token)match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_ruleFloat3442); 

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
    public static final BitSet FOLLOW_15_in_ruleGrana216 = new BitSet(new long[]{0x0000000000810000L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGrana255 = new BitSet(new long[]{0x0000000000810010L});
    public static final BitSet FOLLOW_ruleJob_in_ruleGrana279 = new BitSet(new long[]{0x0000000000810002L});
    public static final BitSet FOLLOW_ruleJob_in_entryRuleJob316 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJob326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRegularJob_in_ruleJob373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRangeJob_in_ruleJob400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRegularJob_in_entryRuleRegularJob435 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRegularJob445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleRegularJob482 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRegularJob499 = new BitSet(new long[]{0x00000000000E0000L});
    public static final BitSet FOLLOW_17_in_ruleRegularJob522 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_18_in_ruleRegularJob554 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleRegularJob580 = new BitSet(new long[]{0x0000000100000040L});
    public static final BitSet FOLLOW_ruleResource_in_ruleRegularJob601 = new BitSet(new long[]{0x0000000100100040L});
    public static final BitSet FOLLOW_20_in_ruleRegularJob614 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_ruleRegularJob635 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_21_in_ruleRegularJob648 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAnalysis_in_ruleRegularJob669 = new BitSet(new long[]{0x0000000000400010L});
    public static final BitSet FOLLOW_22_in_ruleRegularJob682 = new BitSet(new long[]{0x0000000100000040L});
    public static final BitSet FOLLOW_ruleOutput_in_ruleRegularJob703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRangeJob_in_entryRuleRangeJob739 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRangeJob749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleRangeJob786 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRangeJob803 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleRangeJob820 = new BitSet(new long[]{0x0000000100000040L});
    public static final BitSet FOLLOW_ruleResource_in_ruleRangeJob841 = new BitSet(new long[]{0x0000000100100040L});
    public static final BitSet FOLLOW_20_in_ruleRangeJob854 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_ruleRangeJob875 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_21_in_ruleRangeJob888 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAnalysis_in_ruleRangeJob909 = new BitSet(new long[]{0x0000000001000010L});
    public static final BitSet FOLLOW_24_in_ruleRangeJob922 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleRangeJob943 = new BitSet(new long[]{0x0000000068000000L});
    public static final BitSet FOLLOW_ruleRange_in_ruleRangeJob964 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleRangeJob976 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAnalysis_in_ruleRangeJob997 = new BitSet(new long[]{0x0000000004400000L});
    public static final BitSet FOLLOW_26_in_ruleRangeJob1010 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleRangeJob1027 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleRangeJob1046 = new BitSet(new long[]{0x0000000100000040L});
    public static final BitSet FOLLOW_ruleOutput_in_ruleRangeJob1067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRange_in_entryRuleRange1103 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRange1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloatRange_in_ruleRange1160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRange_in_ruleRange1187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloatRange_in_entryRuleFloatRange1222 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloatRange1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleFloatRange1269 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_ruleFloat_in_ruleFloatRange1290 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleFloatRange1303 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_ruleFloat_in_ruleFloatRange1324 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_ruleIntRange_in_entryRuleIntRange1362 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntRange1372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRangeRange_in_ruleIntRange1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRangeValues_in_ruleIntRange1446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRangeValues_in_entryRuleIntRangeValues1481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntRangeValues1491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleIntRangeValues1528 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleIntRangeValues1545 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleIntRangeValues1563 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleIntRangeValues1580 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_ruleIntRangeRange_in_entryRuleIntRangeRange1623 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntRangeRange1633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleIntRangeRange1670 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleIntRangeRange1687 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleIntRangeRange1704 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleIntRangeRange1721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_entryRuleResource1762 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResource1772 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_ruleResource1819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_ruleResource1846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_entryRuleResourceReference1881 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResourceReference1891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleResourceReference1928 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleResourceReference1948 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef1985 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalResourceRef1995 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGlobalResourceRef2037 = new BitSet(new long[]{0x0000000100000040L});
    public static final BitSet FOLLOW_ruleLocalResource_in_ruleGlobalResourceRef2063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_entryRuleLocalResource2099 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalResource2109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalResource2151 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleLocalResource2169 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalResource2186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutput_in_entryRuleOutput2228 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutput2238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutputReference_in_ruleOutput2285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_ruleOutput2312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalOutputRef_in_entryRuleGlobalOutputRef2347 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalOutputRef2357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGlobalOutputRef2399 = new BitSet(new long[]{0x0000000100000040L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_ruleGlobalOutputRef2425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutputReference_in_entryRuleOutputReference2461 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutputReference2471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleOutputReference2508 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleOutputReference2528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_entryRuleLocalOutput2564 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalOutput2574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleLocalOutput2615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_entryRuleAnalysis2655 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalysis2665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleAnalysis2710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier2745 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier2755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleKIdentifier2806 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleKIdentifier2823 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier2845 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKIdentifier2866 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_35_in_ruleKIdentifier2881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry2917 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry2927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rulePersistentEntry2973 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_rulePersistentEntry2985 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rulePropertyValue_in_rulePersistentEntry3006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID3043 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID3054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID3094 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_ruleQualifiedID3113 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID3128 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue3176 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue3187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rulePropertyValue3227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rulePropertyValue3253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rulePropertyValue3286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rulePropertyValue3319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat3365 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloat3376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TFLOAT_in_ruleFloat3416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_ruleFloat3442 = new BitSet(new long[]{0x0000000000000002L});

}