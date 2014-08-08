package de.cau.cs.kieler.kiml.grana.text.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import de.cau.cs.kieler.kiml.grana.text.services.GranaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGranaParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_BOOLEAN", "RULE_STRING", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'job'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'filter'", "'{'", "'}'", "':'", "'.'", "'layoutBeforeAnalysis'"
    };
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_ID=8;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_NATURAL=7;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=9;
    public static final int RULE_TFLOAT=6;
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
    public String getGrammarFileName() { return "../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g"; }


     
     	private GranaGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(GranaGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleGrana"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:60:1: entryRuleGrana : ruleGrana EOF ;
    public final void entryRuleGrana() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:61:1: ( ruleGrana EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:62:1: ruleGrana EOF
            {
             before(grammarAccess.getGranaRule()); 
            pushFollow(FOLLOW_ruleGrana_in_entryRuleGrana61);
            ruleGrana();

            state._fsp--;

             after(grammarAccess.getGranaRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGrana68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGrana"


    // $ANTLR start "ruleGrana"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:69:1: ruleGrana : ( ( rule__Grana__JobsAssignment )* ) ;
    public final void ruleGrana() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:73:2: ( ( ( rule__Grana__JobsAssignment )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:74:1: ( ( rule__Grana__JobsAssignment )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:74:1: ( ( rule__Grana__JobsAssignment )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:75:1: ( rule__Grana__JobsAssignment )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:76:1: ( rule__Grana__JobsAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:76:2: rule__Grana__JobsAssignment
            	    {
            	    pushFollow(FOLLOW_rule__Grana__JobsAssignment_in_ruleGrana94);
            	    rule__Grana__JobsAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getGranaAccess().getJobsAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGrana"


    // $ANTLR start "entryRuleJob"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:88:1: entryRuleJob : ruleJob EOF ;
    public final void entryRuleJob() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:89:1: ( ruleJob EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:90:1: ruleJob EOF
            {
             before(grammarAccess.getJobRule()); 
            pushFollow(FOLLOW_ruleJob_in_entryRuleJob122);
            ruleJob();

            state._fsp--;

             after(grammarAccess.getJobRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJob129); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleJob"


    // $ANTLR start "ruleJob"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:97:1: ruleJob : ( ( rule__Job__Group__0 ) ) ;
    public final void ruleJob() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:101:2: ( ( ( rule__Job__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:102:1: ( ( rule__Job__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:102:1: ( ( rule__Job__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:103:1: ( rule__Job__Group__0 )
            {
             before(grammarAccess.getJobAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:104:1: ( rule__Job__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:104:2: rule__Job__Group__0
            {
            pushFollow(FOLLOW_rule__Job__Group__0_in_ruleJob155);
            rule__Job__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getJobAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleJob"


    // $ANTLR start "entryRuleResource"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:116:1: entryRuleResource : ruleResource EOF ;
    public final void entryRuleResource() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:117:1: ( ruleResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:118:1: ruleResource EOF
            {
             before(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_ruleResource_in_entryRuleResource182);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getResourceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResource189); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleResource"


    // $ANTLR start "ruleResource"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:125:1: ruleResource : ( ( rule__Resource__Group__0 ) ) ;
    public final void ruleResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:129:2: ( ( ( rule__Resource__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:130:1: ( ( rule__Resource__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:130:1: ( ( rule__Resource__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:131:1: ( rule__Resource__Group__0 )
            {
             before(grammarAccess.getResourceAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:132:1: ( rule__Resource__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:132:2: rule__Resource__Group__0
            {
            pushFollow(FOLLOW_rule__Resource__Group__0_in_ruleResource215);
            rule__Resource__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getResourceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleResource"


    // $ANTLR start "entryRuleAnalysis"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:144:1: entryRuleAnalysis : ruleAnalysis EOF ;
    public final void entryRuleAnalysis() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:145:1: ( ruleAnalysis EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:146:1: ruleAnalysis EOF
            {
             before(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_ruleAnalysis_in_entryRuleAnalysis242);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getAnalysisRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalysis249); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAnalysis"


    // $ANTLR start "ruleAnalysis"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:153:1: ruleAnalysis : ( ( rule__Analysis__NameAssignment ) ) ;
    public final void ruleAnalysis() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:157:2: ( ( ( rule__Analysis__NameAssignment ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:158:1: ( ( rule__Analysis__NameAssignment ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:158:1: ( ( rule__Analysis__NameAssignment ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:159:1: ( rule__Analysis__NameAssignment )
            {
             before(grammarAccess.getAnalysisAccess().getNameAssignment()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:160:1: ( rule__Analysis__NameAssignment )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:160:2: rule__Analysis__NameAssignment
            {
            pushFollow(FOLLOW_rule__Analysis__NameAssignment_in_ruleAnalysis275);
            rule__Analysis__NameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getAnalysisAccess().getNameAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAnalysis"


    // $ANTLR start "entryRuleKIdentifier"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:172:1: entryRuleKIdentifier : ruleKIdentifier EOF ;
    public final void entryRuleKIdentifier() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:173:1: ( ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:174:1: ruleKIdentifier EOF
            {
             before(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier302);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getKIdentifierRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKIdentifier309); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleKIdentifier"


    // $ANTLR start "ruleKIdentifier"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:181:1: ruleKIdentifier : ( ( rule__KIdentifier__Group__0 ) ) ;
    public final void ruleKIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:185:2: ( ( ( rule__KIdentifier__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:186:1: ( ( rule__KIdentifier__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:186:1: ( ( rule__KIdentifier__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:187:1: ( rule__KIdentifier__Group__0 )
            {
             before(grammarAccess.getKIdentifierAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:188:1: ( rule__KIdentifier__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:188:2: rule__KIdentifier__Group__0
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier335);
            rule__KIdentifier__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getKIdentifierAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKIdentifier"


    // $ANTLR start "entryRulePersistentEntry"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:200:1: entryRulePersistentEntry : rulePersistentEntry EOF ;
    public final void entryRulePersistentEntry() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:201:1: ( rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:202:1: rulePersistentEntry EOF
            {
             before(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry362);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getPersistentEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistentEntry369); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePersistentEntry"


    // $ANTLR start "rulePersistentEntry"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:209:1: rulePersistentEntry : ( ( rule__PersistentEntry__Group__0 ) ) ;
    public final void rulePersistentEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:213:2: ( ( ( rule__PersistentEntry__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:214:1: ( ( rule__PersistentEntry__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:214:1: ( ( rule__PersistentEntry__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:215:1: ( rule__PersistentEntry__Group__0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:216:1: ( rule__PersistentEntry__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:216:2: rule__PersistentEntry__Group__0
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry395);
            rule__PersistentEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPersistentEntryAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePersistentEntry"


    // $ANTLR start "entryRuleQualifiedID"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:228:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:229:1: ( ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:230:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID422);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID429); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQualifiedID"


    // $ANTLR start "ruleQualifiedID"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:237:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:241:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:242:1: ( ( rule__QualifiedID__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:242:1: ( ( rule__QualifiedID__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:243:1: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:244:1: ( rule__QualifiedID__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:244:2: rule__QualifiedID__Group__0
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID455);
            rule__QualifiedID__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedIDAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQualifiedID"


    // $ANTLR start "entryRulePropertyValue"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:256:1: entryRulePropertyValue : rulePropertyValue EOF ;
    public final void entryRulePropertyValue() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:257:1: ( rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:258:1: rulePropertyValue EOF
            {
             before(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue482);
            rulePropertyValue();

            state._fsp--;

             after(grammarAccess.getPropertyValueRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue489); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePropertyValue"


    // $ANTLR start "rulePropertyValue"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:265:1: rulePropertyValue : ( ( rule__PropertyValue__Alternatives ) ) ;
    public final void rulePropertyValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:269:2: ( ( ( rule__PropertyValue__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:270:1: ( ( rule__PropertyValue__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:270:1: ( ( rule__PropertyValue__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:271:1: ( rule__PropertyValue__Alternatives )
            {
             before(grammarAccess.getPropertyValueAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:272:1: ( rule__PropertyValue__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:272:2: rule__PropertyValue__Alternatives
            {
            pushFollow(FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue515);
            rule__PropertyValue__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPropertyValueAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePropertyValue"


    // $ANTLR start "entryRuleFloat"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:284:1: entryRuleFloat : ruleFloat EOF ;
    public final void entryRuleFloat() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:285:1: ( ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:286:1: ruleFloat EOF
            {
             before(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_ruleFloat_in_entryRuleFloat542);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloat549); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFloat"


    // $ANTLR start "ruleFloat"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:293:1: ruleFloat : ( ( rule__Float__Alternatives ) ) ;
    public final void ruleFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:297:2: ( ( ( rule__Float__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:298:1: ( ( rule__Float__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:298:1: ( ( rule__Float__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:299:1: ( rule__Float__Alternatives )
            {
             before(grammarAccess.getFloatAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:300:1: ( rule__Float__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:300:2: rule__Float__Alternatives
            {
            pushFollow(FOLLOW_rule__Float__Alternatives_in_ruleFloat575);
            rule__Float__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getFloatAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFloat"


    // $ANTLR start "rule__PropertyValue__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:312:1: rule__PropertyValue__Alternatives : ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) );
    public final void rule__PropertyValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:316:1: ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) )
            int alt2=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt2=1;
                }
                break;
            case RULE_STRING:
                {
                alt2=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt2=3;
                }
                break;
            case RULE_ID:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:317:1: ( RULE_BOOLEAN )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:317:1: ( RULE_BOOLEAN )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:318:1: RULE_BOOLEAN
                    {
                     before(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                    match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives611); 
                     after(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:323:6: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:323:6: ( RULE_STRING )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:324:1: RULE_STRING
                    {
                     before(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives628); 
                     after(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:329:6: ( ruleFloat )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:329:6: ( ruleFloat )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:330:1: ruleFloat
                    {
                     before(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives645);
                    ruleFloat();

                    state._fsp--;

                     after(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:335:6: ( ruleQualifiedID )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:335:6: ( ruleQualifiedID )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:336:1: ruleQualifiedID
                    {
                     before(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives662);
                    ruleQualifiedID();

                    state._fsp--;

                     after(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PropertyValue__Alternatives"


    // $ANTLR start "rule__Float__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:346:1: rule__Float__Alternatives : ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) );
    public final void rule__Float__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:350:1: ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_TFLOAT) ) {
                alt3=1;
            }
            else if ( (LA3_0==RULE_NATURAL) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:351:1: ( RULE_TFLOAT )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:351:1: ( RULE_TFLOAT )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:352:1: RULE_TFLOAT
                    {
                     before(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                    match(input,RULE_TFLOAT,FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives694); 
                     after(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:357:6: ( RULE_NATURAL )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:357:6: ( RULE_NATURAL )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:358:1: RULE_NATURAL
                    {
                     before(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                    match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives711); 
                     after(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Float__Alternatives"


    // $ANTLR start "rule__Job__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:370:1: rule__Job__Group__0 : rule__Job__Group__0__Impl rule__Job__Group__1 ;
    public final void rule__Job__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:374:1: ( rule__Job__Group__0__Impl rule__Job__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:375:2: rule__Job__Group__0__Impl rule__Job__Group__1
            {
            pushFollow(FOLLOW_rule__Job__Group__0__Impl_in_rule__Job__Group__0741);
            rule__Job__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__1_in_rule__Job__Group__0744);
            rule__Job__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__0"


    // $ANTLR start "rule__Job__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:382:1: rule__Job__Group__0__Impl : ( () ) ;
    public final void rule__Job__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:386:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:387:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:387:1: ( () )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:388:1: ()
            {
             before(grammarAccess.getJobAccess().getJobAction_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:389:1: ()
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:391:1: 
            {
            }

             after(grammarAccess.getJobAccess().getJobAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__0__Impl"


    // $ANTLR start "rule__Job__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:401:1: rule__Job__Group__1 : rule__Job__Group__1__Impl rule__Job__Group__2 ;
    public final void rule__Job__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:405:1: ( rule__Job__Group__1__Impl rule__Job__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:406:2: rule__Job__Group__1__Impl rule__Job__Group__2
            {
            pushFollow(FOLLOW_rule__Job__Group__1__Impl_in_rule__Job__Group__1802);
            rule__Job__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__2_in_rule__Job__Group__1805);
            rule__Job__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__1"


    // $ANTLR start "rule__Job__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:413:1: rule__Job__Group__1__Impl : ( 'job' ) ;
    public final void rule__Job__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:417:1: ( ( 'job' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:418:1: ( 'job' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:418:1: ( 'job' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:419:1: 'job'
            {
             before(grammarAccess.getJobAccess().getJobKeyword_1()); 
            match(input,12,FOLLOW_12_in_rule__Job__Group__1__Impl833); 
             after(grammarAccess.getJobAccess().getJobKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__1__Impl"


    // $ANTLR start "rule__Job__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:432:1: rule__Job__Group__2 : rule__Job__Group__2__Impl rule__Job__Group__3 ;
    public final void rule__Job__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:436:1: ( rule__Job__Group__2__Impl rule__Job__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:437:2: rule__Job__Group__2__Impl rule__Job__Group__3
            {
            pushFollow(FOLLOW_rule__Job__Group__2__Impl_in_rule__Job__Group__2864);
            rule__Job__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__3_in_rule__Job__Group__2867);
            rule__Job__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__2"


    // $ANTLR start "rule__Job__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:444:1: rule__Job__Group__2__Impl : ( ( rule__Job__NameAssignment_2 )? ) ;
    public final void rule__Job__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:448:1: ( ( ( rule__Job__NameAssignment_2 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:449:1: ( ( rule__Job__NameAssignment_2 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:449:1: ( ( rule__Job__NameAssignment_2 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:450:1: ( rule__Job__NameAssignment_2 )?
            {
             before(grammarAccess.getJobAccess().getNameAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:451:1: ( rule__Job__NameAssignment_2 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:451:2: rule__Job__NameAssignment_2
                    {
                    pushFollow(FOLLOW_rule__Job__NameAssignment_2_in_rule__Job__Group__2__Impl894);
                    rule__Job__NameAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getJobAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__2__Impl"


    // $ANTLR start "rule__Job__Group__3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:461:1: rule__Job__Group__3 : rule__Job__Group__3__Impl rule__Job__Group__4 ;
    public final void rule__Job__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:465:1: ( rule__Job__Group__3__Impl rule__Job__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:466:2: rule__Job__Group__3__Impl rule__Job__Group__4
            {
            pushFollow(FOLLOW_rule__Job__Group__3__Impl_in_rule__Job__Group__3925);
            rule__Job__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__4_in_rule__Job__Group__3928);
            rule__Job__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__3"


    // $ANTLR start "rule__Job__Group__3__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:473:1: rule__Job__Group__3__Impl : ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? ) ;
    public final void rule__Job__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:477:1: ( ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:478:1: ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:478:1: ( ( rule__Job__LayoutBeforeAnalysisAssignment_3 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:479:1: ( rule__Job__LayoutBeforeAnalysisAssignment_3 )?
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:480:1: ( rule__Job__LayoutBeforeAnalysisAssignment_3 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==22) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:480:2: rule__Job__LayoutBeforeAnalysisAssignment_3
                    {
                    pushFollow(FOLLOW_rule__Job__LayoutBeforeAnalysisAssignment_3_in_rule__Job__Group__3__Impl955);
                    rule__Job__LayoutBeforeAnalysisAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getJobAccess().getLayoutBeforeAnalysisAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__3__Impl"


    // $ANTLR start "rule__Job__Group__4"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:490:1: rule__Job__Group__4 : rule__Job__Group__4__Impl rule__Job__Group__5 ;
    public final void rule__Job__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:494:1: ( rule__Job__Group__4__Impl rule__Job__Group__5 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:495:2: rule__Job__Group__4__Impl rule__Job__Group__5
            {
            pushFollow(FOLLOW_rule__Job__Group__4__Impl_in_rule__Job__Group__4986);
            rule__Job__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__5_in_rule__Job__Group__4989);
            rule__Job__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__4"


    // $ANTLR start "rule__Job__Group__4__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:502:1: rule__Job__Group__4__Impl : ( 'resources' ) ;
    public final void rule__Job__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:506:1: ( ( 'resources' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:507:1: ( 'resources' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:507:1: ( 'resources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:508:1: 'resources'
            {
             before(grammarAccess.getJobAccess().getResourcesKeyword_4()); 
            match(input,13,FOLLOW_13_in_rule__Job__Group__4__Impl1017); 
             after(grammarAccess.getJobAccess().getResourcesKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__4__Impl"


    // $ANTLR start "rule__Job__Group__5"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:521:1: rule__Job__Group__5 : rule__Job__Group__5__Impl rule__Job__Group__6 ;
    public final void rule__Job__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:525:1: ( rule__Job__Group__5__Impl rule__Job__Group__6 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:526:2: rule__Job__Group__5__Impl rule__Job__Group__6
            {
            pushFollow(FOLLOW_rule__Job__Group__5__Impl_in_rule__Job__Group__51048);
            rule__Job__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__6_in_rule__Job__Group__51051);
            rule__Job__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__5"


    // $ANTLR start "rule__Job__Group__5__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:533:1: rule__Job__Group__5__Impl : ( ( rule__Job__ResourcesAssignment_5 )* ) ;
    public final void rule__Job__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:537:1: ( ( ( rule__Job__ResourcesAssignment_5 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:538:1: ( ( rule__Job__ResourcesAssignment_5 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:538:1: ( ( rule__Job__ResourcesAssignment_5 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:539:1: ( rule__Job__ResourcesAssignment_5 )*
            {
             before(grammarAccess.getJobAccess().getResourcesAssignment_5()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:540:1: ( rule__Job__ResourcesAssignment_5 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_STRING) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:540:2: rule__Job__ResourcesAssignment_5
            	    {
            	    pushFollow(FOLLOW_rule__Job__ResourcesAssignment_5_in_rule__Job__Group__5__Impl1078);
            	    rule__Job__ResourcesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getJobAccess().getResourcesAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__5__Impl"


    // $ANTLR start "rule__Job__Group__6"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:550:1: rule__Job__Group__6 : rule__Job__Group__6__Impl rule__Job__Group__7 ;
    public final void rule__Job__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:554:1: ( rule__Job__Group__6__Impl rule__Job__Group__7 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:555:2: rule__Job__Group__6__Impl rule__Job__Group__7
            {
            pushFollow(FOLLOW_rule__Job__Group__6__Impl_in_rule__Job__Group__61109);
            rule__Job__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__7_in_rule__Job__Group__61112);
            rule__Job__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__6"


    // $ANTLR start "rule__Job__Group__6__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:562:1: rule__Job__Group__6__Impl : ( 'layoutoptions' ) ;
    public final void rule__Job__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:566:1: ( ( 'layoutoptions' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:567:1: ( 'layoutoptions' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:567:1: ( 'layoutoptions' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:568:1: 'layoutoptions'
            {
             before(grammarAccess.getJobAccess().getLayoutoptionsKeyword_6()); 
            match(input,14,FOLLOW_14_in_rule__Job__Group__6__Impl1140); 
             after(grammarAccess.getJobAccess().getLayoutoptionsKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__6__Impl"


    // $ANTLR start "rule__Job__Group__7"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:581:1: rule__Job__Group__7 : rule__Job__Group__7__Impl rule__Job__Group__8 ;
    public final void rule__Job__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:585:1: ( rule__Job__Group__7__Impl rule__Job__Group__8 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:586:2: rule__Job__Group__7__Impl rule__Job__Group__8
            {
            pushFollow(FOLLOW_rule__Job__Group__7__Impl_in_rule__Job__Group__71171);
            rule__Job__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__8_in_rule__Job__Group__71174);
            rule__Job__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__7"


    // $ANTLR start "rule__Job__Group__7__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:593:1: rule__Job__Group__7__Impl : ( ( rule__Job__LayoutOptionsAssignment_7 ) ) ;
    public final void rule__Job__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:597:1: ( ( ( rule__Job__LayoutOptionsAssignment_7 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:598:1: ( ( rule__Job__LayoutOptionsAssignment_7 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:598:1: ( ( rule__Job__LayoutOptionsAssignment_7 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:599:1: ( rule__Job__LayoutOptionsAssignment_7 )
            {
             before(grammarAccess.getJobAccess().getLayoutOptionsAssignment_7()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:600:1: ( rule__Job__LayoutOptionsAssignment_7 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:600:2: rule__Job__LayoutOptionsAssignment_7
            {
            pushFollow(FOLLOW_rule__Job__LayoutOptionsAssignment_7_in_rule__Job__Group__7__Impl1201);
            rule__Job__LayoutOptionsAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getJobAccess().getLayoutOptionsAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__7__Impl"


    // $ANTLR start "rule__Job__Group__8"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:610:1: rule__Job__Group__8 : rule__Job__Group__8__Impl rule__Job__Group__9 ;
    public final void rule__Job__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:614:1: ( rule__Job__Group__8__Impl rule__Job__Group__9 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:615:2: rule__Job__Group__8__Impl rule__Job__Group__9
            {
            pushFollow(FOLLOW_rule__Job__Group__8__Impl_in_rule__Job__Group__81231);
            rule__Job__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__9_in_rule__Job__Group__81234);
            rule__Job__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__8"


    // $ANTLR start "rule__Job__Group__8__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:622:1: rule__Job__Group__8__Impl : ( 'analyses' ) ;
    public final void rule__Job__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:626:1: ( ( 'analyses' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:627:1: ( 'analyses' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:627:1: ( 'analyses' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:628:1: 'analyses'
            {
             before(grammarAccess.getJobAccess().getAnalysesKeyword_8()); 
            match(input,15,FOLLOW_15_in_rule__Job__Group__8__Impl1262); 
             after(grammarAccess.getJobAccess().getAnalysesKeyword_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__8__Impl"


    // $ANTLR start "rule__Job__Group__9"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:641:1: rule__Job__Group__9 : rule__Job__Group__9__Impl rule__Job__Group__10 ;
    public final void rule__Job__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:645:1: ( rule__Job__Group__9__Impl rule__Job__Group__10 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:646:2: rule__Job__Group__9__Impl rule__Job__Group__10
            {
            pushFollow(FOLLOW_rule__Job__Group__9__Impl_in_rule__Job__Group__91293);
            rule__Job__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__10_in_rule__Job__Group__91296);
            rule__Job__Group__10();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__9"


    // $ANTLR start "rule__Job__Group__9__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:653:1: rule__Job__Group__9__Impl : ( ( rule__Job__AnalysesAssignment_9 )* ) ;
    public final void rule__Job__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:657:1: ( ( ( rule__Job__AnalysesAssignment_9 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:658:1: ( ( rule__Job__AnalysesAssignment_9 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:658:1: ( ( rule__Job__AnalysesAssignment_9 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:659:1: ( rule__Job__AnalysesAssignment_9 )*
            {
             before(grammarAccess.getJobAccess().getAnalysesAssignment_9()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:660:1: ( rule__Job__AnalysesAssignment_9 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:660:2: rule__Job__AnalysesAssignment_9
            	    {
            	    pushFollow(FOLLOW_rule__Job__AnalysesAssignment_9_in_rule__Job__Group__9__Impl1323);
            	    rule__Job__AnalysesAssignment_9();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getJobAccess().getAnalysesAssignment_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__9__Impl"


    // $ANTLR start "rule__Job__Group__10"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:670:1: rule__Job__Group__10 : rule__Job__Group__10__Impl rule__Job__Group__11 ;
    public final void rule__Job__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:674:1: ( rule__Job__Group__10__Impl rule__Job__Group__11 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:675:2: rule__Job__Group__10__Impl rule__Job__Group__11
            {
            pushFollow(FOLLOW_rule__Job__Group__10__Impl_in_rule__Job__Group__101354);
            rule__Job__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Job__Group__11_in_rule__Job__Group__101357);
            rule__Job__Group__11();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__10"


    // $ANTLR start "rule__Job__Group__10__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:682:1: rule__Job__Group__10__Impl : ( 'output' ) ;
    public final void rule__Job__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:686:1: ( ( 'output' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:687:1: ( 'output' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:687:1: ( 'output' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:688:1: 'output'
            {
             before(grammarAccess.getJobAccess().getOutputKeyword_10()); 
            match(input,16,FOLLOW_16_in_rule__Job__Group__10__Impl1385); 
             after(grammarAccess.getJobAccess().getOutputKeyword_10()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__10__Impl"


    // $ANTLR start "rule__Job__Group__11"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:701:1: rule__Job__Group__11 : rule__Job__Group__11__Impl ;
    public final void rule__Job__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:705:1: ( rule__Job__Group__11__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:706:2: rule__Job__Group__11__Impl
            {
            pushFollow(FOLLOW_rule__Job__Group__11__Impl_in_rule__Job__Group__111416);
            rule__Job__Group__11__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__11"


    // $ANTLR start "rule__Job__Group__11__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:712:1: rule__Job__Group__11__Impl : ( ( rule__Job__OutputAssignment_11 ) ) ;
    public final void rule__Job__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:716:1: ( ( ( rule__Job__OutputAssignment_11 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:717:1: ( ( rule__Job__OutputAssignment_11 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:717:1: ( ( rule__Job__OutputAssignment_11 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:718:1: ( rule__Job__OutputAssignment_11 )
            {
             before(grammarAccess.getJobAccess().getOutputAssignment_11()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:719:1: ( rule__Job__OutputAssignment_11 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:719:2: rule__Job__OutputAssignment_11
            {
            pushFollow(FOLLOW_rule__Job__OutputAssignment_11_in_rule__Job__Group__11__Impl1443);
            rule__Job__OutputAssignment_11();

            state._fsp--;


            }

             after(grammarAccess.getJobAccess().getOutputAssignment_11()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__Group__11__Impl"


    // $ANTLR start "rule__Resource__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:753:1: rule__Resource__Group__0 : rule__Resource__Group__0__Impl rule__Resource__Group__1 ;
    public final void rule__Resource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:757:1: ( rule__Resource__Group__0__Impl rule__Resource__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:758:2: rule__Resource__Group__0__Impl rule__Resource__Group__1
            {
            pushFollow(FOLLOW_rule__Resource__Group__0__Impl_in_rule__Resource__Group__01497);
            rule__Resource__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Resource__Group__1_in_rule__Resource__Group__01500);
            rule__Resource__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__Group__0"


    // $ANTLR start "rule__Resource__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:765:1: rule__Resource__Group__0__Impl : ( ( rule__Resource__PathAssignment_0 ) ) ;
    public final void rule__Resource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:769:1: ( ( ( rule__Resource__PathAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:770:1: ( ( rule__Resource__PathAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:770:1: ( ( rule__Resource__PathAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:771:1: ( rule__Resource__PathAssignment_0 )
            {
             before(grammarAccess.getResourceAccess().getPathAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:772:1: ( rule__Resource__PathAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:772:2: rule__Resource__PathAssignment_0
            {
            pushFollow(FOLLOW_rule__Resource__PathAssignment_0_in_rule__Resource__Group__0__Impl1527);
            rule__Resource__PathAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getResourceAccess().getPathAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__Group__0__Impl"


    // $ANTLR start "rule__Resource__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:782:1: rule__Resource__Group__1 : rule__Resource__Group__1__Impl ;
    public final void rule__Resource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:786:1: ( rule__Resource__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:787:2: rule__Resource__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Resource__Group__1__Impl_in_rule__Resource__Group__11557);
            rule__Resource__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__Group__1"


    // $ANTLR start "rule__Resource__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:793:1: rule__Resource__Group__1__Impl : ( ( rule__Resource__Group_1__0 ) ) ;
    public final void rule__Resource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:797:1: ( ( ( rule__Resource__Group_1__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:798:1: ( ( rule__Resource__Group_1__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:798:1: ( ( rule__Resource__Group_1__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:799:1: ( rule__Resource__Group_1__0 )
            {
             before(grammarAccess.getResourceAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:800:1: ( rule__Resource__Group_1__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:800:2: rule__Resource__Group_1__0
            {
            pushFollow(FOLLOW_rule__Resource__Group_1__0_in_rule__Resource__Group__1__Impl1584);
            rule__Resource__Group_1__0();

            state._fsp--;


            }

             after(grammarAccess.getResourceAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__Group__1__Impl"


    // $ANTLR start "rule__Resource__Group_1__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:814:1: rule__Resource__Group_1__0 : rule__Resource__Group_1__0__Impl rule__Resource__Group_1__1 ;
    public final void rule__Resource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:818:1: ( rule__Resource__Group_1__0__Impl rule__Resource__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:819:2: rule__Resource__Group_1__0__Impl rule__Resource__Group_1__1
            {
            pushFollow(FOLLOW_rule__Resource__Group_1__0__Impl_in_rule__Resource__Group_1__01618);
            rule__Resource__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Resource__Group_1__1_in_rule__Resource__Group_1__01621);
            rule__Resource__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__Group_1__0"


    // $ANTLR start "rule__Resource__Group_1__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:826:1: rule__Resource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__Resource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:830:1: ( ( 'filter' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:831:1: ( 'filter' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:831:1: ( 'filter' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:832:1: 'filter'
            {
             before(grammarAccess.getResourceAccess().getFilterKeyword_1_0()); 
            match(input,17,FOLLOW_17_in_rule__Resource__Group_1__0__Impl1649); 
             after(grammarAccess.getResourceAccess().getFilterKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__Group_1__0__Impl"


    // $ANTLR start "rule__Resource__Group_1__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:845:1: rule__Resource__Group_1__1 : rule__Resource__Group_1__1__Impl ;
    public final void rule__Resource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:849:1: ( rule__Resource__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:850:2: rule__Resource__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Resource__Group_1__1__Impl_in_rule__Resource__Group_1__11680);
            rule__Resource__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__Group_1__1"


    // $ANTLR start "rule__Resource__Group_1__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:856:1: rule__Resource__Group_1__1__Impl : ( ( rule__Resource__FilterAssignment_1_1 ) ) ;
    public final void rule__Resource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:860:1: ( ( ( rule__Resource__FilterAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:861:1: ( ( rule__Resource__FilterAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:861:1: ( ( rule__Resource__FilterAssignment_1_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:862:1: ( rule__Resource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getResourceAccess().getFilterAssignment_1_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:863:1: ( rule__Resource__FilterAssignment_1_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:863:2: rule__Resource__FilterAssignment_1_1
            {
            pushFollow(FOLLOW_rule__Resource__FilterAssignment_1_1_in_rule__Resource__Group_1__1__Impl1707);
            rule__Resource__FilterAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceAccess().getFilterAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__Group_1__1__Impl"


    // $ANTLR start "rule__KIdentifier__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:877:1: rule__KIdentifier__Group__0 : rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 ;
    public final void rule__KIdentifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:881:1: ( rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:882:2: rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__01741);
            rule__KIdentifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__01744);
            rule__KIdentifier__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__0"


    // $ANTLR start "rule__KIdentifier__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:889:1: rule__KIdentifier__Group__0__Impl : ( () ) ;
    public final void rule__KIdentifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:893:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:894:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:894:1: ( () )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:895:1: ()
            {
             before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:896:1: ()
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:898:1: 
            {
            }

             after(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__0__Impl"


    // $ANTLR start "rule__KIdentifier__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:908:1: rule__KIdentifier__Group__1 : rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 ;
    public final void rule__KIdentifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:912:1: ( rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:913:2: rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__11802);
            rule__KIdentifier__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__11805);
            rule__KIdentifier__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__1"


    // $ANTLR start "rule__KIdentifier__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:920:1: rule__KIdentifier__Group__1__Impl : ( ( rule__KIdentifier__IdAssignment_1 ) ) ;
    public final void rule__KIdentifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:924:1: ( ( ( rule__KIdentifier__IdAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:925:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:925:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:926:1: ( rule__KIdentifier__IdAssignment_1 )
            {
             before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:927:1: ( rule__KIdentifier__IdAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:927:2: rule__KIdentifier__IdAssignment_1
            {
            pushFollow(FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl1832);
            rule__KIdentifier__IdAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__1__Impl"


    // $ANTLR start "rule__KIdentifier__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:937:1: rule__KIdentifier__Group__2 : rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 ;
    public final void rule__KIdentifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:941:1: ( rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:942:2: rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__21862);
            rule__KIdentifier__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__21865);
            rule__KIdentifier__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__2"


    // $ANTLR start "rule__KIdentifier__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:949:1: rule__KIdentifier__Group__2__Impl : ( '{' ) ;
    public final void rule__KIdentifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:953:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:954:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:954:1: ( '{' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:955:1: '{'
            {
             before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,18,FOLLOW_18_in_rule__KIdentifier__Group__2__Impl1893); 
             after(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__2__Impl"


    // $ANTLR start "rule__KIdentifier__Group__3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:968:1: rule__KIdentifier__Group__3 : rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 ;
    public final void rule__KIdentifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:972:1: ( rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:973:2: rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__31924);
            rule__KIdentifier__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__31927);
            rule__KIdentifier__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__3"


    // $ANTLR start "rule__KIdentifier__Group__3__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:980:1: rule__KIdentifier__Group__3__Impl : ( ( rule__KIdentifier__Group_3__0 )? ) ;
    public final void rule__KIdentifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:984:1: ( ( ( rule__KIdentifier__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:985:1: ( ( rule__KIdentifier__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:985:1: ( ( rule__KIdentifier__Group_3__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:986:1: ( rule__KIdentifier__Group_3__0 )?
            {
             before(grammarAccess.getKIdentifierAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:987:1: ( rule__KIdentifier__Group_3__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:987:2: rule__KIdentifier__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl1954);
                    rule__KIdentifier__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKIdentifierAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__3__Impl"


    // $ANTLR start "rule__KIdentifier__Group__4"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:997:1: rule__KIdentifier__Group__4 : rule__KIdentifier__Group__4__Impl ;
    public final void rule__KIdentifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1001:1: ( rule__KIdentifier__Group__4__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1002:2: rule__KIdentifier__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__41985);
            rule__KIdentifier__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__4"


    // $ANTLR start "rule__KIdentifier__Group__4__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1008:1: rule__KIdentifier__Group__4__Impl : ( '}' ) ;
    public final void rule__KIdentifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1012:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1013:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1013:1: ( '}' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1014:1: '}'
            {
             before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 
            match(input,19,FOLLOW_19_in_rule__KIdentifier__Group__4__Impl2013); 
             after(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group__4__Impl"


    // $ANTLR start "rule__KIdentifier__Group_3__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1037:1: rule__KIdentifier__Group_3__0 : rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 ;
    public final void rule__KIdentifier__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1041:1: ( rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1042:2: rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__02054);
            rule__KIdentifier__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__02057);
            rule__KIdentifier__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group_3__0"


    // $ANTLR start "rule__KIdentifier__Group_3__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1049:1: rule__KIdentifier__Group_3__0__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) ;
    public final void rule__KIdentifier__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1053:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1054:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1054:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1055:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1056:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1056:2: rule__KIdentifier__PersistentEntriesAssignment_3_0
            {
            pushFollow(FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl2084);
            rule__KIdentifier__PersistentEntriesAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group_3__0__Impl"


    // $ANTLR start "rule__KIdentifier__Group_3__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1066:1: rule__KIdentifier__Group_3__1 : rule__KIdentifier__Group_3__1__Impl ;
    public final void rule__KIdentifier__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1070:1: ( rule__KIdentifier__Group_3__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1071:2: rule__KIdentifier__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__12114);
            rule__KIdentifier__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group_3__1"


    // $ANTLR start "rule__KIdentifier__Group_3__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1077:1: rule__KIdentifier__Group_3__1__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) ;
    public final void rule__KIdentifier__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1081:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1082:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1082:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1083:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1084:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1084:2: rule__KIdentifier__PersistentEntriesAssignment_3_1
            	    {
            	    pushFollow(FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl2141);
            	    rule__KIdentifier__PersistentEntriesAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__Group_3__1__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1098:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1102:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1103:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__02176);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__02179);
            rule__PersistentEntry__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__0"


    // $ANTLR start "rule__PersistentEntry__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1110:1: rule__PersistentEntry__Group__0__Impl : ( ( rule__PersistentEntry__KeyAssignment_0 ) ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1114:1: ( ( ( rule__PersistentEntry__KeyAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1115:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1115:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1116:1: ( rule__PersistentEntry__KeyAssignment_0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1117:1: ( rule__PersistentEntry__KeyAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1117:2: rule__PersistentEntry__KeyAssignment_0
            {
            pushFollow(FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl2206);
            rule__PersistentEntry__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__0__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1127:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1131:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1132:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__12236);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__12239);
            rule__PersistentEntry__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__1"


    // $ANTLR start "rule__PersistentEntry__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1139:1: rule__PersistentEntry__Group__1__Impl : ( ':' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1143:1: ( ( ':' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1144:1: ( ':' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1144:1: ( ':' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1145:1: ':'
            {
             before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 
            match(input,20,FOLLOW_20_in_rule__PersistentEntry__Group__1__Impl2267); 
             after(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__1__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1158:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1162:1: ( rule__PersistentEntry__Group__2__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1163:2: rule__PersistentEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__22298);
            rule__PersistentEntry__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__2"


    // $ANTLR start "rule__PersistentEntry__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1169:1: rule__PersistentEntry__Group__2__Impl : ( ( rule__PersistentEntry__ValueAssignment_2 ) ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1173:1: ( ( ( rule__PersistentEntry__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1174:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1174:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1175:1: ( rule__PersistentEntry__ValueAssignment_2 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1176:1: ( rule__PersistentEntry__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1176:2: rule__PersistentEntry__ValueAssignment_2
            {
            pushFollow(FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl2325);
            rule__PersistentEntry__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__2__Impl"


    // $ANTLR start "rule__QualifiedID__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1192:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1196:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1197:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__02361);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__02364);
            rule__QualifiedID__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group__0"


    // $ANTLR start "rule__QualifiedID__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1204:1: rule__QualifiedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1208:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1209:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1209:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1210:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl2391); 
             after(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group__0__Impl"


    // $ANTLR start "rule__QualifiedID__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1221:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1225:1: ( rule__QualifiedID__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1226:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__12420);
            rule__QualifiedID__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group__1"


    // $ANTLR start "rule__QualifiedID__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1232:1: rule__QualifiedID__Group__1__Impl : ( ( rule__QualifiedID__Group_1__0 )* ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1236:1: ( ( ( rule__QualifiedID__Group_1__0 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1237:1: ( ( rule__QualifiedID__Group_1__0 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1237:1: ( ( rule__QualifiedID__Group_1__0 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1238:1: ( rule__QualifiedID__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1239:1: ( rule__QualifiedID__Group_1__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==21) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1239:2: rule__QualifiedID__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl2447);
            	    rule__QualifiedID__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getQualifiedIDAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group__1__Impl"


    // $ANTLR start "rule__QualifiedID__Group_1__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1253:1: rule__QualifiedID__Group_1__0 : rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 ;
    public final void rule__QualifiedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1257:1: ( rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1258:2: rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__02482);
            rule__QualifiedID__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__02485);
            rule__QualifiedID__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group_1__0"


    // $ANTLR start "rule__QualifiedID__Group_1__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1265:1: rule__QualifiedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1269:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1270:1: ( '.' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1270:1: ( '.' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1271:1: '.'
            {
             before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            match(input,21,FOLLOW_21_in_rule__QualifiedID__Group_1__0__Impl2513); 
             after(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedID__Group_1__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1284:1: rule__QualifiedID__Group_1__1 : rule__QualifiedID__Group_1__1__Impl ;
    public final void rule__QualifiedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1288:1: ( rule__QualifiedID__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1289:2: rule__QualifiedID__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__12544);
            rule__QualifiedID__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group_1__1"


    // $ANTLR start "rule__QualifiedID__Group_1__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1295:1: rule__QualifiedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1299:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1300:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1300:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1301:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl2571); 
             after(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__QualifiedID__Group_1__1__Impl"


    // $ANTLR start "rule__Grana__JobsAssignment"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1317:1: rule__Grana__JobsAssignment : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1321:1: ( ( ruleJob ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1322:1: ( ruleJob )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1322:1: ( ruleJob )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1323:1: ruleJob
            {
             before(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleJob_in_rule__Grana__JobsAssignment2609);
            ruleJob();

            state._fsp--;

             after(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Grana__JobsAssignment"


    // $ANTLR start "rule__Job__NameAssignment_2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1332:1: rule__Job__NameAssignment_2 : ( RULE_ID ) ;
    public final void rule__Job__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1336:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1337:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1337:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1338:1: RULE_ID
            {
             before(grammarAccess.getJobAccess().getNameIDTerminalRuleCall_2_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Job__NameAssignment_22640); 
             after(grammarAccess.getJobAccess().getNameIDTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__NameAssignment_2"


    // $ANTLR start "rule__Job__LayoutBeforeAnalysisAssignment_3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1347:1: rule__Job__LayoutBeforeAnalysisAssignment_3 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__Job__LayoutBeforeAnalysisAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1351:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1352:1: ( ( 'layoutBeforeAnalysis' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1352:1: ( ( 'layoutBeforeAnalysis' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1353:1: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1354:1: ( 'layoutBeforeAnalysis' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1355:1: 'layoutBeforeAnalysis'
            {
             before(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); 
            match(input,22,FOLLOW_22_in_rule__Job__LayoutBeforeAnalysisAssignment_32676); 
             after(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); 

            }

             after(grammarAccess.getJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__LayoutBeforeAnalysisAssignment_3"


    // $ANTLR start "rule__Job__ResourcesAssignment_5"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1370:1: rule__Job__ResourcesAssignment_5 : ( ruleResource ) ;
    public final void rule__Job__ResourcesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1374:1: ( ( ruleResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1375:1: ( ruleResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1375:1: ( ruleResource )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1376:1: ruleResource
            {
             before(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_5_0()); 
            pushFollow(FOLLOW_ruleResource_in_rule__Job__ResourcesAssignment_52715);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getJobAccess().getResourcesResourceParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__ResourcesAssignment_5"


    // $ANTLR start "rule__Job__LayoutOptionsAssignment_7"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1385:1: rule__Job__LayoutOptionsAssignment_7 : ( ruleKIdentifier ) ;
    public final void rule__Job__LayoutOptionsAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1389:1: ( ( ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1390:1: ( ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1390:1: ( ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1391:1: ruleKIdentifier
            {
             before(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_rule__Job__LayoutOptionsAssignment_72746);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__LayoutOptionsAssignment_7"


    // $ANTLR start "rule__Job__AnalysesAssignment_9"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1400:1: rule__Job__AnalysesAssignment_9 : ( ruleAnalysis ) ;
    public final void rule__Job__AnalysesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1404:1: ( ( ruleAnalysis ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1405:1: ( ruleAnalysis )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1405:1: ( ruleAnalysis )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1406:1: ruleAnalysis
            {
             before(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 
            pushFollow(FOLLOW_ruleAnalysis_in_rule__Job__AnalysesAssignment_92777);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__AnalysesAssignment_9"


    // $ANTLR start "rule__Job__OutputAssignment_11"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1415:1: rule__Job__OutputAssignment_11 : ( RULE_STRING ) ;
    public final void rule__Job__OutputAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1419:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1420:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1420:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1421:1: RULE_STRING
            {
             before(grammarAccess.getJobAccess().getOutputSTRINGTerminalRuleCall_11_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Job__OutputAssignment_112808); 
             after(grammarAccess.getJobAccess().getOutputSTRINGTerminalRuleCall_11_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Job__OutputAssignment_11"


    // $ANTLR start "rule__Resource__PathAssignment_0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1430:1: rule__Resource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__Resource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1434:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1435:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1435:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1436:1: RULE_STRING
            {
             before(grammarAccess.getResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Resource__PathAssignment_02839); 
             after(grammarAccess.getResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__PathAssignment_0"


    // $ANTLR start "rule__Resource__FilterAssignment_1_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1445:1: rule__Resource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__Resource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1449:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1450:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1450:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1451:1: RULE_STRING
            {
             before(grammarAccess.getResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Resource__FilterAssignment_1_12870); 
             after(grammarAccess.getResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Resource__FilterAssignment_1_1"


    // $ANTLR start "rule__Analysis__NameAssignment"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1460:1: rule__Analysis__NameAssignment : ( ruleQualifiedID ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1464:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1465:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1465:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1466:1: ruleQualifiedID
            {
             before(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__Analysis__NameAssignment2901);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Analysis__NameAssignment"


    // $ANTLR start "rule__KIdentifier__IdAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1475:1: rule__KIdentifier__IdAssignment_1 : ( RULE_ID ) ;
    public final void rule__KIdentifier__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1479:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1480:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1480:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1481:1: RULE_ID
            {
             before(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_12932); 
             after(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__IdAssignment_1"


    // $ANTLR start "rule__KIdentifier__PersistentEntriesAssignment_3_0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1490:1: rule__KIdentifier__PersistentEntriesAssignment_3_0 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1494:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1495:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1495:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1496:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_02963);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__PersistentEntriesAssignment_3_0"


    // $ANTLR start "rule__KIdentifier__PersistentEntriesAssignment_3_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1505:1: rule__KIdentifier__PersistentEntriesAssignment_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1509:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1510:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1510:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1511:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_12994);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KIdentifier__PersistentEntriesAssignment_3_1"


    // $ANTLR start "rule__PersistentEntry__KeyAssignment_0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1520:1: rule__PersistentEntry__KeyAssignment_0 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1524:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1525:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1525:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1526:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_03025);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__KeyAssignment_0"


    // $ANTLR start "rule__PersistentEntry__ValueAssignment_2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1535:1: rule__PersistentEntry__ValueAssignment_2 : ( rulePropertyValue ) ;
    public final void rule__PersistentEntry__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1539:1: ( ( rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1540:1: ( rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1540:1: ( rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1541:1: rulePropertyValue
            {
             before(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_23056);
            rulePropertyValue();

            state._fsp--;

             after(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__ValueAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleGrana_in_entryRuleGrana61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGrana68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__JobsAssignment_in_ruleGrana94 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_ruleJob_in_entryRuleJob122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJob129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__0_in_ruleJob155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_entryRuleResource182 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResource189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__Group__0_in_ruleResource215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_entryRuleAnalysis242 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalysis249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Analysis__NameAssignment_in_ruleAnalysis275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier302 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry362 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID422 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue482 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat542 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloat549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Float__Alternatives_in_ruleFloat575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives645 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__0__Impl_in_rule__Job__Group__0741 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__Job__Group__1_in_rule__Job__Group__0744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__1__Impl_in_rule__Job__Group__1802 = new BitSet(new long[]{0x0000000000402100L});
    public static final BitSet FOLLOW_rule__Job__Group__2_in_rule__Job__Group__1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Job__Group__1__Impl833 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__2__Impl_in_rule__Job__Group__2864 = new BitSet(new long[]{0x0000000000402100L});
    public static final BitSet FOLLOW_rule__Job__Group__3_in_rule__Job__Group__2867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__NameAssignment_2_in_rule__Job__Group__2__Impl894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__3__Impl_in_rule__Job__Group__3925 = new BitSet(new long[]{0x0000000000402100L});
    public static final BitSet FOLLOW_rule__Job__Group__4_in_rule__Job__Group__3928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__LayoutBeforeAnalysisAssignment_3_in_rule__Job__Group__3__Impl955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__4__Impl_in_rule__Job__Group__4986 = new BitSet(new long[]{0x0000000000004020L});
    public static final BitSet FOLLOW_rule__Job__Group__5_in_rule__Job__Group__4989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Job__Group__4__Impl1017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__5__Impl_in_rule__Job__Group__51048 = new BitSet(new long[]{0x0000000000004020L});
    public static final BitSet FOLLOW_rule__Job__Group__6_in_rule__Job__Group__51051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__ResourcesAssignment_5_in_rule__Job__Group__5__Impl1078 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_rule__Job__Group__6__Impl_in_rule__Job__Group__61109 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Job__Group__7_in_rule__Job__Group__61112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Job__Group__6__Impl1140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__7__Impl_in_rule__Job__Group__71171 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_rule__Job__Group__8_in_rule__Job__Group__71174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__LayoutOptionsAssignment_7_in_rule__Job__Group__7__Impl1201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__8__Impl_in_rule__Job__Group__81231 = new BitSet(new long[]{0x0000000000010100L});
    public static final BitSet FOLLOW_rule__Job__Group__9_in_rule__Job__Group__81234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__Job__Group__8__Impl1262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__9__Impl_in_rule__Job__Group__91293 = new BitSet(new long[]{0x0000000000010100L});
    public static final BitSet FOLLOW_rule__Job__Group__10_in_rule__Job__Group__91296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__AnalysesAssignment_9_in_rule__Job__Group__9__Impl1323 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Job__Group__10__Impl_in_rule__Job__Group__101354 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Job__Group__11_in_rule__Job__Group__101357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Job__Group__10__Impl1385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Group__11__Impl_in_rule__Job__Group__111416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__OutputAssignment_11_in_rule__Job__Group__11__Impl1443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__Group__0__Impl_in_rule__Resource__Group__01497 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__Resource__Group__1_in_rule__Resource__Group__01500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__PathAssignment_0_in_rule__Resource__Group__0__Impl1527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__Group__1__Impl_in_rule__Resource__Group__11557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__Group_1__0_in_rule__Resource__Group__1__Impl1584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__Group_1__0__Impl_in_rule__Resource__Group_1__01618 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Resource__Group_1__1_in_rule__Resource__Group_1__01621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Resource__Group_1__0__Impl1649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__Group_1__1__Impl_in_rule__Resource__Group_1__11680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__FilterAssignment_1_1_in_rule__Resource__Group_1__1__Impl1707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__01741 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__01744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__11802 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__11805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl1832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__21862 = new BitSet(new long[]{0x0000000000080100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__21865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__KIdentifier__Group__2__Impl1893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__31924 = new BitSet(new long[]{0x0000000000080100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__31927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl1954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__41985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__KIdentifier__Group__4__Impl2013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__02054 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__02057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl2084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__12114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl2141 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__02176 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__02179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl2206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__12236 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__12239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__PersistentEntry__Group__1__Impl2267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__22298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl2325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__02361 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__02364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl2391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__12420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl2447 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__02482 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__02485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__QualifiedID__Group_1__0__Impl2513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__12544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl2571 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJob_in_rule__Grana__JobsAssignment2609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Job__NameAssignment_22640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Job__LayoutBeforeAnalysisAssignment_32676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_rule__Job__ResourcesAssignment_52715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_rule__Job__LayoutOptionsAssignment_72746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_rule__Job__AnalysesAssignment_92777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Job__OutputAssignment_112808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Resource__PathAssignment_02839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Resource__FilterAssignment_1_12870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__Analysis__NameAssignment2901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_12932 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_02963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_12994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_03025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_23056 = new BitSet(new long[]{0x0000000000000002L});

}