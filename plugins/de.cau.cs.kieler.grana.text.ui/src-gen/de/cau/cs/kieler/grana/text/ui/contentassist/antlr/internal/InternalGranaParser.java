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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_SIGNED_INT", "RULE_FLOAT", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'null'", "'true'", "'false'", "'csv'", "'json'", "'globalResources'", "'globalOutputs'", "'execute'", "'job'", "'resources'", "'layoutoptions'", "'analyses'", "'output'", "'comparejob'", "'rangejob'", "'rangeoption'", "'rangeanalysis'", "'component'", "'rangeanalyses'", "'floatvalues'", "','", "'intvalues'", "'intrange'", "'to'", "'enumvalues'", "'ref'", "'filter'", "'{'", "'}'", "'node'", "'label'", "':'", "'port'", "'layout'", "'['", "']'", "'position'", "'size'", "'edge'", "'->'", "'incoming'", "'outgoing'", "'start'", "'end'", "'bends'", "'|'", "'section'", "'.'", "'parallel'", "'all'", "'layoutBeforeAnalysis'", "'measureExecutionTime'", "'recurse'"
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
    public static final int T__65=65;
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

            if ( (LA2_0==62) ) {
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
            case 21:
                {
                alt3=1;
                }
                break;
            case 27:
                {
                alt3=2;
                }
                break;
            case 26:
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

            if ( (LA4_0==29) ) {
                alt4=1;
            }
            else if ( (LA4_0==31) ) {
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
            case 32:
                {
                alt5=1;
                }
                break;
            case 34:
            case 35:
                {
                alt5=2;
                }
                break;
            case 37:
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

            if ( (LA6_0==35) ) {
                alt6=1;
            }
            else if ( (LA6_0==34) ) {
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

            if ( (LA7_0==38) ) {
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

            if ( (LA8_0==38) ) {
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
            case 43:
                {
                alt9=1;
                }
                break;
            case 45:
                {
                alt9=2;
                }
                break;
            case 42:
                {
                alt9=3;
                }
                break;
            case 51:
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

            if ( (LA11_0==EOF||LA11_0==RULE_ID||LA11_0==48||(LA11_0>=53 && LA11_0<=57)) ) {
                alt11=1;
            }
            else if ( (LA11_0==59) ) {
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

                        if ( (LA10_0==59) ) {
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
    // InternalGrana.g:1396:1: rule__Property__Alternatives_2 : ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) | ( 'null' ) );
    public final void rule__Property__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1400:1: ( ( ( rule__Property__ValueAssignment_2_0 ) ) | ( ( rule__Property__ValueAssignment_2_1 ) ) | ( ( rule__Property__ValueAssignment_2_2 ) ) | ( ( rule__Property__ValueAssignment_2_3 ) ) | ( 'null' ) )
            int alt13=5;
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
            case 14:
            case 15:
                {
                alt13=4;
                }
                break;
            case 13:
                {
                alt13=5;
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
                case 5 :
                    // InternalGrana.g:1425:6: ( 'null' )
                    {
                    // InternalGrana.g:1425:6: ( 'null' )
                    // InternalGrana.g:1426:1: 'null'
                    {
                     before(grammarAccess.getPropertyAccess().getNullKeyword_2_4()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getPropertyAccess().getNullKeyword_2_4()); 

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
    // InternalGrana.g:1438:1: rule__NumberValue__Alternatives : ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) );
    public final void rule__NumberValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1442:1: ( ( RULE_SIGNED_INT ) | ( RULE_FLOAT ) )
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
                    // InternalGrana.g:1443:1: ( RULE_SIGNED_INT )
                    {
                    // InternalGrana.g:1443:1: ( RULE_SIGNED_INT )
                    // InternalGrana.g:1444:1: RULE_SIGNED_INT
                    {
                     before(grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 
                    match(input,RULE_SIGNED_INT,FOLLOW_2); 
                     after(grammarAccess.getNumberValueAccess().getSIGNED_INTTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1449:6: ( RULE_FLOAT )
                    {
                    // InternalGrana.g:1449:6: ( RULE_FLOAT )
                    // InternalGrana.g:1450:1: RULE_FLOAT
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
    // InternalGrana.g:1460:1: rule__BooleanValue__Alternatives : ( ( 'true' ) | ( 'false' ) );
    public final void rule__BooleanValue__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1464:1: ( ( 'true' ) | ( 'false' ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==14) ) {
                alt15=1;
            }
            else if ( (LA15_0==15) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // InternalGrana.g:1465:1: ( 'true' )
                    {
                    // InternalGrana.g:1465:1: ( 'true' )
                    // InternalGrana.g:1466:1: 'true'
                    {
                     before(grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getBooleanValueAccess().getTrueKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1473:6: ( 'false' )
                    {
                    // InternalGrana.g:1473:6: ( 'false' )
                    // InternalGrana.g:1474:1: 'false'
                    {
                     before(grammarAccess.getBooleanValueAccess().getFalseKeyword_1()); 
                    match(input,15,FOLLOW_2); 
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
    // InternalGrana.g:1486:1: rule__OutputType__Alternatives : ( ( ( 'csv' ) ) | ( ( 'json' ) ) );
    public final void rule__OutputType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1490:1: ( ( ( 'csv' ) ) | ( ( 'json' ) ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==16) ) {
                alt16=1;
            }
            else if ( (LA16_0==17) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalGrana.g:1491:1: ( ( 'csv' ) )
                    {
                    // InternalGrana.g:1491:1: ( ( 'csv' ) )
                    // InternalGrana.g:1492:1: ( 'csv' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 
                    // InternalGrana.g:1493:1: ( 'csv' )
                    // InternalGrana.g:1493:3: 'csv'
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getOutputTypeAccess().getCsvEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalGrana.g:1498:6: ( ( 'json' ) )
                    {
                    // InternalGrana.g:1498:6: ( ( 'json' ) )
                    // InternalGrana.g:1499:1: ( 'json' )
                    {
                     before(grammarAccess.getOutputTypeAccess().getJsonEnumLiteralDeclaration_1()); 
                    // InternalGrana.g:1500:1: ( 'json' )
                    // InternalGrana.g:1500:3: 'json'
                    {
                    match(input,17,FOLLOW_2); 

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
    // InternalGrana.g:1512:1: rule__Grana__Group__0 : rule__Grana__Group__0__Impl rule__Grana__Group__1 ;
    public final void rule__Grana__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1516:1: ( rule__Grana__Group__0__Impl rule__Grana__Group__1 )
            // InternalGrana.g:1517:2: rule__Grana__Group__0__Impl rule__Grana__Group__1
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
    // InternalGrana.g:1524:1: rule__Grana__Group__0__Impl : ( ( rule__Grana__Group_0__0 )? ) ;
    public final void rule__Grana__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1528:1: ( ( ( rule__Grana__Group_0__0 )? ) )
            // InternalGrana.g:1529:1: ( ( rule__Grana__Group_0__0 )? )
            {
            // InternalGrana.g:1529:1: ( ( rule__Grana__Group_0__0 )? )
            // InternalGrana.g:1530:1: ( rule__Grana__Group_0__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_0()); 
            // InternalGrana.g:1531:1: ( rule__Grana__Group_0__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==18) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalGrana.g:1531:2: rule__Grana__Group_0__0
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
    // InternalGrana.g:1541:1: rule__Grana__Group__1 : rule__Grana__Group__1__Impl rule__Grana__Group__2 ;
    public final void rule__Grana__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1545:1: ( rule__Grana__Group__1__Impl rule__Grana__Group__2 )
            // InternalGrana.g:1546:2: rule__Grana__Group__1__Impl rule__Grana__Group__2
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
    // InternalGrana.g:1553:1: rule__Grana__Group__1__Impl : ( ( rule__Grana__Group_1__0 )? ) ;
    public final void rule__Grana__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1557:1: ( ( ( rule__Grana__Group_1__0 )? ) )
            // InternalGrana.g:1558:1: ( ( rule__Grana__Group_1__0 )? )
            {
            // InternalGrana.g:1558:1: ( ( rule__Grana__Group_1__0 )? )
            // InternalGrana.g:1559:1: ( rule__Grana__Group_1__0 )?
            {
             before(grammarAccess.getGranaAccess().getGroup_1()); 
            // InternalGrana.g:1560:1: ( rule__Grana__Group_1__0 )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==19) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalGrana.g:1560:2: rule__Grana__Group_1__0
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
    // InternalGrana.g:1570:1: rule__Grana__Group__2 : rule__Grana__Group__2__Impl rule__Grana__Group__3 ;
    public final void rule__Grana__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1574:1: ( rule__Grana__Group__2__Impl rule__Grana__Group__3 )
            // InternalGrana.g:1575:2: rule__Grana__Group__2__Impl rule__Grana__Group__3
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
    // InternalGrana.g:1582:1: rule__Grana__Group__2__Impl : ( ( rule__Grana__Group_2__0 ) ) ;
    public final void rule__Grana__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1586:1: ( ( ( rule__Grana__Group_2__0 ) ) )
            // InternalGrana.g:1587:1: ( ( rule__Grana__Group_2__0 ) )
            {
            // InternalGrana.g:1587:1: ( ( rule__Grana__Group_2__0 ) )
            // InternalGrana.g:1588:1: ( rule__Grana__Group_2__0 )
            {
             before(grammarAccess.getGranaAccess().getGroup_2()); 
            // InternalGrana.g:1589:1: ( rule__Grana__Group_2__0 )
            // InternalGrana.g:1589:2: rule__Grana__Group_2__0
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
    // InternalGrana.g:1599:1: rule__Grana__Group__3 : rule__Grana__Group__3__Impl ;
    public final void rule__Grana__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1603:1: ( rule__Grana__Group__3__Impl )
            // InternalGrana.g:1604:2: rule__Grana__Group__3__Impl
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
    // InternalGrana.g:1610:1: rule__Grana__Group__3__Impl : ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) ;
    public final void rule__Grana__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1614:1: ( ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) ) )
            // InternalGrana.g:1615:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            {
            // InternalGrana.g:1615:1: ( ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* ) )
            // InternalGrana.g:1616:1: ( ( rule__Grana__JobsAssignment_3 ) ) ( ( rule__Grana__JobsAssignment_3 )* )
            {
            // InternalGrana.g:1616:1: ( ( rule__Grana__JobsAssignment_3 ) )
            // InternalGrana.g:1617:1: ( rule__Grana__JobsAssignment_3 )
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1618:1: ( rule__Grana__JobsAssignment_3 )
            // InternalGrana.g:1618:2: rule__Grana__JobsAssignment_3
            {
            pushFollow(FOLLOW_7);
            rule__Grana__JobsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGranaAccess().getJobsAssignment_3()); 

            }

            // InternalGrana.g:1621:1: ( ( rule__Grana__JobsAssignment_3 )* )
            // InternalGrana.g:1622:1: ( rule__Grana__JobsAssignment_3 )*
            {
             before(grammarAccess.getGranaAccess().getJobsAssignment_3()); 
            // InternalGrana.g:1623:1: ( rule__Grana__JobsAssignment_3 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==21||(LA19_0>=26 && LA19_0<=27)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalGrana.g:1623:2: rule__Grana__JobsAssignment_3
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
    // InternalGrana.g:1642:1: rule__Grana__Group_0__0 : rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 ;
    public final void rule__Grana__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1646:1: ( rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1 )
            // InternalGrana.g:1647:2: rule__Grana__Group_0__0__Impl rule__Grana__Group_0__1
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
    // InternalGrana.g:1654:1: rule__Grana__Group_0__0__Impl : ( 'globalResources' ) ;
    public final void rule__Grana__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1658:1: ( ( 'globalResources' ) )
            // InternalGrana.g:1659:1: ( 'globalResources' )
            {
            // InternalGrana.g:1659:1: ( 'globalResources' )
            // InternalGrana.g:1660:1: 'globalResources'
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesKeyword_0_0()); 
            match(input,18,FOLLOW_2); 
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
    // InternalGrana.g:1673:1: rule__Grana__Group_0__1 : rule__Grana__Group_0__1__Impl ;
    public final void rule__Grana__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1677:1: ( rule__Grana__Group_0__1__Impl )
            // InternalGrana.g:1678:2: rule__Grana__Group_0__1__Impl
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
    // InternalGrana.g:1684:1: rule__Grana__Group_0__1__Impl : ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) ;
    public final void rule__Grana__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1688:1: ( ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* ) )
            // InternalGrana.g:1689:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            {
            // InternalGrana.g:1689:1: ( ( rule__Grana__GlobalResourcesAssignment_0_1 )* )
            // InternalGrana.g:1690:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            {
             before(grammarAccess.getGranaAccess().getGlobalResourcesAssignment_0_1()); 
            // InternalGrana.g:1691:1: ( rule__Grana__GlobalResourcesAssignment_0_1 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ID) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalGrana.g:1691:2: rule__Grana__GlobalResourcesAssignment_0_1
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
    // InternalGrana.g:1705:1: rule__Grana__Group_1__0 : rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 ;
    public final void rule__Grana__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1709:1: ( rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1 )
            // InternalGrana.g:1710:2: rule__Grana__Group_1__0__Impl rule__Grana__Group_1__1
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
    // InternalGrana.g:1717:1: rule__Grana__Group_1__0__Impl : ( 'globalOutputs' ) ;
    public final void rule__Grana__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1721:1: ( ( 'globalOutputs' ) )
            // InternalGrana.g:1722:1: ( 'globalOutputs' )
            {
            // InternalGrana.g:1722:1: ( 'globalOutputs' )
            // InternalGrana.g:1723:1: 'globalOutputs'
            {
             before(grammarAccess.getGranaAccess().getGlobalOutputsKeyword_1_0()); 
            match(input,19,FOLLOW_2); 
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
    // InternalGrana.g:1736:1: rule__Grana__Group_1__1 : rule__Grana__Group_1__1__Impl ;
    public final void rule__Grana__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1740:1: ( rule__Grana__Group_1__1__Impl )
            // InternalGrana.g:1741:2: rule__Grana__Group_1__1__Impl
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
    // InternalGrana.g:1747:1: rule__Grana__Group_1__1__Impl : ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) ;
    public final void rule__Grana__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1751:1: ( ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* ) )
            // InternalGrana.g:1752:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            {
            // InternalGrana.g:1752:1: ( ( rule__Grana__GloobalOutputsAssignment_1_1 )* )
            // InternalGrana.g:1753:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            {
             before(grammarAccess.getGranaAccess().getGloobalOutputsAssignment_1_1()); 
            // InternalGrana.g:1754:1: ( rule__Grana__GloobalOutputsAssignment_1_1 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_ID) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalGrana.g:1754:2: rule__Grana__GloobalOutputsAssignment_1_1
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
    // InternalGrana.g:1768:1: rule__Grana__Group_2__0 : rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 ;
    public final void rule__Grana__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1772:1: ( rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1 )
            // InternalGrana.g:1773:2: rule__Grana__Group_2__0__Impl rule__Grana__Group_2__1
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
    // InternalGrana.g:1780:1: rule__Grana__Group_2__0__Impl : ( 'execute' ) ;
    public final void rule__Grana__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1784:1: ( ( 'execute' ) )
            // InternalGrana.g:1785:1: ( 'execute' )
            {
            // InternalGrana.g:1785:1: ( 'execute' )
            // InternalGrana.g:1786:1: 'execute'
            {
             before(grammarAccess.getGranaAccess().getExecuteKeyword_2_0()); 
            match(input,20,FOLLOW_2); 
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
    // InternalGrana.g:1799:1: rule__Grana__Group_2__1 : rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 ;
    public final void rule__Grana__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1803:1: ( rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2 )
            // InternalGrana.g:1804:2: rule__Grana__Group_2__1__Impl rule__Grana__Group_2__2
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
    // InternalGrana.g:1811:1: rule__Grana__Group_2__1__Impl : ( ( rule__Grana__ParallelAssignment_2_1 )? ) ;
    public final void rule__Grana__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1815:1: ( ( ( rule__Grana__ParallelAssignment_2_1 )? ) )
            // InternalGrana.g:1816:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            {
            // InternalGrana.g:1816:1: ( ( rule__Grana__ParallelAssignment_2_1 )? )
            // InternalGrana.g:1817:1: ( rule__Grana__ParallelAssignment_2_1 )?
            {
             before(grammarAccess.getGranaAccess().getParallelAssignment_2_1()); 
            // InternalGrana.g:1818:1: ( rule__Grana__ParallelAssignment_2_1 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==61) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalGrana.g:1818:2: rule__Grana__ParallelAssignment_2_1
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
    // InternalGrana.g:1828:1: rule__Grana__Group_2__2 : rule__Grana__Group_2__2__Impl ;
    public final void rule__Grana__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1832:1: ( rule__Grana__Group_2__2__Impl )
            // InternalGrana.g:1833:2: rule__Grana__Group_2__2__Impl
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
    // InternalGrana.g:1839:1: rule__Grana__Group_2__2__Impl : ( ( rule__Grana__Alternatives_2_2 ) ) ;
    public final void rule__Grana__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1843:1: ( ( ( rule__Grana__Alternatives_2_2 ) ) )
            // InternalGrana.g:1844:1: ( ( rule__Grana__Alternatives_2_2 ) )
            {
            // InternalGrana.g:1844:1: ( ( rule__Grana__Alternatives_2_2 ) )
            // InternalGrana.g:1845:1: ( rule__Grana__Alternatives_2_2 )
            {
             before(grammarAccess.getGranaAccess().getAlternatives_2_2()); 
            // InternalGrana.g:1846:1: ( rule__Grana__Alternatives_2_2 )
            // InternalGrana.g:1846:2: rule__Grana__Alternatives_2_2
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
    // InternalGrana.g:1862:1: rule__RegularJob__Group__0 : rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 ;
    public final void rule__RegularJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1866:1: ( rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1 )
            // InternalGrana.g:1867:2: rule__RegularJob__Group__0__Impl rule__RegularJob__Group__1
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
    // InternalGrana.g:1874:1: rule__RegularJob__Group__0__Impl : ( 'job' ) ;
    public final void rule__RegularJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1878:1: ( ( 'job' ) )
            // InternalGrana.g:1879:1: ( 'job' )
            {
            // InternalGrana.g:1879:1: ( 'job' )
            // InternalGrana.g:1880:1: 'job'
            {
             before(grammarAccess.getRegularJobAccess().getJobKeyword_0()); 
            match(input,21,FOLLOW_2); 
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
    // InternalGrana.g:1893:1: rule__RegularJob__Group__1 : rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 ;
    public final void rule__RegularJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1897:1: ( rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2 )
            // InternalGrana.g:1898:2: rule__RegularJob__Group__1__Impl rule__RegularJob__Group__2
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
    // InternalGrana.g:1905:1: rule__RegularJob__Group__1__Impl : ( ( rule__RegularJob__NameAssignment_1 ) ) ;
    public final void rule__RegularJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1909:1: ( ( ( rule__RegularJob__NameAssignment_1 ) ) )
            // InternalGrana.g:1910:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:1910:1: ( ( rule__RegularJob__NameAssignment_1 ) )
            // InternalGrana.g:1911:1: ( rule__RegularJob__NameAssignment_1 )
            {
             before(grammarAccess.getRegularJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:1912:1: ( rule__RegularJob__NameAssignment_1 )
            // InternalGrana.g:1912:2: rule__RegularJob__NameAssignment_1
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
    // InternalGrana.g:1922:1: rule__RegularJob__Group__2 : rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 ;
    public final void rule__RegularJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1926:1: ( rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3 )
            // InternalGrana.g:1927:2: rule__RegularJob__Group__2__Impl rule__RegularJob__Group__3
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
    // InternalGrana.g:1934:1: rule__RegularJob__Group__2__Impl : ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) ;
    public final void rule__RegularJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1938:1: ( ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? ) )
            // InternalGrana.g:1939:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            {
            // InternalGrana.g:1939:1: ( ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )? )
            // InternalGrana.g:1940:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisAssignment_2()); 
            // InternalGrana.g:1941:1: ( rule__RegularJob__LayoutBeforeAnalysisAssignment_2 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==63) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalGrana.g:1941:2: rule__RegularJob__LayoutBeforeAnalysisAssignment_2
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
    // InternalGrana.g:1951:1: rule__RegularJob__Group__3 : rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 ;
    public final void rule__RegularJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1955:1: ( rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4 )
            // InternalGrana.g:1956:2: rule__RegularJob__Group__3__Impl rule__RegularJob__Group__4
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
    // InternalGrana.g:1963:1: rule__RegularJob__Group__3__Impl : ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) ;
    public final void rule__RegularJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1967:1: ( ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? ) )
            // InternalGrana.g:1968:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            {
            // InternalGrana.g:1968:1: ( ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )? )
            // InternalGrana.g:1969:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeAssignment_3()); 
            // InternalGrana.g:1970:1: ( rule__RegularJob__MeasureExecutionTimeAssignment_3 )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==64) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalGrana.g:1970:2: rule__RegularJob__MeasureExecutionTimeAssignment_3
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
    // InternalGrana.g:1980:1: rule__RegularJob__Group__4 : rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 ;
    public final void rule__RegularJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1984:1: ( rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5 )
            // InternalGrana.g:1985:2: rule__RegularJob__Group__4__Impl rule__RegularJob__Group__5
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
    // InternalGrana.g:1992:1: rule__RegularJob__Group__4__Impl : ( 'resources' ) ;
    public final void rule__RegularJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:1996:1: ( ( 'resources' ) )
            // InternalGrana.g:1997:1: ( 'resources' )
            {
            // InternalGrana.g:1997:1: ( 'resources' )
            // InternalGrana.g:1998:1: 'resources'
            {
             before(grammarAccess.getRegularJobAccess().getResourcesKeyword_4()); 
            match(input,22,FOLLOW_2); 
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
    // InternalGrana.g:2011:1: rule__RegularJob__Group__5 : rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 ;
    public final void rule__RegularJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2015:1: ( rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6 )
            // InternalGrana.g:2016:2: rule__RegularJob__Group__5__Impl rule__RegularJob__Group__6
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
    // InternalGrana.g:2023:1: rule__RegularJob__Group__5__Impl : ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) ;
    public final void rule__RegularJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2027:1: ( ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) ) )
            // InternalGrana.g:2028:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            {
            // InternalGrana.g:2028:1: ( ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* ) )
            // InternalGrana.g:2029:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) ) ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            {
            // InternalGrana.g:2029:1: ( ( rule__RegularJob__ResourcesAssignment_5 ) )
            // InternalGrana.g:2030:1: ( rule__RegularJob__ResourcesAssignment_5 )
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:2031:1: ( rule__RegularJob__ResourcesAssignment_5 )
            // InternalGrana.g:2031:2: rule__RegularJob__ResourcesAssignment_5
            {
            pushFollow(FOLLOW_13);
            rule__RegularJob__ResourcesAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 

            }

            // InternalGrana.g:2034:1: ( ( rule__RegularJob__ResourcesAssignment_5 )* )
            // InternalGrana.g:2035:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            {
             before(grammarAccess.getRegularJobAccess().getResourcesAssignment_5()); 
            // InternalGrana.g:2036:1: ( rule__RegularJob__ResourcesAssignment_5 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_STRING||LA25_0==38) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalGrana.g:2036:2: rule__RegularJob__ResourcesAssignment_5
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
    // InternalGrana.g:2047:1: rule__RegularJob__Group__6 : rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 ;
    public final void rule__RegularJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2051:1: ( rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7 )
            // InternalGrana.g:2052:2: rule__RegularJob__Group__6__Impl rule__RegularJob__Group__7
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
    // InternalGrana.g:2059:1: rule__RegularJob__Group__6__Impl : ( 'layoutoptions' ) ;
    public final void rule__RegularJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2063:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2064:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2064:1: ( 'layoutoptions' )
            // InternalGrana.g:2065:1: 'layoutoptions'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutoptionsKeyword_6()); 
            match(input,23,FOLLOW_2); 
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
    // InternalGrana.g:2078:1: rule__RegularJob__Group__7 : rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 ;
    public final void rule__RegularJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2082:1: ( rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8 )
            // InternalGrana.g:2083:2: rule__RegularJob__Group__7__Impl rule__RegularJob__Group__8
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
    // InternalGrana.g:2090:1: rule__RegularJob__Group__7__Impl : ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) ;
    public final void rule__RegularJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2094:1: ( ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) ) )
            // InternalGrana.g:2095:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            {
            // InternalGrana.g:2095:1: ( ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* ) )
            // InternalGrana.g:2096:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) ) ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            {
            // InternalGrana.g:2096:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 ) )
            // InternalGrana.g:2097:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:2098:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )
            // InternalGrana.g:2098:2: rule__RegularJob__LayoutOptionsAssignment_7
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__LayoutOptionsAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 

            }

            // InternalGrana.g:2101:1: ( ( rule__RegularJob__LayoutOptionsAssignment_7 )* )
            // InternalGrana.g:2102:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            {
             before(grammarAccess.getRegularJobAccess().getLayoutOptionsAssignment_7()); 
            // InternalGrana.g:2103:1: ( rule__RegularJob__LayoutOptionsAssignment_7 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalGrana.g:2103:2: rule__RegularJob__LayoutOptionsAssignment_7
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
    // InternalGrana.g:2114:1: rule__RegularJob__Group__8 : rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 ;
    public final void rule__RegularJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2118:1: ( rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9 )
            // InternalGrana.g:2119:2: rule__RegularJob__Group__8__Impl rule__RegularJob__Group__9
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
    // InternalGrana.g:2126:1: rule__RegularJob__Group__8__Impl : ( 'analyses' ) ;
    public final void rule__RegularJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2130:1: ( ( 'analyses' ) )
            // InternalGrana.g:2131:1: ( 'analyses' )
            {
            // InternalGrana.g:2131:1: ( 'analyses' )
            // InternalGrana.g:2132:1: 'analyses'
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesKeyword_8()); 
            match(input,24,FOLLOW_2); 
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
    // InternalGrana.g:2145:1: rule__RegularJob__Group__9 : rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 ;
    public final void rule__RegularJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2149:1: ( rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10 )
            // InternalGrana.g:2150:2: rule__RegularJob__Group__9__Impl rule__RegularJob__Group__10
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
    // InternalGrana.g:2157:1: rule__RegularJob__Group__9__Impl : ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) ;
    public final void rule__RegularJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2161:1: ( ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) ) )
            // InternalGrana.g:2162:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            {
            // InternalGrana.g:2162:1: ( ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* ) )
            // InternalGrana.g:2163:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) ) ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            {
            // InternalGrana.g:2163:1: ( ( rule__RegularJob__AnalysesAssignment_9 ) )
            // InternalGrana.g:2164:1: ( rule__RegularJob__AnalysesAssignment_9 )
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:2165:1: ( rule__RegularJob__AnalysesAssignment_9 )
            // InternalGrana.g:2165:2: rule__RegularJob__AnalysesAssignment_9
            {
            pushFollow(FOLLOW_3);
            rule__RegularJob__AnalysesAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 

            }

            // InternalGrana.g:2168:1: ( ( rule__RegularJob__AnalysesAssignment_9 )* )
            // InternalGrana.g:2169:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            {
             before(grammarAccess.getRegularJobAccess().getAnalysesAssignment_9()); 
            // InternalGrana.g:2170:1: ( rule__RegularJob__AnalysesAssignment_9 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==RULE_ID) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalGrana.g:2170:2: rule__RegularJob__AnalysesAssignment_9
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
    // InternalGrana.g:2181:1: rule__RegularJob__Group__10 : rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 ;
    public final void rule__RegularJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2185:1: ( rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11 )
            // InternalGrana.g:2186:2: rule__RegularJob__Group__10__Impl rule__RegularJob__Group__11
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
    // InternalGrana.g:2193:1: rule__RegularJob__Group__10__Impl : ( 'output' ) ;
    public final void rule__RegularJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2197:1: ( ( 'output' ) )
            // InternalGrana.g:2198:1: ( 'output' )
            {
            // InternalGrana.g:2198:1: ( 'output' )
            // InternalGrana.g:2199:1: 'output'
            {
             before(grammarAccess.getRegularJobAccess().getOutputKeyword_10()); 
            match(input,25,FOLLOW_2); 
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
    // InternalGrana.g:2212:1: rule__RegularJob__Group__11 : rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 ;
    public final void rule__RegularJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2216:1: ( rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12 )
            // InternalGrana.g:2217:2: rule__RegularJob__Group__11__Impl rule__RegularJob__Group__12
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
    // InternalGrana.g:2224:1: rule__RegularJob__Group__11__Impl : ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) ;
    public final void rule__RegularJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2228:1: ( ( ( rule__RegularJob__OutputTypeAssignment_11 )? ) )
            // InternalGrana.g:2229:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            {
            // InternalGrana.g:2229:1: ( ( rule__RegularJob__OutputTypeAssignment_11 )? )
            // InternalGrana.g:2230:1: ( rule__RegularJob__OutputTypeAssignment_11 )?
            {
             before(grammarAccess.getRegularJobAccess().getOutputTypeAssignment_11()); 
            // InternalGrana.g:2231:1: ( rule__RegularJob__OutputTypeAssignment_11 )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=16 && LA28_0<=17)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalGrana.g:2231:2: rule__RegularJob__OutputTypeAssignment_11
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
    // InternalGrana.g:2241:1: rule__RegularJob__Group__12 : rule__RegularJob__Group__12__Impl ;
    public final void rule__RegularJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2245:1: ( rule__RegularJob__Group__12__Impl )
            // InternalGrana.g:2246:2: rule__RegularJob__Group__12__Impl
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
    // InternalGrana.g:2252:1: rule__RegularJob__Group__12__Impl : ( ( rule__RegularJob__OutputAssignment_12 ) ) ;
    public final void rule__RegularJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2256:1: ( ( ( rule__RegularJob__OutputAssignment_12 ) ) )
            // InternalGrana.g:2257:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            {
            // InternalGrana.g:2257:1: ( ( rule__RegularJob__OutputAssignment_12 ) )
            // InternalGrana.g:2258:1: ( rule__RegularJob__OutputAssignment_12 )
            {
             before(grammarAccess.getRegularJobAccess().getOutputAssignment_12()); 
            // InternalGrana.g:2259:1: ( rule__RegularJob__OutputAssignment_12 )
            // InternalGrana.g:2259:2: rule__RegularJob__OutputAssignment_12
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
    // InternalGrana.g:2295:1: rule__CompareJob__Group__0 : rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 ;
    public final void rule__CompareJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2299:1: ( rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1 )
            // InternalGrana.g:2300:2: rule__CompareJob__Group__0__Impl rule__CompareJob__Group__1
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
    // InternalGrana.g:2307:1: rule__CompareJob__Group__0__Impl : ( 'comparejob' ) ;
    public final void rule__CompareJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2311:1: ( ( 'comparejob' ) )
            // InternalGrana.g:2312:1: ( 'comparejob' )
            {
            // InternalGrana.g:2312:1: ( 'comparejob' )
            // InternalGrana.g:2313:1: 'comparejob'
            {
             before(grammarAccess.getCompareJobAccess().getComparejobKeyword_0()); 
            match(input,26,FOLLOW_2); 
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
    // InternalGrana.g:2326:1: rule__CompareJob__Group__1 : rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 ;
    public final void rule__CompareJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2330:1: ( rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2 )
            // InternalGrana.g:2331:2: rule__CompareJob__Group__1__Impl rule__CompareJob__Group__2
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
    // InternalGrana.g:2338:1: rule__CompareJob__Group__1__Impl : ( ( rule__CompareJob__NameAssignment_1 ) ) ;
    public final void rule__CompareJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2342:1: ( ( ( rule__CompareJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2343:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2343:1: ( ( rule__CompareJob__NameAssignment_1 ) )
            // InternalGrana.g:2344:1: ( rule__CompareJob__NameAssignment_1 )
            {
             before(grammarAccess.getCompareJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2345:1: ( rule__CompareJob__NameAssignment_1 )
            // InternalGrana.g:2345:2: rule__CompareJob__NameAssignment_1
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
    // InternalGrana.g:2355:1: rule__CompareJob__Group__2 : rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 ;
    public final void rule__CompareJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2359:1: ( rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3 )
            // InternalGrana.g:2360:2: rule__CompareJob__Group__2__Impl rule__CompareJob__Group__3
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
    // InternalGrana.g:2367:1: rule__CompareJob__Group__2__Impl : ( 'resources' ) ;
    public final void rule__CompareJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2371:1: ( ( 'resources' ) )
            // InternalGrana.g:2372:1: ( 'resources' )
            {
            // InternalGrana.g:2372:1: ( 'resources' )
            // InternalGrana.g:2373:1: 'resources'
            {
             before(grammarAccess.getCompareJobAccess().getResourcesKeyword_2()); 
            match(input,22,FOLLOW_2); 
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
    // InternalGrana.g:2386:1: rule__CompareJob__Group__3 : rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 ;
    public final void rule__CompareJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2390:1: ( rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4 )
            // InternalGrana.g:2391:2: rule__CompareJob__Group__3__Impl rule__CompareJob__Group__4
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
    // InternalGrana.g:2398:1: rule__CompareJob__Group__3__Impl : ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) ;
    public final void rule__CompareJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2402:1: ( ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) ) )
            // InternalGrana.g:2403:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            {
            // InternalGrana.g:2403:1: ( ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* ) )
            // InternalGrana.g:2404:1: ( ( rule__CompareJob__ResourcesAssignment_3 ) ) ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            {
            // InternalGrana.g:2404:1: ( ( rule__CompareJob__ResourcesAssignment_3 ) )
            // InternalGrana.g:2405:1: ( rule__CompareJob__ResourcesAssignment_3 )
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:2406:1: ( rule__CompareJob__ResourcesAssignment_3 )
            // InternalGrana.g:2406:2: rule__CompareJob__ResourcesAssignment_3
            {
            pushFollow(FOLLOW_13);
            rule__CompareJob__ResourcesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 

            }

            // InternalGrana.g:2409:1: ( ( rule__CompareJob__ResourcesAssignment_3 )* )
            // InternalGrana.g:2410:1: ( rule__CompareJob__ResourcesAssignment_3 )*
            {
             before(grammarAccess.getCompareJobAccess().getResourcesAssignment_3()); 
            // InternalGrana.g:2411:1: ( rule__CompareJob__ResourcesAssignment_3 )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_STRING||LA29_0==38) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalGrana.g:2411:2: rule__CompareJob__ResourcesAssignment_3
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
    // InternalGrana.g:2422:1: rule__CompareJob__Group__4 : rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 ;
    public final void rule__CompareJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2426:1: ( rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5 )
            // InternalGrana.g:2427:2: rule__CompareJob__Group__4__Impl rule__CompareJob__Group__5
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
    // InternalGrana.g:2434:1: rule__CompareJob__Group__4__Impl : ( 'layoutoptions' ) ;
    public final void rule__CompareJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2438:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2439:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2439:1: ( 'layoutoptions' )
            // InternalGrana.g:2440:1: 'layoutoptions'
            {
             before(grammarAccess.getCompareJobAccess().getLayoutoptionsKeyword_4()); 
            match(input,23,FOLLOW_2); 
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
    // InternalGrana.g:2453:1: rule__CompareJob__Group__5 : rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 ;
    public final void rule__CompareJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2457:1: ( rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6 )
            // InternalGrana.g:2458:2: rule__CompareJob__Group__5__Impl rule__CompareJob__Group__6
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
    // InternalGrana.g:2465:1: rule__CompareJob__Group__5__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) ;
    public final void rule__CompareJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2469:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) ) )
            // InternalGrana.g:2470:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            {
            // InternalGrana.g:2470:1: ( ( rule__CompareJob__LayoutOptionsAssignment_5 ) )
            // InternalGrana.g:2471:1: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_5()); 
            // InternalGrana.g:2472:1: ( rule__CompareJob__LayoutOptionsAssignment_5 )
            // InternalGrana.g:2472:2: rule__CompareJob__LayoutOptionsAssignment_5
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
    // InternalGrana.g:2482:1: rule__CompareJob__Group__6 : rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 ;
    public final void rule__CompareJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2486:1: ( rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7 )
            // InternalGrana.g:2487:2: rule__CompareJob__Group__6__Impl rule__CompareJob__Group__7
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
    // InternalGrana.g:2494:1: rule__CompareJob__Group__6__Impl : ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) ;
    public final void rule__CompareJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2498:1: ( ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) ) )
            // InternalGrana.g:2499:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            {
            // InternalGrana.g:2499:1: ( ( rule__CompareJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2500:1: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getCompareJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2501:1: ( rule__CompareJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2501:2: rule__CompareJob__LayoutOptionsAssignment_6
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
    // InternalGrana.g:2511:1: rule__CompareJob__Group__7 : rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 ;
    public final void rule__CompareJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2515:1: ( rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8 )
            // InternalGrana.g:2516:2: rule__CompareJob__Group__7__Impl rule__CompareJob__Group__8
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
    // InternalGrana.g:2523:1: rule__CompareJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__CompareJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2527:1: ( ( 'analyses' ) )
            // InternalGrana.g:2528:1: ( 'analyses' )
            {
            // InternalGrana.g:2528:1: ( 'analyses' )
            // InternalGrana.g:2529:1: 'analyses'
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesKeyword_7()); 
            match(input,24,FOLLOW_2); 
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
    // InternalGrana.g:2542:1: rule__CompareJob__Group__8 : rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 ;
    public final void rule__CompareJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2546:1: ( rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9 )
            // InternalGrana.g:2547:2: rule__CompareJob__Group__8__Impl rule__CompareJob__Group__9
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
    // InternalGrana.g:2554:1: rule__CompareJob__Group__8__Impl : ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__CompareJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2558:1: ( ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2559:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2559:1: ( ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2560:1: ( ( rule__CompareJob__AnalysesAssignment_8 ) ) ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2560:1: ( ( rule__CompareJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2561:1: ( rule__CompareJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2562:1: ( rule__CompareJob__AnalysesAssignment_8 )
            // InternalGrana.g:2562:2: rule__CompareJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__CompareJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2565:1: ( ( rule__CompareJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2566:1: ( rule__CompareJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getCompareJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2567:1: ( rule__CompareJob__AnalysesAssignment_8 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalGrana.g:2567:2: rule__CompareJob__AnalysesAssignment_8
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
    // InternalGrana.g:2578:1: rule__CompareJob__Group__9 : rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 ;
    public final void rule__CompareJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2582:1: ( rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10 )
            // InternalGrana.g:2583:2: rule__CompareJob__Group__9__Impl rule__CompareJob__Group__10
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
    // InternalGrana.g:2590:1: rule__CompareJob__Group__9__Impl : ( 'output' ) ;
    public final void rule__CompareJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2594:1: ( ( 'output' ) )
            // InternalGrana.g:2595:1: ( 'output' )
            {
            // InternalGrana.g:2595:1: ( 'output' )
            // InternalGrana.g:2596:1: 'output'
            {
             before(grammarAccess.getCompareJobAccess().getOutputKeyword_9()); 
            match(input,25,FOLLOW_2); 
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
    // InternalGrana.g:2609:1: rule__CompareJob__Group__10 : rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 ;
    public final void rule__CompareJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2613:1: ( rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11 )
            // InternalGrana.g:2614:2: rule__CompareJob__Group__10__Impl rule__CompareJob__Group__11
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
    // InternalGrana.g:2621:1: rule__CompareJob__Group__10__Impl : ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) ;
    public final void rule__CompareJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2625:1: ( ( ( rule__CompareJob__OutputTypeAssignment_10 )? ) )
            // InternalGrana.g:2626:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            {
            // InternalGrana.g:2626:1: ( ( rule__CompareJob__OutputTypeAssignment_10 )? )
            // InternalGrana.g:2627:1: ( rule__CompareJob__OutputTypeAssignment_10 )?
            {
             before(grammarAccess.getCompareJobAccess().getOutputTypeAssignment_10()); 
            // InternalGrana.g:2628:1: ( rule__CompareJob__OutputTypeAssignment_10 )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=16 && LA31_0<=17)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalGrana.g:2628:2: rule__CompareJob__OutputTypeAssignment_10
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
    // InternalGrana.g:2638:1: rule__CompareJob__Group__11 : rule__CompareJob__Group__11__Impl ;
    public final void rule__CompareJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2642:1: ( rule__CompareJob__Group__11__Impl )
            // InternalGrana.g:2643:2: rule__CompareJob__Group__11__Impl
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
    // InternalGrana.g:2649:1: rule__CompareJob__Group__11__Impl : ( ( rule__CompareJob__OutputAssignment_11 ) ) ;
    public final void rule__CompareJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2653:1: ( ( ( rule__CompareJob__OutputAssignment_11 ) ) )
            // InternalGrana.g:2654:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            {
            // InternalGrana.g:2654:1: ( ( rule__CompareJob__OutputAssignment_11 ) )
            // InternalGrana.g:2655:1: ( rule__CompareJob__OutputAssignment_11 )
            {
             before(grammarAccess.getCompareJobAccess().getOutputAssignment_11()); 
            // InternalGrana.g:2656:1: ( rule__CompareJob__OutputAssignment_11 )
            // InternalGrana.g:2656:2: rule__CompareJob__OutputAssignment_11
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
    // InternalGrana.g:2690:1: rule__RangeJob__Group__0 : rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 ;
    public final void rule__RangeJob__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2694:1: ( rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1 )
            // InternalGrana.g:2695:2: rule__RangeJob__Group__0__Impl rule__RangeJob__Group__1
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
    // InternalGrana.g:2702:1: rule__RangeJob__Group__0__Impl : ( 'rangejob' ) ;
    public final void rule__RangeJob__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2706:1: ( ( 'rangejob' ) )
            // InternalGrana.g:2707:1: ( 'rangejob' )
            {
            // InternalGrana.g:2707:1: ( 'rangejob' )
            // InternalGrana.g:2708:1: 'rangejob'
            {
             before(grammarAccess.getRangeJobAccess().getRangejobKeyword_0()); 
            match(input,27,FOLLOW_2); 
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
    // InternalGrana.g:2721:1: rule__RangeJob__Group__1 : rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 ;
    public final void rule__RangeJob__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2725:1: ( rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2 )
            // InternalGrana.g:2726:2: rule__RangeJob__Group__1__Impl rule__RangeJob__Group__2
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
    // InternalGrana.g:2733:1: rule__RangeJob__Group__1__Impl : ( ( rule__RangeJob__NameAssignment_1 ) ) ;
    public final void rule__RangeJob__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2737:1: ( ( ( rule__RangeJob__NameAssignment_1 ) ) )
            // InternalGrana.g:2738:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            {
            // InternalGrana.g:2738:1: ( ( rule__RangeJob__NameAssignment_1 ) )
            // InternalGrana.g:2739:1: ( rule__RangeJob__NameAssignment_1 )
            {
             before(grammarAccess.getRangeJobAccess().getNameAssignment_1()); 
            // InternalGrana.g:2740:1: ( rule__RangeJob__NameAssignment_1 )
            // InternalGrana.g:2740:2: rule__RangeJob__NameAssignment_1
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
    // InternalGrana.g:2750:1: rule__RangeJob__Group__2 : rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 ;
    public final void rule__RangeJob__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2754:1: ( rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3 )
            // InternalGrana.g:2755:2: rule__RangeJob__Group__2__Impl rule__RangeJob__Group__3
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
    // InternalGrana.g:2762:1: rule__RangeJob__Group__2__Impl : ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) ;
    public final void rule__RangeJob__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2766:1: ( ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? ) )
            // InternalGrana.g:2767:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            {
            // InternalGrana.g:2767:1: ( ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )? )
            // InternalGrana.g:2768:1: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeAssignment_2()); 
            // InternalGrana.g:2769:1: ( rule__RangeJob__MeasureExecutionTimeAssignment_2 )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==64) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalGrana.g:2769:2: rule__RangeJob__MeasureExecutionTimeAssignment_2
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
    // InternalGrana.g:2779:1: rule__RangeJob__Group__3 : rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 ;
    public final void rule__RangeJob__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2783:1: ( rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4 )
            // InternalGrana.g:2784:2: rule__RangeJob__Group__3__Impl rule__RangeJob__Group__4
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
    // InternalGrana.g:2791:1: rule__RangeJob__Group__3__Impl : ( 'resources' ) ;
    public final void rule__RangeJob__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2795:1: ( ( 'resources' ) )
            // InternalGrana.g:2796:1: ( 'resources' )
            {
            // InternalGrana.g:2796:1: ( 'resources' )
            // InternalGrana.g:2797:1: 'resources'
            {
             before(grammarAccess.getRangeJobAccess().getResourcesKeyword_3()); 
            match(input,22,FOLLOW_2); 
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
    // InternalGrana.g:2810:1: rule__RangeJob__Group__4 : rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 ;
    public final void rule__RangeJob__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2814:1: ( rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5 )
            // InternalGrana.g:2815:2: rule__RangeJob__Group__4__Impl rule__RangeJob__Group__5
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
    // InternalGrana.g:2822:1: rule__RangeJob__Group__4__Impl : ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) ;
    public final void rule__RangeJob__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2826:1: ( ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) ) )
            // InternalGrana.g:2827:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            {
            // InternalGrana.g:2827:1: ( ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* ) )
            // InternalGrana.g:2828:1: ( ( rule__RangeJob__ResourcesAssignment_4 ) ) ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            {
            // InternalGrana.g:2828:1: ( ( rule__RangeJob__ResourcesAssignment_4 ) )
            // InternalGrana.g:2829:1: ( rule__RangeJob__ResourcesAssignment_4 )
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2830:1: ( rule__RangeJob__ResourcesAssignment_4 )
            // InternalGrana.g:2830:2: rule__RangeJob__ResourcesAssignment_4
            {
            pushFollow(FOLLOW_13);
            rule__RangeJob__ResourcesAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 

            }

            // InternalGrana.g:2833:1: ( ( rule__RangeJob__ResourcesAssignment_4 )* )
            // InternalGrana.g:2834:1: ( rule__RangeJob__ResourcesAssignment_4 )*
            {
             before(grammarAccess.getRangeJobAccess().getResourcesAssignment_4()); 
            // InternalGrana.g:2835:1: ( rule__RangeJob__ResourcesAssignment_4 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_STRING||LA33_0==38) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalGrana.g:2835:2: rule__RangeJob__ResourcesAssignment_4
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
    // InternalGrana.g:2846:1: rule__RangeJob__Group__5 : rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 ;
    public final void rule__RangeJob__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2850:1: ( rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6 )
            // InternalGrana.g:2851:2: rule__RangeJob__Group__5__Impl rule__RangeJob__Group__6
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
    // InternalGrana.g:2858:1: rule__RangeJob__Group__5__Impl : ( 'layoutoptions' ) ;
    public final void rule__RangeJob__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2862:1: ( ( 'layoutoptions' ) )
            // InternalGrana.g:2863:1: ( 'layoutoptions' )
            {
            // InternalGrana.g:2863:1: ( 'layoutoptions' )
            // InternalGrana.g:2864:1: 'layoutoptions'
            {
             before(grammarAccess.getRangeJobAccess().getLayoutoptionsKeyword_5()); 
            match(input,23,FOLLOW_2); 
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
    // InternalGrana.g:2877:1: rule__RangeJob__Group__6 : rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 ;
    public final void rule__RangeJob__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2881:1: ( rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7 )
            // InternalGrana.g:2882:2: rule__RangeJob__Group__6__Impl rule__RangeJob__Group__7
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
    // InternalGrana.g:2889:1: rule__RangeJob__Group__6__Impl : ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) ;
    public final void rule__RangeJob__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2893:1: ( ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) ) )
            // InternalGrana.g:2894:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            {
            // InternalGrana.g:2894:1: ( ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* ) )
            // InternalGrana.g:2895:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) ) ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            {
            // InternalGrana.g:2895:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 ) )
            // InternalGrana.g:2896:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2897:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )
            // InternalGrana.g:2897:2: rule__RangeJob__LayoutOptionsAssignment_6
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__LayoutOptionsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 

            }

            // InternalGrana.g:2900:1: ( ( rule__RangeJob__LayoutOptionsAssignment_6 )* )
            // InternalGrana.g:2901:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            {
             before(grammarAccess.getRangeJobAccess().getLayoutOptionsAssignment_6()); 
            // InternalGrana.g:2902:1: ( rule__RangeJob__LayoutOptionsAssignment_6 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==RULE_ID) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalGrana.g:2902:2: rule__RangeJob__LayoutOptionsAssignment_6
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
    // InternalGrana.g:2913:1: rule__RangeJob__Group__7 : rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 ;
    public final void rule__RangeJob__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2917:1: ( rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8 )
            // InternalGrana.g:2918:2: rule__RangeJob__Group__7__Impl rule__RangeJob__Group__8
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
    // InternalGrana.g:2925:1: rule__RangeJob__Group__7__Impl : ( 'analyses' ) ;
    public final void rule__RangeJob__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2929:1: ( ( 'analyses' ) )
            // InternalGrana.g:2930:1: ( 'analyses' )
            {
            // InternalGrana.g:2930:1: ( 'analyses' )
            // InternalGrana.g:2931:1: 'analyses'
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesKeyword_7()); 
            match(input,24,FOLLOW_2); 
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
    // InternalGrana.g:2944:1: rule__RangeJob__Group__8 : rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 ;
    public final void rule__RangeJob__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2948:1: ( rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9 )
            // InternalGrana.g:2949:2: rule__RangeJob__Group__8__Impl rule__RangeJob__Group__9
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
    // InternalGrana.g:2956:1: rule__RangeJob__Group__8__Impl : ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) ;
    public final void rule__RangeJob__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2960:1: ( ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) ) )
            // InternalGrana.g:2961:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            {
            // InternalGrana.g:2961:1: ( ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* ) )
            // InternalGrana.g:2962:1: ( ( rule__RangeJob__AnalysesAssignment_8 ) ) ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            {
            // InternalGrana.g:2962:1: ( ( rule__RangeJob__AnalysesAssignment_8 ) )
            // InternalGrana.g:2963:1: ( rule__RangeJob__AnalysesAssignment_8 )
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2964:1: ( rule__RangeJob__AnalysesAssignment_8 )
            // InternalGrana.g:2964:2: rule__RangeJob__AnalysesAssignment_8
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__AnalysesAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 

            }

            // InternalGrana.g:2967:1: ( ( rule__RangeJob__AnalysesAssignment_8 )* )
            // InternalGrana.g:2968:1: ( rule__RangeJob__AnalysesAssignment_8 )*
            {
             before(grammarAccess.getRangeJobAccess().getAnalysesAssignment_8()); 
            // InternalGrana.g:2969:1: ( rule__RangeJob__AnalysesAssignment_8 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_ID) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalGrana.g:2969:2: rule__RangeJob__AnalysesAssignment_8
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
    // InternalGrana.g:2980:1: rule__RangeJob__Group__9 : rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 ;
    public final void rule__RangeJob__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2984:1: ( rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10 )
            // InternalGrana.g:2985:2: rule__RangeJob__Group__9__Impl rule__RangeJob__Group__10
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
    // InternalGrana.g:2992:1: rule__RangeJob__Group__9__Impl : ( 'rangeoption' ) ;
    public final void rule__RangeJob__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:2996:1: ( ( 'rangeoption' ) )
            // InternalGrana.g:2997:1: ( 'rangeoption' )
            {
            // InternalGrana.g:2997:1: ( 'rangeoption' )
            // InternalGrana.g:2998:1: 'rangeoption'
            {
             before(grammarAccess.getRangeJobAccess().getRangeoptionKeyword_9()); 
            match(input,28,FOLLOW_2); 
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
    // InternalGrana.g:3011:1: rule__RangeJob__Group__10 : rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 ;
    public final void rule__RangeJob__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3015:1: ( rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11 )
            // InternalGrana.g:3016:2: rule__RangeJob__Group__10__Impl rule__RangeJob__Group__11
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
    // InternalGrana.g:3023:1: rule__RangeJob__Group__10__Impl : ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) ;
    public final void rule__RangeJob__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3027:1: ( ( ( rule__RangeJob__RangeOptionAssignment_10 ) ) )
            // InternalGrana.g:3028:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            {
            // InternalGrana.g:3028:1: ( ( rule__RangeJob__RangeOptionAssignment_10 ) )
            // InternalGrana.g:3029:1: ( rule__RangeJob__RangeOptionAssignment_10 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeOptionAssignment_10()); 
            // InternalGrana.g:3030:1: ( rule__RangeJob__RangeOptionAssignment_10 )
            // InternalGrana.g:3030:2: rule__RangeJob__RangeOptionAssignment_10
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
    // InternalGrana.g:3040:1: rule__RangeJob__Group__11 : rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 ;
    public final void rule__RangeJob__Group__11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3044:1: ( rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12 )
            // InternalGrana.g:3045:2: rule__RangeJob__Group__11__Impl rule__RangeJob__Group__12
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
    // InternalGrana.g:3052:1: rule__RangeJob__Group__11__Impl : ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) ;
    public final void rule__RangeJob__Group__11__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3056:1: ( ( ( rule__RangeJob__RangeValuesAssignment_11 ) ) )
            // InternalGrana.g:3057:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            {
            // InternalGrana.g:3057:1: ( ( rule__RangeJob__RangeValuesAssignment_11 ) )
            // InternalGrana.g:3058:1: ( rule__RangeJob__RangeValuesAssignment_11 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeValuesAssignment_11()); 
            // InternalGrana.g:3059:1: ( rule__RangeJob__RangeValuesAssignment_11 )
            // InternalGrana.g:3059:2: rule__RangeJob__RangeValuesAssignment_11
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
    // InternalGrana.g:3069:1: rule__RangeJob__Group__12 : rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 ;
    public final void rule__RangeJob__Group__12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3073:1: ( rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13 )
            // InternalGrana.g:3074:2: rule__RangeJob__Group__12__Impl rule__RangeJob__Group__13
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
    // InternalGrana.g:3081:1: rule__RangeJob__Group__12__Impl : ( ( rule__RangeJob__Alternatives_12 ) ) ;
    public final void rule__RangeJob__Group__12__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3085:1: ( ( ( rule__RangeJob__Alternatives_12 ) ) )
            // InternalGrana.g:3086:1: ( ( rule__RangeJob__Alternatives_12 ) )
            {
            // InternalGrana.g:3086:1: ( ( rule__RangeJob__Alternatives_12 ) )
            // InternalGrana.g:3087:1: ( rule__RangeJob__Alternatives_12 )
            {
             before(grammarAccess.getRangeJobAccess().getAlternatives_12()); 
            // InternalGrana.g:3088:1: ( rule__RangeJob__Alternatives_12 )
            // InternalGrana.g:3088:2: rule__RangeJob__Alternatives_12
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
    // InternalGrana.g:3098:1: rule__RangeJob__Group__13 : rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 ;
    public final void rule__RangeJob__Group__13() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3102:1: ( rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14 )
            // InternalGrana.g:3103:2: rule__RangeJob__Group__13__Impl rule__RangeJob__Group__14
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
    // InternalGrana.g:3110:1: rule__RangeJob__Group__13__Impl : ( 'output' ) ;
    public final void rule__RangeJob__Group__13__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3114:1: ( ( 'output' ) )
            // InternalGrana.g:3115:1: ( 'output' )
            {
            // InternalGrana.g:3115:1: ( 'output' )
            // InternalGrana.g:3116:1: 'output'
            {
             before(grammarAccess.getRangeJobAccess().getOutputKeyword_13()); 
            match(input,25,FOLLOW_2); 
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
    // InternalGrana.g:3129:1: rule__RangeJob__Group__14 : rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 ;
    public final void rule__RangeJob__Group__14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3133:1: ( rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15 )
            // InternalGrana.g:3134:2: rule__RangeJob__Group__14__Impl rule__RangeJob__Group__15
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
    // InternalGrana.g:3141:1: rule__RangeJob__Group__14__Impl : ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) ;
    public final void rule__RangeJob__Group__14__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3145:1: ( ( ( rule__RangeJob__OutputTypeAssignment_14 )? ) )
            // InternalGrana.g:3146:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            {
            // InternalGrana.g:3146:1: ( ( rule__RangeJob__OutputTypeAssignment_14 )? )
            // InternalGrana.g:3147:1: ( rule__RangeJob__OutputTypeAssignment_14 )?
            {
             before(grammarAccess.getRangeJobAccess().getOutputTypeAssignment_14()); 
            // InternalGrana.g:3148:1: ( rule__RangeJob__OutputTypeAssignment_14 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=16 && LA36_0<=17)) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalGrana.g:3148:2: rule__RangeJob__OutputTypeAssignment_14
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
    // InternalGrana.g:3158:1: rule__RangeJob__Group__15 : rule__RangeJob__Group__15__Impl ;
    public final void rule__RangeJob__Group__15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3162:1: ( rule__RangeJob__Group__15__Impl )
            // InternalGrana.g:3163:2: rule__RangeJob__Group__15__Impl
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
    // InternalGrana.g:3169:1: rule__RangeJob__Group__15__Impl : ( ( rule__RangeJob__OutputAssignment_15 ) ) ;
    public final void rule__RangeJob__Group__15__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3173:1: ( ( ( rule__RangeJob__OutputAssignment_15 ) ) )
            // InternalGrana.g:3174:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            {
            // InternalGrana.g:3174:1: ( ( rule__RangeJob__OutputAssignment_15 ) )
            // InternalGrana.g:3175:1: ( rule__RangeJob__OutputAssignment_15 )
            {
             before(grammarAccess.getRangeJobAccess().getOutputAssignment_15()); 
            // InternalGrana.g:3176:1: ( rule__RangeJob__OutputAssignment_15 )
            // InternalGrana.g:3176:2: rule__RangeJob__OutputAssignment_15
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
    // InternalGrana.g:3218:1: rule__RangeJob__Group_12_0__0 : rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 ;
    public final void rule__RangeJob__Group_12_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3222:1: ( rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1 )
            // InternalGrana.g:3223:2: rule__RangeJob__Group_12_0__0__Impl rule__RangeJob__Group_12_0__1
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
    // InternalGrana.g:3230:1: rule__RangeJob__Group_12_0__0__Impl : ( 'rangeanalysis' ) ;
    public final void rule__RangeJob__Group_12_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3234:1: ( ( 'rangeanalysis' ) )
            // InternalGrana.g:3235:1: ( 'rangeanalysis' )
            {
            // InternalGrana.g:3235:1: ( 'rangeanalysis' )
            // InternalGrana.g:3236:1: 'rangeanalysis'
            {
             before(grammarAccess.getRangeJobAccess().getRangeanalysisKeyword_12_0_0()); 
            match(input,29,FOLLOW_2); 
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
    // InternalGrana.g:3249:1: rule__RangeJob__Group_12_0__1 : rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 ;
    public final void rule__RangeJob__Group_12_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3253:1: ( rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2 )
            // InternalGrana.g:3254:2: rule__RangeJob__Group_12_0__1__Impl rule__RangeJob__Group_12_0__2
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
    // InternalGrana.g:3261:1: rule__RangeJob__Group_12_0__1__Impl : ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) ;
    public final void rule__RangeJob__Group_12_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3265:1: ( ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) ) )
            // InternalGrana.g:3266:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            {
            // InternalGrana.g:3266:1: ( ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 ) )
            // InternalGrana.g:3267:1: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisAssignment_12_0_1()); 
            // InternalGrana.g:3268:1: ( rule__RangeJob__RangeAnalysisAssignment_12_0_1 )
            // InternalGrana.g:3268:2: rule__RangeJob__RangeAnalysisAssignment_12_0_1
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
    // InternalGrana.g:3278:1: rule__RangeJob__Group_12_0__2 : rule__RangeJob__Group_12_0__2__Impl ;
    public final void rule__RangeJob__Group_12_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3282:1: ( rule__RangeJob__Group_12_0__2__Impl )
            // InternalGrana.g:3283:2: rule__RangeJob__Group_12_0__2__Impl
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
    // InternalGrana.g:3289:1: rule__RangeJob__Group_12_0__2__Impl : ( ( rule__RangeJob__Group_12_0_2__0 )? ) ;
    public final void rule__RangeJob__Group_12_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3293:1: ( ( ( rule__RangeJob__Group_12_0_2__0 )? ) )
            // InternalGrana.g:3294:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            {
            // InternalGrana.g:3294:1: ( ( rule__RangeJob__Group_12_0_2__0 )? )
            // InternalGrana.g:3295:1: ( rule__RangeJob__Group_12_0_2__0 )?
            {
             before(grammarAccess.getRangeJobAccess().getGroup_12_0_2()); 
            // InternalGrana.g:3296:1: ( rule__RangeJob__Group_12_0_2__0 )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==30) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalGrana.g:3296:2: rule__RangeJob__Group_12_0_2__0
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
    // InternalGrana.g:3312:1: rule__RangeJob__Group_12_0_2__0 : rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 ;
    public final void rule__RangeJob__Group_12_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3316:1: ( rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1 )
            // InternalGrana.g:3317:2: rule__RangeJob__Group_12_0_2__0__Impl rule__RangeJob__Group_12_0_2__1
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
    // InternalGrana.g:3324:1: rule__RangeJob__Group_12_0_2__0__Impl : ( 'component' ) ;
    public final void rule__RangeJob__Group_12_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3328:1: ( ( 'component' ) )
            // InternalGrana.g:3329:1: ( 'component' )
            {
            // InternalGrana.g:3329:1: ( 'component' )
            // InternalGrana.g:3330:1: 'component'
            {
             before(grammarAccess.getRangeJobAccess().getComponentKeyword_12_0_2_0()); 
            match(input,30,FOLLOW_2); 
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
    // InternalGrana.g:3343:1: rule__RangeJob__Group_12_0_2__1 : rule__RangeJob__Group_12_0_2__1__Impl ;
    public final void rule__RangeJob__Group_12_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3347:1: ( rule__RangeJob__Group_12_0_2__1__Impl )
            // InternalGrana.g:3348:2: rule__RangeJob__Group_12_0_2__1__Impl
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
    // InternalGrana.g:3354:1: rule__RangeJob__Group_12_0_2__1__Impl : ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) ;
    public final void rule__RangeJob__Group_12_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3358:1: ( ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) ) )
            // InternalGrana.g:3359:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            {
            // InternalGrana.g:3359:1: ( ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 ) )
            // InternalGrana.g:3360:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysisComponentAssignment_12_0_2_1()); 
            // InternalGrana.g:3361:1: ( rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 )
            // InternalGrana.g:3361:2: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1
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
    // InternalGrana.g:3375:1: rule__RangeJob__Group_12_1__0 : rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 ;
    public final void rule__RangeJob__Group_12_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3379:1: ( rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1 )
            // InternalGrana.g:3380:2: rule__RangeJob__Group_12_1__0__Impl rule__RangeJob__Group_12_1__1
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
    // InternalGrana.g:3387:1: rule__RangeJob__Group_12_1__0__Impl : ( 'rangeanalyses' ) ;
    public final void rule__RangeJob__Group_12_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3391:1: ( ( 'rangeanalyses' ) )
            // InternalGrana.g:3392:1: ( 'rangeanalyses' )
            {
            // InternalGrana.g:3392:1: ( 'rangeanalyses' )
            // InternalGrana.g:3393:1: 'rangeanalyses'
            {
             before(grammarAccess.getRangeJobAccess().getRangeanalysesKeyword_12_1_0()); 
            match(input,31,FOLLOW_2); 
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
    // InternalGrana.g:3406:1: rule__RangeJob__Group_12_1__1 : rule__RangeJob__Group_12_1__1__Impl ;
    public final void rule__RangeJob__Group_12_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3410:1: ( rule__RangeJob__Group_12_1__1__Impl )
            // InternalGrana.g:3411:2: rule__RangeJob__Group_12_1__1__Impl
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
    // InternalGrana.g:3417:1: rule__RangeJob__Group_12_1__1__Impl : ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) ;
    public final void rule__RangeJob__Group_12_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3421:1: ( ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) ) )
            // InternalGrana.g:3422:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            {
            // InternalGrana.g:3422:1: ( ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* ) )
            // InternalGrana.g:3423:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) ) ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            {
            // InternalGrana.g:3423:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 ) )
            // InternalGrana.g:3424:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:3425:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )
            // InternalGrana.g:3425:2: rule__RangeJob__RangeAnalysesAssignment_12_1_1
            {
            pushFollow(FOLLOW_3);
            rule__RangeJob__RangeAnalysesAssignment_12_1_1();

            state._fsp--;


            }

             after(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 

            }

            // InternalGrana.g:3428:1: ( ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )* )
            // InternalGrana.g:3429:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            {
             before(grammarAccess.getRangeJobAccess().getRangeAnalysesAssignment_12_1_1()); 
            // InternalGrana.g:3430:1: ( rule__RangeJob__RangeAnalysesAssignment_12_1_1 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==RULE_ID) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalGrana.g:3430:2: rule__RangeJob__RangeAnalysesAssignment_12_1_1
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
    // InternalGrana.g:3445:1: rule__FloatRange__Group__0 : rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 ;
    public final void rule__FloatRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3449:1: ( rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1 )
            // InternalGrana.g:3450:2: rule__FloatRange__Group__0__Impl rule__FloatRange__Group__1
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
    // InternalGrana.g:3457:1: rule__FloatRange__Group__0__Impl : ( 'floatvalues' ) ;
    public final void rule__FloatRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3461:1: ( ( 'floatvalues' ) )
            // InternalGrana.g:3462:1: ( 'floatvalues' )
            {
            // InternalGrana.g:3462:1: ( 'floatvalues' )
            // InternalGrana.g:3463:1: 'floatvalues'
            {
             before(grammarAccess.getFloatRangeAccess().getFloatvaluesKeyword_0()); 
            match(input,32,FOLLOW_2); 
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
    // InternalGrana.g:3476:1: rule__FloatRange__Group__1 : rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 ;
    public final void rule__FloatRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3480:1: ( rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2 )
            // InternalGrana.g:3481:2: rule__FloatRange__Group__1__Impl rule__FloatRange__Group__2
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
    // InternalGrana.g:3488:1: rule__FloatRange__Group__1__Impl : ( ( rule__FloatRange__ValuesAssignment_1 ) ) ;
    public final void rule__FloatRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3492:1: ( ( ( rule__FloatRange__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3493:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3493:1: ( ( rule__FloatRange__ValuesAssignment_1 ) )
            // InternalGrana.g:3494:1: ( rule__FloatRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3495:1: ( rule__FloatRange__ValuesAssignment_1 )
            // InternalGrana.g:3495:2: rule__FloatRange__ValuesAssignment_1
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
    // InternalGrana.g:3505:1: rule__FloatRange__Group__2 : rule__FloatRange__Group__2__Impl ;
    public final void rule__FloatRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3509:1: ( rule__FloatRange__Group__2__Impl )
            // InternalGrana.g:3510:2: rule__FloatRange__Group__2__Impl
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
    // InternalGrana.g:3516:1: rule__FloatRange__Group__2__Impl : ( ( rule__FloatRange__Group_2__0 )* ) ;
    public final void rule__FloatRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3520:1: ( ( ( rule__FloatRange__Group_2__0 )* ) )
            // InternalGrana.g:3521:1: ( ( rule__FloatRange__Group_2__0 )* )
            {
            // InternalGrana.g:3521:1: ( ( rule__FloatRange__Group_2__0 )* )
            // InternalGrana.g:3522:1: ( rule__FloatRange__Group_2__0 )*
            {
             before(grammarAccess.getFloatRangeAccess().getGroup_2()); 
            // InternalGrana.g:3523:1: ( rule__FloatRange__Group_2__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==33) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalGrana.g:3523:2: rule__FloatRange__Group_2__0
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
    // InternalGrana.g:3539:1: rule__FloatRange__Group_2__0 : rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 ;
    public final void rule__FloatRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3543:1: ( rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1 )
            // InternalGrana.g:3544:2: rule__FloatRange__Group_2__0__Impl rule__FloatRange__Group_2__1
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
    // InternalGrana.g:3551:1: rule__FloatRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__FloatRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3555:1: ( ( ',' ) )
            // InternalGrana.g:3556:1: ( ',' )
            {
            // InternalGrana.g:3556:1: ( ',' )
            // InternalGrana.g:3557:1: ','
            {
             before(grammarAccess.getFloatRangeAccess().getCommaKeyword_2_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:3570:1: rule__FloatRange__Group_2__1 : rule__FloatRange__Group_2__1__Impl ;
    public final void rule__FloatRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3574:1: ( rule__FloatRange__Group_2__1__Impl )
            // InternalGrana.g:3575:2: rule__FloatRange__Group_2__1__Impl
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
    // InternalGrana.g:3581:1: rule__FloatRange__Group_2__1__Impl : ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__FloatRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3585:1: ( ( ( rule__FloatRange__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3586:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3586:1: ( ( rule__FloatRange__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3587:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getFloatRangeAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3588:1: ( rule__FloatRange__ValuesAssignment_2_1 )
            // InternalGrana.g:3588:2: rule__FloatRange__ValuesAssignment_2_1
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
    // InternalGrana.g:3602:1: rule__IntRangeValues__Group__0 : rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 ;
    public final void rule__IntRangeValues__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3606:1: ( rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1 )
            // InternalGrana.g:3607:2: rule__IntRangeValues__Group__0__Impl rule__IntRangeValues__Group__1
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
    // InternalGrana.g:3614:1: rule__IntRangeValues__Group__0__Impl : ( 'intvalues' ) ;
    public final void rule__IntRangeValues__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3618:1: ( ( 'intvalues' ) )
            // InternalGrana.g:3619:1: ( 'intvalues' )
            {
            // InternalGrana.g:3619:1: ( 'intvalues' )
            // InternalGrana.g:3620:1: 'intvalues'
            {
             before(grammarAccess.getIntRangeValuesAccess().getIntvaluesKeyword_0()); 
            match(input,34,FOLLOW_2); 
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
    // InternalGrana.g:3633:1: rule__IntRangeValues__Group__1 : rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 ;
    public final void rule__IntRangeValues__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3637:1: ( rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2 )
            // InternalGrana.g:3638:2: rule__IntRangeValues__Group__1__Impl rule__IntRangeValues__Group__2
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
    // InternalGrana.g:3645:1: rule__IntRangeValues__Group__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) ;
    public final void rule__IntRangeValues__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3649:1: ( ( ( rule__IntRangeValues__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3650:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3650:1: ( ( rule__IntRangeValues__ValuesAssignment_1 ) )
            // InternalGrana.g:3651:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3652:1: ( rule__IntRangeValues__ValuesAssignment_1 )
            // InternalGrana.g:3652:2: rule__IntRangeValues__ValuesAssignment_1
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
    // InternalGrana.g:3662:1: rule__IntRangeValues__Group__2 : rule__IntRangeValues__Group__2__Impl ;
    public final void rule__IntRangeValues__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3666:1: ( rule__IntRangeValues__Group__2__Impl )
            // InternalGrana.g:3667:2: rule__IntRangeValues__Group__2__Impl
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
    // InternalGrana.g:3673:1: rule__IntRangeValues__Group__2__Impl : ( ( rule__IntRangeValues__Group_2__0 )* ) ;
    public final void rule__IntRangeValues__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3677:1: ( ( ( rule__IntRangeValues__Group_2__0 )* ) )
            // InternalGrana.g:3678:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            {
            // InternalGrana.g:3678:1: ( ( rule__IntRangeValues__Group_2__0 )* )
            // InternalGrana.g:3679:1: ( rule__IntRangeValues__Group_2__0 )*
            {
             before(grammarAccess.getIntRangeValuesAccess().getGroup_2()); 
            // InternalGrana.g:3680:1: ( rule__IntRangeValues__Group_2__0 )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==33) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalGrana.g:3680:2: rule__IntRangeValues__Group_2__0
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
    // InternalGrana.g:3696:1: rule__IntRangeValues__Group_2__0 : rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 ;
    public final void rule__IntRangeValues__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3700:1: ( rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1 )
            // InternalGrana.g:3701:2: rule__IntRangeValues__Group_2__0__Impl rule__IntRangeValues__Group_2__1
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
    // InternalGrana.g:3708:1: rule__IntRangeValues__Group_2__0__Impl : ( ',' ) ;
    public final void rule__IntRangeValues__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3712:1: ( ( ',' ) )
            // InternalGrana.g:3713:1: ( ',' )
            {
            // InternalGrana.g:3713:1: ( ',' )
            // InternalGrana.g:3714:1: ','
            {
             before(grammarAccess.getIntRangeValuesAccess().getCommaKeyword_2_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:3727:1: rule__IntRangeValues__Group_2__1 : rule__IntRangeValues__Group_2__1__Impl ;
    public final void rule__IntRangeValues__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3731:1: ( rule__IntRangeValues__Group_2__1__Impl )
            // InternalGrana.g:3732:2: rule__IntRangeValues__Group_2__1__Impl
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
    // InternalGrana.g:3738:1: rule__IntRangeValues__Group_2__1__Impl : ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) ;
    public final void rule__IntRangeValues__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3742:1: ( ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:3743:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:3743:1: ( ( rule__IntRangeValues__ValuesAssignment_2_1 ) )
            // InternalGrana.g:3744:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getIntRangeValuesAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:3745:1: ( rule__IntRangeValues__ValuesAssignment_2_1 )
            // InternalGrana.g:3745:2: rule__IntRangeValues__ValuesAssignment_2_1
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
    // InternalGrana.g:3759:1: rule__IntRangeRange__Group__0 : rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 ;
    public final void rule__IntRangeRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3763:1: ( rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1 )
            // InternalGrana.g:3764:2: rule__IntRangeRange__Group__0__Impl rule__IntRangeRange__Group__1
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
    // InternalGrana.g:3771:1: rule__IntRangeRange__Group__0__Impl : ( 'intrange' ) ;
    public final void rule__IntRangeRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3775:1: ( ( 'intrange' ) )
            // InternalGrana.g:3776:1: ( 'intrange' )
            {
            // InternalGrana.g:3776:1: ( 'intrange' )
            // InternalGrana.g:3777:1: 'intrange'
            {
             before(grammarAccess.getIntRangeRangeAccess().getIntrangeKeyword_0()); 
            match(input,35,FOLLOW_2); 
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
    // InternalGrana.g:3790:1: rule__IntRangeRange__Group__1 : rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 ;
    public final void rule__IntRangeRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3794:1: ( rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2 )
            // InternalGrana.g:3795:2: rule__IntRangeRange__Group__1__Impl rule__IntRangeRange__Group__2
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
    // InternalGrana.g:3802:1: rule__IntRangeRange__Group__1__Impl : ( ( rule__IntRangeRange__StartAssignment_1 ) ) ;
    public final void rule__IntRangeRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3806:1: ( ( ( rule__IntRangeRange__StartAssignment_1 ) ) )
            // InternalGrana.g:3807:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            {
            // InternalGrana.g:3807:1: ( ( rule__IntRangeRange__StartAssignment_1 ) )
            // InternalGrana.g:3808:1: ( rule__IntRangeRange__StartAssignment_1 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getStartAssignment_1()); 
            // InternalGrana.g:3809:1: ( rule__IntRangeRange__StartAssignment_1 )
            // InternalGrana.g:3809:2: rule__IntRangeRange__StartAssignment_1
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
    // InternalGrana.g:3819:1: rule__IntRangeRange__Group__2 : rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 ;
    public final void rule__IntRangeRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3823:1: ( rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3 )
            // InternalGrana.g:3824:2: rule__IntRangeRange__Group__2__Impl rule__IntRangeRange__Group__3
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
    // InternalGrana.g:3831:1: rule__IntRangeRange__Group__2__Impl : ( 'to' ) ;
    public final void rule__IntRangeRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3835:1: ( ( 'to' ) )
            // InternalGrana.g:3836:1: ( 'to' )
            {
            // InternalGrana.g:3836:1: ( 'to' )
            // InternalGrana.g:3837:1: 'to'
            {
             before(grammarAccess.getIntRangeRangeAccess().getToKeyword_2()); 
            match(input,36,FOLLOW_2); 
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
    // InternalGrana.g:3850:1: rule__IntRangeRange__Group__3 : rule__IntRangeRange__Group__3__Impl ;
    public final void rule__IntRangeRange__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3854:1: ( rule__IntRangeRange__Group__3__Impl )
            // InternalGrana.g:3855:2: rule__IntRangeRange__Group__3__Impl
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
    // InternalGrana.g:3861:1: rule__IntRangeRange__Group__3__Impl : ( ( rule__IntRangeRange__EndAssignment_3 ) ) ;
    public final void rule__IntRangeRange__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3865:1: ( ( ( rule__IntRangeRange__EndAssignment_3 ) ) )
            // InternalGrana.g:3866:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            {
            // InternalGrana.g:3866:1: ( ( rule__IntRangeRange__EndAssignment_3 ) )
            // InternalGrana.g:3867:1: ( rule__IntRangeRange__EndAssignment_3 )
            {
             before(grammarAccess.getIntRangeRangeAccess().getEndAssignment_3()); 
            // InternalGrana.g:3868:1: ( rule__IntRangeRange__EndAssignment_3 )
            // InternalGrana.g:3868:2: rule__IntRangeRange__EndAssignment_3
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
    // InternalGrana.g:3886:1: rule__EnumRange__Group__0 : rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1 ;
    public final void rule__EnumRange__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3890:1: ( rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1 )
            // InternalGrana.g:3891:2: rule__EnumRange__Group__0__Impl rule__EnumRange__Group__1
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
    // InternalGrana.g:3898:1: rule__EnumRange__Group__0__Impl : ( 'enumvalues' ) ;
    public final void rule__EnumRange__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3902:1: ( ( 'enumvalues' ) )
            // InternalGrana.g:3903:1: ( 'enumvalues' )
            {
            // InternalGrana.g:3903:1: ( 'enumvalues' )
            // InternalGrana.g:3904:1: 'enumvalues'
            {
             before(grammarAccess.getEnumRangeAccess().getEnumvaluesKeyword_0()); 
            match(input,37,FOLLOW_2); 
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
    // InternalGrana.g:3917:1: rule__EnumRange__Group__1 : rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2 ;
    public final void rule__EnumRange__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3921:1: ( rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2 )
            // InternalGrana.g:3922:2: rule__EnumRange__Group__1__Impl rule__EnumRange__Group__2
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
    // InternalGrana.g:3929:1: rule__EnumRange__Group__1__Impl : ( ( rule__EnumRange__ValuesAssignment_1 ) ) ;
    public final void rule__EnumRange__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3933:1: ( ( ( rule__EnumRange__ValuesAssignment_1 ) ) )
            // InternalGrana.g:3934:1: ( ( rule__EnumRange__ValuesAssignment_1 ) )
            {
            // InternalGrana.g:3934:1: ( ( rule__EnumRange__ValuesAssignment_1 ) )
            // InternalGrana.g:3935:1: ( rule__EnumRange__ValuesAssignment_1 )
            {
             before(grammarAccess.getEnumRangeAccess().getValuesAssignment_1()); 
            // InternalGrana.g:3936:1: ( rule__EnumRange__ValuesAssignment_1 )
            // InternalGrana.g:3936:2: rule__EnumRange__ValuesAssignment_1
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
    // InternalGrana.g:3946:1: rule__EnumRange__Group__2 : rule__EnumRange__Group__2__Impl ;
    public final void rule__EnumRange__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3950:1: ( rule__EnumRange__Group__2__Impl )
            // InternalGrana.g:3951:2: rule__EnumRange__Group__2__Impl
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
    // InternalGrana.g:3957:1: rule__EnumRange__Group__2__Impl : ( ( rule__EnumRange__Group_2__0 )* ) ;
    public final void rule__EnumRange__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3961:1: ( ( ( rule__EnumRange__Group_2__0 )* ) )
            // InternalGrana.g:3962:1: ( ( rule__EnumRange__Group_2__0 )* )
            {
            // InternalGrana.g:3962:1: ( ( rule__EnumRange__Group_2__0 )* )
            // InternalGrana.g:3963:1: ( rule__EnumRange__Group_2__0 )*
            {
             before(grammarAccess.getEnumRangeAccess().getGroup_2()); 
            // InternalGrana.g:3964:1: ( rule__EnumRange__Group_2__0 )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==33) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalGrana.g:3964:2: rule__EnumRange__Group_2__0
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
    // InternalGrana.g:3980:1: rule__EnumRange__Group_2__0 : rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1 ;
    public final void rule__EnumRange__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3984:1: ( rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1 )
            // InternalGrana.g:3985:2: rule__EnumRange__Group_2__0__Impl rule__EnumRange__Group_2__1
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
    // InternalGrana.g:3992:1: rule__EnumRange__Group_2__0__Impl : ( ',' ) ;
    public final void rule__EnumRange__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:3996:1: ( ( ',' ) )
            // InternalGrana.g:3997:1: ( ',' )
            {
            // InternalGrana.g:3997:1: ( ',' )
            // InternalGrana.g:3998:1: ','
            {
             before(grammarAccess.getEnumRangeAccess().getCommaKeyword_2_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:4011:1: rule__EnumRange__Group_2__1 : rule__EnumRange__Group_2__1__Impl ;
    public final void rule__EnumRange__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4015:1: ( rule__EnumRange__Group_2__1__Impl )
            // InternalGrana.g:4016:2: rule__EnumRange__Group_2__1__Impl
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
    // InternalGrana.g:4022:1: rule__EnumRange__Group_2__1__Impl : ( ( rule__EnumRange__ValuesAssignment_2_1 ) ) ;
    public final void rule__EnumRange__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4026:1: ( ( ( rule__EnumRange__ValuesAssignment_2_1 ) ) )
            // InternalGrana.g:4027:1: ( ( rule__EnumRange__ValuesAssignment_2_1 ) )
            {
            // InternalGrana.g:4027:1: ( ( rule__EnumRange__ValuesAssignment_2_1 ) )
            // InternalGrana.g:4028:1: ( rule__EnumRange__ValuesAssignment_2_1 )
            {
             before(grammarAccess.getEnumRangeAccess().getValuesAssignment_2_1()); 
            // InternalGrana.g:4029:1: ( rule__EnumRange__ValuesAssignment_2_1 )
            // InternalGrana.g:4029:2: rule__EnumRange__ValuesAssignment_2_1
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
    // InternalGrana.g:4043:1: rule__ResourceReference__Group__0 : rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 ;
    public final void rule__ResourceReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4047:1: ( rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1 )
            // InternalGrana.g:4048:2: rule__ResourceReference__Group__0__Impl rule__ResourceReference__Group__1
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
    // InternalGrana.g:4055:1: rule__ResourceReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__ResourceReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4059:1: ( ( 'ref' ) )
            // InternalGrana.g:4060:1: ( 'ref' )
            {
            // InternalGrana.g:4060:1: ( 'ref' )
            // InternalGrana.g:4061:1: 'ref'
            {
             before(grammarAccess.getResourceReferenceAccess().getRefKeyword_0()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:4074:1: rule__ResourceReference__Group__1 : rule__ResourceReference__Group__1__Impl ;
    public final void rule__ResourceReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4078:1: ( rule__ResourceReference__Group__1__Impl )
            // InternalGrana.g:4079:2: rule__ResourceReference__Group__1__Impl
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
    // InternalGrana.g:4085:1: rule__ResourceReference__Group__1__Impl : ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) ;
    public final void rule__ResourceReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4089:1: ( ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) ) )
            // InternalGrana.g:4090:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            {
            // InternalGrana.g:4090:1: ( ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* ) )
            // InternalGrana.g:4091:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) ) ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            {
            // InternalGrana.g:4091:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 ) )
            // InternalGrana.g:4092:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:4093:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )
            // InternalGrana.g:4093:2: rule__ResourceReference__ResourceRefsAssignment_1
            {
            pushFollow(FOLLOW_3);
            rule__ResourceReference__ResourceRefsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 

            }

            // InternalGrana.g:4096:1: ( ( rule__ResourceReference__ResourceRefsAssignment_1 )* )
            // InternalGrana.g:4097:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsAssignment_1()); 
            // InternalGrana.g:4098:1: ( rule__ResourceReference__ResourceRefsAssignment_1 )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==RULE_ID) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalGrana.g:4098:2: rule__ResourceReference__ResourceRefsAssignment_1
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
    // InternalGrana.g:4113:1: rule__GlobalResourceRef__Group__0 : rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 ;
    public final void rule__GlobalResourceRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4117:1: ( rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1 )
            // InternalGrana.g:4118:2: rule__GlobalResourceRef__Group__0__Impl rule__GlobalResourceRef__Group__1
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
    // InternalGrana.g:4125:1: rule__GlobalResourceRef__Group__0__Impl : ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalResourceRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4129:1: ( ( ( rule__GlobalResourceRef__NameAssignment_0 ) ) )
            // InternalGrana.g:4130:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:4130:1: ( ( rule__GlobalResourceRef__NameAssignment_0 ) )
            // InternalGrana.g:4131:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:4132:1: ( rule__GlobalResourceRef__NameAssignment_0 )
            // InternalGrana.g:4132:2: rule__GlobalResourceRef__NameAssignment_0
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
    // InternalGrana.g:4142:1: rule__GlobalResourceRef__Group__1 : rule__GlobalResourceRef__Group__1__Impl ;
    public final void rule__GlobalResourceRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4146:1: ( rule__GlobalResourceRef__Group__1__Impl )
            // InternalGrana.g:4147:2: rule__GlobalResourceRef__Group__1__Impl
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
    // InternalGrana.g:4153:1: rule__GlobalResourceRef__Group__1__Impl : ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) ;
    public final void rule__GlobalResourceRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4157:1: ( ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) ) )
            // InternalGrana.g:4158:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            {
            // InternalGrana.g:4158:1: ( ( rule__GlobalResourceRef__ResourcesAssignment_1 ) )
            // InternalGrana.g:4159:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            {
             before(grammarAccess.getGlobalResourceRefAccess().getResourcesAssignment_1()); 
            // InternalGrana.g:4160:1: ( rule__GlobalResourceRef__ResourcesAssignment_1 )
            // InternalGrana.g:4160:2: rule__GlobalResourceRef__ResourcesAssignment_1
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
    // InternalGrana.g:4174:1: rule__LocalResource__Group__0 : rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 ;
    public final void rule__LocalResource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4178:1: ( rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1 )
            // InternalGrana.g:4179:2: rule__LocalResource__Group__0__Impl rule__LocalResource__Group__1
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
    // InternalGrana.g:4186:1: rule__LocalResource__Group__0__Impl : ( ( rule__LocalResource__PathAssignment_0 ) ) ;
    public final void rule__LocalResource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4190:1: ( ( ( rule__LocalResource__PathAssignment_0 ) ) )
            // InternalGrana.g:4191:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            {
            // InternalGrana.g:4191:1: ( ( rule__LocalResource__PathAssignment_0 ) )
            // InternalGrana.g:4192:1: ( rule__LocalResource__PathAssignment_0 )
            {
             before(grammarAccess.getLocalResourceAccess().getPathAssignment_0()); 
            // InternalGrana.g:4193:1: ( rule__LocalResource__PathAssignment_0 )
            // InternalGrana.g:4193:2: rule__LocalResource__PathAssignment_0
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
    // InternalGrana.g:4203:1: rule__LocalResource__Group__1 : rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2 ;
    public final void rule__LocalResource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4207:1: ( rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2 )
            // InternalGrana.g:4208:2: rule__LocalResource__Group__1__Impl rule__LocalResource__Group__2
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
    // InternalGrana.g:4215:1: rule__LocalResource__Group__1__Impl : ( ( rule__LocalResource__Group_1__0 ) ) ;
    public final void rule__LocalResource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4219:1: ( ( ( rule__LocalResource__Group_1__0 ) ) )
            // InternalGrana.g:4220:1: ( ( rule__LocalResource__Group_1__0 ) )
            {
            // InternalGrana.g:4220:1: ( ( rule__LocalResource__Group_1__0 ) )
            // InternalGrana.g:4221:1: ( rule__LocalResource__Group_1__0 )
            {
             before(grammarAccess.getLocalResourceAccess().getGroup_1()); 
            // InternalGrana.g:4222:1: ( rule__LocalResource__Group_1__0 )
            // InternalGrana.g:4222:2: rule__LocalResource__Group_1__0
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
    // InternalGrana.g:4232:1: rule__LocalResource__Group__2 : rule__LocalResource__Group__2__Impl ;
    public final void rule__LocalResource__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4236:1: ( rule__LocalResource__Group__2__Impl )
            // InternalGrana.g:4237:2: rule__LocalResource__Group__2__Impl
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
    // InternalGrana.g:4243:1: rule__LocalResource__Group__2__Impl : ( ( rule__LocalResource__RecurseAssignment_2 )? ) ;
    public final void rule__LocalResource__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4247:1: ( ( ( rule__LocalResource__RecurseAssignment_2 )? ) )
            // InternalGrana.g:4248:1: ( ( rule__LocalResource__RecurseAssignment_2 )? )
            {
            // InternalGrana.g:4248:1: ( ( rule__LocalResource__RecurseAssignment_2 )? )
            // InternalGrana.g:4249:1: ( rule__LocalResource__RecurseAssignment_2 )?
            {
             before(grammarAccess.getLocalResourceAccess().getRecurseAssignment_2()); 
            // InternalGrana.g:4250:1: ( rule__LocalResource__RecurseAssignment_2 )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==65) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalGrana.g:4250:2: rule__LocalResource__RecurseAssignment_2
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
    // InternalGrana.g:4266:1: rule__LocalResource__Group_1__0 : rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 ;
    public final void rule__LocalResource__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4270:1: ( rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1 )
            // InternalGrana.g:4271:2: rule__LocalResource__Group_1__0__Impl rule__LocalResource__Group_1__1
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
    // InternalGrana.g:4278:1: rule__LocalResource__Group_1__0__Impl : ( 'filter' ) ;
    public final void rule__LocalResource__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4282:1: ( ( 'filter' ) )
            // InternalGrana.g:4283:1: ( 'filter' )
            {
            // InternalGrana.g:4283:1: ( 'filter' )
            // InternalGrana.g:4284:1: 'filter'
            {
             before(grammarAccess.getLocalResourceAccess().getFilterKeyword_1_0()); 
            match(input,39,FOLLOW_2); 
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
    // InternalGrana.g:4297:1: rule__LocalResource__Group_1__1 : rule__LocalResource__Group_1__1__Impl ;
    public final void rule__LocalResource__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4301:1: ( rule__LocalResource__Group_1__1__Impl )
            // InternalGrana.g:4302:2: rule__LocalResource__Group_1__1__Impl
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
    // InternalGrana.g:4308:1: rule__LocalResource__Group_1__1__Impl : ( ( rule__LocalResource__FilterAssignment_1_1 ) ) ;
    public final void rule__LocalResource__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4312:1: ( ( ( rule__LocalResource__FilterAssignment_1_1 ) ) )
            // InternalGrana.g:4313:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            {
            // InternalGrana.g:4313:1: ( ( rule__LocalResource__FilterAssignment_1_1 ) )
            // InternalGrana.g:4314:1: ( rule__LocalResource__FilterAssignment_1_1 )
            {
             before(grammarAccess.getLocalResourceAccess().getFilterAssignment_1_1()); 
            // InternalGrana.g:4315:1: ( rule__LocalResource__FilterAssignment_1_1 )
            // InternalGrana.g:4315:2: rule__LocalResource__FilterAssignment_1_1
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
    // InternalGrana.g:4329:1: rule__GlobalOutputRef__Group__0 : rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 ;
    public final void rule__GlobalOutputRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4333:1: ( rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1 )
            // InternalGrana.g:4334:2: rule__GlobalOutputRef__Group__0__Impl rule__GlobalOutputRef__Group__1
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
    // InternalGrana.g:4341:1: rule__GlobalOutputRef__Group__0__Impl : ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) ;
    public final void rule__GlobalOutputRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4345:1: ( ( ( rule__GlobalOutputRef__NameAssignment_0 ) ) )
            // InternalGrana.g:4346:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            {
            // InternalGrana.g:4346:1: ( ( rule__GlobalOutputRef__NameAssignment_0 ) )
            // InternalGrana.g:4347:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getNameAssignment_0()); 
            // InternalGrana.g:4348:1: ( rule__GlobalOutputRef__NameAssignment_0 )
            // InternalGrana.g:4348:2: rule__GlobalOutputRef__NameAssignment_0
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
    // InternalGrana.g:4358:1: rule__GlobalOutputRef__Group__1 : rule__GlobalOutputRef__Group__1__Impl ;
    public final void rule__GlobalOutputRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4362:1: ( rule__GlobalOutputRef__Group__1__Impl )
            // InternalGrana.g:4363:2: rule__GlobalOutputRef__Group__1__Impl
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
    // InternalGrana.g:4369:1: rule__GlobalOutputRef__Group__1__Impl : ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) ;
    public final void rule__GlobalOutputRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4373:1: ( ( ( rule__GlobalOutputRef__OutputAssignment_1 ) ) )
            // InternalGrana.g:4374:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            {
            // InternalGrana.g:4374:1: ( ( rule__GlobalOutputRef__OutputAssignment_1 ) )
            // InternalGrana.g:4375:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            {
             before(grammarAccess.getGlobalOutputRefAccess().getOutputAssignment_1()); 
            // InternalGrana.g:4376:1: ( rule__GlobalOutputRef__OutputAssignment_1 )
            // InternalGrana.g:4376:2: rule__GlobalOutputRef__OutputAssignment_1
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
    // InternalGrana.g:4390:1: rule__OutputReference__Group__0 : rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 ;
    public final void rule__OutputReference__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4394:1: ( rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1 )
            // InternalGrana.g:4395:2: rule__OutputReference__Group__0__Impl rule__OutputReference__Group__1
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
    // InternalGrana.g:4402:1: rule__OutputReference__Group__0__Impl : ( 'ref' ) ;
    public final void rule__OutputReference__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4406:1: ( ( 'ref' ) )
            // InternalGrana.g:4407:1: ( 'ref' )
            {
            // InternalGrana.g:4407:1: ( 'ref' )
            // InternalGrana.g:4408:1: 'ref'
            {
             before(grammarAccess.getOutputReferenceAccess().getRefKeyword_0()); 
            match(input,38,FOLLOW_2); 
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
    // InternalGrana.g:4421:1: rule__OutputReference__Group__1 : rule__OutputReference__Group__1__Impl ;
    public final void rule__OutputReference__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4425:1: ( rule__OutputReference__Group__1__Impl )
            // InternalGrana.g:4426:2: rule__OutputReference__Group__1__Impl
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
    // InternalGrana.g:4432:1: rule__OutputReference__Group__1__Impl : ( ( rule__OutputReference__OutputRefAssignment_1 ) ) ;
    public final void rule__OutputReference__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4436:1: ( ( ( rule__OutputReference__OutputRefAssignment_1 ) ) )
            // InternalGrana.g:4437:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            {
            // InternalGrana.g:4437:1: ( ( rule__OutputReference__OutputRefAssignment_1 ) )
            // InternalGrana.g:4438:1: ( rule__OutputReference__OutputRefAssignment_1 )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefAssignment_1()); 
            // InternalGrana.g:4439:1: ( rule__OutputReference__OutputRefAssignment_1 )
            // InternalGrana.g:4439:2: rule__OutputReference__OutputRefAssignment_1
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
    // InternalGrana.g:4453:1: rule__LayoutConfig__Group__0 : rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1 ;
    public final void rule__LayoutConfig__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4457:1: ( rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1 )
            // InternalGrana.g:4458:2: rule__LayoutConfig__Group__0__Impl rule__LayoutConfig__Group__1
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
    // InternalGrana.g:4465:1: rule__LayoutConfig__Group__0__Impl : ( ( rule__LayoutConfig__IdentifierAssignment_0 ) ) ;
    public final void rule__LayoutConfig__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4469:1: ( ( ( rule__LayoutConfig__IdentifierAssignment_0 ) ) )
            // InternalGrana.g:4470:1: ( ( rule__LayoutConfig__IdentifierAssignment_0 ) )
            {
            // InternalGrana.g:4470:1: ( ( rule__LayoutConfig__IdentifierAssignment_0 ) )
            // InternalGrana.g:4471:1: ( rule__LayoutConfig__IdentifierAssignment_0 )
            {
             before(grammarAccess.getLayoutConfigAccess().getIdentifierAssignment_0()); 
            // InternalGrana.g:4472:1: ( rule__LayoutConfig__IdentifierAssignment_0 )
            // InternalGrana.g:4472:2: rule__LayoutConfig__IdentifierAssignment_0
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
    // InternalGrana.g:4482:1: rule__LayoutConfig__Group__1 : rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2 ;
    public final void rule__LayoutConfig__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4486:1: ( rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2 )
            // InternalGrana.g:4487:2: rule__LayoutConfig__Group__1__Impl rule__LayoutConfig__Group__2
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
    // InternalGrana.g:4494:1: rule__LayoutConfig__Group__1__Impl : ( '{' ) ;
    public final void rule__LayoutConfig__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4498:1: ( ( '{' ) )
            // InternalGrana.g:4499:1: ( '{' )
            {
            // InternalGrana.g:4499:1: ( '{' )
            // InternalGrana.g:4500:1: '{'
            {
             before(grammarAccess.getLayoutConfigAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,40,FOLLOW_2); 
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
    // InternalGrana.g:4513:1: rule__LayoutConfig__Group__2 : rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3 ;
    public final void rule__LayoutConfig__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4517:1: ( rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3 )
            // InternalGrana.g:4518:2: rule__LayoutConfig__Group__2__Impl rule__LayoutConfig__Group__3
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
    // InternalGrana.g:4525:1: rule__LayoutConfig__Group__2__Impl : ( ( rule__LayoutConfig__PropertiesAssignment_2 )* ) ;
    public final void rule__LayoutConfig__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4529:1: ( ( ( rule__LayoutConfig__PropertiesAssignment_2 )* ) )
            // InternalGrana.g:4530:1: ( ( rule__LayoutConfig__PropertiesAssignment_2 )* )
            {
            // InternalGrana.g:4530:1: ( ( rule__LayoutConfig__PropertiesAssignment_2 )* )
            // InternalGrana.g:4531:1: ( rule__LayoutConfig__PropertiesAssignment_2 )*
            {
             before(grammarAccess.getLayoutConfigAccess().getPropertiesAssignment_2()); 
            // InternalGrana.g:4532:1: ( rule__LayoutConfig__PropertiesAssignment_2 )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==RULE_ID) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalGrana.g:4532:2: rule__LayoutConfig__PropertiesAssignment_2
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
    // InternalGrana.g:4542:1: rule__LayoutConfig__Group__3 : rule__LayoutConfig__Group__3__Impl ;
    public final void rule__LayoutConfig__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4546:1: ( rule__LayoutConfig__Group__3__Impl )
            // InternalGrana.g:4547:2: rule__LayoutConfig__Group__3__Impl
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
    // InternalGrana.g:4553:1: rule__LayoutConfig__Group__3__Impl : ( '}' ) ;
    public final void rule__LayoutConfig__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4557:1: ( ( '}' ) )
            // InternalGrana.g:4558:1: ( '}' )
            {
            // InternalGrana.g:4558:1: ( '}' )
            // InternalGrana.g:4559:1: '}'
            {
             before(grammarAccess.getLayoutConfigAccess().getRightCurlyBracketKeyword_3()); 
            match(input,41,FOLLOW_2); 
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
    // InternalGrana.g:4582:1: rule__ElkNode__Group__0 : rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 ;
    public final void rule__ElkNode__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4586:1: ( rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1 )
            // InternalGrana.g:4587:2: rule__ElkNode__Group__0__Impl rule__ElkNode__Group__1
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
    // InternalGrana.g:4594:1: rule__ElkNode__Group__0__Impl : ( 'node' ) ;
    public final void rule__ElkNode__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4598:1: ( ( 'node' ) )
            // InternalGrana.g:4599:1: ( 'node' )
            {
            // InternalGrana.g:4599:1: ( 'node' )
            // InternalGrana.g:4600:1: 'node'
            {
             before(grammarAccess.getElkNodeAccess().getNodeKeyword_0()); 
            match(input,42,FOLLOW_2); 
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
    // InternalGrana.g:4613:1: rule__ElkNode__Group__1 : rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 ;
    public final void rule__ElkNode__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4617:1: ( rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2 )
            // InternalGrana.g:4618:2: rule__ElkNode__Group__1__Impl rule__ElkNode__Group__2
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
    // InternalGrana.g:4625:1: rule__ElkNode__Group__1__Impl : ( ( rule__ElkNode__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkNode__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4629:1: ( ( ( rule__ElkNode__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:4630:1: ( ( rule__ElkNode__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:4630:1: ( ( rule__ElkNode__IdentifierAssignment_1 ) )
            // InternalGrana.g:4631:1: ( rule__ElkNode__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkNodeAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:4632:1: ( rule__ElkNode__IdentifierAssignment_1 )
            // InternalGrana.g:4632:2: rule__ElkNode__IdentifierAssignment_1
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
    // InternalGrana.g:4642:1: rule__ElkNode__Group__2 : rule__ElkNode__Group__2__Impl ;
    public final void rule__ElkNode__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4646:1: ( rule__ElkNode__Group__2__Impl )
            // InternalGrana.g:4647:2: rule__ElkNode__Group__2__Impl
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
    // InternalGrana.g:4653:1: rule__ElkNode__Group__2__Impl : ( ( rule__ElkNode__Group_2__0 )? ) ;
    public final void rule__ElkNode__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4657:1: ( ( ( rule__ElkNode__Group_2__0 )? ) )
            // InternalGrana.g:4658:1: ( ( rule__ElkNode__Group_2__0 )? )
            {
            // InternalGrana.g:4658:1: ( ( rule__ElkNode__Group_2__0 )? )
            // InternalGrana.g:4659:1: ( rule__ElkNode__Group_2__0 )?
            {
             before(grammarAccess.getElkNodeAccess().getGroup_2()); 
            // InternalGrana.g:4660:1: ( rule__ElkNode__Group_2__0 )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==40) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalGrana.g:4660:2: rule__ElkNode__Group_2__0
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
    // InternalGrana.g:4676:1: rule__ElkNode__Group_2__0 : rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1 ;
    public final void rule__ElkNode__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4680:1: ( rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1 )
            // InternalGrana.g:4681:2: rule__ElkNode__Group_2__0__Impl rule__ElkNode__Group_2__1
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
    // InternalGrana.g:4688:1: rule__ElkNode__Group_2__0__Impl : ( '{' ) ;
    public final void rule__ElkNode__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4692:1: ( ( '{' ) )
            // InternalGrana.g:4693:1: ( '{' )
            {
            // InternalGrana.g:4693:1: ( '{' )
            // InternalGrana.g:4694:1: '{'
            {
             before(grammarAccess.getElkNodeAccess().getLeftCurlyBracketKeyword_2_0()); 
            match(input,40,FOLLOW_2); 
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
    // InternalGrana.g:4707:1: rule__ElkNode__Group_2__1 : rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2 ;
    public final void rule__ElkNode__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4711:1: ( rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2 )
            // InternalGrana.g:4712:2: rule__ElkNode__Group_2__1__Impl rule__ElkNode__Group_2__2
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
    // InternalGrana.g:4719:1: rule__ElkNode__Group_2__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkNode__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4723:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:4724:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:4724:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:4725:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkNodeAccess().getShapeLayoutParserRuleCall_2_1()); 
            // InternalGrana.g:4726:1: ( ruleShapeLayout )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==46) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // InternalGrana.g:4726:3: ruleShapeLayout
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
    // InternalGrana.g:4736:1: rule__ElkNode__Group_2__2 : rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3 ;
    public final void rule__ElkNode__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4740:1: ( rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3 )
            // InternalGrana.g:4741:2: rule__ElkNode__Group_2__2__Impl rule__ElkNode__Group_2__3
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
    // InternalGrana.g:4748:1: rule__ElkNode__Group_2__2__Impl : ( ( rule__ElkNode__PropertiesAssignment_2_2 )* ) ;
    public final void rule__ElkNode__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4752:1: ( ( ( rule__ElkNode__PropertiesAssignment_2_2 )* ) )
            // InternalGrana.g:4753:1: ( ( rule__ElkNode__PropertiesAssignment_2_2 )* )
            {
            // InternalGrana.g:4753:1: ( ( rule__ElkNode__PropertiesAssignment_2_2 )* )
            // InternalGrana.g:4754:1: ( rule__ElkNode__PropertiesAssignment_2_2 )*
            {
             before(grammarAccess.getElkNodeAccess().getPropertiesAssignment_2_2()); 
            // InternalGrana.g:4755:1: ( rule__ElkNode__PropertiesAssignment_2_2 )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==RULE_ID) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalGrana.g:4755:2: rule__ElkNode__PropertiesAssignment_2_2
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
    // InternalGrana.g:4765:1: rule__ElkNode__Group_2__3 : rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4 ;
    public final void rule__ElkNode__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4769:1: ( rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4 )
            // InternalGrana.g:4770:2: rule__ElkNode__Group_2__3__Impl rule__ElkNode__Group_2__4
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
    // InternalGrana.g:4777:1: rule__ElkNode__Group_2__3__Impl : ( ( rule__ElkNode__Alternatives_2_3 )* ) ;
    public final void rule__ElkNode__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4781:1: ( ( ( rule__ElkNode__Alternatives_2_3 )* ) )
            // InternalGrana.g:4782:1: ( ( rule__ElkNode__Alternatives_2_3 )* )
            {
            // InternalGrana.g:4782:1: ( ( rule__ElkNode__Alternatives_2_3 )* )
            // InternalGrana.g:4783:1: ( rule__ElkNode__Alternatives_2_3 )*
            {
             before(grammarAccess.getElkNodeAccess().getAlternatives_2_3()); 
            // InternalGrana.g:4784:1: ( rule__ElkNode__Alternatives_2_3 )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=42 && LA48_0<=43)||LA48_0==45||LA48_0==51) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalGrana.g:4784:2: rule__ElkNode__Alternatives_2_3
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
    // InternalGrana.g:4794:1: rule__ElkNode__Group_2__4 : rule__ElkNode__Group_2__4__Impl ;
    public final void rule__ElkNode__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4798:1: ( rule__ElkNode__Group_2__4__Impl )
            // InternalGrana.g:4799:2: rule__ElkNode__Group_2__4__Impl
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
    // InternalGrana.g:4805:1: rule__ElkNode__Group_2__4__Impl : ( '}' ) ;
    public final void rule__ElkNode__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4809:1: ( ( '}' ) )
            // InternalGrana.g:4810:1: ( '}' )
            {
            // InternalGrana.g:4810:1: ( '}' )
            // InternalGrana.g:4811:1: '}'
            {
             before(grammarAccess.getElkNodeAccess().getRightCurlyBracketKeyword_2_4()); 
            match(input,41,FOLLOW_2); 
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
    // InternalGrana.g:4834:1: rule__ElkLabel__Group__0 : rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 ;
    public final void rule__ElkLabel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4838:1: ( rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1 )
            // InternalGrana.g:4839:2: rule__ElkLabel__Group__0__Impl rule__ElkLabel__Group__1
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
    // InternalGrana.g:4846:1: rule__ElkLabel__Group__0__Impl : ( 'label' ) ;
    public final void rule__ElkLabel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4850:1: ( ( 'label' ) )
            // InternalGrana.g:4851:1: ( 'label' )
            {
            // InternalGrana.g:4851:1: ( 'label' )
            // InternalGrana.g:4852:1: 'label'
            {
             before(grammarAccess.getElkLabelAccess().getLabelKeyword_0()); 
            match(input,43,FOLLOW_2); 
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
    // InternalGrana.g:4865:1: rule__ElkLabel__Group__1 : rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 ;
    public final void rule__ElkLabel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4869:1: ( rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2 )
            // InternalGrana.g:4870:2: rule__ElkLabel__Group__1__Impl rule__ElkLabel__Group__2
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
    // InternalGrana.g:4877:1: rule__ElkLabel__Group__1__Impl : ( ( rule__ElkLabel__Group_1__0 )? ) ;
    public final void rule__ElkLabel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4881:1: ( ( ( rule__ElkLabel__Group_1__0 )? ) )
            // InternalGrana.g:4882:1: ( ( rule__ElkLabel__Group_1__0 )? )
            {
            // InternalGrana.g:4882:1: ( ( rule__ElkLabel__Group_1__0 )? )
            // InternalGrana.g:4883:1: ( rule__ElkLabel__Group_1__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_1()); 
            // InternalGrana.g:4884:1: ( rule__ElkLabel__Group_1__0 )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==RULE_ID) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // InternalGrana.g:4884:2: rule__ElkLabel__Group_1__0
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
    // InternalGrana.g:4894:1: rule__ElkLabel__Group__2 : rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 ;
    public final void rule__ElkLabel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4898:1: ( rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3 )
            // InternalGrana.g:4899:2: rule__ElkLabel__Group__2__Impl rule__ElkLabel__Group__3
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
    // InternalGrana.g:4906:1: rule__ElkLabel__Group__2__Impl : ( ( rule__ElkLabel__TextAssignment_2 ) ) ;
    public final void rule__ElkLabel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4910:1: ( ( ( rule__ElkLabel__TextAssignment_2 ) ) )
            // InternalGrana.g:4911:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            {
            // InternalGrana.g:4911:1: ( ( rule__ElkLabel__TextAssignment_2 ) )
            // InternalGrana.g:4912:1: ( rule__ElkLabel__TextAssignment_2 )
            {
             before(grammarAccess.getElkLabelAccess().getTextAssignment_2()); 
            // InternalGrana.g:4913:1: ( rule__ElkLabel__TextAssignment_2 )
            // InternalGrana.g:4913:2: rule__ElkLabel__TextAssignment_2
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
    // InternalGrana.g:4923:1: rule__ElkLabel__Group__3 : rule__ElkLabel__Group__3__Impl ;
    public final void rule__ElkLabel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4927:1: ( rule__ElkLabel__Group__3__Impl )
            // InternalGrana.g:4928:2: rule__ElkLabel__Group__3__Impl
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
    // InternalGrana.g:4934:1: rule__ElkLabel__Group__3__Impl : ( ( rule__ElkLabel__Group_3__0 )? ) ;
    public final void rule__ElkLabel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4938:1: ( ( ( rule__ElkLabel__Group_3__0 )? ) )
            // InternalGrana.g:4939:1: ( ( rule__ElkLabel__Group_3__0 )? )
            {
            // InternalGrana.g:4939:1: ( ( rule__ElkLabel__Group_3__0 )? )
            // InternalGrana.g:4940:1: ( rule__ElkLabel__Group_3__0 )?
            {
             before(grammarAccess.getElkLabelAccess().getGroup_3()); 
            // InternalGrana.g:4941:1: ( rule__ElkLabel__Group_3__0 )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==40) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalGrana.g:4941:2: rule__ElkLabel__Group_3__0
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
    // InternalGrana.g:4959:1: rule__ElkLabel__Group_1__0 : rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 ;
    public final void rule__ElkLabel__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4963:1: ( rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1 )
            // InternalGrana.g:4964:2: rule__ElkLabel__Group_1__0__Impl rule__ElkLabel__Group_1__1
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
    // InternalGrana.g:4971:1: rule__ElkLabel__Group_1__0__Impl : ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkLabel__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4975:1: ( ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) ) )
            // InternalGrana.g:4976:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            {
            // InternalGrana.g:4976:1: ( ( rule__ElkLabel__IdentifierAssignment_1_0 ) )
            // InternalGrana.g:4977:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkLabelAccess().getIdentifierAssignment_1_0()); 
            // InternalGrana.g:4978:1: ( rule__ElkLabel__IdentifierAssignment_1_0 )
            // InternalGrana.g:4978:2: rule__ElkLabel__IdentifierAssignment_1_0
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
    // InternalGrana.g:4988:1: rule__ElkLabel__Group_1__1 : rule__ElkLabel__Group_1__1__Impl ;
    public final void rule__ElkLabel__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:4992:1: ( rule__ElkLabel__Group_1__1__Impl )
            // InternalGrana.g:4993:2: rule__ElkLabel__Group_1__1__Impl
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
    // InternalGrana.g:4999:1: rule__ElkLabel__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkLabel__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5003:1: ( ( ':' ) )
            // InternalGrana.g:5004:1: ( ':' )
            {
            // InternalGrana.g:5004:1: ( ':' )
            // InternalGrana.g:5005:1: ':'
            {
             before(grammarAccess.getElkLabelAccess().getColonKeyword_1_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:5022:1: rule__ElkLabel__Group_3__0 : rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 ;
    public final void rule__ElkLabel__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5026:1: ( rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1 )
            // InternalGrana.g:5027:2: rule__ElkLabel__Group_3__0__Impl rule__ElkLabel__Group_3__1
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
    // InternalGrana.g:5034:1: rule__ElkLabel__Group_3__0__Impl : ( '{' ) ;
    public final void rule__ElkLabel__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5038:1: ( ( '{' ) )
            // InternalGrana.g:5039:1: ( '{' )
            {
            // InternalGrana.g:5039:1: ( '{' )
            // InternalGrana.g:5040:1: '{'
            {
             before(grammarAccess.getElkLabelAccess().getLeftCurlyBracketKeyword_3_0()); 
            match(input,40,FOLLOW_2); 
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
    // InternalGrana.g:5053:1: rule__ElkLabel__Group_3__1 : rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 ;
    public final void rule__ElkLabel__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5057:1: ( rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2 )
            // InternalGrana.g:5058:2: rule__ElkLabel__Group_3__1__Impl rule__ElkLabel__Group_3__2
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
    // InternalGrana.g:5065:1: rule__ElkLabel__Group_3__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkLabel__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5069:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:5070:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:5070:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:5071:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkLabelAccess().getShapeLayoutParserRuleCall_3_1()); 
            // InternalGrana.g:5072:1: ( ruleShapeLayout )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==46) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalGrana.g:5072:3: ruleShapeLayout
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
    // InternalGrana.g:5082:1: rule__ElkLabel__Group_3__2 : rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 ;
    public final void rule__ElkLabel__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5086:1: ( rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3 )
            // InternalGrana.g:5087:2: rule__ElkLabel__Group_3__2__Impl rule__ElkLabel__Group_3__3
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
    // InternalGrana.g:5094:1: rule__ElkLabel__Group_3__2__Impl : ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) ;
    public final void rule__ElkLabel__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5098:1: ( ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* ) )
            // InternalGrana.g:5099:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            {
            // InternalGrana.g:5099:1: ( ( rule__ElkLabel__PropertiesAssignment_3_2 )* )
            // InternalGrana.g:5100:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            {
             before(grammarAccess.getElkLabelAccess().getPropertiesAssignment_3_2()); 
            // InternalGrana.g:5101:1: ( rule__ElkLabel__PropertiesAssignment_3_2 )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==RULE_ID) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // InternalGrana.g:5101:2: rule__ElkLabel__PropertiesAssignment_3_2
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
    // InternalGrana.g:5111:1: rule__ElkLabel__Group_3__3 : rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 ;
    public final void rule__ElkLabel__Group_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5115:1: ( rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4 )
            // InternalGrana.g:5116:2: rule__ElkLabel__Group_3__3__Impl rule__ElkLabel__Group_3__4
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
    // InternalGrana.g:5123:1: rule__ElkLabel__Group_3__3__Impl : ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) ;
    public final void rule__ElkLabel__Group_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5127:1: ( ( ( rule__ElkLabel__LabelsAssignment_3_3 )* ) )
            // InternalGrana.g:5128:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            {
            // InternalGrana.g:5128:1: ( ( rule__ElkLabel__LabelsAssignment_3_3 )* )
            // InternalGrana.g:5129:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            {
             before(grammarAccess.getElkLabelAccess().getLabelsAssignment_3_3()); 
            // InternalGrana.g:5130:1: ( rule__ElkLabel__LabelsAssignment_3_3 )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==43) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // InternalGrana.g:5130:2: rule__ElkLabel__LabelsAssignment_3_3
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
    // InternalGrana.g:5140:1: rule__ElkLabel__Group_3__4 : rule__ElkLabel__Group_3__4__Impl ;
    public final void rule__ElkLabel__Group_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5144:1: ( rule__ElkLabel__Group_3__4__Impl )
            // InternalGrana.g:5145:2: rule__ElkLabel__Group_3__4__Impl
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
    // InternalGrana.g:5151:1: rule__ElkLabel__Group_3__4__Impl : ( '}' ) ;
    public final void rule__ElkLabel__Group_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5155:1: ( ( '}' ) )
            // InternalGrana.g:5156:1: ( '}' )
            {
            // InternalGrana.g:5156:1: ( '}' )
            // InternalGrana.g:5157:1: '}'
            {
             before(grammarAccess.getElkLabelAccess().getRightCurlyBracketKeyword_3_4()); 
            match(input,41,FOLLOW_2); 
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
    // InternalGrana.g:5180:1: rule__ElkPort__Group__0 : rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1 ;
    public final void rule__ElkPort__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5184:1: ( rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1 )
            // InternalGrana.g:5185:2: rule__ElkPort__Group__0__Impl rule__ElkPort__Group__1
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
    // InternalGrana.g:5192:1: rule__ElkPort__Group__0__Impl : ( 'port' ) ;
    public final void rule__ElkPort__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5196:1: ( ( 'port' ) )
            // InternalGrana.g:5197:1: ( 'port' )
            {
            // InternalGrana.g:5197:1: ( 'port' )
            // InternalGrana.g:5198:1: 'port'
            {
             before(grammarAccess.getElkPortAccess().getPortKeyword_0()); 
            match(input,45,FOLLOW_2); 
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
    // InternalGrana.g:5211:1: rule__ElkPort__Group__1 : rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2 ;
    public final void rule__ElkPort__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5215:1: ( rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2 )
            // InternalGrana.g:5216:2: rule__ElkPort__Group__1__Impl rule__ElkPort__Group__2
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
    // InternalGrana.g:5223:1: rule__ElkPort__Group__1__Impl : ( ( rule__ElkPort__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkPort__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5227:1: ( ( ( rule__ElkPort__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:5228:1: ( ( rule__ElkPort__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:5228:1: ( ( rule__ElkPort__IdentifierAssignment_1 ) )
            // InternalGrana.g:5229:1: ( rule__ElkPort__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkPortAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:5230:1: ( rule__ElkPort__IdentifierAssignment_1 )
            // InternalGrana.g:5230:2: rule__ElkPort__IdentifierAssignment_1
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
    // InternalGrana.g:5240:1: rule__ElkPort__Group__2 : rule__ElkPort__Group__2__Impl ;
    public final void rule__ElkPort__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5244:1: ( rule__ElkPort__Group__2__Impl )
            // InternalGrana.g:5245:2: rule__ElkPort__Group__2__Impl
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
    // InternalGrana.g:5251:1: rule__ElkPort__Group__2__Impl : ( ( rule__ElkPort__Group_2__0 )? ) ;
    public final void rule__ElkPort__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5255:1: ( ( ( rule__ElkPort__Group_2__0 )? ) )
            // InternalGrana.g:5256:1: ( ( rule__ElkPort__Group_2__0 )? )
            {
            // InternalGrana.g:5256:1: ( ( rule__ElkPort__Group_2__0 )? )
            // InternalGrana.g:5257:1: ( rule__ElkPort__Group_2__0 )?
            {
             before(grammarAccess.getElkPortAccess().getGroup_2()); 
            // InternalGrana.g:5258:1: ( rule__ElkPort__Group_2__0 )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==40) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalGrana.g:5258:2: rule__ElkPort__Group_2__0
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
    // InternalGrana.g:5274:1: rule__ElkPort__Group_2__0 : rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1 ;
    public final void rule__ElkPort__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5278:1: ( rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1 )
            // InternalGrana.g:5279:2: rule__ElkPort__Group_2__0__Impl rule__ElkPort__Group_2__1
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
    // InternalGrana.g:5286:1: rule__ElkPort__Group_2__0__Impl : ( '{' ) ;
    public final void rule__ElkPort__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5290:1: ( ( '{' ) )
            // InternalGrana.g:5291:1: ( '{' )
            {
            // InternalGrana.g:5291:1: ( '{' )
            // InternalGrana.g:5292:1: '{'
            {
             before(grammarAccess.getElkPortAccess().getLeftCurlyBracketKeyword_2_0()); 
            match(input,40,FOLLOW_2); 
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
    // InternalGrana.g:5305:1: rule__ElkPort__Group_2__1 : rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2 ;
    public final void rule__ElkPort__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5309:1: ( rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2 )
            // InternalGrana.g:5310:2: rule__ElkPort__Group_2__1__Impl rule__ElkPort__Group_2__2
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
    // InternalGrana.g:5317:1: rule__ElkPort__Group_2__1__Impl : ( ( ruleShapeLayout )? ) ;
    public final void rule__ElkPort__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5321:1: ( ( ( ruleShapeLayout )? ) )
            // InternalGrana.g:5322:1: ( ( ruleShapeLayout )? )
            {
            // InternalGrana.g:5322:1: ( ( ruleShapeLayout )? )
            // InternalGrana.g:5323:1: ( ruleShapeLayout )?
            {
             before(grammarAccess.getElkPortAccess().getShapeLayoutParserRuleCall_2_1()); 
            // InternalGrana.g:5324:1: ( ruleShapeLayout )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==46) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // InternalGrana.g:5324:3: ruleShapeLayout
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
    // InternalGrana.g:5334:1: rule__ElkPort__Group_2__2 : rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3 ;
    public final void rule__ElkPort__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5338:1: ( rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3 )
            // InternalGrana.g:5339:2: rule__ElkPort__Group_2__2__Impl rule__ElkPort__Group_2__3
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
    // InternalGrana.g:5346:1: rule__ElkPort__Group_2__2__Impl : ( ( rule__ElkPort__PropertiesAssignment_2_2 )* ) ;
    public final void rule__ElkPort__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5350:1: ( ( ( rule__ElkPort__PropertiesAssignment_2_2 )* ) )
            // InternalGrana.g:5351:1: ( ( rule__ElkPort__PropertiesAssignment_2_2 )* )
            {
            // InternalGrana.g:5351:1: ( ( rule__ElkPort__PropertiesAssignment_2_2 )* )
            // InternalGrana.g:5352:1: ( rule__ElkPort__PropertiesAssignment_2_2 )*
            {
             before(grammarAccess.getElkPortAccess().getPropertiesAssignment_2_2()); 
            // InternalGrana.g:5353:1: ( rule__ElkPort__PropertiesAssignment_2_2 )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==RULE_ID) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // InternalGrana.g:5353:2: rule__ElkPort__PropertiesAssignment_2_2
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
    // InternalGrana.g:5363:1: rule__ElkPort__Group_2__3 : rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4 ;
    public final void rule__ElkPort__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5367:1: ( rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4 )
            // InternalGrana.g:5368:2: rule__ElkPort__Group_2__3__Impl rule__ElkPort__Group_2__4
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
    // InternalGrana.g:5375:1: rule__ElkPort__Group_2__3__Impl : ( ( rule__ElkPort__LabelsAssignment_2_3 )* ) ;
    public final void rule__ElkPort__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5379:1: ( ( ( rule__ElkPort__LabelsAssignment_2_3 )* ) )
            // InternalGrana.g:5380:1: ( ( rule__ElkPort__LabelsAssignment_2_3 )* )
            {
            // InternalGrana.g:5380:1: ( ( rule__ElkPort__LabelsAssignment_2_3 )* )
            // InternalGrana.g:5381:1: ( rule__ElkPort__LabelsAssignment_2_3 )*
            {
             before(grammarAccess.getElkPortAccess().getLabelsAssignment_2_3()); 
            // InternalGrana.g:5382:1: ( rule__ElkPort__LabelsAssignment_2_3 )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==43) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // InternalGrana.g:5382:2: rule__ElkPort__LabelsAssignment_2_3
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
    // InternalGrana.g:5392:1: rule__ElkPort__Group_2__4 : rule__ElkPort__Group_2__4__Impl ;
    public final void rule__ElkPort__Group_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5396:1: ( rule__ElkPort__Group_2__4__Impl )
            // InternalGrana.g:5397:2: rule__ElkPort__Group_2__4__Impl
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
    // InternalGrana.g:5403:1: rule__ElkPort__Group_2__4__Impl : ( '}' ) ;
    public final void rule__ElkPort__Group_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5407:1: ( ( '}' ) )
            // InternalGrana.g:5408:1: ( '}' )
            {
            // InternalGrana.g:5408:1: ( '}' )
            // InternalGrana.g:5409:1: '}'
            {
             before(grammarAccess.getElkPortAccess().getRightCurlyBracketKeyword_2_4()); 
            match(input,41,FOLLOW_2); 
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
    // InternalGrana.g:5432:1: rule__ShapeLayout__Group__0 : rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 ;
    public final void rule__ShapeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5436:1: ( rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1 )
            // InternalGrana.g:5437:2: rule__ShapeLayout__Group__0__Impl rule__ShapeLayout__Group__1
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
    // InternalGrana.g:5444:1: rule__ShapeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__ShapeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5448:1: ( ( 'layout' ) )
            // InternalGrana.g:5449:1: ( 'layout' )
            {
            // InternalGrana.g:5449:1: ( 'layout' )
            // InternalGrana.g:5450:1: 'layout'
            {
             before(grammarAccess.getShapeLayoutAccess().getLayoutKeyword_0()); 
            match(input,46,FOLLOW_2); 
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
    // InternalGrana.g:5463:1: rule__ShapeLayout__Group__1 : rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 ;
    public final void rule__ShapeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5467:1: ( rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2 )
            // InternalGrana.g:5468:2: rule__ShapeLayout__Group__1__Impl rule__ShapeLayout__Group__2
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
    // InternalGrana.g:5475:1: rule__ShapeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__ShapeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5479:1: ( ( '[' ) )
            // InternalGrana.g:5480:1: ( '[' )
            {
            // InternalGrana.g:5480:1: ( '[' )
            // InternalGrana.g:5481:1: '['
            {
             before(grammarAccess.getShapeLayoutAccess().getLeftSquareBracketKeyword_1()); 
            match(input,47,FOLLOW_2); 
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
    // InternalGrana.g:5494:1: rule__ShapeLayout__Group__2 : rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 ;
    public final void rule__ShapeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5498:1: ( rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3 )
            // InternalGrana.g:5499:2: rule__ShapeLayout__Group__2__Impl rule__ShapeLayout__Group__3
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
    // InternalGrana.g:5506:1: rule__ShapeLayout__Group__2__Impl : ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) ;
    public final void rule__ShapeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5510:1: ( ( ( rule__ShapeLayout__UnorderedGroup_2 ) ) )
            // InternalGrana.g:5511:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            {
            // InternalGrana.g:5511:1: ( ( rule__ShapeLayout__UnorderedGroup_2 ) )
            // InternalGrana.g:5512:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2()); 
            // InternalGrana.g:5513:1: ( rule__ShapeLayout__UnorderedGroup_2 )
            // InternalGrana.g:5513:2: rule__ShapeLayout__UnorderedGroup_2
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
    // InternalGrana.g:5523:1: rule__ShapeLayout__Group__3 : rule__ShapeLayout__Group__3__Impl ;
    public final void rule__ShapeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5527:1: ( rule__ShapeLayout__Group__3__Impl )
            // InternalGrana.g:5528:2: rule__ShapeLayout__Group__3__Impl
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
    // InternalGrana.g:5534:1: rule__ShapeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__ShapeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5538:1: ( ( ']' ) )
            // InternalGrana.g:5539:1: ( ']' )
            {
            // InternalGrana.g:5539:1: ( ']' )
            // InternalGrana.g:5540:1: ']'
            {
             before(grammarAccess.getShapeLayoutAccess().getRightSquareBracketKeyword_3()); 
            match(input,48,FOLLOW_2); 
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
    // InternalGrana.g:5561:1: rule__ShapeLayout__Group_2_0__0 : rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 ;
    public final void rule__ShapeLayout__Group_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5565:1: ( rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1 )
            // InternalGrana.g:5566:2: rule__ShapeLayout__Group_2_0__0__Impl rule__ShapeLayout__Group_2_0__1
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
    // InternalGrana.g:5573:1: rule__ShapeLayout__Group_2_0__0__Impl : ( 'position' ) ;
    public final void rule__ShapeLayout__Group_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5577:1: ( ( 'position' ) )
            // InternalGrana.g:5578:1: ( 'position' )
            {
            // InternalGrana.g:5578:1: ( 'position' )
            // InternalGrana.g:5579:1: 'position'
            {
             before(grammarAccess.getShapeLayoutAccess().getPositionKeyword_2_0_0()); 
            match(input,49,FOLLOW_2); 
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
    // InternalGrana.g:5592:1: rule__ShapeLayout__Group_2_0__1 : rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 ;
    public final void rule__ShapeLayout__Group_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5596:1: ( rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2 )
            // InternalGrana.g:5597:2: rule__ShapeLayout__Group_2_0__1__Impl rule__ShapeLayout__Group_2_0__2
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
    // InternalGrana.g:5604:1: rule__ShapeLayout__Group_2_0__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5608:1: ( ( ':' ) )
            // InternalGrana.g:5609:1: ( ':' )
            {
            // InternalGrana.g:5609:1: ( ':' )
            // InternalGrana.g:5610:1: ':'
            {
             before(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_0_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:5623:1: rule__ShapeLayout__Group_2_0__2 : rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 ;
    public final void rule__ShapeLayout__Group_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5627:1: ( rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3 )
            // InternalGrana.g:5628:2: rule__ShapeLayout__Group_2_0__2__Impl rule__ShapeLayout__Group_2_0__3
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
    // InternalGrana.g:5635:1: rule__ShapeLayout__Group_2_0__2__Impl : ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5639:1: ( ( ( rule__ShapeLayout__XAssignment_2_0_2 ) ) )
            // InternalGrana.g:5640:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            {
            // InternalGrana.g:5640:1: ( ( rule__ShapeLayout__XAssignment_2_0_2 ) )
            // InternalGrana.g:5641:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getXAssignment_2_0_2()); 
            // InternalGrana.g:5642:1: ( rule__ShapeLayout__XAssignment_2_0_2 )
            // InternalGrana.g:5642:2: rule__ShapeLayout__XAssignment_2_0_2
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
    // InternalGrana.g:5652:1: rule__ShapeLayout__Group_2_0__3 : rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 ;
    public final void rule__ShapeLayout__Group_2_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5656:1: ( rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4 )
            // InternalGrana.g:5657:2: rule__ShapeLayout__Group_2_0__3__Impl rule__ShapeLayout__Group_2_0__4
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
    // InternalGrana.g:5664:1: rule__ShapeLayout__Group_2_0__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5668:1: ( ( ',' ) )
            // InternalGrana.g:5669:1: ( ',' )
            {
            // InternalGrana.g:5669:1: ( ',' )
            // InternalGrana.g:5670:1: ','
            {
             before(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_0_3()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:5683:1: rule__ShapeLayout__Group_2_0__4 : rule__ShapeLayout__Group_2_0__4__Impl ;
    public final void rule__ShapeLayout__Group_2_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5687:1: ( rule__ShapeLayout__Group_2_0__4__Impl )
            // InternalGrana.g:5688:2: rule__ShapeLayout__Group_2_0__4__Impl
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
    // InternalGrana.g:5694:1: rule__ShapeLayout__Group_2_0__4__Impl : ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5698:1: ( ( ( rule__ShapeLayout__YAssignment_2_0_4 ) ) )
            // InternalGrana.g:5699:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            {
            // InternalGrana.g:5699:1: ( ( rule__ShapeLayout__YAssignment_2_0_4 ) )
            // InternalGrana.g:5700:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getYAssignment_2_0_4()); 
            // InternalGrana.g:5701:1: ( rule__ShapeLayout__YAssignment_2_0_4 )
            // InternalGrana.g:5701:2: rule__ShapeLayout__YAssignment_2_0_4
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
    // InternalGrana.g:5721:1: rule__ShapeLayout__Group_2_1__0 : rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 ;
    public final void rule__ShapeLayout__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5725:1: ( rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1 )
            // InternalGrana.g:5726:2: rule__ShapeLayout__Group_2_1__0__Impl rule__ShapeLayout__Group_2_1__1
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
    // InternalGrana.g:5733:1: rule__ShapeLayout__Group_2_1__0__Impl : ( 'size' ) ;
    public final void rule__ShapeLayout__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5737:1: ( ( 'size' ) )
            // InternalGrana.g:5738:1: ( 'size' )
            {
            // InternalGrana.g:5738:1: ( 'size' )
            // InternalGrana.g:5739:1: 'size'
            {
             before(grammarAccess.getShapeLayoutAccess().getSizeKeyword_2_1_0()); 
            match(input,50,FOLLOW_2); 
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
    // InternalGrana.g:5752:1: rule__ShapeLayout__Group_2_1__1 : rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 ;
    public final void rule__ShapeLayout__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5756:1: ( rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2 )
            // InternalGrana.g:5757:2: rule__ShapeLayout__Group_2_1__1__Impl rule__ShapeLayout__Group_2_1__2
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
    // InternalGrana.g:5764:1: rule__ShapeLayout__Group_2_1__1__Impl : ( ':' ) ;
    public final void rule__ShapeLayout__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5768:1: ( ( ':' ) )
            // InternalGrana.g:5769:1: ( ':' )
            {
            // InternalGrana.g:5769:1: ( ':' )
            // InternalGrana.g:5770:1: ':'
            {
             before(grammarAccess.getShapeLayoutAccess().getColonKeyword_2_1_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:5783:1: rule__ShapeLayout__Group_2_1__2 : rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 ;
    public final void rule__ShapeLayout__Group_2_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5787:1: ( rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3 )
            // InternalGrana.g:5788:2: rule__ShapeLayout__Group_2_1__2__Impl rule__ShapeLayout__Group_2_1__3
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
    // InternalGrana.g:5795:1: rule__ShapeLayout__Group_2_1__2__Impl : ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5799:1: ( ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) ) )
            // InternalGrana.g:5800:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            {
            // InternalGrana.g:5800:1: ( ( rule__ShapeLayout__WidthAssignment_2_1_2 ) )
            // InternalGrana.g:5801:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            {
             before(grammarAccess.getShapeLayoutAccess().getWidthAssignment_2_1_2()); 
            // InternalGrana.g:5802:1: ( rule__ShapeLayout__WidthAssignment_2_1_2 )
            // InternalGrana.g:5802:2: rule__ShapeLayout__WidthAssignment_2_1_2
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
    // InternalGrana.g:5812:1: rule__ShapeLayout__Group_2_1__3 : rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 ;
    public final void rule__ShapeLayout__Group_2_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5816:1: ( rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4 )
            // InternalGrana.g:5817:2: rule__ShapeLayout__Group_2_1__3__Impl rule__ShapeLayout__Group_2_1__4
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
    // InternalGrana.g:5824:1: rule__ShapeLayout__Group_2_1__3__Impl : ( ',' ) ;
    public final void rule__ShapeLayout__Group_2_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5828:1: ( ( ',' ) )
            // InternalGrana.g:5829:1: ( ',' )
            {
            // InternalGrana.g:5829:1: ( ',' )
            // InternalGrana.g:5830:1: ','
            {
             before(grammarAccess.getShapeLayoutAccess().getCommaKeyword_2_1_3()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:5843:1: rule__ShapeLayout__Group_2_1__4 : rule__ShapeLayout__Group_2_1__4__Impl ;
    public final void rule__ShapeLayout__Group_2_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5847:1: ( rule__ShapeLayout__Group_2_1__4__Impl )
            // InternalGrana.g:5848:2: rule__ShapeLayout__Group_2_1__4__Impl
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
    // InternalGrana.g:5854:1: rule__ShapeLayout__Group_2_1__4__Impl : ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) ;
    public final void rule__ShapeLayout__Group_2_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5858:1: ( ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) ) )
            // InternalGrana.g:5859:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            {
            // InternalGrana.g:5859:1: ( ( rule__ShapeLayout__HeightAssignment_2_1_4 ) )
            // InternalGrana.g:5860:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            {
             before(grammarAccess.getShapeLayoutAccess().getHeightAssignment_2_1_4()); 
            // InternalGrana.g:5861:1: ( rule__ShapeLayout__HeightAssignment_2_1_4 )
            // InternalGrana.g:5861:2: rule__ShapeLayout__HeightAssignment_2_1_4
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
    // InternalGrana.g:5881:1: rule__ElkEdge__Group__0 : rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1 ;
    public final void rule__ElkEdge__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5885:1: ( rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1 )
            // InternalGrana.g:5886:2: rule__ElkEdge__Group__0__Impl rule__ElkEdge__Group__1
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
    // InternalGrana.g:5893:1: rule__ElkEdge__Group__0__Impl : ( 'edge' ) ;
    public final void rule__ElkEdge__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5897:1: ( ( 'edge' ) )
            // InternalGrana.g:5898:1: ( 'edge' )
            {
            // InternalGrana.g:5898:1: ( 'edge' )
            // InternalGrana.g:5899:1: 'edge'
            {
             before(grammarAccess.getElkEdgeAccess().getEdgeKeyword_0()); 
            match(input,51,FOLLOW_2); 
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
    // InternalGrana.g:5912:1: rule__ElkEdge__Group__1 : rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2 ;
    public final void rule__ElkEdge__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5916:1: ( rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2 )
            // InternalGrana.g:5917:2: rule__ElkEdge__Group__1__Impl rule__ElkEdge__Group__2
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
    // InternalGrana.g:5924:1: rule__ElkEdge__Group__1__Impl : ( ( rule__ElkEdge__Group_1__0 )? ) ;
    public final void rule__ElkEdge__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5928:1: ( ( ( rule__ElkEdge__Group_1__0 )? ) )
            // InternalGrana.g:5929:1: ( ( rule__ElkEdge__Group_1__0 )? )
            {
            // InternalGrana.g:5929:1: ( ( rule__ElkEdge__Group_1__0 )? )
            // InternalGrana.g:5930:1: ( rule__ElkEdge__Group_1__0 )?
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_1()); 
            // InternalGrana.g:5931:1: ( rule__ElkEdge__Group_1__0 )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==RULE_ID) ) {
                int LA58_1 = input.LA(2);

                if ( (LA58_1==44) ) {
                    alt58=1;
                }
            }
            switch (alt58) {
                case 1 :
                    // InternalGrana.g:5931:2: rule__ElkEdge__Group_1__0
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
    // InternalGrana.g:5941:1: rule__ElkEdge__Group__2 : rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3 ;
    public final void rule__ElkEdge__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5945:1: ( rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3 )
            // InternalGrana.g:5946:2: rule__ElkEdge__Group__2__Impl rule__ElkEdge__Group__3
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
    // InternalGrana.g:5953:1: rule__ElkEdge__Group__2__Impl : ( ( rule__ElkEdge__SourcesAssignment_2 ) ) ;
    public final void rule__ElkEdge__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5957:1: ( ( ( rule__ElkEdge__SourcesAssignment_2 ) ) )
            // InternalGrana.g:5958:1: ( ( rule__ElkEdge__SourcesAssignment_2 ) )
            {
            // InternalGrana.g:5958:1: ( ( rule__ElkEdge__SourcesAssignment_2 ) )
            // InternalGrana.g:5959:1: ( rule__ElkEdge__SourcesAssignment_2 )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesAssignment_2()); 
            // InternalGrana.g:5960:1: ( rule__ElkEdge__SourcesAssignment_2 )
            // InternalGrana.g:5960:2: rule__ElkEdge__SourcesAssignment_2
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
    // InternalGrana.g:5970:1: rule__ElkEdge__Group__3 : rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4 ;
    public final void rule__ElkEdge__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5974:1: ( rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4 )
            // InternalGrana.g:5975:2: rule__ElkEdge__Group__3__Impl rule__ElkEdge__Group__4
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
    // InternalGrana.g:5982:1: rule__ElkEdge__Group__3__Impl : ( ( rule__ElkEdge__Group_3__0 )* ) ;
    public final void rule__ElkEdge__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:5986:1: ( ( ( rule__ElkEdge__Group_3__0 )* ) )
            // InternalGrana.g:5987:1: ( ( rule__ElkEdge__Group_3__0 )* )
            {
            // InternalGrana.g:5987:1: ( ( rule__ElkEdge__Group_3__0 )* )
            // InternalGrana.g:5988:1: ( rule__ElkEdge__Group_3__0 )*
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_3()); 
            // InternalGrana.g:5989:1: ( rule__ElkEdge__Group_3__0 )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==33) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // InternalGrana.g:5989:2: rule__ElkEdge__Group_3__0
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
    // InternalGrana.g:5999:1: rule__ElkEdge__Group__4 : rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5 ;
    public final void rule__ElkEdge__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6003:1: ( rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5 )
            // InternalGrana.g:6004:2: rule__ElkEdge__Group__4__Impl rule__ElkEdge__Group__5
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
    // InternalGrana.g:6011:1: rule__ElkEdge__Group__4__Impl : ( '->' ) ;
    public final void rule__ElkEdge__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6015:1: ( ( '->' ) )
            // InternalGrana.g:6016:1: ( '->' )
            {
            // InternalGrana.g:6016:1: ( '->' )
            // InternalGrana.g:6017:1: '->'
            {
             before(grammarAccess.getElkEdgeAccess().getHyphenMinusGreaterThanSignKeyword_4()); 
            match(input,52,FOLLOW_2); 
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
    // InternalGrana.g:6030:1: rule__ElkEdge__Group__5 : rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6 ;
    public final void rule__ElkEdge__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6034:1: ( rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6 )
            // InternalGrana.g:6035:2: rule__ElkEdge__Group__5__Impl rule__ElkEdge__Group__6
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
    // InternalGrana.g:6042:1: rule__ElkEdge__Group__5__Impl : ( ( rule__ElkEdge__TargetsAssignment_5 ) ) ;
    public final void rule__ElkEdge__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6046:1: ( ( ( rule__ElkEdge__TargetsAssignment_5 ) ) )
            // InternalGrana.g:6047:1: ( ( rule__ElkEdge__TargetsAssignment_5 ) )
            {
            // InternalGrana.g:6047:1: ( ( rule__ElkEdge__TargetsAssignment_5 ) )
            // InternalGrana.g:6048:1: ( rule__ElkEdge__TargetsAssignment_5 )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsAssignment_5()); 
            // InternalGrana.g:6049:1: ( rule__ElkEdge__TargetsAssignment_5 )
            // InternalGrana.g:6049:2: rule__ElkEdge__TargetsAssignment_5
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
    // InternalGrana.g:6059:1: rule__ElkEdge__Group__6 : rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7 ;
    public final void rule__ElkEdge__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6063:1: ( rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7 )
            // InternalGrana.g:6064:2: rule__ElkEdge__Group__6__Impl rule__ElkEdge__Group__7
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
    // InternalGrana.g:6071:1: rule__ElkEdge__Group__6__Impl : ( ( rule__ElkEdge__Group_6__0 )* ) ;
    public final void rule__ElkEdge__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6075:1: ( ( ( rule__ElkEdge__Group_6__0 )* ) )
            // InternalGrana.g:6076:1: ( ( rule__ElkEdge__Group_6__0 )* )
            {
            // InternalGrana.g:6076:1: ( ( rule__ElkEdge__Group_6__0 )* )
            // InternalGrana.g:6077:1: ( rule__ElkEdge__Group_6__0 )*
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_6()); 
            // InternalGrana.g:6078:1: ( rule__ElkEdge__Group_6__0 )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==33) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // InternalGrana.g:6078:2: rule__ElkEdge__Group_6__0
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
    // InternalGrana.g:6088:1: rule__ElkEdge__Group__7 : rule__ElkEdge__Group__7__Impl ;
    public final void rule__ElkEdge__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6092:1: ( rule__ElkEdge__Group__7__Impl )
            // InternalGrana.g:6093:2: rule__ElkEdge__Group__7__Impl
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
    // InternalGrana.g:6099:1: rule__ElkEdge__Group__7__Impl : ( ( rule__ElkEdge__Group_7__0 )? ) ;
    public final void rule__ElkEdge__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6103:1: ( ( ( rule__ElkEdge__Group_7__0 )? ) )
            // InternalGrana.g:6104:1: ( ( rule__ElkEdge__Group_7__0 )? )
            {
            // InternalGrana.g:6104:1: ( ( rule__ElkEdge__Group_7__0 )? )
            // InternalGrana.g:6105:1: ( rule__ElkEdge__Group_7__0 )?
            {
             before(grammarAccess.getElkEdgeAccess().getGroup_7()); 
            // InternalGrana.g:6106:1: ( rule__ElkEdge__Group_7__0 )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==40) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalGrana.g:6106:2: rule__ElkEdge__Group_7__0
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
    // InternalGrana.g:6132:1: rule__ElkEdge__Group_1__0 : rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1 ;
    public final void rule__ElkEdge__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6136:1: ( rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1 )
            // InternalGrana.g:6137:2: rule__ElkEdge__Group_1__0__Impl rule__ElkEdge__Group_1__1
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
    // InternalGrana.g:6144:1: rule__ElkEdge__Group_1__0__Impl : ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) ) ;
    public final void rule__ElkEdge__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6148:1: ( ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) ) )
            // InternalGrana.g:6149:1: ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) )
            {
            // InternalGrana.g:6149:1: ( ( rule__ElkEdge__IdentifierAssignment_1_0 ) )
            // InternalGrana.g:6150:1: ( rule__ElkEdge__IdentifierAssignment_1_0 )
            {
             before(grammarAccess.getElkEdgeAccess().getIdentifierAssignment_1_0()); 
            // InternalGrana.g:6151:1: ( rule__ElkEdge__IdentifierAssignment_1_0 )
            // InternalGrana.g:6151:2: rule__ElkEdge__IdentifierAssignment_1_0
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
    // InternalGrana.g:6161:1: rule__ElkEdge__Group_1__1 : rule__ElkEdge__Group_1__1__Impl ;
    public final void rule__ElkEdge__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6165:1: ( rule__ElkEdge__Group_1__1__Impl )
            // InternalGrana.g:6166:2: rule__ElkEdge__Group_1__1__Impl
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
    // InternalGrana.g:6172:1: rule__ElkEdge__Group_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdge__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6176:1: ( ( ':' ) )
            // InternalGrana.g:6177:1: ( ':' )
            {
            // InternalGrana.g:6177:1: ( ':' )
            // InternalGrana.g:6178:1: ':'
            {
             before(grammarAccess.getElkEdgeAccess().getColonKeyword_1_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:6195:1: rule__ElkEdge__Group_3__0 : rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1 ;
    public final void rule__ElkEdge__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6199:1: ( rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1 )
            // InternalGrana.g:6200:2: rule__ElkEdge__Group_3__0__Impl rule__ElkEdge__Group_3__1
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
    // InternalGrana.g:6207:1: rule__ElkEdge__Group_3__0__Impl : ( ',' ) ;
    public final void rule__ElkEdge__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6211:1: ( ( ',' ) )
            // InternalGrana.g:6212:1: ( ',' )
            {
            // InternalGrana.g:6212:1: ( ',' )
            // InternalGrana.g:6213:1: ','
            {
             before(grammarAccess.getElkEdgeAccess().getCommaKeyword_3_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:6226:1: rule__ElkEdge__Group_3__1 : rule__ElkEdge__Group_3__1__Impl ;
    public final void rule__ElkEdge__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6230:1: ( rule__ElkEdge__Group_3__1__Impl )
            // InternalGrana.g:6231:2: rule__ElkEdge__Group_3__1__Impl
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
    // InternalGrana.g:6237:1: rule__ElkEdge__Group_3__1__Impl : ( ( rule__ElkEdge__SourcesAssignment_3_1 ) ) ;
    public final void rule__ElkEdge__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6241:1: ( ( ( rule__ElkEdge__SourcesAssignment_3_1 ) ) )
            // InternalGrana.g:6242:1: ( ( rule__ElkEdge__SourcesAssignment_3_1 ) )
            {
            // InternalGrana.g:6242:1: ( ( rule__ElkEdge__SourcesAssignment_3_1 ) )
            // InternalGrana.g:6243:1: ( rule__ElkEdge__SourcesAssignment_3_1 )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesAssignment_3_1()); 
            // InternalGrana.g:6244:1: ( rule__ElkEdge__SourcesAssignment_3_1 )
            // InternalGrana.g:6244:2: rule__ElkEdge__SourcesAssignment_3_1
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
    // InternalGrana.g:6258:1: rule__ElkEdge__Group_6__0 : rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1 ;
    public final void rule__ElkEdge__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6262:1: ( rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1 )
            // InternalGrana.g:6263:2: rule__ElkEdge__Group_6__0__Impl rule__ElkEdge__Group_6__1
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
    // InternalGrana.g:6270:1: rule__ElkEdge__Group_6__0__Impl : ( ',' ) ;
    public final void rule__ElkEdge__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6274:1: ( ( ',' ) )
            // InternalGrana.g:6275:1: ( ',' )
            {
            // InternalGrana.g:6275:1: ( ',' )
            // InternalGrana.g:6276:1: ','
            {
             before(grammarAccess.getElkEdgeAccess().getCommaKeyword_6_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:6289:1: rule__ElkEdge__Group_6__1 : rule__ElkEdge__Group_6__1__Impl ;
    public final void rule__ElkEdge__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6293:1: ( rule__ElkEdge__Group_6__1__Impl )
            // InternalGrana.g:6294:2: rule__ElkEdge__Group_6__1__Impl
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
    // InternalGrana.g:6300:1: rule__ElkEdge__Group_6__1__Impl : ( ( rule__ElkEdge__TargetsAssignment_6_1 ) ) ;
    public final void rule__ElkEdge__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6304:1: ( ( ( rule__ElkEdge__TargetsAssignment_6_1 ) ) )
            // InternalGrana.g:6305:1: ( ( rule__ElkEdge__TargetsAssignment_6_1 ) )
            {
            // InternalGrana.g:6305:1: ( ( rule__ElkEdge__TargetsAssignment_6_1 ) )
            // InternalGrana.g:6306:1: ( rule__ElkEdge__TargetsAssignment_6_1 )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsAssignment_6_1()); 
            // InternalGrana.g:6307:1: ( rule__ElkEdge__TargetsAssignment_6_1 )
            // InternalGrana.g:6307:2: rule__ElkEdge__TargetsAssignment_6_1
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
    // InternalGrana.g:6321:1: rule__ElkEdge__Group_7__0 : rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1 ;
    public final void rule__ElkEdge__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6325:1: ( rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1 )
            // InternalGrana.g:6326:2: rule__ElkEdge__Group_7__0__Impl rule__ElkEdge__Group_7__1
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
    // InternalGrana.g:6333:1: rule__ElkEdge__Group_7__0__Impl : ( '{' ) ;
    public final void rule__ElkEdge__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6337:1: ( ( '{' ) )
            // InternalGrana.g:6338:1: ( '{' )
            {
            // InternalGrana.g:6338:1: ( '{' )
            // InternalGrana.g:6339:1: '{'
            {
             before(grammarAccess.getElkEdgeAccess().getLeftCurlyBracketKeyword_7_0()); 
            match(input,40,FOLLOW_2); 
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
    // InternalGrana.g:6352:1: rule__ElkEdge__Group_7__1 : rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2 ;
    public final void rule__ElkEdge__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6356:1: ( rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2 )
            // InternalGrana.g:6357:2: rule__ElkEdge__Group_7__1__Impl rule__ElkEdge__Group_7__2
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
    // InternalGrana.g:6364:1: rule__ElkEdge__Group_7__1__Impl : ( ( ruleEdgeLayout )? ) ;
    public final void rule__ElkEdge__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6368:1: ( ( ( ruleEdgeLayout )? ) )
            // InternalGrana.g:6369:1: ( ( ruleEdgeLayout )? )
            {
            // InternalGrana.g:6369:1: ( ( ruleEdgeLayout )? )
            // InternalGrana.g:6370:1: ( ruleEdgeLayout )?
            {
             before(grammarAccess.getElkEdgeAccess().getEdgeLayoutParserRuleCall_7_1()); 
            // InternalGrana.g:6371:1: ( ruleEdgeLayout )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==46) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalGrana.g:6371:3: ruleEdgeLayout
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
    // InternalGrana.g:6381:1: rule__ElkEdge__Group_7__2 : rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3 ;
    public final void rule__ElkEdge__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6385:1: ( rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3 )
            // InternalGrana.g:6386:2: rule__ElkEdge__Group_7__2__Impl rule__ElkEdge__Group_7__3
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
    // InternalGrana.g:6393:1: rule__ElkEdge__Group_7__2__Impl : ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* ) ;
    public final void rule__ElkEdge__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6397:1: ( ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* ) )
            // InternalGrana.g:6398:1: ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* )
            {
            // InternalGrana.g:6398:1: ( ( rule__ElkEdge__PropertiesAssignment_7_2 )* )
            // InternalGrana.g:6399:1: ( rule__ElkEdge__PropertiesAssignment_7_2 )*
            {
             before(grammarAccess.getElkEdgeAccess().getPropertiesAssignment_7_2()); 
            // InternalGrana.g:6400:1: ( rule__ElkEdge__PropertiesAssignment_7_2 )*
            loop63:
            do {
                int alt63=2;
                int LA63_0 = input.LA(1);

                if ( (LA63_0==RULE_ID) ) {
                    alt63=1;
                }


                switch (alt63) {
            	case 1 :
            	    // InternalGrana.g:6400:2: rule__ElkEdge__PropertiesAssignment_7_2
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
    // InternalGrana.g:6410:1: rule__ElkEdge__Group_7__3 : rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4 ;
    public final void rule__ElkEdge__Group_7__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6414:1: ( rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4 )
            // InternalGrana.g:6415:2: rule__ElkEdge__Group_7__3__Impl rule__ElkEdge__Group_7__4
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
    // InternalGrana.g:6422:1: rule__ElkEdge__Group_7__3__Impl : ( ( rule__ElkEdge__LabelsAssignment_7_3 )* ) ;
    public final void rule__ElkEdge__Group_7__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6426:1: ( ( ( rule__ElkEdge__LabelsAssignment_7_3 )* ) )
            // InternalGrana.g:6427:1: ( ( rule__ElkEdge__LabelsAssignment_7_3 )* )
            {
            // InternalGrana.g:6427:1: ( ( rule__ElkEdge__LabelsAssignment_7_3 )* )
            // InternalGrana.g:6428:1: ( rule__ElkEdge__LabelsAssignment_7_3 )*
            {
             before(grammarAccess.getElkEdgeAccess().getLabelsAssignment_7_3()); 
            // InternalGrana.g:6429:1: ( rule__ElkEdge__LabelsAssignment_7_3 )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==43) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // InternalGrana.g:6429:2: rule__ElkEdge__LabelsAssignment_7_3
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
    // InternalGrana.g:6439:1: rule__ElkEdge__Group_7__4 : rule__ElkEdge__Group_7__4__Impl ;
    public final void rule__ElkEdge__Group_7__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6443:1: ( rule__ElkEdge__Group_7__4__Impl )
            // InternalGrana.g:6444:2: rule__ElkEdge__Group_7__4__Impl
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
    // InternalGrana.g:6450:1: rule__ElkEdge__Group_7__4__Impl : ( '}' ) ;
    public final void rule__ElkEdge__Group_7__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6454:1: ( ( '}' ) )
            // InternalGrana.g:6455:1: ( '}' )
            {
            // InternalGrana.g:6455:1: ( '}' )
            // InternalGrana.g:6456:1: '}'
            {
             before(grammarAccess.getElkEdgeAccess().getRightCurlyBracketKeyword_7_4()); 
            match(input,41,FOLLOW_2); 
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
    // InternalGrana.g:6479:1: rule__EdgeLayout__Group__0 : rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 ;
    public final void rule__EdgeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6483:1: ( rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1 )
            // InternalGrana.g:6484:2: rule__EdgeLayout__Group__0__Impl rule__EdgeLayout__Group__1
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
    // InternalGrana.g:6491:1: rule__EdgeLayout__Group__0__Impl : ( 'layout' ) ;
    public final void rule__EdgeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6495:1: ( ( 'layout' ) )
            // InternalGrana.g:6496:1: ( 'layout' )
            {
            // InternalGrana.g:6496:1: ( 'layout' )
            // InternalGrana.g:6497:1: 'layout'
            {
             before(grammarAccess.getEdgeLayoutAccess().getLayoutKeyword_0()); 
            match(input,46,FOLLOW_2); 
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
    // InternalGrana.g:6510:1: rule__EdgeLayout__Group__1 : rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 ;
    public final void rule__EdgeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6514:1: ( rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2 )
            // InternalGrana.g:6515:2: rule__EdgeLayout__Group__1__Impl rule__EdgeLayout__Group__2
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
    // InternalGrana.g:6522:1: rule__EdgeLayout__Group__1__Impl : ( '[' ) ;
    public final void rule__EdgeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6526:1: ( ( '[' ) )
            // InternalGrana.g:6527:1: ( '[' )
            {
            // InternalGrana.g:6527:1: ( '[' )
            // InternalGrana.g:6528:1: '['
            {
             before(grammarAccess.getEdgeLayoutAccess().getLeftSquareBracketKeyword_1()); 
            match(input,47,FOLLOW_2); 
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
    // InternalGrana.g:6541:1: rule__EdgeLayout__Group__2 : rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 ;
    public final void rule__EdgeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6545:1: ( rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3 )
            // InternalGrana.g:6546:2: rule__EdgeLayout__Group__2__Impl rule__EdgeLayout__Group__3
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
    // InternalGrana.g:6553:1: rule__EdgeLayout__Group__2__Impl : ( ( rule__EdgeLayout__Alternatives_2 ) ) ;
    public final void rule__EdgeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6557:1: ( ( ( rule__EdgeLayout__Alternatives_2 ) ) )
            // InternalGrana.g:6558:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            {
            // InternalGrana.g:6558:1: ( ( rule__EdgeLayout__Alternatives_2 ) )
            // InternalGrana.g:6559:1: ( rule__EdgeLayout__Alternatives_2 )
            {
             before(grammarAccess.getEdgeLayoutAccess().getAlternatives_2()); 
            // InternalGrana.g:6560:1: ( rule__EdgeLayout__Alternatives_2 )
            // InternalGrana.g:6560:2: rule__EdgeLayout__Alternatives_2
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
    // InternalGrana.g:6570:1: rule__EdgeLayout__Group__3 : rule__EdgeLayout__Group__3__Impl ;
    public final void rule__EdgeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6574:1: ( rule__EdgeLayout__Group__3__Impl )
            // InternalGrana.g:6575:2: rule__EdgeLayout__Group__3__Impl
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
    // InternalGrana.g:6581:1: rule__EdgeLayout__Group__3__Impl : ( ']' ) ;
    public final void rule__EdgeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6585:1: ( ( ']' ) )
            // InternalGrana.g:6586:1: ( ']' )
            {
            // InternalGrana.g:6586:1: ( ']' )
            // InternalGrana.g:6587:1: ']'
            {
             before(grammarAccess.getEdgeLayoutAccess().getRightSquareBracketKeyword_3()); 
            match(input,48,FOLLOW_2); 
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
    // InternalGrana.g:6608:1: rule__ElkSingleEdgeSection__Group__0 : rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 ;
    public final void rule__ElkSingleEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6612:1: ( rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1 )
            // InternalGrana.g:6613:2: rule__ElkSingleEdgeSection__Group__0__Impl rule__ElkSingleEdgeSection__Group__1
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
    // InternalGrana.g:6620:1: rule__ElkSingleEdgeSection__Group__0__Impl : ( () ) ;
    public final void rule__ElkSingleEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6624:1: ( ( () ) )
            // InternalGrana.g:6625:1: ( () )
            {
            // InternalGrana.g:6625:1: ( () )
            // InternalGrana.g:6626:1: ()
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getElkEdgeSectionAction_0()); 
            // InternalGrana.g:6627:1: ()
            // InternalGrana.g:6629:1: 
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
    // InternalGrana.g:6639:1: rule__ElkSingleEdgeSection__Group__1 : rule__ElkSingleEdgeSection__Group__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6643:1: ( rule__ElkSingleEdgeSection__Group__1__Impl )
            // InternalGrana.g:6644:2: rule__ElkSingleEdgeSection__Group__1__Impl
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
    // InternalGrana.g:6650:1: rule__ElkSingleEdgeSection__Group__1__Impl : ( ( rule__ElkSingleEdgeSection__Group_1__0 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6654:1: ( ( ( rule__ElkSingleEdgeSection__Group_1__0 ) ) )
            // InternalGrana.g:6655:1: ( ( rule__ElkSingleEdgeSection__Group_1__0 ) )
            {
            // InternalGrana.g:6655:1: ( ( rule__ElkSingleEdgeSection__Group_1__0 ) )
            // InternalGrana.g:6656:1: ( rule__ElkSingleEdgeSection__Group_1__0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1()); 
            // InternalGrana.g:6657:1: ( rule__ElkSingleEdgeSection__Group_1__0 )
            // InternalGrana.g:6657:2: rule__ElkSingleEdgeSection__Group_1__0
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
    // InternalGrana.g:6671:1: rule__ElkSingleEdgeSection__Group_1__0 : rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6675:1: ( rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1 )
            // InternalGrana.g:6676:2: rule__ElkSingleEdgeSection__Group_1__0__Impl rule__ElkSingleEdgeSection__Group_1__1
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
    // InternalGrana.g:6683:1: rule__ElkSingleEdgeSection__Group_1__0__Impl : ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6687:1: ( ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) ) )
            // InternalGrana.g:6688:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) )
            {
            // InternalGrana.g:6688:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 ) )
            // InternalGrana.g:6689:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0()); 
            // InternalGrana.g:6690:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0 )
            // InternalGrana.g:6690:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0
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
    // InternalGrana.g:6700:1: rule__ElkSingleEdgeSection__Group_1__1 : rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6704:1: ( rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2 )
            // InternalGrana.g:6705:2: rule__ElkSingleEdgeSection__Group_1__1__Impl rule__ElkSingleEdgeSection__Group_1__2
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
    // InternalGrana.g:6712:1: rule__ElkSingleEdgeSection__Group_1__1__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6716:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? ) )
            // InternalGrana.g:6717:1: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? )
            {
            // InternalGrana.g:6717:1: ( ( rule__ElkSingleEdgeSection__Group_1_1__0 )? )
            // InternalGrana.g:6718:1: ( rule__ElkSingleEdgeSection__Group_1_1__0 )?
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1()); 
            // InternalGrana.g:6719:1: ( rule__ElkSingleEdgeSection__Group_1_1__0 )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==57) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalGrana.g:6719:2: rule__ElkSingleEdgeSection__Group_1_1__0
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
    // InternalGrana.g:6729:1: rule__ElkSingleEdgeSection__Group_1__2 : rule__ElkSingleEdgeSection__Group_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6733:1: ( rule__ElkSingleEdgeSection__Group_1__2__Impl )
            // InternalGrana.g:6734:2: rule__ElkSingleEdgeSection__Group_1__2__Impl
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
    // InternalGrana.g:6740:1: rule__ElkSingleEdgeSection__Group_1__2__Impl : ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6744:1: ( ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* ) )
            // InternalGrana.g:6745:1: ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* )
            {
            // InternalGrana.g:6745:1: ( ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )* )
            // InternalGrana.g:6746:1: ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getPropertiesAssignment_1_2()); 
            // InternalGrana.g:6747:1: ( rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 )*
            loop66:
            do {
                int alt66=2;
                int LA66_0 = input.LA(1);

                if ( (LA66_0==RULE_ID) ) {
                    alt66=1;
                }


                switch (alt66) {
            	case 1 :
            	    // InternalGrana.g:6747:2: rule__ElkSingleEdgeSection__PropertiesAssignment_1_2
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
    // InternalGrana.g:6763:1: rule__ElkSingleEdgeSection__Group_1_0_0__0 : rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6767:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1 )
            // InternalGrana.g:6768:2: rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl rule__ElkSingleEdgeSection__Group_1_0_0__1
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
    // InternalGrana.g:6775:1: rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6779:1: ( ( 'incoming' ) )
            // InternalGrana.g:6780:1: ( 'incoming' )
            {
            // InternalGrana.g:6780:1: ( 'incoming' )
            // InternalGrana.g:6781:1: 'incoming'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingKeyword_1_0_0_0()); 
            match(input,53,FOLLOW_2); 
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
    // InternalGrana.g:6794:1: rule__ElkSingleEdgeSection__Group_1_0_0__1 : rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6798:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2 )
            // InternalGrana.g:6799:2: rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl rule__ElkSingleEdgeSection__Group_1_0_0__2
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
    // InternalGrana.g:6806:1: rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6810:1: ( ( ':' ) )
            // InternalGrana.g:6811:1: ( ':' )
            {
            // InternalGrana.g:6811:1: ( ':' )
            // InternalGrana.g:6812:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_0_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:6825:1: rule__ElkSingleEdgeSection__Group_1_0_0__2 : rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6829:1: ( rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl )
            // InternalGrana.g:6830:2: rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl
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
    // InternalGrana.g:6836:1: rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl : ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6840:1: ( ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) ) )
            // InternalGrana.g:6841:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) )
            {
            // InternalGrana.g:6841:1: ( ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 ) )
            // InternalGrana.g:6842:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeAssignment_1_0_0_2()); 
            // InternalGrana.g:6843:1: ( rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 )
            // InternalGrana.g:6843:2: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2
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
    // InternalGrana.g:6859:1: rule__ElkSingleEdgeSection__Group_1_0_1__0 : rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6863:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1 )
            // InternalGrana.g:6864:2: rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl rule__ElkSingleEdgeSection__Group_1_0_1__1
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
    // InternalGrana.g:6871:1: rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6875:1: ( ( 'outgoing' ) )
            // InternalGrana.g:6876:1: ( 'outgoing' )
            {
            // InternalGrana.g:6876:1: ( 'outgoing' )
            // InternalGrana.g:6877:1: 'outgoing'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingKeyword_1_0_1_0()); 
            match(input,54,FOLLOW_2); 
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
    // InternalGrana.g:6890:1: rule__ElkSingleEdgeSection__Group_1_0_1__1 : rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6894:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2 )
            // InternalGrana.g:6895:2: rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl rule__ElkSingleEdgeSection__Group_1_0_1__2
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
    // InternalGrana.g:6902:1: rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6906:1: ( ( ':' ) )
            // InternalGrana.g:6907:1: ( ':' )
            {
            // InternalGrana.g:6907:1: ( ':' )
            // InternalGrana.g:6908:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_1_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:6921:1: rule__ElkSingleEdgeSection__Group_1_0_1__2 : rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6925:1: ( rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl )
            // InternalGrana.g:6926:2: rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl
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
    // InternalGrana.g:6932:1: rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl : ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6936:1: ( ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) ) )
            // InternalGrana.g:6937:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) )
            {
            // InternalGrana.g:6937:1: ( ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 ) )
            // InternalGrana.g:6938:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeAssignment_1_0_1_2()); 
            // InternalGrana.g:6939:1: ( rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 )
            // InternalGrana.g:6939:2: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2
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
    // InternalGrana.g:6955:1: rule__ElkSingleEdgeSection__Group_1_0_2__0 : rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6959:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1 )
            // InternalGrana.g:6960:2: rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl rule__ElkSingleEdgeSection__Group_1_0_2__1
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
    // InternalGrana.g:6967:1: rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6971:1: ( ( 'start' ) )
            // InternalGrana.g:6972:1: ( 'start' )
            {
            // InternalGrana.g:6972:1: ( 'start' )
            // InternalGrana.g:6973:1: 'start'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartKeyword_1_0_2_0()); 
            match(input,55,FOLLOW_2); 
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
    // InternalGrana.g:6986:1: rule__ElkSingleEdgeSection__Group_1_0_2__1 : rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:6990:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2 )
            // InternalGrana.g:6991:2: rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl rule__ElkSingleEdgeSection__Group_1_0_2__2
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
    // InternalGrana.g:6998:1: rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7002:1: ( ( ':' ) )
            // InternalGrana.g:7003:1: ( ':' )
            {
            // InternalGrana.g:7003:1: ( ':' )
            // InternalGrana.g:7004:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_2_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:7017:1: rule__ElkSingleEdgeSection__Group_1_0_2__2 : rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7021:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3 )
            // InternalGrana.g:7022:2: rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl rule__ElkSingleEdgeSection__Group_1_0_2__3
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
    // InternalGrana.g:7029:1: rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl : ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7033:1: ( ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) ) )
            // InternalGrana.g:7034:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) )
            {
            // InternalGrana.g:7034:1: ( ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 ) )
            // InternalGrana.g:7035:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartXAssignment_1_0_2_2()); 
            // InternalGrana.g:7036:1: ( rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 )
            // InternalGrana.g:7036:2: rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2
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
    // InternalGrana.g:7046:1: rule__ElkSingleEdgeSection__Group_1_0_2__3 : rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7050:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4 )
            // InternalGrana.g:7051:2: rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl rule__ElkSingleEdgeSection__Group_1_0_2__4
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
    // InternalGrana.g:7058:1: rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7062:1: ( ( ',' ) )
            // InternalGrana.g:7063:1: ( ',' )
            {
            // InternalGrana.g:7063:1: ( ',' )
            // InternalGrana.g:7064:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_2_3()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:7077:1: rule__ElkSingleEdgeSection__Group_1_0_2__4 : rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7081:1: ( rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl )
            // InternalGrana.g:7082:2: rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl
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
    // InternalGrana.g:7088:1: rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl : ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7092:1: ( ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) ) )
            // InternalGrana.g:7093:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) )
            {
            // InternalGrana.g:7093:1: ( ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 ) )
            // InternalGrana.g:7094:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getStartYAssignment_1_0_2_4()); 
            // InternalGrana.g:7095:1: ( rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 )
            // InternalGrana.g:7095:2: rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4
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
    // InternalGrana.g:7115:1: rule__ElkSingleEdgeSection__Group_1_0_3__0 : rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7119:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1 )
            // InternalGrana.g:7120:2: rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl rule__ElkSingleEdgeSection__Group_1_0_3__1
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
    // InternalGrana.g:7127:1: rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7131:1: ( ( 'end' ) )
            // InternalGrana.g:7132:1: ( 'end' )
            {
            // InternalGrana.g:7132:1: ( 'end' )
            // InternalGrana.g:7133:1: 'end'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndKeyword_1_0_3_0()); 
            match(input,56,FOLLOW_2); 
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
    // InternalGrana.g:7146:1: rule__ElkSingleEdgeSection__Group_1_0_3__1 : rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7150:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2 )
            // InternalGrana.g:7151:2: rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl rule__ElkSingleEdgeSection__Group_1_0_3__2
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
    // InternalGrana.g:7158:1: rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7162:1: ( ( ':' ) )
            // InternalGrana.g:7163:1: ( ':' )
            {
            // InternalGrana.g:7163:1: ( ':' )
            // InternalGrana.g:7164:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_0_3_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:7177:1: rule__ElkSingleEdgeSection__Group_1_0_3__2 : rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7181:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3 )
            // InternalGrana.g:7182:2: rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl rule__ElkSingleEdgeSection__Group_1_0_3__3
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
    // InternalGrana.g:7189:1: rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl : ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7193:1: ( ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) ) )
            // InternalGrana.g:7194:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) )
            {
            // InternalGrana.g:7194:1: ( ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 ) )
            // InternalGrana.g:7195:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndXAssignment_1_0_3_2()); 
            // InternalGrana.g:7196:1: ( rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 )
            // InternalGrana.g:7196:2: rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2
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
    // InternalGrana.g:7206:1: rule__ElkSingleEdgeSection__Group_1_0_3__3 : rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4 ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7210:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4 )
            // InternalGrana.g:7211:2: rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl rule__ElkSingleEdgeSection__Group_1_0_3__4
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
    // InternalGrana.g:7218:1: rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl : ( ',' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7222:1: ( ( ',' ) )
            // InternalGrana.g:7223:1: ( ',' )
            {
            // InternalGrana.g:7223:1: ( ',' )
            // InternalGrana.g:7224:1: ','
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getCommaKeyword_1_0_3_3()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:7237:1: rule__ElkSingleEdgeSection__Group_1_0_3__4 : rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7241:1: ( rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl )
            // InternalGrana.g:7242:2: rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl
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
    // InternalGrana.g:7248:1: rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl : ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_0_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7252:1: ( ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) ) )
            // InternalGrana.g:7253:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) )
            {
            // InternalGrana.g:7253:1: ( ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 ) )
            // InternalGrana.g:7254:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getEndYAssignment_1_0_3_4()); 
            // InternalGrana.g:7255:1: ( rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 )
            // InternalGrana.g:7255:2: rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4
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
    // InternalGrana.g:7275:1: rule__ElkSingleEdgeSection__Group_1_1__0 : rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7279:1: ( rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1 )
            // InternalGrana.g:7280:2: rule__ElkSingleEdgeSection__Group_1_1__0__Impl rule__ElkSingleEdgeSection__Group_1_1__1
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
    // InternalGrana.g:7287:1: rule__ElkSingleEdgeSection__Group_1_1__0__Impl : ( 'bends' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7291:1: ( ( 'bends' ) )
            // InternalGrana.g:7292:1: ( 'bends' )
            {
            // InternalGrana.g:7292:1: ( 'bends' )
            // InternalGrana.g:7293:1: 'bends'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendsKeyword_1_1_0()); 
            match(input,57,FOLLOW_2); 
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
    // InternalGrana.g:7306:1: rule__ElkSingleEdgeSection__Group_1_1__1 : rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7310:1: ( rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2 )
            // InternalGrana.g:7311:2: rule__ElkSingleEdgeSection__Group_1_1__1__Impl rule__ElkSingleEdgeSection__Group_1_1__2
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
    // InternalGrana.g:7318:1: rule__ElkSingleEdgeSection__Group_1_1__1__Impl : ( ':' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7322:1: ( ( ':' ) )
            // InternalGrana.g:7323:1: ( ':' )
            {
            // InternalGrana.g:7323:1: ( ':' )
            // InternalGrana.g:7324:1: ':'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getColonKeyword_1_1_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:7337:1: rule__ElkSingleEdgeSection__Group_1_1__2 : rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7341:1: ( rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3 )
            // InternalGrana.g:7342:2: rule__ElkSingleEdgeSection__Group_1_1__2__Impl rule__ElkSingleEdgeSection__Group_1_1__3
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
    // InternalGrana.g:7349:1: rule__ElkSingleEdgeSection__Group_1_1__2__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7353:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) ) )
            // InternalGrana.g:7354:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) )
            {
            // InternalGrana.g:7354:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 ) )
            // InternalGrana.g:7355:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_2()); 
            // InternalGrana.g:7356:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 )
            // InternalGrana.g:7356:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2
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
    // InternalGrana.g:7366:1: rule__ElkSingleEdgeSection__Group_1_1__3 : rule__ElkSingleEdgeSection__Group_1_1__3__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7370:1: ( rule__ElkSingleEdgeSection__Group_1_1__3__Impl )
            // InternalGrana.g:7371:2: rule__ElkSingleEdgeSection__Group_1_1__3__Impl
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
    // InternalGrana.g:7377:1: rule__ElkSingleEdgeSection__Group_1_1__3__Impl : ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7381:1: ( ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* ) )
            // InternalGrana.g:7382:1: ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* )
            {
            // InternalGrana.g:7382:1: ( ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )* )
            // InternalGrana.g:7383:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )*
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_1_3()); 
            // InternalGrana.g:7384:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0 )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==58) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // InternalGrana.g:7384:2: rule__ElkSingleEdgeSection__Group_1_1_3__0
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
    // InternalGrana.g:7402:1: rule__ElkSingleEdgeSection__Group_1_1_3__0 : rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1 ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7406:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1 )
            // InternalGrana.g:7407:2: rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl rule__ElkSingleEdgeSection__Group_1_1_3__1
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
    // InternalGrana.g:7414:1: rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl : ( '|' ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7418:1: ( ( '|' ) )
            // InternalGrana.g:7419:1: ( '|' )
            {
            // InternalGrana.g:7419:1: ( '|' )
            // InternalGrana.g:7420:1: '|'
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getVerticalLineKeyword_1_1_3_0()); 
            match(input,58,FOLLOW_2); 
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
    // InternalGrana.g:7433:1: rule__ElkSingleEdgeSection__Group_1_1_3__1 : rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7437:1: ( rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl )
            // InternalGrana.g:7438:2: rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl
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
    // InternalGrana.g:7444:1: rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl : ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) ) ;
    public final void rule__ElkSingleEdgeSection__Group_1_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7448:1: ( ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) ) )
            // InternalGrana.g:7449:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) )
            {
            // InternalGrana.g:7449:1: ( ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 ) )
            // InternalGrana.g:7450:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getBendPointsAssignment_1_1_3_1()); 
            // InternalGrana.g:7451:1: ( rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 )
            // InternalGrana.g:7451:2: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1
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
    // InternalGrana.g:7465:1: rule__ElkEdgeSection__Group__0 : rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 ;
    public final void rule__ElkEdgeSection__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7469:1: ( rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1 )
            // InternalGrana.g:7470:2: rule__ElkEdgeSection__Group__0__Impl rule__ElkEdgeSection__Group__1
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
    // InternalGrana.g:7477:1: rule__ElkEdgeSection__Group__0__Impl : ( 'section' ) ;
    public final void rule__ElkEdgeSection__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7481:1: ( ( 'section' ) )
            // InternalGrana.g:7482:1: ( 'section' )
            {
            // InternalGrana.g:7482:1: ( 'section' )
            // InternalGrana.g:7483:1: 'section'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getSectionKeyword_0()); 
            match(input,59,FOLLOW_2); 
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
    // InternalGrana.g:7496:1: rule__ElkEdgeSection__Group__1 : rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 ;
    public final void rule__ElkEdgeSection__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7500:1: ( rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2 )
            // InternalGrana.g:7501:2: rule__ElkEdgeSection__Group__1__Impl rule__ElkEdgeSection__Group__2
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
    // InternalGrana.g:7508:1: rule__ElkEdgeSection__Group__1__Impl : ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) ;
    public final void rule__ElkEdgeSection__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7512:1: ( ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) ) )
            // InternalGrana.g:7513:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            {
            // InternalGrana.g:7513:1: ( ( rule__ElkEdgeSection__IdentifierAssignment_1 ) )
            // InternalGrana.g:7514:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIdentifierAssignment_1()); 
            // InternalGrana.g:7515:1: ( rule__ElkEdgeSection__IdentifierAssignment_1 )
            // InternalGrana.g:7515:2: rule__ElkEdgeSection__IdentifierAssignment_1
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
    // InternalGrana.g:7525:1: rule__ElkEdgeSection__Group__2 : rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 ;
    public final void rule__ElkEdgeSection__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7529:1: ( rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3 )
            // InternalGrana.g:7530:2: rule__ElkEdgeSection__Group__2__Impl rule__ElkEdgeSection__Group__3
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
    // InternalGrana.g:7537:1: rule__ElkEdgeSection__Group__2__Impl : ( ( rule__ElkEdgeSection__Group_2__0 )? ) ;
    public final void rule__ElkEdgeSection__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7541:1: ( ( ( rule__ElkEdgeSection__Group_2__0 )? ) )
            // InternalGrana.g:7542:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            {
            // InternalGrana.g:7542:1: ( ( rule__ElkEdgeSection__Group_2__0 )? )
            // InternalGrana.g:7543:1: ( rule__ElkEdgeSection__Group_2__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2()); 
            // InternalGrana.g:7544:1: ( rule__ElkEdgeSection__Group_2__0 )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==52) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalGrana.g:7544:2: rule__ElkEdgeSection__Group_2__0
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
    // InternalGrana.g:7554:1: rule__ElkEdgeSection__Group__3 : rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 ;
    public final void rule__ElkEdgeSection__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7558:1: ( rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4 )
            // InternalGrana.g:7559:2: rule__ElkEdgeSection__Group__3__Impl rule__ElkEdgeSection__Group__4
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
    // InternalGrana.g:7566:1: rule__ElkEdgeSection__Group__3__Impl : ( '[' ) ;
    public final void rule__ElkEdgeSection__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7570:1: ( ( '[' ) )
            // InternalGrana.g:7571:1: ( '[' )
            {
            // InternalGrana.g:7571:1: ( '[' )
            // InternalGrana.g:7572:1: '['
            {
             before(grammarAccess.getElkEdgeSectionAccess().getLeftSquareBracketKeyword_3()); 
            match(input,47,FOLLOW_2); 
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
    // InternalGrana.g:7585:1: rule__ElkEdgeSection__Group__4 : rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 ;
    public final void rule__ElkEdgeSection__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7589:1: ( rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5 )
            // InternalGrana.g:7590:2: rule__ElkEdgeSection__Group__4__Impl rule__ElkEdgeSection__Group__5
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
    // InternalGrana.g:7597:1: rule__ElkEdgeSection__Group__4__Impl : ( ( rule__ElkEdgeSection__Group_4__0 ) ) ;
    public final void rule__ElkEdgeSection__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7601:1: ( ( ( rule__ElkEdgeSection__Group_4__0 ) ) )
            // InternalGrana.g:7602:1: ( ( rule__ElkEdgeSection__Group_4__0 ) )
            {
            // InternalGrana.g:7602:1: ( ( rule__ElkEdgeSection__Group_4__0 ) )
            // InternalGrana.g:7603:1: ( rule__ElkEdgeSection__Group_4__0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4()); 
            // InternalGrana.g:7604:1: ( rule__ElkEdgeSection__Group_4__0 )
            // InternalGrana.g:7604:2: rule__ElkEdgeSection__Group_4__0
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
    // InternalGrana.g:7614:1: rule__ElkEdgeSection__Group__5 : rule__ElkEdgeSection__Group__5__Impl ;
    public final void rule__ElkEdgeSection__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7618:1: ( rule__ElkEdgeSection__Group__5__Impl )
            // InternalGrana.g:7619:2: rule__ElkEdgeSection__Group__5__Impl
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
    // InternalGrana.g:7625:1: rule__ElkEdgeSection__Group__5__Impl : ( ']' ) ;
    public final void rule__ElkEdgeSection__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7629:1: ( ( ']' ) )
            // InternalGrana.g:7630:1: ( ']' )
            {
            // InternalGrana.g:7630:1: ( ']' )
            // InternalGrana.g:7631:1: ']'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getRightSquareBracketKeyword_5()); 
            match(input,48,FOLLOW_2); 
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
    // InternalGrana.g:7656:1: rule__ElkEdgeSection__Group_2__0 : rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 ;
    public final void rule__ElkEdgeSection__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7660:1: ( rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1 )
            // InternalGrana.g:7661:2: rule__ElkEdgeSection__Group_2__0__Impl rule__ElkEdgeSection__Group_2__1
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
    // InternalGrana.g:7668:1: rule__ElkEdgeSection__Group_2__0__Impl : ( '->' ) ;
    public final void rule__ElkEdgeSection__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7672:1: ( ( '->' ) )
            // InternalGrana.g:7673:1: ( '->' )
            {
            // InternalGrana.g:7673:1: ( '->' )
            // InternalGrana.g:7674:1: '->'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getHyphenMinusGreaterThanSignKeyword_2_0()); 
            match(input,52,FOLLOW_2); 
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
    // InternalGrana.g:7687:1: rule__ElkEdgeSection__Group_2__1 : rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 ;
    public final void rule__ElkEdgeSection__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7691:1: ( rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2 )
            // InternalGrana.g:7692:2: rule__ElkEdgeSection__Group_2__1__Impl rule__ElkEdgeSection__Group_2__2
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
    // InternalGrana.g:7699:1: rule__ElkEdgeSection__Group_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7703:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) ) )
            // InternalGrana.g:7704:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            {
            // InternalGrana.g:7704:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 ) )
            // InternalGrana.g:7705:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_1()); 
            // InternalGrana.g:7706:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 )
            // InternalGrana.g:7706:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1
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
    // InternalGrana.g:7716:1: rule__ElkEdgeSection__Group_2__2 : rule__ElkEdgeSection__Group_2__2__Impl ;
    public final void rule__ElkEdgeSection__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7720:1: ( rule__ElkEdgeSection__Group_2__2__Impl )
            // InternalGrana.g:7721:2: rule__ElkEdgeSection__Group_2__2__Impl
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
    // InternalGrana.g:7727:1: rule__ElkEdgeSection__Group_2__2__Impl : ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7731:1: ( ( ( rule__ElkEdgeSection__Group_2_2__0 )* ) )
            // InternalGrana.g:7732:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            {
            // InternalGrana.g:7732:1: ( ( rule__ElkEdgeSection__Group_2_2__0 )* )
            // InternalGrana.g:7733:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_2_2()); 
            // InternalGrana.g:7734:1: ( rule__ElkEdgeSection__Group_2_2__0 )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==33) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // InternalGrana.g:7734:2: rule__ElkEdgeSection__Group_2_2__0
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
    // InternalGrana.g:7750:1: rule__ElkEdgeSection__Group_2_2__0 : rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 ;
    public final void rule__ElkEdgeSection__Group_2_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7754:1: ( rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1 )
            // InternalGrana.g:7755:2: rule__ElkEdgeSection__Group_2_2__0__Impl rule__ElkEdgeSection__Group_2_2__1
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
    // InternalGrana.g:7762:1: rule__ElkEdgeSection__Group_2_2__0__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_2_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7766:1: ( ( ',' ) )
            // InternalGrana.g:7767:1: ( ',' )
            {
            // InternalGrana.g:7767:1: ( ',' )
            // InternalGrana.g:7768:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_2_2_0()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:7781:1: rule__ElkEdgeSection__Group_2_2__1 : rule__ElkEdgeSection__Group_2_2__1__Impl ;
    public final void rule__ElkEdgeSection__Group_2_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7785:1: ( rule__ElkEdgeSection__Group_2_2__1__Impl )
            // InternalGrana.g:7786:2: rule__ElkEdgeSection__Group_2_2__1__Impl
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
    // InternalGrana.g:7792:1: rule__ElkEdgeSection__Group_2_2__1__Impl : ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_2_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7796:1: ( ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) ) )
            // InternalGrana.g:7797:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            {
            // InternalGrana.g:7797:1: ( ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 ) )
            // InternalGrana.g:7798:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsAssignment_2_2_1()); 
            // InternalGrana.g:7799:1: ( rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 )
            // InternalGrana.g:7799:2: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1
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
    // InternalGrana.g:7813:1: rule__ElkEdgeSection__Group_4__0 : rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1 ;
    public final void rule__ElkEdgeSection__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7817:1: ( rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1 )
            // InternalGrana.g:7818:2: rule__ElkEdgeSection__Group_4__0__Impl rule__ElkEdgeSection__Group_4__1
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
    // InternalGrana.g:7825:1: rule__ElkEdgeSection__Group_4__0__Impl : ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) ) ;
    public final void rule__ElkEdgeSection__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7829:1: ( ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) ) )
            // InternalGrana.g:7830:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) )
            {
            // InternalGrana.g:7830:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0 ) )
            // InternalGrana.g:7831:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0()); 
            // InternalGrana.g:7832:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0 )
            // InternalGrana.g:7832:2: rule__ElkEdgeSection__UnorderedGroup_4_0
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
    // InternalGrana.g:7842:1: rule__ElkEdgeSection__Group_4__1 : rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2 ;
    public final void rule__ElkEdgeSection__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7846:1: ( rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2 )
            // InternalGrana.g:7847:2: rule__ElkEdgeSection__Group_4__1__Impl rule__ElkEdgeSection__Group_4__2
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
    // InternalGrana.g:7854:1: rule__ElkEdgeSection__Group_4__1__Impl : ( ( rule__ElkEdgeSection__Group_4_1__0 )? ) ;
    public final void rule__ElkEdgeSection__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7858:1: ( ( ( rule__ElkEdgeSection__Group_4_1__0 )? ) )
            // InternalGrana.g:7859:1: ( ( rule__ElkEdgeSection__Group_4_1__0 )? )
            {
            // InternalGrana.g:7859:1: ( ( rule__ElkEdgeSection__Group_4_1__0 )? )
            // InternalGrana.g:7860:1: ( rule__ElkEdgeSection__Group_4_1__0 )?
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1()); 
            // InternalGrana.g:7861:1: ( rule__ElkEdgeSection__Group_4_1__0 )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==57) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalGrana.g:7861:2: rule__ElkEdgeSection__Group_4_1__0
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
    // InternalGrana.g:7871:1: rule__ElkEdgeSection__Group_4__2 : rule__ElkEdgeSection__Group_4__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7875:1: ( rule__ElkEdgeSection__Group_4__2__Impl )
            // InternalGrana.g:7876:2: rule__ElkEdgeSection__Group_4__2__Impl
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
    // InternalGrana.g:7882:1: rule__ElkEdgeSection__Group_4__2__Impl : ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* ) ;
    public final void rule__ElkEdgeSection__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7886:1: ( ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* ) )
            // InternalGrana.g:7887:1: ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* )
            {
            // InternalGrana.g:7887:1: ( ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )* )
            // InternalGrana.g:7888:1: ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getPropertiesAssignment_4_2()); 
            // InternalGrana.g:7889:1: ( rule__ElkEdgeSection__PropertiesAssignment_4_2 )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==RULE_ID) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // InternalGrana.g:7889:2: rule__ElkEdgeSection__PropertiesAssignment_4_2
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
    // InternalGrana.g:7905:1: rule__ElkEdgeSection__Group_4_0_0__0 : rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7909:1: ( rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1 )
            // InternalGrana.g:7910:2: rule__ElkEdgeSection__Group_4_0_0__0__Impl rule__ElkEdgeSection__Group_4_0_0__1
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
    // InternalGrana.g:7917:1: rule__ElkEdgeSection__Group_4_0_0__0__Impl : ( 'incoming' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7921:1: ( ( 'incoming' ) )
            // InternalGrana.g:7922:1: ( 'incoming' )
            {
            // InternalGrana.g:7922:1: ( 'incoming' )
            // InternalGrana.g:7923:1: 'incoming'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingKeyword_4_0_0_0()); 
            match(input,53,FOLLOW_2); 
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
    // InternalGrana.g:7936:1: rule__ElkEdgeSection__Group_4_0_0__1 : rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7940:1: ( rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2 )
            // InternalGrana.g:7941:2: rule__ElkEdgeSection__Group_4_0_0__1__Impl rule__ElkEdgeSection__Group_4_0_0__2
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
    // InternalGrana.g:7948:1: rule__ElkEdgeSection__Group_4_0_0__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7952:1: ( ( ':' ) )
            // InternalGrana.g:7953:1: ( ':' )
            {
            // InternalGrana.g:7953:1: ( ':' )
            // InternalGrana.g:7954:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_0_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:7967:1: rule__ElkEdgeSection__Group_4_0_0__2 : rule__ElkEdgeSection__Group_4_0_0__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7971:1: ( rule__ElkEdgeSection__Group_4_0_0__2__Impl )
            // InternalGrana.g:7972:2: rule__ElkEdgeSection__Group_4_0_0__2__Impl
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
    // InternalGrana.g:7978:1: rule__ElkEdgeSection__Group_4_0_0__2__Impl : ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:7982:1: ( ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) ) )
            // InternalGrana.g:7983:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) )
            {
            // InternalGrana.g:7983:1: ( ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 ) )
            // InternalGrana.g:7984:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeAssignment_4_0_0_2()); 
            // InternalGrana.g:7985:1: ( rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 )
            // InternalGrana.g:7985:2: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2
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
    // InternalGrana.g:8001:1: rule__ElkEdgeSection__Group_4_0_1__0 : rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8005:1: ( rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1 )
            // InternalGrana.g:8006:2: rule__ElkEdgeSection__Group_4_0_1__0__Impl rule__ElkEdgeSection__Group_4_0_1__1
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
    // InternalGrana.g:8013:1: rule__ElkEdgeSection__Group_4_0_1__0__Impl : ( 'outgoing' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8017:1: ( ( 'outgoing' ) )
            // InternalGrana.g:8018:1: ( 'outgoing' )
            {
            // InternalGrana.g:8018:1: ( 'outgoing' )
            // InternalGrana.g:8019:1: 'outgoing'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingKeyword_4_0_1_0()); 
            match(input,54,FOLLOW_2); 
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
    // InternalGrana.g:8032:1: rule__ElkEdgeSection__Group_4_0_1__1 : rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8036:1: ( rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2 )
            // InternalGrana.g:8037:2: rule__ElkEdgeSection__Group_4_0_1__1__Impl rule__ElkEdgeSection__Group_4_0_1__2
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
    // InternalGrana.g:8044:1: rule__ElkEdgeSection__Group_4_0_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8048:1: ( ( ':' ) )
            // InternalGrana.g:8049:1: ( ':' )
            {
            // InternalGrana.g:8049:1: ( ':' )
            // InternalGrana.g:8050:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_1_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:8063:1: rule__ElkEdgeSection__Group_4_0_1__2 : rule__ElkEdgeSection__Group_4_0_1__2__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8067:1: ( rule__ElkEdgeSection__Group_4_0_1__2__Impl )
            // InternalGrana.g:8068:2: rule__ElkEdgeSection__Group_4_0_1__2__Impl
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
    // InternalGrana.g:8074:1: rule__ElkEdgeSection__Group_4_0_1__2__Impl : ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8078:1: ( ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) ) )
            // InternalGrana.g:8079:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) )
            {
            // InternalGrana.g:8079:1: ( ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 ) )
            // InternalGrana.g:8080:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeAssignment_4_0_1_2()); 
            // InternalGrana.g:8081:1: ( rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 )
            // InternalGrana.g:8081:2: rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2
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
    // InternalGrana.g:8097:1: rule__ElkEdgeSection__Group_4_0_2__0 : rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8101:1: ( rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1 )
            // InternalGrana.g:8102:2: rule__ElkEdgeSection__Group_4_0_2__0__Impl rule__ElkEdgeSection__Group_4_0_2__1
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
    // InternalGrana.g:8109:1: rule__ElkEdgeSection__Group_4_0_2__0__Impl : ( 'start' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8113:1: ( ( 'start' ) )
            // InternalGrana.g:8114:1: ( 'start' )
            {
            // InternalGrana.g:8114:1: ( 'start' )
            // InternalGrana.g:8115:1: 'start'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartKeyword_4_0_2_0()); 
            match(input,55,FOLLOW_2); 
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
    // InternalGrana.g:8128:1: rule__ElkEdgeSection__Group_4_0_2__1 : rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8132:1: ( rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2 )
            // InternalGrana.g:8133:2: rule__ElkEdgeSection__Group_4_0_2__1__Impl rule__ElkEdgeSection__Group_4_0_2__2
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
    // InternalGrana.g:8140:1: rule__ElkEdgeSection__Group_4_0_2__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8144:1: ( ( ':' ) )
            // InternalGrana.g:8145:1: ( ':' )
            {
            // InternalGrana.g:8145:1: ( ':' )
            // InternalGrana.g:8146:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_2_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:8159:1: rule__ElkEdgeSection__Group_4_0_2__2 : rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8163:1: ( rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3 )
            // InternalGrana.g:8164:2: rule__ElkEdgeSection__Group_4_0_2__2__Impl rule__ElkEdgeSection__Group_4_0_2__3
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
    // InternalGrana.g:8171:1: rule__ElkEdgeSection__Group_4_0_2__2__Impl : ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8175:1: ( ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) ) )
            // InternalGrana.g:8176:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) )
            {
            // InternalGrana.g:8176:1: ( ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 ) )
            // InternalGrana.g:8177:1: ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartXAssignment_4_0_2_2()); 
            // InternalGrana.g:8178:1: ( rule__ElkEdgeSection__StartXAssignment_4_0_2_2 )
            // InternalGrana.g:8178:2: rule__ElkEdgeSection__StartXAssignment_4_0_2_2
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
    // InternalGrana.g:8188:1: rule__ElkEdgeSection__Group_4_0_2__3 : rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4 ;
    public final void rule__ElkEdgeSection__Group_4_0_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8192:1: ( rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4 )
            // InternalGrana.g:8193:2: rule__ElkEdgeSection__Group_4_0_2__3__Impl rule__ElkEdgeSection__Group_4_0_2__4
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
    // InternalGrana.g:8200:1: rule__ElkEdgeSection__Group_4_0_2__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8204:1: ( ( ',' ) )
            // InternalGrana.g:8205:1: ( ',' )
            {
            // InternalGrana.g:8205:1: ( ',' )
            // InternalGrana.g:8206:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_2_3()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:8219:1: rule__ElkEdgeSection__Group_4_0_2__4 : rule__ElkEdgeSection__Group_4_0_2__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_2__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8223:1: ( rule__ElkEdgeSection__Group_4_0_2__4__Impl )
            // InternalGrana.g:8224:2: rule__ElkEdgeSection__Group_4_0_2__4__Impl
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
    // InternalGrana.g:8230:1: rule__ElkEdgeSection__Group_4_0_2__4__Impl : ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_2__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8234:1: ( ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) ) )
            // InternalGrana.g:8235:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) )
            {
            // InternalGrana.g:8235:1: ( ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 ) )
            // InternalGrana.g:8236:1: ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getStartYAssignment_4_0_2_4()); 
            // InternalGrana.g:8237:1: ( rule__ElkEdgeSection__StartYAssignment_4_0_2_4 )
            // InternalGrana.g:8237:2: rule__ElkEdgeSection__StartYAssignment_4_0_2_4
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
    // InternalGrana.g:8257:1: rule__ElkEdgeSection__Group_4_0_3__0 : rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8261:1: ( rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1 )
            // InternalGrana.g:8262:2: rule__ElkEdgeSection__Group_4_0_3__0__Impl rule__ElkEdgeSection__Group_4_0_3__1
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
    // InternalGrana.g:8269:1: rule__ElkEdgeSection__Group_4_0_3__0__Impl : ( 'end' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8273:1: ( ( 'end' ) )
            // InternalGrana.g:8274:1: ( 'end' )
            {
            // InternalGrana.g:8274:1: ( 'end' )
            // InternalGrana.g:8275:1: 'end'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndKeyword_4_0_3_0()); 
            match(input,56,FOLLOW_2); 
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
    // InternalGrana.g:8288:1: rule__ElkEdgeSection__Group_4_0_3__1 : rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8292:1: ( rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2 )
            // InternalGrana.g:8293:2: rule__ElkEdgeSection__Group_4_0_3__1__Impl rule__ElkEdgeSection__Group_4_0_3__2
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
    // InternalGrana.g:8300:1: rule__ElkEdgeSection__Group_4_0_3__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8304:1: ( ( ':' ) )
            // InternalGrana.g:8305:1: ( ':' )
            {
            // InternalGrana.g:8305:1: ( ':' )
            // InternalGrana.g:8306:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_0_3_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:8319:1: rule__ElkEdgeSection__Group_4_0_3__2 : rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8323:1: ( rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3 )
            // InternalGrana.g:8324:2: rule__ElkEdgeSection__Group_4_0_3__2__Impl rule__ElkEdgeSection__Group_4_0_3__3
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
    // InternalGrana.g:8331:1: rule__ElkEdgeSection__Group_4_0_3__2__Impl : ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8335:1: ( ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) ) )
            // InternalGrana.g:8336:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) )
            {
            // InternalGrana.g:8336:1: ( ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 ) )
            // InternalGrana.g:8337:1: ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndXAssignment_4_0_3_2()); 
            // InternalGrana.g:8338:1: ( rule__ElkEdgeSection__EndXAssignment_4_0_3_2 )
            // InternalGrana.g:8338:2: rule__ElkEdgeSection__EndXAssignment_4_0_3_2
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
    // InternalGrana.g:8348:1: rule__ElkEdgeSection__Group_4_0_3__3 : rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4 ;
    public final void rule__ElkEdgeSection__Group_4_0_3__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8352:1: ( rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4 )
            // InternalGrana.g:8353:2: rule__ElkEdgeSection__Group_4_0_3__3__Impl rule__ElkEdgeSection__Group_4_0_3__4
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
    // InternalGrana.g:8360:1: rule__ElkEdgeSection__Group_4_0_3__3__Impl : ( ',' ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8364:1: ( ( ',' ) )
            // InternalGrana.g:8365:1: ( ',' )
            {
            // InternalGrana.g:8365:1: ( ',' )
            // InternalGrana.g:8366:1: ','
            {
             before(grammarAccess.getElkEdgeSectionAccess().getCommaKeyword_4_0_3_3()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:8379:1: rule__ElkEdgeSection__Group_4_0_3__4 : rule__ElkEdgeSection__Group_4_0_3__4__Impl ;
    public final void rule__ElkEdgeSection__Group_4_0_3__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8383:1: ( rule__ElkEdgeSection__Group_4_0_3__4__Impl )
            // InternalGrana.g:8384:2: rule__ElkEdgeSection__Group_4_0_3__4__Impl
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
    // InternalGrana.g:8390:1: rule__ElkEdgeSection__Group_4_0_3__4__Impl : ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_0_3__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8394:1: ( ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) ) )
            // InternalGrana.g:8395:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) )
            {
            // InternalGrana.g:8395:1: ( ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 ) )
            // InternalGrana.g:8396:1: ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getEndYAssignment_4_0_3_4()); 
            // InternalGrana.g:8397:1: ( rule__ElkEdgeSection__EndYAssignment_4_0_3_4 )
            // InternalGrana.g:8397:2: rule__ElkEdgeSection__EndYAssignment_4_0_3_4
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
    // InternalGrana.g:8417:1: rule__ElkEdgeSection__Group_4_1__0 : rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 ;
    public final void rule__ElkEdgeSection__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8421:1: ( rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1 )
            // InternalGrana.g:8422:2: rule__ElkEdgeSection__Group_4_1__0__Impl rule__ElkEdgeSection__Group_4_1__1
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
    // InternalGrana.g:8429:1: rule__ElkEdgeSection__Group_4_1__0__Impl : ( 'bends' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8433:1: ( ( 'bends' ) )
            // InternalGrana.g:8434:1: ( 'bends' )
            {
            // InternalGrana.g:8434:1: ( 'bends' )
            // InternalGrana.g:8435:1: 'bends'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendsKeyword_4_1_0()); 
            match(input,57,FOLLOW_2); 
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
    // InternalGrana.g:8448:1: rule__ElkEdgeSection__Group_4_1__1 : rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 ;
    public final void rule__ElkEdgeSection__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8452:1: ( rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2 )
            // InternalGrana.g:8453:2: rule__ElkEdgeSection__Group_4_1__1__Impl rule__ElkEdgeSection__Group_4_1__2
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
    // InternalGrana.g:8460:1: rule__ElkEdgeSection__Group_4_1__1__Impl : ( ':' ) ;
    public final void rule__ElkEdgeSection__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8464:1: ( ( ':' ) )
            // InternalGrana.g:8465:1: ( ':' )
            {
            // InternalGrana.g:8465:1: ( ':' )
            // InternalGrana.g:8466:1: ':'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getColonKeyword_4_1_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:8479:1: rule__ElkEdgeSection__Group_4_1__2 : rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3 ;
    public final void rule__ElkEdgeSection__Group_4_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8483:1: ( rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3 )
            // InternalGrana.g:8484:2: rule__ElkEdgeSection__Group_4_1__2__Impl rule__ElkEdgeSection__Group_4_1__3
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
    // InternalGrana.g:8491:1: rule__ElkEdgeSection__Group_4_1__2__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8495:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) ) )
            // InternalGrana.g:8496:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) )
            {
            // InternalGrana.g:8496:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 ) )
            // InternalGrana.g:8497:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_2()); 
            // InternalGrana.g:8498:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_2 )
            // InternalGrana.g:8498:2: rule__ElkEdgeSection__BendPointsAssignment_4_1_2
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
    // InternalGrana.g:8508:1: rule__ElkEdgeSection__Group_4_1__3 : rule__ElkEdgeSection__Group_4_1__3__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8512:1: ( rule__ElkEdgeSection__Group_4_1__3__Impl )
            // InternalGrana.g:8513:2: rule__ElkEdgeSection__Group_4_1__3__Impl
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
    // InternalGrana.g:8519:1: rule__ElkEdgeSection__Group_4_1__3__Impl : ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* ) ;
    public final void rule__ElkEdgeSection__Group_4_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8523:1: ( ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* ) )
            // InternalGrana.g:8524:1: ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* )
            {
            // InternalGrana.g:8524:1: ( ( rule__ElkEdgeSection__Group_4_1_3__0 )* )
            // InternalGrana.g:8525:1: ( rule__ElkEdgeSection__Group_4_1_3__0 )*
            {
             before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_1_3()); 
            // InternalGrana.g:8526:1: ( rule__ElkEdgeSection__Group_4_1_3__0 )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==58) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // InternalGrana.g:8526:2: rule__ElkEdgeSection__Group_4_1_3__0
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
    // InternalGrana.g:8544:1: rule__ElkEdgeSection__Group_4_1_3__0 : rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1 ;
    public final void rule__ElkEdgeSection__Group_4_1_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8548:1: ( rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1 )
            // InternalGrana.g:8549:2: rule__ElkEdgeSection__Group_4_1_3__0__Impl rule__ElkEdgeSection__Group_4_1_3__1
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
    // InternalGrana.g:8556:1: rule__ElkEdgeSection__Group_4_1_3__0__Impl : ( '|' ) ;
    public final void rule__ElkEdgeSection__Group_4_1_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8560:1: ( ( '|' ) )
            // InternalGrana.g:8561:1: ( '|' )
            {
            // InternalGrana.g:8561:1: ( '|' )
            // InternalGrana.g:8562:1: '|'
            {
             before(grammarAccess.getElkEdgeSectionAccess().getVerticalLineKeyword_4_1_3_0()); 
            match(input,58,FOLLOW_2); 
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
    // InternalGrana.g:8575:1: rule__ElkEdgeSection__Group_4_1_3__1 : rule__ElkEdgeSection__Group_4_1_3__1__Impl ;
    public final void rule__ElkEdgeSection__Group_4_1_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8579:1: ( rule__ElkEdgeSection__Group_4_1_3__1__Impl )
            // InternalGrana.g:8580:2: rule__ElkEdgeSection__Group_4_1_3__1__Impl
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
    // InternalGrana.g:8586:1: rule__ElkEdgeSection__Group_4_1_3__1__Impl : ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) ) ;
    public final void rule__ElkEdgeSection__Group_4_1_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8590:1: ( ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) ) )
            // InternalGrana.g:8591:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) )
            {
            // InternalGrana.g:8591:1: ( ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 ) )
            // InternalGrana.g:8592:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getBendPointsAssignment_4_1_3_1()); 
            // InternalGrana.g:8593:1: ( rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 )
            // InternalGrana.g:8593:2: rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1
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
    // InternalGrana.g:8607:1: rule__ElkBendPoint__Group__0 : rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 ;
    public final void rule__ElkBendPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8611:1: ( rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1 )
            // InternalGrana.g:8612:2: rule__ElkBendPoint__Group__0__Impl rule__ElkBendPoint__Group__1
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
    // InternalGrana.g:8619:1: rule__ElkBendPoint__Group__0__Impl : ( ( rule__ElkBendPoint__XAssignment_0 ) ) ;
    public final void rule__ElkBendPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8623:1: ( ( ( rule__ElkBendPoint__XAssignment_0 ) ) )
            // InternalGrana.g:8624:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            {
            // InternalGrana.g:8624:1: ( ( rule__ElkBendPoint__XAssignment_0 ) )
            // InternalGrana.g:8625:1: ( rule__ElkBendPoint__XAssignment_0 )
            {
             before(grammarAccess.getElkBendPointAccess().getXAssignment_0()); 
            // InternalGrana.g:8626:1: ( rule__ElkBendPoint__XAssignment_0 )
            // InternalGrana.g:8626:2: rule__ElkBendPoint__XAssignment_0
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
    // InternalGrana.g:8636:1: rule__ElkBendPoint__Group__1 : rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 ;
    public final void rule__ElkBendPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8640:1: ( rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2 )
            // InternalGrana.g:8641:2: rule__ElkBendPoint__Group__1__Impl rule__ElkBendPoint__Group__2
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
    // InternalGrana.g:8648:1: rule__ElkBendPoint__Group__1__Impl : ( ',' ) ;
    public final void rule__ElkBendPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8652:1: ( ( ',' ) )
            // InternalGrana.g:8653:1: ( ',' )
            {
            // InternalGrana.g:8653:1: ( ',' )
            // InternalGrana.g:8654:1: ','
            {
             before(grammarAccess.getElkBendPointAccess().getCommaKeyword_1()); 
            match(input,33,FOLLOW_2); 
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
    // InternalGrana.g:8667:1: rule__ElkBendPoint__Group__2 : rule__ElkBendPoint__Group__2__Impl ;
    public final void rule__ElkBendPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8671:1: ( rule__ElkBendPoint__Group__2__Impl )
            // InternalGrana.g:8672:2: rule__ElkBendPoint__Group__2__Impl
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
    // InternalGrana.g:8678:1: rule__ElkBendPoint__Group__2__Impl : ( ( rule__ElkBendPoint__YAssignment_2 ) ) ;
    public final void rule__ElkBendPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8682:1: ( ( ( rule__ElkBendPoint__YAssignment_2 ) ) )
            // InternalGrana.g:8683:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            {
            // InternalGrana.g:8683:1: ( ( rule__ElkBendPoint__YAssignment_2 ) )
            // InternalGrana.g:8684:1: ( rule__ElkBendPoint__YAssignment_2 )
            {
             before(grammarAccess.getElkBendPointAccess().getYAssignment_2()); 
            // InternalGrana.g:8685:1: ( rule__ElkBendPoint__YAssignment_2 )
            // InternalGrana.g:8685:2: rule__ElkBendPoint__YAssignment_2
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
    // InternalGrana.g:8701:1: rule__QualifiedId__Group__0 : rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 ;
    public final void rule__QualifiedId__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8705:1: ( rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1 )
            // InternalGrana.g:8706:2: rule__QualifiedId__Group__0__Impl rule__QualifiedId__Group__1
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
    // InternalGrana.g:8713:1: rule__QualifiedId__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8717:1: ( ( RULE_ID ) )
            // InternalGrana.g:8718:1: ( RULE_ID )
            {
            // InternalGrana.g:8718:1: ( RULE_ID )
            // InternalGrana.g:8719:1: RULE_ID
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
    // InternalGrana.g:8730:1: rule__QualifiedId__Group__1 : rule__QualifiedId__Group__1__Impl ;
    public final void rule__QualifiedId__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8734:1: ( rule__QualifiedId__Group__1__Impl )
            // InternalGrana.g:8735:2: rule__QualifiedId__Group__1__Impl
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
    // InternalGrana.g:8741:1: rule__QualifiedId__Group__1__Impl : ( ( rule__QualifiedId__Group_1__0 )* ) ;
    public final void rule__QualifiedId__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8745:1: ( ( ( rule__QualifiedId__Group_1__0 )* ) )
            // InternalGrana.g:8746:1: ( ( rule__QualifiedId__Group_1__0 )* )
            {
            // InternalGrana.g:8746:1: ( ( rule__QualifiedId__Group_1__0 )* )
            // InternalGrana.g:8747:1: ( rule__QualifiedId__Group_1__0 )*
            {
             before(grammarAccess.getQualifiedIdAccess().getGroup_1()); 
            // InternalGrana.g:8748:1: ( rule__QualifiedId__Group_1__0 )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==60) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // InternalGrana.g:8748:2: rule__QualifiedId__Group_1__0
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
    // InternalGrana.g:8762:1: rule__QualifiedId__Group_1__0 : rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 ;
    public final void rule__QualifiedId__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8766:1: ( rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1 )
            // InternalGrana.g:8767:2: rule__QualifiedId__Group_1__0__Impl rule__QualifiedId__Group_1__1
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
    // InternalGrana.g:8774:1: rule__QualifiedId__Group_1__0__Impl : ( '.' ) ;
    public final void rule__QualifiedId__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8778:1: ( ( '.' ) )
            // InternalGrana.g:8779:1: ( '.' )
            {
            // InternalGrana.g:8779:1: ( '.' )
            // InternalGrana.g:8780:1: '.'
            {
             before(grammarAccess.getQualifiedIdAccess().getFullStopKeyword_1_0()); 
            match(input,60,FOLLOW_2); 
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
    // InternalGrana.g:8793:1: rule__QualifiedId__Group_1__1 : rule__QualifiedId__Group_1__1__Impl ;
    public final void rule__QualifiedId__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8797:1: ( rule__QualifiedId__Group_1__1__Impl )
            // InternalGrana.g:8798:2: rule__QualifiedId__Group_1__1__Impl
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
    // InternalGrana.g:8804:1: rule__QualifiedId__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__QualifiedId__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8808:1: ( ( RULE_ID ) )
            // InternalGrana.g:8809:1: ( RULE_ID )
            {
            // InternalGrana.g:8809:1: ( RULE_ID )
            // InternalGrana.g:8810:1: RULE_ID
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
    // InternalGrana.g:8825:1: rule__Property__Group__0 : rule__Property__Group__0__Impl rule__Property__Group__1 ;
    public final void rule__Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8829:1: ( rule__Property__Group__0__Impl rule__Property__Group__1 )
            // InternalGrana.g:8830:2: rule__Property__Group__0__Impl rule__Property__Group__1
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
    // InternalGrana.g:8837:1: rule__Property__Group__0__Impl : ( ( rule__Property__KeyAssignment_0 ) ) ;
    public final void rule__Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8841:1: ( ( ( rule__Property__KeyAssignment_0 ) ) )
            // InternalGrana.g:8842:1: ( ( rule__Property__KeyAssignment_0 ) )
            {
            // InternalGrana.g:8842:1: ( ( rule__Property__KeyAssignment_0 ) )
            // InternalGrana.g:8843:1: ( rule__Property__KeyAssignment_0 )
            {
             before(grammarAccess.getPropertyAccess().getKeyAssignment_0()); 
            // InternalGrana.g:8844:1: ( rule__Property__KeyAssignment_0 )
            // InternalGrana.g:8844:2: rule__Property__KeyAssignment_0
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
    // InternalGrana.g:8854:1: rule__Property__Group__1 : rule__Property__Group__1__Impl rule__Property__Group__2 ;
    public final void rule__Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8858:1: ( rule__Property__Group__1__Impl rule__Property__Group__2 )
            // InternalGrana.g:8859:2: rule__Property__Group__1__Impl rule__Property__Group__2
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
    // InternalGrana.g:8866:1: rule__Property__Group__1__Impl : ( ':' ) ;
    public final void rule__Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8870:1: ( ( ':' ) )
            // InternalGrana.g:8871:1: ( ':' )
            {
            // InternalGrana.g:8871:1: ( ':' )
            // InternalGrana.g:8872:1: ':'
            {
             before(grammarAccess.getPropertyAccess().getColonKeyword_1()); 
            match(input,44,FOLLOW_2); 
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
    // InternalGrana.g:8885:1: rule__Property__Group__2 : rule__Property__Group__2__Impl ;
    public final void rule__Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8889:1: ( rule__Property__Group__2__Impl )
            // InternalGrana.g:8890:2: rule__Property__Group__2__Impl
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
    // InternalGrana.g:8896:1: rule__Property__Group__2__Impl : ( ( rule__Property__Alternatives_2 ) ) ;
    public final void rule__Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8900:1: ( ( ( rule__Property__Alternatives_2 ) ) )
            // InternalGrana.g:8901:1: ( ( rule__Property__Alternatives_2 ) )
            {
            // InternalGrana.g:8901:1: ( ( rule__Property__Alternatives_2 ) )
            // InternalGrana.g:8902:1: ( rule__Property__Alternatives_2 )
            {
             before(grammarAccess.getPropertyAccess().getAlternatives_2()); 
            // InternalGrana.g:8903:1: ( rule__Property__Alternatives_2 )
            // InternalGrana.g:8903:2: rule__Property__Alternatives_2
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
    // InternalGrana.g:8919:1: rule__PropertyKey__Group__0 : rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 ;
    public final void rule__PropertyKey__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8923:1: ( rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1 )
            // InternalGrana.g:8924:2: rule__PropertyKey__Group__0__Impl rule__PropertyKey__Group__1
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
    // InternalGrana.g:8931:1: rule__PropertyKey__Group__0__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8935:1: ( ( RULE_ID ) )
            // InternalGrana.g:8936:1: ( RULE_ID )
            {
            // InternalGrana.g:8936:1: ( RULE_ID )
            // InternalGrana.g:8937:1: RULE_ID
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
    // InternalGrana.g:8948:1: rule__PropertyKey__Group__1 : rule__PropertyKey__Group__1__Impl ;
    public final void rule__PropertyKey__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8952:1: ( rule__PropertyKey__Group__1__Impl )
            // InternalGrana.g:8953:2: rule__PropertyKey__Group__1__Impl
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
    // InternalGrana.g:8959:1: rule__PropertyKey__Group__1__Impl : ( ( rule__PropertyKey__Group_1__0 )* ) ;
    public final void rule__PropertyKey__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8963:1: ( ( ( rule__PropertyKey__Group_1__0 )* ) )
            // InternalGrana.g:8964:1: ( ( rule__PropertyKey__Group_1__0 )* )
            {
            // InternalGrana.g:8964:1: ( ( rule__PropertyKey__Group_1__0 )* )
            // InternalGrana.g:8965:1: ( rule__PropertyKey__Group_1__0 )*
            {
             before(grammarAccess.getPropertyKeyAccess().getGroup_1()); 
            // InternalGrana.g:8966:1: ( rule__PropertyKey__Group_1__0 )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==60) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // InternalGrana.g:8966:2: rule__PropertyKey__Group_1__0
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
    // InternalGrana.g:8980:1: rule__PropertyKey__Group_1__0 : rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 ;
    public final void rule__PropertyKey__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8984:1: ( rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1 )
            // InternalGrana.g:8985:2: rule__PropertyKey__Group_1__0__Impl rule__PropertyKey__Group_1__1
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
    // InternalGrana.g:8992:1: rule__PropertyKey__Group_1__0__Impl : ( '.' ) ;
    public final void rule__PropertyKey__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:8996:1: ( ( '.' ) )
            // InternalGrana.g:8997:1: ( '.' )
            {
            // InternalGrana.g:8997:1: ( '.' )
            // InternalGrana.g:8998:1: '.'
            {
             before(grammarAccess.getPropertyKeyAccess().getFullStopKeyword_1_0()); 
            match(input,60,FOLLOW_2); 
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
    // InternalGrana.g:9011:1: rule__PropertyKey__Group_1__1 : rule__PropertyKey__Group_1__1__Impl ;
    public final void rule__PropertyKey__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9015:1: ( rule__PropertyKey__Group_1__1__Impl )
            // InternalGrana.g:9016:2: rule__PropertyKey__Group_1__1__Impl
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
    // InternalGrana.g:9022:1: rule__PropertyKey__Group_1__1__Impl : ( RULE_ID ) ;
    public final void rule__PropertyKey__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9026:1: ( ( RULE_ID ) )
            // InternalGrana.g:9027:1: ( RULE_ID )
            {
            // InternalGrana.g:9027:1: ( RULE_ID )
            // InternalGrana.g:9028:1: RULE_ID
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
    // InternalGrana.g:9044:1: rule__ShapeLayout__UnorderedGroup_2 : ( rule__ShapeLayout__UnorderedGroup_2__0 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2());
            
        try {
            // InternalGrana.g:9049:1: ( ( rule__ShapeLayout__UnorderedGroup_2__0 )? )
            // InternalGrana.g:9050:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            {
            // InternalGrana.g:9050:2: ( rule__ShapeLayout__UnorderedGroup_2__0 )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( LA75_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt75=1;
            }
            else if ( LA75_0 == 50 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalGrana.g:9050:2: rule__ShapeLayout__UnorderedGroup_2__0
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
    // InternalGrana.g:9060:1: rule__ShapeLayout__UnorderedGroup_2__Impl : ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) ;
    public final void rule__ShapeLayout__UnorderedGroup_2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:9065:1: ( ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) ) )
            // InternalGrana.g:9066:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            {
            // InternalGrana.g:9066:3: ( ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) ) )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( LA76_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt76=1;
            }
            else if ( LA76_0 == 50 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt76=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }
            switch (alt76) {
                case 1 :
                    // InternalGrana.g:9068:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    {
                    // InternalGrana.g:9068:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) ) )
                    // InternalGrana.g:9069:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0)");
                    }
                    // InternalGrana.g:9069:108: ( ( ( rule__ShapeLayout__Group_2_0__0 ) ) )
                    // InternalGrana.g:9070:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9076:6: ( ( rule__ShapeLayout__Group_2_0__0 ) )
                    // InternalGrana.g:9078:7: ( rule__ShapeLayout__Group_2_0__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_0()); 
                    // InternalGrana.g:9079:7: ( rule__ShapeLayout__Group_2_0__0 )
                    // InternalGrana.g:9079:8: rule__ShapeLayout__Group_2_0__0
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
                    // InternalGrana.g:9085:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    {
                    // InternalGrana.g:9085:4: ({...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) ) )
                    // InternalGrana.g:9086:5: {...}? => ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ShapeLayout__UnorderedGroup_2__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1)");
                    }
                    // InternalGrana.g:9086:108: ( ( ( rule__ShapeLayout__Group_2_1__0 ) ) )
                    // InternalGrana.g:9087:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9093:6: ( ( rule__ShapeLayout__Group_2_1__0 ) )
                    // InternalGrana.g:9095:7: ( rule__ShapeLayout__Group_2_1__0 )
                    {
                     before(grammarAccess.getShapeLayoutAccess().getGroup_2_1()); 
                    // InternalGrana.g:9096:7: ( rule__ShapeLayout__Group_2_1__0 )
                    // InternalGrana.g:9096:8: rule__ShapeLayout__Group_2_1__0
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
    // InternalGrana.g:9111:1: rule__ShapeLayout__UnorderedGroup_2__0 : rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? ;
    public final void rule__ShapeLayout__UnorderedGroup_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9115:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )? )
            // InternalGrana.g:9116:2: rule__ShapeLayout__UnorderedGroup_2__Impl ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            {
            pushFollow(FOLLOW_54);
            rule__ShapeLayout__UnorderedGroup_2__Impl();

            state._fsp--;

            // InternalGrana.g:9117:2: ( rule__ShapeLayout__UnorderedGroup_2__1 )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( LA77_0 == 49 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 0) ) {
                alt77=1;
            }
            else if ( LA77_0 == 50 && getUnorderedGroupHelper().canSelect(grammarAccess.getShapeLayoutAccess().getUnorderedGroup_2(), 1) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // InternalGrana.g:9117:2: rule__ShapeLayout__UnorderedGroup_2__1
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
    // InternalGrana.g:9124:1: rule__ShapeLayout__UnorderedGroup_2__1 : rule__ShapeLayout__UnorderedGroup_2__Impl ;
    public final void rule__ShapeLayout__UnorderedGroup_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9128:1: ( rule__ShapeLayout__UnorderedGroup_2__Impl )
            // InternalGrana.g:9129:2: rule__ShapeLayout__UnorderedGroup_2__Impl
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
    // InternalGrana.g:9140:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0 : ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0());
            
        try {
            // InternalGrana.g:9145:1: ( ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )? )
            // InternalGrana.g:9146:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )?
            {
            // InternalGrana.g:9146:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( LA78_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt78=1;
            }
            else if ( LA78_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt78=1;
            }
            else if ( LA78_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt78=1;
            }
            else if ( LA78_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalGrana.g:9146:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0
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
    // InternalGrana.g:9156:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl : ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) ) ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:9161:1: ( ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) ) )
            // InternalGrana.g:9162:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) )
            {
            // InternalGrana.g:9162:3: ( ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) ) )
            int alt79=4;
            int LA79_0 = input.LA(1);

            if ( LA79_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt79=1;
            }
            else if ( LA79_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt79=2;
            }
            else if ( LA79_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt79=3;
            }
            else if ( LA79_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt79=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // InternalGrana.g:9164:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) )
                    {
                    // InternalGrana.g:9164:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) ) )
                    // InternalGrana.g:9165:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0)");
                    }
                    // InternalGrana.g:9165:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) ) )
                    // InternalGrana.g:9166:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9172:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_0__0 ) )
                    // InternalGrana.g:9174:7: ( rule__ElkSingleEdgeSection__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_0()); 
                    // InternalGrana.g:9175:7: ( rule__ElkSingleEdgeSection__Group_1_0_0__0 )
                    // InternalGrana.g:9175:8: rule__ElkSingleEdgeSection__Group_1_0_0__0
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
                    // InternalGrana.g:9181:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) )
                    {
                    // InternalGrana.g:9181:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) ) )
                    // InternalGrana.g:9182:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1)");
                    }
                    // InternalGrana.g:9182:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) ) )
                    // InternalGrana.g:9183:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9189:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_1__0 ) )
                    // InternalGrana.g:9191:7: ( rule__ElkSingleEdgeSection__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_1()); 
                    // InternalGrana.g:9192:7: ( rule__ElkSingleEdgeSection__Group_1_0_1__0 )
                    // InternalGrana.g:9192:8: rule__ElkSingleEdgeSection__Group_1_0_1__0
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
                    // InternalGrana.g:9198:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) )
                    {
                    // InternalGrana.g:9198:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) ) )
                    // InternalGrana.g:9199:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2)");
                    }
                    // InternalGrana.g:9199:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) ) )
                    // InternalGrana.g:9200:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9206:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_2__0 ) )
                    // InternalGrana.g:9208:7: ( rule__ElkSingleEdgeSection__Group_1_0_2__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_2()); 
                    // InternalGrana.g:9209:7: ( rule__ElkSingleEdgeSection__Group_1_0_2__0 )
                    // InternalGrana.g:9209:8: rule__ElkSingleEdgeSection__Group_1_0_2__0
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
                    // InternalGrana.g:9215:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) )
                    {
                    // InternalGrana.g:9215:4: ({...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) ) )
                    // InternalGrana.g:9216:5: {...}? => ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3)");
                    }
                    // InternalGrana.g:9216:119: ( ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) ) )
                    // InternalGrana.g:9217:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9223:6: ( ( rule__ElkSingleEdgeSection__Group_1_0_3__0 ) )
                    // InternalGrana.g:9225:7: ( rule__ElkSingleEdgeSection__Group_1_0_3__0 )
                    {
                     before(grammarAccess.getElkSingleEdgeSectionAccess().getGroup_1_0_3()); 
                    // InternalGrana.g:9226:7: ( rule__ElkSingleEdgeSection__Group_1_0_3__0 )
                    // InternalGrana.g:9226:8: rule__ElkSingleEdgeSection__Group_1_0_3__0
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
    // InternalGrana.g:9241:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9245:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )? )
            // InternalGrana.g:9246:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:9247:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( LA80_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt80=1;
            }
            else if ( LA80_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt80=1;
            }
            else if ( LA80_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt80=1;
            }
            else if ( LA80_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // InternalGrana.g:9247:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1
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
    // InternalGrana.g:9254:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9258:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )? )
            // InternalGrana.g:9259:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:9260:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( LA81_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt81=1;
            }
            else if ( LA81_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt81=1;
            }
            else if ( LA81_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt81=1;
            }
            else if ( LA81_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // InternalGrana.g:9260:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2
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
    // InternalGrana.g:9267:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )? ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9271:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )? )
            // InternalGrana.g:9272:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl();

            state._fsp--;

            // InternalGrana.g:9273:2: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( LA82_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 0) ) {
                alt82=1;
            }
            else if ( LA82_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 1) ) {
                alt82=1;
            }
            else if ( LA82_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 2) ) {
                alt82=1;
            }
            else if ( LA82_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkSingleEdgeSectionAccess().getUnorderedGroup_1_0(), 3) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // InternalGrana.g:9273:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3
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
    // InternalGrana.g:9280:1: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3 : rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl ;
    public final void rule__ElkSingleEdgeSection__UnorderedGroup_1_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9284:1: ( rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl )
            // InternalGrana.g:9285:2: rule__ElkSingleEdgeSection__UnorderedGroup_1_0__Impl
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
    // InternalGrana.g:9300:1: rule__ElkEdgeSection__UnorderedGroup_4_0 : ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0() throws RecognitionException {

            	int stackSize = keepStackSize();
        		getUnorderedGroupHelper().enter(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0());
            
        try {
            // InternalGrana.g:9305:1: ( ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )? )
            // InternalGrana.g:9306:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )?
            {
            // InternalGrana.g:9306:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__0 )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( LA83_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt83=1;
            }
            else if ( LA83_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt83=1;
            }
            else if ( LA83_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt83=1;
            }
            else if ( LA83_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // InternalGrana.g:9306:2: rule__ElkEdgeSection__UnorderedGroup_4_0__0
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
    // InternalGrana.g:9316:1: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl : ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) ) ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        		boolean selected = false;
            
        try {
            // InternalGrana.g:9321:1: ( ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) ) )
            // InternalGrana.g:9322:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) )
            {
            // InternalGrana.g:9322:3: ( ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) ) | ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) ) )
            int alt84=4;
            int LA84_0 = input.LA(1);

            if ( LA84_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt84=1;
            }
            else if ( LA84_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt84=2;
            }
            else if ( LA84_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt84=3;
            }
            else if ( LA84_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt84=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // InternalGrana.g:9324:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) )
                    {
                    // InternalGrana.g:9324:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) ) )
                    // InternalGrana.g:9325:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0)");
                    }
                    // InternalGrana.g:9325:113: ( ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) ) )
                    // InternalGrana.g:9326:6: ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9332:6: ( ( rule__ElkEdgeSection__Group_4_0_0__0 ) )
                    // InternalGrana.g:9334:7: ( rule__ElkEdgeSection__Group_4_0_0__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_0()); 
                    // InternalGrana.g:9335:7: ( rule__ElkEdgeSection__Group_4_0_0__0 )
                    // InternalGrana.g:9335:8: rule__ElkEdgeSection__Group_4_0_0__0
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
                    // InternalGrana.g:9341:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) )
                    {
                    // InternalGrana.g:9341:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) ) )
                    // InternalGrana.g:9342:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1)");
                    }
                    // InternalGrana.g:9342:113: ( ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) ) )
                    // InternalGrana.g:9343:6: ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9349:6: ( ( rule__ElkEdgeSection__Group_4_0_1__0 ) )
                    // InternalGrana.g:9351:7: ( rule__ElkEdgeSection__Group_4_0_1__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_1()); 
                    // InternalGrana.g:9352:7: ( rule__ElkEdgeSection__Group_4_0_1__0 )
                    // InternalGrana.g:9352:8: rule__ElkEdgeSection__Group_4_0_1__0
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
                    // InternalGrana.g:9358:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) )
                    {
                    // InternalGrana.g:9358:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) ) )
                    // InternalGrana.g:9359:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2)");
                    }
                    // InternalGrana.g:9359:113: ( ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) ) )
                    // InternalGrana.g:9360:6: ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9366:6: ( ( rule__ElkEdgeSection__Group_4_0_2__0 ) )
                    // InternalGrana.g:9368:7: ( rule__ElkEdgeSection__Group_4_0_2__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_2()); 
                    // InternalGrana.g:9369:7: ( rule__ElkEdgeSection__Group_4_0_2__0 )
                    // InternalGrana.g:9369:8: rule__ElkEdgeSection__Group_4_0_2__0
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
                    // InternalGrana.g:9375:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) )
                    {
                    // InternalGrana.g:9375:4: ({...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) ) )
                    // InternalGrana.g:9376:5: {...}? => ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) )
                    {
                    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                        throw new FailedPredicateException(input, "rule__ElkEdgeSection__UnorderedGroup_4_0__Impl", "getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3)");
                    }
                    // InternalGrana.g:9376:113: ( ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) ) )
                    // InternalGrana.g:9377:6: ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) )
                    {
                     
                    	 				  getUnorderedGroupHelper().select(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3);
                    	 				

                    	 				  selected = true;
                    	 				
                    // InternalGrana.g:9383:6: ( ( rule__ElkEdgeSection__Group_4_0_3__0 ) )
                    // InternalGrana.g:9385:7: ( rule__ElkEdgeSection__Group_4_0_3__0 )
                    {
                     before(grammarAccess.getElkEdgeSectionAccess().getGroup_4_0_3()); 
                    // InternalGrana.g:9386:7: ( rule__ElkEdgeSection__Group_4_0_3__0 )
                    // InternalGrana.g:9386:8: rule__ElkEdgeSection__Group_4_0_3__0
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
    // InternalGrana.g:9401:1: rule__ElkEdgeSection__UnorderedGroup_4_0__0 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9405:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )? )
            // InternalGrana.g:9406:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:9407:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__1 )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( LA85_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt85=1;
            }
            else if ( LA85_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt85=1;
            }
            else if ( LA85_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt85=1;
            }
            else if ( LA85_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalGrana.g:9407:2: rule__ElkEdgeSection__UnorderedGroup_4_0__1
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
    // InternalGrana.g:9414:1: rule__ElkEdgeSection__UnorderedGroup_4_0__1 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9418:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )? )
            // InternalGrana.g:9419:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:9420:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__2 )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( LA86_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt86=1;
            }
            else if ( LA86_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt86=1;
            }
            else if ( LA86_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt86=1;
            }
            else if ( LA86_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalGrana.g:9420:2: rule__ElkEdgeSection__UnorderedGroup_4_0__2
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
    // InternalGrana.g:9427:1: rule__ElkEdgeSection__UnorderedGroup_4_0__2 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )? ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9431:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )? )
            // InternalGrana.g:9432:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )?
            {
            pushFollow(FOLLOW_55);
            rule__ElkEdgeSection__UnorderedGroup_4_0__Impl();

            state._fsp--;

            // InternalGrana.g:9433:2: ( rule__ElkEdgeSection__UnorderedGroup_4_0__3 )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( LA87_0 == 53 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 0) ) {
                alt87=1;
            }
            else if ( LA87_0 == 54 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 1) ) {
                alt87=1;
            }
            else if ( LA87_0 == 55 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 2) ) {
                alt87=1;
            }
            else if ( LA87_0 == 56 && getUnorderedGroupHelper().canSelect(grammarAccess.getElkEdgeSectionAccess().getUnorderedGroup_4_0(), 3) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalGrana.g:9433:2: rule__ElkEdgeSection__UnorderedGroup_4_0__3
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
    // InternalGrana.g:9440:1: rule__ElkEdgeSection__UnorderedGroup_4_0__3 : rule__ElkEdgeSection__UnorderedGroup_4_0__Impl ;
    public final void rule__ElkEdgeSection__UnorderedGroup_4_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9444:1: ( rule__ElkEdgeSection__UnorderedGroup_4_0__Impl )
            // InternalGrana.g:9445:2: rule__ElkEdgeSection__UnorderedGroup_4_0__Impl
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
    // InternalGrana.g:9460:1: rule__Grana__GlobalResourcesAssignment_0_1 : ( ruleGlobalResourceRef ) ;
    public final void rule__Grana__GlobalResourcesAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9464:1: ( ( ruleGlobalResourceRef ) )
            // InternalGrana.g:9465:1: ( ruleGlobalResourceRef )
            {
            // InternalGrana.g:9465:1: ( ruleGlobalResourceRef )
            // InternalGrana.g:9466:1: ruleGlobalResourceRef
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
    // InternalGrana.g:9475:1: rule__Grana__GloobalOutputsAssignment_1_1 : ( ruleGlobalOutputRef ) ;
    public final void rule__Grana__GloobalOutputsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9479:1: ( ( ruleGlobalOutputRef ) )
            // InternalGrana.g:9480:1: ( ruleGlobalOutputRef )
            {
            // InternalGrana.g:9480:1: ( ruleGlobalOutputRef )
            // InternalGrana.g:9481:1: ruleGlobalOutputRef
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
    // InternalGrana.g:9490:1: rule__Grana__ParallelAssignment_2_1 : ( ( 'parallel' ) ) ;
    public final void rule__Grana__ParallelAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9494:1: ( ( ( 'parallel' ) ) )
            // InternalGrana.g:9495:1: ( ( 'parallel' ) )
            {
            // InternalGrana.g:9495:1: ( ( 'parallel' ) )
            // InternalGrana.g:9496:1: ( 'parallel' )
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            // InternalGrana.g:9497:1: ( 'parallel' )
            // InternalGrana.g:9498:1: 'parallel'
            {
             before(grammarAccess.getGranaAccess().getParallelParallelKeyword_2_1_0()); 
            match(input,61,FOLLOW_2); 
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
    // InternalGrana.g:9513:1: rule__Grana__ExecuteAllAssignment_2_2_0 : ( ( 'all' ) ) ;
    public final void rule__Grana__ExecuteAllAssignment_2_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9517:1: ( ( ( 'all' ) ) )
            // InternalGrana.g:9518:1: ( ( 'all' ) )
            {
            // InternalGrana.g:9518:1: ( ( 'all' ) )
            // InternalGrana.g:9519:1: ( 'all' )
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            // InternalGrana.g:9520:1: ( 'all' )
            // InternalGrana.g:9521:1: 'all'
            {
             before(grammarAccess.getGranaAccess().getExecuteAllAllKeyword_2_2_0_0()); 
            match(input,62,FOLLOW_2); 
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
    // InternalGrana.g:9536:1: rule__Grana__ExecuteAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__Grana__ExecuteAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9540:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:9541:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:9541:1: ( ( RULE_ID ) )
            // InternalGrana.g:9542:1: ( RULE_ID )
            {
             before(grammarAccess.getGranaAccess().getExecuteJobCrossReference_2_2_1_0()); 
            // InternalGrana.g:9543:1: ( RULE_ID )
            // InternalGrana.g:9544:1: RULE_ID
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
    // InternalGrana.g:9555:1: rule__Grana__JobsAssignment_3 : ( ruleJob ) ;
    public final void rule__Grana__JobsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9559:1: ( ( ruleJob ) )
            // InternalGrana.g:9560:1: ( ruleJob )
            {
            // InternalGrana.g:9560:1: ( ruleJob )
            // InternalGrana.g:9561:1: ruleJob
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
    // InternalGrana.g:9570:1: rule__RegularJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RegularJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9574:1: ( ( RULE_ID ) )
            // InternalGrana.g:9575:1: ( RULE_ID )
            {
            // InternalGrana.g:9575:1: ( RULE_ID )
            // InternalGrana.g:9576:1: RULE_ID
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
    // InternalGrana.g:9585:1: rule__RegularJob__LayoutBeforeAnalysisAssignment_2 : ( ( 'layoutBeforeAnalysis' ) ) ;
    public final void rule__RegularJob__LayoutBeforeAnalysisAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9589:1: ( ( ( 'layoutBeforeAnalysis' ) ) )
            // InternalGrana.g:9590:1: ( ( 'layoutBeforeAnalysis' ) )
            {
            // InternalGrana.g:9590:1: ( ( 'layoutBeforeAnalysis' ) )
            // InternalGrana.g:9591:1: ( 'layoutBeforeAnalysis' )
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            // InternalGrana.g:9592:1: ( 'layoutBeforeAnalysis' )
            // InternalGrana.g:9593:1: 'layoutBeforeAnalysis'
            {
             before(grammarAccess.getRegularJobAccess().getLayoutBeforeAnalysisLayoutBeforeAnalysisKeyword_2_0()); 
            match(input,63,FOLLOW_2); 
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
    // InternalGrana.g:9608:1: rule__RegularJob__MeasureExecutionTimeAssignment_3 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RegularJob__MeasureExecutionTimeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9612:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:9613:1: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:9613:1: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:9614:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            // InternalGrana.g:9615:1: ( 'measureExecutionTime' )
            // InternalGrana.g:9616:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRegularJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_3_0()); 
            match(input,64,FOLLOW_2); 
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
    // InternalGrana.g:9631:1: rule__RegularJob__ResourcesAssignment_5 : ( ruleResource ) ;
    public final void rule__RegularJob__ResourcesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9635:1: ( ( ruleResource ) )
            // InternalGrana.g:9636:1: ( ruleResource )
            {
            // InternalGrana.g:9636:1: ( ruleResource )
            // InternalGrana.g:9637:1: ruleResource
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
    // InternalGrana.g:9646:1: rule__RegularJob__LayoutOptionsAssignment_7 : ( ruleLayoutConfig ) ;
    public final void rule__RegularJob__LayoutOptionsAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9650:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9651:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9651:1: ( ruleLayoutConfig )
            // InternalGrana.g:9652:1: ruleLayoutConfig
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
    // InternalGrana.g:9661:1: rule__RegularJob__AnalysesAssignment_9 : ( ruleAnalysis ) ;
    public final void rule__RegularJob__AnalysesAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9665:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9666:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9666:1: ( ruleAnalysis )
            // InternalGrana.g:9667:1: ruleAnalysis
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
    // InternalGrana.g:9676:1: rule__RegularJob__OutputTypeAssignment_11 : ( ruleOutputType ) ;
    public final void rule__RegularJob__OutputTypeAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9680:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9681:1: ( ruleOutputType )
            {
            // InternalGrana.g:9681:1: ( ruleOutputType )
            // InternalGrana.g:9682:1: ruleOutputType
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
    // InternalGrana.g:9691:1: rule__RegularJob__OutputAssignment_12 : ( ruleOutput ) ;
    public final void rule__RegularJob__OutputAssignment_12() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9695:1: ( ( ruleOutput ) )
            // InternalGrana.g:9696:1: ( ruleOutput )
            {
            // InternalGrana.g:9696:1: ( ruleOutput )
            // InternalGrana.g:9697:1: ruleOutput
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
    // InternalGrana.g:9706:1: rule__CompareJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__CompareJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9710:1: ( ( RULE_ID ) )
            // InternalGrana.g:9711:1: ( RULE_ID )
            {
            // InternalGrana.g:9711:1: ( RULE_ID )
            // InternalGrana.g:9712:1: RULE_ID
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
    // InternalGrana.g:9721:1: rule__CompareJob__ResourcesAssignment_3 : ( ruleResource ) ;
    public final void rule__CompareJob__ResourcesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9725:1: ( ( ruleResource ) )
            // InternalGrana.g:9726:1: ( ruleResource )
            {
            // InternalGrana.g:9726:1: ( ruleResource )
            // InternalGrana.g:9727:1: ruleResource
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
    // InternalGrana.g:9736:1: rule__CompareJob__LayoutOptionsAssignment_5 : ( ruleLayoutConfig ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9740:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9741:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9741:1: ( ruleLayoutConfig )
            // InternalGrana.g:9742:1: ruleLayoutConfig
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
    // InternalGrana.g:9751:1: rule__CompareJob__LayoutOptionsAssignment_6 : ( ruleLayoutConfig ) ;
    public final void rule__CompareJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9755:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9756:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9756:1: ( ruleLayoutConfig )
            // InternalGrana.g:9757:1: ruleLayoutConfig
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
    // InternalGrana.g:9766:1: rule__CompareJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__CompareJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9770:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9771:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9771:1: ( ruleAnalysis )
            // InternalGrana.g:9772:1: ruleAnalysis
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
    // InternalGrana.g:9781:1: rule__CompareJob__OutputTypeAssignment_10 : ( ruleOutputType ) ;
    public final void rule__CompareJob__OutputTypeAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9785:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9786:1: ( ruleOutputType )
            {
            // InternalGrana.g:9786:1: ( ruleOutputType )
            // InternalGrana.g:9787:1: ruleOutputType
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
    // InternalGrana.g:9796:1: rule__CompareJob__OutputAssignment_11 : ( ruleOutput ) ;
    public final void rule__CompareJob__OutputAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9800:1: ( ( ruleOutput ) )
            // InternalGrana.g:9801:1: ( ruleOutput )
            {
            // InternalGrana.g:9801:1: ( ruleOutput )
            // InternalGrana.g:9802:1: ruleOutput
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
    // InternalGrana.g:9811:1: rule__RangeJob__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__RangeJob__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9815:1: ( ( RULE_ID ) )
            // InternalGrana.g:9816:1: ( RULE_ID )
            {
            // InternalGrana.g:9816:1: ( RULE_ID )
            // InternalGrana.g:9817:1: RULE_ID
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
    // InternalGrana.g:9826:1: rule__RangeJob__MeasureExecutionTimeAssignment_2 : ( ( 'measureExecutionTime' ) ) ;
    public final void rule__RangeJob__MeasureExecutionTimeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9830:1: ( ( ( 'measureExecutionTime' ) ) )
            // InternalGrana.g:9831:1: ( ( 'measureExecutionTime' ) )
            {
            // InternalGrana.g:9831:1: ( ( 'measureExecutionTime' ) )
            // InternalGrana.g:9832:1: ( 'measureExecutionTime' )
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            // InternalGrana.g:9833:1: ( 'measureExecutionTime' )
            // InternalGrana.g:9834:1: 'measureExecutionTime'
            {
             before(grammarAccess.getRangeJobAccess().getMeasureExecutionTimeMeasureExecutionTimeKeyword_2_0()); 
            match(input,64,FOLLOW_2); 
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
    // InternalGrana.g:9849:1: rule__RangeJob__ResourcesAssignment_4 : ( ruleResource ) ;
    public final void rule__RangeJob__ResourcesAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9853:1: ( ( ruleResource ) )
            // InternalGrana.g:9854:1: ( ruleResource )
            {
            // InternalGrana.g:9854:1: ( ruleResource )
            // InternalGrana.g:9855:1: ruleResource
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
    // InternalGrana.g:9864:1: rule__RangeJob__LayoutOptionsAssignment_6 : ( ruleLayoutConfig ) ;
    public final void rule__RangeJob__LayoutOptionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9868:1: ( ( ruleLayoutConfig ) )
            // InternalGrana.g:9869:1: ( ruleLayoutConfig )
            {
            // InternalGrana.g:9869:1: ( ruleLayoutConfig )
            // InternalGrana.g:9870:1: ruleLayoutConfig
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
    // InternalGrana.g:9879:1: rule__RangeJob__AnalysesAssignment_8 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__AnalysesAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9883:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9884:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9884:1: ( ruleAnalysis )
            // InternalGrana.g:9885:1: ruleAnalysis
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
    // InternalGrana.g:9894:1: rule__RangeJob__RangeOptionAssignment_10 : ( ruleQualifiedId ) ;
    public final void rule__RangeJob__RangeOptionAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9898:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:9899:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:9899:1: ( ruleQualifiedId )
            // InternalGrana.g:9900:1: ruleQualifiedId
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
    // InternalGrana.g:9909:1: rule__RangeJob__RangeValuesAssignment_11 : ( ruleRange ) ;
    public final void rule__RangeJob__RangeValuesAssignment_11() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9913:1: ( ( ruleRange ) )
            // InternalGrana.g:9914:1: ( ruleRange )
            {
            // InternalGrana.g:9914:1: ( ruleRange )
            // InternalGrana.g:9915:1: ruleRange
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
    // InternalGrana.g:9924:1: rule__RangeJob__RangeAnalysisAssignment_12_0_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysisAssignment_12_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9928:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9929:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9929:1: ( ruleAnalysis )
            // InternalGrana.g:9930:1: ruleAnalysis
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
    // InternalGrana.g:9939:1: rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__RangeJob__RangeAnalysisComponentAssignment_12_0_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9943:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:9944:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:9944:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:9945:1: RULE_SIGNED_INT
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
    // InternalGrana.g:9954:1: rule__RangeJob__RangeAnalysesAssignment_12_1_1 : ( ruleAnalysis ) ;
    public final void rule__RangeJob__RangeAnalysesAssignment_12_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9958:1: ( ( ruleAnalysis ) )
            // InternalGrana.g:9959:1: ( ruleAnalysis )
            {
            // InternalGrana.g:9959:1: ( ruleAnalysis )
            // InternalGrana.g:9960:1: ruleAnalysis
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
    // InternalGrana.g:9969:1: rule__RangeJob__OutputTypeAssignment_14 : ( ruleOutputType ) ;
    public final void rule__RangeJob__OutputTypeAssignment_14() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9973:1: ( ( ruleOutputType ) )
            // InternalGrana.g:9974:1: ( ruleOutputType )
            {
            // InternalGrana.g:9974:1: ( ruleOutputType )
            // InternalGrana.g:9975:1: ruleOutputType
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
    // InternalGrana.g:9984:1: rule__RangeJob__OutputAssignment_15 : ( ruleOutput ) ;
    public final void rule__RangeJob__OutputAssignment_15() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:9988:1: ( ( ruleOutput ) )
            // InternalGrana.g:9989:1: ( ruleOutput )
            {
            // InternalGrana.g:9989:1: ( ruleOutput )
            // InternalGrana.g:9990:1: ruleOutput
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
    // InternalGrana.g:9999:1: rule__FloatRange__ValuesAssignment_1 : ( RULE_FLOAT ) ;
    public final void rule__FloatRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10003:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:10004:1: ( RULE_FLOAT )
            {
            // InternalGrana.g:10004:1: ( RULE_FLOAT )
            // InternalGrana.g:10005:1: RULE_FLOAT
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
    // InternalGrana.g:10014:1: rule__FloatRange__ValuesAssignment_2_1 : ( RULE_FLOAT ) ;
    public final void rule__FloatRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10018:1: ( ( RULE_FLOAT ) )
            // InternalGrana.g:10019:1: ( RULE_FLOAT )
            {
            // InternalGrana.g:10019:1: ( RULE_FLOAT )
            // InternalGrana.g:10020:1: RULE_FLOAT
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
    // InternalGrana.g:10029:1: rule__IntRangeValues__ValuesAssignment_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeValues__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10033:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10034:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10034:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10035:1: RULE_SIGNED_INT
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
    // InternalGrana.g:10044:1: rule__IntRangeValues__ValuesAssignment_2_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeValues__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10048:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10049:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10049:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10050:1: RULE_SIGNED_INT
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
    // InternalGrana.g:10059:1: rule__IntRangeRange__StartAssignment_1 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeRange__StartAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10063:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10064:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10064:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10065:1: RULE_SIGNED_INT
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
    // InternalGrana.g:10074:1: rule__IntRangeRange__EndAssignment_3 : ( RULE_SIGNED_INT ) ;
    public final void rule__IntRangeRange__EndAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10078:1: ( ( RULE_SIGNED_INT ) )
            // InternalGrana.g:10079:1: ( RULE_SIGNED_INT )
            {
            // InternalGrana.g:10079:1: ( RULE_SIGNED_INT )
            // InternalGrana.g:10080:1: RULE_SIGNED_INT
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
    // InternalGrana.g:10089:1: rule__EnumRange__ValuesAssignment_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__EnumRange__ValuesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10093:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:10094:1: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:10094:1: ( ruleQualifiedIdValue )
            // InternalGrana.g:10095:1: ruleQualifiedIdValue
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
    // InternalGrana.g:10104:1: rule__EnumRange__ValuesAssignment_2_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__EnumRange__ValuesAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10108:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:10109:1: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:10109:1: ( ruleQualifiedIdValue )
            // InternalGrana.g:10110:1: ruleQualifiedIdValue
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
    // InternalGrana.g:10119:1: rule__ResourceReference__ResourceRefsAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceReference__ResourceRefsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10123:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10124:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10124:1: ( ( RULE_ID ) )
            // InternalGrana.g:10125:1: ( RULE_ID )
            {
             before(grammarAccess.getResourceReferenceAccess().getResourceRefsGlobalResourceRefCrossReference_1_0()); 
            // InternalGrana.g:10126:1: ( RULE_ID )
            // InternalGrana.g:10127:1: RULE_ID
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
    // InternalGrana.g:10138:1: rule__GlobalResourceRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalResourceRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10142:1: ( ( RULE_ID ) )
            // InternalGrana.g:10143:1: ( RULE_ID )
            {
            // InternalGrana.g:10143:1: ( RULE_ID )
            // InternalGrana.g:10144:1: RULE_ID
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
    // InternalGrana.g:10153:1: rule__GlobalResourceRef__ResourcesAssignment_1 : ( ruleLocalResource ) ;
    public final void rule__GlobalResourceRef__ResourcesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10157:1: ( ( ruleLocalResource ) )
            // InternalGrana.g:10158:1: ( ruleLocalResource )
            {
            // InternalGrana.g:10158:1: ( ruleLocalResource )
            // InternalGrana.g:10159:1: ruleLocalResource
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
    // InternalGrana.g:10168:1: rule__LocalResource__PathAssignment_0 : ( RULE_STRING ) ;
    public final void rule__LocalResource__PathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10172:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10173:1: ( RULE_STRING )
            {
            // InternalGrana.g:10173:1: ( RULE_STRING )
            // InternalGrana.g:10174:1: RULE_STRING
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
    // InternalGrana.g:10183:1: rule__LocalResource__FilterAssignment_1_1 : ( RULE_STRING ) ;
    public final void rule__LocalResource__FilterAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10187:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10188:1: ( RULE_STRING )
            {
            // InternalGrana.g:10188:1: ( RULE_STRING )
            // InternalGrana.g:10189:1: RULE_STRING
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
    // InternalGrana.g:10198:1: rule__LocalResource__RecurseAssignment_2 : ( ( 'recurse' ) ) ;
    public final void rule__LocalResource__RecurseAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10202:1: ( ( ( 'recurse' ) ) )
            // InternalGrana.g:10203:1: ( ( 'recurse' ) )
            {
            // InternalGrana.g:10203:1: ( ( 'recurse' ) )
            // InternalGrana.g:10204:1: ( 'recurse' )
            {
             before(grammarAccess.getLocalResourceAccess().getRecurseRecurseKeyword_2_0()); 
            // InternalGrana.g:10205:1: ( 'recurse' )
            // InternalGrana.g:10206:1: 'recurse'
            {
             before(grammarAccess.getLocalResourceAccess().getRecurseRecurseKeyword_2_0()); 
            match(input,65,FOLLOW_2); 
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
    // InternalGrana.g:10221:1: rule__GlobalOutputRef__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__GlobalOutputRef__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10225:1: ( ( RULE_ID ) )
            // InternalGrana.g:10226:1: ( RULE_ID )
            {
            // InternalGrana.g:10226:1: ( RULE_ID )
            // InternalGrana.g:10227:1: RULE_ID
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
    // InternalGrana.g:10236:1: rule__GlobalOutputRef__OutputAssignment_1 : ( ruleLocalOutput ) ;
    public final void rule__GlobalOutputRef__OutputAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10240:1: ( ( ruleLocalOutput ) )
            // InternalGrana.g:10241:1: ( ruleLocalOutput )
            {
            // InternalGrana.g:10241:1: ( ruleLocalOutput )
            // InternalGrana.g:10242:1: ruleLocalOutput
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
    // InternalGrana.g:10251:1: rule__OutputReference__OutputRefAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__OutputReference__OutputRefAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10255:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10256:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10256:1: ( ( RULE_ID ) )
            // InternalGrana.g:10257:1: ( RULE_ID )
            {
             before(grammarAccess.getOutputReferenceAccess().getOutputRefGlobalOutputRefCrossReference_1_0()); 
            // InternalGrana.g:10258:1: ( RULE_ID )
            // InternalGrana.g:10259:1: RULE_ID
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
    // InternalGrana.g:10270:1: rule__LocalOutput__PathAssignment : ( RULE_STRING ) ;
    public final void rule__LocalOutput__PathAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10274:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10275:1: ( RULE_STRING )
            {
            // InternalGrana.g:10275:1: ( RULE_STRING )
            // InternalGrana.g:10276:1: RULE_STRING
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
    // InternalGrana.g:10285:1: rule__Analysis__NameAssignment : ( ruleQualifiedId ) ;
    public final void rule__Analysis__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10289:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10290:1: ( ruleQualifiedId )
            {
            // InternalGrana.g:10290:1: ( ruleQualifiedId )
            // InternalGrana.g:10291:1: ruleQualifiedId
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
    // InternalGrana.g:10300:1: rule__LayoutConfig__IdentifierAssignment_0 : ( RULE_ID ) ;
    public final void rule__LayoutConfig__IdentifierAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10304:1: ( ( RULE_ID ) )
            // InternalGrana.g:10305:1: ( RULE_ID )
            {
            // InternalGrana.g:10305:1: ( RULE_ID )
            // InternalGrana.g:10306:1: RULE_ID
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
    // InternalGrana.g:10315:1: rule__LayoutConfig__PropertiesAssignment_2 : ( ruleProperty ) ;
    public final void rule__LayoutConfig__PropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10319:1: ( ( ruleProperty ) )
            // InternalGrana.g:10320:1: ( ruleProperty )
            {
            // InternalGrana.g:10320:1: ( ruleProperty )
            // InternalGrana.g:10321:1: ruleProperty
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
    // InternalGrana.g:10336:1: rule__ElkNode__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkNode__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10340:1: ( ( RULE_ID ) )
            // InternalGrana.g:10341:1: ( RULE_ID )
            {
            // InternalGrana.g:10341:1: ( RULE_ID )
            // InternalGrana.g:10342:1: RULE_ID
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
    // InternalGrana.g:10351:1: rule__ElkNode__PropertiesAssignment_2_2 : ( ruleProperty ) ;
    public final void rule__ElkNode__PropertiesAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10355:1: ( ( ruleProperty ) )
            // InternalGrana.g:10356:1: ( ruleProperty )
            {
            // InternalGrana.g:10356:1: ( ruleProperty )
            // InternalGrana.g:10357:1: ruleProperty
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
    // InternalGrana.g:10366:1: rule__ElkNode__LabelsAssignment_2_3_0 : ( ruleElkLabel ) ;
    public final void rule__ElkNode__LabelsAssignment_2_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10370:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10371:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10371:1: ( ruleElkLabel )
            // InternalGrana.g:10372:1: ruleElkLabel
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
    // InternalGrana.g:10381:1: rule__ElkNode__PortsAssignment_2_3_1 : ( ruleElkPort ) ;
    public final void rule__ElkNode__PortsAssignment_2_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10385:1: ( ( ruleElkPort ) )
            // InternalGrana.g:10386:1: ( ruleElkPort )
            {
            // InternalGrana.g:10386:1: ( ruleElkPort )
            // InternalGrana.g:10387:1: ruleElkPort
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
    // InternalGrana.g:10396:1: rule__ElkNode__ChildrenAssignment_2_3_2 : ( ruleElkNode ) ;
    public final void rule__ElkNode__ChildrenAssignment_2_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10400:1: ( ( ruleElkNode ) )
            // InternalGrana.g:10401:1: ( ruleElkNode )
            {
            // InternalGrana.g:10401:1: ( ruleElkNode )
            // InternalGrana.g:10402:1: ruleElkNode
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
    // InternalGrana.g:10411:1: rule__ElkNode__ContainedEdgesAssignment_2_3_3 : ( ruleElkEdge ) ;
    public final void rule__ElkNode__ContainedEdgesAssignment_2_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10415:1: ( ( ruleElkEdge ) )
            // InternalGrana.g:10416:1: ( ruleElkEdge )
            {
            // InternalGrana.g:10416:1: ( ruleElkEdge )
            // InternalGrana.g:10417:1: ruleElkEdge
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
    // InternalGrana.g:10426:1: rule__ElkLabel__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkLabel__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10430:1: ( ( RULE_ID ) )
            // InternalGrana.g:10431:1: ( RULE_ID )
            {
            // InternalGrana.g:10431:1: ( RULE_ID )
            // InternalGrana.g:10432:1: RULE_ID
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
    // InternalGrana.g:10441:1: rule__ElkLabel__TextAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ElkLabel__TextAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10445:1: ( ( RULE_STRING ) )
            // InternalGrana.g:10446:1: ( RULE_STRING )
            {
            // InternalGrana.g:10446:1: ( RULE_STRING )
            // InternalGrana.g:10447:1: RULE_STRING
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
    // InternalGrana.g:10456:1: rule__ElkLabel__PropertiesAssignment_3_2 : ( ruleProperty ) ;
    public final void rule__ElkLabel__PropertiesAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10460:1: ( ( ruleProperty ) )
            // InternalGrana.g:10461:1: ( ruleProperty )
            {
            // InternalGrana.g:10461:1: ( ruleProperty )
            // InternalGrana.g:10462:1: ruleProperty
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
    // InternalGrana.g:10471:1: rule__ElkLabel__LabelsAssignment_3_3 : ( ruleElkLabel ) ;
    public final void rule__ElkLabel__LabelsAssignment_3_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10475:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10476:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10476:1: ( ruleElkLabel )
            // InternalGrana.g:10477:1: ruleElkLabel
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
    // InternalGrana.g:10486:1: rule__ElkPort__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkPort__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10490:1: ( ( RULE_ID ) )
            // InternalGrana.g:10491:1: ( RULE_ID )
            {
            // InternalGrana.g:10491:1: ( RULE_ID )
            // InternalGrana.g:10492:1: RULE_ID
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
    // InternalGrana.g:10501:1: rule__ElkPort__PropertiesAssignment_2_2 : ( ruleProperty ) ;
    public final void rule__ElkPort__PropertiesAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10505:1: ( ( ruleProperty ) )
            // InternalGrana.g:10506:1: ( ruleProperty )
            {
            // InternalGrana.g:10506:1: ( ruleProperty )
            // InternalGrana.g:10507:1: ruleProperty
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
    // InternalGrana.g:10516:1: rule__ElkPort__LabelsAssignment_2_3 : ( ruleElkLabel ) ;
    public final void rule__ElkPort__LabelsAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10520:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10521:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10521:1: ( ruleElkLabel )
            // InternalGrana.g:10522:1: ruleElkLabel
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
    // InternalGrana.g:10531:1: rule__ShapeLayout__XAssignment_2_0_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__XAssignment_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10535:1: ( ( ruleNumber ) )
            // InternalGrana.g:10536:1: ( ruleNumber )
            {
            // InternalGrana.g:10536:1: ( ruleNumber )
            // InternalGrana.g:10537:1: ruleNumber
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
    // InternalGrana.g:10546:1: rule__ShapeLayout__YAssignment_2_0_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__YAssignment_2_0_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10550:1: ( ( ruleNumber ) )
            // InternalGrana.g:10551:1: ( ruleNumber )
            {
            // InternalGrana.g:10551:1: ( ruleNumber )
            // InternalGrana.g:10552:1: ruleNumber
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
    // InternalGrana.g:10561:1: rule__ShapeLayout__WidthAssignment_2_1_2 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__WidthAssignment_2_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10565:1: ( ( ruleNumber ) )
            // InternalGrana.g:10566:1: ( ruleNumber )
            {
            // InternalGrana.g:10566:1: ( ruleNumber )
            // InternalGrana.g:10567:1: ruleNumber
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
    // InternalGrana.g:10576:1: rule__ShapeLayout__HeightAssignment_2_1_4 : ( ruleNumber ) ;
    public final void rule__ShapeLayout__HeightAssignment_2_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10580:1: ( ( ruleNumber ) )
            // InternalGrana.g:10581:1: ( ruleNumber )
            {
            // InternalGrana.g:10581:1: ( ruleNumber )
            // InternalGrana.g:10582:1: ruleNumber
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
    // InternalGrana.g:10591:1: rule__ElkEdge__IdentifierAssignment_1_0 : ( RULE_ID ) ;
    public final void rule__ElkEdge__IdentifierAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10595:1: ( ( RULE_ID ) )
            // InternalGrana.g:10596:1: ( RULE_ID )
            {
            // InternalGrana.g:10596:1: ( RULE_ID )
            // InternalGrana.g:10597:1: RULE_ID
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
    // InternalGrana.g:10606:1: rule__ElkEdge__SourcesAssignment_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__SourcesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10610:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10611:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10611:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10612:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_2_0()); 
            // InternalGrana.g:10613:1: ( ruleQualifiedId )
            // InternalGrana.g:10614:1: ruleQualifiedId
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
    // InternalGrana.g:10625:1: rule__ElkEdge__SourcesAssignment_3_1 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__SourcesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10629:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10630:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10630:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10631:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getSourcesElkConnectableShapeCrossReference_3_1_0()); 
            // InternalGrana.g:10632:1: ( ruleQualifiedId )
            // InternalGrana.g:10633:1: ruleQualifiedId
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
    // InternalGrana.g:10644:1: rule__ElkEdge__TargetsAssignment_5 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__TargetsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10648:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10649:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10649:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10650:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_5_0()); 
            // InternalGrana.g:10651:1: ( ruleQualifiedId )
            // InternalGrana.g:10652:1: ruleQualifiedId
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
    // InternalGrana.g:10663:1: rule__ElkEdge__TargetsAssignment_6_1 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdge__TargetsAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10667:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10668:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10668:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10669:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeAccess().getTargetsElkConnectableShapeCrossReference_6_1_0()); 
            // InternalGrana.g:10670:1: ( ruleQualifiedId )
            // InternalGrana.g:10671:1: ruleQualifiedId
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
    // InternalGrana.g:10682:1: rule__ElkEdge__PropertiesAssignment_7_2 : ( ruleProperty ) ;
    public final void rule__ElkEdge__PropertiesAssignment_7_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10686:1: ( ( ruleProperty ) )
            // InternalGrana.g:10687:1: ( ruleProperty )
            {
            // InternalGrana.g:10687:1: ( ruleProperty )
            // InternalGrana.g:10688:1: ruleProperty
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
    // InternalGrana.g:10697:1: rule__ElkEdge__LabelsAssignment_7_3 : ( ruleElkLabel ) ;
    public final void rule__ElkEdge__LabelsAssignment_7_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10701:1: ( ( ruleElkLabel ) )
            // InternalGrana.g:10702:1: ( ruleElkLabel )
            {
            // InternalGrana.g:10702:1: ( ruleElkLabel )
            // InternalGrana.g:10703:1: ruleElkLabel
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
    // InternalGrana.g:10712:1: rule__EdgeLayout__SectionsAssignment_2_0 : ( ruleElkSingleEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10716:1: ( ( ruleElkSingleEdgeSection ) )
            // InternalGrana.g:10717:1: ( ruleElkSingleEdgeSection )
            {
            // InternalGrana.g:10717:1: ( ruleElkSingleEdgeSection )
            // InternalGrana.g:10718:1: ruleElkSingleEdgeSection
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
    // InternalGrana.g:10727:1: rule__EdgeLayout__SectionsAssignment_2_1 : ( ruleElkEdgeSection ) ;
    public final void rule__EdgeLayout__SectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10731:1: ( ( ruleElkEdgeSection ) )
            // InternalGrana.g:10732:1: ( ruleElkEdgeSection )
            {
            // InternalGrana.g:10732:1: ( ruleElkEdgeSection )
            // InternalGrana.g:10733:1: ruleElkEdgeSection
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
    // InternalGrana.g:10742:1: rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__IncomingShapeAssignment_1_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10746:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10747:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10747:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10748:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_1_0_0_2_0()); 
            // InternalGrana.g:10749:1: ( ruleQualifiedId )
            // InternalGrana.g:10750:1: ruleQualifiedId
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
    // InternalGrana.g:10761:1: rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkSingleEdgeSection__OutgoingShapeAssignment_1_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10765:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10766:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10766:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10767:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkSingleEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_1_0_1_2_0()); 
            // InternalGrana.g:10768:1: ( ruleQualifiedId )
            // InternalGrana.g:10769:1: ruleQualifiedId
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
    // InternalGrana.g:10780:1: rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartXAssignment_1_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10784:1: ( ( ruleNumber ) )
            // InternalGrana.g:10785:1: ( ruleNumber )
            {
            // InternalGrana.g:10785:1: ( ruleNumber )
            // InternalGrana.g:10786:1: ruleNumber
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
    // InternalGrana.g:10795:1: rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__StartYAssignment_1_0_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10799:1: ( ( ruleNumber ) )
            // InternalGrana.g:10800:1: ( ruleNumber )
            {
            // InternalGrana.g:10800:1: ( ruleNumber )
            // InternalGrana.g:10801:1: ruleNumber
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
    // InternalGrana.g:10810:1: rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndXAssignment_1_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10814:1: ( ( ruleNumber ) )
            // InternalGrana.g:10815:1: ( ruleNumber )
            {
            // InternalGrana.g:10815:1: ( ruleNumber )
            // InternalGrana.g:10816:1: ruleNumber
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
    // InternalGrana.g:10825:1: rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4 : ( ruleNumber ) ;
    public final void rule__ElkSingleEdgeSection__EndYAssignment_1_0_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10829:1: ( ( ruleNumber ) )
            // InternalGrana.g:10830:1: ( ruleNumber )
            {
            // InternalGrana.g:10830:1: ( ruleNumber )
            // InternalGrana.g:10831:1: ruleNumber
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
    // InternalGrana.g:10840:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10844:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:10845:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:10845:1: ( ruleElkBendPoint )
            // InternalGrana.g:10846:1: ruleElkBendPoint
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
    // InternalGrana.g:10855:1: rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkSingleEdgeSection__BendPointsAssignment_1_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10859:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:10860:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:10860:1: ( ruleElkBendPoint )
            // InternalGrana.g:10861:1: ruleElkBendPoint
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
    // InternalGrana.g:10870:1: rule__ElkSingleEdgeSection__PropertiesAssignment_1_2 : ( ruleProperty ) ;
    public final void rule__ElkSingleEdgeSection__PropertiesAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10874:1: ( ( ruleProperty ) )
            // InternalGrana.g:10875:1: ( ruleProperty )
            {
            // InternalGrana.g:10875:1: ( ruleProperty )
            // InternalGrana.g:10876:1: ruleProperty
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
    // InternalGrana.g:10885:1: rule__ElkEdgeSection__IdentifierAssignment_1 : ( RULE_ID ) ;
    public final void rule__ElkEdgeSection__IdentifierAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10889:1: ( ( RULE_ID ) )
            // InternalGrana.g:10890:1: ( RULE_ID )
            {
            // InternalGrana.g:10890:1: ( RULE_ID )
            // InternalGrana.g:10891:1: RULE_ID
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
    // InternalGrana.g:10900:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10904:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10905:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10905:1: ( ( RULE_ID ) )
            // InternalGrana.g:10906:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_1_0()); 
            // InternalGrana.g:10907:1: ( RULE_ID )
            // InternalGrana.g:10908:1: RULE_ID
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
    // InternalGrana.g:10919:1: rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1 : ( ( RULE_ID ) ) ;
    public final void rule__ElkEdgeSection__OutgoingSectionsAssignment_2_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10923:1: ( ( ( RULE_ID ) ) )
            // InternalGrana.g:10924:1: ( ( RULE_ID ) )
            {
            // InternalGrana.g:10924:1: ( ( RULE_ID ) )
            // InternalGrana.g:10925:1: ( RULE_ID )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingSectionsElkEdgeSectionCrossReference_2_2_1_0()); 
            // InternalGrana.g:10926:1: ( RULE_ID )
            // InternalGrana.g:10927:1: RULE_ID
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
    // InternalGrana.g:10938:1: rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__IncomingShapeAssignment_4_0_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10942:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10943:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10943:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10944:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getIncomingShapeElkConnectableShapeCrossReference_4_0_0_2_0()); 
            // InternalGrana.g:10945:1: ( ruleQualifiedId )
            // InternalGrana.g:10946:1: ruleQualifiedId
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
    // InternalGrana.g:10957:1: rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2 : ( ( ruleQualifiedId ) ) ;
    public final void rule__ElkEdgeSection__OutgoingShapeAssignment_4_0_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10961:1: ( ( ( ruleQualifiedId ) ) )
            // InternalGrana.g:10962:1: ( ( ruleQualifiedId ) )
            {
            // InternalGrana.g:10962:1: ( ( ruleQualifiedId ) )
            // InternalGrana.g:10963:1: ( ruleQualifiedId )
            {
             before(grammarAccess.getElkEdgeSectionAccess().getOutgoingShapeElkConnectableShapeCrossReference_4_0_1_2_0()); 
            // InternalGrana.g:10964:1: ( ruleQualifiedId )
            // InternalGrana.g:10965:1: ruleQualifiedId
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
    // InternalGrana.g:10976:1: rule__ElkEdgeSection__StartXAssignment_4_0_2_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartXAssignment_4_0_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10980:1: ( ( ruleNumber ) )
            // InternalGrana.g:10981:1: ( ruleNumber )
            {
            // InternalGrana.g:10981:1: ( ruleNumber )
            // InternalGrana.g:10982:1: ruleNumber
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
    // InternalGrana.g:10991:1: rule__ElkEdgeSection__StartYAssignment_4_0_2_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__StartYAssignment_4_0_2_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:10995:1: ( ( ruleNumber ) )
            // InternalGrana.g:10996:1: ( ruleNumber )
            {
            // InternalGrana.g:10996:1: ( ruleNumber )
            // InternalGrana.g:10997:1: ruleNumber
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
    // InternalGrana.g:11006:1: rule__ElkEdgeSection__EndXAssignment_4_0_3_2 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndXAssignment_4_0_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11010:1: ( ( ruleNumber ) )
            // InternalGrana.g:11011:1: ( ruleNumber )
            {
            // InternalGrana.g:11011:1: ( ruleNumber )
            // InternalGrana.g:11012:1: ruleNumber
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
    // InternalGrana.g:11021:1: rule__ElkEdgeSection__EndYAssignment_4_0_3_4 : ( ruleNumber ) ;
    public final void rule__ElkEdgeSection__EndYAssignment_4_0_3_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11025:1: ( ( ruleNumber ) )
            // InternalGrana.g:11026:1: ( ruleNumber )
            {
            // InternalGrana.g:11026:1: ( ruleNumber )
            // InternalGrana.g:11027:1: ruleNumber
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
    // InternalGrana.g:11036:1: rule__ElkEdgeSection__BendPointsAssignment_4_1_2 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11040:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:11041:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:11041:1: ( ruleElkBendPoint )
            // InternalGrana.g:11042:1: ruleElkBendPoint
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
    // InternalGrana.g:11051:1: rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1 : ( ruleElkBendPoint ) ;
    public final void rule__ElkEdgeSection__BendPointsAssignment_4_1_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11055:1: ( ( ruleElkBendPoint ) )
            // InternalGrana.g:11056:1: ( ruleElkBendPoint )
            {
            // InternalGrana.g:11056:1: ( ruleElkBendPoint )
            // InternalGrana.g:11057:1: ruleElkBendPoint
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
    // InternalGrana.g:11066:1: rule__ElkEdgeSection__PropertiesAssignment_4_2 : ( ruleProperty ) ;
    public final void rule__ElkEdgeSection__PropertiesAssignment_4_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11070:1: ( ( ruleProperty ) )
            // InternalGrana.g:11071:1: ( ruleProperty )
            {
            // InternalGrana.g:11071:1: ( ruleProperty )
            // InternalGrana.g:11072:1: ruleProperty
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
    // InternalGrana.g:11081:1: rule__ElkBendPoint__XAssignment_0 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__XAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11085:1: ( ( ruleNumber ) )
            // InternalGrana.g:11086:1: ( ruleNumber )
            {
            // InternalGrana.g:11086:1: ( ruleNumber )
            // InternalGrana.g:11087:1: ruleNumber
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
    // InternalGrana.g:11096:1: rule__ElkBendPoint__YAssignment_2 : ( ruleNumber ) ;
    public final void rule__ElkBendPoint__YAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11100:1: ( ( ruleNumber ) )
            // InternalGrana.g:11101:1: ( ruleNumber )
            {
            // InternalGrana.g:11101:1: ( ruleNumber )
            // InternalGrana.g:11102:1: ruleNumber
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
    // InternalGrana.g:11111:1: rule__Property__KeyAssignment_0 : ( rulePropertyKey ) ;
    public final void rule__Property__KeyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11115:1: ( ( rulePropertyKey ) )
            // InternalGrana.g:11116:1: ( rulePropertyKey )
            {
            // InternalGrana.g:11116:1: ( rulePropertyKey )
            // InternalGrana.g:11117:1: rulePropertyKey
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
    // InternalGrana.g:11126:1: rule__Property__ValueAssignment_2_0 : ( ruleStringValue ) ;
    public final void rule__Property__ValueAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11130:1: ( ( ruleStringValue ) )
            // InternalGrana.g:11131:1: ( ruleStringValue )
            {
            // InternalGrana.g:11131:1: ( ruleStringValue )
            // InternalGrana.g:11132:1: ruleStringValue
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
    // InternalGrana.g:11141:1: rule__Property__ValueAssignment_2_1 : ( ruleQualifiedIdValue ) ;
    public final void rule__Property__ValueAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11145:1: ( ( ruleQualifiedIdValue ) )
            // InternalGrana.g:11146:1: ( ruleQualifiedIdValue )
            {
            // InternalGrana.g:11146:1: ( ruleQualifiedIdValue )
            // InternalGrana.g:11147:1: ruleQualifiedIdValue
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
    // InternalGrana.g:11156:1: rule__Property__ValueAssignment_2_2 : ( ruleNumberValue ) ;
    public final void rule__Property__ValueAssignment_2_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11160:1: ( ( ruleNumberValue ) )
            // InternalGrana.g:11161:1: ( ruleNumberValue )
            {
            // InternalGrana.g:11161:1: ( ruleNumberValue )
            // InternalGrana.g:11162:1: ruleNumberValue
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
    // InternalGrana.g:11171:1: rule__Property__ValueAssignment_2_3 : ( ruleBooleanValue ) ;
    public final void rule__Property__ValueAssignment_2_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // InternalGrana.g:11175:1: ( ( ruleBooleanValue ) )
            // InternalGrana.g:11176:1: ( ruleBooleanValue )
            {
            // InternalGrana.g:11176:1: ( ruleBooleanValue )
            // InternalGrana.g:11177:1: ruleBooleanValue
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
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x000000000C200000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000000C200002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x6000000000000080L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x8000000000400000L,0x0000000000000001L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000004000000010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000004000000012L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000004000030010L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000400000L,0x0000000000000001L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000002D00000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00000000A0000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000020000000080L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x00086E0000000080L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x00082C0000000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000090L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x00004A0000000080L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0006000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000060L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0010000200000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000010200000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x09E0000000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x01E0000000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0200000000000080L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0400000000000002L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0010800000000000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x000000000000E0F0L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x01E0000000000002L});

}
