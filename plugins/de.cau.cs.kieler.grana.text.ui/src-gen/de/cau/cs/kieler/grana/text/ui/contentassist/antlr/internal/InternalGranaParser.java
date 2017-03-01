package de.cau.cs.kieler.grana.text.ui.contentassist.antlr.internal; 

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
import de.cau.cs.kieler.grana.text.services.GranaGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGranaParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_SIGNED_INT", "RULE_FLOAT", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'true'", "'false'", "'csv'", "'json'", "'globalResources'", "'globalOutputs'", "'execute'", "'job'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'comparejob'", "'rangejob'", "'rangeoption'", "'rangeanalysis'", "'component'", "'rangeanalyses'", "'floatvalues'", "','", "'intvalues'", "'intrange'", "'to'", "'enumvalues'", "'ref'", "'filter'", "'{'", "'}'", "'node'", "'label'", "':'", "'port'", "'layout'", "'['", "']'", "'position'", "'size'", "'edge'", "'->'", "'incoming'", "'outgoing'", "'start'", "'end'", "'bends'", "'|'", "'section'", "'.'", "'parallel'", "'all'", "'layoutBeforeAnalysis'", "'measureExecutionTime'", "'recurse'"
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
    public static final int RULE_ID=7;
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
    public static final int RULE_STRING=4;
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
    // InternalGrana.g:60:1: entryRuleGrana : ruleGrana EOF ;
    public final void entryRuleGrana() throws RecognitionException {
        try {
            // InternalGrana.g:61:1: ( ruleGrana EOF )
            // InternalGrana.g:62:1: ruleGrana EOF
            {
             before(grammarAccess.getGranaRule()); 
            pushFollow(FOLLOW_1);
            ruleGrana();

            state._fsp--;

             after(grammarAccess.getGranaRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:69:1: ruleGrana : ( ( rule__Grana__Group__0 ) ) ;
    public final void ruleGrana() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:73:2: ( ( ( rule__Grana__Group__0 ) ) )
            // InternalGrana.g:74:1: ( ( rule__Grana__Group__0 ) )
            {
            // InternalGrana.g:74:1: ( ( rule__Grana__Group__0 ) )
            // InternalGrana.g:75:1: ( rule__Grana__Group__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup()); 
            // InternalGrana.g:76:1: ( rule__Grana__Group__0 )
            // InternalGrana.g:76:2: rule__Grana__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:88:1: entryRuleJob : ruleJob EOF ;
    public final void entryRuleJob() throws RecognitionException {
        try {
            // InternalGrana.g:89:1: ( ruleJob EOF )
            // InternalGrana.g:90:1: ruleJob EOF
            {
             before(grammarAccess.getJobRule()); 
            pushFollow(FOLLOW_1);
            ruleJob();

            state._fsp--;

             after(grammarAccess.getJobRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:97:1: ruleJob : ( ( rule__Job__Alternatives ) ) ;
    public final void ruleJob() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:101:2: ( ( ( rule__Job__Alternatives ) ) )
            // InternalGrana.g:102:1: ( ( rule__Job__Alternatives ) )
            {
            // InternalGrana.g:102:1: ( ( rule__Job__Alternatives ) )
            // InternalGrana.g:103:1: ( rule__Job__Alternatives )
            {
             before(grammarAccess.getJobAccess().getAlternatives()); 
            // InternalGrana.g:104:1: ( rule__Job__Alternatives )
            // InternalGrana.g:104:2: rule__Job__Alternatives
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:116:1: entryRuleRegularJob : ruleRegularJob EOF ;
    public final void entryRuleRegularJob() throws RecognitionException {
        try {
            // InternalGrana.g:117:1: ( ruleRegularJob EOF )
            // InternalGrana.g:118:1: ruleRegularJob EOF
            {
             before(grammarAccess.getRegularJobRule()); 
            pushFollow(FOLLOW_1);
            ruleRegularJob();

            state._fsp--;

             after(grammarAccess.getRegularJobRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:125:1: ruleRegularJob : ( ( rule__RegularJob__Group__0 ) ) ;
    public final void ruleRegularJob() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:129:2: ( ( ( rule__RegularJob__Group__0 ) ) )
            // InternalGrana.g:130:1: ( ( rule__RegularJob__Group__0 ) )
            {
            // InternalGrana.g:130:1: ( ( rule__RegularJob__Group__0 ) )
            // InternalGrana.g:131:1: ( rule__RegularJob__Group__0 )
            {
             before(grammarAccess.getRegularJobAccess().getGroup()); 
            // InternalGrana.g:132:1: ( rule__RegularJob__Group__0 )
            // InternalGrana.g:132:2: rule__RegularJob__Group__0
            {
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "entryRuleCompareJob"
    // InternalGrana.g:144:1: entryRuleCompareJob : ruleCompareJob EOF ;
    public final void entryRuleCompareJob() throws RecognitionException {
        try {
            // InternalGrana.g:145:1: ( ruleCompareJob EOF )
            // InternalGrana.g:146:1: ruleCompareJob EOF
            {
             before(grammarAccess.getCompareJobRule()); 
            pushFollow(FOLLOW_1);
            ruleCompareJob();

            state._fsp--;

             after(grammarAccess.getCompareJobRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleCompareJob"


    // $ANTLR start "ruleCompareJob"
    // InternalGrana.g:153:1: ruleCompareJob : ( ( rule__CompareJob__Group__0 ) ) ;
    public final void ruleCompareJob() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:157:2: ( ( ( rule__CompareJob__Group__0 ) ) )
            // InternalGrana.g:158:1: ( ( rule__CompareJob__Group__0 ) )
            {
            // InternalGrana.g:158:1: ( ( rule__CompareJob__Group__0 ) )
            // InternalGrana.g:159:1: ( rule__CompareJob__Group__0 )
            {
             before(grammarAccess.getCompareJobAccess().getGroup()); 
            // InternalGrana.g:160:1: ( rule__CompareJob__Group__0 )
            // InternalGrana.g:160:2: rule__CompareJob__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getGroup()); 

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
    // $ANTLR end "ruleCompareJob"


    // $ANTLR start "entryRuleRangeJob"
    // InternalGrana.g:172:1: entryRuleRangeJob : ruleRangeJob EOF ;
    public final void entryRuleRangeJob() throws RecognitionException {
        try {
            // InternalGrana.g:173:1: ( ruleRangeJob EOF )
            // InternalGrana.g:174:1: ruleRangeJob EOF
            {
             before(grammarAccess.getRangeJobRule()); 
            pushFollow(FOLLOW_1);
            ruleRangeJob();

            state._fsp--;

             after(grammarAccess.getRangeJobRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:181:1: ruleRangeJob : ( ( rule__RangeJob__Group__0 ) ) ;
    public final void ruleRangeJob() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:185:2: ( ( ( rule__RangeJob__Group__0 ) ) )
            // InternalGrana.g:186:1: ( ( rule__RangeJob__Group__0 ) )
            {
            // InternalGrana.g:186:1: ( ( rule__RangeJob__Group__0 ) )
            // InternalGrana.g:187:1: ( rule__RangeJob__Group__0 )
            {
             before(grammarAccess.getRangeJobAccess().getGroup()); 
            // InternalGrana.g:188:1: ( rule__RangeJob__Group__0 )
            // InternalGrana.g:188:2: rule__RangeJob__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:200:1: entryRuleRange : ruleRange EOF ;
    public final void entryRuleRange() throws RecognitionException {
        try {
            // InternalGrana.g:201:1: ( ruleRange EOF )
            // InternalGrana.g:202:1: ruleRange EOF
            {
             before(grammarAccess.getRangeRule()); 
            pushFollow(FOLLOW_1);
            ruleRange();

            state._fsp--;

             after(grammarAccess.getRangeRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:209:1: ruleRange : ( ( rule__Range__Alternatives ) ) ;
    public final void ruleRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:213:2: ( ( ( rule__Range__Alternatives ) ) )
            // InternalGrana.g:214:1: ( ( rule__Range__Alternatives ) )
            {
            // InternalGrana.g:214:1: ( ( rule__Range__Alternatives ) )
            // InternalGrana.g:215:1: ( rule__Range__Alternatives )
            {
             before(grammarAccess.getRangeAccess().getAlternatives()); 
            // InternalGrana.g:216:1: ( rule__Range__Alternatives )
            // InternalGrana.g:216:2: rule__Range__Alternatives
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:228:1: entryRuleFloatRange : ruleFloatRange EOF ;
    public final void entryRuleFloatRange() throws RecognitionException {
        try {
            // InternalGrana.g:229:1: ( ruleFloatRange EOF )
            // InternalGrana.g:230:1: ruleFloatRange EOF
            {
             before(grammarAccess.getFloatRangeRule()); 
            pushFollow(FOLLOW_1);
            ruleFloatRange();

            state._fsp--;

             after(grammarAccess.getFloatRangeRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:237:1: ruleFloatRange : ( ( rule__FloatRange__Group__0 ) ) ;
    public final void ruleFloatRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:241:2: ( ( ( rule__FloatRange__Group__0 ) ) )
            // InternalGrana.g:242:1: ( ( rule__FloatRange__Group__0 ) )
            {
            // InternalGrana.g:242:1: ( ( rule__FloatRange__Group__0 ) )
            // InternalGrana.g:243:1: ( rule__FloatRange__Group__0 )
            {
             before(grammarAccess.getFloatRangeAccess().getGroup()); 
            // InternalGrana.g:244:1: ( rule__FloatRange__Group__0 )
            // InternalGrana.g:244:2: rule__FloatRange__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:256:1: entryRuleIntRange : ruleIntRange EOF ;
    public final void entryRuleIntRange() throws RecognitionException {
        try {
            // InternalGrana.g:257:1: ( ruleIntRange EOF )
            // InternalGrana.g:258:1: ruleIntRange EOF
            {
             before(grammarAccess.getIntRangeRule()); 
            pushFollow(FOLLOW_1);
            ruleIntRange();

            state._fsp--;

             after(grammarAccess.getIntRangeRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:265:1: ruleIntRange : ( ( rule__IntRange__Alternatives ) ) ;
    public final void ruleIntRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:269:2: ( ( ( rule__IntRange__Alternatives ) ) )
            // InternalGrana.g:270:1: ( ( rule__IntRange__Alternatives ) )
            {
            // InternalGrana.g:270:1: ( ( rule__IntRange__Alternatives ) )
            // InternalGrana.g:271:1: ( rule__IntRange__Alternatives )
            {
             before(grammarAccess.getIntRangeAccess().getAlternatives()); 
            // InternalGrana.g:272:1: ( rule__IntRange__Alternatives )
            // InternalGrana.g:272:2: rule__IntRange__Alternatives
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:284:1: entryRuleIntRangeValues : ruleIntRangeValues EOF ;
    public final void entryRuleIntRangeValues() throws RecognitionException {
        try {
            // InternalGrana.g:285:1: ( ruleIntRangeValues EOF )
            // InternalGrana.g:286:1: ruleIntRangeValues EOF
            {
             before(grammarAccess.getIntRangeValuesRule()); 
            pushFollow(FOLLOW_1);
            ruleIntRangeValues();

            state._fsp--;

             after(grammarAccess.getIntRangeValuesRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:293:1: ruleIntRangeValues : ( ( rule__IntRangeValues__Group__0 ) ) ;
    public final void ruleIntRangeValues() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:297:2: ( ( ( rule__IntRangeValues__Group__0 ) ) )
            // InternalGrana.g:298:1: ( ( rule__IntRangeValues__Group__0 ) )
            {
            // InternalGrana.g:298:1: ( ( rule__IntRangeValues__Group__0 ) )
            // InternalGrana.g:299:1: ( rule__IntRangeValues__Group__0 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup()); 
            // InternalGrana.g:300:1: ( rule__IntRangeValues__Group__0 )
            // InternalGrana.g:300:2: rule__IntRangeValues__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:312:1: entryRuleIntRangeRange : ruleIntRangeRange EOF ;
    public final void entryRuleIntRangeRange() throws RecognitionException {
        try {
            // InternalGrana.g:313:1: ( ruleIntRangeRange EOF )
            // InternalGrana.g:314:1: ruleIntRangeRange EOF
            {
             before(grammarAccess.getIntRangeRangeRule()); 
            pushFollow(FOLLOW_1);
            ruleIntRangeRange();

            state._fsp--;

             after(grammarAccess.getIntRangeRangeRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:321:1: ruleIntRangeRange : ( ( rule__IntRangeRange__Group__0 ) ) ;
    public final void ruleIntRangeRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:325:2: ( ( ( rule__IntRangeRange__Group__0 ) ) )
            // InternalGrana.g:326:1: ( ( rule__IntRangeRange__Group__0 ) )
            {
            // InternalGrana.g:326:1: ( ( rule__IntRangeRange__Group__0 ) )
            // InternalGrana.g:327:1: ( rule__IntRangeRange__Group__0 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getGroup()); 
            // InternalGrana.g:328:1: ( rule__IntRangeRange__Group__0 )
            // InternalGrana.g:328:2: rule__IntRangeRange__Group__0
            {
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "entryRuleEnumRange"
    // InternalGrana.g:340:1: entryRuleEnumRange : ruleEnumRange EOF ;
    public final void entryRuleEnumRange() throws RecognitionException {
        try {
            // InternalGrana.g:341:1: ( ruleEnumRange EOF )
            // InternalGrana.g:342:1: ruleEnumRange EOF
            {
             before(grammarAccess.getEnumRangeRule()); 
            pushFollow(FOLLOW_1);
            ruleEnumRange();

            state._fsp--;

             after(grammarAccess.getEnumRangeRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleEnumRange"


    // $ANTLR start "ruleEnumRange"
    // InternalGrana.g:349:1: ruleEnumRange : ( ( rule__EnumRange__Group__0 ) ) ;
    public final void ruleEnumRange() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:353:2: ( ( ( rule__EnumRange__Group__0 ) ) )
            // InternalGrana.g:354:1: ( ( rule__EnumRange__Group__0 ) )
            {
            // InternalGrana.g:354:1: ( ( rule__EnumRange__Group__0 ) )
            // InternalGrana.g:355:1: ( rule__EnumRange__Group__0 )
            {
             before(grammarAccess.getEnumRangeAccess().getGroup()); 
            // InternalGrana.g:356:1: ( rule__EnumRange__Group__0 )
            // InternalGrana.g:356:2: rule__EnumRange__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EnumRange__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEnumRangeAccess().getGroup()); 

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
    // $ANTLR end "ruleEnumRange"


    // $ANTLR start "entryRuleResource"
    // InternalGrana.g:368:1: entryRuleResource : ruleResource EOF ;
    public final void entryRuleResource() throws RecognitionException {
        try {
            // InternalGrana.g:369:1: ( ruleResource EOF )
            // InternalGrana.g:370:1: ruleResource EOF
            {
             before(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_1);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getResourceRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:377:1: ruleResource : ( ( rule__Resource__Alternatives ) ) ;
    public final void ruleResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:381:2: ( ( ( rule__Resource__Alternatives ) ) )
            // InternalGrana.g:382:1: ( ( rule__Resource__Alternatives ) )
            {
            // InternalGrana.g:382:1: ( ( rule__Resource__Alternatives ) )
            // InternalGrana.g:383:1: ( rule__Resource__Alternatives )
            {
             before(grammarAccess.getResourceAccess().getAlternatives()); 
            // InternalGrana.g:384:1: ( rule__Resource__Alternatives )
            // InternalGrana.g:384:2: rule__Resource__Alternatives
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:396:1: entryRuleResourceReference : ruleResourceReference EOF ;
    public final void entryRuleResourceReference() throws RecognitionException {
        try {
            // InternalGrana.g:397:1: ( ruleResourceReference EOF )
            // InternalGrana.g:398:1: ruleResourceReference EOF
            {
             before(grammarAccess.getResourceReferenceRule()); 
            pushFollow(FOLLOW_1);
            ruleResourceReference();

            state._fsp--;

             after(grammarAccess.getResourceReferenceRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:405:1: ruleResourceReference : ( ( rule__ResourceReference__Group__0 ) ) ;
    public final void ruleResourceReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:409:2: ( ( ( rule__ResourceReference__Group__0 ) ) )
            // InternalGrana.g:410:1: ( ( rule__ResourceReference__Group__0 ) )
            {
            // InternalGrana.g:410:1: ( ( rule__ResourceReference__Group__0 ) )
            // InternalGrana.g:411:1: ( rule__ResourceReference__Group__0 )
            {
             before(grammarAccess.getResourceReferenceAccess().getGroup()); 
            // InternalGrana.g:412:1: ( rule__ResourceReference__Group__0 )
            // InternalGrana.g:412:2: rule__ResourceReference__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:424:1: entryRuleGlobalResourceRef : ruleGlobalResourceRef EOF ;
    public final void entryRuleGlobalResourceRef() throws RecognitionException {
        try {
            // InternalGrana.g:425:1: ( ruleGlobalResourceRef EOF )
            // InternalGrana.g:426:1: ruleGlobalResourceRef EOF
            {
             before(grammarAccess.getGlobalResourceRefRule()); 
            pushFollow(FOLLOW_1);
            ruleGlobalResourceRef();

            state._fsp--;

             after(grammarAccess.getGlobalResourceRefRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:433:1: ruleGlobalResourceRef : ( ( rule__GlobalResourceRef__Group__0 ) ) ;
    public final void ruleGlobalResourceRef() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:437:2: ( ( ( rule__GlobalResourceRef__Group__0 ) ) )
            // InternalGrana.g:438:1: ( ( rule__GlobalResourceRef__Group__0 ) )
            {
            // InternalGrana.g:438:1: ( ( rule__GlobalResourceRef__Group__0 ) )
            // InternalGrana.g:439:1: ( rule__GlobalResourceRef__Group__0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getGroup()); 
            // InternalGrana.g:440:1: ( rule__GlobalResourceRef__Group__0 )
            // InternalGrana.g:440:2: rule__GlobalResourceRef__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:452:1: entryRuleLocalResource : ruleLocalResource EOF ;
    public final void entryRuleLocalResource() throws RecognitionException {
        try {
            // InternalGrana.g:453:1: ( ruleLocalResource EOF )
            // InternalGrana.g:454:1: ruleLocalResource EOF
            {
             before(grammarAccess.getLocalResourceRule()); 
            pushFollow(FOLLOW_1);
            ruleLocalResource();

            state._fsp--;

             after(grammarAccess.getLocalResourceRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:461:1: ruleLocalResource : ( ( rule__LocalResource__Group__0 ) ) ;
    public final void ruleLocalResource() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:465:2: ( ( ( rule__LocalResource__Group__0 ) ) )
            // InternalGrana.g:466:1: ( ( rule__LocalResource__Group__0 ) )
            {
            // InternalGrana.g:466:1: ( ( rule__LocalResource__Group__0 ) )
            // InternalGrana.g:467:1: ( rule__LocalResource__Group__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup()); 
            // InternalGrana.g:468:1: ( rule__LocalResource__Group__0 )
            // InternalGrana.g:468:2: rule__LocalResource__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:480:1: entryRuleOutput : ruleOutput EOF ;
    public final void entryRuleOutput() throws RecognitionException {
        try {
            // InternalGrana.g:481:1: ( ruleOutput EOF )
            // InternalGrana.g:482:1: ruleOutput EOF
            {
             before(grammarAccess.getOutputRule()); 
            pushFollow(FOLLOW_1);
            ruleOutput();

            state._fsp--;

             after(grammarAccess.getOutputRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:489:1: ruleOutput : ( ( rule__Output__Alternatives ) ) ;
    public final void ruleOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:493:2: ( ( ( rule__Output__Alternatives ) ) )
            // InternalGrana.g:494:1: ( ( rule__Output__Alternatives ) )
            {
            // InternalGrana.g:494:1: ( ( rule__Output__Alternatives ) )
            // InternalGrana.g:495:1: ( rule__Output__Alternatives )
            {
             before(grammarAccess.getOutputAccess().getAlternatives()); 
            // InternalGrana.g:496:1: ( rule__Output__Alternatives )
            // InternalGrana.g:496:2: rule__Output__Alternatives
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:508:1: entryRuleGlobalOutputRef : ruleGlobalOutputRef EOF ;
    public final void entryRuleGlobalOutputRef() throws RecognitionException {
        try {
            // InternalGrana.g:509:1: ( ruleGlobalOutputRef EOF )
            // InternalGrana.g:510:1: ruleGlobalOutputRef EOF
            {
             before(grammarAccess.getGlobalOutputRefRule()); 
            pushFollow(FOLLOW_1);
            ruleGlobalOutputRef();

            state._fsp--;

             after(grammarAccess.getGlobalOutputRefRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:517:1: ruleGlobalOutputRef : ( ( rule__GlobalOutputRef__Group__0 ) ) ;
    public final void ruleGlobalOutputRef() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:521:2: ( ( ( rule__GlobalOutputRef__Group__0 ) ) )
            // InternalGrana.g:522:1: ( ( rule__GlobalOutputRef__Group__0 ) )
            {
            // InternalGrana.g:522:1: ( ( rule__GlobalOutputRef__Group__0 ) )
            // InternalGrana.g:523:1: ( rule__GlobalOutputRef__Group__0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getGroup()); 
            // InternalGrana.g:524:1: ( rule__GlobalOutputRef__Group__0 )
            // InternalGrana.g:524:2: rule__GlobalOutputRef__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:536:1: entryRuleOutputReference : ruleOutputReference EOF ;
    public final void entryRuleOutputReference() throws RecognitionException {
        try {
            // InternalGrana.g:537:1: ( ruleOutputReference EOF )
            // InternalGrana.g:538:1: ruleOutputReference EOF
            {
             before(grammarAccess.getOutputReferenceRule()); 
            pushFollow(FOLLOW_1);
            ruleOutputReference();

            state._fsp--;

             after(grammarAccess.getOutputReferenceRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:545:1: ruleOutputReference : ( ( rule__OutputReference__Group__0 ) ) ;
    public final void ruleOutputReference() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:549:2: ( ( ( rule__OutputReference__Group__0 ) ) )
            // InternalGrana.g:550:1: ( ( rule__OutputReference__Group__0 ) )
            {
            // InternalGrana.g:550:1: ( ( rule__OutputReference__Group__0 ) )
            // InternalGrana.g:551:1: ( rule__OutputReference__Group__0 )
            {
             before(grammarAccess.getOutputReferenceAccess().getGroup()); 
            // InternalGrana.g:552:1: ( rule__OutputReference__Group__0 )
            // InternalGrana.g:552:2: rule__OutputReference__Group__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:564:1: entryRuleLocalOutput : ruleLocalOutput EOF ;
    public final void entryRuleLocalOutput() throws RecognitionException {
        try {
            // InternalGrana.g:565:1: ( ruleLocalOutput EOF )
            // InternalGrana.g:566:1: ruleLocalOutput EOF
            {
             before(grammarAccess.getLocalOutputRule()); 
            pushFollow(FOLLOW_1);
            ruleLocalOutput();

            state._fsp--;

             after(grammarAccess.getLocalOutputRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:573:1: ruleLocalOutput : ( ( rule__LocalOutput__PathAssignment ) ) ;
    public final void ruleLocalOutput() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:577:2: ( ( ( rule__LocalOutput__PathAssignment ) ) )
            // InternalGrana.g:578:1: ( ( rule__LocalOutput__PathAssignment ) )
            {
            // InternalGrana.g:578:1: ( ( rule__LocalOutput__PathAssignment ) )
            // InternalGrana.g:579:1: ( rule__LocalOutput__PathAssignment )
            {
             before(grammarAccess.getLocalOutputAccess().getPathAssignment()); 
            // InternalGrana.g:580:1: ( rule__LocalOutput__PathAssignment )
            // InternalGrana.g:580:2: rule__LocalOutput__PathAssignment
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:592:1: entryRuleAnalysis : ruleAnalysis EOF ;
    public final void entryRuleAnalysis() throws RecognitionException {
        try {
            // InternalGrana.g:593:1: ( ruleAnalysis EOF )
            // InternalGrana.g:594:1: ruleAnalysis EOF
            {
             before(grammarAccess.getAnalysisRule()); 
            pushFollow(FOLLOW_1);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getAnalysisRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // InternalGrana.g:601:1: ruleAnalysis : ( ( rule__Analysis__NameAssignment ) ) ;
    public final void ruleAnalysis() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:605:2: ( ( ( rule__Analysis__NameAssignment ) ) )
            // InternalGrana.g:606:1: ( ( rule__Analysis__NameAssignment ) )
            {
            // InternalGrana.g:606:1: ( ( rule__Analysis__NameAssignment ) )
            // InternalGrana.g:607:1: ( rule__Analysis__NameAssignment )
            {
             before(grammarAccess.getAnalysisAccess().getNameAssignment()); 
            // InternalGrana.g:608:1: ( rule__Analysis__NameAssignment )
            // InternalGrana.g:608:2: rule__Analysis__NameAssignment
            {
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "entryRuleLayoutConfig"
    // InternalGrana.g:620:1: entryRuleLayoutConfig : ruleLayoutConfig EOF ;
    public final void entryRuleLayoutConfig() throws RecognitionException {
        try {
            // InternalGrana.g:621:1: ( ruleLayoutConfig EOF )
            // InternalGrana.g:622:1: ruleLayoutConfig EOF
            {
             before(grammarAccess.getLayoutConfigRule()); 
            pushFollow(FOLLOW_1);
            ruleLayoutConfig();

            state._fsp--;

             after(grammarAccess.getLayoutConfigRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleLayoutConfig"


    // $ANTLR start "ruleLayoutConfig"
    // InternalGrana.g:629:1: ruleLayoutConfig : ( ( rule__LayoutConfig__Group__0 ) ) ;
    public final void ruleLayoutConfig() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:633:2: ( ( ( rule__LayoutConfig__Group__0 ) ) )
            // InternalGrana.g:634:1: ( ( rule__LayoutConfig__Group__0 ) )
            {
            // InternalGrana.g:634:1: ( ( rule__LayoutConfig__Group__0 ) )
            // InternalGrana.g:635:1: ( rule__LayoutConfig__Group__0 )
            {
             before(grammarAccess.getLayoutConfigAccess().getGroup()); 
            // InternalGrana.g:636:1: ( rule__LayoutConfig__Group__0 )
            // InternalGrana.g:636:2: rule__LayoutConfig__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__LayoutConfig__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getLayoutConfigAccess().getGroup()); 

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
    // $ANTLR end "ruleLayoutConfig"


    // $ANTLR start "entryRuleElkNode"
    // InternalGrana.g:650:1: entryRuleElkNode : ruleElkNode EOF ;
    public final void entryRuleElkNode() throws RecognitionException {
        try {
            // InternalGrana.g:651:1: ( ruleElkNode EOF )
            // InternalGrana.g:652:1: ruleElkNode EOF
            {
             before(grammarAccess.getElkNodeRule()); 
            pushFollow(FOLLOW_1);
            ruleElkNode();

            state._fsp--;

             after(grammarAccess.getElkNodeRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkNode"


    // $ANTLR start "ruleElkNode"
    // InternalGrana.g:659:1: ruleElkNode : ( ( rule__ElkNode__Group__0 ) ) ;
    public final void ruleElkNode() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:663:2: ( ( ( rule__ElkNode__Group__0 ) ) )
            // InternalGrana.g:664:1: ( ( rule__ElkNode__Group__0 ) )
            {
            // InternalGrana.g:664:1: ( ( rule__ElkNode__Group__0 ) )
            // InternalGrana.g:665:1: ( rule__ElkNode__Group__0 )
            {
             before(grammarAccess.getElkNodeAccess().getGroup()); 
            // InternalGrana.g:666:1: ( rule__ElkNode__Group__0 )
            // InternalGrana.g:666:2: rule__ElkNode__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkNode__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkNodeAccess().getGroup()); 

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
    // $ANTLR end "ruleElkNode"


    // $ANTLR start "entryRuleElkLabel"
    // InternalGrana.g:678:1: entryRuleElkLabel : ruleElkLabel EOF ;
    public final void entryRuleElkLabel() throws RecognitionException {
        try {
            // InternalGrana.g:679:1: ( ruleElkLabel EOF )
            // InternalGrana.g:680:1: ruleElkLabel EOF
            {
             before(grammarAccess.getElkLabelRule()); 
            pushFollow(FOLLOW_1);
            ruleElkLabel();

            state._fsp--;

             after(grammarAccess.getElkLabelRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkLabel"


    // $ANTLR start "ruleElkLabel"
    // InternalGrana.g:687:1: ruleElkLabel : ( ( rule__ElkLabel__Group__0 ) ) ;
    public final void ruleElkLabel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:691:2: ( ( ( rule__ElkLabel__Group__0 ) ) )
            // InternalGrana.g:692:1: ( ( rule__ElkLabel__Group__0 ) )
            {
            // InternalGrana.g:692:1: ( ( rule__ElkLabel__Group__0 ) )
            // InternalGrana.g:693:1: ( rule__ElkLabel__Group__0 )
            {
             before(grammarAccess.getElkLabelAccess().getGroup()); 
            // InternalGrana.g:694:1: ( rule__ElkLabel__Group__0 )
            // InternalGrana.g:694:2: rule__ElkLabel__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkLabelAccess().getGroup()); 

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
    // $ANTLR end "ruleElkLabel"


    // $ANTLR start "entryRuleElkPort"
    // InternalGrana.g:706:1: entryRuleElkPort : ruleElkPort EOF ;
    public final void entryRuleElkPort() throws RecognitionException {
        try {
            // InternalGrana.g:707:1: ( ruleElkPort EOF )
            // InternalGrana.g:708:1: ruleElkPort EOF
            {
             before(grammarAccess.getElkPortRule()); 
            pushFollow(FOLLOW_1);
            ruleElkPort();

            state._fsp--;

             after(grammarAccess.getElkPortRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkPort"


    // $ANTLR start "ruleElkPort"
    // InternalGrana.g:715:1: ruleElkPort : ( ( rule__ElkPort__Group__0 ) ) ;
    public final void ruleElkPort() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:719:2: ( ( ( rule__ElkPort__Group__0 ) ) )
            // InternalGrana.g:720:1: ( ( rule__ElkPort__Group__0 ) )
            {
            // InternalGrana.g:720:1: ( ( rule__ElkPort__Group__0 ) )
            // InternalGrana.g:721:1: ( rule__ElkPort__Group__0 )
            {
             before(grammarAccess.getElkPortAccess().getGroup()); 
            // InternalGrana.g:722:1: ( rule__ElkPort__Group__0 )
            // InternalGrana.g:722:2: rule__ElkPort__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkPort__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkPortAccess().getGroup()); 

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
    // $ANTLR end "ruleElkPort"


    // $ANTLR start "ruleShapeLayout"
    // InternalGrana.g:735:1: ruleShapeLayout : ( ( rule__ShapeLayout__Group__0 ) ) ;
    public final void ruleShapeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:739:2: ( ( ( rule__ShapeLayout__Group__0 ) ) )
            // InternalGrana.g:740:1: ( ( rule__ShapeLayout__Group__0 ) )
            {
            // InternalGrana.g:740:1: ( ( rule__ShapeLayout__Group__0 ) )
            // InternalGrana.g:741:1: ( rule__ShapeLayout__Group__0 )
            {
             before(grammarAccess.getShapeLayoutAccess().getGroup()); 
            // InternalGrana.g:742:1: ( rule__ShapeLayout__Group__0 )
            // InternalGrana.g:742:2: rule__ShapeLayout__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getGroup()); 

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
    // $ANTLR end "ruleShapeLayout"


    // $ANTLR start "entryRuleElkEdge"
    // InternalGrana.g:754:1: entryRuleElkEdge : ruleElkEdge EOF ;
    public final void entryRuleElkEdge() throws RecognitionException {
        try {
            // InternalGrana.g:755:1: ( ruleElkEdge EOF )
            // InternalGrana.g:756:1: ruleElkEdge EOF
            {
             before(grammarAccess.getElkEdgeRule()); 
            pushFollow(FOLLOW_1);
            ruleElkEdge();

            state._fsp--;

             after(grammarAccess.getElkEdgeRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkEdge"


    // $ANTLR start "ruleElkEdge"
    // InternalGrana.g:763:1: ruleElkEdge : ( ( rule__ElkEdge__Group__0 ) ) ;
    public final void ruleElkEdge() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:767:2: ( ( ( rule__ElkEdge__Group__0 ) ) )
            // InternalGrana.g:768:1: ( ( rule__ElkEdge__Group__0 ) )
            {
            // InternalGrana.g:768:1: ( ( rule__ElkEdge__Group__0 ) )
            // InternalGrana.g:769:1: ( rule__ElkEdge__Group__0 )
            {
             before(grammarAccess.getElkEdgeAccess().getGroup()); 
            // InternalGrana.g:770:1: ( rule__ElkEdge__Group__0 )
            // InternalGrana.g:770:2: rule__ElkEdge__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeAccess().getGroup()); 

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
    // $ANTLR end "ruleElkEdge"


    // $ANTLR start "ruleEdgeLayout"
    // InternalGrana.g:783:1: ruleEdgeLayout : ( ( rule__EdgeLayout__Group__0 ) ) ;
    public final void ruleEdgeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:787:2: ( ( ( rule__EdgeLayout__Group__0 ) ) )
            // InternalGrana.g:788:1: ( ( rule__EdgeLayout__Group__0 ) )
            {
            // InternalGrana.g:788:1: ( ( rule__EdgeLayout__Group__0 ) )
            // InternalGrana.g:789:1: ( rule__EdgeLayout__Group__0 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getGroup()); 
            // InternalGrana.g:790:1: ( rule__EdgeLayout__Group__0 )
            // InternalGrana.g:790:2: rule__EdgeLayout__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EdgeLayout__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEdgeLayoutAccess().getGroup()); 

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
    // $ANTLR end "ruleEdgeLayout"


    // $ANTLR start "entryRuleElkSingleEdgeSection"
    // InternalGrana.g:802:1: entryRuleElkSingleEdgeSection : ruleElkSingleEdgeSection EOF ;
    public final void entryRuleElkSingleEdgeSection() throws RecognitionException {
        try {
            // InternalGrana.g:803:1: ( ruleElkSingleEdgeSection EOF )
            // InternalGrana.g:804:1: ruleElkSingleEdgeSection EOF
            {
             before(grammarAccess.getElkSingleEdgeSectionRule()); 
            pushFollow(FOLLOW_1);
            ruleElkSingleEdgeSection();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkSingleEdgeSection"


    // $ANTLR start "ruleElkSingleEdgeSection"
    // InternalGrana.g:811:1: ruleElkSingleEdgeSection : ( ( rule__ElkSingleEdgeSection__Group__0 ) ) ;
    public final void ruleElkSingleEdgeSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:815:2: ( ( ( rule__ElkSingleEdgeSection__Group__0 ) ) )
            // InternalGrana.g:816:1: ( ( rule__ElkSingleEdgeSection__Group__0 ) )
            {
            // InternalGrana.g:816:1: ( ( rule__ElkSingleEdgeSection__Group__0 ) )
            // InternalGrana.g:817:1: ( rule__ElkSingleEdgeSection__Group__0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup()); 
            // InternalGrana.g:818:1: ( rule__ElkSingleEdgeSection__Group__0 )
            // InternalGrana.g:818:2: rule__ElkSingleEdgeSection__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup()); 

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
    // $ANTLR end "ruleElkSingleEdgeSection"


    // $ANTLR start "entryRuleElkEdgeSection"
    // InternalGrana.g:830:1: entryRuleElkEdgeSection : ruleElkEdgeSection EOF ;
    public final void entryRuleElkEdgeSection() throws RecognitionException {
        try {
            // InternalGrana.g:831:1: ( ruleElkEdgeSection EOF )
            // InternalGrana.g:832:1: ruleElkEdgeSection EOF
            {
             before(grammarAccess.getElkEdgeSectionRule()); 
            pushFollow(FOLLOW_1);
            ruleElkEdgeSection();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkEdgeSection"


    // $ANTLR start "ruleElkEdgeSection"
    // InternalGrana.g:839:1: ruleElkEdgeSection : ( ( rule__ElkEdgeSection__Group__0 ) ) ;
    public final void ruleElkEdgeSection() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:843:2: ( ( ( rule__ElkEdgeSection__Group__0 ) ) )
            // InternalGrana.g:844:1: ( ( rule__ElkEdgeSection__Group__0 ) )
            {
            // InternalGrana.g:844:1: ( ( rule__ElkEdgeSection__Group__0 ) )
            // InternalGrana.g:845:1: ( rule__ElkEdgeSection__Group__0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup()); 
            // InternalGrana.g:846:1: ( rule__ElkEdgeSection__Group__0 )
            // InternalGrana.g:846:2: rule__ElkEdgeSection__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getGroup()); 

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
    // $ANTLR end "ruleElkEdgeSection"


    // $ANTLR start "entryRuleElkBendPoint"
    // InternalGrana.g:858:1: entryRuleElkBendPoint : ruleElkBendPoint EOF ;
    public final void entryRuleElkBendPoint() throws RecognitionException {
        try {
            // InternalGrana.g:859:1: ( ruleElkBendPoint EOF )
            // InternalGrana.g:860:1: ruleElkBendPoint EOF
            {
             before(grammarAccess.getElkBendPointRule()); 
            pushFollow(FOLLOW_1);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkBendPointRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleElkBendPoint"


    // $ANTLR start "ruleElkBendPoint"
    // InternalGrana.g:867:1: ruleElkBendPoint : ( ( rule__ElkBendPoint__Group__0 ) ) ;
    public final void ruleElkBendPoint() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:871:2: ( ( ( rule__ElkBendPoint__Group__0 ) ) )
            // InternalGrana.g:872:1: ( ( rule__ElkBendPoint__Group__0 ) )
            {
            // InternalGrana.g:872:1: ( ( rule__ElkBendPoint__Group__0 ) )
            // InternalGrana.g:873:1: ( rule__ElkBendPoint__Group__0 )
            {
             before(grammarAccess.getElkBendPointAccess().getGroup()); 
            // InternalGrana.g:874:1: ( rule__ElkBendPoint__Group__0 )
            // InternalGrana.g:874:2: rule__ElkBendPoint__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkBendPoint__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getElkBendPointAccess().getGroup()); 

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
    // $ANTLR end "ruleElkBendPoint"


    // $ANTLR start "entryRuleQualifiedId"
    // InternalGrana.g:886:1: entryRuleQualifiedId : ruleQualifiedId EOF ;
    public final void entryRuleQualifiedId() throws RecognitionException {
        try {
            // InternalGrana.g:887:1: ( ruleQualifiedId EOF )
            // InternalGrana.g:888:1: ruleQualifiedId EOF
            {
             before(grammarAccess.getQualifiedIdRule()); 
            pushFollow(FOLLOW_1);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getQualifiedIdRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleQualifiedId"


    // $ANTLR start "ruleQualifiedId"
    // InternalGrana.g:895:1: ruleQualifiedId : ( ( rule__QualifiedId__Group__0 ) ) ;
    public final void ruleQualifiedId() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:899:2: ( ( ( rule__QualifiedId__Group__0 ) ) )
            // InternalGrana.g:900:1: ( ( rule__QualifiedId__Group__0 ) )
            {
            // InternalGrana.g:900:1: ( ( rule__QualifiedId__Group__0 ) )
            // InternalGrana.g:901:1: ( rule__QualifiedId__Group__0 )
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup()); 
            // InternalGrana.g:902:1: ( rule__QualifiedId__Group__0 )
            // InternalGrana.g:902:2: rule__QualifiedId__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedId__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQualifiedIdAccess().getGroup()); 

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
    // $ANTLR end "ruleQualifiedId"


    // $ANTLR start "entryRuleNumber"
    // InternalGrana.g:914:1: entryRuleNumber : ruleNumber EOF ;
    public final void entryRuleNumber() throws RecognitionException {
        try {
            // InternalGrana.g:915:1: ( ruleNumber EOF )
            // InternalGrana.g:916:1: ruleNumber EOF
            {
             before(grammarAccess.getNumberRule()); 
            pushFollow(FOLLOW_1);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getNumberRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleNumber"


    // $ANTLR start "ruleNumber"
    // InternalGrana.g:923:1: ruleNumber : ( ( rule__Number__Alternatives ) ) ;
    public final void ruleNumber() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:927:2: ( ( ( rule__Number__Alternatives ) ) )
            // InternalGrana.g:928:1: ( ( rule__Number__Alternatives ) )
            {
            // InternalGrana.g:928:1: ( ( rule__Number__Alternatives ) )
            // InternalGrana.g:929:1: ( rule__Number__Alternatives )
            {
             before(grammarAccess.getNumberAccess().getAlternatives()); 
            // InternalGrana.g:930:1: ( rule__Number__Alternatives )
            // InternalGrana.g:930:2: rule__Number__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Number__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getNumberAccess().getAlternatives()); 

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
    // $ANTLR end "ruleNumber"


    // $ANTLR start "entryRuleProperty"
    // InternalGrana.g:942:1: entryRuleProperty : ruleProperty EOF ;
    public final void entryRuleProperty() throws RecognitionException {
        try {
            // InternalGrana.g:943:1: ( ruleProperty EOF )
            // InternalGrana.g:944:1: ruleProperty EOF
            {
             before(grammarAccess.getPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleProperty"


    // $ANTLR start "ruleProperty"
    // InternalGrana.g:951:1: ruleProperty : ( ( rule__Property__Group__0 ) ) ;
    public final void ruleProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:955:2: ( ( ( rule__Property__Group__0 ) ) )
            // InternalGrana.g:956:1: ( ( rule__Property__Group__0 ) )
            {
            // InternalGrana.g:956:1: ( ( rule__Property__Group__0 ) )
            // InternalGrana.g:957:1: ( rule__Property__Group__0 )
            {
             before(grammarAccess.getPropertyAccess().getGroup()); 
            // InternalGrana.g:958:1: ( rule__Property__Group__0 )
            // InternalGrana.g:958:2: rule__Property__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Property__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getGroup()); 

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
    // $ANTLR end "ruleProperty"


    // $ANTLR start "entryRulePropertyKey"
    // InternalGrana.g:970:1: entryRulePropertyKey : rulePropertyKey EOF ;
    public final void entryRulePropertyKey() throws RecognitionException {

        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalGrana.g:974:1: ( rulePropertyKey EOF )
            // InternalGrana.g:975:1: rulePropertyKey EOF
            {
             before(grammarAccess.getPropertyKeyRule()); 
            pushFollow(FOLLOW_1);
            rulePropertyKey();

            state._fsp--;

             after(grammarAccess.getPropertyKeyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	myHiddenTokenState.restore();

        }
        return ;
    }
    // $ANTLR end "entryRulePropertyKey"


    // $ANTLR start "rulePropertyKey"
    // InternalGrana.g:985:1: rulePropertyKey : ( ( rule__PropertyKey__Group__0 ) ) ;
    public final void rulePropertyKey() throws RecognitionException {

        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:990:2: ( ( ( rule__PropertyKey__Group__0 ) ) )
            // InternalGrana.g:991:1: ( ( rule__PropertyKey__Group__0 ) )
            {
            // InternalGrana.g:991:1: ( ( rule__PropertyKey__Group__0 ) )
            // InternalGrana.g:992:1: ( rule__PropertyKey__Group__0 )
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup()); 
            // InternalGrana.g:993:1: ( rule__PropertyKey__Group__0 )
            // InternalGrana.g:993:2: rule__PropertyKey__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__PropertyKey__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyKeyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);
            	myHiddenTokenState.restore();

        }
        return ;
    }
    // $ANTLR end "rulePropertyKey"


    // $ANTLR start "entryRuleStringValue"
    // InternalGrana.g:1006:1: entryRuleStringValue : ruleStringValue EOF ;
    public final void entryRuleStringValue() throws RecognitionException {
        try {
            // InternalGrana.g:1007:1: ( ruleStringValue EOF )
            // InternalGrana.g:1008:1: ruleStringValue EOF
            {
             before(grammarAccess.getStringValueRule()); 
            pushFollow(FOLLOW_1);
            ruleStringValue();

            state._fsp--;

             after(grammarAccess.getStringValueRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleStringValue"


    // $ANTLR start "ruleStringValue"
    // InternalGrana.g:1015:1: ruleStringValue : ( RULE_STRING ) ;
    public final void ruleStringValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1019:2: ( ( RULE_STRING ) )
            // InternalGrana.g:1020:1: ( RULE_STRING )
            {
            // InternalGrana.g:1020:1: ( RULE_STRING )
            // InternalGrana.g:1021:1: RULE_STRING
            {
             before(grammarAccess.getStringValueAccess().getSTRINGTerminalRuleCall()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getStringValueAccess().getSTRINGTerminalRuleCall()); 

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
    // $ANTLR end "ruleStringValue"


    // $ANTLR start "entryRuleQualifiedIdValue"
    // InternalGrana.g:1034:1: entryRuleQualifiedIdValue : ruleQualifiedIdValue EOF ;
    public final void entryRuleQualifiedIdValue() throws RecognitionException {
        try {
            // InternalGrana.g:1035:1: ( ruleQualifiedIdValue EOF )
            // InternalGrana.g:1036:1: ruleQualifiedIdValue EOF
            {
             before(grammarAccess.getQualifiedIdValueRule()); 
            pushFollow(FOLLOW_1);
            ruleQualifiedIdValue();

            state._fsp--;

             after(grammarAccess.getQualifiedIdValueRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleQualifiedIdValue"


    // $ANTLR start "ruleQualifiedIdValue"
    // InternalGrana.g:1043:1: ruleQualifiedIdValue : ( ruleQualifiedId ) ;
    public final void ruleQualifiedIdValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1047:2: ( ( ruleQualifiedId ) )
            // InternalGrana.g:1048:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:1048:1: ( ruleQualifiedId )
            // InternalGrana.g:1049:1: ruleQualifiedId
            {
             before(grammarAccess.getQualifiedIdValueAccess().getQualifiedIdParserRuleCall()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getQualifiedIdValueAccess().getQualifiedIdParserRuleCall()); 

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
    // $ANTLR end "ruleQualifiedIdValue"


    // $ANTLR start "entryRuleNumberValue"
    // InternalGrana.g:1062:1: entryRuleNumberValue : ruleNumberValue EOF ;
    public final void entryRuleNumberValue() throws RecognitionException {
        try {
            // InternalGrana.g:1063:1: ( ruleNumberValue EOF )
            // InternalGrana.g:1064:1: ruleNumberValue EOF
            {
             before(grammarAccess.getNumberValueRule()); 
            pushFollow(FOLLOW_1);
            ruleNumberValue();

            state._fsp--;

             after(grammarAccess.getNumberValueRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleNumberValue"


    // $ANTLR start "ruleNumberValue"
    // InternalGrana.g:1071:1: ruleNumberValue : ( ( rule__NumberValue__Alternatives ) ) ;
    public final void ruleNumberValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1075:2: ( ( ( rule__NumberValue__Alternatives ) ) )
            // InternalGrana.g:1076:1: ( ( rule__NumberValue__Alternatives ) )
            {
            // InternalGrana.g:1076:1: ( ( rule__NumberValue__Alternatives ) )
            // InternalGrana.g:1077:1: ( rule__NumberValue__Alternatives )
            {
             before(grammarAccess.getNumberValueAccess().getAlternatives()); 
            // InternalGrana.g:1078:1: ( rule__NumberValue__Alternatives )
            // InternalGrana.g:1078:2: rule__NumberValue__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__NumberValue__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getNumberValueAccess().getAlternatives()); 

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
    // $ANTLR end "ruleNumberValue"


    // $ANTLR start "entryRuleBooleanValue"
    // InternalGrana.g:1090:1: entryRuleBooleanValue : ruleBooleanValue EOF ;
    public final void entryRuleBooleanValue() throws RecognitionException {
        try {
            // InternalGrana.g:1091:1: ( ruleBooleanValue EOF )
            // InternalGrana.g:1092:1: ruleBooleanValue EOF
            {
             before(grammarAccess.getBooleanValueRule()); 
            pushFollow(FOLLOW_1);
            ruleBooleanValue();

            state._fsp--;

             after(grammarAccess.getBooleanValueRule()); 
            match(input,EOF,FOLLOW_2); 

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
    // $ANTLR end "entryRuleBooleanValue"


    // $ANTLR start "ruleBooleanValue"
    // InternalGrana.g:1099:1: ruleBooleanValue : ( ( rule__BooleanValue__Alternatives ) ) ;
    public final void ruleBooleanValue() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1103:2: ( ( ( rule__BooleanValue__Alternatives ) ) )
            // InternalGrana.g:1104:1: ( ( rule__BooleanValue__Alternatives ) )
            {
            // InternalGrana.g:1104:1: ( ( rule__BooleanValue__Alternatives ) )
            // InternalGrana.g:1105:1: ( rule__BooleanValue__Alternatives )
            {
             before(grammarAccess.getBooleanValueAccess().getAlternatives()); 
            // InternalGrana.g:1106:1: ( rule__BooleanValue__Alternatives )
            // InternalGrana.g:1106:2: rule__BooleanValue__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__BooleanValue__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getBooleanValueAccess().getAlternatives()); 

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
    // $ANTLR end "ruleBooleanValue"


    // $ANTLR start "ruleOutputType"
    // InternalGrana.g:1119:1: ruleOutputType : ( ( rule__OutputType__Alternatives ) ) ;
    public final void ruleOutputType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1123:1: ( ( ( rule__OutputType__Alternatives ) ) )
            // InternalGrana.g:1124:1: ( ( rule__OutputType__Alternatives ) )
            {
            // InternalGrana.g:1124:1: ( ( rule__OutputType__Alternatives ) )
            // InternalGrana.g:1125:1: ( rule__OutputType__Alternatives )
            {
             before(grammarAccess.getOutputTypeAccess().getAlternatives()); 
            // InternalGrana.g:1126:1: ( rule__OutputType__Alternatives )
            // InternalGrana.g:1126:2: rule__OutputType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__OutputType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getOutputTypeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleOutputType"


    // $ANTLR start "rule__Grana__Alternatives_2_2"
    // InternalGrana.g:1137:1: rule__Grana__Alternatives_2_2 : ( ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) ) );
    public final void rule__Grana__Alternatives_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1141:1: ( ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) ) | ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==61) ) {
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
                    // InternalGrana.g:1142:1: ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) )
                    {
                    // InternalGrana.g:1142:1: ( ( rule__Grana__ExecuteAllAssignment_2_2_0 ) )
                    // InternalGrana.g:1143:1: ( rule__Grana__ExecuteAllAssignment_2_2_0 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_2_0()); 
                    // InternalGrana.g:1144:1: ( rule__Grana__ExecuteAllAssignment_2_2_0 )
                    // InternalGrana.g:1144:2: rule__Grana__ExecuteAllAssignment_2_2_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Grana__ExecuteAllAssignment_2_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAllAssignment_2_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1148:6: ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) )
                    {
                    // InternalGrana.g:1148:6: ( ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* ) )
                    // InternalGrana.g:1149:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 ) ) ( ( rule__Grana__ExecuteAssignment_2_2_1 )* )
                    {
                    // InternalGrana.g:1149:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 ) )
                    // InternalGrana.g:1150:1: ( rule__Grana__ExecuteAssignment_2_2_1 )
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 
                    // InternalGrana.g:1151:1: ( rule__Grana__ExecuteAssignment_2_2_1 )
                    // InternalGrana.g:1151:2: rule__Grana__ExecuteAssignment_2_2_1
                    {
                    pushFollow(FOLLOW_3);
                    rule__Grana__ExecuteAssignment_2_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 

                    }

                    // InternalGrana.g:1154:1: ( ( rule__Grana__ExecuteAssignment_2_2_1 )* )
                    // InternalGrana.g:1155:1: ( rule__Grana__ExecuteAssignment_2_2_1 )*
                    {
                     before(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 
                    // InternalGrana.g:1156:1: ( rule__Grana__ExecuteAssignment_2_2_1 )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==RULE_ID) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalGrana.g:1156:2: rule__Grana__ExecuteAssignment_2_2_1
                    	    {
                    	    pushFollow(FOLLOW_3);
                    	    rule__Grana__ExecuteAssignment_2_2_1();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                     after(grammarAccess.getGranaAccess().getExecuteAssignment_2_2_1()); 

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
    // $ANTLR end "rule__Grana__Alternatives_2_2"


    // $ANTLR start "rule__Job__Alternatives"
    // InternalGrana.g:1166:1: rule__Job__Alternatives : ( ( ruleRegularJob ) | ( ruleRangeJob ) | ( ruleCompareJob ) );
    public final void rule__Job__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1170:1: ( ( ruleRegularJob ) | ( ruleRangeJob ) | ( ruleCompareJob ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 20:
                {
                alt3=1;
                }
                break;
            case 26:
                {
                alt3=2;
                }
                break;
            case 25:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalGrana.g:1171:1: ( ruleRegularJob )
                    {
                    // InternalGrana.g:1171:1: ( ruleRegularJob )
                    // InternalGrana.g:1172:1: ruleRegularJob
                    {
                     before(grammarAccess.getJobAccess().getRegularJobParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleRegularJob();

                    state._fsp--;

                     after(grammarAccess.getJobAccess().getRegularJobParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1177:6: ( ruleRangeJob )
                    {
                    // InternalGrana.g:1177:6: ( ruleRangeJob )
                    // InternalGrana.g:1178:1: ruleRangeJob
                    {
                     before(grammarAccess.getJobAccess().getRangeJobParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleRangeJob();

                    state._fsp--;

                     after(grammarAccess.getJobAccess().getRangeJobParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:1183:6: ( ruleCompareJob )
                    {
                    // InternalGrana.g:1183:6: ( ruleCompareJob )
                    // InternalGrana.g:1184:1: ruleCompareJob
                    {
                     before(grammarAccess.getJobAccess().getCompareJobParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleCompareJob();

                    state._fsp--;

                     after(grammarAccess.getJobAccess().getCompareJobParserRuleCall_2()); 

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


    // $ANTLR start "rule__RangeJob__Alternatives_12"
    // InternalGrana.g:1194:1: rule__RangeJob__Alternatives_12 : ( ( ( rule__RangeJob__Group_12_0__0 ) ) | ( ( rule__RangeJob__Group_12_1__0 ) ) );
    public final void rule__RangeJob__Alternatives_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1198:1: ( ( ( rule__RangeJob__Group_12_0__0 ) ) | ( ( rule__RangeJob__Group_12_1__0 ) ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==28) ) {
                alt4=1;
            }
            else if ( (LA4_0==30) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalGrana.g:1199:1: ( ( rule__RangeJob__Group_12_0__0 ) )
                    {
                    // InternalGrana.g:1199:1: ( ( rule__RangeJob__Group_12_0__0 ) )
                    // InternalGrana.g:1200:1: ( rule__RangeJob__Group_12_0__0 )
                    {
                     before(grammarAccess.getRangeJobAccess().getGroup_12_0()); 
                    // InternalGrana.g:1201:1: ( rule__RangeJob__Group_12_0__0 )
                    // InternalGrana.g:1201:2: rule__RangeJob__Group_12_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeJob__Group_12_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRangeJobAccess().getGroup_12_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1205:6: ( ( rule__RangeJob__Group_12_1__0 ) )
                    {
                    // InternalGrana.g:1205:6: ( ( rule__RangeJob__Group_12_1__0 ) )
                    // InternalGrana.g:1206:1: ( rule__RangeJob__Group_12_1__0 )
                    {
                     before(grammarAccess.getRangeJobAccess().getGroup_12_1()); 
                    // InternalGrana.g:1207:1: ( rule__RangeJob__Group_12_1__0 )
                    // InternalGrana.g:1207:2: rule__RangeJob__Group_12_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeJob__Group_12_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRangeJobAccess().getGroup_12_1()); 

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
    // $ANTLR end "rule__RangeJob__Alternatives_12"


    // $ANTLR start "rule__Range__Alternatives"
    // InternalGrana.g:1216:1: rule__Range__Alternatives : ( ( ruleFloatRange ) | ( ruleIntRange ) | ( ruleEnumRange ) );
    public final void rule__Range__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1220:1: ( ( ruleFloatRange ) | ( ruleIntRange ) | ( ruleEnumRange ) )
            int alt5=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt5=1;
                }
                break;
            case 33:
            case 34:
                {
                alt5=2;
                }
                break;
            case 36:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalGrana.g:1221:1: ( ruleFloatRange )
                    {
                    // InternalGrana.g:1221:1: ( ruleFloatRange )
                    // InternalGrana.g:1222:1: ruleFloatRange
                    {
                     before(grammarAccess.getRangeAccess().getFloatRangeParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleFloatRange();

                    state._fsp--;

                     after(grammarAccess.getRangeAccess().getFloatRangeParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1227:6: ( ruleIntRange )
                    {
                    // InternalGrana.g:1227:6: ( ruleIntRange )
                    // InternalGrana.g:1228:1: ruleIntRange
                    {
                     before(grammarAccess.getRangeAccess().getIntRangeParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleIntRange();

                    state._fsp--;

                     after(grammarAccess.getRangeAccess().getIntRangeParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:1233:6: ( ruleEnumRange )
                    {
                    // InternalGrana.g:1233:6: ( ruleEnumRange )
                    // InternalGrana.g:1234:1: ruleEnumRange
                    {
                     before(grammarAccess.getRangeAccess().getEnumRangeParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleEnumRange();

                    state._fsp--;

                     after(grammarAccess.getRangeAccess().getEnumRangeParserRuleCall_2()); 

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
    // InternalGrana.g:1244:1: rule__IntRange__Alternatives : ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) );
    public final void rule__IntRange__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1248:1: ( ( ruleIntRangeRange ) | ( ruleIntRangeValues ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==34) ) {
                alt6=1;
            }
            else if ( (LA6_0==33) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalGrana.g:1249:1: ( ruleIntRangeRange )
                    {
                    // InternalGrana.g:1249:1: ( ruleIntRangeRange )
                    // InternalGrana.g:1250:1: ruleIntRangeRange
                    {
                     before(grammarAccess.getIntRangeAccess().getIntRangeRangeParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleIntRangeRange();

                    state._fsp--;

                     after(grammarAccess.getIntRangeAccess().getIntRangeRangeParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1255:6: ( ruleIntRangeValues )
                    {
                    // InternalGrana.g:1255:6: ( ruleIntRangeValues )
                    // InternalGrana.g:1256:1: ruleIntRangeValues
                    {
                     before(grammarAccess.getIntRangeAccess().getIntRangeValuesParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1266:1: rule__Resource__Alternatives : ( ( ruleResourceReference ) | ( ruleLocalResource ) );
    public final void rule__Resource__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1270:1: ( ( ruleResourceReference ) | ( ruleLocalResource ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==37) ) {
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
                    // InternalGrana.g:1271:1: ( ruleResourceReference )
                    {
                    // InternalGrana.g:1271:1: ( ruleResourceReference )
                    // InternalGrana.g:1272:1: ruleResourceReference
                    {
                     before(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleResourceReference();

                    state._fsp--;

                     after(grammarAccess.getResourceAccess().getResourceReferenceParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1277:6: ( ruleLocalResource )
                    {
                    // InternalGrana.g:1277:6: ( ruleLocalResource )
                    // InternalGrana.g:1278:1: ruleLocalResource
                    {
                     before(grammarAccess.getResourceAccess().getLocalResourceParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1288:1: rule__Output__Alternatives : ( ( ruleOutputReference ) | ( ruleLocalOutput ) );
    public final void rule__Output__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1292:1: ( ( ruleOutputReference ) | ( ruleLocalOutput ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==37) ) {
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
                    // InternalGrana.g:1293:1: ( ruleOutputReference )
                    {
                    // InternalGrana.g:1293:1: ( ruleOutputReference )
                    // InternalGrana.g:1294:1: ruleOutputReference
                    {
                     before(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleOutputReference();

                    state._fsp--;

                     after(grammarAccess.getOutputAccess().getOutputReferenceParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1299:6: ( ruleLocalOutput )
                    {
                    // InternalGrana.g:1299:6: ( ruleLocalOutput )
                    // InternalGrana.g:1300:1: ruleLocalOutput
                    {
                     before(grammarAccess.getOutputAccess().getLocalOutputParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
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


    // $ANTLR start "rule__ElkNode__Alternatives_2_3"
    // InternalGrana.g:1311:1: rule__ElkNode__Alternatives_2_3 : ( ( ( rule__ElkNode__LabelsAssignment_2_3_0 ) ) | ( ( rule__ElkNode__PortsAssignment_2_3_1 ) ) | ( ( rule__ElkNode__ChildrenAssignment_2_3_2 ) ) | ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 ) ) );
    public final void rule__ElkNode__Alternatives_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1315:1: ( ( ( rule__ElkNode__LabelsAssignment_2_3_0 ) ) | ( ( rule__ElkNode__PortsAssignment_2_3_1 ) ) | ( ( rule__ElkNode__ChildrenAssignment_2_3_2 ) ) | ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 ) ) )
            int alt9=4;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt9=1;
                }
                break;
            case 44:
                {
                alt9=2;
                }
                break;
            case 41:
                {
                alt9=3;
                }
                break;
            case 50:
                {
                alt9=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalGrana.g:1316:1: ( ( rule__ElkNode__LabelsAssignment_2_3_0 ) )
                    {
                    // InternalGrana.g:1316:1: ( ( rule__ElkNode__LabelsAssignment_2_3_0 ) )
                    // InternalGrana.g:1317:1: ( rule__ElkNode__LabelsAssignment_2_3_0 )
                    {
                     before(grammarAccess.getElkNodeAccess().getLabelsAssignment_2_3_0()); 
                    // InternalGrana.g:1318:1: ( rule__ElkNode__LabelsAssignment_2_3_0 )
                    // InternalGrana.g:1318:2: rule__ElkNode__LabelsAssignment_2_3_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__LabelsAssignment_2_3_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkNodeAccess().getLabelsAssignment_2_3_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1322:6: ( ( rule__ElkNode__PortsAssignment_2_3_1 ) )
                    {
                    // InternalGrana.g:1322:6: ( ( rule__ElkNode__PortsAssignment_2_3_1 ) )
                    // InternalGrana.g:1323:1: ( rule__ElkNode__PortsAssignment_2_3_1 )
                    {
                     before(grammarAccess.getElkNodeAccess().getPortsAssignment_2_3_1()); 
                    // InternalGrana.g:1324:1: ( rule__ElkNode__PortsAssignment_2_3_1 )
                    // InternalGrana.g:1324:2: rule__ElkNode__PortsAssignment_2_3_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__PortsAssignment_2_3_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkNodeAccess().getPortsAssignment_2_3_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:1328:6: ( ( rule__ElkNode__ChildrenAssignment_2_3_2 ) )
                    {
                    // InternalGrana.g:1328:6: ( ( rule__ElkNode__ChildrenAssignment_2_3_2 ) )
                    // InternalGrana.g:1329:1: ( rule__ElkNode__ChildrenAssignment_2_3_2 )
                    {
                     before(grammarAccess.getElkNodeAccess().getChildrenAssignment_2_3_2()); 
                    // InternalGrana.g:1330:1: ( rule__ElkNode__ChildrenAssignment_2_3_2 )
                    // InternalGrana.g:1330:2: rule__ElkNode__ChildrenAssignment_2_3_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__ChildrenAssignment_2_3_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkNodeAccess().getChildrenAssignment_2_3_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:1334:6: ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 ) )
                    {
                    // InternalGrana.g:1334:6: ( ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 ) )
                    // InternalGrana.g:1335:1: ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 )
                    {
                     before(grammarAccess.getElkNodeAccess().getContainedEdgesAssignment_2_3_3()); 
                    // InternalGrana.g:1336:1: ( rule__ElkNode__ContainedEdgesAssignment_2_3_3 )
                    // InternalGrana.g:1336:2: rule__ElkNode__ContainedEdgesAssignment_2_3_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__ContainedEdgesAssignment_2_3_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkNodeAccess().getContainedEdgesAssignment_2_3_3()); 

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
    // $ANTLR end "rule__ElkNode__Alternatives_2_3"


    // $ANTLR start "rule__EdgeLayout__Alternatives_2"
    // InternalGrana.g:1345:1: rule__EdgeLayout__Alternatives_2 : ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) );
    public final void rule__EdgeLayout__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1349:1: ( ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) ) | ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==EOF||LA11_0==RULE_ID||LA11_0==47||(LA11_0>=52 && LA11_0<=56)) ) {
                alt11=1;
            }
            else if ( (LA11_0==58) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalGrana.g:1350:1: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    {
                    // InternalGrana.g:1350:1: ( ( rule__EdgeLayout__SectionsAssignment_2_0 ) )
                    // InternalGrana.g:1351:1: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_0()); 
                    // InternalGrana.g:1352:1: ( rule__EdgeLayout__SectionsAssignment_2_0 )
                    // InternalGrana.g:1352:2: rule__EdgeLayout__SectionsAssignment_2_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__EdgeLayout__SectionsAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1356:6: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    {
                    // InternalGrana.g:1356:6: ( ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* ) )
                    // InternalGrana.g:1357:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) ) ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    {
                    // InternalGrana.g:1357:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 ) )
                    // InternalGrana.g:1358:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalGrana.g:1359:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )
                    // InternalGrana.g:1359:2: rule__EdgeLayout__SectionsAssignment_2_1
                    {
                    pushFollow(FOLLOW_4);
                    rule__EdgeLayout__SectionsAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 

                    }

                    // InternalGrana.g:1362:1: ( ( rule__EdgeLayout__SectionsAssignment_2_1 )* )
                    // InternalGrana.g:1363:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    {
                     before(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 
                    // InternalGrana.g:1364:1: ( rule__EdgeLayout__SectionsAssignment_2_1 )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==58) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalGrana.g:1364:2: rule__EdgeLayout__SectionsAssignment_2_1
                    	    {
                    	    pushFollow(FOLLOW_4);
                    	    rule__EdgeLayout__SectionsAssignment_2_1();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                     after(grammarAccess.getEdgeLayoutAccess().getSectionsAssignment_2_1()); 

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
    // $ANTLR end "rule__EdgeLayout__Alternatives_2"


    // $ANTLR start "rule__Number__Alternatives"
    // InternalGrana.g:1374:1: rule__Number__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__Number__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1378:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_SIGNED_INT) ) {
                alt12=1;
            }
            else if ( (LA12_0==RULE_FLOAT) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalGrana.g:1379:1: ( RULE_SIGNED_INT )
                    {
                    // InternalGrana.g:1379:1: ( RULE_SIGNED_INT )
                    // InternalGrana.g:1380:1: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FOLLOW_2); 
                     after(grammarAccess.getNumberAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1385:6: ( RULE_FLOAT )
                    {
                    // InternalGrana.g:1385:6: ( RULE_FLOAT )
                    // InternalGrana.g:1386:1: RULE_FLOAT
                    {
                     before(grammarAccess.getNumberAccess().getFLOATTerminalRuleCall_1()); 
                    match(input,RULE_FLOAT,FOLLOW_2); 
                     after(grammarAccess.getNumberAccess().getFLOATTerminalRuleCall_1()); 

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
    // $ANTLR end "rule__Number__Alternatives"


    // $ANTLR start "rule__Property__Alternatives_2"
    // InternalGrana.g:1396:1: rule__Property__Alternatives_2 : ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) );
    public final void rule__Property__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1400:1: ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) )
            int alt13=4;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt13=1;
                }
                break;
            case RULE_ID:
                {
                alt13=2;
                }
                break;
            case RULE_SIGNED_INT:
            case RULE_FLOAT:
                {
                alt13=3;
                }
                break;
            case 13:
            case 14:
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
                    // InternalGrana.g:1401:1: ( ( rule__Property__ValueAssignment_2_0 ) )
                    {
                    // InternalGrana.g:1401:1: ( ( rule__Property__ValueAssignment_2_0 ) )
                    // InternalGrana.g:1402:1: ( rule__Property__ValueAssignment_2_0 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_0()); 
                    // InternalGrana.g:1403:1: ( rule__Property__ValueAssignment_2_0 )
                    // InternalGrana.g:1403:2: rule__Property__ValueAssignment_2_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Property__ValueAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1407:6: ( ( rule__Property__ValueAssignment_2_1 ) )
                    {
                    // InternalGrana.g:1407:6: ( ( rule__Property__ValueAssignment_2_1 ) )
                    // InternalGrana.g:1408:1: ( rule__Property__ValueAssignment_2_1 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_1()); 
                    // InternalGrana.g:1409:1: ( rule__Property__ValueAssignment_2_1 )
                    // InternalGrana.g:1409:2: rule__Property__ValueAssignment_2_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Property__ValueAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:1413:6: ( ( rule__Property__ValueAssignment_2_2 ) )
                    {
                    // InternalGrana.g:1413:6: ( ( rule__Property__ValueAssignment_2_2 ) )
                    // InternalGrana.g:1414:1: ( rule__Property__ValueAssignment_2_2 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_2()); 
                    // InternalGrana.g:1415:1: ( rule__Property__ValueAssignment_2_2 )
                    // InternalGrana.g:1415:2: rule__Property__ValueAssignment_2_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Property__ValueAssignment_2_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:1419:6: ( ( rule__Property__ValueAssignment_2_3 ) )
                    {
                    // InternalGrana.g:1419:6: ( ( rule__Property__ValueAssignment_2_3 ) )
                    // InternalGrana.g:1420:1: ( rule__Property__ValueAssignment_2_3 )
                    {
                     before(grammarAccess.getPropertyAccess().getValueAssignment_2_3()); 
                    // InternalGrana.g:1421:1: ( rule__Property__ValueAssignment_2_3 )
                    // InternalGrana.g:1421:2: rule__Property__ValueAssignment_2_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__Property__ValueAssignment_2_3();

                    state._fsp--;


                    }

                     after(grammarAccess.getPropertyAccess().getValueAssignment_2_3()); 

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
    // $ANTLR end "rule__Property__Alternatives_2"


    // $ANTLR start "rule__NumberValue__Alternatives"
    // InternalGrana.g:1430:1: rule__NumberValue__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__NumberValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1434:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_SIGNED_INT) ) {
                alt14=1;
            }
            else if ( (LA14_0==RULE_FLOAT) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // InternalGrana.g:1435:1: ( RULE_SIGNED_INT )
                    {
                    // InternalGrana.g:1435:1: ( RULE_SIGNED_INT )
                    // InternalGrana.g:1436:1: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FOLLOW_2); 
                     after(grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1441:6: ( RULE_FLOAT )
                    {
                    // InternalGrana.g:1441:6: ( RULE_FLOAT )
                    // InternalGrana.g:1442:1: RULE_FLOAT
                    {
                     before(grammarAccess.getNumberValueAccess().getFLOATTerminalRuleCall_1()); 
                    match(input,RULE_FLOAT,FOLLOW_2); 
                     after(grammarAccess.getNumberValueAccess().getFLOATTerminalRuleCall_1()); 

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
    // $ANTLR end "rule__NumberValue__Alternatives"


    // $ANTLR start "rule__BooleanValue__Alternatives"
    // InternalGrana.g:1452:1: rule__BooleanValue__Alternatives : ( ( 'true' ) | ( 'false' ) );
    public final void rule__BooleanValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1456:1: ( ( 'true' ) | ( 'false' ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==13) ) {
                alt15=1;
            }
            else if ( (LA15_0==14) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // InternalGrana.g:1457:1: ( 'true' )
                    {
                    // InternalGrana.g:1457:1: ( 'true' )
                    // InternalGrana.g:1458:1: 'true'
                    {
                     before(grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1465:6: ( 'false' )
                    {
                    // InternalGrana.g:1465:6: ( 'false' )
                    // InternalGrana.g:1466:1: 'false'
                    {
                     before(grammarAccess.getBooleanValueAccess().getFalseKeyword_1()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getBooleanValueAccess().getFalseKeyword_1()); 

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
    // $ANTLR end "rule__BooleanValue__Alternatives"


    // $ANTLR start "rule__OutputType__Alternatives"
    // InternalGrana.g:1478:1: rule__OutputType__Alternatives : ( ( ( 'csv' ) ) | ( ( 'json' ) ) );
    public final void rule__OutputType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1482:1: ( ( ( 'csv' ) ) | ( ( 'json' ) ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==15) ) {
                alt16=1;
            }
            else if ( (LA16_0==16) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalGrana.g:1483:1: ( ( 'csv' ) )
                    {
                    // InternalGrana.g:1483:1: ( ( 'csv' ) )
                    // InternalGrana.g:1484:1: ( 'csv' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 
                    // InternalGrana.g:1485:1: ( 'csv' )
                    // InternalGrana.g:1485:3: 'csv'
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1490:6: ( ( 'json' ) )
                    {
                    // InternalGrana.g:1490:6: ( ( 'json' ) )
                    // InternalGrana.g:1491:1: ( 'json' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getJsonEnumLiteralDeclaration_1()); 
                    // InternalGrana.g:1492:1: ( 'json' )
                    // InternalGrana.g:1492:3: 'json'
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getOutputTypeAccess().getJsonEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__OutputType__Alternatives"


    // $ANTLR start "rule__Grana__Group__0"
    // InternalGrana.g:1504:1: rule__Grana__Group__0 : rule__Grana__Group__0__Impl rule__Grana__Group__1 ;
    public final void rule__Grana__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1508:1: ( rule__Grana__Group__0__Impl rule__Grana__Group__1 )
            // InternalGrana.g:1509:2: rule__Grana__Group__0__Impl rule__Grana__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Grana__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1516:1: rule__Grana__Group__0__Impl : ( ( rule__Grana__Group_0__0 )? ) ;
    public final void rule__Grana__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1520:1: ( ( ( rule__Grana__Group_0__0 )? ) )
            // InternalGrana.g:1521:1: ( ( rule__Grana__Group_0__0 )? )
            {
            // InternalGrana.g:1521:1: ( ( rule__Grana__Group_0__0 )? )
            // InternalGrana.g:1522:1: ( rule__Grana__Group_0__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_0()); 
            // InternalGrana.g:1523:1: ( rule__Grana__Group_0__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==17) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalGrana.g:1523:2: rule__Grana__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1533:1: rule__Grana__Group__1 : rule__Grana__Group__1__Impl rule__Grana__Group__2 ;
    public final void rule__Grana__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1537:1: ( rule__Grana__Group__1__Impl rule__Grana__Group__2 )
            // InternalGrana.g:1538:2: rule__Grana__Group__1__Impl rule__Grana__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Grana__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1545:1: rule__Grana__Group__1__Impl : ( ( rule__Grana__Group_1__0 )? ) ;
    public final void rule__Grana__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1549:1: ( ( ( rule__Grana__Group_1__0 )? ) )
            // InternalGrana.g:1550:1: ( ( rule__Grana__Group_1__0 )? )
            {
            // InternalGrana.g:1550:1: ( ( rule__Grana__Group_1__0 )? )
            // InternalGrana.g:1551:1: ( rule__Grana__Group_1__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_1()); 
            // InternalGrana.g:1552:1: ( rule__Grana__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==18) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalGrana.g:1552:2: rule__Grana__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1562:1: rule__Grana__Group__2 : rule__Grana__Group__2__Impl rule__Grana__Group__3 ;
    public final void rule__Grana__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1566:1: ( rule__Grana__Group__2__Impl rule__Grana__Group__3 )
            // InternalGrana.g:1567:2: rule__Grana__Group__2__Impl rule__Grana__Group__3
            {
            pushFollow(FOLLOW_6);
            rule__Grana__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1574:1: rule__Grana__Group__2__Impl : ( ( rule__Grana__Group_2__0 ) ) ;
    public final void rule__Grana__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1578:1: ( ( ( rule__Grana__Group_2__0 ) ) )
            // InternalGrana.g:1579:1: ( ( rule__Grana__Group_2__0 ) )
            {
            // InternalGrana.g:1579:1: ( ( rule__Grana__Group_2__0 ) )
            // InternalGrana.g:1580:1: ( rule__Grana__Group_2__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup_2()); 
            // InternalGrana.g:1581:1: ( rule__Grana__Group_2__0 )
            // InternalGrana.g:1581:2: rule__Grana__Group_2__0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1591:1: rule__Grana__Group__3 : rule__Grana__Group__3__Impl ;
    public final void rule__Grana__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1595:1: ( rule__Grana__Group__3__Impl )
            // InternalGrana.g:1596:2: rule__Grana__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1602:1: rule__Grana__Group__3__Impl : ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) ;
    public final void rule__Grana__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1606:1: ( ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) )
            // InternalGrana.g:1607:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            {
            // InternalGrana.g:1607:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            // InternalGrana.g:1608:1: ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* )
            {
            // InternalGrana.g:1608:1: ( ( rule__Grana__JobsAssignment_3 ) )
            // InternalGrana.g:1609:1: ( rule__Grana__JobsAssignment_3 )
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1610:1: ( rule__Grana__JobsAssignment_3 )
            // InternalGrana.g:1610:2: rule__Grana__JobsAssignment_3
            {
            pushFollow(FOLLOW_7);
            rule__Grana__JobsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getJobsAssignment_3()); 

            }

            // InternalGrana.g:1613:1: ( ( rule__Grana__JobsAssignment_3 )* )
            // InternalGrana.g:1614:1: ( rule__Grana__JobsAssignment_3 )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1615:1: ( rule__Grana__JobsAssignment_3 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==20||(LA19_0>=25 && LA19_0<=26)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalGrana.g:1615:2: rule__Grana__JobsAssignment_3
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Grana__JobsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // InternalGrana.g:1634:1: rule__Grana__Group_0__0 : rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 ;
    public final void rule__Grana__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1638:1: ( rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 )
            // InternalGrana.g:1639:2: rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1
            {
            pushFollow(FOLLOW_8);
            rule__Grana__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1646:1: rule__Grana__Group_0__0__Impl : ( 'globalResources' ) ;
    public final void rule__Grana__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1650:1: ( ( 'globalResources' ) )
            // InternalGrana.g:1651:1: ( 'globalResources' )
            {
            // InternalGrana.g:1651:1: ( 'globalResources' )
            // InternalGrana.g:1652:1: 'globalResources'
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0()); 
            match(input,17,FOLLOW_2); 
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
    // InternalGrana.g:1665:1: rule__Grana__Group_0__1 : rule__Grana__Group_0__1__Impl ;
    public final void rule__Grana__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1669:1: ( rule__Grana__Group_0__1__Impl )
            // InternalGrana.g:1670:2: rule__Grana__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1676:1: rule__Grana__Group_0__1__Impl : ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) ;
    public final void rule__Grana__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1680:1: ( ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) )
            // InternalGrana.g:1681:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            {
            // InternalGrana.g:1681:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            // InternalGrana.g:1682:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 
            // InternalGrana.g:1683:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalGrana.g:1683:2: rule__Grana__GlobalResourcesAssignment_0_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Grana__GlobalResourcesAssignment_0_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
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
    // InternalGrana.g:1697:1: rule__Grana__Group_1__0 : rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 ;
    public final void rule__Grana__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1701:1: ( rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 )
            // InternalGrana.g:1702:2: rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1
            {
            pushFollow(FOLLOW_8);
            rule__Grana__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1709:1: rule__Grana__Group_1__0__Impl : ( 'globalOutputs' ) ;
    public final void rule__Grana__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1713:1: ( ( 'globalOutputs' ) )
            // InternalGrana.g:1714:1: ( 'globalOutputs' )
            {
            // InternalGrana.g:1714:1: ( 'globalOutputs' )
            // InternalGrana.g:1715:1: 'globalOutputs'
            {
             before(grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0()); 
            match(input,18,FOLLOW_2); 
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
    // InternalGrana.g:1728:1: rule__Grana__Group_1__1 : rule__Grana__Group_1__1__Impl ;
    public final void rule__Grana__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1732:1: ( rule__Grana__Group_1__1__Impl )
            // InternalGrana.g:1733:2: rule__Grana__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1739:1: rule__Grana__Group_1__1__Impl : ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) ;
    public final void rule__Grana__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1743:1: ( ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) )
            // InternalGrana.g:1744:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            {
            // InternalGrana.g:1744:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            // InternalGrana.g:1745:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1()); 
            // InternalGrana.g:1746:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalGrana.g:1746:2: rule__Grana__GloobalOutputsAssignment_1_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Grana__GloobalOutputsAssignment_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
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
    // InternalGrana.g:1760:1: rule__Grana__Group_2__0 : rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 ;
    public final void rule__Grana__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1764:1: ( rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 )
            // InternalGrana.g:1765:2: rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1
            {
            pushFollow(FOLLOW_9);
            rule__Grana__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1772:1: rule__Grana__Group_2__0__Impl : ( 'execute' ) ;
    public final void rule__Grana__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1776:1: ( ( 'execute' ) )
            // InternalGrana.g:1777:1: ( 'execute' )
            {
            // InternalGrana.g:1777:1: ( 'execute' )
            // InternalGrana.g:1778:1: 'execute'
            {
             before(grammarAccess.getGranaAccess().getExecuteKeyword_2_0()); 
            match(input,19,FOLLOW_2); 
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
    // InternalGrana.g:1791:1: rule__Grana__Group_2__1 : rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 ;
    public final void rule__Grana__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1795:1: ( rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 )
            // InternalGrana.g:1796:2: rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2
            {
            pushFollow(FOLLOW_9);
            rule__Grana__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Grana__Group_2__2();

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
    // InternalGrana.g:1803:1: rule__Grana__Group_2__1__Impl : ( ( rule__Grana__ParallelAssignment_2_1 )? ) ;
    public final void rule__Grana__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1807:1: ( ( ( rule__Grana__ParallelAssignment_2_1 )? ) )
            // InternalGrana.g:1808:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            {
            // InternalGrana.g:1808:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            // InternalGrana.g:1809:1: ( rule__Grana__ParallelAssignment_2_1 )?
            {
             before(grammarAccess.getGranaAccess().getParallelAssignment_2_1()); 
            // InternalGrana.g:1810:1: ( rule__Grana__ParallelAssignment_2_1 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==60) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalGrana.g:1810:2: rule__Grana__ParallelAssignment_2_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Grana__ParallelAssignment_2_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getGranaAccess().getParallelAssignment_2_1()); 

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


    // $ANTLR start "rule__Grana__Group_2__2"
    // InternalGrana.g:1820:1: rule__Grana__Group_2__2 : rule__Grana__Group_2__2__Impl ;
    public final void rule__Grana__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1824:1: ( rule__Grana__Group_2__2__Impl )
            // InternalGrana.g:1825:2: rule__Grana__Group_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Grana__Group_2__2__Impl();

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
    // $ANTLR end "rule__Grana__Group_2__2"


    // $ANTLR start "rule__Grana__Group_2__2__Impl"
    // InternalGrana.g:1831:1: rule__Grana__Group_2__2__Impl : ( ( rule__Grana__Alternatives_2_2 ) ) ;
    public final void rule__Grana__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1835:1: ( ( ( rule__Grana__Alternatives_2_2 ) ) )
            // InternalGrana.g:1836:1: ( ( rule__Grana__Alternatives_2_2 ) )
            {
            // InternalGrana.g:1836:1: ( ( rule__Grana__Alternatives_2_2 ) )
            // InternalGrana.g:1837:1: ( rule__Grana__Alternatives_2_2 )
            {
             before(grammarAccess.getGranaAccess().getAlternatives_2_2()); 
            // InternalGrana.g:1838:1: ( rule__Grana__Alternatives_2_2 )
            // InternalGrana.g:1838:2: rule__Grana__Alternatives_2_2
            {
            pushFollow(FOLLOW_2);
            rule__Grana__Alternatives_2_2();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getAlternatives_2_2()); 

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
    // $ANTLR end "rule__Grana__Group_2__2__Impl"


    // $ANTLR start "rule__RegularJob__Group__0"
    // InternalGrana.g:1854:1: rule__RegularJob__Group__0 : rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 ;
    public final void rule__RegularJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1858:1: ( rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 )
            // InternalGrana.g:1859:2: rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__RegularJob__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1866:1: rule__RegularJob__Group__0__Impl : ( 'job' ) ;
    public final void rule__RegularJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1870:1: ( ( 'job' ) )
            // InternalGrana.g:1871:1: ( 'job' )
            {
            // InternalGrana.g:1871:1: ( 'job' )
            // InternalGrana.g:1872:1: 'job'
            {
             before(grammarAccess.getRegularJobAccess().getJobKeyword_0()); 
            match(input,20,FOLLOW_2); 
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
    // InternalGrana.g:1885:1: rule__RegularJob__Group__1 : rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 ;
    public final void rule__RegularJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1889:1: ( rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 )
            // InternalGrana.g:1890:2: rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2
            {
            pushFollow(FOLLOW_10);
            rule__RegularJob__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1897:1: rule__RegularJob__Group__1__Impl : ( ( rule__RegularJob__NameAssignment_1 ) ) ;
    public final void rule__RegularJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1901:1: ( ( ( rule__RegularJob__NameAssignment_1 ) ) )
            // InternalGrana.g:1902:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:1902:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            // InternalGrana.g:1903:1: ( rule__RegularJob__NameAssignment_1 )
            {
             before(grammarAccess.getRegularJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:1904:1: ( rule__RegularJob__NameAssignment_1 )
            // InternalGrana.g:1904:2: rule__RegularJob__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1914:1: rule__RegularJob__Group__2 : rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 ;
    public final void rule__RegularJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1918:1: ( rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 )
            // InternalGrana.g:1919:2: rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__RegularJob__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1926:1: rule__RegularJob__Group__2__Impl : ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) ;
    public final void rule__RegularJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1930:1: ( ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) )
            // InternalGrana.g:1931:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            {
            // InternalGrana.g:1931:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            // InternalGrana.g:1932:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisAssignment_2()); 
            // InternalGrana.g:1933:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==62) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalGrana.g:1933:2: rule__RegularJob__LayoutBeforeAnalysisAssignment_2
                    {
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1943:1: rule__RegularJob__Group__3 : rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 ;
    public final void rule__RegularJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1947:1: ( rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 )
            // InternalGrana.g:1948:2: rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4
            {
            pushFollow(FOLLOW_10);
            rule__RegularJob__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1955:1: rule__RegularJob__Group__3__Impl : ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) ;
    public final void rule__RegularJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1959:1: ( ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) )
            // InternalGrana.g:1960:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            {
            // InternalGrana.g:1960:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            // InternalGrana.g:1961:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeAssignment_3()); 
            // InternalGrana.g:1962:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==63) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalGrana.g:1962:2: rule__RegularJob__MeasureExecutionTimeAssignment_3
                    {
                    pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1972:1: rule__RegularJob__Group__4 : rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 ;
    public final void rule__RegularJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1976:1: ( rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 )
            // InternalGrana.g:1977:2: rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5
            {
            pushFollow(FOLLOW_11);
            rule__RegularJob__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:1984:1: rule__RegularJob__Group__4__Impl : ( 'resources' ) ;
    public final void rule__RegularJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1988:1: ( ( 'resources' ) )
            // InternalGrana.g:1989:1: ( 'resources' )
            {
            // InternalGrana.g:1989:1: ( 'resources' )
            // InternalGrana.g:1990:1: 'resources'
            {
             before(grammarAccess.getRegularJobAccess().getResourcesKeyword_4()); 
            match(input,21,FOLLOW_2); 
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
    // InternalGrana.g:2003:1: rule__RegularJob__Group__5 : rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 ;
    public final void rule__RegularJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2007:1: ( rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 )
            // InternalGrana.g:2008:2: rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6
            {
            pushFollow(FOLLOW_12);
            rule__RegularJob__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2015:1: rule__RegularJob__Group__5__Impl : ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) ;
    public final void rule__RegularJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2019:1: ( ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) )
            // InternalGrana.g:2020:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            {
            // InternalGrana.g:2020:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            // InternalGrana.g:2021:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            {
            // InternalGrana.g:2021:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) )
            // InternalGrana.g:2022:1: ( rule__RegularJob__ResourcesAssignment_5 )
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:2023:1: ( rule__RegularJob__ResourcesAssignment_5 )
            // InternalGrana.g:2023:2: rule__RegularJob__ResourcesAssignment_5
            {
            pushFollow(FOLLOW_13);
            rule__RegularJob__ResourcesAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 

            }

            // InternalGrana.g:2026:1: ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            // InternalGrana.g:2027:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:2028:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_STRING||LA25_0==37) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalGrana.g:2028:2: rule__RegularJob__ResourcesAssignment_5
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__RegularJob__ResourcesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
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
    // InternalGrana.g:2039:1: rule__RegularJob__Group__6 : rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 ;
    public final void rule__RegularJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2043:1: ( rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 )
            // InternalGrana.g:2044:2: rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7
            {
            pushFollow(FOLLOW_8);
            rule__RegularJob__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2051:1: rule__RegularJob__Group__6__Impl : ( 'layoutoptions' ) ;
    public final void rule__RegularJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2055:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2056:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2056:1: ( 'layoutoptions' )
            // InternalGrana.g:2057:1: 'layoutoptions'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6()); 
            match(input,22,FOLLOW_2); 
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
    // InternalGrana.g:2070:1: rule__RegularJob__Group__7 : rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 ;
    public final void rule__RegularJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2074:1: ( rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 )
            // InternalGrana.g:2075:2: rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8
            {
            pushFollow(FOLLOW_14);
            rule__RegularJob__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2082:1: rule__RegularJob__Group__7__Impl : ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) ;
    public final void rule__RegularJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2086:1: ( ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) )
            // InternalGrana.g:2087:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            {
            // InternalGrana.g:2087:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            // InternalGrana.g:2088:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            {
            // InternalGrana.g:2088:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) )
            // InternalGrana.g:2089:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:2090:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            // InternalGrana.g:2090:2: rule__RegularJob__LayoutOptionsAssignment_7
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__LayoutOptionsAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 

            }

            // InternalGrana.g:2093:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            // InternalGrana.g:2094:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:2095:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalGrana.g:2095:2: rule__RegularJob__LayoutOptionsAssignment_7
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RegularJob__LayoutOptionsAssignment_7();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
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
    // InternalGrana.g:2106:1: rule__RegularJob__Group__8 : rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 ;
    public final void rule__RegularJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2110:1: ( rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 )
            // InternalGrana.g:2111:2: rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9
            {
            pushFollow(FOLLOW_8);
            rule__RegularJob__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2118:1: rule__RegularJob__Group__8__Impl : ( 'analyses' ) ;
    public final void rule__RegularJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2122:1: ( ( 'analyses' ) )
            // InternalGrana.g:2123:1: ( 'analyses' )
            {
            // InternalGrana.g:2123:1: ( 'analyses' )
            // InternalGrana.g:2124:1: 'analyses'
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesKeyword_8()); 
            match(input,23,FOLLOW_2); 
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
    // InternalGrana.g:2137:1: rule__RegularJob__Group__9 : rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 ;
    public final void rule__RegularJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2141:1: ( rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 )
            // InternalGrana.g:2142:2: rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10
            {
            pushFollow(FOLLOW_15);
            rule__RegularJob__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2149:1: rule__RegularJob__Group__9__Impl : ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) ;
    public final void rule__RegularJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2153:1: ( ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) )
            // InternalGrana.g:2154:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            {
            // InternalGrana.g:2154:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            // InternalGrana.g:2155:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            {
            // InternalGrana.g:2155:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) )
            // InternalGrana.g:2156:1: ( rule__RegularJob__AnalysesAssignment_9 )
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:2157:1: ( rule__RegularJob__AnalysesAssignment_9 )
            // InternalGrana.g:2157:2: rule__RegularJob__AnalysesAssignment_9
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__AnalysesAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 

            }

            // InternalGrana.g:2160:1: ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            // InternalGrana.g:2161:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:2162:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalGrana.g:2162:2: rule__RegularJob__AnalysesAssignment_9
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RegularJob__AnalysesAssignment_9();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
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
    // InternalGrana.g:2173:1: rule__RegularJob__Group__10 : rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 ;
    public final void rule__RegularJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2177:1: ( rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 )
            // InternalGrana.g:2178:2: rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11
            {
            pushFollow(FOLLOW_16);
            rule__RegularJob__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2185:1: rule__RegularJob__Group__10__Impl : ( 'output' ) ;
    public final void rule__RegularJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2189:1: ( ( 'output' ) )
            // InternalGrana.g:2190:1: ( 'output' )
            {
            // InternalGrana.g:2190:1: ( 'output' )
            // InternalGrana.g:2191:1: 'output'
            {
             before(grammarAccess.getRegularJobAccess().getOutputKeyword_10()); 
            match(input,24,FOLLOW_2); 
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
    // InternalGrana.g:2204:1: rule__RegularJob__Group__11 : rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 ;
    public final void rule__RegularJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2208:1: ( rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 )
            // InternalGrana.g:2209:2: rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12
            {
            pushFollow(FOLLOW_16);
            rule__RegularJob__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RegularJob__Group__12();

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
    // InternalGrana.g:2216:1: rule__RegularJob__Group__11__Impl : ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) ;
    public final void rule__RegularJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2220:1: ( ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) )
            // InternalGrana.g:2221:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            {
            // InternalGrana.g:2221:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            // InternalGrana.g:2222:1: ( rule__RegularJob__OutputTypeAssignment_11 )?
            {
             before(grammarAccess.getRegularJobAccess().getOutputTypeAssignment_11()); 
            // InternalGrana.g:2223:1: ( rule__RegularJob__OutputTypeAssignment_11 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=15 && LA28_0<=16)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalGrana.g:2223:2: rule__RegularJob__OutputTypeAssignment_11
                    {
                    pushFollow(FOLLOW_2);
                    rule__RegularJob__OutputTypeAssignment_11();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRegularJobAccess().getOutputTypeAssignment_11()); 

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


    // $ANTLR start "rule__RegularJob__Group__12"
    // InternalGrana.g:2233:1: rule__RegularJob__Group__12 : rule__RegularJob__Group__12__Impl ;
    public final void rule__RegularJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2237:1: ( rule__RegularJob__Group__12__Impl )
            // InternalGrana.g:2238:2: rule__RegularJob__Group__12__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RegularJob__Group__12__Impl();

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
    // $ANTLR end "rule__RegularJob__Group__12"


    // $ANTLR start "rule__RegularJob__Group__12__Impl"
    // InternalGrana.g:2244:1: rule__RegularJob__Group__12__Impl : ( ( rule__RegularJob__OutputAssignment_12 ) ) ;
    public final void rule__RegularJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2248:1: ( ( ( rule__RegularJob__OutputAssignment_12 ) ) )
            // InternalGrana.g:2249:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            {
            // InternalGrana.g:2249:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            // InternalGrana.g:2250:1: ( rule__RegularJob__OutputAssignment_12 )
            {
             before(grammarAccess.getRegularJobAccess().getOutputAssignment_12()); 
            // InternalGrana.g:2251:1: ( rule__RegularJob__OutputAssignment_12 )
            // InternalGrana.g:2251:2: rule__RegularJob__OutputAssignment_12
            {
            pushFollow(FOLLOW_2);
            rule__RegularJob__OutputAssignment_12();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getOutputAssignment_12()); 

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
    // $ANTLR end "rule__RegularJob__Group__12__Impl"


    // $ANTLR start "rule__CompareJob__Group__0"
    // InternalGrana.g:2287:1: rule__CompareJob__Group__0 : rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 ;
    public final void rule__CompareJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2291:1: ( rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 )
            // InternalGrana.g:2292:2: rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__CompareJob__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__1();

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
    // $ANTLR end "rule__CompareJob__Group__0"


    // $ANTLR start "rule__CompareJob__Group__0__Impl"
    // InternalGrana.g:2299:1: rule__CompareJob__Group__0__Impl : ( 'comparejob' ) ;
    public final void rule__CompareJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2303:1: ( ( 'comparejob' ) )
            // InternalGrana.g:2304:1: ( 'comparejob' )
            {
            // InternalGrana.g:2304:1: ( 'comparejob' )
            // InternalGrana.g:2305:1: 'comparejob'
            {
             before(grammarAccess.getCompareJobAccess().getComparejobKeyword_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getCompareJobAccess().getComparejobKeyword_0()); 

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
    // $ANTLR end "rule__CompareJob__Group__0__Impl"


    // $ANTLR start "rule__CompareJob__Group__1"
    // InternalGrana.g:2318:1: rule__CompareJob__Group__1 : rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 ;
    public final void rule__CompareJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2322:1: ( rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 )
            // InternalGrana.g:2323:2: rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2
            {
            pushFollow(FOLLOW_17);
            rule__CompareJob__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__2();

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
    // $ANTLR end "rule__CompareJob__Group__1"


    // $ANTLR start "rule__CompareJob__Group__1__Impl"
    // InternalGrana.g:2330:1: rule__CompareJob__Group__1__Impl : ( ( rule__CompareJob__NameAssignment_1 ) ) ;
    public final void rule__CompareJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2334:1: ( ( ( rule__CompareJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2335:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2335:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            // InternalGrana.g:2336:1: ( rule__CompareJob__NameAssignment_1 )
            {
             before(grammarAccess.getCompareJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2337:1: ( rule__CompareJob__NameAssignment_1 )
            // InternalGrana.g:2337:2: rule__CompareJob__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__CompareJob__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__CompareJob__Group__1__Impl"


    // $ANTLR start "rule__CompareJob__Group__2"
    // InternalGrana.g:2347:1: rule__CompareJob__Group__2 : rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 ;
    public final void rule__CompareJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2351:1: ( rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 )
            // InternalGrana.g:2352:2: rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3
            {
            pushFollow(FOLLOW_11);
            rule__CompareJob__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__3();

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
    // $ANTLR end "rule__CompareJob__Group__2"


    // $ANTLR start "rule__CompareJob__Group__2__Impl"
    // InternalGrana.g:2359:1: rule__CompareJob__Group__2__Impl : ( 'resources' ) ;
    public final void rule__CompareJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2363:1: ( ( 'resources' ) )
            // InternalGrana.g:2364:1: ( 'resources' )
            {
            // InternalGrana.g:2364:1: ( 'resources' )
            // InternalGrana.g:2365:1: 'resources'
            {
             before(grammarAccess.getCompareJobAccess().getResourcesKeyword_2()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getCompareJobAccess().getResourcesKeyword_2()); 

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
    // $ANTLR end "rule__CompareJob__Group__2__Impl"


    // $ANTLR start "rule__CompareJob__Group__3"
    // InternalGrana.g:2378:1: rule__CompareJob__Group__3 : rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 ;
    public final void rule__CompareJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2382:1: ( rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 )
            // InternalGrana.g:2383:2: rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4
            {
            pushFollow(FOLLOW_12);
            rule__CompareJob__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__4();

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
    // $ANTLR end "rule__CompareJob__Group__3"


    // $ANTLR start "rule__CompareJob__Group__3__Impl"
    // InternalGrana.g:2390:1: rule__CompareJob__Group__3__Impl : ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) ;
    public final void rule__CompareJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2394:1: ( ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) )
            // InternalGrana.g:2395:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            {
            // InternalGrana.g:2395:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            // InternalGrana.g:2396:1: ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            {
            // InternalGrana.g:2396:1: ( ( rule__CompareJob__ResourcesAssignment_3 ) )
            // InternalGrana.g:2397:1: ( rule__CompareJob__ResourcesAssignment_3 )
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:2398:1: ( rule__CompareJob__ResourcesAssignment_3 )
            // InternalGrana.g:2398:2: rule__CompareJob__ResourcesAssignment_3
            {
            pushFollow(FOLLOW_13);
            rule__CompareJob__ResourcesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 

            }

            // InternalGrana.g:2401:1: ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            // InternalGrana.g:2402:1: ( rule__CompareJob__ResourcesAssignment_3 )*
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:2403:1: ( rule__CompareJob__ResourcesAssignment_3 )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_STRING||LA29_0==37) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalGrana.g:2403:2: rule__CompareJob__ResourcesAssignment_3
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__CompareJob__ResourcesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

             after(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 

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
    // $ANTLR end "rule__CompareJob__Group__3__Impl"


    // $ANTLR start "rule__CompareJob__Group__4"
    // InternalGrana.g:2414:1: rule__CompareJob__Group__4 : rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 ;
    public final void rule__CompareJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2418:1: ( rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 )
            // InternalGrana.g:2419:2: rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5
            {
            pushFollow(FOLLOW_8);
            rule__CompareJob__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__5();

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
    // $ANTLR end "rule__CompareJob__Group__4"


    // $ANTLR start "rule__CompareJob__Group__4__Impl"
    // InternalGrana.g:2426:1: rule__CompareJob__Group__4__Impl : ( 'layoutoptions' ) ;
    public final void rule__CompareJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2430:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2431:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2431:1: ( 'layoutoptions' )
            // InternalGrana.g:2432:1: 'layoutoptions'
            {
             before(grammarAccess.getCompareJobAccess().getLayoutoptionsKeyword_4()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getCompareJobAccess().getLayoutoptionsKeyword_4()); 

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
    // $ANTLR end "rule__CompareJob__Group__4__Impl"


    // $ANTLR start "rule__CompareJob__Group__5"
    // InternalGrana.g:2445:1: rule__CompareJob__Group__5 : rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 ;
    public final void rule__CompareJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2449:1: ( rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 )
            // InternalGrana.g:2450:2: rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6
            {
            pushFollow(FOLLOW_8);
            rule__CompareJob__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__6();

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
    // $ANTLR end "rule__CompareJob__Group__5"


    // $ANTLR start "rule__CompareJob__Group__5__Impl"
    // InternalGrana.g:2457:1: rule__CompareJob__Group__5__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) ;
    public final void rule__CompareJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2461:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) )
            // InternalGrana.g:2462:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            {
            // InternalGrana.g:2462:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            // InternalGrana.g:2463:1: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_5()); 
            // InternalGrana.g:2464:1: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            // InternalGrana.g:2464:2: rule__CompareJob__LayoutOptionsAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__CompareJob__LayoutOptionsAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_5()); 

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
    // $ANTLR end "rule__CompareJob__Group__5__Impl"


    // $ANTLR start "rule__CompareJob__Group__6"
    // InternalGrana.g:2474:1: rule__CompareJob__Group__6 : rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 ;
    public final void rule__CompareJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2478:1: ( rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 )
            // InternalGrana.g:2479:2: rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7
            {
            pushFollow(FOLLOW_14);
            rule__CompareJob__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__7();

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
    // $ANTLR end "rule__CompareJob__Group__6"


    // $ANTLR start "rule__CompareJob__Group__6__Impl"
    // InternalGrana.g:2486:1: rule__CompareJob__Group__6__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) ;
    public final void rule__CompareJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2490:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) )
            // InternalGrana.g:2491:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            {
            // InternalGrana.g:2491:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2492:1: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2493:1: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2493:2: rule__CompareJob__LayoutOptionsAssignment_6
            {
            pushFollow(FOLLOW_2);
            rule__CompareJob__LayoutOptionsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_6()); 

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
    // $ANTLR end "rule__CompareJob__Group__6__Impl"


    // $ANTLR start "rule__CompareJob__Group__7"
    // InternalGrana.g:2503:1: rule__CompareJob__Group__7 : rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 ;
    public final void rule__CompareJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2507:1: ( rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 )
            // InternalGrana.g:2508:2: rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8
            {
            pushFollow(FOLLOW_8);
            rule__CompareJob__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__8();

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
    // $ANTLR end "rule__CompareJob__Group__7"


    // $ANTLR start "rule__CompareJob__Group__7__Impl"
    // InternalGrana.g:2515:1: rule__CompareJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__CompareJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2519:1: ( ( 'analyses' ) )
            // InternalGrana.g:2520:1: ( 'analyses' )
            {
            // InternalGrana.g:2520:1: ( 'analyses' )
            // InternalGrana.g:2521:1: 'analyses'
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesKeyword_7()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getCompareJobAccess().getAnalysesKeyword_7()); 

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
    // $ANTLR end "rule__CompareJob__Group__7__Impl"


    // $ANTLR start "rule__CompareJob__Group__8"
    // InternalGrana.g:2534:1: rule__CompareJob__Group__8 : rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 ;
    public final void rule__CompareJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2538:1: ( rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 )
            // InternalGrana.g:2539:2: rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9
            {
            pushFollow(FOLLOW_15);
            rule__CompareJob__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__9();

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
    // $ANTLR end "rule__CompareJob__Group__8"


    // $ANTLR start "rule__CompareJob__Group__8__Impl"
    // InternalGrana.g:2546:1: rule__CompareJob__Group__8__Impl : ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__CompareJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2550:1: ( ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2551:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2551:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2552:1: ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2552:1: ( ( rule__CompareJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2553:1: ( rule__CompareJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2554:1: ( rule__CompareJob__AnalysesAssignment_8 )
            // InternalGrana.g:2554:2: rule__CompareJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__CompareJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2557:1: ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2558:1: ( rule__CompareJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2559:1: ( rule__CompareJob__AnalysesAssignment_8 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalGrana.g:2559:2: rule__CompareJob__AnalysesAssignment_8
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__CompareJob__AnalysesAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

             after(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 

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
    // $ANTLR end "rule__CompareJob__Group__8__Impl"


    // $ANTLR start "rule__CompareJob__Group__9"
    // InternalGrana.g:2570:1: rule__CompareJob__Group__9 : rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 ;
    public final void rule__CompareJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2574:1: ( rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 )
            // InternalGrana.g:2575:2: rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10
            {
            pushFollow(FOLLOW_16);
            rule__CompareJob__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__10();

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
    // $ANTLR end "rule__CompareJob__Group__9"


    // $ANTLR start "rule__CompareJob__Group__9__Impl"
    // InternalGrana.g:2582:1: rule__CompareJob__Group__9__Impl : ( 'output' ) ;
    public final void rule__CompareJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2586:1: ( ( 'output' ) )
            // InternalGrana.g:2587:1: ( 'output' )
            {
            // InternalGrana.g:2587:1: ( 'output' )
            // InternalGrana.g:2588:1: 'output'
            {
             before(grammarAccess.getCompareJobAccess().getOutputKeyword_9()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getCompareJobAccess().getOutputKeyword_9()); 

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
    // $ANTLR end "rule__CompareJob__Group__9__Impl"


    // $ANTLR start "rule__CompareJob__Group__10"
    // InternalGrana.g:2601:1: rule__CompareJob__Group__10 : rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 ;
    public final void rule__CompareJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2605:1: ( rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 )
            // InternalGrana.g:2606:2: rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11
            {
            pushFollow(FOLLOW_16);
            rule__CompareJob__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__11();

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
    // $ANTLR end "rule__CompareJob__Group__10"


    // $ANTLR start "rule__CompareJob__Group__10__Impl"
    // InternalGrana.g:2613:1: rule__CompareJob__Group__10__Impl : ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) ;
    public final void rule__CompareJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2617:1: ( ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) )
            // InternalGrana.g:2618:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            {
            // InternalGrana.g:2618:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            // InternalGrana.g:2619:1: ( rule__CompareJob__OutputTypeAssignment_10 )?
            {
             before(grammarAccess.getCompareJobAccess().getOutputTypeAssignment_10()); 
            // InternalGrana.g:2620:1: ( rule__CompareJob__OutputTypeAssignment_10 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=15 && LA31_0<=16)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalGrana.g:2620:2: rule__CompareJob__OutputTypeAssignment_10
                    {
                    pushFollow(FOLLOW_2);
                    rule__CompareJob__OutputTypeAssignment_10();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getCompareJobAccess().getOutputTypeAssignment_10()); 

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
    // $ANTLR end "rule__CompareJob__Group__10__Impl"


    // $ANTLR start "rule__CompareJob__Group__11"
    // InternalGrana.g:2630:1: rule__CompareJob__Group__11 : rule__CompareJob__Group__11__Impl ;
    public final void rule__CompareJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2634:1: ( rule__CompareJob__Group__11__Impl )
            // InternalGrana.g:2635:2: rule__CompareJob__Group__11__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CompareJob__Group__11__Impl();

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
    // $ANTLR end "rule__CompareJob__Group__11"


    // $ANTLR start "rule__CompareJob__Group__11__Impl"
    // InternalGrana.g:2641:1: rule__CompareJob__Group__11__Impl : ( ( rule__CompareJob__OutputAssignment_11 ) ) ;
    public final void rule__CompareJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2645:1: ( ( ( rule__CompareJob__OutputAssignment_11 ) ) )
            // InternalGrana.g:2646:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            {
            // InternalGrana.g:2646:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            // InternalGrana.g:2647:1: ( rule__CompareJob__OutputAssignment_11 )
            {
             before(grammarAccess.getCompareJobAccess().getOutputAssignment_11()); 
            // InternalGrana.g:2648:1: ( rule__CompareJob__OutputAssignment_11 )
            // InternalGrana.g:2648:2: rule__CompareJob__OutputAssignment_11
            {
            pushFollow(FOLLOW_2);
            rule__CompareJob__OutputAssignment_11();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getOutputAssignment_11()); 

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
    // $ANTLR end "rule__CompareJob__Group__11__Impl"


    // $ANTLR start "rule__RangeJob__Group__0"
    // InternalGrana.g:2682:1: rule__RangeJob__Group__0 : rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 ;
    public final void rule__RangeJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2686:1: ( rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 )
            // InternalGrana.g:2687:2: rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__RangeJob__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2694:1: rule__RangeJob__Group__0__Impl : ( 'rangejob' ) ;
    public final void rule__RangeJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2698:1: ( ( 'rangejob' ) )
            // InternalGrana.g:2699:1: ( 'rangejob' )
            {
            // InternalGrana.g:2699:1: ( 'rangejob' )
            // InternalGrana.g:2700:1: 'rangejob'
            {
             before(grammarAccess.getRangeJobAccess().getRangejobKeyword_0()); 
            match(input,26,FOLLOW_2); 
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
    // InternalGrana.g:2713:1: rule__RangeJob__Group__1 : rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 ;
    public final void rule__RangeJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2717:1: ( rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 )
            // InternalGrana.g:2718:2: rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2
            {
            pushFollow(FOLLOW_18);
            rule__RangeJob__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2725:1: rule__RangeJob__Group__1__Impl : ( ( rule__RangeJob__NameAssignment_1 ) ) ;
    public final void rule__RangeJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2729:1: ( ( ( rule__RangeJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2730:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2730:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            // InternalGrana.g:2731:1: ( rule__RangeJob__NameAssignment_1 )
            {
             before(grammarAccess.getRangeJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2732:1: ( rule__RangeJob__NameAssignment_1 )
            // InternalGrana.g:2732:2: rule__RangeJob__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2742:1: rule__RangeJob__Group__2 : rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 ;
    public final void rule__RangeJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2746:1: ( rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 )
            // InternalGrana.g:2747:2: rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3
            {
            pushFollow(FOLLOW_18);
            rule__RangeJob__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2754:1: rule__RangeJob__Group__2__Impl : ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) ;
    public final void rule__RangeJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2758:1: ( ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) )
            // InternalGrana.g:2759:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            {
            // InternalGrana.g:2759:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            // InternalGrana.g:2760:1: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeAssignment_2()); 
            // InternalGrana.g:2761:1: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==63) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalGrana.g:2761:2: rule__RangeJob__MeasureExecutionTimeAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeJob__MeasureExecutionTimeAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeAssignment_2()); 

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
    // InternalGrana.g:2771:1: rule__RangeJob__Group__3 : rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 ;
    public final void rule__RangeJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2775:1: ( rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 )
            // InternalGrana.g:2776:2: rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4
            {
            pushFollow(FOLLOW_11);
            rule__RangeJob__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2783:1: rule__RangeJob__Group__3__Impl : ( 'resources' ) ;
    public final void rule__RangeJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2787:1: ( ( 'resources' ) )
            // InternalGrana.g:2788:1: ( 'resources' )
            {
            // InternalGrana.g:2788:1: ( 'resources' )
            // InternalGrana.g:2789:1: 'resources'
            {
             before(grammarAccess.getRangeJobAccess().getResourcesKeyword_3()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getResourcesKeyword_3()); 

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
    // InternalGrana.g:2802:1: rule__RangeJob__Group__4 : rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 ;
    public final void rule__RangeJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2806:1: ( rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 )
            // InternalGrana.g:2807:2: rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5
            {
            pushFollow(FOLLOW_12);
            rule__RangeJob__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2814:1: rule__RangeJob__Group__4__Impl : ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) ;
    public final void rule__RangeJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2818:1: ( ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) )
            // InternalGrana.g:2819:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            {
            // InternalGrana.g:2819:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            // InternalGrana.g:2820:1: ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            {
            // InternalGrana.g:2820:1: ( ( rule__RangeJob__ResourcesAssignment_4 ) )
            // InternalGrana.g:2821:1: ( rule__RangeJob__ResourcesAssignment_4 )
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2822:1: ( rule__RangeJob__ResourcesAssignment_4 )
            // InternalGrana.g:2822:2: rule__RangeJob__ResourcesAssignment_4
            {
            pushFollow(FOLLOW_13);
            rule__RangeJob__ResourcesAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 

            }

            // InternalGrana.g:2825:1: ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            // InternalGrana.g:2826:1: ( rule__RangeJob__ResourcesAssignment_4 )*
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2827:1: ( rule__RangeJob__ResourcesAssignment_4 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_STRING||LA33_0==37) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalGrana.g:2827:2: rule__RangeJob__ResourcesAssignment_4
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__RangeJob__ResourcesAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

             after(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 

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
    // $ANTLR end "rule__RangeJob__Group__4__Impl"


    // $ANTLR start "rule__RangeJob__Group__5"
    // InternalGrana.g:2838:1: rule__RangeJob__Group__5 : rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 ;
    public final void rule__RangeJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2842:1: ( rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 )
            // InternalGrana.g:2843:2: rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6
            {
            pushFollow(FOLLOW_8);
            rule__RangeJob__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2850:1: rule__RangeJob__Group__5__Impl : ( 'layoutoptions' ) ;
    public final void rule__RangeJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2854:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2855:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2855:1: ( 'layoutoptions' )
            // InternalGrana.g:2856:1: 'layoutoptions'
            {
             before(grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_5()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_5()); 

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
    // InternalGrana.g:2869:1: rule__RangeJob__Group__6 : rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 ;
    public final void rule__RangeJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2873:1: ( rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 )
            // InternalGrana.g:2874:2: rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7
            {
            pushFollow(FOLLOW_14);
            rule__RangeJob__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2881:1: rule__RangeJob__Group__6__Impl : ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) ;
    public final void rule__RangeJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2885:1: ( ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) )
            // InternalGrana.g:2886:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            {
            // InternalGrana.g:2886:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            // InternalGrana.g:2887:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            {
            // InternalGrana.g:2887:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2888:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2889:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2889:2: rule__RangeJob__LayoutOptionsAssignment_6
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__LayoutOptionsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 

            }

            // InternalGrana.g:2892:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            // InternalGrana.g:2893:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2894:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==RULE_ID) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalGrana.g:2894:2: rule__RangeJob__LayoutOptionsAssignment_6
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__LayoutOptionsAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 

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
    // $ANTLR end "rule__RangeJob__Group__6__Impl"


    // $ANTLR start "rule__RangeJob__Group__7"
    // InternalGrana.g:2905:1: rule__RangeJob__Group__7 : rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 ;
    public final void rule__RangeJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2909:1: ( rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 )
            // InternalGrana.g:2910:2: rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8
            {
            pushFollow(FOLLOW_8);
            rule__RangeJob__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2917:1: rule__RangeJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__RangeJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2921:1: ( ( 'analyses' ) )
            // InternalGrana.g:2922:1: ( 'analyses' )
            {
            // InternalGrana.g:2922:1: ( 'analyses' )
            // InternalGrana.g:2923:1: 'analyses'
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesKeyword_7()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getAnalysesKeyword_7()); 

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
    // InternalGrana.g:2936:1: rule__RangeJob__Group__8 : rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 ;
    public final void rule__RangeJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2940:1: ( rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 )
            // InternalGrana.g:2941:2: rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9
            {
            pushFollow(FOLLOW_19);
            rule__RangeJob__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2948:1: rule__RangeJob__Group__8__Impl : ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__RangeJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2952:1: ( ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2953:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2953:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2954:1: ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2954:1: ( ( rule__RangeJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2955:1: ( rule__RangeJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2956:1: ( rule__RangeJob__AnalysesAssignment_8 )
            // InternalGrana.g:2956:2: rule__RangeJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2959:1: ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2960:1: ( rule__RangeJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2961:1: ( rule__RangeJob__AnalysesAssignment_8 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_ID) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalGrana.g:2961:2: rule__RangeJob__AnalysesAssignment_8
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__AnalysesAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

             after(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 

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
    // $ANTLR end "rule__RangeJob__Group__8__Impl"


    // $ANTLR start "rule__RangeJob__Group__9"
    // InternalGrana.g:2972:1: rule__RangeJob__Group__9 : rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 ;
    public final void rule__RangeJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2976:1: ( rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 )
            // InternalGrana.g:2977:2: rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10
            {
            pushFollow(FOLLOW_8);
            rule__RangeJob__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:2984:1: rule__RangeJob__Group__9__Impl : ( 'rangeoption' ) ;
    public final void rule__RangeJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2988:1: ( ( 'rangeoption' ) )
            // InternalGrana.g:2989:1: ( 'rangeoption' )
            {
            // InternalGrana.g:2989:1: ( 'rangeoption' )
            // InternalGrana.g:2990:1: 'rangeoption'
            {
             before(grammarAccess.getRangeJobAccess().getRangeoptionKeyword_9()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getRangeoptionKeyword_9()); 

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
    // InternalGrana.g:3003:1: rule__RangeJob__Group__10 : rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 ;
    public final void rule__RangeJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3007:1: ( rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 )
            // InternalGrana.g:3008:2: rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11
            {
            pushFollow(FOLLOW_20);
            rule__RangeJob__Group__10__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3015:1: rule__RangeJob__Group__10__Impl : ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) ;
    public final void rule__RangeJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3019:1: ( ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) )
            // InternalGrana.g:3020:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            {
            // InternalGrana.g:3020:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            // InternalGrana.g:3021:1: ( rule__RangeJob__RangeOptionAssignment_10 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_10()); 
            // InternalGrana.g:3022:1: ( rule__RangeJob__RangeOptionAssignment_10 )
            // InternalGrana.g:3022:2: rule__RangeJob__RangeOptionAssignment_10
            {
            pushFollow(FOLLOW_2);
            rule__RangeJob__RangeOptionAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_10()); 

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
    // InternalGrana.g:3032:1: rule__RangeJob__Group__11 : rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 ;
    public final void rule__RangeJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3036:1: ( rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 )
            // InternalGrana.g:3037:2: rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12
            {
            pushFollow(FOLLOW_21);
            rule__RangeJob__Group__11__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3044:1: rule__RangeJob__Group__11__Impl : ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) ;
    public final void rule__RangeJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3048:1: ( ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) )
            // InternalGrana.g:3049:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            {
            // InternalGrana.g:3049:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            // InternalGrana.g:3050:1: ( rule__RangeJob__RangeValuesAssignment_11 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_11()); 
            // InternalGrana.g:3051:1: ( rule__RangeJob__RangeValuesAssignment_11 )
            // InternalGrana.g:3051:2: rule__RangeJob__RangeValuesAssignment_11
            {
            pushFollow(FOLLOW_2);
            rule__RangeJob__RangeValuesAssignment_11();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_11()); 

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
    // InternalGrana.g:3061:1: rule__RangeJob__Group__12 : rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 ;
    public final void rule__RangeJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3065:1: ( rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 )
            // InternalGrana.g:3066:2: rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13
            {
            pushFollow(FOLLOW_15);
            rule__RangeJob__Group__12__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3073:1: rule__RangeJob__Group__12__Impl : ( ( rule__RangeJob__Alternatives_12 ) ) ;
    public final void rule__RangeJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3077:1: ( ( ( rule__RangeJob__Alternatives_12 ) ) )
            // InternalGrana.g:3078:1: ( ( rule__RangeJob__Alternatives_12 ) )
            {
            // InternalGrana.g:3078:1: ( ( rule__RangeJob__Alternatives_12 ) )
            // InternalGrana.g:3079:1: ( rule__RangeJob__Alternatives_12 )
            {
             before(grammarAccess.getRangeJobAccess().getAlternatives_12()); 
            // InternalGrana.g:3080:1: ( rule__RangeJob__Alternatives_12 )
            // InternalGrana.g:3080:2: rule__RangeJob__Alternatives_12
            {
            pushFollow(FOLLOW_2);
            rule__RangeJob__Alternatives_12();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getAlternatives_12()); 

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
    // InternalGrana.g:3090:1: rule__RangeJob__Group__13 : rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 ;
    public final void rule__RangeJob__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3094:1: ( rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 )
            // InternalGrana.g:3095:2: rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14
            {
            pushFollow(FOLLOW_16);
            rule__RangeJob__Group__13__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3102:1: rule__RangeJob__Group__13__Impl : ( 'output' ) ;
    public final void rule__RangeJob__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3106:1: ( ( 'output' ) )
            // InternalGrana.g:3107:1: ( 'output' )
            {
            // InternalGrana.g:3107:1: ( 'output' )
            // InternalGrana.g:3108:1: 'output'
            {
             before(grammarAccess.getRangeJobAccess().getOutputKeyword_13()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getOutputKeyword_13()); 

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
    // InternalGrana.g:3121:1: rule__RangeJob__Group__14 : rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 ;
    public final void rule__RangeJob__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3125:1: ( rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 )
            // InternalGrana.g:3126:2: rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15
            {
            pushFollow(FOLLOW_16);
            rule__RangeJob__Group__14__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3133:1: rule__RangeJob__Group__14__Impl : ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) ;
    public final void rule__RangeJob__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3137:1: ( ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) )
            // InternalGrana.g:3138:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            {
            // InternalGrana.g:3138:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            // InternalGrana.g:3139:1: ( rule__RangeJob__OutputTypeAssignment_14 )?
            {
             before(grammarAccess.getRangeJobAccess().getOutputTypeAssignment_14()); 
            // InternalGrana.g:3140:1: ( rule__RangeJob__OutputTypeAssignment_14 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=15 && LA36_0<=16)) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalGrana.g:3140:2: rule__RangeJob__OutputTypeAssignment_14
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeJob__OutputTypeAssignment_14();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRangeJobAccess().getOutputTypeAssignment_14()); 

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
    // InternalGrana.g:3150:1: rule__RangeJob__Group__15 : rule__RangeJob__Group__15__Impl ;
    public final void rule__RangeJob__Group__15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3154:1: ( rule__RangeJob__Group__15__Impl )
            // InternalGrana.g:3155:2: rule__RangeJob__Group__15__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3161:1: rule__RangeJob__Group__15__Impl : ( ( rule__RangeJob__OutputAssignment_15 ) ) ;
    public final void rule__RangeJob__Group__15__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3165:1: ( ( ( rule__RangeJob__OutputAssignment_15 ) ) )
            // InternalGrana.g:3166:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            {
            // InternalGrana.g:3166:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            // InternalGrana.g:3167:1: ( rule__RangeJob__OutputAssignment_15 )
            {
             before(grammarAccess.getRangeJobAccess().getOutputAssignment_15()); 
            // InternalGrana.g:3168:1: ( rule__RangeJob__OutputAssignment_15 )
            // InternalGrana.g:3168:2: rule__RangeJob__OutputAssignment_15
            {
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "rule__RangeJob__Group_12_0__0"
    // InternalGrana.g:3210:1: rule__RangeJob__Group_12_0__0 : rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 ;
    public final void rule__RangeJob__Group_12_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3214:1: ( rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 )
            // InternalGrana.g:3215:2: rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1
            {
            pushFollow(FOLLOW_8);
            rule__RangeJob__Group_12_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeJob__Group_12_0__1();

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
    // $ANTLR end "rule__RangeJob__Group_12_0__0"


    // $ANTLR start "rule__RangeJob__Group_12_0__0__Impl"
    // InternalGrana.g:3222:1: rule__RangeJob__Group_12_0__0__Impl : ( 'rangeanalysis' ) ;
    public final void rule__RangeJob__Group_12_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3226:1: ( ( 'rangeanalysis' ) )
            // InternalGrana.g:3227:1: ( 'rangeanalysis' )
            {
            // InternalGrana.g:3227:1: ( 'rangeanalysis' )
            // InternalGrana.g:3228:1: 'rangeanalysis'
            {
             before(grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_12_0_0()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_12_0_0()); 

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
    // $ANTLR end "rule__RangeJob__Group_12_0__0__Impl"


    // $ANTLR start "rule__RangeJob__Group_12_0__1"
    // InternalGrana.g:3241:1: rule__RangeJob__Group_12_0__1 : rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 ;
    public final void rule__RangeJob__Group_12_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3245:1: ( rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 )
            // InternalGrana.g:3246:2: rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2
            {
            pushFollow(FOLLOW_22);
            rule__RangeJob__Group_12_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeJob__Group_12_0__2();

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
    // $ANTLR end "rule__RangeJob__Group_12_0__1"


    // $ANTLR start "rule__RangeJob__Group_12_0__1__Impl"
    // InternalGrana.g:3253:1: rule__RangeJob__Group_12_0__1__Impl : ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) ;
    public final void rule__RangeJob__Group_12_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3257:1: ( ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) )
            // InternalGrana.g:3258:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            {
            // InternalGrana.g:3258:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            // InternalGrana.g:3259:1: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12_0_1()); 
            // InternalGrana.g:3260:1: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            // InternalGrana.g:3260:2: rule__RangeJob__RangeAnalysisAssignment_12_0_1
            {
            pushFollow(FOLLOW_2);
            rule__RangeJob__RangeAnalysisAssignment_12_0_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12_0_1()); 

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
    // $ANTLR end "rule__RangeJob__Group_12_0__1__Impl"


    // $ANTLR start "rule__RangeJob__Group_12_0__2"
    // InternalGrana.g:3270:1: rule__RangeJob__Group_12_0__2 : rule__RangeJob__Group_12_0__2__Impl ;
    public final void rule__RangeJob__Group_12_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3274:1: ( rule__RangeJob__Group_12_0__2__Impl )
            // InternalGrana.g:3275:2: rule__RangeJob__Group_12_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RangeJob__Group_12_0__2__Impl();

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
    // $ANTLR end "rule__RangeJob__Group_12_0__2"


    // $ANTLR start "rule__RangeJob__Group_12_0__2__Impl"
    // InternalGrana.g:3281:1: rule__RangeJob__Group_12_0__2__Impl : ( ( rule__RangeJob__Group_12_0_2__0 )? ) ;
    public final void rule__RangeJob__Group_12_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3285:1: ( ( ( rule__RangeJob__Group_12_0_2__0 )? ) )
            // InternalGrana.g:3286:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            {
            // InternalGrana.g:3286:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            // InternalGrana.g:3287:1: ( rule__RangeJob__Group_12_0_2__0 )?
            {
             before(grammarAccess.getRangeJobAccess().getGroup_12_0_2()); 
            // InternalGrana.g:3288:1: ( rule__RangeJob__Group_12_0_2__0 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==29) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalGrana.g:3288:2: rule__RangeJob__Group_12_0_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RangeJob__Group_12_0_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRangeJobAccess().getGroup_12_0_2()); 

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
    // $ANTLR end "rule__RangeJob__Group_12_0__2__Impl"


    // $ANTLR start "rule__RangeJob__Group_12_0_2__0"
    // InternalGrana.g:3304:1: rule__RangeJob__Group_12_0_2__0 : rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 ;
    public final void rule__RangeJob__Group_12_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3308:1: ( rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 )
            // InternalGrana.g:3309:2: rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1
            {
            pushFollow(FOLLOW_23);
            rule__RangeJob__Group_12_0_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeJob__Group_12_0_2__1();

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
    // $ANTLR end "rule__RangeJob__Group_12_0_2__0"


    // $ANTLR start "rule__RangeJob__Group_12_0_2__0__Impl"
    // InternalGrana.g:3316:1: rule__RangeJob__Group_12_0_2__0__Impl : ( 'component' ) ;
    public final void rule__RangeJob__Group_12_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3320:1: ( ( 'component' ) )
            // InternalGrana.g:3321:1: ( 'component' )
            {
            // InternalGrana.g:3321:1: ( 'component' )
            // InternalGrana.g:3322:1: 'component'
            {
             before(grammarAccess.getRangeJobAccess().getComponentKeyword_12_0_2_0()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getComponentKeyword_12_0_2_0()); 

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
    // $ANTLR end "rule__RangeJob__Group_12_0_2__0__Impl"


    // $ANTLR start "rule__RangeJob__Group_12_0_2__1"
    // InternalGrana.g:3335:1: rule__RangeJob__Group_12_0_2__1 : rule__RangeJob__Group_12_0_2__1__Impl ;
    public final void rule__RangeJob__Group_12_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3339:1: ( rule__RangeJob__Group_12_0_2__1__Impl )
            // InternalGrana.g:3340:2: rule__RangeJob__Group_12_0_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RangeJob__Group_12_0_2__1__Impl();

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
    // $ANTLR end "rule__RangeJob__Group_12_0_2__1"


    // $ANTLR start "rule__RangeJob__Group_12_0_2__1__Impl"
    // InternalGrana.g:3346:1: rule__RangeJob__Group_12_0_2__1__Impl : ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) ;
    public final void rule__RangeJob__Group_12_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3350:1: ( ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) )
            // InternalGrana.g:3351:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            {
            // InternalGrana.g:3351:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            // InternalGrana.g:3352:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_12_0_2_1()); 
            // InternalGrana.g:3353:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            // InternalGrana.g:3353:2: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1
            {
            pushFollow(FOLLOW_2);
            rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_12_0_2_1()); 

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
    // $ANTLR end "rule__RangeJob__Group_12_0_2__1__Impl"


    // $ANTLR start "rule__RangeJob__Group_12_1__0"
    // InternalGrana.g:3367:1: rule__RangeJob__Group_12_1__0 : rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 ;
    public final void rule__RangeJob__Group_12_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3371:1: ( rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 )
            // InternalGrana.g:3372:2: rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1
            {
            pushFollow(FOLLOW_8);
            rule__RangeJob__Group_12_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RangeJob__Group_12_1__1();

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
    // $ANTLR end "rule__RangeJob__Group_12_1__0"


    // $ANTLR start "rule__RangeJob__Group_12_1__0__Impl"
    // InternalGrana.g:3379:1: rule__RangeJob__Group_12_1__0__Impl : ( 'rangeanalyses' ) ;
    public final void rule__RangeJob__Group_12_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3383:1: ( ( 'rangeanalyses' ) )
            // InternalGrana.g:3384:1: ( 'rangeanalyses' )
            {
            // InternalGrana.g:3384:1: ( 'rangeanalyses' )
            // InternalGrana.g:3385:1: 'rangeanalyses'
            {
             before(grammarAccess.getRangeJobAccess().getRangeanalysesKeyword_12_1_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getRangeanalysesKeyword_12_1_0()); 

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
    // $ANTLR end "rule__RangeJob__Group_12_1__0__Impl"


    // $ANTLR start "rule__RangeJob__Group_12_1__1"
    // InternalGrana.g:3398:1: rule__RangeJob__Group_12_1__1 : rule__RangeJob__Group_12_1__1__Impl ;
    public final void rule__RangeJob__Group_12_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3402:1: ( rule__RangeJob__Group_12_1__1__Impl )
            // InternalGrana.g:3403:2: rule__RangeJob__Group_12_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RangeJob__Group_12_1__1__Impl();

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
    // $ANTLR end "rule__RangeJob__Group_12_1__1"


    // $ANTLR start "rule__RangeJob__Group_12_1__1__Impl"
    // InternalGrana.g:3409:1: rule__RangeJob__Group_12_1__1__Impl : ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) ;
    public final void rule__RangeJob__Group_12_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3413:1: ( ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) )
            // InternalGrana.g:3414:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            {
            // InternalGrana.g:3414:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            // InternalGrana.g:3415:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            {
            // InternalGrana.g:3415:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) )
            // InternalGrana.g:3416:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:3417:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            // InternalGrana.g:3417:2: rule__RangeJob__RangeAnalysesAssignment_12_1_1
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__RangeAnalysesAssignment_12_1_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 

            }

            // InternalGrana.g:3420:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            // InternalGrana.g:3421:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:3422:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==RULE_ID) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalGrana.g:3422:2: rule__RangeJob__RangeAnalysesAssignment_12_1_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__RangeJob__RangeAnalysesAssignment_12_1_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

             after(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 

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
    // $ANTLR end "rule__RangeJob__Group_12_1__1__Impl"


    // $ANTLR start "rule__FloatRange__Group__0"
    // InternalGrana.g:3437:1: rule__FloatRange__Group__0 : rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 ;
    public final void rule__FloatRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3441:1: ( rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 )
            // InternalGrana.g:3442:2: rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1
            {
            pushFollow(FOLLOW_24);
            rule__FloatRange__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3449:1: rule__FloatRange__Group__0__Impl : ( 'floatvalues' ) ;
    public final void rule__FloatRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3453:1: ( ( 'floatvalues' ) )
            // InternalGrana.g:3454:1: ( 'floatvalues' )
            {
            // InternalGrana.g:3454:1: ( 'floatvalues' )
            // InternalGrana.g:3455:1: 'floatvalues'
            {
             before(grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0()); 
            match(input,31,FOLLOW_2); 
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
    // InternalGrana.g:3468:1: rule__FloatRange__Group__1 : rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 ;
    public final void rule__FloatRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3472:1: ( rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 )
            // InternalGrana.g:3473:2: rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2
            {
            pushFollow(FOLLOW_25);
            rule__FloatRange__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3480:1: rule__FloatRange__Group__1__Impl : ( ( rule__FloatRange__ValuesAssignment_1 ) ) ;
    public final void rule__FloatRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3484:1: ( ( ( rule__FloatRange__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3485:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3485:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            // InternalGrana.g:3486:1: ( rule__FloatRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3487:1: ( rule__FloatRange__ValuesAssignment_1 )
            // InternalGrana.g:3487:2: rule__FloatRange__ValuesAssignment_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3497:1: rule__FloatRange__Group__2 : rule__FloatRange__Group__2__Impl ;
    public final void rule__FloatRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3501:1: ( rule__FloatRange__Group__2__Impl )
            // InternalGrana.g:3502:2: rule__FloatRange__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3508:1: rule__FloatRange__Group__2__Impl : ( ( rule__FloatRange__Group_2__0 )* ) ;
    public final void rule__FloatRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3512:1: ( ( ( rule__FloatRange__Group_2__0 )* ) )
            // InternalGrana.g:3513:1: ( ( rule__FloatRange__Group_2__0 )* )
            {
            // InternalGrana.g:3513:1: ( ( rule__FloatRange__Group_2__0 )* )
            // InternalGrana.g:3514:1: ( rule__FloatRange__Group_2__0 )*
            {
             before(grammarAccess.getFloatRangeAccess().getGroup_2()); 
            // InternalGrana.g:3515:1: ( rule__FloatRange__Group_2__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==32) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalGrana.g:3515:2: rule__FloatRange__Group_2__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__FloatRange__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
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
    // InternalGrana.g:3531:1: rule__FloatRange__Group_2__0 : rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 ;
    public final void rule__FloatRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3535:1: ( rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 )
            // InternalGrana.g:3536:2: rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1
            {
            pushFollow(FOLLOW_24);
            rule__FloatRange__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3543:1: rule__FloatRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__FloatRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3547:1: ( ( ',' ) )
            // InternalGrana.g:3548:1: ( ',' )
            {
            // InternalGrana.g:3548:1: ( ',' )
            // InternalGrana.g:3549:1: ','
            {
             before(grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0()); 
            match(input,32,FOLLOW_2); 
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
    // InternalGrana.g:3562:1: rule__FloatRange__Group_2__1 : rule__FloatRange__Group_2__1__Impl ;
    public final void rule__FloatRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3566:1: ( rule__FloatRange__Group_2__1__Impl )
            // InternalGrana.g:3567:2: rule__FloatRange__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3573:1: rule__FloatRange__Group_2__1__Impl : ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__FloatRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3577:1: ( ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3578:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3578:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3579:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3580:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            // InternalGrana.g:3580:2: rule__FloatRange__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3594:1: rule__IntRangeValues__Group__0 : rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 ;
    public final void rule__IntRangeValues__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3598:1: ( rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 )
            // InternalGrana.g:3599:2: rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__IntRangeValues__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3606:1: rule__IntRangeValues__Group__0__Impl : ( 'intvalues' ) ;
    public final void rule__IntRangeValues__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3610:1: ( ( 'intvalues' ) )
            // InternalGrana.g:3611:1: ( 'intvalues' )
            {
            // InternalGrana.g:3611:1: ( 'intvalues' )
            // InternalGrana.g:3612:1: 'intvalues'
            {
             before(grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:3625:1: rule__IntRangeValues__Group__1 : rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 ;
    public final void rule__IntRangeValues__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3629:1: ( rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 )
            // InternalGrana.g:3630:2: rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2
            {
            pushFollow(FOLLOW_25);
            rule__IntRangeValues__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3637:1: rule__IntRangeValues__Group__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) ;
    public final void rule__IntRangeValues__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3641:1: ( ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3642:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3642:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            // InternalGrana.g:3643:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3644:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            // InternalGrana.g:3644:2: rule__IntRangeValues__ValuesAssignment_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3654:1: rule__IntRangeValues__Group__2 : rule__IntRangeValues__Group__2__Impl ;
    public final void rule__IntRangeValues__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3658:1: ( rule__IntRangeValues__Group__2__Impl )
            // InternalGrana.g:3659:2: rule__IntRangeValues__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3665:1: rule__IntRangeValues__Group__2__Impl : ( ( rule__IntRangeValues__Group_2__0 )* ) ;
    public final void rule__IntRangeValues__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3669:1: ( ( ( rule__IntRangeValues__Group_2__0 )* ) )
            // InternalGrana.g:3670:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            {
            // InternalGrana.g:3670:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            // InternalGrana.g:3671:1: ( rule__IntRangeValues__Group_2__0 )*
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup_2()); 
            // InternalGrana.g:3672:1: ( rule__IntRangeValues__Group_2__0 )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==32) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalGrana.g:3672:2: rule__IntRangeValues__Group_2__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__IntRangeValues__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

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
    // InternalGrana.g:3688:1: rule__IntRangeValues__Group_2__0 : rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 ;
    public final void rule__IntRangeValues__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3692:1: ( rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 )
            // InternalGrana.g:3693:2: rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1
            {
            pushFollow(FOLLOW_23);
            rule__IntRangeValues__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3700:1: rule__IntRangeValues__Group_2__0__Impl : ( ',' ) ;
    public final void rule__IntRangeValues__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3704:1: ( ( ',' ) )
            // InternalGrana.g:3705:1: ( ',' )
            {
            // InternalGrana.g:3705:1: ( ',' )
            // InternalGrana.g:3706:1: ','
            {
             before(grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0()); 
            match(input,32,FOLLOW_2); 
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
    // InternalGrana.g:3719:1: rule__IntRangeValues__Group_2__1 : rule__IntRangeValues__Group_2__1__Impl ;
    public final void rule__IntRangeValues__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3723:1: ( rule__IntRangeValues__Group_2__1__Impl )
            // InternalGrana.g:3724:2: rule__IntRangeValues__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3730:1: rule__IntRangeValues__Group_2__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) ;
    public final void rule__IntRangeValues__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3734:1: ( ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3735:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3735:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3736:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3737:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            // InternalGrana.g:3737:2: rule__IntRangeValues__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3751:1: rule__IntRangeRange__Group__0 : rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 ;
    public final void rule__IntRangeRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3755:1: ( rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 )
            // InternalGrana.g:3756:2: rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1
            {
            pushFollow(FOLLOW_23);
            rule__IntRangeRange__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3763:1: rule__IntRangeRange__Group__0__Impl : ( 'intrange' ) ;
    public final void rule__IntRangeRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3767:1: ( ( 'intrange' ) )
            // InternalGrana.g:3768:1: ( 'intrange' )
            {
            // InternalGrana.g:3768:1: ( 'intrange' )
            // InternalGrana.g:3769:1: 'intrange'
            {
             before(grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0()); 
            match(input,34,FOLLOW_2); 
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
    // InternalGrana.g:3782:1: rule__IntRangeRange__Group__1 : rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 ;
    public final void rule__IntRangeRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3786:1: ( rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 )
            // InternalGrana.g:3787:2: rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2
            {
            pushFollow(FOLLOW_27);
            rule__IntRangeRange__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3794:1: rule__IntRangeRange__Group__1__Impl : ( ( rule__IntRangeRange__StartAssignment_1 ) ) ;
    public final void rule__IntRangeRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3798:1: ( ( ( rule__IntRangeRange__StartAssignment_1 ) ) )
            // InternalGrana.g:3799:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            {
            // InternalGrana.g:3799:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            // InternalGrana.g:3800:1: ( rule__IntRangeRange__StartAssignment_1 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartAssignment_1()); 
            // InternalGrana.g:3801:1: ( rule__IntRangeRange__StartAssignment_1 )
            // InternalGrana.g:3801:2: rule__IntRangeRange__StartAssignment_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3811:1: rule__IntRangeRange__Group__2 : rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 ;
    public final void rule__IntRangeRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3815:1: ( rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 )
            // InternalGrana.g:3816:2: rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3
            {
            pushFollow(FOLLOW_23);
            rule__IntRangeRange__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3823:1: rule__IntRangeRange__Group__2__Impl : ( 'to' ) ;
    public final void rule__IntRangeRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3827:1: ( ( 'to' ) )
            // InternalGrana.g:3828:1: ( 'to' )
            {
            // InternalGrana.g:3828:1: ( 'to' )
            // InternalGrana.g:3829:1: 'to'
            {
             before(grammarAccess.getIntRangeRangeAccess().getToKeyword_2()); 
            match(input,35,FOLLOW_2); 
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
    // InternalGrana.g:3842:1: rule__IntRangeRange__Group__3 : rule__IntRangeRange__Group__3__Impl ;
    public final void rule__IntRangeRange__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3846:1: ( rule__IntRangeRange__Group__3__Impl )
            // InternalGrana.g:3847:2: rule__IntRangeRange__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:3853:1: rule__IntRangeRange__Group__3__Impl : ( ( rule__IntRangeRange__EndAssignment_3 ) ) ;
    public final void rule__IntRangeRange__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3857:1: ( ( ( rule__IntRangeRange__EndAssignment_3 ) ) )
            // InternalGrana.g:3858:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            {
            // InternalGrana.g:3858:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            // InternalGrana.g:3859:1: ( rule__IntRangeRange__EndAssignment_3 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndAssignment_3()); 
            // InternalGrana.g:3860:1: ( rule__IntRangeRange__EndAssignment_3 )
            // InternalGrana.g:3860:2: rule__IntRangeRange__EndAssignment_3
            {
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "rule__EnumRange__Group__0"
    // InternalGrana.g:3878:1: rule__EnumRange__Group__0 : rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1 ;
    public final void rule__EnumRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3882:1: ( rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1 )
            // InternalGrana.g:3883:2: rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__EnumRange__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumRange__Group__1();

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
    // $ANTLR end "rule__EnumRange__Group__0"


    // $ANTLR start "rule__EnumRange__Group__0__Impl"
    // InternalGrana.g:3890:1: rule__EnumRange__Group__0__Impl : ( 'enumvalues' ) ;
    public final void rule__EnumRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3894:1: ( ( 'enumvalues' ) )
            // InternalGrana.g:3895:1: ( 'enumvalues' )
            {
            // InternalGrana.g:3895:1: ( 'enumvalues' )
            // InternalGrana.g:3896:1: 'enumvalues'
            {
             before(grammarAccess.getEnumRangeAccess().getEnumvaluesKeyword_0()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getEnumRangeAccess().getEnumvaluesKeyword_0()); 

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
    // $ANTLR end "rule__EnumRange__Group__0__Impl"


    // $ANTLR start "rule__EnumRange__Group__1"
    // InternalGrana.g:3909:1: rule__EnumRange__Group__1 : rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2 ;
    public final void rule__EnumRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3913:1: ( rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2 )
            // InternalGrana.g:3914:2: rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2
            {
            pushFollow(FOLLOW_25);
            rule__EnumRange__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumRange__Group__2();

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
    // $ANTLR end "rule__EnumRange__Group__1"


    // $ANTLR start "rule__EnumRange__Group__1__Impl"
    // InternalGrana.g:3921:1: rule__EnumRange__Group__1__Impl : ( ( rule__EnumRange__ValuesAssignment_1 ) ) ;
    public final void rule__EnumRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3925:1: ( ( ( rule__EnumRange__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3926:1: ( ( rule__EnumRange__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3926:1: ( ( rule__EnumRange__ValuesAssignment_1 ) )
            // InternalGrana.g:3927:1: ( rule__EnumRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getEnumRangeAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3928:1: ( rule__EnumRange__ValuesAssignment_1 )
            // InternalGrana.g:3928:2: rule__EnumRange__ValuesAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__EnumRange__ValuesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getEnumRangeAccess().getValuesAssignment_1()); 

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
    // $ANTLR end "rule__EnumRange__Group__1__Impl"


    // $ANTLR start "rule__EnumRange__Group__2"
    // InternalGrana.g:3938:1: rule__EnumRange__Group__2 : rule__EnumRange__Group__2__Impl ;
    public final void rule__EnumRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3942:1: ( rule__EnumRange__Group__2__Impl )
            // InternalGrana.g:3943:2: rule__EnumRange__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EnumRange__Group__2__Impl();

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
    // $ANTLR end "rule__EnumRange__Group__2"


    // $ANTLR start "rule__EnumRange__Group__2__Impl"
    // InternalGrana.g:3949:1: rule__EnumRange__Group__2__Impl : ( ( rule__EnumRange__Group_2__0 )* ) ;
    public final void rule__EnumRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3953:1: ( ( ( rule__EnumRange__Group_2__0 )* ) )
            // InternalGrana.g:3954:1: ( ( rule__EnumRange__Group_2__0 )* )
            {
            // InternalGrana.g:3954:1: ( ( rule__EnumRange__Group_2__0 )* )
            // InternalGrana.g:3955:1: ( rule__EnumRange__Group_2__0 )*
            {
             before(grammarAccess.getEnumRangeAccess().getGroup_2()); 
            // InternalGrana.g:3956:1: ( rule__EnumRange__Group_2__0 )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==32) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalGrana.g:3956:2: rule__EnumRange__Group_2__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__EnumRange__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

             after(grammarAccess.getEnumRangeAccess().getGroup_2()); 

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
    // $ANTLR end "rule__EnumRange__Group__2__Impl"


    // $ANTLR start "rule__EnumRange__Group_2__0"
    // InternalGrana.g:3972:1: rule__EnumRange__Group_2__0 : rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1 ;
    public final void rule__EnumRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3976:1: ( rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1 )
            // InternalGrana.g:3977:2: rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1
            {
            pushFollow(FOLLOW_8);
            rule__EnumRange__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EnumRange__Group_2__1();

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
    // $ANTLR end "rule__EnumRange__Group_2__0"


    // $ANTLR start "rule__EnumRange__Group_2__0__Impl"
    // InternalGrana.g:3984:1: rule__EnumRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__EnumRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3988:1: ( ( ',' ) )
            // InternalGrana.g:3989:1: ( ',' )
            {
            // InternalGrana.g:3989:1: ( ',' )
            // InternalGrana.g:3990:1: ','
            {
             before(grammarAccess.getEnumRangeAccess().getCommaKeyword_2_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getEnumRangeAccess().getCommaKeyword_2_0()); 

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
    // $ANTLR end "rule__EnumRange__Group_2__0__Impl"


    // $ANTLR start "rule__EnumRange__Group_2__1"
    // InternalGrana.g:4003:1: rule__EnumRange__Group_2__1 : rule__EnumRange__Group_2__1__Impl ;
    public final void rule__EnumRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4007:1: ( rule__EnumRange__Group_2__1__Impl )
            // InternalGrana.g:4008:2: rule__EnumRange__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EnumRange__Group_2__1__Impl();

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
    // $ANTLR end "rule__EnumRange__Group_2__1"


    // $ANTLR start "rule__EnumRange__Group_2__1__Impl"
    // InternalGrana.g:4014:1: rule__EnumRange__Group_2__1__Impl : ( ( rule__EnumRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__EnumRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4018:1: ( ( ( rule__EnumRange__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:4019:1: ( ( rule__EnumRange__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:4019:1: ( ( rule__EnumRange__ValuesAssignment_2_1 ) )
            // InternalGrana.g:4020:1: ( rule__EnumRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getEnumRangeAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:4021:1: ( rule__EnumRange__ValuesAssignment_2_1 )
            // InternalGrana.g:4021:2: rule__EnumRange__ValuesAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__EnumRange__ValuesAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getEnumRangeAccess().getValuesAssignment_2_1()); 

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
    // $ANTLR end "rule__EnumRange__Group_2__1__Impl"


    // $ANTLR start "rule__ResourceReference__Group__0"
    // InternalGrana.g:4035:1: rule__ResourceReference__Group__0 : rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 ;
    public final void rule__ResourceReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4039:1: ( rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 )
            // InternalGrana.g:4040:2: rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__ResourceReference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4047:1: rule__ResourceReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__ResourceReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4051:1: ( ( 'ref' ) )
            // InternalGrana.g:4052:1: ( 'ref' )
            {
            // InternalGrana.g:4052:1: ( 'ref' )
            // InternalGrana.g:4053:1: 'ref'
            {
             before(grammarAccess.getResourceReferenceAccess().getRefKeyword_0()); 
            match(input,37,FOLLOW_2); 
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
    // InternalGrana.g:4066:1: rule__ResourceReference__Group__1 : rule__ResourceReference__Group__1__Impl ;
    public final void rule__ResourceReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4070:1: ( rule__ResourceReference__Group__1__Impl )
            // InternalGrana.g:4071:2: rule__ResourceReference__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4077:1: rule__ResourceReference__Group__1__Impl : ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) ;
    public final void rule__ResourceReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4081:1: ( ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) )
            // InternalGrana.g:4082:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            {
            // InternalGrana.g:4082:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            // InternalGrana.g:4083:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            {
            // InternalGrana.g:4083:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) )
            // InternalGrana.g:4084:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:4085:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            // InternalGrana.g:4085:2: rule__ResourceReference__ResourceRefsAssignment_1
            {
            pushFollow(FOLLOW_3);
            rule__ResourceReference__ResourceRefsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }

            // InternalGrana.g:4088:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            // InternalGrana.g:4089:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:4090:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==RULE_ID) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalGrana.g:4090:2: rule__ResourceReference__ResourceRefsAssignment_1
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ResourceReference__ResourceRefsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop42;
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
    // InternalGrana.g:4105:1: rule__GlobalResourceRef__Group__0 : rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 ;
    public final void rule__GlobalResourceRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4109:1: ( rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 )
            // InternalGrana.g:4110:2: rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__GlobalResourceRef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4117:1: rule__GlobalResourceRef__Group__0__Impl : ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalResourceRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4121:1: ( ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) )
            // InternalGrana.g:4122:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:4122:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            // InternalGrana.g:4123:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:4124:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            // InternalGrana.g:4124:2: rule__GlobalResourceRef__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4134:1: rule__GlobalResourceRef__Group__1 : rule__GlobalResourceRef__Group__1__Impl ;
    public final void rule__GlobalResourceRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4138:1: ( rule__GlobalResourceRef__Group__1__Impl )
            // InternalGrana.g:4139:2: rule__GlobalResourceRef__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4145:1: rule__GlobalResourceRef__Group__1__Impl : ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) ;
    public final void rule__GlobalResourceRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4149:1: ( ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) )
            // InternalGrana.g:4150:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            {
            // InternalGrana.g:4150:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            // InternalGrana.g:4151:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 
            // InternalGrana.g:4152:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            // InternalGrana.g:4152:2: rule__GlobalResourceRef__ResourcesAssignment_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4166:1: rule__LocalResource__Group__0 : rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 ;
    public final void rule__LocalResource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4170:1: ( rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 )
            // InternalGrana.g:4171:2: rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1
            {
            pushFollow(FOLLOW_28);
            rule__LocalResource__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4178:1: rule__LocalResource__Group__0__Impl : ( ( rule__LocalResource__PathAssignment_0 ) ) ;
    public final void rule__LocalResource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4182:1: ( ( ( rule__LocalResource__PathAssignment_0 ) ) )
            // InternalGrana.g:4183:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            {
            // InternalGrana.g:4183:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            // InternalGrana.g:4184:1: ( rule__LocalResource__PathAssignment_0 )
            {
             before(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 
            // InternalGrana.g:4185:1: ( rule__LocalResource__PathAssignment_0 )
            // InternalGrana.g:4185:2: rule__LocalResource__PathAssignment_0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4195:1: rule__LocalResource__Group__1 : rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2 ;
    public final void rule__LocalResource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4199:1: ( rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2 )
            // InternalGrana.g:4200:2: rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2
            {
            pushFollow(FOLLOW_29);
            rule__LocalResource__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__LocalResource__Group__2();

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
    // InternalGrana.g:4207:1: rule__LocalResource__Group__1__Impl : ( ( rule__LocalResource__Group_1__0 ) ) ;
    public final void rule__LocalResource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4211:1: ( ( ( rule__LocalResource__Group_1__0 ) ) )
            // InternalGrana.g:4212:1: ( ( rule__LocalResource__Group_1__0 ) )
            {
            // InternalGrana.g:4212:1: ( ( rule__LocalResource__Group_1__0 ) )
            // InternalGrana.g:4213:1: ( rule__LocalResource__Group_1__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup_1()); 
            // InternalGrana.g:4214:1: ( rule__LocalResource__Group_1__0 )
            // InternalGrana.g:4214:2: rule__LocalResource__Group_1__0
            {
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "rule__LocalResource__Group__2"
    // InternalGrana.g:4224:1: rule__LocalResource__Group__2 : rule__LocalResource__Group__2__Impl ;
    public final void rule__LocalResource__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4228:1: ( rule__LocalResource__Group__2__Impl )
            // InternalGrana.g:4229:2: rule__LocalResource__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__LocalResource__Group__2__Impl();

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
    // $ANTLR end "rule__LocalResource__Group__2"


    // $ANTLR start "rule__LocalResource__Group__2__Impl"
    // InternalGrana.g:4235:1: rule__LocalResource__Group__2__Impl : ( ( rule__LocalResource__RecurseAssignment_2 )? ) ;
    public final void rule__LocalResource__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4239:1: ( ( ( rule__LocalResource__RecurseAssignment_2 )? ) )
            // InternalGrana.g:4240:1: ( ( rule__LocalResource__RecurseAssignment_2 )? )
            {
            // InternalGrana.g:4240:1: ( ( rule__LocalResource__RecurseAssignment_2 )? )
            // InternalGrana.g:4241:1: ( rule__LocalResource__RecurseAssignment_2 )?
            {
             before(grammarAccess.getLocalResourceAccess().getRecurseAssignment_2()); 
            // InternalGrana.g:4242:1: ( rule__LocalResource__RecurseAssignment_2 )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==64) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalGrana.g:4242:2: rule__LocalResource__RecurseAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__LocalResource__RecurseAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getLocalResourceAccess().getRecurseAssignment_2()); 

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
    // $ANTLR end "rule__LocalResource__Group__2__Impl"


    // $ANTLR start "rule__LocalResource__Group_1__0"
    // InternalGrana.g:4258:1: rule__LocalResource__Group_1__0 : rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 ;
    public final void rule__LocalResource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4262:1: ( rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 )
            // InternalGrana.g:4263:2: rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1
            {
            pushFollow(FOLLOW_30);
            rule__LocalResource__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4270:1: rule__LocalResource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__LocalResource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4274:1: ( ( 'filter' ) )
            // InternalGrana.g:4275:1: ( 'filter' )
            {
            // InternalGrana.g:4275:1: ( 'filter' )
            // InternalGrana.g:4276:1: 'filter'
            {
             before(grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:4289:1: rule__LocalResource__Group_1__1 : rule__LocalResource__Group_1__1__Impl ;
    public final void rule__LocalResource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4293:1: ( rule__LocalResource__Group_1__1__Impl )
            // InternalGrana.g:4294:2: rule__LocalResource__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4300:1: rule__LocalResource__Group_1__1__Impl : ( ( rule__LocalResource__FilterAssignment_1_1 ) ) ;
    public final void rule__LocalResource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4304:1: ( ( ( rule__LocalResource__FilterAssignment_1_1 ) ) )
            // InternalGrana.g:4305:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            {
            // InternalGrana.g:4305:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            // InternalGrana.g:4306:1: ( rule__LocalResource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 
            // InternalGrana.g:4307:1: ( rule__LocalResource__FilterAssignment_1_1 )
            // InternalGrana.g:4307:2: rule__LocalResource__FilterAssignment_1_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4321:1: rule__GlobalOutputRef__Group__0 : rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 ;
    public final void rule__GlobalOutputRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4325:1: ( rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 )
            // InternalGrana.g:4326:2: rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1
            {
            pushFollow(FOLLOW_16);
            rule__GlobalOutputRef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4333:1: rule__GlobalOutputRef__Group__0__Impl : ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalOutputRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4337:1: ( ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) )
            // InternalGrana.g:4338:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:4338:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            // InternalGrana.g:4339:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:4340:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            // InternalGrana.g:4340:2: rule__GlobalOutputRef__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4350:1: rule__GlobalOutputRef__Group__1 : rule__GlobalOutputRef__Group__1__Impl ;
    public final void rule__GlobalOutputRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4354:1: ( rule__GlobalOutputRef__Group__1__Impl )
            // InternalGrana.g:4355:2: rule__GlobalOutputRef__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4361:1: rule__GlobalOutputRef__Group__1__Impl : ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) ;
    public final void rule__GlobalOutputRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4365:1: ( ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) )
            // InternalGrana.g:4366:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            {
            // InternalGrana.g:4366:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            // InternalGrana.g:4367:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1()); 
            // InternalGrana.g:4368:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            // InternalGrana.g:4368:2: rule__GlobalOutputRef__OutputAssignment_1
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4382:1: rule__OutputReference__Group__0 : rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 ;
    public final void rule__OutputReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4386:1: ( rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 )
            // InternalGrana.g:4387:2: rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__OutputReference__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4394:1: rule__OutputReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__OutputReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4398:1: ( ( 'ref' ) )
            // InternalGrana.g:4399:1: ( 'ref' )
            {
            // InternalGrana.g:4399:1: ( 'ref' )
            // InternalGrana.g:4400:1: 'ref'
            {
             before(grammarAccess.getOutputReferenceAccess().getRefKeyword_0()); 
            match(input,37,FOLLOW_2); 
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
    // InternalGrana.g:4413:1: rule__OutputReference__Group__1 : rule__OutputReference__Group__1__Impl ;
    public final void rule__OutputReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4417:1: ( rule__OutputReference__Group__1__Impl )
            // InternalGrana.g:4418:2: rule__OutputReference__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:4424:1: rule__OutputReference__Group__1__Impl : ( ( rule__OutputReference__OutputRefAssignment_1 ) ) ;
    public final void rule__OutputReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4428:1: ( ( ( rule__OutputReference__OutputRefAssignment_1 ) ) )
            // InternalGrana.g:4429:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            {
            // InternalGrana.g:4429:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            // InternalGrana.g:4430:1: ( rule__OutputReference__OutputRefAssignment_1 )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1()); 
            // InternalGrana.g:4431:1: ( rule__OutputReference__OutputRefAssignment_1 )
            // InternalGrana.g:4431:2: rule__OutputReference__OutputRefAssignment_1
            {
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "rule__LayoutConfig__Group__0"
    // InternalGrana.g:4445:1: rule__LayoutConfig__Group__0 : rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1 ;
    public final void rule__LayoutConfig__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4449:1: ( rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1 )
            // InternalGrana.g:4450:2: rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1
            {
            pushFollow(FOLLOW_31);
            rule__LayoutConfig__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__LayoutConfig__Group__1();

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
    // $ANTLR end "rule__LayoutConfig__Group__0"


    // $ANTLR start "rule__LayoutConfig__Group__0__Impl"
    // InternalGrana.g:4457:1: rule__LayoutConfig__Group__0__Impl : ( ( rule__LayoutConfig__IdentifierAssignment_0 ) ) ;
    public final void rule__LayoutConfig__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4461:1: ( ( ( rule__LayoutConfig__IdentifierAssignment_0 ) ) )
            // InternalGrana.g:4462:1: ( ( rule__LayoutConfig__IdentifierAssignment_0 ) )
            {
            // InternalGrana.g:4462:1: ( ( rule__LayoutConfig__IdentifierAssignment_0 ) )
            // InternalGrana.g:4463:1: ( rule__LayoutConfig__IdentifierAssignment_0 )
            {
             before(grammarAccess.getLayoutConfigAccess().getIdentifierAssignment_0()); 
            // InternalGrana.g:4464:1: ( rule__LayoutConfig__IdentifierAssignment_0 )
            // InternalGrana.g:4464:2: rule__LayoutConfig__IdentifierAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__LayoutConfig__IdentifierAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getLayoutConfigAccess().getIdentifierAssignment_0()); 

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
    // $ANTLR end "rule__LayoutConfig__Group__0__Impl"


    // $ANTLR start "rule__LayoutConfig__Group__1"
    // InternalGrana.g:4474:1: rule__LayoutConfig__Group__1 : rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2 ;
    public final void rule__LayoutConfig__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4478:1: ( rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2 )
            // InternalGrana.g:4479:2: rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2
            {
            pushFollow(FOLLOW_32);
            rule__LayoutConfig__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__LayoutConfig__Group__2();

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
    // $ANTLR end "rule__LayoutConfig__Group__1"


    // $ANTLR start "rule__LayoutConfig__Group__1__Impl"
    // InternalGrana.g:4486:1: rule__LayoutConfig__Group__1__Impl : ( '{' ) ;
    public final void rule__LayoutConfig__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4490:1: ( ( '{' ) )
            // InternalGrana.g:4491:1: ( '{' )
            {
            // InternalGrana.g:4491:1: ( '{' )
            // InternalGrana.g:4492:1: '{'
            {
             before(grammarAccess.getLayoutConfigAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getLayoutConfigAccess().getLeftCurlyBracketKeyword_1()); 

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
    // $ANTLR end "rule__LayoutConfig__Group__1__Impl"


    // $ANTLR start "rule__LayoutConfig__Group__2"
    // InternalGrana.g:4505:1: rule__LayoutConfig__Group__2 : rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3 ;
    public final void rule__LayoutConfig__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4509:1: ( rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3 )
            // InternalGrana.g:4510:2: rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3
            {
            pushFollow(FOLLOW_32);
            rule__LayoutConfig__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__LayoutConfig__Group__3();

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
    // $ANTLR end "rule__LayoutConfig__Group__2"


    // $ANTLR start "rule__LayoutConfig__Group__2__Impl"
    // InternalGrana.g:4517:1: rule__LayoutConfig__Group__2__Impl : ( ( rule__LayoutConfig__PropertiesAssignment_2 )* ) ;
    public final void rule__LayoutConfig__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4521:1: ( ( ( rule__LayoutConfig__PropertiesAssignment_2 )* ) )
            // InternalGrana.g:4522:1: ( ( rule__LayoutConfig__PropertiesAssignment_2 )* )
            {
            // InternalGrana.g:4522:1: ( ( rule__LayoutConfig__PropertiesAssignment_2 )* )
            // InternalGrana.g:4523:1: ( rule__LayoutConfig__PropertiesAssignment_2 )*
            {
             before(grammarAccess.getLayoutConfigAccess().getPropertiesAssignment_2()); 
            // InternalGrana.g:4524:1: ( rule__LayoutConfig__PropertiesAssignment_2 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==RULE_ID) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalGrana.g:4524:2: rule__LayoutConfig__PropertiesAssignment_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__LayoutConfig__PropertiesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);

             after(grammarAccess.getLayoutConfigAccess().getPropertiesAssignment_2()); 

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
    // $ANTLR end "rule__LayoutConfig__Group__2__Impl"


    // $ANTLR start "rule__LayoutConfig__Group__3"
    // InternalGrana.g:4534:1: rule__LayoutConfig__Group__3 : rule__LayoutConfig__Group__3__Impl ;
    public final void rule__LayoutConfig__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4538:1: ( rule__LayoutConfig__Group__3__Impl )
            // InternalGrana.g:4539:2: rule__LayoutConfig__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__LayoutConfig__Group__3__Impl();

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
    // $ANTLR end "rule__LayoutConfig__Group__3"


    // $ANTLR start "rule__LayoutConfig__Group__3__Impl"
    // InternalGrana.g:4545:1: rule__LayoutConfig__Group__3__Impl : ( '}' ) ;
    public final void rule__LayoutConfig__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4549:1: ( ( '}' ) )
            // InternalGrana.g:4550:1: ( '}' )
            {
            // InternalGrana.g:4550:1: ( '}' )
            // InternalGrana.g:4551:1: '}'
            {
             before(grammarAccess.getLayoutConfigAccess().getRightCurlyBracketKeyword_3()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getLayoutConfigAccess().getRightCurlyBracketKeyword_3()); 

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
    // $ANTLR end "rule__LayoutConfig__Group__3__Impl"


    // $ANTLR start "rule__ElkNode__Group__0"
    // InternalGrana.g:4574:1: rule__ElkNode__Group__0 : rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 ;
    public final void rule__ElkNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4578:1: ( rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 )
            // InternalGrana.g:4579:2: rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__ElkNode__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkNode__Group__1();

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
    // $ANTLR end "rule__ElkNode__Group__0"


    // $ANTLR start "rule__ElkNode__Group__0__Impl"
    // InternalGrana.g:4586:1: rule__ElkNode__Group__0__Impl : ( 'node' ) ;
    public final void rule__ElkNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4590:1: ( ( 'node' ) )
            // InternalGrana.g:4591:1: ( 'node' )
            {
            // InternalGrana.g:4591:1: ( 'node' )
            // InternalGrana.g:4592:1: 'node'
            {
             before(grammarAccess.getElkNodeAccess().getNodeKeyword_0()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getElkNodeAccess().getNodeKeyword_0()); 

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
    // $ANTLR end "rule__ElkNode__Group__0__Impl"


    // $ANTLR start "rule__ElkNode__Group__1"
    // InternalGrana.g:4605:1: rule__ElkNode__Group__1 : rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 ;
    public final void rule__ElkNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4609:1: ( rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 )
            // InternalGrana.g:4610:2: rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2
            {
            pushFollow(FOLLOW_31);
            rule__ElkNode__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkNode__Group__2();

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
    // $ANTLR end "rule__ElkNode__Group__1"


    // $ANTLR start "rule__ElkNode__Group__1__Impl"
    // InternalGrana.g:4617:1: rule__ElkNode__Group__1__Impl : ( ( rule__ElkNode__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4621:1: ( ( ( rule__ElkNode__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:4622:1: ( ( rule__ElkNode__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:4622:1: ( ( rule__ElkNode__IdentifierAssignment_1 ) )
            // InternalGrana.g:4623:1: ( rule__ElkNode__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkNodeAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:4624:1: ( rule__ElkNode__IdentifierAssignment_1 )
            // InternalGrana.g:4624:2: rule__ElkNode__IdentifierAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkNode__IdentifierAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getElkNodeAccess().getIdentifierAssignment_1()); 

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
    // $ANTLR end "rule__ElkNode__Group__1__Impl"


    // $ANTLR start "rule__ElkNode__Group__2"
    // InternalGrana.g:4634:1: rule__ElkNode__Group__2 : rule__ElkNode__Group__2__Impl ;
    public final void rule__ElkNode__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4638:1: ( rule__ElkNode__Group__2__Impl )
            // InternalGrana.g:4639:2: rule__ElkNode__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkNode__Group__2__Impl();

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
    // $ANTLR end "rule__ElkNode__Group__2"


    // $ANTLR start "rule__ElkNode__Group__2__Impl"
    // InternalGrana.g:4645:1: rule__ElkNode__Group__2__Impl : ( ( rule__ElkNode__Group_2__0 )? ) ;
    public final void rule__ElkNode__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4649:1: ( ( ( rule__ElkNode__Group_2__0 )? ) )
            // InternalGrana.g:4650:1: ( ( rule__ElkNode__Group_2__0 )? )
            {
            // InternalGrana.g:4650:1: ( ( rule__ElkNode__Group_2__0 )? )
            // InternalGrana.g:4651:1: ( rule__ElkNode__Group_2__0 )?
            {
             before(grammarAccess.getElkNodeAccess().getGroup_2()); 
            // InternalGrana.g:4652:1: ( rule__ElkNode__Group_2__0 )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==39) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalGrana.g:4652:2: rule__ElkNode__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkNode__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkNodeAccess().getGroup_2()); 

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
    // $ANTLR end "rule__ElkNode__Group__2__Impl"


    // $ANTLR start "rule__ElkNode__Group_2__0"
    // InternalGrana.g:4668:1: rule__ElkNode__Group_2__0 : rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1 ;
    public final void rule__ElkNode__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4672:1: ( rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1 )
            // InternalGrana.g:4673:2: rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1
            {
            pushFollow(FOLLOW_33);
            rule__ElkNode__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkNode__Group_2__1();

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
    // $ANTLR end "rule__ElkNode__Group_2__0"


    // $ANTLR start "rule__ElkNode__Group_2__0__Impl"
    // InternalGrana.g:4680:1: rule__ElkNode__Group_2__0__Impl : ( '{' ) ;
    public final void rule__ElkNode__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4684:1: ( ( '{' ) )
            // InternalGrana.g:4685:1: ( '{' )
            {
            // InternalGrana.g:4685:1: ( '{' )
            // InternalGrana.g:4686:1: '{'
            {
             before(grammarAccess.getElkNodeAccess().getLeftCurlyBracketKeyword_2_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getElkNodeAccess().getLeftCurlyBracketKeyword_2_0()); 

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
    // $ANTLR end "rule__ElkNode__Group_2__0__Impl"


    // $ANTLR start "rule__ElkNode__Group_2__1"
    // InternalGrana.g:4699:1: rule__ElkNode__Group_2__1 : rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2 ;
    public final void rule__ElkNode__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4703:1: ( rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2 )
            // InternalGrana.g:4704:2: rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2
            {
            pushFollow(FOLLOW_33);
            rule__ElkNode__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkNode__Group_2__2();

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
    // $ANTLR end "rule__ElkNode__Group_2__1"


    // $ANTLR start "rule__ElkNode__Group_2__1__Impl"
    // InternalGrana.g:4711:1: rule__ElkNode__Group_2__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkNode__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4715:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:4716:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:4716:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:4717:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkNodeAccess().getShapeLayoutParserRuleCall_2_1()); 
            // InternalGrana.g:4718:1: ( ruleShapeLayout )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==45) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalGrana.g:4718:3: ruleShapeLayout
                    {
                    pushFollow(FOLLOW_2);
                    ruleShapeLayout();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkNodeAccess().getShapeLayoutParserRuleCall_2_1()); 

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
    // $ANTLR end "rule__ElkNode__Group_2__1__Impl"


    // $ANTLR start "rule__ElkNode__Group_2__2"
    // InternalGrana.g:4728:1: rule__ElkNode__Group_2__2 : rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3 ;
    public final void rule__ElkNode__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4732:1: ( rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3 )
            // InternalGrana.g:4733:2: rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3
            {
            pushFollow(FOLLOW_33);
            rule__ElkNode__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkNode__Group_2__3();

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
    // $ANTLR end "rule__ElkNode__Group_2__2"


    // $ANTLR start "rule__ElkNode__Group_2__2__Impl"
    // InternalGrana.g:4740:1: rule__ElkNode__Group_2__2__Impl : ( ( rule__ElkNode__PropertiesAssignment_2_2 )* ) ;
    public final void rule__ElkNode__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4744:1: ( ( ( rule__ElkNode__PropertiesAssignment_2_2 )* ) )
            // InternalGrana.g:4745:1: ( ( rule__ElkNode__PropertiesAssignment_2_2 )* )
            {
            // InternalGrana.g:4745:1: ( ( rule__ElkNode__PropertiesAssignment_2_2 )* )
            // InternalGrana.g:4746:1: ( rule__ElkNode__PropertiesAssignment_2_2 )*
            {
             before(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2_2()); 
            // InternalGrana.g:4747:1: ( rule__ElkNode__PropertiesAssignment_2_2 )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==RULE_ID) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalGrana.g:4747:2: rule__ElkNode__PropertiesAssignment_2_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkNode__PropertiesAssignment_2_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);

             after(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2_2()); 

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
    // $ANTLR end "rule__ElkNode__Group_2__2__Impl"


    // $ANTLR start "rule__ElkNode__Group_2__3"
    // InternalGrana.g:4757:1: rule__ElkNode__Group_2__3 : rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4 ;
    public final void rule__ElkNode__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4761:1: ( rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4 )
            // InternalGrana.g:4762:2: rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4
            {
            pushFollow(FOLLOW_33);
            rule__ElkNode__Group_2__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkNode__Group_2__4();

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
    // $ANTLR end "rule__ElkNode__Group_2__3"


    // $ANTLR start "rule__ElkNode__Group_2__3__Impl"
    // InternalGrana.g:4769:1: rule__ElkNode__Group_2__3__Impl : ( ( rule__ElkNode__Alternatives_2_3 )* ) ;
    public final void rule__ElkNode__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4773:1: ( ( ( rule__ElkNode__Alternatives_2_3 )* ) )
            // InternalGrana.g:4774:1: ( ( rule__ElkNode__Alternatives_2_3 )* )
            {
            // InternalGrana.g:4774:1: ( ( rule__ElkNode__Alternatives_2_3 )* )
            // InternalGrana.g:4775:1: ( rule__ElkNode__Alternatives_2_3 )*
            {
             before(grammarAccess.getElkNodeAccess().getAlternatives_2_3()); 
            // InternalGrana.g:4776:1: ( rule__ElkNode__Alternatives_2_3 )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=41 && LA48_0<=42)||LA48_0==44||LA48_0==50) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalGrana.g:4776:2: rule__ElkNode__Alternatives_2_3
            	    {
            	    pushFollow(FOLLOW_34);
            	    rule__ElkNode__Alternatives_2_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);

             after(grammarAccess.getElkNodeAccess().getAlternatives_2_3()); 

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
    // $ANTLR end "rule__ElkNode__Group_2__3__Impl"


    // $ANTLR start "rule__ElkNode__Group_2__4"
    // InternalGrana.g:4786:1: rule__ElkNode__Group_2__4 : rule__ElkNode__Group_2__4__Impl ;
    public final void rule__ElkNode__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4790:1: ( rule__ElkNode__Group_2__4__Impl )
            // InternalGrana.g:4791:2: rule__ElkNode__Group_2__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkNode__Group_2__4__Impl();

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
    // $ANTLR end "rule__ElkNode__Group_2__4"


    // $ANTLR start "rule__ElkNode__Group_2__4__Impl"
    // InternalGrana.g:4797:1: rule__ElkNode__Group_2__4__Impl : ( '}' ) ;
    public final void rule__ElkNode__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4801:1: ( ( '}' ) )
            // InternalGrana.g:4802:1: ( '}' )
            {
            // InternalGrana.g:4802:1: ( '}' )
            // InternalGrana.g:4803:1: '}'
            {
             before(grammarAccess.getElkNodeAccess().getRightCurlyBracketKeyword_2_4()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getElkNodeAccess().getRightCurlyBracketKeyword_2_4()); 

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
    // $ANTLR end "rule__ElkNode__Group_2__4__Impl"


    // $ANTLR start "rule__ElkLabel__Group__0"
    // InternalGrana.g:4826:1: rule__ElkLabel__Group__0 : rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 ;
    public final void rule__ElkLabel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4830:1: ( rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 )
            // InternalGrana.g:4831:2: rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1
            {
            pushFollow(FOLLOW_35);
            rule__ElkLabel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group__1();

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
    // $ANTLR end "rule__ElkLabel__Group__0"


    // $ANTLR start "rule__ElkLabel__Group__0__Impl"
    // InternalGrana.g:4838:1: rule__ElkLabel__Group__0__Impl : ( 'label' ) ;
    public final void rule__ElkLabel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4842:1: ( ( 'label' ) )
            // InternalGrana.g:4843:1: ( 'label' )
            {
            // InternalGrana.g:4843:1: ( 'label' )
            // InternalGrana.g:4844:1: 'label'
            {
             before(grammarAccess.getElkLabelAccess().getLabelKeyword_0()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getLabelKeyword_0()); 

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
    // $ANTLR end "rule__ElkLabel__Group__0__Impl"


    // $ANTLR start "rule__ElkLabel__Group__1"
    // InternalGrana.g:4857:1: rule__ElkLabel__Group__1 : rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 ;
    public final void rule__ElkLabel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4861:1: ( rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 )
            // InternalGrana.g:4862:2: rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2
            {
            pushFollow(FOLLOW_35);
            rule__ElkLabel__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group__2();

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
    // $ANTLR end "rule__ElkLabel__Group__1"


    // $ANTLR start "rule__ElkLabel__Group__1__Impl"
    // InternalGrana.g:4869:1: rule__ElkLabel__Group__1__Impl : ( ( rule__ElkLabel__Group_1__0 )? ) ;
    public final void rule__ElkLabel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4873:1: ( ( ( rule__ElkLabel__Group_1__0 )? ) )
            // InternalGrana.g:4874:1: ( ( rule__ElkLabel__Group_1__0 )? )
            {
            // InternalGrana.g:4874:1: ( ( rule__ElkLabel__Group_1__0 )? )
            // InternalGrana.g:4875:1: ( rule__ElkLabel__Group_1__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_1()); 
            // InternalGrana.g:4876:1: ( rule__ElkLabel__Group_1__0 )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==RULE_ID) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalGrana.g:4876:2: rule__ElkLabel__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkLabel__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkLabelAccess().getGroup_1()); 

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
    // $ANTLR end "rule__ElkLabel__Group__1__Impl"


    // $ANTLR start "rule__ElkLabel__Group__2"
    // InternalGrana.g:4886:1: rule__ElkLabel__Group__2 : rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 ;
    public final void rule__ElkLabel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4890:1: ( rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 )
            // InternalGrana.g:4891:2: rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3
            {
            pushFollow(FOLLOW_31);
            rule__ElkLabel__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group__3();

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
    // $ANTLR end "rule__ElkLabel__Group__2"


    // $ANTLR start "rule__ElkLabel__Group__2__Impl"
    // InternalGrana.g:4898:1: rule__ElkLabel__Group__2__Impl : ( ( rule__ElkLabel__TextAssignment_2 ) ) ;
    public final void rule__ElkLabel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4902:1: ( ( ( rule__ElkLabel__TextAssignment_2 ) ) )
            // InternalGrana.g:4903:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            {
            // InternalGrana.g:4903:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            // InternalGrana.g:4904:1: ( rule__ElkLabel__TextAssignment_2 )
            {
             before(grammarAccess.getElkLabelAccess().getTextAssignment_2()); 
            // InternalGrana.g:4905:1: ( rule__ElkLabel__TextAssignment_2 )
            // InternalGrana.g:4905:2: rule__ElkLabel__TextAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkLabel__TextAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getElkLabelAccess().getTextAssignment_2()); 

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
    // $ANTLR end "rule__ElkLabel__Group__2__Impl"


    // $ANTLR start "rule__ElkLabel__Group__3"
    // InternalGrana.g:4915:1: rule__ElkLabel__Group__3 : rule__ElkLabel__Group__3__Impl ;
    public final void rule__ElkLabel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4919:1: ( rule__ElkLabel__Group__3__Impl )
            // InternalGrana.g:4920:2: rule__ElkLabel__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group__3__Impl();

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
    // $ANTLR end "rule__ElkLabel__Group__3"


    // $ANTLR start "rule__ElkLabel__Group__3__Impl"
    // InternalGrana.g:4926:1: rule__ElkLabel__Group__3__Impl : ( ( rule__ElkLabel__Group_3__0 )? ) ;
    public final void rule__ElkLabel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4930:1: ( ( ( rule__ElkLabel__Group_3__0 )? ) )
            // InternalGrana.g:4931:1: ( ( rule__ElkLabel__Group_3__0 )? )
            {
            // InternalGrana.g:4931:1: ( ( rule__ElkLabel__Group_3__0 )? )
            // InternalGrana.g:4932:1: ( rule__ElkLabel__Group_3__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_3()); 
            // InternalGrana.g:4933:1: ( rule__ElkLabel__Group_3__0 )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==39) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalGrana.g:4933:2: rule__ElkLabel__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkLabel__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkLabelAccess().getGroup_3()); 

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
    // $ANTLR end "rule__ElkLabel__Group__3__Impl"


    // $ANTLR start "rule__ElkLabel__Group_1__0"
    // InternalGrana.g:4951:1: rule__ElkLabel__Group_1__0 : rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 ;
    public final void rule__ElkLabel__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4955:1: ( rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 )
            // InternalGrana.g:4956:2: rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkLabel__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group_1__1();

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
    // $ANTLR end "rule__ElkLabel__Group_1__0"


    // $ANTLR start "rule__ElkLabel__Group_1__0__Impl"
    // InternalGrana.g:4963:1: rule__ElkLabel__Group_1__0__Impl : ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkLabel__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4967:1: ( ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) )
            // InternalGrana.g:4968:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            {
            // InternalGrana.g:4968:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            // InternalGrana.g:4969:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0()); 
            // InternalGrana.g:4970:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            // InternalGrana.g:4970:2: rule__ElkLabel__IdentifierAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__ElkLabel__IdentifierAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0()); 

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
    // $ANTLR end "rule__ElkLabel__Group_1__0__Impl"


    // $ANTLR start "rule__ElkLabel__Group_1__1"
    // InternalGrana.g:4980:1: rule__ElkLabel__Group_1__1 : rule__ElkLabel__Group_1__1__Impl ;
    public final void rule__ElkLabel__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4984:1: ( rule__ElkLabel__Group_1__1__Impl )
            // InternalGrana.g:4985:2: rule__ElkLabel__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group_1__1__Impl();

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
    // $ANTLR end "rule__ElkLabel__Group_1__1"


    // $ANTLR start "rule__ElkLabel__Group_1__1__Impl"
    // InternalGrana.g:4991:1: rule__ElkLabel__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkLabel__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4995:1: ( ( ':' ) )
            // InternalGrana.g:4996:1: ( ':' )
            {
            // InternalGrana.g:4996:1: ( ':' )
            // InternalGrana.g:4997:1: ':'
            {
             before(grammarAccess.getElkLabelAccess().getColonKeyword_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getColonKeyword_1_1()); 

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
    // $ANTLR end "rule__ElkLabel__Group_1__1__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__0"
    // InternalGrana.g:5014:1: rule__ElkLabel__Group_3__0 : rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 ;
    public final void rule__ElkLabel__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5018:1: ( rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 )
            // InternalGrana.g:5019:2: rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1
            {
            pushFollow(FOLLOW_37);
            rule__ElkLabel__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group_3__1();

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
    // $ANTLR end "rule__ElkLabel__Group_3__0"


    // $ANTLR start "rule__ElkLabel__Group_3__0__Impl"
    // InternalGrana.g:5026:1: rule__ElkLabel__Group_3__0__Impl : ( '{' ) ;
    public final void rule__ElkLabel__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5030:1: ( ( '{' ) )
            // InternalGrana.g:5031:1: ( '{' )
            {
            // InternalGrana.g:5031:1: ( '{' )
            // InternalGrana.g:5032:1: '{'
            {
             before(grammarAccess.getElkLabelAccess().getLeftCurlyBracketKeyword_3_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getLeftCurlyBracketKeyword_3_0()); 

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
    // $ANTLR end "rule__ElkLabel__Group_3__0__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__1"
    // InternalGrana.g:5045:1: rule__ElkLabel__Group_3__1 : rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 ;
    public final void rule__ElkLabel__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5049:1: ( rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 )
            // InternalGrana.g:5050:2: rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2
            {
            pushFollow(FOLLOW_37);
            rule__ElkLabel__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group_3__2();

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
    // $ANTLR end "rule__ElkLabel__Group_3__1"


    // $ANTLR start "rule__ElkLabel__Group_3__1__Impl"
    // InternalGrana.g:5057:1: rule__ElkLabel__Group_3__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkLabel__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5061:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:5062:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:5062:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:5063:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 
            // InternalGrana.g:5064:1: ( ruleShapeLayout )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==45) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalGrana.g:5064:3: ruleShapeLayout
                    {
                    pushFollow(FOLLOW_2);
                    ruleShapeLayout();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 

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
    // $ANTLR end "rule__ElkLabel__Group_3__1__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__2"
    // InternalGrana.g:5074:1: rule__ElkLabel__Group_3__2 : rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 ;
    public final void rule__ElkLabel__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5078:1: ( rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 )
            // InternalGrana.g:5079:2: rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3
            {
            pushFollow(FOLLOW_37);
            rule__ElkLabel__Group_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group_3__3();

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
    // $ANTLR end "rule__ElkLabel__Group_3__2"


    // $ANTLR start "rule__ElkLabel__Group_3__2__Impl"
    // InternalGrana.g:5086:1: rule__ElkLabel__Group_3__2__Impl : ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) ;
    public final void rule__ElkLabel__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5090:1: ( ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) )
            // InternalGrana.g:5091:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            {
            // InternalGrana.g:5091:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            // InternalGrana.g:5092:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            {
             before(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2()); 
            // InternalGrana.g:5093:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==RULE_ID) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // InternalGrana.g:5093:2: rule__ElkLabel__PropertiesAssignment_3_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkLabel__PropertiesAssignment_3_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);

             after(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2()); 

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
    // $ANTLR end "rule__ElkLabel__Group_3__2__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__3"
    // InternalGrana.g:5103:1: rule__ElkLabel__Group_3__3 : rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 ;
    public final void rule__ElkLabel__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5107:1: ( rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 )
            // InternalGrana.g:5108:2: rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4
            {
            pushFollow(FOLLOW_37);
            rule__ElkLabel__Group_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group_3__4();

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
    // $ANTLR end "rule__ElkLabel__Group_3__3"


    // $ANTLR start "rule__ElkLabel__Group_3__3__Impl"
    // InternalGrana.g:5115:1: rule__ElkLabel__Group_3__3__Impl : ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) ;
    public final void rule__ElkLabel__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5119:1: ( ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) )
            // InternalGrana.g:5120:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            {
            // InternalGrana.g:5120:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            // InternalGrana.g:5121:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            {
             before(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3()); 
            // InternalGrana.g:5122:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==42) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // InternalGrana.g:5122:2: rule__ElkLabel__LabelsAssignment_3_3
            	    {
            	    pushFollow(FOLLOW_38);
            	    rule__ElkLabel__LabelsAssignment_3_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);

             after(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3()); 

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
    // $ANTLR end "rule__ElkLabel__Group_3__3__Impl"


    // $ANTLR start "rule__ElkLabel__Group_3__4"
    // InternalGrana.g:5132:1: rule__ElkLabel__Group_3__4 : rule__ElkLabel__Group_3__4__Impl ;
    public final void rule__ElkLabel__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5136:1: ( rule__ElkLabel__Group_3__4__Impl )
            // InternalGrana.g:5137:2: rule__ElkLabel__Group_3__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkLabel__Group_3__4__Impl();

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
    // $ANTLR end "rule__ElkLabel__Group_3__4"


    // $ANTLR start "rule__ElkLabel__Group_3__4__Impl"
    // InternalGrana.g:5143:1: rule__ElkLabel__Group_3__4__Impl : ( '}' ) ;
    public final void rule__ElkLabel__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5147:1: ( ( '}' ) )
            // InternalGrana.g:5148:1: ( '}' )
            {
            // InternalGrana.g:5148:1: ( '}' )
            // InternalGrana.g:5149:1: '}'
            {
             before(grammarAccess.getElkLabelAccess().getRightCurlyBracketKeyword_3_4()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getRightCurlyBracketKeyword_3_4()); 

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
    // $ANTLR end "rule__ElkLabel__Group_3__4__Impl"


    // $ANTLR start "rule__ElkPort__Group__0"
    // InternalGrana.g:5172:1: rule__ElkPort__Group__0 : rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1 ;
    public final void rule__ElkPort__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5176:1: ( rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1 )
            // InternalGrana.g:5177:2: rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__ElkPort__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkPort__Group__1();

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
    // $ANTLR end "rule__ElkPort__Group__0"


    // $ANTLR start "rule__ElkPort__Group__0__Impl"
    // InternalGrana.g:5184:1: rule__ElkPort__Group__0__Impl : ( 'port' ) ;
    public final void rule__ElkPort__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5188:1: ( ( 'port' ) )
            // InternalGrana.g:5189:1: ( 'port' )
            {
            // InternalGrana.g:5189:1: ( 'port' )
            // InternalGrana.g:5190:1: 'port'
            {
             before(grammarAccess.getElkPortAccess().getPortKeyword_0()); 
            match(input,44,FOLLOW_2); 
             after(grammarAccess.getElkPortAccess().getPortKeyword_0()); 

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
    // $ANTLR end "rule__ElkPort__Group__0__Impl"


    // $ANTLR start "rule__ElkPort__Group__1"
    // InternalGrana.g:5203:1: rule__ElkPort__Group__1 : rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2 ;
    public final void rule__ElkPort__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5207:1: ( rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2 )
            // InternalGrana.g:5208:2: rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2
            {
            pushFollow(FOLLOW_31);
            rule__ElkPort__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkPort__Group__2();

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
    // $ANTLR end "rule__ElkPort__Group__1"


    // $ANTLR start "rule__ElkPort__Group__1__Impl"
    // InternalGrana.g:5215:1: rule__ElkPort__Group__1__Impl : ( ( rule__ElkPort__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkPort__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5219:1: ( ( ( rule__ElkPort__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:5220:1: ( ( rule__ElkPort__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:5220:1: ( ( rule__ElkPort__IdentifierAssignment_1 ) )
            // InternalGrana.g:5221:1: ( rule__ElkPort__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkPortAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:5222:1: ( rule__ElkPort__IdentifierAssignment_1 )
            // InternalGrana.g:5222:2: rule__ElkPort__IdentifierAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkPort__IdentifierAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getElkPortAccess().getIdentifierAssignment_1()); 

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
    // $ANTLR end "rule__ElkPort__Group__1__Impl"


    // $ANTLR start "rule__ElkPort__Group__2"
    // InternalGrana.g:5232:1: rule__ElkPort__Group__2 : rule__ElkPort__Group__2__Impl ;
    public final void rule__ElkPort__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5236:1: ( rule__ElkPort__Group__2__Impl )
            // InternalGrana.g:5237:2: rule__ElkPort__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkPort__Group__2__Impl();

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
    // $ANTLR end "rule__ElkPort__Group__2"


    // $ANTLR start "rule__ElkPort__Group__2__Impl"
    // InternalGrana.g:5243:1: rule__ElkPort__Group__2__Impl : ( ( rule__ElkPort__Group_2__0 )? ) ;
    public final void rule__ElkPort__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5247:1: ( ( ( rule__ElkPort__Group_2__0 )? ) )
            // InternalGrana.g:5248:1: ( ( rule__ElkPort__Group_2__0 )? )
            {
            // InternalGrana.g:5248:1: ( ( rule__ElkPort__Group_2__0 )? )
            // InternalGrana.g:5249:1: ( rule__ElkPort__Group_2__0 )?
            {
             before(grammarAccess.getElkPortAccess().getGroup_2()); 
            // InternalGrana.g:5250:1: ( rule__ElkPort__Group_2__0 )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==39) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalGrana.g:5250:2: rule__ElkPort__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkPort__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkPortAccess().getGroup_2()); 

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
    // $ANTLR end "rule__ElkPort__Group__2__Impl"


    // $ANTLR start "rule__ElkPort__Group_2__0"
    // InternalGrana.g:5266:1: rule__ElkPort__Group_2__0 : rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1 ;
    public final void rule__ElkPort__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5270:1: ( rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1 )
            // InternalGrana.g:5271:2: rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1
            {
            pushFollow(FOLLOW_37);
            rule__ElkPort__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkPort__Group_2__1();

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
    // $ANTLR end "rule__ElkPort__Group_2__0"


    // $ANTLR start "rule__ElkPort__Group_2__0__Impl"
    // InternalGrana.g:5278:1: rule__ElkPort__Group_2__0__Impl : ( '{' ) ;
    public final void rule__ElkPort__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5282:1: ( ( '{' ) )
            // InternalGrana.g:5283:1: ( '{' )
            {
            // InternalGrana.g:5283:1: ( '{' )
            // InternalGrana.g:5284:1: '{'
            {
             before(grammarAccess.getElkPortAccess().getLeftCurlyBracketKeyword_2_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getElkPortAccess().getLeftCurlyBracketKeyword_2_0()); 

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
    // $ANTLR end "rule__ElkPort__Group_2__0__Impl"


    // $ANTLR start "rule__ElkPort__Group_2__1"
    // InternalGrana.g:5297:1: rule__ElkPort__Group_2__1 : rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2 ;
    public final void rule__ElkPort__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5301:1: ( rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2 )
            // InternalGrana.g:5302:2: rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2
            {
            pushFollow(FOLLOW_37);
            rule__ElkPort__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkPort__Group_2__2();

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
    // $ANTLR end "rule__ElkPort__Group_2__1"


    // $ANTLR start "rule__ElkPort__Group_2__1__Impl"
    // InternalGrana.g:5309:1: rule__ElkPort__Group_2__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkPort__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5313:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:5314:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:5314:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:5315:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkPortAccess().getShapeLayoutParserRuleCall_2_1()); 
            // InternalGrana.g:5316:1: ( ruleShapeLayout )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==45) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // InternalGrana.g:5316:3: ruleShapeLayout
                    {
                    pushFollow(FOLLOW_2);
                    ruleShapeLayout();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkPortAccess().getShapeLayoutParserRuleCall_2_1()); 

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
    // $ANTLR end "rule__ElkPort__Group_2__1__Impl"


    // $ANTLR start "rule__ElkPort__Group_2__2"
    // InternalGrana.g:5326:1: rule__ElkPort__Group_2__2 : rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3 ;
    public final void rule__ElkPort__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5330:1: ( rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3 )
            // InternalGrana.g:5331:2: rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3
            {
            pushFollow(FOLLOW_37);
            rule__ElkPort__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkPort__Group_2__3();

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
    // $ANTLR end "rule__ElkPort__Group_2__2"


    // $ANTLR start "rule__ElkPort__Group_2__2__Impl"
    // InternalGrana.g:5338:1: rule__ElkPort__Group_2__2__Impl : ( ( rule__ElkPort__PropertiesAssignment_2_2 )* ) ;
    public final void rule__ElkPort__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5342:1: ( ( ( rule__ElkPort__PropertiesAssignment_2_2 )* ) )
            // InternalGrana.g:5343:1: ( ( rule__ElkPort__PropertiesAssignment_2_2 )* )
            {
            // InternalGrana.g:5343:1: ( ( rule__ElkPort__PropertiesAssignment_2_2 )* )
            // InternalGrana.g:5344:1: ( rule__ElkPort__PropertiesAssignment_2_2 )*
            {
             before(grammarAccess.getElkPortAccess().getPropertiesAssignment_2_2()); 
            // InternalGrana.g:5345:1: ( rule__ElkPort__PropertiesAssignment_2_2 )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==RULE_ID) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // InternalGrana.g:5345:2: rule__ElkPort__PropertiesAssignment_2_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkPort__PropertiesAssignment_2_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop56;
                }
            } while (true);

             after(grammarAccess.getElkPortAccess().getPropertiesAssignment_2_2()); 

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
    // $ANTLR end "rule__ElkPort__Group_2__2__Impl"


    // $ANTLR start "rule__ElkPort__Group_2__3"
    // InternalGrana.g:5355:1: rule__ElkPort__Group_2__3 : rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4 ;
    public final void rule__ElkPort__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5359:1: ( rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4 )
            // InternalGrana.g:5360:2: rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4
            {
            pushFollow(FOLLOW_37);
            rule__ElkPort__Group_2__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkPort__Group_2__4();

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
    // $ANTLR end "rule__ElkPort__Group_2__3"


    // $ANTLR start "rule__ElkPort__Group_2__3__Impl"
    // InternalGrana.g:5367:1: rule__ElkPort__Group_2__3__Impl : ( ( rule__ElkPort__LabelsAssignment_2_3 )* ) ;
    public final void rule__ElkPort__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5371:1: ( ( ( rule__ElkPort__LabelsAssignment_2_3 )* ) )
            // InternalGrana.g:5372:1: ( ( rule__ElkPort__LabelsAssignment_2_3 )* )
            {
            // InternalGrana.g:5372:1: ( ( rule__ElkPort__LabelsAssignment_2_3 )* )
            // InternalGrana.g:5373:1: ( rule__ElkPort__LabelsAssignment_2_3 )*
            {
             before(grammarAccess.getElkPortAccess().getLabelsAssignment_2_3()); 
            // InternalGrana.g:5374:1: ( rule__ElkPort__LabelsAssignment_2_3 )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==42) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // InternalGrana.g:5374:2: rule__ElkPort__LabelsAssignment_2_3
            	    {
            	    pushFollow(FOLLOW_38);
            	    rule__ElkPort__LabelsAssignment_2_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);

             after(grammarAccess.getElkPortAccess().getLabelsAssignment_2_3()); 

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
    // $ANTLR end "rule__ElkPort__Group_2__3__Impl"


    // $ANTLR start "rule__ElkPort__Group_2__4"
    // InternalGrana.g:5384:1: rule__ElkPort__Group_2__4 : rule__ElkPort__Group_2__4__Impl ;
    public final void rule__ElkPort__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5388:1: ( rule__ElkPort__Group_2__4__Impl )
            // InternalGrana.g:5389:2: rule__ElkPort__Group_2__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkPort__Group_2__4__Impl();

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
    // $ANTLR end "rule__ElkPort__Group_2__4"


    // $ANTLR start "rule__ElkPort__Group_2__4__Impl"
    // InternalGrana.g:5395:1: rule__ElkPort__Group_2__4__Impl : ( '}' ) ;
    public final void rule__ElkPort__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5399:1: ( ( '}' ) )
            // InternalGrana.g:5400:1: ( '}' )
            {
            // InternalGrana.g:5400:1: ( '}' )
            // InternalGrana.g:5401:1: '}'
            {
             before(grammarAccess.getElkPortAccess().getRightCurlyBracketKeyword_2_4()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getElkPortAccess().getRightCurlyBracketKeyword_2_4()); 

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
    // $ANTLR end "rule__ElkPort__Group_2__4__Impl"


    // $ANTLR start "rule__ShapeLayout__Group__0"
    // InternalGrana.g:5424:1: rule__ShapeLayout__Group__0 : rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 ;
    public final void rule__ShapeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5428:1: ( rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 )
            // InternalGrana.g:5429:2: rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1
            {
            pushFollow(FOLLOW_39);
            rule__ShapeLayout__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group__1();

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
    // $ANTLR end "rule__ShapeLayout__Group__0"


    // $ANTLR start "rule__ShapeLayout__Group__0__Impl"
    // InternalGrana.g:5436:1: rule__ShapeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__ShapeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5440:1: ( ( 'layout' ) )
            // InternalGrana.g:5441:1: ( 'layout' )
            {
            // InternalGrana.g:5441:1: ( 'layout' )
            // InternalGrana.g:5442:1: 'layout'
            {
             before(grammarAccess.getShapeLayoutAccess().getLayoutKeyword_0()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getLayoutKeyword_0()); 

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
    // $ANTLR end "rule__ShapeLayout__Group__0__Impl"


    // $ANTLR start "rule__ShapeLayout__Group__1"
    // InternalGrana.g:5455:1: rule__ShapeLayout__Group__1 : rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 ;
    public final void rule__ShapeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5459:1: ( rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 )
            // InternalGrana.g:5460:2: rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2
            {
            pushFollow(FOLLOW_40);
            rule__ShapeLayout__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group__2();

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
    // $ANTLR end "rule__ShapeLayout__Group__1"


    // $ANTLR start "rule__ShapeLayout__Group__1__Impl"
    // InternalGrana.g:5467:1: rule__ShapeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__ShapeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5471:1: ( ( '[' ) )
            // InternalGrana.g:5472:1: ( '[' )
            {
            // InternalGrana.g:5472:1: ( '[' )
            // InternalGrana.g:5473:1: '['
            {
             before(grammarAccess.getShapeLayoutAccess().getLeftSquareBracketKeyword_1()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getLeftSquareBracketKeyword_1()); 

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
    // $ANTLR end "rule__ShapeLayout__Group__1__Impl"


    // $ANTLR start "rule__ShapeLayout__Group__2"
    // InternalGrana.g:5486:1: rule__ShapeLayout__Group__2 : rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 ;
    public final void rule__ShapeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5490:1: ( rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 )
            // InternalGrana.g:5491:2: rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3
            {
            pushFollow(FOLLOW_41);
            rule__ShapeLayout__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group__3();

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
    // $ANTLR end "rule__ShapeLayout__Group__2"


    // $ANTLR start "rule__ShapeLayout__Group__2__Impl"
    // InternalGrana.g:5498:1: rule__ShapeLayout__Group__2__Impl : ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) ;
    public final void rule__ShapeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5502:1: ( ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) )
            // InternalGrana.g:5503:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            {
            // InternalGrana.g:5503:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            // InternalGrana.g:5504:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2()); 
            // InternalGrana.g:5505:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            // InternalGrana.g:5505:2: rule__ShapeLayout__UnorderedGroup_2
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__UnorderedGroup_2();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2()); 

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
    // $ANTLR end "rule__ShapeLayout__Group__2__Impl"


    // $ANTLR start "rule__ShapeLayout__Group__3"
    // InternalGrana.g:5515:1: rule__ShapeLayout__Group__3 : rule__ShapeLayout__Group__3__Impl ;
    public final void rule__ShapeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5519:1: ( rule__ShapeLayout__Group__3__Impl )
            // InternalGrana.g:5520:2: rule__ShapeLayout__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group__3__Impl();

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
    // $ANTLR end "rule__ShapeLayout__Group__3"


    // $ANTLR start "rule__ShapeLayout__Group__3__Impl"
    // InternalGrana.g:5526:1: rule__ShapeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__ShapeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5530:1: ( ( ']' ) )
            // InternalGrana.g:5531:1: ( ']' )
            {
            // InternalGrana.g:5531:1: ( ']' )
            // InternalGrana.g:5532:1: ']'
            {
             before(grammarAccess.getShapeLayoutAccess().getRightSquareBracketKeyword_3()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getRightSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__ShapeLayout__Group__3__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__0"
    // InternalGrana.g:5553:1: rule__ShapeLayout__Group_2_0__0 : rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 ;
    public final void rule__ShapeLayout__Group_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5557:1: ( rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 )
            // InternalGrana.g:5558:2: rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1
            {
            pushFollow(FOLLOW_36);
            rule__ShapeLayout__Group_2_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_0__1();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__0"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__0__Impl"
    // InternalGrana.g:5565:1: rule__ShapeLayout__Group_2_0__0__Impl : ( 'position' ) ;
    public final void rule__ShapeLayout__Group_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5569:1: ( ( 'position' ) )
            // InternalGrana.g:5570:1: ( 'position' )
            {
            // InternalGrana.g:5570:1: ( 'position' )
            // InternalGrana.g:5571:1: 'position'
            {
             before(grammarAccess.getShapeLayoutAccess().getPositionKeyword_2_0_0()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getPositionKeyword_2_0_0()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__0__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__1"
    // InternalGrana.g:5584:1: rule__ShapeLayout__Group_2_0__1 : rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 ;
    public final void rule__ShapeLayout__Group_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5588:1: ( rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 )
            // InternalGrana.g:5589:2: rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2
            {
            pushFollow(FOLLOW_42);
            rule__ShapeLayout__Group_2_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_0__2();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__1"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__1__Impl"
    // InternalGrana.g:5596:1: rule__ShapeLayout__Group_2_0__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5600:1: ( ( ':' ) )
            // InternalGrana.g:5601:1: ( ':' )
            {
            // InternalGrana.g:5601:1: ( ':' )
            // InternalGrana.g:5602:1: ':'
            {
             before(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_0_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_0_1()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__1__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__2"
    // InternalGrana.g:5615:1: rule__ShapeLayout__Group_2_0__2 : rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 ;
    public final void rule__ShapeLayout__Group_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5619:1: ( rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 )
            // InternalGrana.g:5620:2: rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3
            {
            pushFollow(FOLLOW_25);
            rule__ShapeLayout__Group_2_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_0__3();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__2"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__2__Impl"
    // InternalGrana.g:5627:1: rule__ShapeLayout__Group_2_0__2__Impl : ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5631:1: ( ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) )
            // InternalGrana.g:5632:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            {
            // InternalGrana.g:5632:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            // InternalGrana.g:5633:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2()); 
            // InternalGrana.g:5634:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            // InternalGrana.g:5634:2: rule__ShapeLayout__XAssignment_2_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__XAssignment_2_0_2();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__2__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__3"
    // InternalGrana.g:5644:1: rule__ShapeLayout__Group_2_0__3 : rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 ;
    public final void rule__ShapeLayout__Group_2_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5648:1: ( rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 )
            // InternalGrana.g:5649:2: rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4
            {
            pushFollow(FOLLOW_42);
            rule__ShapeLayout__Group_2_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_0__4();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__3"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__3__Impl"
    // InternalGrana.g:5656:1: rule__ShapeLayout__Group_2_0__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5660:1: ( ( ',' ) )
            // InternalGrana.g:5661:1: ( ',' )
            {
            // InternalGrana.g:5661:1: ( ',' )
            // InternalGrana.g:5662:1: ','
            {
             before(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_0_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_0_3()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__3__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__4"
    // InternalGrana.g:5675:1: rule__ShapeLayout__Group_2_0__4 : rule__ShapeLayout__Group_2_0__4__Impl ;
    public final void rule__ShapeLayout__Group_2_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5679:1: ( rule__ShapeLayout__Group_2_0__4__Impl )
            // InternalGrana.g:5680:2: rule__ShapeLayout__Group_2_0__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_0__4__Impl();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__4"


    // $ANTLR start "rule__ShapeLayout__Group_2_0__4__Impl"
    // InternalGrana.g:5686:1: rule__ShapeLayout__Group_2_0__4__Impl : ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5690:1: ( ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) )
            // InternalGrana.g:5691:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            {
            // InternalGrana.g:5691:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            // InternalGrana.g:5692:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4()); 
            // InternalGrana.g:5693:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            // InternalGrana.g:5693:2: rule__ShapeLayout__YAssignment_2_0_4
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__YAssignment_2_0_4();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_0__4__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__0"
    // InternalGrana.g:5713:1: rule__ShapeLayout__Group_2_1__0 : rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 ;
    public final void rule__ShapeLayout__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5717:1: ( rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 )
            // InternalGrana.g:5718:2: rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1
            {
            pushFollow(FOLLOW_36);
            rule__ShapeLayout__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_1__1();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__0"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__0__Impl"
    // InternalGrana.g:5725:1: rule__ShapeLayout__Group_2_1__0__Impl : ( 'size' ) ;
    public final void rule__ShapeLayout__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5729:1: ( ( 'size' ) )
            // InternalGrana.g:5730:1: ( 'size' )
            {
            // InternalGrana.g:5730:1: ( 'size' )
            // InternalGrana.g:5731:1: 'size'
            {
             before(grammarAccess.getShapeLayoutAccess().getSizeKeyword_2_1_0()); 
            match(input,49,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getSizeKeyword_2_1_0()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__0__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__1"
    // InternalGrana.g:5744:1: rule__ShapeLayout__Group_2_1__1 : rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 ;
    public final void rule__ShapeLayout__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5748:1: ( rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 )
            // InternalGrana.g:5749:2: rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2
            {
            pushFollow(FOLLOW_42);
            rule__ShapeLayout__Group_2_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_1__2();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__1"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__1__Impl"
    // InternalGrana.g:5756:1: rule__ShapeLayout__Group_2_1__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5760:1: ( ( ':' ) )
            // InternalGrana.g:5761:1: ( ':' )
            {
            // InternalGrana.g:5761:1: ( ':' )
            // InternalGrana.g:5762:1: ':'
            {
             before(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_1_1()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__1__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__2"
    // InternalGrana.g:5775:1: rule__ShapeLayout__Group_2_1__2 : rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 ;
    public final void rule__ShapeLayout__Group_2_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5779:1: ( rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 )
            // InternalGrana.g:5780:2: rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3
            {
            pushFollow(FOLLOW_25);
            rule__ShapeLayout__Group_2_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_1__3();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__2"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__2__Impl"
    // InternalGrana.g:5787:1: rule__ShapeLayout__Group_2_1__2__Impl : ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5791:1: ( ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) )
            // InternalGrana.g:5792:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            {
            // InternalGrana.g:5792:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            // InternalGrana.g:5793:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2()); 
            // InternalGrana.g:5794:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            // InternalGrana.g:5794:2: rule__ShapeLayout__WidthAssignment_2_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__WidthAssignment_2_1_2();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__2__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__3"
    // InternalGrana.g:5804:1: rule__ShapeLayout__Group_2_1__3 : rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 ;
    public final void rule__ShapeLayout__Group_2_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5808:1: ( rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 )
            // InternalGrana.g:5809:2: rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4
            {
            pushFollow(FOLLOW_42);
            rule__ShapeLayout__Group_2_1__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_1__4();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__3"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__3__Impl"
    // InternalGrana.g:5816:1: rule__ShapeLayout__Group_2_1__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5820:1: ( ( ',' ) )
            // InternalGrana.g:5821:1: ( ',' )
            {
            // InternalGrana.g:5821:1: ( ',' )
            // InternalGrana.g:5822:1: ','
            {
             before(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_1_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_1_3()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__3__Impl"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__4"
    // InternalGrana.g:5835:1: rule__ShapeLayout__Group_2_1__4 : rule__ShapeLayout__Group_2_1__4__Impl ;
    public final void rule__ShapeLayout__Group_2_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5839:1: ( rule__ShapeLayout__Group_2_1__4__Impl )
            // InternalGrana.g:5840:2: rule__ShapeLayout__Group_2_1__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__Group_2_1__4__Impl();

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__4"


    // $ANTLR start "rule__ShapeLayout__Group_2_1__4__Impl"
    // InternalGrana.g:5846:1: rule__ShapeLayout__Group_2_1__4__Impl : ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5850:1: ( ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) )
            // InternalGrana.g:5851:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            {
            // InternalGrana.g:5851:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            // InternalGrana.g:5852:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4()); 
            // InternalGrana.g:5853:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            // InternalGrana.g:5853:2: rule__ShapeLayout__HeightAssignment_2_1_4
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__HeightAssignment_2_1_4();

            state._fsp--;


            }

             after(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4()); 

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
    // $ANTLR end "rule__ShapeLayout__Group_2_1__4__Impl"


    // $ANTLR start "rule__ElkEdge__Group__0"
    // InternalGrana.g:5873:1: rule__ElkEdge__Group__0 : rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1 ;
    public final void rule__ElkEdge__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5877:1: ( rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1 )
            // InternalGrana.g:5878:2: rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdge__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__1();

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
    // $ANTLR end "rule__ElkEdge__Group__0"


    // $ANTLR start "rule__ElkEdge__Group__0__Impl"
    // InternalGrana.g:5885:1: rule__ElkEdge__Group__0__Impl : ( 'edge' ) ;
    public final void rule__ElkEdge__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5889:1: ( ( 'edge' ) )
            // InternalGrana.g:5890:1: ( 'edge' )
            {
            // InternalGrana.g:5890:1: ( 'edge' )
            // InternalGrana.g:5891:1: 'edge'
            {
             before(grammarAccess.getElkEdgeAccess().getEdgeKeyword_0()); 
            match(input,50,FOLLOW_2); 
             after(grammarAccess.getElkEdgeAccess().getEdgeKeyword_0()); 

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
    // $ANTLR end "rule__ElkEdge__Group__0__Impl"


    // $ANTLR start "rule__ElkEdge__Group__1"
    // InternalGrana.g:5904:1: rule__ElkEdge__Group__1 : rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2 ;
    public final void rule__ElkEdge__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5908:1: ( rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2 )
            // InternalGrana.g:5909:2: rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdge__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__2();

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
    // $ANTLR end "rule__ElkEdge__Group__1"


    // $ANTLR start "rule__ElkEdge__Group__1__Impl"
    // InternalGrana.g:5916:1: rule__ElkEdge__Group__1__Impl : ( ( rule__ElkEdge__Group_1__0 )? ) ;
    public final void rule__ElkEdge__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5920:1: ( ( ( rule__ElkEdge__Group_1__0 )? ) )
            // InternalGrana.g:5921:1: ( ( rule__ElkEdge__Group_1__0 )? )
            {
            // InternalGrana.g:5921:1: ( ( rule__ElkEdge__Group_1__0 )? )
            // InternalGrana.g:5922:1: ( rule__ElkEdge__Group_1__0 )?
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_1()); 
            // InternalGrana.g:5923:1: ( rule__ElkEdge__Group_1__0 )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==RULE_ID) ) {
                int LA58_1 = input.LA(2);

                if ( (LA58_1==43) ) {
                    alt58=1;
                }
            }
            switch (alt58) {
                case 1 :
                    // InternalGrana.g:5923:2: rule__ElkEdge__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdge__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkEdgeAccess().getGroup_1()); 

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
    // $ANTLR end "rule__ElkEdge__Group__1__Impl"


    // $ANTLR start "rule__ElkEdge__Group__2"
    // InternalGrana.g:5933:1: rule__ElkEdge__Group__2 : rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3 ;
    public final void rule__ElkEdge__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5937:1: ( rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3 )
            // InternalGrana.g:5938:2: rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3
            {
            pushFollow(FOLLOW_43);
            rule__ElkEdge__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__3();

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
    // $ANTLR end "rule__ElkEdge__Group__2"


    // $ANTLR start "rule__ElkEdge__Group__2__Impl"
    // InternalGrana.g:5945:1: rule__ElkEdge__Group__2__Impl : ( ( rule__ElkEdge__SourcesAssignment_2 ) ) ;
    public final void rule__ElkEdge__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5949:1: ( ( ( rule__ElkEdge__SourcesAssignment_2 ) ) )
            // InternalGrana.g:5950:1: ( ( rule__ElkEdge__SourcesAssignment_2 ) )
            {
            // InternalGrana.g:5950:1: ( ( rule__ElkEdge__SourcesAssignment_2 ) )
            // InternalGrana.g:5951:1: ( rule__ElkEdge__SourcesAssignment_2 )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesAssignment_2()); 
            // InternalGrana.g:5952:1: ( rule__ElkEdge__SourcesAssignment_2 )
            // InternalGrana.g:5952:2: rule__ElkEdge__SourcesAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__SourcesAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeAccess().getSourcesAssignment_2()); 

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
    // $ANTLR end "rule__ElkEdge__Group__2__Impl"


    // $ANTLR start "rule__ElkEdge__Group__3"
    // InternalGrana.g:5962:1: rule__ElkEdge__Group__3 : rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4 ;
    public final void rule__ElkEdge__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5966:1: ( rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4 )
            // InternalGrana.g:5967:2: rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4
            {
            pushFollow(FOLLOW_43);
            rule__ElkEdge__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__4();

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
    // $ANTLR end "rule__ElkEdge__Group__3"


    // $ANTLR start "rule__ElkEdge__Group__3__Impl"
    // InternalGrana.g:5974:1: rule__ElkEdge__Group__3__Impl : ( ( rule__ElkEdge__Group_3__0 )* ) ;
    public final void rule__ElkEdge__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5978:1: ( ( ( rule__ElkEdge__Group_3__0 )* ) )
            // InternalGrana.g:5979:1: ( ( rule__ElkEdge__Group_3__0 )* )
            {
            // InternalGrana.g:5979:1: ( ( rule__ElkEdge__Group_3__0 )* )
            // InternalGrana.g:5980:1: ( rule__ElkEdge__Group_3__0 )*
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_3()); 
            // InternalGrana.g:5981:1: ( rule__ElkEdge__Group_3__0 )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==32) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // InternalGrana.g:5981:2: rule__ElkEdge__Group_3__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__ElkEdge__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);

             after(grammarAccess.getElkEdgeAccess().getGroup_3()); 

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
    // $ANTLR end "rule__ElkEdge__Group__3__Impl"


    // $ANTLR start "rule__ElkEdge__Group__4"
    // InternalGrana.g:5991:1: rule__ElkEdge__Group__4 : rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5 ;
    public final void rule__ElkEdge__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5995:1: ( rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5 )
            // InternalGrana.g:5996:2: rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdge__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__5();

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
    // $ANTLR end "rule__ElkEdge__Group__4"


    // $ANTLR start "rule__ElkEdge__Group__4__Impl"
    // InternalGrana.g:6003:1: rule__ElkEdge__Group__4__Impl : ( '->' ) ;
    public final void rule__ElkEdge__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6007:1: ( ( '->' ) )
            // InternalGrana.g:6008:1: ( '->' )
            {
            // InternalGrana.g:6008:1: ( '->' )
            // InternalGrana.g:6009:1: '->'
            {
             before(grammarAccess.getElkEdgeAccess().getHyphenMinusGreaterThanSignKeyword_4()); 
            match(input,51,FOLLOW_2); 
             after(grammarAccess.getElkEdgeAccess().getHyphenMinusGreaterThanSignKeyword_4()); 

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
    // $ANTLR end "rule__ElkEdge__Group__4__Impl"


    // $ANTLR start "rule__ElkEdge__Group__5"
    // InternalGrana.g:6022:1: rule__ElkEdge__Group__5 : rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6 ;
    public final void rule__ElkEdge__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6026:1: ( rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6 )
            // InternalGrana.g:6027:2: rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6
            {
            pushFollow(FOLLOW_44);
            rule__ElkEdge__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__6();

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
    // $ANTLR end "rule__ElkEdge__Group__5"


    // $ANTLR start "rule__ElkEdge__Group__5__Impl"
    // InternalGrana.g:6034:1: rule__ElkEdge__Group__5__Impl : ( ( rule__ElkEdge__TargetsAssignment_5 ) ) ;
    public final void rule__ElkEdge__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6038:1: ( ( ( rule__ElkEdge__TargetsAssignment_5 ) ) )
            // InternalGrana.g:6039:1: ( ( rule__ElkEdge__TargetsAssignment_5 ) )
            {
            // InternalGrana.g:6039:1: ( ( rule__ElkEdge__TargetsAssignment_5 ) )
            // InternalGrana.g:6040:1: ( rule__ElkEdge__TargetsAssignment_5 )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsAssignment_5()); 
            // InternalGrana.g:6041:1: ( rule__ElkEdge__TargetsAssignment_5 )
            // InternalGrana.g:6041:2: rule__ElkEdge__TargetsAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__TargetsAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeAccess().getTargetsAssignment_5()); 

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
    // $ANTLR end "rule__ElkEdge__Group__5__Impl"


    // $ANTLR start "rule__ElkEdge__Group__6"
    // InternalGrana.g:6051:1: rule__ElkEdge__Group__6 : rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7 ;
    public final void rule__ElkEdge__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6055:1: ( rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7 )
            // InternalGrana.g:6056:2: rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7
            {
            pushFollow(FOLLOW_44);
            rule__ElkEdge__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__7();

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
    // $ANTLR end "rule__ElkEdge__Group__6"


    // $ANTLR start "rule__ElkEdge__Group__6__Impl"
    // InternalGrana.g:6063:1: rule__ElkEdge__Group__6__Impl : ( ( rule__ElkEdge__Group_6__0 )* ) ;
    public final void rule__ElkEdge__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6067:1: ( ( ( rule__ElkEdge__Group_6__0 )* ) )
            // InternalGrana.g:6068:1: ( ( rule__ElkEdge__Group_6__0 )* )
            {
            // InternalGrana.g:6068:1: ( ( rule__ElkEdge__Group_6__0 )* )
            // InternalGrana.g:6069:1: ( rule__ElkEdge__Group_6__0 )*
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_6()); 
            // InternalGrana.g:6070:1: ( rule__ElkEdge__Group_6__0 )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==32) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // InternalGrana.g:6070:2: rule__ElkEdge__Group_6__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__ElkEdge__Group_6__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);

             after(grammarAccess.getElkEdgeAccess().getGroup_6()); 

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
    // $ANTLR end "rule__ElkEdge__Group__6__Impl"


    // $ANTLR start "rule__ElkEdge__Group__7"
    // InternalGrana.g:6080:1: rule__ElkEdge__Group__7 : rule__ElkEdge__Group__7__Impl ;
    public final void rule__ElkEdge__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6084:1: ( rule__ElkEdge__Group__7__Impl )
            // InternalGrana.g:6085:2: rule__ElkEdge__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group__7__Impl();

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
    // $ANTLR end "rule__ElkEdge__Group__7"


    // $ANTLR start "rule__ElkEdge__Group__7__Impl"
    // InternalGrana.g:6091:1: rule__ElkEdge__Group__7__Impl : ( ( rule__ElkEdge__Group_7__0 )? ) ;
    public final void rule__ElkEdge__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6095:1: ( ( ( rule__ElkEdge__Group_7__0 )? ) )
            // InternalGrana.g:6096:1: ( ( rule__ElkEdge__Group_7__0 )? )
            {
            // InternalGrana.g:6096:1: ( ( rule__ElkEdge__Group_7__0 )? )
            // InternalGrana.g:6097:1: ( rule__ElkEdge__Group_7__0 )?
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_7()); 
            // InternalGrana.g:6098:1: ( rule__ElkEdge__Group_7__0 )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==39) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalGrana.g:6098:2: rule__ElkEdge__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdge__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkEdgeAccess().getGroup_7()); 

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
    // $ANTLR end "rule__ElkEdge__Group__7__Impl"


    // $ANTLR start "rule__ElkEdge__Group_1__0"
    // InternalGrana.g:6124:1: rule__ElkEdge__Group_1__0 : rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1 ;
    public final void rule__ElkEdge__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6128:1: ( rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1 )
            // InternalGrana.g:6129:2: rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkEdge__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_1__1();

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
    // $ANTLR end "rule__ElkEdge__Group_1__0"


    // $ANTLR start "rule__ElkEdge__Group_1__0__Impl"
    // InternalGrana.g:6136:1: rule__ElkEdge__Group_1__0__Impl : ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkEdge__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6140:1: ( ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) ) )
            // InternalGrana.g:6141:1: ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) )
            {
            // InternalGrana.g:6141:1: ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) )
            // InternalGrana.g:6142:1: ( rule__ElkEdge__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkEdgeAccess().getIdentifierAssignment_1_0()); 
            // InternalGrana.g:6143:1: ( rule__ElkEdge__IdentifierAssignment_1_0 )
            // InternalGrana.g:6143:2: rule__ElkEdge__IdentifierAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__IdentifierAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeAccess().getIdentifierAssignment_1_0()); 

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
    // $ANTLR end "rule__ElkEdge__Group_1__0__Impl"


    // $ANTLR start "rule__ElkEdge__Group_1__1"
    // InternalGrana.g:6153:1: rule__ElkEdge__Group_1__1 : rule__ElkEdge__Group_1__1__Impl ;
    public final void rule__ElkEdge__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6157:1: ( rule__ElkEdge__Group_1__1__Impl )
            // InternalGrana.g:6158:2: rule__ElkEdge__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_1__1__Impl();

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
    // $ANTLR end "rule__ElkEdge__Group_1__1"


    // $ANTLR start "rule__ElkEdge__Group_1__1__Impl"
    // InternalGrana.g:6164:1: rule__ElkEdge__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdge__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6168:1: ( ( ':' ) )
            // InternalGrana.g:6169:1: ( ':' )
            {
            // InternalGrana.g:6169:1: ( ':' )
            // InternalGrana.g:6170:1: ':'
            {
             before(grammarAccess.getElkEdgeAccess().getColonKeyword_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkEdgeAccess().getColonKeyword_1_1()); 

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
    // $ANTLR end "rule__ElkEdge__Group_1__1__Impl"


    // $ANTLR start "rule__ElkEdge__Group_3__0"
    // InternalGrana.g:6187:1: rule__ElkEdge__Group_3__0 : rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1 ;
    public final void rule__ElkEdge__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6191:1: ( rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1 )
            // InternalGrana.g:6192:2: rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdge__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_3__1();

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
    // $ANTLR end "rule__ElkEdge__Group_3__0"


    // $ANTLR start "rule__ElkEdge__Group_3__0__Impl"
    // InternalGrana.g:6199:1: rule__ElkEdge__Group_3__0__Impl : ( ',' ) ;
    public final void rule__ElkEdge__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6203:1: ( ( ',' ) )
            // InternalGrana.g:6204:1: ( ',' )
            {
            // InternalGrana.g:6204:1: ( ',' )
            // InternalGrana.g:6205:1: ','
            {
             before(grammarAccess.getElkEdgeAccess().getCommaKeyword_3_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkEdgeAccess().getCommaKeyword_3_0()); 

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
    // $ANTLR end "rule__ElkEdge__Group_3__0__Impl"


    // $ANTLR start "rule__ElkEdge__Group_3__1"
    // InternalGrana.g:6218:1: rule__ElkEdge__Group_3__1 : rule__ElkEdge__Group_3__1__Impl ;
    public final void rule__ElkEdge__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6222:1: ( rule__ElkEdge__Group_3__1__Impl )
            // InternalGrana.g:6223:2: rule__ElkEdge__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_3__1__Impl();

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
    // $ANTLR end "rule__ElkEdge__Group_3__1"


    // $ANTLR start "rule__ElkEdge__Group_3__1__Impl"
    // InternalGrana.g:6229:1: rule__ElkEdge__Group_3__1__Impl : ( ( rule__ElkEdge__SourcesAssignment_3_1 ) ) ;
    public final void rule__ElkEdge__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6233:1: ( ( ( rule__ElkEdge__SourcesAssignment_3_1 ) ) )
            // InternalGrana.g:6234:1: ( ( rule__ElkEdge__SourcesAssignment_3_1 ) )
            {
            // InternalGrana.g:6234:1: ( ( rule__ElkEdge__SourcesAssignment_3_1 ) )
            // InternalGrana.g:6235:1: ( rule__ElkEdge__SourcesAssignment_3_1 )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesAssignment_3_1()); 
            // InternalGrana.g:6236:1: ( rule__ElkEdge__SourcesAssignment_3_1 )
            // InternalGrana.g:6236:2: rule__ElkEdge__SourcesAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__SourcesAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeAccess().getSourcesAssignment_3_1()); 

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
    // $ANTLR end "rule__ElkEdge__Group_3__1__Impl"


    // $ANTLR start "rule__ElkEdge__Group_6__0"
    // InternalGrana.g:6250:1: rule__ElkEdge__Group_6__0 : rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1 ;
    public final void rule__ElkEdge__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6254:1: ( rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1 )
            // InternalGrana.g:6255:2: rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdge__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_6__1();

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
    // $ANTLR end "rule__ElkEdge__Group_6__0"


    // $ANTLR start "rule__ElkEdge__Group_6__0__Impl"
    // InternalGrana.g:6262:1: rule__ElkEdge__Group_6__0__Impl : ( ',' ) ;
    public final void rule__ElkEdge__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6266:1: ( ( ',' ) )
            // InternalGrana.g:6267:1: ( ',' )
            {
            // InternalGrana.g:6267:1: ( ',' )
            // InternalGrana.g:6268:1: ','
            {
             before(grammarAccess.getElkEdgeAccess().getCommaKeyword_6_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkEdgeAccess().getCommaKeyword_6_0()); 

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
    // $ANTLR end "rule__ElkEdge__Group_6__0__Impl"


    // $ANTLR start "rule__ElkEdge__Group_6__1"
    // InternalGrana.g:6281:1: rule__ElkEdge__Group_6__1 : rule__ElkEdge__Group_6__1__Impl ;
    public final void rule__ElkEdge__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6285:1: ( rule__ElkEdge__Group_6__1__Impl )
            // InternalGrana.g:6286:2: rule__ElkEdge__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_6__1__Impl();

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
    // $ANTLR end "rule__ElkEdge__Group_6__1"


    // $ANTLR start "rule__ElkEdge__Group_6__1__Impl"
    // InternalGrana.g:6292:1: rule__ElkEdge__Group_6__1__Impl : ( ( rule__ElkEdge__TargetsAssignment_6_1 ) ) ;
    public final void rule__ElkEdge__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6296:1: ( ( ( rule__ElkEdge__TargetsAssignment_6_1 ) ) )
            // InternalGrana.g:6297:1: ( ( rule__ElkEdge__TargetsAssignment_6_1 ) )
            {
            // InternalGrana.g:6297:1: ( ( rule__ElkEdge__TargetsAssignment_6_1 ) )
            // InternalGrana.g:6298:1: ( rule__ElkEdge__TargetsAssignment_6_1 )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsAssignment_6_1()); 
            // InternalGrana.g:6299:1: ( rule__ElkEdge__TargetsAssignment_6_1 )
            // InternalGrana.g:6299:2: rule__ElkEdge__TargetsAssignment_6_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__TargetsAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeAccess().getTargetsAssignment_6_1()); 

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
    // $ANTLR end "rule__ElkEdge__Group_6__1__Impl"


    // $ANTLR start "rule__ElkEdge__Group_7__0"
    // InternalGrana.g:6313:1: rule__ElkEdge__Group_7__0 : rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1 ;
    public final void rule__ElkEdge__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6317:1: ( rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1 )
            // InternalGrana.g:6318:2: rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1
            {
            pushFollow(FOLLOW_37);
            rule__ElkEdge__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_7__1();

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
    // $ANTLR end "rule__ElkEdge__Group_7__0"


    // $ANTLR start "rule__ElkEdge__Group_7__0__Impl"
    // InternalGrana.g:6325:1: rule__ElkEdge__Group_7__0__Impl : ( '{' ) ;
    public final void rule__ElkEdge__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6329:1: ( ( '{' ) )
            // InternalGrana.g:6330:1: ( '{' )
            {
            // InternalGrana.g:6330:1: ( '{' )
            // InternalGrana.g:6331:1: '{'
            {
             before(grammarAccess.getElkEdgeAccess().getLeftCurlyBracketKeyword_7_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getElkEdgeAccess().getLeftCurlyBracketKeyword_7_0()); 

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
    // $ANTLR end "rule__ElkEdge__Group_7__0__Impl"


    // $ANTLR start "rule__ElkEdge__Group_7__1"
    // InternalGrana.g:6344:1: rule__ElkEdge__Group_7__1 : rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2 ;
    public final void rule__ElkEdge__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6348:1: ( rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2 )
            // InternalGrana.g:6349:2: rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2
            {
            pushFollow(FOLLOW_37);
            rule__ElkEdge__Group_7__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_7__2();

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
    // $ANTLR end "rule__ElkEdge__Group_7__1"


    // $ANTLR start "rule__ElkEdge__Group_7__1__Impl"
    // InternalGrana.g:6356:1: rule__ElkEdge__Group_7__1__Impl : ( ( ruleEdgeLayout )? ) ;
    public final void rule__ElkEdge__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6360:1: ( ( ( ruleEdgeLayout )? ) )
            // InternalGrana.g:6361:1: ( ( ruleEdgeLayout )? )
            {
            // InternalGrana.g:6361:1: ( ( ruleEdgeLayout )? )
            // InternalGrana.g:6362:1: ( ruleEdgeLayout )?
            {
             before(grammarAccess.getElkEdgeAccess().getEdgeLayoutParserRuleCall_7_1()); 
            // InternalGrana.g:6363:1: ( ruleEdgeLayout )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==45) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalGrana.g:6363:3: ruleEdgeLayout
                    {
                    pushFollow(FOLLOW_2);
                    ruleEdgeLayout();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkEdgeAccess().getEdgeLayoutParserRuleCall_7_1()); 

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
    // $ANTLR end "rule__ElkEdge__Group_7__1__Impl"


    // $ANTLR start "rule__ElkEdge__Group_7__2"
    // InternalGrana.g:6373:1: rule__ElkEdge__Group_7__2 : rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3 ;
    public final void rule__ElkEdge__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6377:1: ( rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3 )
            // InternalGrana.g:6378:2: rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3
            {
            pushFollow(FOLLOW_37);
            rule__ElkEdge__Group_7__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_7__3();

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
    // $ANTLR end "rule__ElkEdge__Group_7__2"


    // $ANTLR start "rule__ElkEdge__Group_7__2__Impl"
    // InternalGrana.g:6385:1: rule__ElkEdge__Group_7__2__Impl : ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* ) ;
    public final void rule__ElkEdge__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6389:1: ( ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* ) )
            // InternalGrana.g:6390:1: ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* )
            {
            // InternalGrana.g:6390:1: ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* )
            // InternalGrana.g:6391:1: ( rule__ElkEdge__PropertiesAssignment_7_2 )*
            {
             before(grammarAccess.getElkEdgeAccess().getPropertiesAssignment_7_2()); 
            // InternalGrana.g:6392:1: ( rule__ElkEdge__PropertiesAssignment_7_2 )*
            loop63:
            do {
                int alt63=2;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==RULE_ID) ) {
                    alt63=1;
                }


                switch (alt63) {
            	case 1 :
            	    // InternalGrana.g:6392:2: rule__ElkEdge__PropertiesAssignment_7_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkEdge__PropertiesAssignment_7_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop63;
                }
            } while (true);

             after(grammarAccess.getElkEdgeAccess().getPropertiesAssignment_7_2()); 

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
    // $ANTLR end "rule__ElkEdge__Group_7__2__Impl"


    // $ANTLR start "rule__ElkEdge__Group_7__3"
    // InternalGrana.g:6402:1: rule__ElkEdge__Group_7__3 : rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4 ;
    public final void rule__ElkEdge__Group_7__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6406:1: ( rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4 )
            // InternalGrana.g:6407:2: rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4
            {
            pushFollow(FOLLOW_37);
            rule__ElkEdge__Group_7__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_7__4();

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
    // $ANTLR end "rule__ElkEdge__Group_7__3"


    // $ANTLR start "rule__ElkEdge__Group_7__3__Impl"
    // InternalGrana.g:6414:1: rule__ElkEdge__Group_7__3__Impl : ( ( rule__ElkEdge__LabelsAssignment_7_3 )* ) ;
    public final void rule__ElkEdge__Group_7__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6418:1: ( ( ( rule__ElkEdge__LabelsAssignment_7_3 )* ) )
            // InternalGrana.g:6419:1: ( ( rule__ElkEdge__LabelsAssignment_7_3 )* )
            {
            // InternalGrana.g:6419:1: ( ( rule__ElkEdge__LabelsAssignment_7_3 )* )
            // InternalGrana.g:6420:1: ( rule__ElkEdge__LabelsAssignment_7_3 )*
            {
             before(grammarAccess.getElkEdgeAccess().getLabelsAssignment_7_3()); 
            // InternalGrana.g:6421:1: ( rule__ElkEdge__LabelsAssignment_7_3 )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==42) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // InternalGrana.g:6421:2: rule__ElkEdge__LabelsAssignment_7_3
            	    {
            	    pushFollow(FOLLOW_38);
            	    rule__ElkEdge__LabelsAssignment_7_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop64;
                }
            } while (true);

             after(grammarAccess.getElkEdgeAccess().getLabelsAssignment_7_3()); 

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
    // $ANTLR end "rule__ElkEdge__Group_7__3__Impl"


    // $ANTLR start "rule__ElkEdge__Group_7__4"
    // InternalGrana.g:6431:1: rule__ElkEdge__Group_7__4 : rule__ElkEdge__Group_7__4__Impl ;
    public final void rule__ElkEdge__Group_7__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6435:1: ( rule__ElkEdge__Group_7__4__Impl )
            // InternalGrana.g:6436:2: rule__ElkEdge__Group_7__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdge__Group_7__4__Impl();

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
    // $ANTLR end "rule__ElkEdge__Group_7__4"


    // $ANTLR start "rule__ElkEdge__Group_7__4__Impl"
    // InternalGrana.g:6442:1: rule__ElkEdge__Group_7__4__Impl : ( '}' ) ;
    public final void rule__ElkEdge__Group_7__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6446:1: ( ( '}' ) )
            // InternalGrana.g:6447:1: ( '}' )
            {
            // InternalGrana.g:6447:1: ( '}' )
            // InternalGrana.g:6448:1: '}'
            {
             before(grammarAccess.getElkEdgeAccess().getRightCurlyBracketKeyword_7_4()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getElkEdgeAccess().getRightCurlyBracketKeyword_7_4()); 

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
    // $ANTLR end "rule__ElkEdge__Group_7__4__Impl"


    // $ANTLR start "rule__EdgeLayout__Group__0"
    // InternalGrana.g:6471:1: rule__EdgeLayout__Group__0 : rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 ;
    public final void rule__EdgeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6475:1: ( rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 )
            // InternalGrana.g:6476:2: rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1
            {
            pushFollow(FOLLOW_39);
            rule__EdgeLayout__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeLayout__Group__1();

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
    // $ANTLR end "rule__EdgeLayout__Group__0"


    // $ANTLR start "rule__EdgeLayout__Group__0__Impl"
    // InternalGrana.g:6483:1: rule__EdgeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__EdgeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6487:1: ( ( 'layout' ) )
            // InternalGrana.g:6488:1: ( 'layout' )
            {
            // InternalGrana.g:6488:1: ( 'layout' )
            // InternalGrana.g:6489:1: 'layout'
            {
             before(grammarAccess.getEdgeLayoutAccess().getLayoutKeyword_0()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getEdgeLayoutAccess().getLayoutKeyword_0()); 

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
    // $ANTLR end "rule__EdgeLayout__Group__0__Impl"


    // $ANTLR start "rule__EdgeLayout__Group__1"
    // InternalGrana.g:6502:1: rule__EdgeLayout__Group__1 : rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 ;
    public final void rule__EdgeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6506:1: ( rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 )
            // InternalGrana.g:6507:2: rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2
            {
            pushFollow(FOLLOW_45);
            rule__EdgeLayout__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeLayout__Group__2();

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
    // $ANTLR end "rule__EdgeLayout__Group__1"


    // $ANTLR start "rule__EdgeLayout__Group__1__Impl"
    // InternalGrana.g:6514:1: rule__EdgeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__EdgeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6518:1: ( ( '[' ) )
            // InternalGrana.g:6519:1: ( '[' )
            {
            // InternalGrana.g:6519:1: ( '[' )
            // InternalGrana.g:6520:1: '['
            {
             before(grammarAccess.getEdgeLayoutAccess().getLeftSquareBracketKeyword_1()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getEdgeLayoutAccess().getLeftSquareBracketKeyword_1()); 

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
    // $ANTLR end "rule__EdgeLayout__Group__1__Impl"


    // $ANTLR start "rule__EdgeLayout__Group__2"
    // InternalGrana.g:6533:1: rule__EdgeLayout__Group__2 : rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 ;
    public final void rule__EdgeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6537:1: ( rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 )
            // InternalGrana.g:6538:2: rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3
            {
            pushFollow(FOLLOW_41);
            rule__EdgeLayout__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EdgeLayout__Group__3();

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
    // $ANTLR end "rule__EdgeLayout__Group__2"


    // $ANTLR start "rule__EdgeLayout__Group__2__Impl"
    // InternalGrana.g:6545:1: rule__EdgeLayout__Group__2__Impl : ( ( rule__EdgeLayout__Alternatives_2 ) ) ;
    public final void rule__EdgeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6549:1: ( ( ( rule__EdgeLayout__Alternatives_2 ) ) )
            // InternalGrana.g:6550:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            {
            // InternalGrana.g:6550:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            // InternalGrana.g:6551:1: ( rule__EdgeLayout__Alternatives_2 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getAlternatives_2()); 
            // InternalGrana.g:6552:1: ( rule__EdgeLayout__Alternatives_2 )
            // InternalGrana.g:6552:2: rule__EdgeLayout__Alternatives_2
            {
            pushFollow(FOLLOW_2);
            rule__EdgeLayout__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getEdgeLayoutAccess().getAlternatives_2()); 

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
    // $ANTLR end "rule__EdgeLayout__Group__2__Impl"


    // $ANTLR start "rule__EdgeLayout__Group__3"
    // InternalGrana.g:6562:1: rule__EdgeLayout__Group__3 : rule__EdgeLayout__Group__3__Impl ;
    public final void rule__EdgeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6566:1: ( rule__EdgeLayout__Group__3__Impl )
            // InternalGrana.g:6567:2: rule__EdgeLayout__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EdgeLayout__Group__3__Impl();

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
    // $ANTLR end "rule__EdgeLayout__Group__3"


    // $ANTLR start "rule__EdgeLayout__Group__3__Impl"
    // InternalGrana.g:6573:1: rule__EdgeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__EdgeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6577:1: ( ( ']' ) )
            // InternalGrana.g:6578:1: ( ']' )
            {
            // InternalGrana.g:6578:1: ( ']' )
            // InternalGrana.g:6579:1: ']'
            {
             before(grammarAccess.getEdgeLayoutAccess().getRightSquareBracketKeyword_3()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getEdgeLayoutAccess().getRightSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__EdgeLayout__Group__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group__0"
    // InternalGrana.g:6600:1: rule__ElkSingleEdgeSection__Group__0 : rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 ;
    public final void rule__ElkSingleEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6604:1: ( rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 )
            // InternalGrana.g:6605:2: rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1
            {
            pushFollow(FOLLOW_46);
            rule__ElkSingleEdgeSection__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group__0__Impl"
    // InternalGrana.g:6612:1: rule__ElkSingleEdgeSection__Group__0__Impl : ( () ) ;
    public final void rule__ElkSingleEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6616:1: ( ( () ) )
            // InternalGrana.g:6617:1: ( () )
            {
            // InternalGrana.g:6617:1: ( () )
            // InternalGrana.g:6618:1: ()
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0()); 
            // InternalGrana.g:6619:1: ()
            // InternalGrana.g:6621:1: 
            {
            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__Group__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group__1"
    // InternalGrana.g:6631:1: rule__ElkSingleEdgeSection__Group__1 : rule__ElkSingleEdgeSection__Group__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6635:1: ( rule__ElkSingleEdgeSection__Group__1__Impl )
            // InternalGrana.g:6636:2: rule__ElkSingleEdgeSection__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group__1__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group__1__Impl"
    // InternalGrana.g:6642:1: rule__ElkSingleEdgeSection__Group__1__Impl : ( ( rule__ElkSingleEdgeSection__Group_1__0 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6646:1: ( ( ( rule__ElkSingleEdgeSection__Group_1__0 ) ) )
            // InternalGrana.g:6647:1: ( ( rule__ElkSingleEdgeSection__Group_1__0 ) )
            {
            // InternalGrana.g:6647:1: ( ( rule__ElkSingleEdgeSection__Group_1__0 ) )
            // InternalGrana.g:6648:1: ( rule__ElkSingleEdgeSection__Group_1__0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1()); 
            // InternalGrana.g:6649:1: ( rule__ElkSingleEdgeSection__Group_1__0 )
            // InternalGrana.g:6649:2: rule__ElkSingleEdgeSection__Group_1__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1__0();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__0"
    // InternalGrana.g:6663:1: rule__ElkSingleEdgeSection__Group_1__0 : rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6667:1: ( rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1 )
            // InternalGrana.g:6668:2: rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1
            {
            pushFollow(FOLLOW_47);
            rule__ElkSingleEdgeSection__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__0__Impl"
    // InternalGrana.g:6675:1: rule__ElkSingleEdgeSection__Group_1__0__Impl : ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6679:1: ( ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) ) )
            // InternalGrana.g:6680:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) )
            {
            // InternalGrana.g:6680:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) )
            // InternalGrana.g:6681:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0()); 
            // InternalGrana.g:6682:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 )
            // InternalGrana.g:6682:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__1"
    // InternalGrana.g:6692:1: rule__ElkSingleEdgeSection__Group_1__1 : rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6696:1: ( rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2 )
            // InternalGrana.g:6697:2: rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2
            {
            pushFollow(FOLLOW_47);
            rule__ElkSingleEdgeSection__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1__2();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__1__Impl"
    // InternalGrana.g:6704:1: rule__ElkSingleEdgeSection__Group_1__1__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6708:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? ) )
            // InternalGrana.g:6709:1: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? )
            {
            // InternalGrana.g:6709:1: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? )
            // InternalGrana.g:6710:1: ( rule__ElkSingleEdgeSection__Group_1_1__0 )?
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 
            // InternalGrana.g:6711:1: ( rule__ElkSingleEdgeSection__Group_1_1__0 )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==56) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalGrana.g:6711:2: rule__ElkSingleEdgeSection__Group_1_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__2"
    // InternalGrana.g:6721:1: rule__ElkSingleEdgeSection__Group_1__2 : rule__ElkSingleEdgeSection__Group_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6725:1: ( rule__ElkSingleEdgeSection__Group_1__2__Impl )
            // InternalGrana.g:6726:2: rule__ElkSingleEdgeSection__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1__2__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1__2__Impl"
    // InternalGrana.g:6732:1: rule__ElkSingleEdgeSection__Group_1__2__Impl : ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6736:1: ( ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* ) )
            // InternalGrana.g:6737:1: ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* )
            {
            // InternalGrana.g:6737:1: ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* )
            // InternalGrana.g:6738:1: ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesAssignment_1_2()); 
            // InternalGrana.g:6739:1: ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==RULE_ID) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // InternalGrana.g:6739:2: rule__ElkSingleEdgeSection__PropertiesAssignment_1_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkSingleEdgeSection__PropertiesAssignment_1_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop66;
                }
            } while (true);

             after(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesAssignment_1_2()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__0"
    // InternalGrana.g:6755:1: rule__ElkSingleEdgeSection__Group_1_0_0__0 : rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6759:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1 )
            // InternalGrana.g:6760:2: rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_0__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl"
    // InternalGrana.g:6767:1: rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6771:1: ( ( 'incoming' ) )
            // InternalGrana.g:6772:1: ( 'incoming' )
            {
            // InternalGrana.g:6772:1: ( 'incoming' )
            // InternalGrana.g:6773:1: 'incoming'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0_0()); 
            match(input,52,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__1"
    // InternalGrana.g:6786:1: rule__ElkSingleEdgeSection__Group_1_0_0__1 : rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6790:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2 )
            // InternalGrana.g:6791:2: rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2
            {
            pushFollow(FOLLOW_8);
            rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_0__2();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl"
    // InternalGrana.g:6798:1: rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6802:1: ( ( ':' ) )
            // InternalGrana.g:6803:1: ( ':' )
            {
            // InternalGrana.g:6803:1: ( ':' )
            // InternalGrana.g:6804:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_0_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_0_1()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__2"
    // InternalGrana.g:6817:1: rule__ElkSingleEdgeSection__Group_1_0_0__2 : rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6821:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl )
            // InternalGrana.g:6822:2: rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl"
    // InternalGrana.g:6828:1: rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl : ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6832:1: ( ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) ) )
            // InternalGrana.g:6833:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) )
            {
            // InternalGrana.g:6833:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) )
            // InternalGrana.g:6834:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_0_2()); 
            // InternalGrana.g:6835:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 )
            // InternalGrana.g:6835:2: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_0_2()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__0"
    // InternalGrana.g:6851:1: rule__ElkSingleEdgeSection__Group_1_0_1__0 : rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6855:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1 )
            // InternalGrana.g:6856:2: rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_1__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl"
    // InternalGrana.g:6863:1: rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6867:1: ( ( 'outgoing' ) )
            // InternalGrana.g:6868:1: ( 'outgoing' )
            {
            // InternalGrana.g:6868:1: ( 'outgoing' )
            // InternalGrana.g:6869:1: 'outgoing'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_0_1_0()); 
            match(input,53,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_0_1_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__1"
    // InternalGrana.g:6882:1: rule__ElkSingleEdgeSection__Group_1_0_1__1 : rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6886:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2 )
            // InternalGrana.g:6887:2: rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2
            {
            pushFollow(FOLLOW_8);
            rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_1__2();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl"
    // InternalGrana.g:6894:1: rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6898:1: ( ( ':' ) )
            // InternalGrana.g:6899:1: ( ':' )
            {
            // InternalGrana.g:6899:1: ( ':' )
            // InternalGrana.g:6900:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1_1()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__2"
    // InternalGrana.g:6913:1: rule__ElkSingleEdgeSection__Group_1_0_1__2 : rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6917:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl )
            // InternalGrana.g:6918:2: rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl"
    // InternalGrana.g:6924:1: rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl : ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6928:1: ( ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) ) )
            // InternalGrana.g:6929:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) )
            {
            // InternalGrana.g:6929:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) )
            // InternalGrana.g:6930:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_0_1_2()); 
            // InternalGrana.g:6931:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 )
            // InternalGrana.g:6931:2: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_0_1_2()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__0"
    // InternalGrana.g:6947:1: rule__ElkSingleEdgeSection__Group_1_0_2__0 : rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6951:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1 )
            // InternalGrana.g:6952:2: rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl"
    // InternalGrana.g:6959:1: rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6963:1: ( ( 'start' ) )
            // InternalGrana.g:6964:1: ( 'start' )
            {
            // InternalGrana.g:6964:1: ( 'start' )
            // InternalGrana.g:6965:1: 'start'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_0_2_0()); 
            match(input,54,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_0_2_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__1"
    // InternalGrana.g:6978:1: rule__ElkSingleEdgeSection__Group_1_0_2__1 : rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6982:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2 )
            // InternalGrana.g:6983:2: rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2
            {
            pushFollow(FOLLOW_42);
            rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__2();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl"
    // InternalGrana.g:6990:1: rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6994:1: ( ( ':' ) )
            // InternalGrana.g:6995:1: ( ':' )
            {
            // InternalGrana.g:6995:1: ( ':' )
            // InternalGrana.g:6996:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_2_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_2_1()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__2"
    // InternalGrana.g:7009:1: rule__ElkSingleEdgeSection__Group_1_0_2__2 : rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7013:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3 )
            // InternalGrana.g:7014:2: rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3
            {
            pushFollow(FOLLOW_25);
            rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__3();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl"
    // InternalGrana.g:7021:1: rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl : ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7025:1: ( ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) ) )
            // InternalGrana.g:7026:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) )
            {
            // InternalGrana.g:7026:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) )
            // InternalGrana.g:7027:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_0_2_2()); 
            // InternalGrana.g:7028:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 )
            // InternalGrana.g:7028:2: rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_0_2_2()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__3"
    // InternalGrana.g:7038:1: rule__ElkSingleEdgeSection__Group_1_0_2__3 : rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7042:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4 )
            // InternalGrana.g:7043:2: rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4
            {
            pushFollow(FOLLOW_42);
            rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__4();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl"
    // InternalGrana.g:7050:1: rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7054:1: ( ( ',' ) )
            // InternalGrana.g:7055:1: ( ',' )
            {
            // InternalGrana.g:7055:1: ( ',' )
            // InternalGrana.g:7056:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_2_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_2_3()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__4"
    // InternalGrana.g:7069:1: rule__ElkSingleEdgeSection__Group_1_0_2__4 : rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7073:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl )
            // InternalGrana.g:7074:2: rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__4"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl"
    // InternalGrana.g:7080:1: rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl : ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7084:1: ( ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) ) )
            // InternalGrana.g:7085:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) )
            {
            // InternalGrana.g:7085:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) )
            // InternalGrana.g:7086:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_0_2_4()); 
            // InternalGrana.g:7087:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 )
            // InternalGrana.g:7087:2: rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_0_2_4()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__0"
    // InternalGrana.g:7107:1: rule__ElkSingleEdgeSection__Group_1_0_3__0 : rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7111:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1 )
            // InternalGrana.g:7112:2: rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl"
    // InternalGrana.g:7119:1: rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7123:1: ( ( 'end' ) )
            // InternalGrana.g:7124:1: ( 'end' )
            {
            // InternalGrana.g:7124:1: ( 'end' )
            // InternalGrana.g:7125:1: 'end'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_0_3_0()); 
            match(input,55,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_0_3_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__1"
    // InternalGrana.g:7138:1: rule__ElkSingleEdgeSection__Group_1_0_3__1 : rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7142:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2 )
            // InternalGrana.g:7143:2: rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2
            {
            pushFollow(FOLLOW_42);
            rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__2();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl"
    // InternalGrana.g:7150:1: rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7154:1: ( ( ':' ) )
            // InternalGrana.g:7155:1: ( ':' )
            {
            // InternalGrana.g:7155:1: ( ':' )
            // InternalGrana.g:7156:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_3_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_3_1()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__2"
    // InternalGrana.g:7169:1: rule__ElkSingleEdgeSection__Group_1_0_3__2 : rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7173:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3 )
            // InternalGrana.g:7174:2: rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3
            {
            pushFollow(FOLLOW_25);
            rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__3();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl"
    // InternalGrana.g:7181:1: rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl : ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7185:1: ( ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) ) )
            // InternalGrana.g:7186:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) )
            {
            // InternalGrana.g:7186:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) )
            // InternalGrana.g:7187:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_0_3_2()); 
            // InternalGrana.g:7188:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 )
            // InternalGrana.g:7188:2: rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_0_3_2()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__3"
    // InternalGrana.g:7198:1: rule__ElkSingleEdgeSection__Group_1_0_3__3 : rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7202:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4 )
            // InternalGrana.g:7203:2: rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4
            {
            pushFollow(FOLLOW_42);
            rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__4();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl"
    // InternalGrana.g:7210:1: rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7214:1: ( ( ',' ) )
            // InternalGrana.g:7215:1: ( ',' )
            {
            // InternalGrana.g:7215:1: ( ',' )
            // InternalGrana.g:7216:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_3_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_3_3()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__4"
    // InternalGrana.g:7229:1: rule__ElkSingleEdgeSection__Group_1_0_3__4 : rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7233:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl )
            // InternalGrana.g:7234:2: rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__4"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl"
    // InternalGrana.g:7240:1: rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl : ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7244:1: ( ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) ) )
            // InternalGrana.g:7245:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) )
            {
            // InternalGrana.g:7245:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) )
            // InternalGrana.g:7246:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_0_3_4()); 
            // InternalGrana.g:7247:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 )
            // InternalGrana.g:7247:2: rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_0_3_4()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__0"
    // InternalGrana.g:7267:1: rule__ElkSingleEdgeSection__Group_1_1__0 : rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7271:1: ( rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 )
            // InternalGrana.g:7272:2: rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkSingleEdgeSection__Group_1_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__0__Impl"
    // InternalGrana.g:7279:1: rule__ElkSingleEdgeSection__Group_1_1__0__Impl : ( 'bends' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7283:1: ( ( 'bends' ) )
            // InternalGrana.g:7284:1: ( 'bends' )
            {
            // InternalGrana.g:7284:1: ( 'bends' )
            // InternalGrana.g:7285:1: 'bends'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_1_0()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_1_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__1"
    // InternalGrana.g:7298:1: rule__ElkSingleEdgeSection__Group_1_1__1 : rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7302:1: ( rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 )
            // InternalGrana.g:7303:2: rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2
            {
            pushFollow(FOLLOW_42);
            rule__ElkSingleEdgeSection__Group_1_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__2();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__1__Impl"
    // InternalGrana.g:7310:1: rule__ElkSingleEdgeSection__Group_1_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7314:1: ( ( ':' ) )
            // InternalGrana.g:7315:1: ( ':' )
            {
            // InternalGrana.g:7315:1: ( ':' )
            // InternalGrana.g:7316:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_1_1()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__1__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__2"
    // InternalGrana.g:7329:1: rule__ElkSingleEdgeSection__Group_1_1__2 : rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7333:1: ( rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3 )
            // InternalGrana.g:7334:2: rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3
            {
            pushFollow(FOLLOW_48);
            rule__ElkSingleEdgeSection__Group_1_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__3();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__2__Impl"
    // InternalGrana.g:7341:1: rule__ElkSingleEdgeSection__Group_1_1__2__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7345:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) ) )
            // InternalGrana.g:7346:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) )
            {
            // InternalGrana.g:7346:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) )
            // InternalGrana.g:7347:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_2()); 
            // InternalGrana.g:7348:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 )
            // InternalGrana.g:7348:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_2()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__2__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__3"
    // InternalGrana.g:7358:1: rule__ElkSingleEdgeSection__Group_1_1__3 : rule__ElkSingleEdgeSection__Group_1_1__3__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7362:1: ( rule__ElkSingleEdgeSection__Group_1_1__3__Impl )
            // InternalGrana.g:7363:2: rule__ElkSingleEdgeSection__Group_1_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1__3__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__3"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1__3__Impl"
    // InternalGrana.g:7369:1: rule__ElkSingleEdgeSection__Group_1_1__3__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7373:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* ) )
            // InternalGrana.g:7374:1: ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* )
            {
            // InternalGrana.g:7374:1: ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* )
            // InternalGrana.g:7375:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1_3()); 
            // InternalGrana.g:7376:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==57) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // InternalGrana.g:7376:2: rule__ElkSingleEdgeSection__Group_1_1_3__0
            	    {
            	    pushFollow(FOLLOW_49);
            	    rule__ElkSingleEdgeSection__Group_1_1_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop67;
                }
            } while (true);

             after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1_3()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1__3__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1_3__0"
    // InternalGrana.g:7394:1: rule__ElkSingleEdgeSection__Group_1_1_3__0 : rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7398:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1 )
            // InternalGrana.g:7399:2: rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1
            {
            pushFollow(FOLLOW_42);
            rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1_3__1();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1_3__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl"
    // InternalGrana.g:7406:1: rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl : ( '|' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7410:1: ( ( '|' ) )
            // InternalGrana.g:7411:1: ( '|' )
            {
            // InternalGrana.g:7411:1: ( '|' )
            // InternalGrana.g:7412:1: '|'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_1_3_0()); 
            match(input,57,FOLLOW_2); 
             after(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_1_3_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1_3__1"
    // InternalGrana.g:7425:1: rule__ElkSingleEdgeSection__Group_1_1_3__1 : rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7429:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl )
            // InternalGrana.g:7430:2: rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1_3__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl"
    // InternalGrana.g:7436:1: rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7440:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) ) )
            // InternalGrana.g:7441:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) )
            {
            // InternalGrana.g:7441:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) )
            // InternalGrana.g:7442:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_3_1()); 
            // InternalGrana.g:7443:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 )
            // InternalGrana.g:7443:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_3_1()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__0"
    // InternalGrana.g:7457:1: rule__ElkEdgeSection__Group__0 : rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 ;
    public final void rule__ElkEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7461:1: ( rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 )
            // InternalGrana.g:7462:2: rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdgeSection__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group__0"


    // $ANTLR start "rule__ElkEdgeSection__Group__0__Impl"
    // InternalGrana.g:7469:1: rule__ElkEdgeSection__Group__0__Impl : ( 'section' ) ;
    public final void rule__ElkEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7473:1: ( ( 'section' ) )
            // InternalGrana.g:7474:1: ( 'section' )
            {
            // InternalGrana.g:7474:1: ( 'section' )
            // InternalGrana.g:7475:1: 'section'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getSectionKeyword_0()); 
            match(input,58,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getSectionKeyword_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__1"
    // InternalGrana.g:7488:1: rule__ElkEdgeSection__Group__1 : rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 ;
    public final void rule__ElkEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7492:1: ( rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 )
            // InternalGrana.g:7493:2: rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2
            {
            pushFollow(FOLLOW_50);
            rule__ElkEdgeSection__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group__2();

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
    // $ANTLR end "rule__ElkEdgeSection__Group__1"


    // $ANTLR start "rule__ElkEdgeSection__Group__1__Impl"
    // InternalGrana.g:7500:1: rule__ElkEdgeSection__Group__1__Impl : ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7504:1: ( ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:7505:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:7505:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            // InternalGrana.g:7506:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:7507:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            // InternalGrana.g:7507:2: rule__ElkEdgeSection__IdentifierAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__IdentifierAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__2"
    // InternalGrana.g:7517:1: rule__ElkEdgeSection__Group__2 : rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 ;
    public final void rule__ElkEdgeSection__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7521:1: ( rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 )
            // InternalGrana.g:7522:2: rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3
            {
            pushFollow(FOLLOW_50);
            rule__ElkEdgeSection__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group__3();

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
    // $ANTLR end "rule__ElkEdgeSection__Group__2"


    // $ANTLR start "rule__ElkEdgeSection__Group__2__Impl"
    // InternalGrana.g:7529:1: rule__ElkEdgeSection__Group__2__Impl : ( ( rule__ElkEdgeSection__Group_2__0 )? ) ;
    public final void rule__ElkEdgeSection__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7533:1: ( ( ( rule__ElkEdgeSection__Group_2__0 )? ) )
            // InternalGrana.g:7534:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            {
            // InternalGrana.g:7534:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            // InternalGrana.g:7535:1: ( rule__ElkEdgeSection__Group_2__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 
            // InternalGrana.g:7536:1: ( rule__ElkEdgeSection__Group_2__0 )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==51) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalGrana.g:7536:2: rule__ElkEdgeSection__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__3"
    // InternalGrana.g:7546:1: rule__ElkEdgeSection__Group__3 : rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 ;
    public final void rule__ElkEdgeSection__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7550:1: ( rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 )
            // InternalGrana.g:7551:2: rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4
            {
            pushFollow(FOLLOW_46);
            rule__ElkEdgeSection__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group__4();

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
    // $ANTLR end "rule__ElkEdgeSection__Group__3"


    // $ANTLR start "rule__ElkEdgeSection__Group__3__Impl"
    // InternalGrana.g:7558:1: rule__ElkEdgeSection__Group__3__Impl : ( '[' ) ;
    public final void rule__ElkEdgeSection__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7562:1: ( ( '[' ) )
            // InternalGrana.g:7563:1: ( '[' )
            {
            // InternalGrana.g:7563:1: ( '[' )
            // InternalGrana.g:7564:1: '['
            {
             before(grammarAccess.getElkEdgeSectionAccess().getLeftSquareBracketKeyword_3()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getLeftSquareBracketKeyword_3()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__4"
    // InternalGrana.g:7577:1: rule__ElkEdgeSection__Group__4 : rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 ;
    public final void rule__ElkEdgeSection__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7581:1: ( rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 )
            // InternalGrana.g:7582:2: rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5
            {
            pushFollow(FOLLOW_41);
            rule__ElkEdgeSection__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group__5();

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
    // $ANTLR end "rule__ElkEdgeSection__Group__4"


    // $ANTLR start "rule__ElkEdgeSection__Group__4__Impl"
    // InternalGrana.g:7589:1: rule__ElkEdgeSection__Group__4__Impl : ( ( rule__ElkEdgeSection__Group_4__0 ) ) ;
    public final void rule__ElkEdgeSection__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7593:1: ( ( ( rule__ElkEdgeSection__Group_4__0 ) ) )
            // InternalGrana.g:7594:1: ( ( rule__ElkEdgeSection__Group_4__0 ) )
            {
            // InternalGrana.g:7594:1: ( ( rule__ElkEdgeSection__Group_4__0 ) )
            // InternalGrana.g:7595:1: ( rule__ElkEdgeSection__Group_4__0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4()); 
            // InternalGrana.g:7596:1: ( rule__ElkEdgeSection__Group_4__0 )
            // InternalGrana.g:7596:2: rule__ElkEdgeSection__Group_4__0
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4__0();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_4()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group__5"
    // InternalGrana.g:7606:1: rule__ElkEdgeSection__Group__5 : rule__ElkEdgeSection__Group__5__Impl ;
    public final void rule__ElkEdgeSection__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7610:1: ( rule__ElkEdgeSection__Group__5__Impl )
            // InternalGrana.g:7611:2: rule__ElkEdgeSection__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group__5__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group__5"


    // $ANTLR start "rule__ElkEdgeSection__Group__5__Impl"
    // InternalGrana.g:7617:1: rule__ElkEdgeSection__Group__5__Impl : ( ']' ) ;
    public final void rule__ElkEdgeSection__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7621:1: ( ( ']' ) )
            // InternalGrana.g:7622:1: ( ']' )
            {
            // InternalGrana.g:7622:1: ( ']' )
            // InternalGrana.g:7623:1: ']'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getRightSquareBracketKeyword_5()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getRightSquareBracketKeyword_5()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group__5__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__0"
    // InternalGrana.g:7648:1: rule__ElkEdgeSection__Group_2__0 : rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 ;
    public final void rule__ElkEdgeSection__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7652:1: ( rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 )
            // InternalGrana.g:7653:2: rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdgeSection__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_2__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__0__Impl"
    // InternalGrana.g:7660:1: rule__ElkEdgeSection__Group_2__0__Impl : ( '->' ) ;
    public final void rule__ElkEdgeSection__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7664:1: ( ( '->' ) )
            // InternalGrana.g:7665:1: ( '->' )
            {
            // InternalGrana.g:7665:1: ( '->' )
            // InternalGrana.g:7666:1: '->'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getHyphenMinusGreaterThanSignKeyword_2_0()); 
            match(input,51,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getHyphenMinusGreaterThanSignKeyword_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__1"
    // InternalGrana.g:7679:1: rule__ElkEdgeSection__Group_2__1 : rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 ;
    public final void rule__ElkEdgeSection__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7683:1: ( rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 )
            // InternalGrana.g:7684:2: rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2
            {
            pushFollow(FOLLOW_25);
            rule__ElkEdgeSection__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_2__2();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__1__Impl"
    // InternalGrana.g:7691:1: rule__ElkEdgeSection__Group_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7695:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) )
            // InternalGrana.g:7696:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            {
            // InternalGrana.g:7696:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            // InternalGrana.g:7697:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 
            // InternalGrana.g:7698:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            // InternalGrana.g:7698:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__2"
    // InternalGrana.g:7708:1: rule__ElkEdgeSection__Group_2__2 : rule__ElkEdgeSection__Group_2__2__Impl ;
    public final void rule__ElkEdgeSection__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7712:1: ( rule__ElkEdgeSection__Group_2__2__Impl )
            // InternalGrana.g:7713:2: rule__ElkEdgeSection__Group_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_2__2__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_2__2__Impl"
    // InternalGrana.g:7719:1: rule__ElkEdgeSection__Group_2__2__Impl : ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7723:1: ( ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) )
            // InternalGrana.g:7724:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            {
            // InternalGrana.g:7724:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            // InternalGrana.g:7725:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 
            // InternalGrana.g:7726:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==32) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // InternalGrana.g:7726:2: rule__ElkEdgeSection__Group_2_2__0
            	    {
            	    pushFollow(FOLLOW_26);
            	    rule__ElkEdgeSection__Group_2_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop69;
                }
            } while (true);

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2_2__0"
    // InternalGrana.g:7742:1: rule__ElkEdgeSection__Group_2_2__0 : rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 ;
    public final void rule__ElkEdgeSection__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7746:1: ( rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 )
            // InternalGrana.g:7747:2: rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdgeSection__Group_2_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_2_2__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2_2__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_2_2__0__Impl"
    // InternalGrana.g:7754:1: rule__ElkEdgeSection__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7758:1: ( ( ',' ) )
            // InternalGrana.g:7759:1: ( ',' )
            {
            // InternalGrana.g:7759:1: ( ',' )
            // InternalGrana.g:7760:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_2_2_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_2_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2_2__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_2_2__1"
    // InternalGrana.g:7773:1: rule__ElkEdgeSection__Group_2_2__1 : rule__ElkEdgeSection__Group_2_2__1__Impl ;
    public final void rule__ElkEdgeSection__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7777:1: ( rule__ElkEdgeSection__Group_2_2__1__Impl )
            // InternalGrana.g:7778:2: rule__ElkEdgeSection__Group_2_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_2_2__1__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2_2__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_2_2__1__Impl"
    // InternalGrana.g:7784:1: rule__ElkEdgeSection__Group_2_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7788:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) )
            // InternalGrana.g:7789:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            {
            // InternalGrana.g:7789:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            // InternalGrana.g:7790:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 
            // InternalGrana.g:7791:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            // InternalGrana.g:7791:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_2_2__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__0"
    // InternalGrana.g:7805:1: rule__ElkEdgeSection__Group_4__0 : rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1 ;
    public final void rule__ElkEdgeSection__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7809:1: ( rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1 )
            // InternalGrana.g:7810:2: rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1
            {
            pushFollow(FOLLOW_47);
            rule__ElkEdgeSection__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__0__Impl"
    // InternalGrana.g:7817:1: rule__ElkEdgeSection__Group_4__0__Impl : ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) ) ;
    public final void rule__ElkEdgeSection__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7821:1: ( ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) ) )
            // InternalGrana.g:7822:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) )
            {
            // InternalGrana.g:7822:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) )
            // InternalGrana.g:7823:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0()); 
            // InternalGrana.g:7824:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0 )
            // InternalGrana.g:7824:2: rule__ElkEdgeSection__UnorderedGroup_4_0
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__UnorderedGroup_4_0();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__1"
    // InternalGrana.g:7834:1: rule__ElkEdgeSection__Group_4__1 : rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2 ;
    public final void rule__ElkEdgeSection__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7838:1: ( rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2 )
            // InternalGrana.g:7839:2: rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2
            {
            pushFollow(FOLLOW_47);
            rule__ElkEdgeSection__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4__2();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__1__Impl"
    // InternalGrana.g:7846:1: rule__ElkEdgeSection__Group_4__1__Impl : ( ( rule__ElkEdgeSection__Group_4_1__0 )? ) ;
    public final void rule__ElkEdgeSection__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7850:1: ( ( ( rule__ElkEdgeSection__Group_4_1__0 )? ) )
            // InternalGrana.g:7851:1: ( ( rule__ElkEdgeSection__Group_4_1__0 )? )
            {
            // InternalGrana.g:7851:1: ( ( rule__ElkEdgeSection__Group_4_1__0 )? )
            // InternalGrana.g:7852:1: ( rule__ElkEdgeSection__Group_4_1__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 
            // InternalGrana.g:7853:1: ( rule__ElkEdgeSection__Group_4_1__0 )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==56) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalGrana.g:7853:2: rule__ElkEdgeSection__Group_4_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__2"
    // InternalGrana.g:7863:1: rule__ElkEdgeSection__Group_4__2 : rule__ElkEdgeSection__Group_4__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7867:1: ( rule__ElkEdgeSection__Group_4__2__Impl )
            // InternalGrana.g:7868:2: rule__ElkEdgeSection__Group_4__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4__2__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4__2__Impl"
    // InternalGrana.g:7874:1: rule__ElkEdgeSection__Group_4__2__Impl : ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* ) ;
    public final void rule__ElkEdgeSection__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7878:1: ( ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* ) )
            // InternalGrana.g:7879:1: ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* )
            {
            // InternalGrana.g:7879:1: ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* )
            // InternalGrana.g:7880:1: ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getPropertiesAssignment_4_2()); 
            // InternalGrana.g:7881:1: ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==RULE_ID) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // InternalGrana.g:7881:2: rule__ElkEdgeSection__PropertiesAssignment_4_2
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__ElkEdgeSection__PropertiesAssignment_4_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop71;
                }
            } while (true);

             after(grammarAccess.getElkEdgeSectionAccess().getPropertiesAssignment_4_2()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__0"
    // InternalGrana.g:7897:1: rule__ElkEdgeSection__Group_4_0_0__0 : rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7901:1: ( rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1 )
            // InternalGrana.g:7902:2: rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkEdgeSection__Group_4_0_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_0__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__0__Impl"
    // InternalGrana.g:7909:1: rule__ElkEdgeSection__Group_4_0_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7913:1: ( ( 'incoming' ) )
            // InternalGrana.g:7914:1: ( 'incoming' )
            {
            // InternalGrana.g:7914:1: ( 'incoming' )
            // InternalGrana.g:7915:1: 'incoming'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0_0()); 
            match(input,52,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__1"
    // InternalGrana.g:7928:1: rule__ElkEdgeSection__Group_4_0_0__1 : rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7932:1: ( rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2 )
            // InternalGrana.g:7933:2: rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdgeSection__Group_4_0_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_0__2();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__1__Impl"
    // InternalGrana.g:7940:1: rule__ElkEdgeSection__Group_4_0_0__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7944:1: ( ( ':' ) )
            // InternalGrana.g:7945:1: ( ':' )
            {
            // InternalGrana.g:7945:1: ( ':' )
            // InternalGrana.g:7946:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_0_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_0_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__2"
    // InternalGrana.g:7959:1: rule__ElkEdgeSection__Group_4_0_0__2 : rule__ElkEdgeSection__Group_4_0_0__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7963:1: ( rule__ElkEdgeSection__Group_4_0_0__2__Impl )
            // InternalGrana.g:7964:2: rule__ElkEdgeSection__Group_4_0_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_0__2__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_0__2__Impl"
    // InternalGrana.g:7970:1: rule__ElkEdgeSection__Group_4_0_0__2__Impl : ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7974:1: ( ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) ) )
            // InternalGrana.g:7975:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) )
            {
            // InternalGrana.g:7975:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) )
            // InternalGrana.g:7976:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_0_2()); 
            // InternalGrana.g:7977:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 )
            // InternalGrana.g:7977:2: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_0_2()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_0__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__0"
    // InternalGrana.g:7993:1: rule__ElkEdgeSection__Group_4_0_1__0 : rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7997:1: ( rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1 )
            // InternalGrana.g:7998:2: rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkEdgeSection__Group_4_0_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_1__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__0__Impl"
    // InternalGrana.g:8005:1: rule__ElkEdgeSection__Group_4_0_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8009:1: ( ( 'outgoing' ) )
            // InternalGrana.g:8010:1: ( 'outgoing' )
            {
            // InternalGrana.g:8010:1: ( 'outgoing' )
            // InternalGrana.g:8011:1: 'outgoing'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_0_1_0()); 
            match(input,53,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_0_1_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__1"
    // InternalGrana.g:8024:1: rule__ElkEdgeSection__Group_4_0_1__1 : rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8028:1: ( rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2 )
            // InternalGrana.g:8029:2: rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2
            {
            pushFollow(FOLLOW_8);
            rule__ElkEdgeSection__Group_4_0_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_1__2();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__1__Impl"
    // InternalGrana.g:8036:1: rule__ElkEdgeSection__Group_4_0_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8040:1: ( ( ':' ) )
            // InternalGrana.g:8041:1: ( ':' )
            {
            // InternalGrana.g:8041:1: ( ':' )
            // InternalGrana.g:8042:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__2"
    // InternalGrana.g:8055:1: rule__ElkEdgeSection__Group_4_0_1__2 : rule__ElkEdgeSection__Group_4_0_1__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8059:1: ( rule__ElkEdgeSection__Group_4_0_1__2__Impl )
            // InternalGrana.g:8060:2: rule__ElkEdgeSection__Group_4_0_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_1__2__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_1__2__Impl"
    // InternalGrana.g:8066:1: rule__ElkEdgeSection__Group_4_0_1__2__Impl : ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8070:1: ( ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) ) )
            // InternalGrana.g:8071:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) )
            {
            // InternalGrana.g:8071:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) )
            // InternalGrana.g:8072:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_0_1_2()); 
            // InternalGrana.g:8073:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 )
            // InternalGrana.g:8073:2: rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_0_1_2()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_1__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__0"
    // InternalGrana.g:8089:1: rule__ElkEdgeSection__Group_4_0_2__0 : rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8093:1: ( rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1 )
            // InternalGrana.g:8094:2: rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkEdgeSection__Group_4_0_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__0__Impl"
    // InternalGrana.g:8101:1: rule__ElkEdgeSection__Group_4_0_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8105:1: ( ( 'start' ) )
            // InternalGrana.g:8106:1: ( 'start' )
            {
            // InternalGrana.g:8106:1: ( 'start' )
            // InternalGrana.g:8107:1: 'start'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_0_2_0()); 
            match(input,54,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_0_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__1"
    // InternalGrana.g:8120:1: rule__ElkEdgeSection__Group_4_0_2__1 : rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8124:1: ( rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2 )
            // InternalGrana.g:8125:2: rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2
            {
            pushFollow(FOLLOW_42);
            rule__ElkEdgeSection__Group_4_0_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__2();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__1__Impl"
    // InternalGrana.g:8132:1: rule__ElkEdgeSection__Group_4_0_2__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8136:1: ( ( ':' ) )
            // InternalGrana.g:8137:1: ( ':' )
            {
            // InternalGrana.g:8137:1: ( ':' )
            // InternalGrana.g:8138:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_2_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_2_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__2"
    // InternalGrana.g:8151:1: rule__ElkEdgeSection__Group_4_0_2__2 : rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8155:1: ( rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3 )
            // InternalGrana.g:8156:2: rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3
            {
            pushFollow(FOLLOW_25);
            rule__ElkEdgeSection__Group_4_0_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__3();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__2__Impl"
    // InternalGrana.g:8163:1: rule__ElkEdgeSection__Group_4_0_2__2__Impl : ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8167:1: ( ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) ) )
            // InternalGrana.g:8168:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) )
            {
            // InternalGrana.g:8168:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) )
            // InternalGrana.g:8169:1: ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_0_2_2()); 
            // InternalGrana.g:8170:1: ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 )
            // InternalGrana.g:8170:2: rule__ElkEdgeSection__StartXAssignment_4_0_2_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__StartXAssignment_4_0_2_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_0_2_2()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__3"
    // InternalGrana.g:8180:1: rule__ElkEdgeSection__Group_4_0_2__3 : rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8184:1: ( rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4 )
            // InternalGrana.g:8185:2: rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4
            {
            pushFollow(FOLLOW_42);
            rule__ElkEdgeSection__Group_4_0_2__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__4();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__3__Impl"
    // InternalGrana.g:8192:1: rule__ElkEdgeSection__Group_4_0_2__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8196:1: ( ( ',' ) )
            // InternalGrana.g:8197:1: ( ',' )
            {
            // InternalGrana.g:8197:1: ( ',' )
            // InternalGrana.g:8198:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_2_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_2_3()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__4"
    // InternalGrana.g:8211:1: rule__ElkEdgeSection__Group_4_0_2__4 : rule__ElkEdgeSection__Group_4_0_2__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8215:1: ( rule__ElkEdgeSection__Group_4_0_2__4__Impl )
            // InternalGrana.g:8216:2: rule__ElkEdgeSection__Group_4_0_2__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_2__4__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__4"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_2__4__Impl"
    // InternalGrana.g:8222:1: rule__ElkEdgeSection__Group_4_0_2__4__Impl : ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8226:1: ( ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) ) )
            // InternalGrana.g:8227:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) )
            {
            // InternalGrana.g:8227:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) )
            // InternalGrana.g:8228:1: ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_0_2_4()); 
            // InternalGrana.g:8229:1: ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 )
            // InternalGrana.g:8229:2: rule__ElkEdgeSection__StartYAssignment_4_0_2_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__StartYAssignment_4_0_2_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_0_2_4()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_2__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__0"
    // InternalGrana.g:8249:1: rule__ElkEdgeSection__Group_4_0_3__0 : rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8253:1: ( rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1 )
            // InternalGrana.g:8254:2: rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkEdgeSection__Group_4_0_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__0__Impl"
    // InternalGrana.g:8261:1: rule__ElkEdgeSection__Group_4_0_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8265:1: ( ( 'end' ) )
            // InternalGrana.g:8266:1: ( 'end' )
            {
            // InternalGrana.g:8266:1: ( 'end' )
            // InternalGrana.g:8267:1: 'end'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_0_3_0()); 
            match(input,55,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_0_3_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__1"
    // InternalGrana.g:8280:1: rule__ElkEdgeSection__Group_4_0_3__1 : rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8284:1: ( rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2 )
            // InternalGrana.g:8285:2: rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2
            {
            pushFollow(FOLLOW_42);
            rule__ElkEdgeSection__Group_4_0_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__2();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__1__Impl"
    // InternalGrana.g:8292:1: rule__ElkEdgeSection__Group_4_0_3__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8296:1: ( ( ':' ) )
            // InternalGrana.g:8297:1: ( ':' )
            {
            // InternalGrana.g:8297:1: ( ':' )
            // InternalGrana.g:8298:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_3_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_3_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__2"
    // InternalGrana.g:8311:1: rule__ElkEdgeSection__Group_4_0_3__2 : rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8315:1: ( rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3 )
            // InternalGrana.g:8316:2: rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3
            {
            pushFollow(FOLLOW_25);
            rule__ElkEdgeSection__Group_4_0_3__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__3();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__2__Impl"
    // InternalGrana.g:8323:1: rule__ElkEdgeSection__Group_4_0_3__2__Impl : ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8327:1: ( ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) ) )
            // InternalGrana.g:8328:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) )
            {
            // InternalGrana.g:8328:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) )
            // InternalGrana.g:8329:1: ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_0_3_2()); 
            // InternalGrana.g:8330:1: ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 )
            // InternalGrana.g:8330:2: rule__ElkEdgeSection__EndXAssignment_4_0_3_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__EndXAssignment_4_0_3_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_0_3_2()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__3"
    // InternalGrana.g:8340:1: rule__ElkEdgeSection__Group_4_0_3__3 : rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8344:1: ( rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4 )
            // InternalGrana.g:8345:2: rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4
            {
            pushFollow(FOLLOW_42);
            rule__ElkEdgeSection__Group_4_0_3__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__4();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__3__Impl"
    // InternalGrana.g:8352:1: rule__ElkEdgeSection__Group_4_0_3__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8356:1: ( ( ',' ) )
            // InternalGrana.g:8357:1: ( ',' )
            {
            // InternalGrana.g:8357:1: ( ',' )
            // InternalGrana.g:8358:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_3_3()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_3_3()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__4"
    // InternalGrana.g:8371:1: rule__ElkEdgeSection__Group_4_0_3__4 : rule__ElkEdgeSection__Group_4_0_3__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8375:1: ( rule__ElkEdgeSection__Group_4_0_3__4__Impl )
            // InternalGrana.g:8376:2: rule__ElkEdgeSection__Group_4_0_3__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_0_3__4__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__4"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_0_3__4__Impl"
    // InternalGrana.g:8382:1: rule__ElkEdgeSection__Group_4_0_3__4__Impl : ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8386:1: ( ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) ) )
            // InternalGrana.g:8387:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) )
            {
            // InternalGrana.g:8387:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) )
            // InternalGrana.g:8388:1: ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_0_3_4()); 
            // InternalGrana.g:8389:1: ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 )
            // InternalGrana.g:8389:2: rule__ElkEdgeSection__EndYAssignment_4_0_3_4
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__EndYAssignment_4_0_3_4();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_0_3_4()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_0_3__4__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__0"
    // InternalGrana.g:8409:1: rule__ElkEdgeSection__Group_4_1__0 : rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8413:1: ( rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 )
            // InternalGrana.g:8414:2: rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1
            {
            pushFollow(FOLLOW_36);
            rule__ElkEdgeSection__Group_4_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__0__Impl"
    // InternalGrana.g:8421:1: rule__ElkEdgeSection__Group_4_1__0__Impl : ( 'bends' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8425:1: ( ( 'bends' ) )
            // InternalGrana.g:8426:1: ( 'bends' )
            {
            // InternalGrana.g:8426:1: ( 'bends' )
            // InternalGrana.g:8427:1: 'bends'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_1_0()); 
            match(input,56,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_1_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__1"
    // InternalGrana.g:8440:1: rule__ElkEdgeSection__Group_4_1__1 : rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8444:1: ( rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 )
            // InternalGrana.g:8445:2: rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2
            {
            pushFollow(FOLLOW_42);
            rule__ElkEdgeSection__Group_4_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__2();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__1__Impl"
    // InternalGrana.g:8452:1: rule__ElkEdgeSection__Group_4_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8456:1: ( ( ':' ) )
            // InternalGrana.g:8457:1: ( ':' )
            {
            // InternalGrana.g:8457:1: ( ':' )
            // InternalGrana.g:8458:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_1_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__1__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__2"
    // InternalGrana.g:8471:1: rule__ElkEdgeSection__Group_4_1__2 : rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3 ;
    public final void rule__ElkEdgeSection__Group_4_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8475:1: ( rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3 )
            // InternalGrana.g:8476:2: rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3
            {
            pushFollow(FOLLOW_48);
            rule__ElkEdgeSection__Group_4_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__3();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__2"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__2__Impl"
    // InternalGrana.g:8483:1: rule__ElkEdgeSection__Group_4_1__2__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8487:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) ) )
            // InternalGrana.g:8488:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) )
            {
            // InternalGrana.g:8488:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) )
            // InternalGrana.g:8489:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_2()); 
            // InternalGrana.g:8490:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 )
            // InternalGrana.g:8490:2: rule__ElkEdgeSection__BendPointsAssignment_4_1_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__BendPointsAssignment_4_1_2();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_2()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__2__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__3"
    // InternalGrana.g:8500:1: rule__ElkEdgeSection__Group_4_1__3 : rule__ElkEdgeSection__Group_4_1__3__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8504:1: ( rule__ElkEdgeSection__Group_4_1__3__Impl )
            // InternalGrana.g:8505:2: rule__ElkEdgeSection__Group_4_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1__3__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__3"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1__3__Impl"
    // InternalGrana.g:8511:1: rule__ElkEdgeSection__Group_4_1__3__Impl : ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_4_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8515:1: ( ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* ) )
            // InternalGrana.g:8516:1: ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* )
            {
            // InternalGrana.g:8516:1: ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* )
            // InternalGrana.g:8517:1: ( rule__ElkEdgeSection__Group_4_1_3__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1_3()); 
            // InternalGrana.g:8518:1: ( rule__ElkEdgeSection__Group_4_1_3__0 )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==57) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // InternalGrana.g:8518:2: rule__ElkEdgeSection__Group_4_1_3__0
            	    {
            	    pushFollow(FOLLOW_49);
            	    rule__ElkEdgeSection__Group_4_1_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);

             after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1_3()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1__3__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1_3__0"
    // InternalGrana.g:8536:1: rule__ElkEdgeSection__Group_4_1_3__0 : rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8540:1: ( rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1 )
            // InternalGrana.g:8541:2: rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1
            {
            pushFollow(FOLLOW_42);
            rule__ElkEdgeSection__Group_4_1_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1_3__1();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1_3__0"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1_3__0__Impl"
    // InternalGrana.g:8548:1: rule__ElkEdgeSection__Group_4_1_3__0__Impl : ( '|' ) ;
    public final void rule__ElkEdgeSection__Group_4_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8552:1: ( ( '|' ) )
            // InternalGrana.g:8553:1: ( '|' )
            {
            // InternalGrana.g:8553:1: ( '|' )
            // InternalGrana.g:8554:1: '|'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_1_3_0()); 
            match(input,57,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_1_3_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1_3__0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1_3__1"
    // InternalGrana.g:8567:1: rule__ElkEdgeSection__Group_4_1_3__1 : rule__ElkEdgeSection__Group_4_1_3__1__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8571:1: ( rule__ElkEdgeSection__Group_4_1_3__1__Impl )
            // InternalGrana.g:8572:2: rule__ElkEdgeSection__Group_4_1_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__Group_4_1_3__1__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1_3__1"


    // $ANTLR start "rule__ElkEdgeSection__Group_4_1_3__1__Impl"
    // InternalGrana.g:8578:1: rule__ElkEdgeSection__Group_4_1_3__1__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8582:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) ) )
            // InternalGrana.g:8583:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) )
            {
            // InternalGrana.g:8583:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) )
            // InternalGrana.g:8584:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_3_1()); 
            // InternalGrana.g:8585:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 )
            // InternalGrana.g:8585:2: rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1();

            state._fsp--;


            }

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_3_1()); 

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
    // $ANTLR end "rule__ElkEdgeSection__Group_4_1_3__1__Impl"


    // $ANTLR start "rule__ElkBendPoint__Group__0"
    // InternalGrana.g:8599:1: rule__ElkBendPoint__Group__0 : rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 ;
    public final void rule__ElkBendPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8603:1: ( rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 )
            // InternalGrana.g:8604:2: rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1
            {
            pushFollow(FOLLOW_25);
            rule__ElkBendPoint__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkBendPoint__Group__1();

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
    // $ANTLR end "rule__ElkBendPoint__Group__0"


    // $ANTLR start "rule__ElkBendPoint__Group__0__Impl"
    // InternalGrana.g:8611:1: rule__ElkBendPoint__Group__0__Impl : ( ( rule__ElkBendPoint__XAssignment_0 ) ) ;
    public final void rule__ElkBendPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8615:1: ( ( ( rule__ElkBendPoint__XAssignment_0 ) ) )
            // InternalGrana.g:8616:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            {
            // InternalGrana.g:8616:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            // InternalGrana.g:8617:1: ( rule__ElkBendPoint__XAssignment_0 )
            {
             before(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 
            // InternalGrana.g:8618:1: ( rule__ElkBendPoint__XAssignment_0 )
            // InternalGrana.g:8618:2: rule__ElkBendPoint__XAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ElkBendPoint__XAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 

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
    // $ANTLR end "rule__ElkBendPoint__Group__0__Impl"


    // $ANTLR start "rule__ElkBendPoint__Group__1"
    // InternalGrana.g:8628:1: rule__ElkBendPoint__Group__1 : rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 ;
    public final void rule__ElkBendPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8632:1: ( rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 )
            // InternalGrana.g:8633:2: rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2
            {
            pushFollow(FOLLOW_42);
            rule__ElkBendPoint__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ElkBendPoint__Group__2();

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
    // $ANTLR end "rule__ElkBendPoint__Group__1"


    // $ANTLR start "rule__ElkBendPoint__Group__1__Impl"
    // InternalGrana.g:8640:1: rule__ElkBendPoint__Group__1__Impl : ( ',' ) ;
    public final void rule__ElkBendPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8644:1: ( ( ',' ) )
            // InternalGrana.g:8645:1: ( ',' )
            {
            // InternalGrana.g:8645:1: ( ',' )
            // InternalGrana.g:8646:1: ','
            {
             before(grammarAccess.getElkBendPointAccess().getCommaKeyword_1()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getElkBendPointAccess().getCommaKeyword_1()); 

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
    // $ANTLR end "rule__ElkBendPoint__Group__1__Impl"


    // $ANTLR start "rule__ElkBendPoint__Group__2"
    // InternalGrana.g:8659:1: rule__ElkBendPoint__Group__2 : rule__ElkBendPoint__Group__2__Impl ;
    public final void rule__ElkBendPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8663:1: ( rule__ElkBendPoint__Group__2__Impl )
            // InternalGrana.g:8664:2: rule__ElkBendPoint__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkBendPoint__Group__2__Impl();

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
    // $ANTLR end "rule__ElkBendPoint__Group__2"


    // $ANTLR start "rule__ElkBendPoint__Group__2__Impl"
    // InternalGrana.g:8670:1: rule__ElkBendPoint__Group__2__Impl : ( ( rule__ElkBendPoint__YAssignment_2 ) ) ;
    public final void rule__ElkBendPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8674:1: ( ( ( rule__ElkBendPoint__YAssignment_2 ) ) )
            // InternalGrana.g:8675:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            {
            // InternalGrana.g:8675:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            // InternalGrana.g:8676:1: ( rule__ElkBendPoint__YAssignment_2 )
            {
             before(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 
            // InternalGrana.g:8677:1: ( rule__ElkBendPoint__YAssignment_2 )
            // InternalGrana.g:8677:2: rule__ElkBendPoint__YAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ElkBendPoint__YAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 

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
    // $ANTLR end "rule__ElkBendPoint__Group__2__Impl"


    // $ANTLR start "rule__QualifiedId__Group__0"
    // InternalGrana.g:8693:1: rule__QualifiedId__Group__0 : rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 ;
    public final void rule__QualifiedId__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8697:1: ( rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 )
            // InternalGrana.g:8698:2: rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1
            {
            pushFollow(FOLLOW_51);
            rule__QualifiedId__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedId__Group__1();

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
    // $ANTLR end "rule__QualifiedId__Group__0"


    // $ANTLR start "rule__QualifiedId__Group__0__Impl"
    // InternalGrana.g:8705:1: rule__QualifiedId__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8709:1: ( ( RULE_ID ) )
            // InternalGrana.g:8710:1: ( RULE_ID )
            {
            // InternalGrana.g:8710:1: ( RULE_ID )
            // InternalGrana.g:8711:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__QualifiedId__Group__0__Impl"


    // $ANTLR start "rule__QualifiedId__Group__1"
    // InternalGrana.g:8722:1: rule__QualifiedId__Group__1 : rule__QualifiedId__Group__1__Impl ;
    public final void rule__QualifiedId__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8726:1: ( rule__QualifiedId__Group__1__Impl )
            // InternalGrana.g:8727:2: rule__QualifiedId__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedId__Group__1__Impl();

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
    // $ANTLR end "rule__QualifiedId__Group__1"


    // $ANTLR start "rule__QualifiedId__Group__1__Impl"
    // InternalGrana.g:8733:1: rule__QualifiedId__Group__1__Impl : ( ( rule__QualifiedId__Group_1__0 )* ) ;
    public final void rule__QualifiedId__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8737:1: ( ( ( rule__QualifiedId__Group_1__0 )* ) )
            // InternalGrana.g:8738:1: ( ( rule__QualifiedId__Group_1__0 )* )
            {
            // InternalGrana.g:8738:1: ( ( rule__QualifiedId__Group_1__0 )* )
            // InternalGrana.g:8739:1: ( rule__QualifiedId__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup_1()); 
            // InternalGrana.g:8740:1: ( rule__QualifiedId__Group_1__0 )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==59) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // InternalGrana.g:8740:2: rule__QualifiedId__Group_1__0
            	    {
            	    pushFollow(FOLLOW_52);
            	    rule__QualifiedId__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);

             after(grammarAccess.getQualifiedIdAccess().getGroup_1()); 

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
    // $ANTLR end "rule__QualifiedId__Group__1__Impl"


    // $ANTLR start "rule__QualifiedId__Group_1__0"
    // InternalGrana.g:8754:1: rule__QualifiedId__Group_1__0 : rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 ;
    public final void rule__QualifiedId__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8758:1: ( rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 )
            // InternalGrana.g:8759:2: rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1
            {
            pushFollow(FOLLOW_8);
            rule__QualifiedId__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__QualifiedId__Group_1__1();

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
    // $ANTLR end "rule__QualifiedId__Group_1__0"


    // $ANTLR start "rule__QualifiedId__Group_1__0__Impl"
    // InternalGrana.g:8766:1: rule__QualifiedId__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedId__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8770:1: ( ( '.' ) )
            // InternalGrana.g:8771:1: ( '.' )
            {
            // InternalGrana.g:8771:1: ( '.' )
            // InternalGrana.g:8772:1: '.'
            {
             before(grammarAccess.getQualifiedIdAccess().getFullStopKeyword_1_0()); 
            match(input,59,FOLLOW_2); 
             after(grammarAccess.getQualifiedIdAccess().getFullStopKeyword_1_0()); 

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
    // $ANTLR end "rule__QualifiedId__Group_1__0__Impl"


    // $ANTLR start "rule__QualifiedId__Group_1__1"
    // InternalGrana.g:8785:1: rule__QualifiedId__Group_1__1 : rule__QualifiedId__Group_1__1__Impl ;
    public final void rule__QualifiedId__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8789:1: ( rule__QualifiedId__Group_1__1__Impl )
            // InternalGrana.g:8790:2: rule__QualifiedId__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__QualifiedId__Group_1__1__Impl();

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
    // $ANTLR end "rule__QualifiedId__Group_1__1"


    // $ANTLR start "rule__QualifiedId__Group_1__1__Impl"
    // InternalGrana.g:8796:1: rule__QualifiedId__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8800:1: ( ( RULE_ID ) )
            // InternalGrana.g:8801:1: ( RULE_ID )
            {
            // InternalGrana.g:8801:1: ( RULE_ID )
            // InternalGrana.g:8802:1: RULE_ID
            {
             before(grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getQualifiedIdAccess().getIDTerminalRuleCall_1_1()); 

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
    // $ANTLR end "rule__QualifiedId__Group_1__1__Impl"


    // $ANTLR start "rule__Property__Group__0"
    // InternalGrana.g:8817:1: rule__Property__Group__0 : rule__Property__Group__0__Impl rule__Property__Group__1 ;
    public final void rule__Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8821:1: ( rule__Property__Group__0__Impl rule__Property__Group__1 )
            // InternalGrana.g:8822:2: rule__Property__Group__0__Impl rule__Property__Group__1
            {
            pushFollow(FOLLOW_36);
            rule__Property__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Property__Group__1();

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
    // $ANTLR end "rule__Property__Group__0"


    // $ANTLR start "rule__Property__Group__0__Impl"
    // InternalGrana.g:8829:1: rule__Property__Group__0__Impl : ( ( rule__Property__KeyAssignment_0 ) ) ;
    public final void rule__Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8833:1: ( ( ( rule__Property__KeyAssignment_0 ) ) )
            // InternalGrana.g:8834:1: ( ( rule__Property__KeyAssignment_0 ) )
            {
            // InternalGrana.g:8834:1: ( ( rule__Property__KeyAssignment_0 ) )
            // InternalGrana.g:8835:1: ( rule__Property__KeyAssignment_0 )
            {
             before(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 
            // InternalGrana.g:8836:1: ( rule__Property__KeyAssignment_0 )
            // InternalGrana.g:8836:2: rule__Property__KeyAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Property__KeyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 

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
    // $ANTLR end "rule__Property__Group__0__Impl"


    // $ANTLR start "rule__Property__Group__1"
    // InternalGrana.g:8846:1: rule__Property__Group__1 : rule__Property__Group__1__Impl rule__Property__Group__2 ;
    public final void rule__Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8850:1: ( rule__Property__Group__1__Impl rule__Property__Group__2 )
            // InternalGrana.g:8851:2: rule__Property__Group__1__Impl rule__Property__Group__2
            {
            pushFollow(FOLLOW_53);
            rule__Property__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Property__Group__2();

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
    // $ANTLR end "rule__Property__Group__1"


    // $ANTLR start "rule__Property__Group__1__Impl"
    // InternalGrana.g:8858:1: rule__Property__Group__1__Impl : ( ':' ) ;
    public final void rule__Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8862:1: ( ( ':' ) )
            // InternalGrana.g:8863:1: ( ':' )
            {
            // InternalGrana.g:8863:1: ( ':' )
            // InternalGrana.g:8864:1: ':'
            {
             before(grammarAccess.getPropertyAccess().getColonKeyword_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getColonKeyword_1()); 

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
    // $ANTLR end "rule__Property__Group__1__Impl"


    // $ANTLR start "rule__Property__Group__2"
    // InternalGrana.g:8877:1: rule__Property__Group__2 : rule__Property__Group__2__Impl ;
    public final void rule__Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8881:1: ( rule__Property__Group__2__Impl )
            // InternalGrana.g:8882:2: rule__Property__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Property__Group__2__Impl();

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
    // $ANTLR end "rule__Property__Group__2"


    // $ANTLR start "rule__Property__Group__2__Impl"
    // InternalGrana.g:8888:1: rule__Property__Group__2__Impl : ( ( rule__Property__Alternatives_2 ) ) ;
    public final void rule__Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8892:1: ( ( ( rule__Property__Alternatives_2 ) ) )
            // InternalGrana.g:8893:1: ( ( rule__Property__Alternatives_2 ) )
            {
            // InternalGrana.g:8893:1: ( ( rule__Property__Alternatives_2 ) )
            // InternalGrana.g:8894:1: ( rule__Property__Alternatives_2 )
            {
             before(grammarAccess.getPropertyAccess().getAlternatives_2()); 
            // InternalGrana.g:8895:1: ( rule__Property__Alternatives_2 )
            // InternalGrana.g:8895:2: rule__Property__Alternatives_2
            {
            pushFollow(FOLLOW_2);
            rule__Property__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getAlternatives_2()); 

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
    // $ANTLR end "rule__Property__Group__2__Impl"


    // $ANTLR start "rule__PropertyKey__Group__0"
    // InternalGrana.g:8911:1: rule__PropertyKey__Group__0 : rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 ;
    public final void rule__PropertyKey__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8915:1: ( rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 )
            // InternalGrana.g:8916:2: rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1
            {
            pushFollow(FOLLOW_51);
            rule__PropertyKey__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PropertyKey__Group__1();

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
    // $ANTLR end "rule__PropertyKey__Group__0"


    // $ANTLR start "rule__PropertyKey__Group__0__Impl"
    // InternalGrana.g:8923:1: rule__PropertyKey__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8927:1: ( ( RULE_ID ) )
            // InternalGrana.g:8928:1: ( RULE_ID )
            {
            // InternalGrana.g:8928:1: ( RULE_ID )
            // InternalGrana.g:8929:1: RULE_ID
            {
             before(grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__PropertyKey__Group__0__Impl"


    // $ANTLR start "rule__PropertyKey__Group__1"
    // InternalGrana.g:8940:1: rule__PropertyKey__Group__1 : rule__PropertyKey__Group__1__Impl ;
    public final void rule__PropertyKey__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8944:1: ( rule__PropertyKey__Group__1__Impl )
            // InternalGrana.g:8945:2: rule__PropertyKey__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PropertyKey__Group__1__Impl();

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
    // $ANTLR end "rule__PropertyKey__Group__1"


    // $ANTLR start "rule__PropertyKey__Group__1__Impl"
    // InternalGrana.g:8951:1: rule__PropertyKey__Group__1__Impl : ( ( rule__PropertyKey__Group_1__0 )* ) ;
    public final void rule__PropertyKey__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8955:1: ( ( ( rule__PropertyKey__Group_1__0 )* ) )
            // InternalGrana.g:8956:1: ( ( rule__PropertyKey__Group_1__0 )* )
            {
            // InternalGrana.g:8956:1: ( ( rule__PropertyKey__Group_1__0 )* )
            // InternalGrana.g:8957:1: ( rule__PropertyKey__Group_1__0 )*
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup_1()); 
            // InternalGrana.g:8958:1: ( rule__PropertyKey__Group_1__0 )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==59) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // InternalGrana.g:8958:2: rule__PropertyKey__Group_1__0
            	    {
            	    pushFollow(FOLLOW_52);
            	    rule__PropertyKey__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);

             after(grammarAccess.getPropertyKeyAccess().getGroup_1()); 

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
    // $ANTLR end "rule__PropertyKey__Group__1__Impl"


    // $ANTLR start "rule__PropertyKey__Group_1__0"
    // InternalGrana.g:8972:1: rule__PropertyKey__Group_1__0 : rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 ;
    public final void rule__PropertyKey__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8976:1: ( rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 )
            // InternalGrana.g:8977:2: rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1
            {
            pushFollow(FOLLOW_8);
            rule__PropertyKey__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PropertyKey__Group_1__1();

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
    // $ANTLR end "rule__PropertyKey__Group_1__0"


    // $ANTLR start "rule__PropertyKey__Group_1__0__Impl"
    // InternalGrana.g:8984:1: rule__PropertyKey__Group_1__0__Impl : ( '.' ) ;
    public final void rule__PropertyKey__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8988:1: ( ( '.' ) )
            // InternalGrana.g:8989:1: ( '.' )
            {
            // InternalGrana.g:8989:1: ( '.' )
            // InternalGrana.g:8990:1: '.'
            {
             before(grammarAccess.getPropertyKeyAccess().getFullStopKeyword_1_0()); 
            match(input,59,FOLLOW_2); 
             after(grammarAccess.getPropertyKeyAccess().getFullStopKeyword_1_0()); 

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
    // $ANTLR end "rule__PropertyKey__Group_1__0__Impl"


    // $ANTLR start "rule__PropertyKey__Group_1__1"
    // InternalGrana.g:9003:1: rule__PropertyKey__Group_1__1 : rule__PropertyKey__Group_1__1__Impl ;
    public final void rule__PropertyKey__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9007:1: ( rule__PropertyKey__Group_1__1__Impl )
            // InternalGrana.g:9008:2: rule__PropertyKey__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PropertyKey__Group_1__1__Impl();

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
    // $ANTLR end "rule__PropertyKey__Group_1__1"


    // $ANTLR start "rule__PropertyKey__Group_1__1__Impl"
    // InternalGrana.g:9014:1: rule__PropertyKey__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9018:1: ( ( RULE_ID ) )
            // InternalGrana.g:9019:1: ( RULE_ID )
            {
            // InternalGrana.g:9019:1: ( RULE_ID )
            // InternalGrana.g:9020:1: RULE_ID
            {
             before(grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_1_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getPropertyKeyAccess().getIDTerminalRuleCall_1_1()); 

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
    // $ANTLR end "rule__PropertyKey__Group_1__1__Impl"


    // $ANTLR start "rule__ShapeLayout__UnorderedGroup_2"
    // InternalGrana.g:9036:1: rule__ShapeLayout__UnorderedGroup_2 : ( rule__ShapeLayout__UnorderedGroup_2__0 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            
        try {
            // InternalGrana.g:9041:1: ( ( rule__ShapeLayout__UnorderedGroup_2__0 )? )
            // InternalGrana.g:9042:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            {
            // InternalGrana.g:9042:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( LA75_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt75=1;
            }
            else if ( LA75_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalGrana.g:9042:2: rule__ShapeLayout__UnorderedGroup_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ShapeLayout__UnorderedGroup_2__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__UnorderedGroup_2"


    // $ANTLR start "rule__ShapeLayout__UnorderedGroup_2__Impl"
    // InternalGrana.g:9052:1: rule__ShapeLayout__UnorderedGroup_2__Impl : ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) ;
    public final void rule__ShapeLayout__UnorderedGroup_2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:9057:1: ( ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) )
            // InternalGrana.g:9058:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            {
            // InternalGrana.g:9058:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( LA76_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt76=1;
            }
            else if ( LA76_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt76=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }
            switch (alt76) {
                case 1 :
                    // InternalGrana.g:9060:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    {
                    // InternalGrana.g:9060:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    // InternalGrana.g:9061:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
                    }
                    // InternalGrana.g:9061:108: ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    // InternalGrana.g:9062:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9068:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    // InternalGrana.g:9070:7: ( rule__ShapeLayout__Group_2_0__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 
                    // InternalGrana.g:9071:7: ( rule__ShapeLayout__Group_2_0__0 )
                    // InternalGrana.g:9071:8: rule__ShapeLayout__Group_2_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ShapeLayout__Group_2_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:9077:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    {
                    // InternalGrana.g:9077:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    // InternalGrana.g:9078:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
                    }
                    // InternalGrana.g:9078:108: ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    // InternalGrana.g:9079:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9085:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    // InternalGrana.g:9087:7: ( rule__ShapeLayout__Group_2_1__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 
                    // InternalGrana.g:9088:7: ( rule__ShapeLayout__Group_2_1__0 )
                    // InternalGrana.g:9088:8: rule__ShapeLayout__Group_2_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ShapeLayout__Group_2_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ShapeLayout__UnorderedGroup_2__Impl"


    // $ANTLR start "rule__ShapeLayout__UnorderedGroup_2__0"
    // InternalGrana.g:9103:1: rule__ShapeLayout__UnorderedGroup_2__0 : rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9107:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? )
            // InternalGrana.g:9108:2: rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            {
            pushFollow(FOLLOW_54);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

            state._fsp--;

            // InternalGrana.g:9109:2: ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( LA77_0 == 48 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt77=1;
            }
            else if ( LA77_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // InternalGrana.g:9109:2: rule__ShapeLayout__UnorderedGroup_2__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ShapeLayout__UnorderedGroup_2__1();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ShapeLayout__UnorderedGroup_2__0"


    // $ANTLR start "rule__ShapeLayout__UnorderedGroup_2__1"
    // InternalGrana.g:9116:1: rule__ShapeLayout__UnorderedGroup_2__1 : rule__ShapeLayout__UnorderedGroup_2__Impl ;
    public final void rule__ShapeLayout__UnorderedGroup_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9120:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl )
            // InternalGrana.g:9121:2: rule__ShapeLayout__UnorderedGroup_2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

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
    // $ANTLR end "rule__ShapeLayout__UnorderedGroup_2__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0"
    // InternalGrana.g:9132:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0 : ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            
        try {
            // InternalGrana.g:9137:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )? )
            // InternalGrana.g:9138:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )?
            {
            // InternalGrana.g:9138:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( LA78_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt78=1;
            }
            else if ( LA78_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt78=1;
            }
            else if ( LA78_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt78=1;
            }
            else if ( LA78_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalGrana.g:9138:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl"
    // InternalGrana.g:9148:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl : ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) ) ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:9153:1: ( ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) ) )
            // InternalGrana.g:9154:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) )
            {
            // InternalGrana.g:9154:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) )
            int alt79=4;
            int LA79_0 = input.LA(1);

            if ( LA79_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt79=1;
            }
            else if ( LA79_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt79=2;
            }
            else if ( LA79_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt79=3;
            }
            else if ( LA79_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt79=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // InternalGrana.g:9156:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) )
                    {
                    // InternalGrana.g:9156:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) )
                    // InternalGrana.g:9157:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0)");
                    }
                    // InternalGrana.g:9157:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) )
                    // InternalGrana.g:9158:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9164:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) )
                    // InternalGrana.g:9166:7: ( rule__ElkSingleEdgeSection__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_0()); 
                    // InternalGrana.g:9167:7: ( rule__ElkSingleEdgeSection__Group_1_0_0__0 )
                    // InternalGrana.g:9167:8: rule__ElkSingleEdgeSection__Group_1_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:9173:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) )
                    {
                    // InternalGrana.g:9173:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) )
                    // InternalGrana.g:9174:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1)");
                    }
                    // InternalGrana.g:9174:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) )
                    // InternalGrana.g:9175:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9181:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) )
                    // InternalGrana.g:9183:7: ( rule__ElkSingleEdgeSection__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_1()); 
                    // InternalGrana.g:9184:7: ( rule__ElkSingleEdgeSection__Group_1_0_1__0 )
                    // InternalGrana.g:9184:8: rule__ElkSingleEdgeSection__Group_1_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:9190:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) )
                    {
                    // InternalGrana.g:9190:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) )
                    // InternalGrana.g:9191:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2)");
                    }
                    // InternalGrana.g:9191:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) )
                    // InternalGrana.g:9192:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9198:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) )
                    // InternalGrana.g:9200:7: ( rule__ElkSingleEdgeSection__Group_1_0_2__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_2()); 
                    // InternalGrana.g:9201:7: ( rule__ElkSingleEdgeSection__Group_1_0_2__0 )
                    // InternalGrana.g:9201:8: rule__ElkSingleEdgeSection__Group_1_0_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:9207:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) )
                    {
                    // InternalGrana.g:9207:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) )
                    // InternalGrana.g:9208:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3)");
                    }
                    // InternalGrana.g:9208:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) )
                    // InternalGrana.g:9209:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9215:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) )
                    // InternalGrana.g:9217:7: ( rule__ElkSingleEdgeSection__Group_1_0_3__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_3()); 
                    // InternalGrana.g:9218:7: ( rule__ElkSingleEdgeSection__Group_1_0_3__0 )
                    // InternalGrana.g:9218:8: rule__ElkSingleEdgeSection__Group_1_0_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__Group_1_0_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_3()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0"
    // InternalGrana.g:9233:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9237:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )? )
            // InternalGrana.g:9238:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:9239:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( LA80_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt80=1;
            }
            else if ( LA80_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt80=1;
            }
            else if ( LA80_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt80=1;
            }
            else if ( LA80_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // InternalGrana.g:9239:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1"
    // InternalGrana.g:9246:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9250:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )? )
            // InternalGrana.g:9251:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:9252:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( LA81_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt81=1;
            }
            else if ( LA81_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt81=1;
            }
            else if ( LA81_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt81=1;
            }
            else if ( LA81_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // InternalGrana.g:9252:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2"
    // InternalGrana.g:9259:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9263:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )? )
            // InternalGrana.g:9264:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:9265:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( LA82_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt82=1;
            }
            else if ( LA82_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt82=1;
            }
            else if ( LA82_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt82=1;
            }
            else if ( LA82_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // InternalGrana.g:9265:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2"


    // $ANTLR start "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3"
    // InternalGrana.g:9272:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9276:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl )
            // InternalGrana.g:9277:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

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
    // $ANTLR end "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0"
    // InternalGrana.g:9292:1: rule__ElkEdgeSection__UnorderedGroup_4_0 : ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            
        try {
            // InternalGrana.g:9297:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )? )
            // InternalGrana.g:9298:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )?
            {
            // InternalGrana.g:9298:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( LA83_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt83=1;
            }
            else if ( LA83_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt83=1;
            }
            else if ( LA83_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt83=1;
            }
            else if ( LA83_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // InternalGrana.g:9298:2: rule__ElkEdgeSection__UnorderedGroup_4_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4_0__0();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	getUnorderedGroupHelper().leave(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl"
    // InternalGrana.g:9308:1: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl : ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) ) ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:9313:1: ( ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) ) )
            // InternalGrana.g:9314:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) )
            {
            // InternalGrana.g:9314:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) )
            int alt84=4;
            int LA84_0 = input.LA(1);

            if ( LA84_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt84=1;
            }
            else if ( LA84_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt84=2;
            }
            else if ( LA84_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt84=3;
            }
            else if ( LA84_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt84=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // InternalGrana.g:9316:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) )
                    {
                    // InternalGrana.g:9316:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) )
                    // InternalGrana.g:9317:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0)");
                    }
                    // InternalGrana.g:9317:113: ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) )
                    // InternalGrana.g:9318:6: ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9324:6: ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) )
                    // InternalGrana.g:9326:7: ( rule__ElkEdgeSection__Group_4_0_0__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_0()); 
                    // InternalGrana.g:9327:7: ( rule__ElkEdgeSection__Group_4_0_0__0 )
                    // InternalGrana.g:9327:8: rule__ElkEdgeSection__Group_4_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_0()); 

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:9333:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) )
                    {
                    // InternalGrana.g:9333:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) )
                    // InternalGrana.g:9334:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1)");
                    }
                    // InternalGrana.g:9334:113: ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) )
                    // InternalGrana.g:9335:6: ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9341:6: ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) )
                    // InternalGrana.g:9343:7: ( rule__ElkEdgeSection__Group_4_0_1__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_1()); 
                    // InternalGrana.g:9344:7: ( rule__ElkEdgeSection__Group_4_0_1__0 )
                    // InternalGrana.g:9344:8: rule__ElkEdgeSection__Group_4_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_1()); 

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalGrana.g:9350:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) )
                    {
                    // InternalGrana.g:9350:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) )
                    // InternalGrana.g:9351:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2)");
                    }
                    // InternalGrana.g:9351:113: ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) )
                    // InternalGrana.g:9352:6: ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9358:6: ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) )
                    // InternalGrana.g:9360:7: ( rule__ElkEdgeSection__Group_4_0_2__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_2()); 
                    // InternalGrana.g:9361:7: ( rule__ElkEdgeSection__Group_4_0_2__0 )
                    // InternalGrana.g:9361:8: rule__ElkEdgeSection__Group_4_0_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_2()); 

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalGrana.g:9367:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) )
                    {
                    // InternalGrana.g:9367:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) )
                    // InternalGrana.g:9368:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3)");
                    }
                    // InternalGrana.g:9368:113: ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) )
                    // InternalGrana.g:9369:6: ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9375:6: ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) )
                    // InternalGrana.g:9377:7: ( rule__ElkEdgeSection__Group_4_0_3__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_3()); 
                    // InternalGrana.g:9378:7: ( rule__ElkEdgeSection__Group_4_0_3__0 )
                    // InternalGrana.g:9378:8: rule__ElkEdgeSection__Group_4_0_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__Group_4_0_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_3()); 

                    }


                    }


                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	if (selected)
            		getUnorderedGroupHelper().returnFromSelection(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__0"
    // InternalGrana.g:9393:1: rule__ElkEdgeSection__UnorderedGroup_4_0__0 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9397:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )? )
            // InternalGrana.g:9398:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:9399:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( LA85_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt85=1;
            }
            else if ( LA85_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt85=1;
            }
            else if ( LA85_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt85=1;
            }
            else if ( LA85_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalGrana.g:9399:2: rule__ElkEdgeSection__UnorderedGroup_4_0__1
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4_0__1();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__0"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__1"
    // InternalGrana.g:9406:1: rule__ElkEdgeSection__UnorderedGroup_4_0__1 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9410:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )? )
            // InternalGrana.g:9411:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:9412:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( LA86_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt86=1;
            }
            else if ( LA86_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt86=1;
            }
            else if ( LA86_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt86=1;
            }
            else if ( LA86_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalGrana.g:9412:2: rule__ElkEdgeSection__UnorderedGroup_4_0__2
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4_0__2();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__1"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__2"
    // InternalGrana.g:9419:1: rule__ElkEdgeSection__UnorderedGroup_4_0__2 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9423:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )? )
            // InternalGrana.g:9424:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:9425:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( LA87_0 == 52 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt87=1;
            }
            else if ( LA87_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt87=1;
            }
            else if ( LA87_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt87=1;
            }
            else if ( LA87_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalGrana.g:9425:2: rule__ElkEdgeSection__UnorderedGroup_4_0__3
                    {
                    pushFollow(FOLLOW_2);
                    rule__ElkEdgeSection__UnorderedGroup_4_0__3();

                    state._fsp--;


                    }
                    break;

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__2"


    // $ANTLR start "rule__ElkEdgeSection__UnorderedGroup_4_0__3"
    // InternalGrana.g:9432:1: rule__ElkEdgeSection__UnorderedGroup_4_0__3 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9436:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl )
            // InternalGrana.g:9437:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

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
    // $ANTLR end "rule__ElkEdgeSection__UnorderedGroup_4_0__3"


    // $ANTLR start "rule__Grana__GlobalResourcesAssignment_0_1"
    // InternalGrana.g:9452:1: rule__Grana__GlobalResourcesAssignment_0_1 : ( ruleGlobalResourceRef ) ;
    public final void rule__Grana__GlobalResourcesAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9456:1: ( ( ruleGlobalResourceRef ) )
            // InternalGrana.g:9457:1: ( ruleGlobalResourceRef )
            {
            // InternalGrana.g:9457:1: ( ruleGlobalResourceRef )
            // InternalGrana.g:9458:1: ruleGlobalResourceRef
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesGlobalResourceRefParserRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:9467:1: rule__Grana__GloobalOutputsAssignment_1_1 : ( ruleGlobalOutputRef ) ;
    public final void rule__Grana__GloobalOutputsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9471:1: ( ( ruleGlobalOutputRef ) )
            // InternalGrana.g:9472:1: ( ruleGlobalOutputRef )
            {
            // InternalGrana.g:9472:1: ( ruleGlobalOutputRef )
            // InternalGrana.g:9473:1: ruleGlobalOutputRef
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsGlobalOutputRefParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "rule__Grana__ParallelAssignment_2_1"
    // InternalGrana.g:9482:1: rule__Grana__ParallelAssignment_2_1 : ( ( 'parallel' ) ) ;
    public final void rule__Grana__ParallelAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9486:1: ( ( ( 'parallel' ) ) )
            // InternalGrana.g:9487:1: ( ( 'parallel' ) )
            {
            // InternalGrana.g:9487:1: ( ( 'parallel' ) )
            // InternalGrana.g:9488:1: ( 'parallel' )
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            // InternalGrana.g:9489:1: ( 'parallel' )
            // InternalGrana.g:9490:1: 'parallel'
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            match(input,60,FOLLOW_2); 
             after(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 

            }

             after(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 

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
    // $ANTLR end "rule__Grana__ParallelAssignment_2_1"


    // $ANTLR start "rule__Grana__ExecuteAllAssignment_2_2_0"
    // InternalGrana.g:9505:1: rule__Grana__ExecuteAllAssignment_2_2_0 : ( ( 'all' ) ) ;
    public final void rule__Grana__ExecuteAllAssignment_2_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9509:1: ( ( ( 'all' ) ) )
            // InternalGrana.g:9510:1: ( ( 'all' ) )
            {
            // InternalGrana.g:9510:1: ( ( 'all' ) )
            // InternalGrana.g:9511:1: ( 'all' )
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            // InternalGrana.g:9512:1: ( 'all' )
            // InternalGrana.g:9513:1: 'all'
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            match(input,61,FOLLOW_2); 
             after(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 

            }

             after(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 

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
    // $ANTLR end "rule__Grana__ExecuteAllAssignment_2_2_0"


    // $ANTLR start "rule__Grana__ExecuteAssignment_2_2_1"
    // InternalGrana.g:9528:1: rule__Grana__ExecuteAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__Grana__ExecuteAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9532:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:9533:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:9533:1: ( ( RULE_ID ) )
            // InternalGrana.g:9534:1: ( RULE_ID )
            {
             before(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_2_1_0()); 
            // InternalGrana.g:9535:1: ( RULE_ID )
            // InternalGrana.g:9536:1: RULE_ID
            {
             before(grammarAccess.getGranaAccess().getExecuteJobIDTerminalRuleCall_2_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getGranaAccess().getExecuteJobIDTerminalRuleCall_2_2_1_0_1()); 

            }

             after(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_2_1_0()); 

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
    // $ANTLR end "rule__Grana__ExecuteAssignment_2_2_1"


    // $ANTLR start "rule__Grana__JobsAssignment_3"
    // InternalGrana.g:9547:1: rule__Grana__JobsAssignment_3 : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9551:1: ( ( ruleJob ) )
            // InternalGrana.g:9552:1: ( ruleJob )
            {
            // InternalGrana.g:9552:1: ( ruleJob )
            // InternalGrana.g:9553:1: ruleJob
            {
             before(grammarAccess.getGranaAccess().getJobsJobParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:9562:1: rule__RegularJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RegularJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9566:1: ( ( RULE_ID ) )
            // InternalGrana.g:9567:1: ( RULE_ID )
            {
            // InternalGrana.g:9567:1: ( RULE_ID )
            // InternalGrana.g:9568:1: RULE_ID
            {
             before(grammarAccess.getRegularJobAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
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
    // InternalGrana.g:9577:1: rule__RegularJob__LayoutBeforeAnalysisAssignment_2 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__RegularJob__LayoutBeforeAnalysisAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9581:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // InternalGrana.g:9582:1: ( ( 'layoutBeforeAnalysis' ) )
            {
            // InternalGrana.g:9582:1: ( ( 'layoutBeforeAnalysis' ) )
            // InternalGrana.g:9583:1: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            // InternalGrana.g:9584:1: ( 'layoutBeforeAnalysis' )
            // InternalGrana.g:9585:1: 'layoutBeforeAnalysis'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            match(input,62,FOLLOW_2); 
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
    // InternalGrana.g:9600:1: rule__RegularJob__MeasureExecutionTimeAssignment_3 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RegularJob__MeasureExecutionTimeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9604:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:9605:1: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:9605:1: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:9606:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            // InternalGrana.g:9607:1: ( 'measureExecutionTime' )
            // InternalGrana.g:9608:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            match(input,63,FOLLOW_2); 
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
    // InternalGrana.g:9623:1: rule__RegularJob__ResourcesAssignment_5 : ( ruleResource ) ;
    public final void rule__RegularJob__ResourcesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9627:1: ( ( ruleResource ) )
            // InternalGrana.g:9628:1: ( ruleResource )
            {
            // InternalGrana.g:9628:1: ( ruleResource )
            // InternalGrana.g:9629:1: ruleResource
            {
             before(grammarAccess.getRegularJobAccess().getResourcesResourceParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:9638:1: rule__RegularJob__LayoutOptionsAssignment_7 : ( ruleLayoutConfig ) ;
    public final void rule__RegularJob__LayoutOptionsAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9642:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9643:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9643:1: ( ruleLayoutConfig )
            // InternalGrana.g:9644:1: ruleLayoutConfig
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
            ruleLayoutConfig();

            state._fsp--;

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_7_0()); 

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
    // InternalGrana.g:9653:1: rule__RegularJob__AnalysesAssignment_9 : ( ruleAnalysis ) ;
    public final void rule__RegularJob__AnalysesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9657:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9658:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9658:1: ( ruleAnalysis )
            // InternalGrana.g:9659:1: ruleAnalysis
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAnalysisParserRuleCall_9_0()); 
            pushFollow(FOLLOW_2);
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


    // $ANTLR start "rule__RegularJob__OutputTypeAssignment_11"
    // InternalGrana.g:9668:1: rule__RegularJob__OutputTypeAssignment_11 : ( ruleOutputType ) ;
    public final void rule__RegularJob__OutputTypeAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9672:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9673:1: ( ruleOutputType )
            {
            // InternalGrana.g:9673:1: ( ruleOutputType )
            // InternalGrana.g:9674:1: ruleOutputType
            {
             before(grammarAccess.getRegularJobAccess().getOutputTypeOutputTypeEnumRuleCall_11_0()); 
            pushFollow(FOLLOW_2);
            ruleOutputType();

            state._fsp--;

             after(grammarAccess.getRegularJobAccess().getOutputTypeOutputTypeEnumRuleCall_11_0()); 

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
    // $ANTLR end "rule__RegularJob__OutputTypeAssignment_11"


    // $ANTLR start "rule__RegularJob__OutputAssignment_12"
    // InternalGrana.g:9683:1: rule__RegularJob__OutputAssignment_12 : ( ruleOutput ) ;
    public final void rule__RegularJob__OutputAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9687:1: ( ( ruleOutput ) )
            // InternalGrana.g:9688:1: ( ruleOutput )
            {
            // InternalGrana.g:9688:1: ( ruleOutput )
            // InternalGrana.g:9689:1: ruleOutput
            {
             before(grammarAccess.getRegularJobAccess().getOutputOutputParserRuleCall_12_0()); 
            pushFollow(FOLLOW_2);
            ruleOutput();

            state._fsp--;

             after(grammarAccess.getRegularJobAccess().getOutputOutputParserRuleCall_12_0()); 

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
    // $ANTLR end "rule__RegularJob__OutputAssignment_12"


    // $ANTLR start "rule__CompareJob__NameAssignment_1"
    // InternalGrana.g:9698:1: rule__CompareJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__CompareJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9702:1: ( ( RULE_ID ) )
            // InternalGrana.g:9703:1: ( RULE_ID )
            {
            // InternalGrana.g:9703:1: ( RULE_ID )
            // InternalGrana.g:9704:1: RULE_ID
            {
             before(grammarAccess.getCompareJobAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getCompareJobAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__CompareJob__NameAssignment_1"


    // $ANTLR start "rule__CompareJob__ResourcesAssignment_3"
    // InternalGrana.g:9713:1: rule__CompareJob__ResourcesAssignment_3 : ( ruleResource ) ;
    public final void rule__CompareJob__ResourcesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9717:1: ( ( ruleResource ) )
            // InternalGrana.g:9718:1: ( ruleResource )
            {
            // InternalGrana.g:9718:1: ( ruleResource )
            // InternalGrana.g:9719:1: ruleResource
            {
             before(grammarAccess.getCompareJobAccess().getResourcesResourceParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getCompareJobAccess().getResourcesResourceParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__CompareJob__ResourcesAssignment_3"


    // $ANTLR start "rule__CompareJob__LayoutOptionsAssignment_5"
    // InternalGrana.g:9728:1: rule__CompareJob__LayoutOptionsAssignment_5 : ( ruleLayoutConfig ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9732:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9733:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9733:1: ( ruleLayoutConfig )
            // InternalGrana.g:9734:1: ruleLayoutConfig
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleLayoutConfig();

            state._fsp--;

             after(grammarAccess.getCompareJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__CompareJob__LayoutOptionsAssignment_5"


    // $ANTLR start "rule__CompareJob__LayoutOptionsAssignment_6"
    // InternalGrana.g:9743:1: rule__CompareJob__LayoutOptionsAssignment_6 : ( ruleLayoutConfig ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9747:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9748:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9748:1: ( ruleLayoutConfig )
            // InternalGrana.g:9749:1: ruleLayoutConfig
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleLayoutConfig();

            state._fsp--;

             after(grammarAccess.getCompareJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__CompareJob__LayoutOptionsAssignment_6"


    // $ANTLR start "rule__CompareJob__AnalysesAssignment_8"
    // InternalGrana.g:9758:1: rule__CompareJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__CompareJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9762:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9763:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9763:1: ( ruleAnalysis )
            // InternalGrana.g:9764:1: ruleAnalysis
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAnalysisParserRuleCall_8_0()); 
            pushFollow(FOLLOW_2);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getCompareJobAccess().getAnalysesAnalysisParserRuleCall_8_0()); 

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
    // $ANTLR end "rule__CompareJob__AnalysesAssignment_8"


    // $ANTLR start "rule__CompareJob__OutputTypeAssignment_10"
    // InternalGrana.g:9773:1: rule__CompareJob__OutputTypeAssignment_10 : ( ruleOutputType ) ;
    public final void rule__CompareJob__OutputTypeAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9777:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9778:1: ( ruleOutputType )
            {
            // InternalGrana.g:9778:1: ( ruleOutputType )
            // InternalGrana.g:9779:1: ruleOutputType
            {
             before(grammarAccess.getCompareJobAccess().getOutputTypeOutputTypeEnumRuleCall_10_0()); 
            pushFollow(FOLLOW_2);
            ruleOutputType();

            state._fsp--;

             after(grammarAccess.getCompareJobAccess().getOutputTypeOutputTypeEnumRuleCall_10_0()); 

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
    // $ANTLR end "rule__CompareJob__OutputTypeAssignment_10"


    // $ANTLR start "rule__CompareJob__OutputAssignment_11"
    // InternalGrana.g:9788:1: rule__CompareJob__OutputAssignment_11 : ( ruleOutput ) ;
    public final void rule__CompareJob__OutputAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9792:1: ( ( ruleOutput ) )
            // InternalGrana.g:9793:1: ( ruleOutput )
            {
            // InternalGrana.g:9793:1: ( ruleOutput )
            // InternalGrana.g:9794:1: ruleOutput
            {
             before(grammarAccess.getCompareJobAccess().getOutputOutputParserRuleCall_11_0()); 
            pushFollow(FOLLOW_2);
            ruleOutput();

            state._fsp--;

             after(grammarAccess.getCompareJobAccess().getOutputOutputParserRuleCall_11_0()); 

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
    // $ANTLR end "rule__CompareJob__OutputAssignment_11"


    // $ANTLR start "rule__RangeJob__NameAssignment_1"
    // InternalGrana.g:9803:1: rule__RangeJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RangeJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9807:1: ( ( RULE_ID ) )
            // InternalGrana.g:9808:1: ( RULE_ID )
            {
            // InternalGrana.g:9808:1: ( RULE_ID )
            // InternalGrana.g:9809:1: RULE_ID
            {
             before(grammarAccess.getRangeJobAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
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


    // $ANTLR start "rule__RangeJob__MeasureExecutionTimeAssignment_2"
    // InternalGrana.g:9818:1: rule__RangeJob__MeasureExecutionTimeAssignment_2 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RangeJob__MeasureExecutionTimeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9822:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:9823:1: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:9823:1: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:9824:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            // InternalGrana.g:9825:1: ( 'measureExecutionTime' )
            // InternalGrana.g:9826:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            match(input,63,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 

            }

             after(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 

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
    // $ANTLR end "rule__RangeJob__MeasureExecutionTimeAssignment_2"


    // $ANTLR start "rule__RangeJob__ResourcesAssignment_4"
    // InternalGrana.g:9841:1: rule__RangeJob__ResourcesAssignment_4 : ( ruleResource ) ;
    public final void rule__RangeJob__ResourcesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9845:1: ( ( ruleResource ) )
            // InternalGrana.g:9846:1: ( ruleResource )
            {
            // InternalGrana.g:9846:1: ( ruleResource )
            // InternalGrana.g:9847:1: ruleResource
            {
             before(grammarAccess.getRangeJobAccess().getResourcesResourceParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getResourcesResourceParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__RangeJob__ResourcesAssignment_4"


    // $ANTLR start "rule__RangeJob__LayoutOptionsAssignment_6"
    // InternalGrana.g:9856:1: rule__RangeJob__LayoutOptionsAssignment_6 : ( ruleLayoutConfig ) ;
    public final void rule__RangeJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9860:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9861:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9861:1: ( ruleLayoutConfig )
            // InternalGrana.g:9862:1: ruleLayoutConfig
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleLayoutConfig();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsLayoutConfigParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__RangeJob__LayoutOptionsAssignment_6"


    // $ANTLR start "rule__RangeJob__AnalysesAssignment_8"
    // InternalGrana.g:9871:1: rule__RangeJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9875:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9876:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9876:1: ( ruleAnalysis )
            // InternalGrana.g:9877:1: ruleAnalysis
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAnalysisParserRuleCall_8_0()); 
            pushFollow(FOLLOW_2);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getAnalysesAnalysisParserRuleCall_8_0()); 

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
    // $ANTLR end "rule__RangeJob__AnalysesAssignment_8"


    // $ANTLR start "rule__RangeJob__RangeOptionAssignment_10"
    // InternalGrana.g:9886:1: rule__RangeJob__RangeOptionAssignment_10 : ( ruleQualifiedId ) ;
    public final void rule__RangeJob__RangeOptionAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9890:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9891:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:9891:1: ( ruleQualifiedId )
            // InternalGrana.g:9892:1: ruleQualifiedId
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIdParserRuleCall_10_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getRangeOptionQualifiedIdParserRuleCall_10_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeOptionAssignment_10"


    // $ANTLR start "rule__RangeJob__RangeValuesAssignment_11"
    // InternalGrana.g:9901:1: rule__RangeJob__RangeValuesAssignment_11 : ( ruleRange ) ;
    public final void rule__RangeJob__RangeValuesAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9905:1: ( ( ruleRange ) )
            // InternalGrana.g:9906:1: ( ruleRange )
            {
            // InternalGrana.g:9906:1: ( ruleRange )
            // InternalGrana.g:9907:1: ruleRange
            {
             before(grammarAccess.getRangeJobAccess().getRangeValuesRangeParserRuleCall_11_0()); 
            pushFollow(FOLLOW_2);
            ruleRange();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getRangeValuesRangeParserRuleCall_11_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeValuesAssignment_11"


    // $ANTLR start "rule__RangeJob__RangeAnalysisAssignment_12_0_1"
    // InternalGrana.g:9916:1: rule__RangeJob__RangeAnalysisAssignment_12_0_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysisAssignment_12_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9920:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9921:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9921:1: ( ruleAnalysis )
            // InternalGrana.g:9922:1: ruleAnalysis
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisAnalysisParserRuleCall_12_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getRangeAnalysisAnalysisParserRuleCall_12_0_1_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeAnalysisAssignment_12_0_1"


    // $ANTLR start "rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1"
    // InternalGrana.g:9931:1: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9935:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:9936:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:9936:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:9937:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentSIGNED_INTTerminalRuleCall_12_0_2_1_0()); 
            match(input,RULE_SIGNED_INT,FOLLOW_2); 
             after(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentSIGNED_INTTerminalRuleCall_12_0_2_1_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1"


    // $ANTLR start "rule__RangeJob__RangeAnalysesAssignment_12_1_1"
    // InternalGrana.g:9946:1: rule__RangeJob__RangeAnalysesAssignment_12_1_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysesAssignment_12_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9950:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9951:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9951:1: ( ruleAnalysis )
            // InternalGrana.g:9952:1: ruleAnalysis
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAnalysisParserRuleCall_12_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleAnalysis();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getRangeAnalysesAnalysisParserRuleCall_12_1_1_0()); 

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
    // $ANTLR end "rule__RangeJob__RangeAnalysesAssignment_12_1_1"


    // $ANTLR start "rule__RangeJob__OutputTypeAssignment_14"
    // InternalGrana.g:9961:1: rule__RangeJob__OutputTypeAssignment_14 : ( ruleOutputType ) ;
    public final void rule__RangeJob__OutputTypeAssignment_14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9965:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9966:1: ( ruleOutputType )
            {
            // InternalGrana.g:9966:1: ( ruleOutputType )
            // InternalGrana.g:9967:1: ruleOutputType
            {
             before(grammarAccess.getRangeJobAccess().getOutputTypeOutputTypeEnumRuleCall_14_0()); 
            pushFollow(FOLLOW_2);
            ruleOutputType();

            state._fsp--;

             after(grammarAccess.getRangeJobAccess().getOutputTypeOutputTypeEnumRuleCall_14_0()); 

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
    // $ANTLR end "rule__RangeJob__OutputTypeAssignment_14"


    // $ANTLR start "rule__RangeJob__OutputAssignment_15"
    // InternalGrana.g:9976:1: rule__RangeJob__OutputAssignment_15 : ( ruleOutput ) ;
    public final void rule__RangeJob__OutputAssignment_15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9980:1: ( ( ruleOutput ) )
            // InternalGrana.g:9981:1: ( ruleOutput )
            {
            // InternalGrana.g:9981:1: ( ruleOutput )
            // InternalGrana.g:9982:1: ruleOutput
            {
             before(grammarAccess.getRangeJobAccess().getOutputOutputParserRuleCall_15_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:9991:1: rule__FloatRange__ValuesAssignment_1 : ( RULE_FLOAT ) ;
    public final void rule__FloatRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9995:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:9996:1: ( RULE_FLOAT )
            {
            // InternalGrana.g:9996:1: ( RULE_FLOAT )
            // InternalGrana.g:9997:1: RULE_FLOAT
            {
             before(grammarAccess.getFloatRangeAccess().getValuesFLOATTerminalRuleCall_1_0()); 
            match(input,RULE_FLOAT,FOLLOW_2); 
             after(grammarAccess.getFloatRangeAccess().getValuesFLOATTerminalRuleCall_1_0()); 

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
    // InternalGrana.g:10006:1: rule__FloatRange__ValuesAssignment_2_1 : ( RULE_FLOAT ) ;
    public final void rule__FloatRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10010:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:10011:1: ( RULE_FLOAT )
            {
            // InternalGrana.g:10011:1: ( RULE_FLOAT )
            // InternalGrana.g:10012:1: RULE_FLOAT
            {
             before(grammarAccess.getFloatRangeAccess().getValuesFLOATTerminalRuleCall_2_1_0()); 
            match(input,RULE_FLOAT,FOLLOW_2); 
             after(grammarAccess.getFloatRangeAccess().getValuesFLOATTerminalRuleCall_2_1_0()); 

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
    // InternalGrana.g:10021:1: rule__IntRangeValues__ValuesAssignment_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeValues__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10025:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10026:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10026:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10027:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesSIGNED_INTTerminalRuleCall_1_0()); 
            match(input,RULE_SIGNED_INT,FOLLOW_2); 
             after(grammarAccess.getIntRangeValuesAccess().getValuesSIGNED_INTTerminalRuleCall_1_0()); 

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
    // InternalGrana.g:10036:1: rule__IntRangeValues__ValuesAssignment_2_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeValues__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10040:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10041:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10041:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10042:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesSIGNED_INTTerminalRuleCall_2_1_0()); 
            match(input,RULE_SIGNED_INT,FOLLOW_2); 
             after(grammarAccess.getIntRangeValuesAccess().getValuesSIGNED_INTTerminalRuleCall_2_1_0()); 

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
    // InternalGrana.g:10051:1: rule__IntRangeRange__StartAssignment_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeRange__StartAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10055:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10056:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10056:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10057:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartSIGNED_INTTerminalRuleCall_1_0()); 
            match(input,RULE_SIGNED_INT,FOLLOW_2); 
             after(grammarAccess.getIntRangeRangeAccess().getStartSIGNED_INTTerminalRuleCall_1_0()); 

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
    // InternalGrana.g:10066:1: rule__IntRangeRange__EndAssignment_3 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeRange__EndAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10070:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10071:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10071:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10072:1: RULE_SIGNED_INT
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndSIGNED_INTTerminalRuleCall_3_0()); 
            match(input,RULE_SIGNED_INT,FOLLOW_2); 
             after(grammarAccess.getIntRangeRangeAccess().getEndSIGNED_INTTerminalRuleCall_3_0()); 

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


    // $ANTLR start "rule__EnumRange__ValuesAssignment_1"
    // InternalGrana.g:10081:1: rule__EnumRange__ValuesAssignment_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__EnumRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10085:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:10086:1: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:10086:1: ( ruleQualifiedIdValue )
            // InternalGrana.g:10087:1: ruleQualifiedIdValue
            {
             before(grammarAccess.getEnumRangeAccess().getValuesQualifiedIdValueParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedIdValue();

            state._fsp--;

             after(grammarAccess.getEnumRangeAccess().getValuesQualifiedIdValueParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__EnumRange__ValuesAssignment_1"


    // $ANTLR start "rule__EnumRange__ValuesAssignment_2_1"
    // InternalGrana.g:10096:1: rule__EnumRange__ValuesAssignment_2_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__EnumRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10100:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:10101:1: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:10101:1: ( ruleQualifiedIdValue )
            // InternalGrana.g:10102:1: ruleQualifiedIdValue
            {
             before(grammarAccess.getEnumRangeAccess().getValuesQualifiedIdValueParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedIdValue();

            state._fsp--;

             after(grammarAccess.getEnumRangeAccess().getValuesQualifiedIdValueParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__EnumRange__ValuesAssignment_2_1"


    // $ANTLR start "rule__ResourceReference__ResourceRefsAssignment_1"
    // InternalGrana.g:10111:1: rule__ResourceReference__ResourceRefsAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceReference__ResourceRefsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10115:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10116:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10116:1: ( ( RULE_ID ) )
            // InternalGrana.g:10117:1: ( RULE_ID )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            // InternalGrana.g:10118:1: ( RULE_ID )
            // InternalGrana.g:10119:1: RULE_ID
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
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
    // InternalGrana.g:10130:1: rule__GlobalResourceRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalResourceRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10134:1: ( ( RULE_ID ) )
            // InternalGrana.g:10135:1: ( RULE_ID )
            {
            // InternalGrana.g:10135:1: ( RULE_ID )
            // InternalGrana.g:10136:1: RULE_ID
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
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
    // InternalGrana.g:10145:1: rule__GlobalResourceRef__ResourcesAssignment_1 : ( ruleLocalResource ) ;
    public final void rule__GlobalResourceRef__ResourcesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10149:1: ( ( ruleLocalResource ) )
            // InternalGrana.g:10150:1: ( ruleLocalResource )
            {
            // InternalGrana.g:10150:1: ( ruleLocalResource )
            // InternalGrana.g:10151:1: ruleLocalResource
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesLocalResourceParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:10160:1: rule__LocalResource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__LocalResource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10164:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10165:1: ( RULE_STRING )
            {
            // InternalGrana.g:10165:1: ( RULE_STRING )
            // InternalGrana.g:10166:1: RULE_STRING
            {
             before(grammarAccess.getLocalResourceAccess().getPathSTRINGTerminalRuleCall_0_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
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
    // InternalGrana.g:10175:1: rule__LocalResource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__LocalResource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10179:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10180:1: ( RULE_STRING )
            {
            // InternalGrana.g:10180:1: ( RULE_STRING )
            // InternalGrana.g:10181:1: RULE_STRING
            {
             before(grammarAccess.getLocalResourceAccess().getFilterSTRINGTerminalRuleCall_1_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
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


    // $ANTLR start "rule__LocalResource__RecurseAssignment_2"
    // InternalGrana.g:10190:1: rule__LocalResource__RecurseAssignment_2 : ( ( 'recurse' ) ) ;
    public final void rule__LocalResource__RecurseAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10194:1: ( ( ( 'recurse' ) ) )
            // InternalGrana.g:10195:1: ( ( 'recurse' ) )
            {
            // InternalGrana.g:10195:1: ( ( 'recurse' ) )
            // InternalGrana.g:10196:1: ( 'recurse' )
            {
             before(grammarAccess.getLocalResourceAccess().getRecurseRecurseKeyword_2_0()); 
            // InternalGrana.g:10197:1: ( 'recurse' )
            // InternalGrana.g:10198:1: 'recurse'
            {
             before(grammarAccess.getLocalResourceAccess().getRecurseRecurseKeyword_2_0()); 
            match(input,64,FOLLOW_2); 
             after(grammarAccess.getLocalResourceAccess().getRecurseRecurseKeyword_2_0()); 

            }

             after(grammarAccess.getLocalResourceAccess().getRecurseRecurseKeyword_2_0()); 

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
    // $ANTLR end "rule__LocalResource__RecurseAssignment_2"


    // $ANTLR start "rule__GlobalOutputRef__NameAssignment_0"
    // InternalGrana.g:10213:1: rule__GlobalOutputRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalOutputRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10217:1: ( ( RULE_ID ) )
            // InternalGrana.g:10218:1: ( RULE_ID )
            {
            // InternalGrana.g:10218:1: ( RULE_ID )
            // InternalGrana.g:10219:1: RULE_ID
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
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
    // InternalGrana.g:10228:1: rule__GlobalOutputRef__OutputAssignment_1 : ( ruleLocalOutput ) ;
    public final void rule__GlobalOutputRef__OutputAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10232:1: ( ( ruleLocalOutput ) )
            // InternalGrana.g:10233:1: ( ruleLocalOutput )
            {
            // InternalGrana.g:10233:1: ( ruleLocalOutput )
            // InternalGrana.g:10234:1: ruleLocalOutput
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputLocalOutputParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
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
    // InternalGrana.g:10243:1: rule__OutputReference__OutputRefAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__OutputReference__OutputRefAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10247:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10248:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10248:1: ( ( RULE_ID ) )
            // InternalGrana.g:10249:1: ( RULE_ID )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 
            // InternalGrana.g:10250:1: ( RULE_ID )
            // InternalGrana.g:10251:1: RULE_ID
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
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
    // InternalGrana.g:10262:1: rule__LocalOutput__PathAssignment : ( RULE_STRING ) ;
    public final void rule__LocalOutput__PathAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10266:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10267:1: ( RULE_STRING )
            {
            // InternalGrana.g:10267:1: ( RULE_STRING )
            // InternalGrana.g:10268:1: RULE_STRING
            {
             before(grammarAccess.getLocalOutputAccess().getPathSTRINGTerminalRuleCall_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
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
    // InternalGrana.g:10277:1: rule__Analysis__NameAssignment : ( ruleQualifiedId ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10281:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10282:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:10282:1: ( ruleQualifiedId )
            // InternalGrana.g:10283:1: ruleQualifiedId
            {
             before(grammarAccess.getAnalysisAccess().getNameQualifiedIdParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getAnalysisAccess().getNameQualifiedIdParserRuleCall_0()); 

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


    // $ANTLR start "rule__LayoutConfig__IdentifierAssignment_0"
    // InternalGrana.g:10292:1: rule__LayoutConfig__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__LayoutConfig__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10296:1: ( ( RULE_ID ) )
            // InternalGrana.g:10297:1: ( RULE_ID )
            {
            // InternalGrana.g:10297:1: ( RULE_ID )
            // InternalGrana.g:10298:1: RULE_ID
            {
             before(grammarAccess.getLayoutConfigAccess().getIdentifierIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getLayoutConfigAccess().getIdentifierIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__LayoutConfig__IdentifierAssignment_0"


    // $ANTLR start "rule__LayoutConfig__PropertiesAssignment_2"
    // InternalGrana.g:10307:1: rule__LayoutConfig__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__LayoutConfig__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10311:1: ( ( ruleProperty ) )
            // InternalGrana.g:10312:1: ( ruleProperty )
            {
            // InternalGrana.g:10312:1: ( ruleProperty )
            // InternalGrana.g:10313:1: ruleProperty
            {
             before(grammarAccess.getLayoutConfigAccess().getPropertiesPropertyParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getLayoutConfigAccess().getPropertiesPropertyParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__LayoutConfig__PropertiesAssignment_2"


    // $ANTLR start "rule__ElkNode__IdentifierAssignment_1"
    // InternalGrana.g:10328:1: rule__ElkNode__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkNode__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10332:1: ( ( RULE_ID ) )
            // InternalGrana.g:10333:1: ( RULE_ID )
            {
            // InternalGrana.g:10333:1: ( RULE_ID )
            // InternalGrana.g:10334:1: RULE_ID
            {
             before(grammarAccess.getElkNodeAccess().getIdentifierIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElkNodeAccess().getIdentifierIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__ElkNode__IdentifierAssignment_1"


    // $ANTLR start "rule__ElkNode__PropertiesAssignment_2_2"
    // InternalGrana.g:10343:1: rule__ElkNode__PropertiesAssignment_2_2 : ( ruleProperty ) ;
    public final void rule__ElkNode__PropertiesAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10347:1: ( ( ruleProperty ) )
            // InternalGrana.g:10348:1: ( ruleProperty )
            {
            // InternalGrana.g:10348:1: ( ruleProperty )
            // InternalGrana.g:10349:1: ruleProperty
            {
             before(grammarAccess.getElkNodeAccess().getPropertiesPropertyParserRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getPropertiesPropertyParserRuleCall_2_2_0()); 

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
    // $ANTLR end "rule__ElkNode__PropertiesAssignment_2_2"


    // $ANTLR start "rule__ElkNode__LabelsAssignment_2_3_0"
    // InternalGrana.g:10358:1: rule__ElkNode__LabelsAssignment_2_3_0 : ( ruleElkLabel ) ;
    public final void rule__ElkNode__LabelsAssignment_2_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10362:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10363:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10363:1: ( ruleElkLabel )
            // InternalGrana.g:10364:1: ruleElkLabel
            {
             before(grammarAccess.getElkNodeAccess().getLabelsElkLabelParserRuleCall_2_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleElkLabel();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getLabelsElkLabelParserRuleCall_2_3_0_0()); 

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
    // $ANTLR end "rule__ElkNode__LabelsAssignment_2_3_0"


    // $ANTLR start "rule__ElkNode__PortsAssignment_2_3_1"
    // InternalGrana.g:10373:1: rule__ElkNode__PortsAssignment_2_3_1 : ( ruleElkPort ) ;
    public final void rule__ElkNode__PortsAssignment_2_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10377:1: ( ( ruleElkPort ) )
            // InternalGrana.g:10378:1: ( ruleElkPort )
            {
            // InternalGrana.g:10378:1: ( ruleElkPort )
            // InternalGrana.g:10379:1: ruleElkPort
            {
             before(grammarAccess.getElkNodeAccess().getPortsElkPortParserRuleCall_2_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleElkPort();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getPortsElkPortParserRuleCall_2_3_1_0()); 

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
    // $ANTLR end "rule__ElkNode__PortsAssignment_2_3_1"


    // $ANTLR start "rule__ElkNode__ChildrenAssignment_2_3_2"
    // InternalGrana.g:10388:1: rule__ElkNode__ChildrenAssignment_2_3_2 : ( ruleElkNode ) ;
    public final void rule__ElkNode__ChildrenAssignment_2_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10392:1: ( ( ruleElkNode ) )
            // InternalGrana.g:10393:1: ( ruleElkNode )
            {
            // InternalGrana.g:10393:1: ( ruleElkNode )
            // InternalGrana.g:10394:1: ruleElkNode
            {
             before(grammarAccess.getElkNodeAccess().getChildrenElkNodeParserRuleCall_2_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleElkNode();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getChildrenElkNodeParserRuleCall_2_3_2_0()); 

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
    // $ANTLR end "rule__ElkNode__ChildrenAssignment_2_3_2"


    // $ANTLR start "rule__ElkNode__ContainedEdgesAssignment_2_3_3"
    // InternalGrana.g:10403:1: rule__ElkNode__ContainedEdgesAssignment_2_3_3 : ( ruleElkEdge ) ;
    public final void rule__ElkNode__ContainedEdgesAssignment_2_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10407:1: ( ( ruleElkEdge ) )
            // InternalGrana.g:10408:1: ( ruleElkEdge )
            {
            // InternalGrana.g:10408:1: ( ruleElkEdge )
            // InternalGrana.g:10409:1: ruleElkEdge
            {
             before(grammarAccess.getElkNodeAccess().getContainedEdgesElkEdgeParserRuleCall_2_3_3_0()); 
            pushFollow(FOLLOW_2);
            ruleElkEdge();

            state._fsp--;

             after(grammarAccess.getElkNodeAccess().getContainedEdgesElkEdgeParserRuleCall_2_3_3_0()); 

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
    // $ANTLR end "rule__ElkNode__ContainedEdgesAssignment_2_3_3"


    // $ANTLR start "rule__ElkLabel__IdentifierAssignment_1_0"
    // InternalGrana.g:10418:1: rule__ElkLabel__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkLabel__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10422:1: ( ( RULE_ID ) )
            // InternalGrana.g:10423:1: ( RULE_ID )
            {
            // InternalGrana.g:10423:1: ( RULE_ID )
            // InternalGrana.g:10424:1: RULE_ID
            {
             before(grammarAccess.getElkLabelAccess().getIdentifierIDTerminalRuleCall_1_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getIdentifierIDTerminalRuleCall_1_0_0()); 

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
    // $ANTLR end "rule__ElkLabel__IdentifierAssignment_1_0"


    // $ANTLR start "rule__ElkLabel__TextAssignment_2"
    // InternalGrana.g:10433:1: rule__ElkLabel__TextAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ElkLabel__TextAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10437:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10438:1: ( RULE_STRING )
            {
            // InternalGrana.g:10438:1: ( RULE_STRING )
            // InternalGrana.g:10439:1: RULE_STRING
            {
             before(grammarAccess.getElkLabelAccess().getTextSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getElkLabelAccess().getTextSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__ElkLabel__TextAssignment_2"


    // $ANTLR start "rule__ElkLabel__PropertiesAssignment_3_2"
    // InternalGrana.g:10448:1: rule__ElkLabel__PropertiesAssignment_3_2 : ( ruleProperty ) ;
    public final void rule__ElkLabel__PropertiesAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10452:1: ( ( ruleProperty ) )
            // InternalGrana.g:10453:1: ( ruleProperty )
            {
            // InternalGrana.g:10453:1: ( ruleProperty )
            // InternalGrana.g:10454:1: ruleProperty
            {
             before(grammarAccess.getElkLabelAccess().getPropertiesPropertyParserRuleCall_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkLabelAccess().getPropertiesPropertyParserRuleCall_3_2_0()); 

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
    // $ANTLR end "rule__ElkLabel__PropertiesAssignment_3_2"


    // $ANTLR start "rule__ElkLabel__LabelsAssignment_3_3"
    // InternalGrana.g:10463:1: rule__ElkLabel__LabelsAssignment_3_3 : ( ruleElkLabel ) ;
    public final void rule__ElkLabel__LabelsAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10467:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10468:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10468:1: ( ruleElkLabel )
            // InternalGrana.g:10469:1: ruleElkLabel
            {
             before(grammarAccess.getElkLabelAccess().getLabelsElkLabelParserRuleCall_3_3_0()); 
            pushFollow(FOLLOW_2);
            ruleElkLabel();

            state._fsp--;

             after(grammarAccess.getElkLabelAccess().getLabelsElkLabelParserRuleCall_3_3_0()); 

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
    // $ANTLR end "rule__ElkLabel__LabelsAssignment_3_3"


    // $ANTLR start "rule__ElkPort__IdentifierAssignment_1"
    // InternalGrana.g:10478:1: rule__ElkPort__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkPort__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10482:1: ( ( RULE_ID ) )
            // InternalGrana.g:10483:1: ( RULE_ID )
            {
            // InternalGrana.g:10483:1: ( RULE_ID )
            // InternalGrana.g:10484:1: RULE_ID
            {
             before(grammarAccess.getElkPortAccess().getIdentifierIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElkPortAccess().getIdentifierIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__ElkPort__IdentifierAssignment_1"


    // $ANTLR start "rule__ElkPort__PropertiesAssignment_2_2"
    // InternalGrana.g:10493:1: rule__ElkPort__PropertiesAssignment_2_2 : ( ruleProperty ) ;
    public final void rule__ElkPort__PropertiesAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10497:1: ( ( ruleProperty ) )
            // InternalGrana.g:10498:1: ( ruleProperty )
            {
            // InternalGrana.g:10498:1: ( ruleProperty )
            // InternalGrana.g:10499:1: ruleProperty
            {
             before(grammarAccess.getElkPortAccess().getPropertiesPropertyParserRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkPortAccess().getPropertiesPropertyParserRuleCall_2_2_0()); 

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
    // $ANTLR end "rule__ElkPort__PropertiesAssignment_2_2"


    // $ANTLR start "rule__ElkPort__LabelsAssignment_2_3"
    // InternalGrana.g:10508:1: rule__ElkPort__LabelsAssignment_2_3 : ( ruleElkLabel ) ;
    public final void rule__ElkPort__LabelsAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10512:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10513:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10513:1: ( ruleElkLabel )
            // InternalGrana.g:10514:1: ruleElkLabel
            {
             before(grammarAccess.getElkPortAccess().getLabelsElkLabelParserRuleCall_2_3_0()); 
            pushFollow(FOLLOW_2);
            ruleElkLabel();

            state._fsp--;

             after(grammarAccess.getElkPortAccess().getLabelsElkLabelParserRuleCall_2_3_0()); 

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
    // $ANTLR end "rule__ElkPort__LabelsAssignment_2_3"


    // $ANTLR start "rule__ShapeLayout__XAssignment_2_0_2"
    // InternalGrana.g:10523:1: rule__ShapeLayout__XAssignment_2_0_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__XAssignment_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10527:1: ( ( ruleNumber ) )
            // InternalGrana.g:10528:1: ( ruleNumber )
            {
            // InternalGrana.g:10528:1: ( ruleNumber )
            // InternalGrana.g:10529:1: ruleNumber
            {
             before(grammarAccess.getShapeLayoutAccess().getXNumberParserRuleCall_2_0_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getShapeLayoutAccess().getXNumberParserRuleCall_2_0_2_0()); 

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
    // $ANTLR end "rule__ShapeLayout__XAssignment_2_0_2"


    // $ANTLR start "rule__ShapeLayout__YAssignment_2_0_4"
    // InternalGrana.g:10538:1: rule__ShapeLayout__YAssignment_2_0_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__YAssignment_2_0_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10542:1: ( ( ruleNumber ) )
            // InternalGrana.g:10543:1: ( ruleNumber )
            {
            // InternalGrana.g:10543:1: ( ruleNumber )
            // InternalGrana.g:10544:1: ruleNumber
            {
             before(grammarAccess.getShapeLayoutAccess().getYNumberParserRuleCall_2_0_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getShapeLayoutAccess().getYNumberParserRuleCall_2_0_4_0()); 

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
    // $ANTLR end "rule__ShapeLayout__YAssignment_2_0_4"


    // $ANTLR start "rule__ShapeLayout__WidthAssignment_2_1_2"
    // InternalGrana.g:10553:1: rule__ShapeLayout__WidthAssignment_2_1_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__WidthAssignment_2_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10557:1: ( ( ruleNumber ) )
            // InternalGrana.g:10558:1: ( ruleNumber )
            {
            // InternalGrana.g:10558:1: ( ruleNumber )
            // InternalGrana.g:10559:1: ruleNumber
            {
             before(grammarAccess.getShapeLayoutAccess().getWidthNumberParserRuleCall_2_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getShapeLayoutAccess().getWidthNumberParserRuleCall_2_1_2_0()); 

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
    // $ANTLR end "rule__ShapeLayout__WidthAssignment_2_1_2"


    // $ANTLR start "rule__ShapeLayout__HeightAssignment_2_1_4"
    // InternalGrana.g:10568:1: rule__ShapeLayout__HeightAssignment_2_1_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__HeightAssignment_2_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10572:1: ( ( ruleNumber ) )
            // InternalGrana.g:10573:1: ( ruleNumber )
            {
            // InternalGrana.g:10573:1: ( ruleNumber )
            // InternalGrana.g:10574:1: ruleNumber
            {
             before(grammarAccess.getShapeLayoutAccess().getHeightNumberParserRuleCall_2_1_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getShapeLayoutAccess().getHeightNumberParserRuleCall_2_1_4_0()); 

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
    // $ANTLR end "rule__ShapeLayout__HeightAssignment_2_1_4"


    // $ANTLR start "rule__ElkEdge__IdentifierAssignment_1_0"
    // InternalGrana.g:10583:1: rule__ElkEdge__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkEdge__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10587:1: ( ( RULE_ID ) )
            // InternalGrana.g:10588:1: ( RULE_ID )
            {
            // InternalGrana.g:10588:1: ( RULE_ID )
            // InternalGrana.g:10589:1: RULE_ID
            {
             before(grammarAccess.getElkEdgeAccess().getIdentifierIDTerminalRuleCall_1_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElkEdgeAccess().getIdentifierIDTerminalRuleCall_1_0_0()); 

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
    // $ANTLR end "rule__ElkEdge__IdentifierAssignment_1_0"


    // $ANTLR start "rule__ElkEdge__SourcesAssignment_2"
    // InternalGrana.g:10598:1: rule__ElkEdge__SourcesAssignment_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__SourcesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10602:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10603:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10603:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10604:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_2_0()); 
            // InternalGrana.g:10605:1: ( ruleQualifiedId )
            // InternalGrana.g:10606:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeQualifiedIdParserRuleCall_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeQualifiedIdParserRuleCall_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_2_0()); 

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
    // $ANTLR end "rule__ElkEdge__SourcesAssignment_2"


    // $ANTLR start "rule__ElkEdge__SourcesAssignment_3_1"
    // InternalGrana.g:10617:1: rule__ElkEdge__SourcesAssignment_3_1 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__SourcesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10621:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10622:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10622:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10623:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_3_1_0()); 
            // InternalGrana.g:10624:1: ( ruleQualifiedId )
            // InternalGrana.g:10625:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeQualifiedIdParserRuleCall_3_1_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeQualifiedIdParserRuleCall_3_1_0_1()); 

            }

             after(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_3_1_0()); 

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
    // $ANTLR end "rule__ElkEdge__SourcesAssignment_3_1"


    // $ANTLR start "rule__ElkEdge__TargetsAssignment_5"
    // InternalGrana.g:10636:1: rule__ElkEdge__TargetsAssignment_5 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__TargetsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10640:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10641:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10641:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10642:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_5_0()); 
            // InternalGrana.g:10643:1: ( ruleQualifiedId )
            // InternalGrana.g:10644:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeQualifiedIdParserRuleCall_5_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeQualifiedIdParserRuleCall_5_0_1()); 

            }

             after(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_5_0()); 

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
    // $ANTLR end "rule__ElkEdge__TargetsAssignment_5"


    // $ANTLR start "rule__ElkEdge__TargetsAssignment_6_1"
    // InternalGrana.g:10655:1: rule__ElkEdge__TargetsAssignment_6_1 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__TargetsAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10659:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10660:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10660:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10661:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_6_1_0()); 
            // InternalGrana.g:10662:1: ( ruleQualifiedId )
            // InternalGrana.g:10663:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeQualifiedIdParserRuleCall_6_1_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeQualifiedIdParserRuleCall_6_1_0_1()); 

            }

             after(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_6_1_0()); 

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
    // $ANTLR end "rule__ElkEdge__TargetsAssignment_6_1"


    // $ANTLR start "rule__ElkEdge__PropertiesAssignment_7_2"
    // InternalGrana.g:10674:1: rule__ElkEdge__PropertiesAssignment_7_2 : ( ruleProperty ) ;
    public final void rule__ElkEdge__PropertiesAssignment_7_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10678:1: ( ( ruleProperty ) )
            // InternalGrana.g:10679:1: ( ruleProperty )
            {
            // InternalGrana.g:10679:1: ( ruleProperty )
            // InternalGrana.g:10680:1: ruleProperty
            {
             before(grammarAccess.getElkEdgeAccess().getPropertiesPropertyParserRuleCall_7_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkEdgeAccess().getPropertiesPropertyParserRuleCall_7_2_0()); 

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
    // $ANTLR end "rule__ElkEdge__PropertiesAssignment_7_2"


    // $ANTLR start "rule__ElkEdge__LabelsAssignment_7_3"
    // InternalGrana.g:10689:1: rule__ElkEdge__LabelsAssignment_7_3 : ( ruleElkLabel ) ;
    public final void rule__ElkEdge__LabelsAssignment_7_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10693:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10694:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10694:1: ( ruleElkLabel )
            // InternalGrana.g:10695:1: ruleElkLabel
            {
             before(grammarAccess.getElkEdgeAccess().getLabelsElkLabelParserRuleCall_7_3_0()); 
            pushFollow(FOLLOW_2);
            ruleElkLabel();

            state._fsp--;

             after(grammarAccess.getElkEdgeAccess().getLabelsElkLabelParserRuleCall_7_3_0()); 

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
    // $ANTLR end "rule__ElkEdge__LabelsAssignment_7_3"


    // $ANTLR start "rule__EdgeLayout__SectionsAssignment_2_0"
    // InternalGrana.g:10704:1: rule__EdgeLayout__SectionsAssignment_2_0 : ( ruleElkSingleEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10708:1: ( ( ruleElkSingleEdgeSection ) )
            // InternalGrana.g:10709:1: ( ruleElkSingleEdgeSection )
            {
            // InternalGrana.g:10709:1: ( ruleElkSingleEdgeSection )
            // InternalGrana.g:10710:1: ruleElkSingleEdgeSection
            {
             before(grammarAccess.getEdgeLayoutAccess().getSectionsElkSingleEdgeSectionParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleElkSingleEdgeSection();

            state._fsp--;

             after(grammarAccess.getEdgeLayoutAccess().getSectionsElkSingleEdgeSectionParserRuleCall_2_0_0()); 

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
    // $ANTLR end "rule__EdgeLayout__SectionsAssignment_2_0"


    // $ANTLR start "rule__EdgeLayout__SectionsAssignment_2_1"
    // InternalGrana.g:10719:1: rule__EdgeLayout__SectionsAssignment_2_1 : ( ruleElkEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10723:1: ( ( ruleElkEdgeSection ) )
            // InternalGrana.g:10724:1: ( ruleElkEdgeSection )
            {
            // InternalGrana.g:10724:1: ( ruleElkEdgeSection )
            // InternalGrana.g:10725:1: ruleElkEdgeSection
            {
             before(grammarAccess.getEdgeLayoutAccess().getSectionsElkEdgeSectionParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleElkEdgeSection();

            state._fsp--;

             after(grammarAccess.getEdgeLayoutAccess().getSectionsElkEdgeSectionParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__EdgeLayout__SectionsAssignment_2_1"


    // $ANTLR start "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2"
    // InternalGrana.g:10734:1: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10738:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10739:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10739:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10740:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_0_2_0()); 
            // InternalGrana.g:10741:1: ( ruleQualifiedId )
            // InternalGrana.g:10742:1: ruleQualifiedId
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_0_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_0_2_0_1()); 

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_0_2_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2"
    // InternalGrana.g:10753:1: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10757:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10758:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10758:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10759:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_0_1_2_0()); 
            // InternalGrana.g:10760:1: ( ruleQualifiedId )
            // InternalGrana.g:10761:1: ruleQualifiedId
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_1_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_1_0_1_2_0_1()); 

            }

             after(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_0_1_2_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2"
    // InternalGrana.g:10772:1: rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10776:1: ( ( ruleNumber ) )
            // InternalGrana.g:10777:1: ( ruleNumber )
            {
            // InternalGrana.g:10777:1: ( ruleNumber )
            // InternalGrana.g:10778:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_0_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartXNumberParserRuleCall_1_0_2_2_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4"
    // InternalGrana.g:10787:1: rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10791:1: ( ( ruleNumber ) )
            // InternalGrana.g:10792:1: ( ruleNumber )
            {
            // InternalGrana.g:10792:1: ( ruleNumber )
            // InternalGrana.g:10793:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_0_2_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getStartYNumberParserRuleCall_1_0_2_4_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4"


    // $ANTLR start "rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2"
    // InternalGrana.g:10802:1: rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10806:1: ( ( ruleNumber ) )
            // InternalGrana.g:10807:1: ( ruleNumber )
            {
            // InternalGrana.g:10807:1: ( ruleNumber )
            // InternalGrana.g:10808:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_0_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndXNumberParserRuleCall_1_0_3_2_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4"
    // InternalGrana.g:10817:1: rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10821:1: ( ( ruleNumber ) )
            // InternalGrana.g:10822:1: ( ruleNumber )
            {
            // InternalGrana.g:10822:1: ( ruleNumber )
            // InternalGrana.g:10823:1: ruleNumber
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_0_3_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getEndYNumberParserRuleCall_1_0_3_4_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4"


    // $ANTLR start "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2"
    // InternalGrana.g:10832:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10836:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:10837:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:10837:1: ( ruleElkBendPoint )
            // InternalGrana.g:10838:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_2_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2"


    // $ANTLR start "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1"
    // InternalGrana.g:10847:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10851:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:10852:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:10852:1: ( ruleElkBendPoint )
            // InternalGrana.g:10853:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_1_1_3_1_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1"


    // $ANTLR start "rule__ElkSingleEdgeSection__PropertiesAssignment_1_2"
    // InternalGrana.g:10862:1: rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 : ( ruleProperty ) ;
    public final void rule__ElkSingleEdgeSection__PropertiesAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10866:1: ( ( ruleProperty ) )
            // InternalGrana.g:10867:1: ( ruleProperty )
            {
            // InternalGrana.g:10867:1: ( ruleProperty )
            // InternalGrana.g:10868:1: ruleProperty
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesPropertyParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesPropertyParserRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__ElkSingleEdgeSection__PropertiesAssignment_1_2"


    // $ANTLR start "rule__ElkEdgeSection__IdentifierAssignment_1"
    // InternalGrana.g:10877:1: rule__ElkEdgeSection__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkEdgeSection__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10881:1: ( ( RULE_ID ) )
            // InternalGrana.g:10882:1: ( RULE_ID )
            {
            // InternalGrana.g:10882:1: ( RULE_ID )
            // InternalGrana.g:10883:1: RULE_ID
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getIdentifierIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__IdentifierAssignment_1"


    // $ANTLR start "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1"
    // InternalGrana.g:10892:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10896:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10897:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10897:1: ( ( RULE_ID ) )
            // InternalGrana.g:10898:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 
            // InternalGrana.g:10899:1: ( RULE_ID )
            // InternalGrana.g:10900:1: RULE_ID
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionIDTerminalRuleCall_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionIDTerminalRuleCall_2_1_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1"


    // $ANTLR start "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1"
    // InternalGrana.g:10911:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10915:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10916:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10916:1: ( ( RULE_ID ) )
            // InternalGrana.g:10917:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 
            // InternalGrana.g:10918:1: ( RULE_ID )
            // InternalGrana.g:10919:1: RULE_ID
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionIDTerminalRuleCall_2_2_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionIDTerminalRuleCall_2_2_1_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1"


    // $ANTLR start "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2"
    // InternalGrana.g:10930:1: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10934:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10935:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10935:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10936:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_0_2_0()); 
            // InternalGrana.g:10937:1: ( ruleQualifiedId )
            // InternalGrana.g:10938:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_0_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_0_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_0_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2"


    // $ANTLR start "rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2"
    // InternalGrana.g:10949:1: rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10953:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10954:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10954:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10955:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_0_1_2_0()); 
            // InternalGrana.g:10956:1: ( ruleQualifiedId )
            // InternalGrana.g:10957:1: ruleQualifiedId
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_1_2_0_1()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedId();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeQualifiedIdParserRuleCall_4_0_1_2_0_1()); 

            }

             after(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_0_1_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2"


    // $ANTLR start "rule__ElkEdgeSection__StartXAssignment_4_0_2_2"
    // InternalGrana.g:10968:1: rule__ElkEdgeSection__StartXAssignment_4_0_2_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartXAssignment_4_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10972:1: ( ( ruleNumber ) )
            // InternalGrana.g:10973:1: ( ruleNumber )
            {
            // InternalGrana.g:10973:1: ( ruleNumber )
            // InternalGrana.g:10974:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_0_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getStartXNumberParserRuleCall_4_0_2_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__StartXAssignment_4_0_2_2"


    // $ANTLR start "rule__ElkEdgeSection__StartYAssignment_4_0_2_4"
    // InternalGrana.g:10983:1: rule__ElkEdgeSection__StartYAssignment_4_0_2_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartYAssignment_4_0_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10987:1: ( ( ruleNumber ) )
            // InternalGrana.g:10988:1: ( ruleNumber )
            {
            // InternalGrana.g:10988:1: ( ruleNumber )
            // InternalGrana.g:10989:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_0_2_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getStartYNumberParserRuleCall_4_0_2_4_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__StartYAssignment_4_0_2_4"


    // $ANTLR start "rule__ElkEdgeSection__EndXAssignment_4_0_3_2"
    // InternalGrana.g:10998:1: rule__ElkEdgeSection__EndXAssignment_4_0_3_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndXAssignment_4_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11002:1: ( ( ruleNumber ) )
            // InternalGrana.g:11003:1: ( ruleNumber )
            {
            // InternalGrana.g:11003:1: ( ruleNumber )
            // InternalGrana.g:11004:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_0_3_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getEndXNumberParserRuleCall_4_0_3_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__EndXAssignment_4_0_3_2"


    // $ANTLR start "rule__ElkEdgeSection__EndYAssignment_4_0_3_4"
    // InternalGrana.g:11013:1: rule__ElkEdgeSection__EndYAssignment_4_0_3_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndYAssignment_4_0_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11017:1: ( ( ruleNumber ) )
            // InternalGrana.g:11018:1: ( ruleNumber )
            {
            // InternalGrana.g:11018:1: ( ruleNumber )
            // InternalGrana.g:11019:1: ruleNumber
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_0_3_4_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getEndYNumberParserRuleCall_4_0_3_4_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__EndYAssignment_4_0_3_4"


    // $ANTLR start "rule__ElkEdgeSection__BendPointsAssignment_4_1_2"
    // InternalGrana.g:11028:1: rule__ElkEdgeSection__BendPointsAssignment_4_1_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11032:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:11033:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:11033:1: ( ruleElkBendPoint )
            // InternalGrana.g:11034:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__BendPointsAssignment_4_1_2"


    // $ANTLR start "rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1"
    // InternalGrana.g:11043:1: rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11047:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:11048:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:11048:1: ( ruleElkBendPoint )
            // InternalGrana.g:11049:1: ruleElkBendPoint
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleElkBendPoint();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getBendPointsElkBendPointParserRuleCall_4_1_3_1_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1"


    // $ANTLR start "rule__ElkEdgeSection__PropertiesAssignment_4_2"
    // InternalGrana.g:11058:1: rule__ElkEdgeSection__PropertiesAssignment_4_2 : ( ruleProperty ) ;
    public final void rule__ElkEdgeSection__PropertiesAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11062:1: ( ( ruleProperty ) )
            // InternalGrana.g:11063:1: ( ruleProperty )
            {
            // InternalGrana.g:11063:1: ( ruleProperty )
            // InternalGrana.g:11064:1: ruleProperty
            {
             before(grammarAccess.getElkEdgeSectionAccess().getPropertiesPropertyParserRuleCall_4_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getElkEdgeSectionAccess().getPropertiesPropertyParserRuleCall_4_2_0()); 

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
    // $ANTLR end "rule__ElkEdgeSection__PropertiesAssignment_4_2"


    // $ANTLR start "rule__ElkBendPoint__XAssignment_0"
    // InternalGrana.g:11073:1: rule__ElkBendPoint__XAssignment_0 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__XAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11077:1: ( ( ruleNumber ) )
            // InternalGrana.g:11078:1: ( ruleNumber )
            {
            // InternalGrana.g:11078:1: ( ruleNumber )
            // InternalGrana.g:11079:1: ruleNumber
            {
             before(grammarAccess.getElkBendPointAccess().getXNumberParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkBendPointAccess().getXNumberParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__ElkBendPoint__XAssignment_0"


    // $ANTLR start "rule__ElkBendPoint__YAssignment_2"
    // InternalGrana.g:11088:1: rule__ElkBendPoint__YAssignment_2 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__YAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11092:1: ( ( ruleNumber ) )
            // InternalGrana.g:11093:1: ( ruleNumber )
            {
            // InternalGrana.g:11093:1: ( ruleNumber )
            // InternalGrana.g:11094:1: ruleNumber
            {
             before(grammarAccess.getElkBendPointAccess().getYNumberParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumber();

            state._fsp--;

             after(grammarAccess.getElkBendPointAccess().getYNumberParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__ElkBendPoint__YAssignment_2"


    // $ANTLR start "rule__Property__KeyAssignment_0"
    // InternalGrana.g:11103:1: rule__Property__KeyAssignment_0 : ( rulePropertyKey ) ;
    public final void rule__Property__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11107:1: ( ( rulePropertyKey ) )
            // InternalGrana.g:11108:1: ( rulePropertyKey )
            {
            // InternalGrana.g:11108:1: ( rulePropertyKey )
            // InternalGrana.g:11109:1: rulePropertyKey
            {
             before(grammarAccess.getPropertyAccess().getKeyPropertyKeyParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            rulePropertyKey();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getKeyPropertyKeyParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__Property__KeyAssignment_0"


    // $ANTLR start "rule__Property__ValueAssignment_2_0"
    // InternalGrana.g:11118:1: rule__Property__ValueAssignment_2_0 : ( ruleStringValue ) ;
    public final void rule__Property__ValueAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11122:1: ( ( ruleStringValue ) )
            // InternalGrana.g:11123:1: ( ruleStringValue )
            {
            // InternalGrana.g:11123:1: ( ruleStringValue )
            // InternalGrana.g:11124:1: ruleStringValue
            {
             before(grammarAccess.getPropertyAccess().getValueStringValueParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleStringValue();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueStringValueParserRuleCall_2_0_0()); 

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
    // $ANTLR end "rule__Property__ValueAssignment_2_0"


    // $ANTLR start "rule__Property__ValueAssignment_2_1"
    // InternalGrana.g:11133:1: rule__Property__ValueAssignment_2_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__Property__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11137:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:11138:1: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:11138:1: ( ruleQualifiedIdValue )
            // InternalGrana.g:11139:1: ruleQualifiedIdValue
            {
             before(grammarAccess.getPropertyAccess().getValueQualifiedIdValueParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleQualifiedIdValue();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueQualifiedIdValueParserRuleCall_2_1_0()); 

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
    // $ANTLR end "rule__Property__ValueAssignment_2_1"


    // $ANTLR start "rule__Property__ValueAssignment_2_2"
    // InternalGrana.g:11148:1: rule__Property__ValueAssignment_2_2 : ( ruleNumberValue ) ;
    public final void rule__Property__ValueAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11152:1: ( ( ruleNumberValue ) )
            // InternalGrana.g:11153:1: ( ruleNumberValue )
            {
            // InternalGrana.g:11153:1: ( ruleNumberValue )
            // InternalGrana.g:11154:1: ruleNumberValue
            {
             before(grammarAccess.getPropertyAccess().getValueNumberValueParserRuleCall_2_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNumberValue();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueNumberValueParserRuleCall_2_2_0()); 

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
    // $ANTLR end "rule__Property__ValueAssignment_2_2"


    // $ANTLR start "rule__Property__ValueAssignment_2_3"
    // InternalGrana.g:11163:1: rule__Property__ValueAssignment_2_3 : ( ruleBooleanValue ) ;
    public final void rule__Property__ValueAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11167:1: ( ( ruleBooleanValue ) )
            // InternalGrana.g:11168:1: ( ruleBooleanValue )
            {
            // InternalGrana.g:11168:1: ( ruleBooleanValue )
            // InternalGrana.g:11169:1: ruleBooleanValue
            {
             before(grammarAccess.getPropertyAccess().getValueBooleanValueParserRuleCall_2_3_0()); 
            pushFollow(FOLLOW_2);
            ruleBooleanValue();

            state._fsp--;

             after(grammarAccess.getPropertyAccess().getValueBooleanValueParserRuleCall_2_3_0()); 

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
    // $ANTLR end "rule__Property__ValueAssignment_2_3"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000006100000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000006100002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x3000000000000080L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0xC000000000200000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000002000000010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000002000000012L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000002000018010L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x8000000000200000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000001680000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000010000000080L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0004370000000080L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0004160000000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000250000000080L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0003000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0008000100000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000008100000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x04F0000000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x00F0000000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0100000000000080L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0008400000000000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x00000000000060F0L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0003000000000002L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x00F0000000000002L});

}
