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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_BOOLEAN", "RULE_STRING", "RULE_TFLOAT", "RULE_NATURAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'globalResources'", "'globalOutputs'", "'execute'", "'job'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'rangejob'", "'rangeoption'", "'rangeanalysis'", "'component'", "'floatvalues'", "','", "'intvalues'", "'intrange'", "'to'", "'ref'", "'filter'", "'{'", "'}'", "':'", "'.'", "'all'", "'layoutBeforeAnalysis'", "'measureExecutionTime'"
    };
    public static final int RULE_BOOLEAN=4;
    public static final int RULE_ID=8;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_NATURAL=7;
    public static final int RULE_SL_COMMENT=10;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=9;
    public static final int RULE_TFLOAT=6;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int RULE_STRING=5;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__16=16;
    public static final int T__34=34;
    public static final int T__15=15;
    public static final int T__35=35;
    public static final int T__18=18;
    public static final int T__36=36;
    public static final int T__17=17;
    public static final int T__37=37;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:69:1: ruleGrana : ( ( rule__Grana__Group__0 ) ) ;
    public final void ruleGrana() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:73:2: ( ( ( rule__Grana__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:74:1: ( ( rule__Grana__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:74:1: ( ( rule__Grana__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:75:1: ( rule__Grana__Group__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:76:1: ( rule__Grana__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:76:2: rule__Grana__Group__0
            {
            pushFollow(FOLLOW_rule__Grana__Group__0_in_ruleGrana94);
            rule__Grana__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getGroup()); 

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
            pushFollow(FOLLOW_ruleJob_in_entryRuleJob121);
            ruleJob();

            state._fsp--;

             after(grammarAccess.getJobRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJob128); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:97:1: ruleJob : ( ( rule__Job__Alternatives ) ) ;
    public final void ruleJob() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:101:2: ( ( ( rule__Job__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:102:1: ( ( rule__Job__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:102:1: ( ( rule__Job__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:103:1: ( rule__Job__Alternatives )
            {
             before(grammarAccess.getJobAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:104:1: ( rule__Job__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:104:2: rule__Job__Alternatives
            {
            pushFollow(FOLLOW_rule__Job__Alternatives_in_ruleJob154);
            rule__Job__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getJobAccess().getAlternatives()); 

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


    // $ANTLR start "entryRuleRegularJob"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:116:1: entryRuleRegularJob : ruleRegularJob EOF ;
    public final void entryRuleRegularJob() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:117:1: ( ruleRegularJob EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:118:1: ruleRegularJob EOF
            {
             before(grammarAccess.getRegularJobRule()); 
            pushFollow(FOLLOW_ruleRegularJob_in_entryRuleRegularJob181);
            ruleRegularJob();

            state._fsp--;

             after(grammarAccess.getRegularJobRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRegularJob188); 

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
    // $ANTLR end "entryRuleRegularJob"


    // $ANTLR start "ruleRegularJob"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:125:1: ruleRegularJob : ( ( rule__RegularJob__Group__0 ) ) ;
    public final void ruleRegularJob() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:129:2: ( ( ( rule__RegularJob__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:130:1: ( ( rule__RegularJob__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:130:1: ( ( rule__RegularJob__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:131:1: ( rule__RegularJob__Group__0 )
            {
             before(grammarAccess.getRegularJobAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:132:1: ( rule__RegularJob__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:132:2: rule__RegularJob__Group__0
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__0_in_ruleRegularJob214);
            rule__RegularJob__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getGroup()); 

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
    // $ANTLR end "ruleRegularJob"


    // $ANTLR start "entryRuleRangeJob"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:144:1: entryRuleRangeJob : ruleRangeJob EOF ;
    public final void entryRuleRangeJob() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:145:1: ( ruleRangeJob EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:146:1: ruleRangeJob EOF
            {
             before(grammarAccess.getRangeJobRule()); 
            pushFollow(FOLLOW_ruleRangeJob_in_entryRuleRangeJob241);
            ruleRangeJob();

            state._fsp--;

             after(grammarAccess.getRangeJobRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRangeJob248); 

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
    // $ANTLR end "entryRuleRangeJob"


    // $ANTLR start "ruleRangeJob"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:153:1: ruleRangeJob : ( ( rule__RangeJob__Group__0 ) ) ;
    public final void ruleRangeJob() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:157:2: ( ( ( rule__RangeJob__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:158:1: ( ( rule__RangeJob__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:158:1: ( ( rule__RangeJob__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:159:1: ( rule__RangeJob__Group__0 )
            {
             before(grammarAccess.getRangeJobAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:160:1: ( rule__RangeJob__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:160:2: rule__RangeJob__Group__0
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__0_in_ruleRangeJob274);
            rule__RangeJob__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getGroup()); 

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
    // $ANTLR end "ruleRangeJob"


    // $ANTLR start "entryRuleRange"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:172:1: entryRuleRange : ruleRange EOF ;
    public final void entryRuleRange() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:173:1: ( ruleRange EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:174:1: ruleRange EOF
            {
             before(grammarAccess.getRangeRule()); 
            pushFollow(FOLLOW_ruleRange_in_entryRuleRange301);
            ruleRange();

            state._fsp--;

             after(grammarAccess.getRangeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRange308); 

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
    // $ANTLR end "entryRuleRange"


    // $ANTLR start "ruleRange"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:181:1: ruleRange : ( ( rule__Range__Alternatives ) ) ;
    public final void ruleRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:185:2: ( ( ( rule__Range__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:186:1: ( ( rule__Range__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:186:1: ( ( rule__Range__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:187:1: ( rule__Range__Alternatives )
            {
             before(grammarAccess.getRangeAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:188:1: ( rule__Range__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:188:2: rule__Range__Alternatives
            {
            pushFollow(FOLLOW_rule__Range__Alternatives_in_ruleRange334);
            rule__Range__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getRangeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleRange"


    // $ANTLR start "entryRuleFloatRange"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:200:1: entryRuleFloatRange : ruleFloatRange EOF ;
    public final void entryRuleFloatRange() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:201:1: ( ruleFloatRange EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:202:1: ruleFloatRange EOF
            {
             before(grammarAccess.getFloatRangeRule()); 
            pushFollow(FOLLOW_ruleFloatRange_in_entryRuleFloatRange361);
            ruleFloatRange();

            state._fsp--;

             after(grammarAccess.getFloatRangeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloatRange368); 

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
    // $ANTLR end "entryRuleFloatRange"


    // $ANTLR start "ruleFloatRange"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:209:1: ruleFloatRange : ( ( rule__FloatRange__Group__0 ) ) ;
    public final void ruleFloatRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:213:2: ( ( ( rule__FloatRange__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:214:1: ( ( rule__FloatRange__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:214:1: ( ( rule__FloatRange__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:215:1: ( rule__FloatRange__Group__0 )
            {
             before(grammarAccess.getFloatRangeAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:216:1: ( rule__FloatRange__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:216:2: rule__FloatRange__Group__0
            {
            pushFollow(FOLLOW_rule__FloatRange__Group__0_in_ruleFloatRange394);
            rule__FloatRange__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFloatRangeAccess().getGroup()); 

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
    // $ANTLR end "ruleFloatRange"


    // $ANTLR start "entryRuleIntRange"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:228:1: entryRuleIntRange : ruleIntRange EOF ;
    public final void entryRuleIntRange() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:229:1: ( ruleIntRange EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:230:1: ruleIntRange EOF
            {
             before(grammarAccess.getIntRangeRule()); 
            pushFollow(FOLLOW_ruleIntRange_in_entryRuleIntRange421);
            ruleIntRange();

            state._fsp--;

             after(grammarAccess.getIntRangeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntRange428); 

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
    // $ANTLR end "entryRuleIntRange"


    // $ANTLR start "ruleIntRange"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:237:1: ruleIntRange : ( ( rule__IntRange__Alternatives ) ) ;
    public final void ruleIntRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:241:2: ( ( ( rule__IntRange__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:242:1: ( ( rule__IntRange__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:242:1: ( ( rule__IntRange__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:243:1: ( rule__IntRange__Alternatives )
            {
             before(grammarAccess.getIntRangeAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:244:1: ( rule__IntRange__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:244:2: rule__IntRange__Alternatives
            {
            pushFollow(FOLLOW_rule__IntRange__Alternatives_in_ruleIntRange454);
            rule__IntRange__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getIntRangeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleIntRange"


    // $ANTLR start "entryRuleIntRangeValues"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:256:1: entryRuleIntRangeValues : ruleIntRangeValues EOF ;
    public final void entryRuleIntRangeValues() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:257:1: ( ruleIntRangeValues EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:258:1: ruleIntRangeValues EOF
            {
             before(grammarAccess.getIntRangeValuesRule()); 
            pushFollow(FOLLOW_ruleIntRangeValues_in_entryRuleIntRangeValues481);
            ruleIntRangeValues();

            state._fsp--;

             after(grammarAccess.getIntRangeValuesRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntRangeValues488); 

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
    // $ANTLR end "entryRuleIntRangeValues"


    // $ANTLR start "ruleIntRangeValues"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:265:1: ruleIntRangeValues : ( ( rule__IntRangeValues__Group__0 ) ) ;
    public final void ruleIntRangeValues() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:269:2: ( ( ( rule__IntRangeValues__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:270:1: ( ( rule__IntRangeValues__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:270:1: ( ( rule__IntRangeValues__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:271:1: ( rule__IntRangeValues__Group__0 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:272:1: ( rule__IntRangeValues__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:272:2: rule__IntRangeValues__Group__0
            {
            pushFollow(FOLLOW_rule__IntRangeValues__Group__0_in_ruleIntRangeValues514);
            rule__IntRangeValues__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIntRangeValuesAccess().getGroup()); 

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
    // $ANTLR end "ruleIntRangeValues"


    // $ANTLR start "entryRuleIntRangeRange"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:284:1: entryRuleIntRangeRange : ruleIntRangeRange EOF ;
    public final void entryRuleIntRangeRange() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:285:1: ( ruleIntRangeRange EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:286:1: ruleIntRangeRange EOF
            {
             before(grammarAccess.getIntRangeRangeRule()); 
            pushFollow(FOLLOW_ruleIntRangeRange_in_entryRuleIntRangeRange541);
            ruleIntRangeRange();

            state._fsp--;

             after(grammarAccess.getIntRangeRangeRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntRangeRange548); 

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
    // $ANTLR end "entryRuleIntRangeRange"


    // $ANTLR start "ruleIntRangeRange"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:293:1: ruleIntRangeRange : ( ( rule__IntRangeRange__Group__0 ) ) ;
    public final void ruleIntRangeRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:297:2: ( ( ( rule__IntRangeRange__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:298:1: ( ( rule__IntRangeRange__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:298:1: ( ( rule__IntRangeRange__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:299:1: ( rule__IntRangeRange__Group__0 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:300:1: ( rule__IntRangeRange__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:300:2: rule__IntRangeRange__Group__0
            {
            pushFollow(FOLLOW_rule__IntRangeRange__Group__0_in_ruleIntRangeRange574);
            rule__IntRangeRange__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIntRangeRangeAccess().getGroup()); 

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
    // $ANTLR end "ruleIntRangeRange"


    // $ANTLR start "entryRuleResource"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:312:1: entryRuleResource : ruleResource EOF ;
    public final void entryRuleResource() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:313:1: ( ruleResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:314:1: ruleResource EOF
            {
             before(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_ruleResource_in_entryRuleResource601);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getResourceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResource608); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:321:1: ruleResource : ( ( rule__Resource__Alternatives ) ) ;
    public final void ruleResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:325:2: ( ( ( rule__Resource__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:326:1: ( ( rule__Resource__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:326:1: ( ( rule__Resource__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:327:1: ( rule__Resource__Alternatives )
            {
             before(grammarAccess.getResourceAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:328:1: ( rule__Resource__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:328:2: rule__Resource__Alternatives
            {
            pushFollow(FOLLOW_rule__Resource__Alternatives_in_ruleResource634);
            rule__Resource__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getResourceAccess().getAlternatives()); 

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


    // $ANTLR start "entryRuleResourceReference"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:340:1: entryRuleResourceReference : ruleResourceReference EOF ;
    public final void entryRuleResourceReference() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:341:1: ( ruleResourceReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:342:1: ruleResourceReference EOF
            {
             before(grammarAccess.getResourceReferenceRule()); 
            pushFollow(FOLLOW_ruleResourceReference_in_entryRuleResourceReference661);
            ruleResourceReference();

            state._fsp--;

             after(grammarAccess.getResourceReferenceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleResourceReference668); 

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
    // $ANTLR end "entryRuleResourceReference"


    // $ANTLR start "ruleResourceReference"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:349:1: ruleResourceReference : ( ( rule__ResourceReference__Group__0 ) ) ;
    public final void ruleResourceReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:353:2: ( ( ( rule__ResourceReference__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:354:1: ( ( rule__ResourceReference__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:354:1: ( ( rule__ResourceReference__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:355:1: ( rule__ResourceReference__Group__0 )
            {
             before(grammarAccess.getResourceReferenceAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:356:1: ( rule__ResourceReference__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:356:2: rule__ResourceReference__Group__0
            {
            pushFollow(FOLLOW_rule__ResourceReference__Group__0_in_ruleResourceReference694);
            rule__ResourceReference__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getGroup()); 

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
    // $ANTLR end "ruleResourceReference"


    // $ANTLR start "entryRuleGlobalResourceRef"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:368:1: entryRuleGlobalResourceRef : ruleGlobalResourceRef EOF ;
    public final void entryRuleGlobalResourceRef() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:369:1: ( ruleGlobalResourceRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:370:1: ruleGlobalResourceRef EOF
            {
             before(grammarAccess.getGlobalResourceRefRule()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef721);
            ruleGlobalResourceRef();

            state._fsp--;

             after(grammarAccess.getGlobalResourceRefRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalResourceRef728); 

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
    // $ANTLR end "entryRuleGlobalResourceRef"


    // $ANTLR start "ruleGlobalResourceRef"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:377:1: ruleGlobalResourceRef : ( ( rule__GlobalResourceRef__Group__0 ) ) ;
    public final void ruleGlobalResourceRef() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:381:2: ( ( ( rule__GlobalResourceRef__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:382:1: ( ( rule__GlobalResourceRef__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:382:1: ( ( rule__GlobalResourceRef__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:383:1: ( rule__GlobalResourceRef__Group__0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:384:1: ( rule__GlobalResourceRef__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:384:2: rule__GlobalResourceRef__Group__0
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__0_in_ruleGlobalResourceRef754);
            rule__GlobalResourceRef__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGlobalResourceRefAccess().getGroup()); 

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
    // $ANTLR end "ruleGlobalResourceRef"


    // $ANTLR start "entryRuleLocalResource"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:396:1: entryRuleLocalResource : ruleLocalResource EOF ;
    public final void entryRuleLocalResource() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:397:1: ( ruleLocalResource EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:398:1: ruleLocalResource EOF
            {
             before(grammarAccess.getLocalResourceRule()); 
            pushFollow(FOLLOW_ruleLocalResource_in_entryRuleLocalResource781);
            ruleLocalResource();

            state._fsp--;

             after(grammarAccess.getLocalResourceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalResource788); 

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
    // $ANTLR end "entryRuleLocalResource"


    // $ANTLR start "ruleLocalResource"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:405:1: ruleLocalResource : ( ( rule__LocalResource__Group__0 ) ) ;
    public final void ruleLocalResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:409:2: ( ( ( rule__LocalResource__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:410:1: ( ( rule__LocalResource__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:410:1: ( ( rule__LocalResource__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:411:1: ( rule__LocalResource__Group__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:412:1: ( rule__LocalResource__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:412:2: rule__LocalResource__Group__0
            {
            pushFollow(FOLLOW_rule__LocalResource__Group__0_in_ruleLocalResource814);
            rule__LocalResource__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLocalResourceAccess().getGroup()); 

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
    // $ANTLR end "ruleLocalResource"


    // $ANTLR start "entryRuleOutput"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:424:1: entryRuleOutput : ruleOutput EOF ;
    public final void entryRuleOutput() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:425:1: ( ruleOutput EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:426:1: ruleOutput EOF
            {
             before(grammarAccess.getOutputRule()); 
            pushFollow(FOLLOW_ruleOutput_in_entryRuleOutput841);
            ruleOutput();

            state._fsp--;

             after(grammarAccess.getOutputRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutput848); 

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
    // $ANTLR end "entryRuleOutput"


    // $ANTLR start "ruleOutput"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:433:1: ruleOutput : ( ( rule__Output__Alternatives ) ) ;
    public final void ruleOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:437:2: ( ( ( rule__Output__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:438:1: ( ( rule__Output__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:438:1: ( ( rule__Output__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:439:1: ( rule__Output__Alternatives )
            {
             before(grammarAccess.getOutputAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:440:1: ( rule__Output__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:440:2: rule__Output__Alternatives
            {
            pushFollow(FOLLOW_rule__Output__Alternatives_in_ruleOutput874);
            rule__Output__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getOutputAccess().getAlternatives()); 

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
    // $ANTLR end "ruleOutput"


    // $ANTLR start "entryRuleGlobalOutputRef"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:452:1: entryRuleGlobalOutputRef : ruleGlobalOutputRef EOF ;
    public final void entryRuleGlobalOutputRef() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:453:1: ( ruleGlobalOutputRef EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:454:1: ruleGlobalOutputRef EOF
            {
             before(grammarAccess.getGlobalOutputRefRule()); 
            pushFollow(FOLLOW_ruleGlobalOutputRef_in_entryRuleGlobalOutputRef901);
            ruleGlobalOutputRef();

            state._fsp--;

             after(grammarAccess.getGlobalOutputRefRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalOutputRef908); 

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
    // $ANTLR end "entryRuleGlobalOutputRef"


    // $ANTLR start "ruleGlobalOutputRef"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:461:1: ruleGlobalOutputRef : ( ( rule__GlobalOutputRef__Group__0 ) ) ;
    public final void ruleGlobalOutputRef() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:465:2: ( ( ( rule__GlobalOutputRef__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:466:1: ( ( rule__GlobalOutputRef__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:466:1: ( ( rule__GlobalOutputRef__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:467:1: ( rule__GlobalOutputRef__Group__0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:468:1: ( rule__GlobalOutputRef__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:468:2: rule__GlobalOutputRef__Group__0
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__Group__0_in_ruleGlobalOutputRef934);
            rule__GlobalOutputRef__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGlobalOutputRefAccess().getGroup()); 

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
    // $ANTLR end "ruleGlobalOutputRef"


    // $ANTLR start "entryRuleOutputReference"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:480:1: entryRuleOutputReference : ruleOutputReference EOF ;
    public final void entryRuleOutputReference() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:481:1: ( ruleOutputReference EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:482:1: ruleOutputReference EOF
            {
             before(grammarAccess.getOutputReferenceRule()); 
            pushFollow(FOLLOW_ruleOutputReference_in_entryRuleOutputReference961);
            ruleOutputReference();

            state._fsp--;

             after(grammarAccess.getOutputReferenceRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutputReference968); 

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
    // $ANTLR end "entryRuleOutputReference"


    // $ANTLR start "ruleOutputReference"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:489:1: ruleOutputReference : ( ( rule__OutputReference__Group__0 ) ) ;
    public final void ruleOutputReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:493:2: ( ( ( rule__OutputReference__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:494:1: ( ( rule__OutputReference__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:494:1: ( ( rule__OutputReference__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:495:1: ( rule__OutputReference__Group__0 )
            {
             before(grammarAccess.getOutputReferenceAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:496:1: ( rule__OutputReference__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:496:2: rule__OutputReference__Group__0
            {
            pushFollow(FOLLOW_rule__OutputReference__Group__0_in_ruleOutputReference994);
            rule__OutputReference__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOutputReferenceAccess().getGroup()); 

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
    // $ANTLR end "ruleOutputReference"


    // $ANTLR start "entryRuleLocalOutput"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:508:1: entryRuleLocalOutput : ruleLocalOutput EOF ;
    public final void entryRuleLocalOutput() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:509:1: ( ruleLocalOutput EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:510:1: ruleLocalOutput EOF
            {
             before(grammarAccess.getLocalOutputRule()); 
            pushFollow(FOLLOW_ruleLocalOutput_in_entryRuleLocalOutput1021);
            ruleLocalOutput();

            state._fsp--;

             after(grammarAccess.getLocalOutputRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLocalOutput1028); 

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
    // $ANTLR end "entryRuleLocalOutput"


    // $ANTLR start "ruleLocalOutput"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:517:1: ruleLocalOutput : ( ( rule__LocalOutput__PathAssignment ) ) ;
    public final void ruleLocalOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:521:2: ( ( ( rule__LocalOutput__PathAssignment ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:522:1: ( ( rule__LocalOutput__PathAssignment ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:522:1: ( ( rule__LocalOutput__PathAssignment ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:523:1: ( rule__LocalOutput__PathAssignment )
            {
             before(grammarAccess.getLocalOutputAccess().getPathAssignment()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:524:1: ( rule__LocalOutput__PathAssignment )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:524:2: rule__LocalOutput__PathAssignment
            {
            pushFollow(FOLLOW_rule__LocalOutput__PathAssignment_in_ruleLocalOutput1054);
            rule__LocalOutput__PathAssignment();

            state._fsp--;


            }

             after(grammarAccess.getLocalOutputAccess().getPathAssignment()); 

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
    // $ANTLR end "ruleLocalOutput"


    // $ANTLR start "entryRuleAnalysis"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:536:1: entryRuleAnalysis : ruleAnalysis EOF ;
    public final void entryRuleAnalysis() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:537:1: ( ruleAnalysis EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:538:1: ruleAnalysis EOF
            {
             before(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_ruleAnalysis_in_entryRuleAnalysis1081);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getAnalysisRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAnalysis1088); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:545:1: ruleAnalysis : ( ( rule__Analysis__NameAssignment ) ) ;
    public final void ruleAnalysis() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:549:2: ( ( ( rule__Analysis__NameAssignment ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:550:1: ( ( rule__Analysis__NameAssignment ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:550:1: ( ( rule__Analysis__NameAssignment ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:551:1: ( rule__Analysis__NameAssignment )
            {
             before(grammarAccess.getAnalysisAccess().getNameAssignment()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:552:1: ( rule__Analysis__NameAssignment )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:552:2: rule__Analysis__NameAssignment
            {
            pushFollow(FOLLOW_rule__Analysis__NameAssignment_in_ruleAnalysis1114);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:564:1: entryRuleKIdentifier : ruleKIdentifier EOF ;
    public final void entryRuleKIdentifier() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:565:1: ( ruleKIdentifier EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:566:1: ruleKIdentifier EOF
            {
             before(grammarAccess.getKIdentifierRule()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier1141);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getKIdentifierRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleKIdentifier1148); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:573:1: ruleKIdentifier : ( ( rule__KIdentifier__Group__0 ) ) ;
    public final void ruleKIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:577:2: ( ( ( rule__KIdentifier__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:578:1: ( ( rule__KIdentifier__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:578:1: ( ( rule__KIdentifier__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:579:1: ( rule__KIdentifier__Group__0 )
            {
             before(grammarAccess.getKIdentifierAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:580:1: ( rule__KIdentifier__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:580:2: rule__KIdentifier__Group__0
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier1174);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:592:1: entryRulePersistentEntry : rulePersistentEntry EOF ;
    public final void entryRulePersistentEntry() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:593:1: ( rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:594:1: rulePersistentEntry EOF
            {
             before(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry1201);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getPersistentEntryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePersistentEntry1208); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:601:1: rulePersistentEntry : ( ( rule__PersistentEntry__Group__0 ) ) ;
    public final void rulePersistentEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:605:2: ( ( ( rule__PersistentEntry__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:606:1: ( ( rule__PersistentEntry__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:606:1: ( ( rule__PersistentEntry__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:607:1: ( rule__PersistentEntry__Group__0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:608:1: ( rule__PersistentEntry__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:608:2: rule__PersistentEntry__Group__0
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry1234);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:620:1: entryRuleQualifiedID : ruleQualifiedID EOF ;
    public final void entryRuleQualifiedID() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:621:1: ( ruleQualifiedID EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:622:1: ruleQualifiedID EOF
            {
             before(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID1261);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getQualifiedIDRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID1268); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:629:1: ruleQualifiedID : ( ( rule__QualifiedID__Group__0 ) ) ;
    public final void ruleQualifiedID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:633:2: ( ( ( rule__QualifiedID__Group__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:634:1: ( ( rule__QualifiedID__Group__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:634:1: ( ( rule__QualifiedID__Group__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:635:1: ( rule__QualifiedID__Group__0 )
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:636:1: ( rule__QualifiedID__Group__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:636:2: rule__QualifiedID__Group__0
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID1294);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:648:1: entryRulePropertyValue : rulePropertyValue EOF ;
    public final void entryRulePropertyValue() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:649:1: ( rulePropertyValue EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:650:1: rulePropertyValue EOF
            {
             before(grammarAccess.getPropertyValueRule()); 
            pushFollow(FOLLOW_rulePropertyValue_in_entryRulePropertyValue1321);
            rulePropertyValue();

            state._fsp--;

             after(grammarAccess.getPropertyValueRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePropertyValue1328); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:657:1: rulePropertyValue : ( ( rule__PropertyValue__Alternatives ) ) ;
    public final void rulePropertyValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:661:2: ( ( ( rule__PropertyValue__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:662:1: ( ( rule__PropertyValue__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:662:1: ( ( rule__PropertyValue__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:663:1: ( rule__PropertyValue__Alternatives )
            {
             before(grammarAccess.getPropertyValueAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:664:1: ( rule__PropertyValue__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:664:2: rule__PropertyValue__Alternatives
            {
            pushFollow(FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue1354);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:676:1: entryRuleFloat : ruleFloat EOF ;
    public final void entryRuleFloat() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:677:1: ( ruleFloat EOF )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:678:1: ruleFloat EOF
            {
             before(grammarAccess.getFloatRule()); 
            pushFollow(FOLLOW_ruleFloat_in_entryRuleFloat1381);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFloat1388); 

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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:685:1: ruleFloat : ( ( rule__Float__Alternatives ) ) ;
    public final void ruleFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:689:2: ( ( ( rule__Float__Alternatives ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:690:1: ( ( rule__Float__Alternatives ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:690:1: ( ( rule__Float__Alternatives ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:691:1: ( rule__Float__Alternatives )
            {
             before(grammarAccess.getFloatAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:692:1: ( rule__Float__Alternatives )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:692:2: rule__Float__Alternatives
            {
            pushFollow(FOLLOW_rule__Float__Alternatives_in_ruleFloat1414);
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


    // $ANTLR start "rule__Grana__Alternatives_2_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:704:1: rule__Grana__Alternatives_2_1 : ( ( ( rule__Grana__ExecuteAllAssignment_2_1_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* ) ) );
    public final void rule__Grana__Alternatives_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:708:1: ( ( ( rule__Grana__ExecuteAllAssignment_2_1_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==35) ) {
                alt2=1;
            }
            else if ( (LA2_0==RULE_ID) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:709:1: ( ( rule__Grana__ExecuteAllAssignment_2_1_0 ) )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:709:1: ( ( rule__Grana__ExecuteAllAssignment_2_1_0 ) )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:710:1: ( rule__Grana__ExecuteAllAssignment_2_1_0 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_1_0()); 
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:711:1: ( rule__Grana__ExecuteAllAssignment_2_1_0 )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:711:2: rule__Grana__ExecuteAllAssignment_2_1_0
                    {
                    pushFollow(FOLLOW_rule__Grana__ExecuteAllAssignment_2_1_0_in_rule__Grana__Alternatives_2_11450);
                    rule__Grana__ExecuteAllAssignment_2_1_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_1_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:715:6: ( ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* ) )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:715:6: ( ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* ) )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:716:1: ( ( rule__Grana__ExecuteAssignment_2_1_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_1_1 )* )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:716:1: ( ( rule__Grana__ExecuteAssignment_2_1_1 ) )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:717:1: ( rule__Grana__ExecuteAssignment_2_1_1 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_1_1()); 
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:718:1: ( rule__Grana__ExecuteAssignment_2_1_1 )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:718:2: rule__Grana__ExecuteAssignment_2_1_1
                    {
                    pushFollow(FOLLOW_rule__Grana__ExecuteAssignment_2_1_1_in_rule__Grana__Alternatives_2_11470);
                    rule__Grana__ExecuteAssignment_2_1_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAssignment_2_1_1()); 

                    }

                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:721:1: ( ( rule__Grana__ExecuteAssignment_2_1_1 )* )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:722:1: ( rule__Grana__ExecuteAssignment_2_1_1 )*
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_1_1()); 
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:723:1: ( rule__Grana__ExecuteAssignment_2_1_1 )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:723:2: rule__Grana__ExecuteAssignment_2_1_1
                    	    {
                    	    pushFollow(FOLLOW_rule__Grana__ExecuteAssignment_2_1_1_in_rule__Grana__Alternatives_2_11482);
                    	    rule__Grana__ExecuteAssignment_2_1_1();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                     after(grammarAccess.getGranaAccess().getExecuteAssignment_2_1_1()); 

                    }


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
    // $ANTLR end "rule__Grana__Alternatives_2_1"


    // $ANTLR start "rule__Job__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:733:1: rule__Job__Alternatives : ( ( ruleRegularJob ) | ( ruleRangeJob ) );
    public final void rule__Job__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:737:1: ( ( ruleRegularJob ) | ( ruleRangeJob ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            else if ( (LA3_0==20) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:738:1: ( ruleRegularJob )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:738:1: ( ruleRegularJob )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:739:1: ruleRegularJob
                    {
                     before(grammarAccess.getJobAccess().getRegularJobParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleRegularJob_in_rule__Job__Alternatives1518);
                    ruleRegularJob();

                    state._fsp--;

                     after(grammarAccess.getJobAccess().getRegularJobParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:744:6: ( ruleRangeJob )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:744:6: ( ruleRangeJob )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:745:1: ruleRangeJob
                    {
                     before(grammarAccess.getJobAccess().getRangeJobParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleRangeJob_in_rule__Job__Alternatives1535);
                    ruleRangeJob();

                    state._fsp--;

                     after(grammarAccess.getJobAccess().getRangeJobParserRuleCall_1()); 

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
    // $ANTLR end "rule__Job__Alternatives"


    // $ANTLR start "rule__Range__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:755:1: rule__Range__Alternatives : ( ( ruleFloatRange ) | ( ruleIntRange ) );
    public final void rule__Range__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:759:1: ( ( ruleFloatRange ) | ( ruleIntRange ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==24) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=26 && LA4_0<=27)) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:760:1: ( ruleFloatRange )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:760:1: ( ruleFloatRange )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:761:1: ruleFloatRange
                    {
                     before(grammarAccess.getRangeAccess().getFloatRangeParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleFloatRange_in_rule__Range__Alternatives1567);
                    ruleFloatRange();

                    state._fsp--;

                     after(grammarAccess.getRangeAccess().getFloatRangeParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:766:6: ( ruleIntRange )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:766:6: ( ruleIntRange )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:767:1: ruleIntRange
                    {
                     before(grammarAccess.getRangeAccess().getIntRangeParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleIntRange_in_rule__Range__Alternatives1584);
                    ruleIntRange();

                    state._fsp--;

                     after(grammarAccess.getRangeAccess().getIntRangeParserRuleCall_1()); 

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
    // $ANTLR end "rule__Range__Alternatives"


    // $ANTLR start "rule__IntRange__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:777:1: rule__IntRange__Alternatives : ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) );
    public final void rule__IntRange__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:781:1: ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==27) ) {
                alt5=1;
            }
            else if ( (LA5_0==26) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:782:1: ( ruleIntRangeRange )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:782:1: ( ruleIntRangeRange )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:783:1: ruleIntRangeRange
                    {
                     before(grammarAccess.getIntRangeAccess().getIntRangeRangeParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleIntRangeRange_in_rule__IntRange__Alternatives1616);
                    ruleIntRangeRange();

                    state._fsp--;

                     after(grammarAccess.getIntRangeAccess().getIntRangeRangeParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:788:6: ( ruleIntRangeValues )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:788:6: ( ruleIntRangeValues )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:789:1: ruleIntRangeValues
                    {
                     before(grammarAccess.getIntRangeAccess().getIntRangeValuesParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleIntRangeValues_in_rule__IntRange__Alternatives1633);
                    ruleIntRangeValues();

                    state._fsp--;

                     after(grammarAccess.getIntRangeAccess().getIntRangeValuesParserRuleCall_1()); 

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
    // $ANTLR end "rule__IntRange__Alternatives"


    // $ANTLR start "rule__Resource__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:799:1: rule__Resource__Alternatives : ( ( ruleResourceReference ) | ( ruleLocalResource ) );
    public final void rule__Resource__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:803:1: ( ( ruleResourceReference ) | ( ruleLocalResource ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==29) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_STRING) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:804:1: ( ruleResourceReference )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:804:1: ( ruleResourceReference )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:805:1: ruleResourceReference
                    {
                     before(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleResourceReference_in_rule__Resource__Alternatives1665);
                    ruleResourceReference();

                    state._fsp--;

                     after(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:810:6: ( ruleLocalResource )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:810:6: ( ruleLocalResource )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:811:1: ruleLocalResource
                    {
                     before(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLocalResource_in_rule__Resource__Alternatives1682);
                    ruleLocalResource();

                    state._fsp--;

                     after(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 

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
    // $ANTLR end "rule__Resource__Alternatives"


    // $ANTLR start "rule__Output__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:821:1: rule__Output__Alternatives : ( ( ruleOutputReference ) | ( ruleLocalOutput ) );
    public final void rule__Output__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:825:1: ( ( ruleOutputReference ) | ( ruleLocalOutput ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==29) ) {
                alt7=1;
            }
            else if ( (LA7_0==RULE_STRING) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:826:1: ( ruleOutputReference )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:826:1: ( ruleOutputReference )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:827:1: ruleOutputReference
                    {
                     before(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleOutputReference_in_rule__Output__Alternatives1714);
                    ruleOutputReference();

                    state._fsp--;

                     after(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:832:6: ( ruleLocalOutput )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:832:6: ( ruleLocalOutput )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:833:1: ruleLocalOutput
                    {
                     before(grammarAccess.getOutputAccess().getLocalOutputParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleLocalOutput_in_rule__Output__Alternatives1731);
                    ruleLocalOutput();

                    state._fsp--;

                     after(grammarAccess.getOutputAccess().getLocalOutputParserRuleCall_1()); 

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
    // $ANTLR end "rule__Output__Alternatives"


    // $ANTLR start "rule__PropertyValue__Alternatives"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:843:1: rule__PropertyValue__Alternatives : ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) );
    public final void rule__PropertyValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:847:1: ( ( RULE_BOOLEAN ) | ( RULE_STRING ) | ( ruleFloat ) | ( ruleQualifiedID ) )
            int alt8=4;
            switch ( input.LA(1) ) {
            case RULE_BOOLEAN:
                {
                alt8=1;
                }
                break;
            case RULE_STRING:
                {
                alt8=2;
                }
                break;
            case RULE_TFLOAT:
            case RULE_NATURAL:
                {
                alt8=3;
                }
                break;
            case RULE_ID:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:848:1: ( RULE_BOOLEAN )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:848:1: ( RULE_BOOLEAN )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:849:1: RULE_BOOLEAN
                    {
                     before(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 
                    match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives1763); 
                     after(grammarAccess.getPropertyValueAccess().getBOOLEANTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:854:6: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:854:6: ( RULE_STRING )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:855:1: RULE_STRING
                    {
                     before(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives1780); 
                     after(grammarAccess.getPropertyValueAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:860:6: ( ruleFloat )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:860:6: ( ruleFloat )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:861:1: ruleFloat
                    {
                     before(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives1797);
                    ruleFloat();

                    state._fsp--;

                     after(grammarAccess.getPropertyValueAccess().getFloatParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:866:6: ( ruleQualifiedID )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:866:6: ( ruleQualifiedID )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:867:1: ruleQualifiedID
                    {
                     before(grammarAccess.getPropertyValueAccess().getQualifiedIDParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives1814);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:877:1: rule__Float__Alternatives : ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) );
    public final void rule__Float__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:881:1: ( ( RULE_TFLOAT ) | ( RULE_NATURAL ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_TFLOAT) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_NATURAL) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:882:1: ( RULE_TFLOAT )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:882:1: ( RULE_TFLOAT )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:883:1: RULE_TFLOAT
                    {
                     before(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 
                    match(input,RULE_TFLOAT,FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives1846); 
                     after(grammarAccess.getFloatAccess().getTFLOATTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:888:6: ( RULE_NATURAL )
                    {
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:888:6: ( RULE_NATURAL )
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:889:1: RULE_NATURAL
                    {
                     before(grammarAccess.getFloatAccess().getNATURALTerminalRuleCall_1()); 
                    match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives1863); 
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


    // $ANTLR start "rule__Grana__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:901:1: rule__Grana__Group__0 : rule__Grana__Group__0__Impl rule__Grana__Group__1 ;
    public final void rule__Grana__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:905:1: ( rule__Grana__Group__0__Impl rule__Grana__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:906:2: rule__Grana__Group__0__Impl rule__Grana__Group__1
            {
            pushFollow(FOLLOW_rule__Grana__Group__0__Impl_in_rule__Grana__Group__01893);
            rule__Grana__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group__1_in_rule__Grana__Group__01896);
            rule__Grana__Group__1();

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
    // $ANTLR end "rule__Grana__Group__0"


    // $ANTLR start "rule__Grana__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:913:1: rule__Grana__Group__0__Impl : ( ( rule__Grana__Group_0__0 )? ) ;
    public final void rule__Grana__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:917:1: ( ( ( rule__Grana__Group_0__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:918:1: ( ( rule__Grana__Group_0__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:918:1: ( ( rule__Grana__Group_0__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:919:1: ( rule__Grana__Group_0__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:920:1: ( rule__Grana__Group_0__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==12) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:920:2: rule__Grana__Group_0__0
                    {
                    pushFollow(FOLLOW_rule__Grana__Group_0__0_in_rule__Grana__Group__0__Impl1923);
                    rule__Grana__Group_0__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getGranaAccess().getGroup_0()); 

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
    // $ANTLR end "rule__Grana__Group__0__Impl"


    // $ANTLR start "rule__Grana__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:930:1: rule__Grana__Group__1 : rule__Grana__Group__1__Impl rule__Grana__Group__2 ;
    public final void rule__Grana__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:934:1: ( rule__Grana__Group__1__Impl rule__Grana__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:935:2: rule__Grana__Group__1__Impl rule__Grana__Group__2
            {
            pushFollow(FOLLOW_rule__Grana__Group__1__Impl_in_rule__Grana__Group__11954);
            rule__Grana__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group__2_in_rule__Grana__Group__11957);
            rule__Grana__Group__2();

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
    // $ANTLR end "rule__Grana__Group__1"


    // $ANTLR start "rule__Grana__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:942:1: rule__Grana__Group__1__Impl : ( ( rule__Grana__Group_1__0 )? ) ;
    public final void rule__Grana__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:946:1: ( ( ( rule__Grana__Group_1__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:947:1: ( ( rule__Grana__Group_1__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:947:1: ( ( rule__Grana__Group_1__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:948:1: ( rule__Grana__Group_1__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:949:1: ( rule__Grana__Group_1__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==13) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:949:2: rule__Grana__Group_1__0
                    {
                    pushFollow(FOLLOW_rule__Grana__Group_1__0_in_rule__Grana__Group__1__Impl1984);
                    rule__Grana__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getGranaAccess().getGroup_1()); 

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
    // $ANTLR end "rule__Grana__Group__1__Impl"


    // $ANTLR start "rule__Grana__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:959:1: rule__Grana__Group__2 : rule__Grana__Group__2__Impl rule__Grana__Group__3 ;
    public final void rule__Grana__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:963:1: ( rule__Grana__Group__2__Impl rule__Grana__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:964:2: rule__Grana__Group__2__Impl rule__Grana__Group__3
            {
            pushFollow(FOLLOW_rule__Grana__Group__2__Impl_in_rule__Grana__Group__22015);
            rule__Grana__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group__3_in_rule__Grana__Group__22018);
            rule__Grana__Group__3();

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
    // $ANTLR end "rule__Grana__Group__2"


    // $ANTLR start "rule__Grana__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:971:1: rule__Grana__Group__2__Impl : ( ( rule__Grana__Group_2__0 ) ) ;
    public final void rule__Grana__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:975:1: ( ( ( rule__Grana__Group_2__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:976:1: ( ( rule__Grana__Group_2__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:976:1: ( ( rule__Grana__Group_2__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:977:1: ( rule__Grana__Group_2__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:978:1: ( rule__Grana__Group_2__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:978:2: rule__Grana__Group_2__0
            {
            pushFollow(FOLLOW_rule__Grana__Group_2__0_in_rule__Grana__Group__2__Impl2045);
            rule__Grana__Group_2__0();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getGroup_2()); 

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
    // $ANTLR end "rule__Grana__Group__2__Impl"


    // $ANTLR start "rule__Grana__Group__3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:988:1: rule__Grana__Group__3 : rule__Grana__Group__3__Impl ;
    public final void rule__Grana__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:992:1: ( rule__Grana__Group__3__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:993:2: rule__Grana__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group__3__Impl_in_rule__Grana__Group__32075);
            rule__Grana__Group__3__Impl();

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
    // $ANTLR end "rule__Grana__Group__3"


    // $ANTLR start "rule__Grana__Group__3__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:999:1: rule__Grana__Group__3__Impl : ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) ;
    public final void rule__Grana__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1003:1: ( ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1004:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1004:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1005:1: ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1005:1: ( ( rule__Grana__JobsAssignment_3 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1006:1: ( rule__Grana__JobsAssignment_3 )
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1007:1: ( rule__Grana__JobsAssignment_3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1007:2: rule__Grana__JobsAssignment_3
            {
            pushFollow(FOLLOW_rule__Grana__JobsAssignment_3_in_rule__Grana__Group__3__Impl2104);
            rule__Grana__JobsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getJobsAssignment_3()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1010:1: ( ( rule__Grana__JobsAssignment_3 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1011:1: ( rule__Grana__JobsAssignment_3 )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1012:1: ( rule__Grana__JobsAssignment_3 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==15||LA12_0==20) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1012:2: rule__Grana__JobsAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__Grana__JobsAssignment_3_in_rule__Grana__Group__3__Impl2116);
            	    rule__Grana__JobsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getGranaAccess().getJobsAssignment_3()); 

            }


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
    // $ANTLR end "rule__Grana__Group__3__Impl"


    // $ANTLR start "rule__Grana__Group_0__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1031:1: rule__Grana__Group_0__0 : rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 ;
    public final void rule__Grana__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1035:1: ( rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1036:2: rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1
            {
            pushFollow(FOLLOW_rule__Grana__Group_0__0__Impl_in_rule__Grana__Group_0__02157);
            rule__Grana__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group_0__1_in_rule__Grana__Group_0__02160);
            rule__Grana__Group_0__1();

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
    // $ANTLR end "rule__Grana__Group_0__0"


    // $ANTLR start "rule__Grana__Group_0__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1043:1: rule__Grana__Group_0__0__Impl : ( 'globalResources' ) ;
    public final void rule__Grana__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1047:1: ( ( 'globalResources' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1048:1: ( 'globalResources' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1048:1: ( 'globalResources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1049:1: 'globalResources'
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0()); 
            match(input,12,FOLLOW_12_in_rule__Grana__Group_0__0__Impl2188); 
             after(grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0()); 

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
    // $ANTLR end "rule__Grana__Group_0__0__Impl"


    // $ANTLR start "rule__Grana__Group_0__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1062:1: rule__Grana__Group_0__1 : rule__Grana__Group_0__1__Impl ;
    public final void rule__Grana__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1066:1: ( rule__Grana__Group_0__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1067:2: rule__Grana__Group_0__1__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group_0__1__Impl_in_rule__Grana__Group_0__12219);
            rule__Grana__Group_0__1__Impl();

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
    // $ANTLR end "rule__Grana__Group_0__1"


    // $ANTLR start "rule__Grana__Group_0__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1073:1: rule__Grana__Group_0__1__Impl : ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) ;
    public final void rule__Grana__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1077:1: ( ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1078:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1078:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1079:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1080:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_ID) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1080:2: rule__Grana__GlobalResourcesAssignment_0_1
            	    {
            	    pushFollow(FOLLOW_rule__Grana__GlobalResourcesAssignment_0_1_in_rule__Grana__Group_0__1__Impl2246);
            	    rule__Grana__GlobalResourcesAssignment_0_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 

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
    // $ANTLR end "rule__Grana__Group_0__1__Impl"


    // $ANTLR start "rule__Grana__Group_1__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1094:1: rule__Grana__Group_1__0 : rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 ;
    public final void rule__Grana__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1098:1: ( rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1099:2: rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1
            {
            pushFollow(FOLLOW_rule__Grana__Group_1__0__Impl_in_rule__Grana__Group_1__02281);
            rule__Grana__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group_1__1_in_rule__Grana__Group_1__02284);
            rule__Grana__Group_1__1();

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
    // $ANTLR end "rule__Grana__Group_1__0"


    // $ANTLR start "rule__Grana__Group_1__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1106:1: rule__Grana__Group_1__0__Impl : ( 'globalOutputs' ) ;
    public final void rule__Grana__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1110:1: ( ( 'globalOutputs' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1111:1: ( 'globalOutputs' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1111:1: ( 'globalOutputs' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1112:1: 'globalOutputs'
            {
             before(grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0()); 
            match(input,13,FOLLOW_13_in_rule__Grana__Group_1__0__Impl2312); 
             after(grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0()); 

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
    // $ANTLR end "rule__Grana__Group_1__0__Impl"


    // $ANTLR start "rule__Grana__Group_1__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1125:1: rule__Grana__Group_1__1 : rule__Grana__Group_1__1__Impl ;
    public final void rule__Grana__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1129:1: ( rule__Grana__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1130:2: rule__Grana__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group_1__1__Impl_in_rule__Grana__Group_1__12343);
            rule__Grana__Group_1__1__Impl();

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
    // $ANTLR end "rule__Grana__Group_1__1"


    // $ANTLR start "rule__Grana__Group_1__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1136:1: rule__Grana__Group_1__1__Impl : ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) ;
    public final void rule__Grana__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1140:1: ( ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1141:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1141:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1142:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1143:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1143:2: rule__Grana__GloobalOutputsAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_rule__Grana__GloobalOutputsAssignment_1_1_in_rule__Grana__Group_1__1__Impl2370);
            	    rule__Grana__GloobalOutputsAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

             after(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1()); 

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
    // $ANTLR end "rule__Grana__Group_1__1__Impl"


    // $ANTLR start "rule__Grana__Group_2__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1157:1: rule__Grana__Group_2__0 : rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 ;
    public final void rule__Grana__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1161:1: ( rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1162:2: rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1
            {
            pushFollow(FOLLOW_rule__Grana__Group_2__0__Impl_in_rule__Grana__Group_2__02405);
            rule__Grana__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Grana__Group_2__1_in_rule__Grana__Group_2__02408);
            rule__Grana__Group_2__1();

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
    // $ANTLR end "rule__Grana__Group_2__0"


    // $ANTLR start "rule__Grana__Group_2__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1169:1: rule__Grana__Group_2__0__Impl : ( 'execute' ) ;
    public final void rule__Grana__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1173:1: ( ( 'execute' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1174:1: ( 'execute' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1174:1: ( 'execute' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1175:1: 'execute'
            {
             before(grammarAccess.getGranaAccess().getExecuteKeyword_2_0()); 
            match(input,14,FOLLOW_14_in_rule__Grana__Group_2__0__Impl2436); 
             after(grammarAccess.getGranaAccess().getExecuteKeyword_2_0()); 

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
    // $ANTLR end "rule__Grana__Group_2__0__Impl"


    // $ANTLR start "rule__Grana__Group_2__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1188:1: rule__Grana__Group_2__1 : rule__Grana__Group_2__1__Impl ;
    public final void rule__Grana__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1192:1: ( rule__Grana__Group_2__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1193:2: rule__Grana__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__Grana__Group_2__1__Impl_in_rule__Grana__Group_2__12467);
            rule__Grana__Group_2__1__Impl();

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
    // $ANTLR end "rule__Grana__Group_2__1"


    // $ANTLR start "rule__Grana__Group_2__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1199:1: rule__Grana__Group_2__1__Impl : ( ( rule__Grana__Alternatives_2_1 ) ) ;
    public final void rule__Grana__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1203:1: ( ( ( rule__Grana__Alternatives_2_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1204:1: ( ( rule__Grana__Alternatives_2_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1204:1: ( ( rule__Grana__Alternatives_2_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1205:1: ( rule__Grana__Alternatives_2_1 )
            {
             before(grammarAccess.getGranaAccess().getAlternatives_2_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1206:1: ( rule__Grana__Alternatives_2_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1206:2: rule__Grana__Alternatives_2_1
            {
            pushFollow(FOLLOW_rule__Grana__Alternatives_2_1_in_rule__Grana__Group_2__1__Impl2494);
            rule__Grana__Alternatives_2_1();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getAlternatives_2_1()); 

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
    // $ANTLR end "rule__Grana__Group_2__1__Impl"


    // $ANTLR start "rule__RegularJob__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1220:1: rule__RegularJob__Group__0 : rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 ;
    public final void rule__RegularJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1224:1: ( rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1225:2: rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__0__Impl_in_rule__RegularJob__Group__02528);
            rule__RegularJob__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__1_in_rule__RegularJob__Group__02531);
            rule__RegularJob__Group__1();

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
    // $ANTLR end "rule__RegularJob__Group__0"


    // $ANTLR start "rule__RegularJob__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1232:1: rule__RegularJob__Group__0__Impl : ( 'job' ) ;
    public final void rule__RegularJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1236:1: ( ( 'job' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1237:1: ( 'job' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1237:1: ( 'job' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1238:1: 'job'
            {
             before(grammarAccess.getRegularJobAccess().getJobKeyword_0()); 
            match(input,15,FOLLOW_15_in_rule__RegularJob__Group__0__Impl2559); 
             after(grammarAccess.getRegularJobAccess().getJobKeyword_0()); 

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
    // $ANTLR end "rule__RegularJob__Group__0__Impl"


    // $ANTLR start "rule__RegularJob__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1251:1: rule__RegularJob__Group__1 : rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 ;
    public final void rule__RegularJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1255:1: ( rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1256:2: rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__1__Impl_in_rule__RegularJob__Group__12590);
            rule__RegularJob__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__2_in_rule__RegularJob__Group__12593);
            rule__RegularJob__Group__2();

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
    // $ANTLR end "rule__RegularJob__Group__1"


    // $ANTLR start "rule__RegularJob__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1263:1: rule__RegularJob__Group__1__Impl : ( ( rule__RegularJob__NameAssignment_1 ) ) ;
    public final void rule__RegularJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1267:1: ( ( ( rule__RegularJob__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1268:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1268:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1269:1: ( rule__RegularJob__NameAssignment_1 )
            {
             before(grammarAccess.getRegularJobAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1270:1: ( rule__RegularJob__NameAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1270:2: rule__RegularJob__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__RegularJob__NameAssignment_1_in_rule__RegularJob__Group__1__Impl2620);
            rule__RegularJob__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__RegularJob__Group__1__Impl"


    // $ANTLR start "rule__RegularJob__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1280:1: rule__RegularJob__Group__2 : rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 ;
    public final void rule__RegularJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1284:1: ( rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1285:2: rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__2__Impl_in_rule__RegularJob__Group__22650);
            rule__RegularJob__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__3_in_rule__RegularJob__Group__22653);
            rule__RegularJob__Group__3();

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
    // $ANTLR end "rule__RegularJob__Group__2"


    // $ANTLR start "rule__RegularJob__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1292:1: rule__RegularJob__Group__2__Impl : ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) ;
    public final void rule__RegularJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1296:1: ( ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1297:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1297:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1298:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1299:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==36) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1299:2: rule__RegularJob__LayoutBeforeAnalysisAssignment_2
                    {
                    pushFollow(FOLLOW_rule__RegularJob__LayoutBeforeAnalysisAssignment_2_in_rule__RegularJob__Group__2__Impl2680);
                    rule__RegularJob__LayoutBeforeAnalysisAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisAssignment_2()); 

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
    // $ANTLR end "rule__RegularJob__Group__2__Impl"


    // $ANTLR start "rule__RegularJob__Group__3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1309:1: rule__RegularJob__Group__3 : rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 ;
    public final void rule__RegularJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1313:1: ( rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1314:2: rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__3__Impl_in_rule__RegularJob__Group__32711);
            rule__RegularJob__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__4_in_rule__RegularJob__Group__32714);
            rule__RegularJob__Group__4();

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
    // $ANTLR end "rule__RegularJob__Group__3"


    // $ANTLR start "rule__RegularJob__Group__3__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1321:1: rule__RegularJob__Group__3__Impl : ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) ;
    public final void rule__RegularJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1325:1: ( ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1326:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1326:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1327:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1328:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==37) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1328:2: rule__RegularJob__MeasureExecutionTimeAssignment_3
                    {
                    pushFollow(FOLLOW_rule__RegularJob__MeasureExecutionTimeAssignment_3_in_rule__RegularJob__Group__3__Impl2741);
                    rule__RegularJob__MeasureExecutionTimeAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeAssignment_3()); 

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
    // $ANTLR end "rule__RegularJob__Group__3__Impl"


    // $ANTLR start "rule__RegularJob__Group__4"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1338:1: rule__RegularJob__Group__4 : rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 ;
    public final void rule__RegularJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1342:1: ( rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1343:2: rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__4__Impl_in_rule__RegularJob__Group__42772);
            rule__RegularJob__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__5_in_rule__RegularJob__Group__42775);
            rule__RegularJob__Group__5();

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
    // $ANTLR end "rule__RegularJob__Group__4"


    // $ANTLR start "rule__RegularJob__Group__4__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1350:1: rule__RegularJob__Group__4__Impl : ( 'resources' ) ;
    public final void rule__RegularJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1354:1: ( ( 'resources' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1355:1: ( 'resources' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1355:1: ( 'resources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1356:1: 'resources'
            {
             before(grammarAccess.getRegularJobAccess().getResourcesKeyword_4()); 
            match(input,16,FOLLOW_16_in_rule__RegularJob__Group__4__Impl2803); 
             after(grammarAccess.getRegularJobAccess().getResourcesKeyword_4()); 

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
    // $ANTLR end "rule__RegularJob__Group__4__Impl"


    // $ANTLR start "rule__RegularJob__Group__5"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1369:1: rule__RegularJob__Group__5 : rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 ;
    public final void rule__RegularJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1373:1: ( rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1374:2: rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__5__Impl_in_rule__RegularJob__Group__52834);
            rule__RegularJob__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__6_in_rule__RegularJob__Group__52837);
            rule__RegularJob__Group__6();

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
    // $ANTLR end "rule__RegularJob__Group__5"


    // $ANTLR start "rule__RegularJob__Group__5__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1381:1: rule__RegularJob__Group__5__Impl : ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) ;
    public final void rule__RegularJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1385:1: ( ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1386:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1386:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1387:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1387:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1388:1: ( rule__RegularJob__ResourcesAssignment_5 )
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1389:1: ( rule__RegularJob__ResourcesAssignment_5 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1389:2: rule__RegularJob__ResourcesAssignment_5
            {
            pushFollow(FOLLOW_rule__RegularJob__ResourcesAssignment_5_in_rule__RegularJob__Group__5__Impl2866);
            rule__RegularJob__ResourcesAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1392:1: ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1393:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1394:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_STRING||LA17_0==29) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1394:2: rule__RegularJob__ResourcesAssignment_5
            	    {
            	    pushFollow(FOLLOW_rule__RegularJob__ResourcesAssignment_5_in_rule__RegularJob__Group__5__Impl2878);
            	    rule__RegularJob__ResourcesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 

            }


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
    // $ANTLR end "rule__RegularJob__Group__5__Impl"


    // $ANTLR start "rule__RegularJob__Group__6"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1405:1: rule__RegularJob__Group__6 : rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 ;
    public final void rule__RegularJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1409:1: ( rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1410:2: rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__6__Impl_in_rule__RegularJob__Group__62911);
            rule__RegularJob__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__7_in_rule__RegularJob__Group__62914);
            rule__RegularJob__Group__7();

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
    // $ANTLR end "rule__RegularJob__Group__6"


    // $ANTLR start "rule__RegularJob__Group__6__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1417:1: rule__RegularJob__Group__6__Impl : ( 'layoutoptions' ) ;
    public final void rule__RegularJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1421:1: ( ( 'layoutoptions' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1422:1: ( 'layoutoptions' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1422:1: ( 'layoutoptions' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1423:1: 'layoutoptions'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6()); 
            match(input,17,FOLLOW_17_in_rule__RegularJob__Group__6__Impl2942); 
             after(grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6()); 

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
    // $ANTLR end "rule__RegularJob__Group__6__Impl"


    // $ANTLR start "rule__RegularJob__Group__7"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1436:1: rule__RegularJob__Group__7 : rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 ;
    public final void rule__RegularJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1440:1: ( rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1441:2: rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__7__Impl_in_rule__RegularJob__Group__72973);
            rule__RegularJob__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__8_in_rule__RegularJob__Group__72976);
            rule__RegularJob__Group__8();

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
    // $ANTLR end "rule__RegularJob__Group__7"


    // $ANTLR start "rule__RegularJob__Group__7__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1448:1: rule__RegularJob__Group__7__Impl : ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) ;
    public final void rule__RegularJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1452:1: ( ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1453:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1453:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1454:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1454:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1455:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1456:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1456:2: rule__RegularJob__LayoutOptionsAssignment_7
            {
            pushFollow(FOLLOW_rule__RegularJob__LayoutOptionsAssignment_7_in_rule__RegularJob__Group__7__Impl3005);
            rule__RegularJob__LayoutOptionsAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1459:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1460:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1461:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_ID) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1461:2: rule__RegularJob__LayoutOptionsAssignment_7
            	    {
            	    pushFollow(FOLLOW_rule__RegularJob__LayoutOptionsAssignment_7_in_rule__RegularJob__Group__7__Impl3017);
            	    rule__RegularJob__LayoutOptionsAssignment_7();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 

            }


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
    // $ANTLR end "rule__RegularJob__Group__7__Impl"


    // $ANTLR start "rule__RegularJob__Group__8"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1472:1: rule__RegularJob__Group__8 : rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 ;
    public final void rule__RegularJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1476:1: ( rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1477:2: rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__8__Impl_in_rule__RegularJob__Group__83050);
            rule__RegularJob__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__9_in_rule__RegularJob__Group__83053);
            rule__RegularJob__Group__9();

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
    // $ANTLR end "rule__RegularJob__Group__8"


    // $ANTLR start "rule__RegularJob__Group__8__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1484:1: rule__RegularJob__Group__8__Impl : ( 'analyses' ) ;
    public final void rule__RegularJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1488:1: ( ( 'analyses' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1489:1: ( 'analyses' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1489:1: ( 'analyses' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1490:1: 'analyses'
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesKeyword_8()); 
            match(input,18,FOLLOW_18_in_rule__RegularJob__Group__8__Impl3081); 
             after(grammarAccess.getRegularJobAccess().getAnalysesKeyword_8()); 

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
    // $ANTLR end "rule__RegularJob__Group__8__Impl"


    // $ANTLR start "rule__RegularJob__Group__9"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1503:1: rule__RegularJob__Group__9 : rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 ;
    public final void rule__RegularJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1507:1: ( rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1508:2: rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__9__Impl_in_rule__RegularJob__Group__93112);
            rule__RegularJob__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__10_in_rule__RegularJob__Group__93115);
            rule__RegularJob__Group__10();

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
    // $ANTLR end "rule__RegularJob__Group__9"


    // $ANTLR start "rule__RegularJob__Group__9__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1515:1: rule__RegularJob__Group__9__Impl : ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) ;
    public final void rule__RegularJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1519:1: ( ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1520:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1520:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1521:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1521:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1522:1: ( rule__RegularJob__AnalysesAssignment_9 )
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1523:1: ( rule__RegularJob__AnalysesAssignment_9 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1523:2: rule__RegularJob__AnalysesAssignment_9
            {
            pushFollow(FOLLOW_rule__RegularJob__AnalysesAssignment_9_in_rule__RegularJob__Group__9__Impl3144);
            rule__RegularJob__AnalysesAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1526:1: ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1527:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1528:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1528:2: rule__RegularJob__AnalysesAssignment_9
            	    {
            	    pushFollow(FOLLOW_rule__RegularJob__AnalysesAssignment_9_in_rule__RegularJob__Group__9__Impl3156);
            	    rule__RegularJob__AnalysesAssignment_9();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 

            }


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
    // $ANTLR end "rule__RegularJob__Group__9__Impl"


    // $ANTLR start "rule__RegularJob__Group__10"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1539:1: rule__RegularJob__Group__10 : rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 ;
    public final void rule__RegularJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1543:1: ( rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1544:2: rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__10__Impl_in_rule__RegularJob__Group__103189);
            rule__RegularJob__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RegularJob__Group__11_in_rule__RegularJob__Group__103192);
            rule__RegularJob__Group__11();

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
    // $ANTLR end "rule__RegularJob__Group__10"


    // $ANTLR start "rule__RegularJob__Group__10__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1551:1: rule__RegularJob__Group__10__Impl : ( 'output' ) ;
    public final void rule__RegularJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1555:1: ( ( 'output' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1556:1: ( 'output' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1556:1: ( 'output' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1557:1: 'output'
            {
             before(grammarAccess.getRegularJobAccess().getOutputKeyword_10()); 
            match(input,19,FOLLOW_19_in_rule__RegularJob__Group__10__Impl3220); 
             after(grammarAccess.getRegularJobAccess().getOutputKeyword_10()); 

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
    // $ANTLR end "rule__RegularJob__Group__10__Impl"


    // $ANTLR start "rule__RegularJob__Group__11"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1570:1: rule__RegularJob__Group__11 : rule__RegularJob__Group__11__Impl ;
    public final void rule__RegularJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1574:1: ( rule__RegularJob__Group__11__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1575:2: rule__RegularJob__Group__11__Impl
            {
            pushFollow(FOLLOW_rule__RegularJob__Group__11__Impl_in_rule__RegularJob__Group__113251);
            rule__RegularJob__Group__11__Impl();

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
    // $ANTLR end "rule__RegularJob__Group__11"


    // $ANTLR start "rule__RegularJob__Group__11__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1581:1: rule__RegularJob__Group__11__Impl : ( ( rule__RegularJob__OutputAssignment_11 ) ) ;
    public final void rule__RegularJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1585:1: ( ( ( rule__RegularJob__OutputAssignment_11 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1586:1: ( ( rule__RegularJob__OutputAssignment_11 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1586:1: ( ( rule__RegularJob__OutputAssignment_11 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1587:1: ( rule__RegularJob__OutputAssignment_11 )
            {
             before(grammarAccess.getRegularJobAccess().getOutputAssignment_11()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1588:1: ( rule__RegularJob__OutputAssignment_11 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1588:2: rule__RegularJob__OutputAssignment_11
            {
            pushFollow(FOLLOW_rule__RegularJob__OutputAssignment_11_in_rule__RegularJob__Group__11__Impl3278);
            rule__RegularJob__OutputAssignment_11();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getOutputAssignment_11()); 

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
    // $ANTLR end "rule__RegularJob__Group__11__Impl"


    // $ANTLR start "rule__RangeJob__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1622:1: rule__RangeJob__Group__0 : rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 ;
    public final void rule__RangeJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1626:1: ( rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1627:2: rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__0__Impl_in_rule__RangeJob__Group__03332);
            rule__RangeJob__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__1_in_rule__RangeJob__Group__03335);
            rule__RangeJob__Group__1();

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
    // $ANTLR end "rule__RangeJob__Group__0"


    // $ANTLR start "rule__RangeJob__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1634:1: rule__RangeJob__Group__0__Impl : ( 'rangejob' ) ;
    public final void rule__RangeJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1638:1: ( ( 'rangejob' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1639:1: ( 'rangejob' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1639:1: ( 'rangejob' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1640:1: 'rangejob'
            {
             before(grammarAccess.getRangeJobAccess().getRangejobKeyword_0()); 
            match(input,20,FOLLOW_20_in_rule__RangeJob__Group__0__Impl3363); 
             after(grammarAccess.getRangeJobAccess().getRangejobKeyword_0()); 

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
    // $ANTLR end "rule__RangeJob__Group__0__Impl"


    // $ANTLR start "rule__RangeJob__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1653:1: rule__RangeJob__Group__1 : rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 ;
    public final void rule__RangeJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1657:1: ( rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1658:2: rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__1__Impl_in_rule__RangeJob__Group__13394);
            rule__RangeJob__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__2_in_rule__RangeJob__Group__13397);
            rule__RangeJob__Group__2();

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
    // $ANTLR end "rule__RangeJob__Group__1"


    // $ANTLR start "rule__RangeJob__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1665:1: rule__RangeJob__Group__1__Impl : ( ( rule__RangeJob__NameAssignment_1 ) ) ;
    public final void rule__RangeJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1669:1: ( ( ( rule__RangeJob__NameAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1670:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1670:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1671:1: ( rule__RangeJob__NameAssignment_1 )
            {
             before(grammarAccess.getRangeJobAccess().getNameAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1672:1: ( rule__RangeJob__NameAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1672:2: rule__RangeJob__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__RangeJob__NameAssignment_1_in_rule__RangeJob__Group__1__Impl3424);
            rule__RangeJob__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__RangeJob__Group__1__Impl"


    // $ANTLR start "rule__RangeJob__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1682:1: rule__RangeJob__Group__2 : rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 ;
    public final void rule__RangeJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1686:1: ( rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1687:2: rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__2__Impl_in_rule__RangeJob__Group__23454);
            rule__RangeJob__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__3_in_rule__RangeJob__Group__23457);
            rule__RangeJob__Group__3();

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
    // $ANTLR end "rule__RangeJob__Group__2"


    // $ANTLR start "rule__RangeJob__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1694:1: rule__RangeJob__Group__2__Impl : ( 'resources' ) ;
    public final void rule__RangeJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1698:1: ( ( 'resources' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1699:1: ( 'resources' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1699:1: ( 'resources' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1700:1: 'resources'
            {
             before(grammarAccess.getRangeJobAccess().getResourcesKeyword_2()); 
            match(input,16,FOLLOW_16_in_rule__RangeJob__Group__2__Impl3485); 
             after(grammarAccess.getRangeJobAccess().getResourcesKeyword_2()); 

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
    // $ANTLR end "rule__RangeJob__Group__2__Impl"


    // $ANTLR start "rule__RangeJob__Group__3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1713:1: rule__RangeJob__Group__3 : rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 ;
    public final void rule__RangeJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1717:1: ( rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1718:2: rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__3__Impl_in_rule__RangeJob__Group__33516);
            rule__RangeJob__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__4_in_rule__RangeJob__Group__33519);
            rule__RangeJob__Group__4();

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
    // $ANTLR end "rule__RangeJob__Group__3"


    // $ANTLR start "rule__RangeJob__Group__3__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1725:1: rule__RangeJob__Group__3__Impl : ( ( ( rule__RangeJob__ResourcesAssignment_3 ) ) ( ( rule__RangeJob__ResourcesAssignment_3 )* ) ) ;
    public final void rule__RangeJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1729:1: ( ( ( ( rule__RangeJob__ResourcesAssignment_3 ) ) ( ( rule__RangeJob__ResourcesAssignment_3 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1730:1: ( ( ( rule__RangeJob__ResourcesAssignment_3 ) ) ( ( rule__RangeJob__ResourcesAssignment_3 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1730:1: ( ( ( rule__RangeJob__ResourcesAssignment_3 ) ) ( ( rule__RangeJob__ResourcesAssignment_3 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1731:1: ( ( rule__RangeJob__ResourcesAssignment_3 ) ) ( ( rule__RangeJob__ResourcesAssignment_3 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1731:1: ( ( rule__RangeJob__ResourcesAssignment_3 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1732:1: ( rule__RangeJob__ResourcesAssignment_3 )
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1733:1: ( rule__RangeJob__ResourcesAssignment_3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1733:2: rule__RangeJob__ResourcesAssignment_3
            {
            pushFollow(FOLLOW_rule__RangeJob__ResourcesAssignment_3_in_rule__RangeJob__Group__3__Impl3548);
            rule__RangeJob__ResourcesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getResourcesAssignment_3()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1736:1: ( ( rule__RangeJob__ResourcesAssignment_3 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1737:1: ( rule__RangeJob__ResourcesAssignment_3 )*
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1738:1: ( rule__RangeJob__ResourcesAssignment_3 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_STRING||LA20_0==29) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1738:2: rule__RangeJob__ResourcesAssignment_3
            	    {
            	    pushFollow(FOLLOW_rule__RangeJob__ResourcesAssignment_3_in_rule__RangeJob__Group__3__Impl3560);
            	    rule__RangeJob__ResourcesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getRangeJobAccess().getResourcesAssignment_3()); 

            }


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
    // $ANTLR end "rule__RangeJob__Group__3__Impl"


    // $ANTLR start "rule__RangeJob__Group__4"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1749:1: rule__RangeJob__Group__4 : rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 ;
    public final void rule__RangeJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1753:1: ( rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1754:2: rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__4__Impl_in_rule__RangeJob__Group__43593);
            rule__RangeJob__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__5_in_rule__RangeJob__Group__43596);
            rule__RangeJob__Group__5();

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
    // $ANTLR end "rule__RangeJob__Group__4"


    // $ANTLR start "rule__RangeJob__Group__4__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1761:1: rule__RangeJob__Group__4__Impl : ( 'layoutoptions' ) ;
    public final void rule__RangeJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1765:1: ( ( 'layoutoptions' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1766:1: ( 'layoutoptions' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1766:1: ( 'layoutoptions' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1767:1: 'layoutoptions'
            {
             before(grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_4()); 
            match(input,17,FOLLOW_17_in_rule__RangeJob__Group__4__Impl3624); 
             after(grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_4()); 

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
    // $ANTLR end "rule__RangeJob__Group__4__Impl"


    // $ANTLR start "rule__RangeJob__Group__5"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1780:1: rule__RangeJob__Group__5 : rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 ;
    public final void rule__RangeJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1784:1: ( rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1785:2: rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__5__Impl_in_rule__RangeJob__Group__53655);
            rule__RangeJob__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__6_in_rule__RangeJob__Group__53658);
            rule__RangeJob__Group__6();

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
    // $ANTLR end "rule__RangeJob__Group__5"


    // $ANTLR start "rule__RangeJob__Group__5__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1792:1: rule__RangeJob__Group__5__Impl : ( ( ( rule__RangeJob__LayoutOptionsAssignment_5 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_5 )* ) ) ;
    public final void rule__RangeJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1796:1: ( ( ( ( rule__RangeJob__LayoutOptionsAssignment_5 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_5 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1797:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_5 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_5 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1797:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_5 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_5 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1798:1: ( ( rule__RangeJob__LayoutOptionsAssignment_5 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_5 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1798:1: ( ( rule__RangeJob__LayoutOptionsAssignment_5 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1799:1: ( rule__RangeJob__LayoutOptionsAssignment_5 )
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_5()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1800:1: ( rule__RangeJob__LayoutOptionsAssignment_5 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1800:2: rule__RangeJob__LayoutOptionsAssignment_5
            {
            pushFollow(FOLLOW_rule__RangeJob__LayoutOptionsAssignment_5_in_rule__RangeJob__Group__5__Impl3687);
            rule__RangeJob__LayoutOptionsAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_5()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1803:1: ( ( rule__RangeJob__LayoutOptionsAssignment_5 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1804:1: ( rule__RangeJob__LayoutOptionsAssignment_5 )*
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_5()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1805:1: ( rule__RangeJob__LayoutOptionsAssignment_5 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1805:2: rule__RangeJob__LayoutOptionsAssignment_5
            	    {
            	    pushFollow(FOLLOW_rule__RangeJob__LayoutOptionsAssignment_5_in_rule__RangeJob__Group__5__Impl3699);
            	    rule__RangeJob__LayoutOptionsAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_5()); 

            }


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
    // $ANTLR end "rule__RangeJob__Group__5__Impl"


    // $ANTLR start "rule__RangeJob__Group__6"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1816:1: rule__RangeJob__Group__6 : rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 ;
    public final void rule__RangeJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1820:1: ( rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1821:2: rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__6__Impl_in_rule__RangeJob__Group__63732);
            rule__RangeJob__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__7_in_rule__RangeJob__Group__63735);
            rule__RangeJob__Group__7();

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
    // $ANTLR end "rule__RangeJob__Group__6"


    // $ANTLR start "rule__RangeJob__Group__6__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1828:1: rule__RangeJob__Group__6__Impl : ( 'analyses' ) ;
    public final void rule__RangeJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1832:1: ( ( 'analyses' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1833:1: ( 'analyses' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1833:1: ( 'analyses' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1834:1: 'analyses'
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesKeyword_6()); 
            match(input,18,FOLLOW_18_in_rule__RangeJob__Group__6__Impl3763); 
             after(grammarAccess.getRangeJobAccess().getAnalysesKeyword_6()); 

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
    // $ANTLR end "rule__RangeJob__Group__6__Impl"


    // $ANTLR start "rule__RangeJob__Group__7"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1847:1: rule__RangeJob__Group__7 : rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 ;
    public final void rule__RangeJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1851:1: ( rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1852:2: rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__7__Impl_in_rule__RangeJob__Group__73794);
            rule__RangeJob__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__8_in_rule__RangeJob__Group__73797);
            rule__RangeJob__Group__8();

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
    // $ANTLR end "rule__RangeJob__Group__7"


    // $ANTLR start "rule__RangeJob__Group__7__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1859:1: rule__RangeJob__Group__7__Impl : ( ( ( rule__RangeJob__AnalysesAssignment_7 ) ) ( ( rule__RangeJob__AnalysesAssignment_7 )* ) ) ;
    public final void rule__RangeJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1863:1: ( ( ( ( rule__RangeJob__AnalysesAssignment_7 ) ) ( ( rule__RangeJob__AnalysesAssignment_7 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1864:1: ( ( ( rule__RangeJob__AnalysesAssignment_7 ) ) ( ( rule__RangeJob__AnalysesAssignment_7 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1864:1: ( ( ( rule__RangeJob__AnalysesAssignment_7 ) ) ( ( rule__RangeJob__AnalysesAssignment_7 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1865:1: ( ( rule__RangeJob__AnalysesAssignment_7 ) ) ( ( rule__RangeJob__AnalysesAssignment_7 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1865:1: ( ( rule__RangeJob__AnalysesAssignment_7 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1866:1: ( rule__RangeJob__AnalysesAssignment_7 )
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_7()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1867:1: ( rule__RangeJob__AnalysesAssignment_7 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1867:2: rule__RangeJob__AnalysesAssignment_7
            {
            pushFollow(FOLLOW_rule__RangeJob__AnalysesAssignment_7_in_rule__RangeJob__Group__7__Impl3826);
            rule__RangeJob__AnalysesAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getAnalysesAssignment_7()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1870:1: ( ( rule__RangeJob__AnalysesAssignment_7 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1871:1: ( rule__RangeJob__AnalysesAssignment_7 )*
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_7()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1872:1: ( rule__RangeJob__AnalysesAssignment_7 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==RULE_ID) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1872:2: rule__RangeJob__AnalysesAssignment_7
            	    {
            	    pushFollow(FOLLOW_rule__RangeJob__AnalysesAssignment_7_in_rule__RangeJob__Group__7__Impl3838);
            	    rule__RangeJob__AnalysesAssignment_7();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getRangeJobAccess().getAnalysesAssignment_7()); 

            }


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
    // $ANTLR end "rule__RangeJob__Group__7__Impl"


    // $ANTLR start "rule__RangeJob__Group__8"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1883:1: rule__RangeJob__Group__8 : rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 ;
    public final void rule__RangeJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1887:1: ( rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1888:2: rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__8__Impl_in_rule__RangeJob__Group__83871);
            rule__RangeJob__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__9_in_rule__RangeJob__Group__83874);
            rule__RangeJob__Group__9();

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
    // $ANTLR end "rule__RangeJob__Group__8"


    // $ANTLR start "rule__RangeJob__Group__8__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1895:1: rule__RangeJob__Group__8__Impl : ( 'rangeoption' ) ;
    public final void rule__RangeJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1899:1: ( ( 'rangeoption' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1900:1: ( 'rangeoption' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1900:1: ( 'rangeoption' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1901:1: 'rangeoption'
            {
             before(grammarAccess.getRangeJobAccess().getRangeoptionKeyword_8()); 
            match(input,21,FOLLOW_21_in_rule__RangeJob__Group__8__Impl3902); 
             after(grammarAccess.getRangeJobAccess().getRangeoptionKeyword_8()); 

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
    // $ANTLR end "rule__RangeJob__Group__8__Impl"


    // $ANTLR start "rule__RangeJob__Group__9"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1914:1: rule__RangeJob__Group__9 : rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 ;
    public final void rule__RangeJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1918:1: ( rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1919:2: rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__9__Impl_in_rule__RangeJob__Group__93933);
            rule__RangeJob__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__10_in_rule__RangeJob__Group__93936);
            rule__RangeJob__Group__10();

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
    // $ANTLR end "rule__RangeJob__Group__9"


    // $ANTLR start "rule__RangeJob__Group__9__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1926:1: rule__RangeJob__Group__9__Impl : ( ( rule__RangeJob__RangeOptionAssignment_9 ) ) ;
    public final void rule__RangeJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1930:1: ( ( ( rule__RangeJob__RangeOptionAssignment_9 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1931:1: ( ( rule__RangeJob__RangeOptionAssignment_9 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1931:1: ( ( rule__RangeJob__RangeOptionAssignment_9 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1932:1: ( rule__RangeJob__RangeOptionAssignment_9 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_9()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1933:1: ( rule__RangeJob__RangeOptionAssignment_9 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1933:2: rule__RangeJob__RangeOptionAssignment_9
            {
            pushFollow(FOLLOW_rule__RangeJob__RangeOptionAssignment_9_in_rule__RangeJob__Group__9__Impl3963);
            rule__RangeJob__RangeOptionAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_9()); 

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
    // $ANTLR end "rule__RangeJob__Group__9__Impl"


    // $ANTLR start "rule__RangeJob__Group__10"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1943:1: rule__RangeJob__Group__10 : rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 ;
    public final void rule__RangeJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1947:1: ( rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1948:2: rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__10__Impl_in_rule__RangeJob__Group__103993);
            rule__RangeJob__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__11_in_rule__RangeJob__Group__103996);
            rule__RangeJob__Group__11();

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
    // $ANTLR end "rule__RangeJob__Group__10"


    // $ANTLR start "rule__RangeJob__Group__10__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1955:1: rule__RangeJob__Group__10__Impl : ( ( rule__RangeJob__RangeValuesAssignment_10 ) ) ;
    public final void rule__RangeJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1959:1: ( ( ( rule__RangeJob__RangeValuesAssignment_10 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1960:1: ( ( rule__RangeJob__RangeValuesAssignment_10 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1960:1: ( ( rule__RangeJob__RangeValuesAssignment_10 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1961:1: ( rule__RangeJob__RangeValuesAssignment_10 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_10()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1962:1: ( rule__RangeJob__RangeValuesAssignment_10 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1962:2: rule__RangeJob__RangeValuesAssignment_10
            {
            pushFollow(FOLLOW_rule__RangeJob__RangeValuesAssignment_10_in_rule__RangeJob__Group__10__Impl4023);
            rule__RangeJob__RangeValuesAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_10()); 

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
    // $ANTLR end "rule__RangeJob__Group__10__Impl"


    // $ANTLR start "rule__RangeJob__Group__11"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1972:1: rule__RangeJob__Group__11 : rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 ;
    public final void rule__RangeJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1976:1: ( rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1977:2: rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__11__Impl_in_rule__RangeJob__Group__114053);
            rule__RangeJob__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__12_in_rule__RangeJob__Group__114056);
            rule__RangeJob__Group__12();

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
    // $ANTLR end "rule__RangeJob__Group__11"


    // $ANTLR start "rule__RangeJob__Group__11__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1984:1: rule__RangeJob__Group__11__Impl : ( 'rangeanalysis' ) ;
    public final void rule__RangeJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1988:1: ( ( 'rangeanalysis' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1989:1: ( 'rangeanalysis' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1989:1: ( 'rangeanalysis' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:1990:1: 'rangeanalysis'
            {
             before(grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_11()); 
            match(input,22,FOLLOW_22_in_rule__RangeJob__Group__11__Impl4084); 
             after(grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_11()); 

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
    // $ANTLR end "rule__RangeJob__Group__11__Impl"


    // $ANTLR start "rule__RangeJob__Group__12"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2003:1: rule__RangeJob__Group__12 : rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 ;
    public final void rule__RangeJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2007:1: ( rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2008:2: rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__12__Impl_in_rule__RangeJob__Group__124115);
            rule__RangeJob__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__13_in_rule__RangeJob__Group__124118);
            rule__RangeJob__Group__13();

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
    // $ANTLR end "rule__RangeJob__Group__12"


    // $ANTLR start "rule__RangeJob__Group__12__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2015:1: rule__RangeJob__Group__12__Impl : ( ( rule__RangeJob__RangeAnalysisAssignment_12 ) ) ;
    public final void rule__RangeJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2019:1: ( ( ( rule__RangeJob__RangeAnalysisAssignment_12 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2020:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2020:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2021:1: ( rule__RangeJob__RangeAnalysisAssignment_12 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2022:1: ( rule__RangeJob__RangeAnalysisAssignment_12 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2022:2: rule__RangeJob__RangeAnalysisAssignment_12
            {
            pushFollow(FOLLOW_rule__RangeJob__RangeAnalysisAssignment_12_in_rule__RangeJob__Group__12__Impl4145);
            rule__RangeJob__RangeAnalysisAssignment_12();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12()); 

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
    // $ANTLR end "rule__RangeJob__Group__12__Impl"


    // $ANTLR start "rule__RangeJob__Group__13"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2032:1: rule__RangeJob__Group__13 : rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 ;
    public final void rule__RangeJob__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2036:1: ( rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2037:2: rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__13__Impl_in_rule__RangeJob__Group__134175);
            rule__RangeJob__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__14_in_rule__RangeJob__Group__134178);
            rule__RangeJob__Group__14();

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
    // $ANTLR end "rule__RangeJob__Group__13"


    // $ANTLR start "rule__RangeJob__Group__13__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2044:1: rule__RangeJob__Group__13__Impl : ( ( rule__RangeJob__Group_13__0 )? ) ;
    public final void rule__RangeJob__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2048:1: ( ( ( rule__RangeJob__Group_13__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2049:1: ( ( rule__RangeJob__Group_13__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2049:1: ( ( rule__RangeJob__Group_13__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2050:1: ( rule__RangeJob__Group_13__0 )?
            {
             before(grammarAccess.getRangeJobAccess().getGroup_13()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2051:1: ( rule__RangeJob__Group_13__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==23) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2051:2: rule__RangeJob__Group_13__0
                    {
                    pushFollow(FOLLOW_rule__RangeJob__Group_13__0_in_rule__RangeJob__Group__13__Impl4205);
                    rule__RangeJob__Group_13__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRangeJobAccess().getGroup_13()); 

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
    // $ANTLR end "rule__RangeJob__Group__13__Impl"


    // $ANTLR start "rule__RangeJob__Group__14"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2061:1: rule__RangeJob__Group__14 : rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 ;
    public final void rule__RangeJob__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2065:1: ( rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2066:2: rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__14__Impl_in_rule__RangeJob__Group__144236);
            rule__RangeJob__Group__14__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group__15_in_rule__RangeJob__Group__144239);
            rule__RangeJob__Group__15();

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
    // $ANTLR end "rule__RangeJob__Group__14"


    // $ANTLR start "rule__RangeJob__Group__14__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2073:1: rule__RangeJob__Group__14__Impl : ( 'output' ) ;
    public final void rule__RangeJob__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2077:1: ( ( 'output' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2078:1: ( 'output' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2078:1: ( 'output' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2079:1: 'output'
            {
             before(grammarAccess.getRangeJobAccess().getOutputKeyword_14()); 
            match(input,19,FOLLOW_19_in_rule__RangeJob__Group__14__Impl4267); 
             after(grammarAccess.getRangeJobAccess().getOutputKeyword_14()); 

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
    // $ANTLR end "rule__RangeJob__Group__14__Impl"


    // $ANTLR start "rule__RangeJob__Group__15"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2092:1: rule__RangeJob__Group__15 : rule__RangeJob__Group__15__Impl ;
    public final void rule__RangeJob__Group__15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2096:1: ( rule__RangeJob__Group__15__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2097:2: rule__RangeJob__Group__15__Impl
            {
            pushFollow(FOLLOW_rule__RangeJob__Group__15__Impl_in_rule__RangeJob__Group__154298);
            rule__RangeJob__Group__15__Impl();

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
    // $ANTLR end "rule__RangeJob__Group__15"


    // $ANTLR start "rule__RangeJob__Group__15__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2103:1: rule__RangeJob__Group__15__Impl : ( ( rule__RangeJob__OutputAssignment_15 ) ) ;
    public final void rule__RangeJob__Group__15__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2107:1: ( ( ( rule__RangeJob__OutputAssignment_15 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2108:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2108:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2109:1: ( rule__RangeJob__OutputAssignment_15 )
            {
             before(grammarAccess.getRangeJobAccess().getOutputAssignment_15()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2110:1: ( rule__RangeJob__OutputAssignment_15 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2110:2: rule__RangeJob__OutputAssignment_15
            {
            pushFollow(FOLLOW_rule__RangeJob__OutputAssignment_15_in_rule__RangeJob__Group__15__Impl4325);
            rule__RangeJob__OutputAssignment_15();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getOutputAssignment_15()); 

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
    // $ANTLR end "rule__RangeJob__Group__15__Impl"


    // $ANTLR start "rule__RangeJob__Group_13__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2152:1: rule__RangeJob__Group_13__0 : rule__RangeJob__Group_13__0__Impl rule__RangeJob__Group_13__1 ;
    public final void rule__RangeJob__Group_13__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2156:1: ( rule__RangeJob__Group_13__0__Impl rule__RangeJob__Group_13__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2157:2: rule__RangeJob__Group_13__0__Impl rule__RangeJob__Group_13__1
            {
            pushFollow(FOLLOW_rule__RangeJob__Group_13__0__Impl_in_rule__RangeJob__Group_13__04387);
            rule__RangeJob__Group_13__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__RangeJob__Group_13__1_in_rule__RangeJob__Group_13__04390);
            rule__RangeJob__Group_13__1();

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
    // $ANTLR end "rule__RangeJob__Group_13__0"


    // $ANTLR start "rule__RangeJob__Group_13__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2164:1: rule__RangeJob__Group_13__0__Impl : ( 'component' ) ;
    public final void rule__RangeJob__Group_13__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2168:1: ( ( 'component' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2169:1: ( 'component' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2169:1: ( 'component' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2170:1: 'component'
            {
             before(grammarAccess.getRangeJobAccess().getComponentKeyword_13_0()); 
            match(input,23,FOLLOW_23_in_rule__RangeJob__Group_13__0__Impl4418); 
             after(grammarAccess.getRangeJobAccess().getComponentKeyword_13_0()); 

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
    // $ANTLR end "rule__RangeJob__Group_13__0__Impl"


    // $ANTLR start "rule__RangeJob__Group_13__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2183:1: rule__RangeJob__Group_13__1 : rule__RangeJob__Group_13__1__Impl ;
    public final void rule__RangeJob__Group_13__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2187:1: ( rule__RangeJob__Group_13__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2188:2: rule__RangeJob__Group_13__1__Impl
            {
            pushFollow(FOLLOW_rule__RangeJob__Group_13__1__Impl_in_rule__RangeJob__Group_13__14449);
            rule__RangeJob__Group_13__1__Impl();

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
    // $ANTLR end "rule__RangeJob__Group_13__1"


    // $ANTLR start "rule__RangeJob__Group_13__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2194:1: rule__RangeJob__Group_13__1__Impl : ( ( rule__RangeJob__RangeAnalysisComponentAssignment_13_1 ) ) ;
    public final void rule__RangeJob__Group_13__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2198:1: ( ( ( rule__RangeJob__RangeAnalysisComponentAssignment_13_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2199:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_13_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2199:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_13_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2200:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_13_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_13_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2201:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_13_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2201:2: rule__RangeJob__RangeAnalysisComponentAssignment_13_1
            {
            pushFollow(FOLLOW_rule__RangeJob__RangeAnalysisComponentAssignment_13_1_in_rule__RangeJob__Group_13__1__Impl4476);
            rule__RangeJob__RangeAnalysisComponentAssignment_13_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_13_1()); 

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
    // $ANTLR end "rule__RangeJob__Group_13__1__Impl"


    // $ANTLR start "rule__FloatRange__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2215:1: rule__FloatRange__Group__0 : rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 ;
    public final void rule__FloatRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2219:1: ( rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2220:2: rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1
            {
            pushFollow(FOLLOW_rule__FloatRange__Group__0__Impl_in_rule__FloatRange__Group__04510);
            rule__FloatRange__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FloatRange__Group__1_in_rule__FloatRange__Group__04513);
            rule__FloatRange__Group__1();

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
    // $ANTLR end "rule__FloatRange__Group__0"


    // $ANTLR start "rule__FloatRange__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2227:1: rule__FloatRange__Group__0__Impl : ( 'floatvalues' ) ;
    public final void rule__FloatRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2231:1: ( ( 'floatvalues' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2232:1: ( 'floatvalues' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2232:1: ( 'floatvalues' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2233:1: 'floatvalues'
            {
             before(grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0()); 
            match(input,24,FOLLOW_24_in_rule__FloatRange__Group__0__Impl4541); 
             after(grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0()); 

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
    // $ANTLR end "rule__FloatRange__Group__0__Impl"


    // $ANTLR start "rule__FloatRange__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2246:1: rule__FloatRange__Group__1 : rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 ;
    public final void rule__FloatRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2250:1: ( rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2251:2: rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2
            {
            pushFollow(FOLLOW_rule__FloatRange__Group__1__Impl_in_rule__FloatRange__Group__14572);
            rule__FloatRange__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FloatRange__Group__2_in_rule__FloatRange__Group__14575);
            rule__FloatRange__Group__2();

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
    // $ANTLR end "rule__FloatRange__Group__1"


    // $ANTLR start "rule__FloatRange__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2258:1: rule__FloatRange__Group__1__Impl : ( ( rule__FloatRange__ValuesAssignment_1 ) ) ;
    public final void rule__FloatRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2262:1: ( ( ( rule__FloatRange__ValuesAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2263:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2263:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2264:1: ( rule__FloatRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2265:1: ( rule__FloatRange__ValuesAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2265:2: rule__FloatRange__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__FloatRange__ValuesAssignment_1_in_rule__FloatRange__Group__1__Impl4602);
            rule__FloatRange__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFloatRangeAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__FloatRange__Group__1__Impl"


    // $ANTLR start "rule__FloatRange__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2275:1: rule__FloatRange__Group__2 : rule__FloatRange__Group__2__Impl ;
    public final void rule__FloatRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2279:1: ( rule__FloatRange__Group__2__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2280:2: rule__FloatRange__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__FloatRange__Group__2__Impl_in_rule__FloatRange__Group__24632);
            rule__FloatRange__Group__2__Impl();

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
    // $ANTLR end "rule__FloatRange__Group__2"


    // $ANTLR start "rule__FloatRange__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2286:1: rule__FloatRange__Group__2__Impl : ( ( rule__FloatRange__Group_2__0 )* ) ;
    public final void rule__FloatRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2290:1: ( ( ( rule__FloatRange__Group_2__0 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2291:1: ( ( rule__FloatRange__Group_2__0 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2291:1: ( ( rule__FloatRange__Group_2__0 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2292:1: ( rule__FloatRange__Group_2__0 )*
            {
             before(grammarAccess.getFloatRangeAccess().getGroup_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2293:1: ( rule__FloatRange__Group_2__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==25) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2293:2: rule__FloatRange__Group_2__0
            	    {
            	    pushFollow(FOLLOW_rule__FloatRange__Group_2__0_in_rule__FloatRange__Group__2__Impl4659);
            	    rule__FloatRange__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getFloatRangeAccess().getGroup_2()); 

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
    // $ANTLR end "rule__FloatRange__Group__2__Impl"


    // $ANTLR start "rule__FloatRange__Group_2__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2309:1: rule__FloatRange__Group_2__0 : rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 ;
    public final void rule__FloatRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2313:1: ( rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2314:2: rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1
            {
            pushFollow(FOLLOW_rule__FloatRange__Group_2__0__Impl_in_rule__FloatRange__Group_2__04696);
            rule__FloatRange__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FloatRange__Group_2__1_in_rule__FloatRange__Group_2__04699);
            rule__FloatRange__Group_2__1();

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
    // $ANTLR end "rule__FloatRange__Group_2__0"


    // $ANTLR start "rule__FloatRange__Group_2__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2321:1: rule__FloatRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__FloatRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2325:1: ( ( ',' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2326:1: ( ',' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2326:1: ( ',' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2327:1: ','
            {
             before(grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0()); 
            match(input,25,FOLLOW_25_in_rule__FloatRange__Group_2__0__Impl4727); 
             after(grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__FloatRange__Group_2__0__Impl"


    // $ANTLR start "rule__FloatRange__Group_2__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2340:1: rule__FloatRange__Group_2__1 : rule__FloatRange__Group_2__1__Impl ;
    public final void rule__FloatRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2344:1: ( rule__FloatRange__Group_2__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2345:2: rule__FloatRange__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__FloatRange__Group_2__1__Impl_in_rule__FloatRange__Group_2__14758);
            rule__FloatRange__Group_2__1__Impl();

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
    // $ANTLR end "rule__FloatRange__Group_2__1"


    // $ANTLR start "rule__FloatRange__Group_2__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2351:1: rule__FloatRange__Group_2__1__Impl : ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__FloatRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2355:1: ( ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2356:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2356:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2357:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_2_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2358:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2358:2: rule__FloatRange__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__FloatRange__ValuesAssignment_2_1_in_rule__FloatRange__Group_2__1__Impl4785);
            rule__FloatRange__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getFloatRangeAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__FloatRange__Group_2__1__Impl"


    // $ANTLR start "rule__IntRangeValues__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2372:1: rule__IntRangeValues__Group__0 : rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 ;
    public final void rule__IntRangeValues__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2376:1: ( rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2377:2: rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1
            {
            pushFollow(FOLLOW_rule__IntRangeValues__Group__0__Impl_in_rule__IntRangeValues__Group__04819);
            rule__IntRangeValues__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IntRangeValues__Group__1_in_rule__IntRangeValues__Group__04822);
            rule__IntRangeValues__Group__1();

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
    // $ANTLR end "rule__IntRangeValues__Group__0"


    // $ANTLR start "rule__IntRangeValues__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2384:1: rule__IntRangeValues__Group__0__Impl : ( 'intvalues' ) ;
    public final void rule__IntRangeValues__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2388:1: ( ( 'intvalues' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2389:1: ( 'intvalues' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2389:1: ( 'intvalues' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2390:1: 'intvalues'
            {
             before(grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0()); 
            match(input,26,FOLLOW_26_in_rule__IntRangeValues__Group__0__Impl4850); 
             after(grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0()); 

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
    // $ANTLR end "rule__IntRangeValues__Group__0__Impl"


    // $ANTLR start "rule__IntRangeValues__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2403:1: rule__IntRangeValues__Group__1 : rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 ;
    public final void rule__IntRangeValues__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2407:1: ( rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2408:2: rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2
            {
            pushFollow(FOLLOW_rule__IntRangeValues__Group__1__Impl_in_rule__IntRangeValues__Group__14881);
            rule__IntRangeValues__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IntRangeValues__Group__2_in_rule__IntRangeValues__Group__14884);
            rule__IntRangeValues__Group__2();

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
    // $ANTLR end "rule__IntRangeValues__Group__1"


    // $ANTLR start "rule__IntRangeValues__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2415:1: rule__IntRangeValues__Group__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) ;
    public final void rule__IntRangeValues__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2419:1: ( ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2420:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2420:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2421:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2422:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2422:2: rule__IntRangeValues__ValuesAssignment_1
            {
            pushFollow(FOLLOW_rule__IntRangeValues__ValuesAssignment_1_in_rule__IntRangeValues__Group__1__Impl4911);
            rule__IntRangeValues__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__IntRangeValues__Group__1__Impl"


    // $ANTLR start "rule__IntRangeValues__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2432:1: rule__IntRangeValues__Group__2 : rule__IntRangeValues__Group__2__Impl ;
    public final void rule__IntRangeValues__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2436:1: ( rule__IntRangeValues__Group__2__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2437:2: rule__IntRangeValues__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__IntRangeValues__Group__2__Impl_in_rule__IntRangeValues__Group__24941);
            rule__IntRangeValues__Group__2__Impl();

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
    // $ANTLR end "rule__IntRangeValues__Group__2"


    // $ANTLR start "rule__IntRangeValues__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2443:1: rule__IntRangeValues__Group__2__Impl : ( ( rule__IntRangeValues__Group_2__0 ) ) ;
    public final void rule__IntRangeValues__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2447:1: ( ( ( rule__IntRangeValues__Group_2__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2448:1: ( ( rule__IntRangeValues__Group_2__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2448:1: ( ( rule__IntRangeValues__Group_2__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2449:1: ( rule__IntRangeValues__Group_2__0 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2450:1: ( rule__IntRangeValues__Group_2__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2450:2: rule__IntRangeValues__Group_2__0
            {
            pushFollow(FOLLOW_rule__IntRangeValues__Group_2__0_in_rule__IntRangeValues__Group__2__Impl4968);
            rule__IntRangeValues__Group_2__0();

            state._fsp--;


            }

             after(grammarAccess.getIntRangeValuesAccess().getGroup_2()); 

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
    // $ANTLR end "rule__IntRangeValues__Group__2__Impl"


    // $ANTLR start "rule__IntRangeValues__Group_2__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2466:1: rule__IntRangeValues__Group_2__0 : rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 ;
    public final void rule__IntRangeValues__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2470:1: ( rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2471:2: rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1
            {
            pushFollow(FOLLOW_rule__IntRangeValues__Group_2__0__Impl_in_rule__IntRangeValues__Group_2__05004);
            rule__IntRangeValues__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IntRangeValues__Group_2__1_in_rule__IntRangeValues__Group_2__05007);
            rule__IntRangeValues__Group_2__1();

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
    // $ANTLR end "rule__IntRangeValues__Group_2__0"


    // $ANTLR start "rule__IntRangeValues__Group_2__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2478:1: rule__IntRangeValues__Group_2__0__Impl : ( ',' ) ;
    public final void rule__IntRangeValues__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2482:1: ( ( ',' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2483:1: ( ',' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2483:1: ( ',' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2484:1: ','
            {
             before(grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0()); 
            match(input,25,FOLLOW_25_in_rule__IntRangeValues__Group_2__0__Impl5035); 
             after(grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__IntRangeValues__Group_2__0__Impl"


    // $ANTLR start "rule__IntRangeValues__Group_2__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2497:1: rule__IntRangeValues__Group_2__1 : rule__IntRangeValues__Group_2__1__Impl ;
    public final void rule__IntRangeValues__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2501:1: ( rule__IntRangeValues__Group_2__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2502:2: rule__IntRangeValues__Group_2__1__Impl
            {
            pushFollow(FOLLOW_rule__IntRangeValues__Group_2__1__Impl_in_rule__IntRangeValues__Group_2__15066);
            rule__IntRangeValues__Group_2__1__Impl();

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
    // $ANTLR end "rule__IntRangeValues__Group_2__1"


    // $ANTLR start "rule__IntRangeValues__Group_2__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2508:1: rule__IntRangeValues__Group_2__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) ;
    public final void rule__IntRangeValues__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2512:1: ( ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2513:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2513:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2514:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_2_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2515:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2515:2: rule__IntRangeValues__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_rule__IntRangeValues__ValuesAssignment_2_1_in_rule__IntRangeValues__Group_2__1__Impl5093);
            rule__IntRangeValues__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__IntRangeValues__Group_2__1__Impl"


    // $ANTLR start "rule__IntRangeRange__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2529:1: rule__IntRangeRange__Group__0 : rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 ;
    public final void rule__IntRangeRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2533:1: ( rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2534:2: rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1
            {
            pushFollow(FOLLOW_rule__IntRangeRange__Group__0__Impl_in_rule__IntRangeRange__Group__05127);
            rule__IntRangeRange__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IntRangeRange__Group__1_in_rule__IntRangeRange__Group__05130);
            rule__IntRangeRange__Group__1();

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
    // $ANTLR end "rule__IntRangeRange__Group__0"


    // $ANTLR start "rule__IntRangeRange__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2541:1: rule__IntRangeRange__Group__0__Impl : ( 'intrange' ) ;
    public final void rule__IntRangeRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2545:1: ( ( 'intrange' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2546:1: ( 'intrange' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2546:1: ( 'intrange' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2547:1: 'intrange'
            {
             before(grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0()); 
            match(input,27,FOLLOW_27_in_rule__IntRangeRange__Group__0__Impl5158); 
             after(grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0()); 

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
    // $ANTLR end "rule__IntRangeRange__Group__0__Impl"


    // $ANTLR start "rule__IntRangeRange__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2560:1: rule__IntRangeRange__Group__1 : rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 ;
    public final void rule__IntRangeRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2564:1: ( rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2565:2: rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2
            {
            pushFollow(FOLLOW_rule__IntRangeRange__Group__1__Impl_in_rule__IntRangeRange__Group__15189);
            rule__IntRangeRange__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IntRangeRange__Group__2_in_rule__IntRangeRange__Group__15192);
            rule__IntRangeRange__Group__2();

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
    // $ANTLR end "rule__IntRangeRange__Group__1"


    // $ANTLR start "rule__IntRangeRange__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2572:1: rule__IntRangeRange__Group__1__Impl : ( ( rule__IntRangeRange__StartAssignment_1 ) ) ;
    public final void rule__IntRangeRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2576:1: ( ( ( rule__IntRangeRange__StartAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2577:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2577:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2578:1: ( rule__IntRangeRange__StartAssignment_1 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2579:1: ( rule__IntRangeRange__StartAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2579:2: rule__IntRangeRange__StartAssignment_1
            {
            pushFollow(FOLLOW_rule__IntRangeRange__StartAssignment_1_in_rule__IntRangeRange__Group__1__Impl5219);
            rule__IntRangeRange__StartAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getIntRangeRangeAccess().getStartAssignment_1()); 

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
    // $ANTLR end "rule__IntRangeRange__Group__1__Impl"


    // $ANTLR start "rule__IntRangeRange__Group__2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2589:1: rule__IntRangeRange__Group__2 : rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 ;
    public final void rule__IntRangeRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2593:1: ( rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2594:2: rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3
            {
            pushFollow(FOLLOW_rule__IntRangeRange__Group__2__Impl_in_rule__IntRangeRange__Group__25249);
            rule__IntRangeRange__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__IntRangeRange__Group__3_in_rule__IntRangeRange__Group__25252);
            rule__IntRangeRange__Group__3();

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
    // $ANTLR end "rule__IntRangeRange__Group__2"


    // $ANTLR start "rule__IntRangeRange__Group__2__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2601:1: rule__IntRangeRange__Group__2__Impl : ( 'to' ) ;
    public final void rule__IntRangeRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2605:1: ( ( 'to' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2606:1: ( 'to' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2606:1: ( 'to' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2607:1: 'to'
            {
             before(grammarAccess.getIntRangeRangeAccess().getToKeyword_2()); 
            match(input,28,FOLLOW_28_in_rule__IntRangeRange__Group__2__Impl5280); 
             after(grammarAccess.getIntRangeRangeAccess().getToKeyword_2()); 

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
    // $ANTLR end "rule__IntRangeRange__Group__2__Impl"


    // $ANTLR start "rule__IntRangeRange__Group__3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2620:1: rule__IntRangeRange__Group__3 : rule__IntRangeRange__Group__3__Impl ;
    public final void rule__IntRangeRange__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2624:1: ( rule__IntRangeRange__Group__3__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2625:2: rule__IntRangeRange__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__IntRangeRange__Group__3__Impl_in_rule__IntRangeRange__Group__35311);
            rule__IntRangeRange__Group__3__Impl();

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
    // $ANTLR end "rule__IntRangeRange__Group__3"


    // $ANTLR start "rule__IntRangeRange__Group__3__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2631:1: rule__IntRangeRange__Group__3__Impl : ( ( rule__IntRangeRange__EndAssignment_3 ) ) ;
    public final void rule__IntRangeRange__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2635:1: ( ( ( rule__IntRangeRange__EndAssignment_3 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2636:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2636:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2637:1: ( rule__IntRangeRange__EndAssignment_3 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndAssignment_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2638:1: ( rule__IntRangeRange__EndAssignment_3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2638:2: rule__IntRangeRange__EndAssignment_3
            {
            pushFollow(FOLLOW_rule__IntRangeRange__EndAssignment_3_in_rule__IntRangeRange__Group__3__Impl5338);
            rule__IntRangeRange__EndAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getIntRangeRangeAccess().getEndAssignment_3()); 

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
    // $ANTLR end "rule__IntRangeRange__Group__3__Impl"


    // $ANTLR start "rule__ResourceReference__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2656:1: rule__ResourceReference__Group__0 : rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 ;
    public final void rule__ResourceReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2660:1: ( rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2661:2: rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1
            {
            pushFollow(FOLLOW_rule__ResourceReference__Group__0__Impl_in_rule__ResourceReference__Group__05376);
            rule__ResourceReference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__ResourceReference__Group__1_in_rule__ResourceReference__Group__05379);
            rule__ResourceReference__Group__1();

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
    // $ANTLR end "rule__ResourceReference__Group__0"


    // $ANTLR start "rule__ResourceReference__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2668:1: rule__ResourceReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__ResourceReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2672:1: ( ( 'ref' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2673:1: ( 'ref' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2673:1: ( 'ref' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2674:1: 'ref'
            {
             before(grammarAccess.getResourceReferenceAccess().getRefKeyword_0()); 
            match(input,29,FOLLOW_29_in_rule__ResourceReference__Group__0__Impl5407); 
             after(grammarAccess.getResourceReferenceAccess().getRefKeyword_0()); 

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
    // $ANTLR end "rule__ResourceReference__Group__0__Impl"


    // $ANTLR start "rule__ResourceReference__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2687:1: rule__ResourceReference__Group__1 : rule__ResourceReference__Group__1__Impl ;
    public final void rule__ResourceReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2691:1: ( rule__ResourceReference__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2692:2: rule__ResourceReference__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__ResourceReference__Group__1__Impl_in_rule__ResourceReference__Group__15438);
            rule__ResourceReference__Group__1__Impl();

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
    // $ANTLR end "rule__ResourceReference__Group__1"


    // $ANTLR start "rule__ResourceReference__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2698:1: rule__ResourceReference__Group__1__Impl : ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) ;
    public final void rule__ResourceReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2702:1: ( ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2703:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2703:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2704:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2704:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2705:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2706:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2706:2: rule__ResourceReference__ResourceRefsAssignment_1
            {
            pushFollow(FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl5467);
            rule__ResourceReference__ResourceRefsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }

            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2709:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2710:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2711:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_ID) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2711:2: rule__ResourceReference__ResourceRefsAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl5479);
            	    rule__ResourceReference__ResourceRefsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }


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
    // $ANTLR end "rule__ResourceReference__Group__1__Impl"


    // $ANTLR start "rule__GlobalResourceRef__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2726:1: rule__GlobalResourceRef__Group__0 : rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 ;
    public final void rule__GlobalResourceRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2730:1: ( rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2731:2: rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__0__Impl_in_rule__GlobalResourceRef__Group__05516);
            rule__GlobalResourceRef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__1_in_rule__GlobalResourceRef__Group__05519);
            rule__GlobalResourceRef__Group__1();

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
    // $ANTLR end "rule__GlobalResourceRef__Group__0"


    // $ANTLR start "rule__GlobalResourceRef__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2738:1: rule__GlobalResourceRef__Group__0__Impl : ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalResourceRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2742:1: ( ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2743:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2743:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2744:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2745:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2745:2: rule__GlobalResourceRef__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__NameAssignment_0_in_rule__GlobalResourceRef__Group__0__Impl5546);
            rule__GlobalResourceRef__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 

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
    // $ANTLR end "rule__GlobalResourceRef__Group__0__Impl"


    // $ANTLR start "rule__GlobalResourceRef__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2755:1: rule__GlobalResourceRef__Group__1 : rule__GlobalResourceRef__Group__1__Impl ;
    public final void rule__GlobalResourceRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2759:1: ( rule__GlobalResourceRef__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2760:2: rule__GlobalResourceRef__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__Group__1__Impl_in_rule__GlobalResourceRef__Group__15576);
            rule__GlobalResourceRef__Group__1__Impl();

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
    // $ANTLR end "rule__GlobalResourceRef__Group__1"


    // $ANTLR start "rule__GlobalResourceRef__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2766:1: rule__GlobalResourceRef__Group__1__Impl : ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) ;
    public final void rule__GlobalResourceRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2770:1: ( ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2771:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2771:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2772:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2773:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2773:2: rule__GlobalResourceRef__ResourcesAssignment_1
            {
            pushFollow(FOLLOW_rule__GlobalResourceRef__ResourcesAssignment_1_in_rule__GlobalResourceRef__Group__1__Impl5603);
            rule__GlobalResourceRef__ResourcesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 

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
    // $ANTLR end "rule__GlobalResourceRef__Group__1__Impl"


    // $ANTLR start "rule__LocalResource__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2787:1: rule__LocalResource__Group__0 : rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 ;
    public final void rule__LocalResource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2791:1: ( rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2792:2: rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1
            {
            pushFollow(FOLLOW_rule__LocalResource__Group__0__Impl_in_rule__LocalResource__Group__05637);
            rule__LocalResource__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalResource__Group__1_in_rule__LocalResource__Group__05640);
            rule__LocalResource__Group__1();

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
    // $ANTLR end "rule__LocalResource__Group__0"


    // $ANTLR start "rule__LocalResource__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2799:1: rule__LocalResource__Group__0__Impl : ( ( rule__LocalResource__PathAssignment_0 ) ) ;
    public final void rule__LocalResource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2803:1: ( ( ( rule__LocalResource__PathAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2804:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2804:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2805:1: ( rule__LocalResource__PathAssignment_0 )
            {
             before(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2806:1: ( rule__LocalResource__PathAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2806:2: rule__LocalResource__PathAssignment_0
            {
            pushFollow(FOLLOW_rule__LocalResource__PathAssignment_0_in_rule__LocalResource__Group__0__Impl5667);
            rule__LocalResource__PathAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 

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
    // $ANTLR end "rule__LocalResource__Group__0__Impl"


    // $ANTLR start "rule__LocalResource__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2816:1: rule__LocalResource__Group__1 : rule__LocalResource__Group__1__Impl ;
    public final void rule__LocalResource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2820:1: ( rule__LocalResource__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2821:2: rule__LocalResource__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalResource__Group__1__Impl_in_rule__LocalResource__Group__15697);
            rule__LocalResource__Group__1__Impl();

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
    // $ANTLR end "rule__LocalResource__Group__1"


    // $ANTLR start "rule__LocalResource__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2827:1: rule__LocalResource__Group__1__Impl : ( ( rule__LocalResource__Group_1__0 ) ) ;
    public final void rule__LocalResource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2831:1: ( ( ( rule__LocalResource__Group_1__0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2832:1: ( ( rule__LocalResource__Group_1__0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2832:1: ( ( rule__LocalResource__Group_1__0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2833:1: ( rule__LocalResource__Group_1__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2834:1: ( rule__LocalResource__Group_1__0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2834:2: rule__LocalResource__Group_1__0
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__0_in_rule__LocalResource__Group__1__Impl5724);
            rule__LocalResource__Group_1__0();

            state._fsp--;


            }

             after(grammarAccess.getLocalResourceAccess().getGroup_1()); 

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
    // $ANTLR end "rule__LocalResource__Group__1__Impl"


    // $ANTLR start "rule__LocalResource__Group_1__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2848:1: rule__LocalResource__Group_1__0 : rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 ;
    public final void rule__LocalResource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2852:1: ( rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2853:2: rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__0__Impl_in_rule__LocalResource__Group_1__05758);
            rule__LocalResource__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__LocalResource__Group_1__1_in_rule__LocalResource__Group_1__05761);
            rule__LocalResource__Group_1__1();

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
    // $ANTLR end "rule__LocalResource__Group_1__0"


    // $ANTLR start "rule__LocalResource__Group_1__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2860:1: rule__LocalResource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__LocalResource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2864:1: ( ( 'filter' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2865:1: ( 'filter' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2865:1: ( 'filter' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2866:1: 'filter'
            {
             before(grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0()); 
            match(input,30,FOLLOW_30_in_rule__LocalResource__Group_1__0__Impl5789); 
             after(grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0()); 

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
    // $ANTLR end "rule__LocalResource__Group_1__0__Impl"


    // $ANTLR start "rule__LocalResource__Group_1__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2879:1: rule__LocalResource__Group_1__1 : rule__LocalResource__Group_1__1__Impl ;
    public final void rule__LocalResource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2883:1: ( rule__LocalResource__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2884:2: rule__LocalResource__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__LocalResource__Group_1__1__Impl_in_rule__LocalResource__Group_1__15820);
            rule__LocalResource__Group_1__1__Impl();

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
    // $ANTLR end "rule__LocalResource__Group_1__1"


    // $ANTLR start "rule__LocalResource__Group_1__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2890:1: rule__LocalResource__Group_1__1__Impl : ( ( rule__LocalResource__FilterAssignment_1_1 ) ) ;
    public final void rule__LocalResource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2894:1: ( ( ( rule__LocalResource__FilterAssignment_1_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2895:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2895:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2896:1: ( rule__LocalResource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2897:1: ( rule__LocalResource__FilterAssignment_1_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2897:2: rule__LocalResource__FilterAssignment_1_1
            {
            pushFollow(FOLLOW_rule__LocalResource__FilterAssignment_1_1_in_rule__LocalResource__Group_1__1__Impl5847);
            rule__LocalResource__FilterAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 

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
    // $ANTLR end "rule__LocalResource__Group_1__1__Impl"


    // $ANTLR start "rule__GlobalOutputRef__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2911:1: rule__GlobalOutputRef__Group__0 : rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 ;
    public final void rule__GlobalOutputRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2915:1: ( rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2916:2: rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__Group__0__Impl_in_rule__GlobalOutputRef__Group__05881);
            rule__GlobalOutputRef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__GlobalOutputRef__Group__1_in_rule__GlobalOutputRef__Group__05884);
            rule__GlobalOutputRef__Group__1();

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
    // $ANTLR end "rule__GlobalOutputRef__Group__0"


    // $ANTLR start "rule__GlobalOutputRef__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2923:1: rule__GlobalOutputRef__Group__0__Impl : ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalOutputRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2927:1: ( ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2928:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2928:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2929:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2930:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2930:2: rule__GlobalOutputRef__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__NameAssignment_0_in_rule__GlobalOutputRef__Group__0__Impl5911);
            rule__GlobalOutputRef__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0()); 

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
    // $ANTLR end "rule__GlobalOutputRef__Group__0__Impl"


    // $ANTLR start "rule__GlobalOutputRef__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2940:1: rule__GlobalOutputRef__Group__1 : rule__GlobalOutputRef__Group__1__Impl ;
    public final void rule__GlobalOutputRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2944:1: ( rule__GlobalOutputRef__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2945:2: rule__GlobalOutputRef__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__Group__1__Impl_in_rule__GlobalOutputRef__Group__15941);
            rule__GlobalOutputRef__Group__1__Impl();

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
    // $ANTLR end "rule__GlobalOutputRef__Group__1"


    // $ANTLR start "rule__GlobalOutputRef__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2951:1: rule__GlobalOutputRef__Group__1__Impl : ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) ;
    public final void rule__GlobalOutputRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2955:1: ( ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2956:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2956:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2957:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2958:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2958:2: rule__GlobalOutputRef__OutputAssignment_1
            {
            pushFollow(FOLLOW_rule__GlobalOutputRef__OutputAssignment_1_in_rule__GlobalOutputRef__Group__1__Impl5968);
            rule__GlobalOutputRef__OutputAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1()); 

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
    // $ANTLR end "rule__GlobalOutputRef__Group__1__Impl"


    // $ANTLR start "rule__OutputReference__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2972:1: rule__OutputReference__Group__0 : rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 ;
    public final void rule__OutputReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2976:1: ( rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2977:2: rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1
            {
            pushFollow(FOLLOW_rule__OutputReference__Group__0__Impl_in_rule__OutputReference__Group__06002);
            rule__OutputReference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__OutputReference__Group__1_in_rule__OutputReference__Group__06005);
            rule__OutputReference__Group__1();

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
    // $ANTLR end "rule__OutputReference__Group__0"


    // $ANTLR start "rule__OutputReference__Group__0__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2984:1: rule__OutputReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__OutputReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2988:1: ( ( 'ref' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2989:1: ( 'ref' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2989:1: ( 'ref' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:2990:1: 'ref'
            {
             before(grammarAccess.getOutputReferenceAccess().getRefKeyword_0()); 
            match(input,29,FOLLOW_29_in_rule__OutputReference__Group__0__Impl6033); 
             after(grammarAccess.getOutputReferenceAccess().getRefKeyword_0()); 

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
    // $ANTLR end "rule__OutputReference__Group__0__Impl"


    // $ANTLR start "rule__OutputReference__Group__1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3003:1: rule__OutputReference__Group__1 : rule__OutputReference__Group__1__Impl ;
    public final void rule__OutputReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3007:1: ( rule__OutputReference__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3008:2: rule__OutputReference__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__OutputReference__Group__1__Impl_in_rule__OutputReference__Group__16064);
            rule__OutputReference__Group__1__Impl();

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
    // $ANTLR end "rule__OutputReference__Group__1"


    // $ANTLR start "rule__OutputReference__Group__1__Impl"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3014:1: rule__OutputReference__Group__1__Impl : ( ( rule__OutputReference__OutputRefAssignment_1 ) ) ;
    public final void rule__OutputReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3018:1: ( ( ( rule__OutputReference__OutputRefAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3019:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3019:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3020:1: ( rule__OutputReference__OutputRefAssignment_1 )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3021:1: ( rule__OutputReference__OutputRefAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3021:2: rule__OutputReference__OutputRefAssignment_1
            {
            pushFollow(FOLLOW_rule__OutputReference__OutputRefAssignment_1_in_rule__OutputReference__Group__1__Impl6091);
            rule__OutputReference__OutputRefAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1()); 

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
    // $ANTLR end "rule__OutputReference__Group__1__Impl"


    // $ANTLR start "rule__KIdentifier__Group__0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3035:1: rule__KIdentifier__Group__0 : rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 ;
    public final void rule__KIdentifier__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3039:1: ( rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3040:2: rule__KIdentifier__Group__0__Impl rule__KIdentifier__Group__1
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__06125);
            rule__KIdentifier__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__06128);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3047:1: rule__KIdentifier__Group__0__Impl : ( () ) ;
    public final void rule__KIdentifier__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3051:1: ( ( () ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3052:1: ( () )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3052:1: ( () )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3053:1: ()
            {
             before(grammarAccess.getKIdentifierAccess().getKIdentifierAction_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3054:1: ()
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3056:1: 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3066:1: rule__KIdentifier__Group__1 : rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 ;
    public final void rule__KIdentifier__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3070:1: ( rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3071:2: rule__KIdentifier__Group__1__Impl rule__KIdentifier__Group__2
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__16186);
            rule__KIdentifier__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__16189);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3078:1: rule__KIdentifier__Group__1__Impl : ( ( rule__KIdentifier__IdAssignment_1 ) ) ;
    public final void rule__KIdentifier__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3082:1: ( ( ( rule__KIdentifier__IdAssignment_1 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3083:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3083:1: ( ( rule__KIdentifier__IdAssignment_1 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3084:1: ( rule__KIdentifier__IdAssignment_1 )
            {
             before(grammarAccess.getKIdentifierAccess().getIdAssignment_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3085:1: ( rule__KIdentifier__IdAssignment_1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3085:2: rule__KIdentifier__IdAssignment_1
            {
            pushFollow(FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl6216);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3095:1: rule__KIdentifier__Group__2 : rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 ;
    public final void rule__KIdentifier__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3099:1: ( rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3100:2: rule__KIdentifier__Group__2__Impl rule__KIdentifier__Group__3
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__26246);
            rule__KIdentifier__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__26249);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3107:1: rule__KIdentifier__Group__2__Impl : ( '{' ) ;
    public final void rule__KIdentifier__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3111:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3112:1: ( '{' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3112:1: ( '{' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3113:1: '{'
            {
             before(grammarAccess.getKIdentifierAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,31,FOLLOW_31_in_rule__KIdentifier__Group__2__Impl6277); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3126:1: rule__KIdentifier__Group__3 : rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 ;
    public final void rule__KIdentifier__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3130:1: ( rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3131:2: rule__KIdentifier__Group__3__Impl rule__KIdentifier__Group__4
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__36308);
            rule__KIdentifier__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__36311);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3138:1: rule__KIdentifier__Group__3__Impl : ( ( rule__KIdentifier__Group_3__0 )? ) ;
    public final void rule__KIdentifier__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3142:1: ( ( ( rule__KIdentifier__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3143:1: ( ( rule__KIdentifier__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3143:1: ( ( rule__KIdentifier__Group_3__0 )? )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3144:1: ( rule__KIdentifier__Group_3__0 )?
            {
             before(grammarAccess.getKIdentifierAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3145:1: ( rule__KIdentifier__Group_3__0 )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3145:2: rule__KIdentifier__Group_3__0
                    {
                    pushFollow(FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl6338);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3155:1: rule__KIdentifier__Group__4 : rule__KIdentifier__Group__4__Impl ;
    public final void rule__KIdentifier__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3159:1: ( rule__KIdentifier__Group__4__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3160:2: rule__KIdentifier__Group__4__Impl
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__46369);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3166:1: rule__KIdentifier__Group__4__Impl : ( '}' ) ;
    public final void rule__KIdentifier__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3170:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3171:1: ( '}' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3171:1: ( '}' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3172:1: '}'
            {
             before(grammarAccess.getKIdentifierAccess().getRightCurlyBracketKeyword_4()); 
            match(input,32,FOLLOW_32_in_rule__KIdentifier__Group__4__Impl6397); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3195:1: rule__KIdentifier__Group_3__0 : rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 ;
    public final void rule__KIdentifier__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3199:1: ( rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3200:2: rule__KIdentifier__Group_3__0__Impl rule__KIdentifier__Group_3__1
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__06438);
            rule__KIdentifier__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__06441);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3207:1: rule__KIdentifier__Group_3__0__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) ;
    public final void rule__KIdentifier__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3211:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3212:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3212:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3213:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3214:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3214:2: rule__KIdentifier__PersistentEntriesAssignment_3_0
            {
            pushFollow(FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl6468);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3224:1: rule__KIdentifier__Group_3__1 : rule__KIdentifier__Group_3__1__Impl ;
    public final void rule__KIdentifier__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3228:1: ( rule__KIdentifier__Group_3__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3229:2: rule__KIdentifier__Group_3__1__Impl
            {
            pushFollow(FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__16498);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3235:1: rule__KIdentifier__Group_3__1__Impl : ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) ;
    public final void rule__KIdentifier__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3239:1: ( ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3240:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3240:1: ( ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3241:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesAssignment_3_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3242:1: ( rule__KIdentifier__PersistentEntriesAssignment_3_1 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3242:2: rule__KIdentifier__PersistentEntriesAssignment_3_1
            	    {
            	    pushFollow(FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl6525);
            	    rule__KIdentifier__PersistentEntriesAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3256:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3260:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3261:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__06560);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__06563);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3268:1: rule__PersistentEntry__Group__0__Impl : ( ( rule__PersistentEntry__KeyAssignment_0 ) ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3272:1: ( ( ( rule__PersistentEntry__KeyAssignment_0 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3273:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3273:1: ( ( rule__PersistentEntry__KeyAssignment_0 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3274:1: ( rule__PersistentEntry__KeyAssignment_0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3275:1: ( rule__PersistentEntry__KeyAssignment_0 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3275:2: rule__PersistentEntry__KeyAssignment_0
            {
            pushFollow(FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl6590);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3285:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3289:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3290:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__16620);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__16623);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3297:1: rule__PersistentEntry__Group__1__Impl : ( ':' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3301:1: ( ( ':' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3302:1: ( ':' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3302:1: ( ':' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3303:1: ':'
            {
             before(grammarAccess.getPersistentEntryAccess().getColonKeyword_1()); 
            match(input,33,FOLLOW_33_in_rule__PersistentEntry__Group__1__Impl6651); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3316:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3320:1: ( rule__PersistentEntry__Group__2__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3321:2: rule__PersistentEntry__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__26682);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3327:1: rule__PersistentEntry__Group__2__Impl : ( ( rule__PersistentEntry__ValueAssignment_2 ) ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3331:1: ( ( ( rule__PersistentEntry__ValueAssignment_2 ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3332:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3332:1: ( ( rule__PersistentEntry__ValueAssignment_2 ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3333:1: ( rule__PersistentEntry__ValueAssignment_2 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_2()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3334:1: ( rule__PersistentEntry__ValueAssignment_2 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3334:2: rule__PersistentEntry__ValueAssignment_2
            {
            pushFollow(FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl6709);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3350:1: rule__QualifiedID__Group__0 : rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 ;
    public final void rule__QualifiedID__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3354:1: ( rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3355:2: rule__QualifiedID__Group__0__Impl rule__QualifiedID__Group__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__06745);
            rule__QualifiedID__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__06748);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3362:1: rule__QualifiedID__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3366:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3367:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3367:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3368:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl6775); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3379:1: rule__QualifiedID__Group__1 : rule__QualifiedID__Group__1__Impl ;
    public final void rule__QualifiedID__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3383:1: ( rule__QualifiedID__Group__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3384:2: rule__QualifiedID__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__16804);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3390:1: rule__QualifiedID__Group__1__Impl : ( ( rule__QualifiedID__Group_1__0 )* ) ;
    public final void rule__QualifiedID__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3394:1: ( ( ( rule__QualifiedID__Group_1__0 )* ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3395:1: ( ( rule__QualifiedID__Group_1__0 )* )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3395:1: ( ( rule__QualifiedID__Group_1__0 )* )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3396:1: ( rule__QualifiedID__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIDAccess().getGroup_1()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3397:1: ( rule__QualifiedID__Group_1__0 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==34) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3397:2: rule__QualifiedID__Group_1__0
            	    {
            	    pushFollow(FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl6831);
            	    rule__QualifiedID__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3411:1: rule__QualifiedID__Group_1__0 : rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 ;
    public final void rule__QualifiedID__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3415:1: ( rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1 )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3416:2: rule__QualifiedID__Group_1__0__Impl rule__QualifiedID__Group_1__1
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__06866);
            rule__QualifiedID__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__06869);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3423:1: rule__QualifiedID__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedID__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3427:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3428:1: ( '.' )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3428:1: ( '.' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3429:1: '.'
            {
             before(grammarAccess.getQualifiedIDAccess().getFullStopKeyword_1_0()); 
            match(input,34,FOLLOW_34_in_rule__QualifiedID__Group_1__0__Impl6897); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3442:1: rule__QualifiedID__Group_1__1 : rule__QualifiedID__Group_1__1__Impl ;
    public final void rule__QualifiedID__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3446:1: ( rule__QualifiedID__Group_1__1__Impl )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3447:2: rule__QualifiedID__Group_1__1__Impl
            {
            pushFollow(FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__16928);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3453:1: rule__QualifiedID__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedID__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3457:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3458:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3458:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3459:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl6955); 
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


    // $ANTLR start "rule__Grana__GlobalResourcesAssignment_0_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3475:1: rule__Grana__GlobalResourcesAssignment_0_1 : ( ruleGlobalResourceRef ) ;
    public final void rule__Grana__GlobalResourcesAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3479:1: ( ( ruleGlobalResourceRef ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3480:1: ( ruleGlobalResourceRef )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3480:1: ( ruleGlobalResourceRef )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3481:1: ruleGlobalResourceRef
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesGlobalResourceRefParserRuleCall_0_1_0()); 
            pushFollow(FOLLOW_ruleGlobalResourceRef_in_rule__Grana__GlobalResourcesAssignment_0_16993);
            ruleGlobalResourceRef();

            state._fsp--;

             after(grammarAccess.getGranaAccess().getGlobalResourcesGlobalResourceRefParserRuleCall_0_1_0()); 

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
    // $ANTLR end "rule__Grana__GlobalResourcesAssignment_0_1"


    // $ANTLR start "rule__Grana__GloobalOutputsAssignment_1_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3490:1: rule__Grana__GloobalOutputsAssignment_1_1 : ( ruleGlobalOutputRef ) ;
    public final void rule__Grana__GloobalOutputsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3494:1: ( ( ruleGlobalOutputRef ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3495:1: ( ruleGlobalOutputRef )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3495:1: ( ruleGlobalOutputRef )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3496:1: ruleGlobalOutputRef
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsGlobalOutputRefParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_ruleGlobalOutputRef_in_rule__Grana__GloobalOutputsAssignment_1_17024);
            ruleGlobalOutputRef();

            state._fsp--;

             after(grammarAccess.getGranaAccess().getGloobalOutputsGlobalOutputRefParserRuleCall_1_1_0()); 

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
    // $ANTLR end "rule__Grana__GloobalOutputsAssignment_1_1"


    // $ANTLR start "rule__Grana__ExecuteAllAssignment_2_1_0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3505:1: rule__Grana__ExecuteAllAssignment_2_1_0 : ( ( 'all' ) ) ;
    public final void rule__Grana__ExecuteAllAssignment_2_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3509:1: ( ( ( 'all' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3510:1: ( ( 'all' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3510:1: ( ( 'all' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3511:1: ( 'all' )
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_1_0_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3512:1: ( 'all' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3513:1: 'all'
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_1_0_0()); 
            match(input,35,FOLLOW_35_in_rule__Grana__ExecuteAllAssignment_2_1_07060); 
             after(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_1_0_0()); 

            }

             after(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_1_0_0()); 

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
    // $ANTLR end "rule__Grana__ExecuteAllAssignment_2_1_0"


    // $ANTLR start "rule__Grana__ExecuteAssignment_2_1_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3528:1: rule__Grana__ExecuteAssignment_2_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__Grana__ExecuteAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3532:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3533:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3533:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3534:1: ( RULE_ID )
            {
             before(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_1_1_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3535:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3536:1: RULE_ID
            {
             before(grammarAccess.getGranaAccess().getExecuteJobIDTerminalRuleCall_2_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Grana__ExecuteAssignment_2_1_17103); 
             after(grammarAccess.getGranaAccess().getExecuteJobIDTerminalRuleCall_2_1_1_0_1()); 

            }

             after(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_1_1_0()); 

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
    // $ANTLR end "rule__Grana__ExecuteAssignment_2_1_1"


    // $ANTLR start "rule__Grana__JobsAssignment_3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3547:1: rule__Grana__JobsAssignment_3 : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3551:1: ( ( ruleJob ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3552:1: ( ruleJob )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3552:1: ( ruleJob )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3553:1: ruleJob
            {
             before(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleJob_in_rule__Grana__JobsAssignment_37138);
            ruleJob();

            state._fsp--;

             after(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Grana__JobsAssignment_3"


    // $ANTLR start "rule__RegularJob__NameAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3562:1: rule__RegularJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RegularJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3566:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3567:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3567:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3568:1: RULE_ID
            {
             before(grammarAccess.getRegularJobAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__RegularJob__NameAssignment_17169); 
             after(grammarAccess.getRegularJobAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__RegularJob__NameAssignment_1"


    // $ANTLR start "rule__RegularJob__LayoutBeforeAnalysisAssignment_2"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3577:1: rule__RegularJob__LayoutBeforeAnalysisAssignment_2 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__RegularJob__LayoutBeforeAnalysisAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3581:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3582:1: ( ( 'layoutBeforeAnalysis' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3582:1: ( ( 'layoutBeforeAnalysis' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3583:1: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3584:1: ( 'layoutBeforeAnalysis' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3585:1: 'layoutBeforeAnalysis'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            match(input,36,FOLLOW_36_in_rule__RegularJob__LayoutBeforeAnalysisAssignment_27205); 
             after(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 

            }

             after(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 

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
    // $ANTLR end "rule__RegularJob__LayoutBeforeAnalysisAssignment_2"


    // $ANTLR start "rule__RegularJob__MeasureExecutionTimeAssignment_3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3600:1: rule__RegularJob__MeasureExecutionTimeAssignment_3 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RegularJob__MeasureExecutionTimeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3604:1: ( ( ( 'measureExecutionTime' ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3605:1: ( ( 'measureExecutionTime' ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3605:1: ( ( 'measureExecutionTime' ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3606:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3607:1: ( 'measureExecutionTime' )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3608:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            match(input,37,FOLLOW_37_in_rule__RegularJob__MeasureExecutionTimeAssignment_37249); 
             after(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 

            }

             after(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 

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
    // $ANTLR end "rule__RegularJob__MeasureExecutionTimeAssignment_3"


    // $ANTLR start "rule__RegularJob__ResourcesAssignment_5"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3623:1: rule__RegularJob__ResourcesAssignment_5 : ( ruleResource ) ;
    public final void rule__RegularJob__ResourcesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3627:1: ( ( ruleResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3628:1: ( ruleResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3628:1: ( ruleResource )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3629:1: ruleResource
            {
             before(grammarAccess.getRegularJobAccess().getResourcesResourceParserRuleCall_5_0()); 
            pushFollow(FOLLOW_ruleResource_in_rule__RegularJob__ResourcesAssignment_57288);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getRegularJobAccess().getResourcesResourceParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__RegularJob__ResourcesAssignment_5"


    // $ANTLR start "rule__RegularJob__LayoutOptionsAssignment_7"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3638:1: rule__RegularJob__LayoutOptionsAssignment_7 : ( ruleKIdentifier ) ;
    public final void rule__RegularJob__LayoutOptionsAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3642:1: ( ( ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3643:1: ( ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3643:1: ( ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3644:1: ruleKIdentifier
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_rule__RegularJob__LayoutOptionsAssignment_77319);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsKIdentifierParserRuleCall_7_0()); 

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
    // $ANTLR end "rule__RegularJob__LayoutOptionsAssignment_7"


    // $ANTLR start "rule__RegularJob__AnalysesAssignment_9"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3653:1: rule__RegularJob__AnalysesAssignment_9 : ( ruleAnalysis ) ;
    public final void rule__RegularJob__AnalysesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3657:1: ( ( ruleAnalysis ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3658:1: ( ruleAnalysis )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3658:1: ( ruleAnalysis )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3659:1: ruleAnalysis
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 
            pushFollow(FOLLOW_ruleAnalysis_in_rule__RegularJob__AnalysesAssignment_97350);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getRegularJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 

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
    // $ANTLR end "rule__RegularJob__AnalysesAssignment_9"


    // $ANTLR start "rule__RegularJob__OutputAssignment_11"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3668:1: rule__RegularJob__OutputAssignment_11 : ( ruleOutput ) ;
    public final void rule__RegularJob__OutputAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3672:1: ( ( ruleOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3673:1: ( ruleOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3673:1: ( ruleOutput )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3674:1: ruleOutput
            {
             before(grammarAccess.getRegularJobAccess().getOutputOutputParserRuleCall_11_0()); 
            pushFollow(FOLLOW_ruleOutput_in_rule__RegularJob__OutputAssignment_117381);
            ruleOutput();

            state._fsp--;

             after(grammarAccess.getRegularJobAccess().getOutputOutputParserRuleCall_11_0()); 

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
    // $ANTLR end "rule__RegularJob__OutputAssignment_11"


    // $ANTLR start "rule__RangeJob__NameAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3683:1: rule__RangeJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RangeJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3687:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3688:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3688:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3689:1: RULE_ID
            {
             before(grammarAccess.getRangeJobAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__RangeJob__NameAssignment_17412); 
             after(grammarAccess.getRangeJobAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__RangeJob__NameAssignment_1"


    // $ANTLR start "rule__RangeJob__ResourcesAssignment_3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3698:1: rule__RangeJob__ResourcesAssignment_3 : ( ruleResource ) ;
    public final void rule__RangeJob__ResourcesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3702:1: ( ( ruleResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3703:1: ( ruleResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3703:1: ( ruleResource )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3704:1: ruleResource
            {
             before(grammarAccess.getRangeJobAccess().getResourcesResourceParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleResource_in_rule__RangeJob__ResourcesAssignment_37443);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getResourcesResourceParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__RangeJob__ResourcesAssignment_3"


    // $ANTLR start "rule__RangeJob__LayoutOptionsAssignment_5"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3713:1: rule__RangeJob__LayoutOptionsAssignment_5 : ( ruleKIdentifier ) ;
    public final void rule__RangeJob__LayoutOptionsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3717:1: ( ( ruleKIdentifier ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3718:1: ( ruleKIdentifier )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3718:1: ( ruleKIdentifier )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3719:1: ruleKIdentifier
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsKIdentifierParserRuleCall_5_0()); 
            pushFollow(FOLLOW_ruleKIdentifier_in_rule__RangeJob__LayoutOptionsAssignment_57474);
            ruleKIdentifier();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsKIdentifierParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__RangeJob__LayoutOptionsAssignment_5"


    // $ANTLR start "rule__RangeJob__AnalysesAssignment_7"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3728:1: rule__RangeJob__AnalysesAssignment_7 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__AnalysesAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3732:1: ( ( ruleAnalysis ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3733:1: ( ruleAnalysis )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3733:1: ( ruleAnalysis )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3734:1: ruleAnalysis
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAnalysisParserRuleCall_7_0()); 
            pushFollow(FOLLOW_ruleAnalysis_in_rule__RangeJob__AnalysesAssignment_77505);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getAnalysesAnalysisParserRuleCall_7_0()); 

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
    // $ANTLR end "rule__RangeJob__AnalysesAssignment_7"


    // $ANTLR start "rule__RangeJob__RangeOptionAssignment_9"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3743:1: rule__RangeJob__RangeOptionAssignment_9 : ( ruleQualifiedID ) ;
    public final void rule__RangeJob__RangeOptionAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3747:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3748:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3748:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3749:1: ruleQualifiedID
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIDParserRuleCall_9_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__RangeJob__RangeOptionAssignment_97536);
            ruleQualifiedID();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIDParserRuleCall_9_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeOptionAssignment_9"


    // $ANTLR start "rule__RangeJob__RangeValuesAssignment_10"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3758:1: rule__RangeJob__RangeValuesAssignment_10 : ( ruleRange ) ;
    public final void rule__RangeJob__RangeValuesAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3762:1: ( ( ruleRange ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3763:1: ( ruleRange )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3763:1: ( ruleRange )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3764:1: ruleRange
            {
             before(grammarAccess.getRangeJobAccess().getRangeValuesRangeParserRuleCall_10_0()); 
            pushFollow(FOLLOW_ruleRange_in_rule__RangeJob__RangeValuesAssignment_107567);
            ruleRange();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getRangeValuesRangeParserRuleCall_10_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeValuesAssignment_10"


    // $ANTLR start "rule__RangeJob__RangeAnalysisAssignment_12"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3773:1: rule__RangeJob__RangeAnalysisAssignment_12 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysisAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3777:1: ( ( ruleAnalysis ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3778:1: ( ruleAnalysis )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3778:1: ( ruleAnalysis )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3779:1: ruleAnalysis
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisAnalysisParserRuleCall_12_0()); 
            pushFollow(FOLLOW_ruleAnalysis_in_rule__RangeJob__RangeAnalysisAssignment_127598);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getRangeAnalysisAnalysisParserRuleCall_12_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeAnalysisAssignment_12"


    // $ANTLR start "rule__RangeJob__RangeAnalysisComponentAssignment_13_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3788:1: rule__RangeJob__RangeAnalysisComponentAssignment_13_1 : ( RULE_NATURAL ) ;
    public final void rule__RangeJob__RangeAnalysisComponentAssignment_13_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3792:1: ( ( RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3793:1: ( RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3793:1: ( RULE_NATURAL )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3794:1: RULE_NATURAL
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentNATURALTerminalRuleCall_13_1_0()); 
            match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__RangeJob__RangeAnalysisComponentAssignment_13_17629); 
             after(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentNATURALTerminalRuleCall_13_1_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeAnalysisComponentAssignment_13_1"


    // $ANTLR start "rule__RangeJob__OutputAssignment_15"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3803:1: rule__RangeJob__OutputAssignment_15 : ( ruleOutput ) ;
    public final void rule__RangeJob__OutputAssignment_15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3807:1: ( ( ruleOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3808:1: ( ruleOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3808:1: ( ruleOutput )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3809:1: ruleOutput
            {
             before(grammarAccess.getRangeJobAccess().getOutputOutputParserRuleCall_15_0()); 
            pushFollow(FOLLOW_ruleOutput_in_rule__RangeJob__OutputAssignment_157660);
            ruleOutput();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getOutputOutputParserRuleCall_15_0()); 

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
    // $ANTLR end "rule__RangeJob__OutputAssignment_15"


    // $ANTLR start "rule__FloatRange__ValuesAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3818:1: rule__FloatRange__ValuesAssignment_1 : ( ruleFloat ) ;
    public final void rule__FloatRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3822:1: ( ( ruleFloat ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3823:1: ( ruleFloat )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3823:1: ( ruleFloat )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3824:1: ruleFloat
            {
             before(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleFloat_in_rule__FloatRange__ValuesAssignment_17691);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__FloatRange__ValuesAssignment_1"


    // $ANTLR start "rule__FloatRange__ValuesAssignment_2_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3833:1: rule__FloatRange__ValuesAssignment_2_1 : ( ruleFloat ) ;
    public final void rule__FloatRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3837:1: ( ( ruleFloat ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3838:1: ( ruleFloat )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3838:1: ( ruleFloat )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3839:1: ruleFloat
            {
             before(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_ruleFloat_in_rule__FloatRange__ValuesAssignment_2_17722);
            ruleFloat();

            state._fsp--;

             after(grammarAccess.getFloatRangeAccess().getValuesFloatParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__FloatRange__ValuesAssignment_2_1"


    // $ANTLR start "rule__IntRangeValues__ValuesAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3848:1: rule__IntRangeValues__ValuesAssignment_1 : ( RULE_NATURAL ) ;
    public final void rule__IntRangeValues__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3852:1: ( ( RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3853:1: ( RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3853:1: ( RULE_NATURAL )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3854:1: RULE_NATURAL
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_1_0()); 
            match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__IntRangeValues__ValuesAssignment_17753); 
             after(grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__IntRangeValues__ValuesAssignment_1"


    // $ANTLR start "rule__IntRangeValues__ValuesAssignment_2_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3863:1: rule__IntRangeValues__ValuesAssignment_2_1 : ( RULE_NATURAL ) ;
    public final void rule__IntRangeValues__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3867:1: ( ( RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3868:1: ( RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3868:1: ( RULE_NATURAL )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3869:1: RULE_NATURAL
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_2_1_0()); 
            match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__IntRangeValues__ValuesAssignment_2_17784); 
             after(grammarAccess.getIntRangeValuesAccess().getValuesNATURALTerminalRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__IntRangeValues__ValuesAssignment_2_1"


    // $ANTLR start "rule__IntRangeRange__StartAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3878:1: rule__IntRangeRange__StartAssignment_1 : ( RULE_NATURAL ) ;
    public final void rule__IntRangeRange__StartAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3882:1: ( ( RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3883:1: ( RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3883:1: ( RULE_NATURAL )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3884:1: RULE_NATURAL
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartNATURALTerminalRuleCall_1_0()); 
            match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__IntRangeRange__StartAssignment_17815); 
             after(grammarAccess.getIntRangeRangeAccess().getStartNATURALTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__IntRangeRange__StartAssignment_1"


    // $ANTLR start "rule__IntRangeRange__EndAssignment_3"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3893:1: rule__IntRangeRange__EndAssignment_3 : ( RULE_NATURAL ) ;
    public final void rule__IntRangeRange__EndAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3897:1: ( ( RULE_NATURAL ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3898:1: ( RULE_NATURAL )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3898:1: ( RULE_NATURAL )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3899:1: RULE_NATURAL
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndNATURALTerminalRuleCall_3_0()); 
            match(input,RULE_NATURAL,FOLLOW_RULE_NATURAL_in_rule__IntRangeRange__EndAssignment_37846); 
             after(grammarAccess.getIntRangeRangeAccess().getEndNATURALTerminalRuleCall_3_0()); 

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
    // $ANTLR end "rule__IntRangeRange__EndAssignment_3"


    // $ANTLR start "rule__ResourceReference__ResourceRefsAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3908:1: rule__ResourceReference__ResourceRefsAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceReference__ResourceRefsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3912:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3913:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3913:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3914:1: ( RULE_ID )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3915:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3916:1: RULE_ID
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__ResourceReference__ResourceRefsAssignment_17881); 
             after(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 

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
    // $ANTLR end "rule__ResourceReference__ResourceRefsAssignment_1"


    // $ANTLR start "rule__GlobalResourceRef__NameAssignment_0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3927:1: rule__GlobalResourceRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalResourceRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3931:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3932:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3932:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3933:1: RULE_ID
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__GlobalResourceRef__NameAssignment_07916); 
             after(grammarAccess.getGlobalResourceRefAccess().getNameIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__GlobalResourceRef__NameAssignment_0"


    // $ANTLR start "rule__GlobalResourceRef__ResourcesAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3942:1: rule__GlobalResourceRef__ResourcesAssignment_1 : ( ruleLocalResource ) ;
    public final void rule__GlobalResourceRef__ResourcesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3946:1: ( ( ruleLocalResource ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3947:1: ( ruleLocalResource )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3947:1: ( ruleLocalResource )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3948:1: ruleLocalResource
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleLocalResource_in_rule__GlobalResourceRef__ResourcesAssignment_17947);
            ruleLocalResource();

            state._fsp--;

             after(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__GlobalResourceRef__ResourcesAssignment_1"


    // $ANTLR start "rule__LocalResource__PathAssignment_0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3957:1: rule__LocalResource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__LocalResource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3961:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3962:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3962:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3963:1: RULE_STRING
            {
             before(grammarAccess.getLocalResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__LocalResource__PathAssignment_07978); 
             after(grammarAccess.getLocalResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__LocalResource__PathAssignment_0"


    // $ANTLR start "rule__LocalResource__FilterAssignment_1_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3972:1: rule__LocalResource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__LocalResource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3976:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3977:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3977:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3978:1: RULE_STRING
            {
             before(grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__LocalResource__FilterAssignment_1_18009); 
             after(grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 

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
    // $ANTLR end "rule__LocalResource__FilterAssignment_1_1"


    // $ANTLR start "rule__GlobalOutputRef__NameAssignment_0"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3987:1: rule__GlobalOutputRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalOutputRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3991:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3992:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3992:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:3993:1: RULE_ID
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__GlobalOutputRef__NameAssignment_08040); 
             after(grammarAccess.getGlobalOutputRefAccess().getNameIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__GlobalOutputRef__NameAssignment_0"


    // $ANTLR start "rule__GlobalOutputRef__OutputAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4002:1: rule__GlobalOutputRef__OutputAssignment_1 : ( ruleLocalOutput ) ;
    public final void rule__GlobalOutputRef__OutputAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4006:1: ( ( ruleLocalOutput ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4007:1: ( ruleLocalOutput )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4007:1: ( ruleLocalOutput )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4008:1: ruleLocalOutput
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputLocalOutputParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleLocalOutput_in_rule__GlobalOutputRef__OutputAssignment_18071);
            ruleLocalOutput();

            state._fsp--;

             after(grammarAccess.getGlobalOutputRefAccess().getOutputLocalOutputParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__GlobalOutputRef__OutputAssignment_1"


    // $ANTLR start "rule__OutputReference__OutputRefAssignment_1"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4017:1: rule__OutputReference__OutputRefAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__OutputReference__OutputRefAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4021:1: ( ( ( RULE_ID ) ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4022:1: ( ( RULE_ID ) )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4022:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4023:1: ( RULE_ID )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4024:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4025:1: RULE_ID
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__OutputReference__OutputRefAssignment_18106); 
             after(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 

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
    // $ANTLR end "rule__OutputReference__OutputRefAssignment_1"


    // $ANTLR start "rule__LocalOutput__PathAssignment"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4036:1: rule__LocalOutput__PathAssignment : ( RULE_STRING ) ;
    public final void rule__LocalOutput__PathAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4040:1: ( ( RULE_STRING ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4041:1: ( RULE_STRING )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4041:1: ( RULE_STRING )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4042:1: RULE_STRING
            {
             before(grammarAccess.getLocalOutputAccess().getPathSTRINGTerminalRuleCall_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__LocalOutput__PathAssignment8141); 
             after(grammarAccess.getLocalOutputAccess().getPathSTRINGTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__LocalOutput__PathAssignment"


    // $ANTLR start "rule__Analysis__NameAssignment"
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4051:1: rule__Analysis__NameAssignment : ( ruleQualifiedID ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4055:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4056:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4056:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4057:1: ruleQualifiedID
            {
             before(grammarAccess.getAnalysisAccess().getNameQualifiedIDParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__Analysis__NameAssignment8172);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4066:1: rule__KIdentifier__IdAssignment_1 : ( RULE_ID ) ;
    public final void rule__KIdentifier__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4070:1: ( ( RULE_ID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4071:1: ( RULE_ID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4071:1: ( RULE_ID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4072:1: RULE_ID
            {
             before(grammarAccess.getKIdentifierAccess().getIdIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_18203); 
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4081:1: rule__KIdentifier__PersistentEntriesAssignment_3_0 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4085:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4086:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4086:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4087:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_08234);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4096:1: rule__KIdentifier__PersistentEntriesAssignment_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KIdentifier__PersistentEntriesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4100:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4101:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4101:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4102:1: rulePersistentEntry
            {
             before(grammarAccess.getKIdentifierAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_18265);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4111:1: rule__PersistentEntry__KeyAssignment_0 : ( ruleQualifiedID ) ;
    public final void rule__PersistentEntry__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4115:1: ( ( ruleQualifiedID ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4116:1: ( ruleQualifiedID )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4116:1: ( ruleQualifiedID )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4117:1: ruleQualifiedID
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyQualifiedIDParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_08296);
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
    // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4126:1: rule__PersistentEntry__ValueAssignment_2 : ( rulePropertyValue ) ;
    public final void rule__PersistentEntry__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4130:1: ( ( rulePropertyValue ) )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4131:1: ( rulePropertyValue )
            {
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4131:1: ( rulePropertyValue )
            // ../de.cau.cs.kieler.kiml.grana.text.ui/src-gen/de/cau/cs/kieler/kiml/grana/text/ui/contentassist/antlr/internal/InternalGrana.g:4132:1: rulePropertyValue
            {
             before(grammarAccess.getPersistentEntryAccess().getValuePropertyValueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_28327);
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
    public static final BitSet FOLLOW_rule__Grana__Group__0_in_ruleGrana94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJob_in_entryRuleJob121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJob128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Job__Alternatives_in_ruleJob154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRegularJob_in_entryRuleRegularJob181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRegularJob188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__0_in_ruleRegularJob214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRangeJob_in_entryRuleRangeJob241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRangeJob248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__0_in_ruleRangeJob274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRange_in_entryRuleRange301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRange308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Range__Alternatives_in_ruleRange334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloatRange_in_entryRuleFloatRange361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloatRange368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FloatRange__Group__0_in_ruleFloatRange394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRange_in_entryRuleIntRange421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntRange428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRange__Alternatives_in_ruleIntRange454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRangeValues_in_entryRuleIntRangeValues481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntRangeValues488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group__0_in_ruleIntRangeValues514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRangeRange_in_entryRuleIntRangeRange541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntRangeRange548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeRange__Group__0_in_ruleIntRangeRange574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_entryRuleResource601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResource608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Resource__Alternatives_in_ruleResource634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_entryRuleResourceReference661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleResourceReference668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__0_in_ruleResourceReference694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_entryRuleGlobalResourceRef721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalResourceRef728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__0_in_ruleGlobalResourceRef754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_entryRuleLocalResource781 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalResource788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__0_in_ruleLocalResource814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutput_in_entryRuleOutput841 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutput848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Output__Alternatives_in_ruleOutput874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalOutputRef_in_entryRuleGlobalOutputRef901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalOutputRef908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__Group__0_in_ruleGlobalOutputRef934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutputReference_in_entryRuleOutputReference961 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutputReference968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OutputReference__Group__0_in_ruleOutputReference994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_entryRuleLocalOutput1021 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLocalOutput1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalOutput__PathAssignment_in_ruleLocalOutput1054 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_entryRuleAnalysis1081 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAnalysis1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Analysis__NameAssignment_in_ruleAnalysis1114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_entryRuleKIdentifier1141 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleKIdentifier1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__0_in_ruleKIdentifier1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry1201 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID1261 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID1268 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0_in_ruleQualifiedID1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_entryRulePropertyValue1321 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePropertyValue1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PropertyValue__Alternatives_in_rulePropertyValue1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_entryRuleFloat1381 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFloat1388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Float__Alternatives_in_ruleFloat1414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__ExecuteAllAssignment_2_1_0_in_rule__Grana__Alternatives_2_11450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__ExecuteAssignment_2_1_1_in_rule__Grana__Alternatives_2_11470 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Grana__ExecuteAssignment_2_1_1_in_rule__Grana__Alternatives_2_11482 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_ruleRegularJob_in_rule__Job__Alternatives1518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRangeJob_in_rule__Job__Alternatives1535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloatRange_in_rule__Range__Alternatives1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRange_in_rule__Range__Alternatives1584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRangeRange_in_rule__IntRange__Alternatives1616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntRangeValues_in_rule__IntRange__Alternatives1633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResourceReference_in_rule__Resource__Alternatives1665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_rule__Resource__Alternatives1682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutputReference_in_rule__Output__Alternatives1714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_rule__Output__Alternatives1731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rule__PropertyValue__Alternatives1763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__PropertyValue__Alternatives1780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rule__PropertyValue__Alternatives1797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PropertyValue__Alternatives1814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_TFLOAT_in_rule__Float__Alternatives1846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__Float__Alternatives1863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__0__Impl_in_rule__Grana__Group__01893 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_rule__Grana__Group__1_in_rule__Grana__Group__01896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__0_in_rule__Grana__Group__0__Impl1923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__1__Impl_in_rule__Grana__Group__11954 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_rule__Grana__Group__2_in_rule__Grana__Group__11957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_1__0_in_rule__Grana__Group__1__Impl1984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__2__Impl_in_rule__Grana__Group__22015 = new BitSet(new long[]{0x0000000000108000L});
    public static final BitSet FOLLOW_rule__Grana__Group__3_in_rule__Grana__Group__22018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_2__0_in_rule__Grana__Group__2__Impl2045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group__3__Impl_in_rule__Grana__Group__32075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__JobsAssignment_3_in_rule__Grana__Group__3__Impl2104 = new BitSet(new long[]{0x0000000000108002L});
    public static final BitSet FOLLOW_rule__Grana__JobsAssignment_3_in_rule__Grana__Group__3__Impl2116 = new BitSet(new long[]{0x0000000000108002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__0__Impl_in_rule__Grana__Group_0__02157 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__1_in_rule__Grana__Group_0__02160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rule__Grana__Group_0__0__Impl2188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_0__1__Impl_in_rule__Grana__Group_0__12219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__GlobalResourcesAssignment_0_1_in_rule__Grana__Group_0__1__Impl2246 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Grana__Group_1__0__Impl_in_rule__Grana__Group_1__02281 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Grana__Group_1__1_in_rule__Grana__Group_1__02284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_rule__Grana__Group_1__0__Impl2312 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_1__1__Impl_in_rule__Grana__Group_1__12343 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__GloobalOutputsAssignment_1_1_in_rule__Grana__Group_1__1__Impl2370 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__Grana__Group_2__0__Impl_in_rule__Grana__Group_2__02405 = new BitSet(new long[]{0x0000000800000100L});
    public static final BitSet FOLLOW_rule__Grana__Group_2__1_in_rule__Grana__Group_2__02408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_rule__Grana__Group_2__0__Impl2436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Group_2__1__Impl_in_rule__Grana__Group_2__12467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Grana__Alternatives_2_1_in_rule__Grana__Group_2__1__Impl2494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__0__Impl_in_rule__RegularJob__Group__02528 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__1_in_rule__RegularJob__Group__02531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_rule__RegularJob__Group__0__Impl2559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__1__Impl_in_rule__RegularJob__Group__12590 = new BitSet(new long[]{0x0000003000010000L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__2_in_rule__RegularJob__Group__12593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__NameAssignment_1_in_rule__RegularJob__Group__1__Impl2620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__2__Impl_in_rule__RegularJob__Group__22650 = new BitSet(new long[]{0x0000003000010000L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__3_in_rule__RegularJob__Group__22653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__LayoutBeforeAnalysisAssignment_2_in_rule__RegularJob__Group__2__Impl2680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__3__Impl_in_rule__RegularJob__Group__32711 = new BitSet(new long[]{0x0000003000010000L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__4_in_rule__RegularJob__Group__32714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__MeasureExecutionTimeAssignment_3_in_rule__RegularJob__Group__3__Impl2741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__4__Impl_in_rule__RegularJob__Group__42772 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__5_in_rule__RegularJob__Group__42775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__RegularJob__Group__4__Impl2803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__5__Impl_in_rule__RegularJob__Group__52834 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__6_in_rule__RegularJob__Group__52837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__ResourcesAssignment_5_in_rule__RegularJob__Group__5__Impl2866 = new BitSet(new long[]{0x0000000020000022L});
    public static final BitSet FOLLOW_rule__RegularJob__ResourcesAssignment_5_in_rule__RegularJob__Group__5__Impl2878 = new BitSet(new long[]{0x0000000020000022L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__6__Impl_in_rule__RegularJob__Group__62911 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__7_in_rule__RegularJob__Group__62914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__RegularJob__Group__6__Impl2942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__7__Impl_in_rule__RegularJob__Group__72973 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__8_in_rule__RegularJob__Group__72976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__LayoutOptionsAssignment_7_in_rule__RegularJob__Group__7__Impl3005 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__RegularJob__LayoutOptionsAssignment_7_in_rule__RegularJob__Group__7__Impl3017 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__8__Impl_in_rule__RegularJob__Group__83050 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__9_in_rule__RegularJob__Group__83053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__RegularJob__Group__8__Impl3081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__9__Impl_in_rule__RegularJob__Group__93112 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__10_in_rule__RegularJob__Group__93115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__AnalysesAssignment_9_in_rule__RegularJob__Group__9__Impl3144 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__RegularJob__AnalysesAssignment_9_in_rule__RegularJob__Group__9__Impl3156 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__10__Impl_in_rule__RegularJob__Group__103189 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__11_in_rule__RegularJob__Group__103192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__RegularJob__Group__10__Impl3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__Group__11__Impl_in_rule__RegularJob__Group__113251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RegularJob__OutputAssignment_11_in_rule__RegularJob__Group__11__Impl3278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__0__Impl_in_rule__RangeJob__Group__03332 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__1_in_rule__RangeJob__Group__03335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__RangeJob__Group__0__Impl3363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__1__Impl_in_rule__RangeJob__Group__13394 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__2_in_rule__RangeJob__Group__13397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__NameAssignment_1_in_rule__RangeJob__Group__1__Impl3424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__2__Impl_in_rule__RangeJob__Group__23454 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__3_in_rule__RangeJob__Group__23457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__RangeJob__Group__2__Impl3485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__3__Impl_in_rule__RangeJob__Group__33516 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__4_in_rule__RangeJob__Group__33519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__ResourcesAssignment_3_in_rule__RangeJob__Group__3__Impl3548 = new BitSet(new long[]{0x0000000020000022L});
    public static final BitSet FOLLOW_rule__RangeJob__ResourcesAssignment_3_in_rule__RangeJob__Group__3__Impl3560 = new BitSet(new long[]{0x0000000020000022L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__4__Impl_in_rule__RangeJob__Group__43593 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__5_in_rule__RangeJob__Group__43596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__RangeJob__Group__4__Impl3624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__5__Impl_in_rule__RangeJob__Group__53655 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__6_in_rule__RangeJob__Group__53658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__LayoutOptionsAssignment_5_in_rule__RangeJob__Group__5__Impl3687 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__RangeJob__LayoutOptionsAssignment_5_in_rule__RangeJob__Group__5__Impl3699 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__6__Impl_in_rule__RangeJob__Group__63732 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__7_in_rule__RangeJob__Group__63735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__RangeJob__Group__6__Impl3763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__7__Impl_in_rule__RangeJob__Group__73794 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__8_in_rule__RangeJob__Group__73797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__AnalysesAssignment_7_in_rule__RangeJob__Group__7__Impl3826 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__RangeJob__AnalysesAssignment_7_in_rule__RangeJob__Group__7__Impl3838 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__8__Impl_in_rule__RangeJob__Group__83871 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__9_in_rule__RangeJob__Group__83874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__RangeJob__Group__8__Impl3902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__9__Impl_in_rule__RangeJob__Group__93933 = new BitSet(new long[]{0x000000000D000000L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__10_in_rule__RangeJob__Group__93936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__RangeOptionAssignment_9_in_rule__RangeJob__Group__9__Impl3963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__10__Impl_in_rule__RangeJob__Group__103993 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__11_in_rule__RangeJob__Group__103996 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__RangeValuesAssignment_10_in_rule__RangeJob__Group__10__Impl4023 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__11__Impl_in_rule__RangeJob__Group__114053 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__12_in_rule__RangeJob__Group__114056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__RangeJob__Group__11__Impl4084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__12__Impl_in_rule__RangeJob__Group__124115 = new BitSet(new long[]{0x0000000000880000L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__13_in_rule__RangeJob__Group__124118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__RangeAnalysisAssignment_12_in_rule__RangeJob__Group__12__Impl4145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__13__Impl_in_rule__RangeJob__Group__134175 = new BitSet(new long[]{0x0000000000880000L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__14_in_rule__RangeJob__Group__134178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group_13__0_in_rule__RangeJob__Group__13__Impl4205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__14__Impl_in_rule__RangeJob__Group__144236 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__15_in_rule__RangeJob__Group__144239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__RangeJob__Group__14__Impl4267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group__15__Impl_in_rule__RangeJob__Group__154298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__OutputAssignment_15_in_rule__RangeJob__Group__15__Impl4325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group_13__0__Impl_in_rule__RangeJob__Group_13__04387 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__RangeJob__Group_13__1_in_rule__RangeJob__Group_13__04390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__RangeJob__Group_13__0__Impl4418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__Group_13__1__Impl_in_rule__RangeJob__Group_13__14449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__RangeJob__RangeAnalysisComponentAssignment_13_1_in_rule__RangeJob__Group_13__1__Impl4476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FloatRange__Group__0__Impl_in_rule__FloatRange__Group__04510 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_rule__FloatRange__Group__1_in_rule__FloatRange__Group__04513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__FloatRange__Group__0__Impl4541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FloatRange__Group__1__Impl_in_rule__FloatRange__Group__14572 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__FloatRange__Group__2_in_rule__FloatRange__Group__14575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FloatRange__ValuesAssignment_1_in_rule__FloatRange__Group__1__Impl4602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FloatRange__Group__2__Impl_in_rule__FloatRange__Group__24632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FloatRange__Group_2__0_in_rule__FloatRange__Group__2__Impl4659 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_rule__FloatRange__Group_2__0__Impl_in_rule__FloatRange__Group_2__04696 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_rule__FloatRange__Group_2__1_in_rule__FloatRange__Group_2__04699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__FloatRange__Group_2__0__Impl4727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FloatRange__Group_2__1__Impl_in_rule__FloatRange__Group_2__14758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FloatRange__ValuesAssignment_2_1_in_rule__FloatRange__Group_2__1__Impl4785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group__0__Impl_in_rule__IntRangeValues__Group__04819 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group__1_in_rule__IntRangeValues__Group__04822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__IntRangeValues__Group__0__Impl4850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group__1__Impl_in_rule__IntRangeValues__Group__14881 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group__2_in_rule__IntRangeValues__Group__14884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__ValuesAssignment_1_in_rule__IntRangeValues__Group__1__Impl4911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group__2__Impl_in_rule__IntRangeValues__Group__24941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group_2__0_in_rule__IntRangeValues__Group__2__Impl4968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group_2__0__Impl_in_rule__IntRangeValues__Group_2__05004 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group_2__1_in_rule__IntRangeValues__Group_2__05007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__IntRangeValues__Group_2__0__Impl5035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__Group_2__1__Impl_in_rule__IntRangeValues__Group_2__15066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeValues__ValuesAssignment_2_1_in_rule__IntRangeValues__Group_2__1__Impl5093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeRange__Group__0__Impl_in_rule__IntRangeRange__Group__05127 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__IntRangeRange__Group__1_in_rule__IntRangeRange__Group__05130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__IntRangeRange__Group__0__Impl5158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeRange__Group__1__Impl_in_rule__IntRangeRange__Group__15189 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__IntRangeRange__Group__2_in_rule__IntRangeRange__Group__15192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeRange__StartAssignment_1_in_rule__IntRangeRange__Group__1__Impl5219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeRange__Group__2__Impl_in_rule__IntRangeRange__Group__25249 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__IntRangeRange__Group__3_in_rule__IntRangeRange__Group__25252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__IntRangeRange__Group__2__Impl5280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeRange__Group__3__Impl_in_rule__IntRangeRange__Group__35311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__IntRangeRange__EndAssignment_3_in_rule__IntRangeRange__Group__3__Impl5338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__0__Impl_in_rule__ResourceReference__Group__05376 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__1_in_rule__ResourceReference__Group__05379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__ResourceReference__Group__0__Impl5407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__Group__1__Impl_in_rule__ResourceReference__Group__15438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl5467 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__ResourceReference__ResourceRefsAssignment_1_in_rule__ResourceReference__Group__1__Impl5479 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__0__Impl_in_rule__GlobalResourceRef__Group__05516 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__1_in_rule__GlobalResourceRef__Group__05519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__NameAssignment_0_in_rule__GlobalResourceRef__Group__0__Impl5546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__Group__1__Impl_in_rule__GlobalResourceRef__Group__15576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalResourceRef__ResourcesAssignment_1_in_rule__GlobalResourceRef__Group__1__Impl5603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__0__Impl_in_rule__LocalResource__Group__05637 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__1_in_rule__LocalResource__Group__05640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__PathAssignment_0_in_rule__LocalResource__Group__0__Impl5667 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group__1__Impl_in_rule__LocalResource__Group__15697 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__0_in_rule__LocalResource__Group__1__Impl5724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__0__Impl_in_rule__LocalResource__Group_1__05758 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__1_in_rule__LocalResource__Group_1__05761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__LocalResource__Group_1__0__Impl5789 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__Group_1__1__Impl_in_rule__LocalResource__Group_1__15820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__LocalResource__FilterAssignment_1_1_in_rule__LocalResource__Group_1__1__Impl5847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__Group__0__Impl_in_rule__GlobalOutputRef__Group__05881 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__Group__1_in_rule__GlobalOutputRef__Group__05884 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__NameAssignment_0_in_rule__GlobalOutputRef__Group__0__Impl5911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__Group__1__Impl_in_rule__GlobalOutputRef__Group__15941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__GlobalOutputRef__OutputAssignment_1_in_rule__GlobalOutputRef__Group__1__Impl5968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OutputReference__Group__0__Impl_in_rule__OutputReference__Group__06002 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__OutputReference__Group__1_in_rule__OutputReference__Group__06005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__OutputReference__Group__0__Impl6033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OutputReference__Group__1__Impl_in_rule__OutputReference__Group__16064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__OutputReference__OutputRefAssignment_1_in_rule__OutputReference__Group__1__Impl6091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__0__Impl_in_rule__KIdentifier__Group__06125 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__1_in_rule__KIdentifier__Group__06128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__1__Impl_in_rule__KIdentifier__Group__16186 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__2_in_rule__KIdentifier__Group__16189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__IdAssignment_1_in_rule__KIdentifier__Group__1__Impl6216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__2__Impl_in_rule__KIdentifier__Group__26246 = new BitSet(new long[]{0x0000000100000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__3_in_rule__KIdentifier__Group__26249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__KIdentifier__Group__2__Impl6277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__3__Impl_in_rule__KIdentifier__Group__36308 = new BitSet(new long[]{0x0000000100000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__4_in_rule__KIdentifier__Group__36311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0_in_rule__KIdentifier__Group__3__Impl6338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group__4__Impl_in_rule__KIdentifier__Group__46369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_rule__KIdentifier__Group__4__Impl6397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__0__Impl_in_rule__KIdentifier__Group_3__06438 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1_in_rule__KIdentifier__Group_3__06441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_0_in_rule__KIdentifier__Group_3__0__Impl6468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__Group_3__1__Impl_in_rule__KIdentifier__Group_3__16498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__KIdentifier__PersistentEntriesAssignment_3_1_in_rule__KIdentifier__Group_3__1__Impl6525 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__06560 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__06563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__KeyAssignment_0_in_rule__PersistentEntry__Group__0__Impl6590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__16620 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__16623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_rule__PersistentEntry__Group__1__Impl6651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__26682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PersistentEntry__ValueAssignment_2_in_rule__PersistentEntry__Group__2__Impl6709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__0__Impl_in_rule__QualifiedID__Group__06745 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1_in_rule__QualifiedID__Group__06748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group__0__Impl6775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group__1__Impl_in_rule__QualifiedID__Group__16804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0_in_rule__QualifiedID__Group__1__Impl6831 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__0__Impl_in_rule__QualifiedID__Group_1__06866 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1_in_rule__QualifiedID__Group_1__06869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_rule__QualifiedID__Group_1__0__Impl6897 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__QualifiedID__Group_1__1__Impl_in_rule__QualifiedID__Group_1__16928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__QualifiedID__Group_1__1__Impl6955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalResourceRef_in_rule__Grana__GlobalResourcesAssignment_0_16993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalOutputRef_in_rule__Grana__GloobalOutputsAssignment_1_17024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_rule__Grana__ExecuteAllAssignment_2_1_07060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Grana__ExecuteAssignment_2_1_17103 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleJob_in_rule__Grana__JobsAssignment_37138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__RegularJob__NameAssignment_17169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_rule__RegularJob__LayoutBeforeAnalysisAssignment_27205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_rule__RegularJob__MeasureExecutionTimeAssignment_37249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_rule__RegularJob__ResourcesAssignment_57288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_rule__RegularJob__LayoutOptionsAssignment_77319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_rule__RegularJob__AnalysesAssignment_97350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutput_in_rule__RegularJob__OutputAssignment_117381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__RangeJob__NameAssignment_17412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleResource_in_rule__RangeJob__ResourcesAssignment_37443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleKIdentifier_in_rule__RangeJob__LayoutOptionsAssignment_57474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_rule__RangeJob__AnalysesAssignment_77505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__RangeJob__RangeOptionAssignment_97536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRange_in_rule__RangeJob__RangeValuesAssignment_107567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAnalysis_in_rule__RangeJob__RangeAnalysisAssignment_127598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__RangeJob__RangeAnalysisComponentAssignment_13_17629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOutput_in_rule__RangeJob__OutputAssignment_157660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rule__FloatRange__ValuesAssignment_17691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFloat_in_rule__FloatRange__ValuesAssignment_2_17722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__IntRangeValues__ValuesAssignment_17753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__IntRangeValues__ValuesAssignment_2_17784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__IntRangeRange__StartAssignment_17815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NATURAL_in_rule__IntRangeRange__EndAssignment_37846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__ResourceReference__ResourceRefsAssignment_17881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__GlobalResourceRef__NameAssignment_07916 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalResource_in_rule__GlobalResourceRef__ResourcesAssignment_17947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__LocalResource__PathAssignment_07978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__LocalResource__FilterAssignment_1_18009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__GlobalOutputRef__NameAssignment_08040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLocalOutput_in_rule__GlobalOutputRef__OutputAssignment_18071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__OutputReference__OutputRefAssignment_18106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__LocalOutput__PathAssignment8141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__Analysis__NameAssignment8172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__KIdentifier__IdAssignment_18203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_08234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KIdentifier__PersistentEntriesAssignment_3_18265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_rule__PersistentEntry__KeyAssignment_08296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePropertyValue_in_rule__PersistentEntry__ValueAssignment_28327 = new BitSet(new long[]{0x0000000000000002L});

}